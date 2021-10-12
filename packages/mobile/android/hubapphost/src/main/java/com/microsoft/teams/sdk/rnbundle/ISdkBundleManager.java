/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk.rnbundle;

import androidx.annotation.NonNull;

import com.microsoft.teams.sdk.models.SdkAppManifest;
import com.microsoft.teams.storage.tables.RNBundle;

/**
 * Interface to manage downloaded bundles
 */
public interface ISdkBundleManager {

    boolean exists(@NonNull String appId, @NonNull String version);

    void addBundle(@NonNull String appId, @NonNull SdkAppManifest manifest, @NonNull String tempBundleLocation, @NonNull SdkBundleDownloadRequest source);

    void deleteBundle(@NonNull String appId, @NonNull String version);

    RNBundle getLocalBundle(@NonNull String appId, @NonNull String packageHash);

    String getBundleLocation(@NonNull String appId, @NonNull String version);
}
