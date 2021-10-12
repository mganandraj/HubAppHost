/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
export declare class TeamsDevAppDownloader {
    static downloadTeamsDevApp(downloadOutputPath: string, platform: string, callback: (error: Error) => any): void;
    private static download;
    private static downloadBinary;
}
