/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.mobilemodules;

import android.content.Context;

//import com.microsoft.skype.teams.mobilemodules.injection.MobileModuleFactory;
import com.microsoft.skype.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.skype.teams.sdk.utils.SdkRunnerUtils;
//import com.microsoft.skype.teams.storage.dao.appdefinition.AppDefinitionDao;
import com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioContext;
import com.microsoft.skype.teams.storage.IExperimentationManager;
import com.microsoft.skype.teams.storage.dao.appdefinition.AppDefinitionDao;
import com.microsoft.skype.teams.storage.dao.rnapps.RNAppsDao;
import com.microsoft.skype.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.skype.teams.storage.models.MobileModuleDefinition;
import com.microsoft.skype.teams.storage.tables.AppDefinition;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.microsoft.teams.core.services.IScenarioManager;
//import com.microsoft.teams.injection.PlatformAppId;
//import com.microsoft.teams.injection.PlatformAppScope;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Map;


//import static com.microsoft.skype.teams.mobilemodules.MobileModuleConstants.MODULE_TYPE_NATIVE;
//import static com.microsoft.skype.teams.mobilemodules.MobileModuleConstants.MODULE_TYPE_REACT_NATIVE;

/**
 * Class for platform app
 */
// @PlatformAppScope
public class PlatformApp implements IPlatformApp {
    private String mAppId;
    private AppDefinition mAppDefinition;
    private MobileModuleDefinition mMobileModuleDefinition;
    private IMobileModule mMobileModule;
    // private MobileModuleFactory mMobileModuleFactory;

    @Inject
    public PlatformApp(@NonNull /*@PlatformAppId*/ String appId,
                       @Nullable MobileModuleDefinition mobileModuleDefinition,
                       @NonNull AppDefinitionDao appDefinitionDao,
                       // @NonNull MobileModuleFactory mobileModuleFactory,
                       @NonNull ISdkRunnerAppManager sdkRunnerAppManager) {

        mAppId = appId;
        if (SdkRunnerUtils.isRunnerApp(appId)) {
            mAppDefinition = sdkRunnerAppManager.getAppDefinition();
        } else {
           mAppDefinition = appDefinitionDao.fromId(appId);
        }
        // mMobileModuleFactory = mobileModuleFactory;
        mMobileModuleDefinition = mobileModuleDefinition;
    }

    @NonNull
    public String getAppId() {
        return mAppId;
    }

    @NonNull
    public AppDefinition getAppDefinition() {
        return mAppDefinition;
    }

    @Nullable
    public IMobileModule getMobileModule(Context context, ISdkRunnerAppManager sdkRunnerAppManager,
                                         ITeamsApplication teamsApplication, RNAppsDao rnAppsDao, RNBundlesDao rnBundlesDao) {
        if (mMobileModuleDefinition == null) {
            return null;
        }

        if (mMobileModule == null) {
//            if (MODULE_TYPE_REACT_NATIVE.equalsIgnoreCase(mMobileModuleDefinition.type)) {
//                mMobileModule = mMobileModuleFactory.create(ReactNativeMobileModule.class);
//            } else if (MODULE_TYPE_NATIVE.equalsIgnoreCase(mMobileModuleDefinition.type)) {
//                mMobileModule = mMobileModuleFactory.create(NativeMobileModule.class);
//            } else {
//                return null;
//            }
            mMobileModule = new ReactNativeMobileModule(mMobileModuleDefinition, context, rnAppsDao, rnBundlesDao, sdkRunnerAppManager, teamsApplication, null, null, teamsApplication.getLogger(""), null
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
        return mMobileModule;
    }

    @Override
    public void setAppDefinition(@NonNull AppDefinition appDefinition) {
        mAppDefinition = appDefinition;
    }
}
