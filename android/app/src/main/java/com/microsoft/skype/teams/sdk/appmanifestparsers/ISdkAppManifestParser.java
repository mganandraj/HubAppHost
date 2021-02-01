/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk.appmanifestparsers;

import androidx.annotation.NonNull;

import com.google.gson.JsonObject;
import com.microsoft.skype.teams.sdk.models.SdkAppManifest;

/**
 * Parses the manifest for an SDK app.
 */
public interface ISdkAppManifestParser {
    @NonNull
    SdkAppManifest parseManifest(@NonNull JsonObject manifestObject) throws Exception;
}
