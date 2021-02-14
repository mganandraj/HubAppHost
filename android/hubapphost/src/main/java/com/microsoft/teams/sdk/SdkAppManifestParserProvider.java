/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk;

import com.microsoft.teams.sdk.appmanifestparsers.ISdkAppManifestParser;
import com.microsoft.teams.sdk.appmanifestparsers.unsupported.UnsupportedSdkAppManifestParser;
import com.microsoft.teams.sdk.appmanifestparsers.v12.SdkAppManifestParserV12;
import com.microsoft.teams.sdk.utils.SdkVersionUtils;

import androidx.annotation.NonNull;

import static com.microsoft.teams.sdk.utils.SdkVersionUtils.VERSION_0_7_0;
import static com.microsoft.teams.sdk.utils.SdkVersionUtils.VERSION_0_7_1;
import static com.microsoft.teams.sdk.utils.SdkVersionUtils.VERSION_0_7_2;
import static com.microsoft.teams.sdk.utils.SdkVersionUtils.VERSION_0_7_3;
import static com.microsoft.teams.sdk.utils.SdkVersionUtils.VERSION_0_7_4;
import static com.microsoft.teams.sdk.utils.SdkVersionUtils.VERSION_0_7_5;

/**
 * Provides the sdk app manifest parser for the specified version of the app manifest.
 */
public final class SdkAppManifestParserProvider {

    private static ISdkAppManifestParser manifestParserV12 = new SdkAppManifestParserV12();

    /**
     * Assumption : targetReactNativeSdkVersion is compatible.
     * Note : Pre-release versions are supported either for Runner App or Dev-Debug environment.
     */
    @NonNull
    public static ISdkAppManifestParser getAppManifestParser(String targetReactNativeSdkVersion, boolean isExperimentalRnSdkAllowed) {
        switch (targetReactNativeSdkVersion) {
            case VERSION_0_7_0:
            case VERSION_0_7_1:
            case VERSION_0_7_2:
            case VERSION_0_7_3:
            case VERSION_0_7_4:
            case VERSION_0_7_5:
                return manifestParserV12;
            default:
                if (isExperimentalRnSdkAllowed) {
                    return manifestParserV12;
                } else {
                    // Assumption: logic is for pre-public release (0.y.z)
                    // if targetReactNativeSdkVersion > MaxSupportedBinaryVersion
                    if ((SdkVersionUtils.compareSDKVersions(targetReactNativeSdkVersion, SdkVersionUtils.getMaxSupportedBinaryVersion()) == 1)
                            && (SdkVersionUtils.compareMinorSDKVersions(targetReactNativeSdkVersion, SdkVersionUtils.getMaxSupportedBinaryVersion()) == 0)) {
                        return manifestParserV12;
                    }
                }
                return new UnsupportedSdkAppManifestParser();
        }
    }

    private SdkAppManifestParserProvider() {
    }
}
