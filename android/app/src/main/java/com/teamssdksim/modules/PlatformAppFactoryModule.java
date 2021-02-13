package com.teamssdksim.modules;

import com.microsoft.skype.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.skype.teams.storage.dao.appdefinition.AppDefinitionDao;
import com.teamssdksim.impls.TeamsSdkSimMobileModuleFactory;
import com.teamssdksim.impls.TeamsSdkSimPlatformAppFactory;

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