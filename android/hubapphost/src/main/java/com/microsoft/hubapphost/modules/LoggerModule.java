package com.microsoft.hubapphost.modules;

import com.microsoft.hubapphost.impls.TeamsSdkSimLogger;
import com.microsoft.teams.logger.ILogger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LoggerModule {

    @Singleton
    @Provides
    static ILogger provideLogger() {
        return new TeamsSdkSimLogger();
    }
}