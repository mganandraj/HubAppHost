/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.storage;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Enumeration of Types of Thread.
 */
public enum ThreadType {
    SPACE(0, "Space"),
    TOPIC(1, "Topic"),
    CHAT(2, "Chat"),
    CHAT1ON1(3, "1ON1"),
    UNKNOWN(4, "Unknown"),
    // Don't change this as this will require a DB reset
    PRIVATE_MEETING(5, "Meeting"),
    SFB_INTEROP_CHAT(6, "sfbinteropchat"), // Both sfb and sfc (Skype for Consumer) interop chats use the same threadType "sfbinteropchat"
    FEDERATED_CHAT(7, "federatedchat"), // internal concept for telemetry usage.  Server knows it as SFB_INTEROP_CHAT only
    PHONE_SMS_CHAT(8, "phonechat");

    private final int mNumValue;
    private final String mText;

    ThreadType(int numVal, String text) {
        this.mNumValue = numVal;
        this.mText = text;
    }

    public static ThreadType fromString(String text) {
        if (text != null) {
            for (ThreadType threadType : ThreadType.values()) {
                if (text.equalsIgnoreCase(threadType.mText)) {
                    return threadType;
                }
            }
        }

        return UNKNOWN;
    }

    public static boolean isChatType(ThreadType threadType) {
        return threadType == ThreadType.CHAT
                || threadType == ThreadType.CHAT1ON1
                || threadType == ThreadType.PRIVATE_MEETING
                || threadType == ThreadType.SFB_INTEROP_CHAT
                || threadType == ThreadType.FEDERATED_CHAT
                || threadType == ThreadType.PHONE_SMS_CHAT;
    }

    public static List<String> getChatTypeNumValues() {
        List<String> chatTypes = new ArrayList<>();
        for (ThreadType threadType : ThreadType.values()) {
            if (isChatType(threadType)) {
                chatTypes.add(String.valueOf(threadType.getNumVal()));
            }
        }
        return chatTypes;
    }

    public static boolean isChannelType(ThreadType threadType) {
        return threadType == ThreadType.TOPIC
                || threadType == ThreadType.SPACE;
    }

    public static boolean isPrivateMeeting(@Nullable ThreadType threadType) {
        return ThreadType.PRIVATE_MEETING.equals(threadType);
    }

    /**
     * Check if a given chat thread is an SMS chat
     * @param threadType - threadType
     * @return - true or false
     */
    public static boolean isSMSChat(@Nullable ThreadType threadType) {
        return ThreadType.PHONE_SMS_CHAT.equals(threadType);
    }

    public int getNumVal() {
        return mNumValue;
    }

    public String getText() {
        return mText;
    }

    @Override
    public String toString() {
        // Return the String representation of the ordinal value
        return Integer.toString(mNumValue);
    }
}