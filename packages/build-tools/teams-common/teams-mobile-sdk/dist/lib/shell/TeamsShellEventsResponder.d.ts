import { BaseTeamsShellEvent } from '../models/events/BaseTeamsShellEvent';
import { TabSelectedEvent } from '../models/events/TabSelectedEvent';
import { TitleDropdownItemSelectedEvent } from '../models/events/TitleDropdownItemSelectedEvent';
/**
 * @hidden from docs
 */
export interface TeamsShellEventsResponder {
    onOptionsMenuInvalidated(event: any): any;
    onOptionsMenuItemSelected(event: any): any;
    onSnackbarActionSelected(event: any): any;
    onPrimaryFabClick(event: any): any;
    onSecondaryFabClick(event: any): any;
    onTitleDropdownItemSelected(event: TitleDropdownItemSelectedEvent): any;
    onBackNavigationInitiated(event: BaseTeamsShellEvent): void;
    onTabSelected(event: TabSelectedEvent): any;
    onTitleClicked(event: any): any;
}
