package com.teamssdksim.modules;

import androidx.annotation.NonNull;

import com.microsoft.skype.teams.logger.ILogger;
import com.microsoft.skype.teams.mobilemodules.IPlatformAppManager;
import com.microsoft.skype.teams.mobilemodules.PlatformAppManager;
import com.microsoft.skype.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.skype.teams.storage.dao.appdefinition.AppDefinitionDao;
import com.teamssdksim.TeamsSdkSimLogger;
import com.teamssdksim.TeamsSdkSimPlatformAppFactory;

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