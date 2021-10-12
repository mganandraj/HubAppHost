/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.services.diagnostics.telemetryschema;

import android.util.Log;

import com.microsoft.teams.logger.ITelemetryLogger;
import com.microsoft.teams.telemetry.EventProperties;

import java.io.Serializable;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Abstract implementation of a telemetry event.
 */
public abstract class TelemetryEvent implements Serializable {
    protected static final String APP_FOREGROUND = "App_Foreground";
    protected static final String APP_BACKGROUND = "App_Background";
    protected static final String INSTRUMENTATION_SOURCE_KEY = "InstrumentationSource";
    protected static final String SCRUBBED_MRI = "Scrubbed";

    private final long mStartTimeMillis;
    public String tag;
    public int priority;

    protected boolean mPolluteCustomerLogs;

    public TelemetryEvent() {
        mStartTimeMillis = System.currentTimeMillis();
    }

    @NonNull
    public String getLogTag() { return null; }

    public long getStartTime() {
        return mStartTimeMillis;
    }

    public long calculateLatencyFromNow() {
        return calculateLatency(System.currentTimeMillis());
    }

    public long calculateLatency(long endTimeMillis) {
        if (endTimeMillis < mStartTimeMillis) {
            Log.e(getClass().getSimpleName(), String.format(
                    Locale.ENGLISH,
                    "End time(%d) cannot be earlier than start time(%d).",
                    endTimeMillis,
                    mStartTimeMillis));
            return -1;
        }

        return (endTimeMillis - mStartTimeMillis);
    }

    public boolean shouldPolluteCustomerLogs() {
        return mPolluteCustomerLogs;
    }

    @Nullable
    public EventProperties toEventProperties(@NonNull ITelemetryLogger telemetryLogger) {
        return null;
    }
}
