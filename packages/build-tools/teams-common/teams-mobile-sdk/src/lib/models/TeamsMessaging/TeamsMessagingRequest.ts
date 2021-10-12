/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import { Dictionary } from 'lodash';
import { TeamsMessagingCard } from './TeamsMessagingCard';
import { TeamsMessagingFile } from './TeamsMessagingFile';
import { TeamsMessagingMention } from './TeamsMessagingMention';

export enum TeamsMessageType {
  TEXT = 'Text',
  HTML = 'Html'
}

export interface TeamsMessagingRequest {

  /**
   * Thread ID of the conversation
   */
  threadID: string;

  /**
   * Message to be posted
   */
  text?: string;

  /**
   * Message ID to which it should be posted as reply. 
   * If message should not be posted in reply thread, ignore.
   */
  rootMessageID?: string;

  /**
   * Message type - Text or Html or Card
   */
  messageType: TeamsMessageType;

  /**
   * Information about mentions
   */
  mentionDictionary?: Dictionary<TeamsMessagingMention>;

  /**
   * File info which has to be attached
   */
  files?: TeamsMessagingFile[];

  /**
   * Card info  which has to be attached
   * Cards & files cannot exist together. Files would be ignored when cards is available
   */
  cards?: TeamsMessagingCard[];
}
