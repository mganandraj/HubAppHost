package com.microsoft.skype.teams.sdk.models.params;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.microsoft.skype.teams.sdk.react.ReadableMapUtilities;

import java.util.ArrayList;

/**
 * Represents Tab for SDK app
 */
public class SdkAppImageParams implements SdkAppWriteableParams {
    private static final String LOG_TAG = "SdkAppImageParams";

    private static final String FILE_ID_KEY = "id";
    private static final String FILE_TITLE = "title";
    private static final String FILE_TYPE = "type";
    private static final String FILE_ORIGINAL_URL = "url";
    private static final String FILE_OBJECT_URL = "objectUrl";
    private static final String FILE_THUMBNAIL_URL = "thumbnailUrl";
    private static final String FILE_DOWNLOAD_URL = "downloadUrl";
    private static final String FILE_SERVER_TYPE = "serverType";
    private static final String FILE_LAST_MODIFIED_TIME = "lastModifiedTime";
    private static final String FILE_CHANNEL_ID = "channelId";
    private static final String FILE_TEAM_ID = "teamId";
    private static final String FILE_PERSON_ID = "userIdentifier";
    private static final String FILE_MESSAGE_ID = "messageId";
    private static final String FILE_PARENT_MESSAGE_ID = "parentMessageId";

    public final String id;
    public final String title;
    public final String type;
    public final String url;
    public final String objectUrl;
    public String thumbnailUrl;
    public final String downloadUrl;
    public final String serverType;
    public final String lastModifiedTime;
    public final String channelId;
    public final String teamId;
    public final String userIdentifier;
    public final String messageId;
    public final String parentMessageId;

    public SdkAppImageParams(@NonNull String id,
                             @Nullable String title,
                             @NonNull String type,
                             @NonNull String url,
                             @NonNull String objectUrl,
                             @NonNull String thumbnailUrl,
                             @Nullable String downloadUrl,
                             @NonNull String serverType,
                             @Nullable String lastModifiedTime,
                             @NonNull String channelId,
                             @NonNull String teamId,
                             @NonNull String userIdentifier,
                             @NonNull String messageId,
                             @Nullable String parentMessageId) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.url = url;
        this.objectUrl = objectUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.downloadUrl = downloadUrl;
        this.serverType = serverType;
        this.lastModifiedTime = lastModifiedTime;
        this.channelId = channelId;
        this.teamId = teamId;
        this.userIdentifier = userIdentifier;
        this.messageId = messageId;
        this.parentMessageId = parentMessageId;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
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
        map.putString(FILE_ID_KEY, id);
        map.putString(FILE_TITLE, title);
        map.putString(FILE_TYPE, type);
        map.putString(FILE_ORIGINAL_URL, url);
        map.putString(FILE_OBJECT_URL, objectUrl);
        map.putString(FILE_THUMBNAIL_URL, thumbnailUrl);
        map.putString(FILE_DOWNLOAD_URL, downloadUrl);
        map.putString(FILE_SERVER_TYPE, serverType);
        map.putString(FILE_LAST_MODIFIED_TIME, lastModifiedTime);
        map.putString(FILE_CHANNEL_ID, channelId);
        map.putString(FILE_TEAM_ID, teamId);
        map.putString(FILE_PERSON_ID, userIdentifier);
        map.putString(FILE_MESSAGE_ID, messageId);
        map.putString(FILE_PARENT_MESSAGE_ID, parentMessageId);
        return map;
    }

    @Nullable
    public static ArrayList<SdkAppImageParams> fromArray(@Nullable ReadableArray array) {
        if (array == null) {
            return null;
        }

        ArrayList<SdkAppImageParams> sdkAppImageParams = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            SdkAppImageParams sdkAppFileParam = fromMap(array.getMap(i));

            if (sdkAppFileParam != null) {
                sdkAppImageParams.add(sdkAppFileParam);
            }
        }

        return sdkAppImageParams;
    }

    @Nullable
    public static SdkAppImageParams fromMap(@Nullable ReadableMap map) {
        if (map == null) {
            return null;
        }

        String id = ReadableMapUtilities.getString(map, FILE_ID_KEY);
        String title = ReadableMapUtilities.getString(map, FILE_TITLE);
        String type = ReadableMapUtilities.getString(map, FILE_TYPE);
        String url = ReadableMapUtilities.getString(map, FILE_ORIGINAL_URL);
        String objectUrl = ReadableMapUtilities.getString(map, FILE_OBJECT_URL);
        String thumbnailUrl = ReadableMapUtilities.getString(map, FILE_THUMBNAIL_URL);
        String downloadUrl = ReadableMapUtilities.getString(map, FILE_DOWNLOAD_URL);
        String serverType = ReadableMapUtilities.getString(map, FILE_SERVER_TYPE);
        String lastModifiedTime = ReadableMapUtilities.getString(map, FILE_LAST_MODIFIED_TIME);
        String channelId = ReadableMapUtilities.getString(map, FILE_CHANNEL_ID);
        String teamId = ReadableMapUtilities.getString(map, FILE_TEAM_ID);
        String userIdentifier = ReadableMapUtilities.getString(map, FILE_PERSON_ID);
        String messageId = ReadableMapUtilities.getString(map, FILE_MESSAGE_ID);
        String parentMessageId = ReadableMapUtilities.getString(map, FILE_PARENT_MESSAGE_ID);

        return new SdkAppImageParams(id,
                                    title,
                                    type,
                                    url,
                                    objectUrl,
                                    thumbnailUrl,
                                    downloadUrl,
                                    serverType,
                                    lastModifiedTime,
                                    channelId,
                                    teamId,
                                    userIdentifier,
                                    messageId,
                                    parentMessageId);
    }
}


