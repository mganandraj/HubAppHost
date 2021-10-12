/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

export enum TeamsMessagingMentionType {
  PERSON = 'person',
  CHANNEL = 'channel'
}

export interface TeamsMessagingMention {

  /**
   * Display name for mention
   */
  displayName: string;

  /**
   * Unique item id for mention. Same as span item id
   */
  itemid: number;

  /**
   * Order of mention in message
   */
  _index: number;

  /**
   * Type of mention - Person / Channel / Bot
   */
  mentionType: TeamsMessagingMentionType;

  /**
   * MRI for person mention & thread ID for channel mention
   */
  mri: string;
}
