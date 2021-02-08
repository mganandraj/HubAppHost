/**
 * Copyright © <author>. All rights reserved.
 */

import * as React from 'react';
import {
  ScrollView,
  StyleSheet,
  Text
} from 'react-native';

import {
  AppTheme,
  TeamsView
} from 'teams-mobile-sdk';

const styles = StyleSheet.create({
  container: {
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10
  }
});

export class HomeComponent extends TeamsView<any, any> {

  constructor (props: any, state?: any) {
    super(props, state);
  }

  public render () {
    const appTheme = this.getApplicationContext().getCurrentAppTheme();
    const textColor = appTheme === AppTheme.DARK ? 'white' : 'black';

    return (
      <ScrollView contentContainerStyle={styles.container}>
        <Text
          style={[styles.hello, {color: textColor}]}>
          Hello world!Updated again and again ..
        </Text>
      </ScrollView>
    );
  }
}
