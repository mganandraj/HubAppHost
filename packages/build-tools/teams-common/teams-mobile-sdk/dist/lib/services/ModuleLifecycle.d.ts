export interface IModuleLifecycleHandler {
    onUserSignedOut(event: any): any;
}
export declare class ModuleLifecycle {
    private static handler?;
    static registerHandler(handler: IModuleLifecycleHandler): void;
    private static subscribeToEvents;
    private static onUserSignedOut;
}
