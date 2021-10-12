# StateLayout

The `StateLayout` component is a common view from the native app that is used to display state UI in modules. `UserAvatar` inherits all properties of Views.

## ViewState
The state of the `StateLayout` is configured through the `viewState` prop, which must be an instance of `ViewState`. The following fields are available for configuration in `ViewState`:

1. **type:** The type of state that the view is in. The following are the possible values:
    * **ViewStateType.AVAILABLE:** View content is available and ready to display. This shows the nested view(s) and enables pull to refresh.
    * **ViewStateType.LOADING:** View content is currently being loaded. This should be the initial state when retrieving external data. This hides the nested view(s) and displays a loading spinner.
    * **ViewStateType.ERROR:** An error has occurred that should be surfaced to the user. An implementation for `ViewError` should also be provided. This hides the nested view(s) and displays an error view that consists of a title, description, image resource, and retry button.
    * **ViewStateType.EMPTY:** View content request has completed successfully but no data was returned. This hides the nested view(s) and displays an error view that consists of a title, description, and image. This looks the same as **ViewStateType.ERROR**, except without the retry button.
2. **error:** The error to be displayed in the error view. This should be an instance of `ViewError`, which has the following fields:
    * **title:** Title of the error.
    * **description:** Description of the error.
    * **errorImageResource:** Error image resource.
3. **errorMessage:** Error message.
4. **lastUpdatedTime:** UNIX time of last update. This is used to show last updated time if network connectivity is lost.

## Child view hierarchy


StateLayout can only support a single direct child / view group/ view container. We should not declare multiple children directly under the stateLayout  component. The container can have multiple children.

we can use below containers to host the child views

1. ```<View collapsable={false}>``` // add this flag to bypass below optimisation for view hierarchy.
Caveat: 
Views that are only used to layout their children or otherwise don't draw anything may be automatically removed from the native hierarchy as an optimization.
Reference:
 https://facebook.github.io/react-native/docs/view#collapsable


2. ```<ScrollView>```

3. ```<FlatList>```

4. or any other relevant view containers. 

### Example
```typescript
// Set the view state to an error message
this.setState({viewState: {...this.state.viewState, type: ViewStateType.ERROR}});
```
```typescript
// Example of defining an error image resource in the ViewError implementation.
this.state = {
  viewState: {
    type: ViewStateType.ERROR,
    error: {
      title: 'Error title',
      description: 'Error description',
      errorImageResource: 'res://images/icn_menu_item_1.png'
    } as ViewError,
    errorMessage: 'errorMessage',
    lastUpdatedTime: 0
  }
};
```

## Handling Refresh
When a user pulls to refresh in the `ViewStateType.AVAILABLE` state or taps the retry button in the `ViewStateType.ERROR` state, the function set to the `onChange` prop will be called.

### Example
```typescript
// Define a refresh method
onRefresh () {
  TraceLogger.logDebug(LOG_TAG, 'StateLayout onRefresh called');
}
```
```typescript
// Set refresh method to onChange prop
<StateLayout viewState={this.state.viewState} onChange={this.onRefresh} style={{ width: 300, height: 300 }}>
  <Text>React Native Text View</Text>
</StateLayout>
```

## Sync Indicator
To display a sync indicator, set the `syncing` prop.

### Example
```typescript
// Define state
this.state = {
  // ...
  syncing: false
} as State;
```

```typescript
// Define a refresh method
onRefresh () {
  ApplicationContext.getTraceLogger().logDebug(LOG_TAG, 'StateLayout onRefresh called');

  // Stop syncing after 5 seconds
  this.setState({syncing: true});
  setTimeout(() => { this.setState({syncing: false}); }, 5000);
}
```

```typescript
// Set refresh method to onChange prop
<StateLayout viewState={this.state.viewState} onChange={this.onRefresh} syncing={this.state.syncing} style={{ width: 300, height: 300 }}>
  <Text>React Native Text View</Text>
</StateLayout>
```

## Example
This example renders a Text view inside a `StateLayout` with buttons to change the state.
```typescript
import * as React from 'react';
import {
  StyleSheet,
  Text,
  View,
  Button
} from 'react-native';
import {
  StateLayout,
  ViewState,
  ViewStateType,
  ViewError
} from 'teams-mobile-sdk';

let styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20
  }
});

interface State {
  viewState: ViewState;
  syncing: boolean;
}

export class StateLayoutComponent extends React.Component<any, State> {
  private static readonly LOG_TAG: string = 'StateLayoutComponent';

  constructor (props: any, state?: any) {
    super(props, state);
    this.onRefresh = this.onRefresh.bind(this);
    this.state = {
      viewState: {
        type: ViewStateType.LOADING,
        error: {
          title: 'Error title',
          description: 'Error description',
          errorImageResource: 'res://images/icn_menu_item_1.svg'
        } as ViewError,
        errorMessage: 'errorMessage',
        lastUpdatedTime: 0,
        refreshing: false
      } as ViewState,
      syncing: false
    } as State;
  }

  onRefresh () {
    ApplicationContext.getTraceLogger().logDebug(StateLayoutComponent.LOG_TAG, 'StateLayout onRefresh called');

    // Stop syncing after 5 seconds
    this.setState({syncing: true});
    setTimeout(() => { this.setState({syncing: false}); }, 5000);
  }

  render () {
    return (
      <View style={styles.container}>
        <StateLayout viewState={this.state.viewState} onChange={this.onRefresh} syncing={this.state.syncing} style={{ width: 300, height: 300 }}>
          <Text>React Native Text View</Text>
        </StateLayout>
        <Button
          onPress={() => { this.setState({viewState: {...this.state.viewState, type: ViewStateType.LOADING}}); }}
          title={'LOADING'}
        />
        <Button
          onPress={() => { this.setState({viewState: {...this.state.viewState, type: ViewStateType.EMPTY}}); }}
          title={'EMPTY'}
        />
        <Button
          onPress={() => { this.setState({viewState: {...this.state.viewState, type: ViewStateType.AVAILABLE}}); }}
          title={'AVAILABLE'}
        />
        <Button
          onPress={() => { this.setState({viewState: {...this.state.viewState, type: ViewStateType.ERROR}}); }}
          title={'ERROR'}
        />
        <Button
          onPress={() => { this.setState({syncing: !this.state.syncing}); }}
          title={'TOGGLE SYNCING'}
        />
      </View>
    );
  }
}
```
#Example - Declaring a complex view hierarchy under state layout

```typescript
  <StateLayout viewState={this.state.viewState} onChange={this.onRefresh} syncing={this.state.syncing} style={styles.stateLayout}>
    <View collapsable={false}>
      { /* Here view component is acting as a container view for the child compoents. */}
      {/* setting collapsable to false is instructing the RN to not to automatically remove from the native hierarchy as an optimization. */}
      {/* {@link https://facebook.github.io/react-native/docs/view#collapsable} */}
      {/* We can use a FlatList, ScrollView, or any other Container components depending on the use case. */}
      <Button
        onPress={() => { this.setState({ viewState: { ...this.state.viewState, type: ViewStateType.LOADING } }); }}
        title={ApplicationContext.getString('state_layout_loading')} />
      <Text style={{ backgroundColor: 'powderblue' }}>{ApplicationContext.getString('state_layout_content')}</Text>
      <Text style={{ backgroundColor: 'yellow' }}>'Lorem ipsum dolor sit amet, eos placerat insolens expetenda cu. Id gubergren efficiantur pro. Ut mel case minim commodo. Quas ceteros ex quo, nam platonem mediocritatem ne, et habeo eripuit voluptua mea.'</Text>
    </View>
  </StateLayout>
```
