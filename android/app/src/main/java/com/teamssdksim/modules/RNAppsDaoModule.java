package com.teamssdksim.modules;

import com.microsoft.skype.teams.storage.dao.rnapps.RNAppsDao;
import com.teamssdksim.TeamsSdkRNAppsDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RNAppsDaoModule {

    @Singleton
    @Provides
    static RNAppsDao provideRNAppsDao() {
        return new TeamsSdkRNAppsDao();
    }
}