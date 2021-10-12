/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
import * as React from 'react';
import { ViewProps } from "react-native";
/**
 * An enumeration of possible icon styles
 */
export declare enum IconStyle {
    REGULAR = "regular",
    FILLED = "filled"
}
/**
 * Props supported by the fluent icon view
 */
export interface FluentIconViewProps extends ViewProps {
    /**
     * A string identifer for the icon. All possible icon symbol names can be
     * found here: https://github.com/microsoft/fluentui-system-icons/blob/master/icons.md
     */
    iconSymbol: string;
    /**
     * The tint/outline color of the icon in hex format (e.g. #6264a7). If the
     * icon is filled, this will be used as the fill color. If not specified, the
     * defaults defined by Stardust will be used.
     */
    iconColor?: string;
    /**
     * The style of the icon: regular or filled. If not specified, the defaults
     * set by Stardust will be used.
     */
    iconStyle?: IconStyle;
}
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
export declare class FluentIconView extends React.Component<FluentIconViewProps, any> {
    /**
     * @hidden from docs
     */
    render(): JSX.Element;
}
