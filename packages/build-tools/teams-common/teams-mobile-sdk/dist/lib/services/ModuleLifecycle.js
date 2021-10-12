"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ModuleLifecycle = void 0;
const react_native_1 = require("react-native");
const Events = {
    ON_USER_SIGNED_OUT: 'onUserSignedOut'
};
class ModuleLifecycle {
    static registerHandler(handler) {
        if (!this.handler) {
            this.subscribeToEvents();
        }
        this.handler = handler;
    }
    static subscribeToEvents() {
        react_native_1.NativeAppEventEmitter.addListener(Events.ON_USER_SIGNED_OUT, this.onUserSignedOut);
    }
}
exports.ModuleLifecycle = ModuleLifecycle;
ModuleLifecycle.onUserSignedOut = (event) => {
    if (ModuleLifecycle.handler) {
        ModuleLifecycle.handler.onUserSignedOut(event);
    }
};
