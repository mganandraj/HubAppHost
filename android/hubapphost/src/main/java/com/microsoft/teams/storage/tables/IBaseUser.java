/**
 * Copyright (C) Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.storage.tables;

import android.content.Context;

import androidx.annotation.NonNull;

/**
 * Interface for a user.
 */
public interface IBaseUser {
    String getObjectId();

    String getMri();

    String getDisplayName();

    String getUserPrincipalName();

    String getProfileImageString(@NonNull Context context);

    String getImageUri(@NonNull Context context);

    @NonNull
    String getType();
}