import { Person, PersonContactMetadata, PersonPresenceStatus, PersonProfilePicture } from "../../models/core/Person";
/**
 * A user identifier type.
 */
export declare type IdType = 'aadUpn' | 'aadObjectId' | 'mri';
/**
 * A tuple containing a single user ID and its type.
 */
export interface UserId {
    userId: string;
    userIdType: IdType;
}
/**
 * People information provider
 *
 * Method in this interface has equivalents in native code.
 *
 * See SdkPeopleProviderModule.java for Android.
 *
 * See SDKPeopleProviderModule.m for iOS.
 *
 * > [!IMPORTANT]
 * > PeopleProviderInterface is under active development. Please, expect
 * frequent breaking changes and use this module at your discretion.
 */
export interface PeopleProviderInterface {
    /**
     * This method fetches the people information.
     *
     * @param userIds User IDs to fetch
     * @returns {Promise} A promise that returns the people information when resolved
     */
    getPeopleDetails(UserIds: UserId[]): Promise<Person[]>;
    /**
     * This method fetches profile picture urls.
     *
     * @param userIds User IDs to fetch
     * @returns {Promise} A promise that returns the profile picture urls when resolved
     */
    getProfilePictures(UserIds: UserId[]): Promise<PersonProfilePicture[]>;
    /**
     * This method fetches people presence information.
     *
     * @param userIds User IDs to fetch
     * @returns {Promise} A promise that returns the presense info when resolved
     */
    getPresenceStatuses(UserIds: UserId[]): Promise<PersonPresenceStatus[]>;
    /**
     * This method fetches people metadata such as Shifts metadata.
     *
     * @param userIds User IDs to fetch
     * @returns {Promise} A promise that returns the metadata info when resolved
     */
    getContactMetadata(UserIds: UserId[]): Promise<PersonContactMetadata[]>;
    /**
     * API to open contact metadata link such as Shifts "Set Availablility"
     * @param userMri User MRI
     * @param contactMetadataType The contact metadata provider such as "shifts"
     * @param contentDescription The link descriptor such as "staffhubAvailabilityKey"
     */
    openContactMetadataLink(UserId: UserId, contactMetadataType: string, contentDescription: string): any;
    /**
     * API to view profile picture.
     *
     * @param userId User ID to view the profile picture for
     * @returns {Promise} A 'void' promise that resolves when profile picture is removed successfully
     */
    viewProfilePictureForPerson(UserId: UserId): Promise<void>;
}
