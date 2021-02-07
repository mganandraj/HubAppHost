/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.core.models;

import androidx.annotation.NonNull;

/**
 * Represents a build configuration.
 */
public final class BuildConfiguration {
    public final String applicationId;
    public final int versionCode;
    public final String versionName;
    public final boolean debug;
    public final String flavor;
    public final String buildType;
    public boolean automation;
    public boolean isConvergedApp;
    public final String branchName;

    public BuildConfiguration(@NonNull String applicationId,
                              int versionCode,
                              @NonNull String versionName,
                              boolean debug,
                              @NonNull String flavor,
                              @NonNull String buildType,
                              boolean isConvergedApp,
                              @NonNull String branchName) {
        this.applicationId = applicationId;
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.debug = debug;
        this.flavor = flavor;
        this.buildType = buildType;
        this.isConvergedApp = isConvergedApp;
        this.branchName = branchName;
        // This flag is used only when automated tests are run.
        // We don't want anyone to turn on this flag accidentally in constructor.
        // This can be turned only by calling setAutomationOn() method.
        // Hence setting this value to false by default.
        this.automation = false;
    }
}
