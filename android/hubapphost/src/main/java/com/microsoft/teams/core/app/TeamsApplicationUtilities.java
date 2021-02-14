/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.core.app;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import com.microsoft.teams.utilities.java.UtilityInstantiationException;

/** Utility class to provide static helper methods for {@link ITeamsApplication}. */
public final class TeamsApplicationUtilities {

    @VisibleForTesting
    private static ITeamsApplication sTestTeamsApplication = null;

    @NonNull
    public static ITeamsApplication getTeamsApplication(@NonNull Context context) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext instanceof ITeamsApplication) {
            return sTestTeamsApplication != null ? sTestTeamsApplication : (ITeamsApplication) applicationContext;
        } else {
            throw new IllegalStateException("Application must implement ITeamsApplication.");
        }
    }

    @VisibleForTesting
    public static void setTestTeamsApplication(@NonNull ITeamsApplication teamsApplication) {
        sTestTeamsApplication = teamsApplication;
    }

    private TeamsApplicationUtilities() {
        throw new UtilityInstantiationException();
    }
}
