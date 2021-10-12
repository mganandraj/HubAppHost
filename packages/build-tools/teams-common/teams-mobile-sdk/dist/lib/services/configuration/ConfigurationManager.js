"use strict";
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.ConfigurationManager = void 0;
const SdkNativeModules_1 = require("../SdkNativeModules");
class ConfigurationManager {
    constructor() {
    }
    static getInstance() {
        if (!this.instance) {
            this.instance = new ConfigurationManager();
        }
        return this.instance;
    }
    setAriaTenant(tenantToken) {
        SdkNativeModules_1.Logger.setAriaTenant(tenantToken);
        SdkNativeModules_1.NativeTelemetryClient.setAriaTenant(tenantToken);
    }
}
exports.ConfigurationManager = ConfigurationManager;
