package com.teamssdksim;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.skype.teams.mobilemodules.BaseMobileModule;
import com.microsoft.skype.teams.mobilemodules.ReactNativeMobileModule;
import com.microsoft.skype.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioContext;
import com.microsoft.skype.teams.storage.IExperimentationManager;
import com.microsoft.skype.teams.storage.dao.rnapps.RNAppsDao;
import com.microsoft.skype.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.skype.teams.storage.models.MobileModuleDefinition;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.microsoft.teams.core.services.IScenarioManager;

import java.util.Map;

public class TeamsSdkSimMobileModuleFactory {
    public BaseMobileModule create(MobileModuleDefinition mobileModuleDefinition, Context context, ISdkRunnerAppManager sdkRunnerAppManager,
                                   ITeamsApplication teamsApplication, RNAppsDao rnAppsDao, RNBundlesDao rnBundlesDao) {
        return new ReactNativeMobileModule(mobileModuleDefinition, context, rnAppsDao, rnBundlesDao, sdkRunnerAppManager, teamsApplication, null, null, teamsApplication.getLogger(""), null
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

            @Override
            public void endScenarioOnError(@Nullable ScenarioContext scenarioContext, @NonNull String scenarioStatusCode, @NonNull String scenarioStatusReason, String... tags) {

            }

            @Override
            public void endScenarioOnSuccess(@Nullable ScenarioContext scenarioContext, String... tags) {

            }
        });
    }
}
