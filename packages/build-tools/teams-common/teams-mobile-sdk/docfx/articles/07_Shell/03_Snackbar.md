# Snackbar

## Methods

### showSnackbar (snackbar: Snackbar, callback?: (snackbarId: number) => void)

`Snackbar` is comprised of the following:

* **title:** String to display within snackbar when visible.
* **duration:** `SnackbarDuration` representing length of time the snackbar is visible. See "SnackbarDuration" for more info.
* **action:** Optional `SnackbarAction` available within the snackbar. See "SnackbarAction" for more info.

#### SnackbarDuration
There are three available durations:

* `SnackbarDuration.SHORT`
* `SnackbarDuration.LONG`
* `SnackbarDuration.INDEFINITE`

If SnackbarDuration.INDEFINITE is used without valid SnackbarAction (without action title / action id), duration is changed to SnackbarDuration.SHORT.

#### SnackbarAction
A snackbar can show an action in the form of a button within the snackbar. A `SnackbarAction` is comprised of the following:

* **id:** Unique string ID for action.
* **title:** String to display as button text.
* **onSelected:** Callback on button press.

#### Callback
An optional callback can be provided in order to obtain the snackbar's ID. This ID is required to dismiss the snackbar manually.

#### Example
```typescript
let snackbar = {
    title: 'I am a snackbar.',
    duration: SnackbarDuration.LONG,
    action: {
        id: 'snackbar-undo-action',
        title: 'Undo',
        onSelected: () => {
            // handle the selection event here
        }
    } as SnackbarAction
} as Snackbar;

this.getTeamsShell().showSnackbar(snackbar);
```

### dismissSnackbar (snackbarId: number, callback?: (dismissed: boolean) => void)

To manually dismiss a snackbar, the ID of the snackbar is required. To obtain a snackbar's ID, specify a callback when invoking `showSnackbar`.

#### Callback
An optional callback can be provided to determine when and if the snackbar is dismissed.

#### Example
```typescript
let snackbarId = 0;
let snackbar = {
    title: 'You selected menu item 1',
    duration: SnackbarDuration.INDEFINITE
} as Snackbar;

this.getTeamsShell().showSnackbar(snackbar, (snackbarId: number) => {
    snackbarId = snackbarId;
});

this.getTeamsShell().dismissSnackbar(snackbarId);
```
