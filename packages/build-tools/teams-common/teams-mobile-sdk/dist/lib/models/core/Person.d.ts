/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
import { ContactCardItem } from "../contact/ContactCardItem";
/**
 * The user type of a Person object.
 */
export declare type UserType = "Admin" | "User" | "Guest" | "NonGuest" | "ADUser" | "FTLUser" | "Federated" | "TFLConsumer" | "Education" | "SkypeConsumer" | "Shadow";
/**
 * The real time presence status of a person.
 */
export declare type PresenceStatus = "Available" | "Busy" | "DoNotDisturb" | "BeRightBack" | "Away" | "Offline" | "Reset" | "OffWork" | "Unknown" | "OnThePhone" | "InAConferenceCall" | "InAMeeting" | "Presenting" | "Idle" | "OOF" | "OOFAvailable" | "OOFBusy" | "OOFDoNotDisturb" | "OOFInACall" | "OOFInAConferenceCall" | "OOFPresenting" | "OnShift" | "OffShift" | "Blocked";
/**
 * The address type of a given Address object.
 */
export declare type AddressType = "Business" | "Home";
/**
 * An object representing a postal address.
 */
export interface Address {
    /**
     * The address type.
     */
    type: AddressType;
    /**
     * The post office box.
     */
    postOfficeBox: string;
    /**
     * The street address.
     */
    street: string;
    /**
     * The city name.
     */
    city: string;
    /**
     * The state name.
     */
    state: string;
    /**
     * The country or region name.
     */
    countryOrRegion: string;
    /**
     * The postal code.
     */
    postalCode: string;
}
/**
 * The phone number type of a given Phone object.
 */
export declare type PhoneType = "Business" | "Mobile" | "Home";
/**
 * An object representing a phone number.
 */
export interface Phone {
    /**
     * The phone number type
     */
    type: PhoneType;
    /**
     * The phone number
     */
    number: string;
}
/**
 * An object containing the Identifiers of a Person.
 * The base class for Person, PersonProfilePicture, PersonPresenceStatus, etc.
 */
export interface PersonIds {
    /**
     * Teams MRI.
     */
    mri: string;
    /**
     * User Principle Name.
     */
    upn: string;
    /**
     * Azure Active Directory ID.
     */
    aadId: string;
}
/**
 * An object containing a Person profile picture.
 */
export interface PersonProfilePicture extends PersonIds {
    /**
     * The URL of the profile picture.
     */
    profilePictureUrl?: string;
}
/**
 * An object containing a Person's real time presence status.
 */
export interface PersonPresenceStatus extends PersonIds {
    /**
     * The Person's realtime presence status: available, away, etc.
     */
    presenceStatus?: PresenceStatus;
    /**
     * The Person's custom status message.
     */
    customStatusMessage?: string;
    /**
     * The time the custom status message was set in ISO 8601
     */
    customStatusMessageTime?: string;
    /**
     * The Person's Out of Office message.
     */
    oofStatusMessage?: string;
    /**
     * The time the Out of Office message was set in ISO 8601
     */
    oofStatusMessageTime?: string;
}
/**
 * An object containing a Person's Contact Metadata, such as "Shifts" data.
 */
export interface PersonContactMetadata extends PersonIds {
    /**
     * A dictionary of ContactCardItem arrays.
     * The key of the dictionary is the name of the feature providing the metadata such as "shifts".
     * The array of ContactCardItem produced by the metadata provider.
     */
    contactMetadata?: {
        [name: string]: ContactCardItem[];
    };
}
/**
 * An object containing a Person's contact details.
 */
export interface Person extends PersonIds {
    /**
     * The Person's first name.
     */
    firstName: string;
    /**
     * The Person's last name.
     */
    lastName: string;
    /**
     * The Person's display name.
     */
    displayName: string;
    /**
     * The Person's tenant ID.
     */
    tenantId: string;
    /**
     * The Person's office location.
     */
    officeLocation: string;
    /**
     * The Person's job title
     */
    jobTitle: string;
    /**
     * The Person's company name
     */
    company: string;
    /**
     * The Persona's department name
     */
    department: string;
    /**
     * An array of the Person's postal addresses.
     */
    postalAddresses: Address[];
    /**
     * An array of the Person's phone numbers.
     */
    phoneNumbers: Phone[];
    /**
     * The Person's primary email address.
     */
    email: string;
    /**
     * An array of the Person's additional email addresses.
     */
    emails: string[];
    /**
     * The Person's contact notes
     */
    contactNotes: string;
    /**
     * The user type of the Person
     */
    userType: UserType;
    /**
     * True if this Person contact is blocked by the current signed in user.
     */
    isBlocked: boolean;
    /**
     * True if this Person contact is in the current signed in user's speed dial list.
     */
    isSpeedDialContact: boolean;
}
