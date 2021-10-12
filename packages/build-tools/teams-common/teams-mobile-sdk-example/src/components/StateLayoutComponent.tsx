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
  Resource,
  StateLayout,
  TeamsView,
  TraceLogger,
  ViewError,
  ViewState,
  ViewStateType
} from 'teams-mobile-sdk';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20
  },
  stateLayout: {
    flex: 1
  },
  margin: {
    margin: 10
  }
});

interface State {
  viewState: ViewState;
  syncing: boolean;
}

export class StateLayoutComponent extends TeamsView<any, State> {
  private static readonly LOG_TAG: string = 'StateLayoutComponent';

  constructor (props: any, state?: any) {
    super(props, state);
    this.onRefresh = this.onRefresh.bind(this);
    this.state = {
      viewState: {
        type: ViewStateType.LOADING,
        error: {
          title: Resource.getLocalizedString('state_layout_error_title'),
          description: Resource.getLocalizedString('state_layout_error_desc'),
          errorImageResource: 'res://images/default/icn_menu_item_1.svg'
        } as ViewError,
        errorMessage: 'errorMessage',
        lastUpdatedTime: 0,
        refreshing: false
      } as ViewState,
      syncing: false
    } as State;
  }

  public onRefresh () {
    TraceLogger.logDebug(StateLayoutComponent.LOG_TAG, 'StateLayout onRefresh called');

    // Stop syncing after 5 seconds
    if (this.state.viewState.type === ViewStateType.AVAILABLE) {
      this.setState({ syncing: true });
      setTimeout(() => { this.setState({ syncing: false }); }, 5000);
    }
  }

  public render () {
    return (
      <View style={styles.container}>
        <StateLayout viewState={this.state.viewState} onChange={this.onRefresh} syncing={this.state.syncing} style={styles.stateLayout}>
          <View collapsable={false}>
            { /* Here view component is acting as a container view for the child components. */}
            {/* setting collapsable to false is instructing the RN to not to automatically remove from the native hierarchy as an optimization. */}
            {/* {@link https://facebook.github.io/react-native/docs/view#collapsable} */}
            {/* We can use a FlatList, ScrollView, or any other Container components depending on the use case. */}
            <Button
              onPress={() => { this.setState({ viewState: { ...this.state.viewState, type: ViewStateType.LOADING } }); }}
              title={Resource.getLocalizedString('state_layout_loading')} />
            <Text style={{ backgroundColor: 'powderblue' }}>{Resource.getLocalizedString('state_layout_content')}</Text>
            <Text style={{ backgroundColor: 'yellow' }}>'Lorem ipsum dolor sit amet, eos placerat insolens expetenda cu. Id gubergren efficiantur pro. Ut mel case minim commodo. Quas ceteros ex quo, nam platonem mediocritatem ne, et habeo eripuit voluptua mea.'</Text>
          </View>
        </StateLayout>
        <View style={styles.margin} />
        <Button
          onPress={() => { this.setState({ viewState: { ...this.state.viewState, type: ViewStateType.EMPTY } }); }}
          title={Resource.getLocalizedString('state_layout_empty')}
        />
        <View style={styles.margin} />
        <Button
          onPress={() => { this.setState({ viewState: { ...this.state.viewState, type: ViewStateType.LOADING } }); }}
          title={Resource.getLocalizedString('state_layout_loading')}
        />
        <View style={styles.margin} />
        <Button
          onPress={() => { this.setState({ viewState: { ...this.state.viewState, type: ViewStateType.ERROR } }); }}
          title={Resource.getLocalizedString('state_layout_error')}
        />
        <View style={styles.margin} />
        <Button
          onPress={() => { this.setState({ viewState: { ...this.state.viewState, type: ViewStateType.AVAILABLE } }); }}
          title={Resource.getLocalizedString('state_layout_available')}
        />
        <View style={styles.margin} />
        <Button
          onPress={() => { this.setState({ syncing: !this.state.syncing }); }}
          title={Resource.getLocalizedString('state_layout_toggle_sync')}
        />
      </View>
    );
  }
}
