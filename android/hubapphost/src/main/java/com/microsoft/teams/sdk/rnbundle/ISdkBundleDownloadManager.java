/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk.rnbundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.teams.logger.ILogger;
import com.microsoft.teams.storage.IExperimentationManager;
import com.microsoft.teams.storage.dao.rnapps.RNAppsDao;
import com.microsoft.teams.storage.models.MobileModuleDefinition;
import com.microsoft.teams.core.services.IScenarioManager;
//import com.microsoft.teams.core.services.IScenarioManager;

import java.util.List;

/**
 * Interface for trigger bundle download
 */
public interface ISdkBundleDownloadManager {
    List<Long> syncRNApps(@Nullable List<MobileModuleDefinition> mobileModuleDefinitions, @NonNull String scenarioTag, @NonNull RNAppsDao rnAppsDao,
                          @NonNull IScenarioManager scenarioManager, @NonNull ILogger logger, @NonNull IExperimentationManager experimentationManager);
}