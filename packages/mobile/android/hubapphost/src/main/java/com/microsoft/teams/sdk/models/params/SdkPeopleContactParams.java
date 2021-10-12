/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk.models.params;

import android.os.Bundle;
import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.microsoft.teams.sdk.react.WritableMapUtils;
import com.microsoft.teams.storage.tables.Contact;

import java.util.List;

/**
 * Class to wrap the contacts response params to be sent over the RN bridge
 */
public class SdkPeopleContactParams implements SdkAppWriteableParams {

    public static final int SUCCESS = 200;
    public static final int UNABLE_TO_ACCESS_DATA = 301;

    private List<Contact> mContacts;
    private int mStatusCode;

    public SdkPeopleContactParams(int statusCode, List<Contact> contacts) {
        this.mContacts = contacts;
        this.mStatusCode = statusCode;
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        Bundle bundle = Arguments.toBundle(toMap());
        return bundle != null ? bundle : new Bundle();
    }

    @NonNull
    @Override
    public WritableMap toMap() {
        WritableNativeArray contactsArray = null;
        if (mContacts != null) {
            contactsArray = new WritableNativeArray();
            for (Contact contact : mContacts) {
                contactsArray.pushMap(WritableMapUtils.parseToMap(contact, Contact.class));
            }
        }
        return WritableMapUtils.getDefaultResponseMap(mStatusCode, contactsArray);
    }
}
