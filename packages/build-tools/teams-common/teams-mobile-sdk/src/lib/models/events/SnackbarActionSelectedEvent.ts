import { BaseTeamsShellEvent } from './BaseTeamsShellEvent';

/**
 * @hidden from docs
 */
export interface SnackbarActionSelectedEvent extends BaseTeamsShellEvent {
  snackbarId: number;
  snackbarActionId: string;
}
