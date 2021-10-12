import { HostContext } from '../application/HostContext';
import { BaseTeamsShellEvent } from '../models/events/BaseTeamsShellEvent';
import { OptionsMenuItemSelectedEvent } from '../models/events/OptionsMenuItemSelectedEvent';
import { SecondaryFabClickEvent } from '../models/events/SecondaryFabClickEvent';
import { SnackbarActionSelectedEvent } from '../models/events/SnackbarActionSelectedEvent';
import { TabSelectedEvent } from '../models/events/TabSelectedEvent';
import { TitleDropdownItemSelectedEvent } from '../models/events/TitleDropdownItemSelectedEvent';
import { TeamsShell } from './TeamsShell';
import { TeamsShellEventsResponder } from './TeamsShellEventsResponder';
import { TeamsShellInteractionListener } from './TeamsShellInteractionListener';
/**
 * @hidden from docs
 * Not exposed to outside developer. They will use TeamsShell instead.
 */
export declare class TeamsShellPrivate extends TeamsShell implements TeamsShellEventsResponder {
    private readonly LOG_TAG;
    constructor(host: HostContext, listener: TeamsShellInteractionListener);
    destroy(): void;
    closeModule(success?: boolean): void;
    onOptionsMenuInvalidated(_: BaseTeamsShellEvent): void;
    onOptionsMenuItemSelected(event: OptionsMenuItemSelectedEvent): void;
    onTabSelected(event: TabSelectedEvent): void;
    onSnackbarActionSelected(event: SnackbarActionSelectedEvent): void;
    onPrimaryFabClick(event: BaseTeamsShellEvent): void;
    onSecondaryFabClick(event: SecondaryFabClickEvent): void;
    onTitleDropdownItemSelected(event: TitleDropdownItemSelectedEvent): void;
    onBackNavigationInitiated(_: BaseTeamsShellEvent): void;
    onTitleClicked(event: BaseTeamsShellEvent): void;
}
