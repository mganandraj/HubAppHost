/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import { ArgumentsValidator } from '../../common/utilities/ArgumentsValidator';
import { HostContext } from '../application/HostContext';
import { ActionSheet } from '../models/shell/ActionSheet';
import { FabLayoutParams } from '../models/shell/FabLayoutParams';
import { Snackbar } from '../models/shell/Snackbar';
import { SnackbarCallback } from '../models/shell/SnackbarCallback';
import { TeamsShellTab } from '../models/shell/TeamsShellTab';
import { TitleDropdownItem } from '../models/shell/TitleDropdownItem';
import { TeamsShellInteractor as TeamsShellInteractorImpl } from '../services/SdkNativeModules';
import { TeamsShellInteractionListener } from './TeamsShellInteractionListener';

export class TeamsShell {
  protected host: HostContext;
  protected listener: TeamsShellInteractionListener | undefined;
  protected snackbarCallbacks: SnackbarCallback[];
  protected titleDropdownHandler?: (selectedItemId: string) => void;
  protected backNavigationHandler?: () => void;
  protected titleClickHandler?: () => void;

  constructor (host: HostContext, listener: TeamsShellInteractionListener) {
    this.listener = listener;
    this.host = host;
    this.snackbarCallbacks = [];
    this.backNavigationHandler = undefined;
    this.titleClickHandler = undefined;
  }

  public destroy () {
    this.listener = undefined;
    this.clearSnackbarCallbacks();
  }

  public showSnackbar (snackbar: Snackbar, callback?: (snackbarId: number) => void): void {
    // Validate snackbar argument
    if (ArgumentsValidator.warnIfNullOrUndefined('snackbar', snackbar)) {
      return;
    }
    if (ArgumentsValidator.warnIfNullOrUndefined('snackbar.title', snackbar.title)) {
      return;
    }

    // Add the snackbar callback.
    TeamsShellInteractorImpl.showSnackbar(this.host.hostInstanceId, snackbar, (snackbarId: number) => {
      if (snackbarId !== -1) {
        this.snackbarCallbacks.push(new SnackbarCallback(snackbarId, snackbar));
      }

      if (callback) {
        callback(snackbarId);
      }
    });
  }

  public dismissSnackbar (snackbarId: number, callback?: (dismissed: boolean) => void): void {
    TeamsShellInteractorImpl.dismissSnackbar(snackbarId, (dismissed: boolean) => {
      let index = 0;
      for (; index < this.snackbarCallbacks.length; index++) {
        const snackbarCallback: SnackbarCallback = this.snackbarCallbacks[index];
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

  public setTitle (title: string): void {
    // Validate title argument
    if (ArgumentsValidator.warnIfNullOrUndefined('title', title)) {
      return;
    }

    TeamsShellInteractorImpl.setTitle(this.host.hostInstanceId, title);
  }

  public setTitleWithCallBack (title: string, callback: () => void): void {
    // Validate title argument
    if (ArgumentsValidator.warnIfNullOrUndefined('title', title)) {
      return;
    }

    TeamsShellInteractorImpl.setTitleWithCallBack(this.host.hostInstanceId, title);
    this.titleClickHandler = callback;
  }

  public setHomeIndicatorBackgroundColorIOS (colorInHexCode: string){
    // Validate colorInHexCode argument
    if (ArgumentsValidator.warnIfNullOrUndefined('colorInHexCode', colorInHexCode)) {
      return;
    }

    TeamsShellInteractorImpl.setHomeIndicatorBackgroundColorIOS(this.host.hostInstanceId, colorInHexCode);
  }

  public setSubtitle (subtitle: string): void {
    // Validate sub title argument
    if (ArgumentsValidator.warnIfNullOrUndefined('subtitle', subtitle)) {
      return;
    }

    TeamsShellInteractorImpl.setSubtitle(this.host.hostInstanceId, subtitle);
  }

  public setBackgroundColor (color: string): void {
    // Validate title argument
    if (ArgumentsValidator.warnIfNullOrUndefined('color', color)) {
      return;
    }
    TeamsShellInteractorImpl.setBackgroundColor(this.host.hostInstanceId, color);
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
  public showActionSheet (actionSheet: ActionSheet, onOptionSelected: (selectedOptionId: string) => void, onCanceled?: () => void): void {
    if (ArgumentsValidator.warnIfNullOrUndefined('actionSheet', actionSheet)) {
      return;
    }
    if (ArgumentsValidator.warnIfNullOrUndefined('onOptionSelect', onOptionSelected)) {
      return;
    }

    TeamsShellInteractorImpl.showActionSheet(this.host.hostInstanceId, actionSheet, onOptionSelected, onCanceled);
  }

  public showFabLayoutAndroid (fabLayoutParams: FabLayoutParams): void {
    if (ArgumentsValidator.warnIfNullOrUndefined('fabLayout', fabLayoutParams)) {
      return;
    }
    TeamsShellInteractorImpl.enableFabLayoutAndroid(this.host.hostInstanceId, fabLayoutParams);
  }

  public hideFabLayoutAndroid (): void {
    TeamsShellInteractorImpl.disableFabLayoutAndroid(this.host.hostInstanceId);
  }

  public invalidateOptionsMenu () {
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
  public registerBackNavigationHandler (handler: () => void): void {
    this.backNavigationHandler = handler;
    TeamsShellInteractorImpl.registerBackNavigationHandler(this.host.hostInstanceId);
  }

  /**
   * Removes any registered back navigation handlers. You app will no longer have control
   * of back navigation. The default back navigation behaviour of the Teams app will come
   * into effect.
   */
  public removeBackNavigationHandler (): void {
    this.backNavigationHandler = undefined;
    TeamsShellInteractorImpl.removeBackNavigationHandler(this.host.hostInstanceId);
  }

  /**
   * Sets up a title dropdown. The items are displayed in the order in which they
   * are passed to this method. The first item will be selected by default.
   *
   * @param items items to be displayed in the title dropdown
   */
  public setTitleDropdown (items: TitleDropdownItem[], handler: (selectedItemId: string) => void) {
    TeamsShellInteractorImpl.setTitleDropdown(this.host.hostInstanceId, items);
    this.titleDropdownHandler = handler;
  }

  /**
   * Sets up tabs in AppBar(Android)/NavigationBar(iOS)
   * 
   * @param tabList list of tabs to be added to AppBar(Android)/NavigationBar(iOS)
   */
  public setUpTabs (tabList: TeamsShellTab[]): void {
    TeamsShellInteractorImpl.setUpTabs(this.host.hostInstanceId, tabList);
  }

  /**
   * Sets up tabs in AppBar(Android)/NavigationBar(iOS) with default selected tab
   * If defaultSelectedTabId is not present in tab list, first tab from tab list gets selected.
   *
   * @param tabList list of tabs to be added to AppBar(Android)/NavigationBar(iOS)
   * @param defaultSelectedTabId tab that should be selected when the tabbed view is loaded
   */
  public setUpTabsWithDefaultSelectedTab (tabList: TeamsShellTab[], defaultSelectedTabId: string): void {
    TeamsShellInteractorImpl.setUpTabsWithDefaultSelectedTab(this.host.hostInstanceId, tabList, defaultSelectedTabId);
  }

  protected setOptionsMenu (): void {
    if (this.listener) {
      TeamsShellInteractorImpl.setOptionsMenu(this.host.hostInstanceId, this.listener.getOptionsMenuItems());
    }
  }

  private clearSnackbarCallbacks (): void {
    this.snackbarCallbacks = [];
  }
}
