/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import * as React from 'react';
import {
  Button,
  StyleSheet,
  Text,
  View
} from 'react-native';
import {
  DeviceContact,
  DeviceContactsProvider,
  Resource,
  TraceLogger,
  UserAvatar,
  UserAvatarProps,
  UserIdType
} from 'teams-mobile-sdk';
import { Utilities } from './utilities/Utilities';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20
  },
  contactRow: {
    paddingTop: 20,
    flexDirection: 'row'
  },
  centerText: {
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: 10,
    marginLeft: 10
  }
});

export class DeviceContactsComponent extends React.Component<any, any> {
  private static readonly LOG_TAG: string = 'DeviceContactsComponent';

  constructor (props: any, state?: any) {
    super(props, state);
    this.state = { contacts: [] };
  }

  public render () {
    return (
      <View style={styles.container}>
        <Button
          onPress={this.handleGetDeviceContactsBtnPress.bind(this)}
          title={Resource.getLocalizedString('get_device_contacts_btn_title')}
          accessibilityLabel={Resource.getLocalizedString('get_device_contacts_btn_accessibility_label')}
        />
        {this.state.contacts.map(function (contact, index) {
          const avatarProps = {
            userId: contact.contactId,
            userIdType: UserIdType.DeviceContactId,
            displayName: contact.displayName
          } as UserAvatarProps;
          return (<View style={styles.contactRow} key={index}>
                    <UserAvatar avatarProps={avatarProps} style={{ width: 40, height: 40 }} />
                    {<Text style={styles.centerText}>{avatarProps.displayName}</Text>}
                  </View>);
        })}
      </View>
    );
  }

  public handleGetDeviceContactsBtnPress () {
    DeviceContactsProvider.getDeviceContacts()
      .then((result: DeviceContact[]) => {
        const contactsCount = result ? result.length : 0;
        Utilities.showAlert(Resource.getLocalizedString('get_device_contacts_result_message', { 'contactsCount': contactsCount }));
        if (contactsCount >= 3) {
          const shuffled = result.sort(() => .5 - Math.random());
          this.setState({ contacts: shuffled.slice(0, 3) });
        }
      }).catch((error) => {
        if (error['code'] === 'PermissionDenied') {
          Utilities.showAlert(Resource.getLocalizedString('get_device_contacts_permissions_message'));
          return;
        }
        TraceLogger.logError(DeviceContactsComponent.LOG_TAG, 'Failed to get device contacts.');
        TraceLogger.logError(DeviceContactsComponent.LOG_TAG, error);
        Utilities.showAlert(Resource.getLocalizedString('get_device_contacts_failure_message'));
      });
  }
}
