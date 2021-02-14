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
 * Represents parameter of Image Source metadata from SDK
 */
public class SdkImageSourceMetadata implements SdkAppWriteableParams {
    private static final String LOG_TAG = "SdkImageSourceMetadata";

    private static final String SOURCE_TYPE = "type";
    private static final String SKIP_TOKEN = "skipToken";
    private static final String IMAGES = "images";

    public final String type;
    public final String skipToken;
    public List<SdkAppImageParams> images;

    public SdkImageSourceMetadata(@NonNull String type,
                                  @NonNull String skipToken,
                                  @NonNull List<SdkAppImageParams> images) {
        this.type = type;
        this.skipToken = skipToken;
        this.images = images;
    }

    public void setImages(List<SdkAppImageParams> images) {
        this.images = images;
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
        map.putString(SOURCE_TYPE, type);
        map.putString(SKIP_TOKEN, skipToken);
        map.putArray(IMAGES, ReactNativeUtilities.convertObjectArraytoWritableArray(images));
        return map;
    }

    @Nullable
    public static ArrayList<SdkImageSourceMetadata> fromArray(@Nullable ReadableArray array) {
        if (array == null) {
            return null;
        }

        ArrayList<SdkImageSourceMetadata> sdkImageSourceMetadataParams = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            SdkImageSourceMetadata sdkImageSourceMetadataParam = fromMap(array.getMap(i));

            if (sdkImageSourceMetadataParam != null) {
                sdkImageSourceMetadataParams.add(sdkImageSourceMetadataParam);
            }
        }

        return sdkImageSourceMetadataParams;
    }

    @Nullable
    public static SdkImageSourceMetadata fromMap(@Nullable ReadableMap map) {
        if (map == null) {
            return null;
        }

        String type = ReadableMapUtilities.getString(map, SOURCE_TYPE);
        String skipToken = ReadableMapUtilities.getString(map, SKIP_TOKEN);
        List<SdkAppImageParams> images = SdkAppImageParams.fromArray(ReadableMapUtilities.getArray(map, IMAGES));

        return new SdkImageSourceMetadata(type, skipToken, images);
    }
}

