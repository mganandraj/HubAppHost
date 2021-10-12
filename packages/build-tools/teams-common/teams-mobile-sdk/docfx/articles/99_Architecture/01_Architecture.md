# Architecture
> [!IMPORTANT]
> The Teams React Native SDK is currently available for **Microsoft INTERNAL USE ONLY**.
> Do not share or expose any information on this site to anyone outside of
> Microsoft.

This document describes the internal architecture of the Teams React Native SDK
for curious souls. The information in this document is not strictly necessary if
you just want to develop an app. Although, it can help you understand more about
the platform and evaluate whether it meets your needs.

## Overview
![architecture diagram](~/images/TeamsRNSDKArchitecture.png)

The Teams React Native SDK is simply a layer that exposes parts of Teams core 
(and, in some cases, native functionality like Camera) to the Javascript layer
via [native modules](https://reactnative.dev/docs/native-modules-ios). This
layer also takes care of some housekeeping stuff like creating and invalidating
bridge instances and downloading/updating app packages from CodePush. Note that
each app has it's own bridge instance. This helps achieve sandboxing for apps up
to an extent.

In the JS layer, the `teams-mobile-sdk` npm package provides some useful wrappers
around the APIs exposed by the native modules. One example is `TeamsView` which
is discussed later. It is extremely lightweight as all the heavy lifting is done
by the native modules. The following [react-native-community](https://github.com/react-native-community)
modules are also supported:
1. `async-storage`
2. `react-native-webview`
3. `react-native-netinfo`

Since we use vanilla React Native, most of the things you expect to work in any
other React Native app should work exactly the same way with the Teams SDK. If
you understand the React Native architecture then you already understand most of
the Teams SDK architecture. The rest of this page discusses the noteworthy things
that have been done to use React Native as a platform for multiple apps.

## App bundle
The app bundle is simply a zip file that contains the following:
1. `index.android.bundle` and `index.ios.bundle`: These are generated using the
    React Native CLI.
2. App manifest: Every app has to define a `manifest.json` which contains some
    metadata about the app such as app ID, icon, localized name, list of views
    in the app etc.
3. Static resources like images etc. For e.g. the app icon.

## CodePush
CodePush is used to host and serve the bundles to the Teams app. The developer
_cannot_ choose to serve app bundles from any arbitrary location. One advantage of
CodePush is that developers can update their app independently without relying
on the native app release cycle (as long as the update does't need native
changes). Please see the [CodePush documentation](https://docs.microsoft.com/en-us/appcenter/distribution/codepush/) to know more.

### Versioning
CodePush brings some versioning capabilities which helps download a bundle that
is compatible with the native app. For e.g. an old version of the Teams app that
is not be able to run the latest version of an RN app should be served an older
bundle that is compatible.

To achieve this, two things are needed. Firstly, while uploading the bundle to
CodePush, the app developer should specify which _SDK versions_ the bundle can
be run on. This is called "Target Versions" in the CodePush UI. It can be a
range like `0.7.1-0.7.x`.

> [!NOTE]
> _SDK version_ here refers to the version of the `teams-mobile-sdk` npm package
> that the RN app uses.

Secondly, when the Teams native app makes a request to CodePush it also sends
the SDK version it supports. With these two pieces of information, the CodePush
service is able to decide which version of the bundle to serve. If the native
app supports multiple SDK versions, then multiple requests to CodePush are made.
For e.g. let's say the native app supports the versions: `[0.7.2, 0.7.1, 0.7.0]`.
In the first request to CodePush, the highest version is sent (in this case
0.7.2). If a compatible bundle is found, that bundle is used and no further
requests are made to CodePush. In case no compatible bundle is found for 0.7.2,
another request is made: this time with SDK version 0.7.1. The process continues
so on in the decreasing order of supported versions until either a compatible
bundle is found or all of the versions are exhausted. If, at the end, no 
compatible bundles are found, the app is not shown in Teams.

The above process is performed upon every launch of Teams app to check for new
bundles.

### Which apps to download?
![architecture diagram](~/images/codepush.png)

The Teams app uses the user entitlements received from the middle tier to know
which apps to list/show for the user. This is for all apps built using the Teams
platform and not just React Native apps. Here's what an app definition looks
like:

```json
{
    "manifestVersion": "1.0",
    "version": "0.1.16",
    "categories": [
        "Microsoft",
        "Productivity"
    ],
    "developerName": "Microsoft Corp.",
    "developerUrl": "http://www.microsoft.com/",
    "privacyUrl": "https://privacy.microsoft.com/en-us/privacystatement",
    "termsOfUseUrl": "https://www.microsoft.com/en-us/servicesagreement",
    "validDomains": [],
    "permissions": [],
    "mobileModules": [{
        "id": "9a1f0cd2-ff89-443f-9618-01993b1c5ff0",
        "title": "Hello World!",
        "type": "reactNative",
        "targetSdkVersion": "0.1.2018061304",
        "rnPackageUrl": "codepush://teams-mobile-sdk-example/deploymentKey=_uVIQTiFhfURM3ryjiyjgWE",
        "icons": {
        "default": "assets://app_icons/teams-mobile-sdk-example/icn_default.svg",
        "selected": "assets://app_icons/teams-mobile-sdk-example/icn_selected.svg"
        }
    }],
    "id": "9a1f0cd2-ff89-443f-9618-01993b1c5ff0",
    "name": "Hello World!",
    "shortDescription": "Showcases the Teams Mobile React Native SDK.",
    "longDescription": "Showcases the Teams Mobile React Native SDK.",
    "smallImageUrl": "assets://app_icons/teams-mobile-sdk-example/icn_default.svg",
    "largeImageUrl": "assets://app_icons/teams-mobile-sdk-example/icn_default.svg",
    "accentColor": "#7719aa"
}
```

React native apps are identified by `type = "reactNative"` in the `mobileModules`
array. The `rnPackageUrl` property gives the deployment key for the app. The
deployment key is used by CodePush to uniquely identify an app.

## Bridge creation
Once the bundles have been downloaded, the next order of business is to create 
bridge instances. Several things happen when a bridge is initialized:
1.  All declared native modules  get initialized.
2.	Module source code is loaded from `index.android.js`/`index.ios.js` and is 
    executed.

One instance of bridge is created per app. So, each app will have its own
instance of each native module. Bridge creation is done in a lazy manner. It 
can be triggered by:
1.	User opening the module for the first time
2.	When a contact card extension is used
3.	When share extension is used

Note that at this point no views have been rendered yet. This means that JS has 
not yet received any of the application context level information such as: 
current logged in user, current locale and current theme. Opening and rendering
of a view will be examined in the next section.

##	Rendering a view
<p align="center">
  <img src="~/images/shell_and_canvas.png" height="750"/>
</p>

Let’s examine what happens when the user opens a module. For this discussion, 
let’s assume that the module that is being opened is in the app drawer.  When 
the user taps on the module icon to open it, a new `Activity` / `UIViewController`
(called the host for this view) is created. This host has a unique id attached 
to it called the host instance ID. All the information required about the module
being opened is passed on to this host. This information includes all the 
metadata specified in the manifest. The view definition that appears first in 
the views property in the manifest is considered as the default view for the
module. It is this view that is opened when the user opens the module.

Since all views are React components, props can be passed to them. The SDK 
passes the following set of props to every view.
1.  Application parameters (henceforth referred to as _app params_) – These 
    parameters define the current application context. It has three things: the 
    current user details, the current locale and the current app theme.
2.  Host parameters (henceforth referred to as _host params_) – These parameters 
    define the current host. Contains only the host instance ID.
3.  View parameters (henceforth referred to as _view params_) – These are the 
    parameters that have been passed to the current view. These can come from 
    multiple sources:

    1. Default parameters specified in manifest
    2. `openView`/`openViewForResult` calls
    3. `openModule` calls
    4. Share extension data

Note that these props are not part of the public API and are not meant to be 
directly used by the developer. The SDK provides various convenience methods to
access the information contained in these props. This is the main purpose of
TeamsView and that’s why all views must extend it.

These components are rendered inside the platform specific native views:
`ReactRootView` (Android) and `RCTRootView` (iOS).

##	Navigation
The SDK exposes a set of navigation functionality that the React Native modules
can leverage to provide a native-like navigation experience i.e. similar to
push/pop of a View Controller or start/finish of an Activity.

###	Opening views
There are two methods available for opening new views:

1.	`openView(viewID, params)`
2.	`openViewForResult(viewID, params, callback)`

Both methods open the view denoted by `viewID` and pass params to it. The only
difference between the two is that the latter expects a result to be returned by
the opened view. The callback will be invoked with the returned result. When a
view is opened using the opened view needs to invoke `closeViewWithResult()` to\
be able to pass back the result.

The params for the new view are never sent over the bridge to avoid any
serialization issues that might occur. To understand this better, let’s examine
what happens when `openViewForResult(viewID, params, callback)` is called:

1.  The params and callback received are stored in a map with a freshly 
    generated UUID as the key. Let’s call this key `viewParamsKey`.
2.  The `viewID` and `viewParamsKey` are sent to the native via a call to the 
    `openViewForResult()` method of the `NavigationService` native module. The
    view with the given `viewID` is found (by searching the manifest for the
    definition) and it is rendered in a new Activity/View controller.
    `viewParamsKey` is passed to the view as _view params_.
3.  During the view initialization (which happens in `TeamsView`), the actual 
    params and callback are retrieved from the map using the received 
    `viewParamsKey`. The developer is able to access the params by calling 
    `getViewParams()`. The callback is retained until `closeViewWithResult()` 
    is called.

###	Closing views
There are two methods available for closing views:

1.	`closeView(animated?)`
2.	`closeViewWithResult(result, animated?)`

Both methods close the current view. The second method is used to pass a result
back to the parent view.

The result to be returned to the parent view as well as the callback received 
with the `openViewForResult` calls are stored in a map in JS against the current
host instance ID. Then a call is made to the `closeView` method of the
`NavigationService` native module. This native module does the work of finishing
/popping the `Activity`/`UIViewController`. When this happens, an event named
`onHostClosed` is emitted from native to JS along with the host instance ID of
the host being closed. When the SDK receives this event, the result and callback
stored earlier are retrieved and the callback is invoked with the result as a
parameter.

## Interacting with the Teams shell
A view is able to interact with (and modify) the Teams shell it is in. For
example, changing the title, setting an options menu, setting a title dropdown,
showing action sheets, snackbars, alerts and so on.

There are two ways to communicate user interaction with these shell components
back to the view: events and callbacks. Both of them are used by the platform
(although events are more commonly used). Today, we have no clear set of best
practices regarding these.
