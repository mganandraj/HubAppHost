package com.teamssdksim.modules;

import com.microsoft.skype.teams.mobilemodules.IPlatformAppManager;
import com.microsoft.skype.teams.mobilemodules.PlatformAppManager;
import com.microsoft.skype.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.skype.teams.storage.dao.appdefinition.AppDefinitionDao;
import com.teamssdksim.impls.TeamsSdkSimPlatformAppFactory;

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