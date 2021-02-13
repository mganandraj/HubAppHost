package com.teamssdksim.modules;

import com.microsoft.skype.teams.storage.dao.rnapps.RNAppsDao;
import com.microsoft.skype.teams.storage.database.SdkDatabase;
import com.teamssdksim.impls.TeamsSdkRNAppsDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RNAppsDaoModule {
    @Singleton
    @Provides
    static RNAppsDao provideRNAppsDao() {
        return SdkDatabase.get().rNAppsDao();
        // return new TeamsSdkRNAppsDao();
    }
}