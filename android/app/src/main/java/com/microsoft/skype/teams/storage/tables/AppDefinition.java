/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.storage.tables;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.microsoft.skype.teams.storage.IModel;
// import com.microsoft.skype.teams.storage.SkypeTeamsDatabase;
// import com.microsoft.skype.teams.storage.models.Bot;
import com.microsoft.skype.teams.storage.models.MobileModuleDefinition;
import com.microsoft.skype.teams.utilities.java.JsonUtils;
import com.microsoft.skype.teams.utilities.java.StringUtils;
/*import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;*/

import java.util.HashSet;
import java.util.Set;

/**
 * Represents an app definition (bot or tab)
 */
/*
@Table(
        database = SkypeTeamsDatabase.class
)
*/
// public class AppDefinition extends BaseModel implements IModel {
public class AppDefinition implements IModel {
    public static final String COLUMN_EXTERNAL_ID = "externalId";

    // @PrimaryKey
    public String appId;

    // @PrimaryKey
    public String tenantId;

    //@Column
    public String developerName;

    //@Column
    public String developerUrl;

    // url for the full color image
    //@Column
    public String largeImageUrl;

    //@Column
    public String longDescription;

    //@Column
    public String manifestVersion;

    //@Column
    public String name;

    //@Column
    public String privacyUrl;

    //@Column
    public String shortDescription;

    // url for the transparent outline image
    //@Column
    public String smallImageUrl;

    //@Column
    public String termsOfUseUrl;

    //@Column
    public String accentColor;

    //@Column
    public String botId;

    //@Column
    public boolean isNotificationOnly;

    //@Column
    public String appDefinitionJson;

    //@Column
    public String version;

    //@Column
    public String externalId;  // added in db version 157

    // reuse processed results from app definition Json
    private transient String[] mValidDomains;
    // private transient Bot mBot;
    private transient MobileModuleDefinition mMobileModuleDefinition;

    /**
     * Valid domains are mentioned explicitly in a list of domains with wildcard supports
     * They are also implicitly inferred from {@code contentUrl} in static tabs and {@code configurationUrl } in configurable tabs
     */
    @NonNull
    public String[] getValidDomains() {
        if (mValidDomains == null) {
            Set<String> validDomains = new HashSet<>();
            JsonObject appDefinition = JsonUtils.getJsonObjectFromString(appDefinitionJson);

            // parse explicitly defined valid domains
            JsonArray validDomainsJson = JsonUtils.parseArray(appDefinition, "validDomains");
            if (validDomainsJson != null) {
                for (int i = 0; i < validDomainsJson.size(); i++) {
                    validDomains.add(validDomainsJson.get(i).getAsString());
                }
            }

            // parse implicit valid domains from staticTabs[i].contentUrl
            JsonArray staticTabsJson = JsonUtils.parseArray(appDefinition, "staticTabs");
            if (staticTabsJson != null) {
                for (JsonElement staticTab : staticTabsJson) {
                    String contentUrl = JsonUtils.parseString(staticTab, "contentUrl");
                    String authority = Uri.parse(contentUrl).getAuthority();
                    if (StringUtils.isNotEmpty(authority)) {
                        validDomains.add(authority);
                    }

                    String host = Uri.parse(contentUrl).getHost();
                    if (StringUtils.isNotEmpty(host)) {
                        validDomains.add(host);
                    }
                }
            }

            // parse implicit valid domains from configurableTabs[i].contentUrl
            JsonElement configurableTabsJson = getConfigurableTabJson();
            if (configurableTabsJson != null) {
                String configurationUrl = JsonUtils.parseString(configurableTabsJson, "configurationUrl");
                String domain = Uri.parse(configurationUrl).getAuthority();
                if (StringUtils.isNotEmpty(domain)) {
                    validDomains.add(domain);
                }

                String host = Uri.parse(configurationUrl).getHost();
                if (StringUtils.isNotEmpty(host)) {
                    validDomains.add(host);
                }
            }

            mValidDomains = validDomains.toArray(new String[0]);
        }
        return mValidDomains;
    }

    public boolean isSideLoadedApp() {
        return StringUtils.isNotEmpty(getExternalId());
    }

    /**
     * @return check if the app is a LOB app private to a tenant.
     */
    public boolean isLOBApp() {
        JsonObject appDefinition = JsonUtils.getJsonObjectFromString(appDefinitionJson);
        return StringUtils.isNotEmpty(JsonUtils.parseString(appDefinition, "tenantId"));
    }

    /**
     * @return true if the app definition has mobile modules section
     */
    public boolean hasMobileModule() {
        JsonObject appDefinition = JsonUtils.getJsonObjectFromString(appDefinitionJson);
        JsonArray mobileModules = JsonUtils.parseArray(appDefinition, "mobileModules");
        return !JsonUtils.isNullOrEmpty(mobileModules);
    }

    public String getMobileModuleId() {
        JsonObject appDefinition = JsonUtils.getJsonObjectFromString(appDefinitionJson);
        JsonArray mobileModules = JsonUtils.parseArray(appDefinition, "mobileModules");
        if (JsonUtils.isNullOrEmpty(mobileModules)) {
            return "";
        }

        return JsonUtils.parseString(mobileModules.get(0), "id");
    }

    public String getExternalId() {
        JsonObject appDefinition = JsonUtils.getJsonObjectFromString(appDefinitionJson);
        return JsonUtils.parseString(appDefinition, "externalId");
    }

    @Nullable
    public JsonElement getConfigurableTabJson() {
        JsonElement configurableTab = null;
        @Nullable JsonObject appDefinition = JsonUtils.getJsonObjectFromString(appDefinitionJson);
        JsonArray configurableTabsJson = null;
        if (appDefinition != null) {
            // configurableTabs were called galleryTabs in older manifest versions
            if (appDefinition.has("configurableTabs")) {
                configurableTabsJson = JsonUtils.parseArray(appDefinition, "configurableTabs");
            } else if (appDefinition.has("galleryTabs")) {
                configurableTabsJson = JsonUtils.parseArray(appDefinition, "galleryTabs");
            }
        }

        // There can be at max 1 configurable tab definition in the app manifest
        if (configurableTabsJson != null && configurableTabsJson.size() != 0) {
            configurableTab = configurableTabsJson.get(0);
        }
        return configurableTab;
    }

    /**
     * @return the bot defined in this app definition
     */
    @Nullable
    /*public Bot getBot() {
        if (mBot == null && StringUtils.isNotEmpty(botId)) {
            JsonObject appDefinitionJson = JsonUtils.getJsonObjectFromString(this.appDefinitionJson);
            JsonArray bots = JsonUtils.parseArray(appDefinitionJson, "bots");

            if (JsonUtils.isNullOrEmpty(bots)) {
                mBot = null;
            } else {
                //Currently only one bot is allowed per app
                JsonElement bot = bots.get(0);
                mBot = (Bot) JsonUtils.getObjectFromString(bot, Bot.class);
            }
        }

        return mBot;
    }*/

    /**
     * @return the manifest schema version.
     * This is of the form of MAJOR.MINOR.PATCH following the semantic version pattern.
     */
    @NonNull
    public String getManifestVersion() {
        JsonObject appDefinition = JsonUtils.getJsonObjectFromString(appDefinitionJson);
        return JsonUtils.parseString(appDefinition, "manifestVersion");
    }

    /**
     * @return mobileModule object if it's attached to it.
     */
    @Nullable
    public MobileModuleDefinition getMobileModuleDefinition() {
        if (mMobileModuleDefinition == null) {
            JsonObject appDefinition = JsonUtils.getJsonObjectFromString(appDefinitionJson);
            JsonArray mobileModules = JsonUtils.parseArray(appDefinition, "mobileModules");
            if (!JsonUtils.isNullOrEmpty(mobileModules)) {
                for (JsonElement mobileModule : mobileModules) {
                    if (mobileModule.isJsonObject()) {
                        mMobileModuleDefinition = parseMobileModuleDefinition(appId, mobileModule.getAsJsonObject());
                        return mMobileModuleDefinition;
                    }
                }
            }
        }
        return null;
    }

    private static MobileModuleDefinition parseMobileModuleDefinition(String appId, JsonObject mobileModuleObject) {
        MobileModuleDefinition mobileModuleDefinition = new MobileModuleDefinition();
        mobileModuleDefinition.appId = appId;
        mobileModuleDefinition.id = appId;
        mobileModuleDefinition.type = JsonUtils.parseString(mobileModuleObject, "type");
        mobileModuleDefinition.rnPackageUrl = JsonUtils.parseString(mobileModuleObject, "rnPackageUrl");

        return mobileModuleDefinition;
    }
}