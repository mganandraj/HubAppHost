package com.microsoft.skype.teams.storage.tables;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.microsoft.skype.teams.storage.IModel;
//import com.microsoft.skype.teams.storage.SkypeTeamsDatabase;
//import com.raizlabs.android.dbflow.annotation.Column;
//import com.raizlabs.android.dbflow.annotation.PrimaryKey;
//import com.raizlabs.android.dbflow.annotation.Table;
//import com.raizlabs.android.dbflow.structure.BaseModel;

///**
// * Represents React Native Apps
// */
//@Table(
//        database = SkypeTeamsDatabase.class
//)
@Entity
public class RNApp /*extends BaseModel */implements IModel {
    @NonNull
    @PrimaryKey
    public String moduleId;

//    @PrimaryKey
    public String tenantId;

    //@Column
    public String bundleVersion;

    //@Column
    public long lastUpdateCheckedTimeInMillis;
}
