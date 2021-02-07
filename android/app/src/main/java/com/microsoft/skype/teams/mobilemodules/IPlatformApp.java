/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.mobilemodules;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.skype.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.skype.teams.sdk.rnbundle.ISdkBundleDownloadManager;
import com.microsoft.skype.teams.storage.dao.rnapps.RNAppsDao;
import com.microsoft.skype.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.skype.teams.storage.tables.AppDefinition;
import com.microsoft.teams.core.app.ITeamsApplication;

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