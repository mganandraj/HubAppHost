# UserAvatar

The `UserAvatar` component shows the avatar for a given contact. `UserAvatar` inherits all properties of Views.

## UserAvatarProps
Contact details for `UserAvatar` is configured through the `avatarProps` prop which must be an instance of `UserAvatarProps`. The following fields are available for configuration in `UserAvatarProps`:

1. **displayName:** The display name of the user. This is an optional field. If not specified, Teams will resolve the profile image URL based on `userId` and `userIdType`. If resolved, the profile image will be shown. Else, a default placeholder image will be shown.
2. **userId:** The unique identifier for the user. This is an optional field. If specified, the profile image URL will be resolved based on the specified `userIdType`. If unspecified, the user profile image will show the initials if `displayName` is set. If `displayName` is not set, the default placeholder image will be shown.
3. **userIdType:** The type of unique identifier set for the user. Below are the possible values for userIdType:
    * **AadUpn:** `userId` is the `userPrincipalName` of an AAD user. For example: `ashmeh@corp.microsoft.com`
    * **AadObjectId:** `userId` is the unique `objectId` for an AAD user. For example: `4f45a682-424c-4d69-81ee-28092c2eceac`
    * **Mri:** `userId` the Skype mri of the user. For example: `8:orgid:4f45a682-424c-4d69-81ee-28092c2eceac`
    * **DeviceContactId:** `userId` is the contact identifier (a number) of a device contact. For example: `1`
    * **PhoneNumber:** `userId` is the phone number of the user. For example: `14258671234`
    * **Unknown:** `userId` is an unknown type.
4. **userAvatarUrl:** URL for the user image. This is an optional field. If set, this URL will be used when showing the profile image. Else, it will show the default placeholder image.

## Image Priority
The following priority is used when determining the image to display:

1. `userAvatarUrl`
2. Profile image resolved from `userId` and `userIdType`.
3. Initials from `displayName`.
4. Default placeholder image.

## Example
This example renders the user profile of the currently signed in user.
```typescript
import * as React from 'react';
import {
  StyleSheet,
  View
} from 'react-native';
import {
  ApplicationContext,
  UserAvatar,
  UserAvatarProps,
  UserIdType,
  Contact
} from 'teams-mobile-sdk';

let styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20
  }
});

export class UserAvatarComponent extends React.Component<any, any> {
  constructor (props: any, state?: any) {
    super(props, state);
  }

  render () {
    let currentUser: Contact = ApplicationContext.getCurrentUser();
    let avatarProps = {
      userId: currentUser.skypeMri,
      displayName: currentUser.displayName,
      userIdType: UserIdType.Mri
    } as UserAvatarProps;

    return (
      <View style={styles.container}>
        <UserAvatar avatarProps={avatarProps} style={{ width: 40, height: 40 }} />
      </View>
    );
  }
}
```
