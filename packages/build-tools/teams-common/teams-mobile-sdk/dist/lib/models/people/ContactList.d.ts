export declare enum ContactListType {
    /**
     * SFBFAVOURITES - shown as Favorites in both old and new clients
     */
    SFBFAVOURITES = "SfbFavorites",
    /**
     * FAVOURITES - only shown by old clients as speed dials
     */
    FAVOURITES = "Favorites",
    /**
     * OTHERCONTACTS - new clients will treat this as user created custom list
     */
    OTHERCONTACTS = "OtherContacts",
    /**
     * TAGGED - new clients will treat this as user created custom list
     */
    TAGGED = "Tagged",
    /**
     * CUSTOM - new clients will treat this as user created custom list
     */
    CUSTOM = "Custom"
}
/**
 * Represents the contact list
 */
export interface ContactList {
    id: string;
    eTag: string;
    contactListType: ContactListType;
    displayName: string;
    createdDate: string;
}
