/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
export declare class SdkCache {
    static getSdkCachePath(appSrcDirectory: string): string;
    static getTeamsDevAppApkCacheDirectoryPath(appSrcDirectory: string): string;
    static getTeamsDevAppApkCachePath(appSrcDirectory: string): string;
    static getTeamsDevAppIpaCacheDirectoryPath(appSrcDirectory: string): string;
    static getTeamsDevAppIpaCachePath(appSrcDirectory: string): string;
    static getConfigCachePath(appSrcDirectory: string): string;
}
