/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.storage.tables;

import com.microsoft.skype.teams.storage.IModel;
//import com.microsoft.skype.teams.storage.SkypeTeamsDatabase;
//import com.microsoft.skype.teams.storage.SkypeTeamsIndexes;
import com.microsoft.skype.teams.storage.ThreadType;
import com.microsoft.skype.teams.storage.converters.ThreadTypeConverter;
//import com.raizlabs.android.dbflow.annotation.Column;
//import com.raizlabs.android.dbflow.annotation.Index;
//import com.raizlabs.android.dbflow.annotation.IndexGroup;
//import com.raizlabs.android.dbflow.annotation.PrimaryKey;
//import com.raizlabs.android.dbflow.annotation.Table;
//import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

// import static com.microsoft.skype.teams.storage.dao.chatconversation.ChatConversationDaoDbFlowImpl.NEW_CHAT_CONVERSATION_PREFIX;

/**
 * Represents a conversation.
 */
//@Table(
//        database = SkypeTeamsDatabase.class,
//        indexGroups = {
//            //    @IndexGroup(number = 1, name = SkypeTeamsIndexes.CONVERSATION_PARENT_CONV_ID_INDEX),
//            //    @IndexGroup(number = 2, name = SkypeTeamsIndexes.CONVERSATION_LAST_MSG_ARRIVAL_INDEX)
//        }
//)
public class Conversation /*extends BaseModel*/ implements IModel {
    public static final String GLOBAL_CONVERSATION_SYNC_STATE_KEY = "Conversations";
    public static final String COLON_SPECIAL = ":"; // With DoD/GCC we might have different prefix for MRIs like orgid: or dod: etc.
    private static final String SPACE = "space";
    public static final short FLAG_SFC_CONVERSATION_STATUS_ACCEPTED = 1 << 0;
    public static final short FLAG_SFC_CONVERSATION_STATUS_BLOCKED = 1 << 1;
    public static final short FLAG_SFC_CONVERSATION_STATUS_ACCEPT_PENDING = 1 << 2;
    public static final short FLAG_SFC_CONVERSATION_STATUS_ALL = FLAG_SFC_CONVERSATION_STATUS_ACCEPTED
            | FLAG_SFC_CONVERSATION_STATUS_BLOCKED
            | FLAG_SFC_CONVERSATION_STATUS_ACCEPT_PENDING;

//     @PrimaryKey
    public String conversationId;

//     @PrimaryKey
    public String tenantId;

//    @Index(indexGroups = {1})
//    @Column
    public String parentConversationId;

//    @Column
    public String displayName;

//    @Column
    public Boolean alerts;

//    @Column
    public long unpinnedTime;

//    @Column
    public String consumptionHorizon;

//    @Column
    public String consumptionHorizonBookmark;

//    @Column
    public boolean isFavorite;

//    @Column
    public boolean isDirty;

//    @Column
    public long threadLastJoin;

//    @Column
    public long threadLastLeave;

//    @Column
    public long threadVersion;

//    @Column
    public long rosterVersion;

//    @Column(typeConverter = ThreadTypeConverter.class)
    public ThreadType threadType;

//    @Column
    public long lastMessageId;

//    @Index(indexGroups = {2})
//    @Column
    public long lastMessageArrivalTime;

//    @Column
    public String conversationType;

//    @Column
    public long version;

//    @Column
    public boolean leftConversation;

//    @Column
    public boolean isDeleted;

//    @Column
    public boolean isDead;

//    @Column
    public String archivalLevel;

//    @Column
    public String spaceTypes;

//    @Column
    public String parentSpaces;

//    @Column
    public int mentionCount;

//    @Column
    public Date createdDate;

//    @Column
    public String createdBy;

//    @Column
    public long readUntil;

//    @Column
    public String topic;

//    @Column
    public int accessCount;

//    @Column
    public boolean isPinned;

//    @Column
    public int pinOrder;

//    @Column
    public String substrateGroupId;

//    @Column
    public boolean channelOnlyMember; // Indicates if logged in user has membership only to a channel and not the whole team

//    @Column
    public long retentionHorizon;

//    @Column
    public boolean gapDetectionEnabled; // If this is true then V2 thread else V1 thread.

//    @Column
    public boolean isDisabled;

//    @Column
    public int conversationStatus;

//    @Column
    public long notificationSyncTime;

//    @Column
    public long lastMessageSequenceId; // lastMessage's sequeceId, no sync involved

//    @Column
    public long lastMessageSequenceIdAtSync; // the last message sequeceId from getMessages sync call of the conversation

//    @Column
    public long lastMessageIdAtSync; // the lastMessageId from last sync. If lastMessageIdAtSync equals to lastMessageId, no sync needed.

    private String mAadGroupId;

    public String getAadGroupId() {
        return mAadGroupId;
    }

    public void setAadGroupId(String aadGroupId) {
        this.mAadGroupId = aadGroupId;
    }

    /**
     * Check if a conversation is an SMS conversation.
     * @return true if SMS conversation, false otherwise
     */
    public boolean isSMSChat() {
        return threadType == ThreadType.PHONE_SMS_CHAT;
    }

    public boolean isChat() {
        return false;
    }

    public boolean isTeamConversationArchived() {
        return SPACE.equals(archivalLevel);
    }

    public boolean setSfcConversationStatusFlags(int flags) {
        boolean changed = false;
        final int newFlags = (conversationStatus & ~FLAG_SFC_CONVERSATION_STATUS_ALL) | flags;

        if (newFlags != conversationStatus) {
            conversationStatus = newFlags;
            changed = true;
        }

        return changed;
    }

    public boolean isSfcConversationStatusFlagSet(int flag) {
        return (conversationStatus & flag) > 0;
    }

    public boolean isSfcConversationAccepted() {
        return isSfcConversationStatusFlagSet(FLAG_SFC_CONVERSATION_STATUS_ACCEPTED);
    }

    public boolean isSfcConversationBlocked() {
        return isSfcConversationStatusFlagSet(FLAG_SFC_CONVERSATION_STATUS_BLOCKED);
    }

    public boolean isSfcConversationAcceptPending() {
        return isSfcConversationStatusFlagSet(FLAG_SFC_CONVERSATION_STATUS_ACCEPT_PENDING);
    }

    public static final String NEW_CHAT_CONVERSATION_PREFIX = "NewChatConversation_";

    @Override
    public String toString() {
        String conversationIdToLog = conversationId;
        if (isSMSChat() && conversationId.startsWith(NEW_CHAT_CONVERSATION_PREFIX)) {
            conversationIdToLog = "CONVERSATION_ID_CONTAINS_USER_INFO";
        }
        return "Conversation{"
                + "conversationId='"
                + conversationIdToLog
                + '\''
                + ", parentConversationId='"
                + parentConversationId
                + '\''
                + ", alerts="
                + alerts
                + ", consumptionHorizon='"
                + consumptionHorizon
                + '\''
                + ", consumptionHorizonBookmark='"
                + consumptionHorizonBookmark
                + '\''
                + ", isFavorite="
                + isFavorite
                + ", isDirty="
                + isDirty
                + ", threadLastJoin="
                + threadLastJoin
                + ", threadLastLeave="
                + threadLastLeave
                + ", threadType="
                + threadType
                + ", lastMessageId="
                + lastMessageId
                + ", lastMessageArrivalTime="
                + lastMessageArrivalTime
                + ", conversationType='"
                + conversationType
                + '\''
                + ", version="
                + version
                + ", threadVersion="
                + threadVersion
                + ", rosterVersion="
                + rosterVersion
                + ", leftConversation="
                + leftConversation
                + ", isDeleted="
                + isDeleted
                + ", mentionCount="
                + mentionCount
                + ", createdDate="
                + createdDate
                + ", isPinned="
                + isPinned
                + ", retentionHorizon="
                + retentionHorizon
                + '}';
    }
}