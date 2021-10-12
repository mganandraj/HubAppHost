/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
/**
 * Implementation of Teams Dev App Installer.
 *
 * Downloads the configured Teams Dev App from App Center using the user API token specified by the developer
 * and installs the app on the user device. It also performs caching of the app to avoid downloading it over the wire every time.
 */
export declare class TeamsDevAppInstaller {
    static installTeamsDevApp(platform: string): void;
    private static installTeamsAndroidApp;
    private static installTeamsIosApp;
    private static executeAdbInstall;
    private static executeIpaInstall;
}
