/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import * as Path from 'path';

const sdkCacheRelativePath = '.sdk_cache';
const sdkConfigCachePath = 'config/config.json';
const teamsDevAppApkCacheDirectoryName = 'apk';
const teamsDevAppIpaCacheDirectoryName = 'ipa';
const teamsDevAppApkCacheFileName = 'Teams_dev_debug.apk';
const teamsDevAppIpaCacheFileName = 'Teams_dev_debug.ipa';

export class SdkCache {
  public static getSdkCachePath (appSrcDirectory: string): string {
    return Path.resolve(appSrcDirectory, sdkCacheRelativePath);
  }

  public static getTeamsDevAppApkCacheDirectoryPath (appSrcDirectory: string): string {
    return Path.resolve(SdkCache.getSdkCachePath(appSrcDirectory), teamsDevAppApkCacheDirectoryName);
  }

  public static getTeamsDevAppApkCachePath (appSrcDirectory: string): string {
    return Path.resolve(SdkCache.getTeamsDevAppApkCacheDirectoryPath(appSrcDirectory), teamsDevAppApkCacheFileName);
  }

  public static getTeamsDevAppIpaCacheDirectoryPath (appSrcDirectory: string): string {
    return Path.resolve(SdkCache.getSdkCachePath(appSrcDirectory), teamsDevAppIpaCacheDirectoryName);
  }

  public static getTeamsDevAppIpaCachePath (appSrcDirectory: string): string {
    return Path.resolve(SdkCache.getTeamsDevAppIpaCacheDirectoryPath(appSrcDirectory), teamsDevAppIpaCacheFileName);
  }

  public static getConfigCachePath (appSrcDirectory: string): string {
    return Path.resolve(SdkCache.getSdkCachePath(appSrcDirectory), sdkConfigCachePath);
  }
}
