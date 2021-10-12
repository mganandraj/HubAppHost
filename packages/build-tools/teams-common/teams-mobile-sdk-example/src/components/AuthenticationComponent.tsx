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
  AuthenticationService,
  Resource,
  TraceLogger
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

export class AuthenticationComponent extends React.Component<any, any> {
  private static readonly LOG_TAG: string = 'AuthenticationComponent';

  constructor (props: any, state?: any) {
    super(props, state);
  }

  public render () {
    return (
      <View style={styles.container}>
        <Button
          onPress={this.handleGetGraphAccessTokenBtnPress.bind(this)}
          title={Resource.getLocalizedString('get_graph_access_token_btn_title')}
          accessibilityLabel={Resource.getLocalizedString('get_graph_access_token_btn_accessibility_label')}
        />
        <Button
          onPress={this.handleGetSkypeBtnPress.bind(this)}
          title={Resource.getLocalizedString('get_skype_token_btn_title')}
          accessibilityLabel={Resource.getLocalizedString('get_skype_token_btn_accessibility_label')}
        />
        <Button
          onPress={this.handleGetResourceTokenBtnPress.bind(this)}
          title={Resource.getLocalizedString('get_resource_token_btn_title')}
          accessibilityLabel={Resource.getLocalizedString('get_resource_token_btn_accessibility_label')}
        />
      </View>
    );
  }

  public handleGetGraphAccessTokenBtnPress () {
    AuthenticationService.getResourceToken('https://graph.microsoft.com')
      .then((_: string) => {
        TraceLogger.logDebug(AuthenticationComponent.LOG_TAG, 'Acquired access token.');
        const message =  Resource.getLocalizedString('get_graph_access_token_success_message');
        Utilities.showAlert(message);
      }).catch((error) => {
        TraceLogger.logError(AuthenticationComponent.LOG_TAG, 'Failed to acquire token.');
        TraceLogger.logError(AuthenticationComponent.LOG_TAG, error);
        const message =  Resource.getLocalizedString('get_graph_access_token_failure_message');
        Utilities.showAlert(message);
      });
  }

  public handleGetSkypeBtnPress () {
    AuthenticationService.getSkypeToken(false)
      .then((_: string) => {
        TraceLogger.logDebug(AuthenticationComponent.LOG_TAG, 'Acquired skype token.');
        const message =  Resource.getLocalizedString('get_skype_token_success_message');
        Utilities.showAlert(message);
      }).catch((error) => {
        TraceLogger.logError(AuthenticationComponent.LOG_TAG, 'Failed to acquire skype token.');
        TraceLogger.logError(AuthenticationComponent.LOG_TAG, error);
        const message =  Resource.getLocalizedString('get_skype_token_failure_message');
        Utilities.showAlert(message);
      });
  }

  public handleGetResourceTokenBtnPress () {
    // ID for DevOps app created by MEE team for testing
    AuthenticationService.getResourceTokenWithClaim('499b84ac-1321-427f-aa17-267ca6975798')
      .then((_: string) => {
        TraceLogger.logDebug(AuthenticationComponent.LOG_TAG, 'Acquired resource token.');
        const message =  Resource.getLocalizedString('get_resource_token_success_message');
        Utilities.showAlert(message);
      }).catch((error) => {
        TraceLogger.logError(AuthenticationComponent.LOG_TAG, 'Failed to acquire resource token.');
        TraceLogger.logError(AuthenticationComponent.LOG_TAG, error);
        const message =  Resource.getLocalizedString('get_resource_token_failure_message');
        Utilities.showAlert(message);
      });
  }
}
