package com.microsoft.skype.teams.storage.tables;

import com.microsoft.skype.teams.storage.IModel;
//import com.microsoft.skype.teams.storage.SkypeTeamsDatabase;
//import com.raizlabs.android.dbflow.annotation.Column;
//import com.raizlabs.android.dbflow.annotation.ModelContainer;
//import com.raizlabs.android.dbflow.annotation.PrimaryKey;
//import com.raizlabs.android.dbflow.annotation.Table;
//import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Table to store contact lists of user
 */
//@Table(
//        database = SkypeTeamsDatabase.class
//)
//@ModelContainer
public class ContactList /*extends BaseModel*/ implements IModel {
    //@Column
    //@PrimaryKey
    public String id;

    //@Column
    public String tenantId;

    //@Column
    public String contactListType;

    //@Column
    public String createdDate;

    //@Column
    public String displayName;

    //@Column
    public String eTag;
}
