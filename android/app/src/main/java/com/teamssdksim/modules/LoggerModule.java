package com.teamssdksim.modules;

import com.microsoft.skype.teams.logger.ILogger;
import com.teamssdksim.TeamsSdkSimLogger;

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