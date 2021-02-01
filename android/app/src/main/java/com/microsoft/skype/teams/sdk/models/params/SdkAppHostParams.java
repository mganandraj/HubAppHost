package com.microsoft.skype.teams.sdk.models.params;

import android.os.Bundle;
import androidx.annotation.NonNull;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;

/**
 * Represents the context for an RN module host.
 */
public class SdkAppHostParams implements SdkAppWriteableParams {
    private static final String HOST_INSTANCE_ID_KEY = "hostInstanceId";

    private String mHostInstanceId;

    public SdkAppHostParams(String hostInstanceId) {
        this.mHostInstanceId = hostInstanceId;
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(HOST_INSTANCE_ID_KEY, this.mHostInstanceId);
        return bundle;
    }

    @NonNull
    @Override
    public WritableMap toMap() {
        WritableMap map = new WritableNativeMap();
        map.putString(HOST_INSTANCE_ID_KEY, this.mHostInstanceId);
        return map;
    }
}
