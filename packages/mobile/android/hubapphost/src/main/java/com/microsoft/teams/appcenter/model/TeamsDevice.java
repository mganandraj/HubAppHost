package com.microsoft.teams.appcenter.model;

import androidx.annotation.NonNull;

import com.microsoft.appcenter.ingestion.models.Device;

/**
 * Model describing device properties
 */
public class TeamsDevice {
    private String appVersion;
    private String appBuild;
    private final String sdkName = "appcenter.android";
    private String sdkVersion;
    private String osName;
    private String osVersion;
    private String model;
    private String locale;
    private int timeZoneOffset;
    // TODO: this will all be removed after upgrading to new app center version as they support api to upload custom crashes
    private String appNamespace = "com.microsoft.teams.dev";

    TeamsDevice(@NonNull Device device) {
        appVersion = device.getAppVersion();
        appBuild = device.getAppBuild();
        sdkVersion = device.getSdkVersion();
        osName = device.getOsName();
        osVersion = device.getOsVersion();
        model = device.getModel();
        locale = device.getLocale();
        timeZoneOffset = device.getTimeZoneOffset();
    }
}
