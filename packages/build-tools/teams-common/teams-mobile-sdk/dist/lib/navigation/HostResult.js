"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.HostResult = void 0;
/**
 * @hidden from docs
 *
 * Used to store the result returned by a host along
 * with the callback that needs to called when the host
 * closes.
 */
class HostResult {
    constructor(result, callback) {
        this.result = result;
        this.callback = callback;
    }
}
exports.HostResult = HostResult;
