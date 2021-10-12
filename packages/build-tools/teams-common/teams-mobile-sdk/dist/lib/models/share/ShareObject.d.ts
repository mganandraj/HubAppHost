/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
/**
 * Represents a share object in Teams chat/channels.
 */
export declare class ShareObject {
    /**
     * The array containing image handles to be shared
     *
     * > [!NOTE]
     * > Image handles can be obtained via `ImageAndFilePicker` APIs.
     *
     * > [!NOTE]
     * > If both images and text shared, compose message will show
     * > images on top and then the text is shown.
     * > E.g. Image(s)
     * >      Text
     */
    imagesToShare: string[];
    /**
     * The string containing text/links to be shared.
     *
     * > [!NOTE]
     * > A single string with delminiators, such as \n can be passed
     * > in the same textToShare string if needed.
     */
    textToShare: string;
}
