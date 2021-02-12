package com.teamssdksim.modules;

import com.microsoft.teams.core.preferences.IPreferences;
import com.teamssdksim.Preferences;

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
