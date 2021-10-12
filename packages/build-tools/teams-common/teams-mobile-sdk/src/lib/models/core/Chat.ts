/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

/**
 * @hidden from docs
 * Represent different types associated with a chat
 * i.e federatedChat/ SMSChat/ GroupChat etc
 */
class ChatTypes {
  public federatedChat: boolean;
  public smsChat: boolean;
  public groupChat: boolean;

  public constructor (chatTypes: ChatTypes) {
    this.federatedChat = chatTypes["federatedChat"];
    this.smsChat = chatTypes["SMSChat"];
    this.groupChat = chatTypes["groupChat"];
  }
}

/**
 * Represent details about a chat
 */
export class Chat {
  public conversationId: string;
  public displayName: string;
  public type: string;
  public consumerGroupId: string;
  private chatCreatedByConsumerAccount: boolean;
  private chatTypes: ChatTypes;

  /**
   * @hidden from docs
   * Constructor for Chat object
   * @param chat 
   */
  public constructor (chat: any) {
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
  public isFederatedChat(): boolean {
    return this.chatTypes.federatedChat;
  }

  /**
   * Check if a chat is a oneOnoneSmsChat or not
   * @returns boolean
   */
  public isSMSChat(): boolean {
    return this.chatTypes.smsChat;
  }

  /**
   * Check if a chat is a group chat or not
   * @returns boolean
   */
  public isGroupChat(): boolean {
    return this.chatTypes.groupChat;
  }

  /**
   * Check if a chat is created by Consumer account or not
   * Consumer account - Consumer users are Skype users available from SfC interop in enterprise scenarios, or TfL users.
   * @returns boolean
   */
  public isChatCreatedByConsumerAccount(): boolean {
    return this.chatCreatedByConsumerAccount;
  }
}
