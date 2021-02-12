package com.teamssdksim.modules;

import android.content.Context;

import com.microsoft.skype.teams.logger.ILogger;
import com.microsoft.skype.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.skype.teams.sdk.rnbundle.ISdkBundleDownloadManager;
import com.microsoft.skype.teams.services.configuration.AppConfiguration;
import com.microsoft.skype.teams.storage.IExperimentationManager;
import com.microsoft.skype.teams.storage.dao.rnapps.RNAppsDao;
import com.microsoft.skype.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.microsoft.teams.core.preferences.IPreferences;
import com.microsoft.teams.core.services.IScenarioManager;
import com.teamssdksim.TeamSDKActivity;
import com.teamssdksim.TeamsSdkSimLogger;
import com.teamssdksim.TeamsSdkSimMobileModuleFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MobileModuleFactoryModule {

    @Singleton
    @Provides
    static TeamsSdkSimMobileModuleFactory provideMobileModuleFactory(Context context, ISdkRunnerAppManager sdkRunnerAppManager,
                                                                     ITeamsApplication teamsApplication, ISdkBundleDownloadManager sdkBundleDownloadManager, RNAppsDao rnAppsDao, RNBundlesDao rnBundlesDao, AppConfiguration appConfiguration,
                                                                     IPreferences preferences, IScenarioManager scenarioManager, IExperimentationManager experimentationManager) {
        return new TeamsSdkSimMobileModuleFactory(context,
                sdkRunnerAppManager,
                teamsApplication,
                sdkBundleDownloadManager,
                rnAppsDao,
                rnBundlesDao,
                appConfiguration,
                preferences,
                scenarioManager,
                experimentationManager);

    }
}