package com.teamssdksim.modules;

import com.microsoft.skype.teams.storage.dao.rnbundles.RNBundlesDao;
import com.teamssdksim.TeamsSdkSimRNBundlesDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RNBundlesDaoModule {

    @Singleton
    @Provides
    static RNBundlesDao provideRNBundlesDao() {
        return new TeamsSdkSimRNBundlesDao();
    }
}