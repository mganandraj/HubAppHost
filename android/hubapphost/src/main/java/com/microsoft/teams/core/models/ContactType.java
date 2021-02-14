/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.core.models;

/**
 * Represents the type of a contact
 */
public class ContactType {
    public final String id;
    public final String name;

    public ContactType(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
