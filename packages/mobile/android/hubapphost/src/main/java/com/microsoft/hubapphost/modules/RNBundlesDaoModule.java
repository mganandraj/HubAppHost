package com.microsoft.hubapphost.modules;

import com.microsoft.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.teams.storage.database.SdkDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RNBundlesDaoModule {

    @Singleton
    @Provides
    static RNBundlesDao provideRNBundlesDao() {
        return SdkDatabase.get().rnBundlesDao();
        // return new TeamsSdkSimRNBundlesDao();
    }
}