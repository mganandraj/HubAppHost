"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ViewLifecycleNativeEventsDispatcher = void 0;
const react_native_1 = require("react-native");
const SdkNativeModules_1 = require("../services/SdkNativeModules");
const viewEvents = {
    viewAppear: 'onViewAppear'
};
/**
 * @hidden from docs
 *
 * Subscribes to all the native Teams view events and routes them to their intended
 * targets.
 */
class ViewLifecycleNativeEventsDispatcher {
    constructor() {
        this.LOG_TAG = 'ViewLifecycleNativeEventsDispatcher';
        this.onViewAppear = (event) => {
            const targetView = this.activeViews.get(event.hostInstanceId);
            if (targetView) {
                targetView.onViewAppear();
            }
        };
        this.activeEmitterSubscriptions = [];
        this.activeViews = new Map();
    }
    static getInstance() {
        if (!this.globalViewLifecycleNativeEventsDispatcher) {
            this.globalViewLifecycleNativeEventsDispatcher = new ViewLifecycleNativeEventsDispatcher();
        }
        return this.globalViewLifecycleNativeEventsDispatcher;
    }
    registerView(hostInstanceId, view) {
        SdkNativeModules_1.Logger.logInformation(this.LOG_TAG, `Registered view for host ${hostInstanceId}`);
        if (this.activeViews.size === 0) {
            this.subscribeToNativeEvents();
        }
        this.activeViews.set(hostInstanceId, view);
    }
    deregisterView(hostInstanceId, view) {
        const currentlyRegisteredView = this.activeViews.get(hostInstanceId);
        if (currentlyRegisteredView && currentlyRegisteredView === view) {
            this.activeViews.delete(hostInstanceId);
        }
        if (this.activeViews.size === 0) {
            this.unsubscribeToNativeEvents();
        }
    }
    subscribeToNativeEvents() {
        this.activeEmitterSubscriptions.push(react_native_1.NativeAppEventEmitter.addListener(viewEvents.viewAppear, this.onViewAppear));
    }
    unsubscribeToNativeEvents() {
        this.activeEmitterSubscriptions.forEach((subscription) => {
            subscription.remove();
        });
        this.activeEmitterSubscriptions = [];
    }
}
exports.ViewLifecycleNativeEventsDispatcher = ViewLifecycleNativeEventsDispatcher;
