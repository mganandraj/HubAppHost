/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
/**
 * Represent details about a chat
 */
export declare class Chat {
    conversationId: string;
    displayName: string;
    type: string;
    consumerGroupId: string;
    private chatCreatedByConsumerAccount;
    private chatTypes;
    /**
     * @hidden from docs
     * Constructor for Chat object
     * @param chat
     */
    constructor(chat: any);
    /**
     * Check if the chat is federated or not
     * @returns boolean
     */
    isFederatedChat(): boolean;
    /**
     * Check if a chat is a oneOnoneSmsChat or not
     * @returns boolean
     */
    isSMSChat(): boolean;
    /**
     * Check if a chat is a group chat or not
     * @returns boolean
     */
    isGroupChat(): boolean;
    /**
     * Check if a chat is created by Consumer account or not
     * Consumer account - Consumer users are Skype users available from SfC interop in enterprise scenarios, or TfL users.
     * @returns boolean
     */
    isChatCreatedByConsumerAccount(): boolean;
}
