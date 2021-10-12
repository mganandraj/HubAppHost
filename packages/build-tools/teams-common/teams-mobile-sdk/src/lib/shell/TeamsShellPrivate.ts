import { HostContext } from '../application/HostContext';
import { BaseTeamsShellEvent } from '../models/events/BaseTeamsShellEvent';
import { OptionsMenuItemSelectedEvent } from '../models/events/OptionsMenuItemSelectedEvent';
import { SecondaryFabClickEvent } from '../models/events/SecondaryFabClickEvent';
import { SnackbarActionSelectedEvent } from '../models/events/SnackbarActionSelectedEvent';
import { TabSelectedEvent } from '../models/events/TabSelectedEvent';
import { TitleDropdownItemSelectedEvent } from '../models/events/TitleDropdownItemSelectedEvent';
import { TeamsShellInteractor } from '../services/SdkNativeModules';
import { Logger } from '../services/SdkNativeModules';
import { TeamsShell } from './TeamsShell';
import { TeamsShellEventsResponder } from './TeamsShellEventsResponder';
import { TeamsShellInteractionListener } from './TeamsShellInteractionListener';
import { TeamsShellNativeEventsDispatcher } from './TeamsShellNativeEventsDispatcher';

/**
 * @hidden from docs
 * Not exposed to outside developer. They will use TeamsShell instead.
 */
export class TeamsShellPrivate extends TeamsShell implements TeamsShellEventsResponder {
  private readonly LOG_TAG: string = 'TeamsShellPrivate';

  constructor (host: HostContext, listener: TeamsShellInteractionListener) {
    super(host, listener);
    TeamsShellNativeEventsDispatcher.getInstance().registerShell(this.host.hostInstanceId, this);
  }

  public destroy () {
    super.destroy();
    TeamsShellNativeEventsDispatcher.getInstance().deregisterShell(this.host.hostInstanceId, this);
  }

  public closeModule (success?: boolean): void {
    if (success) {
      TeamsShellInteractor.closeModuleWithResult(this.host.hostInstanceId, success);
    } else {
      TeamsShellInteractor.closeModule(this.host.hostInstanceId);
    }
  }

  public onOptionsMenuInvalidated (_: BaseTeamsShellEvent) {
    this.invalidateOptionsMenu();
  }

  public onOptionsMenuItemSelected (event: OptionsMenuItemSelectedEvent) {
    if (this.listener && event) {
      this.listener.onOptionsMenuItemSelected(event.optionsMenuItemId);
    }
  }

  public onTabSelected (event: TabSelectedEvent) {
    if (this.listener && event) {
      this.listener.onTabSelected(event.tabId);
    }
  }

  public onSnackbarActionSelected (event: SnackbarActionSelectedEvent): void {
    if (event && event.snackbarId && event.snackbarActionId) {
      for (const snackbarCallback of this.snackbarCallbacks) {
        if (snackbarCallback && snackbarCallback.getSnackbarId() === event.snackbarId) {
          snackbarCallback.onActionSelected(event.snackbarActionId);
        }
      }
    }
  }

  public onPrimaryFabClick (event: BaseTeamsShellEvent): void {
    if (this.listener && event) {
      this.listener.onPrimaryFabClick();
    }
  }

  public onSecondaryFabClick (event: SecondaryFabClickEvent): void {
    if (this.listener && event && event.buttonId) {
      this.listener.onSecondaryFabClick(event.buttonId);
    }
  }

  public onTitleDropdownItemSelected (event: TitleDropdownItemSelectedEvent) {
    if (this.titleDropdownHandler) {
      this.titleDropdownHandler(event.selectedItemId);
    } else {
      Logger.logWarning(this.LOG_TAG, 'Received title dropdown item selected event but no handler is registered.');
    }
  }

  public onBackNavigationInitiated (_: BaseTeamsShellEvent) {
    if (this.backNavigationHandler) {
      this.backNavigationHandler();
    }
  }

  public onTitleClicked (event: BaseTeamsShellEvent): void {
    if (event && this.titleClickHandler) {
      this.titleClickHandler();
    }
  }
}
