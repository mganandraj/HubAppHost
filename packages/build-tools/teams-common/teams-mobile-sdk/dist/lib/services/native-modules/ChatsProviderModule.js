"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ChatsProviderModule = void 0;
const react_native_1 = require("react-native");
const Chat_1 = require("../../models/core/Chat");
const NativeChatsProviderModule = react_native_1.NativeModules.chatsProvider;
/**
 *  Module to provide chats and related APIs
 */
class ChatsProviderModule {
    /**
     * Gets all chats for the current user.
     * This API will not return archived chats.
     *
     * @returns {Promise} A promise that returns an array of Chat objects when resolved
     */
    getAllChats() {
        return NativeChatsProviderModule.getAllChats().then((chats) => {
            const chatsObjectArray = new Array();
            chats.forEach((chat) => {
                chatsObjectArray.push(new Chat_1.Chat(chat));
            });
            return chatsObjectArray;
        });
    }
    /**
     * Gets all tabs for the given list of chat ids
     *
     * @param chatIDs chat ids for which tabs will be returned
     * @returns {Promise} A promise that returns a Map of Tabs objects when resolved
     * Tabs are fetched from db and if not present will be fetched from network call
     */
    getTabsForChatsWithIDs(chatIDs) {
        return NativeChatsProviderModule.getTabsForChatsWithIDs(chatIDs);
    }
    /**
     * Indicate native client that tab list of chats have changed and it's time for update.
     *
     * @param chatIDs chat ids for which tabs will be returned
     * @returns {Promise} A promise that returns true if tab lists for given chatIds have been updated, otherwise false.
     */
    tabUpdatedForThreads(chatIDs) {
        return NativeChatsProviderModule.tabUpdatedForThreads(chatIDs);
    }
    /**
     * Get the initial sync status of chats
     * @returns {Promise} A promise that returns the initial sync state
     * This API is exposed for IOS only, its no-op in android
     */
    getTabsInChatsInitialSyncState() {
        return NativeChatsProviderModule.getTabsInChatsInitialSyncState();
    }
    /**
     * Gets all member details for the given list of chat ids.
     *
     * @returns {Promise} A promise that returns an array of Consumer IDs when resolved
     */
    getMembersDetailsForChats(chatIDs) {
        return NativeChatsProviderModule.getMembersDetailsForChats(chatIDs);
    }
}
exports.ChatsProviderModule = ChatsProviderModule;
