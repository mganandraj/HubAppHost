/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import { Logger } from '../SdkNativeModules';

export class TraceLogger {
  public static logVerbose (tag: string, message: string) {
    Logger.logVerbose(tag, TraceLogger.createMessage(message));
  }

  public static logInformation (tag: string, message: string) {
    Logger.logInformation(tag, TraceLogger.createMessage(message));
  }

  public static logDebug (tag: string, message: string) {
    Logger.logDebug(tag, TraceLogger.createMessage(message));
  }

  public static logWarning (tag: string, message: string) {
    Logger.logWarning(tag, TraceLogger.createMessage(message));
  }

  public static logError (tag: string, message: string) {
    Logger.logError(tag, TraceLogger.createMessage(message));
  }

  private static createMessage (message: string): string {
    return 'Message: ' + message;
  }
}
