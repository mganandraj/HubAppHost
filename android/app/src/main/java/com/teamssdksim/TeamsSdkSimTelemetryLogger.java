package com.teamssdksim;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.skype.teams.logger.ITelemetryLogger;
import com.microsoft.skype.teams.services.diagnostics.telemetryschema.FailureEvent;
import com.microsoft.skype.teams.services.diagnostics.telemetryschema.SampledMetricEvent;
import com.microsoft.skype.teams.services.diagnostics.telemetryschema.TelemetryEvent;
import com.microsoft.skype.teams.storage.IExperimentationManager;
import com.microsoft.teams.telemetry.AppLifecycleState;
import com.microsoft.teams.telemetry.EventProperties;

import javax.inject.Inject;

public class TeamsSdkSimTelemetryLogger implements ITelemetryLogger {

    @Inject public TeamsSdkSimTelemetryLogger() {}

    @Nullable
    @Override
    public String getSessionId() {
        return null;
    }

    @NonNull
    @Override
    public String getNetworkStatus() {
        return null;
    }

    @NonNull
    @Override
    public String getNetworkQuality() {
        return null;
    }

    @NonNull
    @Override
    public String getNetworkType() {
        return null;
    }

    @Override
    public String getDeviceNetworkSpeed() {
        return null;
    }

    @NonNull
    @Override
    public String getUserTypeForTelemetry() {
        return null;
    }

    @Override
    public boolean shouldAllowLoggingMri() {
        return false;
    }

    @NonNull
    @Override
    public IExperimentationManager getExperimentationManager() {
        return null;
    }

    @Nullable
    @Override
    public String getConversationIdToLog(@Nullable String inputString) {
        return null;
    }

    @Override
    public void setChannelContext(@NonNull String teamId, @NonNull String channelId) {

    }

    @Override
    public void setChannelContext(@NonNull String teamId, @NonNull String channelId, boolean isV2Thread) {

    }

    @Override
    public void setTelemetryInformation(@NonNull String tenantId, @NonNull String userObjectId, @NonNull String userPrincipalName, @NonNull String appLanguage, @NonNull String region, @NonNull String cid) {

    }

    @Override
    public void log(@NonNull TelemetryEvent event) {

    }

    @Override
    public void log(@NonNull FailureEvent event) {

    }

    @Override
    public void log(@NonNull SampledMetricEvent event) {

    }

    @Override
    public void logAppLifecycle(AppLifecycleState lifecycleState, EventProperties eventProperties) {

    }

    @Override
    public void debugPrintEvents(@NonNull String tag, @NonNull TelemetryEvent event, boolean polluteLogcat) {

    }

    @Override
    public void setCallContext(@Nullable String callId, @Nullable String meetingRole) {

    }
}
