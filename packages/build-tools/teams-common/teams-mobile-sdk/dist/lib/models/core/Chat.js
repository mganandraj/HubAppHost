"use strict";
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.Chat = void 0;
/**
 * @hidden from docs
 * Represent different types associated with a chat
 * i.e federatedChat/ SMSChat/ GroupChat etc
 */
class ChatTypes {
    constructor(chatTypes) {
        this.federatedChat = chatTypes["federatedChat"];
        this.smsChat = chatTypes["SMSChat"];
        this.groupChat = chatTypes["groupChat"];
    }
}
/**
 * Represent details about a chat
 */
class Chat {
    /**
     * @hidden from docs
     * Constructor for Chat object
     * @param chat
     */
    constructor(chat) {
        this.conversationId = chat.conversationId;
        this.displayName = chat.displayName;
        this.type = chat.type;
        this.consumerGroupId = chat.consumerGroupId;
        this.chatCreatedByConsumerAccount = chat.chatCreatedByConsumerAccount;
        this.chatTypes = new ChatTypes(chat.chatTypes);
    }
    /**
     * Check if the chat is federated or not
     * @returns boolean
     */
    isFederatedChat() {
        return this.chatTypes.federatedChat;
    }
    /**
     * Check if a chat is a oneOnoneSmsChat or not
     * @returns boolean
     */
    isSMSChat() {
        return this.chatTypes.smsChat;
    }
    /**
     * Check if a chat is a group chat or not
     * @returns boolean
     */
    isGroupChat() {
        return this.chatTypes.groupChat;
    }
    /**
     * Check if a chat is created by Consumer account or not
     * Consumer account - Consumer users are Skype users available from SfC interop in enterprise scenarios, or TfL users.
     * @returns boolean
     */
    isChatCreatedByConsumerAccount() {
        return this.chatCreatedByConsumerAccount;
    }
}
exports.Chat = Chat;
