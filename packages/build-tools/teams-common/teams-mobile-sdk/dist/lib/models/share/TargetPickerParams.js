"use strict";
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.TargetPickerParams = exports.PickerType = void 0;
/**
 * Represents the accepted values of picker type
 */
var PickerType;
(function (PickerType) {
    PickerType[PickerType["CHAT"] = 0] = "CHAT";
    PickerType[PickerType["CHANNEL"] = 1] = "CHANNEL";
    PickerType[PickerType["ALL"] = 2] = "ALL";
})(PickerType = exports.PickerType || (exports.PickerType = {}));
/**
 * Represents configuration options for share target picker.
 */
class TargetPickerParams {
}
exports.TargetPickerParams = TargetPickerParams;
