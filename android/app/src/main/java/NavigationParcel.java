/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.androidutils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

/**
 * A parcel implementation that wraps navigation parameters as a parcelable object
 */
public class NavigationParcel implements Parcelable {
    /**
     * The default key to use for using NavigationParcel as an Extra
     */
    public static final String NAVIGATION_PARAMS = "Navigation.Parameters";

    /**
     * Key for group members.
     */
    public static final String PARAM_MEMBERS = "members";

    /**
     * Key for a conversation's thread ID.
     */
    public static final String PARAM_THREAD_ID = "threadId";

    /**
     * Creates an instance of the NavigationParcel class from a given parcel input
     */
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        /**
         * @see android.os.Parcelable.Creator#createFromParcel(android.os.Parcel)
         */
        public NavigationParcel createFromParcel(Parcel in) {
            return new NavigationParcel(in);
        }

        /**
         * @see android.os.Parcelable.Creator#newArray(int)
         */
        public NavigationParcel[] newArray(int size) {
            return new NavigationParcel[size];
        }
    };

    /**
     * Field for keeping the parameters
     */
    public final Map<String, Object> parameters;

    /**
     * Initializes a new instance of the NavigationParcel class with a given dictionary of parameters
     *
     * @param objects Navigation parameters
     */
    public NavigationParcel(Map<String, Object> objects) {
        parameters = objects;
    }

    /**
     * Initializes a new instance of the NavigationParcel class with a given parcel
     */
    private NavigationParcel(Parcel in) {
        parameters = (Map<String, Object>) ParcelUtilities.readObject(in);
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public final int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param out The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     * May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public final void writeToParcel(Parcel out, int flags) {
        ParcelUtilities.writeObject(out, parameters);
    }
}