/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */
package com.microsoft.teams.core.preferences;

import android.content.Context;
import android.content.SharedPreferences;

// import com.microsoft.skype.teams.storage.dao.userpreferences.UserPreferencesDao;
import com.microsoft.teams.core.models.UserPreferences;

import java.util.List;
import java.util.Set;

import androidx.annotation.NonNull;

/**
 * Interface for app level preferences
 */
public interface IUserPreferences {
    boolean getBooleanUserPref(@UserPreferences String key, String userId, boolean defaultValue);
    void putBooleanUserPref(@UserPreferences String key, boolean value, String userId);

    boolean getBooleanPersistedUserPref(@UserPreferences String key, Context context, String userId, boolean defaultValue);
    void putBooleanPersistedUserPref(@UserPreferences String key, Context context, boolean value, String userId);

    int getIntUserPref(@UserPreferences String key, String userId, int defaultValue);
    void putIntUserPref(@UserPreferences String key, int value, String userId);

    int getIntPersistedUserPref(@UserPreferences String key, Context context, String userId, int defaultValue);
    void putIntPersistedUserPref(@UserPreferences String key, Context context, int value, String userId);

    long getLongUserPref(@UserPreferences String key, String userId, long defaultValue);
    void putLongUserPref(@UserPreferences String key, long value, String userId);

    long getLongPersistedUserPref(@UserPreferences String key, Context context, String userId, long defaultValue);
    void putLongPersistedUserPref(@UserPreferences String key, Context context, long value, String userId);

    String getStringUserPref(@UserPreferences String key, String userId, String defaultValue);
    void putStringUserPref(@UserPreferences String key, String value, String userId);

    void putStringPersistedUserPref(@UserPreferences String key, Context context, String value, String userId);
    String getStringPersistedUserPref(@UserPreferences String key, Context context, String userId, String defaultValue);

    Set<String> getStringSetUserPref(@UserPreferences String key, String userId, Set<String> defaultValue);
    void putStringSetUserPref(@UserPreferences String key, Set<String> stringSetValue, String userId);

    void removeUserPref(@UserPreferences String key, String userId);
    void removePersistedPrefKey(@UserPreferences String key, Context context, String userId);
    void removePersistedPrefsForAllUsers(@UserPreferences String targetKey, Context context);
    void removeAllUserPrefsForUser(String userObjectId);

    SharedPreferences getPersistedUserSharedPreferences(Context context);
    boolean containsPersistedUserPref(Context context, String key, String userId);
    boolean containsUserPref(@UserPreferences String key, String userId);
    boolean containsPersistedUserPrefStartWith(Context context, String targetKey);
    // String getUserPreferenceForKey(@NonNull String key, @NonNull UserPreferencesDao userPreferencesDao);
    String getUserPreferenceForKey(@NonNull String key);
    List<String> getMigratedUserPreferences();

    // Extended User Preference API section
    boolean getBooleanUserPrefWithExtKey(@UserPreferences String key, String userId, String extKey, boolean defaultValue);
    void putBooleanUserPrefWithExtKey(@UserPreferences String key, boolean value, String userId, String extKey);

    int getIntUserPrefWithExtKey(@UserPreferences String key, String userId, String extKey, int defaultValue);
    void putIntUserPrefWithExtKey(@UserPreferences String key, int value, String userId, String extKey);

    long getLongUserPrefWithExtKey(@UserPreferences String key, String userId, String extKey, long defaultValue);
    void putLongUserPrefWithExtKey(@UserPreferences String key, long value, String userId, String extKey);

    String getStringUserPrefWithExtKey(@UserPreferences String key, String userId, String extKey, String defaultValue);
    void putStringUserPrefWithExtKey(@UserPreferences String key, String value, String userId, String extKey);
}
