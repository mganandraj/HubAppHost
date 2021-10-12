# Version 0.9.1

# Version 0.9.0
## Updated
1. Updated the following dependencies
 - react-native - 0.64.2
 - react-native-async-storage/async-storage - 1.15.5
 - react-native-community/datetimepicker - 3.4.6
 - react-native-community/netinfo - 6.0.0
 - react-native-picker/picker - 1.9.11
 - react - 17.0.1
 - react-native-svg - 12.1.1
 - react-native-webview - 11.4.2

# Version 0.8.3
## Added
1. Added `isAppPinned`(whether teams app is pinned) to `TelemetryInfo`.
2. Added `FEDERATED_CHATS`, `NO_SMS_USERS`, `NO_SMS_CHATS` filters in TeamsPickerComponent. 
3. Added support for teacher/student license for `getUserLicense` API in `UserInfoProviderInterface`.
4. Added support for `chatTypes` and `isChatCreatedByConsumerAccount`(chat owner type) in `ChatsProviderModule`.

## Updated
1. Updated the RN container view in `ReactNativeApps.storyboard` (iOS) by removing the fixated safe area layout guide in the native layer.

# Version 0.8.2
1. Added `PeopleProvider` API to support fetching details about a Person's details, presence info, profile photo, or contact metadata.
2. Added `pickShareTargetAndShareObject` API with ShareObject as parameters in `IShareUtilsModule`.
3. Added `getAccentColor` API support for RN apps which want to use access color from appDefinition to be used in their apps.

# Version 0.8.0
## Added
1. Added `setUpTabsWithDefaultSelectedTab` API in TeamsShell to support selecting any tab as a default tab in tabbed components.
## Updated
1. Updated React Native version to 0.62.2
2. Updated Async Storage to 1.12.1
3. Updated Webview to 11.0.3
4. Updated React Native Svg to 12.1.0
5. Updated React Native NetInfo to 5.9.10

## Removed
1. Removed React version from peer dependency

# Version 0.7.6
## Added
1. Added `getUserLicense` API in `UserInfoProviderInterface`.
2. Added `shareToShareTarget` API in `IShareUtilsModule`.
3. Deprecated `shareImagesToShareTarget` API and image sharing is supported using `shareToShareTarget`
4. Added `executeDeepLink` API in DeepLinkUtils
5. Added `viewStartTime`(Start time of a view) to `TelemetryInfo`.
6. Added `SecureStorageInterface` which has APIs to store, retrieve and remove key-value pairs securely using encryption/decryption.

# Version 0.7.5
## Added
1. Added tenantSiteUrl (SharePoint Url) to User properties.
2. Added support of App Definition for runner mode

# Version 0.7.4
## Added
1. Added support for fluent icons. Apps can now leverage the &lt;FluentIconView&gt;
   component and `fluent://` URI scheme to specify fluent icons.

# Version 0.7.3
## Updated
1. Updated lodash version to 4.17.20
2. Added `threadType`, `threadId` and `threadMembers` params in UserBIEvent.

# Version 0.7.2
## Updated
1. Upgraded dependencies version
2. Replace `gulp-util` dependency with `ansi-colors`

# Version 0.7.1
## Added
1. Added API `tabUpdatedForThreads` in `ChatsProviderInterface`.
2. Added `getSCSEndpoint` API in `EndpointProviderInterface`.
3. Added `AdaptiveCard` support in `TeamsMessagingRequest`.
4. Added accessibilityLabel prop for TeamsPicker.
5. Added one to one chats prop in TeamsPickerComponent.
6. Added boolean for showing presence indicator in UserAvatarComponent.
7. Refined JSON structure and implementation of sendig Adaptive Card.

# Version 0.7.0
## Added
1. Added `getMembersDetailsForChats` API in chatsProviderInterface.

## Updated
1. Updated React Native version to 0.61.5
2. Updated React version to 16.12.0
3. Updated Async Storage to 1.6.3
4. Updated Webview to 7.5.1
5. Updated React Native Svg to 9.13.3
6. Udpated React Native NetInfo to 5.3.3
7. Added `getSkypeToken` API in `AuthenticationServiceInterface`.


# Version 0.6.5
1. Added `chatsProviderInterface` which has APIs for getting metadata of chats and tabs in chats. And also an API to get tabs sync state in IOS.

# Version 0.6.4
1. Added `setHomeIndicatorBackgroundColorIOS` to provide ability to change background color of home indicator in iOS X+ devices.
2. Added `getContactSyncState` in `ContactsProviderInterface` to expose the sync state of contacts in 
3. TeamsPicker changes to handle contacts.
4. Added `getTeamsAndChannelsInitialSyncStateIOS` API, and also added `SyncEventListener` class to register for different native events.
5. Yarn version upgraded to 1.19.1.

# Version 0.6.3
1. Added autoFocus prop for TeamsPicker
2. Added `ConfigurationManager` module to log in different aria tenant.
3. Added `setSubtitle` to provide support for subtitle.
4. Added `setTitleWithCallBack` to provide actionable title.
5. Added support for opening of contact card for a people contact.

# Version 0.6.2

# Version 0.6.1
## Added
1. Added `takePhotoWithCustomizedOfficeLensCamera` API in ImageAndFilePicker.
2. Added `compressImage` API in ImageAndFilePicker.
3. Added `androidForceSyncTabsForChannels` API in TeamsAndChannelsProviderInterface

# Version 0.6.0
## Changes: This is related to React Native version upgrade
1. React Native version upgraded to 0.59.10
2. React version upgraded to 16.8.3
3. React Native Svg version upgraded to 9.5.1
4. Async Storage has been added as a separate dependency

# Version 0.5.7
## Added
1. Added `openMeetingDetails` API in TeamsModule.
2. Exposed `tabDefinitionJson` entity from TeamsAndChannelsProvider.
3. Added `optimizeImageForNetworkUpload` method to ImageAndFilePickerInterface.

# Version 0.5.6
## Added
1. Added tab support for appbar(Android)/navigationbar(iOS)
2. Added `EndpointProvider` module to fetch endpoint URLs from native side.

# Version 0.5.5
## Added
1. Warning when user is trying to publish a bundle which has alpha version of teams-mobile-sdk

## Fix
1. Crash issue when opening TeamsWebView in HelloWord App

# Version 0.5.4
## Added
1. Added "ViewLifecycle". Now any view can override "onViewAppear" method to perform some action when the view comes into focus.

# Version 0.5.3
## Added
1. Added "reload" functionality in TeamsWebView. This will refresh the content of WebView when required.

## Fixed
1. #476098: teams view openView api randomly stops working on android/ios

# Version 0.5.2
## Changes
1. Changed the "assets-dest" parameter in PackageUtils.createReactNativeBundle(), assets and raw folders no longer placed inside another parent assets folder.
2. Added support to copy any file(s)/folder(s) in `resources/` to `build/resources`, retaining their previous nomenclature. Note this treatment does not apply to `strings` resources, they are treated the same as before.

# Version 0.5.1
## Added : new features
1. Added `openChatWithUserMris` API in TeamsModule to open 1:1 or group chat with user ID(s).
2. Introduced back navigation handler. Apps can now intercept back navigation and choose to proceed or stay
on the same screen (based on user input).
3. Added `ShareUtils` module to provide sharing related APIs. Currently supports picking a recipient (aka `ShareTarget`) and sharing images to a recipient (i.e. `ShareTarget`).
4. Added postMessage in TeamsModule to post message with @mention, files & hyperlink support.

# Version 0.5.0
## Changes
### Major re-architecture
1. Base view `TeamsView` for all views
2. Various initializations are now internal
3. Manifest structure changed: `modules` discontinued and more.
4. `setTitleDropdown` API introduced.
5. Moved all documentation to docfx.

See documentation article "Migrating to 0.5.0" for more info.

### Teams web view
* `TeamsWebView` now supports URLs & asset files. Use the `uri` property of the `source` prop. For asset files you can `require` the file 'into' the `source` prop like so: `<TeamsWebView source={require('path/to/your/file.html')} ... />`.

# Version 0.4.1
## Changes : existing functionality that is now different
### Logging
Added compiler option to generate source maps for RN bundles when packaging the app.

### Image and File Picker
File metadata is passed as `ImageAndFilePicker` response.

# Version 0.4.0
## Changes : existing functionality that is now different
### Version Compatibility
Support for `minReactNativeSdkVersion` and `targetReactNativeSdkVersion` is added.
Support for `targetNativeSdkVersion` is removed.

### TeamsWebView
* `onLoad`, `onLoadStart`, `onLoadEnd`, `onError` events are exposed.
* `injectJavascript` function exposed. 

# Version 0.3.3
Bug fixes.

# Version 0.3.2
o365 groupId support added in `TeamsAndChannelProvider`.

# Version 0.3.1
Minor theme related changes published.

# Version 0.3.0
## Highlights
Following libraries has been upgraded to latest version :
react               :16.6.1
react-native        :0.57.5
react-native-svg    :8.0.8
typescript          :3.1

## Added : new features
Added `TelemetryClient` to the SDK to log userBI events and Scenario Events to Teams ARIA

# Version 0.2.0 (15 November 2018)
## Added : new features
### File Preview
`SharepointFilePreview` module is added to preview sharepoint files.

### Navigation
`openView`, `openViewForResult`, `closeView` and `closeViewWithResult` methods are added for in-app navigation.

### Floating Action Button (Android Only)
`FabLayout` is exposed to display Floating Action Buttons.

### Channel Picker
`ChannelPicker` module is added to select a channel from a list of channels.

### Image and File Picker
`ImageAndFilePicker` module is added to take photo / pick photo / pick document from device.

### Date Time Picker (Android Only)
`DateTimePickerAndroid` module is added to select Date and Time.

### Theme
Support for Dark Theme has been added.

## Changes : existing functionality that is now different
### Navigation
`openModule` API is updated to support generics.


# Version 0.1.0
### TeamsPickerView
This new component exposes a customizable search bar with auto suggest for people, teams, and channels.

Please refer to the documentation for all available options. You can see it in action within the example app's UserAvatarComponent and the new Organization module.

### ActionSheet
Mobile modules can now display ActionSheets (aka BottomSheets) with TeamsShellInteractor.

### Teams and Channels API 
Mobile modules can now access a user's teams and channels via TeamsAndChannelsProvider.

### Set background color
It is now possible to set the background color of a module's native container with TeamsShellInteractor.

### SDK enchancements
* Added TypeScript declaration file.
* Documenation has been reorganized by topic and now matches a consistent style.
* Update example app to follow best practices.
* `publish-app` now properly respects `additionalArgs`.
* Added watch mode.
