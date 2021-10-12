/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import { Logger, NativeTelemetryClient } from '../SdkNativeModules';

export class ConfigurationManager {
  private static instance: ConfigurationManager;

  private constructor () {
  }

  public static getInstance(): ConfigurationManager {
    if (!this.instance) {
      this.instance = new ConfigurationManager();
    }
    return this.instance;
  }

  public setAriaTenant (tenantToken: string) {
    Logger.setAriaTenant(tenantToken);
    NativeTelemetryClient.setAriaTenant(tenantToken);
  }
}
