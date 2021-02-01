/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.core.models;

/**
 * Represents the profile of a contact
 */
public class ContactProfile {
    public final String id;
    public final String contactId;
    public final String name;
    public final String type;
    public final String description;
    public final String phoneNumber;
    public final String email;
    public final String avatarUrl;

    public ContactProfile(String id, String name, String type, String description, String phoneNumber, String email, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.contactId = null;
        this.type = type;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.avatarUrl = avatarUrl;
    }

    public ContactProfile(String id, String contactId, String name, String type, String description, String phoneNumber, String email, String avatarUrl) {
        this.id = id;
        this.contactId = contactId;
        this.name = name;
        this.type = type;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.avatarUrl = avatarUrl;
    }
}
