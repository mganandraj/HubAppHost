/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.services.diagnostics.telemetryschema;

import android.app.ActivityManager;
import android.content.Context;

import com.microsoft.teams.logger.ITelemetryLogger;
import com.microsoft.teams.logger.constants.SampledMetricPropKeys;
import com.microsoft.teams.telemetry.EventPriority;
import com.microsoft.teams.telemetry.EventProperties;

import java.util.Locale;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;

/**
 * Schema for a metric event.
 */
public class SampledMetricEvent extends TelemetryEvent {
    public static final String LOG_TAG = SampledMetricEvent.class.getSimpleName();
    public static final String EVENT_NAME = "sampledmetric";

    public String name;
    public String instanceName;
    public String units;
    public double value;
    public String objectClass;
    public String objectId;

    public static SampledMetricEvent createAvailableMemoryEvent(
            int priority,
            String tag,
            String instanceName,
            String objectClass,
            String objectId,
            @NonNull ActivityManager.MemoryInfo memoryInfo) {
        SampledMetricEvent event = new SampledMetricEvent();
        event.priority = priority;
        event.tag = tag;
        event.instanceName = instanceName;
        event.objectClass = objectClass;
        event.objectId = objectId;

        event.name = "Available Memory";
        event.value = memoryInfo.availMem;
        event.units = "bytes";

        return event;
    }

    public static SampledMetricEvent createHeapSizeEvent(
            int priority,
            String tag,
            String instanceName,
            String objectClass,
            String objectId,
            int heapSize) {
        SampledMetricEvent event = new SampledMetricEvent();
        event.priority = priority;
        event.tag = tag;
        event.instanceName = instanceName;
        event.objectClass = objectClass;
        event.objectId = objectId;

        event.name = "Heap Size";
        event.value = heapSize;
        event.units = "bytes";

        return event;
    }

    public static SampledMetricEvent createPerfEvent(int priority, String tag, String instanceName, String objectClass, long timeTaken) {
        SampledMetricEvent event = new SampledMetricEvent();
        event.priority = priority;
        event.tag = tag;
        event.instanceName = instanceName;
        event.objectClass = objectClass;
        event.objectId = "";

        event.name = "Perf";
        event.value = timeTaken;
        event.units = "ms";

        return event;
    }

    public static SampledMetricEvent createDataBaseSizeEvent(Context context, int priority, String tag, String instanceName, String objectClass, String objectId, double value) {
        SampledMetricEvent event = new SampledMetricEvent();
        event.priority = priority;
        event.tag = tag;
        event.instanceName = instanceName;
        event.objectClass = objectClass;
        event.objectId = objectId;
        event.name = "Database Size";
        event.value = value;
        event.units = "bytes";

        return event;
    }

    @Override
    @NonNull
    public String getLogTag() { return LOG_TAG; }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "Name: %s, InstanceName: %s, Value: %.2f %s, ObjectClass: %s, ObjectId: %s",
                name,
                instanceName,
                value,
                units,
                objectClass,
                objectId);
    }

    @Override
    @Nullable
    public EventProperties toEventProperties(@NonNull ITelemetryLogger telemetryLogger) {
        final Map<String, String> dictionary = new ArrayMap<>(2);
        dictionary.put(SampledMetricPropKeys.TAG, tag);
        dictionary.put(SampledMetricPropKeys.PRIORITY, "" + priority);

        final EventProperties properties = new EventProperties(SampledMetricEvent.EVENT_NAME, dictionary);
        properties.setPriority(EventPriority.LOW);
        return properties;
    }
}
