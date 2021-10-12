import { NativeAppEventEmitter } from 'react-native';
import * as uuid from 'uuid/v4';
import { HostClosedEvent } from '../models/events/HostClosedEvent';
import { NavigationService } from '../services/SdkNativeModules';
import { HostResult } from './HostResult';
import { ViewParamsBundle } from './ViewParamsBundle';

const NativeEvents = {
  hostClosed: 'onHostClosed'
};

/**
 * @hidden from docs
 */
export class NavigationManager {
  private static globalNavigationManager?: NavigationManager;

  // private closeHostEventSubscription: EmitterSubscription;
  // private navigationServiceEventEmitter: NativeEventEmitter;

  /**
   * Stores results that are returned by various hosts.
   */
  private results: Map<string, HostResult>;

  /**
   * Stores view params that need to be passed to different views. These
   * view params come from `openView` and `openViewForResult` calls. The
   * key in the map is a UUID.
   */
  private viewParamsStore: Map<string, ViewParamsBundle>;

  constructor () {
    this.viewParamsStore = new Map<string, ViewParamsBundle>();
    this.results = new Map<string, HostResult>();
    this.subscribeToNativeEvents();
  }

  public static getInstance (): NavigationManager {
    if (this.globalNavigationManager === undefined) {
      this.globalNavigationManager = new NavigationManager();
    }
    return this.globalNavigationManager;
  }

  public retrieveViewParams (viewParamsKey: string): ViewParamsBundle | undefined {
    const viewParmas = this.viewParamsStore.get(viewParamsKey);
    return viewParmas;
  }

  public deleteViewParams (viewParamsKey: string): void {
    this.viewParamsStore.delete(viewParamsKey);
  }

  /**
   * Wrapper for method with the same name in the `NavigationService` module.
   */
  public openView<T> (viewId: string, params?: T): void {
    this.openViewForResult(viewId, params, undefined);
  }

  /**
   * Wrapper for method with the same name in the `NavigationService` module.
   */
  public openViewForResult<T,R> (viewId: string, params: T | undefined, callback: ((closeHostResult: R) => void) | undefined): void {
    let viewParamsKey: string = '';
    if (params) {
      viewParamsKey = uuid();
      const viewParams: ViewParamsBundle = new ViewParamsBundle(params, callback);
      this.viewParamsStore.set(viewParamsKey, viewParams);
    }
    NavigationService.openView(viewId, viewParamsKey);
  }

  /**
   * Wrapper for method with the same name in the `NavigationService` module.
   */
  public closeView (hostInstanceId: string, animated?: boolean): void {
    if (animated === undefined) {
      animated = true;
    }
    NavigationService.closeView(hostInstanceId, animated);
  }

  public closeViewForResult (hostInstanceId: string, result: HostResult, animated?: boolean): void {
    this.setResultForHost(hostInstanceId, result);
    this.closeView(hostInstanceId, animated);
  }

  private setResultForHost (hostInstanceId: string, result: HostResult) {
    this.results.set(hostInstanceId, result);
  }

  private subscribeToNativeEvents (): void {
    // Add listeners for shell events from native
    NativeAppEventEmitter.addListener(NativeEvents.hostClosed, this.onHostClosed.bind(this));
  }

  /**
   * Handler for `onHostClosed` native event.
   */
  private onHostClosed (event: HostClosedEvent): void {
    if (!event) {
      return;
    }

    const result = this.results.get(event.closedHostInstanceId);
    if (!result) {
      return;
    }

    this.results.delete(event.closedHostInstanceId);
    result.callback(result.result);
  }
}
