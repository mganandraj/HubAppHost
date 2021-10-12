import * as React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { Resource, TeamsShellTab, TeamsView } from 'teams-mobile-sdk';


const styles = StyleSheet.create({
  tabStyle:{
            flex:1, 
            alignItems:"center", 
            justifyContent:"center"
          }
});

/**
 * Demo component with tabs in Toolbar
 */
export class TabbedComponent extends TeamsView<any, any>{
  private tabs = [{ id: "1", title: Resource.string("tabbed_component_tab1_title") } as TeamsShellTab,
                  { id: "2", title: Resource.string("tabbed_component_tab2_title") } as TeamsShellTab];

  constructor(props){
    super(props);
    this.state = {
      selectedTabId: "1"
    };
  }

  public componentDidMount() {
    // Generally tabs.length will be greater than 0,
    // as we are defining tabs in this component, but this will
    // be helpful to show that we support both the APIs, i.e.
    // API with default selected tab and without.
    if (this.tabs.length > 0) {
      this.getTeamsShell().setUpTabsWithDefaultSelectedTab(this.tabs, this.tabs[0].id);
    } else {
      this.getTeamsShell().setUpTabs(this.tabs);
    }
  }

  public render(){
    return (<View style={{flex:1}}>
      {(this.state.selectedTabId === "1") && <View style={styles.tabStyle}>
        <Text>Tab 1</Text>
      </View>}
      {(this.state.selectedTabId === "2") && <View style={styles.tabStyle}>
        <Text>Tab 2</Text>
      </View>}
    </View>);
  }

  public onTabSelected(selectedTabId){
    this.setState({selectedTabId});
  }

}
