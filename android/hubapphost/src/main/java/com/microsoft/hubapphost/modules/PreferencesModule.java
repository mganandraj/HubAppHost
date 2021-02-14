package com.microsoft.hubapphost.modules;

import com.microsoft.hubapphost.impls.Preferences;
import com.microsoft.teams.core.preferences.IPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PreferencesModule {

    @Singleton
    @Provides
    static IPreferences providePreferences() {
        return new Preferences();
    }
}
