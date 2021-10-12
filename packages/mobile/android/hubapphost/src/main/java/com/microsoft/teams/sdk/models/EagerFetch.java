/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk.models;

import androidx.annotation.Nullable;

import java.util.List;

/**
 * Represents sdk resources which are required by app during boot.
 */
public class EagerFetch {
    /**
     * List of domains for which resource token needs to be fetched.
     */
    public final List<String> resourceToken;

    public EagerFetch(@Nullable List<String> resourceToken) {
        this.resourceToken = resourceToken;
    }

    @Nullable
    public List<String> getResourceToken() {
        return resourceToken;
    }
}
