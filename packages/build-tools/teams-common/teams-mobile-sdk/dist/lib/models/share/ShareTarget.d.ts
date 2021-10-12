/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
/**
 * Represents the type of a share target.
 */
export declare enum ShareTargetType {
    CHANNEL = 0,
    PERSON = 1,
    GROUP = 2,
    PRIVATE_MEETING = 3
}
interface Team {
    /**
     * A unique identifier for the team.
     * For e.g: 19:0477a4749e7d472e81af13df2f9d0ca4@thread.skype
     */
    id: string;
    /**
     * Name of the team
     */
    name: string;
}
/**
 * Represents a share target in Teams. A share target can
 * be a user, group chat, meeting chat or channel.
 */
export interface ShareTarget {
    /**
     * The type of the target.
     */
    type: ShareTargetType;
    /**
     * Unique identifier for the share target. This can
     * either be a user MRI or a thread ID.
     */
    id: string;
    /**
     * The name of the selected chat, group chat or channel
     */
    displayName: string;
    /**
     * The team of the selected channel. Would be null in case of chat/group chat.
     */
    team?: Team;
    /**
     * The url of the icon to be displayed for chat or channel.
     */
    icon?: string;
}
export {};
