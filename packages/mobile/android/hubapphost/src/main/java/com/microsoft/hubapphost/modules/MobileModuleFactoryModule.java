package com.microsoft.hubapphost.modules;

import android.content.Context;

import com.microsoft.hubapphost.impls.MobileModuleFactoryImpl;
import com.microsoft.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.teams.sdk.rnbundle.ISdkBundleDownloadManager;
import com.microsoft.teams.services.configuration.AppConfiguration;
import com.microsoft.teams.storage.IExperimentationManager;
import com.microsoft.teams.storage.dao.rnapps.RNAppsDao;
import com.microsoft.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.microsoft.teams.core.preferences.IPreferences;
import com.microsoft.teams.core.services.IScenarioManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MobileModuleFactoryModule {

    @Singleton
    @Provides
    static MobileModuleFactoryImpl provideMobileModuleFactory(Context context, ISdkRunnerAppManager sdkRunnerAppManager,
                                                              ITeamsApplication teamsApplication, ISdkBundleDownloadManager sdkBundleDownloadManager, RNAppsDao rnAppsDao, RNBundlesDao rnBundlesDao, AppConfiguration appConfiguration,
                                                              IPreferences preferences, IScenarioManager scenarioManager, IExperimentationManager experimentationManager) {
        return new MobileModuleFactoryImpl(context,
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