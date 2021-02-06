/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk;

import android.net.Uri;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
//import com.microsoft.skype.teams.app.SkypeTeamsApplication;
//import com.microsoft.skype.teams.injection.components.DataContextComponent;
//import com.microsoft.skype.teams.sdk.models.SdkTabContext;
//import com.microsoft.skype.teams.sdk.rnbundle.SdkBundleUtils;
//import com.microsoft.skype.teams.storage.IExperimentationManager;
//import com.microsoft.skype.teams.storage.dao.conversation.ConversationDaoHelper;
//import com.microsoft.skype.teams.storage.tables.Conversation;
import com.microsoft.skype.teams.sdk.rnbundle.SdkBundleUtils;
import com.microsoft.skype.teams.storage.models.MobileModuleDefinition;
//import com.microsoft.skype.teams.storage.tables.Thread;
import com.microsoft.skype.teams.storage.IExperimentationManager;
import com.microsoft.skype.teams.utilities.java.JsonUtils;
import com.microsoft.skype.teams.utilities.java.StringUtils;
//import com.microsoft.teams.core.services.navigation.ITeamsNavigationService;

import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Helper class containing SDK related methods
 */
public final class SdkHelper {
    public static final String LOG_TAG = "SdkHelper";
    public static final String MODULE_PARAMS_PARAM_NAME = "moduleParams";
    public static final String DEEPLINK_PATH_TYPE = "l";
    public static final String DEEPLINK_ENTITY_TYPE = "entity";
    public static final String DEEPLINK_PARAM_CONTEXT = "context";
    public static final String DEEPLINK_PARAM_CHANNELID = "channelId";

    @Nullable
    public static String getUserObjectId() {
        // return SkypeTeamsApplication.getCurrentAuthenticatedUser() == null ? null : SkypeTeamsApplication.getCurrentAuthenticatedUser().userObjectId;
        return "TeamsSdkSim";
    }

    /**
     * Helper method to derive CID from the imageUri field in consumer scenarios
     *
     * @param imageUri will be in this form: "https://substrate.office.com/profile/v1.0/users/cid:xxxxxxxxxx/image/$value?hashKey=''"
     * @return Consumer ID
     */
    @Nullable
    public static String getCidFromImageUri(String imageUri) {
        if (StringUtils.isNullOrEmptyOrWhitespace(imageUri)) {
            return null;
        }
        Pattern cidPattern = Pattern.compile("cid:(.*?)/");
        Matcher cidMatch = cidPattern.matcher(imageUri);
        return cidMatch.find() ? cidMatch.group(1) : null;
    }

    @NonNull
    public static Uri prepareDeeplinkToChannelTab(@NonNull String appId, @NonNull String entityId, @NonNull String channelId, @NonNull String displayName) {
        // e.g msteams://teams.microsoft.com/l/entity/<appId>/<entityId>?label=<entityLabel>&context={"channelId": "channel_id"}
        JsonObject context = new JsonObject();
        context.add("channelId", new JsonPrimitive(channelId));

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("msteams")
                .authority("teams.microsoft.com")
                .appendPath("l")
                .appendPath("entity")
                .appendPath(appId)
                .appendPath(entityId)
                .appendQueryParameter("label", displayName)
                .appendQueryParameter("context", JsonUtils.getJsonStringFromObject(context));
        return builder.build();
    }

    private SdkHelper() {
    }

    public static String getDeploymentKey(@NonNull String appId,
                                          @NonNull IExperimentationManager experimentationManager,
                                          @NonNull MobileModuleDefinition mobileModuleDefinition) {
        String deploymentKey = null;
        String deploymentKeyFromECS = experimentationManager.getReactNativeAppDeploymentKey(appId);
        if (deploymentKeyFromECS != null) {
            deploymentKey = deploymentKeyFromECS;
        } else {
            if (mobileModuleDefinition.rnPackageUrl != null) {
                deploymentKey = SdkBundleUtils.getDeploymentKey(mobileModuleDefinition.rnPackageUrl);
            }
        }
        return deploymentKey;
    }

    /**
     * Throws an error with the specified message and message parameters.
     */
    public static void throwError(String message, Object... params) throws Exception {
        String errorMessage = String.format(Locale.ENGLISH, message, params);
        throw new Exception(errorMessage);
    }

    /**
     * Helper to check if the deeplink passed is a vaild deelink to a static or channel tab or a personal app
     *
     * @param deeplink - teams deeplink to static or channel tab
     * @return - true or false
     */
//    public static boolean isValidDeeplinkToTab(@Nullable Uri deeplink, @NonNull ITeamsNavigationService teamsNavigationService) {
//        if (deeplink == null) {
//            return false;
//        }
//
//        List<String> paths = deeplink.getPathSegments();
//        if (paths.size() < 4) {
//            return false;
//        }
//
//        String pathType = paths.get(0);
//        String entityType = paths.get(1);
//        String appId = paths.get(2);
//
//        // validate if the path starts with "l/entity" and a valid teams deep link url
//        if (!teamsNavigationService.isTeamsDeeplink(deeplink)
//                || !DEEPLINK_PATH_TYPE.equalsIgnoreCase(pathType)
//                || !DEEPLINK_ENTITY_TYPE.equalsIgnoreCase(entityType)) {
//            return false;
//        }
//
//        return true;
//    }

    /**
     * Helper to fetch tab context data for passing to RN module, when launching as a channel tab
     * sample deeplink e.g msteams://teams.microsoft.com/l/entity/<appId>/<entityId>?label=<entityLabel>&context={"channelId": "channel_id"}
     *
     * @param deeplink - deeplink to a channel tab
     * @return - if the deeplink is a valid channel tab deeplink, then return the tab context data, else return null
     */
//    public static SdkTabContext fetchTabContextForChannelTab(Uri deeplink, @NonNull ITeamsNavigationService teamsNavigationService) {
//        SdkTabContext tabContext = null;
//        DataContextComponent dataComponent = SkypeTeamsApplication.getAuthenticatedUserComponent();
//        if (dataComponent == null) {
//            return null;
//        }
//
//        if (isValidDeeplinkToTab(deeplink, teamsNavigationService)) {
//            String context = deeplink.getQueryParameter(DEEPLINK_PARAM_CONTEXT);
//            JsonObject contextJson = JsonUtils.getJsonObjectFromString(context);
//            if (contextJson != null && contextJson.has(DEEPLINK_PARAM_CHANNELID)) {
//                String channelId = JsonUtils.parseString(contextJson, DEEPLINK_PARAM_CHANNELID);
//                //Resolves the Team and channel id
//                Conversation channel = dataComponent.conversationDao().fromId(channelId);
//                if (channel != null) {
//                    String teamId = ConversationDaoHelper.isGeneralChannel(channel) ? channel.conversationId : channel.parentConversationId;
//                    Thread teamThread = dataComponent.threadDao().fromId(teamId);
//                    if (teamThread != null) {
//                        tabContext = new SdkTabContext(teamId, teamThread.aadGroupId, channelId);
//                    }
//                }
//            }
//            // else this is a link to static tab.
//        }
//
//        return tabContext;
//    }
}
