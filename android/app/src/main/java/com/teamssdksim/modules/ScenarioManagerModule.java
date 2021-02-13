package com.teamssdksim.modules;

import com.microsoft.skype.teams.logger.ILogger;
import com.microsoft.skype.teams.logger.ITelemetryLogger;
import com.microsoft.skype.teams.storage.IExperimentationManager;
import com.microsoft.teams.core.preferences.IPreferences;
import com.microsoft.teams.core.services.IScenarioManager;
import com.teamssdksim.impls.TeamsSdkSimScenarioManager;

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
        return new TeamsSdkSimScenarioManager(experimentationManager,
                telemetryLoggerModule,
                loggerModule,
                preferencesModule);
    }
}