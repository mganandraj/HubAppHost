/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk.appmanifestparsers.unsupported;

import androidx.annotation.NonNull;

import com.google.gson.JsonObject;
import com.microsoft.skype.teams.sdk.appmanifestparsers.ISdkAppManifestParser;
import com.microsoft.skype.teams.sdk.models.SdkAppManifest;

/**
 * Implements the unsupported app manifest parser.
 */
public class UnsupportedSdkAppManifestParser implements ISdkAppManifestParser {
    @NonNull
    @Override
    public SdkAppManifest parseManifest(@NonNull JsonObject manifestObject) throws Exception {
        throw new Exception("Unsupported manifest.");
    }
}
