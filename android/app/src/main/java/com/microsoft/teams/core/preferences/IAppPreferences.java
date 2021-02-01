/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */
package com.microsoft.teams.core.preferences;

import com.microsoft.teams.core.models.GlobalPreferences;

import java.util.Set;

import androidx.annotation.NonNull;

/**
 * Interface for app level preferences
 */
public interface IAppPreferences {
    boolean getBooleanGlobalPref(@GlobalPreferences String key, boolean defaultValue);
    void putBooleanGlobalPref(@GlobalPreferences String key, boolean value);

    int getIntGlobalPref(@GlobalPreferences String key, int defaultValue);
    void putIntGlobalPref(@GlobalPreferences String key, int value);

    long getLongGlobalPref(@GlobalPreferences String key, long defaultValue);
    void putLongGlobalPref(@GlobalPreferences String key, long value);

    String getStringGlobalPref(@GlobalPreferences String key, String defaultValue);
    void putStringGlobalPref(@GlobalPreferences String key, String value);

    void putStringSetGlobalPref(@GlobalPreferences String key, Set<String> value);
    Set<String> getStringSetGlobalPref(@GlobalPreferences String key, Set<String> defaultValue);

    void removeGlobalPref(@GlobalPreferences String key);

    boolean containsGlobalPref(@GlobalPreferences String key);

    String getLaunchPreferenceKey(@NonNull String tabExtensionId);
}
