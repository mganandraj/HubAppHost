import { ContactList } from "../../models/people/ContactList";
import { ContactsProviderResponse, ContactSyncState, SortOrder, SortType } from "../../models/people/ContactsProviderResponse";
import { PeopleContact } from "../../models/people/PeopleContact";
/**
 * Contacts Provider.
 */
export interface ContactsProviderInterface {
    /**
     * API to get contactLists
     *
     * @param sortType type of the sort
     * @param sortOrder order of the sort
     * @returns {Promise} A promise that returns an array of contactLists
     */
    getContactLists(sortType: SortType, sortOrder: SortOrder): Promise<ContactsProviderResponse<ContactList[]>>;
    /**
     * API to get contacts with pagination
     *
     * @param listId the id of the contact list
     * @param pageNumber number of the page
     * @param withPageSize size of the page
     * @param sortType type of the sort
     * @param sortOrder order of the sort
     * @returns {Promise} A promise that returns an array of contacts
     */
    getPagesOfContactsInList(listId: string, pageNumber: Number, withPageSize: Number, sortType: SortType, sortOrder: SortOrder): Promise<ContactsProviderResponse<PeopleContact[]>>;
    /**
     * API to get favourite contacts
     *
     * @param sortType type of the sort
     * @param sortOrder order of the sort
     * @returns {Promise} A promise that returns an array of contacts
     */
    getFavoriteContacts(sortType: SortType, sortOrder: SortOrder): Promise<ContactsProviderResponse<PeopleContact[]>>;
    /**
     * API to get contacts of a contact list
     *
     * @param listId id of the contact list
     * @param sortType type of the sort
     * @param sortOrder order of the sort
     * @returns {Promise} A promise that returns an array of contacts
     */
    getContactsInList(listId: string, sortType: SortType, sortOrder: SortOrder): Promise<ContactsProviderResponse<PeopleContact[]>>;
    /**
     * API to get contacts details
     *
     * @param contactId id of the contact
     * @returns {Promise} A promise that returns the contact details
     */
    getContactDetails(contactId: string): Promise<ContactsProviderResponse<PeopleContact>>;
    /**
     * API to save the contact list in the db
     *
     * @param contactList contact list which needs to be saved
     * @returns {Promise} A promise that returns whether save to db succeded or not
     */
    saveContactList(contactList: ContactList): Promise<ContactsProviderResponse<any>>;
    /**
     * API to delete the contact list in the db
     *
     * @param contactListId id of the contact list which needs to be deleted
     * @returns {Promise} A promise that returns whether save to db succeded or not
     *
     */
    deleteContactList(contactListId: string): Promise<ContactsProviderResponse<any>>;
    /**
     * API to save a contact which is created/edited in the db
     *
     * @param contact contact which needs to be saved
     * @returns {Promise} A promise that returns whether save to db succeded or not
     */
    saveContact(Contact: PeopleContact): Promise<ContactsProviderResponse<any>>;
    /**
     * API to sync contacts, contacts lists in db
     *
     */
    syncContacts(): any;
    /**
     * API to get the sync state of contacts
     * @returns {Promise} A promise that resolves to one of the contact sync states
     */
    getContactSyncState(): Promise<ContactSyncState>;
}
