/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.mobilemodules;

//import com.microsoft.teams.mobilemodules.injection.MobileModuleFactory;
import com.microsoft.hubapphost.impls.TeamsSdkSimMobileModuleFactory;
import com.microsoft.teams.sdk.ISdkRunnerAppManager;
        import com.microsoft.teams.sdk.utils.SdkRunnerUtils;
//import com.microsoft.teams.storage.dao.appdefinition.AppDefinitionDao;
//import com.microsoft.teams.services.diagnostics.telemetryschema.ScenarioContext;
//import com.microsoft.teams.storage.IExperimentationManager;
import com.microsoft.teams.storage.dao.appdefinition.AppDefinitionDao;
        import com.microsoft.teams.storage.models.MobileModuleDefinition;
import com.microsoft.teams.storage.tables.AppDefinition;
//import com.microsoft.teams.core.services.IScenarioManager;
//import com.microsoft.teams.injection.PlatformAppId;
//import com.microsoft.teams.injection.PlatformAppScope;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

        import static com.microsoft.teams.mobilemodules.MobileModuleConstants.MODULE_TYPE_NATIVE;
import static com.microsoft.teams.mobilemodules.MobileModuleConstants.MODULE_TYPE_REACT_NATIVE;

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
    TeamsSdkSimMobileModuleFactory mMobileModuleFactory;

    @Inject
    public PlatformApp(@NonNull /*@PlatformAppId*/ String appId,
                       @Nullable MobileModuleDefinition mobileModuleDefinition,
                       @NonNull AppDefinitionDao appDefinitionDao,
                       /*@NonNull MobileModuleFactory mobileModuleFactory,*/
                       TeamsSdkSimMobileModuleFactory mobileModuleFactory,
                       @NonNull ISdkRunnerAppManager sdkRunnerAppManager) {

        mAppId = appId;
        if (SdkRunnerUtils.isRunnerApp(appId)) {
            mAppDefinition = sdkRunnerAppManager.getAppDefinition();
        } else {
           mAppDefinition = appDefinitionDao.fromId(appId);
        }
        mMobileModuleFactory = mobileModuleFactory;
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
    public IMobileModule getMobileModule() {
        if (mMobileModuleDefinition == null) {
            return null;
        }

        if (mMobileModule == null) {
            if (MODULE_TYPE_REACT_NATIVE.equalsIgnoreCase(mMobileModuleDefinition.type)) {
//                mMobileModule = mMobileModuleFactory.create(ReactNativeMobileModule.class);
                mMobileModule = mMobileModuleFactory.create(mMobileModuleDefinition);

            } else if (MODULE_TYPE_NATIVE.equalsIgnoreCase(mMobileModuleDefinition.type)) {
//                mMobileModule = mMobileModuleFactory.create(NativeMobileModule.class);
                mMobileModule = mMobileModuleFactory.create(mMobileModuleDefinition);
            } else {
                return null;
            }
        }

        return mMobileModule;
    }

    @Override
    public void setAppDefinition(@NonNull AppDefinition appDefinition) {
        mAppDefinition = appDefinition;
    }
}
