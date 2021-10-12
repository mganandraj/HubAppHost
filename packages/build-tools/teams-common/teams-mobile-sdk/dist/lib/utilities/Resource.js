"use strict";
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.Resource = void 0;
const ArgumentsValidator_1 = require("../../common/utilities/ArgumentsValidator");
const LocalizedStrings = require("../services/LocalizedStrings");
class Resource {
    /**
     * Returns the URI for a string resource. Useful when sending a
     * string to native.
     *
     * @param stringResourceName name of the string resource
     */
    static string(stringResourceName) {
        ArgumentsValidator_1.ArgumentsValidator.warnIfNullOrUndefined('stringResourceName', stringResourceName);
        return 'res://strings/' + stringResourceName;
    }
    /**
     * Returns the URI for an image resource. Useful when sending an
     * image to native.
     *
     * @param imageResourceName name of the image resource
     */
    static image(imageResourcePath) {
        ArgumentsValidator_1.ArgumentsValidator.warnIfNullOrUndefined('imageResourceName', imageResourcePath);
        return 'res://images/' + imageResourcePath;
    }
    /**
     * Gets the localized version of a string.
     *
     * @param path path to the string (usually the string resource name)
     * @param paramData data for placeholder replacement in the string
     */
    static getLocalizedString(path, paramData = {}) {
        return LocalizedStrings.getString(path, paramData);
    }
}
exports.Resource = Resource;
