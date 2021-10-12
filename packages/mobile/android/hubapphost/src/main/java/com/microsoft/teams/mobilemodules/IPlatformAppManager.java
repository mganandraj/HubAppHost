/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.mobilemodules;

// import com.microsoft.teams.injection.PlatformAppId;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Interface to manage platform app for a user
 */
public interface IPlatformAppManager {
    @Nullable
    IPlatformApp get(@NonNull /*@PlatformAppId */String appId);
}
