/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk;

import androidx.annotation.NonNull;

import com.microsoft.teams.storage.tables.AppDefinition;
import com.microsoft.teams.storage.tables.RNBundle;
// import com.microsoft.teams.tabs.ITabProvider;

// import bolts.Task;
// import bolts.Task;

/**
 * Interface to manage Runner App
 */
public interface ISdkRunnerAppManager {

    @NonNull
    AppDefinition getAppDefinition();

    /**
     * Gets the details of the app.
     */
    @NonNull
    RNBundle getRNBundle();

    /**
     * Syncs the runner app.
     */
    @NonNull
    //Task<Void> syncRunnerApp(@NonNull ITabProvider tabProvider);
    void syncRunnerApp();
}
