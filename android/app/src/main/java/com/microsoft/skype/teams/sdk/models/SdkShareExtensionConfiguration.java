/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk.models;

import androidx.annotation.NonNull;

import com.microsoft.teams.core.models.share.ShareTarget;

import java.util.Collections;
import java.util.Set;

/**
 * Represents the sdk app configuration for share extension.
 */
public class SdkShareExtensionConfiguration {

    /**
     * Share targets exposed by the SDK app.
     */
    public final Set<ShareTarget> shareTargets;

    public SdkShareExtensionConfiguration(@NonNull Set<ShareTarget> shareTargets) {
        this.shareTargets = Collections.unmodifiableSet(shareTargets);
    }
}
