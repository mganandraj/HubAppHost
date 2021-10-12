import * as React from 'react';
import { Button, Dimensions, NativeSyntheticEvent, StyleSheet, View } from 'react-native';
import {
  TeamsView,
  TeamsWebView,
  TeamsWebViewMessage,
  ViewState,
  WebViewSource
  } from 'teams-mobile-sdk';

const ckeditor = require('../static/sample.html');

const styles = StyleSheet.create({
  verticalStack: {
    flex: 1,
    flexDirection: 'column'
  },
  stateLayout: {
    flex: 1
  },
  container: {
    flex: 1
  }
});

// Color Hex codes
const windowWidth: number = Dimensions.get('window').width;

const html: string = `
  <html>
    <head>
      <title>Hello</title>
      <style>
        body {
          font-size: 3em;
          background-color: coral;
        }
      </style>
    </head>
    <body>
      <div id='main'>Default content</div>
    </body>
  </html>
`;

const injectedJavascript: string = `
  document.getElementById('main').innerText = 'Modified by Javascript!';
  window.postMessage('Hello world!');
`;

interface State {
  viewState: ViewState;
  syncing: boolean;
  source: WebViewSource | number;
}

export class TeamsWebViewComponent extends TeamsView<any, State> {
  private webViewRef: TeamsWebView | null;
  public appTheme = this.getApplicationContext().getCurrentAppTheme();
  constructor (props: any, state?: any) {
    super(props, state);

    this.state = {
      source: {
        html: html
      },
      viewState: {},
      syncing: false
    } as State;
  }

  public componentDidMount () {
    //
  }

  public render () {
    return (
      <View style={styles.container}>
        <Button title='Load HTML from string (default)' onPress={this.loadHtmlFromString}/>
        <Button title='Load HTML from static file' onPress={this.loadHtmlFromStaticFile}/>
        <View style={styles.verticalStack}>
          <View style={{ flex: 1 }}>
            <TeamsWebView
              ref={ref => { this.webViewRef = ref; }}
              style={{ flex: 1, width: windowWidth, opacity: 1, backgroundColor: "yellow"  }}
              source={this.state.source}
              injectedJavaScript={injectedJavascript}
              onMessage={this.webViewOnMessage}
              onLoad={this.onLoad}
              onLoadStart={this.onLoadStart}
              onLoadEnd={this.onLoadEnd}
              onError={this.onError}
            />
          </View>
        </View>
      </View>
    );
  }

  public webViewOnMessage = (event: NativeSyntheticEvent<TeamsWebViewMessage>) => {
    console.log(`RN WebView message received: ${event.nativeEvent.data}`);
    if (this.webViewRef === null) {
      console.log('WebView ref is null');
      return;
    }

    const javaScript = `
        document.getElementById('main').insertAdjacentHTML('beforeend', '<div>injectJavascript() works!</div>');
      `;
    this.webViewRef.injectJavaScript(javaScript);
  }

  private onLoad = () => {
    console.log('RN WebView: onLoad called.');
  }

  private onLoadStart = () => {
    console.log('RN WebView: onLoadStart called.');
  }

  private onLoadEnd = () => {
    console.log('RN WebView: onLoadEnd called.');
  }

  private onError = () => {
    console.log('RN WebView: onError called.');
  }

  private loadHtmlFromString = () => {
    this.setState({
      source: {
        html: html
      },
      viewState: this.state.viewState,
      syncing: this.state.syncing
    });
  }

  private loadHtmlFromStaticFile = () => {
    this.setState({
      source: ckeditor,
      viewState: this.state.viewState,
      syncing: this.state.syncing
    });
  }
}
