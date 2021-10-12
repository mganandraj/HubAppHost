package com.microsoft.hubapphost.modules;

import com.microsoft.teams.sdk.rnbundle.ISdkBundleManager;
import com.microsoft.teams.sdk.rnbundle.SdkBundleManager;
import com.microsoft.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.teams.core.app.ITeamsApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SdkBundleManagerModule {
    @Singleton
    @Provides
    static ISdkBundleManager provideLogger(ITeamsApplication teamsApplication, RNBundlesDao rnBundlesDao) {
        return new SdkBundleManager(teamsApplication, rnBundlesDao);
    }
}