/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Map;

/**
 * Represents an app build using the Teams Mobile SDK.
 */
public class SdkAppManifest {
    /**
     * Type of the module: quick action or UI
     */
    @ModuleType
    public String moduleType;

    /**
     * App version
     */
    public String version;

    /**
     * App capabilities
     */
    public SdkAppCapability[] capabilities;

    /**
     * All views configured by the app
     */
    public Map<String, SdkAppViewConfiguration> views;

    /**
     * Auth domains declared by the app
     */
    public List<String> authDomains;

    /**
     * The contacts extension configuration
     */
    public SdkContactsExtensionConfiguration contactExtensionConfiguration;

    /**
     * The share extension configuration
     */
    public SdkShareExtensionConfiguration shareExtensionConfiguration;

    /**
     * The version of the react native SDK this app uses.
     */
    public String targetReactNativeSdkVersion;

    /**
     * The minimum version of the react native SDK supported by app.
     */
    public String minReactNativeSdkVersion;

    /**
     * The version of the native SDK this app use
     */
    public int targetNativeSdkVersion;

    /**
     * The quick action configuration if the app is a quick action,
     * `null` otherwise.
     */
    public QuickActionConfig quickActionConfig;

    /**
     * This encapsulate the components which needs to be fetched before bridge initialisation.
     */
    public EagerFetch eagerFetch;

    /**
     * Gets the contacts extension configuration.
     */
    public SdkContactsExtensionConfiguration getContactExtensionConfiguration() {
        return contactExtensionConfiguration;
    }

    @Nullable
    public SdkShareExtensionConfiguration getShareExtensionConfiguration() {
        return shareExtensionConfiguration;
    }

    @StringDef({ModuleType.QUICK_ACTION, ModuleType.UI_MODULE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ModuleType {
        String QUICK_ACTION = "quickAction";
        String UI_MODULE = "uiModule";
    }

    @Override
    @NonNull
    public String toString() {
        return "SdkAppManifest{"
                + "moduleType='" + moduleType + '\''
                + ", version='" + version + '\''
                + ", views=" + views
                + ", targetReactNativeSdkVersion='" + targetReactNativeSdkVersion + '\''
                + ", minReactNativeSdkVersion='" + minReactNativeSdkVersion + '\''
                + ", quickActionConfig=" + quickActionConfig
                + '}';
    }
}
