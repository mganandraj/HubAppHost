# Navigation
Teams Mobile SDK provides the ability to navigate to different modules within the Teams app. There are three categories of navigation:
1. Opening a Teams module (i.e. chat, call etc.)
2. Opening another non-teams module
3. Opening another view in the same module

## Opening a Teams modules

### openContactCard (contact: Contact)
This method opens the contact card for the specified contact.

**Note:** Only AAD user contacts within the user's organization are currently supported.

#### Example
This example opens the contact card for the signed in user.

```typescript
import { TeamsModules } from 'teams-mobile-sdk';

let currentUser = this.getApplicationContext().getCurrentUser();
let currentUserContact = {
    id: currentUser.skypeMri,
    name: currentUser.displayName,
    type: 'org'
} as Contact;

TeamsModules.openContactCard(currentUserContact);
```

See [API docs](xref:teams-mobile-sdk.TeamsModules.openContactCard) to learn more.

### startAudioCall (contact: Contact)
This method starts an audio call with the specified contact. If calling is not supported for the logged in user, this call will result in a no-op.

**Note:** Only AAD user contacts within the user's organization are currently supported.

#### Example
This example starts an audio call with the signed in user. This operation is expected to fail.

```typescript
import { TeamsModules } from 'teams-mobile-sdk';

let currentUser = this.getApplicationContext().getCurrentUser();
let currentUserContact = {
    id: currentUser.skypeMri,
    name: currentUser.displayName,
    type: 'org'
} as Contact;

TeamsModules.startAudioCall(currentUserContact);
```

See [API docs](xref:teams-mobile-sdk.TeamsModules.startAudioCall) to learn more.

### openChat (conversationId: string)
This method opens a chat with the specified conversation ID. If the conversation does not exist, this call will result in a no-op.
See [API docs](xref:teams-mobile-sdk.TeamsModules.openChat) to learn more.

#### Example
```typescript
let conversationId = 'conversationId';
NavigationService.openChat(conversationId);
```

###  openChatWithUserMris (userMris: string[])
This method opens a chat with the specified list of user/bot mris.
See [API docs](xref:teams-mobile-sdk.TeamsModules.openChatWithUserMris) to learn more.

### Example
```typescript
const mriList: string[] = ["28:c398fa18-450a-4ae5-9ce0-936afc71b36d"]; //contoso bot
TeamsModules.openChatWithUserMris(mriList);
```

### openMeetingDetails (eventId: string, isSeriesMaster: boolean)
This method opens meeting details page for specified id of an event.
See [API docs](xref:teams-mobile-sdk.TeamsModules.openMeetingDetails) to learn more.

### Example
```typescript
    const eventId: string = "AAMkADVjOTQ1ZmU2LTFhYWEtNGNjMC1iYjc2LTFhZWZjM2VhYTMyZgBGAAAAAAA6h6yyf5K5Q7sPdNecz-_NBwBuJbC1u3duRKZtWG47p6lhAAAAAAENAABuJbC1u3duRKZtWG47p6lhAABfdgYJAAA=";
    TeamsModules.openMeetingDetails(eventId, false);
```

## Opening another module
### openModule<T> (moduleId: string, params: T)
This method is available in <code><a href='xref:teams-mobile-sdk.TeamsView'>TeamsView</a></code>.

This method opens the specified module with the first view as a default view with the params. The params are of generic type T.

#### Example
```typescript
let person: Person = new Person('123', 'Test');
this.openModule<Person>('edit-person', person)
```

See [API docs](xref:teams-mobile-sdk.TeamsView.openModule) to learn more. Use <code>[closeModule](xref:teams-mobile-sdk.TeamsView.closeModule)</code> to close the
current module.

## Opening a view in the same module
All the following methods are available in <code><a href='xref:teams-mobile-sdk.TeamsView'>TeamsView</a></code>.

### openView<T> (viewId: string, params: T)
This method pushes a specific view in the navigation stack. The type parameter `T` is the type of the `params` object that will be passed to the new view.

#### Example
```typescript
let person: Person = new Person('123', 'Test');

this.openView<Person>('home', person);
```

See [API docs](xref:teams-mobile-sdk.TeamsView.openView) to learn more.

### openViewForResult<T,R> (viewId: string, params:T, callback: (closeHostResult: R) => void)
This method pushes a specific view in the navigation stack. The params are of generic type T. The callback will be invoked with the result by the new view when it closes.

#### Example
```typescript

let person: Person = new Person('123','Test');
let params = {
  callbackParams:{
    modalVisible: this.state.modalVisible,
  },
  person: person
};
let callback = (hostResult) => {
  if (hostResult.hasOwnProperty('callbackParams')) {
    let hostParams = hostResult.callbackParams;
    if (hostParams.hasOwnProperty('modalVisible')) {
       this.setState({modalVisible: hostParams.modalVisible});
    }
  }
};

this.openViewForResult<object, object>('navigation' ,params, callback);
```

See [API docs](xref:teams-mobile-sdk.TeamsView.openViewForResult) to learn more.

### closeView (animated?: boolean)
This method pops the current view with or without animation from the navigation stack.

#### Example
```typescript
this.closeView(true);
```

See [API docs](xref:teams-mobile-sdk.TeamsView.closeView) to learn more.

### closeViewWithResult<T>(result: T,animated?: boolean)
This method closes the current view with or without animation boolean from the navigation stack and callback is invoked with the result. The result is of generic type T.

#### Example
```typescript
let person = {
  personId: '123',
  callbackParams: this.callbackParams,
};

this.closeViewWithResult(person, true);
```

See [API docs](xref:teams-mobile-sdk.TeamsView.closeViewWithResult) to learn more.

## Utilities
### getDeepLinkForModule (moduleId: string, params?: string): Promise<string>
This method returns a deep link to the given module along with parameters, if provided. The params must be a string. If passing an object as params, first stringify as JSON.

Deep links take the following format:

```
msteams://teams.microsoft.com/l/mobilemodule/<moduleId>?params=<params>
```

The deeplink can be opened like any other URL using `Linking`.

#### Example
```typescript
let person = {
  personId: '123'
};

DeepLinkUtils.getDeepLinkForModule('edit-person', JSON.stringify(person))
  .then((deepLink: string) => {
    ApplicationContext.getTraceLogger().logDebug(LOG_TAG, deeplink);
  }).catch((error) => {
    ApplicationContext.getTraceLogger().logError(LOG_TAG, error);
  });
```

See [API docs](xref:teams-mobile-sdk.DeepLinkUtils.getDeepLinkForModule) to learn more.
