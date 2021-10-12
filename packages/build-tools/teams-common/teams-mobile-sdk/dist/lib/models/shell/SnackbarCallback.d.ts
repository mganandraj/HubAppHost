import { Snackbar } from './Snackbar';
/**
 * @hidden from docs
 */
export declare class SnackbarCallback {
    private snackbar;
    private snackbarId;
    constructor(snackbarId: number, snackbar: Snackbar);
    getSnackbarId(): number;
    onActionSelected(actionId: string): void;
}
