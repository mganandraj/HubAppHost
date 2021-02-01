/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.core.models.share;

import androidx.annotation.NonNull;

import com.microsoft.teams.core.models.ShareContentType;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a share target.
 */
public class ShareTarget implements Serializable {

    /**
     * Unique human readable string literal that identifies this specific target.
     * Used for telemetry, logging, and equality checks.
     */
    public String targetId;

    /**
     * ID of module to open when the share target is selected.
     */
    public String moduleId;

    /**
     * Name of share target to display in share target picker.
     */
    public String displayName;

    /**
     * Path to icon to display in share target picker.
     */
    public String icon;

    /**
     * List of ShareContentType supported by the share target.
     */
    @ShareContentType
    public List<String> contentTypes;

    public ShareTarget(@NonNull String targetId, @NonNull String moduleId, @NonNull String displayName,
                       @NonNull @ShareContentType List<String> contentTypes) {
        this(targetId, moduleId, displayName, contentTypes, "");
    }

    public ShareTarget(@NonNull String targetId, @NonNull String moduleId, @NonNull String displayName,
                       @NonNull @ShareContentType List<String> contentTypes, @NonNull String icon) {
        this.targetId = targetId;
        this.moduleId = moduleId;
        this.displayName = displayName;
        this.contentTypes = contentTypes;
        this.icon = icon;
    }

    @Override
    public int hashCode() {
        return targetId.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ShareTarget)) {
            return false;
        }

        ShareTarget other = (ShareTarget) o;
        return other.targetId.equals(targetId);
    }
}
