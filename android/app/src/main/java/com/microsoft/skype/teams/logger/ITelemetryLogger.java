/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.logger;

import com.microsoft.skype.teams.services.diagnostics.telemetryschema.FailureEvent;
import com.microsoft.skype.teams.services.diagnostics.telemetryschema.SampledMetricEvent;
import com.microsoft.skype.teams.services.diagnostics.telemetryschema.TelemetryEvent;
import com.microsoft.skype.teams.storage.IExperimentationManager;
import com.microsoft.teams.telemetry.AppLifecycleState;
import com.microsoft.teams.telemetry.EventProperties;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Interface for the service that logs messages related with telemetry/aria.
 */
public interface ITelemetryLogger {
    @Nullable
    String getSessionId();

    @NonNull
    String getNetworkStatus();

    @NonNull
    String getNetworkQuality();

    @NonNull
    String getNetworkType();

    String getDeviceNetworkSpeed();

    @NonNull
    String getUserTypeForTelemetry();

    boolean shouldAllowLoggingMri();

    @NonNull
    IExperimentationManager getExperimentationManager();

    @Nullable
    String getConversationIdToLog(@Nullable String inputString);

    /**
     * Changes the channel context for all the events sent from this point onwards
     *
     * @param teamId the teamId to be used as context
     * @param channelId the channelId to be used as context
     */
    void setChannelContext(@NonNull String teamId, @NonNull String channelId);

    /**
     * Changes the channel context for all the events sent from this point onwards
     *
     * @param teamId the teamId to be used as context
     * @param channelId the channelId to be used as context
     * @param isV2Thread true if it is v2 thread else false
     */
    void setChannelContext(@NonNull String teamId, @NonNull String channelId, boolean isV2Thread);

    /**
     * Changes the telemetry information for all of the events sent from this point forward.
     *
     * @param tenantId - tenant id of the current user account.
     * @param region - region of the current user account.
     * @param userObjectId - userObjecId of the current user account.
     * @param appLanguage - appLanguage of the current user account.
     * @param cid - cid of the current user account.
     */
    void setTelemetryInformation(@NonNull String tenantId,
                                 @NonNull String userObjectId,
                                 @NonNull String userPrincipalName,
                                 @NonNull String appLanguage,
                                 @NonNull String region,
                                 @NonNull String cid);

    /**
     * Logs a telemetry event
     *
     * @param event - a telemetry event.
     */
    void log(@NonNull TelemetryEvent event);

    /**
     * Logs a failure health event.
     *
     * @param event - a failure health event.
     */
    void log(@NonNull FailureEvent event);

    /**
     * Logs a sampled metric health event.
     *
     * @param event - a sampled metric health event.
     */
    void log(@NonNull SampledMetricEvent event);

    /**
     * Logs an app lifecycle event
     *
     * @param lifecycleState - current app lifecycle state
     * @param eventProperties - extra event properties
     */
    void logAppLifecycle(AppLifecycleState lifecycleState, EventProperties eventProperties);

    /**
     * Logs a TelemetryEventBase for debugging and automation.
     *
     * @param tag - log tag
     * @param event - event to log
     * @param polluteLogcat - whe
     */
    void debugPrintEvents(@NonNull String tag, @NonNull TelemetryEvent event, boolean polluteLogcat);

    void setCallContext(@Nullable String callId, @Nullable String meetingRole);
}