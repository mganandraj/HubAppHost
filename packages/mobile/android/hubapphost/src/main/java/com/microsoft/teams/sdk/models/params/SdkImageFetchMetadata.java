package com.microsoft.teams.sdk.models.params;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.microsoft.teams.sdk.react.ReactNativeUtilities;
import com.microsoft.teams.sdk.react.ReadableMapUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents parameter of Image Fetch metadata from SDK
 */
public class SdkImageFetchMetadata implements SdkAppWriteableParams {
    private static final String LOG_TAG = "SdkImageFetchMetadata";

    private static final String TEAM_ID = "teamId";
    private static final String CHANNEL_ID = "channelId";
    private static final String SOURCES = "sources";

    public final String teamId;
    public final String channelId;
    public final List<SdkImageSourceMetadata> sources;

    public SdkImageFetchMetadata(@Nullable String teamId,
                                 @NonNull String channelId,
                                 @NonNull List<SdkImageSourceMetadata> sources) {
        this.teamId = teamId;
        this.channelId = channelId;
        this.sources = sources;
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        Bundle bundle = Arguments.toBundle(toMap());
        return bundle != null ? bundle : new Bundle();
    }

    @NonNull
    @Override
    public WritableMap toMap() {
        WritableMap map = Arguments.createMap();
        map.putString(TEAM_ID, teamId);
        map.putString(CHANNEL_ID, channelId);
        map.putArray(SOURCES, ReactNativeUtilities.convertObjectArraytoWritableArray(sources));
        return map;
    }

    @Nullable
    public static ArrayList<SdkImageFetchMetadata> fromArray(@Nullable ReadableArray array) {
        if (array == null) {
            return null;
        }

        ArrayList<SdkImageFetchMetadata> sdkConversationImagesParams = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            SdkImageFetchMetadata sdkConversationImagesParam = fromMap(array.getMap(i));

            if (sdkConversationImagesParam != null) {
                sdkConversationImagesParams.add(sdkConversationImagesParam);
            }
        }

        return sdkConversationImagesParams;
    }

    @Nullable
    public static SdkImageFetchMetadata fromMap(@Nullable ReadableMap map) {
        if (map == null) {
            return null;
        }

        String teamId = ReadableMapUtilities.getString(map, TEAM_ID);
        String channelId = ReadableMapUtilities.getString(map, CHANNEL_ID);
        List<SdkImageSourceMetadata> sources = SdkImageSourceMetadata.fromArray(ReadableMapUtilities.getArray(map, SOURCES));

        return new SdkImageFetchMetadata(teamId, channelId, sources);
    }
}

