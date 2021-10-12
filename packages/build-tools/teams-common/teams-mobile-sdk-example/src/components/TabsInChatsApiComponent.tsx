import * as React from 'react';
import { ActivityIndicator, Dimensions, FlatList, NativeAppEventEmitter, Platform, StyleSheet, Text, View} from 'react-native';
import {
    AppTheme,
    Chat,
    ChatsProvider,
    SyncEventListener,
    SyncState,
    Tab,
    TeamsView,
    User
} from 'teams-mobile-sdk';

const styles = StyleSheet.create({
    root: {
        flex: 1, 
        marginLeft: 0
    },
    sectionHeaderTitle: {
        fontSize: 16,
        fontWeight: 'bold',
        textAlign: 'left',
        color: '#000000',
        marginLeft: 16,
        marginTop: 17,
        marginBottom: 8
    },
    itemSeparator: {
        backgroundColor: 'rgb(223,222,222)',
        height: 1,
        marginLeft: 0
    }
});

export interface TabsInChatsApiComponentState {
    chatsList: Chat[];
    tabsMap: Map<string, Tab[]>;
    loading: boolean;
}

export class TabsInChatsApiComponent extends TeamsView<any, TabsInChatsApiComponentState>
{
    public appTheme:any;
    constructor (props: any) {
        super(props);
        this.appTheme = this.getApplicationContext().getCurrentAppTheme();
        this.state = {
          chatsList: [],
          tabsMap: new Map(),
          loading: true
        };
    }

    public componentWillMount() {
        // getTabsInChatsInitialSyncState is a no-op for Android OS
        if (Platform.OS === "android") {
            this.getChats();
        } else {
            ChatsProvider.getTabsInChatsInitialSyncState().then((state: SyncState) => {
                if (state === SyncState.COMPLETED) {
                console.log("Initial threads sync completed");
                this.getChats();
                }
                SyncEventListener.registerHandlerForTabsInChatsSync(() => {
                    console.log("Threads sync completed");
                    this.getChats();
                });           
            }).catch(() => {
                    //
            });
        }
    }

    public componentWillUnmount() {
        NativeAppEventEmitter.removeAllListeners();
    }

    public getChats() {
        ChatsProvider.getAllChats().then((chats: Chat[]) => {
            const chatIds = new Array<string>();
            chats.forEach((chat: Chat) => {
                chatIds.push(chat.conversationId);
            });
            ChatsProvider.getTabsForChatsWithIDs(chatIds).then((tabsMap: Map<string, Tab[]>) => {
                const chatsList = new Array<Chat>();
                // filter chats with no tabs
                chats.forEach((chat: Chat) => {
                    if(tabsMap[chat.conversationId] && (tabsMap[chat.conversationId] as Tab[]).length >0 ) {
                        chatsList.push(chat);
                    }
                });
                this.setState({
                    chatsList: chatsList,
                    tabsMap: tabsMap,
                    loading: false
                });
            });
            ChatsProvider.getMembersDetailsForChats(chatIds).then((chatMembersMap: Map<string, User[]>) => {
                console.log(chatMembersMap);
            });
        });
    }

    public renderChatListItem = (item) => {
        const chat = item.item as Chat;
        return(
            <View>
                <Text style={[styles.sectionHeaderTitle,{color: this.appTheme === AppTheme.DARK ? 'white' : 'black'}]}>{chat.displayName}</Text>
                <View style={{ marginLeft: 32 }}>
                    <FlatList
                        data={this.state.tabsMap[chat.conversationId] as Tab[]}
                        showsVerticalScrollIndicator={false}
                        renderItem= {this.renderTabListItem}
                        keyExtractor= {(_, index) => chat.conversationId + index}>
                    </FlatList>
                </View>
            </View>
        );
    }

    public renderTabListItem = (item) => {
        return(<Text style = {{color: this.appTheme === AppTheme.DARK ? 'white' : 'black'}}> {item.item.displayName} </Text>);
    }

    public render() {
        return (
            <View style={styles.root}> 
                {this.state.loading ? (
                <View
                    style={{
                    position: "absolute",
                    marginLeft: Dimensions.get("screen").width / 2 - 10,
                    marginTop: Dimensions.get("screen").height / 2 - 60
                    }}>
                    <ActivityIndicator color={"rgb(98,100,167)"} size="large" />
                </View>
                ):  
                (<View style={{flex: 1}}>
                    <FlatList
                    data={this.state.chatsList}
                    showsVerticalScrollIndicator={false}
                    renderItem= {this.renderChatListItem}
                    ItemSeparatorComponent={ () => <View style={[styles.itemSeparator, { marginLeft: 32}]} /> }
                    keyExtractor= {item => item.conversationId}>
                    </FlatList>
                </View>
                )}
            </View>
        );
    }
}