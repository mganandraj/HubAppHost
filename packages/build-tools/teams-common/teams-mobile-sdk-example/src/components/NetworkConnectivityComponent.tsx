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
  NetworkConnectivityManager,
  Resource,
  TeamsView,
  TraceLogger
} from 'teams-mobile-sdk';
import { Utilities } from './utilities/Utilities';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20
  },
  margin: {
    margin: 10
  }
});

export class NetworkConnectivityComponent extends TeamsView<any, any> {
  private static readonly LOG_TAG: string = 'NetworkConnectivityComponent';

  constructor (props: any, state?: any) {
    super(props, state);
  }

  public render () {
    return (
      <View style={styles.container}>
        <Button
          onPress={this.handleGetNetworkAvailabilityBtnPress.bind(this)}
          title={Resource.getLocalizedString('get_network_availability_btn_title')}
          accessibilityLabel={Resource.getLocalizedString('get_network_availability_btn_accessibility_label')}
        />
      </View>
    );
  }

  public handleGetNetworkAvailabilityBtnPress () {
    NetworkConnectivityManager.isNetworkAvailable()
    .then((result: boolean) => {
      const message = result ? Resource.getLocalizedString('get_network_availability_available_message') : Resource.getLocalizedString('get_network_availability_unavailable_message');
      Utilities.showAlert(message);
    }).catch((error) => {
      TraceLogger.logError(NetworkConnectivityComponent.LOG_TAG, error);
      const message = Resource.getLocalizedString('get_network_availability_failure_message');
      Utilities.showAlert(message);
    });
  }
}
