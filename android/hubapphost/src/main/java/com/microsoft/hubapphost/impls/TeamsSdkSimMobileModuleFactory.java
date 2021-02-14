package com.microsoft.hubapphost.impls;

import android.content.Context;

import com.microsoft.teams.mobilemodules.BaseMobileModule;
import com.microsoft.teams.mobilemodules.ReactNativeMobileModule;
import com.microsoft.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.teams.sdk.rnbundle.ISdkBundleDownloadManager;
import com.microsoft.teams.services.configuration.AppConfiguration;
import com.microsoft.teams.storage.IExperimentationManager;
import com.microsoft.teams.storage.dao.rnapps.RNAppsDao;
import com.microsoft.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.teams.storage.models.MobileModuleDefinition;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.microsoft.teams.core.preferences.IPreferences;
import com.microsoft.teams.core.services.IScenarioManager;

public class TeamsSdkSimMobileModuleFactory {

    Context mContext;
    ISdkRunnerAppManager mSdkRunnerAppManager;
    ITeamsApplication mTeamsApplication;
    ISdkBundleDownloadManager mSdkBundleDownloadManager;
    RNAppsDao mRNAppsDao;
    RNBundlesDao mRNBundlesDao;
    AppConfiguration mAppConfiguration;
    IPreferences mPreferences;
    IScenarioManager mScenarioManager;
    IExperimentationManager mExperimentationManager;

    public TeamsSdkSimMobileModuleFactory(Context context, ISdkRunnerAppManager sdkRunnerAppManager,
                                          ITeamsApplication teamsApplication, ISdkBundleDownloadManager sdkBundleDownloadManager, RNAppsDao rnAppsDao, RNBundlesDao rnBundlesDao, AppConfiguration appConfiguration,
                                          IPreferences preferences, IScenarioManager scenarioManager, IExperimentationManager experimentationManager) {
        mContext = context;
        mSdkRunnerAppManager = sdkRunnerAppManager ;
        mTeamsApplication = teamsApplication;
        mSdkBundleDownloadManager = sdkBundleDownloadManager;
        mRNAppsDao = rnAppsDao;
        mRNBundlesDao = rnBundlesDao;

        mAppConfiguration = appConfiguration;
        mPreferences = preferences;

        mScenarioManager = scenarioManager;
        mExperimentationManager = experimentationManager;
    }

    public BaseMobileModule create(MobileModuleDefinition mobileModuleDefinition) {
        return new ReactNativeMobileModule(mobileModuleDefinition, mContext, mRNAppsDao, mRNBundlesDao, mSdkRunnerAppManager, mTeamsApplication, mAppConfiguration, mPreferences, mTeamsApplication.getLogger(""), mSdkBundleDownloadManager
                , mExperimentationManager, mScenarioManager);
    }
}
