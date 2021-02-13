package com.teamssdksim.modules;

import com.microsoft.skype.teams.services.configuration.AppConfiguration;
import com.teamssdksim.impls.TeamsSdkSimAppConfiguration;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppConfigurationModule {
    @Singleton
    @Provides
    static AppConfiguration provideAppConfiguration() {
        return new TeamsSdkSimAppConfiguration();
    }
}
