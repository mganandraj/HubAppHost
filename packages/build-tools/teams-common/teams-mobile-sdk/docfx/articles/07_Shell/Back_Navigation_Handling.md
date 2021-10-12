# Back navigation handling
The SDK provides a way for your app to intercept and stop back navigation from the current view. You may use this,
for example, to warn the user of unsaved changes in the current view that will be discarded upon navigating away.

See <code>[TeamsShell.registerBackNavigationHandler](xref:teams-mobile-sdk.TeamsShell.registerBackNavigationHandler)</code>
and <code>[TeamsShell.removeBackNavigationHandler](xref:teams-mobile-sdk.TeamsShell.removeBackNavigationHandler)</code> to learn more.

## Example
```ts
import { Alert } from 'react-native';
import { TeamsView } from 'teams-mobile-sdk';

class SomeEditorView extends TeamsView {
  constructor (props: any) {
    super(props);
    this.getTeamsShell().registerBackNavigationHandler(this.onNavigateBack);
  }

  public onNavigateBack = () => {
    const buttons: AlertButton[] = [
      {
        text: 'Discard changes',
        onPress: () => {
          this.closeView(); // Need to close view explicitly!
        },
      },
      {
        text: 'Keep Editing',
        onPress: () => {
          // Do nothing and the view won't close!
        },
      }
    ];
    Alert.alert('Do you really want to go back?', 
                'You will lose unsaved changes!', 
                buttons);
  }

}
```