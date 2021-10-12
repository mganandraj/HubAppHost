"use strict";
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.EventLogger = void 0;
const SdkNativeModules_1 = require("../SdkNativeModules");
class EventLogger {
    constructor(hostInstanceId) {
        this.hostInstanceId = hostInstanceId;
    }
    logViewReady() {
        SdkNativeModules_1.SdkNativeEventPublisher.publishEvent('sdkAppViewReady', {
            event: {
                appInstanceId: this.hostInstanceId
            }
        });
    }
    logContentReady() {
        // Currently Android code is listening to 'sdkAppHostReady' event for content ready.
        // TODO remove it once android code adoption to 'skdAppContentReady' is completed.
        // [Task 1270089: [RN][Android] Change event name 'sdkAppHostReady' to 'sdkAppContentReady' in Native code.]
        SdkNativeModules_1.SdkNativeEventPublisher.publishEvent('sdkAppHostReady', {
            event: {
                appInstanceId: this.hostInstanceId
            }
        });
        SdkNativeModules_1.SdkNativeEventPublisher.publishEvent('sdkAppContentReady', {
            event: {
                appInstanceId: this.hostInstanceId
            }
        });
    }
}
exports.EventLogger = EventLogger;
