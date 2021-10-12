"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.TeamsWebView = exports.AuthorizationType = void 0;
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
const React = require("react");
const react_native_1 = require("react-native");
const resolveAssetSource = react_native_1.Image.resolveAssetSource;
const SDK_WEBVIEW_REF = 'webview';
var AuthorizationType;
(function (AuthorizationType) {
    AuthorizationType["Sharepoint"] = "SharepointAuth";
})(AuthorizationType = exports.AuthorizationType || (exports.AuthorizationType = {}));
const NativeTeamsWebView = (0, react_native_1.requireNativeComponent)('TeamsWebView');
class TeamsWebView extends React.Component {
    constructor() {
        super(...arguments);
        this.injectJavaScript = (script) => {
            react_native_1.UIManager.dispatchViewManagerCommand(this.getWebViewHandle(), react_native_1.UIManager.TeamsWebView.Commands.injectJavaScript, [script]);
        };
        this.reload = () => {
            react_native_1.UIManager.dispatchViewManagerCommand(this.getWebViewHandle(), react_native_1.UIManager.TeamsWebView.Commands.reload, []);
        };
        this._onLoadingStart = (event) => {
            this.props.onLoadStart && this.props.onLoadStart(event);
        };
        this._onLoadingFinish = (event) => {
            const { onLoad, onLoadEnd } = this.props;
            onLoad && onLoad(event);
            onLoadEnd && onLoadEnd(event);
        };
        this._onLoadingError = (event) => {
            const { onError, onLoadEnd } = this.props;
            onError && onError(event);
            onLoadEnd && onLoadEnd(event);
        };
        this.getWebViewHandle = () => {
            return (0, react_native_1.findNodeHandle)(this.refs[SDK_WEBVIEW_REF]);
        };
    }
    render() {
        const messagingEnabled = typeof this.props.onMessage === 'function';
        let source;
        // The `source` prop can denote an asset ID.
        // It will be a number in those cases.
        if (typeof this.props.source === 'number') {
            const assetSource = resolveAssetSource(this.props.source);
            source = {
                uri: assetSource.uri
            };
        }
        else {
            source = this.props.source;
        }
        return React.createElement(NativeTeamsWebView, { ref: SDK_WEBVIEW_REF, style: this.props.style, source: source, injectedJavaScript: this.props.injectedJavaScript, onMessage: this.props.onMessage, onLoadingStart: this._onLoadingStart, onLoadingFinish: this._onLoadingFinish, onLoadingError: this._onLoadingError, messagingEnabled: messagingEnabled });
    }
}
exports.TeamsWebView = TeamsWebView;
