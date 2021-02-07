/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk.rnbundle;

import android.content.Context;

import androidx.annotation.NonNull;

import com.microsoft.skype.teams.logger.ILogger;
import com.microsoft.skype.teams.logger.LogPriority;
import com.microsoft.skype.teams.sdk.SdkConstants;
import com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioContext;
import com.microsoft.skype.teams.storage.IExperimentationManager;
import com.microsoft.skype.teams.storage.dao.rnapps.RNAppsDao;
import com.microsoft.skype.teams.utilities.IOUtilities;
import com.microsoft.teams.androidutils.tasks.TaskUtilities;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.microsoft.teams.core.services.IScenarioManager;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.microsoft.skype.teams.sdk.rnbundle.SdkBundleUtils.getCacheAppDirectory;

/**
 * Responsible to download locally packaged bundle
 */
@Singleton
public class SdkLocalBundleDownloader implements ISdkBundleDownloader {
    private static final String LOG_TAG = "SdkLocalBundleDownloader";
    private static final String APP_INITIAL_LOCATION = "app_packages";
    private final ITeamsApplication mTeamsApplication;
    private ISdkBundleDownloadProgressListener mSdkBundleDownloadProgressListener;

    @Inject
    public SdkLocalBundleDownloader(@NonNull ITeamsApplication teamsApplication) {
        mTeamsApplication = teamsApplication;
    }

    @Override
    public void setSdkBundleDownloadProgressListener(@NonNull ISdkBundleDownloadProgressListener sdkBundleDownloadProgressListener) {
        mSdkBundleDownloadProgressListener = sdkBundleDownloadProgressListener;
    }

    @Override
    public void download(@NonNull String appId, @NonNull String bundleName, long requestId, @NonNull ILogger logger,
                         @NonNull IScenarioManager scenarioManager, @NonNull ScenarioContext scenarioContext,
                         @NonNull IExperimentationManager experimentationManager, @NonNull RNAppsDao rnAppsDao) {
        // Update Request
        mSdkBundleDownloadProgressListener.onBundleDownloadStatusUpdated(requestId, SdkConstants.SdkRequestDownloadStatus.QUEUED);
        Context context = mTeamsApplication.getApplicationContext();

        TaskUtilities.runOnBackgroundThread(() -> {
            // Update Request
            mSdkBundleDownloadProgressListener.onBundleDownloadStatusUpdated(requestId, SdkConstants.SdkRequestDownloadStatus.DOWNLOADING);
            createTemporaryAppDirectory(context, appId);
            String newFileName = String.format("%s_%d", appId, System.nanoTime());
            try {
                IOUtilities.copyFileorDirFromAssets(context, getCacheAppDirectory(context, appId) + "/" + newFileName,
                        APP_INITIAL_LOCATION + "/" + bundleName);
            } catch (IOException e) {
                logger.log(LogPriority.ERROR, LOG_TAG, e.getMessage());
            }
            File destination = new File(getCacheAppDirectory(context, appId), newFileName);
            mSdkBundleDownloadProgressListener.onBundleDownloadSuccess(requestId, appId, destination.toString());
        });
    }

    @Override
    public void clearTemporaryStorage(@NonNull String appId, @NonNull ILogger logger) {
        Context context = mTeamsApplication.getApplicationContext();
        try {
            IOUtilities.deleteDirectory(getCacheAppDirectory(context, appId).toString());
        } catch (IOException e) {
            logger.log(LogPriority.ERROR, LOG_TAG, e);
        }
    }

    private void createTemporaryAppDirectory(@NonNull Context context, @NonNull String appId) {
        File tempAppDir = new File(String.valueOf(getCacheAppDirectory(context, appId)));
        if (!tempAppDir.exists()) {
            tempAppDir.mkdir();
        }
    }
}
