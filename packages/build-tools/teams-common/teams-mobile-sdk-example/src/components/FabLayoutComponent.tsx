/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import * as React from 'react';
import {
  Button,
  FlatList,
  Platform,
  StyleSheet,
  Text,
  ToastAndroid,
  View
} from 'react-native';
import {
  AppTheme,
  FabLayoutParams,
  OptionsMenuItem,
  TeamsShell
} from 'teams-mobile-sdk';

import { Utilities } from './utilities/Utilities';

const styles = StyleSheet.create({
  parent: {
    flex: 1
  },
  container: {
    paddingTop: 22
  },
  item: {
    padding: 10,
    fontSize: 24,
    height: 50,
    alignSelf: 'flex-end'
  },
  fabNotSupportedMessageContainer: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center'
  }
});

interface State {
  fabLayoutParams: FabLayoutParams;
}

interface FabLayoutComponentProps {
  teamsShell: TeamsShell;
  theme: AppTheme;
}

export class FabLayoutComponent extends React.Component<FabLayoutComponentProps, State> {
  public appTheme: any;
  constructor (props: FabLayoutComponentProps, state?: any) {
    super(props, state);
    this.appTheme = props.theme;
    if (!this.appTheme) {
      this.appTheme = AppTheme.DEFAULT;
    }
    this.state = {
      fabLayoutParams: {
        description: 'This is decription',
        title: 'This is title',
        iconUri: 'res://images/default/icn_default.svg',
        secondaryFabs: [
          {
            description: 'This is description 1',
            title: 'This is title 1',
            iconUri: 'res://images/default/icn_default.svg',
            buttonId: 'item1'
          },
          {
            description: 'This is description 2',
            title: 'This is title 2',
            iconUri: 'res://images/default/icn_default.svg',
            buttonId: 'item2'
          }
        ]
      } as FabLayoutParams
    } as State;

    if (Platform.OS === 'android') {
      this.showFab();
    }
  }

  public showFab (): void {
    this.props.teamsShell.showFabLayoutAndroid(this.state.fabLayoutParams);
  }

  public hideFab (): void {
    this.props.teamsShell.hideFabLayoutAndroid();
  }

  public onPrimaryFabClick (): void {
    console.log('Primary fab clicked');
    Utilities.showAlert('Primary Button clicked', ToastAndroid.SHORT);
  }

  public onSecondaryFabClick (_: string): void {
    console.log('Secondary fab clicked');
  }

  public onOptionsMenuItemSelected (_: string): void {
    // Do nothing
  }

  public getOptionsMenuItems (): OptionsMenuItem[] {
    // Do nothing
    return [];
  }

  public componentWillUnmount() {
    this.hideFab();
  }

  public render () {
    if (Platform.OS === 'android') {
      return (
        <View style={styles.parent}>
          <Button
            title='Show FAB'
            onPress={() => { this.showFab(); }}
          />
          <FlatList style={styles.container}
            data={[
              { key: 'Scroll here' },
              { key: 'This is a ' },
              { key: 'flat list!' },
              { key: 'Lorem ipsum' },
              { key: 'dolor sit amet,' },
              { key: 'consectetur adipiscing elit,' },
              { key: 'sed do eiusmod tempor' },
              { key: 'ut labore et dolore ' },
              { key: 'Ut enim ad minim veniam,' },
              { key: 'quis nostrud exercitation' },
              { key: 'ullamco laboris nisi' },
              { key: ' ex ea commodo consequat.' },
              { key: 'Duis aute irure dolor in ' },
              { key: 'reprehenderit in voluptate' },
              { key: 'velit esse cillum dolore' },
              { key: ' nulla pariatur. Excepteur' },
              { key: 'sint occaecat cupidatat non' },
              { key: 'proident, sunt in culpa qui ' },
              { key: 'officia deserunt mollit' },
              { key: 'anim id est laborum.' }
            ]}
            renderItem={({ item }) => <Text style={[styles.item,{color: this.appTheme === AppTheme.DARK ? 'white' : 'black'}]}>{item.key}</Text>}
          />
          <Button
            title='Hide FAB'
            onPress={() => { this.hideFab(); }}
          />
        </View>
      );
    } else {
      return (
        <View style={styles.fabNotSupportedMessageContainer}>
          <Text style = {[{color: this.appTheme === AppTheme.DARK ? 'white' : 'black'}]}>FAB is available only in Android.</Text>
        </View>
      );
    }
  }
}
