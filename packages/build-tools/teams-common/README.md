Microsoft Teams iOS Client
======================

### Setup
Install the following
* [Node](https://nodejs.org/en/download/)
* [Yarn](https://classic.yarnpkg.com/en/docs/install/#mac-stable). We need version 1.19.1
<br/>`yarn set version 1.19.1`
* [better-vsts-npm-auth](https://www.npmjs.com/package/better-vsts-npm-auth?activeTab=readme)
* Obtain `Read` permissions for the `teams-mobile-platform` NPM registry by
   reaching out on **Teams Mobile Platform > Issues** channel.

### Build SDK and SDK Example App
Run the following commands. Check `package.json` for more details.
```
cd sdk/teams-mobile-sdk
better-vsts-npm-auth -config .npmrc
yarn install
yarn build
cd ../teams-mobile-sdk-example
yarn upgrade teams-mobile-sdk
yarn build
```

### Run SDK Example App
This will load SDK Example App (displayed as `Runner App`) in App Tray of Teams app.

#### Run on simulator
iOS: `yarn ios-sim`
<br/>Android: `yarn android`

#### Run on device
iOS: `yarn ios-device`
<br/>Android: `yarn android`

### Troubleshooting
* Make sure Teams app is installed and was run at least once.
* Make sure Teams app is not active while executing yarn command to run example app. Example app will not show up in app tray otherwise.
* React Native debug mode can be used only with SDK Example App. App crashes if you open other React Native apps like Wiki, Organization while React Native Debug mode is ON.
* If you see the following error on running `yarn install`,
   <br/>`error Couldn't find package <SOME-NPM-PACKAGE> on the "npm" registry.`
   <br/>run command `better-vsts-npm-auth` to resolve the issue.

