package com.microsoft.skype.teams.storage.tables;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.microsoft.skype.teams.storage.IModel;
/*
import com.microsoft.skype.teams.storage.SkypeTeamsDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
*/

/**
 * Represents React Native Bundles stored
 */
/*@Table(
        database = SkypeTeamsDatabase.class
)*/
// public class RNBundle extends BaseModel implements IModel {
@Entity (primaryKeys = {"appId", "bundleVersion"})
public class RNBundle implements IModel {
    @NonNull
    public String appId;

    @NonNull
    public String bundleVersion;

    //@Column
    public String bundleSource;

    //@Column
    public String packageHashList;

    //@Column
    public String bundleLocation;

    //@Column
    public String manifest;
}
