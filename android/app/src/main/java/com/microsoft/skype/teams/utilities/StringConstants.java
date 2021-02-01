/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.utilities;

/**
 * String Constants
 */
public final class StringConstants {
    public static final String CHARSET_UTF_8 = "UTF-8";
    public static final String VERSION = "version";
    public static final String CONVERSATION_ID = "conversationId";
    public static final String CONVERSATION_ID_NON_CAMEL = "conversationid";
    public static final String MESSAGE_ID = "messageId";
    public static final String CONVERSATIONS = "conversations";
    public static final String PROPERTIES = "properties";
    public static final String CONTENT = "content";
    public static final String SUBJECT = "subject";
    public static final String STAR = "star";
    public static final String NUMBER_OF_ACCOUNTS = "numberOfAccounts";
    public static final String NUMBER_OF_CONSUMER_ACCOUNTS = "numberOfConsumerAccounts";
    public static final String CONTACT_GROUP_ID = "contactGroupId";
    public static final String REGISTRATION_ID = "registrationid";

    // for urgent message acknowledge sign in message property, emotions section
    public static final String ACKS = "acks";
    public static final String STARRED = "starred";
    public static final String EMOTIONS = "emotions";
    public static final String ESCALATIONS = "escalations";
    public static final String COMPOSE_TIME = "composetime";
    public static final String ORIGINAL_ARRIVAL_TIME = "originalarrivaltime";
    public static final String THREAD_PROPERTIES = "threadProperties";
    public static final String MEMBER_PROPERTIES = "memberProperties";
    public static final String RELATIONSHIP_STATE = "relationshipState";
    public static final String METADATA = "_metadata";
    public static final String RELATED_MESSAGES_KEY = ";messageid=";
    public static final String MRI = "mri";
    public static final String KEY = "key";
    public static final String USER_TYPE_GUEST = "guest";
    public static final String USER_TYPE_GROUP_CHAT = "group_chat";
    public static final String FORWARD_SLASH = "/";
    // sms delivery reports related json keys and values
    public static final String SMS_DELIVERY_REPORTS = "smsDeliveryReports";
    public static final String SMS_DELIVERY_REPORT_STATUS_KEY = "key";
    public static final String SMS_DELIVERY_REPORT_SUCCESS = "sent";
    public static final String SMS_DELIVERY_REPORT_FAILED = "failed";
    public static final String SMS_DELIVERY_REPORT_USERS_KEY = "users";
    public static final String SMS_DELIVERY_REPORT_ERROR_CODE_KEY = "errorCode";
    public static final String SMS_DELIVERY_REPORT_ERROR_DETAILS_KEY = "value";

    // used to check if a chat is a 1 on 1 chat
    public static final String UNIQUE_GLOBAL_SPACES = "unq.gbl.spaces";
    // used to check if a chat is a legacy sfb inter-op chat.
    public static final String LEGACY_INTER_OP_UNIQUE_GLOBAL_SPACES = "sfbio.unq.gbl.spaces";

    // Presence related
    public static final String REFRESH_PRESENCE_KEY = "Refresh_Presence_Key";
    public static final String LONGPOLL_IM_KEY = "LongPoll_IM_Key";

    //BI Telemetry
    public static final String NOTIFICATION_LAUNCHTYPE_KEY = "Notification_Launch_Key";

    //Chat Source
    public static final String CHAT_MODULE_SOURCE = "chat_module";
    public static final String CHAT_NOTIFICATION_MODULE_SOURCE = "notification_module";
    public static final String CHAT_PROFILE_CARD_MODULE_SOURCE = "profile_card_module";
    public static final String CHAT_CALL_MODULE_SOURCE = "call_module";
    public static final String CHAT_CALL_HISTORY_MODULE_SOURCE = "call_history_module";
    public static final String CHAT_DEEPLINK_SOURCE = "deeplink";

    public static final String ALERTS_MODULE_SOURCE = "alerts_module";

    public static final String PARAM_SOURCE = "source";

    //Suggested contact chat Source
    public static final String SUGGESTED_CONTACT_CHAT_SOURCE = "suggested_contact_chat";

    // Teams & Channels Source
    public static final String TEAMS_AND_CHANNELS_MODULE_SOURCE = "teams_and_channels_module";

    //OEM blocking detection show banner
    public static final String SHOW_BACKGROUND_RESTRICTED_BANNER = "Show_Background_Restricted_Banner";
    public static final String SHOW_IGNORING_BATTERY_OPT_BANNER = "Show_Ignoring_Battery_Opt_Banner";

    //Auth Error Code
    public static final String TEAM_DISABLED_FOR_TENANT = "TeamsDisabledForTenantForbidden";
    public static final String USER_LICENSE_NOT_PRESENT = "UserLicenseNotPresentForbidden";
    public static final String ADMIN_LICENSE_NOT_PRESENT = "AdminUserLicenseNotPresentForbidden";
    public static final String ADMIN_TEAMS_DISABLED_FOR_TENANT = "AdminTeamsDisabledForTenantForbidden";
    public static final String USER_LICENSE_NOT_PRESENT_TRIAL_ELIGIBLE = "UserLicenseNotPresentTrialEligible";
    public static final String GUEST_USER_NOT_REDEEMED = "GuestUserNotRedeemed";
    public static final String FORBIDDEN = "Forbidden";

    // Email properties
    public static final String EMAIL_PROPS_SITE_URL = "siteUrl";
    public static final String EMAIL_PROPS_SERVER_RELATIVE_URL = "serverRelativeUrl";
    public static final String EMAIL_PROPS_DOWNLOAD_URL = "emailDownloadUrl";
    public static final String EMAIL_PROPS_FILE_NAME = "fileName";
    public static final String EMAIL_PROPS_FILE_URL = "fileUrl";
    public static final String EMAIL_PROPS_FROM_NAME = "name";
    public static final String EMAIL_PROPS_FROM_SMTP = "smtp";
    public static final String EMAIL_PROPS_TO = "to";
    public static final String EMAIL_PROPS_CC = "cc";
    public static final String EMAIL_PROPS_IS_TRUNCATED = "isTruncated";

    // File Properties
    public static final String FILE_PROPS_AUTHORIZED_DOWNLOAD_URL = "authorizedDownloadUrl";
    public static final String FILE_PROPS_ITEM_ID = "itemid";
    public static final String FILE_PROPS_FILE_INFO = "fileInfo";
    public static final String FILE_PROPS_SITE_URL = "siteUrl";
    public static final String FILE_PROPS_FILE_PREVIEW = "filePreview";
    public static final String FILE_PROPS_CHICLET_BREADCRUMBS = "chicletBreadcrumbs";
    public static final String FILE_PROPS_SOURCE_TEAM_NAME = "sourceTeamName";
    public static final String FILE_PROPS_SOURCE_CHANNEL_NAME = "sourceChannelName";
    public static final String FILE_PROPS_PREVIEW_URL = "previewUrl";
    public static final String FILE_PROPS_PREVIEW_WIDTH = "previewWidth";
    public static final String FILE_PROPS_PREVIEW_HEIGHT = "previewHeight";
    public static final String FILE_PROPS_FILE_TYPE = "fileType";
    public static final String FILE_PROPS_FILE_URL = "fileUrl";
    public static final String FILE_PROPS_FILE_NAME = "fileName";
    public static final String FILE_PROPS_TAB_ITEM = "tab";
    public static final String FILE_PROPS_PROVIDERDATA = "providerData";
    public static final String FILE_PROPS_HOSTVIEWURL = "hostViewUrl";
    public static final String FILE_PROPS_SERVER_RELATIVE_URL = "serverRelativeUrl";
    public static final String FILE_PROPS_SHARE_URL = "shareUrl";
    public static final String FILE_PROPS_DOWNLOAD_URL = "downloadUrl";
    public static final String FILE_PROPS_OBJECT_ID = "objectId";
    public static final String FILE_UPLOAD_PROPS_IS_UPLOAD_ERROR = "isUploadError";
    public static final String FILE_UPLOAD_PROPS_PROGRESS_COMPLETE = "progressComplete";
    public static final String FILE_UPLOAD_PROPS_REQUEST_ID = "requestId";
    public static final String FILE_LINK_SHARING_PROPS_IS_CHICLET_READY = "linkSharingIsChicletReady";
    public static final String FILE_PROPS_ASYNC_ATTACHMENT_TYPE = "asyncAttachmentType";
    public static final String FILE_PROPS_VROOM_ITEM_ID = "VROOMItemId";
    public static final String FILE_INFO_PROPS_VROOM_ITEM_ID = "itemId";
    public static final String FILE_CHICLET_STATE = "fileChicletState";
    public static final String STATE = "state";

    //Url preview properties
    public static final String URL_PREVIEW_PROPS_PREVIEW = "preview";
    public static final String URL_PREVIEW_PROPS_PREVIEW_ENABLED = "previewenabled";
    public static final String URL_PREVIEW_PROPS_URL = "url";
    public static final String URL_PREVIEW_PROPS_PREVIEW_URL = "previewurl";
    public static final String URL_PREVIEW_PROPS_PREVIEW_META = "previewmeta";
    public static final String URL_PREVIEW_PROPS_PREVIEW_WIDTH = "width";
    public static final String URL_PREVIEW_PROPS_PREVIEW_HEIGHT = "height";
    public static final String URL_PREVIEW_PROPS_TITLE = "title";
    public static final String URL_PREVIEW_PROPS_DESCRIPTION = "description";
    public static final String URL_PREVIEW_PROPS_IS_ATP_SAFE_URL_PREVIEW = "isAtpSafeUrl";

    // adaptive string constants
    public static final String ADAPTIVE_INPUT_TEXT = "Input.Text";
    public static final String ADAPTIVE_DATE_INPUT = "Input.Date";
    public static final String ADAPTIVE_TIME_INPUT = "Input.Time";
    public static final String ADAPTIVE_INPUT_CHOICE_SET = "Input.ChoiceSet";
    public static final String ADAPTIVE_MULTI_CHOICE_COMPACT = "compact";
    public static final String ADAPTIVE_REQUIRED_INPUT_PREFIX = "isRequired";

    // user constants
    public static final String USER_OBJECT_ID = "objectId";
    public static final String DL_RESOLVE_NESTED_GROUPS = "resolveNestedGroups";
    public static final String ADUSER_OBJECT_TYPE = "ADUser";
    public static final String GROUP_OBJECT_TYPE = "Group";

    // buddy group constants
    public static final String BUDDY_GROUP_ID = "buddyGroupId";
    public static final String TFL_CONTACTS = "TflContacts";
    public static final String TFL_SUGGESTED = "TflSuggested";
    public static final String TFL_BLOCKED = "TflBlocked";
    public static final String TFL_FAVORITES = "TflFavorites";
    public static final String SFB_FAVORITES = "SfbFavorites";
    public static final String TAGGED = "Tagged";
    public static final String BUDDY_LIST_FAVORITES = "Favorites";
    public static final String OTHER_CONTACTS = "OtherContacts";

    // o365 string constants
    public static final String O365_TEXT_INPUT = "textinput";
    public static final String O365_DATE_INPUT = "dateinput";
    public static final String O365_MULTI_CHOICE_INPUT = "multichoiceinput";

    //Inter-op properties
    public static final String INTER_OP_TYPE = "interopType";

    // User CoExistence mode
    public static final String TEAMS_ONLY = "TeamsOnly";
    public static final String ISLANDS = "Islands";
    public static final String SFB_ONLY = "SfBOnly";
    public static final String SFB_WITH_TEAMS_COLLAB = "SfBWithTeamsCollab";
    public static final String SFB_WITH_TEAMS_COLLAB_AND_MEETINGS = "SfBWithTeamsCollabAndMeetings";

    //For legacy federation chat link
    public static final String NOT_APPLICABLE = "N/A";

    //Meeting properties
    public static final String MEETING_PROPS_MEETING_JSON = "meetingJson";

    public static final String MESSAGE_PROP_SKIP_FAN_OUT_TO_BOTS = "SkipFanOutToBots";

    // scenario context property name
    public static final String MESSAGE_PROP_SCENARIO_CONTEXT = "Scenario_Context";

    // DLP Properties
    public static final String ORIGINAL_DLP_BLOCKED_MESSAGE_CONTENT = "OriginalDlpBlockedMessageContent";
    public static final String GENERIC_STREAM_METADATA = "genericStreamMetadata";

    //CRUD ErrorCodes
    public static final String LAST_ADMIN_IN_TEAM = "OperationDeniedAsMemberIsLastAdminInTeam";
    public static final String LAST_ADMIN_IN_CHANNEL = "OperationDeniedAsMemberIsLastAdminInChannel";
    public static final String LAST_ADMIN_IN_SHARED_CHANNEL = "\"Action cannot be performed as it would leave the channel ownerless.\"";
    public static final String LAST_OWNER_OF_PRIVATE_CHANNEL = "RemoveFromTeamFailedAsUserIsLastOwnerOfPrivateChannel";
    public static final String USER_EXCEEDED_AAD_TEAM_CREATION_LIMIT = "AadGroupCreationLimitExceeded";

    public static final String GZIP_ACCEPT_ENCODING = "gzip, deflate";

    // Federated user search needs to match full email address Regex
    // chars of not @ or empty space to start with, followed by @, then with chars of not @ or empty space with .
    // e.g.    a@b.c is valid;   a@b.c.d is valid;    @@b.c is not valid;    @b.c is not valid
    public static final String EMAIL_REGEX = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";
    static final String SKYPEID_REGEX = "\\b8:(?!orgid:)(?!notifications)(?!teamsvisitor:)(live:)?(\\.cid\\.)?[a-zA-Z0-9][a-zA-Z0-9\\.,\\-_]{6,31}";
    // consumer group email identified by outlook service is in format  oid:c8c90000-6f3c-8644-0000-000000000000@84df9e7f-e9f6-40af-b435-aaaaaaaaaaaa
    public static final String GROUP_EMAIL_REGEX = "[A-Za-z0-9-:]+@[A-Za-z0-9-]+[A-Za-z0-9-]";

    // ARMY CONSTANTS
    public static final String OFFICE_CONFIG_VALUE = "config16";
    public static final String SERVICES_FOR_CONFIG = "TeamsMTEndpoint,TeamsECSEndpoint,TeamsAriaCollector,TeamsOneDSCollector";

    // Translation Properties
    public static final String CHAT_ID = "chatId";
    public static final String LANGUAGE_ID = "languageId";
    public static final String TEAM_ID = "teamId";
    public static final String CHANNEL_ID = "channelId";
    public static final String REPLYCHAIN_ID = "replyChainId";
    public static final String LOCALE = "locale";
    public static final String TRANSLATION_PROPERTY = "TranslationProperty";
    public static final String TRANSLATEDCONTENT_TRANSLATION_PROPERTY_ATTRIBUTE = "TRANSLATEDCONTENT_TRANSLATION_PROPERTY_ATTRIBUTE";
    public static final String TRANSLATEDCONTENT_LANGUAGEID_TRANSLATION_PROPERTY_ATTRIBUTE = "TRANSLATEDCONTENT_LANGUAGEID_TRANSLATION_PROPERTY_ATTRIBUTE";
    public static final String RESULTCODE_TRANSLATION_PROPERTY_ATTRIBUTE = "RESULTCODE_TRANSLATION_PROPERTY_ATTRIBUTE";
    public static final String CONTENT_LANGUAGEID_TRANSLATION_PROPERTY_ATTRIBUTE = "CONTENT_LANGUAGEID_TRANSLATION_PROPERTY_ATTRIBUTE";
    public static final String TRANSLATEDSUBJECT_TRANSLATION_PROPERTY_ATTRIBUTE = "TRANSLATEDSUBJECT_TRANSLATION_PROPERTY_ATTRIBUTE";
    public static final String TRANSLATEDTITLE_TRANSLATION_PROPERTY_ATTRIBUTE = "TRANSLATEDTITLE_TRANSLATION_PROPERTY_ATTRIBUTE";

    // Badge count Properties
    public static final String ENDPOINT_ID = "endpointId";
    public static final String UNREAD_BADGE_COUNT_DISPLAY_STRING_FOR_MORE_THAN_MAX = "99+";

    // AppCenter Properties
    public static final String API_VERSION = "Api-Version";

    // Navigation and Layout constants
    public static final String OPEN_NEW_ACTIVITY_INSTANCE = "Open new activity instance";
    public static final String RETAIN_ACTIVITY_INSTANCE = "Retain activity instance";
    public static final String UPDATE_MASTER_LAYOUT = "Update Master Layout";
    public static final String UPDATE_DETAIL_LAYOUT = "Update Detail Layout";
    public static final String NAVIGATION_SET = "NavigationSet";

    // Whiteboard Properties
    public static final String WHITEBOARD_TYPE = "type";
    public static final String WHITEBOARD_CONVERSATIONID = "conversationId";
    public static final String WHITEBOARD_MESSAGEID = "messageId";
    public static final String WHITEBOARD_URL = "url";
    public static final String WHITEBOARD_SHAREURL = "shareUrl";
    public static final String WHITEBOARD_ICON = "icon";
    public static final String WHITEBOARD_PRESENTER = "presenter";

    //Auth properties
    public static final String BROKERTYPE_STRING = "brokerType";
    public static final String TENANT_ID_STRING = "tenantId";
    public static final String USEROBJECT_ID_STRING = "userObjectId";
    public static final String SANITIZED_RESOURCE_STRING = "sanitizedResource";

    //People picker properties
    public static final String PEOPLE_PICKER_GROUP_HEADER_ITEM = "peoplePickerGroupHeaderItem";

    private StringConstants() {
    }
}
