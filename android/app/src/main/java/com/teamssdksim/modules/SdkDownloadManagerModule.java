package com.teamssdksim.modules;

import com.google.gson.GsonBuilder;
import com.microsoft.skype.teams.sdk.rnbundle.ISdkBundleDownloadManager;
import com.microsoft.skype.teams.sdk.rnbundle.ISdkBundleManager;
import com.microsoft.skype.teams.sdk.rnbundle.SdkBundleDownloadManager;
import com.microsoft.skype.teams.sdk.rnbundle.SdkCodepushBundleDownloader;
import com.microsoft.skype.teams.sdk.rnbundle.SdkLocalBundleDownloader;
import com.microsoft.skype.teams.services.configuration.AppConfiguration;
import com.microsoft.skype.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.teams.core.app.ITeamsApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SdkDownloadManagerModule {
    @Singleton
    @Provides
    static ISdkBundleDownloadManager provideSdkBundleDownloadManager(ISdkBundleManager sdkBundleManager,
                                                                     AppConfiguration appConfiguration,
                                                                     RNBundlesDao rnBundlesDao,
                                                                     ITeamsApplication teamsApplication) {
        return new SdkBundleDownloadManager(
                new SdkCodepushBundleDownloader(teamsApplication, sdkBundleManager),
                new SdkLocalBundleDownloader(teamsApplication),
                sdkBundleManager,
                new GsonBuilder().create(),
                appConfiguration,
                rnBundlesDao,
                teamsApplication);
    }
}