/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
import * as React from 'react';
import { 
  findNodeHandle, 
  Image, 
  ImageResolvedAssetSource, 
  requireNativeComponent, 
  UIManager, 
  ViewProperties 
} from 'react-native';

const resolveAssetSource = Image.resolveAssetSource;
const SDK_WEBVIEW_REF = 'webview';

export enum AuthorizationType {
  Sharepoint = 'SharepointAuth'
}

export interface AuthorizationHeaders {
  url: string;
  authType: AuthorizationType;
  headers: any;
}

export interface WebViewSource {
  html?: string;
  uri?: string;
  headers?: AuthorizationHeaders;
}

/**
 * Props for `TeamsWebView`.
 */
export interface TeamsWebViewProps extends ViewProperties {
  /**
   * Supports raw HTML content, URLs and asset files.
   *
   * To use asset files:
   * ```ts
   * <TeamsWebView source={require('path/to/html/file/example.html')} />
   * ```
   */
  source: WebViewSource | number;
  injectedJavaScript?: string;
  onMessage?: (event: any) => void;
  onLoad?: (event: any) => void;
  onLoadStart?: (event: any) => void;
  onLoadEnd?: (event: any) => void;
  onError?: (event: any) => void;
}

/**
 * Structure of the native event passed to `onMessage` prop of
 * the web view.
 */
export interface TeamsWebViewMessage {
  data: string;
}

/**
 * Props that are not exposed to the developers but are available
 * from native.
 */
interface NativeWebViewProps extends ViewProperties {
  source: WebViewSource;
  injectedJavaScript?: string;
  messagingEnabled: boolean;
  onMessage?: (event: any) => void;
  onLoadingStart: (event: any) => void;
  onLoadingFinish: (event: any) => void;
  onLoadingError: (event: any) => void;
}

const NativeTeamsWebView = requireNativeComponent<NativeWebViewProps>('TeamsWebView');

export class TeamsWebView extends React.Component<TeamsWebViewProps> {
  public render () {
    const messagingEnabled = typeof this.props.onMessage === 'function';

    let source: WebViewSource;
    // The `source` prop can denote an asset ID.
    // It will be a number in those cases.
    if (typeof this.props.source === 'number') {
      const assetSource: ImageResolvedAssetSource = resolveAssetSource(this.props.source);
      source = {
        uri: assetSource.uri
      };
    } else {
      source = this.props.source;
    }

    return <NativeTeamsWebView
              ref={SDK_WEBVIEW_REF}
              style={this.props.style}
              source={source}
              injectedJavaScript={this.props.injectedJavaScript}
              onMessage={this.props.onMessage}
              onLoadingStart={this._onLoadingStart}
              onLoadingFinish={this._onLoadingFinish}
              onLoadingError={this._onLoadingError}
              messagingEnabled={messagingEnabled} />;
  }

  public injectJavaScript = (script: string) => {
    (UIManager as any).dispatchViewManagerCommand(this.getWebViewHandle(), (UIManager as any).TeamsWebView.Commands.injectJavaScript, [script]);
  }

  public reload = () => {
    (UIManager as any).dispatchViewManagerCommand(this.getWebViewHandle(), (UIManager as any).TeamsWebView.Commands.reload, []);
  }

  private _onLoadingStart = (event: any) => {
    this.props.onLoadStart && this.props.onLoadStart(event);
  }

  private _onLoadingFinish = (event: any) => {
    const { onLoad, onLoadEnd } = this.props;
    onLoad && onLoad(event);
    onLoadEnd && onLoadEnd(event);
  }

  private _onLoadingError = (event: any) => {
    const { onError, onLoadEnd } = this.props;
    onError && onError(event);
    onLoadEnd && onLoadEnd(event);
  }

  private getWebViewHandle = (): any => {
    return findNodeHandle(this.refs[SDK_WEBVIEW_REF] as React.Component);
  }
}
