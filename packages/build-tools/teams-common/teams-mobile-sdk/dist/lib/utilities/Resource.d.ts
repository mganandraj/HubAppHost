/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
export declare class Resource {
    /**
     * Returns the URI for a string resource. Useful when sending a
     * string to native.
     *
     * @param stringResourceName name of the string resource
     */
    static string(stringResourceName: string): string;
    /**
     * Returns the URI for an image resource. Useful when sending an
     * image to native.
     *
     * @param imageResourceName name of the image resource
     */
    static image(imageResourcePath: string): string;
    /**
     * Gets the localized version of a string.
     *
     * @param path path to the string (usually the string resource name)
     * @param paramData data for placeholder replacement in the string
     */
    static getLocalizedString(path: string, paramData?: {
        [name: string]: string | number;
    }): string;
}
