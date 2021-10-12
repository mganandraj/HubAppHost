/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import * as ChildProcess from 'child_process';
import * as FileSystem from 'fs-extra';
import * as Path from 'path';
import { SdkCache } from '../sdkcache/SdkCache';
import { TeamsDevAppDownloader } from './TeamsDevAppDownloader';


const PLATFORM_ANDROID = 'android';
const PLATFORM_IOS = 'ios';

/**
 * Implementation of Teams Dev App Installer.
 *
 * Downloads the configured Teams Dev App from App Center using the user API token specified by the developer
 * and installs the app on the user device. It also performs caching of the app to avoid downloading it over the wire every time.
 */
export class TeamsDevAppInstaller {
  public static installTeamsDevApp (platform: string): void {
    if (platform === PLATFORM_IOS) {
      TeamsDevAppInstaller.installTeamsIosApp();
    } else if (platform === PLATFORM_ANDROID) {
      TeamsDevAppInstaller.installTeamsAndroidApp();
    }
  }

  private static installTeamsAndroidApp() {
    const appDirectory = Path.resolve('.');
    const teamsDevAppApkPath = SdkCache.getTeamsDevAppApkCachePath(appDirectory);
    
    if (!FileSystem.existsSync(teamsDevAppApkPath)) {
      console.error("Teams dev app doesn't exist in the cache. Downloading Teams dev app apk from App Center.");
      FileSystem.ensureDirSync(SdkCache.getTeamsDevAppApkCacheDirectoryPath(appDirectory));
      TeamsDevAppDownloader.downloadTeamsDevApp(teamsDevAppApkPath, PLATFORM_ANDROID, (error) => {
        if (error) {
          console.error('Failed to download Teams dev app apk from App Center. Reason:');
          console.error(error.message);
        } else {
          console.error('Download Teams dev app apk from App Center complete!');
          TeamsDevAppInstaller.executeAdbInstall(teamsDevAppApkPath);
        }
      });
    } else {
        TeamsDevAppInstaller.executeAdbInstall(teamsDevAppApkPath);
    }
  }

  private static installTeamsIosApp() {
    const appDirectory = Path.resolve('.');
    const teamsDevAppIpaPath = SdkCache.getTeamsDevAppIpaCachePath(appDirectory);
    
    if (!FileSystem.existsSync(teamsDevAppIpaPath)) {
      console.error("Teams dev app doesn't exist in the cache. Downloading Teams canary app ipa from App Center.");
      FileSystem.ensureDirSync(SdkCache.getTeamsDevAppIpaCacheDirectoryPath(appDirectory));
      TeamsDevAppDownloader.downloadTeamsDevApp(teamsDevAppIpaPath, PLATFORM_IOS, (error) => {
        if (error) {
          console.error('Failed to download Teams canary app ipa from App Center. Reason:');
          console.error(error.message);
        } else {
          console.error('Download Teams canary app ipa from App Center complete!');
            console.log("PATH",teamsDevAppIpaPath);
            TeamsDevAppInstaller.executeIpaInstall(teamsDevAppIpaPath);
        }
      });
    } else {
        console.log("PATH",teamsDevAppIpaPath);
        TeamsDevAppInstaller.executeIpaInstall(teamsDevAppIpaPath);
    }
  }
  
  private static executeAdbInstall (teamsDevAppApkPath: string) {
    console.error('Installing Teams dev app on device.');
    ChildProcess.execFile('adb', ['install', '-r', teamsDevAppApkPath], function (error) {
      if (error) {
        console.error('Failed to install on device. Reason:');
        console.error(error);
      } else {
        console.log('Installation complete!');
      }
    });
  }

  private static executeIpaInstall (teamsDevAppIpaPath: string) {
    console.error('Installing Teams canary app on device.');
    ChildProcess.execFile('ideviceinstaller', ['-i', teamsDevAppIpaPath], function (error) {
      if (error) {
        console.error('Failed to install on device. Reason:');
        console.error(error);
      } else {
        console.log('Installation complete!');
      }
    });
  }
}
