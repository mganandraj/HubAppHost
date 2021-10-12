"use strict";
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.TeamsShell = void 0;
const ArgumentsValidator_1 = require("../../common/utilities/ArgumentsValidator");
const SnackbarCallback_1 = require("../models/shell/SnackbarCallback");
const SdkNativeModules_1 = require("../services/SdkNativeModules");
class TeamsShell {
    constructor(host, listener) {
        this.listener = listener;
        this.host = host;
        this.snackbarCallbacks = [];
        this.backNavigationHandler = undefined;
        this.titleClickHandler = undefined;
    }
    destroy() {
        this.listener = undefined;
        this.clearSnackbarCallbacks();
    }
    showSnackbar(snackbar, callback) {
        // Validate snackbar argument
        if (ArgumentsValidator_1.ArgumentsValidator.warnIfNullOrUndefined('snackbar', snackbar)) {
            return;
        }
        if (ArgumentsValidator_1.ArgumentsValidator.warnIfNullOrUndefined('snackbar.title', snackbar.title)) {
            return;
        }
        // Add the snackbar callback.
        SdkNativeModules_1.TeamsShellInteractor.showSnackbar(this.host.hostInstanceId, snackbar, (snackbarId) => {
            if (snackbarId !== -1) {
                this.snackbarCallbacks.push(new SnackbarCallback_1.SnackbarCallback(snackbarId, snackbar));
            }
            if (callback) {
                callback(snackbarId);
            }
        });
    }
    dismissSnackbar(snackbarId, callback) {
        SdkNativeModules_1.TeamsShellInteractor.dismissSnackbar(snackbarId, (dismissed) => {
            let index = 0;
            for (; index < this.snackbarCallbacks.length; index++) {
                const snackbarCallback = this.snackbarCallbacks[index];
                if (snackbarCallback && snackbarCallback.getSnackbarId() === snackbarId) {
                    break;
                }
            }
            if (index < this.snackbarCallbacks.length) {
                this.snackbarCallbacks.splice(index, 1);
            }
            if (callback) {
                callback(dismissed);
            }
        });
    }
    setTitle(title) {
        // Validate title argument
        if (ArgumentsValidator_1.ArgumentsValidator.warnIfNullOrUndefined('title', title)) {
            return;
        }
        SdkNativeModules_1.TeamsShellInteractor.setTitle(this.host.hostInstanceId, title);
    }
    setTitleWithCallBack(title, callback) {
        // Validate title argument
        if (ArgumentsValidator_1.ArgumentsValidator.warnIfNullOrUndefined('title', title)) {
            return;
        }
        SdkNativeModules_1.TeamsShellInteractor.setTitleWithCallBack(this.host.hostInstanceId, title);
        this.titleClickHandler = callback;
    }
    setHomeIndicatorBackgroundColorIOS(colorInHexCode) {
        // Validate colorInHexCode argument
        if (ArgumentsValidator_1.ArgumentsValidator.warnIfNullOrUndefined('colorInHexCode', colorInHexCode)) {
            return;
        }
        SdkNativeModules_1.TeamsShellInteractor.setHomeIndicatorBackgroundColorIOS(this.host.hostInstanceId, colorInHexCode);
    }
    setSubtitle(subtitle) {
        // Validate sub title argument
        if (ArgumentsValidator_1.ArgumentsValidator.warnIfNullOrUndefined('subtitle', subtitle)) {
            return;
        }
        SdkNativeModules_1.TeamsShellInteractor.setSubtitle(this.host.hostInstanceId, subtitle);
    }
    setBackgroundColor(color) {
        // Validate title argument
        if (ArgumentsValidator_1.ArgumentsValidator.warnIfNullOrUndefined('color', color)) {
            return;
        }
        SdkNativeModules_1.TeamsShellInteractor.setBackgroundColor(this.host.hostInstanceId, color);
    }
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
    showActionSheet(actionSheet, onOptionSelected, onCanceled) {
        if (ArgumentsValidator_1.ArgumentsValidator.warnIfNullOrUndefined('actionSheet', actionSheet)) {
            return;
        }
        if (ArgumentsValidator_1.ArgumentsValidator.warnIfNullOrUndefined('onOptionSelect', onOptionSelected)) {
            return;
        }
        SdkNativeModules_1.TeamsShellInteractor.showActionSheet(this.host.hostInstanceId, actionSheet, onOptionSelected, onCanceled);
    }
    showFabLayoutAndroid(fabLayoutParams) {
        if (ArgumentsValidator_1.ArgumentsValidator.warnIfNullOrUndefined('fabLayout', fabLayoutParams)) {
            return;
        }
        SdkNativeModules_1.TeamsShellInteractor.enableFabLayoutAndroid(this.host.hostInstanceId, fabLayoutParams);
    }
    hideFabLayoutAndroid() {
        SdkNativeModules_1.TeamsShellInteractor.disableFabLayoutAndroid(this.host.hostInstanceId);
    }
    invalidateOptionsMenu() {
        this.setOptionsMenu();
    }
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
    registerBackNavigationHandler(handler) {
        this.backNavigationHandler = handler;
        SdkNativeModules_1.TeamsShellInteractor.registerBackNavigationHandler(this.host.hostInstanceId);
    }
    /**
     * Removes any registered back navigation handlers. You app will no longer have control
     * of back navigation. The default back navigation behaviour of the Teams app will come
     * into effect.
     */
    removeBackNavigationHandler() {
        this.backNavigationHandler = undefined;
        SdkNativeModules_1.TeamsShellInteractor.removeBackNavigationHandler(this.host.hostInstanceId);
    }
    /**
     * Sets up a title dropdown. The items are displayed in the order in which they
     * are passed to this method. The first item will be selected by default.
     *
     * @param items items to be displayed in the title dropdown
     */
    setTitleDropdown(items, handler) {
        SdkNativeModules_1.TeamsShellInteractor.setTitleDropdown(this.host.hostInstanceId, items);
        this.titleDropdownHandler = handler;
    }
    /**
     * Sets up tabs in AppBar(Android)/NavigationBar(iOS)
     *
     * @param tabList list of tabs to be added to AppBar(Android)/NavigationBar(iOS)
     */
    setUpTabs(tabList) {
        SdkNativeModules_1.TeamsShellInteractor.setUpTabs(this.host.hostInstanceId, tabList);
    }
    /**
     * Sets up tabs in AppBar(Android)/NavigationBar(iOS) with default selected tab
     * If defaultSelectedTabId is not present in tab list, first tab from tab list gets selected.
     *
     * @param tabList list of tabs to be added to AppBar(Android)/NavigationBar(iOS)
     * @param defaultSelectedTabId tab that should be selected when the tabbed view is loaded
     */
    setUpTabsWithDefaultSelectedTab(tabList, defaultSelectedTabId) {
        SdkNativeModules_1.TeamsShellInteractor.setUpTabsWithDefaultSelectedTab(this.host.hostInstanceId, tabList, defaultSelectedTabId);
    }
    setOptionsMenu() {
        if (this.listener) {
            SdkNativeModules_1.TeamsShellInteractor.setOptionsMenu(this.host.hostInstanceId, this.listener.getOptionsMenuItems());
        }
    }
    clearSnackbarCallbacks() {
        this.snackbarCallbacks = [];
    }
}
exports.TeamsShell = TeamsShell;
