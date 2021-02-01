package com.teamssdksim;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.skype.teams.logger.ILogger;
import com.microsoft.teams.core.app.ITeamsApplication;

import java.util.Map;
import java.util.logging.Logger;

public class TeamsSdkSimApplication implements ITeamsApplication {

    class TeamsSdkSimLogger implements ILogger {

        @Override
        public boolean isLogTransmissionEnabled() {
            return true;
        }

        @Override
        public int getMinimumLogPriority() {
            return 0;
        }

        @Override
        public void setMinimumLogPriority(int logPriority) {

        }

        @Override
        public void enableFileLogging(boolean enabled) {

        }

        @Override
        public void log(int priority, String tag, String format, Object... args) {
            Log.e(tag, String.format(format, args));
        }

        @Override
        public void log(int priority, String tag, Throwable t) {
            Log.e(tag, t.toString());
        }

        @Override
        public void log(int priority, String tag, Throwable t, String format, Object... args) {
            Log.e(tag, String.format(format, args) + " || " + t.toString());
        }

        @Override
        public void log(int priority, String tag, Throwable t, boolean skipCustomerDataScan, String format, Object... args) {
            Log.e(tag, String.format(format, args) + " || " + t.toString());
        }

        @Override
        public void logAdal(Map<String, String> adalAuthEvent) {

        }

        @Override
        public void logCrash(Map<String, String> crashEvent) {
            Log.e("CRASHH", crashEvent.toString());
        }

        @Override
        public void pauseTransmission() {

        }

        @Override
        public void resumeTransmission() {

        }
    }

    // Very hacky .. Trying to get the code running mode !!
    Activity mActivity;
    Application mApplication;

    TeamsSdkSimLogger logger = new TeamsSdkSimLogger();

    TeamsSdkSimApplication (Activity activity, Application application) {
        mActivity = activity;
        mApplication = mApplication;
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
    public ILogger getLogger(@Nullable String userObjectId) {
        return logger;
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
