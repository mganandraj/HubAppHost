/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk.models.params;

import android.os.Bundle;
import androidx.annotation.NonNull;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;

/**
 * Represents params needed to initialize an SDK app.
 */
public class SdkAppInitializationParams implements SdkAppWriteableParams {
    private static final String LOCALE_KEY = "appLocale";
    private static final String CURRENT_USER_KEY = "currentUser";
    private static final String APP_INSTANCE_ID = "appInstanceId";
    private static final String APP_THEME_KEY = "appTheme";
    private static final String TELEMETRY_INFO = "telemetryInfo";

    public final String appLocale;
    public final SdkAppUserParams currentUser;
    public final String appInstanceId;
    public final String appTheme;
    public final SdkTelemetryInfo telemetryInfo;

    public SdkAppInitializationParams(@NonNull String appLocale, @NonNull SdkAppUserParams currentUser, @NonNull String appInstanceId,
                                      @NonNull String appTheme, @NonNull SdkTelemetryInfo telemetryInfo) {
        this.appLocale = appLocale;
        this.currentUser = currentUser;
        this.appInstanceId = appInstanceId;
        this.appTheme = appTheme;
        this.telemetryInfo = telemetryInfo;
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(LOCALE_KEY, appLocale);
        bundle.putBundle(CURRENT_USER_KEY, currentUser.toBundle());
        bundle.putString(APP_INSTANCE_ID, appInstanceId);
        bundle.putString(APP_THEME_KEY, appTheme);
        bundle.putBundle(TELEMETRY_INFO, telemetryInfo.toBundle());
        return bundle;
    }

    @NonNull
    @Override
    public WritableMap toMap() {
        WritableMap map = new WritableNativeMap();
        map.putString(LOCALE_KEY, appLocale);
        map.putMap(CURRENT_USER_KEY, currentUser.toMap());
        map.putString(APP_INSTANCE_ID, appInstanceId);
        map.putString(APP_THEME_KEY, appTheme);
        map.putMap(TELEMETRY_INFO, telemetryInfo.toMap());
        return map;
    }
}
