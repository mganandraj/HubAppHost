package com.microsoft.hubapphost.modules;

import android.app.Activity;
import android.app.Application;

import com.microsoft.hubapphost.impls.TeamsSdkSimApplication;
import com.microsoft.teams.logger.ILogger;
import com.microsoft.teams.logger.ITelemetryLogger;
import com.microsoft.teams.storage.IExperimentationManager;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.microsoft.teams.core.preferences.IPreferences;
import com.microsoft.teams.core.services.IScenarioManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TeamsApplicationModule {
    Application mApplication;
    Activity mActivity;
    public TeamsApplicationModule(Application application,      Activity activity) {
        this.mActivity = activity;
        this.mApplication = application;
    }

    @Singleton
    @Provides
    public ITeamsApplication provideTeamsApplication(IExperimentationManager experimentationManager,
                                                     IScenarioManager scenarioManager,
                                                     ILogger logger,
                                                     ITelemetryLogger telemetryLogger,
                                                     IPreferences preferences) {
        return new TeamsSdkSimApplication(mActivity, mApplication, experimentationManager, scenarioManager, logger, telemetryLogger, preferences);
    }
}