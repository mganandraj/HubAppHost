package com.microsoft.hubapphost.modules;

import com.microsoft.hubapphost.impls.TeamsSdkSimExperimentationManager;
import com.microsoft.teams.storage.IExperimentationManager;

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
