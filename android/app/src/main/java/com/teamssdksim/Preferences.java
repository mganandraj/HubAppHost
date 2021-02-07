package com.teamssdksim;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.microsoft.teams.core.preferences.IPreferences;

import java.util.List;
import java.util.Set;

public class Preferences implements IPreferences {
    @Override
    public boolean getBooleanGlobalPref(String key, boolean defaultValue) {
        return false;
    }

    @Override
    public void putBooleanGlobalPref(String key, boolean value) {

    }

    @Override
    public int getIntGlobalPref(String key, int defaultValue) {
        return 0;
    }

    @Override
    public void putIntGlobalPref(String key, int value) {

    }

    @Override
    public long getLongGlobalPref(String key, long defaultValue) {
        return 0;
    }

    @Override
    public void putLongGlobalPref(String key, long value) {

    }

    @Override
    public String getStringGlobalPref(String key, String defaultValue) {
        return null;
    }

    @Override
    public void putStringGlobalPref(String key, String value) {

    }

    @Override
    public void putStringSetGlobalPref(String key, Set<String> value) {

    }

    @Override
    public Set<String> getStringSetGlobalPref(String key, Set<String> defaultValue) {
        return null;
    }

    @Override
    public void removeGlobalPref(String key) {

    }

    @Override
    public boolean containsGlobalPref(String key) {
        return false;
    }

    @Override
    public String getLaunchPreferenceKey(@NonNull String tabExtensionId) {
        return null;
    }

    @Override
    public boolean getBooleanUserPref(String key, String userId, boolean defaultValue) {
        return false;
    }

    @Override
    public void putBooleanUserPref(String key, boolean value, String userId) {

    }

    @Override
    public boolean getBooleanPersistedUserPref(String key, Context context, String userId, boolean defaultValue) {
        return false;
    }

    @Override
    public void putBooleanPersistedUserPref(String key, Context context, boolean value, String userId) {

    }

    @Override
    public int getIntUserPref(String key, String userId, int defaultValue) {
        return 0;
    }

    @Override
    public void putIntUserPref(String key, int value, String userId) {

    }

    @Override
    public int getIntPersistedUserPref(String key, Context context, String userId, int defaultValue) {
        return 0;
    }

    @Override
    public void putIntPersistedUserPref(String key, Context context, int value, String userId) {

    }

    @Override
    public long getLongUserPref(String key, String userId, long defaultValue) {
        return 0;
    }

    @Override
    public void putLongUserPref(String key, long value, String userId) {

    }

    @Override
    public long getLongPersistedUserPref(String key, Context context, String userId, long defaultValue) {
        return 0;
    }

    @Override
    public void putLongPersistedUserPref(String key, Context context, long value, String userId) {

    }

    @Override
    public String getStringUserPref(String key, String userId, String defaultValue) {
        return null;
    }

    @Override
    public void putStringUserPref(String key, String value, String userId) {

    }

    @Override
    public void putStringPersistedUserPref(String key, Context context, String value, String userId) {

    }

    @Override
    public String getStringPersistedUserPref(String key, Context context, String userId, String defaultValue) {
        return null;
    }

    @Override
    public Set<String> getStringSetUserPref(String key, String userId, Set<String> defaultValue) {
        return null;
    }

    @Override
    public void putStringSetUserPref(String key, Set<String> stringSetValue, String userId) {

    }

    @Override
    public void removeUserPref(String key, String userId) {

    }

    @Override
    public void removePersistedPrefKey(String key, Context context, String userId) {

    }

    @Override
    public void removePersistedPrefsForAllUsers(String targetKey, Context context) {

    }

    @Override
    public void removeAllUserPrefsForUser(String userObjectId) {

    }

    @Override
    public SharedPreferences getPersistedUserSharedPreferences(Context context) {
        return null;
    }

    @Override
    public boolean containsPersistedUserPref(Context context, String key, String userId) {
        return false;
    }

    @Override
    public boolean containsUserPref(String key, String userId) {
        return false;
    }

    @Override
    public boolean containsPersistedUserPrefStartWith(Context context, String targetKey) {
        return false;
    }

    @Override
    public String getUserPreferenceForKey(@NonNull String key) {
        return null;
    }

    @Override
    public List<String> getMigratedUserPreferences() {
        return null;
    }

    @Override
    public boolean getBooleanUserPrefWithExtKey(String key, String userId, String extKey, boolean defaultValue) {
        return false;
    }

    @Override
    public void putBooleanUserPrefWithExtKey(String key, boolean value, String userId, String extKey) {

    }

    @Override
    public int getIntUserPrefWithExtKey(String key, String userId, String extKey, int defaultValue) {
        return 0;
    }

    @Override
    public void putIntUserPrefWithExtKey(String key, int value, String userId, String extKey) {

    }

    @Override
    public long getLongUserPrefWithExtKey(String key, String userId, String extKey, long defaultValue) {
        return 0;
    }

    @Override
    public void putLongUserPrefWithExtKey(String key, long value, String userId, String extKey) {

    }

    @Override
    public String getStringUserPrefWithExtKey(String key, String userId, String extKey, String defaultValue) {
        return null;
    }

    @Override
    public void putStringUserPrefWithExtKey(String key, String value, String userId, String extKey) {

    }
}
