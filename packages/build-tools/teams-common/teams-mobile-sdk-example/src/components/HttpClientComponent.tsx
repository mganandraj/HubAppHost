/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import * as React from 'react';
import {
  Button,
  StyleSheet,
  View
} from 'react-native';
import {
  HttpClient,
  HttpMethod,
  HttpRequest,
  HttpResponse,
  Resource
} from 'teams-mobile-sdk';
import { Utilities } from './utilities/Utilities';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20
  },
  margin: {
    margin: 10
  }
});

export class HttpClientComponent extends React.Component<any, any> {
  constructor (props: any, state?: any) {
    super(props, state);
  }

  public render () {
    return (
      <View style={styles.container}>
        <Button
          onPress={this.executeGet.bind(this)}
          title={Resource.getLocalizedString('http_client_get_btn')}
        />
        <View style={styles.margin} />
        <Button
          onPress={this.executePost.bind(this)}
          title={Resource.getLocalizedString('http_client_post_btn')}
        />
      </View>
    );
  }

  public executeGet () {
    const request = {
      requestId: '1',
      serviceName: 'JSONPlaceholder',
      requestName: 'GetPosts',
      method: HttpMethod.GET,
      url: 'https://jsonplaceholder.typicode.com/posts',
      headers: {
        'Accept': 'application/json'
      }
    } as HttpRequest;

    HttpClient.execute(request)
      .then((response: HttpResponse) => {
        const message = 'Received response: status - ' + response.status + ', data - ' + response.data;
        Utilities.showAlert(message);
      }).catch((error) => {
        const message = 'Failed to execute request. Error: ' + error;
        Utilities.showAlert(message);
      });
  }

  public executePost () {
    const request = {
      requestId: '2',
      serviceName: 'JSONPlaceholder',
      requestName: 'CreatePost',
      method: HttpMethod.POST,
      url: 'https://jsonplaceholder.typicode.com/posts',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        userId: 1,
        id: Date.now(),
        title: 'Sample post',
        body: 'This is a sample post'
      })
    } as HttpRequest;

    HttpClient.execute(request)
      .then((response: HttpResponse) => {
        const message = 'Received response: status - ' + response.status + ', data - ' + response.data;
        Utilities.showAlert(message);
      }).catch((error) => {
        const message = 'Failed to execute request. Error: ' + error;
        Utilities.showAlert(message);
      });
  }
}
