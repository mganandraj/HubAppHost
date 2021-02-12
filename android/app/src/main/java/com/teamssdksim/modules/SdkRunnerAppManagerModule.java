package com.teamssdksim.modules;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.skype.teams.logger.ILogger;
import com.microsoft.skype.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.skype.teams.sdk.SdkRunnerAppManager;
import com.microsoft.skype.teams.services.configuration.AppConfiguration;
import com.microsoft.teams.androidutils.tasks.ITaskRunner;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.teamssdksim.TeamsSdkSimLogger;

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