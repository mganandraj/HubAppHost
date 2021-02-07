package com.teamssdksim;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.skype.teams.storage.IExperimentationManager;

public class TeamsSdkSimExperimentationManager implements IExperimentationManager {
    @Nullable
    @Override
    public String[] getEcsSettingAsStringArray(@NonNull String teamName, @NonNull String experimentName, @Nullable String[] defaultValue) {
        return new String[0];
    }

    @Nullable
    @Override
    public String[] getEcsSettingAsStringArray(String configName, String[] defaultValue) {
        return new String[0];
    }

    @Override
    public String getRingInfo() {
        return null;
    }

    @Override
    public String[] getListOfRNAppForPreInit() {
        return new String[0];
    }

    @Override
    public int getRetryCountForCodepushBundleDownload() {
        return 0;
    }

    @Override
    public int getCodePushUpdateCheckBackoffTimeInMinutes() {
        return 0;
    }

    @Override
    public boolean isStepTelemetryEnabled() {
        return false;
    }

    @Override
    public String getReactNativeAppDeploymentKey(@NonNull String appId) {
        return null;
    }

    @Override
    public boolean shouldLogExperimentIds() {
        return false;
    }

    @Override
    public String getAppInfoExperimentationIds() {
        return null;
    }
}