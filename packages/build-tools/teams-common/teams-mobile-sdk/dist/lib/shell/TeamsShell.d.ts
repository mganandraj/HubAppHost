/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
import { HostContext } from '../application/HostContext';
import { ActionSheet } from '../models/shell/ActionSheet';
import { FabLayoutParams } from '../models/shell/FabLayoutParams';
import { Snackbar } from '../models/shell/Snackbar';
import { SnackbarCallback } from '../models/shell/SnackbarCallback';
import { TeamsShellTab } from '../models/shell/TeamsShellTab';
import { TitleDropdownItem } from '../models/shell/TitleDropdownItem';
import { TeamsShellInteractionListener } from './TeamsShellInteractionListener';
export declare class TeamsShell {
    protected host: HostContext;
    protected listener: TeamsShellInteractionListener | undefined;
    protected snackbarCallbacks: SnackbarCallback[];
    protected titleDropdownHandler?: (selectedItemId: string) => void;
    protected backNavigationHandler?: () => void;
    protected titleClickHandler?: () => void;
    constructor(host: HostContext, listener: TeamsShellInteractionListener);
    destroy(): void;
    showSnackbar(snackbar: Snackbar, callback?: (snackbarId: number) => void): void;
    dismissSnackbar(snackbarId: number, callback?: (dismissed: boolean) => void): void;
    setTitle(title: string): void;
    setTitleWithCallBack(title: string, callback: () => void): void;
    setHomeIndicatorBackgroundColorIOS(colorInHexCode: string): void;
    setSubtitle(subtitle: string): void;
    setBackgroundColor(color: string): void;
    /**
     * Shows an action sheet. Available in both Android & iOS.
     *
     * A maximum of 10 options will be shown in the action sheet. Any
     * extra options passed to this method will be ignored.
     *
     * If no options are provided in the actionSheet object, the action
     * sheet will not be displayed. If any of the given options are
     * invalid (for example, icon is undefined, label is empty etc.), they
     * are ignored. If all the provided options are invalid, action sheet
     * will not be displayed.
     *
     * The onOptionSelected handler is invoked with the option id of the selected
     * option.
     *
     * The onCanceled handler is invoked when the user cancels the action sheet.
     * The user can do this by tapping on any area of the screen outside the
     * action sheet or by pressing the back button on Android.
     *
     * @param actionSheet action sheet to show
     * @param onOptionSelected handler to call when a user selects an option in the action sheet.
     * @param onCanceled Handler to call when user cancels the action sheet
     */
    showActionSheet(actionSheet: ActionSheet, onOptionSelected: (selectedOptionId: string) => void, onCanceled?: () => void): void;
    showFabLayoutAndroid(fabLayoutParams: FabLayoutParams): void;
    hideFabLayoutAndroid(): void;
    invalidateOptionsMenu(): void;
    /**
     * Registers a handler that will be called when the user tries to navigate back from
     * the current view. You may use this to warn the user of unsaved changes or any other
     * consequences of navigating back from the current view. If the user wishes to go back
     * anyway, you should call <code>[closeView](xref:teams-mobile-sdk.TeamsView.closeView)</code>.
     * If you don't call `closeView` in your handler, back navigation won't be executed. So,
     * your app gains full control of whether or not to allow back navigation.
     *
     * Calling this method multiple times will simply overwrite the existing handler.
     *
     * **Platform specific notes:**
     *
     * **iOS**
     *
     * Your handler will be called when either of the following happens:
     * 1. User taps on the back button of the navigation controller.
     * 2. User performs a swipe gesture (interactive pop gesture).
     *
     * **Android**
     *
     * Your handler will be called either of the following happens:
     * 1. User taps on the hardware back button of the device.
     * 2. User taps on the up navigation button on the toolbar.
     *
     * @param handler a callback that should be called when user attempts to navigate back
     */
    registerBackNavigationHandler(handler: () => void): void;
    /**
     * Removes any registered back navigation handlers. You app will no longer have control
     * of back navigation. The default back navigation behaviour of the Teams app will come
     * into effect.
     */
    removeBackNavigationHandler(): void;
    /**
     * Sets up a title dropdown. The items are displayed in the order in which they
     * are passed to this method. The first item will be selected by default.
     *
     * @param items items to be displayed in the title dropdown
     */
    setTitleDropdown(items: TitleDropdownItem[], handler: (selectedItemId: string) => void): void;
    /**
     * Sets up tabs in AppBar(Android)/NavigationBar(iOS)
     *
     * @param tabList list of tabs to be added to AppBar(Android)/NavigationBar(iOS)
     */
    setUpTabs(tabList: TeamsShellTab[]): void;
    /**
     * Sets up tabs in AppBar(Android)/NavigationBar(iOS) with default selected tab
     * If defaultSelectedTabId is not present in tab list, first tab from tab list gets selected.
     *
     * @param tabList list of tabs to be added to AppBar(Android)/NavigationBar(iOS)
     * @param defaultSelectedTabId tab that should be selected when the tabbed view is loaded
     */
    setUpTabsWithDefaultSelectedTab(tabList: TeamsShellTab[], defaultSelectedTabId: string): void;
    protected setOptionsMenu(): void;
    private clearSnackbarCallbacks;
}
