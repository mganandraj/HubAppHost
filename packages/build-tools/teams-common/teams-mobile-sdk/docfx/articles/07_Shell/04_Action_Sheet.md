# Action sheet

## Methods

### showActionSheet (actionSheet: ActionSheet, onOptionSelected: (selectedOptionId: string) => void, onCanceled?: () => void): void
Shows an action sheet with the given configuration. Up to 10 options can be displayed in the
action sheet. Any extra options will be discarded.

#### ActionSheet
`ActionSheet` is comprised of the following:

* **title:** Optional. A string to display in the top part of the action sheet.
* **message:** Optional. A string to display just below the title. This can be thought of as a subtitle.
* **options:** `ActionSheetOption[]` list of options to be shown in the action sheet.

#### ActionSheetOption
`ActionSheetOption` is comprised of the following:

* **icon:** An image resource that is to be used as the icon for an action sheet option.
* **label:** Label for the action sheet option. This is the main text content of an option
* **id:** A unique identifier for an action sheet option. Use this for identifying the selected option in the handler.

#### onOptionSelected callback
[Mandatory] A callback that the Teams shell will invoke when the user selects an option in the action
sheet. The ID of the selected option will be provided to the handler.

#### onCanceled callback
[Optional] A callback that the Teams shell will invoke when the user cancels the action sheet. This means
that no item was selected and the user dismissed the action sheet by tapping in any area outside it or by
pressing the back button (Android).

#### Example
```typescript
const actionSheet: ActionSheet = {
    title: 'Lorem',
    message: 'Lorem ipsum dolor sit amet',
    options: [
        {
            icon: Resource.image('icn_menu_item_1.svg'),
            label: 'Option 1',
            id: 'OPTION1_ACTION_ITEM'
        } as ActionSheetOption,
        {
            icon: Resource.image('icn_menu_item_2.svg'),
            label: 'Option 2',
            id: 'OPTION2_ACTION_ITEM'
        } as ActionSheetOption
    ]
} as ActionSheet;

this.getTeamsShell().showActionSheet(actionSheet, (id: string) => {
    this.setState({
        selectedActionItem: id
    });
}, () => {
    // handle what happens when user cancels
});
```

