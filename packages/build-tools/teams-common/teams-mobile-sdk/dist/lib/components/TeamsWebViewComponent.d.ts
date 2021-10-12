/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
import * as React from 'react';
import { ViewProperties } from 'react-native';
export declare enum AuthorizationType {
    Sharepoint = "SharepointAuth"
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
export declare class TeamsWebView extends React.Component<TeamsWebViewProps> {
    render(): JSX.Element;
    injectJavaScript: (script: string) => void;
    reload: () => void;
    private _onLoadingStart;
    private _onLoadingFinish;
    private _onLoadingError;
    private getWebViewHandle;
}
