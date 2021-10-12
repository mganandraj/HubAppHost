/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.storage.tables;

import com.microsoft.teams.storage.IModel;
//import com.microsoft.teams.storage.SkypeTeamsDatabase;
//import com.raizlabs.android.dbflow.annotation.Column;
//import com.raizlabs.android.dbflow.annotation.ModelContainer;
//import com.raizlabs.android.dbflow.annotation.PrimaryKey;
//import com.raizlabs.android.dbflow.annotation.Table;
//import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Represents the Thread Properties for each Thread.
 */
//@ModelContainer
//@Table(
//        database = SkypeTeamsDatabase.class
//)
public class Thread /*extends BaseModel*/ implements IModel {

    public static final String THREAD_ID_PREFIX = "19:";
    public static final String COLUMN_DYNAMIC_MEMBERSHIP = "dynamicMembership";
    public static final String COLUMN_EXTENSION_DEFINITION_CONTAINER = "extensionDefinitionContainer";

//    @PrimaryKey
    public String threadId;
//    @Column
    public String tenantId;
//    @Column
    public String aadGroupId;
//    @Column
    public String displayName;
//    @Column
    public String sharepointUrl;
//    @Column
    public String relativeSharepointUrl;
//    @Column
    public String sharepointRootLibrary;
//    @Column
    public String createdBy;
//    @Column
    public Date createdTime;
//    @Column
    public Date modifiedTime;
//    @Column
    public long version;
//    @Column
    public long rosterVersion;
//    @Column
    public boolean isDeleted;
//    @Column
    public boolean allowTeamMentions;
//    @Column
    public String dynamicMembership;
//    @Column
    public boolean allowChannelMentions;
//    @Column
    public long createdAt;
//    @Column
    public long retentionHorizon;
//    @Column
    public boolean isDisabled;
//    @Column
    public String extensionDefinitionContainer; // added in V143
//    @Column
    public String substrateGroupId;
    // Since we already use tenantId for other reasons, we prefix "thread" to mean the tenantId where the
    // thread is hosted for a shared channel, not necessarily the same tenantId as the logged in user.
//    @Column
    public String threadTenantId;
//    @Column
    public String meetingChatPolicy;

    @Override
    public String toString() {
        return "Thread{"
                + "threadId='"
                + threadId
                + '\''
                //                + ", displayName='" + displayName + '\''
                + ", sharepointUrl='"
                + sharepointUrl
                + '\''
                + ", relativeSharepointUrl='"
                + relativeSharepointUrl
                + '\''
                //                + ", pictureUrl='" + pictureUrl + '\''
                //                + ", createdBy='" + createdBy + '\''
                + ", createdTime="
                + createdTime
                + ", modifiedTime="
                + modifiedTime
                + ", version="
                + version
                + ", rosterVersion="
                + rosterVersion
                + ", isDeleted="
                + isDeleted
                + '}';
    }

    // SpaceTypes is a flagged Enum.
  /*
  1 - 01 -> Private
  2 - 10 -> TenantWide
  3 - 11 -> Private + TenantWide
  */
    public enum SpaceTypes {
        Private,
        TenantWide;

        public final int flag;

        SpaceTypes() {
            this.flag = 1 << this.ordinal();
        }
    }
}