package com.teamssdksim.modules;

import com.microsoft.skype.teams.storage.IExperimentationManager;
import com.teamssdksim.TeamsSdkSimExperimentationManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ExperimentationManagerModule {

    @Singleton
    @Provides
    static IExperimentationManager provideTeamsNavigationService() {
        return new TeamsSdkSimExperimentationManager();
    }
}
