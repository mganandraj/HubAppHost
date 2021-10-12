"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.TeamsShellNativeEventsDispatcher = void 0;
const react_native_1 = require("react-native");
const SdkNativeModules_1 = require("../services/SdkNativeModules");
const shellEvents = {
    optionsMenuInvalidated: 'onShellOptionsMenuInvalidated',
    optionsMenuItemSelected: 'onShellOptionsMenuItemSelected',
    snackbarActionSelected: 'onSnackbarActionSelected',
    primaryFabClick: 'onPrimaryFabClick',
    secondaryFabClick: 'onSecondaryFabClick',
    titleDropdownItemSelected: 'onTitleDropdownItemSelected',
    backNavigationInitiated: 'onBackNavigationInitiated',
    onTabSelected: "onTabSelected",
    onTitleClicked: 'onTitleClicked'
};
/**
 * @hidden from docs
 *
 * Subscribes to all the native Teams shell events and routes them to their intended
 * targets.
 */
class TeamsShellNativeEventsDispatcher {
    constructor() {
        this.LOG_TAG = 'TeamsShellNativeEventsDispatcher';
        this.onOptionsMenuInvalidated = (event) => {
            const targetShell = this.activeShells.get(event.hostInstanceId);
            if (targetShell) {
                targetShell.onOptionsMenuInvalidated(event);
            }
        };
        this.onOptionsMenuItemSelected = (event) => {
            const targetShell = this.activeShells.get(event.hostInstanceId);
            if (targetShell) {
                targetShell.onOptionsMenuItemSelected(event);
            }
        };
        this.onTabSelected = (event) => {
            const targetShell = this.activeShells.get(event.hostInstanceId);
            if (targetShell) {
                targetShell.onTabSelected(event);
            }
        };
        this.onSnackbarActionSelected = (event) => {
            const targetShell = this.activeShells.get(event.hostInstanceId);
            if (targetShell) {
                targetShell.onSnackbarActionSelected(event);
            }
        };
        this.onPrimaryFabClick = (event) => {
            const targetShell = this.activeShells.get(event.hostInstanceId);
            if (targetShell) {
                targetShell.onPrimaryFabClick(event);
            }
        };
        this.onSecondaryFabClick = (event) => {
            const targetShell = this.activeShells.get(event.hostInstanceId);
            if (targetShell) {
                targetShell.onSecondaryFabClick(event);
            }
        };
        this.onTitleDropdownItemSelected = (event) => {
            const targetShell = this.activeShells.get(event.hostInstanceId);
            if (targetShell) {
                targetShell.onTitleDropdownItemSelected(event);
            }
            else {
                SdkNativeModules_1.Logger.logWarning(this.LOG_TAG, 'Received title dropdown item selected event but there are no registered targets');
            }
        };
        this.onBackNavigationInitiated = (event) => {
            const targetShell = this.activeShells.get(event.hostInstanceId);
            if (targetShell) {
                targetShell.onBackNavigationInitiated(event);
            }
        };
        this.onTitleClicked = (event) => {
            const targetShell = this.activeShells.get(event.hostInstanceId);
            if (targetShell) {
                targetShell.onTitleClicked(event);
            }
        };
        this.activeEmitterSubscriptions = [];
        this.activeShells = new Map();
        // this.teamsShellEventEmitter = new NativeEventEmitter(TeamsShellInteractorImpl);
    }
    static getInstance() {
        if (!this.globalTeamsShellNativeEventsDispatcher) {
            this.globalTeamsShellNativeEventsDispatcher = new TeamsShellNativeEventsDispatcher();
        }
        return this.globalTeamsShellNativeEventsDispatcher;
    }
    registerShell(hostInstanceId, shell) {
        SdkNativeModules_1.Logger.logInformation(this.LOG_TAG, `Registered shell for host ${hostInstanceId}`);
        if (this.activeShells.size === 0) {
            this.subscribeToNativeEvents();
        }
        this.activeShells.set(hostInstanceId, shell);
    }
    deregisterShell(hostInstanceId, shell) {
        const currentlyRegisteredShell = this.activeShells.get(hostInstanceId);
        if (currentlyRegisteredShell && currentlyRegisteredShell === shell) {
            this.activeShells.delete(hostInstanceId);
        }
        if (this.activeShells.size === 0) {
            this.unsubscribeToNativeEvents();
        }
    }
    subscribeToNativeEvents() {
        this.activeEmitterSubscriptions.push(react_native_1.NativeAppEventEmitter.addListener(shellEvents.optionsMenuInvalidated, this.onOptionsMenuInvalidated), react_native_1.NativeAppEventEmitter.addListener(shellEvents.optionsMenuItemSelected, this.onOptionsMenuItemSelected), react_native_1.NativeAppEventEmitter.addListener(shellEvents.snackbarActionSelected, this.onSnackbarActionSelected), react_native_1.NativeAppEventEmitter.addListener(shellEvents.primaryFabClick, this.onPrimaryFabClick), react_native_1.NativeAppEventEmitter.addListener(shellEvents.secondaryFabClick, this.onSecondaryFabClick), react_native_1.NativeAppEventEmitter.addListener(shellEvents.titleDropdownItemSelected, this.onTitleDropdownItemSelected), react_native_1.NativeAppEventEmitter.addListener(shellEvents.backNavigationInitiated, this.onBackNavigationInitiated), react_native_1.NativeAppEventEmitter.addListener(shellEvents.onTabSelected, this.onTabSelected), react_native_1.NativeAppEventEmitter.addListener(shellEvents.onTitleClicked, this.onTitleClicked));
    }
    unsubscribeToNativeEvents() {
        this.activeEmitterSubscriptions.forEach((subscription) => {
            subscription.remove();
        });
        this.activeEmitterSubscriptions = [];
    }
}
exports.TeamsShellNativeEventsDispatcher = TeamsShellNativeEventsDispatcher;
