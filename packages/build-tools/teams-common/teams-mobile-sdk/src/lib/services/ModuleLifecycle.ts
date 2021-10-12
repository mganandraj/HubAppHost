import { NativeAppEventEmitter } from 'react-native';

export interface IModuleLifecycleHandler {
  onUserSignedOut (event: any);
}

const Events = {
  ON_USER_SIGNED_OUT: 'onUserSignedOut'
};

export class ModuleLifecycle {
  private static handler?: IModuleLifecycleHandler;

  public static registerHandler (handler: IModuleLifecycleHandler) {
    if (!this.handler) {
      this.subscribeToEvents();
    }
    this.handler = handler;
  }

  private static subscribeToEvents () {
    NativeAppEventEmitter.addListener(Events.ON_USER_SIGNED_OUT, this.onUserSignedOut);
  }

  private static onUserSignedOut = (event: any) => {
    if (ModuleLifecycle.handler) {
      ModuleLifecycle.handler.onUserSignedOut(event);
    }
  }
}
