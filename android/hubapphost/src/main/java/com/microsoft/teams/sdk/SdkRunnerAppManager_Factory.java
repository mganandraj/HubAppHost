// Generated by Dagger (https://dagger.dev).
package com.microsoft.teams.sdk;

import android.content.Context;
import com.google.gson.Gson;
import com.microsoft.teams.services.configuration.AppConfiguration;
import com.microsoft.teams.androidutils.tasks.ITaskRunner;
import com.microsoft.teams.core.app.ITeamsApplication;
import dagger.internal.Factory;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SdkRunnerAppManager_Factory implements Factory<SdkRunnerAppManager> {
  private final Provider<Context> contextProvider;

  private final Provider<Gson> gsonProvider;

  private final Provider<ITaskRunner> taskRunnerProvider;

  private final Provider<AppConfiguration> appConfigurationProvider;

  private final Provider<ITeamsApplication> teamsApplicationProvider;

  public SdkRunnerAppManager_Factory(Provider<Context> contextProvider, Provider<Gson> gsonProvider,
      Provider<ITaskRunner> taskRunnerProvider, Provider<AppConfiguration> appConfigurationProvider,
      Provider<ITeamsApplication> teamsApplicationProvider) {
    this.contextProvider = contextProvider;
    this.gsonProvider = gsonProvider;
    this.taskRunnerProvider = taskRunnerProvider;
    this.appConfigurationProvider = appConfigurationProvider;
    this.teamsApplicationProvider = teamsApplicationProvider;
  }

  @Override
  public SdkRunnerAppManager get() {
    return newInstance(contextProvider.get(), gsonProvider.get(), taskRunnerProvider.get(), appConfigurationProvider.get(), teamsApplicationProvider.get());
  }

  public static SdkRunnerAppManager_Factory create(Provider<Context> contextProvider,
      Provider<Gson> gsonProvider, Provider<ITaskRunner> taskRunnerProvider,
      Provider<AppConfiguration> appConfigurationProvider,
      Provider<ITeamsApplication> teamsApplicationProvider) {
    return new SdkRunnerAppManager_Factory(contextProvider, gsonProvider, taskRunnerProvider, appConfigurationProvider, teamsApplicationProvider);
  }

  public static SdkRunnerAppManager newInstance(Context context, Gson gson, ITaskRunner taskRunner,
      AppConfiguration appConfiguration, ITeamsApplication teamsApplication) {
    return new SdkRunnerAppManager(context, gson, taskRunner, appConfiguration, teamsApplication);
  }
}
