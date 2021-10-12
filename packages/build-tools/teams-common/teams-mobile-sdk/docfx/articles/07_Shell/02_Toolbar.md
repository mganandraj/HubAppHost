# Toolbar

## Setting toolbar title
An app can set its toolbar title, as shown below:

```typescript
this.getTeamsShell().setTitle("New Title");
```

Note that the toolbar title is not persisted and will change when switching views, etc.

## Adding options menu 
Override methods `onOptionsMenuItemSelected` and `getOptionsMenuItems` from `TeamsShell`.

### Example
```typescript
import * as React from 'react';
import {
  Text,
  StyleSheet,
  View,
  ToastAndroid
} from 'react-native';
import {
  ApplicationContext,
  OptionsMenuItem,
  ArgumentsValidator,
  Resource
} from 'teams-mobile-sdk';

let styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10
  }
});

export interface GreetingComponentProps {
  userGreeting: string;
}

export class GreetingComponent extends TeamsView<GreetingComponentProps, any> {
  private userGreeting: string;

  constructor (props: GreetingComponentProps, state?: any) {
    super(props, state);

    ArgumentsValidator.assertNotNullOrUndefined('props', props);
    ArgumentsValidator.assertNotNullOrUndefined('props.userGreeting', props.userGreeting);
    this.userGreeting = props.userGreeting;
  }

  render () {
    let greeting = this.userGreeting;
    return (
      <View style={styles.container}>
        <Text
          style={styles.hello}>
          {greeting}
        </Text>
      </View>
    );
  }

  onOptionsMenuItemSelected (selectedMenuItemId: string): void {
    if (selectedMenuItemId === 'menuItem1' || selectedMenuItemId === 'menuItem2') {
      ToastAndroid.show('Menu item selected: ' + selectedMenuItemId, ToastAndroid.SHORT);
    }
  }

  getOptionsMenuItems (): Array<OptionsMenuItem> {
    return [
      { id: 'menuItem1', title: ResourceId.string('menu_item_1_title'), icon: Resource.image('icn_menu_item_1.svg') },
      { id: 'menuItem2', title: ResourceId.string('menu_item_2_title'), icon: Resource.image('icn_menu_item_2.svg') }
    ];
  }
}
```
