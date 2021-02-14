package com.microsoft.teams.sdk.models.params;

import android.os.Bundle;
import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

/**
 * Response class of SdkMessageModule.java
 */
public class SdkMessageModuleResponseParams implements SdkAppWriteableParams {

    public static final String STATUS_CODE_TAG = "statusCode";
    public static final String MESSAGE_ID_TAG = "messageID";

    public static final int MESSAGE_MODULE_SUCCESS = 200;
    public static final int MESSAGE_MODULE_THREAD_ID_NOT_FOUND = 404;
    public static final int MESSAGE_MODULE_THREAD_ID_NOT_VALID = 401;
    public static final int MESSAGE_MODULE_MESSAGE_TYPE_MISSING = 402;
    public static final int MESSAGE_MODULE_UPLOAD_FAILED = 500;
    public static final int MESSAGE_MODULE_CREATE_MESSAGE_FAILED = 501;

    private int mStatusCode;
    private String mMessageId;

    public SdkMessageModuleResponseParams(int statusCode, String messageId) {
        mStatusCode = statusCode;
        mMessageId = messageId;
    }

    public int getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(int statusCode) {
        mStatusCode = statusCode;
    }

    public String getMessageId() {
        return mMessageId;
    }

    public void setMessageId(String messageId) {
        mMessageId = messageId;
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        return Arguments.toBundle(toMap());
    }

    @NonNull
    @Override
    public WritableMap toMap() {
        WritableMap map = Arguments.createMap();
        map.putInt(STATUS_CODE_TAG, mStatusCode);
        map.putString(MESSAGE_ID_TAG, mMessageId);
        return map;
    }
}
