package com.microsoft.hubapphost.modules;

import com.microsoft.hubapphost.impls.ScenarioManagerImpl;
import com.microsoft.teams.logger.ILogger;
import com.microsoft.teams.logger.ITelemetryLogger;
import com.microsoft.teams.storage.IExperimentationManager;
import com.microsoft.teams.core.preferences.IPreferences;
import com.microsoft.teams.core.services.IScenarioManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ScenarioManagerModule {

    @Singleton
    @Provides
    static IScenarioManager provideScenarioManager(IExperimentationManager experimentationManager,
                                                   ITelemetryLogger telemetryLoggerModule,
                                                   ILogger loggerModule,
                                                   IPreferences preferencesModule) {
        return new ScenarioManagerImpl(experimentationManager,
                telemetryLoggerModule,
                loggerModule,
                preferencesModule);
    }
}