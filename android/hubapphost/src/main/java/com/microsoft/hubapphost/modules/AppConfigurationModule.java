package com.microsoft.hubapphost.modules;

import com.microsoft.hubapphost.impls.TeamsSdkSimAppConfiguration;
import com.microsoft.teams.services.configuration.AppConfiguration;

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
