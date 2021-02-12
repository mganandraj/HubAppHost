package com.teamssdksim.modules;

import com.microsoft.skype.teams.logger.ITelemetryLogger;
import com.teamssdksim.TeamsSdkSimTelemetryLogger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TelemetryLoggerModule {

    @Singleton
    @Provides
    static ITelemetryLogger provideTelemetryLogger() {
        return new TeamsSdkSimTelemetryLogger();
    }
}
