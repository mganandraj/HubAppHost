package com.microsoft.skype.teams.storage.tables;

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
public class RNBundle implements IModel {
    //@PrimaryKey
    public String appId;

    //@PrimaryKey
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
