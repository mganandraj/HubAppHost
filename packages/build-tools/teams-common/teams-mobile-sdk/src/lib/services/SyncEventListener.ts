import { NativeAppEventEmitter } from 'react-native';

const Events = {
  ON_TEAMS_AND_CHANNELS_SYNC: 'onTeamsAndChannelsSynced',
  ON_TABS_IN_CHATS_SYNCED: 'onTabsInChatsSynced'
};

export class SyncEventListener {
  private static teamsAndChannelsSyncHandler?: () => any;
  private static tabsInChatsSyncHandler?: () => any;

  public static registerHandlerForTeamsAndChannelsSync (handler) {
    if (!this.teamsAndChannelsSyncHandler) {
        NativeAppEventEmitter.addListener(Events.ON_TEAMS_AND_CHANNELS_SYNC, this.onThreadsSynced);
    }
    this.teamsAndChannelsSyncHandler = handler;
  }

  public static registerHandlerForTabsInChatsSync (handler) {
    if (!this.tabsInChatsSyncHandler) {
      NativeAppEventEmitter.addListener(Events.ON_TABS_IN_CHATS_SYNCED, this.onTabsInChatsSynced);
    }
    this.tabsInChatsSyncHandler = handler;
  }

  private static onThreadsSynced = () => {
    if (SyncEventListener.teamsAndChannelsSyncHandler) {
        SyncEventListener.teamsAndChannelsSyncHandler();
    }
  }

  private static onTabsInChatsSynced = () => {
    if (SyncEventListener.tabsInChatsSyncHandler) {
        SyncEventListener.tabsInChatsSyncHandler();
    }
  }
}
