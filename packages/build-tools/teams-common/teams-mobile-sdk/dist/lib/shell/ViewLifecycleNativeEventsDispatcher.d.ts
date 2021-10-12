import { ViewLifecycleListener } from './ViewLifecycleListener';
/**
 * @hidden from docs
 *
 * Subscribes to all the native Teams view events and routes them to their intended
 * targets.
 */
export declare class ViewLifecycleNativeEventsDispatcher {
    private static globalViewLifecycleNativeEventsDispatcher;
    private activeEmitterSubscriptions;
    private activeViews;
    private readonly LOG_TAG;
    private constructor();
    static getInstance(): ViewLifecycleNativeEventsDispatcher;
    registerView(hostInstanceId: string, view: ViewLifecycleListener): void;
    deregisterView(hostInstanceId: string, view: ViewLifecycleListener): void;
    private subscribeToNativeEvents;
    private unsubscribeToNativeEvents;
    private onViewAppear;
}
