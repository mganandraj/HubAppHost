# Teams and channels Provider
Teams Mobile SDK provides `TeamsAndChannelsProvider` to enable access to the device's contacts.

## Methods

### getAllTeams (includeChannels: boolean): Promise<Team[]>
Returns all teams the user is a part of.

### getTeamsWithIDs (teamIDs: string[], includeChannels: boolean): Promise<Team[]>
Returns all teams with the given IDs that the user is a part of.

### getTabsForChannelsWithIDs (channelIDs: string[]): Promise< Map<string, Tab[]>>
Every channel has multiple tabs. The function returns a `Map` with channel ID as key and tabs list as value. 

#### Example
```typescript
import { TeamsAndChannelsProvider, Team } from 'teams-mobile-sdk';

...

TeamsAndChannelsProvider.getAllTeams(true).then((teams: Team[]) => {
    // do something with the response
});

const channelIDs = [ 'channel1', 'channel2', ... ]; // a list of channel IDs
TeamsAndChannelsProvider.getTabsForChannelsWithIDs(channelIDs).then((tabsListMap: Map<string, Tab[]>) => {
    // do something with the response
});
```

See <code>[API docs](xref:teams-mobile-sdk.TeamsAndChannelsProviderInterface)</code> to learn more.
