"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.SyncEventListener = void 0;
const react_native_1 = require("react-native");
const Events = {
    ON_TEAMS_AND_CHANNELS_SYNC: 'onTeamsAndChannelsSynced',
    ON_TABS_IN_CHATS_SYNCED: 'onTabsInChatsSynced'
};
class SyncEventListener {
    static registerHandlerForTeamsAndChannelsSync(handler) {
        if (!this.teamsAndChannelsSyncHandler) {
            react_native_1.NativeAppEventEmitter.addListener(Events.ON_TEAMS_AND_CHANNELS_SYNC, this.onThreadsSynced);
        }
        this.teamsAndChannelsSyncHandler = handler;
    }
    static registerHandlerForTabsInChatsSync(handler) {
        if (!this.tabsInChatsSyncHandler) {
            react_native_1.NativeAppEventEmitter.addListener(Events.ON_TABS_IN_CHATS_SYNCED, this.onTabsInChatsSynced);
        }
        this.tabsInChatsSyncHandler = handler;
    }
}
exports.SyncEventListener = SyncEventListener;
SyncEventListener.onThreadsSynced = () => {
    if (SyncEventListener.teamsAndChannelsSyncHandler) {
        SyncEventListener.teamsAndChannelsSyncHandler();
    }
};
SyncEventListener.onTabsInChatsSynced = () => {
    if (SyncEventListener.tabsInChatsSyncHandler) {
        SyncEventListener.tabsInChatsSyncHandler();
    }
};
