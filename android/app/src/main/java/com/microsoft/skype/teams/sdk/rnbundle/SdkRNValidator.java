/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk.rnbundle;

import androidx.annotation.NonNull;

import com.microsoft.skype.teams.sdk.SdkConstants;

import java.io.File;

import static com.microsoft.codepush.react.CodePushReactNativeCore.DEFAULT_JS_BUNDLE_NAME;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.ERROR_BUNDLE_UNAVAILABLE;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.ERROR_JS_FILE_UNAVAILABLE;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.ERROR_MANIFEST_UNAVAILABLE;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.JS_FILE_EXISTS;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.MANIFEST_FILE_EXISTS;

/**
 * Helper utility to validate integrity
 */
final class SdkRNValidator {

    private SdkRNValidator() {

    }

    @SdkConstants.SdkRequestDownloadStatus
    static int validateBundle(@NonNull String bundleLocation) {
        File bundle = new File(bundleLocation);
        if (bundle.exists()) {
            File manifest = new File(bundleLocation, DEFAULT_JS_BUNDLE_NAME);
            if (manifest.exists()) {
                return JS_FILE_EXISTS;
            } else {
                return ERROR_JS_FILE_UNAVAILABLE;
            }
        }
        return ERROR_BUNDLE_UNAVAILABLE;
    }

    @SdkConstants.SdkRequestDownloadStatus
    static int validateManifest(@NonNull String bundleLocation) {
        File manifestFile = new File(bundleLocation, "manifest/manifest.json");
        if (manifestFile.exists()) {
            return MANIFEST_FILE_EXISTS;
        } else {
            return ERROR_MANIFEST_UNAVAILABLE;
        }
    }
}
