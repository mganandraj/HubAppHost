package com.teamssdksim.modules;

import android.os.Handler;

import com.microsoft.teams.androidutils.tasks.ITaskRunner;
import com.microsoft.teams.androidutils.tasks.TaskRunner;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TaskRunnerModule {

    Handler mUiThreadHandler;
    public TaskRunnerModule(Handler uiThreadHandler) {
        mUiThreadHandler = uiThreadHandler;
    }

    @Singleton
    @Provides
    public ITaskRunner provideTaskRunner() {
        return new TaskRunner(mUiThreadHandler);
    }
}