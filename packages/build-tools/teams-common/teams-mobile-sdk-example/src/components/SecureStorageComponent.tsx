import * as React from 'react';
import {
    Alert, Button, ScrollView, StyleSheet, Text, TextInput, View
} from 'react-native';
import { SecureStorage, TeamsView } from 'teams-mobile-sdk';

const styles = StyleSheet.create({
  input: {
    borderColor: '#d6d7da',
    borderWidth: 0.5,
    margin: 4,
    marginBottom: 8
  },
  buttonSpace: {
    height: 4
  }
});

export class SecureStorageComponent extends TeamsView<any, any> {
  
  constructor(props, state) {
    super(props, state);
    this.state = {
      key: '',
      value: '',
      dbContent: ''
    };
  }

  private async addEntry() {
    SecureStorage.addEntry(this.state.key, this.state.value).then(
      () => {
        Alert.alert('Data inserted');
      }
    )
    .catch(e => {
      Alert.alert('Error', e.message);
    });
  }

  private async getEntry() {
    SecureStorage.getEntry(this.state.key).then(
      (data) => {
        Alert.alert('Data retrieved:', data !== null ? data : 'null');
        this.setState({ value: data });
      }
    )
    .catch(e => {
      Alert.alert('Error', e.message);
    });
  }

  private async removeEntry() {
    SecureStorage.removeEntry(this.state.key).then(
      () => {
        Alert.alert('Removed entry');
        this.setState({ value: '' });
      }
    )
    .catch(e => {
      Alert.alert('Error', e.message);
    });
  }

  public render() {
    return(
      <ScrollView>
        <View>
          <Text>Key</Text>

          <TextInput
            style = {styles.input}
            onChangeText = {(text) => this.setState({ key: text })}
            value = {this.state.key}></TextInput>

          <Text>Value</Text>

          <TextInput
            style = {styles.input}
            onChangeText = {(text) => this.setState({ value: text })}
            value = {this.state.value}></TextInput>

          <Button
          onPress = {() => {this.addEntry();}}
          title = 'Set item'></Button>
          <View style = {styles.buttonSpace}></View>

          <Button 
          onPress = {() => {this.getEntry();}}
          title = 'Get item'></Button>
          <View style = {styles.buttonSpace}></View>

          <Button 
          onPress = { ()=> {this.removeEntry();}}
          title = 'Remove item'></Button>
          <View style = {styles.buttonSpace}></View>
        </View>
      </ScrollView>
    );
  }
}
