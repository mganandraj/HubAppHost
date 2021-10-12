"use strict";
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.TraceLogger = void 0;
const SdkNativeModules_1 = require("../SdkNativeModules");
class TraceLogger {
    static logVerbose(tag, message) {
        SdkNativeModules_1.Logger.logVerbose(tag, TraceLogger.createMessage(message));
    }
    static logInformation(tag, message) {
        SdkNativeModules_1.Logger.logInformation(tag, TraceLogger.createMessage(message));
    }
    static logDebug(tag, message) {
        SdkNativeModules_1.Logger.logDebug(tag, TraceLogger.createMessage(message));
    }
    static logWarning(tag, message) {
        SdkNativeModules_1.Logger.logWarning(tag, TraceLogger.createMessage(message));
    }
    static logError(tag, message) {
        SdkNativeModules_1.Logger.logError(tag, TraceLogger.createMessage(message));
    }
    static createMessage(message) {
        return 'Message: ' + message;
    }
}
exports.TraceLogger = TraceLogger;
