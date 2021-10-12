# FAB Layout

The `FabLayout` component is a common view from the native app that is used to display set of floating action buttons. There is one primary FAB and there can be secondary FABs also. On pressing the primary FAB, secondary FABs collapse and expand.

A component must implement the TeamsShellInteractionListener interface from the SDK. The component should bind itself to an instance of TeamsShellInteractor after it has been mounted. The component should unbind from the TeamsShellInteractor instance specific to the component before being unmounted.

```typescript
this.getTeamsShell().enableFabLayoutAndroid(fabLayoutParams);

this.getTeamsShell().disableFabLayoutAndroid();
```

**Note:** This is only supported in Android.

#### FabLayoutParams
`FabLayoutParams` is comprised of the following:

* **description:** Optional. A string description associated with primary FAB used for accessibility.
* **title:** Optional. A string to display just besides the primary FAB.
* **iconUri:** A drawable image resource that is to be used as the icon for a primary FAB.
* **secondaryFabs:** Optional. `SecondaryActionButton[]` list of secondary buttons.

#### SecondaryFloatingActionButton
`SecondaryFloatingActionButton` is comprised of the following:

* **description:** Optional. A string description associated with secondary FAB used for accessibility.
* **title:** Optional. A string to display just besides the corresponding secondary FAB.
* **iconUri:** A drawable image resource that is to be used as the icon for corresponding secondary FAB.
* **buttonId:** A unique identifier for a secondary action button. It should be of type `string`.

## Events
`FabLayout` exposes events to detect when a button is clicked.

### onPrimaryFabClick
If there is no secondary button, on click of primary FAB should trigger this event.  
In presence of secondary buttons, on click of this button is restricted to expand/collapse the layout. Hence, it is optional in this case. 

```typescript
onPrimaryFabClick?: () => void;
```

#### onSecondaryFabClick
Every secondary FAB should have unique identity string `buttonId`. On clicking the secondary button, unique id will be argument and this method is called.
```typescript
onSecondaryFabClick?: (buttonId : string) => void;
```

#### Example
```typescript
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import * as React from 'react';
import {
  ToastAndroid,
  View
} from 'react-native';
import {
  FabLayoutParams,
  SecondaryFloatingActionButton
} from 'teams-mobile-sdk';

interface State {
  fabLayoutParams: FabLayoutParams;
}

export class FabLayoutComponent extends TeamsView<any, any> {
  constructor (props: any, state?: any) {
    super(props, state);
    this.state = {
      fabLayoutParams: {
        description: 'This is decription',
        title: 'This is title',
        iconUri: 'res://images/icn_default.svg',
        secondaryFabs: [
          {
            description: 'This is description 1',
            title: 'This is title 1',
            iconUri: 'res://images/icn_default.svg',
            buttonId: 'item1'
          } as SecondaryFloatingActionButton, 
          {
            description: 'This is description 2',
            title: 'This is title 2',
            iconUri: 'res://images/icn_default.svg',
            buttonId: 'item2'
          } as SecondaryFloatingActionButton
        ]
      } as FabLayoutParams;
    } as State;

    this.getTeamsShell().enableFabLayoutAndroid(fabLayoutParams);
  }

  componentDidMount (): void {
    this.getTeamsShell().bindListener(this);
  }

  componentWillUnmount (): void {
    this.getTeamsShell().unbindListener();
  }

  onPrimaryFabClick () {

  }

  onSecondaryFabClick (buttonId: string) {
    ToastAndroid.show('Secondary button clicked with buttonId: ' + buttonId, ToastAndroid.SHORT);
  }
}


```

