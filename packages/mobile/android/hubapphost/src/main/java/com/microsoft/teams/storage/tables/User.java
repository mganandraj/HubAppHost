/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.storage.tables;

import android.content.Context;

import com.microsoft.teams.storage.IModel;
//import com.microsoft.teams.storage.SkypeTeamsDatabase;
//import com.microsoft.teams.storage.SkypeTeamsIndexes;
//import com.microsoft.teams.storage.converters.StringArrayConverter;
import com.microsoft.teams.utilities.java.ListUtils;
import com.microsoft.teams.utilities.java.StringUtils;
//import com.raizlabs.android.dbflow.annotation.Column;
//import com.raizlabs.android.dbflow.annotation.ConflictAction;
//import com.raizlabs.android.dbflow.annotation.Index;
//import com.raizlabs.android.dbflow.annotation.IndexGroup;
//import com.raizlabs.android.dbflow.annotation.PrimaryKey;
//import com.raizlabs.android.dbflow.annotation.Table;
//import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Represents a user profile.
 */
//@Table(database = SkypeTeamsDatabase.class,
//        indexGroups = {
//                //@IndexGroup(number = 1, name = SkypeTeamsIndexes.USER_OBJECT_ID_INDEX),
//                //@IndexGroup(number = 2, name = SkypeTeamsIndexes.USER_DISPLAY_NAME_INDEX),
//                //@IndexGroup(number = 3, name = SkypeTeamsIndexes.USER_UPN_INDEX)
//        },
//        insertConflict = ConflictAction.REPLACE)
public class User /*extends BaseModel*/ implements IModel,
        IBaseUser {
    public static final short FLAG_ISIBBARRED = 1 << 0;
    public static final short FLAG_ISBLOCKED = 1 << 1;

//@Column
    public String userLocation;

//@PrimaryKey
    public String mri;

//@PrimaryKey
    public String tenantId;

    //@Index(indexGroups = {1})
//@Column
    public String objectId;

//@Column
    public String description;

    //@Index(indexGroups = {2})
//@Column
    public String displayName;

//@Column
    public String department;

//@Column
    public String developer;

//@Column
    public String email;

//@Column
    public String secondaryEmail;

//@Column
    public String alternativeEmail;

//@Column
    public String mail;

//@Column
    public String givenName;

//@Column
    public String surname;

//@Column
    public String companyName;

//@Column
    public String jobTitle;

//@Column
    public String mobile;

//@Column
    public String physicalDeliveryOfficeName;

//@Column
    public String telephoneNumber;

//@Column
    public String homeNumber;

//@Column
    public boolean accountEnabled;

//@Column
    public String type;

    //@Index(indexGroups = {3})
//@Column
    public String userPrincipalName;

//@Column
    public String profileImageString;

//@Column
    public String imageUri;

//@Column
    public int chatCount;

//@Column
    public int mentionCount;

//@Column
    public int callCount;

//@Column
    public int miscAccessCount;

//@Column
    public long lastSyncTime;

//@Column
    public long lastSyncTimeFeatureSettings;

//@Column
    public boolean isSkypeTeamsUser;

//@Column
    public boolean isPrivateChatEnabled = true;

//@Column(typeConverter = StringArrayConverter.class)
    public String[] capabilities;

//@Column(typeConverter = StringArrayConverter.class)
    public String[] categories;

//@Column
    public String userType;

//@Column
    public String sipProxyAddress;

//@Column
    public boolean isSipDisabled;

//@Column
    public int blockedFlags;

//@Column
    public String homeTenantId;

    // DO NOT RELY ON THE VALUE OF THIS FIELD
    // Used only for receiving response from MT, stored in blockedFlags
    // Use isBlockedUser() instead.
    public boolean isIbBarred;

    // DO NOT RELY ON THE VALUE OF THIS FIELD
    // Used only for receiving response from MT, stored in blockedFlags
    // Use isInformationBarrierBarredUser() instead.
    public boolean isBlocked;

    // (TFL) Status of individual user profile fetch from MT. Available iff isShortProfile is true.
    public int status;

    // (TFL) Indicates whether the user profile returned from MT is a mini profile
    public boolean isShortProfile;

    // (TFL) In-memory - User's name in address book if matched with a device contact
    public String addressBookName;

    // (TFL) In-memory - User's phone in address book if matched with a device contact
    public String addressBookPhone;

    // (TFL) In-memory - User's email in address book if matched with a device contact
    public String addressBookEmail;

//    public SkypeTeamsInfo skypeTeamsInfo;
//
//    public FeatureSettings featureSettings;

    @NonNull
    public String getType() {
        return type;
    }

    @NonNull
    public String getJobTitle() { return jobTitle; }

    @Override
    public String getObjectId() {
        return objectId;
    }

    public String getMri() {
        return mri;
    }

    @Override
    public String getProfileImageString(@NonNull Context context) {
        return profileImageString;
    }

    @Override
    public String getImageUri(@NonNull Context context) {
        return imageUri;
    }

//@Column
    public String coExistenceMode;

//@Column
    public boolean isInterOpChatAllowed; // Specific for sfb interop

    public Phones[] phones;

    /**
     * This will be non null only if user is a saved contact[
     */
    public String contactId;

    /***
     * Get raw display name.
     * @return The raw name.
     */
    public String getDisplayName() {
        return displayName;
    }

    public String getGivenName() {
        return StringUtils.isEmpty(givenName) ? displayName : givenName;
    }

    public boolean shouldBlockChangingPresence() {
        return shouldBlockChangingPresence(coExistenceMode);
    }

    @Override
    public String getUserPrincipalName() {
        return userPrincipalName;
    }

    public boolean isInformationBarrierBarredUser() {
        return isBlockedFlagSet(FLAG_ISIBBARRED);
    }

    public boolean isBlockedUser() {
        return isBlockedFlagSet(FLAG_ISBLOCKED);
    }

    @Override
    public int hashCode() {
        String uniqueIdentifier = this.userPrincipalName;
        if (StringUtils.isEmptyOrWhiteSpace(uniqueIdentifier)) {
            uniqueIdentifier = this.mri;
        }

        if (StringUtils.isEmptyOrWhiteSpace(uniqueIdentifier)) {
            uniqueIdentifier = this.mail;
        }

        if (StringUtils.isEmptyOrWhiteSpace(uniqueIdentifier)) {
            uniqueIdentifier = this.email;
        }

        return !StringUtils.isEmptyOrWhiteSpace(uniqueIdentifier) ? uniqueIdentifier.hashCode() : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;
        return stringEqual(this.userPrincipalName, other.userPrincipalName)
                || stringEqual(this.mri, other.mri)
                || stringEqual(this.email, other.email)
                || stringEqual(this.mail, other.mail)
                || stringEqual(this.email, other.mail)
                || stringEqual(this.mail, other.email);
    }

    public String getEmail() {
        return !StringUtils.isEmptyOrWhiteSpace(email) ? email : userPrincipalName;
    }

    private boolean stringEqual(String one, String two) {
        return one != null
                && two != null
                && !StringUtils.isEmpty(one)
                && !StringUtils.isEmpty(two)
                && one.equalsIgnoreCase(two);
    }

    public int getAccessCount() {
        return callCount + chatCount + mentionCount + miscAccessCount;
    }


    public static String[] convertToMriArray(List<User> users) {
        if (ListUtils.isListNullOrEmpty(users)) {
            return null;
        }

        int count = users.size();
        String[] userMris = new String[count];
        for (int i = 0; i < count; i++) {
            userMris[i] = users.get(i).mri;
        }

        return userMris;
    }

    public static List<String> convertToMriList(List<User> users) {
        List<String> userMris = new ArrayList<>();
        if (!ListUtils.isListNullOrEmpty(users)) {
            for (User user : users) {
                userMris.add(user.mri);
            }
        }
        return userMris;
    }

    public static boolean shouldBlockChangingPresence(String userCoExistenceMode) {
        return !StringUtils.isEmpty(userCoExistenceMode) && (userCoExistenceMode.equalsIgnoreCase("SfBOnly")
                || userCoExistenceMode.equalsIgnoreCase("SfBWithTeamsCollab")
                || userCoExistenceMode.equalsIgnoreCase("SfBWithTeamsCollabAndMeetings"));
    }

    public boolean isBlockedFlagSet(int flag) {
        return (blockedFlags & flag) > 0;
    }

    public void setBlockedFlag(int flag) {
        blockedFlags |= flag;
    }

    public void clearBlockedFlag(int flag) {
        blockedFlags &= ~flag;
    }

    /**
     * This function maps phones array to user object by looking at the type.
     */
    public void constructPhoneNos() {
        if (phones == null || phones.length == 0) {
            return;
        }

        for (Phones phone : phones) {
            switch (phone.type) {
                case "Business":
                    telephoneNumber = phone.number;
                    break;
                case "Mobile":
                    mobile = phone.number;
                    break;
                case "Home":
                    homeNumber = phone.number;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * This function maps server skype teams info data to user isSkypeTeamsUser field
     */
//    public void constructSkypeTeamsInfo() {
//        if (skypeTeamsInfo == null) {
//            return;
//        }
//        this.isSkypeTeamsUser = skypeTeamsInfo.isSkypeTeamsUser;
//    }

    /**
     * Parser class for phones which is part of search user response
     */
    public static class Phones implements Serializable {
        String number;
        String type;

        public String getNumber() {
            return number;
        }

        public String getType() {
            return type;
        }
    }
}