/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk.rnbundle;

import androidx.annotation.NonNull;

import com.microsoft.teams.logger.ILogger;
import com.microsoft.teams.services.diagnostics.telemetryschema.ScenarioContext;
import com.microsoft.teams.storage.IExperimentationManager;
import com.microsoft.teams.storage.dao.rnapps.RNAppsDao;
import com.microsoft.teams.core.services.IScenarioManager;

/**
 * Interface to download bundles
 */
public interface ISdkBundleDownloader {

    void setSdkBundleDownloadProgressListener(@NonNull ISdkBundleDownloadProgressListener sdkBundleDownloadProgressListener);

    void download(@NonNull String appId, @NonNull String sources, @NonNull long requestId, @NonNull ILogger logger,
                  @NonNull IScenarioManager scenarioManager, @NonNull ScenarioContext scenarioContext,
                  @NonNull IExperimentationManager experimentationManager, @NonNull RNAppsDao rnAppsDao);

    void clearTemporaryStorage(@NonNull String appId, @NonNull ILogger logger);
}
