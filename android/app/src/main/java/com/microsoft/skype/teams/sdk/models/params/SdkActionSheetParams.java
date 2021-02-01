package com.microsoft.skype.teams.sdk.models.params;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.microsoft.skype.teams.sdk.react.ReadableMapUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an action sheet/bottom sheet.
 */
public final class SdkActionSheetParams {
    public static final int MAX_ALLOWED_OPTIONS = 10;

    /**
     * Title of the action sheet. Displayed on top of the action sheet.
     */
    @Nullable
    public final String title;

    /**
     * Message for the action sheet. Displayed just below the title similar
     * to a subtitle.
     */
    @Nullable
    public final String message;

    /**
     * List of options in the action sheet. If no options are provided, the
     * action sheet will not be displayed. If any of the options are invalid,
     * they will be ignored.
     *
     * NOTE: A maximum of 10 options are allowed.
     */
    @NonNull
    public final List<SdkActionSheetOptionParams> options;

    @Nullable
    public static SdkActionSheetParams fromReactNativeMap(@Nullable ReadableMap rnMap) {
        if (rnMap == null) {
            return null;
        }

        String title = ReadableMapUtilities.getString(rnMap, "title");
        String message = ReadableMapUtilities.getString(rnMap, "message");

        ReadableArray optionsReadableArray = ReadableMapUtilities.getArray(rnMap, "options");
        List<SdkActionSheetOptionParams> options = new ArrayList<>();
        if (optionsReadableArray != null) {
            for (int i = 0; i < optionsReadableArray.size(); ++i) {
                SdkActionSheetOptionParams actionSheetOption = SdkActionSheetOptionParams.fromReactNativeMap(optionsReadableArray.getMap(i));
                if (actionSheetOption == null)
                {
                    continue;
                }
                options.add(actionSheetOption);
            }
        }

        return new SdkActionSheetParams(title, message, options);
    }

    private SdkActionSheetParams(@Nullable String title, @Nullable String message, @NonNull List<SdkActionSheetOptionParams> options) {
        this.title = title;
        this.message = message;
        this.options = options;
    }
}
