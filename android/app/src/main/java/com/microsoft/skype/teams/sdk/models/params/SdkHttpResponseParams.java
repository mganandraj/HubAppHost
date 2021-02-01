/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk.models.params;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.microsoft.skype.teams.utilities.java.ListUtils;

import java.util.List;
import java.util.Map;

import okhttp3.Headers;

/**
 * Represents an HTTP response.
 */
public class SdkHttpResponseParams implements SdkAppWriteableParams {
    public final int statusCode;
    public final String responseBody;
    public final Headers responseHeaders;

    public SdkHttpResponseParams(int statusCode, @Nullable String responseBody, @Nullable Headers headers) {
        this.statusCode = statusCode;
        this.responseBody = responseBody;
        this.responseHeaders = headers;
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("status", statusCode);
        bundle.putString("data", responseBody);

        Bundle headersBundle = new Bundle();
        if (responseHeaders != null) {
            for (Map.Entry<String, List<String>> header : responseHeaders.toMultimap().entrySet()) {
                String key = header.getKey();
                List<String> values = header.getValue();
                if (!ListUtils.isListNullOrEmpty(values)) {
                    // As of React Native 0.47.1, ArrayLists cannot be parsed to a WritableMap
                    headersBundle.putStringArray(key, values.toArray(new String[0]));
                }
            }
        }

        bundle.putBundle("headers", headersBundle);
        return bundle;
    }

    @NonNull
    @Override
    public WritableMap toMap() {
        return Arguments.fromBundle(toBundle());
    }
}
