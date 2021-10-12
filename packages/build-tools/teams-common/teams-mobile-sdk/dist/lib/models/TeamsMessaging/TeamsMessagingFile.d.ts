import { Dictionary } from 'lodash';
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
export interface TeamsMessagingFileInfo {
    /**
     * File url - Complete url to the file
     * Eg: https://microsoft.sharepoint.com/teams/veryveryveryveryveryverylongteamname/Shared%20Documents/General/IMG_0037.JPG
     */
    fileUrl?: string;
    /**
     * Relative url from site url - Path component of file url or deeplink params
     * Eg: /teams/veryveryveryveryveryverylongteamname/Shared Documents/General/IMG_0037.JPG
     */
    serverRelativeUrl?: string;
    /**
     * Base url / site url
     * Eg: https://microsoft.sharepoint.com/teams/veryveryveryveryveryverylongteamname
     */
    siteUrl?: string;
}
export interface TeamsMessagingFile {
    /**
     * Item id for the file
     */
    itemid: string;
    /**
     * File name
     */
    fileName: string;
    /**
     * File type - deeplink or file extension
     */
    fileType: string;
    /**
     * File info
     */
    fileInfo: TeamsMessagingFileInfo;
    /**
     * Bot file properties info
     */
    botFileProperties: Dictionary<any>;
}
