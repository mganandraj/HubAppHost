package com.microsoft.teams.sdk.models.params;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
///import com.microsoft.teams.app.SkypeTeamsApplication;
//import com.microsoft.teams.data.proxy.MiddleTierServiceProvider;
import com.microsoft.teams.logger.ILogger;
import com.microsoft.teams.logger.LogPriority;
import com.microsoft.teams.sdk.react.ReactNativeUtilities;
import com.microsoft.teams.storage.tables.Conversation;
import com.microsoft.teams.storage.tables.TeamOrder;
import com.microsoft.teams.storage.tables.Thread;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents team for SDK app
 */
public class SdkAppTeamParams implements Comparable<SdkAppTeamParams>, SdkAppWriteableParams {
    private static final String LOG_TAG = "SdkAppTeamParams";

    private static final String ID_KEY = "id";
    private static final String NAME_KEY = "name";
    private static final String ORDER_KEY = "order";
    private static final String IS_FAVORITE_KEY = "isFavorite";
    private static final String CHANNELS_KEY = "channels";
    private static final String PICTURE_URL_KEY = "teamPictureUrl";
    private static final String SHAREPOINT_SITE_URL_KEY = "teamSharepointSiteUrl";
    private static final String GROUP_ID = "groupId";

    public final String id;
    public final String name;
    public final String groupId;
    public final int order;
    public final boolean isFavorite;
    public final List<SdkAppChannelParams> channels;
    public final String pictureUrl;
    public final String sharepointSiteUrl;

    public SdkAppTeamParams(@NonNull Conversation conversation, @NonNull List<TeamOrder> teamOrders, @NonNull ILogger logger) {
        this.id = conversation.conversationId;
        this.name = conversation.displayName;
        this.isFavorite = conversation.isFavorite;
        this.channels = new ArrayList<>();
        this.pictureUrl = "";/*SkypeTeamsApplication.getAuthenticatedUserComponent()
                .conversationDao().getAvatarUrl(conversation,
                MiddleTierServiceProvider.getMiddleTierServiceBaseImageUrl());*/
        this.groupId = conversation.getAadGroupId();

        Thread thread = null; /*SkypeTeamsApplication.getAuthenticatedUserComponent()
                .threadDao().getThreadProperties(conversation.conversationId);*/
        if (thread != null) {
            this.sharepointSiteUrl = ""; //thread.sharepointUrl;
        } else {
            this.sharepointSiteUrl = "";
            logger.log(LogPriority.INFO, LOG_TAG, "No thread found for team id %s", conversation.conversationId);
        }

        this.order = getOrder(teamOrders, isFavorite);
    }

    public SdkAppTeamParams(@NonNull Conversation conversation,
                            @NonNull List<TeamOrder> teamOrders,
                            @Nullable String pictureUrl,
                            @Nullable Thread thread,
                            @NonNull ILogger logger) {
        this.id = conversation.conversationId;
        this.name = conversation.displayName;
        this.isFavorite = conversation.isFavorite;
        this.channels = new ArrayList<>();
        this.pictureUrl = pictureUrl;
        this.groupId = conversation.getAadGroupId();

        if (thread != null) {
            this.sharepointSiteUrl = ""; //thread.sharepointUrl;
        } else {
            this.sharepointSiteUrl = "";
            logger.log(LogPriority.INFO, LOG_TAG, "No thread found for team id %s", conversation.conversationId);
        }

        this.order = getOrder(teamOrders, isFavorite);
    }

    @Override
    public int compareTo(@NonNull SdkAppTeamParams that) {
        return Integer.compare(this.order, that.order);
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
        map.putString(ID_KEY, id);
        map.putString(NAME_KEY, name);
        map.putInt(ORDER_KEY, order);
        map.putBoolean(IS_FAVORITE_KEY, isFavorite);
        map.putArray(CHANNELS_KEY,
                     ReactNativeUtilities.convertObjectArraytoWritableArray(channels));
        map.putString(PICTURE_URL_KEY, pictureUrl);
        map.putString(SHAREPOINT_SITE_URL_KEY, sharepointSiteUrl);
        map.putString(GROUP_ID, groupId);
        return map;
    }

    private int getOrder(@NonNull List<TeamOrder> teamOrders, boolean isFavorite) {
        if (!isFavorite) {
            return Integer.MAX_VALUE;
        }

        for (TeamOrder to : teamOrders) {
            if (to.teamId.equals(id)) {
                return to.order;
            }
        }

        return Integer.MAX_VALUE;
    }
}
