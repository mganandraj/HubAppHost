/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.mobilemodules;

import android.content.Context;

import com.microsoft.teams.logger.ILogger;
import com.microsoft.teams.storage.models.MobileModuleDefinition;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.microsoft.teams.core.preferences.IPreferences;

import androidx.annotation.NonNull;

/**
 * Abstract class for MobileModule
 */
public abstract class BaseMobileModule implements IMobileModule {
    protected final MobileModuleDefinition mModuleDefinition;
    protected final ITeamsApplication mTeamsApplication;
    protected final IPreferences mPreferences;
    protected final Context mContext;
    protected ILogger mLogger;

    BaseMobileModule(@NonNull MobileModuleDefinition moduleDefinition,
                     @NonNull Context context,
                     @NonNull ITeamsApplication teamsApplication,
                     @NonNull IPreferences preferences,
                     @NonNull ILogger logger) {
        mModuleDefinition = moduleDefinition;
        mTeamsApplication = teamsApplication;
        mContext = context;
        mPreferences = preferences;
        mLogger = logger;
    }

    @NonNull
    @Override
    public MobileModuleDefinition getModuleDefinition() {
        return mModuleDefinition;
    }
}
