/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import * as Path from 'path';
import { FileUtilities } from '../../common/utilities/FileUtilities';
import { SdkCache } from '../sdkcache/SdkCache';

/**
 * Manages the configuration needed by SDK to perform different tasks like build, install, etc.
 * Developers use this to configure their development environment.
 */
export class SdkConfigManager {
  public static getUserApiToken (): string | undefined {
    return SdkConfigManager.get('userApiToken');
  }

  public static get (configKey: string): string | undefined {
    if (!configKey) {
      throw new Error('Config key (' + configKey + ') is invalid.');
    }

    const configObject = SdkConfigManager.readConfigObject();
    return configObject[configKey];
  }

  public static getAll () {
    return SdkConfigManager.readConfigObject();
  }

  public static set (configKey: string, configValue: string | undefined) {
    const configObject = SdkConfigManager.readConfigObject();
    configObject[configKey] = configValue;
    SdkConfigManager.writeConfigObject(configObject);
  }

  public static remove (configKey: string) {
    SdkConfigManager.set(configKey, undefined);
  }

  private static readConfigObject (): object {
    const configCachePath = SdkCache.getConfigCachePath(Path.resolve('.'));
    return FileUtilities.readJsonSync(configCachePath) || {};
  }

  private static writeConfigObject (configObject: object) {
    const configCachePath = SdkCache.getConfigCachePath(Path.resolve('.'));
    FileUtilities.writeJsonSync(configCachePath, configObject);
  }
}
