package com.microsoft.hubapphost.impls;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.teams.mobilemodules.IPlatformApp;
import com.microsoft.teams.mobilemodules.PlatformApp;
import com.microsoft.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.teams.storage.dao.appdefinition.AppDefinitionDao;
import com.microsoft.teams.storage.models.MobileModuleDefinition;

public class TeamsSdkSimPlatformAppFactory {

    AppDefinitionDao m_appDefinitionDao;
    TeamsSdkSimMobileModuleFactory m_mobileModuleFactory;
    ISdkRunnerAppManager m_sdkRunnerAppManager;

    public TeamsSdkSimPlatformAppFactory(AppDefinitionDao appDefinitionDao,
                                         TeamsSdkSimMobileModuleFactory mobileModuleFactory,
                                         ISdkRunnerAppManager sdkRunnerAppManager) {
        m_appDefinitionDao = appDefinitionDao;
        m_mobileModuleFactory = mobileModuleFactory;
        m_sdkRunnerAppManager = sdkRunnerAppManager;
    }

    public IPlatformApp create(String appId,
                        @Nullable MobileModuleDefinition mobileModuleDefinition,
                        @NonNull AppDefinitionDao appDefinitionDao,
                        @NonNull TeamsSdkSimMobileModuleFactory mobileModuleFactory,
                        @NonNull ISdkRunnerAppManager sdkRunnerAppManager) {

        return new PlatformApp(appId, mobileModuleDefinition, appDefinitionDao, mobileModuleFactory, sdkRunnerAppManager);
    }

    public TeamsSdkSimMobileModuleFactory getMobileModuleFactory() {
        return m_mobileModuleFactory;
    }
}