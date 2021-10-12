/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
export declare enum TeamsMessageCardType {
    HERO_CARD = "HeroCard",
    THUMBNAIL_CARD = "ThumbnailCard",
    SWIFT_CARD = "SwiftCard",
    SWIFT_CONNECTOR_CARD = "SwiftConnectorCard",
    PROFILE_CARD = "ProfileCard",
    LIST_CARD = "ListCard",
    SIGN_IN_CARD = "SignInCard",
    ADAPTIVE_CARD = "AdaptiveCard",
    CODE_SNIPPET_CARD = "CodeSnippetCard",
    AUDIO_CARD = "AudioCard"
}
export interface TeamsMessagingCard {
    /**
     * Cards json  which has to be attached
     */
    cardJson: string;
    /**
     * Required if `messageType` equals `Card`
     * Indicates type of card
     */
    cardType: TeamsMessageCardType;
}
