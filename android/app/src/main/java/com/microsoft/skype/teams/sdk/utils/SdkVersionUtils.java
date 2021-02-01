/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk.utils;

import com.microsoft.skype.teams.sdk.rnbundle.SdkBundleUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Utility class for maintaining SDK version.
 */
public final class SdkVersionUtils {
    public static final String VERSION_0_7_5 = "0.7.5";
    public static final String VERSION_0_7_4 = "0.7.4";
    public static final String VERSION_0_7_3 = "0.7.3";
    public static final String VERSION_0_7_2 = "0.7.2";
    public static final String VERSION_0_7_1 = "0.7.1";
    public static final String VERSION_0_7_0 = "0.7.0";

    public static final List<String> SUPPORTED_BINARY_VERSION_LIST = Collections.unmodifiableList(
            Arrays.asList(
                    VERSION_0_7_5,
                    VERSION_0_7_4,
                    VERSION_0_7_3,
                    VERSION_0_7_2,
                    VERSION_0_7_1,
                    VERSION_0_7_0
            )
    );

    public static String getMaxSupportedBinaryVersion() {
        return SUPPORTED_BINARY_VERSION_LIST.get(0);
    }

    public static String getMinSupportedBinaryVersion() {
        return SUPPORTED_BINARY_VERSION_LIST.get(SUPPORTED_BINARY_VERSION_LIST.size() - 1);
    }

    /**
     * Note : binaryVersion will always be smaller or equal to maxSupportedBinaryVersion
     */
    @SdkBundleUtils.CompatibilityStatus
    public static String checkCompatibilityStatus(String binaryVersion, boolean isExperimentalRnSdkAllowed) {
        String rnSdkVersion = binaryVersion;
        if (isExperimentalRnSdkAllowed && binaryVersion.indexOf('-') >= 0) {
            rnSdkVersion = binaryVersion.substring(0, binaryVersion.indexOf('-'));
        }
        if (SUPPORTED_BINARY_VERSION_LIST.contains(rnSdkVersion)) {
            return SdkBundleUtils.CompatibilityStatus.COMPATIBLE;
        } else if (compareSDKVersions(rnSdkVersion, getMinSupportedBinaryVersion()) == -1) {
            // Ex. binaryVersion:0.4.0, MinSupportedBinaryVersion: 0.5.0
            return SdkBundleUtils.CompatibilityStatus.INCOMPATIBLE_UPDATE_RNAPP;
        }

        // fallback case - Ex. binaryVersion:0.6.0, MinSupportedBinaryVersion: 0.5.0, MaxSupportedBinaryVersion: 0.5.4
        return SdkBundleUtils.CompatibilityStatus.INCOMPATIBLE_UPDATE_TEAMS;
    }

    /*
     * Assumption : 1) Pre-release versions are not supported
     * If v1 > v2, returns 1
     * If v1 == v2, returns 0
     * If v1 < v2, returns -1
     */
    public static int compareSDKVersions(@NonNull String version1, @NonNull String version2) {
        String[] levels1 = version1.trim().split("-")[0].split("\\.");
        String[] levels2 = version2.trim().split("-")[0].split("\\.");

        int length = Math.max(levels1.length, levels2.length);
        for (int i = 0; i < length; i++) {
            Long v1 = i < levels1.length ? Long.parseLong(levels1[i]) : 0;
            Long v2 = i < levels2.length ? Long.parseLong(levels2[i]) : 0;
            int compare = v1.compareTo(v2);
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }

    /*
     * Assumption : 1) Pre-release versions are not supported
     *              2) Logic is written for pre-public release (0.y.z)
     * If v1 > v2, returns 1
     * If v1 == v2, returns 0
     * If v1 < v2, returns -1
     */
    public static int compareMinorSDKVersions(String v1, String v2) {
        int ver1 = getMinorVersion(v1);
        int ver2 = getMinorVersion(v2);
        return Integer.compare(ver1, ver2);
    }

    private static int getMinorVersion(String version) {
        String versionWithoutPrerelease = version.trim().split("-")[0];
        String[] subversions = versionWithoutPrerelease.split("\\.");
        return Integer.parseInt(subversions[1]);
    }

    private SdkVersionUtils() {
    }
}
