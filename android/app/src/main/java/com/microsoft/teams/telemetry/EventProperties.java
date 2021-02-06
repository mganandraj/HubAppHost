/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.telemetry;

import android.util.Log;

import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Defines event properties of the event
 */
public class EventProperties {
    private static final String LOG_TAG = "EventProperties";

    private String mName;
    private Map<String, String> mProperties;
    private Map<String, Double> mPropertiesDouble;
    private Map<String, Long> mPropertiesLong;
    private Map<String, Boolean> mPropertiesBoolean;
    private Map<String, Date> mPropertiesDate;
    private Map<String, UUID> mPropertiesUUID;
    private Map<String, PiiKind> mPii;
    private EventPriority mPriority;

    public EventProperties(@NonNull String name) {
        mName = name;
    }

    public EventProperties(@NonNull String name, @NonNull Map<String, String> properties) {
        this(name);
        mProperties = properties;
    }

    public EventProperties(@NonNull String name,
                           @Nullable Map<String, String> properties,
                           @Nullable Map<String, Double> propertiesDouble,
                           @Nullable Map<String, Long> propertiesLong,
                           @Nullable Map<String, Boolean> propertiesBoolean,
                           @Nullable Map<String, Date> propertiesDate,
                           @Nullable Map<String, UUID> propertiesUUID) {
        this(name);
        mProperties = properties;
        mPropertiesDouble = propertiesDouble;
        mPropertiesLong = propertiesLong;
        mPropertiesBoolean = propertiesBoolean;
        mPropertiesDate = propertiesDate;
        mPropertiesUUID = propertiesUUID;
    }

    public void setProperty(@NonNull String key, @NonNull String value) {
        if (mProperties == null) {
            mProperties = new HashMap<>();
        }
        try {
            mProperties.put(key, value);
        } catch (ClassCastException ex) {
            Log.e(LOG_TAG, String.format("ClassCastException: Failed to set string property, key: %s, value: %s", key, value));
        } catch (ConcurrentModificationException ex) {
            Log.e(LOG_TAG, String.format("ConcurrentModificationException: Failed to set string property, key: %s, value: %s", key, value));
        }
    }

    public void setProperty(@NonNull String key, @NonNull String value, @NonNull PiiKind piiKind) {
        if (mProperties == null) {
            mProperties = new HashMap<>();
        }
        mProperties.put(key, value);

        if (mPii == null) {
            mPii = new HashMap<>();
        }
        mPii.put(key, piiKind);
    }

    public void setProperty(@NonNull String key, double value) {
        if (mPropertiesDouble == null) {
            mPropertiesDouble = new HashMap<>();
        }
        mPropertiesDouble.put(key, value);
    }

    public void setProperty(@NonNull String key, long value) {
        if (mPropertiesLong == null) {
            mPropertiesLong = new HashMap<>();
        }
        try {
            mPropertiesLong.put(key, value);
        } catch (ClassCastException ex) {
            Log.e(LOG_TAG, String.format("ClassCastException: Failed to set long property, key: %s, value: %s", key, value));
        } catch (ConcurrentModificationException ex) {
            Log.e(LOG_TAG, String.format("ConcurrentModificationException: Failed to set long property, key: %s, value: %s", key, value));
        }
    }

    public void setProperty(@NonNull String key, boolean value) {
        if (mPropertiesBoolean == null) {
            mPropertiesBoolean = new HashMap<>();
        }
        mPropertiesBoolean.put(key, value);
    }

    public void setProperty(@NonNull String key, @NonNull Date value) {
        if (mPropertiesDate == null) {
            mPropertiesDate = new HashMap<>();
        }
        mPropertiesDate.put(key, value);
    }

    public void setProperty(@NonNull String key, @NonNull UUID value) {
        if (mPropertiesUUID == null) {
            mPropertiesUUID = new HashMap<>();
        }
        mPropertiesUUID.put(key, value);
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public void setName(@NonNull String name) {
        mName = name;
    }

    @Nullable
    public EventPriority getPriority() {
        return mPriority;
    }

    public void setPriority(@NonNull EventPriority priority) {
        mPriority = priority;
    }

    @Nullable
    public Map<String, String> getProperties() {
        return mProperties;
    }

    @Nullable
    public Map<String, Double> getPropertiesDouble() {
        return mPropertiesDouble;
    }

    @Nullable
    public Map<String, Long> getPropertiesLong() {
        return mPropertiesLong;
    }

    @Nullable
    public Map<String, Boolean> getPropertiesBoolean() {
        return mPropertiesBoolean;
    }

    @Nullable
    public Map<String, Date> getPropertiesDate() {
        return mPropertiesDate;
    }

    @Nullable
    public Map<String, UUID> getPropertiesUUID() {
        return mPropertiesUUID;
    }

    @Nullable
    public Map<String, PiiKind> getPII() {
        return mPii;
    }
}
