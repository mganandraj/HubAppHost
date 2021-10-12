import * as React from 'react';
import { Button, StyleSheet, Text, View } from 'react-native';
import {
  ActionSheet,
  ActionSheetOption,
  AppTheme,
  Resource,
  TeamsShell
} from 'teams-mobile-sdk';

let MusicNoteIcon: string;
let MoonIcon: string;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20
  }
});

interface ActionSheetsComponentProps {
  teamsShell: TeamsShell;
  theme: AppTheme;
}

interface ActionSheetsComponentState {
  lastSelectedActionItems: any;
}

export class ActionSheetsComponent extends React.Component<ActionSheetsComponentProps, ActionSheetsComponentState> {
  constructor (props: ActionSheetsComponentProps, state?: ActionSheetsComponentState) {
    super(props, state);
    this.state = {
      lastSelectedActionItems: {}
    };

    let appTheme = props.theme;
    if (!appTheme) {
      appTheme = AppTheme.DEFAULT;
    }

    MusicNoteIcon = Resource.image(appTheme + '/icn_menu_item_1.svg');
    MoonIcon = Resource.image(appTheme + '/icn_menu_item_2.svg');
  }

  public render () {
    return (
      <View style={styles.container}>
        <Button onPress={() => { this.showActionSheet(0, 3, 'Lorem', 'Dolor amet'); }} title='Action sheet'/>
        <Text>Last selected item: {this.getLastSelection(0)}</Text>

        <Button onPress={() => { this.showActionSheet(1, 3, undefined, 'Dolor amet'); }} title='Action sheet: No title'/>
        <Text>Last selected item: {this.getLastSelection(1)}</Text>

        <Button onPress={() => { this.showActionSheet(2, 3, 'Lorem', undefined); }} title='Action sheet: No subtitle'/>
        <Text>Last selected item: {this.getLastSelection(2)}</Text>

        <Button onPress={() => { this.showActionSheet(3, 3, undefined, undefined); }} title='Action sheet: No title and subtitle'/>
        <Text>Last selected item: {this.getLastSelection(3)}</Text>
      </View>
    );
  }

  public getLastSelection (index: number): string {
    const key = String(index);
    if (!(key in this.state.lastSelectedActionItems)) {
      return 'None';
    }

    return this.state.lastSelectedActionItems[key];
  }

  public showActionSheet = (index: number, optionCount: number, title?: string, subtitle?: string): void => {
    const generatedOptions: ActionSheetOption[] = [];
    for (let i = 0; i < optionCount; ++i) {
      const option: ActionSheetOption = {
        label: `OPTION ${i} in SHEET ${index}`,
        icon: `${(i % 2 === 0) ? MoonIcon : MusicNoteIcon}`,
        id: `OPTION_${i}_SHEET_${index}`
      };
      generatedOptions.push(option);
    }
    const actionSheet: ActionSheet = {
      title: title,
      message: subtitle,
      options: generatedOptions
    } as ActionSheet;

    this.props.teamsShell.showActionSheet(actionSheet, (selectedOption: string) => {
      const updatedSelections: any = this.state.lastSelectedActionItems;
      const key: string = String(index);
      if (!(key in updatedSelections)) {
        updatedSelections[key] = selectedOption;
      } else {
        updatedSelections[key] = selectedOption;
      }
      this.setState({
        lastSelectedActionItems: updatedSelections
      });
    }, () => {
      const updatedSelections: any = this.state.lastSelectedActionItems;
      const key: string = String(index);
      if (!(key in updatedSelections)) {
        updatedSelections[key] = 'Canceled';
      } else {
        updatedSelections[key] = 'Canceled';
      }
      this.setState({
        lastSelectedActionItems: updatedSelections
      });
    });
  }
}
