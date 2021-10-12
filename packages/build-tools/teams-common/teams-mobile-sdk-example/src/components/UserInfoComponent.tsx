/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import * as React from 'react';
import {
  Button,
  StyleSheet,
  View
} from 'react-native';
import {
  Resource,
  TraceLogger,
  UserInfoProvider
} from 'teams-mobile-sdk';

import { Utilities } from './utilities/Utilities';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10
  },
  margin: {
    margin: 10
  }
});

export class UserInfoComponent extends React.Component<any, any> {
  private static readonly LOG_TAG: string = 'UserInfoComponent';

  constructor (props: any, state?: any) {
    super(props, state);
  }

  public render () {
    return (
      <View style={styles.container}>
        <Button
          onPress={this.handleGetLicenseBtnPress.bind(this)}
          title={Resource.getLocalizedString('get_user_license_btn_title')}
          accessibilityLabel={Resource.getLocalizedString('get_user_license_btn_accessibility_label')}
        />
      </View>
    );
  }

  public handleGetLicenseBtnPress () {
    UserInfoProvider.getUserLicense()
    .then((_: string) => {
        TraceLogger.logDebug(UserInfoComponent.LOG_TAG, 'Received user license.');
      const message =  Resource.getLocalizedString('get_user_license_success_message');
      Utilities.showAlert(message);
    }).catch((error) => {
        TraceLogger.logError(UserInfoComponent.LOG_TAG, 'Failed to receive user license.');
        TraceLogger.logError(UserInfoComponent.LOG_TAG, error);
      const message =  Resource.getLocalizedString('get_user_license_failure_message');
      Utilities.showAlert(message);
    });
  }
}
