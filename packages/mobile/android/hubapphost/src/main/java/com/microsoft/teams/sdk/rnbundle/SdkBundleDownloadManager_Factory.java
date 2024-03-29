// Generated by Dagger (https://dagger.dev).
package com.microsoft.teams.sdk.rnbundle;

import com.google.gson.Gson;
import com.microsoft.teams.services.configuration.AppConfiguration;
import com.microsoft.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.teams.core.app.ITeamsApplication;
import dagger.internal.Factory;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SdkBundleDownloadManager_Factory implements Factory<SdkBundleDownloadManager> {
  private final Provider<ISdkBundleDownloader> sdkCodepushBundleDownloaderProvider;

  private final Provider<ISdkBundleDownloader> sdkLocalBundleDownloaderProvider;

  private final Provider<ISdkBundleManager> sdkBundleManagerProvider;

  private final Provider<Gson> gsonProvider;

  private final Provider<AppConfiguration> appConfigurationProvider;

  private final Provider<RNBundlesDao> rnBundlesDaoProvider;

  private final Provider<ITeamsApplication> teamsApplicationProvider;

  public SdkBundleDownloadManager_Factory(
      Provider<ISdkBundleDownloader> sdkCodepushBundleDownloaderProvider,
      Provider<ISdkBundleDownloader> sdkLocalBundleDownloaderProvider,
      Provider<ISdkBundleManager> sdkBundleManagerProvider, Provider<Gson> gsonProvider,
      Provider<AppConfiguration> appConfigurationProvider,
      Provider<RNBundlesDao> rnBundlesDaoProvider,
      Provider<ITeamsApplication> teamsApplicationProvider) {
    this.sdkCodepushBundleDownloaderProvider = sdkCodepushBundleDownloaderProvider;
    this.sdkLocalBundleDownloaderProvider = sdkLocalBundleDownloaderProvider;
    this.sdkBundleManagerProvider = sdkBundleManagerProvider;
    this.gsonProvider = gsonProvider;
    this.appConfigurationProvider = appConfigurationProvider;
    this.rnBundlesDaoProvider = rnBundlesDaoProvider;
    this.teamsApplicationProvider = teamsApplicationProvider;
  }

  @Override
  public SdkBundleDownloadManager get() {
    return newInstance(sdkCodepushBundleDownloaderProvider.get(), sdkLocalBundleDownloaderProvider.get(), sdkBundleManagerProvider.get(), gsonProvider.get(), appConfigurationProvider.get(), rnBundlesDaoProvider.get(), teamsApplicationProvider.get());
  }

  public static SdkBundleDownloadManager_Factory create(
      Provider<ISdkBundleDownloader> sdkCodepushBundleDownloaderProvider,
      Provider<ISdkBundleDownloader> sdkLocalBundleDownloaderProvider,
      Provider<ISdkBundleManager> sdkBundleManagerProvider, Provider<Gson> gsonProvider,
      Provider<AppConfiguration> appConfigurationProvider,
      Provider<RNBundlesDao> rnBundlesDaoProvider,
      Provider<ITeamsApplication> teamsApplicationProvider) {
    return new SdkBundleDownloadManager_Factory(sdkCodepushBundleDownloaderProvider, sdkLocalBundleDownloaderProvider, sdkBundleManagerProvider, gsonProvider, appConfigurationProvider, rnBundlesDaoProvider, teamsApplicationProvider);
  }

  public static SdkBundleDownloadManager newInstance(
      ISdkBundleDownloader sdkCodepushBundleDownloader,
      ISdkBundleDownloader sdkLocalBundleDownloader, ISdkBundleManager sdkBundleManager, Gson gson,
      AppConfiguration appConfiguration, RNBundlesDao rnBundlesDao,
      ITeamsApplication teamsApplication) {
    return new SdkBundleDownloadManager(sdkCodepushBundleDownloader, sdkLocalBundleDownloader, sdkBundleManager, gson, appConfiguration, rnBundlesDao, teamsApplication);
  }
}
