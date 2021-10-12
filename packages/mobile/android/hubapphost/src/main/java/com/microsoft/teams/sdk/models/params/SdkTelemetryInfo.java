/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk.models.params;

import android.os.Bundle;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
//import com.microsoft.teams.cortana.utils.OEMPropertiesUtil;
//import com.microsoft.teams.services.utilities.ApplicationUtilities;

import androidx.annotation.NonNull;

/**
 * Represents the params used to expose the currently signed in user to the SDK app.
 */
public final class SdkTelemetryInfo implements SdkAppWriteableParams {
    private static final String TEAMS_VERSION = "teamsVersion";
    private static final String SDK_VERSION = "sdkVersion";
    private static final String USER_RING = "userRing";
    private static final String DEVICE_ID = "deviceId";
    private static final String DEVICE_MANUFACTURER = "deviceManufacturer";
    private static final String DEVICE_MODEL = "deviceModel";
    private static final String DEVICE_NAME = "deviceName";
    private static final String DEVICE_TYPE = "deviceType";
    private static final String DEVICE_VERSION = "deviceVersion";

    private final String mTeamsVersion;
    private final String mSdkVersion;
    private final String mUserRing;
    private final String mDeviceId;
    private final String mDeviceManufacturer;
    private final String mDeviceModel;
    private final String mDeviceName;
    private final String mDeviceType;
    private final String mDeviceVersion;

    public SdkTelemetryInfo(@NonNull String sdkVersion, @NonNull String userRing, @NonNull String deviceId) {
        this.mTeamsVersion = "TeamsSdkSim"; // ApplicationUtilities.getAppVersionDisplayString();
        this.mSdkVersion = sdkVersion;
        this.mUserRing = userRing;
        this.mDeviceId = deviceId;
        this.mDeviceManufacturer = "TeamsSdkSim"; //OEMPropertiesUtil.getDeviceManufacturer();
        this.mDeviceModel = "TeamsSdkSim";
        this.mDeviceName = "TeamsSdkSim";
        this.mDeviceType = "TeamsSdkSim";
        this.mDeviceVersion = "TeamsSdkSim";
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(TEAMS_VERSION, mTeamsVersion);
        bundle.putString(SDK_VERSION, mSdkVersion);
        bundle.putString(USER_RING, mUserRing);
        bundle.putString(DEVICE_ID, mDeviceId);
        bundle.putString(DEVICE_MANUFACTURER, mDeviceManufacturer);
        bundle.putString(DEVICE_MODEL, mDeviceModel);
        bundle.putString(DEVICE_NAME, mDeviceName);
        bundle.putString(DEVICE_TYPE, mDeviceType);
        bundle.putString(DEVICE_VERSION, mDeviceVersion);
        return bundle;
    }

    @NonNull
    @Override
    public WritableMap toMap() {
        WritableMap map = new WritableNativeMap();
        map.putString(TEAMS_VERSION, mTeamsVersion);
        map.putString(SDK_VERSION, mSdkVersion);
        map.putString(USER_RING, mUserRing);
        map.putString(DEVICE_ID, mDeviceId);
        map.putString(DEVICE_MANUFACTURER, mDeviceManufacturer);
        map.putString(DEVICE_MODEL, mDeviceModel);
        map.putString(DEVICE_NAME, mDeviceName);
        map.putString(DEVICE_TYPE, mDeviceType);
        map.putString(DEVICE_VERSION, mDeviceVersion);
        return map;
    }
}
