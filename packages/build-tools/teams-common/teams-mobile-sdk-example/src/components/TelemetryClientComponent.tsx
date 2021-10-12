/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import * as React from 'react';
import {
  Button,
  StyleSheet,
  ToastAndroid,
  View
} from 'react-native';
import {
  Resource,
  TelemetryClient,
  UserBIEvent,
  UserBIEventName
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
 
 export class TelemetryClientComponent extends React.Component<any, any> {
   constructor (props: any, state?: any) {
     super(props, state);
   }
 
   public render () {
     return (
       <View style={styles.container}>
         <Button
           onPress={this.executeLogUserBIEvent.bind(this)}
           title={Resource.getLocalizedString('send_user_bi_event')}
         />
         <View style={styles.margin} />
         <Button
           onPress={this.executeScenarioEvent.bind(this)}
           title={Resource.getLocalizedString('send_scenario_event')}
         />
       </View>
     );
   }
 
   public executeLogUserBIEvent () {
     const event = {
       eventName: UserBIEventName.PANEL_ACTION,
       moduleName: ' test button',
       databagProp: { 'a': 'a', 'b': '10', mobileModuleType: 'x' },
       userBIDatabag: { 'a': 'a', 'b': 10, mobileModuleType: 'x', isA: true }
     } as UserBIEvent;
     
     try {
       TelemetryClient.logUserBIEvent(event);
       console.log('Successfully logged UserBI event');
       const message = 'Successfully logged UserBI event';
       Utilities.showAlert(message, ToastAndroid.SHORT);
     } catch (e) {
       console.log('Failed to log UserBI event : '+e);
       const message = 'Failed to log UserBI event';
       Utilities.showAlert(message, ToastAndroid.SHORT);
     }
   }
 
   public executeScenarioEvent () {
     TelemetryClient.startScenario('clickedfab', { 'a': 'a', 'b': 10, mobileModuleType: 'x', isA: true })
     .then((id: string) => {
       const message = 'scenario id' + id;
       Utilities.showAlert(message, ToastAndroid.SHORT);
       TelemetryClient.startScenarioUnderParent('child scenario', id, { 'x': 'x', 'y': 10, isZ: true }).then((childid: string) => {
         TelemetryClient.endScenarioChainOnIncomplete(childid, 'app_error_code_1', 'errormsg', { 'k': 'k', 'm': 10, isK: true });
         TelemetryClient.endScenarioChainOnSuccess(childid);
       });
     });
   }
 }
 