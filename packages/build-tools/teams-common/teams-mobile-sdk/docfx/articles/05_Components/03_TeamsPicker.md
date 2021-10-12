# TeamsPickerComponent
The `TeamsPicker` component is a common view from the native app that is used to display state UI in modules. `TeamsPicker` inherits all properties of Views.

## TeamsPickerProps
`TeamsPicker` is configured through the `pickerProps` prop which must be an instance of `TeamsPickerProps`. The following fields are available for configuration in `TeamsPickerProps`:

* **types:** List of types of content to display in picker. The following are the possible values of `TeamsPickerType`:
    * **PEOPLE:** Default. Teams users not covered by other types below.
    * **SELF:** Current user.
    * **BOTS:** Bots.
    * **FEDERATED_USERS:** Federated users.
    * **GROUP_CHATS:** Group chats.
    * **CHANNELS:** Channels.
    * **TEAMS:** Teams.
* **behavior:** Defines behavior when selecting content. The following are the possible values of `TeamsPickerBehavior`:
    * **MULTIPLE:** Default. Can select multiple items (up to `limit`, if provided). Each selected item appears as a "chip" in the search box which can  be deleted later.
    * **SINGLE_CLEAR:** Allows selection of one item at a time. Upon selection, the search box is cleared.
* **position:** Defines position of picker suggestions. The following are the possible values of `TeamsPickerPosition`:
    * **BOTTOM:** Default. Picker suggestions will appear below the search box.
    * **TOP:** Picker suggestions will appear above the search box.
* **scope:** Defines scope of search results. The following are the possible values of `TeamsPickerScope`:
    * **ORG:** Default. Search org-wide.
    * **TEAM:** Search limited to team/thread specified in `scopeTeamId` and `scopeThreadId`.
* **scopeTeamId:** Team ID related to `scope`. Required if `scope` is `TEAM`.
* **scopeThreadId:** Thread ID Related to `scope`. Optional if `scope` is `TEAM`.
* **hint:** Text to display in search box when empty and not focused.
* **accessibilityLabel:** Text to announce when voiceover is focused on search box.
* **limit:** Maximum number of items which can be selected. Defaults to unlimited (-1).
    * 0 - no items (clears selected items)
    * negative number - unlimited
* **fullscreen:** Indicates if suggestions should occupy entire screen. Defaults to `false`.
* **showSuggestionsWhenEmpty:** Indicates if suggestions should be shown when search box is empty. If enabled, focus is requested for the search box. Defaults to `false`.
* **showTopAnchor:** Indicates if top anchor (line) is visible. Defaults to `false`.
* **showBottomAnchor:** Indicates if bottom anchor (line) is visible. Defaults to `false`.

**Note:** The above props are only fully supported on Android. On iOS, `TeamsPicker` currently only supports the `hint` prop, with others taking a default value which may differ from those listed above.

## Events
`TeamsPicker` exposes events to detect when an item has been selected or removed.

**Note:** iOS currently only supports the `onSelectUser` event. Android has full support for all events.

### Selection
An item is selected when the user taps on a suggestion. The following events may be invoked, depending on the type of item selected:

```typescript
onSelectUser?: (person: User) => void;
onSelectGroupChat?: (chat: Chat) => void;
onSelectChannel?: (channel: Channel) => void;
onSelectTeam?: (team: Team) => void;
```

#### Removal
An item is removed when the user deletes a chip from the search box. The following events may be invoked, depending on the type of item removed:

```typescript
onRemoveUser?: (person: User) => void;
onRemoveGroupChat?: (chat: Chat) => void;
onRemoveChannel?: (channel: Channel) => void;
onRemoveTeam?: (team: Team) => void;
```

## Example
This example extends the `UserAvatar` example by adding a picker to change the displayed user.

```typescript
import * as React from 'react';
import {
  Dimensions,
  StyleSheet,
  Text,
  View
} from 'react-native';
import {
  ApplicationContext,
  UserAvatar,
  UserAvatarProps,
  UserIdType,
  TeamsPicker,
  TeamsPickerProps,
  TeamsPickerType,
  TeamsPickerBehavior,
  User
} from 'teams-mobile-sdk';

let styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20
  },
  picker: {
    width: Dimensions.get('window').width,
    height: 48,
    position: 'absolute',
    top: 0
  },
  name: {
    paddingTop: 15,
    fontSize: 18,
    fontWeight: 'bold'
  }
});

interface State {
  pickerProps: TeamsPickerProps;
  avatarProps: UserAvatarProps;
}

export class UserAvatarComponent extends React.Component<any, any> {
  constructor (props: any, state?: any) {
    super(props, state);

    let currentUser: User = ApplicationContext.getCurrentUser();
    this.state = {
      pickerProps: {
        types: [TeamsPickerType.PEOPLE, TeamsPickerType.SELF],
        behavior: TeamsPickerBehavior.SINGLE_CLEAR,
        hint: ApplicationContext.getString('user_avatar_picker_hint')
      } as TeamsPickerProps,
      avatarProps: {
        userId: currentUser.skypeMri,
        displayName: currentUser.displayName,
        principalName: currentUser.principalName,
        userIdType: UserIdType.Mri
      } as UserAvatarProps
    } as State;

    this.onSelectUser = this.onSelectUser.bind(this);
  }

  onSelectUser (person: User) {
    this.setState({
      avatarProps: {
        ...this.state.avatarProps,
        userId: person.skypeMri,
        displayName: person.displayName,
        principalName: person.principalName
      }
    });
  }

  render () {
    return (
      <View style={styles.container}>
        <TeamsPicker
          pickerProps={this.state.pickerProps}
          onSelectUser={person => this.onSelectUser(person)}
          style={styles.picker} />
        <UserAvatar avatarProps={this.state.avatarProps} style={{ width: 50, height: 50 }} />
        <Text style={styles.name}>{this.state.avatarProps.displayName}</Text>
        <Text>{this.state.avatarProps.principalName}</Text>
      </View>
    );
  }
}
```
