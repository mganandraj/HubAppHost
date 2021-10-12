# Teams Mobile Platform Test Plan

## SDK Release
### Update Clients
- Update client apps with latest SDK example app.
- Update mobile module definitions with latest version and `targetSdkVersion`.

### Update teams-mobile-sdk
1. Update `teams-mobile-sdk/sdkconfig.json`
    * Update `devAppDownloadConfig.appVersion` to latest build version from App Center with above changes.
        [iOS app on App](https://install.appcenter.ms/orgs/thomas-olsen-er92/apps/Microsoft-Teams-Canary-iOS)
        [Android app on App](https://install.appcenter.ms/orgs/thomas-olsen-er92/apps/Microsoft-Teams-ARM-Canary)
2. Update release notes
    * `teams-mobile-sdk/README.md`

### Update teams-mobile-sdk-example
* Update `teams-mobile-sdk-example/codepush.json`.
    * Ensure `targetBinaryVersion` corresponds to latest `targetNativeSdkVersion` (format: `<=0.0.<targetNativeSdkVersion>`).

### Testing
1. Perform test pass (see below) and bug bash.

### Release SDK
1. Once PR(s) with above changes are merged, find the corresponding package version [here](https://domoreexp.visualstudio.com/Teamspace/Android/_packaging?feed=teams-mobile-platform&view=versions&package=teams-mobile-sdk&protocolType=Npm&_a=package).
2. Post release announcement to [Teams Mobile Platform > Releases](https://teams.microsoft.com/l/channel/19%3a37328f100bcc467ea951e7b2b363db39%40thread.skype/Releases?groupId=5d549106-0ad3-4180-8657-efe0e41938a9&tenantId=72f988bf-86f1-41af-91ab-2d7cd011db47) channel.

## Test Plan

### Fundamentals

| Description | Expectation |
| ----------- | ----------- |
| Module appears in app | Hello World module appears in hamburger menu with icon. |
| Opening module | Selecting module opens module to Home view. |

### View Picker

| Description | Expectation |
| ----------- | ----------- |
| Select view picker | Bottom sheet with available views appears. |
| Select view from picker | Selected view is displayed. |
| Select Home view from picker. | Home view is displayed. |

### Shell

| Description | Expectation |
| ----------- | ----------- |
| Toolbar items | Two toolbar items are displayed: moon and music note. |
| Tap toolbar item | Tapping toolbar items displays snackbar and sets title to "Home (updated)". |
| Tap snackbar "Dismiss" button | Snackbar is dismissed. |
| Tap "Close View" button | View is popped and previous View is restored. |
| Tap "Close View With Result" button | View is popped and previous View is restored with the callback. |

### Authentication

| Description | Expectation |
| ----------- | ----------- |
| Navigate to Authentication view | Authentication view appears. |
| Tap "Get Graph Access Token" button | Success message appears. |

### Navigation

| Description | Expectation |
| ----------- | ----------- |
| Navigate to Navigation view | Navigation view appears. |
| Tap "Open Contact Card" button | Own contact card appears. |
| Tap "Start Audio Call" button | Starts call with self and eventually fails. |
| Tap "Open Chat" button | Displays "something went wrong". |
| Tap "Open another view" button | Pushes a specific view in the navigation stack. |
| Tap "Open another view with result" button | Pushes a specific view in the navigation stack  and passes a callback. |
| Tap "Open Module via Deep Link" button | New instance of Hello World module is opened. |

### Device Contacts

| Description | Expectation |
| ----------- | ----------- |
| Navigate to Device Contacts view | Device Contacts view appears. |
| Tap "Get Device Contacts" button | 1. Contacts permission is requested if needed.<br>2. Total number of device contacts is displayed.<br>3. Three random device contacts are displayed. |

### User Avatar

| Description | Expectation |
| ----------- | ----------- |
| Navigate to User Avatar view | User Avatar view appears. |
| User avatar appearance | Own user avatar is displayed. |
| User name appearance | Own name is displayed. |
| User email appearance | Own email is displayed. |
| Tap user avatar | Own contact card is displayed. |
| Tap search box, search for another user, and select them. | Avatar, name, and email change to reflect selection. |

### Network Connectivity

| Description | Expectation |
| ----------- | ----------- |
| Navigate to Network Connectivity view | Network Connectivity view appears. |
| Tap "Get Network Availability" button | Current network status is displayed. |

### State Layout

| Description | Expectation |
| ----------- | ----------- |
| Navigate to State Layout view | State Layout view appears. |
| Tap "Loading" button | Animated spinner appears. |
| Tap "Empty" button | Icon, title, and description are displayed. |
| Tap "Available" button | Text view is displayed. |
| Pull to refresh on "React Native Text View" | Sync status bar will appear and disappear after ~5 seconds. |
| Tap "Error" button | Icon, title, description, and retry button are displayed. |
| Tap "Toggle Syncing" button | If state is "available", will toggle visibility of sync status bar. |

### HTTP Client

| Description | Expectation |
| ----------- | ----------- |
| Navigate to Http Client view | Http Client view appears. |
| Tap "Execute GET Call" button | Response with 200 status is displayed. |
| Tap "Execute POST Call" button | Response with 201 status is displayed. |

### Contact Search

| Description | Expectation |
| ----------- | ----------- |
| Navigate to Teams search view | Search view appears. |
| Search for any value | Own contact card appears. |
| Tap "People" tab | "Sample Contact 1" and "Sample Contact 2" appear within two groups:<br>1. "Group 1: Contacts from React Native"<br>2. "Group 2: Contacts from React Native"<br><br>Groups are above default Teams group. |
| Tap "Sample Contact 1" item | Contact card for "Sample Contact 1" is displayed. |

### Contact Card

| Description | Expectation |
| ----------- | ----------- |
| Navigate to "Sample Contact 1" contact card | Navigation view appears. |
| Contact card appearance | The following items are displayed:<br>1. Phone<br>2. Website<br>3. "Personal info" header<br>4. Greetings<br>5. Notes with links to MSN and Example App<br>6. Edit button |
| Tap "MSN" link | msn.com is opened in browser. |
| Tap "Open Example App" link | Hello World module is opened. |
| Tap edit button | Hello World module is opened with contact name displayed. |

### Share (Android only)

| Description | Expectation |
| ----------- | ----------- |
| Share text to Teams | If no default share target set, share target picker is shown. Otherwise, default share target is shown. |
| Tap "Share Example" target | Hello World module's Home view is displayed with the shared text. |
| Share image to Teams | If no default share target set, share target picker is shown. Otherwise, default share target is shown. |
| Tap "Share Example" target | Hello World module's Home view is displayed with the shared text. |
| Tap "Back to Share Targets" icon | Share target picker is displayed. |

### Date Time Picker (Android only)

| Description | Expectation |
| ----------- | ----------- |
| Navigate to DateTime Picker (Android) view | Date Time Picker view appears with current Date and Time. |
| Click on 'Show DateTime Picker' button | Dialog with Date Picker appears. |
| Select Date and click on Next button | Time Picker view appears. |
| Select Time and click on OK button | Snackbar should show selected Date and Time. |

### Action sheet

| Description | Expectation |
| ----------- | ----------- |
| Navigate to "Home" view | Home view appears |
| Observe the text below the "Show action sheet" button | It should read: "Last selected action sheet item: None" |
| Tap "Show action menu" button | Action menu is show with a title, subtitle and 2 options. Both options should have icons. |
| Select the 'Option 1' in the action sheet | The text below the "Show action sheet" button indicates that the first option was selected |
| Open the action sheet again and select the 'Option 2' in the action sheet | The text below the "Show action sheet" button indicates that the second option was selected |
| Open the action sheet and tap on the screen anywhere outside the action sheet | Action sheet is dismissed. |

### Teams and Channels API

| Description | Expectation |
| ----------- | ----------- |
| Navigate to "Teams & channels" view | View is rendered without any error or failure message shown on the screen. |
| Observe the teams and channels listed | Teams and channels list (including order of teams) is correct for the account being used. |

### Image And File Picker

| Description | Expectation |
| ----------- | ----------- |
| Navigate to Image And File Picker view | Image And File Picker view appears. |
| Tap "Take Photo with default camera" button | If camera not supported, error alert is shown. Otherwise, default camera app is shown |
| Take Photo and use photo | File identifier for photo taken is shown as alert |
| Tap "Take Photo with Office lens" button | Office lens camera is shown |
| Take single / multiple photo | File identifier for photo taken is shown as alert |
| Tap "Pick Photo" button | Device's gallery is shown |
| Select photo | File identifier for photo selected is shown as alert |
| Tap "Pick Document" button | Device's browser is shown |
| Select document from browser | File identifier for file chosen is shown as alert |

### Sharepoint File Preview

| Description | Expectation |
| ----------- | ----------- |
| Navigate to Sharepoint File Preview view | Sharepoint File Preview view appears. |
| Tap "File preview" button | File is previewed. |
| Tap "Image Preview" button | Image is previewed. |

### Channel picker

| Description | Expectation |
| ----------- | ----------- |
| Navigate to "Home" view | Home view appears |
| Observe the presence of the button "Launch channel picker" | The "Launch channel picker" button is present |
| Tap "Launch channel picker" | Channel picker appears |
| Select any channel in the picker | Upon selecting a channel, the channel picker closes automatically |
| Notice the text below the "Launch channel picker" button | The text should correctly indicate the channel selected in the previous step|

### Async Storage

| Description | Expectation |
| ----------- | ----------- |
| Navigate to Async Storage view | Async Storage view appears |
| Observe "DB Contents" text area at bottom | "DB Contents" shows correct database content at any point of time. |
| Fill "key" and "value" fields and tap "Set Item" button | The key-value pair appears in "DB Contents" |
| Fill "key" field from one of the keys present in "DB Contents" and tap "Get Item" button | The "value" field is populated with the correct value as present in "DB Contents" |
| Fill "key" field from one of the keys not present in "DB Contents" and tap "Get Item" button | The "value" field becomes blank |
| Fill "key" field from one of the keys present in "DB Contents" and tap "Remove Item" button | The key-value pair is removed from "DB Contents" |
| Fill "key" field from one of the keys not present in "DB Contents" and tap "Remove Item" button | No Change |
| Fill "key" from one of the keys present in "DB Contents", fill "value" field and tap "Merge Item" button | The updated key-value pair appears in "DB Contents" |
| Fill "key" from one of the keys not present in "DB Contents", fill "value" field and tap "Merge Item" button | The key-value pair appears in "DB Contents" |
| Tap "Get All Keys" button | The correct list of keys appears as an alert |
| Tap "Clear" button | "DB Contents" becomes blank |

## CodePush Test Plan

### Setup
The App Center app [Teams-Mobile-Platform/SDK-Test-Scenarios](https://appcenter.ms/orgs/Teams-Mobile-Platform/apps/SDK-Test-Scenarios/distribute/code-push) (sdk-test) has been setup with several variations of the SDK example app to facilitate testing. Before testing, add one of the following deployment keys to Teams:

* **Alpha:** `AbS1SvaGmtkHcdiTi3ti-1Y07NL1ryOESknGQ`
* **Beta:** `buBhKAl5-mLbKbt2Kvv7TOnEFNtrr1pVB1hGm`

The deployment keys have identical contents. Multiple are provided to allow for simultaneous testing across multiple platforms. Join [Teams Mobile Platform Team > Issues](https://teams.microsoft.com/l/channel/19%3ad28312f3bee944a4a32d08f0865f0bbe%40thread.skype/Issues?groupId=5d549106-0ad3-4180-8657-efe0e41938a9&tenantId=72f988bf-86f1-41af-91ab-2d7cd011db47) to request access.

### Fresh Install
#### None available
| Steps       | Expected Results |
| ----------- | ---------------- |
| 1. Disable all versions.<br>2. Clear app data, launch Teams, and login. | 1. No sdk-test version is downloaded.<br>2. Non-existent manifest fails to parse.<br>3. Placeholder icon/text is shown in hamburger menu.<br>4. Opening sdk-test shows error module. |

#### Success
| Steps       | Expected Results |
| ----------- | ---------------- |
| 1. Enable only v1.<br>2. Clear app data, launch Teams, and login. | 1. sdk-test v1 (0.0.1) downloads and installs.<br>2. sdk-test icon/text ("Test (0.0.1)") is shown in hamburger menu.<br>3. Opening sdk-test shows example app with "Home (0.0.1)" title. |

#### Failure
*See Rollbacks*

### Updates
#### None available
| Steps       | Expected Results |
| ----------- | ---------------- |
| 1. Enable only v1.<br>2. Clear app data, launch Teams, and login.<br>3. Quit Teams and reopen. | 1. No sdk-test update is downloaded.<br>2. sdk-test icon/text ("Test (0.0.1)") is shown in hamburger menu.<br>3. Opening sdk-test shows example app with "Home (0.0.1)" title. |

#### Success
| Steps       | Expected Results |
| ----------- | ---------------- |
| 1. Enable only v1.<br>2. Clear app data, launch Teams, and login.<br>3. Enable v2.<br>4. Quit Teams and reopen. | 1. sdk-test v2 (0.0.2) downloads and installs.<br>2. sdk-test icon/text ("Test (0.0.2)") is shown in hamburger menu.<br>3. Opening sdk-test shows example app with "Home (0.0.2)" title. |

#### Failure
*See Rollbacks*

### Rollbacks
#### None available
| Steps       | Expected Results |
| ----------- | ---------------- |
| 1. Enable only v3.<br>2. Clear app data, launch Teams, and login. | 1. sdk-test v3 (0.0.3) downloads.<br>2. Malformed manifest (missing modules/views) fails to parse.<br>3. Placeholder icon/text is shown in hamburger menu.<br>4. Opening sdk-test shows error module. |

#### Success
| Steps       | Expected Results |
| ----------- | ---------------- |
| 1. Enable only v2.<br>2. Clear app data, launch Teams, and login.<br>3. Enable v3.<br>4. Quit Teams and reopen. | 1. sdk-test v3 (0.0.3) downloads.<br>2. Malformed manifest (missing modules/views) fails to parse.<br>3. sdk-test is rolled back to v2 (0.0.2).<br>4. sdk-test icon/text ("Test (0.0.2)") is shown in hamburger menu.<br>5. Opening sdk-test shows example app with "Home (0.0.2)" title.<br>6. Subsequent update checks will repeat process (download, rollback). |

#### Failure
| Steps       | Expected Results |
| ----------- | ---------------- |
| 1. Enable only v3.<br>2. Clear app data, launch Teams, and login.<br>3. Enable v4.<br>4. Quit Teams and reopen. | 1. sdk-test v4 (0.0.3) downloads.<br>2. Malformed manifest (missing icons) fails to parse.<br>3. sdk-test is rolled back to v3 (0.0.3).<br>4. Malformed manifest (missing modules/views) fails to parse.<br>5. Rollback is abandoned, v4 (0.0.4) is loaded in error state.<br>6. Placeholder icon/text is shown in hamburger menu.<br>7. Opening sdk-test shows error module.<br>8. Subsequent update checks will repeat process (download, rollback, abandon rollback). |

### Source Switch
#### CodePush to assets
| Steps       | Expected Results |
| ----------- | ---------------- |
| 1. Enable only v1.<br>2. Clear app data, launch Teams, and login.<br>3.	Download v6 bundle as zip (CodePush > v6 > Download Bundle) and add contents to Teams.<br>4. Change mobile module definition to load from zip instead of CodePush.<br>5. Build and launch Teams. | 1. sdk-test v6 (0.0.6) is applied as an update from the local filesystem.<br>2. sdk-test icon/text ("Test (0.0.6)") is shown in hamburger menu.<br>3. Opening sdk-test shows example app with "Home (0.0.6)" title.<br>4. Subsequent update checks will use local filesystem instead of CodePush. |

#### Assets to CodePush
| Steps       | Expected Results |
| ----------- | ---------------- |
| 1. Download v1 bundle as zip (CodePush > v1 > Download Bundle) and add contents to Teams.<br>2. Clear app data, launch Teams, and login.<br>3. Change mobile module definition to load from zip instead of CodePush.<br>4. Build and launch Teams. | 1. sdk-test v6 (0.0.6) is applied as an update from CodePush.<br>2. sdk-test icon/text ("Test (0.0.6)") is shown in hamburger menu.<br>3. Opening sdk-test shows example app with "Home (0.0.6)" title.<br>4. Subsequent update checks will use CodePush instead of local filesystem. |

### Miscellaneous
#### Downgrade
| Steps       | Expected Results |
| ----------- | ---------------- |
| 1. Enable only v6.<br>2. Clear app data, launch Teams, and login.<br>3. Disable v6, enable v1.<br>4. Quit and relaunch Teams. | 1. sdk-test v1 (0.0.1) is downloaded as an "update" from v6.<br>2. Downgrade is detected, v1 "update" is treated as a clean install.<br>3. sdk-test icon/text ("Test (0.0.1)") is shown in hamburger menu.<br>4. Opening sdk-test shows example app with "Home (0.0.1)" title.<br>5. v6 (0.0.6) is deleted from the filesystem. |

#### Required Update / Clean Install
| Steps       | Expected Results |
| ----------- | ---------------- |
| 1. Enable only v1.<br>2. Clear app data, launch Teams, and login.<br>3. Disable v1, enable v2 with "Required Update" checked.<br>4. Quit and relaunch Teams.<br>5. Uncheck "Required Update" for v2. | 1. sdk-test v2 (0.0.2) downloads as an update from v1.<br>2. v2 update is treated as a clean install.<br>3. sdk-test icon/text ("Test (0.0.2)") is shown in hamburger menu.<br>4. Opening sdk-test shows example app with "Home (0.0.2)" title.<br>5. v1 (0.0.1) is deleted from the filesystem. |

#### Target App Version
| Steps       | Expected Results |
| ----------- | ---------------- |
| 1. Enable only v1.<br>2. Clear app data, launch Teams, and login.<br>3. Disable v1, enable v5.<br>4. Quit and relaunch Teams.<br>5. Uncheck "Required Update" for v2. | 1. No sdk-test update is downloaded.<br>2. sdk-test icon/text ("Test (0.0.1)") is shown in hamburger menu.<br>3. Opening sdk-test shows example app with "Home (0.0.1)" title. |
