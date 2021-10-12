import { Chat } from "../../models/core/Chat";
import { Tab } from "../../models/core/Tab";
import { User } from "../../models/core/User";
import { SyncState } from "../../models/params/ApplicationParams";
/**
 *  Module to provide chats and related APIs
 */
export declare class ChatsProviderModule {
    /**
     * Gets all chats for the current user.
     * This API will not return archived chats.
     *
     * @returns {Promise} A promise that returns an array of Chat objects when resolved
     */
    getAllChats(): Promise<Chat[]>;
    /**
     * Gets all tabs for the given list of chat ids
     *
     * @param chatIDs chat ids for which tabs will be returned
     * @returns {Promise} A promise that returns a Map of Tabs objects when resolved
     * Tabs are fetched from db and if not present will be fetched from network call
     */
    getTabsForChatsWithIDs(chatIDs: string[]): Promise<Map<string, Tab[]>>;
    /**
     * Indicate native client that tab list of chats have changed and it's time for update.
     *
     * @param chatIDs chat ids for which tabs will be returned
     * @returns {Promise} A promise that returns true if tab lists for given chatIds have been updated, otherwise false.
     */
    tabUpdatedForThreads(chatIDs: string[]): Promise<boolean>;
    /**
     * Get the initial sync status of chats
     * @returns {Promise} A promise that returns the initial sync state
     * This API is exposed for IOS only, its no-op in android
     */
    getTabsInChatsInitialSyncState(): Promise<SyncState>;
    /**
     * Gets all member details for the given list of chat ids.
     *
     * @returns {Promise} A promise that returns an array of Consumer IDs when resolved
     */
    getMembersDetailsForChats(chatIDs: string[]): Promise<Map<string, User[]>>;
}
