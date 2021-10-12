"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ContactListType = void 0;
var ContactListType;
(function (ContactListType) {
    /**
     * SFBFAVOURITES - shown as Favorites in both old and new clients
     */
    ContactListType["SFBFAVOURITES"] = "SfbFavorites";
    /**
     * FAVOURITES - only shown by old clients as speed dials
     */
    ContactListType["FAVOURITES"] = "Favorites";
    /**
     * OTHERCONTACTS - new clients will treat this as user created custom list
     */
    ContactListType["OTHERCONTACTS"] = "OtherContacts";
    /**
     * TAGGED - new clients will treat this as user created custom list
     */
    ContactListType["TAGGED"] = "Tagged";
    /**
     * CUSTOM - new clients will treat this as user created custom list
     */
    ContactListType["CUSTOM"] = "Custom";
})(ContactListType = exports.ContactListType || (exports.ContactListType = {}));
