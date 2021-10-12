import * as React from 'react';
import { ActivityIndicator, Dimensions, FlatList, NativeAppEventEmitter, Platform, StyleSheet, Text, View } from 'react-native';

import {
  AppTheme,
  Channel,
  Resource,
  SyncEventListener,
  SyncState,
  Tab,
  Team,
  TeamsAndChannelsProvider,
  TeamsView
} from 'teams-mobile-sdk';

const styles = StyleSheet.create({
  root: {
    flex: 1, 
    marginLeft: 0
  },
  teamRoot: {
    marginTop: 5,
    marginBottom: 5
  },
  teamTitle: {
    fontWeight: 'bold',
    paddingTop: 5,
    paddingBottom: 5,
    paddingLeft: 3,
    marginBottom: 5,
    backgroundColor: '#ddd'
  },
  sectionHeaderContainer: {
    flex: 1,
    backgroundColor: '#f2f2f2',
    justifyContent: 'center'
  },
  sectionHeaderTitle: {
    fontSize: 15,
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

export interface TeamsAndChannelsApiComponentState {
  teamsList: Team[];
  verificationStatus: string;
  filterVerificationStatus: string;
  tabsMap: Map<string, Tab[]>;
  loading: boolean;
}

export class TeamsAndChannelsApiComponent extends TeamsView<any, TeamsAndChannelsApiComponentState> {
  public appTheme: any;
  constructor (props: any) {
    super(props);
    this.appTheme = this.getApplicationContext().getCurrentAppTheme();
    const waitingText: string = Resource.getLocalizedString('waiting');
    this.state = {
      teamsList: [],
      tabsMap: new Map(),
      verificationStatus: waitingText,
      filterVerificationStatus: waitingText,
      loading: true
    };
  }

  public componentWillMount() {
    if (Platform.OS === 'ios') {
      // The getTeamsAndChannelsInitialSyncStateIOS API is only for IOS
      TeamsAndChannelsProvider.getTeamsAndChannelsInitialSyncStateIOS().then((state: SyncState) => {
        if (state === SyncState.COMPLETED) {
          console.log("Initial threads sync completed");
          this.setState({loading: false});
          this.getTeamsAndChannels();
        }
        else {
          SyncEventListener.registerHandlerForTeamsAndChannelsSync(() => {
            console.log("Threads sync completed");
            this.setState({loading: false});
            this.getTeamsAndChannels();
          });
        }
      });
    } else {
      this.setState({loading: false});
      this.getTeamsAndChannels();
    }
    
  }

  public componentWillUnmount() {
    NativeAppEventEmitter.removeAllListeners();
  }

  public getTeamsAndChannels() {
    TeamsAndChannelsProvider.getAllTeams(true).then((teams: Team[]) => {
      const channelsIds = new Array<string>() ;
      teams.forEach((team: Team) => {
        team.channels.forEach((channel: Channel) => {
          channelsIds.push(channel.id);
        });
      });
      TeamsAndChannelsProvider.getTabsForChannelsWithIDs(channelsIds).
        then((tabsListMap: Map<string, Tab[]>) => {
          this.setState({
            teamsList: teams,
            tabsMap: tabsListMap,
            verificationStatus: JSON.stringify(this.verifyTeamResponse(teams.map((v) => v)))
          });
          this.verifyFilteredResponses(teams.map((v) => v.id));
        });
    });
  }

  public renderTeamListItem = (item) => {
    return(
    <View>
      <View style={[styles.sectionHeaderContainer, { backgroundColor: this.appTheme === AppTheme.DARK ? 'black' : 'white' }]}>
              <Text style={[styles.sectionHeaderTitle,{color: this.appTheme === AppTheme.DARK ? 'white' : 'black'}]}>{item.item.name}</Text>
            </View>
      <View style={{ marginLeft: 32 }}>
        <FlatList
              data={item.item.channels as Channel[]}
              showsVerticalScrollIndicator={false}
              renderItem = {this.renderChannelListItem}
              ItemSeparatorComponent={ () => <View style={styles.itemSeparator} /> }
              keyExtractor= {item => item.id}>
        </FlatList>
      </View>
    </View>
    );
  }
  public renderChannelListItem = (item) => {
    const channel = item.item as Channel;
    return(
          <View>
            <Text style = {{color: this.appTheme === AppTheme.DARK ? 'white' : 'black'}}> {channel.name} </Text>
            <View style={{ marginLeft: 32 }}>
              <FlatList
                data={this.state.tabsMap[channel.id] as Tab[]}
                showsVerticalScrollIndicator={false}
                renderItem= {this.renderTabListItem}
                keyExtractor= {item => item.id}>
              </FlatList>
            </View>
          </View>
    );
  }

  public renderTabListItem = (item) => {
    return(<Text style = {{color: this.appTheme === AppTheme.DARK ? 'white' : 'black'}}> {item.item.displayName} </Text>);
  }

  public render () {
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
            <Text style = {{color: this.appTheme === AppTheme.DARK ? 'white' : 'black'}}>Verification: {this.state.verificationStatus}</Text>
            <Text style = {{color: this.appTheme === AppTheme.DARK ? 'white' : 'black'}}>Filter verification: {this.state.filterVerificationStatus}</Text>
            <FlatList
              data={this.state.teamsList}
              showsVerticalScrollIndicator={false}
              renderItem= {this.renderTeamListItem}
              ItemSeparatorComponent={ () => <View style={[styles.itemSeparator, { marginLeft: 32}]} /> }
              keyExtractor= {item => item.id}>
              </FlatList>
          </View>
        )}
      </View>
    );
  }

  public verifyTeamResponse (teams: Team[]): string {
    const failText: string = Resource.getLocalizedString('fail');
    const okText: string = Resource.getLocalizedString('ok');

    teams.forEach(team => {
      if (team.id === undefined
        || team.isFavorite === undefined
        || team.name === undefined
        || team.channels === undefined
        || team.channels.length === 0
        || team.teamPictureUrl === undefined
        || team.teamSharepointSiteUrl === undefined
        || team.order === undefined) {
        return failText;
      }
    });
    return okText;
  }

  public verifyFilteredResponses (teamIds: string[]) {
    TeamsAndChannelsProvider.getTeamsWithIDs([teamIds[0]], false).then((teams: Team[]) => {
      if (teams.length === 1) {
        const filterOkText: string = Resource.getLocalizedString('filter_ok_message');
        this.setState({
          teamsList: this.state.teamsList,
          verificationStatus: this.state.verificationStatus,
          filterVerificationStatus: filterOkText
        });
      } else {
        const filterFailedText: string = Resource.getLocalizedString('filter_failed_message', { actualCount: teams.length });
        this.setState({
          teamsList: this.state.teamsList,
          verificationStatus: this.state.verificationStatus,
          filterVerificationStatus: filterFailedText
        });
      }
    });
  }
}
