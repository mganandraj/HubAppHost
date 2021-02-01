/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk;

import java.util.Locale;

/**
 * Helper for SDK runner server.
 */
final class SdkRunnerServerHelper {
    private static final String APP_MANIFEST_URL = "http://%s/manifest";
    private static final String APP_RESOURCES_URL = "http://%s/resources";
    private static final String APP_DEFINITION_URL = "http://%s/definition";

    private SdkRunnerServerHelper() {
    }

    static String getAppDefinitionUrl() {
        return String.format(Locale.ENGLISH, APP_DEFINITION_URL, getRunnerServerHost());
    }

    static String getAppManifestUrl() {
        return String.format(Locale.ENGLISH, APP_MANIFEST_URL, getRunnerServerHost());
    }

    static String getAppResourcesDownloadUrl() {
        return String.format(Locale.ENGLISH, APP_RESOURCES_URL, getRunnerServerHost());
    }

    private static String getRunnerServerHost() {
        return "localhost:3333";
    }
}
