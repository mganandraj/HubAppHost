package com.microsoft.hubapphost.modules;

import com.microsoft.hubapphost.impls.TeamsSdkSimMobileModuleFactory;
import com.microsoft.hubapphost.impls.TeamsSdkSimPlatformAppFactory;
import com.microsoft.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.teams.storage.dao.appdefinition.AppDefinitionDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PlatformAppFactoryModule {

    @Singleton
    @Provides
    static TeamsSdkSimPlatformAppFactory providePlatformAppFactory(AppDefinitionDao appDefinitionDao,
                                                                   TeamsSdkSimMobileModuleFactory mobileModuleFactory,
                                                                   ISdkRunnerAppManager sdkRunnerAppManager) {
        return new TeamsSdkSimPlatformAppFactory(appDefinitionDao,
                mobileModuleFactory,
                sdkRunnerAppManager);
    }
}