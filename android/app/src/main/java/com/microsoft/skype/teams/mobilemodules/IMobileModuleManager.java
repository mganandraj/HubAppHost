/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.mobilemodules;

import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//import com.microsoft.teams.core.data.extensions.IActivityFeedExtension;
//import com.microsoft.teams.core.data.extensions.IContactCardExtension;
//import com.microsoft.teams.core.data.extensions.IFreDataExtension;

import java.util.List;

import bolts.Task;

/**
 * Manages the mobile modules for a user.
 */
public interface IMobileModuleManager {

    @Nullable
    com.microsoft.skype.teams.mobilemodules.IMobileModule getMobileModule(@NonNull String appId);

//    @NonNull
//    List<IContactCardExtension> getContactCardExtensions();
//
//    @NonNull
//    List<IFreDataExtension> getFreDataExtensions();
//
//    @NonNull
//    List<IActivityFeedExtension> getActivityFeedExtensions();

    @NonNull
    Task<Void> destroyAll(boolean clearStorage);

    @Nullable
    Intent resolveMobileModuleForDeepLink(@NonNull Uri deepLink);
}
