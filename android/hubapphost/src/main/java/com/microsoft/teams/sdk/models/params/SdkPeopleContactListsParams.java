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
import com.microsoft.teams.storage.tables.ContactList;

import java.util.List;

/**
 * Class to wrap the contact lists response params to be sent over the RN bridge
 */
public class SdkPeopleContactListsParams implements SdkAppWriteableParams {
    public static final int SUCCESS = 200;
    public static final int UNABLE_TO_ACCESS_DATA = 301;

    private List<ContactList> mContactLists;
    private int mStatusCode;

    public SdkPeopleContactListsParams(int statusCode, List<ContactList> contactLists) {
        this.mContactLists = contactLists;
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
        WritableNativeArray contactListsArray = null;
        if (mContactLists != null) {
            contactListsArray = new WritableNativeArray();
            for (ContactList contactList : mContactLists) {
                contactListsArray.pushMap(WritableMapUtils.parseToMap(contactList, ContactList.class));
            }
        }
        return WritableMapUtils.getDefaultResponseMap(mStatusCode, contactListsArray);
    }
}
