/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.data.transforms;

import android.content.Context;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.collection.ArrayMap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.microsoft.skype.teams.logger.ILogger;
import com.microsoft.skype.teams.logger.LogPriority;
//import com.microsoft.skype.teams.models.AuthenticatedUser;
//import com.microsoft.skype.teams.models.ChatMessageResponse;
//import com.microsoft.skype.teams.models.ConversationResponse;
//import com.microsoft.skype.teams.models.ListModel;
//import com.microsoft.skype.teams.models.RecordingPermissionsAndSettings;
//import com.microsoft.skype.teams.models.TenantInfo;
//import com.microsoft.skype.teams.models.TypingUser;
//import com.microsoft.skype.teams.models.UserAggregatedSettings;
//import com.microsoft.skype.teams.models.UserGroupsSettings;
//import com.microsoft.skype.teams.models.VoiceAdminSettings;
//import com.microsoft.skype.teams.services.authorization.IAccountManager;
//import com.microsoft.skype.teams.services.authorization.helpers.CoreAuthorizationUtilities;
//import com.microsoft.skype.teams.services.utilities.StringUtilities;
//import com.microsoft.skype.teams.storage.CallRecordingDetails;
//import com.microsoft.skype.teams.storage.IExperimentationManager;
//import com.microsoft.skype.teams.storage.ITransaction;
//import com.microsoft.skype.teams.storage.MessagePropertyType;
//import com.microsoft.skype.teams.storage.RunnableOf;
//import com.microsoft.skype.teams.storage.SkypeDBTransactionManager;
//import com.microsoft.skype.teams.storage.ThreadType;
//import com.microsoft.skype.teams.storage.dao.chatconversation.ChatConversationDao;
//import com.microsoft.skype.teams.storage.dao.giphydefinition.GiphyDefinitionDao;
//import com.microsoft.skype.teams.storage.dao.messagepropertyattribute.MessagePropertyAttributeDao;
//import com.microsoft.skype.teams.storage.dao.messagesyncstate.MessageSyncStateDao;
//import com.microsoft.skype.teams.storage.dao.user.UserDao;
import com.microsoft.skype.teams.storage.tables.AppDefinition;
//import com.microsoft.skype.teams.storage.tables.ChatAppDefinition;
//import com.microsoft.skype.teams.storage.tables.ChatConversation;
//import com.microsoft.skype.teams.storage.tables.Conversation;
//import com.microsoft.skype.teams.storage.tables.GiphyDefinition;
//import com.microsoft.skype.teams.storage.tables.Message;
//import com.microsoft.skype.teams.storage.tables.MessagePropertyAttribute;
//import com.microsoft.skype.teams.storage.tables.MessageSyncState;
import com.microsoft.skype.teams.storage.tables.TeamEntitlement;
//import com.microsoft.skype.teams.storage.tables.User;
//import com.microsoft.skype.teams.storage.tables.UserEntitlement;
//import com.microsoft.skype.teams.utilities.CoreConversationUtilities;
//import com.microsoft.skype.teams.utilities.StringConstants;
import com.microsoft.skype.teams.utilities.java.JsonUtils;
//import com.microsoft.teams.core.R;
//import com.microsoft.teams.core.models.UserPreferences;
//import com.microsoft.teams.core.preferences.IPreferences;
//import com.microsoft.teams.core.services.configuration.IUserConfiguration;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.nodes.Entities;
//import org.jsoup.parser.Tag;
//import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

//import static com.microsoft.skype.teams.util.CallConstants.RECORDING_EXPIRED;
//import static com.microsoft.skype.teams.util.CallConstants.RECORDING_READY_ONLY_IN_AMS;
//import static com.microsoft.skype.teams.util.CallConstants.RECORDING_STORAGE_TYPE_ONEDRIVEFORBUSINESS;
//import static com.microsoft.skype.teams.util.CallConstants.RECORDING_STORAGE_TYPE_STREAM;

/**
 * Class containing utility methods that help in parsing
 */
public abstract class CoreParserHelper {

    private static final String TAG = com.microsoft.skype.teams.data.transforms.CoreParserHelper.class.getSimpleName();
//    private static final String TIME_ZONE_UTC = "UTC";
//    protected static final String VALUE = "value";
//    private static final String REST = "rest";
//    private static final String URL = "url";
//    private static final String MAILBOX_STATUS = "mailboxStatus";
//    private static final String NOT_DISCOVERABLE = "Undiscoverable";
//    private static final String EWS = "ews";
//    private static final String AUTO_DISCOVER_SETTINGS = "autodiscoverSettings";
//    private static final int DEFAULT_TEAMS_APP_BLOCKING_BUFFER_IN_MINUTES = 30;
//    private static final String ASSIGNMENTS_APP_ID = "66aeee93-507d-479a-a3ef-8f494af43945";
//    private static final String GRADES_APP_ID = "62554c36-4756-4d4e-b8fb-f4b24875ae0c";
//    static final String NOT_SET = "NOT_SET";
//    static final String BACK_UP_FALSE = "false";
//    static final String CALL_PARK_POLICY = "callParkPolicy";
//    static final String ALLOW_CALL_PARK = "allowCallPark";
//    static final String MOBILITY_POLICY = "mobilityPolicy";
//    static final String IP_AUDIO_MOBILE_MODE = "ipAudioMobileMode";
//    static final String IP_VIDEO_MOBILE_MODE = "ipVideoMobileMode";
//    static final String ERROR_STRING_AGGREGATED_SETTINGS = "Error";
//
//    public static UserAggregatedSettings parseUserAggregatedSettingsResponse(JsonObject rootElement,
//                                                                             AuthenticatedUser authUser,
//                                                                             @NonNull IExperimentationManager experimentationManager,
//                                                                             @NonNull ILogger logger) {
//        if (rootElement == null) {
//            return null;
//        }
//
//        UserAggregatedSettings existingSettings = null;
//        if (authUser != null && authUser.settings != null) {
//            existingSettings = authUser.settings;
//        }
//
//        UserAggregatedSettings userAggregatedSettings = null;
//        if (existingSettings != null) {
//            userAggregatedSettings = existingSettings;
//        } else {
//            userAggregatedSettings = new UserAggregatedSettings();
//        }
//
//        parseTenantSettingsPostConvergence(rootElement, userAggregatedSettings, logger);
//
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, "userPropertiesSettings")) {
//            parseUserPropertiesSettings(rootElement.getAsJsonObject("userPropertiesSettings"), userAggregatedSettings, logger);
//        }
//
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, "userResourcesSettings")) {
//            parseUserResourcesSettings(rootElement.getAsJsonObject("userResourcesSettings"), userAggregatedSettings);
//        }
//
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, "tenantVoiceSettings")) {
//            parseTenantVoiceSettings(rootElement.getAsJsonObject("tenantVoiceSettings"), userAggregatedSettings);
//        }
//
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, "policySettings")) {
//            parsePolicySettings(rootElement.getAsJsonObject("policySettings"), userAggregatedSettings);
//        }
//
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, "voiceAdminSettings")) {
//            parseVoiceAdminSettings(rootElement.getAsJsonObject("voiceAdminSettings"), userAggregatedSettings);
//        }
//
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, "onlineDialinConferencingPolicy")) {
//            parseOnlineDialinConferencingPolicy(rootElement.getAsJsonObject("onlineDialinConferencingPolicy"), userAggregatedSettings);
//        }
//
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, "shiftsPolicy")) {
//            parseShiftsPolicy(rootElement.getAsJsonObject("shiftsPolicy"), userAggregatedSettings);
//        }
//
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, "adminSettings")) {
//            parseAdminSettings(rootElement.getAsJsonObject("adminSettings"), userAggregatedSettings);
//        }
//
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, "tenantProperties")) {
//            parseTenantProperties(rootElement.getAsJsonObject("tenantProperties"), userAggregatedSettings);
//        }
//
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, "ipPhonePolicy")) {
//            parseIpPhonePolicy(rootElement.getAsJsonObject("ipPhonePolicy"), userAggregatedSettings, experimentationManager);
//        }
//
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, "teamsAndChannelsPolicy")) {
//            parseTeamsAndChannelsPolicy(rootElement.getAsJsonObject("teamsAndChannelsPolicy"), userAggregatedSettings);
//        }
//
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, "targetingPolicy")) {
//            parseTargetingPolicy(rootElement.getAsJsonObject("targetingPolicy"), userAggregatedSettings);
//        }
//
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, "feedbackPolicy")) {
//            parsefeedbackPolicy(rootElement.getAsJsonObject("feedbackPolicy"), userAggregatedSettings);
//        }
//
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, "branchSurvivabilityPolicy")) {
//            parseBranchSurvivabilityPolicy(rootElement.getAsJsonObject("branchSurvivabilityPolicy"), userAggregatedSettings);
//        }
//
//        return userAggregatedSettings;
//    }
//
//    /**
//     * Parses MemberConsumptionHorizonUpdate message obtained over longpoll. This is used for Read Receipts Feature.
//     */
//    static Message parseReadReceiptsMessageDetails(JsonElement message) {
//        final Message sMessage = new Message();
//        String conversationUrl = JsonUtils.parseString(message, "conversationLink");
//        sMessage.conversationId = fetchConversationId(conversationUrl, StringConstants.RELATED_MESSAGES_KEY);
//        sMessage.messageType = JsonUtils.parseString(message, "messagetype");
//        sMessage.content = JsonUtils.parseString(message, StringConstants.CONTENT);
//
//        return sMessage;
//    }
//
//    /**
//     * Parses incoming call message obtained over longpoll. Does not save the message to database.
//     */
//    static Message parseCallMessageDetails(JsonElement message) {
//        final Message sMessage = new Message();
//        String conversationUrl = JsonUtils.parseString(message, "conversationLink");
//        sMessage.conversationId = fetchConversationId(conversationUrl, StringConstants.RELATED_MESSAGES_KEY);
//        sMessage.messageType = JsonUtils.parseString(message, "messagetype");
//        sMessage.content = JsonUtils.parseString(message, StringConstants.CONTENT);
//        sMessage.from = extractMri(JsonUtils.parseString(message, "from"));
//        sMessage.userDisplayName = JsonUtils.parseString(message, "imdisplayname");
//        sMessage.eventId = JsonUtils.parseInt(message, "eventId");
//
//        return sMessage;
//    }
//
//    /**
//     * Function used to parse the UserGroupsSettings JSON to Objects
//     *
//     * @param rootElement will be the rootElement of the response Json Object
//     * @return UserGroupsSettings object which are serialized
//     */
//    public static UserGroupsSettings parseUserGroupsSettings(JsonObject rootElement) {
//        if (rootElement == null) {
//            return null;
//        }
//
//        // parsing classifications
//        Gson gson = new Gson();
//        return gson.fromJson(rootElement, UserGroupsSettings.class);
//    }
//
//    public static void callRecordingPermissionsAndSettings(@Nullable JsonObject content, String userObjectId, @NonNull IPreferences preferences) {
//        if (content == null) {
//            return;
//        }
//        RecordingPermissionsAndSettings recordingPermissionsAndSettings = new RecordingPermissionsAndSettings();
//        JsonObject settings = JsonUtils.parseObject(content, "settings");
//        if (settings != null) {
//            JsonObject system = JsonUtils.parseObject(settings, "system");
//            if (system != null) {
//                JsonObject contentManagement = JsonUtils.parseObject(system, "contentManagement");
//                if (contentManagement != null) {
//                    JsonObject companyUploadPolicy = JsonUtils.parseObject(contentManagement, "companyUploadPolicy");
//                    if (companyUploadPolicy != null) {
//                        recordingPermissionsAndSettings.acknowledgementRequired = JsonUtils.parseBoolean(companyUploadPolicy, "acknowledgementRequired");
//                        recordingPermissionsAndSettings.acknowledged = JsonUtils.parseBoolean(companyUploadPolicy, "acknowledged");
//                        recordingPermissionsAndSettings.acknowledgementUrl = JsonUtils.parseString(companyUploadPolicy, "url");
//                    }
//                }
//
//            }
//        }
//        JsonObject permissions = JsonUtils.parseObject(content, "permissions");
//        if (permissions != null) {
//            recordingPermissionsAndSettings.canCreateChannel = JsonUtils.parseBoolean(permissions, "canCreateChannel");
//            recordingPermissionsAndSettings.canCreateGroup = JsonUtils.parseBoolean(permissions, "canCreateGroup");
//            recordingPermissionsAndSettings.canCreateVideo = JsonUtils.parseBoolean(permissions, "canCreateVideo");
//            recordingPermissionsAndSettings.canCreateLiveEvent = JsonUtils.parseBoolean(permissions, "canCreateLiveEvent");
//        }
//        preferences.putStringUserPref(UserPreferences.RECORDING_PERMISSIONS_AND_SETTINGS,
//                JsonUtils.getJsonStringFromObject(recordingPermissionsAndSettings),
//                userObjectId);
//    }
//
//    public static void meetingConfigurationDetails(@Nullable JsonObject content,
//                                                   @NonNull String userObjectId,
//                                                   @NonNull IPreferences preferences) {
//        if (content == null) {
//            return;
//        }
//
//        if (content.has(VALUE)) {
//            JsonObject valueObject = content.getAsJsonObject(VALUE);
//            if (valueObject != null && !valueObject.isJsonNull()) {
//                UserAggregatedSettings.ConferenceConfigurationSetting conferenceConfigurationSetting = JsonUtils.parseObject(
//                        valueObject,
//                        UserAggregatedSettings.ConferenceConfigurationSetting.class,
//                        null);
//
//                preferences.putStringUserPref(UserPreferences.MEETING_CONFIGURATION_SETTINGS,
//                        JsonUtils.getJsonStringFromObject(conferenceConfigurationSetting),
//                        userObjectId);
//            }
//        }
//    }
//
//    @Nullable
//    public static CallRecordingDetails getCallRecordingDetails(@Nullable final String content) {
//        if (StringUtils.isEmptyOrWhiteSpace(content)) {
//            return null;
//        }
//        String thumbnail = null;
//        String status = null;
//        String duration = null;
//        String link = null;
//        String initiatorId = null;
//        String callId = null;
//        String callLegId = null;
//        String exportResultType = null;
//        String oneDriveForBusinessUri = null;
//        String amsUri = null;
//        String timestamp = null;
//        Document parsedMessageContent = Jsoup.parse(content);
//        if (parsedMessageContent != null) {
//            Element uriObject = parsedMessageContent.select("uriobject").first();
//            if (uriObject != null) {
//                thumbnail = uriObject.attr("url_thumbnail");
//            }
//            Element recordingStatus = parsedMessageContent.select("recordingstatus").first();
//            if (recordingStatus != null) {
//                status = recordingStatus.attr("code");
//            }
//            Element recordingLink = parsedMessageContent.select("a").first();
//            if (recordingLink != null) {
//                link = recordingLink.attr("href");
//            }
//            Element recordingInitiatorId = parsedMessageContent.select("RecordingInitiatorId").first();
//            if (recordingInitiatorId != null) {
//                initiatorId = recordingInitiatorId.attr("value");
//            }
//
//            Element requestedExports =  parsedMessageContent.select("requestedexports").first();
//            if (requestedExports != null) {
//                Document parsed = Jsoup.parse(requestedExports.toString());
//                if (parsed != null) {
//                    Elements exportResults = parsed.select("ExportResult");
//                    if (exportResults != null) {
//                        for (Element element : exportResults) {
//                            String type = element.attr("type");
//                            if (!StringUtils.isEmptyOrWhiteSpace(type)) {
//                                if (type.equalsIgnoreCase("ExportToStream")) {
//                                    exportResultType = RECORDING_STORAGE_TYPE_STREAM;
//                                } else if (type.equalsIgnoreCase("ExportToOnedriveForBusiness")) {
//                                    exportResultType = RECORDING_STORAGE_TYPE_ONEDRIVEFORBUSINESS;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            Element recordingContent = parsedMessageContent.select("recordingcontent").first();
//            if (recordingContent != null) {
//                duration = recordingContent.attr("duration");
//                if (exportResultType != null && exportResultType.equals(RECORDING_STORAGE_TYPE_ONEDRIVEFORBUSINESS)) {
//                    Document parsed = Jsoup.parse(recordingContent.toString());
//                    if (parsed != null) {
//                        Elements items = parsed.select("item");
//                        if (items != null) {
//                            for (Element element : items) {
//                                String idType = element.attr("type");
//                                if (!StringUtils.isEmptyOrWhiteSpace(idType) && idType.equalsIgnoreCase("onedriveForBusinessVideo")) {
//                                        oneDriveForBusinessUri = element.attr("uri");
//                                        break;
//                                }
//                            }
//                        }
//                    }
//                } else if (exportResultType != null && (status.equals(RECORDING_READY_ONLY_IN_AMS) || status.equals(RECORDING_EXPIRED))) {
//                    Document parsed = Jsoup.parse(recordingContent.toString());
//                    timestamp = recordingContent.attr("timestamp");
//                    if (parsed != null) {
//                        Elements items = parsed.select("item");
//                        if (items != null) {
//                            for (Element element : items) {
//                                String idType = element.attr("type");
//                                if (!StringUtils.isEmptyOrWhiteSpace(idType) && idType.equalsIgnoreCase("amsVideo")) {
//                                    amsUri = element.attr("uri");
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            Element identifiers = parsedMessageContent.select("Identifiers").first();
//            if (identifiers != null) {
//                Document parsed = Jsoup.parse(identifiers.toString());
//                if (parsed != null) {
//                    Elements idElements = parsed.select("Id");
//                    if (idElements != null) {
//                        for (Element element : idElements) {
//                            String idType = element.attr("type");
//                            if (!StringUtils.isEmptyOrWhiteSpace(idType)) {
//                                if (idType.equalsIgnoreCase("callId")) {
//                                    callId = element.attr("value");
//                                } else if (idType.equalsIgnoreCase("callLegId")) {
//                                    callLegId = element.attr("value");
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return new CallRecordingDetails(thumbnail, duration, status, link, callId, callLegId, oneDriveForBusinessUri, initiatorId, amsUri, timestamp);
//    }
//
//    @NonNull
//    public static Element parseHtml(@Nullable String html, @NonNull ILogger logger) {
//        String finalHtml = html;
//        if (StringUtils.isEmpty(html)) {
//            finalHtml = "";
//        }
//
//        // Remove line breaks/tabs and any surrounding whitespace to prevent additional line breaks in rendered message.
//        // See: https://domoreexp.visualstudio.com/DefaultCollection/Teamspace/_git/SkypeSpaces-Android/pullrequest/167933
//        String finalContent = finalHtml.replaceAll("\\s*\\r?\\n\\t?\\s*", "");
//        try {
//            Document document = Jsoup.parse(StringUtilities.wrapAsHtml(finalContent));
//            document.outputSettings().escapeMode(Entities.EscapeMode.extended);
//            Element parsedElement = new Element(Tag.valueOf("div"), "");
//
//            Elements elements = document.getElementsByTag("body");
//            if (elements != null && elements.size() > 0) {
//                // Iterate over the body of all children of body elements and append them to the parsed element
//                for (Element element : elements) {
//                    for (Element child : element.children()) {
//                        child.remove();
//                        parsedElement.appendChild(child);
//                    }
//                }
//            }
//
//            return parsedElement;
//        } catch (Exception ex) {
//            logger.log(LogPriority.VERBOSE, "ParseHtml", ex, "Failed to parse %s.", html);
//            return new Element(Tag.valueOf("div"), null);
//        }
//    }
//
//    @NonNull
//    public static String parseFirstAttrInTagInHtml(@NonNull String html, @NonNull String tag, @NonNull String attr) {
//        Document parsedDocument = Jsoup.parse(html);
//        Elements elementsByTag = parsedDocument.getElementsByTag(tag);
//        Element firstElement = elementsByTag.first();
//        if (firstElement != null) {
//            return firstElement.attr(attr);
//        }
//
//        return StringUtilities.EMPTY;
//    }
//
//    public static int parseCallDurationFromEndedCallHtml(@NonNull String html, @NonNull ILogger logger) {
//        Document parsedMessageContent = Jsoup.parse(html);
//        Elements durationElements = parsedMessageContent.select(Message.CONTENT_TYPE_DURATION);
//        int duration = 0;
//        if (!durationElements.isEmpty() && durationElements.first() != null) {
//            try {
//                duration = Integer.decode(durationElements.first().ownText());
//            } catch (NumberFormatException e) {
//                logger.log(
//                        LogPriority.ERROR,
//                        "ParserHelper",
//                        "Invalid number format, could not parse duration");
//            }
//        }
//        return duration;
//    }
//
//    /**
//     * Parse the Tenant array
//     *
//     * @param tenantsArrary the JSON Array of Tenants
//     * @param user if NULL then CURRENT authenticated user attached to the TenantInfo
//     * @return List of Tenant Info
//     */
//    public static List<TenantInfo> parseTenantData(@NonNull Context context,
//                                                   @Nullable JsonArray tenantsArrary,
//                                                   @NonNull AuthenticatedUser user) {
//        List<TenantInfo> tenantsList = new ArrayList<>();
//        if (tenantsArrary != null && tenantsArrary.isJsonArray() && tenantsArrary.size() > 0) {
//            for (JsonElement tenantObject : tenantsArrary) {
//                String tenantId = JsonUtils.parseString(tenantObject, "tenantId");
//                String tenantType = JsonUtils.parseString(tenantObject, "tenantType");
//                boolean isConsumer = (!StringUtils.isEmptyOrWhiteSpace(tenantType) && tenantType.equalsIgnoreCase(CoreAuthorizationUtilities.CONSUMER_TENANT)) ? true : false;
//                String tenantName;
//                if (isConsumer) {
//                    tenantName = context.getString(R.string.personal_account);
//                } else {
//                    tenantName = JsonUtils.parseString(tenantObject, "tenantName");
//                }
//                boolean isInvitationRedeemed = JsonUtils.parseBoolean(tenantObject, "isInvitationRedeemed");
//                int unreadCount = JsonUtils.parseInt(tenantObject, "unreadCount");
//                String redeemUrl = JsonUtils.parseString(tenantObject, "redeemUrl");
//                String userType = JsonUtils.parseString(tenantObject, "userType");
//                String userId = JsonUtils.parseString(tenantObject, "userId");
//                //Property to exclude tenants that have no access to teams
//                //hasNoAccess is defaulted to false and will be set if flagged in server
//                boolean hasNoAccess = JsonUtils.parseBoolean(tenantObject, "hasNoAccess");
//
//                if (!hasNoAccess) {
//                    TenantInfo tenant = new TenantInfo(
//                            tenantId, tenantName, userId, unreadCount, isInvitationRedeemed, redeemUrl, hasNoAccess, userType, user, isConsumer, tenantType);
//                    tenantsList.add(tenant);
//                }
//            }
//        }
//
//        return tenantsList;
//    }
//
//    public static List<GiphyDefinition> processGiphyResponse(
//            JsonElement response, boolean shouldPersistToDB, @NonNull final GiphyDefinitionDao giphyDefinitionDao, @NonNull SkypeDBTransactionManager skypeDBTransactionManager) {
//        final List<GiphyDefinition> list = new ArrayList<>();
//
//        if (response == null) {
//            return list;
//        }
//
//        JsonObject jsonObject = response.getAsJsonObject();
//        if (jsonObject == null) {
//            return list;
//        }
//
//        JsonElement jsonArrayElement = jsonObject.get("result");
//        if (jsonArrayElement == null || !jsonArrayElement.isJsonArray()) {
//            return list;
//        }
//
//        JsonArray jsonArray = jsonArrayElement.getAsJsonArray();
//        if (jsonArray.size() != 1) {
//            return list;
//        }
//
//        jsonObject = jsonArray.get(0).getAsJsonObject();
//        if (jsonObject == null) {
//            return list;
//        }
//
//        jsonArrayElement = jsonObject.get("result");
//        if (jsonArrayElement == null || !jsonArrayElement.isJsonArray()) {
//            return list;
//        }
//
//        JsonArray resultArray = jsonArrayElement.getAsJsonArray();
//        for (JsonElement result : resultArray) {
//            GiphyDefinition giphy = parseGiphy(result);
//            if (giphy != null) {
//                list.add(giphy);
//            }
//        }
//
//        if (shouldPersistToDB) {
//            skypeDBTransactionManager.performTransaction(new ITransaction() {
//                @Override
//                public void execute() {
//                    for (GiphyDefinition giphy : list) {
//                        giphyDefinitionDao.save(giphy);
//                    }
//                }
//            });
//        }
//
//        return list;
//    }
//
//    public static String fetchConversationId(@NonNull String url, @NonNull String endDelimiter) {
//        // example url: https://co4-df-client-s.gateway.messenger.live.com/v1/users/ME/conversations/19:skypex_6ad3766f7cdc40dfb345d888fff1c364@thread.skype
//        String conversations = "conversations/";
//        if (url.contains(conversations)) {
//            int index = url.indexOf(conversations);
//            if (url.contains(endDelimiter)) {
//                return url.substring(index + conversations.length(), url.lastIndexOf(endDelimiter));
//            } else {
//                return url.substring(index + conversations.length());
//            }
//        }
//
//        return "";
//    }
//
//    static void parseNewConversation(
//            final JsonElement thread, final ListModel conversations, @NonNull ChatConversationDao chatConversationDao, @NonNull IUserConfiguration userConfiguration) {
//        JsonElement threadProperties;
//        if (thread.getAsJsonObject().has(StringConstants.PROPERTIES)) {
//            threadProperties = thread.getAsJsonObject().get(StringConstants.PROPERTIES);
//        } else {
//            return;
//        }
//
//        if (!threadProperties.getAsJsonObject().has("threadType")) {
//            return;
//        }
//
//        String id = JsonUtils.parseString(thread, "id");
//        final String threadTypeStr = JsonUtils.parseString(threadProperties, "threadType");
//        ThreadType threadType = ThreadType.fromString(threadTypeStr);
//
//        switch (threadType) {
//            case PHONE_SMS_CHAT:
//                if (!userConfiguration.isSMSChatEnabled()) {
//                    break;
//                }
//            case CHAT:
//            case SFB_INTEROP_CHAT:
//            case CHAT1ON1:
//            case PRIVATE_MEETING:
//                chatConversationDao.updateWith(id, threadType, new RunnableOf<ChatConversation>() {
//                    @Override
//                    public void run(ChatConversation item) {
//                        conversations.add(item);
//                    }
//                });
//                break;
//
//            default:
//                // No need to update the Conversation.
//                break;
//        }
//    }
//
//    static TypingUser parseTypingUser(JsonElement jsonElement, boolean isClearType, @NonNull UserDao userDao) {
//        String senderUri = extractMri(JsonUtils.parseString(jsonElement, "from"));
//        String conversationId = fetchConversationId(JsonUtils.parseString(jsonElement, "conversationLink"), StringConstants.RELATED_MESSAGES_KEY);
//        User user = userDao.fromId(senderUri);
//        if (user != null) {
//            long composeTime = System.currentTimeMillis();
//            if (jsonElement.getAsJsonObject().has(StringConstants.COMPOSE_TIME)) {
//                composeTime = JsonUtils.getDateFromJsonString(
//                        JsonUtils.parseString(jsonElement, StringConstants.COMPOSE_TIME),
//                        TimeZone.getTimeZone(TIME_ZONE_UTC))
//                        .getTime();
//            }
//
//            return new TypingUser(
//                    user,
//                    composeTime,
//                    isClearType,
//                    conversationId);
//        }
//
//        return null;
//    }
//
//    static void parseMetaData(
//            JsonObject metadata,
//            ChatMessageResponse messageResponse,
//            long syncTime,
//            @NonNull MessageSyncStateDao messageSyncStateDao,
//            @NonNull SkypeDBTransactionManager skypeDBTransactionManager) {
//        if (metadata == null) {
//            return;
//        }
//
//        String rawSyncState = JsonUtils.parseString(metadata, "syncState");
//        if (!StringUtils.isEmptyOrWhiteSpace(rawSyncState)) {
//            String conversationId = fetchConversationId(rawSyncState, "/messages");
//            String syncState = parseSyncState(rawSyncState);
//
//            messageResponse.conversationId = conversationId;
//            messageResponse.backwardLink = JsonUtils.parseString(metadata, "backwardLink");
//            messageResponse.syncState = syncState;
//
//            if (!CoreConversationUtilities.isConversationRelatedMessagesCall(conversationId)) {
//                MessageSyncState messageSyncState = messageSyncStateDao.getMessageSyncState(conversationId);
//                if (messageSyncState == null) {
//                    messageSyncState = new MessageSyncState();
//                }
//
//                messageSyncState.conversationId = conversationId;
//                messageSyncState.syncState = syncState;
//                messageSyncState.backwardLink = JsonUtils.parseString(metadata, "backwardLink");
//                messageSyncState.lastSyncTime = String.valueOf(syncTime);
//                messageSyncState.syncMessages = false;
//
//                // Reset clientCacheClearedAt time so that next time you sync messages only
//                // using the SyncState as it is not corrupted.
//                messageSyncState.clientCacheClearedAt = 0L;
//
//                //Previously we were creating new object of MessageSyncState, so the following variables would get initialized to their default values.
//                //Preserving that behaviour
//                messageSyncState.lastSyncCheckTime = 0L;
//
//                if (!StringUtils.isEmptyOrWhiteSpace(conversationId)) {
//                    messageSyncStateDao.save(messageSyncState);
//                }
//            }
//        }
//    }
//
//    static void parseMetaData(
//            JsonObject metadata,
//            ConversationResponse conversationResponse,
//            @NonNull MessageSyncStateDao messageSyncStateDao,
//            @NonNull SkypeDBTransactionManager skypeDBTransactionManager) {
//        if (metadata == null) {
//            return;
//        }
//
//        String rawSyncState = JsonUtils.parseString(metadata, "syncState");
//        if (!StringUtils.isEmptyOrWhiteSpace(rawSyncState)) {
//            String syncState = parseSyncState(rawSyncState);
//
//            conversationResponse.backwardLink = JsonUtils.parseString(metadata, "backwardLink");
//            conversationResponse.syncState = syncState;
//        }
//
//        MessageSyncState messageSyncState = new MessageSyncState();
//        messageSyncState.conversationId = Conversation.GLOBAL_CONVERSATION_SYNC_STATE_KEY;
//        messageSyncState.syncState = conversationResponse.syncState;
//        messageSyncState.backwardLink = conversationResponse.backwardLink;
//
//        //For a single DB call, transaction is not required
//        messageSyncStateDao.save(messageSyncState);
//    }
//
    static List<AppDefinition> parseAppDefinitions(JsonObject appDefinitionsObject, @NonNull ILogger logger) {
        List<AppDefinition> appDefinitions = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : appDefinitionsObject.entrySet()) {
            try {
                JsonObject definition = entry.getValue().getAsJsonObject();
                if (definition != null) {
                    AppDefinition appDefinition = new AppDefinition();
                    parseAppDefinition(definition, appDefinition, null, logger);
                    appDefinitions.add(appDefinition);
                }
            } catch (IllegalStateException e) {
                logger.log(LogPriority.ERROR, TAG, String.format("MalformedJSON: Malformed appDefinition for app: %s", entry.getKey()));
            }
        }

        return appDefinitions;
    }

//    static List<ChatAppDefinition> parseChatAppDefinitions(
//            JsonArray appDefinitionsArray, @NonNull List<TeamEntitlement> entitlements, @NonNull String threadId, ILogger logger) {
//
//        List<ChatAppDefinition> chatAppDefinitions = new ArrayList<>();
//        for (JsonElement appDefinitionElement : appDefinitionsArray) {
//            try {
//                JsonObject definition = appDefinitionElement.getAsJsonObject();
//                if (definition != null) {
//                    ChatAppDefinition chatAppDefinition = new ChatAppDefinition();
//                    TeamEntitlement entitlement = new TeamEntitlement();
//                    entitlement.threadId = threadId;
//                    parseAppDefinition(definition, chatAppDefinition, entitlement, logger);
//                    chatAppDefinitions.add(chatAppDefinition);
//                    entitlements.add(entitlement);
//                }
//            } catch (IllegalStateException e) {
//                logger.log(LogPriority.ERROR, TAG, "MalformedJSON: Malformed chatAppDefinition");
//            }
//        }
//
//        return chatAppDefinitions;
//    }

    public static void parseAppDefinition(@NonNull JsonObject definition,
                                           @NonNull AppDefinition appDefinition,
                                           @Nullable TeamEntitlement entitlement,
                                           @NonNull ILogger logger) {
        appDefinition.appId = JsonUtils.parseString(definition, "id");
        appDefinition.externalId = JsonUtils.parseString(definition, "externalId");
        appDefinition.developerName = JsonUtils.parseString(definition, "developerName");
        appDefinition.developerUrl = JsonUtils.parseString(definition, "developerUrl");
        appDefinition.largeImageUrl = JsonUtils.parseString(definition, "largeImageUrl");
        appDefinition.longDescription = JsonUtils.parseString(definition, "longDescription");
        appDefinition.manifestVersion = JsonUtils.parseString(definition, "manifestVersion");
        appDefinition.name = JsonUtils.parseString(definition, "name");
        appDefinition.privacyUrl = JsonUtils.parseString(definition, "privacyUrl");
        appDefinition.shortDescription = JsonUtils.parseString(definition, "shortDescription");
        appDefinition.smallImageUrl = JsonUtils.parseString(definition, "smallImageUrl");
        appDefinition.termsOfUseUrl = JsonUtils.parseString(definition, "termsOfUseUrl");
        appDefinition.accentColor = JsonUtils.parseString(definition, "accentColor");
        appDefinition.version = JsonUtils.parseString(definition, "version");

        // parseBotIdFromAppManifest(appDefinition, definition, logger);

        appDefinition.appDefinitionJson = definition.toString();

        // we create entitlement object from status field present in app definition.
        // status field is present only for Chat apps
        if (entitlement != null) {
            entitlement.appId = appDefinition.appId;
            entitlement.status = JsonUtils.parseString(definition, "state");
        }
    }

//    private static void parseBotIdFromAppManifest(@NonNull AppDefinition appDefinition,
//                                                  @NonNull JsonObject definition,
//                                                  @NonNull ILogger logger) {
//
//        if (populateBotIdFromBotsArray(appDefinition, definition, logger)) {
//            return;
//        }
//
//        if (populateBotIdFromInputExtensionsArray(appDefinition, definition, logger)) {
//            return;
//        }
//
//        populateBotIdAsAppId(appDefinition);
//    }
//
//    /**
//     * Sample JSON :
//     *
//     * "bots": [
//     *     {
//     *       "botId": "36b1586d-b1da-45d2-9b32-899c3757b6f8",
//     *       "scopes": [
//     *         "personal"
//     *       ]
//     *     }
//     *   ]
//     **/
//    private static boolean populateBotIdFromBotsArray(@NonNull AppDefinition appDefinition,
//                                                   @NonNull JsonObject definition,
//                                                   @NonNull ILogger logger) {
//
//        // parse Bots node and store the 1st botID if given
//        JsonArray botsArray = JsonUtils.getJsonArrayFromObject(definition, "bots");
//        if (!JsonUtils.isNullOrEmpty(botsArray)) {
//            JsonElement botObject = botsArray.get(0);
//            if (botObject != null && botObject.isJsonObject()) {
//                appDefinition.botId = JsonUtils.parseString(botObject, "id");
//                appDefinition.isNotificationOnly = JsonUtils.parseBoolean(botObject, "isNotificationOnly");
//                return true;
//            } else {
//                logger.log(LogPriority.ERROR, TAG, String.format("MalformedJSON: Malformed bot node for app: %s", appDefinition.appId));
//            }
//        }
//
//        return false;
//    }
//
//    /**
//     * Sample JSON :
//     *
//     * "inputExtensions": [
//     *     {
//     *       "botId": "4e1f8576-93d5-4c24-abb5-f02782e00a4e",
//     *       "canUpdateConfiguration": true,
//     *       "scopes": [
//     *         "Team",
//     *         "Personal"
//     *       ],
//     *       "commands": [
//     *         {
//     *           "id": "insertNote",
//     *           "initialRun": true,
//     *           "parameters": [
//     *             {
//     *               "name": "searchQuery",
//     *               "title": "Search Notes",
//     *               "description": "Search for notes"
//     *             }
//     *           ],
//     *           "title": "Insert Note",
//     *           "description": "Find and attach Evernote note to your message"
//     *         }
//     *       ]
//     *     }
//     *   ]
//     **/
//    private static boolean populateBotIdFromInputExtensionsArray(@NonNull AppDefinition appDefinition,
//                                                             @NonNull JsonObject definition,
//                                                             @NonNull ILogger logger) {
//
//        // If there is no botId then parse InputExtensions node and store the 1st botID if given
//        if (StringUtils.isEmptyOrWhiteSpace(appDefinition.botId)) {
//            JsonArray inputExtensionsArray = JsonUtils.getJsonArrayFromObject(definition, "inputExtensions");
//            if (!JsonUtils.isNullOrEmpty(inputExtensionsArray)) {
//                JsonElement inputExtensionObject = inputExtensionsArray.get(0);
//                if (inputExtensionObject != null && inputExtensionObject.isJsonObject()) {
//                    appDefinition.botId = JsonUtils.parseString(inputExtensionObject, "botId");
//                    return true;
//                } else {
//                    logger.log(LogPriority.ERROR, TAG, String.format("MalformedJSON: Malformed inputExtensions node for app: %s", appDefinition.appId));
//                }
//            }
//        }
//
//        return false;
//    }
//
//    private static void populateBotIdAsAppId(@NonNull AppDefinition appDefinition) {
//        // If there is no botId then we treat app Id as bot Id so that it is easier for the clients to query on botId.
//        if (StringUtils.isEmptyOrWhiteSpace(appDefinition.botId)) {
//            appDefinition.botId = appDefinition.appId;
//        }
//    }
//
//    @NonNull
//    static Pair<List<UserEntitlement>, Map<String, Integer>> parseUserEntitlements(final JsonObject userEntitlementsObject,
//                                                                                   @NonNull IAccountManager accountManager,
//                                                                                   @NonNull ILogger logger,
//                                                                                   @NonNull IUserConfiguration userConfiguration) {
//        final List<UserEntitlement> userEntitlements = new ArrayList<>();
//        final Map<String, Integer> pinnedApps = new ArrayMap<>();
//        StringBuilder logBuilder = new StringBuilder();
//        StringBuilder expectedAppIds = new StringBuilder();
//        int numberOfExpectedApps = 0;
//
//        for (Map.Entry<String, JsonElement> entry : userEntitlementsObject.entrySet()) {
//            if (entry != null) {
//                try {
//                    JsonArray entitlementsArrays = entry.getValue().getAsJsonArray();
//                    if (entitlementsArrays != null && entitlementsArrays.isJsonArray()) {
//                        for (JsonElement entitlement : entitlementsArrays) {
//                            if (entitlement != null && entitlement.isJsonObject()) {
//                                UserEntitlement userEntitlement = new UserEntitlement();
//                                userEntitlement.id = JsonUtils.parseString(entitlement, "id");
//                                userEntitlement.state = JsonUtils.parseString(entitlement, "state");
//                                userEntitlement.userId = accountManager.getUser().mri;
//
//                                userEntitlement.isAppBarPinned = JsonUtils.parseBoolean(entitlement, "isAppBarPinned");
//
//                                if (userEntitlement.isAppBarPinned) {
//                                    userEntitlement.appBarOrder = JsonUtils.parseInt(entitlement, "appBarOrder");
//                                    pinnedApps.put(userEntitlement.id, userEntitlement.appBarOrder);
//                                    numberOfExpectedApps += 1;
//                                    expectedAppIds.append(userEntitlement.id).append(", ");
//                                } else {
//                                    userEntitlement.appBarOrder = -1;
//                                }
//
//                                userEntitlements.add(userEntitlement);
//                            } else {
//                                logger.log(LogPriority.ERROR, TAG, "MalformedJSON: Malformed userEntitlement");
//                            }
//                        }
//                        logBuilder.append(String.format(
//                            "Total user entitlements: %s, Expected apps: %s, Expected app ids: %s, "
//                                    + "Pinned apps size: %s, Pinned apps: %s [%s]\n",
//                            userEntitlements.size(), numberOfExpectedApps, expectedAppIds, pinnedApps.size(),
//                            pinnedApps, accountManager.getCurrentUserObjectId()));
//                        logger.log(LogPriority.DEBUG, TAG, logBuilder.toString());
//                    }
//                } catch (IllegalStateException e) {
//                    logger.log(LogPriority.ERROR, TAG, "MalformedJSON: Malformed userEntitlement list");
//                }
//            }
//        }
//
//        return new Pair<>(userEntitlements, pinnedApps);
//    }
//
//    static List<TeamEntitlement> parseTeamEntitlements(JsonObject teamEntitlementsObject,
//                                                       @NonNull ILogger logger) {
//        List<TeamEntitlement> teamEntitlements = new ArrayList<>();
//        for (Map.Entry<String, JsonElement> entry : teamEntitlementsObject.entrySet()) {
//            if (entry != null) {
//                try {
//                    JsonArray entitlementTeam = entry.getValue().getAsJsonArray();
//                    if (entitlementTeam != null && entitlementTeam.isJsonArray()) {
//                        for (JsonElement entitlement : entitlementTeam) {
//                            if (entitlement != null && entitlement.isJsonObject()) {
//                                TeamEntitlement teamEntitlement = new TeamEntitlement();
//                                teamEntitlement.threadId = entry.getKey();
//                                teamEntitlement.appId = JsonUtils.parseString(entitlement, "id");
//                                teamEntitlement.status = JsonUtils.parseString(entitlement, "state");
//
//                                teamEntitlements.add(teamEntitlement);
//                            } else {
//                                logger.log(LogPriority.ERROR, TAG, "MalformedJSON: Malformed teamEntitlement");
//                            }
//                        }
//                    }
//                } catch (IllegalStateException e) {
//                    logger.log(LogPriority.ERROR, TAG, "MalformedJSON: Malformed teamEntitlements");
//                }
//            }
//        }
//
//        return teamEntitlements;
//    }
//
//    public static String extractMri(String url) {
//        // example url: https://co4-df-client-s.gateway.messenger.live.com/v1/users/ME/contacts/19:skypex_6ad3766f7cdc40dfb345d888fff1c364@thread.skype
//        String contacts = "contacts/";
//        if (url.contains(contacts)) {
//            int index = url.indexOf(contacts);
//            return url.substring(index + contacts.length());
//        }
//
//        return url;
//    }
//
//    /**
//     * This method is responsible to fetch user tenant settings from new schema after the required migration for convergence.
//     * <p>
//     * One can see the updated mapping in dev design document placed here:
//     * https://microsoft.sharepoint.com/teams/theteamspace/_layouts/OneNote.aspx?id=%2Fteams%2Ftheteamspace%2FSiteAssets%2Fteamspace%20Notebook%2FDev&wd=target%28Mobile%20Clients
//     * .one%7CFCC14B8F-7877-432E-9917-BD6E91D55E36%2FTenant%20Settings%20migration%7C80AEB532-8096-4184-BB8F-72A66C54F631%2F%29
//     */
//    private static void parseTenantSettingsPostConvergence(JsonObject rootElement, UserAggregatedSettings userAggregatedSettings, @NonNull ILogger logger) {
//        if (rootElement == null) {
//            return;
//        }
//
//        // read app settings from tenantSettingsV2
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, "tenantSettingsV2")) {
//            JsonObject tenantSettingsV2 = rootElement.getAsJsonObject("tenantSettingsV2");
//            if (tenantSettingsV2 != null && tenantSettingsV2.has(VALUE)) {
//                JsonObject tenantSettingsValue = tenantSettingsV2.getAsJsonObject(VALUE);
//
//                JsonObject bots = tenantSettingsValue.getAsJsonObject("apps");
//                if (bots != null) {
//                    userAggregatedSettings.isBotsEnabled = getAsBoolean(bots, "isAppsEnabled", false);
//                    userAggregatedSettings.isSideLoadedBotsEnabled = getAsBoolean(bots, "isSideLoadedAppsEnabled", false);
//                    userAggregatedSettings.isSideLoadingInteractionEnabled = getAsBoolean(bots, "isSideLoadingInteractionEnabled", false);
//                    JsonArray botSettingsArray = bots.getAsJsonArray("appSettingsList");
//                    if (botSettingsArray != null) {
//                        userAggregatedSettings.botsSettings = new ArrayMap<>();
//                        Gson gson = new Gson();
//                        for (JsonElement element : botSettingsArray) {
//                            UserAggregatedSettings.BotSettings botSettings = gson.fromJson(element, UserAggregatedSettings.BotSettings.class);
//                            if (botSettings != null) {
//                                userAggregatedSettings.botsSettings.put(botSettings.id, botSettings);
//                            }
//                        }
//                    }
//                }
//                // TODO : need to refactor the whole function to use GSON parser
//                JsonObject licenseCategorySettingsMap = tenantSettingsValue.getAsJsonObject("licenseCategorySettingsMap");
//                userAggregatedSettings.tenantSKU = getTenantSkuType(licenseCategorySettingsMap);
//            }
//        }
//
//        // read meetingPolicy
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, "meetingPolicy")) {
//            JsonObject meetingPolicy = rootElement.getAsJsonObject("meetingPolicy");
//            if (meetingPolicy != null && meetingPolicy.has(VALUE)) {
//                JsonObject values = meetingPolicy.getAsJsonObject(VALUE);
//                if (values != null) {
//                userAggregatedSettings.isVideoEnabled = getAsBoolean(values, "allowIPVideo", false);
//                    userAggregatedSettings.isChannelMeetingEnabled = getAsBoolean(values, "allowChannelMeetingScheduling", false);
//                    userAggregatedSettings.isPrivateMeetingEnabled = getAsBoolean(values, "allowPrivateMeetingScheduling", false);
//                    userAggregatedSettings.isAdhocChannelMeetingEnabled = getAsBoolean(values, "allowMeetNow", false);
//                    userAggregatedSettings.isPrivateAdhocMeetingEnabled = getAsBoolean(values, "allowPrivateMeetNow", false);
//                    userAggregatedSettings.allowCloudRecording = getAsBoolean(values, "allowCloudRecording", false);
//                    userAggregatedSettings.allowRecordingStorageOutsideRegion = getAsBoolean(values, "allowRecordingStorageOutsideRegion", false);
//                    userAggregatedSettings.allowPowerPointSharing = getAsBoolean(values, "allowPowerPointSharing", false);
//                    userAggregatedSettings.allowWhiteboard = getAsBoolean(values, "allowWhiteboard", false);
//                    userAggregatedSettings.mediaBitRateKb = getAsLong(values, "mediaBitRateKb", 0);
//                    userAggregatedSettings.meetingChatEnableType = getAsString(values, "meetingChatEnableType", UserAggregatedSettings.MeetingChatEnableType.ENABLED);
//                    userAggregatedSettings.appDesktopSharing = getAsString(values, "appDesktopSharing", UserAggregatedSettings.AppDesktopSharingType.ENTIRESCREEN);
//                    userAggregatedSettings.liveCaptionsEnabledType = getAsString(values, "liveCaptionsEnabledType",
//                                                                                 UserAggregatedSettings.LiveCaptionsEnabledType.DISABLEDUSEROVERRIDE);
//                    userAggregatedSettings.liveCaptionsEnabledTypeForCalls = getAsString(values, "liveCaptionsEnabledTypeForCalls",
//                            UserAggregatedSettings.LiveCaptionsEnabledTypeForCalls.DISABLEDUSEROVERRIDE);
//                    userAggregatedSettings.ipAudioMode = getAsString(values, "ipAudioMode",
//                            UserAggregatedSettings.IPAudioVideoMode.ENABLEDOUTGOINGINCOMING);
//                    userAggregatedSettings.ipVideoMode = getAsString(values, "ipVideoMode",
//                            UserAggregatedSettings.IPAudioVideoMode.ENABLEDOUTGOINGINCOMING);
//                    userAggregatedSettings.recordingStorageMode = getAsString(values, "recordingStorageMode",
//                            UserAggregatedSettings.RecordingStorageMode.STREAM);
//                    userAggregatedSettings.videoFilterMode = getVideoFilterMode(getAsString(values, "videoFiltersMode", StringUtilities.EMPTY));
//                }
//            }
//        }
//
//        // read callingPolicy
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, "callingPolicy")) {
//            JsonObject callingPolicy = rootElement.getAsJsonObject("callingPolicy");
//            if (callingPolicy != null && callingPolicy.has(VALUE)) {
//                JsonObject values = callingPolicy.getAsJsonObject(VALUE);
//                if (values != null) {
//                    String allowPrivateCalling = getAsString(values, "allowPrivateCalling", NOT_SET);
//                    userAggregatedSettings.isAudioCallsEnabled = isFlagTrue(allowPrivateCalling);
//                    userAggregatedSettings.allowVoicemail = getAsString(values, "allowVoicemail", UserAggregatedSettings.AllowVocemailMode.USEROVERRIDE);
//                    logger.log(LogPriority.INFO, "GetUserAggregateSettings", "allowPrivateCalling = " + allowPrivateCalling);
//                    userAggregatedSettings.isGroupCallingEnabled = getAsBoolean(values, "allowGroupCalling", false);
//                    userAggregatedSettings.preventTollBypass = getAsBoolean(values, "preventTollBypass", false);
//                    userAggregatedSettings.autoAnswerEnabledType = getAsString(values, "autoAnswerEnabledType", UserAggregatedSettings.AutoAnswerEnabledType.DISABLED);
//                    userAggregatedSettings.musicOnHoldEnabledType = getAsString(values, "musicOnHoldEnabledType", UserAggregatedSettings.MusicOnHoldEnabledType.ENABLED);
//                    userAggregatedSettings.allowCloudRecordingForCalls = getAsBoolean(values, "allowCloudRecordingForCalls", false);
//                }
//            }
//        }
//
//        // read callParkPolicy
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, CALL_PARK_POLICY)) {
//            JsonObject callParkPolicy = rootElement.getAsJsonObject(CALL_PARK_POLICY);
//            if (callParkPolicy != null && callParkPolicy.has(VALUE)) {
//                JsonObject values = callParkPolicy.getAsJsonObject(VALUE);
//                if (values != null) {
//                    userAggregatedSettings.isCallParkEnabled = getAsBoolean(values, ALLOW_CALL_PARK, false);
//                    logger.log(LogPriority.INFO, "GetUserAggregateSettings",
//                            "isCallParkPolicyEnabled = " + userAggregatedSettings.isCallParkEnabled);
//                }
//            }
//        }
//
//        // read callParkPolicy
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, MOBILITY_POLICY)) {
//            final JsonObject mobilityPolicy = rootElement.getAsJsonObject(MOBILITY_POLICY);
//            if (mobilityPolicy != null && mobilityPolicy.has(VALUE)) {
//                final JsonObject values = mobilityPolicy.getAsJsonObject(VALUE);
//                if (values != null) {
//                    final Class<UserAggregatedSettings.MobilePolicyMode> enumClass = UserAggregatedSettings.MobilePolicyMode.class;
//                    final UserAggregatedSettings.MobilePolicyMode defaultMode = UserAggregatedSettings.MobilePolicyMode.AllNetworks;
//                    // Retrieve the policy calling modes, falling back to AllNetworks - if a mode is not specified, calling in the mode is not restricted.
//                    userAggregatedSettings.videoMobileMode = getAsEnumIgnoreCase(enumClass, logger, values, IP_VIDEO_MOBILE_MODE, defaultMode);
//                    userAggregatedSettings.audioMobileMode = getAsEnumIgnoreCase(enumClass, logger, values, IP_AUDIO_MOBILE_MODE, defaultMode);
//                    logger.log(LogPriority.INFO, "GetUserAggregateSettings", "videoMobileMode = " + userAggregatedSettings.videoMobileMode.name());
//                    logger.log(LogPriority.INFO, "GetUserAggregateSettings", "audioMobileMode = " + userAggregatedSettings.audioMobileMode.name());
//                }
//            }
//        }
//
//        // read clientSettings
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, "clientSettings")) {
//            JsonObject clientSettings = rootElement.getAsJsonObject("clientSettings");
//            if (clientSettings != null && clientSettings.has(VALUE)) {
//                JsonObject values = clientSettings.getAsJsonObject(VALUE);
//                if (values != null) {
//                    userAggregatedSettings.isOrganizationTabEnabled = getAsBoolean(values, "allowOrganizationTab", false);
//                    userAggregatedSettings.isSkypeBusinessInteropEnabled = getAsBoolean(values, "allowSkypeBusinessInterop", false);
//                    userAggregatedSettings.allowEmailIntoChannel = getAsBoolean(values, "allowEmailIntoChannel", true);
//                }
//            }
//        }
//
//        // read messagingPolicy
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, "messagingPolicy")) {
//            JsonObject messagingPolicy = rootElement.getAsJsonObject("messagingPolicy");
//            if (messagingPolicy != null && messagingPolicy.has(VALUE)) {
//                JsonObject values = messagingPolicy.getAsJsonObject(VALUE);
//                if (values != null) {
//                    userAggregatedSettings.enableUserDeleteOwnMessages = getAsBoolean(values, "allowUserDeleteMessage", false);
//                    userAggregatedSettings.enableUserEditOwnMessages = getAsBoolean(values, "allowUserEditMessage", false);
//                    String allowUserChat = getAsString(values, "allowUserChat", NOT_SET);
//                    userAggregatedSettings.enableUserPrivateChat = isFlagTrue(allowUserChat);
//                    logger.log(LogPriority.INFO, "GetUserAggregateSettings", "allowUserChat = " + allowUserChat);
//                    userAggregatedSettings.enableOwnersDeleteAllMessages = getAsBoolean(values, "allowOwnerDeleteMessage", false);
//                    userAggregatedSettings.enableUserTranslation = getAsBoolean(values, "allowUserTranslation", false);
//                    userAggregatedSettings.isGiphyEnabled = getAsBoolean(values, "allowGiphy", false);
//                    userAggregatedSettings.isGiphyDisplayEnabled = getAsBoolean(values, "allowGiphyDisplay", false);
//                    userAggregatedSettings.allowUrlPreviews = getAsBoolean(values, "allowUrlPreviews", false);
//                    userAggregatedSettings.giphyRatingType = getAsString(values, "giphyRatingType", "Strict");
//                    userAggregatedSettings.isStickersEnabled = getAsBoolean(values, "allowStickers", false);
//                    userAggregatedSettings.isCustomMemesEnabled = getAsBoolean(values, "allowMemes", false);
//                    // Enum Values = Everyone|None|UserPreference
//                    userAggregatedSettings.readReceiptsEnabledType = getAsString(values, "readReceiptsEnabledType", "UserPreference");
//                    userAggregatedSettings.audioMessageEnabledType = getAsString(values, "audioMessageEnabledType",
//                            UserAggregatedSettings.AudioMessageEnabledType.CHATS_AND_CHANNELS);
//                    userAggregatedSettings.allowPriorityMessages = getAsBoolean(values, "allowPriorityMessages", false);
//                    userAggregatedSettings.allowEscalationUpdate = getAsBoolean(values, "allowEscalationUpdate", false);
//                    // Admin setting for allowing remove user from group chat
//                    userAggregatedSettings.allowRemoveUser = getAsBoolean(values, "allowRemoveUser", false);
//                    // This is the admin setting for showing unified chats & channels:
//                    userAggregatedSettings.channelsInChatListEnabledType =
//                            getAsString(
//                                    values,
//                                    "channelsInChatListEnabledType",
//                                    "DisabledUserOverride");
//                    userAggregatedSettings.allowSmartReply = getAsBoolean(values, "allowSmartReply", false);
//                }
//            }
//        }
//
//        // read tenantSiteUrl
//        if (!isErrorFromUserAggregatedSettings(rootElement, logger, "tenantSiteUrl")) {
//            JsonObject tenantSiteUrlObject = rootElement.getAsJsonObject("tenantSiteUrl");
//            if (tenantSiteUrlObject != null && tenantSiteUrlObject.has(VALUE)) {
//                JsonObject values = tenantSiteUrlObject.getAsJsonObject(VALUE);
//                if (values != null) {
//                    userAggregatedSettings.tenantSiteUrl = getAsString(values, "webUrl", "");
//                }
//            }
//        }
//    }
//
//    private static String getTenantSkuType(JsonObject licenseCategorySettingsMap) {
//        String tenantSKU = UserAggregatedSettings.TenantSkuType.UNKNOWN;
//        if (licenseCategorySettingsMap != null) {
//            Set<String> licenseCategories = licenseCategorySettingsMap.keySet();
//            for (String category:licenseCategories) {
//                String categoryLowerCase = category.toLowerCase();
//                if (UserAggregatedSettings.LicenseCategoriesType.EDUCATION_FACULTY.equals(categoryLowerCase)
//                        || UserAggregatedSettings.LicenseCategoriesType.EDUCATION_STUDENT.equals(categoryLowerCase)) {
//                    tenantSKU = UserAggregatedSettings.TenantSkuType.EDU;
//                    break;
//                }
//                if (UserAggregatedSettings.LicenseCategoriesType.BUSINESS_ENTERPRISE.equals(categoryLowerCase)) {
//                    tenantSKU = UserAggregatedSettings.TenantSkuType.ENTERPRISE;
//                    break;
//                }
//                if (UserAggregatedSettings.LicenseCategoriesType.FREE.equals(categoryLowerCase)) {
//                    tenantSKU = UserAggregatedSettings.TenantSkuType.FREE;
//                    break;
//                }
//            }
//        }
//        return tenantSKU;
//    }
//
//    private static boolean isErrorFromUserAggregatedSettings(@NonNull JsonObject rootElement, @NonNull ILogger logger, String jPath) {
//        JsonObject resourceSetting = rootElement.getAsJsonObject(jPath);
//        if (resourceSetting != null && resourceSetting.has("resultCode")
//            && ERROR_STRING_AGGREGATED_SETTINGS.equalsIgnoreCase(JsonUtils.parseString(resourceSetting, "resultCode"))) {
//            logger.log(LogPriority.INFO, TAG, "Error occured on server getting these settings. Setting Type: %s", jPath);
//            return true;
//        }
//
//        return false;
//    }
//
//    private static void parseUserPropertiesSettings(JsonObject userPropertiesJson, UserAggregatedSettings tenantSettings, @NonNull ILogger logger) {
//        if (userPropertiesJson != null && userPropertiesJson.has(VALUE)) {
//            JsonObject userPropertiesSettings = userPropertiesJson.getAsJsonObject(VALUE);
//            parseAutoDiscoverSettings(userPropertiesSettings, tenantSettings, logger);
//        }
//    }
//
//    @VisibleForTesting
//    protected static void parseAutoDiscoverSettings(@NonNull JsonObject autoDiscoverJson, @NonNull UserAggregatedSettings tenantSettings, @NonNull ILogger logger) {
//        tenantSettings.isOnCloud = getAsBoolean(autoDiscoverJson, "isOnCloud", false);
//        if (autoDiscoverJson != null && autoDiscoverJson.has(AUTO_DISCOVER_SETTINGS)) {
//            JsonObject autoDiscoverSettings = autoDiscoverJson.getAsJsonObject(AUTO_DISCOVER_SETTINGS);
//            if (autoDiscoverSettings != null && autoDiscoverSettings.has(REST)) {
//                JsonObject restSettings = autoDiscoverSettings.getAsJsonObject(REST);
//                String url = getAsString(restSettings, URL, StringUtils.EMPTY_STRING);
//                String restMailBoxStatus = getAsString(restSettings, MAILBOX_STATUS, StringUtils.EMPTY_STRING);
//                tenantSettings.isRestEnabled = !StringUtils.isEmptyOrWhiteSpace(url) && tenantSettings.isOnCloud
//                        && !StringUtils.isEmptyOrWhiteSpace(restMailBoxStatus) && !NOT_DISCOVERABLE.equalsIgnoreCase(restMailBoxStatus);
//                tenantSettings.restUrl = url;
//                int urlLength = StringUtils.isEmptyOrWhiteSpace(tenantSettings.restUrl) ? 0 : tenantSettings.restUrl.length();
//                logger.log(LogPriority.INFO, "RestSettings", "restMailboxStatus: %s, restUrl length: %d", restMailBoxStatus, urlLength);
//            }
//            boolean isEWSEnabled = false;
//            if (autoDiscoverSettings != null && autoDiscoverSettings.has(EWS)) {
//                JsonObject ewsSettings = autoDiscoverSettings.getAsJsonObject(EWS);
//                String ewsMailboxStatus = getAsString(ewsSettings, MAILBOX_STATUS, StringUtils.EMPTY_STRING);
//                isEWSEnabled = !StringUtils.isEmptyOrWhiteSpace(ewsMailboxStatus) && !NOT_DISCOVERABLE.equalsIgnoreCase(ewsMailboxStatus);
//                logger.log(LogPriority.INFO, "EwsSettings", "EwsMailboxStatus: %s", ewsMailboxStatus);
//            }
//            tenantSettings.isMailboxDiscoverableOnService = isEWSEnabled;
//            // value should not be changed if once changed to true, as server response becomes flaky sometimes.
//            tenantSettings.isMailboxDiscoverable = tenantSettings.isMailboxDiscoverableOnService || tenantSettings.isMailboxDiscoverable;
//            logger.log(LogPriority.INFO, "GetAutoDiscoverSettings",
//                    "isMailboxDiscoverable: %b, isRestEnabled: %b, isEWSEnabled: %b, isOnCloudEnabled: %b",
//                    tenantSettings.isMailboxDiscoverableOnService,
//                    tenantSettings.isRestEnabled,
//                    isEWSEnabled,
//                    tenantSettings.isOnCloud);
//        }
//    }
//
//    private static void parseUserResourcesSettings(JsonObject userResourcesSettings, UserAggregatedSettings tenantSettings) {
//        if (userResourcesSettings != null && userResourcesSettings.has(VALUE)) {
//            JsonObject valueObject = userResourcesSettings.getAsJsonObject(VALUE);
//            tenantSettings.isSipEnabled = getAsBoolean(valueObject, "isSipEnabled", false);
//
//            // this setting will be deprecated
//            // MT is moving isSfBCloud flag from userResource to voiceAdminSetting(BVD service)
//            if (valueObject.has("isSfbCloud")) {
//                tenantSettings.isSfbCloud = getAsBoolean(valueObject, "isSfbCloud", false);
//            }
//
//            tenantSettings.isTenantAdmin = getAsBoolean(valueObject, "isTenantAdmin", false);
//            tenantSettings.userType = getAsString(valueObject, "userType", StringUtilities.EMPTY);
//
//            JsonObject licenseSettings = valueObject.getAsJsonObject("licenseSettings");
//
//            tenantSettings.isSharePointEnabled = getAsBoolean(licenseSettings, "isSharePointEnabled", false);
//            tenantSettings.isExchangeEnabled = getAsBoolean(licenseSettings, "isExchangeEnabled", false);
//            tenantSettings.isPowerBIEnabled = getAsBoolean(licenseSettings, "isPowerBIEnabled", false);
//            tenantSettings.isProjectWorkManagementEnabled = getAsBoolean(licenseSettings, "isProjectWorkManagementEnabled", false);
//            tenantSettings.isOfficeEnabled = getAsBoolean(licenseSettings, "isOfficeEnabled", false);
//            tenantSettings.isOneDriveForBusinessEnabled = getAsBoolean(licenseSettings, "isOneDriveForBusinessEnabled", false);
//            tenantSettings.isAtpSafelinksEnabled = getAsBoolean(licenseSettings, "isAtpSafelinksEnabled", false);
//            tenantSettings.licenseType = getAsString(valueObject, "licenseType", null);
//        }
//    }
//
//    private static void parsePolicySettings(@Nullable JsonObject policySettings, @NonNull UserAggregatedSettings tenantSettings) {
//        if (policySettings != null && policySettings.has(VALUE)) {
//            JsonObject valueObject = policySettings.getAsJsonObject(VALUE);
//            JsonObject locationPolicyJsonObject = valueObject.getAsJsonObject("locationPolicy");
//            Gson gson = new Gson();
//
//            if (locationPolicyJsonObject != null) {
//                UserAggregatedSettings.LocationPolicy locationPolicy = gson.fromJson(locationPolicyJsonObject, UserAggregatedSettings.LocationPolicy.class);
//                if (locationPolicy != null) {
//                    tenantSettings.locationPolicy = locationPolicy;
//                }
//            }
//
//            JsonObject dialPlanPolicyJsonObject = valueObject.getAsJsonObject("dialPlanPolicy");
//            if (dialPlanPolicyJsonObject != null) {
//                UserAggregatedSettings.DialPlanPolicy dialPlanPolicy = gson.fromJson(dialPlanPolicyJsonObject, UserAggregatedSettings.DialPlanPolicy.class);
//                if (dialPlanPolicy != null) {
//                    tenantSettings.dialPlanPolicy = dialPlanPolicy;
//                }
//            }
//
//            // enable block caller ID toggle
//            JsonObject callingLineIdentityPolicyJsonObject = valueObject.getAsJsonObject("callingLineIdentityPolicy");
//            if (callingLineIdentityPolicyJsonObject != null) {
//                tenantSettings.isToggleBlockOutgoingCallerIdAllowed = getAsBoolean(callingLineIdentityPolicyJsonObject, "enableUserOverride", false);
//            }
//
//            JsonObject userByotJsonObject = valueObject.getAsJsonObject("byot");
//            if (userByotJsonObject != null) {
//                UserAggregatedSettings.UserByot userByotPolicy = gson.fromJson(userByotJsonObject, UserAggregatedSettings.UserByot.class);
//                if (userByotPolicy != null) {
//                    tenantSettings.byot = userByotPolicy;
//                }
//            }
//
//        }
//    }
//
//    private static void parseTenantVoiceSettings(JsonObject tenantVoiceSettings, UserAggregatedSettings tenantSettings) {
//        if (tenantVoiceSettings != null && tenantVoiceSettings.has(VALUE)) {
//            JsonObject valueObject = tenantVoiceSettings.getAsJsonObject(VALUE);
//            tenantSettings.pstnType = getTenantType(getAsString(valueObject, "pstnType", StringUtilities.EMPTY));
//            tenantSettings.voiceTenantType = getTenantType(getAsString(valueObject, "voiceTenantType", StringUtilities.EMPTY));
//        }
//    }
//
//    private static void parseVoiceAdminSettings(JsonObject voiceAdminSettings, UserAggregatedSettings tenantSettings) {
//        if (voiceAdminSettings != null && voiceAdminSettings.has(VALUE)) {
//            JsonObject valueObject = voiceAdminSettings.getAsJsonObject(VALUE);
//            Gson gson = new Gson();
//
//            // update isSfbCloud flag from BVD service
//            if (valueObject != null) {
//                if (valueObject.has("isSfbCloud")) {
//                        tenantSettings.isSfbCloud = getAsBoolean(valueObject, "isSfbCloud", false);
//                }
//
//                VoiceAdminSettings voiceAdminSetting = gson.fromJson(valueObject, VoiceAdminSettings.class);
//                if (voiceAdminSetting != null) {
//                    tenantSettings.voiceAdminSettings = voiceAdminSetting;
//                }
//            }
//        }
//    }
//
//    private static void parseOnlineDialinConferencingPolicy(JsonObject onlineDialinConferencingPolicy, UserAggregatedSettings tenantSettings) {
//        if (onlineDialinConferencingPolicy != null && onlineDialinConferencingPolicy.has(VALUE)) {
//            JsonObject valueObject = onlineDialinConferencingPolicy.getAsJsonObject(VALUE);
//
//            if (valueObject != null && !valueObject.isJsonNull()) {
//                tenantSettings.allowOnlineDialinConferencing = getAsBoolean(valueObject, "allowOnlineDialinConferencing", false);
//            }
//        }
//    }
//
//    private static void parseShiftsPolicy(JsonObject shiftsPolicy, UserAggregatedSettings tenantSettings) {
//        if (shiftsPolicy != null && shiftsPolicy.has(VALUE)) {
//            JsonObject valueObject = shiftsPolicy.getAsJsonObject(VALUE);
//            tenantSettings.shiftsPolicySettings = new UserAggregatedSettings.ShiftsPolicySettings();
//            if (valueObject != null && !valueObject.isJsonNull()) {
//                tenantSettings.shiftNoticeFrequency = getAsString(valueObject, "shiftNoticeFrequency", StringUtilities.EMPTY);
//                tenantSettings.shiftsPolicySettings.enableShiftPresence = getAsBoolean(valueObject, "enableShiftPresence", false);
//                tenantSettings.shiftsPolicySettings.shiftNoticeMessageType = getAsString(valueObject, "shiftNoticeMessageType", StringUtilities.EMPTY);
//                tenantSettings.shiftsPolicySettings.shiftNoticeMessageCustom = getAsString(valueObject, "shiftNoticeMessageCustom", StringUtilities.EMPTY);
//
//                tenantSettings.shiftsPolicySettings.accessType = getAsString(valueObject, "accessType",
//                                                                                       UserAggregatedSettings.ShiftsBlockingPolicyType.UNRESTRICTED_ACCESS);
//                tenantSettings.shiftsPolicySettings.accessGracePeriodInMinutes = getAsInteger(valueObject, "accessGracePeriodMinutes",
//                                                                                                   DEFAULT_TEAMS_APP_BLOCKING_BUFFER_IN_MINUTES);
//            }
//        }
//    }
//
//    private static void parseAdminSettings(JsonObject adminSettings, UserAggregatedSettings tenantSettings) {
//        if (adminSettings != null && adminSettings.has(VALUE)) {
//            JsonObject valueObject = adminSettings.getAsJsonObject(VALUE);
//            Gson gson = new Gson();
//
//            if (valueObject != null) {
//                UserAggregatedSettings.AdminSettings tempAdminSetting = new UserAggregatedSettings.AdminSettings();
//
//                JsonObject conferencingConfigJsonObject = valueObject.getAsJsonObject("conferencingConfigurationSetting");
//                UserAggregatedSettings.ConferenceConfigurationSetting conferenceConfig;
//                if (conferencingConfigJsonObject != null) {
//                    conferenceConfig = gson.fromJson(conferencingConfigJsonObject, UserAggregatedSettings.ConferenceConfigurationSetting.class);
//                    tempAdminSetting.conferenceConfigurationSetting = conferenceConfig;
//                }
//
//                JsonObject mediaConfigJsonObject = valueObject.getAsJsonObject("mediaConfigurationSetting");
//                UserAggregatedSettings.MediaConfigurationSetting mediaConfig;
//                if (mediaConfigJsonObject != null) {
//                    mediaConfig = gson.fromJson(mediaConfigJsonObject, UserAggregatedSettings.MediaConfigurationSetting.class);
//                    tempAdminSetting.mediaConfigurationSetting = mediaConfig;
//                }
//
//                if (tempAdminSetting.conferenceConfigurationSetting != null && tempAdminSetting.mediaConfigurationSetting != null) {
//                    tenantSettings.adminSettings = tempAdminSetting;
//                }
//            }
//        }
//    }
//
//    private static void parseTenantProperties(JsonObject tenantProperties, UserAggregatedSettings userAggregatedSettings) {
//        if (tenantProperties != null && tenantProperties.has(VALUE)) {
//            JsonObject tenantPropertiesObject = tenantProperties.getAsJsonObject(VALUE);
//            userAggregatedSettings.isNutmix = getAsBoolean(tenantPropertiesObject, "isFreemium", false);
//            userAggregatedSettings.defaultTeamThreadId = getAsString(tenantPropertiesObject, "defaultTeamThreadId", StringUtilities.EMPTY);
//
//            // This will be null for enterprise, 2 for freemium, and 3 for TFL
//            userAggregatedSettings.tenantModel = getAsString(tenantPropertiesObject, "tenantModel", StringUtilities.EMPTY);
//        }
//    }
//
//    private static void parseIpPhonePolicy(JsonObject tenantProperties, UserAggregatedSettings userAggregatedSettings, @NonNull IExperimentationManager experimentationManager) {
//        if (tenantProperties != null && tenantProperties.has(VALUE)) {
//            JsonObject ipPhonePolicyObject = tenantProperties.getAsJsonObject(VALUE);
//            userAggregatedSettings.userSignInMode = getUserSignInMode(getAsString(ipPhonePolicyObject, "signInMode", StringUtilities.EMPTY));
//            userAggregatedSettings.commonAreaPhoneStatus = getCommonAreaPhoneSearchStatus(getAsString(ipPhonePolicyObject, "searchOnCommonAreaMode", StringUtilities.EMPTY));
//            userAggregatedSettings.isHotDeskingEnabled = getAsBoolean(ipPhonePolicyObject, "allowHotDesking", false);
//            userAggregatedSettings.homeScreenStatus = getAsString(ipPhonePolicyObject, "allowHomeScreen", StringUtilities.EMPTY);
//            userAggregatedSettings.hotDeskingIdleTimeout = TimeUnit.MINUTES.toMillis(getAsLong(ipPhonePolicyObject, "hotDeskingIdleTimeoutInMinutes",
//                    experimentationManager.hotDeskingDefaultTimeout()));
//        }
//    }
//
//    private static void parseTeamsAndChannelsPolicy(
//            JsonObject tenantProperties, UserAggregatedSettings userAggregatedSettings) {
//        if (tenantProperties != null && tenantProperties.has(VALUE)) {
//            JsonObject teamsAndChannelsPolicy = tenantProperties.getAsJsonObject(VALUE);
//            userAggregatedSettings.allowPrivateChannelCreation =
//                    getAsBoolean(teamsAndChannelsPolicy, "allowPrivateChannelCreation", false);
//        }
//    }
//
//    private static void parseTargetingPolicy(JsonObject tenantProperties, UserAggregatedSettings userAggregatedSettings) {
//        if (tenantProperties != null && tenantProperties.has(VALUE)) {
//            JsonObject targetingPolicy = tenantProperties.getAsJsonObject(VALUE);
//            String manageTagsPermissionMode = getAsString(targetingPolicy, "manageTagsPermissionMode", "teamOwners");
//            String teamOwnersEditWhoCanManageTagsMode = getAsString(targetingPolicy, "teamOwnersEditWhoCanManageTagsMode", "Disabled");
//            String customTagsMode = getAsString(targetingPolicy, "customTagsMode", "Disabled");
//
//            //Parse disallowed sources, none by default.
//            JsonElement disallowedSourcesElement = tenantProperties.get("disallowedSources");
//            List<String> disallowedSources = new ArrayList<>();
//            if (disallowedSourcesElement != null && disallowedSourcesElement.isJsonArray()) {
//                JsonArray disallowedSourcesArray = disallowedSourcesElement.getAsJsonArray();
//                for (JsonElement arrayElement : disallowedSourcesArray) {
//                    disallowedSources.add(arrayElement.getAsString());
//                }
//            }
//            userAggregatedSettings.teamMemberTagsDisallowedSources = disallowedSources;
//
//
//            //Parse disallowed sources, none by default.
//            JsonElement suggestedTagsElement = tenantProperties.get("disallowedSources");
//            List<String> suggestedTags = new ArrayList<>();
//            if (suggestedTagsElement != null && suggestedTagsElement.isJsonArray()) {
//                JsonArray suggestedTagsArray = suggestedTagsElement.getAsJsonArray();
//                for (JsonElement arrayElement : suggestedTagsArray) {
//                    suggestedTags.add(arrayElement.getAsString());
//                }
//            }
//            userAggregatedSettings.teamMemberTagsSuggestedTagNames = suggestedTags;
//
//
//            if (manageTagsPermissionMode.equals("TagPermissionsDisabled")) {
//                userAggregatedSettings.teamMemberTagPermissionsMode = "Disabled";
//            } else if (manageTagsPermissionMode.equals("EnabledTeamOwnerMember")) {
//                userAggregatedSettings.teamMemberTagPermissionsMode = "OwnersAndMembers";
//            } else {
//                userAggregatedSettings.teamMemberTagPermissionsMode = "OwnersOnly";
//            }
//
//            userAggregatedSettings.teamMemberTagsCustomTagsAllowed = customTagsMode.equals("Enabled");
//            userAggregatedSettings.teamMemberTagsOwnersCanOverwriteTagSettings = teamOwnersEditWhoCanManageTagsMode.equals("EnabledTeamOwner");
//            userAggregatedSettings.teamMemberTagSettingsUpdateTime = System.currentTimeMillis();
//        }
//    }
//
//    private static void parsefeedbackPolicy(
//            @Nullable JsonObject tenantProperties, @NonNull UserAggregatedSettings userAggregatedSettings) {
//        if (tenantProperties != null && tenantProperties.has(VALUE)) {
//            JsonObject feedbackPolicy = tenantProperties.getAsJsonObject(VALUE);
//            userAggregatedSettings.receiveSurveysMode = getAsString(feedbackPolicy, "receiveSurveysMode", "Enabled");
//            userAggregatedSettings.userInitiatedMode = getAsString(feedbackPolicy, "userInitiatedMode", "Disabled").equalsIgnoreCase("Enabled");
//        }
//    }
//
//    private static void parseBranchSurvivabilityPolicy(
//            @Nullable JsonObject tenantProperties, @NonNull UserAggregatedSettings userAggregatedSettings) {
//        if (tenantProperties != null && tenantProperties.has(VALUE)) {
//            JsonObject branchSurvivabilityPolicy = tenantProperties.getAsJsonObject(VALUE);
//            userAggregatedSettings.branchApplianceFqdns =
//                    getAsStringArray(branchSurvivabilityPolicy, "branchApplianceFqdns", StringUtilities.EMPTY_ARRAY);
//        }
//    }
//
//    protected static String getAsString(JsonObject jsonObject, String name, String defaultValue) {
//        JsonElement element = jsonObject.get(name);
//        if (element != null && !element.isJsonNull()) {
//            return element.getAsString();
//        }
//
//        return defaultValue;
//    }
//
//    protected static String[] getAsStringArray(@NonNull JsonObject jsonObject, @NonNull String name, @NonNull String[] defaultValue) {
//        JsonElement element = jsonObject.get(name);
//        if (element != null && element.isJsonArray()) {
//            JsonArray jsonArray = element.getAsJsonArray();
//            if (jsonArray != null && jsonArray.size() > 0) {
//                String[] stringArray = new String[jsonArray.size()];
//                for (int i = 0; i < jsonArray.size(); i++) {
//                    stringArray[i] = jsonArray.get(i).getAsString();
//                }
//                return stringArray;
//            }
//        }
//        return defaultValue;
//    }
//
//    protected static int getAsInteger(JsonObject jsonObject, String name, int defaultValue) {
//        JsonElement element = jsonObject.get(name);
//        try {
//            if (element != null && !element.isJsonNull()) {
//                return element.getAsInt();
//            }
//        } catch (NumberFormatException nfe) {
//            // Do nothing, as it returns default value.
//        }
//
//        return defaultValue;
//    }
//
//
//    static long getAsLong(JsonObject jsonObject, String name, int defaultValue) {
//        JsonElement element = jsonObject.get(name);
//        try {
//            if (element != null && !element.isJsonNull()) {
//                return element.getAsLong();
//            }
//        } catch (Exception nfe) {
//            // Do nothing, as it returns default value.
//        }
//
//        return defaultValue;
//    }
//
//    protected static boolean getAsBoolean(JsonObject jsonObject, String name, boolean defaultValue) {
//        JsonElement element = jsonObject.get(name);
//        if (element != null && !element.isJsonNull()) {
//            return element.getAsBoolean();
//        }
//
//        return defaultValue;
//    }
//
//    @NonNull
//    static <E extends Enum<E>> E getAsEnumIgnoreCase(
//            @NonNull Class<E> enumClass,
//            @NonNull final ILogger logger,
//            @NonNull final JsonObject jsonObject,
//            @NonNull final String propertyName,
//            @NonNull final E defaultValue) {
//        final String memberName = getAsString(jsonObject, propertyName, null);
//        // Run a case insensitive search of an enum member
//        if (memberName != null) {
//            for (E member : enumClass.getEnumConstants()) {
//                if (memberName.equalsIgnoreCase(member.name())) {
//                    return member;
//                }
//            }
//        }
//
//        logger.log(LogPriority.WARNING, "getAsEnumIgnoreCase", "%s: falling back to the default value %s", enumClass.getName(), defaultValue.name());
//
//        return defaultValue;
//    }
//
//    private static boolean isFlagTrue(@NonNull String value) {
//        return !(NOT_SET.equals(value) || BACK_UP_FALSE.equalsIgnoreCase(value));
//    }
//
//    @UserAggregatedSettings.SignInMode
//    static int getUserSignInMode(@NonNull String userSignInMode) {
//        switch (userSignInMode.toUpperCase()) {
//            case UserAggregatedSettings.SignInMode.USER_MODE_STR:
//                return UserAggregatedSettings.SignInMode.USER;
//            case UserAggregatedSettings.SignInMode.COMMON_AREA_MODE_STR:
//                return UserAggregatedSettings.SignInMode.COMMON_AREA_PHONE;
//            case UserAggregatedSettings.SignInMode.MEETING_AREA_MODE_STR:
//                return UserAggregatedSettings.SignInMode.MEETING_PHONE;
//            default:
//                return UserAggregatedSettings.SignInMode.UNKNOWN;
//        }
//    }
//
//    @UserAggregatedSettings.TenantType
//    private static int getTenantType(@NonNull String tenantType) {
//        switch (tenantType.toUpperCase()) {
//            case UserAggregatedSettings.TenantType.ONLINE_STR:
//                return UserAggregatedSettings.TenantType.ONLINE;
//            case UserAggregatedSettings.TenantType.ONPREM_STR:
//                return UserAggregatedSettings.TenantType.ON_PREM;
//            default:
//                return UserAggregatedSettings.TenantType.UNKNOWN;
//        }
//    }
//
//    @UserAggregatedSettings.VideoFilterMode
//    private static int getVideoFilterMode(@NonNull String videoFilterMode) {
//        switch (videoFilterMode.toUpperCase()) {
//            case UserAggregatedSettings.VideoFilterMode.ALL_FILTER_STR:
//                return UserAggregatedSettings.VideoFilterMode.ALL_FILTER;
//            case UserAggregatedSettings.VideoFilterMode.NO_FILTER_STR:
//                return UserAggregatedSettings.VideoFilterMode.NO_FILTER;
//            case UserAggregatedSettings.VideoFilterMode.BLUR_ONLY_STR:
//                return UserAggregatedSettings.VideoFilterMode.BLUR_ONLY;
//            case UserAggregatedSettings.VideoFilterMode.BLUR_AND_DEFAULT_STR:
//                return UserAggregatedSettings.VideoFilterMode.BLUR_AND_DEFAULT;
//            default:
//                return UserAggregatedSettings.VideoFilterMode.UNKNOWN;
//        }
//    }
//
//    @UserAggregatedSettings.CommonAreaPhoneSearchStatus
//    static int getCommonAreaPhoneSearchStatus(@NonNull String commonAreaPhoneSearchStatus) {
//        switch (commonAreaPhoneSearchStatus.toUpperCase()) {
//            case UserAggregatedSettings.CommonAreaPhoneSearchStatus.ENABLED_STR:
//                return UserAggregatedSettings.CommonAreaPhoneSearchStatus.ENABLED;
//            case UserAggregatedSettings.CommonAreaPhoneSearchStatus.DISABLED_STR:
//                return UserAggregatedSettings.CommonAreaPhoneSearchStatus.DISABLED;
//            default:
//                return UserAggregatedSettings.CommonAreaPhoneSearchStatus.UNKNOWN;
//        }
//    }
//
//    private static GiphyDefinition parseGiphy(JsonElement jsonElement) {
////        [{
////            "fieldValues": [
////            {
////                "fieldName": "title"
////            },
////            {
////                "fieldName": "image",
////                    "fieldValue": "https://media4.giphy.com/media/3oz8xJMndpmVonXBPW/giphy.gif"
////            },
////            {
////                "fieldName": "previewImage",
////                    "fieldValue": "https://media4.giphy.com/media/3oz8xJMndpmVonXBPW/giphy-tumblr.gif"
////            },
////            {
////                "fieldName": "width",
////                    "fieldValue": "480"
////            },
////            {
////                "fieldName": "height",
////                    "fieldValue": "270"
////            },
////            {
////                "fieldName": "previewImageWidth",
////                    "fieldValue": "480"
////            },
////            {
////                "fieldName": "previewImageHeight",
////                    "fieldValue": "270"
////            }
////            ],
////            "composeValue": "https://media4.giphy.com/media/3oz8xJMndpmVonXBPW/giphy.gif",
////                "composeType": "Image"
////        },...]
//
//        try {
//            JsonObject imageObject = jsonElement.getAsJsonObject();
//            if (imageObject == null) {
//                return null;
//            }
//
//            JsonElement fieldValuesElement = imageObject.get("fieldValues");
//            if (fieldValuesElement == null || !fieldValuesElement.isJsonArray()) {
//                return null;
//            }
//
//            GiphyDefinition giphyDefinition = new GiphyDefinition();
//            JsonArray fieldValues = fieldValuesElement.getAsJsonArray();
//
//            for (JsonElement fieldElement : fieldValues) {
//                JsonElement fieldNameElement = fieldElement.getAsJsonObject().get("fieldName");
//                if (fieldNameElement != null) {
//                    String fieldName = fieldNameElement.getAsString();
//                    if ("previewImage".equalsIgnoreCase(fieldName)) {
//                        giphyDefinition.previewUrl = parseImageStringFieldValue(fieldElement);
//                    } else if ("image".equalsIgnoreCase(fieldName)) {
//                        giphyDefinition.fullUrl = parseImageStringFieldValue(fieldElement);
//                    } else if ("width".equalsIgnoreCase(fieldName)) {
//                        giphyDefinition.fullWidth = parseImageIntFieldValue(fieldElement);
//                    } else if ("height".equalsIgnoreCase(fieldName)) {
//                        giphyDefinition.fullHeight = parseImageIntFieldValue(fieldElement);
//                    } else if ("previewImageWidth".equalsIgnoreCase(fieldName)) {
//                        giphyDefinition.previewWidth = parseImageIntFieldValue(fieldElement);
//                    } else if ("previewImageHeight".equalsIgnoreCase(fieldName)) {
//                        giphyDefinition.previewHeight = parseImageIntFieldValue(fieldElement);
//                    } else if ("title".equalsIgnoreCase(fieldName)) {
//                        giphyDefinition.title = parseImageStringFieldValue(fieldElement);
//                    }
//                }
//            }
//
//            return giphyDefinition;
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    private static int parseImageIntFieldValue(JsonElement fieldElement) {
//        return JsonUtils.parseInt(fieldElement, "fieldValue");
//    }
//
//    private static String parseImageStringFieldValue(JsonElement fieldElement) {
//        return JsonUtils.parseString(fieldElement, "fieldValue");
//    }
//
//    /**
//     * Parse a JsonObject containing Data Loss Protection specific properties, this message differs
//     * in a normal message in that we will not have an existing conversation in the database for
//     * this message, and we must persist in a different fashion than a normal message.
//     *
//     * @param sourceMessage - the recently fetched message that contains the original content that
//     * was DLP blocked
//     * @param messageObject - the JsonElement containing DLP message and generic stream metadata
//     * (e.g. reason string, policy URL)
//     * @param messagePropertyAttributeDao - the messagePropertyAttributeDao for the user
//     * @return - the message object if it was properly parse, null otherwise.
//     */
//    @Nullable
//    public static Message parseAndSaveOriginalDlpBlockedMessageAndMetadata(
//            @NonNull Message sourceMessage,
//            @NonNull JsonElement messageObject,
//            @NonNull MessagePropertyAttributeDao messagePropertyAttributeDao) {
//        if (messageObject.getAsJsonObject().has(StringConstants.PROPERTIES)) {
//            JsonElement propertiesElement =
//                    messageObject.getAsJsonObject().get(StringConstants.PROPERTIES);
//            if (propertiesElement.getAsJsonObject().has(StringConstants.GENERIC_STREAM_METADATA)) {
//                JsonElement genericStreamMetadataElement =
//                        propertiesElement
//                                .getAsJsonObject()
//                                .get(StringConstants.GENERIC_STREAM_METADATA);
//                messagePropertyAttributeDao.save(
//                                MessagePropertyAttribute.create(
//                                        sourceMessage.messageId,
//                                        sourceMessage.conversationId,
//                                        MessagePropertyType.GENERIC_STREAM_METADATA,
//                                        sourceMessage.from,
//                                        StringConstants.GENERIC_STREAM_METADATA,
//                                        genericStreamMetadataElement.getAsString()));
//            }
//
//            if (propertiesElement.getAsJsonObject().has(StringConstants.SUBJECT)) {
//                JsonElement oldBlockedMessageSubject =
//                        propertiesElement
//                                .getAsJsonObject()
//                                .get(StringConstants.SUBJECT);
//                sourceMessage.subject = oldBlockedMessageSubject.getAsString();
//            }
//        }
//
//        if (messageObject.getAsJsonObject().has(StringConstants.CONTENT)) {
//            JsonElement oldBlockedMessageContent =
//                    messageObject.getAsJsonObject().get(StringConstants.CONTENT);
//            if (!StringUtils.isEmpty(sourceMessage.subject)) {
//                String subjectHtml = StringUtilities.wrapAsHtml(sourceMessage.subject, "h4");
//                sourceMessage.content = StringUtilities.wrapAsHtml(subjectHtml + oldBlockedMessageContent.getAsString());
//            } else {
//                sourceMessage.content = oldBlockedMessageContent.getAsString();
//            }
//            messagePropertyAttributeDao.save(
//                            MessagePropertyAttribute.create(
//                                    sourceMessage.messageId,
//                                    sourceMessage.conversationId,
//                                    MessagePropertyType.ORIGINAL_BLOCKED_MESSAGE_PROPERTY,
//                                    sourceMessage.from,
//                                    StringConstants.ORIGINAL_DLP_BLOCKED_MESSAGE_CONTENT,
//                                    sourceMessage.content));
//
//        }
//
//        return sourceMessage;
//    }
//
//    public static String parseSyncState(@NonNull String url) {
//        final String syncState = "syncState=";
//        try {
//            java.net.URL syncStateURL = new URL(url);
//            String query = syncStateURL.getQuery();
//            String[] pairs = query.split("&");
//            for (String pair : pairs) {
//                if (pair.contains(syncState)) {
//                    return pair.substring(syncState.length());
//                }
//            }
//        } catch (MalformedURLException ex) {
//            // Log the exception
//        }
//
//        return null;
//    }
}
