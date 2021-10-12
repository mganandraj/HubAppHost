/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import * as React from 'react';
import {
  Dimensions,
  StyleSheet,
  Text,
  View
} from 'react-native';
import {
  AppTheme,
  Resource,
  TeamsPicker,
  TeamsPickerBehavior,
  TeamsPickerProps,
  TeamsPickerType,
  TeamsView,
  User,
  UserAvatar,
  UserAvatarProps,
  UserIdType
} from 'teams-mobile-sdk';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20
  },
  picker: {
    width: Dimensions.get('window').width,
    height: 100,
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

export class UserAvatarComponent extends TeamsView<any, any> {
  constructor (props: any, state?: any) {
    super(props, state);

    const currentUser: User = this.getApplicationContext().getCurrentUser();
    this.state = {
      pickerProps: {
        types: [TeamsPickerType.PEOPLE, TeamsPickerType.SELF],
        behavior: TeamsPickerBehavior.SINGLE_CLEAR,
        hint: Resource.getLocalizedString('user_avatar_picker_hint'),
        showBottomAnchor: true
      } as TeamsPickerProps,
      avatarProps: {
        userId: currentUser.skypeMri,
        displayName: currentUser.displayName,
        principalName: currentUser.principalName,
        userIdType: UserIdType.Mri,
        showPresenceIndicator: true
      } as UserAvatarProps
    } as State;

    this.onSelectUser = this.onSelectUser.bind(this);
  }

  public onSelectUser (person: User) {
    this.setState({
      avatarProps: {
        ...this.state.avatarProps,
        userId: person.skypeMri,
        displayName: person.displayName,
        principalName: person.principalName
      }
    });
  }

  public render () {
    const appTheme = this.getApplicationContext().getCurrentAppTheme();
    const textColor = appTheme === AppTheme.DARK ? 'white' : 'black';
    return (
      <View style={styles.container}>
        <TeamsPicker
          pickerProps={this.state.pickerProps}
          onSelectUser={person => this.onSelectUser(person)}
          style={styles.picker} />
        <UserAvatar avatarProps={this.state.avatarProps} style={{ width: 50, height: 50 }} />
        <Text style={[styles.name, {color: textColor}]}>{this.state.avatarProps.displayName}</Text>
        <Text style={{color: textColor}}>{this.state.avatarProps.principalName}</Text>
      </View>
    );
  }
}
