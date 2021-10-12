import { OptionsMenuItem } from '../models/shell/OptionsMenuItem';
/**
 * @hidden from docs
 */
export interface TeamsShellInteractionListener {
    getOptionsMenuItems(): OptionsMenuItem[];
    onOptionsMenuItemSelected(selectedMenuItemId: string): void;
    onPrimaryFabClick(): void;
    onSecondaryFabClick(buttonId: string): void;
    onTabSelected(tabId: string): void;
}
