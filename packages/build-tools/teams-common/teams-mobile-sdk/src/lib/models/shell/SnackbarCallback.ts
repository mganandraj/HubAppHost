import { Snackbar } from './Snackbar';

/**
 * @hidden from docs
 */
export class SnackbarCallback {
  private snackbar: Snackbar;
  private snackbarId: number;

  constructor (snackbarId: number, snackbar: Snackbar) {
    this.snackbarId = snackbarId;
    this.snackbar = snackbar;
  }

  public getSnackbarId (): number {
    return this.snackbarId;
  }

  public onActionSelected (actionId: string): void {
    if (this.snackbar.action && this.snackbar.action.onSelected && this.snackbar.action.id === actionId) {
      this.snackbar.action.onSelected();
    }
  }
}
