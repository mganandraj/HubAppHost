import { EmitterSubscription, NativeAppEventEmitter } from 'react-native';
import { BaseTeamsShellEvent } from '../models/events/BaseTeamsShellEvent';
import { OptionsMenuItemSelectedEvent } from '../models/events/OptionsMenuItemSelectedEvent';
import { SecondaryFabClickEvent } from '../models/events/SecondaryFabClickEvent';
import { SnackbarActionSelectedEvent } from '../models/events/SnackbarActionSelectedEvent';
import { TabSelectedEvent } from '../models/events/TabSelectedEvent';
import { TitleDropdownItemSelectedEvent } from '../models/events/TitleDropdownItemSelectedEvent';
import { Logger } from '../services/SdkNativeModules';
import { TeamsShellEventsResponder } from './TeamsShellEventsResponder';

const shellEvents = {
  optionsMenuInvalidated: 'onShellOptionsMenuInvalidated',
  optionsMenuItemSelected: 'onShellOptionsMenuItemSelected',
  snackbarActionSelected: 'onSnackbarActionSelected',
  primaryFabClick: 'onPrimaryFabClick',
  secondaryFabClick: 'onSecondaryFabClick',
  titleDropdownItemSelected: 'onTitleDropdownItemSelected',
  backNavigationInitiated: 'onBackNavigationInitiated',
  onTabSelected: "onTabSelected",
  onTitleClicked: 'onTitleClicked'
};

/**
 * @hidden from docs
 *
 * Subscribes to all the native Teams shell events and routes them to their intended
 * targets.
 */
export class TeamsShellNativeEventsDispatcher {
  private static globalTeamsShellNativeEventsDispatcher: TeamsShellNativeEventsDispatcher;

  // private teamsShellEventEmitter: NativeEventEmitter;
  private activeEmitterSubscriptions: EmitterSubscription[];
  private activeShells: Map<string, TeamsShellEventsResponder>;
  private readonly LOG_TAG: string = 'TeamsShellNativeEventsDispatcher';

  private constructor () {
    this.activeEmitterSubscriptions = [];
    this.activeShells = new Map<string, TeamsShellEventsResponder>();
    // this.teamsShellEventEmitter = new NativeEventEmitter(TeamsShellInteractorImpl);
  }

  public static getInstance (): TeamsShellNativeEventsDispatcher {
    if (!this.globalTeamsShellNativeEventsDispatcher) {
      this.globalTeamsShellNativeEventsDispatcher = new TeamsShellNativeEventsDispatcher();
    }

    return this.globalTeamsShellNativeEventsDispatcher;
  }

  public registerShell (hostInstanceId: string, shell: TeamsShellEventsResponder): void {
    Logger.logInformation(this.LOG_TAG, `Registered shell for host ${hostInstanceId}`);
    if (this.activeShells.size === 0) {
      this.subscribeToNativeEvents();
    }
    this.activeShells.set(hostInstanceId, shell);
  }

  public deregisterShell (hostInstanceId: string, shell: TeamsShellEventsResponder): void {
    const currentlyRegisteredShell = this.activeShells.get(hostInstanceId);
    if (currentlyRegisteredShell && currentlyRegisteredShell === shell) {
      this.activeShells.delete(hostInstanceId);
    }

    if (this.activeShells.size === 0) {
      this.unsubscribeToNativeEvents();
    }
  }

  private subscribeToNativeEvents (): void {
    this.activeEmitterSubscriptions.push(
      NativeAppEventEmitter.addListener(shellEvents.optionsMenuInvalidated, this.onOptionsMenuInvalidated),
      NativeAppEventEmitter.addListener(shellEvents.optionsMenuItemSelected, this.onOptionsMenuItemSelected),
      NativeAppEventEmitter.addListener(shellEvents.snackbarActionSelected, this.onSnackbarActionSelected),
      NativeAppEventEmitter.addListener(shellEvents.primaryFabClick, this.onPrimaryFabClick),
      NativeAppEventEmitter.addListener(shellEvents.secondaryFabClick, this.onSecondaryFabClick),
      NativeAppEventEmitter.addListener(shellEvents.titleDropdownItemSelected, this.onTitleDropdownItemSelected),
      NativeAppEventEmitter.addListener(shellEvents.backNavigationInitiated, this.onBackNavigationInitiated),
      NativeAppEventEmitter.addListener(shellEvents.onTabSelected, this.onTabSelected),
      NativeAppEventEmitter.addListener(shellEvents.onTitleClicked, this.onTitleClicked)
    );
  }

  private unsubscribeToNativeEvents (): void {
    this.activeEmitterSubscriptions.forEach((subscription: EmitterSubscription) => {
      subscription.remove();
    });
    this.activeEmitterSubscriptions = [];
  }

  private onOptionsMenuInvalidated = (event: BaseTeamsShellEvent) => {
    const targetShell = this.activeShells.get(event.hostInstanceId);
    if (targetShell) {
      targetShell.onOptionsMenuInvalidated(event);
    }
  }

  private onOptionsMenuItemSelected = (event: OptionsMenuItemSelectedEvent) => {
    const targetShell = this.activeShells.get(event.hostInstanceId);
    if (targetShell) {
      targetShell.onOptionsMenuItemSelected(event);
    }
  }

  private onTabSelected = (event: TabSelectedEvent) => {
    const targetShell = this.activeShells.get(event.hostInstanceId);
    if (targetShell) {
      targetShell.onTabSelected(event);
    }
  }

  private onSnackbarActionSelected = (event: SnackbarActionSelectedEvent) => {
    const targetShell = this.activeShells.get(event.hostInstanceId);
    if (targetShell) {
      targetShell.onSnackbarActionSelected(event);
    }
  }

  private onPrimaryFabClick = (event: BaseTeamsShellEvent) => {
    const targetShell = this.activeShells.get(event.hostInstanceId);
    if (targetShell) {
      targetShell.onPrimaryFabClick(event);
    }
  }

  private onSecondaryFabClick = (event: SecondaryFabClickEvent) => {
    const targetShell = this.activeShells.get(event.hostInstanceId);
    if (targetShell) {
      targetShell.onSecondaryFabClick(event);
    }
  }

  private onTitleDropdownItemSelected = (event: TitleDropdownItemSelectedEvent) => {
    const targetShell = this.activeShells.get(event.hostInstanceId);
    if (targetShell) {
      targetShell.onTitleDropdownItemSelected(event);
    } else {
      Logger.logWarning(this.LOG_TAG, 'Received title dropdown item selected event but there are no registered targets');
    }
  }

  private onBackNavigationInitiated = (event: BaseTeamsShellEvent) => {
    const targetShell = this.activeShells.get(event.hostInstanceId);
    if (targetShell) {
      targetShell.onBackNavigationInitiated(event);
    }
  }

  private onTitleClicked = (event: BaseTeamsShellEvent) => {
    const targetShell = this.activeShells.get(event.hostInstanceId);
    if (targetShell) {
      targetShell.onTitleClicked(event);
    }
  }
}
