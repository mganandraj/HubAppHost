import AsyncStorage from '@react-native-async-storage/async-storage';
import * as React from 'react';
import {
    Alert, Button, ScrollView, StyleSheet, Text, TextInput, View
} from 'react-native';

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

export class AsyncStorageComponent extends React.Component<any, any> {
  
  constructor(props, state) {
    super(props, state);
    this.state = {
      key: '',
      value: '',
      dbContent: ''
    };
  }

  public componentDidMount() {
    this.updateDbContent();
  }

  private async setItem() {
    await AsyncStorage.setItem(this.state.key, this.state.value).then(
      () => {
        Alert.alert('Data inserted');
        this.updateDbContent();
      }
    )
    .catch(e => {
      Alert.alert('Error', e);
    });
  }

  private async getItem() {
    await AsyncStorage.getItem(this.state.key).then(
      (data) => {
        Alert.alert('Data retrieved:', data !== null ? data : 'null');
        this.setState({ value: data });
      }
    )
    .catch(e => {
      Alert.alert('Error', e);
    });
  }

  private async getAllKeys() {
    await AsyncStorage.getAllKeys().then(
      (data) => {
        Alert.alert('Data retrieved:', data.join('\n'));
      }
    )
    .catch(e => {
      Alert.alert('Error', e);
    });
  }

  private async clear() {
    await AsyncStorage.clear().then(
      () => {
        Alert.alert('Cleared');
        this.updateDbContent();
      }
    )
    .catch(e => {
      Alert.alert('Error', e);
    });
  }

  private async removeItem() {
    await AsyncStorage.removeItem(this.state.key).then(
      () => {
        Alert.alert('Removed item');
        this.setState({ value: '' });
        this.updateDbContent();
      }
    )
    .catch(e => {
      Alert.alert('Error', e);
    });
  }
  
  private async mergeItem() {
    await AsyncStorage.mergeItem(this.state.key, this.state.value).then(
      () => {
        Alert.alert('Merged item');
        this.updateDbContent();
      }
    )
    .catch(e => {
      Alert.alert('Error', e);
    });
  }

  private async updateDbContent() {
    await AsyncStorage.getAllKeys().then(async (keys) => {
      await AsyncStorage.multiGet(keys).then((values) => {
        this.setState({ dbContent: values.join('\n\n') });
      });
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
          onPress = {() => {this.setItem();}}
          title = 'Set item'></Button>
          <View style = {styles.buttonSpace}></View>

          <Button 
          onPress = {() => {this.getItem();}}
          title = 'Get item'></Button>
          <View style = {styles.buttonSpace}></View>

          <Button 
          onPress = { ()=> {this.removeItem();}}
          title = 'Remove item'></Button>
          <View style = {styles.buttonSpace}></View>

          <Button 
          onPress = {() => {this.mergeItem();}}
          title = 'Merge item (requires data in JSON format)'></Button>
          <View style = {styles.buttonSpace}></View>

          <Button 
          onPress = {() => {this.getAllKeys();}}
          title = 'Get all keys'></Button>
          <View style = {styles.buttonSpace}></View>
          
          <Button 
          onPress = {() => {this.clear();}}
          title = 'Clear'></Button>
          <View style = {styles.buttonSpace}></View>

          <Text>DB Contents: </Text>
          <Text>(Key, Value)</Text>

          <TextInput
            multiline = {true}
            editable = {false}
            style = {[styles.input, { color: 'black' }]}
            value = {this.state.dbContent}></TextInput>
        </View>
      </ScrollView>
    );
  }
}
