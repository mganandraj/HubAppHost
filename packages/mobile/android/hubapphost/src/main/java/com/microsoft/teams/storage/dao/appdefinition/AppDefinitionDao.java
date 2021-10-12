/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.storage.dao.appdefinition;

import com.microsoft.teams.storage.dao.IBaseDao;
import com.microsoft.teams.storage.tables.AppDefinition;

import androidx.annotation.Nullable;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/** Represents an app definition (bot or tab) */
@Dao
public interface AppDefinitionDao extends IBaseDao<AppDefinition> {

    // Note : In teams code, there is only one "save" method which is expected to "Update or Add".
    // It is hard to do it with Room db, hence simplifying the flow by adding an "add method" and renaming "save" to update (to ensure we udpate all callsites)

    @Insert
    void add(AppDefinition appDefinition);

    @Query("DELETE FROM AppDefinition")
    void purge();

    @Nullable
    @Query("SELECT * FROM AppDefinition WHERE appId=:appId LIMIT 1")
    AppDefinition fromId(@Nullable String appId);

//    @Nullable
//    @Query("SELECT * FROM AppDefinition WHERE appId=:appId LIMIT 1")
//    AppDefinition fromId(@Nullable String appId, boolean includeChatAppDefinitions);

//    @Nullable
//    @Query("SELECT * FROM AppDefinition WHERE botId=:botId LIMIT 1")
//    AppDefinition fromBotId(@Nullable String botId);
//
//    @Nullable
//    @Query("SELECT * FROM AppDefinition WHERE botId=:botId LIMIT 1")
//    AppDefinition fromBotId(@Nullable String botId, boolean includeChatAppDefinitions);

//    @Nullable
//    @Query("SELECT * FROM AppDefinition WHERE botId=:botId")
//    List<AppDefinition> getAppDefinitionListFromBotId(@Nullable String botId);

//    @NonNull
//    Map<String, AppDefinition> fromIds(@Nullable List<String> ids);
//
//    @Nullable
//    String getCleanBotId(@Nullable String botId);
//
//    boolean isBotId(@Nullable String botId);
//
//    Map<String, AppDefinition> fromBotIds(ArrayList<String> botIds);
//
//    List<AppDefinition> fromBotList(List<String> botIds);
//
//    boolean isCustomBot(AppDefinition appDefinition);
//
//    List<AppDefinition> getCustomBotsForTeam(@NonNull String threadId);
//
//    @Nullable
//    List<AppDefinition> getAppDefinitionsWithTeamEntitlement(String parentThreadId);
//
//    void deleteAppsNotInCollection(@NonNull Collection<String> appIds);
//
    @Query("SELECT * FROM AppDefinition")
    List<AppDefinition> getAppDefinitionsWithMobileModules();
}
