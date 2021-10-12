/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.storage.tables;

import com.microsoft.teams.storage.IModel;
//import com.microsoft.teams.storage.SkypeTeamsDatabase;
//import com.raizlabs.android.dbflow.annotation.Column;
//import com.raizlabs.android.dbflow.annotation.PrimaryKey;
//import com.raizlabs.android.dbflow.annotation.Table;
//import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Represents an team entitlements for app.
 */
//)
public class TeamEntitlement/* extends BaseModel */implements IModel {
//    @PrimaryKey
    public String threadId;

//    @PrimaryKey
    public String appId;

//    @Column
    public String tenantId;

//    @Column
    public String status;
}