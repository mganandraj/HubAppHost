export interface Channel {
    /**
     * A unique identifier for the channel. The ID of the General
     * channel in a team is equal to the ID of the team. But, it
     * is recommended to use the isGeneralChannel property of this
     * class to identify the General channel instead of doing the
     * comparison yourself.
     *
     * For e.g.: 19:0477a47b9e7d471e81df13dfaffdfca4@thread.skype
     */
    id: string;
    /**
     * Unique identifier for the channel's Team.
     *
     * For General channels, `id` will equal `teamId`.
     */
    teamId: string;
    /**
     * Name of the channel
     */
    name: string;
    /**
     * Indicates whether the channels is a favorite of the user
     */
    isFavorite: boolean;
    /**
     * Indicates whether the channel is a private channel
     */
    isPrivateChannel: boolean;
    /**
     * Indicates whether this channel is the General channel for the team.
     */
    isGeneralChannel: boolean;
}
