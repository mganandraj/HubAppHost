package com.teamssdksim.modules;

import com.microsoft.skype.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.skype.teams.storage.database.SdkDatabase;
import com.teamssdksim.impls.TeamsSdkSimRNBundlesDao;

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