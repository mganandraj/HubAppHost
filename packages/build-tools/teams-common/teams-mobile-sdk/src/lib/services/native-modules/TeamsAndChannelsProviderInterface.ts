import { Tab } from '../../models/core/Tab';
import { Team } from '../../models/core/Team';
import { SyncState } from '../../models/params/ApplicationParams';

export interface TeamsAndChannelsProviderInterface {
  /**
   * Gets all teams the current user is a part of.
   * This API will not return archived teams.
   *
   * If includeChannels is false, the channels property of Team will be
   * an empty array
   *
   * @param includeChannels whether details of channels are to be returned for each team
   * @returns {Promise} A promise that returns an array of Team objects when resolved
   */
  getAllTeams (includeChannels: boolean): Promise<Team[]>;

  /**
   * Gets the teams with the given IDs. Any invalid team IDs are ignored. If any of the
   * given team ID refers to an archived team, it will be ignored.
   *
   * If includeChannels is false, the channels property of Team will be
   * an empty array
   *
   * @param teamIDs list of team IDs
   * @param includeChannels whether details of channels are to be returned for each team
   * @returns {Promise} A promise that returns an array of Team objects when resolved
   */
  getTeamsWithIDs (teamIDs: string[], includeChannels: boolean): Promise<Team[]>;

  /**
   * Gets all tabs for the given list of channels
   *
   * @param channelIDs channel ids for which tabs will be returned
   * @returns {Promise} A promise that returns a Map of Tabs objects when resolved
   */
  getTabsForChannelsWithIDs (channelIDs: string[]): Promise<Map<string, Tab[]>>;

  /**
   * Gets all tabs for the given list of channels
   * Prefered to send channelIds in small chunk and handle pagination on RN side
   * @param channelIDs channel ids for which tabs will be returned
   * @returns {Promise} A promise that returns a Map of Tabs objects when resolved
   * This API is specifically exposed for Android OS only.
   */
  androidForceSyncTabsForChannels (channelIDs: string[]): Promise<Map<string, Tab[]>>;

  /**
   * Get the initial sync status of teams and channels.
   * @returns {Promise} A promise that returns the initial sync state
   * This API is exposed for IOS only.
   */
  getTeamsAndChannelsInitialSyncStateIOS () : Promise<SyncState>;
}
