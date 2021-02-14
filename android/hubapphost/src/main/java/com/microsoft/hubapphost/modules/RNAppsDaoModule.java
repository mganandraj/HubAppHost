package com.microsoft.hubapphost.modules;

import com.microsoft.teams.storage.dao.rnapps.RNAppsDao;
import com.microsoft.teams.storage.database.SdkDatabase;

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