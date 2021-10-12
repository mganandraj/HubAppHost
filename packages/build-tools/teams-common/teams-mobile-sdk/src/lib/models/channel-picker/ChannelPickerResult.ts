/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import { Channel } from '../core/Channel';
import { Team } from '../core/Team';

/**
 * Represents the result returned by the channel picker.
 */
export interface ChannelPickerResult {
  /**
   * The channel selected by the user.
   */
  selectedChannel: Channel;

  /**
   * The parent team of the channel selected by the user.
   */
  parentTeam: Team;
}
