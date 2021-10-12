import React = require("react");
import { ScrollView, StyleSheet, Text } from "react-native";
import { FluentIconView, IconStyle, TeamsView } from "teams-mobile-sdk";

const styles = StyleSheet.create({
  container: {
    flex: 1
  }
});

export class FluentIconsDemoComponent extends TeamsView<any, any> {
  public render() {
    return (
      <ScrollView style={styles.container}>
        <Text>Org chart regular</Text>
        <FluentIconView
          iconSymbol='organization_chart'
          style={{ width: 64, height: 64 }} />
        <Text>Org chart filled</Text>
        <FluentIconView iconSymbol='organization_chart'
          iconStyle={IconStyle.FILLED}
            style={{width: 64, height: 64}}/>
        <Text>Wiki regular</Text>
        <FluentIconView
          iconSymbol='notepad'
          style={{width: 64, height: 64}}/>
        <Text>Wiki filled</Text>
        <FluentIconView iconSymbol='notepad'
          iconStyle={IconStyle.FILLED}
          style={{width: 64, height: 64}}/>
        <Text>Camera regular</Text>
        <FluentIconView
          iconSymbol='camera'
          iconStyle={IconStyle.REGULAR}
          style={{width: 64, height: 64}}/>
        <Text>Camera filled</Text>
        <FluentIconView
          iconSymbol='camera'
          iconStyle={IconStyle.FILLED}
          style={{ width: 64, height: 64 }} />
        <Text>contact_card</Text>
        <FluentIconView
          iconSymbol='contact_card'
          iconStyle={IconStyle.FILLED}
          style={{ width: 64, height: 64}} />
        <Text>chevron_down</Text>
        <FluentIconView
          iconSymbol='chevron_down'
          iconStyle={IconStyle.FILLED}
          style={{ width: 64, height: 64 }} />
        <Text>chevron_right</Text>
        <FluentIconView
          iconSymbol='chevron_right'
          iconStyle={IconStyle.FILLED}
          style={{ width: 64, height: 64 }} />
        <Text>checkmark</Text>
        <FluentIconView
          iconSymbol='checkmark'
          iconStyle={IconStyle.FILLED}
          style={{ width: 64, height: 64 }} />
      </ScrollView>
    );
  }
}
