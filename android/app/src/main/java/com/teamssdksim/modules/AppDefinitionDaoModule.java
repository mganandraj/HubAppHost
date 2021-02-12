package com.teamssdksim.modules;

import com.microsoft.skype.teams.logger.ILogger;
import com.microsoft.skype.teams.storage.dao.appdefinition.AppDefinitionDao;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.teamssdksim.TeamsSdkSimAppDefinitionDao;
import com.teamssdksim.TeamsSdkSimLogger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppDefinitionDaoModule {
    @Singleton
    @Provides
    static AppDefinitionDao provideAppDefinitionDao(ITeamsApplication teamsApplication) {
        return new TeamsSdkSimAppDefinitionDao(teamsApplication);
    }
}