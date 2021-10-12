/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Defines a view module in an SDK app
 */
public class SdkAppViewConfiguration {
    /**
     * Name of the view
     */
    public final String name;

    /**
     * Default title for the title.
     */
    public final SdkAppResource title;

    /**
     * Name of the component to be rendered.
     */
    public final String componentName;

    /**
     * Default parameters to pass to the component.
     */
    public final String componentParams;

    /**
     * Initializes a new instance of SdkAppViewConfiguration class with the specified values.
     */
    public SdkAppViewConfiguration(@NonNull String name, @NonNull SdkAppResource title, @NonNull String componentName, @Nullable String componentParams) {
        this.name = name;
        this.title = title;
        this.componentName = componentName;
        this.componentParams = componentParams;
    }

    @Override
    @NonNull
    public String toString() {
        return "SdkAppViewConfiguration{"
                + "name='" + name + '\''
                + ", componentName='" + componentName + '\''
                + ", componentParams='" + componentParams + '\''
                + '}';
    }
}
