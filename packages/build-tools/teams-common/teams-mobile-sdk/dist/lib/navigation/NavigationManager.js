"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.NavigationManager = void 0;
const react_native_1 = require("react-native");
const uuid = require("uuid/v4");
const SdkNativeModules_1 = require("../services/SdkNativeModules");
const ViewParamsBundle_1 = require("./ViewParamsBundle");
const NativeEvents = {
    hostClosed: 'onHostClosed'
};
/**
 * @hidden from docs
 */
class NavigationManager {
    constructor() {
        this.viewParamsStore = new Map();
        this.results = new Map();
        this.subscribeToNativeEvents();
    }
    static getInstance() {
        if (this.globalNavigationManager === undefined) {
            this.globalNavigationManager = new NavigationManager();
        }
        return this.globalNavigationManager;
    }
    retrieveViewParams(viewParamsKey) {
        const viewParmas = this.viewParamsStore.get(viewParamsKey);
        return viewParmas;
    }
    deleteViewParams(viewParamsKey) {
        this.viewParamsStore.delete(viewParamsKey);
    }
    /**
     * Wrapper for method with the same name in the `NavigationService` module.
     */
    openView(viewId, params) {
        this.openViewForResult(viewId, params, undefined);
    }
    /**
     * Wrapper for method with the same name in the `NavigationService` module.
     */
    openViewForResult(viewId, params, callback) {
        let viewParamsKey = '';
        if (params) {
            viewParamsKey = uuid();
            const viewParams = new ViewParamsBundle_1.ViewParamsBundle(params, callback);
            this.viewParamsStore.set(viewParamsKey, viewParams);
        }
        SdkNativeModules_1.NavigationService.openView(viewId, viewParamsKey);
    }
    /**
     * Wrapper for method with the same name in the `NavigationService` module.
     */
    closeView(hostInstanceId, animated) {
        if (animated === undefined) {
            animated = true;
        }
        SdkNativeModules_1.NavigationService.closeView(hostInstanceId, animated);
    }
    closeViewForResult(hostInstanceId, result, animated) {
        this.setResultForHost(hostInstanceId, result);
        this.closeView(hostInstanceId, animated);
    }
    setResultForHost(hostInstanceId, result) {
        this.results.set(hostInstanceId, result);
    }
    subscribeToNativeEvents() {
        // Add listeners for shell events from native
        react_native_1.NativeAppEventEmitter.addListener(NativeEvents.hostClosed, this.onHostClosed.bind(this));
    }
    /**
     * Handler for `onHostClosed` native event.
     */
    onHostClosed(event) {
        if (!event) {
            return;
        }
        const result = this.results.get(event.closedHostInstanceId);
        if (!result) {
            return;
        }
        this.results.delete(event.closedHostInstanceId);
        result.callback(result.result);
    }
}
exports.NavigationManager = NavigationManager;
