/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk.models.params;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.microsoft.teams.storage.tables.User;

/**
 * Represents a chat.
 */
public final class SdkAppChatParams implements SdkAppWriteableParams {
    private static final String CONVERSATION_ID_KEY = "conversationId";
    private static final String DISPLAY_NAME_KEY = "displayName";
    private static final String TYPE_KEY = "type";
    private static final String CONSUMER_GROUP_ID = "consumerGroupId";


    public final String conversationId;
    public final String displayName;
    public final String type;
    public final String consumerGroupId;


    public static SdkAppChatParams fromUser(@Nullable User user) {
        if (user == null) {
            return null;
        }

        return new SdkAppChatParams(user.mri, user.displayName, user.type, null);
    }

    public SdkAppChatParams(String conversationId, String displayName, String type, String consumerGroupId) {
        this.conversationId = conversationId;
        this.displayName = displayName;
        this.type = type;
        this.consumerGroupId = consumerGroupId;
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(CONVERSATION_ID_KEY, conversationId);
        bundle.putString(DISPLAY_NAME_KEY, displayName);
        bundle.putString(TYPE_KEY, type);
        bundle.putString(CONSUMER_GROUP_ID, consumerGroupId);
        return bundle;
    }

    @NonNull
    @Override
    public WritableMap toMap() {
        WritableMap map = new WritableNativeMap();
        map.putString(CONVERSATION_ID_KEY, conversationId);
        map.putString(DISPLAY_NAME_KEY, displayName);
        map.putString(TYPE_KEY, type);
        map.putString(CONSUMER_GROUP_ID, consumerGroupId);
        return map;
    }
}
