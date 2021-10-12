# Channel picker
The channel picker enables your users to select a channel from a list of channels. The user's selection
is then communicated back to your app via a Promise.

## Options
There are various configuration options that can be leveraged to customize the channel picker. These are
defined in the `ChannelPickerOptions` interface.

- `title` : Use this option to configure the title of the channel picker. By default, it is a localized version of "Select a channel". Note that if you provide a custom title, you need to provide a localized form if localization is desired.

- `preSelectedChannelId`: Use this option to specify if any channel is selected by default. For e.g. to highlight a previous selection by the user. By default, no channel is selected. _Note: if you specify `preSelectedChannelId` and the user cancels the picker, the promise will resolve to `undefined` and not the 
given pre-selected channel._

- `pickableChannels`: Use this to specify which channels the user can pick from. For e.g. you can restrict selection to a few channels that have a special significance in your app. If this is not specified, all channels the user is a member of will be displayed in the picker. _Note: `pickableChannels` is a list of channel IDs_.

## Result
The channel picker gives back a result of type `Promise<ChannelPickerResult>`. The following fields are available in `ChannelPickerResult`:
- `selectedChannel` : Represents the channel selected by the user. This is of type `Channel`.
- `parentTeam`: Represents the parent team of the channel selected by the user. This is of type `Team`.

See documentation for `Team` and `Channel` for the list of properties available.

### Cancellation
Sometimes the user might choose to cancel the channel picker i.e. dismiss it without selecting any channel.
In such cases, the promise yields the value `undefined` when resolved. You should check for this before using
the result.

## Usage
The following examples use the `async`/`await` syntax to work with promises. You can also use `.then()`, `.catch()` and `.finally()`.

### Import
Make sure you import `ChannelPicker` before using. 
```typescript
import { ChannelPicker } from 'teams-mobile-sdk';
```

### Basic usage
```typescript
try {
    const result: ChannelPickerResult = await ChannelPicker.launch();

    if (result !== undefined) {
        // use the result here
    }
    else {
        // handle what happens if the user canceled the channel picker
    }
} catch (e) {
    // handle errors
}
```

### Pickable channels
```typescript
try {
    const result: ChannelPickerResult = await ChannelPicker.launch({
        pickableChannels: ['19:aed...4c0@thread.skype', '19:afb...1b2@thread.skype']
    });

    if (result !== undefined) {
        // use the result here
    }
} catch (e) {
    // handle errors
}
```

### Title and pre-selected channel
```typescript
try {
    const result: ChannelPickerResult = await ChannelPicker.launch({
        title: 'Share with channel',
        preSelectedChannelId: '19:14c...ff0@thread.skype'
    });

    if (result !== undefined) {
        // use the result here
    }
} catch (e) {
    // handle errors
}
```
