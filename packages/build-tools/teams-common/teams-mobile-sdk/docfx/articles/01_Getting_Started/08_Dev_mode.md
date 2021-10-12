# Dev Mode / Runner Mode

## Step 1 : Install dev dependencies
* Install Concurrently: `yarn add --save-dev concurrently`

## Step 2 : Update npm scripts 
Add following scripts in package.json
```json
{
  "scripts": {
    "start-android": "adb reverse tcp:3333 tcp:3333 && adb reverse tcp:8081 tcp:8081 && adb shell am start -n \"com.microsoft.skype.teams.dev/com.microsoft.skype.teams.Launcher\" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER --esn \"sdkRunner\"",
    "start-rn": "node ../node_modules/react-native/local-cli/cli.js start --root .",
    "watch": "tsc --watch",
    "android": "concurrently \"yarn watch\" \"yarn run-dev\" \"yarn start-rn\" \"yarn start-android\""
  }
}
```

## Step 3 : Install Teams app

* Assumptions:
    1. Only one Android/iOS device/simulator is connected
    2. You have access to Teams app on App Center.
    3. Phone is connected to computer via USB.

### On Android phone/simulator

Make sure USB debugging is turned on for the device. For more on the topic click [here](https://developer.android.com/studio/debug/dev-options).

* Method-1 : Using `install-teams` command (Recommended)
    - Run `yarn clean-sdk-cache` (if required, to remove older apk from cache)
    - [Setup user API token](05_Build_and_deploy.md) (if installing first-time or cleared cache)
    - Run `yarn install-teams -p 'android'`

* Method-2 : Using browser on device
    - Go to [App Center](https://install.appcenter.ms/orgs/thomas-olsen-er92/apps/Microsoft-Teams-ARM-Canary)
    - Download the latest version by clicking `Download` option 

### On iPhone

Follow https://microsoft.sharepoint.com/teams/appledev to prepare your device for development and device debugging.

Method 1 is easy if you can install the various dependencies required. If you can't
or don't want to, you should follow method 2.

* Method 1: Using the `install-teams` command
    - (FIRST TIME ONLY) Before installing on iPhone, make sure you have run the following commands to install certain libraries:

      ```
      brew update
      brew uninstall ideviceinstaller
      brew uninstall libimobiledevice
      brew uninstall --ignore-dependencies usbmuxd
      brew install --HEAD usbmuxd
      brew unlink usbmuxd
      brew link usbmuxd
      brew install --HEAD libimobiledevice
      brew link --overwrite libimobiledevice
      brew install ideviceinstaller
      brew link --overwrite ideviceinstaller
      ```

    - Run `yarn clean-sdk-cache` (if required, to remove older apk from cache)
    - [Setup user API token](05_Build_and_deploy.md) (if installing first-time or cleared cache)
    - Run `yarn install-teams -p 'ios'` 

* Method 2: Directly getting it from App Center
    - Go to [Teams iOS Canary on App Center](https://install.appcenter.ms/orgs/thomas-olsen-er92/apps/Microsoft-Teams-Canary-iOS)
    - Download the latest version by clicking `Download` option

  **Note:**
    - If you are unable to access `Microsoft Teams Canary` app i.e. cannot see the app on App Center then you need to be added as a 'Tester'. Ask `Teams Mobile Platform` team to add you as a 'Tester'.
    - If you see `Untrusted Enterprise Developer` error, Tap Settings > General > Profiles or Profiles & Device Management. Under `Enterprise App`, you see a profile for the developer. Tap the name of the developer profile under `Enterprise App` to establish trust for this developer. Then you see a prompt to confirm your choice.

### On iPhone simulator 
App Center builds don't work on iPhone simulator. So, if you have to test something
on a simulator you need to build the Teams iOS codebase on your machine using
Xcode.

Repository link: [Teamspace-iOS](https://domoreexp.visualstudio.com/Teamspace/_git/Teamspace-iOS)
Setup instructions are available in the README file at the root of the repo.

## Step 4 : Run in Dev Mode
Ensure your metro server and the SDK dev server are running.

```
yarn start-rn // start metro server
yarn run-dev // start SDK dev server
```

Also ensure your device and your development machine are on the same network
and can talk to each other.

### For Android using phone/emulator
* Phone can be connected to computer via USB or WiFi
* Steps:
  - Open the app and sign-in
  - Close the app
  - Run `yarn android` command [**Note:** Always close the app before running this command]
  - Grant permission to `Allow display over other apps`, if asked
  - In case of any issue, re-run step-2 and step-3

### For iOS

> [!NOTE]
> The settings mentioned in this section are not persisted across app runs. If you kill the
> Teams app, the below steps need to be performed again for "Runner App" to show
> up in the app tray.

1. Open the Teams Canary build downloaded in the previous step
2. Log in if not already done
3. Tap on the profile picture on the top left corner and select "Settings"
4. On the Settings screen, select "Dev settings".

   <img src="~/images/DevSettingsInSettings.jpg" width="60%">

5. In the "React Native development" section, select "Local development"

   <img src="~/images/LocalDevelopmentInDevSettings.jpg" width="60%">
   
6. Turn on "Enable local development"

   <img src="~/images/LocalDevelopmentSettingsPage.jpg" width="60%">

7. Configure the IP address of your SDK dev server and Metro server. In most cases this is the
   same as your development machine's IP address
8. Tap on "Start". You will see a toast on the top of the screen as shown below.

   <img src="~/images/RunnerModeBundleLoading.jpg" width="60%">

   Once the JS is loaded, you can exit the Settings screen and you will find
   "Runner App" in the app tray.
  
   <img src="~/images/RunnerAppInTray.jpg" width="60%">

**Troubleshooting common issues**

If you don't see the "Loading" toast and see an error, the following steps can
help you figure out the most common issues.

1. Ensure dev server and metro servers are actually running on the development machine.
   You can verify this by trying to access them from your browser at the url
   http://localhost:&lt;PORT&gt;/ (replace PORT with the actual port number:
   default is 3333 for dev server, 8081 for metro server).
2. Ensure your device can actually talk to these servers. You can verify this by
   trying to access them from your _device_ browser using the appropriate URL.
   For e.g. http://&lt;ip-address&gt;:&lt;port&gt;/ (use your dev machine's IP
   and the appropriate port number as described in the previous step). Remember,
   your development machine and your mobile device need to be on the same network
   and should be able to communicate with each other.
3. It is possible that your machine is configured to block certain ports and you
   might need to change these settings. You'll find many resources available online
   that tell you how to do this. Please refer to them and retry the above two steps
   to verify that everything works.
