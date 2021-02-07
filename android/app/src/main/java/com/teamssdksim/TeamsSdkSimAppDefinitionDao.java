package com.teamssdksim;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.JsonObject;
import com.microsoft.skype.teams.data.transforms.CoreParserHelper;
import com.microsoft.skype.teams.logger.ILogger;
import com.microsoft.skype.teams.storage.dao.appdefinition.AppDefinitionDao;
import com.microsoft.skype.teams.storage.tables.AppDefinition;
import com.microsoft.skype.teams.utilities.java.JsonUtils;
import com.microsoft.teams.core.app.ITeamsApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.microsoft.skype.teams.sdk.SdkHelper.throwError;

public class TeamsSdkSimAppDefinitionDao implements AppDefinitionDao {
    ITeamsApplication mTeamsApplication;
    HashMap<String, AppDefinition> mAppDefinitionMap;

    TeamsSdkSimAppDefinitionDao(ITeamsApplication teamsApplication) {
        mTeamsApplication = teamsApplication;
        mAppDefinitionMap = new HashMap<>();

        String appDefinitionString = "{\n" +
                "    \"manifestVersion\": \"1.0\",\n" +
                "    \"version\": \"0.1.16\",\n" +
                "    \"categories\": [\n" +
                "        \"Microsoft\",\n" +
                "        \"Productivity\"\n" +
                "    ],\n" +
                "    \"developerName\": \"Microsoft Corp.\",\n" +
                "    \"developerUrl\": \"http://www.microsoft.com/\",\n" +
                "    \"privacyUrl\": \"https://privacy.microsoft.com/en-us/privacystatement\",\n" +
                "    \"termsOfUseUrl\": \"https://www.microsoft.com/en-us/servicesagreement\",\n" +
                "    \"validDomains\": [],\n" +
                "    \"permissions\": [],\n" +
                "    \"mobileModules\": [\n" +
                "        {\n" +
                "            \"type\": \"reactNative\",\n" +
                "            \"rnPackageUrl\": \"codepush://teams-mobile-sdk-example/?deploymentKey=_uVIKMilIBiQTiFhfURMqHy4G7S3ryjiyjgWE\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"id\": \"0b04a6cf-e696-4eb0-bac2-aaf883c9add5\",\n" +
                "    \"name\": \"React Native Hello World\",\n" +
                "    \"shortDescription\": \"Showcases the Teams Mobile React Native SDK.\",\n" +
                "    \"longDescription\": \"Showcases the Teams Mobile React Native SDK.\",\n" +
                "    \"smallImageUrl\": \"https://statics.teams.cdn.office.net/evergreen-assets/apps/1ded03cb-ece5-4e7c-9f73-61c375528078_smallImage.png?v=0.0.15\",\n" +
                "    \"largeImageUrl\": \"https://statics.teams.cdn.office.net/evergreen-assets/apps/1ded03cb-ece5-4e7c-9f73-61c375528078_largeImage.png?v=0.0.15\",\n" +
                "    \"accentColor\": \"#7719aa\"\n" +
                "}";

        JsonObject jsonObject = JsonUtils.parseObject(appDefinitionString, JsonObject.class, null);
        AppDefinition appDefinition = new AppDefinition();
        CoreParserHelper.parseAppDefinition(jsonObject, appDefinition, null, mTeamsApplication.getLogger(""));
        if (appDefinition != null) {
            mAppDefinitionMap.put("0b04a6cf-e696-4eb0-bac2-aaf883c9add5", appDefinition);
        }
    }

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
        return mAppDefinitionMap.get(appId);
    }

    @Nullable
    @Override
    public AppDefinition fromId(@Nullable String appId, boolean includeChatAppDefinitions) {
        return fromId(appId);
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
        return Collections.singletonList(mAppDefinitionMap.get("0b04a6cf-e696-4eb0-bac2-aaf883c9add5"));
    }
}
