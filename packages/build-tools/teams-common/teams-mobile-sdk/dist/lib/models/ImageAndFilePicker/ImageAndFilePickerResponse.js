"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.OfficeLensMode = exports.ImageQuality = void 0;
/**
 * Enums to describe image quality
 */
var ImageQuality;
(function (ImageQuality) {
    ImageQuality[ImageQuality["EXCELLENT"] = 0] = "EXCELLENT";
    ImageQuality[ImageQuality["GOOD"] = 1] = "GOOD";
    ImageQuality[ImageQuality["MODERATE"] = 2] = "MODERATE";
    ImageQuality[ImageQuality["LOW"] = 3] = "LOW";
})(ImageQuality = exports.ImageQuality || (exports.ImageQuality = {}));
exports.OfficeLensMode = {
    WHITEBOARD: 'WhiteBoard',
    BUSINESS_CARD: 'BusinessCard',
    DOCUMENT: 'Document',
    PHOTO: 'Photo'
};
