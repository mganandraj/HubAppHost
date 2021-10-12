import { NativeModules } from "react-native";
import { Chat } from "../../models/core/Chat";
import { Tab } from "../../models/core/Tab";
import { User } from "../../models/core/User";
import { SyncState } from "../../models/params/ApplicationParams";

const NativeChatsProviderModule = NativeModules.chatsProvider;

/**
 *  Module to provide chats and related APIs
 */
export class ChatsProviderModule {
  /**
   * Gets all chats for the current user.
   * This API will not return archived chats.
   *
   * @returns {Promise} A promise that returns an array of Chat objects when resolved
   */
  public getAllChats(): Promise<Chat[]> {
    return NativeChatsProviderModule.getAllChats().then((chats: any[]) => {
      const chatsObjectArray = new Array();
      chats.forEach((chat: any) => {
          chatsObjectArray.push(new Chat(chat));
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
  public getTabsForChatsWithIDs(chatIDs: string[]): Promise<Map<string, Tab[]>> {
    return NativeChatsProviderModule.getTabsForChatsWithIDs(chatIDs);
  }

  /**
   * Indicate native client that tab list of chats have changed and it's time for update.
   *
   * @param chatIDs chat ids for which tabs will be returned
   * @returns {Promise} A promise that returns true if tab lists for given chatIds have been updated, otherwise false.
   */
  public tabUpdatedForThreads(chatIDs: string[]): Promise<boolean> {
    return NativeChatsProviderModule.tabUpdatedForThreads(chatIDs);
  }

  /**
   * Get the initial sync status of chats
   * @returns {Promise} A promise that returns the initial sync state
   * This API is exposed for IOS only, its no-op in android
   */
  public getTabsInChatsInitialSyncState() : Promise<SyncState> {
    return NativeChatsProviderModule.getTabsInChatsInitialSyncState();
  }

   /**
    * Gets all member details for the given list of chat ids.
    *
    * @returns {Promise} A promise that returns an array of Consumer IDs when resolved
    */
  public getMembersDetailsForChats(chatIDs: string[]): Promise<Map<string, User[]>> {
    return NativeChatsProviderModule.getMembersDetailsForChats(chatIDs);
  }
}