package com.microsoft.teams.sdk.models.params;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
// import com.microsoft.teams.storage.dao.conversation.ConversationDaoHelper;
import com.microsoft.teams.storage.tables.Conversation;

/**
 * Represents channel for SDK app
 */
public class SdkAppChannelParams implements SdkAppWriteableParams {

    private static final String ID_KEY = "id";
    private static final String TEAM_ID_KEY = "teamId";
    private static final String NAME_KEY = "name";
    private static final String IS_FAVORITE_KEY = "isFavorite";
    private static final String IS_GENERAL_CHANNEL_KEY = "isGeneralChannel";
    private static final String IS_PRIVATE_CHANNEL_KEY = "isPrivateChannel";

    public final String id;
    public final String teamId;
    public final String name;
    public final boolean isFavorite;
    public final boolean isPrivateChannel;
    public final boolean isGeneralChannel;

    public SdkAppChannelParams(@NonNull Context context, @NonNull Conversation conversation) {
        this.id = conversation.conversationId;
        this.teamId = conversation.parentConversationId;
        this.isFavorite = conversation.isFavorite;
        this.isPrivateChannel = false; // ConversationDaoHelper.isPrivateChannel(conversation);
        this.isGeneralChannel = false; // ConversationDaoHelper.isGeneralChannel(conversation);

        if (isGeneralChannel) {
            name = ""; // ConversationDaoHelper.getGeneralChannelName(context);
        } else {
            name = conversation.displayName;
        }
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
        map.putString(TEAM_ID_KEY, teamId);
        map.putString(NAME_KEY, name);
        map.putBoolean(IS_FAVORITE_KEY, isFavorite);
        map.putBoolean(IS_GENERAL_CHANNEL_KEY, isGeneralChannel);
        map.putBoolean(IS_PRIVATE_CHANNEL_KEY, isPrivateChannel);
        return map;
    }
}
