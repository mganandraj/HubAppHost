/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.mobilemodules;

import com.microsoft.teams.sdk.SdkApplicationContext;
import com.microsoft.teams.storage.models.MobileModuleDefinition;
//import com.microsoft.teams.core.data.extensions.IActivityFeedExtension;
//import com.microsoft.teams.core.views.fragments.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import bolts.Task;

/**
 * Interface of a mobile module.
 */
public interface IMobileModule {

    boolean isEnabled();

    @NonNull
    MobileModuleDefinition getModuleDefinition();

//    @NonNull
//    BaseFragment getFragment(@Nullable Object params);

    @NonNull
    Task<Void> syncModule(@NonNull String scenarioTag);

    @NonNull
    Task<Void> destroyModule(boolean clearStorage);

//    @Nullable
//    IActivityFeedExtension getActivityFeedExtension();

    @Nullable
    SdkApplicationContext getSdkApplicationContext();

    @Nullable
    String getPackageId();

//    @Nullable
//    Intent resolveDeepLink(@NonNull Uri deepLink);
}