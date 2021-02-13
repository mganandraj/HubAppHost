package com.teamssdksim.modules;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.microsoft.skype.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.skype.teams.sdk.SdkRunnerAppManager;
import com.microsoft.skype.teams.services.configuration.AppConfiguration;
import com.microsoft.teams.androidutils.tasks.ITaskRunner;
import com.microsoft.teams.core.app.ITeamsApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SdkRunnerAppManagerModule {
    @Singleton
    @Provides
    static ISdkRunnerAppManager provideSdkRunnerAppManagerModule(Context context,
                                                                 ITaskRunner taskRunner,
                                                                 AppConfiguration appConfiguration,
                                                                 ITeamsApplication teamsApplication) {
        return new SdkRunnerAppManager(context, new GsonBuilder().create(), taskRunner, appConfiguration, teamsApplication);
    }
}