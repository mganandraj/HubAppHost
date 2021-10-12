/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk.models;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;

import com.microsoft.teams.utilities.java.StringUtils;

/**
 * Represents a resource managed by an SDK app.
 */
public final class SdkAppResource {
    private static final String RESOURCE_SCHEME = "res";
    private static final String FLUENT_SCHEME = "fluent";
    private static final String STRINGS_RESOURCE_HOST = "strings";
    private static final String IMAGES_RESOURCE_HOST = "images";
    public final String id;
    public final ResourceType type;

    public static SdkAppResource fromUri(@Nullable String resourceUri) {
        if (StringUtils.isEmpty(resourceUri)) {
            return null;
        }

        Uri parsedResourceUri = Uri.parse(resourceUri);
        String scheme = parsedResourceUri.getScheme();
        if (scheme == null) {
            return null;
        }

        try {
            switch (scheme) {
                case RESOURCE_SCHEME:
                    String resourceType = parsedResourceUri.getHost();
                    String resourceId = parsedResourceUri.getPath();

                    if (resourceId == null) {
                        throw new Exception("Invalid resource URI: " + parsedResourceUri.toString() + ". Path is null.");
                    }

                    if (resourceId.startsWith("/")) {
                        resourceId = resourceId.substring(1);
                    }

                    if (STRINGS_RESOURCE_HOST.equalsIgnoreCase(resourceType)) {
                        return new SdkAppResource(resourceId, ResourceType.STRING);
                    }

                    if (IMAGES_RESOURCE_HOST.equalsIgnoreCase(resourceType)) {
                        return new SdkAppResource(resourceId, ResourceType.IMAGE);
                    }
                    throw new Exception("Unknown resource type: " + resourceType);
                case FLUENT_SCHEME:
                    return new SdkAppResource(resourceUri.substring(FLUENT_SCHEME.length() + "://".length()), ResourceType.FLUENT_ICON);
                default:
                    throw new Exception("Unknown resource scheme " + parsedResourceUri.getScheme());
            }
        } catch (Exception ex) {
            //TODO: only alternative here is to add an ILogger member to this class.
            Log.e("SdkAppResource", String.format("Failed to parse resource uri %s.", resourceUri), ex);
            return null;
        }
    }

    private SdkAppResource(String id, ResourceType type) {
        this.id = id;
        this.type = type;
    }

    public enum ResourceType {
        STRING,
        IMAGE,
        FLUENT_ICON
    }
}