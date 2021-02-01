/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk.models.params;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.microsoft.skype.teams.sdk.react.ReadableMapUtilities;
import com.microsoft.skype.teams.storage.tables.User;
import com.microsoft.skype.teams.utilities.java.StringUtils;
import com.microsoft.teams.core.models.ContactProfile;

/**
 * Represents contact parameters from SDK apps.
 */
public final class SdkAppContactParams extends ContactProfile implements SdkAppWriteableParams {
    private static final String OBJECT_KEY_ID = "id";
    private static final String OBJECT_KEY_CONTACT_ID = "contactId";
    private static final String OBJECT_KEY_NAME = "name";
    private static final String OBJECT_KEY_TYPE = "type";
    private static final String OBJECT_KEY_DESCRIPTION = "description";
    private static final String OBJECT_KEY_EMAIL = "email";
    private static final String OBJECT_KEY_PHONE_NUMBER = "phoneNumber";
    private static final String OBJECT_KEY_AVATAR_URL = "avatarUrl";

    @Nullable
    public static SdkAppContactParams fromReactNativeMap(@Nullable ReadableMap map) {
        if (map == null) {
            return null;
        }

        String id = ReadableMapUtilities.getString(map, OBJECT_KEY_ID);
        String contactId = ReadableMapUtilities.getString(map, OBJECT_KEY_CONTACT_ID);
        String name = ReadableMapUtilities.getString(map, OBJECT_KEY_NAME);
        String type = ReadableMapUtilities.getString(map, OBJECT_KEY_TYPE);
        String description = ReadableMapUtilities.getString(map, OBJECT_KEY_DESCRIPTION);
        String phoneNumber = ReadableMapUtilities.getString(map, OBJECT_KEY_PHONE_NUMBER);
        String email = ReadableMapUtilities.getString(map, OBJECT_KEY_EMAIL);
        String avatarUrl = ReadableMapUtilities.getString(map, OBJECT_KEY_AVATAR_URL);

        if ((StringUtils.isEmpty(id) && StringUtils.isEmpty(contactId)) || StringUtils.isEmpty(name) || StringUtils.isEmpty(type)) {
            return null;
        }

        return new SdkAppContactParams(id, contactId, name, type, description, phoneNumber, email, avatarUrl);
    }

    public static SdkAppContactParams fromUser(@Nullable User user) {
        if (user == null) {
            return null;
        }

        return new SdkAppContactParams(user.mri, null, user.displayName, user.type, user.description, user.telephoneNumber, user.email, user.profileImageString);
    }

    public SdkAppContactParams(@NonNull String id, @Nullable String name, @NonNull String type) {
        this(id, null, name, type, null, null, null, null);
    }

    public SdkAppContactParams(@NonNull String id, @Nullable String name, @NonNull String type, @Nullable String phoneNumber) {
        this(id, null, name, type, null, phoneNumber, null, null);
    }

    private SdkAppContactParams(@Nullable String id, @Nullable String contactId, @Nullable String name, @NonNull String type,
                                @Nullable String description, @Nullable String phoneNumber,
                                @Nullable String email, @Nullable String avatarUrl) {
        super(id, contactId, name, type, description, phoneNumber, email, avatarUrl);
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(OBJECT_KEY_ID, id);
        bundle.putString(OBJECT_KEY_CONTACT_ID, contactId);
        bundle.putString(OBJECT_KEY_NAME, name);
        bundle.putString(OBJECT_KEY_TYPE, type);
        bundle.putString(OBJECT_KEY_DESCRIPTION, description);
        bundle.putString(OBJECT_KEY_EMAIL, email);
        bundle.putString(OBJECT_KEY_PHONE_NUMBER, phoneNumber);
        bundle.putString(OBJECT_KEY_AVATAR_URL, avatarUrl);
        return bundle;
    }

    @NonNull
    @Override
    public WritableMap toMap() {
        WritableMap map = new WritableNativeMap();
        map.putString(OBJECT_KEY_ID, id);
        map.putString(OBJECT_KEY_CONTACT_ID, contactId);
        map.putString(OBJECT_KEY_NAME, name);
        map.putString(OBJECT_KEY_TYPE, type);
        map.putString(OBJECT_KEY_DESCRIPTION, description);
        map.putString(OBJECT_KEY_EMAIL, email);
        map.putString(OBJECT_KEY_PHONE_NUMBER, phoneNumber);
        map.putString(OBJECT_KEY_AVATAR_URL, avatarUrl);
        return map;
    }
}
