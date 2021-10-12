/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import { SdkNativeEventPublisher } from '../SdkNativeModules';

export class EventLogger {
  private hostInstanceId: string;

  constructor (hostInstanceId: string) {
    this.hostInstanceId = hostInstanceId;
  }

  public logViewReady () {
    SdkNativeEventPublisher.publishEvent('sdkAppViewReady', {
      event: {
        appInstanceId: this.hostInstanceId
      }
    });
  }

  public logContentReady () {
    // Currently Android code is listening to 'sdkAppHostReady' event for content ready.
    // TODO remove it once android code adoption to 'skdAppContentReady' is completed.
    // [Task 1270089: [RN][Android] Change event name 'sdkAppHostReady' to 'sdkAppContentReady' in Native code.]
    SdkNativeEventPublisher.publishEvent('sdkAppHostReady', {
      event: {
        appInstanceId: this.hostInstanceId
      }
    });
    SdkNativeEventPublisher.publishEvent('sdkAppContentReady', {
      event: {
        appInstanceId: this.hostInstanceId
      }
    });
  }
}
