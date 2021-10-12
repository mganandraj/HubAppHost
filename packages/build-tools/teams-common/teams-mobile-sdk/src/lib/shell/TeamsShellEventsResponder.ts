import { BaseTeamsShellEvent } from '../models/events/BaseTeamsShellEvent';
import { TabSelectedEvent } from '../models/events/TabSelectedEvent';
import { TitleDropdownItemSelectedEvent } from '../models/events/TitleDropdownItemSelectedEvent';

/**
 * @hidden from docs
 */
export interface TeamsShellEventsResponder {
  onOptionsMenuInvalidated (event: any);
  onOptionsMenuItemSelected (event: any);
  onSnackbarActionSelected (event: any);
  onPrimaryFabClick (event: any);
  onSecondaryFabClick (event: any);
  onTitleDropdownItemSelected (event: TitleDropdownItemSelectedEvent);
  onBackNavigationInitiated (event: BaseTeamsShellEvent): void;
  onTabSelected(event: TabSelectedEvent);
  onTitleClicked (event: any);
}
