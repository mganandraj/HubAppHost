# TeamsWebView

The `TeamsWebView` component shows the webview given the HTML data.

## Props
The parameters of the `TeamsWebView` is configured through props, which must be an instance of `TeamsWebViewProps`.

1. **source**: The source of web view content. Adheres to class `WebViewSource`
2. **injectedJavascript**: JS code to inject into each page. Code is injected after navigation to the page is complete.
3. **onLoad**: Function that is invoked when the WebView has finished loading.
4. **onLoadEnd**: Function that is invoked when the WebView load succeeds or fails.
5. **onLoadStart**: Function that is invoked when the WebView starts loading.
6. **onError**: Function that is invoked when the WebView load fails.
7. **injectJavascript**: Function that accepts a string that will be passed to the WebView and executed immediately as JavaScript.
8. **reload**: Function that can be used to refresh the content of WebView.

## Examples
```tsx
// Example of how to create authHeaders dictionary.
let url = 'http://example.com';
let urlHostname = new URL(url).hostname as string;
let authHeadersProps = {url: url, authType: AuthorizationType.Sharepoint} as AuthorizationHeaders;

// html content should have proper body and html tags
// also all images in the html should be added to src attribute inside img tag
let htmlContent = '<html><head><title>Title of the document</title></head><body>The content of the document</body></html>';

class MyComponent extends React.Component {
  render () {
    return (
      <TeamsWebView
        ref={ref => { this.webViewRef = ref; }}
        style={{ flex: 1, width: windowWidth, opacity: 1, backgroundColor: appWhite }}
        source={{ html: htmlContent, headers: authHeadersProps } as WebViewSource}
        injectedJavaScript={injectedJavascript}
        onMessage={this.webViewOnMessage}
        onLoad={this.onLoad}
        onLoadStart={this.onLoadStart}
        onLoadEnd={this.onLoadEnd}
        onError={this.onError}
      />
    );
  }
}
```

To use asset files
```tsx
...
render () {
  return <TeamsWebView source={require('path/to/your/html/file/example.html')} ... />
}
```