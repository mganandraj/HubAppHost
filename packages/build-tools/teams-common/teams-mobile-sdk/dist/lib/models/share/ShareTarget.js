"use strict";
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.ShareTargetType = void 0;
/**
 * Represents the type of a share target.
 */
var ShareTargetType;
(function (ShareTargetType) {
    ShareTargetType[ShareTargetType["CHANNEL"] = 0] = "CHANNEL";
    ShareTargetType[ShareTargetType["PERSON"] = 1] = "PERSON";
    ShareTargetType[ShareTargetType["GROUP"] = 2] = "GROUP";
    ShareTargetType[ShareTargetType["PRIVATE_MEETING"] = 3] = "PRIVATE_MEETING";
})(ShareTargetType = exports.ShareTargetType || (exports.ShareTargetType = {}));
