package com.teamssdksim;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.skype.teams.mobilemodules.BaseMobileModule;
import com.microsoft.skype.teams.mobilemodules.ReactNativeMobileModule;
import com.microsoft.skype.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.skype.teams.sdk.rnbundle.ISdkBundleDownloadManager;
import com.microsoft.skype.teams.services.configuration.AppConfiguration;
import com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioContext;
import com.microsoft.skype.teams.storage.IExperimentationManager;
import com.microsoft.skype.teams.storage.dao.rnapps.RNAppsDao;
import com.microsoft.skype.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.skype.teams.storage.models.MobileModuleDefinition;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.microsoft.teams.core.preferences.IPreferences;
import com.microsoft.teams.core.services.IScenarioManager;

import java.util.Map;

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
