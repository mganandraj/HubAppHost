import { TeamsShellEventsResponder } from './TeamsShellEventsResponder';
/**
 * @hidden from docs
 *
 * Subscribes to all the native Teams shell events and routes them to their intended
 * targets.
 */
export declare class TeamsShellNativeEventsDispatcher {
    private static globalTeamsShellNativeEventsDispatcher;
    private activeEmitterSubscriptions;
    private activeShells;
    private readonly LOG_TAG;
    private constructor();
    static getInstance(): TeamsShellNativeEventsDispatcher;
    registerShell(hostInstanceId: string, shell: TeamsShellEventsResponder): void;
    deregisterShell(hostInstanceId: string, shell: TeamsShellEventsResponder): void;
    private subscribeToNativeEvents;
    private unsubscribeToNativeEvents;
    private onOptionsMenuInvalidated;
    private onOptionsMenuItemSelected;
    private onTabSelected;
    private onSnackbarActionSelected;
    private onPrimaryFabClick;
    private onSecondaryFabClick;
    private onTitleDropdownItemSelected;
    private onBackNavigationInitiated;
    private onTitleClicked;
}
