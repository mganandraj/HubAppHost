"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.SnackbarCallback = void 0;
/**
 * @hidden from docs
 */
class SnackbarCallback {
    constructor(snackbarId, snackbar) {
        this.snackbarId = snackbarId;
        this.snackbar = snackbar;
    }
    getSnackbarId() {
        return this.snackbarId;
    }
    onActionSelected(actionId) {
        if (this.snackbar.action && this.snackbar.action.onSelected && this.snackbar.action.id === actionId) {
            this.snackbar.action.onSelected();
        }
    }
}
exports.SnackbarCallback = SnackbarCallback;
