import { EmitterSubscription, NativeAppEventEmitter } from 'react-native';
import { BaseTeamsShellEvent } from '../models/events/BaseTeamsShellEvent';
import { Logger } from '../services/SdkNativeModules';
import { ViewLifecycleListener } from './ViewLifecycleListener';

const viewEvents = {
  viewAppear: 'onViewAppear'
};

/**
 * @hidden from docs
 *
 * Subscribes to all the native Teams view events and routes them to their intended
 * targets.
 */
export class ViewLifecycleNativeEventsDispatcher {
  private static globalViewLifecycleNativeEventsDispatcher: ViewLifecycleNativeEventsDispatcher;
  private activeEmitterSubscriptions: EmitterSubscription[];
  private activeViews: Map<string, ViewLifecycleListener>;
  private readonly LOG_TAG: string = 'ViewLifecycleNativeEventsDispatcher';

  private constructor () {
    this.activeEmitterSubscriptions = [];
    this.activeViews = new Map<string, ViewLifecycleListener>();
  }

  public static getInstance (): ViewLifecycleNativeEventsDispatcher {
    if (!this.globalViewLifecycleNativeEventsDispatcher) {
      this.globalViewLifecycleNativeEventsDispatcher = new ViewLifecycleNativeEventsDispatcher();
    }

    return this.globalViewLifecycleNativeEventsDispatcher;
  }

  public registerView (hostInstanceId: string, view: ViewLifecycleListener): void {
    Logger.logInformation(this.LOG_TAG, `Registered view for host ${hostInstanceId}`);
    if (this.activeViews.size === 0) {
      this.subscribeToNativeEvents();
    }
    this.activeViews.set(hostInstanceId, view);
  }

  public deregisterView (hostInstanceId: string, view: ViewLifecycleListener): void {
    const currentlyRegisteredView = this.activeViews.get(hostInstanceId);
    if (currentlyRegisteredView && currentlyRegisteredView === view) {
      this.activeViews.delete(hostInstanceId);
    }

    if (this.activeViews.size === 0) {
      this.unsubscribeToNativeEvents();
    }
  }

  private subscribeToNativeEvents (): void {
    this.activeEmitterSubscriptions.push(
      NativeAppEventEmitter.addListener(viewEvents.viewAppear, this.onViewAppear)
    );
  }

  private unsubscribeToNativeEvents (): void {
    this.activeEmitterSubscriptions.forEach((subscription: EmitterSubscription) => {
      subscription.remove();
    });
    this.activeEmitterSubscriptions = [];
  }

  private onViewAppear = (event: BaseTeamsShellEvent) => {
    const targetView = this.activeViews.get(event.hostInstanceId);
    if (targetView) {
      targetView.onViewAppear();
    }
  }
}
