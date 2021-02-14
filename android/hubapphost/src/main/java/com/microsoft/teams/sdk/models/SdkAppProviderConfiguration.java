/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk.models;

import androidx.annotation.NonNull;

/**
 * Defines a provider module in an SDK app
 */
public class SdkAppProviderConfiguration {
    /**
     * Name of the runnable to be run for the provider.
     */
    public final String runnableName;

    public SdkAppProviderConfiguration(@NonNull String runnableName) {
        this.runnableName = runnableName;
    }
}
