export declare class SyncEventListener {
    private static teamsAndChannelsSyncHandler?;
    private static tabsInChatsSyncHandler?;
    static registerHandlerForTeamsAndChannelsSync(handler: any): void;
    static registerHandlerForTabsInChatsSync(handler: any): void;
    private static onThreadsSynced;
    private static onTabsInChatsSynced;
}
