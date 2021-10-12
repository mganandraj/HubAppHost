"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.TeamsShellPrivate = void 0;
const SdkNativeModules_1 = require("../services/SdkNativeModules");
const SdkNativeModules_2 = require("../services/SdkNativeModules");
const TeamsShell_1 = require("./TeamsShell");
const TeamsShellNativeEventsDispatcher_1 = require("./TeamsShellNativeEventsDispatcher");
/**
 * @hidden from docs
 * Not exposed to outside developer. They will use TeamsShell instead.
 */
class TeamsShellPrivate extends TeamsShell_1.TeamsShell {
    constructor(host, listener) {
        super(host, listener);
        this.LOG_TAG = 'TeamsShellPrivate';
        TeamsShellNativeEventsDispatcher_1.TeamsShellNativeEventsDispatcher.getInstance().registerShell(this.host.hostInstanceId, this);
    }
    destroy() {
        super.destroy();
        TeamsShellNativeEventsDispatcher_1.TeamsShellNativeEventsDispatcher.getInstance().deregisterShell(this.host.hostInstanceId, this);
    }
    closeModule(success) {
        if (success) {
            SdkNativeModules_1.TeamsShellInteractor.closeModuleWithResult(this.host.hostInstanceId, success);
        }
        else {
            SdkNativeModules_1.TeamsShellInteractor.closeModule(this.host.hostInstanceId);
        }
    }
    onOptionsMenuInvalidated(_) {
        this.invalidateOptionsMenu();
    }
    onOptionsMenuItemSelected(event) {
        if (this.listener && event) {
            this.listener.onOptionsMenuItemSelected(event.optionsMenuItemId);
        }
    }
    onTabSelected(event) {
        if (this.listener && event) {
            this.listener.onTabSelected(event.tabId);
        }
    }
    onSnackbarActionSelected(event) {
        if (event && event.snackbarId && event.snackbarActionId) {
            for (const snackbarCallback of this.snackbarCallbacks) {
                if (snackbarCallback && snackbarCallback.getSnackbarId() === event.snackbarId) {
                    snackbarCallback.onActionSelected(event.snackbarActionId);
                }
            }
        }
    }
    onPrimaryFabClick(event) {
        if (this.listener && event) {
            this.listener.onPrimaryFabClick();
        }
    }
    onSecondaryFabClick(event) {
        if (this.listener && event && event.buttonId) {
            this.listener.onSecondaryFabClick(event.buttonId);
        }
    }
    onTitleDropdownItemSelected(event) {
        if (this.titleDropdownHandler) {
            this.titleDropdownHandler(event.selectedItemId);
        }
        else {
            SdkNativeModules_2.Logger.logWarning(this.LOG_TAG, 'Received title dropdown item selected event but no handler is registered.');
        }
    }
    onBackNavigationInitiated(_) {
        if (this.backNavigationHandler) {
            this.backNavigationHandler();
        }
    }
    onTitleClicked(event) {
        if (event && this.titleClickHandler) {
            this.titleClickHandler();
        }
    }
}
exports.TeamsShellPrivate = TeamsShellPrivate;
