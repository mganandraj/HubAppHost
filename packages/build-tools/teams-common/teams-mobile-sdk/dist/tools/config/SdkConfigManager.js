"use strict";
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.SdkConfigManager = void 0;
const Path = require("path");
const FileUtilities_1 = require("../../common/utilities/FileUtilities");
const SdkCache_1 = require("../sdkcache/SdkCache");
/**
 * Manages the configuration needed by SDK to perform different tasks like build, install, etc.
 * Developers use this to configure their development environment.
 */
class SdkConfigManager {
    static getUserApiToken() {
        return SdkConfigManager.get('userApiToken');
    }
    static get(configKey) {
        if (!configKey) {
            throw new Error('Config key (' + configKey + ') is invalid.');
        }
        const configObject = SdkConfigManager.readConfigObject();
        return configObject[configKey];
    }
    static getAll() {
        return SdkConfigManager.readConfigObject();
    }
    static set(configKey, configValue) {
        const configObject = SdkConfigManager.readConfigObject();
        configObject[configKey] = configValue;
        SdkConfigManager.writeConfigObject(configObject);
    }
    static remove(configKey) {
        SdkConfigManager.set(configKey, undefined);
    }
    static readConfigObject() {
        const configCachePath = SdkCache_1.SdkCache.getConfigCachePath(Path.resolve('.'));
        return FileUtilities_1.FileUtilities.readJsonSync(configCachePath) || {};
    }
    static writeConfigObject(configObject) {
        const configCachePath = SdkCache_1.SdkCache.getConfigCachePath(Path.resolve('.'));
        FileUtilities_1.FileUtilities.writeJsonSync(configCachePath, configObject);
    }
}
exports.SdkConfigManager = SdkConfigManager;
