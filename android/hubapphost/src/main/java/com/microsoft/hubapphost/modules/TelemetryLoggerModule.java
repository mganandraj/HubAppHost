package com.microsoft.hubapphost.modules;

import com.microsoft.hubapphost.impls.TeamsSdkSimTelemetryLogger;
import com.microsoft.teams.logger.ITelemetryLogger;

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
