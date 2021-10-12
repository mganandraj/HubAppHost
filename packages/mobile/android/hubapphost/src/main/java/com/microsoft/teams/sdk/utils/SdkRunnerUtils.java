package com.microsoft.teams.sdk.utils;

import androidx.annotation.NonNull;

/**
 * Utility class for Runner Mode
 */
public final class SdkRunnerUtils {
    private static boolean sRunnerMode = false;
    public static final String SDK_RUNNER_APP_PACKAGE_URL = "assets://runner-app";
    public static final String SDK_RUNNER_PREFIX = "runner-";
    public static final String SDK_RUNNER_SUFFIX = "(Dev)";


    private SdkRunnerUtils() {
    }

    public static boolean isRunnerApp(@NonNull String appId) {
        return appId.startsWith(SDK_RUNNER_PREFIX);
    }

    public static void enableRunnerMode() {
        sRunnerMode = true;
    }

    public static boolean isRunnerMode() {
        // return AppBuildConfigurationHelper.isDebug() && AppBuildConfigurationHelper.isDev() && sRunnerMode;
        return true;
    }

    public static String transformToRunnerAppId(@NonNull String appId) {
        return appId.startsWith(SDK_RUNNER_PREFIX) ? appId : SDK_RUNNER_PREFIX + appId;
    }

    public static String transformToRunnerAppName(@NonNull String name) {
        return name.endsWith(SDK_RUNNER_SUFFIX) ? name : name + SDK_RUNNER_SUFFIX;
    }
}
