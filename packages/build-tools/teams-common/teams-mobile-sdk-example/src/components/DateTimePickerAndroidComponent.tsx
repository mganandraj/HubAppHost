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
  DateTimeParams,
  DateTimePickerAndroid,
  Resource
} from 'teams-mobile-sdk';

import { Utilities } from './utilities/Utilities';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center'
  }
});

export class DateTimePickerAndroidComponent extends React.Component<any, any> {
  constructor (props: any, state?: any) {
    super(props, state);
    this.state = {
      pickerProps: {
        dateViewSubmitButtonText: 'Next',
        timeViewSubmitButtonText: 'Done',
        dateViewTitle: 'Start Date',
        timeViewTitle: 'Start Time',
        isTimeSelectionRequired: true,
        minute: 10,
        hour: 10,
        dayOfMonth: 15,
        month: 7,
        year: 2018,
        minDate: 1533081600000,
        maxDate: 1564617600000
      } as DateTimeParams
    };
  }

  public render () {
    return (
      <View style={styles.container}>
          <Button
            onPress={this.showDateTimePicker.bind(this)}
            title={Resource.getLocalizedString('date_time_picker_btn')}
          />
      </View>
    );
  }

  public showDateTimePicker () {
    DateTimePickerAndroid.showDateTimePickerDialog(this.state.pickerProps)
      .then((timeInMillis: number | null) => {
        if (timeInMillis === null) {
          Utilities.showAlert('Dialog cancelled.');
        } else {
          const date = new Date(timeInMillis);
          const dateString = date.getDate() + '-' + date.getMonth() + '-' + date.getFullYear() + '  ' + date.getHours() + ':' + date.getMinutes();
          Utilities.showAlert('Selected Date Time : ' + dateString);
        }
      });
  }
}
