package com.teamssdksim.modules;

import com.microsoft.skype.teams.storage.dao.appdefinition.AppDefinitionDao;
import com.microsoft.skype.teams.storage.database.SdkDatabase;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.teamssdksim.impls.TeamsSdkSimAppDefinitionDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppDefinitionDaoModule {
    @Singleton
    @Provides
    static AppDefinitionDao provideAppDefinitionDao(ITeamsApplication teamsApplication) {
        // return new TeamsSdkSimAppDefinitionDao(teamsApplication);
        return SdkDatabase.get().appDefinitionDao();
    }
}