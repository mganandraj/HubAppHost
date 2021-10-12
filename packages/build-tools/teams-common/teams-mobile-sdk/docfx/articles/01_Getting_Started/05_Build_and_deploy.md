---
uid: build_and_deploy 
---

# Build and deploy
Teams Mobile SDK provides the following tools to help developers build their apps:

1. `build-resources`: Checks, sanitizes & packages the resources, strings, images, etc. from your app's resources folder. 
    * **Note:** `tsc`  must be separately called to transpile TypeScript code to JavaScript.
2. `sdk-config`: Configures parameters needed to run the SDK tools.
3. `install-teams`: Installs a dev debug build of Teams app on your device. It downloads the stable version of the Teams dev app running the current SDK version from App Center. 
    * **Note:** Steps to get user API token from App Center:
        1. Raise a request in the [Issues](https://teams.microsoft.com/l/channel/19%3ad28312f3bee944a4a32d08f0865f0bbe%40thread.skype/Issues?groupId=5d549106-0ad3-4180-8657-efe0e41938a9&tenantId=72f988bf-86f1-41af-91ab-2d7cd011db47) channel for access.
        2. Go To [App Center Settings](https://appcenter.ms/settings/apitokens) -> New API Token -> Create user API token with full access or use an active user API token which has full access.
    * Configure user API token in Teams Mobile SDK
        `yarn sdk-config -k userApiToken -v <your-token>`
4. `run-dev`: Runs your app in dev mode inside the downloaded Teams dev app.
    * **Note:** `install-teams` must be run at least once prior to running your app.
5. `package-app`: Creates deployable package of your app. Includes both Android and iOS bundles.
6. `publish-app`: Publishes app to CodePush for release. See Publishing documentation for more info.

These tools can be found in the following location:

* **yarn:** `node_modules/.bin`
* **npm:** `node_modules/teams-mobile-sdk/bin`

Below is a sample snippet of the script definitions to be configured within an app's `package.json` file using Yarn:

```json
{
  "scripts": {
    "clean": "rimraf/bin.js ./build",
    "clean-sdk-cache": "rimraf/bin.js ./.sdk_cache",
    "package-app": "package-app",
    "build": "yarn clean && build-resources && tsc",
    "lint": "tslint --project tsconfig.json",
    "lintautofix": "tslint --project tsconfig.json --fix",
    "sdk-config": "sdk-config",
    "install-teams": "install-teams",
    "publish-app": "publish-app --config codepush.json"
  },
}
```

The following actions can now be performed:

1. **Build:** `yarn build`
2. **Clean build:** `yarn clean`
3. **Run TSLint(*See configuring TSLint section below*):** `yarn lint` or `yarn lintautofix`
    * See "Configuring TSLint" below.
4. **Install Teams debug app:** `yarn install-teams -p <Platform>`
    * For `<Platform>` , specify `ios` or `android`.
5. **Package app:** `yarn package-app`.

## Configuring TSLint
It's good idea to use TSLint if using TypeScript.

1. Install TSLint tool and TSLint standard rules: `yarn add --dev tslint tslint-config-standard`
2. Define TSLint rules to be used for the app in a file named `tslint.json` in the app root directory. 

### Example
```json
{
  "extends": ["tslint-config-standard"],
  "env": {
    "node": true
  },
  "rules": {
      "semicolon": [true, "always"]
  }
}
```

## Test your app inside Teams
1. Build your app: `yarn build`
2. Install Teams debug app: `yarn install-teams -p <platform>`. This will download the latest Teams debug app for Android or iOS device from App Center and installs it on your device. This only needs to be done once.
3. Teams app will launch on the device. Please login and navigate to the main page. 
	  * If the app is rootable, i.e. it defines a `rootComponent` module, the app will be listed in the left hamburger menu's "More" section. Click on the app in the hamburger menu to launch it.
	  * If the app provides contact extensions, open the contact card of a user provided by the app to test the provider module.
    * If the app provides share extensions, share text or image(s) (as applicable) to Teams to test the share target module.

>[!NOTE]
> In case you are testing with an Android device, make sure USB debugging is turned on for the device. For more on the topic click [here](https://developer.android.com/studio/debug/dev-options).
