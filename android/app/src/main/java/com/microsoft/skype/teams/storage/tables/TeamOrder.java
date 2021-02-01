/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.storage.tables;

import com.microsoft.skype.teams.storage.IModel;
//import com.microsoft.skype.teams.storage.SkypeTeamsDatabase;
//import com.raizlabs.android.dbflow.annotation.Column;
//import com.raizlabs.android.dbflow.annotation.PrimaryKey;
//import com.raizlabs.android.dbflow.annotation.Table;
//import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Represents the Team Order for Conversations.
 */
//@Table(
//        database = SkypeTeamsDatabase.class
//)
public class TeamOrder /*extends BaseModel*/ implements IModel {
    //@PrimaryKey
    public String teamId;

    //@Column
    public String tenantId;

    //@Column
    public int order;

    @Override
    public String toString() {
        return "TeamOrder{" + "teamId='" + teamId + '\'' + ", order=" + order + '}';
    }
}