/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.services.configuration;

import com.microsoft.teams.core.models.UserPreferences;

/**
 * Defines ecs constants here.
 */
public final class ExperimentationConstants {
    // CORE FEATURES
    public static final String ENABLE_READ_RECEIPTS = "enableReadReceipts";
    public static final String SHOULD_OVERRIDE_USER_SETTINGS_NAMESPACE = "user/overrideUserSettingsNamespace";
    public static final String CHAT_READ_RECEIPT_MAX_PARTICIPANTS = "chatReadReceiptsMaxParticipants";
    public static final String MEETING_READ_RECEIPT_MAX_PARTICIPANTS = "meetingReadReceiptsMaxParticipants";
    public static final String BURN_NOTIFICATION = "burnNotification";
    public static final String ENABLE_NOTIFY_ALWAYS_BY_DEFAULT = "enableNotifyAlwaysByDefault";
    public static final String ENABLE_ACCOUNT_RESOLUTION = "enableAccountResolution";
    public static final String ENABLE_FEDERATED_CHAT = "enableFederatedChat";
    public static final String ENABLE_NATIVE_FEDERATED_CHAT = "enableNativeFederatedChat";
    public static final String ENABLE_NATIVE_FEDERATED_GROUP_CHAT = "nativeFedGroupChatEnabled";
    public static final String ENABLE_SFB_CHAT_INTEROP = "enableSFBChatInterOp";
    public static final String ENABLE_SMS_CHAT = "enableSMSChat";
    public static final String ENABLE_SMS_ENTITLEMENT_CHECK = "smsEnablementCheckEnabled";
    public static final String HIDE_SFB_CHAT_INTEROP_PRESENCE = "hideInterOpChatPresence";
    public static final String SUPPORT_COEXISTENCE_MODES = "supportCoExistenceModes";
    public static final String MUTE_CHAT_CONVERSATION = "muteChatConversation";
    public static final String HIDE_CHAT_CONVERSATION = "hideChatConversation";
    public static final String TOPIC_NAME_IN_NEW_CHAT_ENABLED = "topicNameInNewChatEnabled";
    public static final String CALL_QUEUE_SETTINGS_ENABLED = "showCallQueuesSettings";
    public static final String CALL_QUEUE_SETTINGS_RESOURCE_STRING = "callQueueResourceString";
    public static final String SEARCH_FROM_MULTIPLE_PARTICIPANTS = "search/chatsFromMultipleParticipants";
    public static final String ENABLE_GUEST_SWITCH = "enableGuestSwitch";
    public static final String ENABLE_ADAPTIVE_CARDS = "enableAdaptiveCards";
    public static final String ENABLE_O365_CARDS_CONVERSION = "enableO365CardsConversion";
    public static final String ENABLE_CREATE_EDIT_TEAM = "enableCreateEditTeam";
    public static final String CHAT_TIMESTAMP_SESSION_MINUTES = "chatTimeStampSessionMinutes";
    public static final String IS_CHAT_SWITCH_OPTIMIZATION_ENABLED = "isChatSwitchOptimizationEnabled";
    public static final String ENABLE_FEDERATED_GROUP_CHAT_CONSUMPTION = "enableFederatedGroupChatConsumption";
    public static final String POLL_UNIFIED_PRESENCE = "pollUnifiedPresence";
    public static final String SHOULD_HONOR_REGION_SETTINGS_FOR_UPS = "shouldHonorRegionSettingsForUps";
    public static final String SHOW_CONTACTS_GROUP = "showContactsGroup";
    public static final String SHOW_CONTACTS_GROUP_V2 = "showContactsGroupV2";
    public static final String LIKES_IN_CHAT = "likesInChat";
    public static final String MENTIONS_IN_CHAT = "mentionsInChat";
    public static final String RENEW_TEAM = "renewTeam";
    public static final String SHARE_GROUP_CHAT_HISTORY = "shareGroupChatHistory";
    public static final String REMOVE_USER_FROM_GROUP_CHAT = "RemoveUserFromGroupChat";
    public static final String ENABLE_GUEST_MSA = "enableGuestMSA";
    public static final String ENABLE_FREEMIUM_SIGNUP = "enableFreemiumSignUp";
    public static final String NOPAFORMSAL = "NOPAFORMSAL";
    public static final String ENABLE_MSAL_OPTIMIZATION = "enableMSALOptimization";
    public static final String SHOULD_ADD_CONSUMER_TENANT = "shouldAddConsumerTenant";
    public static final String FORCE_SYNC_IF_NOTIFICATION_TRUNCATED = "forceSyncIfNotificationTruncated";
    public static final String DISABLE_FREEMIUM_O365_GUEST_INVITIATON = "disableFreemiumO365GuestInvitiaton";
    public static final String ENABLE_GUEST_NONLICENSE = "enableGuestNonLicense";
    public static final String CHAT_TABS_TAB = "chatTabsTab"; // ECS to control more tabs in meetings chat
    public static final String CLOSED_CAPTIONS_BOT_MRI = "closedCaptionsBotMri";
    public static final String ENABLE_CREATE_EVENT = "enableCreateEvent";
    public static final String ENABLE_MEETING_CALL_ME = "meetingCallMeEnabled";
    public static final String ENABLE_ADD_TO_CALENDAR = "addToCalendarEnabled";
    public static final String ENABLE_BROADCAST_MEETING = "broadcastMeetingEnabled";
    public static final String NEW_MEETING_DETAILS_DESIGN = "enableNewMeetingDetailsDesign";
    public static final String ENABLE_MEETING_DETAILS_TAB_EXPERIENCE = "enableMeetingDetailsTabExperience";
    public static final String ENABLE_CALENDAR_EVENT_EXPORT = "calendarEventExportEnabled";
    public static final String ENABLE_CALENDAR_INCREMENTAL_API_EXPERIENCE = "calendarIncrementalAPIEnabled";
    public static final String ENABLE_CALENDAR_RESET_OPTION = "isClearCalendarEnabled";
    public static final String CALENDAR_EVENT_SYNC_NUM_OF_DAYS = "calendarEventSyncNumOfDays";
    public static final String CALENDAR_INCREMENTAL_API_MIN_BACK_OFFSET_IN_DAYS = "incrementalAPIMinBackOffsetInDays";
    public static final String CALENDAR_INCREMENTAL_API_EVENT_FETCH_COUNT = "eventFetchCount";
    public static final String CALENDAR_INCREMENTAL_API_MIN_EVENT_FETCH_COUNT = "minEventFetchCount";
    public static final String CALENDAR_DATE_PICKER_DURATION_IN_MONTHS = "dpDurationInMonths"; // calendar date picker window range
    public static final String CALENDAR_DATE_PICKER_SERIES_EXPANSION_DELTA_IN_MONTHS = "dpSeriesExpansionDeltaInMonths"; // months to expand and store events
    public static final String CALENDAR_SYNC_YIELD_ENABLE = "enableCalendarSyncYield";
    // Calendar Date Picker(new approach) [[
    public static final String CALENDAR_PAGINATED_DATE_PICKER_FEATURE_ENABLED = "calendarDpPg/isFeatureEnabled";
    public static final String CALENDAR_PAGINATED_DATE_PICKER_FOCUS_DAYS_BEFORE = "calendarDpPg/focusDaysBefore";
    public static final String CALENDAR_PAGINATED_DATE_PICKER_FOCUS_DAYS_AFTER = "calendarDpPg/focusDaysAfter";
    public static final String CALENDAR_PAGINATED_DATE_PICKER_FOCUS_WEEK_DAYS_BEFORE = "calendarDpPg/focusWeekDaysBefore";
    public static final String CALENDAR_PAGINATED_DATE_PICKER_FOCUS_WEEK_DAYS_AFTER = "calendarDpPg/focusWeekDaysAfter";
    public static final String CALENDAR_PAGINATED_DATE_PICKER_DELTA_LOAD_DAYS = "calendarDpPg/deltaLoadDays";
    public static final String CALENDAR_PAGINATED_DATE_PICKER_BOUNDARY_DAYS_THRESHOLD = "calendarDpPg/boundaryDaysThreshold";
    public static final String CALENDAR_PAGINATED_DATE_PICKER_MONTHS_BEFORE = "calendarDpPg/monthsBefore";
    public static final String CALENDAR_PAGINATED_DATE_PICKER_MONTHS_AFTER = "calendarDpPg/monthsAfter";
    // Calendar Date Picker(new approach) ]]
    public static final String CALENDAR_INCREMENTAL_API_RESETTABLE_ERROR_CONFIG = "resettableErrors";
    public static final String CALENDAR_MEETING_REMINDER_NOTIFICATIONS_ENABLED = "eventReminderNotifications/enabled";
    public static final String CALENDAR_MEETING_REMINDER_NOTIFICATIONS_DEFAULT_TYPE = "eventReminderNotifications/defaultType";
    public static final String CALENDAR_MEETING_REMINDER_NOTIFICATIONS_DEFAULT_TIME = "eventReminderNotifications/defaultTime";
    public static final String ENABLE_SHARE_MEETING = "isShareMeetingEnabled";
    public static final String ENABLE_MEETING_OPTIONS = "isMeetingOptionsEnabled";
    public static final String ENABLE_FORWARD_MEETING = "isForwardMeetingEnabled";
    public static final String ENABLE_CHANNEL_MEETING_TABS = "enableChannelMeetingsTabs";
    public static final String DISABLE_REFRESH_ON_CREATE_EDIT_EVENT = "disableRefreshOnCreateEditMeeting";
    public static final String ALLOW_ANONYMOUS_USER_MEETING = "allowAnonymousUserInMeetings";
    public static final String ALLOW_ANONYMOUS_USER_SHARE_LOCATION = "allowAnonymousUserShareLocation";
    public static final String ALLOW_ANONYMOUS_USER_SHARE_IMAGES = "allowAnonymousUserShareImages";
    public static final String ENABLE_CHICLET_BREAK_CHANNEL_RENAME = "files/enableChicletBreakingChannelRename";
    public static final String ENABLE_MUTING_UNMUTING_DELAY = "enableMutingUnmutingDelay";
    public static final String ENABLE_VIDEO_ON_OFF_DELAY = "enableVideoOnOffDelay";
    public static final String MUTE_ACTION_DELAY_TIMEOUT_LIMIT = "muteActionDelayTimeoutLimit";
    public static final String VIDEO_ACTION_DELAY_TIMEOUT_LIMIT = "videoActionDelayTimeoutLimit";
    public static final String ENABLE_ANONYMOUS_JOIN = "enableAnonymousJoin";
    public static final String ANONYMOUS_JOIN_HELP_URI = "anonymousJoinHelpUri";
    public static final String AUTO_IDLE = "autoIdle";
    public static final String DISCOVER_PRIVATE_TEAMS = "discoverPrivateTeams";
    public static final String TEAM_SENSITIVITY_LABELS_ENABLED = "teamSensitivityLabelsEnabled";
    public static final String JOIN_CHANNEL_THROTTLING_LIMIT_IN_SECONDS = "joinChannelTimeLimit";
    public static final String PINNED_CHANNELS = "pinnedChannels";
    public static final String PINNED_CHANNELS_MAX_LIMIT = "pinChannelMaxLimit";
    public static final String PINNED_CHANNELS_IN_CHATS = "pinChannelInChatListEnabled";
    public static final String PINNED_CHATS_ENABLED = "pinChatsEnabled";
    public static final String PINNED_CHATS_MAX_LIMIT = "pinChatMaxLimit";
    public static final String ORG_WIDE_TEAMS = "enableTenantWideTeams";
    public static final String TEAM_DISCOVERY_SETTING_ENABLED = "teamDiscoverySettingEnabled";
    public static final String PERSIST_SELECTED_TAB_ENABLED = "persistSelectedTabEnabled";
    public static final String SUPPORT_LARGE_TEAMS = "supportLargeTeams";
    public static final String TEAM_SIZE_TO_DISABLE_TEAM_CHANNEL_MENTION = "teamSizeToDisableTeamChannelMention";
    public static final String DOUBLE_TAP_TO_LIKE_ENABLED = "doubleTapToLikeEnabled";
    public static final String QUOTED_CHAT_REPLIES_ENABLED = "quotedChatRepliesEnabled";
    public static final String QUOTED_CHAT_REPLIES_ALTERNATE_NAME_ENABLED = "quotedChatRepliesAlternateNameEnabled";
    public static final String MESSAGE_FORWARDING_ENABLED = "messageForwardingEnabled";
    public static final String SHOULD_USER_SEE_READ_RECEIPTS_PRIVACY_NOTICE = "should_user_see_read_receipts_privacy_notice";
    public static final String VOICE_MESSAGE_SENDING_ENABLED = "voiceMessageSendingEnabled";
    public static final String VIDEO_MESSAGE_SENDING_ENABLED = "videoMessageSendingEnabled";
    public static final String VIDEO_MESSAGE_COMPRESSION_SETTINGS_ENABLED = "videoMessageCompressionSettingsEnabled";
    public static final String PRE_JOIN_ENABLED = "preJoinMeeting";
    public static final String ENABLE_CONTINUE_CALL_ON_PRE_JOIN_NAVIGATED_AWAY = "enableContinueCallOnPreJoinNavigatedAway";
    public static final String ENABLE_CONTINUE_CALL_ON_PRE_CALL_NAVIGATED_AWAY = "enableContinueCallOnPreCallNavigatedAway";
    public static final String ENABLE_OID_GUID_CHECK_IN_PRE_CALL = "oidGuidCheckInPreCallEnabled";
    public static final String PRE_JOIN_COACHMARK_THRESHOLD = "prejoinCoachmarkThreshold";
    public static final String PRE_JOIN_COACHMARK_ENABLED = "prejoinCoachmarkEnabled";
    public static final String SCREEN_SHARE_ENABLED = "screenSharingEnabled";
    public static final String ENABLE_SCREEN_SHARE_IMAGE_OPTIMIZATION = "enableScreenShareImageOptimization";
    public static final String MESSAGING_EXTENSIONS = "messagingExtensions";
    public static final String MEETING_EXTENSIBILITY_ENABLED = "MeetingExtensibilityEnabled";
    public static final String MEETING_EXTENSIBILITY_APP_CONTROL_BAR_ENTRY_ENABLED = "MeetingExtensibilityAppControlBarEntryEnabled";
    public static final String TRIGGER_DB_FULL_MIGRATION_ON_NO_COLUMN_FOUND_ERROR = "triggerFullMigrationOnNoColumnFoundError";
    public static final String DO_RESET_INSTEAD_OF_MIGRATION = "doResetInsteadOfMigration"; // Should always be controlled with DB Version.
    public static final String SHOULD_CLEAR_MESSAGE_SYNC_STATE = "shouldClearMessageSyncState";
    public static final String RUN_CLEAR_SYNC_STATE_MIGRATION_FROM_VERSION = "fromVersionToClearSyncStateMigration";
    public static final String RUN_CLEAR_SYNC_STATE_MIGRATION_TO_VERSION = "toVersionToClearSyncStateMigration";
    public static final String PRE_JOIN_CALL_EVENTS_SUBSCRIBING_ENABLED = "preJoinCallEventsSubscribing";
    public static final String CARD_TASK_MODULE_ENABLED = "cardTaskModuleEnabled";
    public static final String WEB_TASK_MODULE_ENABLED = "webTaskModuleEnabled";
    public static final String CONSUME_PINNING_INFO_FROM_APP_POLICY = "consumePinningInfoFromAppPolicy";
    public static final String CAN_SHOW_APP_POLICY_PINNING_INFO_CHANGED_POPUP = "canShowAppPolicyPinningInfoChangedPopUp";
    public static final String EXTENSIBILITY_AUTH_ENABLED = "extensibilityAuthEnabled";
    public static final String QUERY_MESSAGING_EXTENSIONS_ENABLED = "queryMessagingExtensionsEnabled";
    public static final String PRAISE_ONLY_FLYOUT_ENABLED = "praiseOnlyFlyoutEnabled";
    public static final String USER_ENTITLEMENT_POLL_DURATION = "userEntitlementsPollDuration";
    public static final String CHAT_ENTITLEMENT_POLL_DURATION = "chatEntitlementsPollDuration";
    public static final String CHAT_NEW_MESSAGE_SLIDE_IN_ANIMATION_ENABLED = "chatSlideinAnimationEnabled";
    public static final String RUN_NOTIFICATION_ASYNC = "runNotificationAsync";
    public static final String ANNOUNCEMENT_ILLUSTRATION_MESSAGE_ENABLED = "announcementIllustrationMessagesEnabled";
    public static final String PRUNE_PENDING_SYNC_ITEMS = "prunePendingSyncItems";
    public static final String ENABLE_FORCE_UPDATE_ENDPOINT = "enableForceUpdateEndpoint";
    public static final String CHAT_FIRST_EXPERIENCE = "enableChatFirstExperience";
    public static final String MSAL_ENABLED = "msalEnabled";
    public static final String FRE4V_ENABLED = "fre4vEnabled";
    public static final String LOAD_MORE_REPLY_CHAINS_PAGE_SIZE = "loadMoreReplyChainsPageSize";
    public static final String DISABLE_PROFILE_PICTURE_FADE_ANIMATION = "disableProfilePictureFadeAnimation";
    public static final String DISABLE_RECYCLER_ITEM_ANIMATION = "disableRecyclerViewItemAnimation";
    public static final String DEV_SETTINGS_ENABLED = "devSettingsEnabled";
    public static final String BOTTOM_BAR_FIVE_AND_MORE_ENABLED = "fiveAndMoreInBottomBar";
    public static final String BOTTOM_BAR_SIX_PLUS_LABELS_ENABLED = "bottomBarSixPlusLabelsEnabled";
    public static final String SINGLE_TENANT_NOTIFICATION_SYNC_TIME = "singleTenantNotificationSyncTime";
    public static final String MULTIPLE_TENANT_NOTIFICATION_SYNC_TIME = "multipleTenantNotificationSyncTime";
    public static final String TEXT_TO_EMOTICON_ENABLED = "chat/textToEmoticonEnabled";
    public static final String CHATS_SHOW_MORE_ENABLED = "messageEnableShowMoreChats";
    public static final String CHATS_LIST_RECENT_MAX_ITEMS = "maxItemsInRecentChatList";
    public static final String CHATS_LIST_RECENT_MAX_TIME_SECOND = "maxTimeSecondInRecentChatList";
    public static final String TIME_TO_REFRESH_USER_AGGREGATED_SETTINGS = "timeToRefreshUserAggregatedSettings";
    public static final String LOAD_MORE_SECTION_APPS_IN_MAIN_ACTIVITY = "loadMoreSectionAppsInMainActivity";
    public static final String ACTIVE_JSHOSTFRAGMENT_HANDLES_BACK_PRESSED = "activeJsHostFragmentHandlesBackPressed";
    public static final String MAX_GROUP_CHAT_PARTICIPANTS_FOR_GROUP_CALL = "maxGroupChatParticipantsForGroupCall";

    // VALUE-ADD FEATURES
    public static final String OFFICE_LENS_ENABLED = "officeLensEnabled";
    public static final String MEDIA_FROM_NATIVE_KEYBOARD_ENABLED = "mediaFromNativeKeyboardEnabled";
    public static final String CORTANA_ENABLED = "cortanaEnabled";
    public static final String CORTANA_ENABLED_FOR_EDU = "cortanaEnabledForEdu";
    public static final String CORTANA_KWS_ENABLED = "cortanaKWSEnabled";
    public static final String CORTANA_BACKGROUND_TOKEN_REFRESH_ENABLED = "cortanaBackgroundTokenRefreshEnabled";
    public static final String CORTANA_EARLY_ADOPTERS = "cortanaEarlyAdopters";
    public static final String ENABLE_FILE_PREVIEW = "enableFilePreview";
    public static final String ENABLE_SMART_COMPOSE = "enableSmartCompose";
    public static final String ENABLE_SMART_COMPOSE_IN_CHANNEL = "enableSmartComposeInChannel";
    public static final String DISABLE_SMART_COMPOSE_IN_POOR_NETWORK = "disableSmartComposeInPoorNetwork";
    public static final String SEND_TIME_TO_COMPOSE_LOG = "sendTimeToComposeLog";
    public static final String SHOW_SMART_COMPOSE = "showSmartCompose";
    public static final String SMART_COMPOSE_DEBOUNCE_INTERVAL = "smartComposeDebounceInterval";
    public static final String SMART_COMPOSE_HISTORY_MESSAGE_COUNT = "smartComposeHistoryMessageCount";
    public static final String SMART_COMPOSE_MODEL_VERSION = "smartComposeModelVersion";
    public static final String AUGLOOP_ENDPOINT = "augloopEndpoint";
    public static final String ENABLE_SMART_REPLY = "enableSmartReply";
    public static final String SMART_REPLY_ENABLED_LOCALE_LIST = "smartReplyEnabledLocaleList";
    public static final String ENABLE_SMART_REPLY_RENDER_ANIM = "enableSmartReplyRenderAnimation";
    public static final String ENABLE_SMART_REPLY_EMOJI = "enableSmartReplyEmoji";
    public static final String ENABLE_SMART_REPLY_OCV_FEEDBACK = "enableSmartReplyOcvFeedback";
    public static final String SMART_REPLY_LONG_PRESS_TO_SEND = "smartReplyLongPressToSend";
    public static final String ENABLE_SMART_REPLY_LONG_PRESS_TIP = "enableSmartReplyLongPressTip";
    public static final String ENABLE_SMART_REPLY_SUGGESTED_MEETING = "enableSmartReplySuggestedMeeting";
    public static final String SUGGESTED_MEETING_MESSAGE_CHECK_COUNT = "suggestedMeetingMessageCheckCount";
    public static final String SHOULD_READ_SMART_REPLY_TENANT_CONTROL = "shouldReadSmartReplyTenantControl";
    public static final String SHOULD_USE_SMART_REPLY_FILTER = "shouldUseSmartReplyFilter";
    public static final String SMART_REPLY_IN_GROUP_CHAT_STATUS = "smartReplyInGroupChatStatus";
    public static final String SMART_REPLY_IN_GROUP_CHAT_LAST_MESSAGE_COUNT = "smartReplyInGroupChatLastMessageCount";
    public static final String CORTANA_VOICE_FONT_ENABLED = "cortanaVoiceFontEnabled";
    public static final String CORTANA_BLUETOOTH_PROFILE_SWITCH_ENABLED = "cortanaBluetoothProfileSwitchEnabled";
    public static final String CORTANA_MEETING_HANDS_FREE_JOIN_ENABLED = "cortanaMeetingHandsFreeJoinEnabled";
    public static final String CORTANA_I18N_ENABLED = "cortanaI18NEnabled";
    public static final String CORTANA_HELP_CONTENT_PATH = "cortanaEducation/help";
    public static final String CORTANA_TIPS_PATH = "cortanaEducation/tips";
    public static final String CORTANA_FRE_BANNER_DISPLAY_TIME = "cortanaFreShow";
    public static final String CORTANA_OPT_IN_PROMOTION_COACH_MARK_DISPLAY_TIME = "cortanaOptInPromotionCoachMarkDisplay";
    public static final String CORTANA_WATCHDOG_POLICY = "cortanaWatchdogPolicy";
    public static final String CORTANA_IN_VIEW_KWS_CONSENT = "cortanaKWSConsent/inView";
    public static final String CORTANA_BEFORE_VIEW_KWS_CONSENT = "cortanaKWSConsent/beforeView";
    public static final String ONEPLAYER_MEETING_RECORDING_ENABLED = "onePlayerForMeetingRecordingEnabled";

    // CODE EXPERIMENTATION FLAGS (THESE CAN BE REMOVED ONCE CODE STABILIZES)
    public static final String IS_END_OF_MEETING_NOTIFICATION_ENABLED = "isEndOfMeetingNotificationEnabled";
    public static final String MEETING_PRESENTATION_TIMER_ENABLED = "isMeetingPresentationTimerEnabled";
    public static final String IS_SAFE_LINK_CHECK_ENABLED = "isSafeLinkCheckEnabled";
    public static final String ENABLE_ATP_VIA_MT = "enableATPViaMT";
    public static final String SHOULD_SAFE_LINK_VALIDATE_PREVIEW_URL = "shouldSafeLinkValidatePreviewUrl";
    public static final String SCHEDULER_ENABLED = "schedulerEnabled";
    public static final String CALL_HANDLER_ON_LOGIN_ENABLED = "getCallHandlerInstanceOnLogin";
    public static final String ENABLE_LIVE_STATE_LOGGING = "enableLiveStateLoging";
    public static final String ENABLE_DUMP_FILE_UPLOAD_FOR_ALL_CRASHES = "enableDumpFileUploadForAllCrashes";
    public static final String ENABLE_BREAKPAD = "enableBreakpad";
    public static final String ENABLE_EXTEND_BRB_REPORT = "enableExtendBrbReport";
    public static final String ENABLE_PRIORITY_MESSAGES = "enablePriorityMessages";
    public static final String ENABLE_PRIORITY_MESSAGES_OVERRIDE = "enablePriorityMessagesOverride";
    public static final String ENABLE_ESCALATION_UPDATE = "enableEscalationUpdate";
    public static final String ENABLE_ESCALATE_TO_NEW_PERSON = "enableEscalateToNewPerson";
    public static final String MESSAGE_DELIVERY_LATENCY_SAMPLING_PERCENTAGE = "messageDeliveryLatencySamplingPercentage";
    public static final String ENABLE_NOTIFICATION_ENCRYPTION = "enableNotificationEncryption";
    public static final String ENABLE_GLOBAL_ENDPOINT_ID = "enableGlobalEndpointId";
    public static final String ENABLE_GROUP_CALL_HIDING = "hideGroupCallInCallHistory";
    public static final String IS_BAIDU_NOTIFICATION_DEFAULT_PROVIDER = "isBaiduNotificationDefaultProvider";
    public static final String MAX_IDLE_CONNECTIONS = "maxIdleConnections";
    public static final String KEEP_ALIVE_DURATION = "keepAliveDuration";
    public static final String PREFERRED_CLIENT_PROMPT = "preferredClientPrompt";
    public static final String CROSS_TENANT_NOTIFICATION = "crossTenantNotification";
    public static final String MULTI_ACCOUNT_CALLING = "multiAccountCalling";
    public static final String ENABLE_PERSONAL_CONSUMER_ANONYMOUS_JOIN = "enablePersonalConsumerAnonymousJoin";
    public static final String MULTIPLE_NUMBER_CALLING = "multipleNumberCalling";
    public static final String AUDIENCE_GROUP = "AudienceGroup";
    public static final String VSTS_TOKEN = "vstsToken";
    public static final String ENABLE_PRESENCE_ETAG = "enablePresenceEtag";
    public static final String ENABLE_RESOLVE_HRD = "enableResolveHrd";
    public static final String ENABLE_LONGPOLL_V2 = "enableLongPollV2";
    public static final String IS_PRIVATE_CHANNEL_ENABLED = "isPrivateChannelEnabled";
    public static final String SHARED_CHANNELS_PROD_API_ENABLED = "sharedChannelsProdAPIEnabled";
    public static final String IS_CREATE_PRIVATE_CHANNEL_ENABLED = "isCreatePrivateChannelEnabled";
    public static final String DB_TRANSACTION_TRACE_ENABLED = "dbTransactionTraceEnabled";
    public static final String SKYPE_CHAT_TOKEN_REFRESH_SCHEDULER = "skypeChatTokenRefreshScheduler";
    public static final String PRESENCE_FETCH_INTERVAL = "presenceFetchInterval";
    public static final String ENABLE_TROUBLESHOOT_OPTIONS = "enableTroubleshootOptions";
    public static final String ENABLE_PRESENCE_DEBUGGING = "enablePresenceDebugging";
    public static final String HARDWARE_AUDIO_OFF = "hardwareAudioOff";
    public static final String PHONE_AUTHENTICATION = "phoneAuthentication";
    public static final String MESSAGE_STALE_THRESHOLD_IN_HOURS = "messageStaleThresholdInHours";
    public static final String READ_MIGRATED_TENANT_SETTINGS = "readMigratedTenantSettings";
    public static final String FETCH_TENANT_SPECIFIED_PRIVACY_URL = "fetchTenantSpecifiedPrivacyUrl";
    public static final String ENABLE_BRB = "enableBRB";
    public static final String ENABLE_CLIENT_FILE_LOGGING = "enableClientFileLogging";
    public static final String ENABLE_MANAGE_DELEGATES = "enableManageDelegates";
    public static final String ENABLE_PEOPLE_APP_V2 = "enablePeopleAppV2";
    public static final String REREGISTER_SLIMCORE_CONTEXTS_ON_SKYPE_TOKEN_REFRESH = "reregisterSlimcoreContextsOnSkypeTokenRefresh";
    public static final String ENABLE_BETTER_TOGETHER_SETTINGS = "enableBetterTogetherSettings";
    public static final String ENABLE_BT_ALSO_RING_DESKTOP_OPTION = "enableBTAlsoRingDesktopOption";
    public static final String ENABLE_PEOPLE_APP_V2_END_POINTS = "enablePeopleV2EndPoints";
    public static final String ENABLE_IPPHONE_HOME_SCREEN = "enableIpphoneHomeScreen";
    public static final String ENABLE_SCROLL_TO_UPCOMING_MEETING = "enableScrollToUpcomingMeeting";
    public static final String GET_UPCOMING_MEETING_SCROLLING_FREQUENCY = "GetUpcomingMeetingScrollingFrequency";
    public static final String REMOVE_PICTURE_QUALITY_OPTIMIZATION = "removePictureQualityOptimization";
    public static final String SHOULD_OVERRIDE_ENTER_BUTTON_FOR_PHYSICAL_KEYBOARD = "shouldOverrideEnterButtonForPhysicalKeyboard";
    public static final String THREAD_FIRST_PAGE_ROSTER_SIZE = "threadFirstPageRosterSize";
    public static final String SERVICE_STATE_MANAGER_ENABLED = "serviceStateManagerEnabled";
    public static final String ENABLE_MOCKING_NETWORK_DATA = "enableNetworkData";
    public static final String ENABLE_UNIFIED_CHATS_VIEW = "enableUnifiedChatsView";
    public static final String ENABLE_QUIET_HOURS_WRAP_UP_NOTIFICATION = "enableQuietHoursWrapUpNotification";
    public static final String CHANNELS_IN_CHAT_LIST_PILOT = "channelsInChatListPilot";
    public static final String PRIVATE_CHANNEL_FILES_SUPPORT = "privateChannelFilesSupport";
    public static final String LOG_CUSTOM_COLUMNS_ARIA_ENABLED = "logCustomColumnsInAria";
    public static final String IS_STATUS_OOF_MESSAGE_ENABLED = "isStatusOofMessageEnabled";
    public static final String IS_SET_STATUS_NOTE_ENABLED = "isSetStatusNoteEnabled";
    public static final String INCOMING_CALLS_OFF_ENABLED = "incomingCallsOffEnabled";
    public static final String WHATS_NEW_EXPERIENCE_ENABLED = "whatsNewExperienceEnabled";
    public static final String WHATS_NEW_UNREAD_DOT_ENABLED = "whatsNewUnreadDotEnabled";
    public static final String WHATS_NEW_NOTIFICATION_ENABLED = "whatsNewNotificationEnabled";
    public static final String IS_STATUS_NOTE_V2_ENABLED = "enableStatusNoteV2";
    public static final String IS_STATUS_NOTE_V2_BANNER_ENABLED = "enableStatusNoteV2Banner";
    public static final String IS_STATUS_NOTE_V2_BANNER_DISMISSAL_PERSISTENCE_ENABLED = "isStatusNoteV2BannerDismissalPersistenceEnabled";
    public static final String STATUS_NOTE_V2_BANNER_DISMISS_DURATION_1_ON_1 = "statusV2DismissDurationOneOnOne";
    public static final String STATUS_NOTE_V2_BANNER_DISMISS_DURATION_GROUP = "statusV2DismissDurationGroup";
    public static final String STATUS_NOTE_V2_BANNER_LARGE_GROUP_CHAT_SIZE = "statusNoteV2BannerLargeGroupChatSize";
    public static final String STATUS_NOTE_V2_BANNER_MAX_DISMISSALS = "statusNoteV2BannerMaxDismissals";
    public static final String IS_STATUS_NOTE_V2_MENTION_MESSAGES_ENABLED = "enableStatusNoteV2MentionMessages";
    public static final String GET_USER_PROFILES_BATCH_SIZE = "getUserProfilesBatchSize";
    public static final String GET_USER_PROFILE_EXPIRY_DAYS = "getUserProfilesExpiryDays";
    public static final String GET_USER_PROFILE_EXPIRY_TIME = "defaultProfileExpiryTime";
    public static final String CUSTOM_FILE_UPLOAD_RETRY_POLICY_PARAMS = "customRetryPolicyParams_";
    public static final String CUSTOM_FILE_UPLOAD_RETRY_POLICY_ERRORS = "customRetryPolicyErrors";
    public static final String PROPERTY_TO_PARSE_RETENTION_HORIZON = "propertyToParseRetentionHorizon";
    public static final String SHOULD_FETCH_OCPS_PDC_LEVEL = "shouldFetchOcpsPdcLevel";
    public static final String OCPS_PDC_LEVEL_ECS_OVERRIDE = "ocpsPdcLevelEcsOverride";
    public static final String ARE_CONNECTED_SERVICES_CONTROLS_ENABLED = "areConnectedServicesControlsEnabled";
    public static final String IS_CONNECTED_EXPERIENCES_FRE_VISIBLE = "isConnectedExperiencesFreVisible";
    public static final String CONTROLLER_CONNECTED_SERVICES_ROAMING_ENABLED = "controllerConnectedServicesRoamingEnabled";
    public static final String TELEMETRY_FLUSH_ENABLED = "telemetryFlushEnabled";
    public static final String ARIA_LOGGING_SUPPRESS_EVENTS_TELEMETRY = "ariaLoggingSuppressEventsTelemetry"; //https://ecs.skype.com/?page=ExperimentPage&type=Rollout&id=56617
    public static final String SHOULD_ALLOW_UNKNOWN_SIZE_UPLOADS = "shouldAllowUnknownSizeUploads";
    public static final String DOWNLOAD_DISABLED_FILE_TYPES = "downloadDisabledFileTypes";
    public static final String TOGETHER_MODE_ENABLED = "enableTogetherMode";
    public static final String LARGE_GRID_MODE_ENABLED = "enableLargeGridMode";
    public static final String MINIMUM_PARTICIPANTS_FOR_TOGETHER_MODE = "minimumParticipantsForTogetherMode";
    public static final String MINIMUM_VIDEOS_FOR_LARGE_GALLERY_MODE = "minimumVideosForLargeGalleryMode";
    public static final String LARGE_GRID_BOT_ID = "largeGridBotId";
    public static final String AUDIENCE_VIEW_BOT_ID = "audienceBotId";
    static final String NOTIFICATION_BLOCKED_DETECTION = "notificationBlockedDetection";
    static final String APP_DATA_CLEANUP_WORK_MANAGER = "appDataCleanupWorkManager";
    static final String THROTTLE_CLIENT_CALLS_SERVER_RESPONSE = "enableClientThrottleOnServerResponse";
    static final String THROTTLE_CLIENT_CALLS_NO_NETWORK = "enableClientThrottleOnNoNetwork";
    static final String CAPTIVE_PORTAL_CHECK_ENABLED = "captivePortalCheckEnabled";
    static final String CAPTIVE_PORTAL_CHECK_URL = "captivePortalCheckURL";
    static final String NON_GLOBAL_ENDPOINT_FORCE_UPDATE = "nonGlobalEndpointForceUpdate";
    static final String RICH_TEXT_BLOCK_RECYCLING = "richTextBlockRecycling";
    static final String FRE_OPTIMIZATIONS = "freOptimizations";
    static final String ENABLE_ACTIVE_SIGNAL_ON_LONGPOLL = "enableActiveSignalOnLongpoll";
    static final String SHOULD_SEND_TIMEZONE_IN_CLIENT_INFO = "shouldSendTimezoneInClientInfo";
    static final String ENABLE_NOTIFICATIONS_DISABLED_INDICATOR = "enableNotificationsDisabledIndicator";
    public static final String MOVE_HTTP_TO_NETWORK_THREAD_POOL = "moveHttpToNetworkThreadPool";
    public static final String AUTH_ENABLE_SKYPETOKEN_EXPIRY_ON_IDENTITY_PROMPT = "skypetokenExpiryOnIdentityPrompt";
    public static final String AUTH_ENABLE_SKYPETOKEN_REVOCATION = "enableSkypeTokenRevocation";
    public static final String AUTH_ENABLE_ANONYMOUS_SKYPETOKEN_REVOCATION = "enableAnonymousTokenRevocation";
    public static final String ALWAYS_HONOR_NETWORK_AVAILABILTY_CHECK = "alwaysHonorNetworkAvailabilityCheck";
    public static final String ALWAYS_HONOR_POWER_OPTIMIZATION_CHECK = "alwaysHonorPowerOptimizationCheck";
    public static final String ENABLE_TIMEOUT_FOR_APP_START_LOADING_PROGRESS = "enableTimeoutForAppStartLoadingProgress";
    public static final String NETWORK_BANDWIDTH_SAMPLING_INTERVAL_FACTOR = "getNetworkBandwidthSamplingInterval";
    public static final String NETWORK_BANDWIDTH_THRESHOLD_FACTOR = "getNetworkBandwidthTransitionThreshold";
    public static final String SHOULD_SHOW_POOR_CONNECTION_BANNER = "shouldShowPoorConnectionBanner";
    public static final String AUTH_ENABLE_LLT = "enableLLT";
    public static final String AUTH_ENABLE_TENANTED_SIGN_IN = "enableTenantedSignIn";
    public static final String API_CALLS_TO_IGNORE_SAMPLING = "apiCallsToIgnoreSampling";
    public static final String RANGE_VALUES_FOR_NW_EXCEPTION_MONITOR = "getRangeValuesForNwExceptionMonitor";
    public static final String BANDWIDTH_TIMER_DELAY_IN_SECS = "bandwidthTimerDelayInSeconds";
    public static final String SHOULD_SHOW_POOR_NW_BANNER_DUE_TO_EXCEPTIONS = "shouldShowPoorNetworkBannerDueToExceptions";
    public static final String SHOULD_RESET_NW_BANDWIDTH_ON_NW_TYPE_CHANGE = "shouldResetNetworkBandwidthOnNetworkTypeChange";
    public static final String SHOULD_SHOW_CONNECTING_BANNER = "shouldShowConnectingBanner";
    public static final String LIVE_STATE_SERVICE_PPTSHARING_ENABLED = "liveStateServicePPTSharingEnabled";
    public static final String LIVE_STATE_SERVICE_REACTIONS_ENABLED = "liveStateServiceReactionsEnabled";
    public static final String LIVE_STATE_SERVICE_TELEMETRY_ENABLED = "liveStateServiceTelemetryEnabled";
    public static final String LIVE_STATE_SERVICE_SETTINGS = "liveStateServiceSettings";
    public static final String CALL_PROFILE_VIEW_DISABLE_OVERLAPPING_RENDERING = "callProfileViewDisableOverlappingRendering";
    public static final String SOCKET_TIMEOUT_VALUE_IN_SECS = "socketTimeoutValueInSecs";
    public static final String NUM_OF_BUCKETS_FOR_SOCKET_EXCEPTION_MONITOR = "numBucketsForSocketExcpMonitoring";
    public static final String SHOULD_ENABLE_ADAPTIVE_TIMEOUT_FOR_NW_CALLS = "shouldEnableAdaptiveTimeoutForNwCalls";
    public static final String SOCKET_TIMEOUT_EXCP_THRESHOLD_VALUE = "socketTimeoutExcpThresholdValue";
    public static final String TURN_ON_VIDEO_NOTIFICATION_ENABLED = "isTurnOnVideoNotificationEnabled";
    public static final String ARIA_OVERRIDE_SUPPRESS_LOGGING = "ariaOverrideSuppressLogging";

    public static final String SHOULD_SHOW_MORE_TAB_COACHMARK = "shouldShowMoreTabCoachmark";
    public static final String SHOULD_SHOW_SEARCH_ICON = "shouldShowSearchIcon";

    /* Tagging/Targeting related flags */
    static final String ENABLE_TEAM_MEMBER_TAGS = "enableTeamMemberTags";
    static final String ENABLE_GET_TAG_CARDS_BY_TEAM_IDS = "enableGetTagCardsByTeamIds";
    static final String ENABLE_TAGS_TARGETED_CHAT = "enableTagsTargetedChat";
    static final String ENABLE_TAGS_SETTINGS_FROM_MT = "enableTenantTagSettingsV2";
    static final String MAXIMUM_NUMBER_OF_TAGS_IN_TEAM = "maximumNumberOfTagsInTeam";
    static final String MAXIMUM_TEAMS_IN_TAG_CARDS_BY_TEAM_IDS = "maxTeamsInTagCardsByTeamIdsRequest";
    static final String MAXIMUM_MEMBERS_IN_A_TAG = "maximumMembersInATag";
    static final String MAXIMUM_TAG_NAME_LENGTH = "maximumTagNameLength";
    static final String MAXIMUM_TAG_MEMBERS_ADDED_AT_ONCE = "maximumTagMembersAddedAtOnce";
    static final String TARGETING_TENANT_SETTINGS_CACHE_TIME = "targetingTenantSettingsCacheTime";
    static final String TARGETING_FETCH_TAGS_ON_START = "targetingFetchTagsOnStart";
    public static final String TARGETING_SERVICE_ECS_URL = "targetingServiceUrl";
    public static final String TARGETING_ENABLED_SCHEDULED_TAGS = "enableScheduledTags";
    public static final String TARGETING_ENABLE_TAG_CARD = "enableTagCardsV2";
    public static final String TARGETING_ENABLE_MULTIPLE_CALLS_FOR_ADD_MEMBER = "enableMultipleCallsForAddMember";
    // key to retrieve from config.json
    public static final String DEFAULT_TARGETING_SERVICE_BASE_URL_KEY = "TargetingServiceBaseUrl";

    /* Used to control features inside a group chat, not group chat size */
    public static final String MAX_ROSTER_SIZE_IN_GROUP_CHAT_CHAT_SERVICE_LIMIT = "maxRosterSizeInGroupChat_ChatServiceLimit";
    /* Controls the actual size of group chat */
    public static final String MAX_ROSTER_SIZE_IN_GROUP_CHAT = "maxRosterSizeInGroupChat";
    public static final String ENABLE_PERSONAL_APPS_TRAY = UserPreferences.PERSONAL_APPS_TRAY_SETTING;
    public static final String ENABLE_MEETING_REMINDER = "meetingReminder";
    public static final String ENABLE_MEETING_LIST_IMPROVED_STYLE = "meetingListImprovedStyle";
    public static final String ENABLE_AMS_TRAFFIC_SEPARATION_FOR_UPLOAD = "enableAMSTrafficSeparationForUpload";
    public static final String ENABLE_AMS_TRAFFIC_SEPARATION_FOR_DOWNLOAD = "enableAMSTrafficSeparationForDownload";
    public static final String ENABLE_AMS_REFERENCES_POPULATION = "enableAMSReferencesPopulation";
    public static final String ENABLE_CNS_ACTIVE_FILTER = "enableCNSActiveFiltering";
    public static final String AVOID_CREATE_ENDPOINT_V2 = "shouldAvoidCreateEndpointV2";
    // Database Related Flags
    public static final String ENABLE_FORCE_PRUNE_FOR_OPTIMIZATION = "enableDBForcePruneForOptimization";
    public static final String RUN_FORCE_PRUNE_IRRESPECTIVE_OF_DB_SIZE = "shouldRemoveDBSizeGateToForcePrune";  // Should be very careful about this flag :)
    public static final String SHOULD_DO_FULL_PRUNE = "shouldDoFullPrune";  // Equivalent to DB Reset instead of Pruning last 'X' Days
    public static final String MIN_DAYS_BEFORE_NEXT_DATABASE_FORCE_PRUNE = "minDaysBeforeNextForcePrune";
    public static final String MIN_DB_SIZE_TO_TRIGGER_FORCE_PRUNE_IN_MBS = "minDBSizeToTriggerForcePruneInMBs";
    public static final String AUTO_PRUNE_DAYS_TO_OPTIMIZING_DB = "autoPruneDaysToOptimizeDatabase";
    public static final String MAX_AND_CONDITIONS = "maxAndConditions";
    public static final String SHOULD_LOG_ROW_COUNT_FOR_ALL_TABLE = "shouldLogRowCountForAllTables";
    public static final String SHOULD_ENABLE_DATABASE_INTERCEPTOR = "shouldEnableDatabaseInterceptor";
    public static final String THRESHOLD_QUERY_TIME_BEFORE_LOGGING = "thresholdQueryTimeBeforeLogging";
    public static final String THRESHOLD_RESULT_COUNT_BEFORE_LOGGING = "thresholdResultsCountBeforeLogging";
    public static final String GET_VERSION_WHERE_APP_SHOULD_BE_RESET = "getVersionWhereAppShouldBeReset";
    public static final String GET_VERSION_WHERE_SYNC_STATE_SHOULD_BE_REMOVED = "getVersionWhereSyncStateShouldBeRemoved";
    public static final String DATE_WHEN_LOGGING_IS_REQUESTED = "dateWhenCustomInstrumentationShouldHappen";
    public static final String CLEAR_INDICES = "clearIndices";
    // Files
    public static final String LINK_SHARING_FEATURE = "linkSharing";
    public static final String USE_INVITE_API_FOR_SHARE_LINKS = "useInviteApiForShareLinks";
    public static final String ATTACH_AND_SEND_FILE = "attachAndSendFile";
    public static final String FILE_THUMBNAIL_PREVIEW = "fileThumbnailPreview";
    public static final String FILE_SIZE_PREVIEW = "fileSizePreview";
    public static final String FILE_LIST_CACHING = "fileListCaching";
    public static final String ENABLE_SPECIAL_DOCUMENT_LIBRARIES = "enableSpecialDocumentLibraries";
    public static final String ENABLE_SDL_FOR_NON_EDU_TENANTS = "enableSDLForNonEduTenants";
    public static final String FILE_CACHING_ENABLED = "fileCaching/isFileCachingEnabled";
    public static final String FILE_CACHING_BIG_CACHE_SIZE = "fileCaching/BigCacheSize";
    public static final String FILE_CACHING_SMALL_CACHE_SIZE = "fileCaching/SmallCacheSize";
    public static final String FILE_CACHING_BIG_CACHE_FILE_SIZE_LIMIT = "fileCaching/BigCacheFileSizeLimit";
    public static final String FILE_CACHING_SMALL_CACHE_FILE_SIZE_LIMIT = "fileCaching/SmallCacheFileSizeLimit";
    public static final String FILE_CACHING_CLEANUP_WORKER_DELAY = "fileCaching/fileCachingCleanupWorkerDelay";
    public static final String FILE_UPLOAD_PAUSE_RESUME = "fileUploadPauseResume";
    public static final String FILE_UPLOAD_WORK_MANAGER = "fileUploadWorkManager";
    public static final String FILE_UPLOAD_CLEANUP_THRESHOLD_DAYS = "fileUploadCleanupThresholdDays";
    public static final String FILE_UPLOAD_RESUME_THRESHOLD_DAYS = "fileUploadResumeThresholdDays";
    public static final String FILE_UPLOAD_DEFAULT_MAX_RETRIES = "fileUploadDefaultMaxRetries";
    public static final String FILE_UPLOAD_DEFAULT_INITIAL_DELAY_MS = "fileUploadDefaultInitialDelayMs";
    public static final String FILE_UPLOAD_DEFAULT_MAX_DELAY_MS = "fileUploadDefaultMaxDelayMs";
    public static final String FILE_UPLOAD_DEFAULT_BACKOFF_POLICY = "fileUploadDefaultBackoffPolicy";
    public static final String FILE_UPLOAD_DEFAULT_UPLOAD_SIZE_LIMIT_MB = "fileUploadDefaultSizeMB";
    public static final String DOWNLOAD_FILE_INTERNALLY = "DownloadFileInternally";
    public static final String SHARED_FILES_CLEANUP_DELAY_TIME_HOURS = "sharedFilesCleanupDelayTimeHours";
    public static final String SHARED_FILES_CLEANUP_THRESHOLD_TIME_HOURS = "sharedFilesCleanupThresholdTimeHours";
    public static final String ONE_DRIVE_CONSUMER_ENABLED = "fileOneDriveConsumerEnabled";
    public static final String FILES_TAB_ENABLED = "fileFilesTabEnabled";
    public static final String CHAT_FILES_TAB_UPLOAD_ENABLED = "chatFilesTabUploadEnabled";
    static final String VROOM_FOR_UPLOAD = "useVroomForFileUpload";
    static final String ENABLE_SILENT_RENAMING_ON_FILENAME_CONFLICT = "enableSilentRenamingOnFilenameConflict";
    public static final String DISABLE_AUTO_FOLDER_CREATION_IN_UPLOAD = "disableAutoFolderCreationInUpload";
    static final String VROOM_FOR_DOWNLOAD = "useVroomForFileDownload";
    public static final String DRIVE_ITEM_FOR_FILE_PREVIEW = "useDriveItemForFilePreview";
    static final String FILE_UPLOAD_PAUSE_SCENARIOS = "fileUploadPauseScenarios";
    static final String FILE_UPLOAD_RETRY_SCENARIOS = "fileUploadRetryScenarios";
    static final String ENABLE_UPLOAD_IN_FILES_TAB = "enableUploadInFilesTab";
    static final String FILE_LIST_REFRESH_TIME = "fileListRefreshTime";
    static final String FILE_PREVIEW_FALLBACK_SETTINGS = "filePreviewFallbackSettings";
    public static final String ENABLE_DOWNLOAD_AS_PDF = "files/enableDownloadFileAsPDF";
    public static final String MAX_FILE_SIZE_LIMIT_FOR_PDF_CONVERSION = "files/maxFileSizeLimitForPDFConversion";
    public static final String ALLOWED_FILE_TYPES_FOR_PDF_CONVERSION = "files/allowedFileTypesForPDFConversion";
    public static final String NON_CACHED_FILES_PRUNE_HRS = "files/nonCachedFilesPruneHrs";
    public static final String SKIP_FILE_METADATA_CALL = "files/skipMetadataCall";
    public static final String ENABLE_MS_PDF_VIEWER_FOR_PDF = "files/enableMSPDFViewerForPDF";
    public static final String REFRESH_SITE_URL_ERROR_CODES = "refreshSiteUrlErrorCodes";
    public static final String SHOULD_LIMIT_REFRESH_SITE_URL = "shouldLimitRefreshSiteUrl";
    public static final String SHOULD_PREVIEW_LINKS_WITHIN_TEAMS = "shouldPreviewLinksWithinTeams";
    public static final String SHOULD_SKIP_IS_SHAREPOINT_ENABLED_FOR_FILES_TAB = "shouldSkipIsSharepointEnabledForFilesTab";
    public static final String SHOULD_SKIP_IS_SHAREPOINT_ENABLED_FOR_UPLOAD = "shouldSkipIsSharepointEnabledForUpload";
    public static final String ENABLE_VROOM_RECENTS_LISTING = "files/enableVroomRecentsListing";
    public static final String RECENTS_FILES_LIST_PAGE_SIZE = "files/recentsFilesListPageSize";
    public static final String ENABLE_VIDEO_CDN_SUPPORT = "files/enableVideoCDNSupport";
    public static final String ALLOW_SITE_PROVISIONING_FOR_SHARED_CHANNELS = "allowSiteProvisioningForSharedChannels";
    public static final String ALLOW_REFRESH_SITE_URL_FOR_SHARED_CHANNELS = "allowRefreshSiteUrlForSharedChannels";
    public static final String UPLOAD_CHUNK_SIZES_IN_MB = "uploadChunkSizesInMB";

    // Feeds and Notifications
    public static final String PRIORITY_FEEDS_ENABLED = "enablePriorityMessages";
    public static final String PRIORITY_NOTIFICATIONS_ENABLED = "enablePriorityNotifications";
    public static final String DEEPLINKS_IN_FEEDS = "DeeplinksInFeeds";
    public static final String NOW_IN_FEEDS = "enableNowInFeeds";
    public static final String NOW_PRIORITY_NOTIFICATION_APP = "now/isPriorityNotificationAppEnabled";
    public static final String NOW_WHITELISTED_APP_IDS = "now/whitelistedAppIds";
    public static final String NOW_ANIMATIONS_ENABLED = "now/isNowAnimationsEnabled";
    public static final String FEEDS_GRAPH_API_NOTIFICATION_ENABLED = "feedsGraphAPINotificationsEnabled";
    public static final String ENABLE_V2_NOTIFICATIONS_UI = "enableV2NotificationsUI";
    public static final String ENABLE_MESSAGING_STYLE_NOTIFICATIONS = "enableMessagingStyleNotifications";

    //Feedback
    public static final String FEEDBACK_SHAKE_AND_SEND_ANNOTATION_ENABLED = "shakeAndSendAnnotationEnabled";
    // Data Loss Prevention
    public static final String ENABLE_DLP_NOTIFICATION = "enableDLPNotification";
    // CALLING FEATURES
    public static final String TIMEZONE_ENABLED = "timeZoneEnabled";
    public static final String TIMEZONE_PERSISTENT = "timeZonePersistent";
    public static final String MAX_OVERFLOW_REACTIONS_COUNT = "maxOverflowReactionsCount";
    public static final String MAX_OVERFLOW_REACTIONS_COUNT_TABLET = "maxOverflowReactionsCountTablet";
    public static final String REACTIONS_LASTING_TIME = "reactionsLastingTime";
    public static final String REACTIONS_MEETING_OPTIONS_POLICY_ENABLED = "reactionsMeetingOptionsPolicyEnabled";
    public static final String REACTIONS_ENABLED = "reactionsEnabled";
    public static final String OVERFLOW_REACTIONS_ENABLED = "overflowReactionsEnabled";
    public static final String ONE_TO_ONE_CALL_ESCALATION_ENABLED = "oneToOneCallEscalationEnabled";
    public static final String ONE_TO_ONE_PSTN_CALL_ESCALATION_ENABLED = "oneToOnePstnCallEscalationEnabled";
    public static final String VIDEO_ENABLED = "videoEnabled";
    public static final String MOBILITY_POLICY_ENABLED = "mobilityPolicyEnabled";
    public static final String SYNC_PRESENCE_INCOMING_CALL = "syncPresenceOnIncomingCall";
    public static final String BLOCK_CALLS_ENABLED = "blockCallsEnabled";
    public static final String BLOCKED_CONTACTS_LIST_FETCH_FREQUENCY = "blockedContactsListFetchFrequency";
    public static final String CHAT_CALLING = "chatCalling";
    public static final String ANONYMOUS_MEETING_CHAT_SHOWN = "anonymousMeetingChatShown";
    public static final String ENABLE_LONGPOLL_FOR_INCOMING_CALLS = "enableLongPollForIncomingCalls";
    public static final String ENABLE_CALL_VIDEOUFD = "enableCallVideoUFD";
    public static final String DIALIN_UFD_ENABLED = "enableDialInUFD";
    public static final String START_STOP_RECORDING_ENABLED = "startRecordingEnabled";
    public static final String COMPLIANCE_RECORDING_HANDLING_ENABLED = "complianceRecordingHandlingEnabled";
    public static final String FOLLOW_CALL_RECORDING_POLICY_ENABLED = "followCallRecordingPolicyEnabled";
    public static final String SET_MEDIA_PORT_RANGES_ENABLED = "setMediaPortRangesEnabled";
    public static final String MEETING_JOIN_RESTRICTION = "meetingJoinRestrictionEnabled";
    public static final String CHAT_ONGOING_MEETING_RESTRICTION = "chatMessageOngoingMeetingRestrictionEnabled";
    public static final String CHAT_MESSAGE_RESTRICTION = "chatMessageRestrictionEnabled";
    public static final String PRIVATE_MEETING = "privateMeeting";
    public static final String DELETE_MEETING = "deleteMeeting";
    public static final String ENABLE_BLOCK_INCOMING_CALL_BASED_ON_POLICY = "enableBlockIncomingCallBasedOnPolicy";
    public static final String STRUCTURED_MEETING_ENABLED = "structuredMeetingEnabled";
    public static final String STRUCTURED_MEETING_ACTIONS_ENABLED = "structuredMeetingActionsEnabled";
    public static final String STRUCTURED_MEETING_FOR_ANONYMOUS_USRES_ENABLED = "structuredMeetingForAnonymousUsersEnabled";
    public static final String FREEMIUM_AD_HOC_MEETINGS_ENABLED = "freemiumAdHocMeetingsEnabled";
    public static final String FREEMIUM_AD_HOC_MEETINGS_WHATS_NEW = "freemiumAdHocMeetingsWhatsNew";
    public static final String FREEMIUM_AD_HOC_MEETINGS_RENAME_ENABLED = "freemiumAdHocMeetingsRenameEnabled";
    public static final String FREEMIUM_AD_HOC_MEETINGS_DELETE_ENABLED = "freemiumAdHocMeetingsDeleteEnabled";
    public static final String FREEMIUM_AD_HOC_MEETINGS_LIST_ENABLED = "freemiumAdHocMeetingsListEnabled";
    public static final String FREEMIUM_MEETING_OPTIONS_ENABLED = "freemiumAdHocMeetingOptionsEnabled";
    public static final String FREEMIUM_MEETING_OPTIONS_COOKIE_ENABLED = "freemiumAdHocMeetingOptionsCookieEnabled";
    public static final String CALL_ROSTER_MEETING_OPTIONS_ENABLED = "callRosterMeetingOptionsEnabled";
    public static final String CALL_ROSTER_MANAGE_AUDIO_VIDEO_ENABLED = "callRosterManageAudioVideoEnabled";
    public static final String CALL_ROSTER_XL_MEETING_ENABLED  = "callRosterXLMeeting";
    public static final String CALL_ROSTER_V2_ENABLED = "callRosterV2Enabled";
    public static final String AUTO_RECONNECT = "autoReconnect";
    public static final String CALL_ME_BACK = "callMeBack";
    public static final String ENABLE_ASYNC_MEDIA = "asyncMEdiaInit";
    public static final String DAIL_IN_ENABLE = "dailInEnabled";
    public static final String MEETING_DIALIN_ENABLED = "meetingDialInEnabled";
    public static final String DIAL_IN_ONLY_MEETING_JOIN_ENABLED = "dialInOnlyMeetingJoin";
    public static final String IP_AUDIO_VIDEO_MODE_ENABLED = "ipAudioVideoModelEnabled";
    public static final String CALLS_TAB = "callsTab";
    public static final String PSTN_CALLING = "PSTNCalling";
    public static final String MEETING_JOIN_RECONNECTING_POP_OUT_TIME = "meetingJoinReconnectingDialogPopOutTime";
    public static final String INTENT_TO_CALL_ENABLED = "intentToCallEnabled";
    public static final String PPT_SHARING = "PPTSharing";
    public static final String PPT_PRIVATE_VIEWING_ENABLED = "PPTPrivateViewingEnabled";
    public static final String REACT_NATIVE_CALLING_SYNCHRONIZATION_ENABLED = "reactNativeCallingSynchronizationEnabled";
    public static final String BROADCAST_ATTENDEE_EXPERIENCE = "BroadcastAttendeeExperience";
    public static final String BROADCAST_EVENT_LOG = "enableBroadcastEventLog";
    public static final String REJOIN_TENANTED_SIGN_IN_ENABLED = "isRejoinTenantedSignInEnabled";
    public static final String ADD_A_ROOM_JOIN_EXPERIENCE = "addARoomJoinExperience";
    public static final String ENABLE_NEW_ATTENDEE_SERVICE_URL = "enableNewAttendeeServiceURL";
    public static final String IN_APP_STREAM_PLAYER = "inAppStreamPlayer";
    public static final String PLAY_AMS_RECORDING = "PlayAMSRecording";
    public static final String AMS_RECORDING_EXPIRATION_DURATION = "AMSRecordingExpirationDuration";
    public static final String START_PLAY_RECORDING_IN_ONE_DRIVE_FOR_BUSINESS = "StartPlayRecordingInOneDriveForBusinessEnabled";
    public static final String ENABLE_MEETINGS_DIALOUT = "enableMeetingsDialOut";
    public static final String CHECK_BUSINESS_VOICE_FOR_PSTN = "checkBusinessVoiceForPSTN";
    public static final String VOICE_MAIL = "voiceMail";
    public static final String VOICE_MAIL_FOR_ALL_ENABLED = "voiceMailForAllEnabled";
    public static final String NATIVE_CALL_TAB_ENABLED = "nativeCallsTabEnabled";
    public static final String CQF_ENABLED = "cqfEnabled";
    public static final String CQF_FOR_SUCCESSFUL_CALLS_ONLY_ENABLED = "CQFForSuccessfulCallsOnlyEnabled";
    public static final String CQF_AUTO_DISMISS_ENABLE = "CQFAutoDismissEnable";
    public static final String CQF_AUTO_DISMISS_TIMEOUT = "CQFAutoDismissTimeout";
    public static final String CQF_AUTO_DISMISS_NO_ACTION_TIMEOUT = "CQFAutoDismissNoActionTimeout";
    public static final String CQF_RATING_CAPTURE_ON_DISMISS_ENABLED = "CQFRatingCaptureOnDismissEnabled";
    public static final String HANDLE_PUSH_WITH_EXPIRED_TOKEN_ENABLED = "handlePushWithExpiredTokenEnabled";
    public static final String CALLING_LEGACY_MEDIA_PEER_TYPE_ENABLED = "callingLegacyMediaPeerTypeEnabled";
    public static final String CALL_FORWARDING_SETTINGS_ENABLED = "callForwardingSettingsEnabled";
    public static final String QUIET_HOURS_SETTINGS_ENABLED = "quietHoursSettingsEnabled";
    public static final String MULTIACCOUNT_ENABLED = "multiaccountSettingEnabled";
    public static final String RECORDING_BOT_MRI = "recordingBotMri";
    public static final String CONSULT_TRANSFER_ENABLED = "consultTransferEnabled";
    public static final String COMPANION_MODE_ENABLED = "companionModeEnabled";
    public static final String STEP_TELEMETRY_ENABLED = "stepTelemetryEnabled";
    public static final String MEETING_RECORDING_STORAGE_ENABLED = "enableRecordingSharepointStorage";
    public static final String E911_NCS_ENABLED = "e911NCSEnabled";
    public static final String E911_LLDP_INFO_UPDATE_ENABLED = "e911LLDPInfoUpdateEnabled";
    public static final String E911_DIRECT_ROUTING_ENABLED = "e911DirectRoutingEnabled";
    public static final String E911_CALLING_PLAN_ENABLED = "e911CallingPlanEnabled";
    public static final String E911_SECURITY_DESK_ENABLED = "e911SecurityDeskEnabled";
    public static final String MEDIA_PATH_OPTIMIZATION_ENABLED = "mediaPathOptimizationEnabled";
    public static final String BADGE_COUNT_SERVICE_ENABLED = "badgeCountServiceEnabled";
    public static final String BADGE_COUNT_SERVICE_PUSH_NOTIFICATION_ENABLED = "badgeCountServicePushNotificationEnabled";
    public static final String ACCOUNT_TYPE_FOR_BADGE_COUNT_SERVICE = "accountTypeForBadgeCountService";
    public static final String REGISTRATION_EXPIRATION_TIME_FOR_BADGE_COUNT_SERVICE = "registrationExpirationTimeForBadgeCountService";
    public static final String HANDOFF_REQUEST_ENABLED = "handOffRequestEnabled";
    public static final String CALL_HANDOFF_ENABLED = "callHandOffEnabled";
    public static final String ADD_ROOM_ENABLED = "addRoomEnabled";
    public static final String ADD_ROOM_ENABLED_FOR_FREEMIUM = "addRoomEnabledForFreemium";
    public static final String BIG_SWITCH_ENABLED = "bigSwitchEnabled";
    public static final String EARLY_CANCELLED_CALL_TIME = "earlyCancelledCallTime";
    public static final String EARLY_CANCELLED_MEETING_TIME = "earlyCancelledMeetingTime";
    public static final String ENABLE_REMOTE_MUTE = "enableRemoteMute";
    public static final String GROUP_CALLING_ENABLED = "groupCallingEnabled";
    public static final String MULTI_CALL_SUPPORT_ENABLED = "multiCallSupportEnabled";
    public static final String LOBBY_JOIN_ENABLED = "lobbyJoinEnabled";
    public static final String NUMBER_OF_CONCURRENT_CALLS = "numberOfConcurrentCalls";
    public static final String MAX_NUMBER_OF_CALL_REGISTRY_QUEUED_CALLS = "maxNumberOfCallRegistryQueuedCalls";
    public static final String S2S_PERCENT = "S2S_Percent";
    public static final String PSTN_PERCENT = "PSTN_Percent";
    public static final String GROUP_CALL_PERCENT = "GroupCall_Percent";
    public static final String MIN_CALL_DURATION_SECS = "MinCallDurationSecs";
    public static final String MIN_EVENT_DURATION_SECS = "MinEventDurationSecs";
    public static final String LIVE_EVENT_PERCENT = "LiveEvent_Percent";
    public static final String STREAM_PLAYER_TOKEN_REQ_TIMEOUT_IN_SEC = "streamPlayerTokenReqTimeoutInSec";
    public static final String AMP_STYLESHEET_URL = "ampStylesheetUrl";
    public static final String AMP_MINIFIED_JS_URL = "ampMinifiedJSUrl";
    public static final String UPDATE_SUGGESTED_REMINDER_INTERVAL = "updateSuggestedReminderInterval";
    public static final String UPDATE_ENCOURAGED_REMINDER_INTERVAL = "updateEncouragedReminderInterval";
    public static final String MASK_CALLERID_ENABLED = "maskCallerIdEnabled";
    public static final String CALL_TRANSFER_ENABLED = "callTransferEnabled";
    public static final String FETCH_FEDERATED_USER_CALL_TRANSFER_ENABLED = "fetchFederatedUserCallTransferEnabled";
    public static final String CQF_SLIMCORE_TRIGGER_ENABLED = "cqfSlimcoreTriggerEnabled";
    public static final String WEBINAR_MEETING_JOIN_ENABLED = "webinarMeetingJoinEnabled";
    public static final String CALL_DEBUG_ENABLED = "callDebugEnabled";
    public static final String MUSIC_ON_HOLD_ENABLED = "musicOnHoldEnabled";
    public static final String MUSIC_ON_HOLD_V2_ENABLED = "enableMusicOnHoldV2";
    public static final String CALL_DRIVE_MODE_ENABLED = "callDriveModeEnabled";
    public static final String PIP_ENABLED = "pipEnabled";
    public static final String LIVE_EVENT_PIP_ENABLED = "liveEventPipEnabled";
    public static final String CALL_LOG_CONTACT_TYPE = "callLogContactType";
    public static final String NEW_CALLING_UX_ENABLED = "newCallingUXEnabled";
    public static final String NEW_CALLING_UX2_ENABLED = "newCallingUX2Enabled";
    public static final String NEW_CALL_ME_BACK_UX_ENABLED = "newCallMeBackUXEnabled";
    public static final String PROXIMITY_JOIN_MEETING = "proximityJoin";
    public static final String ENABLE_ONGOING_MEETING_JOIN_AS_DEEP_LINK = "enableOngoingMeetingJoinAsDeepLink";
    public static final String ENABLE_ONE_ON_ONE_CALL_RECORDING = "enable1on1CallRecording";
    public static final String EARLY_RINGING = "earlyRinging";
    public static final String MEETING_DETAILS_DAYS_TO_SYNC = "meetingDetailsDaysToSync";
    public static final String MEETING_CHAT_ADD_PARTICIPANTS_ENABLED = "meetingChatAddParticipantsEnabled";
    public static final String DTMF_OPTION_ENABLED = "DTMFOptionEnabled";
    public static final String CALL_ROSTER_ACTIVE_SPEAKER_ENABLED = "callRosterActiveSpeakerEnabled";
    public static final String BROADCAST_PRESENTER_EXPERIENCE = "BroadcastPresenterExperience";
    public static final String LIVE_EVENT_TICK_INTERVAL_IN_SEC = "liveEventTickIntervalInSec";
    public static final String CALL_PARK_ENABLED = "callParkEnabled";
    public static final String MEETING_START_NOTIFICATIONS_ENABLED = "meetingNotificationsEnabled";
    public static final String FLW_PRESENCE_ENABLED = "enableFLWPresence";
    public static final String FLW_PRESENCE_DIALOG_ENABLED = "enableOffShiftNoticeDialog";
    public static final String FLW_PRESENCE_OFFSHIFT_BLOCKING_MODE_ENABLED = "enableOffShiftBlockingModeEnabled";
    public static final String FORCE_FULL_SCREEN_CALL_NOTIFICATION = "forceFullScreenCallNotificationEnabled";
    public static final String AUTO_BRB_FOR_LONG_RUNNING_SKYLIB_INITIALIZATION = "autoBRBForLongRunningSkylibInitializationEnabled";
    public static final String GROUP_CALL_PICKUP_ENABLED = "groupCallPickupEnabled";
    public static final String CALL_DELEGATION_ENABLED = "callDelegationEnabled";
    public static final String LOCATION_BASED_CALL_ROUTING_ENABLED = "locationBasedCallRoutingEnabled";
    public static final String MEETING_CHAT_MUTE_SETTINGS_FOR_NOISY_NOTIFICATIONS_ENABLED = "meetingChatMuteSettingsEnabled";
    public static final String DEFAULT_MEETING_MUTE_SETTING = "defaultMeetingMuteSetting";
    public static final String ENABLE_LISTENER_FOR_SKYLIB_CALL_EVENTS = "listenerForSkylibManagerEvents";
    public static final String ENABLE_FOREGROUND_CALL_NOTIFICATION = "foregroundCallNotification";
    public static final String ENABLE_HIGH_RES_AVATAR = "enableHighResAvatar";
    public static final String AVATAR_RESOLUTION = "avatarResolution";
    public static final String ENABLE_NATIVE_PHONEBOOK_CALLING = "enableNativePhonebookCalling";
    public static final String ENABLE_NEW_MEETING_CAPABILITIES = "enableNewMeetingCapabilities";
    public static final String TROUTER_ENABLED = "trouterEnabled";
    public static final String PARTICIPANT_PIN_ENABLE = "enableParticipantPin";
    public static final String RAISE_HANDS_ENABLED = "enableRaiseHands";
    public static final String RAISE_HANDS_MESSAGE_TIME_OUT = "raiseHandMessageTimeout";
    public static final String HARD_MUTE_MESSAGE_TIME_OUT = "hardMuteMessageTimeout";
    public static final String ENABLE_END_JOIN_SCENARIO_ON_CALL_STATUS = "enabledEndJoinScenarioOnCallStatus";
    public static final String MAIN_STAGE_CONTENT_SWITCH_ENABLED = "enableMainStageContentSwitch";
    public static final String MAIN_STAGE_ANIMATION_ENABLED = "mainStageAnimationEnabled";
    public static final String MODERN_STAGE_ENABLED = "enableModernStage";
    public static final String BREAKOUT_ROOM_EXPERIENCE_ENABLED = "enableBreakoutRoomJoinExperience";
    public static final String DOCKED_CALL_CONTROLS_ENABLED = "dockedCallControlsEnabled";
    public static final String MOBILE_CALL_CONTROLS_CONFIGURATION = "mobileCallControlsConfiguration";
    public static final String TABLET_CALL_CONTROLS_CONFIGURATION = "tabletCallControlsConfiguration";
    public static final String IPPHONE_CALL_CONTROLS_CONFIGURATION = "ipPhoneCallControlsConfiguration";
    public static final String KINGSTON_CALL_CONTROLS_CONFIGURATION = "kingstonCallControlsConfiguration";
    public static final String DUO_CALL_CONTROLS_CONFIGURATION = "duoCallControlsConfiguration";
    public static final String BREAKOUT_ROOM_BANNER_ENABLED = "enabledBreakoutRoomBanner";
    public static final String BREAKOUT_ROOM_MM_BANNER_ENABLED = "enabledBreakoutRoomMMBanner";
    public static final String BREAKOUT_ROOM_ANNOUNCEMENT_DIALOG_ENABLED = "enableBreakoutRoomAnnouncementDialog";
    public static final String BREAKOUT_ROOM_DURATION_ALERT = "breakoutRoomDurationAlertInSeconds";
    public static final String BREAKOUT_ROOM_ENABLE_HIDE_LEAVE = "enableBreakoutRoomHideLeaveButton";
    public static final String VIDEO_GRID_VIEW_V2_ENABLED = "enableVideoGridViewV2";
    public static final String VIDEO_OPTIMIZATION_ON_STAGE_ENABLED = "enableVideoOptimizationOnStage";
    public static final String MAIN_STAGE_PARTICIPANT_COUNT = "mainStageParticipantCount";
    public static final String REMEMBER_MAIN_STAGE_TYPE_ENABLED = "enableRememberMainStageType";
    public static final String LOW_DATA_USAGE_MODE_ENABLED = "lowDataUsageEnabled";
    public static final String LOW_DATA_USAGE_MODE_ENABLED_BY_DEFAULT = "lowDataUsageEnabledByDefault";
    public static final String LOW_DATA_USAGE_MODE_BANDWIDTH_IN_KPS = "callingLowDataBandwidthInKbps";
    public static final String LOW_DATA_USAGE_MODE_MAX_COUNT_BANNER_DISPLAYED = "callingLowDataMaxCountBanner";
    public static final String ENABLE_RESET_ARIA_TRANSMIT_PROFILE_IN_CALL = "enableResetAriaTransmitProfileInCall";
    public static final String ENABLE_PARTICIPANT_LONG_PRESS_MENU_TOOL_TIP = "enableParticipantLongPressMenuToolTip";
    public static final String PARTICIPANT_LONG_PRESS_MENU_TOOL_TIP_SHOWN_TIMES = "participantLongPressMenuToolTipShownTimes";
    public static final String ENABLE_MULTIPLE_INCOMING_CALL = "enableMultipleIncomingCall";
    public static final String NUMBER_OF_PRECALL_ALLOWED = "numberOfPrecallAllowed";
    public static final String LIVE_CAPTIONS_ENABLED = "liveCaptionsEnabled";
    public static final String CALL_LIVE_CAPTIONS_ENABLED = "callLiveCaptionsEnabled";
    public static final String ENABLE_ANDROIDQ_INCOMING_CALLS_BY_NOTIFICATIONS_ONLY = "enableAndroidQIncomingCallsByNotificationsOnly";
    public static final String ENABLE_USING_NEW_START_FOREGROUND_SERVICE_API_FOR_CALL_SERVICES = "enableUsingStartForegroundServiceApiForCallServices";
    public static final String ENABLE_SERVICE_BINDING_TO_START_SERVICE = "enableServiceBindingToStartService";
    public static final String USE_VALID_SKYPE_TOKEN_FOR_SKYLIB = "useValidSkypeTokenWithoutExecAuthRequest";
    public static final String ATTENDEE_SERVICE_APP_ID = "attendeeServiceAppId";
    public static final String SHOULD_USE_MT_TOKEN_FOR_ATTENDEE_SERVICE = "shouldUseMTTokenForAttendeeService";
    public static final String ENABLE_AUDIO_BLUETOOTH_FILTER_HEADSET_ONLY = "enableAudioBluetoothFilterHeadsetOnly";
    public static final String ENABLE_SKYLIB_EVENTS_DEDUPING = "enableSkylibEventsDeduping";
    public static final String ENABLE_SKYLIB_EVENTS_WHITELIST = "enableSkylibEventsWhitelist";
    public static final String SHOULD_IGNORE_SCO_ERROR = "shouldIgnoreSCOError";
    public static final String ENABLE_CALL_MERGE = "enableCallMerge";
    public static final String ENABLE_PSTN_CALL_MERGE = "enablePSTNCallMerge";
    public static final String ENABLE_SKYLIB_SETUP_KEYS_FOR_ENDPOINTS = "enableSkylibSetupKeysForEndpoints";
    public static final String ENABLE_HARD_MUTE = "enableHardMute";
    public static final String ENABLE_INDIVIDUAL_AUDIO_HARD_MUTE = "enableIndividualAudioHardMute";
    public static final String ENABLE_INDIVIDUAL_VIDEO_HARD_MUTE = "enableIndividualVideoHardMute";
    public static final String BACKGROUND_BLUR_ENABLED = "backgroundBlurEnabled";
    public static final String BACKGROUND_REPLACEMENT_ENABLED = "backgroundReplacementEnabled";
    public static final String BACKGROUND_CUSTOM_IMAGE_ENABLED = "backgroundCustomImageEnabled";
    public static final String ENABLE_PARTICIPANT_COUNT_FROM_SLIMCORE = "enableParticipantCountFromSlimcore";
    public static final String DISABLE_ATTENDEE_TO_ADD_PEOPLE = "disableAttendeeToAddPeople";
    public static final String ENABLE_NON_INTERACTIVE_XL_MEETING = "enableNonInteractiveXLMeeting";
    public static final String SPOTLIGHT_ENABLED = "enableSpotlight";
    public static final String MULTIPLE_PARTICIPANT_SPOTLIGHT = "enableMultipleParticipantSpotlight";
    public static final String LIVESTATE_CHECK_FOR_GLOBAL_ACTIVE_CALL = "enableLiveStateCheckForGlobalActiveCall";
    public static final String TRANSCRIPTION_UFD_ENABLED = "enableTranscriptionUfd";
    public static final String NDI_UFD_ENABLED = "enableNDIUfd";
    public static final String OVERFLOW_MEETING_ENABLED = "overflowMeetingEnabled";
    public static final String ENABLE_MEETING_LOCK = "enableMeetingSettings";
    public static final String ANONYMOUS_JOIN_ACCOUNT_PICKER_ENABLED = "anonymousJoinAccountPickerEnabled";
    public static final String WEBINAR_ACCOUNT_PICKER_ENABLED = "isWebinarAccountPickerEnabled";
    public static final String MEETING_EXTERNAL_ACCOUNT_PICKER_ENABLED = "isMeetingExternalAccountPickerEnabled";
    public static final String MEETING_TENANT_ID_TO_NAME = "meetingTenantIdToName";
    public static final String MEETING_TENANT_GET_HELP_URL = "meetingTenantGetHelpUrl";
    public static final String  NOTIFY_DUMMY_NOTIFICATION_CALL_FOREGROUND_SERVICE = "notifyInCallTempNotificationOnStart";
    public static final String  NOTIFY_DUMMY_NOTIFICATION_CALL_FOREGROUND_SERVICE_ON_CREATE = "notifyInCallTempNotificationOnCreate";
    public static final String  NOTIFY_DUMMY_NOTIFICATION_PRE_CALL_FOREGROUND_SERVICE = "notifyPreCallTempNotificationOnStart";
    public static final String ENABLE_SLIM_CORE_LOGGING = "enableSlimCoreLogging";
    public static final String ALLOW_DISABLE_CALL_SERVICES_APP_IN_FOREGROUND = "allowDisableCallForegroundServicesWhenAppInForeground";
    public static final String CALLS_TAB_SHORTCUT_ENABLED = "callsTabShortcutEnabled";

    // MEETING FILES
    public static final String MEETING_RECOMMENDATION_FILES_ENABLED  = "meetings/isRelatedFilesEnabled";
    public static final String MEETING_MAX_RECOMMENDED_FILES_SHOW_COUNT = "meetings/maxRelatedFilesShown";
    public static final String MEETING_RECOMMENDATION_EXCHANGE_FILES_ENABLED = "meetings/isExchangeRelatedFilesEnabled";

    // PLATFORM RELATED FLAGS
    public static final String PLATFORM_REACT_NATIVE_MODULES_ENABLED = "platform/reactNativeModulesEnabled";
    public static final String PLATFORM_NATIVE_MODULES_ENABLED = "platform/nativeModulesEnabled";
    public static final String SIDE_LOAD_MOBILE_MODULES_ENABLED = "sideLoadMobileModulesEnabled";
    public static final String ENABLE_ASSIGNMENTS = "enableAssignments";
    public static final String ENABLE_MOBILE_MODULES = "enableMobileModules";
    public static final String ENABLE_DEEPLINK_TO_MOBILE_MODULE = "deepLinkToMobileModuleEnabled";
    public static final String LIST_OF_RN_APPS_FOR_PREINIT = "platform/listOfRNAppsForPreInit";
    // enable/ disable showing user installed LOB (line of business) from the user entitlement list.
    public static final String SHOW_USER_INSTALLED_LOB_APPS = "showUserInstalledLOBApps";
    public static final String HIDE_DEFAULT_APPS = "hideDefaultApps";
    public static final String SHOW_DEVICE_MANAGEMENT_SETTING_OPTION = "showDeviceManagementSettingsOption";
    // enable/ disable showing user installed third party apps from the user entitlement list.
    public static final String SHOW_USER_INSTALLED_STORE_APPS = "showUserInstalledStoreApps";
    public static final String SHOW_USER_INSTALLED_SIDE_LOADED_APPS = "showUserInstalledSideLoadedApps";
    public static final String WHITELISTED_USER_INSTALLED_APPS = "whitelistedUserInstalledApps";
    public static final String ONCLICK_INITIALIZE_APPS_FOR_BOTTOM_BAR = "platform/onClickInitializeAppsForBottomBar";
    public static final String MIN_APP_SCHEMA_VERSION_TO_ENABLE_TABS_ON_MOBILE = "minAppSchemaVersionToEnableTabsOnMobile";
    //fetching max retry limit for chat, team and user entitlement APIs
    public static final String MAX_RETRY_LIMIT_FOR_APP_ENTITLEMENTS = "platform/fetchAppEntitlementsNetworkRetryCount";
    public static final String PLATFORM_MOBILE_SUPPORTED_FEEDS_NOTIFICATIONS_APPS = "platform/feedsAndNotificationsMobileSupportedApps";
    public static final String PLATFORM_ALLOW_CHAT_ENTITLEMENTS_SYNC_FROM_ACTIVITY = "platform/allowChatAppEntitlementsSyncFromActivity";
    public static final String PLATFORM_CHECK_USER_PINNING_ALLOWED_SETTING = "platform/checkUserPinningAllowedSetting";
    // Enable Tabs SSO for 3P apps
    public static final String EXTENSIBILITY_SSO_CONSENT_ENABLED = "platform/extensibilityConsentSSOEnabled";
    // Enable Bots SSO
    public static final String EXTENSIBILITY_BOT_SSO_ENABLED = "platform/extensibilityBotSsoEnabled";
    // Enable Bots File Attachment
    public static final String BOT_FILE_ATTACHMENT_ENABLED = "platform/extensibilityBotFileAttachmentEnabled";
    // Message Action Flag
    public static final String MESSAGING_ACTIONS = "platform/messageActionsEnabled";
    // Enable Messaging Extension SSO
    public static final String EXTENSIBILITY_ME_SSO_ENABLED = "platform/extensibilityMESsoEnabled";
    // Enable Messaging Extension SSO proactively for 1st party apps
    public static final String EXTENSIBILITY_ME_SSO_BOT_ALLOW_LIST = "platform/extensibilityMESsoBotAllowList";
    //Enable device capability check
    public static final String DEVICE_CAPABILITY_CHECK_ENABLED = "deviceCapabilityCheck";
    // Enable apps device permission
    public static final String PLATFORM_DEVICE_PERMISSIONS_ENABLED = "platform/platformAppDevicePermissionsEnabled";
    // Device capabilities image size threshold
    public static final String PLATFORM_DEVICE_CAPABILITY_FILE_SIZE_THRESHOLD_KB = "platform/platformDeviceCapabilityFileSizeThresholdInKB";
    // Device capability apis enabled
    public static final String PLATFORM_DEVICE_CAPABILITY_ENABLED_API_LIST = "platform/deviceCapabilitiesEnabledApiList";
    // Device capability settings enabled
    public static final String PLATFORM_DEVICE_CAPABILITY_ENABLED_SETTING_LIST = "platform/deviceCapabilitiesEnabledSettingList";
    // Device capabilities get media file transfer threshold for low end devices
    public static final String PLATFORM_GET_MEDIA_LOW_END_FILE_SIZE_THRESHOLD_KB = "platform/platformGetMediaLowEndFileSizeThresholdInKB";
    // sync app definition without thread property update (Refer: extensionDefinitionContainer property)
    public static final String SYNC_CHAT_ENTITLEMENTS_WITHOUT_EXTENSION_PROPERTY = "platform/syncChatEntitlementsWithoutExtensionProperty";
    // Max audio recording time
    public static final String PLATFORM_DEVICE_CAPABILITY_MAX_AUDIO_RECORD_TIME = "platform/deviceCapabilitiesMaxAudioRecordTime";
    // GALLERY TAB RELATED FLAGS
    public static final String GALLERY_TAB_ENABLED = "gallery/galleryTabEnabled";
    // Meeting extensibility flag
    public static final String MEETING_EXTENSIBLITY_ENABLED = "platform/meetingExtensibilityEnabled";
    // ChatEntitlement sync enabled for anonymous users
    public static final String ANONYMOUS_USER_CHAT_ENTITLEMENT_SYNC_ENABLED = "platform/anonymousUserChatEntitlementSyncEnabled";
    // Apps whitelisted for showing up as in meeting tabs
    public static final String IN_MEETING_TAB_WHITELISTED_APPS = "platform/inMeetingTabsWhitelistedApps";
    // Supported Platform Flag for static tabs
    public static final String SUPPORTED_PLATFORM_FLAG_FOR_STATIC_TABS_ENABLED = "platform/readSupportedPlatformsFlagForStaticTabsEnabled";
    // Supported Platform Flag for configurable tabs
    public static final String SUPPORTED_PLATFORM_FLAG_FOR_CONFIGURABLE_TABS_ENABLED = "platform/readSupportedPlatformsFlagForConfigTabsEnabled";

    public static final String CHAT_INVITE_LINK_BANNER_ENABLED = "chatInviteLinkBannerEnabled";

    // Vault
    public static final String VAULT_ENABLED = "vaultEnabled";
    public static final String EXPERTS_ENABLED = "expertsEnabled";
    public static final String VAULT_AUTH_TIMEOUT = "vaultAuthTimeout";
    public static final String VAULT_SHARE_IN_CHAT_ENABLED = "vaultShareInChatEnabled";
    public static final String VAULT_RESET_USER_ENABLED = "vaultResetUserEnabled";
    public static final String VAULT_DELETE_PERSONAL_VAULT_ENABLED = "vaultDeletePersonalEnabled";
    public static final String VAULT_TELEMETRY_SAMPLING_RATE = "vaultTelemetrySamplingRate";
    public static final String VAULT_RECOVERY_ENABLED = "vaultRecoveryEnabled";
    public static final String VAULT_MEDIA_SETTINGS = "vaultMediaSettings";
    public static final int VAULT_DEF_AUTH_TIMEOUT = 0;
    public static final int VAULT_DEF_TELEMETRY_SAMPLING_RATE = 100;

    // APP RATING FLAGS
    public static final String APP_RATING = "appRating";
    public static final String DAYS_SINCE_INSTALLED_THRESHOLD = "daysSinceInstalledThreshold";
    public static final String NUM_OF_LAUNCHES_THRESHOLD = "numOfLaunchesThreshold";
    public static final String DAYS_SINCE_SKIPPED_THRESHOLD = "daysSinceSkippedThreshold";
    public static final String NUM_OF_LAUNCHES_SINCE_SKIPPED_THRESHOLD = "numOfLaunchesSinceSkippedThreshold";
    public static final String NUM_OF_DETAIL_PAGE_VISITED_THRESHOLD = "numOfDetailPageVisitedThreshold";
    public static final String NUM_OF_CORE_TASKS_COMPLETED_THRESHOLD = "numOfCoreTasksCompletedThreshold";
    public static final String PERIOD_FOR_APPLAUNCH_THRESHOLD = "periodforAppLaunchThreshold";
    // Retention related flags - AUTO-PRUNE/TimeBasedRetention
    public static final String ENABLE_AUTO_PRUNE = "enableAutoPrune";
    public static final String AUTO_PRUNE_DAYS = "autoPruneDays";
    public static final String FORCE_AUTO_PRUNE_DAYS = "forceAutoPruneDays";
    public static final String AUTO_PRUNE_INTERVAL = "autoPruneInterval"; // To be used Auto Prune based Policy
    public static final String SUPPORT_TIME_BASED_RETENTION = "supportTimeBasedRetention";
    public static final String RETENTION_PRUNE_FOR_SHARED_CHANNEL_ENABLED = "isRetentionPruneForSharedChannelEnabled";
    public static final String BACKGROUND_PRUNE_JOB_INTERVAL = "backgroundPruneJobInterval"; // To be used with Server triggered retention policy
    public static final String SUPPORT_TIME_BASED_RETENTION_USING_THREADS = "supportTimeBasedRetentionUsingThreads"; // To be used if retentionHorizon does not come in conversation
    // SHARE FEATURE FLAGS
    public static final String SHARE_PHOTO_MEDIA_ENABLED = "sharePhotoMediaEnabled";
    public static final String SHARE_FILE_ENABLED = "shareFileEnabled";
    public static final String SHARE_CHANNEL_OR_ONEDRIVE_FILE_ENABLED = "shareChannelOrOnedriveFileEnabled";
    public static final String SHARE_MEDIA_ENABLED = "shareMediaEnabled";
    public static final String SHARE_LOCATION_ENABLED = "shareLocationEnabled";
    public static final String SHARE_LOCATION_V2_ENABLED = "shareLocationV2Enabled";
    public static final String PLACE_SEARCH_V2_ENABLED = "placeSearchV2Enabled";
    public static final String ENABLE_ENHANCED_TELEMETRY = "enableEnhancedTelemetry";
    public static final String SHARE_LOCATION_AMS_UPLOAD_ENABLED = "shareLocationAmsUploadEnabled";
    public static final String SHARE_ANNOTATION_ENABLED = "shareAnnotationEnabled";
    public static final String SHARE_INTENTIONAL_WHITEBOARD_ENABLED = "shareIntentionalWhiteboardEnabled";
    public static final String SHARE_INTENTIONAL_WHITEBOARD_FOR_ANON_ENABLED = "shareIntentionalWhiteboardForAnonEnabled";
    public static final String SHARE_INTENTIONAL_WHITEBOARD_FOR_FREEMIUM_ENABLED = "shareIntentionalWhiteboardForFreemiumEnabled";
    public static final String START_INTENTIONAL_WHITEBOARD_ENABLED = "startIntentionalWhiteboardEnabled";
    public static final String SHARE_INVISION_WHITEBOARD_ENABLED = "shareInvisionWhiteboardEnabled";
    public static final String SHARE_INVISION_WHITEBOARD_FOR_ANON_ENABLED = "shareInvisionWhiteboardForAnonEnabled";
    public static final String SHARE_INVISION_WHITEBOARD_FOR_FREEMIUM_ENABLED = "shareInvisionWhiteboardForFreemiumEnabled";
    public static final String SHOULD_FETCH_WHITEBOARD_POLICY = "shouldFetchWhiteboardPolicy";
    public static final String SHOULD_FETCH_WHITEBOARD_POLICY_FOR_ANON = "shouldFetchWhiteboardPolicyForAnonUser";
    public static final String SHOULD_FETCH_WHITEBOARD_POLICY_FOR_FREEMIUM = "shouldFetchWhiteboardPolicyForFreemiumUser";
    public static final String BLUEBOARD_QSP_ENABLED = "blueboardQSPEnabled";
    // REGION
    public static final String IS_GOOGLE_PLAY_SERVICE_REGION = "isGooglePlayServiceRegion";
    public static final String IS_GOOGLE_AVAILABILITY_DIALOG_ENABLED = "isGoogleAvailabilityDialogEnabled";
    // ANIMATIONS
    public static final String BOTTOM_BAR_ANIMATIONS_DISABLED = "bottomBarAnimationsDisabled";
    public static final String CHAT_MESSAGE_ANIMATIONS_DISABLED = "chatMessageAnimationsDisabled";
    public static final String CALLING_ANIMATIONS_DISABLED = "callingAnimationsDisabled";
    public static final String CALL_CONTROLS_CONFIGURABLE_DELAY = "callControlsOnScreenTime";
    // Teams Dock device
    public static final String TEAMS_DOCK_MEETING_NOTIFICATIONS_ENABLED = "teamsDockMeetingNotificationsEnabled";
    public static final String TEAMS_DOCK_CALLING_EVENTS_ENABLED = "teamsDockCallingEventsEnabled";
    public static final String TEAMS_DOCK_ENABLED = "teamsDockEnabled";
    public static final String TEAMS_SCAN_DOCKS_STARTUP = "teamsDockScanStartUp";
    public static final String TEAMS_DOCK_DELAY_SCO_ON = "teamsDockDelaySco";
    public static final String TEAMS_DOCK_CORTANA_ENABLED = "teamsDockCortanaEnabled";
    public static final String TEAMS_DOCK_CONTEXT_CHECK_TIMER_ENABLED = "teamsDockContextCheckTimerEnabled";
    // DATACHANNEL
    public static final String ENABLE_DATACHANNEL = "dataChannel";
    // Filter Trace Logs
    public static final String SHOW_TRACE_LOGS_UPTO = "showTraceLogsUpto";
    public static final String LOG_SCENARIO_START_WHITELIST = "logScenarioStartWhitelist";
    // PowerLift
    public static final String ANDROID_POWER_LIFT_ENABLED = "androidPowerLiftEnabled";
    public static final String ANDROID_POWER_LIFT_ENABLED_PRE_LOGIN = "enablePowerLiftPreLogin";

    // Storage cache
    public static final String USER_CACHE_ENABLED = "userCacheEnabled";
    public static final String USER_CACHE_SIZE = "userCacheSize";
    public static final String THREAD_CACHE_ENABLED = "threadCacheEnabled";
    public static final String THREAD_CACHE_SIZE = "threadCacheSize";
    public static final String THREAD_USER_CACHE_ENABLED = "threadUserCacheEnabled";
    public static final String THREAD_USER_CACHE_SIZE = "threadUserCacheSize";
    public static final String THREAD_PROPERTY_ATTRIBUTE_CACHE_ENABLED = "threadPropertyAttributeCacheEnabled";
    public static final String THREAD_PROPERTY_ATTRIBUTE_CACHE_SIZE = "threadPropertyAttributeCacheSize";
    public static final String CHAT_CONVERSATION_CACHE_ENABLED = "chatConversationCacheEnabled";
    public static final String CHAT_CONVERSATION_CACHE_SIZE = "chatConversationCacheSize";
    public static final String CONVERSATION_CACHE_ENABLED = "conversationCacheEnabled";
    public static final String CONVERSATION_CACHE_SIZE = "conversationCacheSize";

    // HOLOGRAPHIC
    public static final String ENABLE_HOLOLENS_INTERACTION = "enableHoloLensInteraction";
    //IP Phone
    public static final String IP_PHONE_MODELS_TO_ENABLE_VIDEO = "IpPhoneModelsToEnableVideo";
    public static final String IP_PHONE_MODELS_TO_DISABLE_SCREEN_SHARE_CONSUMPTION = "IpPhoneModelsToDisableScreenShareConsumption";
    public static final String IP_PHONE_MODELS_TO_DISABLE_WHITE_BOARD_CONSUMPTION = "IpPhoneModelsToDisableWhiteBoardConsumption";
    public static final String IP_PHONE_MODELS_CONFERENCE_DEVICE = "IpPhoneModelsConferenceDevice";
    public static final String ENABLE_IP_PHONE_POLICIES = "enableIpPhonePolicies";
    public static final String COMMON_AREA_PHONE_MODE_ALLOWED = "commonAreaPhoneModeAllowed";
    public static final String HIDE_SIGN_OUT_FOR_SHARED_ACCOUNTS = "hideSignOutForSharedAccounts";
    public static final String HOT_DESKING_ENABLED = "hotDeskingEnabled";
    public static final String HOT_DESKING_DEFAULT_TIMEOUT = "hotDeskingDefaultTimeout";
    public static final String DIRECT_DIALING_911 = "directDialing911";
    public static final String ENABLE_VOICEMAIL_SYNC = "backgroundVoiceMailSync";
    public static final String ENABLE_NEW_CALLS_UX = "enableNewCallsUXForDevices";
    public static final String ENABLE_SIDECAR_ON_IPPHONE = "enableSidecarOnIpPhone";
    public static final String ENABLE_BRANCH_SURVIVABILITY = "enableBranchSurvivability";
    // Realwear
    public static final String OPEN_CHAT_IMAGES_IN_EXTERNAL_APP = "openChatImagesInExternalApp";
    public static final String ENABLE_FLASHLIGHT_REALWEAR = "enableFlashlightRealWear";
    public static final String SHOW_CALL_OR_MEETING_PARTICIPANTS_REALWEAR = "showCallOrMeetingParticipantsRealWear";
    public static final String SHOW_DEVICE_UPDATE_DIALOG_ON_REALWEAR = "showDeviceUpdateDialog";
    public static final String REALWEAR_APP_CENTER_UPDATES_ENABLED = "realwear/appCenterUpdatesEnabled";

    //React Native
    public static final String PLATFORM = "platform";
    public static final String ENABLED = "enabled";
    public static final String DEPLOYMENT_KEY = "deploymentKey";
    public static final String ENABLE_FALLBACK_LOADER = "enableFallbackLoader";
    public static final String RETRY_COUNT_FOR_CODEPUSH_BUNDLE_DOWNLOAD = "retryCountForCodepushBundleDownload";
    public static final String CODE_PUSH_UPDATE_CHECK_BACKOFF_TIME_IN_MINUTES = "codePushUpdateCheckBackoffTimeInMinutes";
    public static final String VALID_MOBILE_MODULES = "validMobileModules";

    public static final String ENABLE_PRE_FETCH_RESOURCE_TOKEN = "enablePreFetchResourceToken";

    // NPS - Floodgate
    public static final String IS_FLOODGATE_ENABLED = "nps/isFloodgateEnabled";
    public static final String NPS_PANEL_ACTION_DAYS_COUNT = "nps/panelActionDaysCount";
    public static final String NPS_UNIQUE_MESSAGE_SENT_COUNT = "nps/uniqueMessageSentCount";
    public static final String IS_NPS_FIVE_POINT_STATIC_SURVEY_ENABLED = "nps/isFloodgateFivePointerEnabled";
    public static final String NPS_AUDIENCE_GROUP = "nps/audienceGroup";
    public static final String NPS_USER_LOGIN_DAYS_COUNT = "nps/userLoginDaysCount";
    // URL Overrides
    public static final String CONVERSATION_SERVICE_PUBLIC_URL = "conversationServicePublicUrl";
    // Tab Extensions
    public static final String TAB_EXTENSION_OPTIONS_ENABLED = "tabExtensionOptionsEnabled";
    public static final String WHITELISTED_APPS_TO_OPEN_INSIDE_TEAMS = "whitelistedAppsToOpenInTeams";
    public static final String CHAT_TAB_EXTENSIONS_ENABLED = "chatTabExtensionsEnabledV2"; // chatTabExtensionsEnabled was stopped to suppress one crash

    // Supported Recording Regions
    public static final String SUPPORTED_RECORDING_REGIONS = "supportedRecordingRegions";

    // Supported odb Recording Regions
    public static final String SUPPORTED_RECORDING_ODB_REGIONS = "supportedRecordingOdbRegions";

    // Invalid forward URLs opened in the WebView, it doesn't cover the URLs
    // that are opened in the WebView in the first time.
    public static final String BLACKLISTED_HOSTS_FROM_WEBVIEW_ACCESS = "blacklistedHostsFromWebViewAccess";
    // Search
    public static final String ENABLE_PEOPLE_SEARCH_V3 = "search/peopleSearchV3";
    public static final String ENABLE_LOCAL_FILE_SEARCH = "enableLocalFileSearch";
    public static final String ENABLE_SERVER_MESSAGE_SEARCH = "search/enableServerMessageSearch";
    public static final String ENABLE_SERVER_PEOPLE_SEARCH = "enableServerPeopleSearch";
    public static final String ENABLE_SERVER_IMPLICIT_SEARCH = "enableServerImplicitSearch";
    public static final String ENABLE_SUBSTARTE_FOR_GUEST_USER = "search/supportsGuestUser";
    public static final String SUBSTRATE_MESSAGE_SEARCH = "enableSubstrateMessageSearch";
    public static final String SUBSTRATE_MESSAGE_SEARCH_PAGE_SIZE = "substrateMessagePageSize";
    public static final String ENABLE_SUBSTRATE_MY_ACTIVITY = "enableSubstrateMyActivity";
    public static final String ENABLE_SUBSTRATE_USER_ACTIVITY = "enableSubstrateUserActivity";
    public static final String ENABLE_SUBSTRATE_CHAT_FILES = "enableSubstrateChatFiles";
    public static final String PAGINATION_MESSAGE_SUBSTRATE_SEARCH = "enablePaginatedMessageSubstrateSearch";
    public static final String RICH_RECENT_SEARCH_SUGGESTION = "search/enableRichRecentSearchSuggestion";
    public static final String ALL_TAB_SEARCH = "search/enableAllTab";
    public static final String CONTEXTUAL_SEARCH = "search/contextualSearch";
    public static final String FILE_SEARCH_PAYLOAD_LENGTH = "search/fileSearchPayloadLength";
    public static final String FILE_SEARCH_WITH_GROUP_ID = "enableFileSearchWithGroupId";
    public static final String FILE_SEARCH_USE_OF_SITE_URL = "search/enableUseOfSiteUrlForFileSearch";
    public static final String FILE_SEARCH_REFINING_QUERIES = "search/fileRefiningQueries";
    public static final String TOP_N_CACHE_REFRESH_PERIOD = "topNCacheRefreshPeriod";
    public static final String TOP_N_CACHE_SIZE = "topNCacheSize";
    public static final String TOP_N_CACHE_ENABLED = "isTopNCacheEnabled";
    public static final String WARMUP_REFRESH_PERIOD = "search/warmUpRefreshPeriod";
    public static final String ENABLE_SUBSTRATE_WARMUP = "search/enableSubstrateWarmup";
    public static final String SDK_APP_CONTACTS_SEARCH_ENABLED = "isSdkAppContactsSearchEnabled";
    public static final String ENABLE_SEARCH_SPELLER = "search/speller";
    public static final String ENABLE_SEARCH_PERSONALIZED_SPELLER = "search/personalizedSpeller";
    public static final String DISABLE_SEARCH_SPELLER_WORD_WHEELING = "search/disableSpellerWordWheelingUX";
    public static final String DISABLE_SEARCH_ANSWER_WORD_WHEELING = "search/disableAnswerWordWheeling";
    public static final String TIMEOUT_DURATION_FOR_ANSWER = "search/timeoutDurationForAnswer";
    public static final String ENABLE_BOOKMARK_ANSWER_V2 = "search/enableBookmarkAnswerV2";
    public static final String ENABLE_CALENDAR_ANSWER_V2 = "search/enableCalendarAnswerV2";
    public static final String BOOKMARK_ANSWER_V2_DEFAULT_COUNT = "search/defaultBookmarkAnswerCount";
    public static final String CALENDAR_ANSWER_EXAPAND_COUNT_THRESHOLD = "search/defaultCalendarAnswerCount";
    public static final String ENABLE_SEARCH_SPELLER_TRIGGER_CONTROL = "search/enableSpellerTriggerControl";
    public static final String ENABLE_MSAI_SEARCH = "search/searchSDKFiles";
    public static final String ENABLE_MSAI_BOOKMARK_ANSWER_SEARCH = "search/bookmarkAnswer";
    public static final String ENABLE_MSAI_CALENDAR_ANSWER_SEARCH = "search/calendarAnswer";
    public static final String ENABLE_MSAI_ACRONYM_ANSWER_SEARCH = "search/acronymAnswer";
    public static final String ENABLE_MSAI_BOOKMARK_ANSWER_TRIGGER_CONTROL = "search/enableBookmarkAnswerTriggerControl";
    public static final String ENABLE_MSAI_CALENDAR_ANSWER_TRIGGER_CONTROL = "search/enableCalendarAnswerTriggerControl";
    public static final String ENABLE_MSAI_ACRONYM_ANSWER_TRIGGER_CONTROL = "search/enableAcronymAnswerTriggerControl";
    public static final String ENABLE_MSAI_RECOURSE_LINK = "search/enableNLRecourseLink";
    public static final String ENABLE_MSAI_RECOURSE_LINK_TRIGGER_CONTROL = "search/enableNLRecourseLinkTriggerControl";
    public static final String ENABLE_MESSAGE_RELEVANCE_BASED_RANKING = "search/enableMessageRelevanceBasedRanking";
    public static final String BASELINE_TELEMETRY_SEARCH = "search/baselineTelemetry";
    public static final String ENABLE_NL_SEARCH = "search/nlsearch";
    public static final String ENABLE_FILE_NL_SEARCH = "search/nlFileSearch";
    public static final String ENABLE_FILE_NL_SEARCH_ALTERATION = "search/nlFileSearchAlteration";
    public static final String DEVICE_CONTACTS_SEARCH = "enableDeviceContactsSearch";
    public static final String ENABLE_SEARCH_CONTACT_NAVIGATE_TO_CHAT = "enableSearchContactNavigateToChat";
    public static final String ENABLE_UNIVERSAL_API_SEARCH = "search/enableUniversalApiSearch";
    public static final String ENABLE_UNIVERSAL_API_USER_SEARCH = "search/enableUniversalApiUserSearch";
    public static final String UNIVERSAL_API_SEARCH_TIMEOUT = "search/universalApiSearchTimeout";
    public static final String ENABLE_SEARCH_FILE_IN_CHAT = "search/fileInChat";
    public static final String ENABLE_PRE_SEARCH_CONTACT_ENPTY_STATE = "enablePreSearchContactSyncEmptyState";
    public static final String ENABLE_SEARCH_QUERY_FORMULATION = "search/enableQueryFormulation";
    public static final String TEXT_SUGGESTIONS_COUNT = "search/textSuggestionsCount";
    public static final String TOP_HIT_SUGGESTIONS_COUNT = "search/topHitSuggestionsCount";
    public static final String SHOW_TEXT_SUGGESTIONS_AT_BOTTOM = "search/showTextSuggestionsAtBottom";
    public static final String QUERY_FORMULATION_DEBOUNCE_TIME_DELAY = "search/queryFormulationDebounceTimeDelay";
    public static final String ENABLE_PROFILE_BUTTON_ON_SEARCH_CONTACTS = "search/enableProfileButtonOnContacts";
    public static final String MAX_COMPANY_CONTACT_COUNT_FOR_ALL_TAB = "search/maxCompanyContactCountForAllTab";
    public static final String DISABLE_SEARCH_BASELINE_TELEMETRY_LOG_TO_ARIA = "search/disableSearchBaselineTelemetryLogToAria";
    public static final String ENABLE_SEARCH_BASELINE_TELEMETRY_LOG_TO_EVENT_API = "search/enableSearchBaselineTelemetryLogToEventAPI";
    public static final String ENABLE_PRE_SEARCH_SUGGESTED_CONTACTS = "enablePreSearchSuggestedContacts";
    //RNL
    public static final String IS_REVERSE_NUMBER_LOOKUP_ENABLED = "contacts/reverseNumberLookUpEnabled";
    public static final String RNL_SYNC_INTERVAL = "contacts/syncInterval";
    public static final String VOICE_MAIL_RNL_ENABLED = "contacts/voiceMailRNLEnabled";
    //People
    public static final String PEOPLE_SYNC_INTERVAL_IN_HRS = "people/peopleAppSyncInterval";
    public static final String PEOPLE_APP_ENABLED = "people/isPeopleAppEnabled";
    public static final String PEOPLE_RN_APP_ENABLED = "people/isPeopleRNAppEnabled";
    public static final String PEOPLE_PICKER_FALLBACK_TIME = "people/peoplePickerFallbackTime";
    public static final String PEOPLE_FRE_ENABLED = "people/isPeopleFreEnabled";
    public static final String ENABLE_PROXY_SMB_LICENSE = "people/enableProxySMBLicense";
    //Images
    public static final String IMAGE_OPTIMIZATION_ENABLED = "imageOptimizationEnabled";
    public static final String STARTUP_PROFILING_ENABLED = "startupProfilingEnabled";
    // Developer preview
    public static final String ALLOW_DEVELOPER_PREVIEW = "allowDeveloperPreview";

    // Performance optimizations related flags
    public static final String TEAM_ORDER_FETCH_FREQUENCY = "teamOrderFetchFrequency";

    // Config flag for bots not allowed to join a call/meeting as a regular participant
    public static final String BOTS_NOT_ALLOWED_IN_CALL = "blacklistedBotsInCall";

    // People picker related flags
    public static final String NEW_PEOPLE_PICKER_ENABLED = "newPeoplePickerEnabled";
    public static final String PEOPLE_PICKER_TOP_USERS_COUNT = "peoplePickerTopUsersCount";
    public static final String PEOPLE_PICKER_BOTS_COUNT = "peoplePickerBotsCount";
    public static final String PEOPLE_PICKER_NAMED_GROUP_CHATS_COUNT = "peoplePickerNamedGroupChatsCount";
    public static final String PEOPLE_PICKER_UNNAMED_GROUP_CHATS_COUNT = "peoplePickerUnnamedGroupChatsCount";
    public static final String PEOPLE_PICKER_CHANNELS_COUNT = "peoplePickerChannelsCount";
    public static final String CONFIG_BASED_PEOPLE_PICKER = "configBasedPeoplePicker";
    public static final String PEOPLE_PICKER_SECTION_HEADERS = "peoplePickerSectionHeaders";
    public static final String PEOPLE_PICKER_DEVICE_CONTACTS = "peoplePickerDeviceContacts";
    public static final String PEOPLE_PICKER_SCD_MATCH = "peoplePickerSCDMatch";
    public static final String PEOPLE_PICKER_LIST_COLLAPSIBLE = "peoplePickerListCollapsible";
    public static final String HIDE_PEOPLE_PICKER_DEVICE_CONTACTS_HEADER = "hidePeoplePickerDeviceContactsHeader";

    // Pending Members
    public static final String PENDING_MEMBERS_ENABLED = "pendingMembersEnabled";
    public static final String USE_COUNT_OF_PENDING_MEMBERS_API_ENABLED = "useCountOfPendingMembersApiEnabled";
    // Live location
    public static final String LIVE_LOCATION_ENABLED = "Location/enableLiveLocation";
    public static final String LIVE_LOCATION_URL_OVERRIDE = "Location/serviceUrlOverride";
    public static final String LIVE_LOCATION_BEACON_TELEMETRY_ENABLED = "Location/enableBeaconTelemetry";
    public static final String LIVE_LOCATION_VERBOSE_TELEMETRY_ENABLED = "Location/enableVerboseTelemetry";
    public static final String LIVE_LOCATION_PASSIVE_TRACKING_ENABLED = "Location/enablePassiveTracking";
    public static final String LIVE_LOCATION_INDEFINITE_SHARING_ENABLED = "Location/enableIndefiniteSharing";
    public static final String LIVE_LOCATION_BATTERY_SETTINGS = "Location/batterySettings";
    public static final String LIVE_LOCATION_TELEMETRY_SAMPLING = "Location/telemetrySampling";
    public static final String LIVE_LOCATION_DURATION_OPTIONS = "Location/durationOptions";
    public static final String LIVE_LOCATION_SHARED_PLACES_ENABLED = "Location/enableSharedPlaces";
    public static final String LIVE_LOCATION_NEARBY_PLACES_ENABLED = "Location/enableNearbyPlaces";
    public static final String LIVE_LOCATION_ACTIVE_SESSION_BANNER_FREQUENCY = "Location/activeSessionBannerFrequency";
    public static final String LIVE_LOCATION_ACTIVE_SESSION_SYNC_FREQUENCY = "Location/activeSessionSyncFrequency";
    public static final String LIVE_LOCATION_SHARED_PLACES_GROUP_LIMIT = "Location/sharedPlacesGroupLimit";
    public static final String LIVE_LOCATION_STATIC_LOCATION_CHICLET_ENABLED = "Location/staticLocationCustomChiclet";
    public static final String LIVE_LOCATION_GEOFENCE_FROM_PLACES_ENABLED = "Location/enableGeofenceFromPlaces";
    public static final String LIVE_LOCATION_ACTIVITY_FEED_ENABLED = "Location/enableActivityFeed";
    public static final String LIVE_LOCATION_GEOFENCE_TRIGGER_SYNC_DELAY = "Location/geofenceTriggerSyncDelay";
    public static final String LIVE_LOCATION_APP_ENABLED = "Location/enableApp";

    // OCV Feedback
    public static final String OCV_FEEDBACK_ENABLED = "Feedback/enableOcvFeedback";
    public static final String OCV_SNS_ENABLED = "Feedback/enableOcvSns";
    public static final String INTERNAL_LIFE_FEEDBACK_LINK = "Feedback/internalLifeFeedbackUrl";
    public static final String HELP_ARTICLES_LINK = "Feedback/helpArticlesUrl";
    public static final String RATE_US_ENABLED = "Feedback/rateUs";
    public static final String REPORT_ISSUE_ENABLED = "Feedback/reportIssue";

    // Semantic Object
    public static final String FLUID_CONSUMPTION_ENABLED = "semo/fluidConsumptionEnabled";
    public static final String FLUID_COMPOSE_ENABLED_ITEM = "semo/composeEnabledComponents";
    public static final String FLUID_SIZE_LIMITATION_FEATURE = "semo/sizeLimitEnabled";
    public static final String FLUID_SIZE_LIMIT = "semo/sizeLimitValueBytes";
    public static final String FLUID_LOAD_TIME_OUT_VALUE = "semo/fluidLoadTimeOutValueInMs";
    public static final String FLUID_AUDIENCE = "semo/fluidAudience";
    public static final String FLUID_EDU = "semo/eduEnabled";
    public static final String FLUID_FORCE_WRITE_PERMISSION_ENABLED = "semo/fluidForcedWritePermissionEnabled";
    public static final String FLUID_BLOCK_MAX_HEIGHT_RATIO = "semo/fluidMessageMaxHeightRatio";

    // New Compose Bar
    public static final String NEW_COMPOSE_ENABLED = "newComposeEnabled";
    public static final String NEW_COMPOSE_TOGGLE_ENABLED = "newComposeShowToggle";

    // Gallery
    public static final String GALLERY_TAB_IN_CHAT_ENABLED = "galleryTabInChatEnabled";
    public static final String INLINE_IMAGE_VIEW_IN_CHAT_ENABLED = "inlineImageViewInChatEnabled";
    public static final String GALLERY_UPLOAD_IMAGES = "galleryUploadImagesEnabled";
    public static final String NEW_IMAGE_PREVIEW_ENABLED = "newImagePreviewEnabled";
    public static final String SELECT_MULTIPLE_IMAGE_ENABLED = "selectMultipleImageEnabled";
    public static final String MAX_GALLERY_IMAGES_SHARE_COUNT = "maxGalleryImagesShareCount";
    public static final String NEW_IMAGE_RENDER_IN_CHAT_ENABLED = "newImageRenderInChatEnabled";

    // Shared Links
    public static final String ENABLE_SHARED_LINKS = "sharedLinkInDashboardEnabled";

    // Stickers/meme
    public static final String ENABLE_STICKERS = "stickers/enableStickers";
    public static final String ENABLE_CUSTOM_MEMES = "stickers/enableCustomMemes";
    public static final String ENABLE_CUSTOM_MEMES_IMAGE_PASTE = "stickers/enableCustomMemesImagePaste";

    // Tasks
    public static final String TASKS_TAB_IN_DASHBOARD_ENABLED = "tasksTabInDashboardEnabled";
    public static final String TASKS_IN_CHAT_ENABLED = "tasksInChatEnabled";

    // TFL Sync Missing Consumer Group
    public static final String SYNC_MISSING_CONSUNMER_GROUP_ENABLED = "consumerGroup/syncMissingCgEnabled";

    // AddressBook sync
    public static final String ADDRESS_BOOK_SYNC_ENABLED = "enableABContactsSync";
    public static final String ADDRESS_BOOK_SYNC_BATCH_COUNT = "addressBookUploadBatchCount";
    public static final String ADDRESS_BOOK_SYNC_DISPLAY_NAME = "enableDisplayNameOveride";
    public static final String ADDRESS_BOOK_SYNC_DEVICE_CONTACT_CACHE_ENABLED = "enableDeviceContactHash";
    public static final String ADDRESS_BOOK_SYNC_NEGATIVE_CACHE_SIZE = "deviceContactNegativeCacheSize";
    public static final String ADDRESS_BOOK_SYNC_DEVICE_CONTACT_CACHE_SIZE = "deviceContactHashCacheSize";
    public static final String ADDRESS_BOOK_SYNC_MRI_DISPLAY_NAME_CACHE_SIZE = "mriDisplayNameCacheSize";
    public static final String ADDRESS_BOOK_SYNC_MRI_PHONE_EMAIL_CACHE_SIZE = "mriPhoneEmailCacheSize";
    public static final String DEVICE_CONTACT_TAG = "deviceContactTag";

    //TFL ContactSync Dialog/contact sync in empty state
    public static final String CONTACT_SYNC_DIALOG_AND_VIEW_ENABLED = "enableContactSyncDialogAndView";
    public static final String CHAT_CREATION_EMPTY_VIEW = "chatCreationEmptyView";

    // Privacy
    public static final String PRIVACY_ENABLED = "privacy/enableSettingsPrivacySection";

    // Invite Join link
    public static final String ENABLE_JOIN_LINK_INVITE = "enableJoinLinkInvite";

    // Activation Banner
    public static final String ENABLE_TFL_ACTIVATION_BANNER = "enableTflUpsell";
    public static final String TFL_UPSELL_PILLAR_PROP = "tflUpsellPillarProp";
    public static final String TFL_TUTORING_UPSELL = "tflTutoringUpsell";
    public static final String ENABLE_TFL_BANNER_COLOR = "enableTFLBannerColor";

    // Suggested Contact
    public static final String ENABLE_SUGGESTED_CONTACT = "enableSuggestedContact";

    // 2-way SMS
    public static final String ENABLE_GROUP_CHAT_TWO_WAY_SMS = "enableGroupChatTwoWaySMS";
    public static final String TWO_WAY_SMS_PHONE_ENABLED_PREFIXES_CONFIG = "chat/twoWaySmsEnabledPrefixes";
    public static final String TWO_WAY_SMS_PHONE_EXCLUDED_PREFIXES_CONFIG = "chat/twoWaySmsExcludedPrefixes";
    public static final String ENABLE_GROUP_CHAT_CREATE_SMS_USERS = "enableGroupChatCreateSMSUsers";

    // Background activity detection, for better delivery notification
    public static final String BACKGROUND_ACTIVITY_DETECTION = "enableBackgroundActivityDetection";
    public static final String BACKGROUND_INACTIVE_COUNTER_THRESHOLD = "backgroundInactiveCounterThreshold";
    public static final String BACKGROUND_ACTIVITY_DETECTION_SHOW_BANNER = "enableBackgroundActivityDetectionShowBanner";
    public static final String BACKGROUND_NOTIFICATION_SYNC = "enableBackgroundNotificationSync";
    public static final String NOTIFICATION_MESSAGES_PROCESSED_LIMIT_SIZE = "notificationMessagesProcessedLimitSize";

    // Enable notification delivery option to Always by default for FRE users.
    public static final String NOTIFICATION_ALWAYS_RECEIVE_FOR_FRE_USERS = "enableNotificaionAlwaysReceiveForFreUsers";

    public static final String TFL_NEW_SYNC_CONTACTS_FRE_ENABLED = "tflNewContactsSyncInFreEnabled";

    public static final String TFL_GIPHY_PICKER_COACHMARK_ENABLED = "tflGiphyPickerCoachMarkEnabled";

    public static final String BOOKMARKED_MESSAGES_ENABLED = "chat/savedMessagesTabEnabled";

    public static final String TFL_SHOW_RED_DOT_ON_DASHBOARD_TAB_ENABLED = "tflShowRedDotOnDashboardTabEnabled";

    public static final String TFL_SHOW_BOLD_INVITE_FRIENDS_ENABLED = "tflShowBoldInviteFriendsEnabled";

    public static final String DO_NOT_SHOW_AGAIN_CONTACTS_ENABLED = "doNotShowAgainContactsInFreEnabled";

    // Enalbe Gap detection based notification sync
    public static final String GAP_DETECTION_BASED_NOTIFICATION_SYNC = "gapDetectionBasedNotificationSync";
    public static final String GAP_DETECTION_BASED_NOTIFICATION_SYNC_THRESHOLD = "gapDetectionBasedNotificationSyncThreshold";

    // Invite Group Invite link
    public static final String ENABLE_GROUP_LINK_INVITE = "threadJoinLinkEnabled";

    // Contacts related flags
    public static final String BLOCK_CONTACT_ENABLED = "enableBlockContact";

    public static final String ENABLE_SERVICE_INVITE_STRINGS = "enableServiceInviteStrings";
    // Translation related flags
    public static final String ENABLE_AUTOMATIC_CHAT_TRANSLATION_ALWAYS = "enableAutomaticChatTranslationAlways";
    public static final String ENABLE_AUTOMATIC_CHAT_TRANSLATION_SUGGESTION = "enableAutomaticChatTranslationSuggestion";
    public static final String ENABLE_AUTOMATIC_CHANNEL_TRANSLATION_ALWAYS = "enableAutomaticChannelTranslationAlways";
    public static final String ENABLE_AUTOMATIC_CHANNEL_TRANSLATION_SUGGESTION = "enableAutomaticChannelTranslationSuggestion";
    public static final String ENABLE_ONDEMAND_CHAT_TRANSLATION = "enableOnDemandChatTranslation";
    public static final String ENABLE_ONDEMAND_CHANNEL_TRANSLATION = "enableOnDemandChannelTranslation";

    // SMB NonVoice vertical FRE Flags
    public static final String SMB_NONVOICE_FRE_ENABLED = "smbNonVoiceFreEnabled";

    // SMB (Small Medium Business) vertical FRE flags
    public static final String SMB_BUSINESS_VOICE_FRE_ENABLED = "smbBusinessVoiceFreEnabled";
    public static final String SMB_BUSINESS_VOICE_WHATS_NEW_ENABLED = "smbBusinessVoiceWhatsNewEnabled";

    public static final String CHANNEL_FOLLOW_ICON_UPDATE_ENABLED = "channelFollowIconUpdateEnabled";
    public static final String CHANNEL_NOTIFICATION_SETTING_PROMPT_ENABLED = "showChannelNotificationSettingPrompt";
    public static final String CHANNEL_SHOW_NOTIFICATION_ICON_ENABLED = "showChannelNotificationIcon";
    public static final String CHANNEL_NOTIFICATION_DLG_ENABLED = "channelNotificationDlgUpdateEnabled";

    // Conversation dashboard flags
    public static final String IS_GROUP_CALENDAR_ENABLED = "isGroupCalendarEnabled";
    public static final String IS_CONSUMER_ONE_ON_ONE_CALENDAR_ENABLED = "consumerCalendarOneOnOneEnabled";

    // TFL add MSA alias
    public static final String ADD_MSA_ALIAS = "addMSAAliasEnabled";

    // UPDATE MSA AVATAR
    public static final String UPDATE_MSA_AVATAR = "enableUpdateAvatar";

    // DELETE MSA AVATAR
    public static final String DELETE_MSA_AVATAR = "enableDeleteAvatar";

    // ME Profile flags
    public static final String ENABLE_EDIT_DISPLAYNAME = "enableEditDisplayName";

    // TFL show presence UI
    public static final String ENABLE_PRESENCE_UI = "enablePresenceUI";

    // TFL Unified presence feature
    public static final String TFL_PRESENCE_ENABLED = "presence/TFLPresenceEnabled";

    // sending TFL as context in push notification
    public static final String ENABLE_TFL_CONTEXT = "notification/enableTFLContext";

    // Activity feed coming soon
    public static final String ACTIVITY_FEED_ENABLED = "enableActivityFeed";
    public static final String ACTIVITY_FEED_DELETE_ITEM_ENABLED = "enableActivityFeedDeleteItem";
    public static final String ACTIVITY_FEED_SHOW_MORE = "activityEnableShowMoreFeeds";
    public static final String ACTIVITY_FEED_RECENT_MAX_ITEMS = "maxItemsInRecentActivityList";
    public static final String ACTIVITY_FEED_RECENT_MAX_TIME_SECOND = "maxTimeSecondInRecentActivityList";

    // TFL alias-email/phone visibility and searchability controls
    public static final String ENABLE_ALIAS_CONTROLS = "aliasControlsEnabled";

    // Override the tenant ID for all threads with the user's tenant ID (workaround for chat service not providing tenant ID)
    // Used for TFL only
    public static final String OVERRIDE_THREAD_TENANTID = "overrideThreadTenantId";

    public static final String ADD_CONTACT_ON_MESSAGE_SENT = "enableAddContactOnMessageSent";

    // Custom logo feature
    public static final String CUSTOM_LOGO = "meetingBrandingCustomizationsEnabled ";

    // Fetch Custom logo from AMS feature
    public static final String CUSTOM_AMS_LOGO = "meetingBrandingCustomizationsFromAMSEnabled";

    // PSTN masking feature
    public static final String PSTN_MASKING = "pstnMaskingEnabled";
    public static final String PSTN_MASKING_REGEX = "pstnMaskingRegex";

    public static final String HIDE_REJOIN_ENABLED = "hideRejoinEnabled";

    // TFL mini profiles
    public static final String ENABLE_MINI_PROFILES = "enableMiniProfilesSync";

    // Invite-free experience with accept-block banner
    public static final String ENABLE_INVITE_FREE = "enableInviteFree";
    public static final String SHOULD_BLOCK_UI_ON_ACCEPTING_CHAT = "shouldBlockUIOnAcceptingChat";

    // Files Drag and Drop Feature
    public static final String ENABLE_DRAG_AND_DROP = "enableDragAndDrop";

    //Master-Detail mode
    public static final String DUAL_MODE_ENABLED_DEVICE_CLASSES = "dualModeEnabledDeviceClasses";

    // Drawer Animation
    public static final String ENABLE_DRAWER_ANIMATION = "enableDrawerAnimation";

    // Empty group
    public static final String ENABLE_EMPTY_GROUP_CREATION = "enableEmptyGroupCreation";

    // Chat List Empty State

    public static final String ENABLE_CHAT_LIST_EMPTY_STATE = "enableChatListEmptyState";

    public static final String ENABLE_CHAT_LIST_EMPTY_STATE_CREATE_GROUP = "enableChatListEmptyStateCreateGroup";

    public static final String ENABLE_CHAT_LIST_EMPTY_STATE_INVITE_FRIEND = "enableChatListEmptyStateInviteFriend";

    public static final String ENABLE_CHAT_LIST_EMPTY_STATE_PLAN_GROUP = "enableChatListEmptyStatePlanGroup";

    public static final String ENABLE_CHAT_LIST_EMPTY_STATE_SHARE_PHOTO = "enableChatListEmptyStateSharePhoto";

    public static final String ENABLE_CHAT_LIST_EMPTY_STATE_SHARE_TASK = "enableChatListEmptyStateShareTask";

    public static final String ENABLE_CHAT_LIST_EMPTY_STATE_SHARE_LOCATION = "enableChatListEmptyStateShareLocation";

    public static final String ENABLE_CHAT_LIST_EMPTY_STATE_SYNC_CONTACT = "enableChatListEmptyStateSyncContact";

    // Group Chat New Chat Text Color
    public static final String ENABLE_NEW_GROUP_CHAT_TEXT_COLOR = "enableNewGroupChatTextColor";

    // Maximum number of messages a user can send to someone who they're contacting for the first time without a reply
    public static final String MAX_QUARANTINE_COUNTER = "maxQuarantineCounter";

    // TFL Chat Report Abuse Feature
    public static final String ENABLE_CHAT_REPORT_ABUSE = "customerAdHocAbuseReport";

    /**
     * Environment to send to Auth Service for consumer (TFL) users
     */
    public static final String CONSUMER_AUTHSVC_ENVIRONMENT = "consumerRegionGmtsEnv";

    // EXPO related flags
    public static final String EXPO_ENABLED = "enableExpo";

    //Content only mode
    public static final String CONTENT_ONLY_MODE_ENABLED = "enableContentOnlyMode";

    //ExperimentationId flags
    public static final String SHOULD_LOG_EXPERIMENTATION_IDS = "shouldLogExperimentIds";

    // Sfc interop message
    public static final String SFC_INTEROP_ENABLED = "interop/SfCInteropEnabled";

    // Off-network invite
    public static final String OFF_NETWORK_INVITE = "offNetworkInviteEnabled";

    // Off-network single user
    public static final String OFF_NETWORK_SINGLE_USER_INVITE = "offNetworkSingleUserInviteEnabled";

    // Off-network invite requests body supports message id vs message content
    public static final String OFF_NETWORK_INVITE_REQUEST_SUPPORTS_MESSAGE_ID = "offNetworkInviteRequestSupportsMessageId";

    // Group Avatar
    public static final String EDIT_GROUP_AVATAR = "editGroupAvatarEnabled";

    // Chat Service Personal Streams
    public static final String USE_CHATSERVICE_PERSONAL_STREAMS = "usePersonalStreams";

    // Visio Tab flag
    public static final String VISIO_TAB_ENABLED = "visioTabEnabled";

    // Enable On-Behalf-Of Attribution in Bot Adaptive Cards
    public static final String ENABLE_ON_BEHALF_OF_USER_ATTRIBUTION = "enableOnBehalfOfUserAttribution";

    // Enable Action.Execute for AdaptiveCards
    public static final String ENABLE_ACTION_EXECUTE_FOR_ADAPTIVE_CARDS = "platform/enableActionExecuteForAdaptiveCards";

    // Enable Universal action for AdaptiveCards
    public static final String ENABLE_UNIVERSAL_ACTION_FOR_ADAPTIVE_CARDS = "platform/enableUniversalActionForAdaptiveCards";

    //Enable Refresh in Adaptive cards through Action.Execute
    public static final String ENABLE_REFRESH_FOR_ADAPTIVE_CARDS = "platform/enableRefreshForAdaptiveCards";

    //Enable Long Press Manual Refresh context Menu UX in Adaptive Cards
    public static final String ENABLE_CONTEXT_MENU_FOR_ADAPTIVE_CARDS = "platform/enableContextMenuForAdaptiveCards";

    //Enable compliance Data to be incorporated in the action Invoke Payload
    public static final String ENABLE_CARD_ACTION_INVOKE_COMPLIANCE_DATA = "platform/enableCardActionInvokeComplianceData";

    // Enable Scroll Optimizations For Refresh
    public static final String ENABLE_SCROLL_OPTIMIZATIONS_FOR_ADAPTIVE_CARD_REFRESH = "platform/enableScrollOptimizationsForAdaptiveCardRefresh";

    // Specifies the size(i.e. no. of rows) to which the Adaptive Card Cache must be limited
    public static final String ADAPTIVE_CARD_CACHE_SIZE_LIMIT = "platform/adaptiveCardCacheSizeLimit";

    // Specifies the time in seconds after which the Adaptive Card Cache must be cleaned up
    public static final String ADAPTIVE_CARD_CACHE_PRUNING_FREQUENCY = "platform/adaptiveCardCachePruningFrequency";

    //Specifies the time in seconds after which the refreshed Adaptive Card cache expires
    public static final String ADAPTIVE_CARD_CACHE_ITEM_EXPIRY_DURATION = "platform/adaptiveCardCacheItemExpiryDuration";

    // Specifies whether to enable the userIds list should be used to restrict auto refresh requests
    public static final String ENABLE_USER_IDS_LIST_FILTERING_FOR_ADAPTIVE_CARD_REFRESH = "platform/enableUserIdsListFilteringForAdaptiveCardRefresh";

    // Specifies a list of Bot Ids that are allowed to invoke Action.Execute
    public static final String BOT_IDS_ALLOWED_TO_INVOKE_ACTION_EXECUTE = "platform/botIdsAllowedToInvokeActionExecute";

    // Specifies a list of Bot Ids that are allowed to invoke automatic refresh
    public static final String BOT_IDS_ALLOWED_TO_INVOKE_AUTOMATIC_REFRESH = "platform/botIdsAllowedToInvokeAutomaticRefresh";

    // show meeting chiclet card in group chat
    public static final String SHOW_MEETING_CHICLET_IN_GROUP_CHAT = "showMeetingChicletInGroupChat";

    // Show Teams tab in TFL
    public static final String SHOW_TEAMS_TAB = "teamsTabEnabled";

    // Meeting join by code feature
    public static final String MEETING_JOIN_BY_CODE = "meetingJoinByCode";

    // Contact groups
    public static final String CONTACT_GROUPS_FETCH_FREQUENCY = "contactGroupsFetchFrequency";

    // Show tenant switching toast
    public static final String TENANT_SWITCH_TOAST_ENABLED = "accountAwareToastEnabled";

    public static final String BACKGROUND_THREAD_POOL_ENABLED = "customBGThreadPoolEnabled";

    public static final String GROUP_BIFURCATION_ENABLED = "shouldShowCreateNewGroupChatEntryPoint";

    public static final String GROUP_CREATE_MULTI_SELECT_ENABLED = "groupCreateMultiSelectEnabled";

    public static final String NEW_CHAT_MESSAGE_SUGGESTION_ENABLED = "newChatMessageSuggestionEnabled";

    public static final String NEW_GROUP_CHAT_PERSONALIZATION_CARD_ENABLED = "newGroupChatPersonalizationCardEnabled";

    public static final String NEW_ONE_TO_ONE_CHAT_PERSONALIZATION_CARD_ENABLED = "newOneToOneChatPersonalizationCardEnabled";

    public static final String REMOVE_FILE_FROM_RECENT_ENABLED = "removeFileFromRecentEnabled";

    public static final String ACCOUNT_PLACEHOLDER_ICONS_ENABLED = "accountPlaceholderIconsEnabled";

    // SCD lookup.
    public static final String ON_NETWORK_SCD_LOOKUP = "onNetworkScdLookupEnabled";

    public static final String ENABLE_TEAMS_PLAYGROUND_THEME = "teamsThemesPlayground";
    public static final String SHOULD_USE_PEOPLE_SEARCH_V2 = "shouldUsePeopleSearchV2";

    // Call ringtones
    public static final String CALL_RINGTONES_OPTIONS = "allowCallRingtoneOptions";

    public static final String LIVE_CAPTIONS_UNMIXED_BOT_PROTOCOL = "liveCaptionsUnmixedBotProtocol";

    // Middle-Tier Graph S2S.
    public static final String MIDDLETIER_GRAPH_S2S_ENABLED = "middletierGraphS2SEnabled";

    // Middle-Tier M365 S2S.
    public static final String MIDDLETIER_CALENDAR_AND_CONSUMERGROUP_S2S_ENABLED = "middletierM365S2SEnabled";

    // Middle-Tier Profile S2S
    public static final String MIDDLETIER_PROFILE_S2S_ENABLED = "middletierProfileS2SEnabled";

    // Group avatar control message.
    public static final String GROUP_AVATAR_CONTROL_MESSAGE = "groupAvatarControlMessageEnabled";

    // Meet now
    public static final String MEET_NOW_ENABLE = "meetNowEnable";
    public static final String MEET_NOW_PREJOIN_ENABLED = "meetNowPrejoinEnabled";
    public static final String MEET_NOW_FROM_CHAT_ENABLED = "meetNowFromChatEnabled";
    public static final String MEET_NOW_FROM_MEETINGS_ENABLED = "meetNowFromMeetingsEnabled";
    public static final String MEET_NOW_FROM_MEETINGS_ENABLE_FREEMIUM = "meetNowFromMeetingsEnableFreemium";
    public static final String MEET_NOW_FROM_CHANNEL_ENABLED = "meetNowFromChannelEnabled";
    public static final String MEET_NOW_FROM_CHANNEL_ENABLE_FREEMIUM = "meetNowFromChannelEnableFreemium";
    public static final String MEET_NOW_FROM_TAB_ENABLED = "meetNowFromTabEnabled";
    public static final String MEET_NOW_IN_SHARED_CHANNEL_ENABLED = "meetNowInSharedChannelsEnabled";
    public static final String MEET_NOW_EXTERNAL_USER_IN_CHANNEL_ENABLED = "meetNowExternalUserInChannelEnabled";
    public static final String MEET_NOW_PREJOIN_VIDEO_ON_DEFAULT_ENABLE_FREEMIUM = "meetNowPrejoinVideoOnDefaultEnableFreemium";
    public static final String MEET_NOW_PREJOIN_MIC_ON_DEFAULT_ENABLE_FREEMIUM = "meetNowPrejoinMicOnDefaultEnableFreemium";
    public static final String MEET_NOW_EASY_SHARE_ENABLED = "meetNowEasyShareEnabled";
    public static final String MEET_NOW_EASY_SHARE_ENABLE_FREEMIUM = "meetNowEasyShareEnableFreemium";
    public static final String MEET_NOW_ROSTER_SHARE_INVITE_ENABLED = "meetNowRosterShareInviteEnabled";
    public static final String MEET_NOW_ROSTER_SHARE_INVITE_ENABLE_FREEMIUM = "meetNowRosterShareInviteEnableFreemium";
    public static final String MEET_NOW_ROSTER_COPY_LINK_ENABLED = "meetNowRosterCopyLinkEnabled";
    public static final String MEET_NOW_ROSTER_COPY_LINK_ENABLE_FREEMIUM = "meetNowRosterCopyLinkEnableFreemium";
    public static final String MEET_NOW_SEND_INITIAL_MESSAGE_ENABLED = "meetNowSendInitialMessageEnabled";
    public static final String MEET_NOW_MEETING_TYPE_ENABLED = "meetNowMeetingTypeEnabled";
    public static final String MEET_NOW_SCHEDULED_ENABLED = "meetNowScheduledEnabled";
    public static final String MEET_NOW_GROUP_SCHEDULED_ENABLED = "meetNowGroupScheduledEnabled";
    public static final String MEETING_ACCOUNT_PICKER_PAGE_ENABLED = "meetingAccountPickerPageEnabled";
    public static final String MEET_NOW_WHATS_NEW_DIALOG_ENABLED = "meetNowWhatsNewDialogEnabled";
    public static final String MEET_NOW_ENABLE_TFL_OVERRIDE = "overrideTFLMeetNowEnable";

    // Android Auto
    public static final String ANDROID_AUTO_MESSAGING = "androidAutoMessagingEnabled";
    public static final String ANDROID_AUTO_AVATAR = "androidAutoAvatarEnabled";

    // SCD ping interval
    public static final String SCD_PING_INTERVAL = "scdPingIntervalInDays";

    // Large Meeting
    public static final String LARGE_MEETING_CAPACITY = "largeMeetingCapacity";

    // Substrate scope enabled for calendar incremental sync
    public static final String SUBSTRATE_SCOPE_ENABLED_FOR_CALENDAR = "substrateScopeForConsumerCalendarEnabled";

    //auto casting on Norden and devices
    public static final String BYOMAUTOACCEPT_ENABLED = "BYOMAutoAcceptEnabled";

    public static final String SEARCH_DEBOUNCE_TIME_DELAY = "search/searchDebounceTimeDelay";
    public static final String SEARCH_MIN_QUERY_LENGTH = "search/searchMinQueryLength";
    public static final String IMAGE_CACHE_DAYS = "people/imageCacheDays";

    // Enable dashboard v2 feature phase 1
    public static final String DASHBOARD_V2 = "isChatDashboardTabEnabled";

    // Enable Dashboard in chat
    public static final String CHAT_DASHBOARD_ENABLED = "chatDashboardEnabled";

    // Enable Dashboard preheat in chat
    public static final String CHAT_DASHBOARD_PREHEAT_ENABLED = "isChatDashboardPreheatEnabled";

    //Enable EDU Supervised Chats
    public static final String SUPERVISED_CHAT_ENABLED = "isSupervisedChatEnabled";

    // Norden auto exit meeting
    public static final String ENABLE_MEETING_AUTO_EXIT_ON_NORDEN = "isMeetingAutoExitEnabledOnNorden";

    public static final String CREATE_REMINDER_FROM_MESSAGE_ENABLED = "createReminderFromMessageEnabled";

    // Image
    public static final String AMS_PERMANENT_FAILURE_CODES = "amsPermanentFailureCodes";
    public static final String ONE_STEP_AMS_UPLOAD_ENABLED = "oneStepAMSUploadEnabled";
    public static final String EDIT_IMAGE_ENABLED = "editImageEnabled";
    public static final String EDIT_IMAGE_ENABLED_FOR_CHANNEL = "editImageChannelEnabled";
    public static final String FORWARD_IMAGE_ENABLED = "forwardImageEnabled";

    // Should check searchability stamping outside FRE
    public static final String ENABLE_STAMPING_OUTSIDE_ONBOARDING = "enableStampingOutsideOnboarding";

    // Background Sync Service
    public static final String BACKGROUND_SYNC_SERVICE_ENABLED = "bgSyncServiceEnabled";
    public static final String BACKGROUND_SYNC_SERVICE_RUN_INTERVAL_MINS = "bgSyncServiceTimeIntervalMins";

    // Priority based Sync Service
    public static final String OPTIMIZE_SYNC_SERVICE_FOR_BATTERY = "optimizeSyncServiceForBattery";
    public static final String OPTIMIZE_SYNC_SERVICE_FOR_NETWORK = "optimizeSyncServiceForNetwork";
    public static final String SLA_FOR_SYNC_QUEUE_HOURS = "slaForSyncQueueHrs";

    // Consumer GetTenants fallback
    public static final String ENABLE_CONSUMER_GET_TENANTS_FALLBACK = "enableConsumerGetTenantsFallback";

    // Global Sign In Sign Out feature
    public static final String GLOBAL_SIGN_IN_OUT = "isGlobalSignInOutEnabled";

    // Global Sign In Sign Out feature: whether or not flag secure is enabled
    public static final String GLOBAL_SIGN_IN_OUT_FLAG_SECURE = "isGlobalSignInOutFlagSecureEnabled";

    // Norden full screen mode
    public static final String ENABLE_CONTENT_SHARING_FULL_SCREEN_MODE = "enableContentSharingFullScreenMode";

    //IpPhone default call view options
    public static final String DEFAULT_CALL_VIEW_OPTION_ENABLED = "enableDefaultCallViewOption";

    // TFL Optional Data
    public static final String ENABLE_TFL_OPTIONAL_TELEMETRY = "enableTflOptionalTelemetry";

    // TFL Download content toggle
    public static final String ENABLE_DOWNLOAD_CONTENT_TOGGLE = "enableDownloadContentToggle";

    // TFL enable gif toggle
    public static final String ENABLE_GIF_TOGGLE = "enableGiphyToggle";

    // Norden dual screen support
    public static final String ENABLE_NORDEN_DUAL_SCREEN_SUPPORT = "enableDualScreenSupportOnNorden";

    // Norden lock screen support
    public static final String ENABLE_NORDEN_LOCK_SCREEN = "enableNordenLockScreen";

    // Internal testing support
    public static final String ENABLE_NORDEN_LOCK_SCREEN_INTERNAL_TEST = "enableNordenLockScreenInternalTest";

    // Kingston never lock timeout setting option
    public static final String ENABLE_NEVER_LOCK_TIME_OUT_SETTING_OPTION_ON_KINGSTON = "enableNeverLockTimeOutSettingOptionOnKingston";

    // Should Skip initial auth Check on Devices
    public static final String SHOULD_SKIP_INITIAL_AUTH_CHECK = "shouldSkipInitialAuthCheck";

    // Norden meeting sync interval in seconds
    public static final String SYNC_INTERVAL_IN_SECONDS_FOR_NORDEN_MEETING = "syncIntervalInSecondsForNordenMeeting";

    // Auto accept meeting nudges
    public static final String ENABLE_AUTO_ACCEPT_MEETING_NUDGES = "enableAutoAcceptMeetingNudges";

    // Auto accept call countdown in seconds
    public static final String AUTO_ACCEPT_CALL_COUNTDOWN_IN_SECONDS = "autoAcceptCallCountdownInSeconds";

    // call scenario marker end status
    public static final String LIST_OF_CALL_SCENARIOS_TO_CHECK_END_CALL_STATUS = "listOfCallScenariosToCheckEndCallStatus";
    public static final String LIST_OF_CALL_END_ERROR_CODES_TO_IGNORE = "listOfCallEndErrorCodesToIgnore";
    public static final String LIST_OF_CALL_END_SCENARIOS_TO_IGNORE = "listOfCallEndScenariosToIgnore";

    // Check for same account
    public static final String SHOULD_CHECK_FOR_CURRENT_USER_ON_SKYLIB_UPDATE_TOKEN = "shouldCheckForCurrentUserOnSkylibUpdateToken";

    // Check for same account
    public static final String SHOULD_SHOW_MEETING_BANNER_DEFAULT_STATE = "shouldShowMeetingBannerDefaultState";

    // JS SDK Internal API: openConversation
    public static final String ENABLE_JS_SDK_OPEN_NEW_CONVERSATIONS = "platform/enableJsSDKOpenConversation";
    public static final String ENABLE_JS_SDK_OPEN_EXISTING_CONVERSATIONS = "platform/enableJsSDKOpenConversationForExistingConversations";
    public static final String ENABLE_JS_SDK_OPEN_CONVERSATION_SPINNER = "platform/showOpenConversationSpinner";

    // JS SDK Internal API: getUserJoinedTeams
    public static final String ENABLE_GET_USER_TEAMS_JS_SDK_API = "platform/enableJsSDKGetUserJoinedTeams";

    // Stream query parameter override
    public static final String STREAM_EMBED_QUERY_PARAM_OVERRIDE = "streamEmbedQueryParameterOverride";

    // enable incremental group sync
    public static final String ENABLE_INCREMENTAL_GROUP_SYNC = "isGroupCalendarIncrementalAPIEnabled";

    // show sparse calendar
    public static final String SHOW_SPARSE_CALENDAR = "showSparseCalendar";

    // notifications
    public static final String IS_PER_ACCOUNT_NOTIFICATIONS_SETTINGS_ENABLED = "isPerAccountNotificationSettingsEnabled";

    // OneDrive provisioning on sign-in for TFL.
    public static final String CONSUMER_ONEDRIVE_PROVISIONING = "consumerOneDriveProvisioning";

    public static final String ENABLE_ROOM_CONTROL = "enableRoomControl";
    public static final String ENABLE_JS_ROOM_CONTROLLER = "enableJSRoomController";
    public static final String ROOM_CONTROLLER_REACT_URL = "roomControllerReactURL";

    public static final String SHOW_ANIMATED_BADGE_VIEW = "showAnimatedBadgeView";
    public static final String NEW_GROUP_WELCOME_SCREEN_TYPE = "newGroupWelcomeScreenType";
    public static final String IS_UNTITLED_GROUP_CREATION_ENABLED = "isUntitledGroupCreationEnabled";

    public static final String SKYPE_CHAT_SERVICES_PRE_INIT = "skypeChatServicesPreInit";

    //XL meeting optimization
    public static final String PREEMPTIVE_CALL_STATUS_DETETCTION = "preemptiveCallStatusDetection";
    public static final String SHOULD_UPDATE_PARTICIPANT_ENABLED = "shouldUpdateParticipantEnabled";
    public static final String DEDUPE_PARTICIPANT_LIST_TO_LISTENERS_ENABLED = "enableDedupeParticipantListToListeners";
    public static final String DELAYED_PARTICIPANT_LIST_UPDATE_ENABLED = "enableDelayedParticipantListUpdate";
    public static final String REFRESH_PARTICIPANT_LIST_DELAY = "refreshParticipantListDelay";

    // Giphy
    public static final String KNOWN_GIPHY_HOSTS = "knownGiphyHosts";
    public static final String SHOULD_RESPECT_GIPHY_DISPLAY_POLICY = "shouldRespectGiphyDisplayPolicy";

    // Enable stardust navigation bar
    public static final String ENABLE_L1_NAVIGATION_BAR = "enableL1NavigationBar";
    public static final String ENABLE_L2_NAVIGATION_BAR = "enableL2NavigationBar";
    public static final String ENABLE_TENANT_SWITCH_ON_AVATAR_SWIPE = "enableTenantSwitchOnAvatarSwipe";

    // POP Token
    public static final String POP_TOKEN_SUPPORT_ENABLED = "popTokenSupportEnabled";

    //Teams SDK
    public static final String SDK_BLOCKLISTED_APPS = "sdk/sdkBlocklistedApps";

    // TFL people APIs - prevent parallel calls
    public static final String SHOULD_PREVENT_PARALLEL_BLOCK_LIST_SYNC = "shouldPreventParallelBlocklistSync";
    public static final String SHOULD_PREVENT_PARALLEL_CONTACT_GROUP_SYNC = "shouldPreventParallelContactGroupSync";
    public static final String SHOULD_PREVENT_PARALLEL_ADDRESS_BOOK_SYNC = "shouldPreventParallelAddressBookSync";

    // enable doormat in consumer version
    public static final String ENABLE_DOORMAT = "enableDoormat";
    public static final String ENABLE_ADD_ACCOUNT_COACHMARK = "enableAddAccountCoachMark";

    // Norden hdmi ingest
    public static final String ENABLE_HDMI_INGEST_ON_NORDEN = "enableHDMIIngestOnNorden";

    // Diagnostic Data Viewer
    public static final String DIAGNOSTIC_DATA_VIEWER_ENABLED = "diagnosticDataViewerEnabled";

    // Immersive Reader button
    public static final String IMMERSIVE_READER_ENABLED = "immersiveReaderEnabled";

    // Shared element transitions
    public static final String MEDIA_SHARED_ELEMENT_TRANSITIONS_ENABLED = "mediaSharedElementTransitionsEnabled";

    // Chat Filter Feature
    public static final String ENABLE_CHAT_FILTER = "enableChatFiltering";

    // Token prefetch
    public static final String TOKEN_PREFETCH_RESOURCES = "tokenPrefetchResources";
    public static final String TOKEN_FETCH_NON_USE_THRESHOLD = "tokenFetchNonUseThreshold";

    // Alias discoverability
    public static final String ALIAS_DISCOVERABILITY_ENABLED = "enableAliasDiscoverabilitySettings";

    // Chat Dashboard coachmark
    public static final String CHAT_DASHBOARD_COACHMARK_ENABLED = "isChatDashboardCoachmarkEnabled";

    // Emoji
    public static final String EXTENDED_EMOJI_METADATA_ID = "extendedEmojiMetadataId";
    public static final String EXTENDED_EMOJI_ENABLED = "extendedEmojiEnabled";

    // Alias discoverability
    public static final String ENABLE_APP_PROTECTION_CAPABILITY = "enableAppProtectionCapability";

    public static final String SHOULD_SET_ANONYMOUS_USER_DISPLAY_NAME = "shouldSetAnonymousUserDisplayName";

    // Notificaton filter via server side
    public static final String NOTIFICATION_FILTER_VIA_SERVER_ENABLED = "enableNotificationFilteringViaServer";

    // Custom brb logging for native apps
    public static final String NATIVE_APPS_CUSTOM_BRB_LOGGING_ENABLED = "nativeAppsCustomBrbLoggingEnabled";

    // flag to check reduce sync time in user entitlements is enabled.
    public static final String SYNC_USER_ENTITLEMENTS_IMPROVEMENT = "platform/syncUserEntitlementsImprovement";


    // flag to show DogfoodToast
    public static final String DF_TOAST_ENABLED = "dogfood/enableDogfoodToast";

    // Dog food prompt reset count in days
    public static final String DF_TOAST_RESET_DAY_COUNT = "dogfood/dogfoodToastResetDayCount";

    // Dog food prompt reset count in days
    public static final String DF_APP_PACKAGE = "dogfood/dogfoodAppPackage";

    // Dog food prompt reset count in days
    public static final String DF_APPCENTER_URL = "dogfood/dogfoodAppCenterUrl";

    // Upload message size limit in KB
    public static final String MESSAGE_LENGTH_MAX_LIMIT = "messaging/messageSizeLimitInKB";

    private ExperimentationConstants() {
    }
}
