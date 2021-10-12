import * as Path from 'path';
import { SdkCache } from '../src/tools/sdkcache/SdkCache';

const appSrcDirectory = '__tests__';
const sdkCacheRelativePath = '.sdk_cache';
const sdkConfigCachePath = Path.join('config', 'config.json');
const teamsDevAppApkCacheDirectoryName = 'apk';
const teamsDevAppApkCacheFileName = 'Teams_dev_debug.apk';

it('should return path to SDK cache', () => {
  expect(SdkCache.getSdkCachePath(appSrcDirectory)).toMatch(Path.resolve(__dirname, '.sdk_cache'));
});

it('should return path to Teams Dev app APK cache directory', () => {
  expect(SdkCache.getTeamsDevAppApkCacheDirectoryPath(appSrcDirectory)).toMatch(Path.resolve(__dirname, sdkCacheRelativePath, teamsDevAppApkCacheDirectoryName));
});

it('should return path to Teams Dev app APK cache', () => {
  expect(SdkCache.getTeamsDevAppApkCachePath(appSrcDirectory)).toMatch(Path.resolve(__dirname, sdkCacheRelativePath, teamsDevAppApkCacheDirectoryName, teamsDevAppApkCacheFileName));
});

it('should return path to config cache', () => {
  expect(SdkCache.getConfigCachePath(appSrcDirectory)).toMatch(Path.resolve(__dirname, sdkCacheRelativePath, sdkConfigCachePath));
});
