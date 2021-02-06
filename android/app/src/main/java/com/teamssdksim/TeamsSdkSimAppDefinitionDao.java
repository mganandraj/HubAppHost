package com.teamssdksim;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.skype.teams.storage.dao.appdefinition.AppDefinitionDao;
import com.microsoft.skype.teams.storage.tables.AppDefinition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class TeamsSdkSimAppDefinitionDao implements AppDefinitionDao {
    @Override
    public void deleteTable(AppDefinition table) {

    }

    @Override
    public void delete(@NonNull AppDefinition item) {

    }

    @Override
    public void save(AppDefinition appDefinition) {

    }

    @Override
    public void saveAllInTransaction(@Nullable Collection<AppDefinition> items) {

    }

    @Override
    public void saveAllWithoutTransaction(@Nullable Collection<AppDefinition> items) {

    }

    @Override
    public void update(@NonNull AppDefinition item) {

    }

    @Override
    public void purge() {

    }

    @Nullable
    @Override
    public AppDefinition fromId(@Nullable String appId) {
        return null;
    }

    @Nullable
    @Override
    public AppDefinition fromId(@Nullable String appId, boolean includeChatAppDefinitions) {
        return null;
    }

    @Nullable
    @Override
    public AppDefinition fromBotId(@Nullable String botId) {
        return null;
    }

    @Nullable
    @Override
    public AppDefinition fromBotId(@Nullable String botId, boolean includeChatAppDefinitions) {
        return null;
    }

    @Nullable
    @Override
    public List<AppDefinition> getAppDefinitionListFromBotId(@Nullable String botId) {
        return null;
    }

    @NonNull
    @Override
    public Map<String, AppDefinition> fromIds(@Nullable List<String> ids) {
        return null;
    }

    @Nullable
    @Override
    public String getCleanBotId(@Nullable String botId) {
        return null;
    }

    @Override
    public boolean isBotId(@Nullable String botId) {
        return false;
    }

    @Override
    public Map<String, AppDefinition> fromBotIds(ArrayList<String> botIds) {
        return null;
    }

    @Override
    public List<AppDefinition> fromBotList(List<String> botIds) {
        return null;
    }

    @Override
    public boolean isCustomBot(AppDefinition appDefinition) {
        return false;
    }

    @Override
    public List<AppDefinition> getCustomBotsForTeam(@NonNull String threadId) {
        return null;
    }

    @Nullable
    @Override
    public List<AppDefinition> getAppDefinitionsWithTeamEntitlement(String parentThreadId) {
        return null;
    }

    @Override
    public void deleteAppsNotInCollection(@NonNull Collection<String> appIds) {

    }

    @Override
    public List<AppDefinition> getAppDefinitionsWithMobileModules() {
        return null;
    }
}
