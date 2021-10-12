package com.microsoft.teams.sdk.models.params;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableMap;
import com.microsoft.teams.sdk.react.ReadableMapUtilities;

/**
 * Represents a single option of an action sheet.
 */
public final class SdkActionSheetOptionParams {
    /**
     * An SDK image resource URI. The image referred to
     * by this URI will be used as an icon for the
     * action sheet option.
     */
    @NonNull
    public final String icon;

    /**
     * Label text for an action sheet option.
     */
    @NonNull
    public final String label;

    /**
     * A unique identifier for an action sheet option. This
     * is the id that will be passed to the React Native
     * callback when the user selects an option from an
     * action sheet.
     */
    @NonNull
    public final String id;

    @Nullable
    public static SdkActionSheetOptionParams fromReactNativeMap(@Nullable ReadableMap rnMap) {
        if (rnMap == null) {
            return null;
        }

        String icon = ReadableMapUtilities.getString(rnMap, "icon");
        String label = ReadableMapUtilities.getString(rnMap, "label");
        String id = ReadableMapUtilities.getString(rnMap, "id");

        if (icon == null || label == null || id == null)
        {
            return null;
        }

        return new SdkActionSheetOptionParams(icon, label, id);
    }

    private SdkActionSheetOptionParams(@NonNull String icon, @NonNull String label, @NonNull String id) {
        this.icon = icon;
        this.label = label;
        this.id = id;
    }
}