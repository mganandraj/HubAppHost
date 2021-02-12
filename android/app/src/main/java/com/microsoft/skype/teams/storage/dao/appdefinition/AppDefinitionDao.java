/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.storage.dao.appdefinition;

import com.microsoft.skype.teams.storage.dao.IBaseDao;
import com.microsoft.skype.teams.storage.tables.AppDefinition;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/** Represents an app definition (bot or tab) */
public interface AppDefinitionDao extends IBaseDao<AppDefinition> {
    void save(AppDefinition appDefinition);

    void purge();

    @Nullable
    AppDefinition fromId(@Nullable String appId);

    @Nullable
    AppDefinition fromId(@Nullable String appId, boolean includeChatAppDefinitions);

    @Nullable
    AppDefinition fromBotId(@Nullable String botId);

    @Nullable
    AppDefinition fromBotId(@Nullable String botId, boolean includeChatAppDefinitions);

    @Nullable
    List<AppDefinition> getAppDefinitionListFromBotId(@Nullable String botId);

    @NonNull
    Map<String, AppDefinition> fromIds(@Nullable List<String> ids);

    @Nullable
    String getCleanBotId(@Nullable String botId);

    boolean isBotId(@Nullable String botId);

    Map<String, AppDefinition> fromBotIds(ArrayList<String> botIds);

    List<AppDefinition> fromBotList(List<String> botIds);

    boolean isCustomBot(AppDefinition appDefinition);

    List<AppDefinition> getCustomBotsForTeam(@NonNull String threadId);

    @Nullable
    List<AppDefinition> getAppDefinitionsWithTeamEntitlement(String parentThreadId);

    void deleteAppsNotInCollection(@NonNull Collection<String> appIds);

    List<AppDefinition> getAppDefinitionsWithMobileModules();
}
