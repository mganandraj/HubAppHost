"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ViewParamsBundle = void 0;
/**
 * @hidden from docs
 *
 * Represents the params and callback passed to openView.
 */
class ViewParamsBundle {
    constructor(params, onCloseCallback) {
        this.params = params;
        this.onCloseCallback = onCloseCallback;
    }
}
exports.ViewParamsBundle = ViewParamsBundle;
