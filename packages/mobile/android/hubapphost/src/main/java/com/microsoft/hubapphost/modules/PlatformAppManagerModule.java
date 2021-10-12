package com.microsoft.hubapphost.modules;

import com.microsoft.hubapphost.impls.TeamsSdkSimPlatformAppFactory;
import com.microsoft.teams.mobilemodules.IPlatformAppManager;
import com.microsoft.teams.mobilemodules.PlatformAppManager;
import com.microsoft.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.teams.storage.dao.appdefinition.AppDefinitionDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PlatformAppManagerModule {

    @Singleton
    @Provides
    static IPlatformAppManager providePlatformAppManager(TeamsSdkSimPlatformAppFactory platformAppFactory,
                                                         ISdkRunnerAppManager sdkRunnerAppManager,
                                                         AppDefinitionDao appDefinitionDao) {
        return new PlatformAppManager(platformAppFactory,
                sdkRunnerAppManager,
                appDefinitionDao);
    }
}