import { Channel } from './Channel';
export interface Team {
    /**
     * A unique identifier for the team.
     * For e.g: 19:0477a4749e7d472e81af13df2f9d0ca4@thread.skype
     */
    id: string;
    /**
     * Name of the team
     */
    name: string;
    /**
     * The Office 365 group ID for the team with which the content is associated.
     */
    groupId?: string;
    /**
     * Indicates whether the user has marked this team as favorite.
     */
    isFavorite: boolean;
    /**
     * URL of the team thumbnail picture
     */
    teamPictureUrl: string;
    /**
     * The URL of the root of the team Sharepoint site.
     * For e.g: https://microsoft.sharepoint.com/teams/SomeTeamNameHere
     */
    teamSharepointSiteUrl: string;
    /**
     * Indicates the relative position in which this team appears in the
     * teams list. The user can re-order teams. Sorting the teams in
     * increasing order of this property gives the user selected order of teams
     * in the teams list.
     */
    order: number;
    /**
     * List of channels in this team. There is at least one channel in every team.
     */
    channels: Channel[];
}
