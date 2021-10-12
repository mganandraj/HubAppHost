import { HostResult } from './HostResult';
import { ViewParamsBundle } from './ViewParamsBundle';
/**
 * @hidden from docs
 */
export declare class NavigationManager {
    private static globalNavigationManager?;
    /**
     * Stores results that are returned by various hosts.
     */
    private results;
    /**
     * Stores view params that need to be passed to different views. These
     * view params come from `openView` and `openViewForResult` calls. The
     * key in the map is a UUID.
     */
    private viewParamsStore;
    constructor();
    static getInstance(): NavigationManager;
    retrieveViewParams(viewParamsKey: string): ViewParamsBundle | undefined;
    deleteViewParams(viewParamsKey: string): void;
    /**
     * Wrapper for method with the same name in the `NavigationService` module.
     */
    openView<T>(viewId: string, params?: T): void;
    /**
     * Wrapper for method with the same name in the `NavigationService` module.
     */
    openViewForResult<T, R>(viewId: string, params: T | undefined, callback: ((closeHostResult: R) => void) | undefined): void;
    /**
     * Wrapper for method with the same name in the `NavigationService` module.
     */
    closeView(hostInstanceId: string, animated?: boolean): void;
    closeViewForResult(hostInstanceId: string, result: HostResult, animated?: boolean): void;
    private setResultForHost;
    private subscribeToNativeEvents;
    /**
     * Handler for `onHostClosed` native event.
     */
    private onHostClosed;
}
