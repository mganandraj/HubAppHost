/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.services.diagnostics.telemetryschema;

import com.microsoft.teams.logger.ITelemetryLogger;
import com.microsoft.teams.logger.constants.FailurePropKeys;
import com.microsoft.teams.telemetry.EventPriority;
import com.microsoft.teams.telemetry.EventProperties;

import java.util.Map;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;

/**
 * Schema for failure event.
 */
public class FailureEvent extends TelemetryEvent {
    public static final String LOG_TAG = FailureEvent.class.getSimpleName();
    public static final String EVENT_NAME = "failure";

    private static final String EXCEPTION_CATEGORY = "Exception";

    public String id;
    public String category;
    public String detail;
    public String signature;

    public static FailureEvent createExceptionFailureEvent(String tag, String signature, String detail, int priority) {
        FailureEvent event = new FailureEvent();
        event.id = UUID.randomUUID().toString();
        event.tag = tag;
        event.category = EXCEPTION_CATEGORY;
        event.signature = signature;
        event.detail = detail;
        event.priority = priority;

        return event;
    }

    @Override
    @NonNull
    public String getLogTag() { return LOG_TAG; }

    public String toString() {
        String msg = String.format("%s %s %s %s",
                id,
                category,
                detail,
                signature);
        return msg;
    }

    @Override
    @Nullable
    public EventProperties toEventProperties(@NonNull ITelemetryLogger telemetryLogger) {
        final Map<String, String> dictionary = new ArrayMap<>(2);
        dictionary.put(FailurePropKeys.TAG, tag);
        dictionary.put(FailurePropKeys.PRIORITY, "" + priority);

        final EventProperties properties = new EventProperties(FailureEvent.EVENT_NAME, dictionary);
        properties.setPriority(EventPriority.HIGH);
        return properties;
    }
}
