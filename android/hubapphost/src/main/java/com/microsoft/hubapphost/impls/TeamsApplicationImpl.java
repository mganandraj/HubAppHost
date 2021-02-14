package com.microsoft.hubapphost.impls;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.teams.logger.ILogger;
import com.microsoft.teams.logger.ITelemetryLogger;
import com.microsoft.teams.storage.IExperimentationManager;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.microsoft.teams.core.preferences.IPreferences;
import com.microsoft.teams.core.services.IScenarioManager;

public class TeamsApplicationImpl implements ITeamsApplication {

    // Very hacky .. Trying to get the code running mode !!
    Activity mActivity;
    Application mApplication;
    IExperimentationManager mExperimentationManager;
    ITelemetryLogger mTelemetryLogger;
    IPreferences mPreferences;
    ILogger mLogger;
    IScenarioManager mScenarioManager;

    public TeamsApplicationImpl(Activity activity, Application application,
                                IExperimentationManager experimentationManager,
                                IScenarioManager scenarioManager,
                                ILogger logger,
                                ITelemetryLogger telemetryLogger,
                                IPreferences preferences) {
        mActivity = activity;
        mApplication = application;
        mExperimentationManager = experimentationManager;
        mTelemetryLogger = telemetryLogger;
        mPreferences = preferences;
        mScenarioManager = scenarioManager;
        mLogger = logger;
    }

    @Nullable
    @Override
    public Activity getActivity() {
        return mActivity;
    }

    @Nullable
    @Override
    public String getAppLaunchStepId() {
        return null;
    }

    @NonNull
    @Override
    public Application getApplication() {
        return mApplication;
    }

    @NonNull
    @Override
    public Context getApplicationContext() {
        return mApplication;
    }

    @Nullable
    @Override
    public String getCurrentCallDescription() {
        return null;
    }

    @NonNull
    @Override
    public String getFakeIMEI() {
        return null;
    }

    @NonNull
    @Override
    public String getFakeAndroidId() {
        return null;
    }

    @NonNull
    @Override
    public IExperimentationManager getExperimentationManager(@Nullable String userObjectId) {
        return mExperimentationManager;
    }

    @NonNull
    @Override
    public ILogger getLogger(@Nullable String userObjectId) {
        return mLogger;
    }

    @Nullable
    @Override
    public int getMsalConsumerConfigId() {
        return 0;
    }

    @Nullable
    @Override
    public int getMsalEnterpriseConfigId() {
        return 0;
    }

    @NonNull
    @Override
    public IScenarioManager getScenarioManager(@Nullable String userObjectId) {
        return mScenarioManager;
    }

    @Nullable
    @Override
    public String getUserId() {
        return "teamsdksimuser";
    }

    @Override
    public boolean isApplicationLaunch() {
        return false;
    }

    @Override
    public void onANR() {

    }

    @Override
    public void resetAppLaunchScenarioId() {

    }

    @Override
    public void reLaunchAppScheduled(long restartAfter) {

    }

    @Override
    public void reLaunchAppScheduled(long restartAfter, @NonNull Intent launchIntent) {

    }
}
