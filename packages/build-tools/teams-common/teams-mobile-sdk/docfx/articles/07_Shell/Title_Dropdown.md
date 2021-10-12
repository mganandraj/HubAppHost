# Title dropdown
You can use the `setTitleDropdown` function of `TeamsShell` to set a title dropdown in any view of your app.

## Example
```ts
class MySampleView extends TeamsView<any, MySampleViewState> {
  constructor (props: any, state: MySampleViewState) {
      super (props, state);
      const titleDropdownItems: TitleDropdownItem[] = [
        {
          id: 'item1',
          title: 'First item'
        },
        {
          id: 'item2',
          title: 'Second item'
        }
      ];
      this.getTeamsShell().setTitleDropdown(titleDropdownItems, (selectedItemId: string) => {
        console.log(`User selected title dropdown item with id ${selectedItemId}`);
      });
  }
}
```

See <code>[TeamsShell.setTitleDropdown](xref:teams-mobile-sdk.TeamsShell.setTitleDropdown)</code> to learn more.