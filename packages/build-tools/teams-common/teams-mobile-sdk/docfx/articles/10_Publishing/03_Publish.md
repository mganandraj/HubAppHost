# Publishing to CodePush

## Setup Publish Script
First, add the following to your app's package.json:

```json
{
  "scripts": {
    // ...
    "publish-app": "publish-app --config codepush.json"
  }
}
```

Create a codepush.json file in your project root. This file should contain the following:

```json
{
  "accessToken": "<accessToken>",
  "appName": "<ownerName>/<appName>",
  "deployment": "<deploymentName>",
  "description": "Example Description",
  "additionalArgs": [
    "--debug"
  ]
}
```

* **accessToken:** App Center [access token](https://docs.microsoft.com/en-us/appcenter/distribution/codepush/cli#access-tokens). Optional.
    * If not provided, the publish script will assume the CLI is already logged in and will attempt to publish the update.
    * Generate access token from [Settings page in App Center](https://appcenter.ms/settings/apitokens)
* **appName:** App Center app identifier in the format: `<ownerName>/<appName>`.
* **deployment:** Deployment to which the update will be pushed.
* **description:** Optional. Description about release version. SDK will automatically concat current app version.
* **additionalArgs:** Any additional arguments to be passed to the `appcenter codepush release` script. Optional.
    * See [here](https://docs.microsoft.com/en-us/appcenter/distribution/codepush/cli#releasing-updates-general) for full list of supported arguments.
    * Note that Teams does not support `--private-key-path`.

## Publish App
After a successful build, publish the app with the following command:

`yarn publish-app`

## Special Considerations

* Updates marked as a "Required Update" trigger a clean install regardless of the currently installed version. 
    * **Please use this option sparingly.**
