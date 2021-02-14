/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.mobilemodules;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.teams.storage.tables.AppDefinition;

/**
 * Interface of a PlatformApp.
 */
public interface IPlatformApp {
    @NonNull
    String getAppId();

    @NonNull
    AppDefinition getAppDefinition();

    @Nullable
    // IMobileModule getMobileModule();
    public IMobileModule getMobileModule();

    void setAppDefinition(@NonNull AppDefinition appDefinition);
}