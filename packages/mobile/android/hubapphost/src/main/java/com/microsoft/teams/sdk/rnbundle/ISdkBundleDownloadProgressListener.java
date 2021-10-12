/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk.rnbundle;

import androidx.annotation.NonNull;

import com.microsoft.teams.sdk.SdkConstants;
import com.microsoft.teams.storage.tables.RNBundle;

/**
 * Interface for listening to React Native bundle download
 */
public interface ISdkBundleDownloadProgressListener {
    void onBundleDownloadSuccess(long requestId, @NonNull String appId, @NonNull String tempBundleLocation);

    void onBundleDownloadFailure(long requestId, @NonNull String appId, @SdkConstants.SdkRequestDownloadStatus int failureReason);

    void onBundleDownloadNotAvailable(long requestId, @NonNull String appId);

    void onBundleAlreadyExists(long requestId, @NonNull String appId, @NonNull RNBundle rnBundle);

    void onBundleDownloadStatusUpdated(long requestId, @SdkConstants.SdkRequestDownloadStatus int status);
}
