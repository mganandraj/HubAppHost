package com.microsoft.skype.teams.sdk.models.params;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReadableMap;

/**
 * Represents a title dropdown item (set by an RN module).
 */
public final class SdkTitleDropdownItem {
    @NonNull
    public final String id;

    @NonNull
    public final String title;

    public static SdkTitleDropdownItem fromReadableMap(ReadableMap map) {
        String id = map.getString("id");
        String title = map.getString("title");
        return new SdkTitleDropdownItem(id, title);
    }

    private SdkTitleDropdownItem(@NonNull String id, @NonNull String title) {
        this.id = id;
        this.title = title;
    }
}
