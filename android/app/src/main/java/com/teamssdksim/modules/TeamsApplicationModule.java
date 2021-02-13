package com.teamssdksim.modules;

import android.app.Activity;
import android.app.Application;

import com.microsoft.skype.teams.logger.ILogger;
import com.microsoft.skype.teams.logger.ITelemetryLogger;
import com.microsoft.skype.teams.storage.IExperimentationManager;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.microsoft.teams.core.preferences.IPreferences;
import com.microsoft.teams.core.services.IScenarioManager;
import com.teamssdksim.impls.TeamsSdkSimApplication;

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