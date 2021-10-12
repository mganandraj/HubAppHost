"use strict";
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
var __rest = (this && this.__rest) || function (s, e) {
    var t = {};
    for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p) && e.indexOf(p) < 0)
        t[p] = s[p];
    if (s != null && typeof Object.getOwnPropertySymbols === "function")
        for (var i = 0, p = Object.getOwnPropertySymbols(s); i < p.length; i++) {
            if (e.indexOf(p[i]) < 0 && Object.prototype.propertyIsEnumerable.call(s, p[i]))
                t[p[i]] = s[p[i]];
        }
    return t;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.FluentIconView = exports.IconStyle = void 0;
const React = require("react");
const react_native_1 = require("react-native");
/**
 * An enumeration of possible icon styles
 */
var IconStyle;
(function (IconStyle) {
    IconStyle["REGULAR"] = "regular";
    IconStyle["FILLED"] = "filled";
})(IconStyle = exports.IconStyle || (exports.IconStyle = {}));
/**
 * @hidden from docs
 *
 * Native fluent icon view component. This is not directly exported to
 * developers.
 */
const NativeFluentIconView = (0, react_native_1.requireNativeComponent)('FluentIconView');
/**
 * An image view that renders icons from the Fluent icon set provided by
 * Stardust.
 *
 * Syntax:
 * ```jsx
 * <FluentIconView
 *  iconSymbol='notepad'
 *  iconStyle={IconStyle.FILLED}
 *  style={{width: 64, height: 64}}/>
 * ```
 * The above code produces a 64x64 filled notepad icon.
 *
 * Also see: [FluentIconViewProps](xref:teams-mobile-sdk.FluentIconViewProps),
 * [IconStyle](xref:teams-mobile-sdk.IconStyle)
 *
 * Introduced in v0.7.4. Set minReactNativeSdkVersion to 0.7.4 in manifest.json
 * if you use this component in your app.
 */
class FluentIconView extends React.Component {
    /**
     * @hidden from docs
     */
    render() {
        const _a = this.props, { iconSymbol, iconColor, iconStyle } = _a, otherProps = __rest(_a, ["iconSymbol", "iconColor", "iconStyle"]);
        return React.createElement(NativeFluentIconView, Object.assign({ iconProps: {
                'iconSymbol': iconSymbol,
                'iconColor': iconColor,
                'iconStyle': iconStyle
            } }, otherProps));
    }
}
exports.FluentIconView = FluentIconView;
