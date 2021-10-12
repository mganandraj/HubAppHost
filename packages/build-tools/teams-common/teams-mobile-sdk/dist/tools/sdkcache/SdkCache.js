"use strict";
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.SdkCache = void 0;
const Path = require("path");
const sdkCacheRelativePath = '.sdk_cache';
const sdkConfigCachePath = 'config/config.json';
const teamsDevAppApkCacheDirectoryName = 'apk';
const teamsDevAppIpaCacheDirectoryName = 'ipa';
const teamsDevAppApkCacheFileName = 'Teams_dev_debug.apk';
const teamsDevAppIpaCacheFileName = 'Teams_dev_debug.ipa';
class SdkCache {
    static getSdkCachePath(appSrcDirectory) {
        return Path.resolve(appSrcDirectory, sdkCacheRelativePath);
    }
    static getTeamsDevAppApkCacheDirectoryPath(appSrcDirectory) {
        return Path.resolve(SdkCache.getSdkCachePath(appSrcDirectory), teamsDevAppApkCacheDirectoryName);
    }
    static getTeamsDevAppApkCachePath(appSrcDirectory) {
        return Path.resolve(SdkCache.getTeamsDevAppApkCacheDirectoryPath(appSrcDirectory), teamsDevAppApkCacheFileName);
    }
    static getTeamsDevAppIpaCacheDirectoryPath(appSrcDirectory) {
        return Path.resolve(SdkCache.getSdkCachePath(appSrcDirectory), teamsDevAppIpaCacheDirectoryName);
    }
    static getTeamsDevAppIpaCachePath(appSrcDirectory) {
        return Path.resolve(SdkCache.getTeamsDevAppIpaCacheDirectoryPath(appSrcDirectory), teamsDevAppIpaCacheFileName);
    }
    static getConfigCachePath(appSrcDirectory) {
        return Path.resolve(SdkCache.getSdkCachePath(appSrcDirectory), sdkConfigCachePath);
    }
}
exports.SdkCache = SdkCache;
