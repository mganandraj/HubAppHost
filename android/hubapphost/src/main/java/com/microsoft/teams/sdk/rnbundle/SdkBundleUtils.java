/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk.rnbundle;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringDef;

import com.microsoft.teams.utilities.java.StringUtils;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.microsoft.teams.sdk.rnbundle.SdkBundleUtils.CompatibilityStatus.COMPATIBLE;
import static com.microsoft.teams.sdk.rnbundle.SdkBundleUtils.CompatibilityStatus.INCOMPATIBLE_UPDATE_RNAPP;
import static com.microsoft.teams.sdk.rnbundle.SdkBundleUtils.CompatibilityStatus.INCOMPATIBLE_UPDATE_TEAMS;

/**
 * Utility class for React Native bundle
 */
public final class SdkBundleUtils {
    private static final String ASSETS_SCHEME_NAME = "assets";
    private static final String CODE_PUSH_SCHEME_NAME = "codepush";
    private static final String BUNDLE_CACHE_DIRECTORY = "rnbundle";

    private SdkBundleUtils() {
    }

    /**
     * Extract bundle source from rnPackageUrl
     */
    @BundleSource
    public static String getBundleSourceType(@NonNull String rnPackageUrl) {
        Uri parsedPackageUri = Uri.parse(rnPackageUrl);

        if (ASSETS_SCHEME_NAME.equalsIgnoreCase(parsedPackageUri.getScheme())) {
            return BundleSource.LOCAL;
        } else if (CODE_PUSH_SCHEME_NAME.equalsIgnoreCase(parsedPackageUri.getScheme())) {
            return BundleSource.CODEPUSH;
        } else {
            return BundleSource.UNKNOWN;
        }
    }

    /**
     * Extract bundle name from rnPackageUrl
     */
    public static String getBundleName(@NonNull String rnPackageUrl) {
        Uri parsedPackageUri = Uri.parse(rnPackageUrl);
        return parsedPackageUri.getHost();
    }

    /**
     * Extract deployment key from rnPackageUrl
     */
    @Nullable
    public static String getDeploymentKey(@NonNull String rnPackageUrl) {
        String deploymentKey = null;
        if (BundleSource.CODEPUSH.equals(getBundleSourceType(rnPackageUrl))) {
            Uri parsedPackageUri = Uri.parse(rnPackageUrl);
            String key = parsedPackageUri.getQueryParameter("deploymentKey");
            if (!StringUtils.isEmptyOrWhiteSpace(key)) {
                deploymentKey = key;
            }
        }
        return deploymentKey;
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({BundleSource.LOCAL, BundleSource.CODEPUSH, BundleSource.UNKNOWN})
    public @interface BundleSource {
        String LOCAL = "local";
        String CODEPUSH = "codepush";
        String UNKNOWN = "unknown";
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({COMPATIBLE, INCOMPATIBLE_UPDATE_TEAMS, INCOMPATIBLE_UPDATE_RNAPP})
    public @interface CompatibilityStatus {
        String COMPATIBLE = "compatible";
        String INCOMPATIBLE_UPDATE_TEAMS = "incompatible:update_teams";
        String INCOMPATIBLE_UPDATE_RNAPP = "incompatible:update_rnapp";
    }

    /**
     * System.nanoTime is monotonic as per Android documentation:
     * https://developer.android.com/reference/android/os/SystemClock
     * Using System.currentTimeInMillis may result in duplicate request ids as the doc
     * says the value can move backwards as well.
     * @return
     */
    static synchronized long generateRequestId() {
        return System.nanoTime();
    }

    public static File getCacheBaseDirectory(@NonNull Context context) {
        return new File(context.getCacheDir(), BUNDLE_CACHE_DIRECTORY);
    }

    public static File getCacheAppDirectory(@NonNull Context context, @NonNull String appId) {
        return new File(getCacheBaseDirectory(context), appId);
    }
}
