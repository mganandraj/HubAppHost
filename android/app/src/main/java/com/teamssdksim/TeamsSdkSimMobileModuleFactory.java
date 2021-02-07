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

    TeamsSdkSimMobileModuleFactory(Context context, ISdkRunnerAppManager sdkRunnerAppManager,
                                   ITeamsApplication teamsApplication, ISdkBundleDownloadManager sdkBundleDownloadManager, RNAppsDao rnAppsDao, RNBundlesDao rnBundlesDao, AppConfiguration appConfiguration,
                                   IPreferences preferences) {
        mContext = context;
        mSdkRunnerAppManager = sdkRunnerAppManager ;
        mTeamsApplication = teamsApplication;
        mSdkBundleDownloadManager = sdkBundleDownloadManager;
        mRNAppsDao = rnAppsDao;
        mRNBundlesDao = rnBundlesDao;

        mAppConfiguration = appConfiguration;
        mPreferences = preferences;
    }

    public BaseMobileModule create(MobileModuleDefinition mobileModuleDefinition) {
        return new ReactNativeMobileModule(mobileModuleDefinition, mContext, mRNAppsDao, mRNBundlesDao, mSdkRunnerAppManager, mTeamsApplication, mAppConfiguration, mPreferences, mTeamsApplication.getLogger(""), mSdkBundleDownloadManager
                , new IExperimentationManager() {
            @Nullable
            @Override
            public String[] getEcsSettingAsStringArray(@NonNull String teamName, @NonNull String experimentName, @Nullable String[] defaultValue) {
                return new String[0];
            }

            @Nullable
            @Override
            public String[] getEcsSettingAsStringArray(String configName, String[] defaultValue) {
                return new String[0];
            }

            @Override
            public String getRingInfo() {
                return null;
            }

            @Override
            public String[] getListOfRNAppForPreInit() {
                return new String[0];
            }

            @Override
            public int getRetryCountForCodepushBundleDownload() {
                return 0;
            }

            @Override
            public int getCodePushUpdateCheckBackoffTimeInMinutes() {
                return 0;
            }

            @Override
            public boolean isStepTelemetryEnabled() {
                return false;
            }

            @Override
            public String getReactNativeAppDeploymentKey(@NonNull String appId) {
                return null;
            }

            @Override
            public boolean shouldLogExperimentIds() {
                return false;
            }

            @Override
            public String getAppInfoExperimentationIds() {
                return null;
            }
        }, new IScenarioManager(){

            @Override
            public ScenarioContext startScenario(String scenarioName, @Nullable String instrumentationSource, @Nullable Map<String, Object> databag, String... tags) {
                return null;
            }

            @NonNull
            @Override
            public ScenarioContext startScenario(String scenarioName, String... tags) {
                return null;
            }

            @Override
            public ScenarioContext startScenario(String scenarioName, @NonNull ScenarioContext parentScenarioContext, String... tags) {
                return null;
            }

            @Override
            public ScenarioContext getScenario(@Nullable String stepId) {
                return null;
            }

            @Override
            public void endScenarioOnError(@Nullable ScenarioContext scenarioContext, @NonNull String scenarioStatusCode, @NonNull String scenarioStatusReason, String... tags) {

            }

            @Override
            public void endScenarioOnSuccess(@Nullable ScenarioContext scenarioContext, String... tags) {

            }
        });
    }
}
