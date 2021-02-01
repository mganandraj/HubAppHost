package com.microsoft.skype.teams.storage.tables;

import androidx.annotation.StringDef;
import androidx.annotation.VisibleForTesting;

import com.microsoft.skype.teams.storage.IModel;
// import com.microsoft.skype.teams.storage.SkypeTeamsDatabase;
//import com.microsoft.skype.teams.storage.converters.ContactAddressConverter;
//import com.microsoft.skype.teams.storage.converters.ContactPhoneConverter;
//import com.microsoft.skype.teams.storage.converters.StringListConverter;
import com.microsoft.skype.teams.utilities.java.ListUtils;
//import com.raizlabs.android.dbflow.annotation.Column;
//import com.raizlabs.android.dbflow.annotation.ModelContainer;
//import com.raizlabs.android.dbflow.annotation.PrimaryKey;
//import com.raizlabs.android.dbflow.annotation.Table;
//import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Table to store info of contacts
 */
//@Table(
//        database = SkypeTeamsDatabase.class
//)
//@ModelContainer
public class Contact /*extends BaseModel*/ implements IModel {
//    @Column
//    @PrimaryKey
    public String id;

//    @Column
    public String tenantId;

//    @Column
    public String eTag;

//    @Column
    public String firstName;

//    @Column
    public String lastName;

//    @Column
    public String title;

//    @Column
    public String displayName;

//    @Column
    public String nickName;

//    @Column
    public String middleName;

//    @Column
    public String mri;

//    @Column
    public String sipAddress;

//    @Column
    public @ContactType
    String contactType;

//    @Column
    public String upn;

//    @Column
    public boolean isTeamsFavorite;

//    @Column(typeConverter = StringListConverter.class)
    @VisibleForTesting
    public List<String> contactListIds;

//    @Column
    public String location;

//    @Column
    public String jobTitle;

//    @Column
    public String assistant;

//    @Column
    public String companyName;

//    @Column
    public String department;

//    @Column
    public String manager;

//    @Column(typeConverter = ContactAddressConverter.class)
    public Address homeAddress;

//    @Column(typeConverter = ContactAddressConverter.class)
    public Address businessAddress;

//    @Column(typeConverter = ContactAddressConverter.class)
    public Address otherAddress;

//    @Column(typeConverter = ContactPhoneConverter.class)
    public List<Phone> phones;

//    @Column(typeConverter = StringListConverter.class)
    public List<String> emails;

    public String creationDate;

//    @Column
    public long creationDateLong;

    public String modifiedDate;

//    @Column
    public long modifiedDateLong;

//    @Column
    public String notes;

    public List<String> getContactListIds() {
        ArrayList<String> localContactListIds = new ArrayList<>();
        if (ListUtils.isListNullOrEmpty(contactListIds)) {
            return localContactListIds;
        }
        localContactListIds.addAll(contactListIds);
        return localContactListIds;
    }

    public void setContactListIds(List<String> contactListIds) {
        this.contactListIds = contactListIds;
    }

    /**
     * Model for contact phones
     */
    public static class Phone implements Serializable {
        public @PhoneType
        String type;
        public String number;
    }

    /**
     * Model for address
     */
    public static class Address implements Serializable {
        public @AddressType
        String addressType;
        public String postOfficeBox;
        public String street;
        public String city;
        public String countryOrRegion;
        public String postalCode;
    }

    @StringDef(value = {
            AddressType.NONE,
            AddressType.HOME,
            AddressType.BUSINESS,
            AddressType.OTHER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AddressType {
        String NONE = "none";
        String HOME = "home";
        String BUSINESS = "business";
        String OTHER = "other";
    }

    @StringDef(value = {
            PhoneType.NONE,
            PhoneType.HOME,
            PhoneType.BUSINESS,
            PhoneType.MOBILE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PhoneType {
        String NONE = "none";
        String HOME = "home";
        String BUSINESS = "business";
        String MOBILE = "mobile";
    }

    @StringDef(value = {
            ContactType.UNKNOWN,
            ContactType.EXTERNAL,
            ContactType.FEDERATED_ONPREM,
            ContactType.FEDERATED_ONLINE,
            ContactType.IN_TENANT
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface ContactType {
        String UNKNOWN = "unknown";
        String EXTERNAL = "external";
        String FEDERATED_ONPREM = "federatedOnPrem";
        String FEDERATED_ONLINE = "federatedOnline";
        String IN_TENANT = "inTenant";
    }

}

