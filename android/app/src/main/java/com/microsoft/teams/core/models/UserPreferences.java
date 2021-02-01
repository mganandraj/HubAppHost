/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */
package com.microsoft.teams.core.models;

import androidx.annotation.StringDef;

@StringDef(value = {UserPreferences.ALERT_BADGE_COUNT,
        UserPreferences.ALERT_UNREAD_NOTIFICATION_ID_SET,
        UserPreferences.ANON_JOIN_DOMAIN,
        UserPreferences.APP_SETTINGS_PRIMARY_TENANT_ID,
        UserPreferences.APP_SETTINGS_TENANT_ID,
        UserPreferences.APP_SETTINGS_TENANT_LIST,
        UserPreferences.APP_SETTINGS_USERNAME,
        UserPreferences.APP_SETTINGS_USEROBJECT_ID,
        UserPreferences.AT_MENTION_SERVICE_BASE_URL_KEY,
        UserPreferences.AUTHZ_SERVICE_BASE_URL_KEY,
        UserPreferences.AUTHZ_CONSUMER_SERVICE_BASE_URL_KEY,
        UserPreferences.AUTHZ_CONSUMER_GUEST_SERVICE_BASE_URL_KEY,
        UserPreferences.AUTO_ANSWER_CALL_ENABLED,
        UserPreferences.BING_SERVICE_BASE_URL,
        UserPreferences.BRB_SERVICE_BASE_URL,
        UserPreferences.CACHED_CALL_ME_BACK_PHONE_NO,
        UserPreferences.CALENDAR_LIST_LAST_SYNC_TIME,
        UserPreferences.CALENDAR_INCREMENTAL_START_DATE,
        UserPreferences.CALENDAR_INCREMENTAL_NEXT_LINK,
        UserPreferences.CALENDAR_HAS_WHOLE_CALENDAR_SYNCED,
        UserPreferences.CALENDAR_DATE_PICKER_CHAIN_SYNC_START_TIME,
        UserPreferences.CALENDAR_DATE_PICKER_CHAIN_SYNC_START_PROCESS_ID,
        UserPreferences.CALENDAR_DATE_PICKER_CHAIN_STEP_COUNT,
        UserPreferences.CALENDAR_DATE_PICKER_CHAIN_TOTAL_EVENT_FETCH_COUNT,
        UserPreferences.CALENDAR_DATE_PICKER_CHAIN_TRANSFORMATION_TIME,
        UserPreferences.CALENDAR_DATE_PICKER_CHAIN_DB_UPDATE_TIME,
        UserPreferences.CALENDAR_DATE_PICKER_IS_LEGACY_DATA_CLEARED, // calendar date picker related(paged_meeting_fetch)
        UserPreferences.CALENDAR_MEETING_REMINDER_TYPE,
        UserPreferences.CALENDAR_MEETING_REMINDER_TIME,
        UserPreferences.CALENDAR_SYNC_START_TIME,
        UserPreferences.CALENDAR_DATE_PICKER_VIEW_ENABLED,
        UserPreferences.CALENDAR_EXPANSION_TILL_DATE,
        UserPreferences.CALENDAR_LAST_DB_PURGE_TIME,
        UserPreferences.CALENDAR_DATE_PICKER_ENABLED_GROUPS,
        UserPreferences.CALLS_BADGE_COUNT,
        UserPreferences.CHANNEL_MEETING_COUNT_KEY,
        UserPreferences.CHANNEL_ID_KEY,
        UserPreferences.CHAT_BADGE_COUNT,
        UserPreferences.CHAT_UNREAD_NOTIFICATION_ID_SET,
        UserPreferences.CHAT_UNREAD_NOTIFICATIONS_HISTORY,
        UserPreferences.HOME_SCREEN_NOTIFICATIONS_ENABLED_KEY,
        UserPreferences.HOME_SCREEN_CALL_NOTIFICATIONS_IGNORED_KEY,
        UserPreferences.HOME_SCREEN_MEETING_NOTIFICATIONS_IGNORED_KEY,
        UserPreferences.HOME_SCREEN_VOICEMAIL_NOTIFICATIONS_IGNORED_KEY,
        UserPreferences.HOME_SCREEN_ENABLED_KEY,
        UserPreferences.CNS_BASE_URL_KEY,
        UserPreferences.COLLAPSED_TEAMS_LIST,
        UserPreferences.COMPANY_PORTAL_ENROLLMENT_STATUS,
        UserPreferences.CONFIG_ENVIRONMENT_NAME,
        UserPreferences.CONFIG_PROVIDER_NAME,
        UserPreferences.PERSONAL_STREAM_ACTIVITY_THREAD_ID,
        UserPreferences.PERSONAL_STREAM_STARRED_MESSAGES_THREAD_ID,
        UserPreferences.PERSONAL_STREAM_CALL_LOGS_THREAD_ID,
        UserPreferences.CONTACTS_DISABLED_MSG_DISMISSED,
        UserPreferences.CORTANA_KWS_ENABLED_KEY,
        UserPreferences.HAS_DISABLED_KWS_BEFORE,
        UserPreferences.CORTANA_AUDIO_ENABLED_KEY,
        UserPreferences.CORTANA_AUDIO_DUMP_ENABLED_KEY,
        UserPreferences.CORTANA_LAST_TOKEN_REFRESH_TRY_TIMESTAMP_MILLIS,
        UserPreferences.CORTANA_LAST_SUCCESSFUL_TOKEN_REFRESH_TRY_TIMESTAMP_MILLIS,
        UserPreferences.CORTANA_QUALITY_OVERRIDE_KEY,
        UserPreferences.CORTANA_TOKEN_REFRESH_RETRY_COUNT,
        UserPreferences.CORTANA_FRE_VISIBLE_TO_USER,
        UserPreferences.LAST_SIGNIN_TIMESTAMP_MILLIS,
        UserPreferences.CORTANA_LAST_FRE_DISMISSED_TIMESTAMP_MILLIS,
        UserPreferences.USER_PERFORMED_ACTION,
        UserPreferences.USER_SEEN_SPEECH_RECOGNITION_CONSENT,
        UserPreferences.USER_CONSENTED_SPEECH_RECOGNITION,
        UserPreferences.USER_SPEECH_LOGGING_CONSENT,
        UserPreferences.USER_SEEN_CORTANA_FRE_COUNT,
        UserPreferences.CORTANA_FRE_TRIGGER_MODE,
        UserPreferences.USER_SEEN_SPEECH_LOGGING_CONSENT,
        UserPreferences.SPEECH_LOGGING_CONSENT_ACCEPTED_VERSION,
        UserPreferences.IS_CORTANA_FRE_BANNER_ACTIVE,
        UserPreferences.HAS_ENABLED_CORTANA_BEFORE,
        UserPreferences.HAS_USER_SEEN_SAFETY_NOTICE,
        UserPreferences.HAS_FRE_BEEN_STARTED,
        UserPreferences.IS_CORTANA_FRE_FINISHED,
        UserPreferences.CORTANA_LAST_FRE_SHOWED_TIMESTAMP_MILLIS,
        UserPreferences.HAS_USER_SEEN_IN_VIEW_KWS_CONSENT,
        UserPreferences.HAS_USER_SEEN_BEFORE_VIEW_KWS_CONSENT,
        UserPreferences.USER_LAST_REJECT_IN_VIEW_KWS_CONSENT_TIMESTAMP,
        UserPreferences.HAS_FIRST_SESSION_STARTED,
        UserPreferences.HAS_FIRST_SESSION_FINISHED,
        UserPreferences.HAS_ONE_MORE_SESSION_STARTED,
        UserPreferences.HAS_ONE_MORE_SESSION_FINISHED,
        UserPreferences.CORTANA_LAST_SEARCH_ENTRY_COACH_MARK_SHOWED_TIMESTAMP_MILLIS,
        UserPreferences.CORTANA_USER_SEEN_SEARCH_ENTRY_COACH_MARK_COUNT,
        UserPreferences.DOMAIN_DISCOVERY_BASE_URL,
        UserPreferences.ECS_QUERY_PARAM_KEY,
        UserPreferences.ECS_URL,
        UserPreferences.EXTERNAL_MOBILE_MODULES,
        UserPreferences.FEATURES_USER_DARK_THEME_ENABLED,
        UserPreferences.FEATURES_MERGE_CHATS_AND_CHANNELS_ENABLED,
        UserPreferences.FEATURES_NEW_COMPOSE_ENABLED,
        UserPreferences.FEATURES_NATIVE_MOBILE_MODULES_ENABLED,
        UserPreferences.FEATURES_REACT_NATIVE_MOBILE_MODULES_ENABLED,
        UserPreferences.FEATURES_UNIFIED_CHAT_LIST_ENABLED,
        UserPreferences.FEATURES_WEB_MOBILE_MODULES_ENABLED,
        UserPreferences.FILE_JOB_CANCELLED,
        UserPreferences.FILES_LIST_LAST_PRUNE_TIME,
        UserPreferences.FIRST_TIME_FRE_DONE,
        UserPreferences.FIRST_TIME_HIDE_CHAT,
        UserPreferences.FRE_EXPERIENCE_KEY,
        UserPreferences.FRE_SETTING_KEY,
        UserPreferences.FRE_STARTED,
        UserPreferences.FRE_SCREEN_FINISHED,
        UserPreferences.TFL_DOORMAT_SHOWN_COUNT,
        UserPreferences.USER_ORS_PRIVACY_DATA_CONTROL_LEVEL,
        UserPreferences.USER_ORS_OFFICE_PRIVACY_DOWNLOAD_CONTENT_SETTING_LEVEL,
        UserPreferences.USER_ENABLE_GIPHY_SETTING,
        UserPreferences.GIPHY_DATA_REFRESH_TIME,
        UserPreferences.HAS_USER_EVER_TOGGLED_UNIFIED_CHATS_CHANNELS_SETTINGS,
        UserPreferences.HAS_USER_REORDERED_APP,
        UserPreferences.HAS_TIME_BASED_RETENTION_EVER_KICKED_IN,
        UserPreferences.IS_ACTIVE_URL_CNS,
        UserPreferences.IS_USER_PINNING_DISABLED,
        UserPreferences.LAST_DISMISS_ACTIVATION_BANNER_TIME,
        UserPreferences.LAST_CALLED_PHONE_NUMBER,
        UserPreferences.LAST_DIALED_PHONE_NUMBER,
        UserPreferences.FUNNEL_UTM_SOURCE,
        UserPreferences.FUNNEL_UTM_TERM,
        UserPreferences.FUNNEL_UTM_CAMPAIGN,
        UserPreferences.SYNC_CONTACT,
        UserPreferences.CLICKED_DRAWER_COMPOSE_ICON,
        UserPreferences.CLICKED_DRAWER_PLUS_BUTTON,
        UserPreferences.TFL_BANNER_CLICKED,
        UserPreferences.TFL_BANNER_VERSION,
        UserPreferences.TFL_SUGGESTED_CONTACT_DISPLAY_TIME,
        UserPreferences.LONGPOLL_V2_URL,
        UserPreferences.MEETING_CHAT_MUTE_SETTING_ENABLED_NOTIFICATION_COUNT,
        UserPreferences.MEETING_CHAT_MUTE_SETTINGS_ENABLED_FOR_NOISY_CHATS,
        UserPreferences.MEETING_NOTIFICATION_SETTING,
        UserPreferences.MEETING_WHITEBOARD_POLICY,
        UserPreferences.MEETING_WHITEBOARD_POLICY_LAST_LOAD_TIME,
        UserPreferences.PARTICIPANT_LONG_PRESS_MENU_TOOL_TIP_COUNT,
        UserPreferences.MIDDLE_TIER_ACTIVECALL_SERVICE_BASE_URL_KEY,
        UserPreferences.MIDDLE_TIER_ROOM_SERVICE_BASE_URL_KEY,
        UserPreferences.MIDDLE_TIER_AUTH_SERVICE_BASE_URL_KEY,
        UserPreferences.MIDDLE_TIER_SERVICE_BASE_IMAGE_URL_KEY,
        UserPreferences.MIDDLE_TIER_SERVICE_BASE_URL_KEY,
        UserPreferences.MIDDLE_TIER_SERVICE_CUSTOM_ENDPOINT_KEY,
        UserPreferences.MS_GRAPH_SERVICE_BASE_URL,
        UserPreferences.CONSUMER_VROOM_SERVICE_BASE_URL,
        UserPreferences.NEW_CALLING_UX_TAB_SELECTION,
        UserPreferences.NG_CONVERSATION_SERVICE_URL,
        UserPreferences.NG_UDP_TRANSPORT_URL,
        UserPreferences.NG_KEY_DISTRIBUTION_URL,
        UserPreferences.NG_POTENTIAL_CALL_REQUEST_URL,
        UserPreferences.NG_UPLOAD_LOG,
        UserPreferences.BETTER_TOGETHER_SETTING_LOCK_UNLOCK,
        UserPreferences.BETTER_TOGETHER_SETTING_DESKTOP_REPLY,
        UserPreferences.BETTER_TOGETHER_SETTING_DESKTOP_RING,
        UserPreferences.BACK_GROUND_EFFECT_SELECTED,
        UserPreferences.NOTIFICATIONS_ENDPOINT_ID,
        UserPreferences.NOTIFICATION_LIVE_MEETING,
        UserPreferences.OCPS_BASE_URL,
        UserPreferences.OCV_PETROL_SERVICE_BASE_URL,
        UserPreferences.OCV_PETROL_SERVICE_INTERNAL_BASE_URL,
        UserPreferences.ODSP_VIEWER_ASSETS_SERVER_FOLDER_URL,
        UserPreferences.OUTLOOK_SERVICE_BASE_URL,
        UserPreferences.PERSONAL_APPS_TRAY_SETTING,
        UserPreferences.PINNED_CHATS,
        UserPreferences.PINNED_CHATS_ENABLED,
        UserPreferences.PREJOIN_SETTINGS,
        UserPreferences.PREJOIN_COACHMARK_COUNT,
        UserPreferences.PROXIMITY_JOIN_ENABLED,
        UserPreferences.ROOM_REMOTE_ENABLED,
        UserPreferences.PREVIOUS_CHANNELS_IN_CHAT_LIST_PILOT_ENABLED,
        UserPreferences.PRIMARY_TENANT_ID,
        UserPreferences.SMART_COMPOSE_ENABLED,
        UserPreferences.SMART_COMPOSE_ACCESSIBILITY_VISITED,
        UserPreferences.SMART_REPLY_ENABLED,
        UserPreferences.READ_RECEIPTS_ENABLED,
        UserPreferences.REAL_TIME_REGISTRATION_TOKEN_SETTING_KEY,
        UserPreferences.RECORDING_PERMISSIONS_AND_SETTINGS,
        UserPreferences.MEETING_CONFIGURATION_SETTINGS,
        UserPreferences.RING,
        UserPreferences.SCHEDULING_SERVICE_BASE_URL,
        UserPreferences.SCT_ARIA_COLLECTOR_URI,
        UserPreferences.SEARCH_HISTORY_ENABLED,
        UserPreferences.SHAREPOINT_CHANNEL_SITE_URL_KEY,
        UserPreferences.SHAREPOINT_PERSONAL_ROOT_URL_KEY,
        UserPreferences.SHAREPOINT_PERSONAL_SITE_URL_KEY,
        UserPreferences.SHOULD_LOG_UNIFIED_LIST_TELEMETRY,
        UserPreferences.SKYPE_ASM_BASE_URL_KEY,
        UserPreferences.SKYPE_ASM_LEGACY_URL_KEY,
        UserPreferences.SKYPE_CHAT_SERVICE_BASE_URL_KEY,
        UserPreferences.SKYPE_CSA_BASE_URL_KEY,
        UserPreferences.SKYPE_EDF_BASE_URL_KEY,
        UserPreferences.SKYPE_QUERY_SERVICE_BASE_URL_KEY,
        UserPreferences.SKYPE_URLPREVIEW_URL_KEY,
        UserPreferences.STREAM_API_URL,
        UserPreferences.SUBSTRATE_SERVICE_BASE_URL,
        UserPreferences.FILE_SEARCH_BASE_URL,
        UserPreferences.TEAMS_AND_CHANNELS_SERVICE_BASE_URL_KEY,
        UserPreferences.TENANT_LIST_FOR_USER,
        UserPreferences.TRAP_RELAY_LYNC_FQDN,
        UserPreferences.TRAP_RELAY_SKYPE_FQDN,
        UserPreferences.TRAP_RELAY_TURN_ADDRESSES,
        UserPreferences.TRAP_RELAY_TURN_URL,
        UserPreferences.TRAP_SERVICE_TOKEN_URL,
        UserPreferences.TRAP_SERVICE_URL,
        UserPreferences.TROUTER_CONNECTION_URL,
        UserPreferences.UNIFIED_PRESENCE_SERVICE_BASE_URL_KEY,
        UserPreferences.UNIFIED_PRESENCE_SERVICE_ENDPOINT_ID,
        UserPreferences.UNREAD_VOICEMAILS_BADGE_COUNT,
        UserPreferences.USER_ACCOUNT_SHARED,
        UserPreferences.USER_ENTITLEMENT_LAST_SYNC_TIME,
        UserPreferences.USER_APP_POLICIES_LAST_SYNC_TIME,
        UserPreferences.USER_FIRST_LOGIN_TIME,
        UserPreferences.USER_PROPERTIES_UPDATED,
        UserPreferences.USER_PROPERTIES_FIRSTLOGININFORMATIONPRESENT,
        UserPreferences.USER_SETTINGS_LAST_LOADED,
        UserPreferences.VOICEMAIL_LAST_SEEN_TIME,
        UserPreferences.VSTS_SERVICE_BASE_URL_KEY,
        UserPreferences.VSTS_SERVICE_PROJECT_NAME_KEY,
        UserPreferences.CALL_ME_BACK_PHONE_NUMBER_CACHE,
        UserPreferences.ARIA_COLLECTOR_URL,
        UserPreferences.ONE_DS_COLLECTOR_URL,
        UserPreferences.OUTLOOK_CONTACT_SYNC_TOKEN,
        UserPreferences.OUTLOOK_CONTACT_SYNC_TIME,
        UserPreferences.CALL_ME_BACK_PHONE_NUMBER_CACHE,
        UserPreferences.CORTANA_ADMIN_POLICY_REFRESH_TIMESTAMP_MILLIS,
        UserPreferences.CORTANA_LAST_HEART_BEAT_SENT_TIMESTAMP_MILLIS,
        UserPreferences.CORTANA_HEART_BEAT_DATA,
        UserPreferences.CORTANA_INVOCATION_MODE,
        UserPreferences.CORTANA_HOST_NAME,
        UserPreferences.CORTANA_VOICE_FONT,
        UserPreferences.PINNED_CHANNELS_METADATA,
        UserPreferences.PINNED_CHANNELS_HEADER_COLLAPSE_STATE_PINNED_CHANNELS,
        UserPreferences.PINNED_CHANNELS_HEADER_COLLAPSE_STATE_TEAMS,
        UserPreferences.PINNED_CHANNELS_HEADER_COLLAPSE_STATE_UNIFIED_CHANNELS,
        UserPreferences.PINNED_CHANNELS_HEADER_COLLAPSE_STATE_UNIFIED_CHATS,
        UserPreferences.PINNED_CHANNELS_UNIFIED_MIGRATION_STATUS,
        UserPreferences.PINNED_CHANNELS_SHOULD_SKIP_AUTO_PIN,
        UserPreferences.BOTTOM_NAVIGATION_BAR_TAB_SELECTED,
        UserPreferences.CONTACT_SYNC_TIME,
        UserPreferences.CONTACT_SYNC_STATUS,
        UserPreferences.CONTACT_GROUPS_LAST_SYNC_TIME,
        UserPreferences.SAFE_LINK_SERVICE_BASE_URL_KEY,
        UserPreferences.SAFE_LINK_GET_POLICY_LAST_LOADED,
        UserPreferences.HAS_SAFE_LINK_GET_POLICY_LICENSE,
        UserPreferences.REDUCED_DATA_MODE_BANNER_DISPLAYED_COUNT,
        UserPreferences.USER_UPDATED_THEIR_PROFILE_PHOTO,
        UserPreferences.USER_OCPS_PRIVACY_DATA_CONTROL_LEVEL,
        UserPreferences.USER_OCPS_PDC_LEVEL_LAST_FETCH_TIME,
        UserPreferences.USER_OCPS_POLICIES_HASH,
        UserPreferences.USER_OCPS_CONNECTED_EXPERIENCES_POLICY,
        UserPreferences.USER_OCPS_CONNECTED_EXPERIENCES_USER_PREFERENCE,
        UserPreferences.USER_OCPS_CONNECTED_EXPERIENCES_FRE_SEEN,
        UserPreferences.USER_JOINED_CHANNEL_LIST,
        UserPreferences.LONGPOLL_V2_BASE_URL,
        UserPreferences.DATA_SOURCE_REGISTRY_STATE,
        UserPreferences.DEVELOPER_PREVIEW_ENABLED,
        UserPreferences.LAST_TEAMS_ORDER_FETCH_TIME,
        UserPreferences.CORTANA_SDK_HTTP_PROXY_ADDRESS,
        UserPreferences.CORTANA_SDK_HTTP_PROXY_PORT,
        UserPreferences.HAS_SEEN_ACTIVATE_TEAM_COACHMARK,
        UserPreferences.HAS_SEEN_GIPHY_PICKER_COACHMARK,
        UserPreferences.STATUS_BANNER_DISMISSALS,
        UserPreferences.CORTANA_SDK_HTTP_PROXY_PORT,
        UserPreferences.EMERGENCY_LOCATION_INFO,
        UserPreferences.SEMANTIC_OBJECT_SERVICE_BASE_URL,
        UserPreferences.LAST_EMERGENCY_LOCATION_UPDATE_REQUEST_TICKS,
        UserPreferences.ADDRESS_BOOK_SYNC_STATUS,
        UserPreferences.ADDRESS_BOOK_CHANGES_DIRTY_BIT,
        UserPreferences.ADDRESS_BOOK_SYNC_TIMESTAMP,
        UserPreferences.ADDRESS_BOOK_SYNC_DIALOG,
        UserPreferences.NOW_CONSUMPTION_HORIZON,
        UserPreferences.BETTER_TOGETHER_ENDPOINT,
        UserPreferences.BETTER_TOGETHER_PAIRED_ENDPOINT,
        UserPreferences.BETTER_TOGETHER_PAIRED_ENDPOINT_LAST_ACTIVE_TIME,
        UserPreferences.BETTER_TOGETHER_SELF_ENDPOINT_LAST_CHECK_TIME,
        UserPreferences.LOCATION_SHARING_SERVICE_BASE_URL_KEY,
        UserPreferences.LOCATION_SHARING_CONSENTED,
        UserPreferences.LOCATION_SHARING_PRIVACY_CONSENT_ASKED,
        UserPreferences.LOCATION_SHARING_PERMISSION_ASKED,
        UserPreferences.LOCATION_SHARING_MY_SESSIONS_SYNC_TIME,
        UserPreferences.VAULT_SERVICE_BASE_URL_KEY,
        UserPreferences.VAULT_RECOVERY_MSAKEY_SERVICE_URL_KEY,
        UserPreferences.VAULT_PREFERENCES,
        UserPreferences.VAULT_USER_PRIVATE_KEY,
        UserPreferences.VAULT_USER_PUBLIC_KEY,
        UserPreferences.VAULT_USER_KEYBUNDLEVERSION,
        UserPreferences.VAULT_USER_CLIENT_KEY,
        UserPreferences.WALLPAPER,
        UserPreferences.ECS_EXPERIMENTATION_IDS,
        UserPreferences.ECS_ETAG,
        UserPreferences.CHANNEL_AUTO_FOLLOW_USER_CHOICE,
        UserPreferences.CHANNEL_AUTO_FOLLOW_WORKING_SET,
        UserPreferences.CHANNEL_AUTO_FOLLOW_RETRY_SET,
        UserPreferences.CHANNEL_AUTO_FOLLOW_NUMBER_OF_RETRIES,
        UserPreferences.INVITE_SERVICE_BASE_URL_KEY,
        UserPreferences.PRESENCE_OFF_SHIFT_DID_USER_CONFIRM_DIALOG_DURING_SHIFT,
        UserPreferences.CURRENT_OFF_SHIFT_START_TIME,
        UserPreferences.CURRENT_OFF_SHIFT_END_TIME,
        UserPreferences.TRANSLATION_PREFERRED_LANGUAGE_ID,
        UserPreferences.TRANSLATION_UNDERSTOOD_LANGUAGE_IDS,
        UserPreferences.TRANSLATION_SUPPORTED_LANGUAGES,
        UserPreferences.TRANSLATION_SUPPORTED_LANGUAGES_LOCALE,
        UserPreferences.TRANSLATION_SUPPORTED_LANGUAGES_LAST_SYNC_TIME,
        UserPreferences.TRANSLATION_SUPPORTED_LANGUAGES_NEEDS_SYNCING,
        UserPreferences.TRANSLATION_DEFAULT_UI_LANGUAGE_ID,
        UserPreferences.FAVORITES_GROUP_ID,
        UserPreferences.HOT_DESKING_TIMEOUT,
        UserPreferences.OFF_NETWORK_INVITE_FAILED_ERROR_KEY,
        UserPreferences.SERVICE_BASE_URL_SUBSTRATE_SEARCH,
        UserPreferences.CLIENT_FLIGHTS_SUBSTRATE_SEARCH,
        UserPreferences.SERVER_FLIGHTS_SUBSTRATE_SEARCH,
        UserPreferences.QUERY_FLIGHTS_SUBSTRATE_SEARCH,
        UserPreferences.SPELLER_SUBSTRATE_SEARCH,
        UserPreferences.MESSAGE_QUERY_SUBSTRATE_SEARCH,
        UserPreferences.MESSAGE_QUERY_TOGGLE_SUBSTRATE_SEARCH,
        UserPreferences.WALLPAPER_V2,
        UserPreferences.SEARCHABILITY_STAMPING_CHECKED,
        UserPreferences.BACKGROUND_SYNC_SERVICE_RUN_INTERVAL_MINS,
        UserPreferences.IP_PHONES_CALL_DEFAULT_VIEW,
        UserPreferences.DEVICE_LOCK_TIME_OUT_MILLIS,
        UserPreferences.IS_PHONE_NUMBER_ONLY,
        UserPreferences.IS_PHONE_NUMBER_UPN,
        UserPreferences.CALL_AUTO_ACCEPT_MEETING_NUDGES,
        UserPreferences.CALL_AUTO_ACCEPT_WITH_VIDEO,
        UserPreferences.ONEDRIVE_PROVISIONING,
        UserPreferences.GROUPS_TO_SHOW_IN_SIDECAR,
        UserPreferences.NOTIFICATION_PREFERENCES_MIGRATED,
        UserPreferences.CHATS_NOTIFICATION_SWITCH,
        UserPreferences.MENTIONS_NOTIFICATION_SWITCH,
        UserPreferences.REPLIES_NOTIFICATION_SWITCH,
        UserPreferences.LIKES_NOTIFICATION_SWITCH,
        UserPreferences.APPS_NOTIFICATION_SWITCH,
        UserPreferences.OTHER_ALERTS_NOTIFICATION_SWITCH,
        UserPreferences.INFERRED_ALERTS_NOTIFICATION_SWITCH,
        UserPreferences.QUIET_HOURS_START,
        UserPreferences.QUIET_HOURS_END,
        UserPreferences.QUIET_HOURS_DAILY_HOURS_ENABLED,
        UserPreferences.QUIET_HOURS_QUIET_DAYS_ENABLED,
        UserPreferences.QUIET_HOURS_QUIET_DAYS,
        UserPreferences.QUIET_HOURS_SUPPRESSED_NOTIFICATION_COUNT,
        UserPreferences.QUIET_HOURS_HAS_SUPPRESSED_CHAT_MESSAGE,
        UserPreferences.QUIET_TIME_ALLOW_INCOMING_CALLS,
        UserPreferences.QUIET_TIME_ALLOW_IMPORTANT_MESSAGES,
        UserPreferences.QUIET_TIME_ALLOW_MENTIONS,
        UserPreferences.QUIET_TIME_ALLOW_BADGE_COUNTS,
        UserPreferences.SEND_NOTIFICATIONS_ONLY_WHEN_ACTIVE,
        UserPreferences.SEND_NOTIFICATIONS_DEBUG,
        UserPreferences.VIBRATE_NOTIFICATIONS_SWITCH,
        UserPreferences.WHEN_IN_MEETING_SWITCH,
        UserPreferences.HAS_SIDECAR_PRESENTATION,
        UserPreferences.SHOWED_SIDECAR_FRE_BEFORE,
        UserPreferences.CHANNELS_NOTIFICATION_SWITCH,
        UserPreferences.CALLS_NOTIFICATION_SWITCH,
        UserPreferences.DASHBOARD_COACH_MARK_DISPLAYED,
        UserPreferences.CLOUD_SETTING_LOGIN_SELECTED,
        UserPreferences.TOKEN_FETCH_TIMESTAMP,
        UserPreferences.LAST_COMPUTED_USER_ENTITLEMENT_HASH,
        UserPreferences.CLICK_SMART_REPLY_COUNT,
        UserPreferences.SHOW_LONG_PRESS_TIP,
        UserPreferences.LED_UNAVAILBLE_COLOR,
        UserPreferences.EXTENDED_EMOJI_METADATA_ID,
        UserPreferences.LAST_SENT_TELEMETRY_SW_VERSION
})
public @interface UserPreferences {
    String ALERT_BADGE_COUNT = "alertBadgeCount";
    String AUTHZ_SERVICE_BASE_URL_KEY = "AuthzEndpointUrl";
    String AUTHZ_CONSUMER_SERVICE_BASE_URL_KEY = "ConsumerAuthzEndpointUrl";
    String AUTHZ_CONSUMER_GUEST_SERVICE_BASE_URL_KEY = "ConsumerGuestAuthzEndpointUrl";
    String AUTO_ANSWER_CALL_ENABLED = "auto_answer_call_enabled";
    String CACHED_CALL_ME_BACK_PHONE_NO = "callMeBackPhoneNumber";
    String CALENDAR_LIST_LAST_SYNC_TIME = "Calendar_sync_last_sync_time";
    String CALENDAR_INCREMENTAL_START_DATE = "calendarIncrementalStartDate";
    String CALENDAR_INCREMENTAL_NEXT_LINK = "calendarIncrementalNextLink";
    String CALENDAR_HAS_WHOLE_CALENDAR_SYNCED = "calendarHasWholeCalendarSynced";
    String CALENDAR_SYNC_START_TIME = "calendarSyncStartTime";
    String CALENDAR_DATE_PICKER_VIEW_ENABLED = "calendarDatePickerEnabled";
    String CALENDAR_DATE_PICKER_CHAIN_SYNC_START_TIME = "calendarDPChainSyncStartTime"; // diff Api chain start time
    String CALENDAR_DATE_PICKER_CHAIN_SYNC_START_PROCESS_ID = "calendarDPChainSyncStartProcessId"; // diff Api chain start processid
    String CALENDAR_DATE_PICKER_CHAIN_STEP_COUNT = "calendarDPChainStepCount"; // diff Api chain step count
    String CALENDAR_DATE_PICKER_CHAIN_TOTAL_EVENT_FETCH_COUNT = "calendarDPChainTotalEventFetchCount"; // diff Api chain total event fetch count
    String CALENDAR_DATE_PICKER_CHAIN_TRANSFORMATION_TIME = "calendarDPChainTransformationTime"; // diff Api chain transformation time
    String CALENDAR_DATE_PICKER_CHAIN_DB_UPDATE_TIME = "calendarDatePickerChainDbUpdateTime"; // diff Api chain db update time
    String CALENDAR_EXPANSION_TILL_DATE = "calendarExpansionTillDate";
    String CALENDAR_LAST_DB_PURGE_TIME = "calendarLastDBPurgeTime";
    // calendar date picker related(paged_meeting_fetch) [[
    String CALENDAR_DATE_PICKER_IS_LEGACY_DATA_CLEARED = "calendarDPLegacyDataCleared"; // to clear existing data loaded from diff API based approach(if any)
    // calendar date picker related(paged_meeting_fetch) ]]
    String CALENDAR_DATE_PICKER_ENABLED_GROUPS = "calendarDatePickerEnabledGroups";
    String CALENDAR_MEETING_REMINDER_TYPE = "meetingReminderMeetingType";
    String CALENDAR_MEETING_REMINDER_TIME = "meetingReminderTime";
    String CALLS_BADGE_COUNT = "callsBadgeCount";
    String CHANNEL_MEETING_COUNT_KEY = "channelMeetingCountKey";
    String CHAT_BADGE_COUNT = "currentBadgeCount";
    String COLLAPSED_TEAMS_LIST = "Collapsed_Team_List";
    String COMPANY_PORTAL_ENROLLMENT_STATUS = "COMPANY_PORTAL_ENROLLMENT_STATUS";
    String PERSONAL_STREAM_ACTIVITY_THREAD_ID = "PERSONAL_STREAM_ACTIVITY_THREAD_ID";
    String PERSONAL_STREAM_STARRED_MESSAGES_THREAD_ID = "PERSONAL_STREAM_STARRED_MESSAGES_THREAD_ID";
    String PERSONAL_STREAM_CALL_LOGS_THREAD_ID = "PERSONAL_STREAM_CALL_LOGS_THREAD_ID";
    String CONTACTS_DISABLED_MSG_DISMISSED = "CONTACTS_DISABLED_MESSAGE_DISMISSED";
    String ECS_QUERY_PARAM_KEY = "Ecs_Query_Param_Key";
    String EXTERNAL_MOBILE_MODULES = "External_Mobile_Modules";
    String FEATURES_USER_DARK_THEME_ENABLED = "Features_User_Dark_Theme_Enabled";
    String FILE_JOB_CANCELLED = "JobCancelled";
    String FIRST_TIME_FRE_DONE = "Fre_Done";
    String FIRST_TIME_HIDE_CHAT = "First_Hide_Chat";
    String FRE_EXPERIENCE_KEY = "Fre_Experience_Key";
    String FRE_STARTED = "Fre_Started";
    String FRE_SCREEN_FINISHED = "Fre_Screen_Finished";
    String TFL_DOORMAT_SHOWN_COUNT = "TFL_Doormat_Shown_Count";
    String USER_ORS_PRIVACY_DATA_CONTROL_LEVEL = "USER_ORS_PRIVACY_DATA_CONTROL_LEVEL";
    String USER_ORS_OFFICE_PRIVACY_DOWNLOAD_CONTENT_SETTING_LEVEL = "USER_ORS_OFFICE_PRIVACY_DOWNLOAD_CONTENT_SETTING_LEVEL";
    String USER_ENABLE_GIPHY_SETTING = "USER_ENABLE_GIPHY_SETTING";
    String GIPHY_DATA_REFRESH_TIME = "Giphy_Data_Refresh_Time";
    String HAS_TIME_BASED_RETENTION_EVER_KICKED_IN = "isRetentionEverKickedInForThisUser";
    String HAS_USER_EVER_TOGGLED_UNIFIED_CHATS_CHANNELS_SETTINGS = "Has_User_Ever_Toggled_Unified_Chats_Channels_Settings";
    String HAS_USER_REORDERED_APP = "hasUserEditedAppOrderLocally";
    String LAST_CALLED_PHONE_NUMBER = "LAST_CALLED_PHONE_NUMBER";
    String LAST_DIALED_PHONE_NUMBER = "LAST_DIALED_PHONE_NUMBER";
    String LAST_DISMISS_ACTIVATION_BANNER_TIME = "LAST_DISMISS_ACTIVATION_BANNER_TIME";
    String FUNNEL_UTM_SOURCE = "FUNNEL_UTM_SOURCE";
    String FUNNEL_UTM_TERM = "FUNNEL_UTM_TERM";
    String FUNNEL_UTM_CAMPAIGN = "FUNNEL_UTM_CAMPAIGN";
    String SYNC_CONTACT = "SYNC_CONTACT";
    String CLICKED_DRAWER_COMPOSE_ICON = "CLICKED_DRAWER_COMPOSE_ICON";
    String CLICKED_DRAWER_PLUS_BUTTON = "CLICKED_DRAWER_PLUS_BUTTON";
    String TFL_BANNER_CLICKED = "TFL_BANNER_CLICKED";
    String TFL_BANNER_VERSION = "TFL_BANNER_VERSION";
    String TFL_SUGGESTED_CONTACT_DISPLAY_TIME = "TFL_SUGGESTED_CONTACT_DISPLAY_TIME";
    String NEW_CALLING_UX_TAB_SELECTION = "newCallingUXTabSelection";
    String NOTIFICATION_LIVE_MEETING = "Notification_Live_Meeting";
    String ODSP_VIEWER_ASSETS_SERVER_FOLDER_URL = "ODSPViewerAssets_ServerFolderUrl";
    String PERSONAL_APPS_TRAY_SETTING = "personalAppsTrayEnabled";
    String PREJOIN_SETTINGS = "Prejoin_Settings";
    String PREJOIN_COACHMARK_COUNT = "Prejoin_Coachmark_Count";
    String PROXIMITY_JOIN_ENABLED = "Proximity_Join_Enabled";
    String ROOM_REMOTE_ENABLED = "Room_Remote_Enabled";
    String SMART_COMPOSE_ENABLED = "Smart_Compose_Enabled";
    String SMART_COMPOSE_ACCESSIBILITY_VISITED = "Smart_Compose_Accessibility_Visited";
    String SMART_REPLY_ENABLED = "Smart_Reply_Enabled";
    String READ_RECEIPTS_ENABLED = "Read_Receipts_Enabled";
    String RECORDING_PERMISSIONS_AND_SETTINGS = "Recording_Permissions_And_Settings";
    String MEETING_CONFIGURATION_SETTINGS = "Meeting_Configuration_Settings";
    String RING = "ring_key";
    String SEARCH_HISTORY_ENABLED = "Search_History_Enabled";
    String UNREAD_VOICEMAILS_BADGE_COUNT = "unreadVoicemailsBadgeCount";
    String VOICEMAIL_LAST_SEEN_TIME = "VoicemailLastSeenTime";
    String USER_ACCOUNT_SHARED = "USER_ACCOUNT_SHARED";
    String USER_ENTITLEMENT_LAST_SYNC_TIME = "USER_ENTITLEMENT_LAST_SYNC_TIME";
    String USER_APP_POLICIES_LAST_SYNC_TIME = "USER_APP_POLICIES_LAST_SYNC_TIME";
    String USER_PROPERTIES_UPDATED = "Authenticated_User_Property_Updated";
    String USER_PROPERTIES_FIRSTLOGININFORMATIONPRESENT = "User_Property_FirstLoginInformationPresent";
    String USER_SETTINGS_LAST_LOADED = "User_Settings_Last_loaded";
    String SAFE_LINK_GET_POLICY_LAST_LOADED = "safeLinkGetPolicyLastLoadedTimestamp";
    String HAS_SAFE_LINK_GET_POLICY_LICENSE = "hasSafeLinkGetPolicyLicense";
    String ECS_EXPERIMENTATION_IDS = "Ecs_Experimentation_Ids";
    String ECS_ETAG = "Ecs_ETag";
    String REDUCED_DATA_MODE_BANNER_DISPLAYED_COUNT = "reducedDataModeBannerDisplayedCount";
    String USER_OCPS_PRIVACY_DATA_CONTROL_LEVEL = "UserOcpsPrivacyDataControlLevel";
    String USER_OCPS_PDC_LEVEL_LAST_FETCH_TIME = "UserOcpsPdcLevelLastFetchTime";
    String USER_OCPS_POLICIES_HASH = "UserOcpsPoliciesHash";
    String USER_OCPS_CONNECTED_EXPERIENCES_POLICY = "userOcpsConnectedExperiencesPolicy";
    String USER_OCPS_CONNECTED_EXPERIENCES_USER_PREFERENCE = "userOcpsConnectedExperiencesUserPreference";
    String USER_OCPS_CONNECTED_EXPERIENCES_FRE_SEEN = "userOcpsConnectedExperiencesFRESeen";

    // temp join joinConversation map used for throttling put thread calls
    String USER_JOINED_CHANNEL_LIST = "userJoinedChannelList";

    String NOTIFICATIONS_ENDPOINT_ID = "Notifications_Endpoint_Id";
    String REAL_TIME_REGISTRATION_TOKEN_SETTING_KEY = "Real_Time_Registration_Token_Setting_Key";
    String UNIFIED_PRESENCE_SERVICE_ENDPOINT_ID = "Unified_Presence_Service_Android_Endpoint_Id";

    // Endpoint manager
    String AT_MENTION_SERVICE_BASE_URL_KEY = "AtMentionServiceBaseUrl";
    String SAFE_LINK_SERVICE_BASE_URL_KEY = "safeLinkServiceBaseUrl";
    String BING_SERVICE_BASE_URL = "BingServiceBaseUrl";
    String BRB_SERVICE_BASE_URL = "BrbServiceBaseUrl";
    String CNS_BASE_URL_KEY = "ChatNotificationServiceBaseUrl";
    String DOMAIN_DISCOVERY_BASE_URL = "DomainDiscoveryBaseUrl";
    String MIDDLE_TIER_ACTIVECALL_SERVICE_BASE_URL_KEY = "activeCallServiceBaseUrl";
    String MIDDLE_TIER_AUTH_SERVICE_BASE_URL_KEY = "MiddleTierAuthServiceBaseUrl";
    String MIDDLE_TIER_ROOM_SERVICE_BASE_URL_KEY = "OutlookServiceBaseUrl";
    String MIDDLE_TIER_SERVICE_BASE_URL_KEY = "MiddleTierServiceBaseUrl";
    String MT_TENANT_SERVICE_BASE_URL_KEY = "MtTenantServiceBaseUrl";
    String MIDDLE_TIER_SERVICE_CUSTOM_ENDPOINT_KEY = "MiddleTierServiceCustomEndpoint";
    String MIDDLE_TIER_SERVICE_BASE_IMAGE_URL_KEY = "MiddleTierServiceBaseImageUrl";
    String TEAMS_CALL_QUEUE_SETTINGS_BASE_URL = "SettingsAgentCallqueueBaseUrl";
    String MS_GRAPH_SERVICE_BASE_URL = "MSGraphServiceBaseUrl";
    String CONSUMER_VROOM_SERVICE_BASE_URL = "ConsumerVroomServiceBaseUrl";
    String OCV_PETROL_SERVICE_BASE_URL = "OcvPetrolServiceBaseUrl";
    String OCV_PETROL_SERVICE_INTERNAL_BASE_URL = "OcvPetrolServiceInternalBaseUrl";
    String OUTLOOK_SERVICE_BASE_URL = "OutlookServiceBaseUrl";
    String SCHEDULING_SERVICE_BASE_URL = "SchedulingServiceBaseUrl";
    String ATTENDEE_SERVICE_BASE_URL = "AttendeeServiceBaseUrl";
    String ATTENDEE_SERVICE_BASE_URL_V2 = "AttendeeServiceBaseUrlV2";
    String SKYPE_ASM_BASE_URL_KEY = "SkypeAsmBaseUrl";
    String SKYPE_ASM_LEGACY_URL_KEY = "SkypeAsmLegacyeUrl";
    String SKYPE_CHAT_SERVICE_BASE_URL_KEY = "SkypeChatServiceBaseUrl";
    String SKYPE_CSA_BASE_URL_KEY = "SkypeCsaBaseUrl";
    String SKYPE_CSA_TFL_BASE_URL_KEY = "SkypeCsaTflBaseUrl";
    String SKYPE_EDF_BASE_URL_KEY = "edfRegistrationService";
    String CONSUMER_SKYPE_EDF_BASE_URL_KEY = "consumerEdfRegistrationService";
    String SKYPE_QUERY_SERVICE_BASE_URL_KEY = "SkypeQueryServiceBaseUrl";
    String SKYPE_URLPREVIEW_URL_KEY = "SkypeURLPreviewServiceURL";
    String STREAM_API_URL = "StreamApiUrl";
    String SUBSTRATE_SERVICE_BASE_URL = "SubstrateServiceBaseUrl";
    String SUBSTRATE_SERVICE_GCCH_BASE_URL = "SubstrateServiceGCCHBaseUrl";
    String SUBSTRATE_SERVICE_DOD_BASE_URL = "SubstrateServiceDODBaseUrl";
    String FILE_SEARCH_BASE_URL = "FileSearchBaseUrl";
    String TEAMS_AND_CHANNELS_SERVICE_BASE_URL_KEY = "TeamsAndChannelsServiceBaseUrl";
    String TEAMS_AND_CHANNELS_PROVISIONING_SERVICE_BASE_URL_KEY = "TeamsAndChannelsProvisioningServiceBaseUrl";
    String UNIFIED_PRESENCE_SERVICE_BASE_URL_KEY = "UnifiedPresenceServiceUrl";
    String VSTS_SERVICE_BASE_URL_KEY = "VstsServiceBaseUrl";
    String VSTS_SERVICE_PROJECT_NAME_KEY = "VstsServiceProjectName";
    String WHITEBOARD_SERVICE_BASE_URL = "WhiteboardServiceBaseUrl";
    String SEMANTIC_OBJECT_SERVICE_BASE_URL = "SemanticObjectServiceBaseUrl";
    String LOCATION_SHARING_SERVICE_BASE_URL_KEY = "LiveLocationServiceBaseUrl";
    String VAULT_SERVICE_BASE_URL_KEY = "VaultServiceBaseUrl";
    String VAULT_RECOVERY_MSAKEY_SERVICE_URL_KEY = "VaultRecoveryMSAKeyServiceUrl";
    String INVITE_SERVICE_BASE_URL_KEY = "InviteServiceBaseUrl";
    String STATE_SERVICE_END_POINT_KEY = "StateServiceEndpoint";

    /**
     * Key used to store endpoints
     */
    String NG_CONVERSATION_SERVICE_URL = "NGConversationServiceUrl";
    String NG_UDP_TRANSPORT_URL = "NGUdpTransportUrl";
    String NG_KEY_DISTRIBUTION_URL = "NGKeyDistributionUrl";
    String NG_POTENTIAL_CALL_REQUEST_URL = "NGPotentialCallRequestUrl";
    String NG_UPLOAD_LOG = "NGUploadLogUrl";
    String TROUTER_CONNECTION_URL = "TrouterConnectionUrl";
    String SCT_ARIA_COLLECTOR_URI = "SCTAriaCollectorUri";
    String TRAP_RELAY_LYNC_FQDN = "TRAPRelayLyncFqdn";
    String TRAP_RELAY_SKYPE_FQDN = "TRAPRelaySkypeFqdn";
    String TRAP_RELAY_TURN_ADDRESSES = "TRAPRelayTurnAddresses";
    String TRAP_RELAY_TURN_URL = "TRAPRelayTurnUrl";
    String TRAP_SERVICE_TOKEN_URL = "TRAPServiceTokenUrl";
    String TRAP_SERVICE_URL = "TRAPServiceUrl";

    String IS_ACTIVE_URL_CNS = "Is_Active_Url";
    String LONGPOLL_V2_URL = "Longpoll_V2_Url";
    String LONGPOLL_V2_BASE_URL = "Longpoll_V2_Base_Url";

    // Meeting chat settings
    String MEETING_NOTIFICATION_SETTING = "Meeting_Notifications_Setting";
    String MEETING_CHAT_MUTE_SETTINGS_ENABLED_FOR_NOISY_CHATS = "Meeting_Chat_Mute_Settings_Enabled_For_Noisy_Chats";
    String MEETING_CHAT_MUTE_SETTING_ENABLED_NOTIFICATION_COUNT = "meeting_chat_mute_setting_enabled_notification_count";
    String MEETING_WHITEBOARD_POLICY = "meeting_whiteboard_policy";
    String MEETING_WHITEBOARD_POLICY_LAST_LOAD_TIME = "meeting_whiteboard_policy_last_load_time";
    String PARTICIPANT_LONG_PRESS_MENU_TOOL_TIP_COUNT = "participant_long_press_menu_tool_tip_count";

    // ARMY/DoD/GCC High
    String CONFIG_ENVIRONMENT_NAME = "configEnvironmentName";
    String CONFIG_PROVIDER_NAME = "configProviderName";
    String ANON_JOIN_DOMAIN = "anonJoinDomain";
    String ECS_URL = "EcsServiceUrl";

    // Unified chats and channels
    String FEATURES_UNIFIED_CHAT_LIST_ENABLED = "Features_UnifiedChatList_Enabled";
    String PREVIOUS_CHANNELS_IN_CHAT_LIST_PILOT_ENABLED = "Previous_Channels_In_Chat_List_Pilot_Enable";
    String SHOULD_LOG_UNIFIED_LIST_TELEMETRY = "Should_Log_Unified_List_Telemetry";

    String FEATURES_REACT_NATIVE_MOBILE_MODULES_ENABLED = "Features_React_Native_Mobile_Modules_Enabled";
    String FEATURES_NATIVE_MOBILE_MODULES_ENABLED = "Features_Native_Mobile_Modules_Enabled";
    String FEATURES_WEB_MOBILE_MODULES_ENABLED = "Features_Web_Mobile_Modules_Enabled";
    String FEATURES_MERGE_CHATS_AND_CHANNELS_ENABLED = "Features_MergeChats_Enabled";

    String ALERT_UNREAD_NOTIFICATION_ID_SET = "unreadAlertNotificationIds";
    String CHAT_UNREAD_NOTIFICATION_ID_SET = "unreadChatNotificationIds";
    String CHAT_UNREAD_NOTIFICATIONS_HISTORY = "unreadChatNotificationHistory";

    //Home screen
    String HOME_SCREEN_ENABLED_KEY = "homeScreenEnabledPreference";
    String HOME_SCREEN_NOTIFICATIONS_ENABLED_KEY = "homeScreenNotificationsEnabledPreference";
    String HOME_SCREEN_CALL_NOTIFICATIONS_IGNORED_KEY = "homeScreenCallNotificationsIgnoredPreference";
    String HOME_SCREEN_MEETING_NOTIFICATIONS_IGNORED_KEY = "homeScreenMeetingNotificationsIgnoredPreference";
    String HOME_SCREEN_VOICEMAIL_NOTIFICATIONS_IGNORED_KEY = "homeScreenVoicemailNotificationsIgnoredPreference";

    //Better together settings
    String BETTER_TOGETHER_SETTING_LOCK_UNLOCK = "btSettingLockUnlockPreference";
    String BETTER_TOGETHER_SETTING_DESKTOP_REPLY = "btSettingDesktopReplyPreference";
    String BETTER_TOGETHER_SETTING_DESKTOP_RING = "btSettingDesktopRingPreference";

    //Background effects
    String BACK_GROUND_EFFECT_SELECTED = "backgroundEffectsSelected";

    /* App settings and tenant switching*/
    String PRIMARY_TENANT_ID = "primary_tenant_id";
    String TENANT_LIST_FOR_USER = "Tenant_List_For_User";

    /* Files */
    String CHANNEL_ID_KEY = "channel_id";
    String FILES_LIST_LAST_PRUNE_TIME = "filesListLastPruneTime";

    /**
     * Keys from userPreferencesDao
     */
    String FRE_SETTING_KEY = "Fre_Setting_Key";

    String SHAREPOINT_PERSONAL_SITE_URL_KEY = "personalSiteUrl";
    String SHAREPOINT_PERSONAL_ROOT_URL_KEY = "personalRootFolderUrl";
    String SHAREPOINT_CHANNEL_SITE_URL_KEY = "channelSiteUrl";

    /**
     * Keys from AppSettingsDao
     */
    String APP_SETTINGS_PRIMARY_TENANT_ID = "primary_tenant_id";
    String APP_SETTINGS_TENANT_ID = "tenantid";
    String APP_SETTINGS_TENANT_LIST = "tenant_list";
    String APP_SETTINGS_USERNAME = "username";
    String APP_SETTINGS_USEROBJECT_ID = "userobjectid";

    // Aria Collector URL
    String ARIA_COLLECTOR_URL = "AriaCollectorServiceUrl";
    String ONE_DS_COLLECTOR_URL = "OneDSCollectorServiceUrl";

    // Call me back - user phone number cache
    String CALL_ME_BACK_PHONE_NUMBER_CACHE = "call_me_back_phone_number_cache_map";

    // Keys for Cortana Settings
    String CORTANA_KWS_ENABLED_KEY = "key_cortana_kws_enabled";
    String HAS_DISABLED_KWS_BEFORE = "has_disabled_kws_before";
    String CORTANA_TOKEN_REFRESH_RETRY_COUNT = "cortana_token_refresh_retry_count";
    String CORTANA_LAST_TOKEN_REFRESH_TRY_TIMESTAMP_MILLIS = "cortana_last_token_refresh_try_timestamp_millis";
    String CORTANA_LAST_SUCCESSFUL_TOKEN_REFRESH_TRY_TIMESTAMP_MILLIS = "cortana_last_successful_token_refresh_try_timestamp_millis";
    String CORTANA_ADMIN_POLICY_REFRESH_TIMESTAMP_MILLIS = "cortana_admin_policy_refresh_timestamp_millis";
    String CORTANA_LAST_HEART_BEAT_SENT_TIMESTAMP_MILLIS = "cortana_last_heart_beat_sent_timestamp_millis";
    String CORTANA_HEART_BEAT_DATA = "cortana_heart_beat_data";
    String CORTANA_INVOCATION_MODE = "cortana_invocation_mode";
    String CORTANA_HOST_NAME = "cortana_host_name";
    String CORTANA_AUDIO_ENABLED_KEY = "cortana_audio_enable";
    String CORTANA_VOICE_FONT = "cortana_voice_font";

    // Keys for Cortana debug settings
    String CORTANA_QUALITY_OVERRIDE_KEY = "key_cortana_quality_override";
    String CORTANA_SDK_HTTP_PROXY_PORT = "key_cortana_sdk_http_proxy_port";
    String CORTANA_SDK_HTTP_PROXY_ADDRESS = "key_cortana_sdk_http_proxy_address";
    String CORTANA_AUDIO_DUMP_ENABLED_KEY = "key_cortana_audio_dump_enabled";

    // Cortana FRE
    String CORTANA_FRE_VISIBLE_TO_USER = "fre_visible_to_user";
    String LAST_SIGNIN_TIMESTAMP_MILLIS = "last_signin_timestamp_millis_";
    String CORTANA_LAST_FRE_DISMISSED_TIMESTAMP_MILLIS = "last_fre_dismissed_timestamp_millis_";
    String CORTANA_LAST_FRE_SHOWED_TIMESTAMP_MILLIS = "last_fre_showed_timestamp_millis";
    String USER_PERFORMED_ACTION = "user_performed_action_";
    String USER_SEEN_SPEECH_RECOGNITION_CONSENT = "user_consented_cortana_";
    String USER_CONSENTED_SPEECH_RECOGNITION = "user_accepted_speech_recognition_consent";
    String USER_SPEECH_LOGGING_CONSENT = "user_speech_logging_consent";
    String USER_SEEN_CORTANA_FRE_COUNT = "user_seen_cortana_fre_count";
    String CORTANA_FRE_TRIGGER_MODE = "cortana_fre_trigger_mode";
    String USER_SEEN_SPEECH_LOGGING_CONSENT = "user_seen_speech_logging_consent_";
    String SPEECH_LOGGING_CONSENT_ACCEPTED_VERSION = "speech_logging_consent_accepted_version";
    String IS_CORTANA_FRE_BANNER_ACTIVE = "is_cortana_fre_banner_active";
    String HAS_ENABLED_CORTANA_BEFORE = "has_enabled_cortana_before";
    String HAS_USER_SEEN_SAFETY_NOTICE = "has_user_seen_safety_notice";
    String HAS_FRE_BEEN_STARTED = "has_fre_been_started";
    String IS_CORTANA_FRE_FINISHED = "is_cortana_fre_finished";
    String USER_LAST_REJECT_IN_VIEW_KWS_CONSENT_TIMESTAMP = "user_last_reject_in_view_kws_consent_timestamp";
    String HAS_USER_SEEN_IN_VIEW_KWS_CONSENT = "has_user_seen_in_view_kws_consent";
    String HAS_USER_SEEN_BEFORE_VIEW_KWS_CONSENT = "has_user_seen_before_view_kws_consent";
    String HAS_FIRST_SESSION_STARTED = "has_first_session_started";
    String HAS_FIRST_SESSION_FINISHED = "has_first_session_finished";
    String HAS_ONE_MORE_SESSION_STARTED = "has_one_more_session_started";
    String HAS_ONE_MORE_SESSION_FINISHED = "has_one_more_session_finished";
    String CORTANA_LAST_SEARCH_ENTRY_COACH_MARK_SHOWED_TIMESTAMP_MILLIS = "cortana_last_search_entry_coach_mark_showed_timestamp_millis";
    String CORTANA_USER_SEEN_SEARCH_ENTRY_COACH_MARK_COUNT = "cortana_user_seen_search_entry_coach_mark_count";

    /** Pinned Channels */
    String PINNED_CHANNELS_METADATA = "pinned_channels_metadata";
    String PINNED_CHANNELS_HEADER_COLLAPSE_STATE_PINNED_CHANNELS = "pinned_channels_collapsed_state_pinned_channels";
    String PINNED_CHANNELS_HEADER_COLLAPSE_STATE_TEAMS = "pinned_channels_collapsed_state_teams";
    String PINNED_CHANNELS_HEADER_COLLAPSE_STATE_UNIFIED_CHANNELS = "pinned_channels_collapsed_state_unified_channels";
    String PINNED_CHANNELS_HEADER_COLLAPSE_STATE_UNIFIED_CHATS = "pinned_channels_collapsed_state_unified_chats";
    String PINNED_CHANNELS_UNIFIED_MIGRATION_STATUS = "pinned_channels_unified_migration_status";
    String PINNED_CHANNELS_SHOULD_SKIP_AUTO_PIN = "pinned_channels_should_skip_auto_pin";

    /** Pinned Chats */
    String PINNED_CHATS = "Pinned_Chats";
    String PINNED_CHATS_ENABLED = "Pinned_Chats_Enabled";

    /** Bottom Navigation Bar tab */
    String BOTTOM_NAVIGATION_BAR_TAB_SELECTED = "bottom_navigation_bar_tab_selected";

    //RNL keys
    String OUTLOOK_CONTACT_SYNC_TOKEN = "outlookContactSyncToken";
    String OUTLOOK_CONTACT_SYNC_TIME = "outlookContactSyncTime";

    /** Floodgate - NPS settings */
    String USER_FIRST_LOGIN_TIME = "User_First_Login_Time";

    //Contact keys
    String CONTACT_SYNC_TIME = "contactSyncTime";
    String CONTACT_SYNC_STATUS = "contactSyncStatus";

    //Contact Groups (Buddy List)
    String CONTACT_GROUPS_LAST_SYNC_TIME = "contactGroupSyncTime";

    String USER_UPDATED_THEIR_PROFILE_PHOTO = "user_updated_their_profile_photo";

    //Searchability stamping check
    String SEARCHABILITY_STAMPING_CHECKED = "searchabilityStampingChecked";

    // One string property that contains serialized JSON configuration of DataSourceRegistry
    String DATA_SOURCE_REGISTRY_STATE = "data_source_registry_state";

    // Developer preview
    String DEVELOPER_PREVIEW_ENABLED = "developerPreviewEnabled";

    // Performance optimizations
    String LAST_TEAMS_ORDER_FETCH_TIME = "lastTeamsOrderFetchTime";

    // Pending Members
    String HAS_SEEN_ACTIVATE_TEAM_COACHMARK = "has_seen_activate_team_coachmark";

    String HAS_SEEN_GIPHY_PICKER_COACHMARK = "has_seen_giphy_picker_coachmark";

    // Dismissals of status banner
    String STATUS_BANNER_DISMISSALS = "statusBannerDismissals";

    // E911
    String EMERGENCY_LOCATION_INFO = "emergencyLocationInfo";
    String LAST_EMERGENCY_LOCATION_UPDATE_REQUEST_TICKS = "lastEmergencyLocationUpdateRequestTicks";

    // Device address book contact sync

    String ADDRESS_BOOK_SYNC_STATUS = "addressBookSync";

    String ADDRESS_BOOK_CHANGES_DIRTY_BIT = "addressBookChangesDirtyBit";
    String ADDRESS_BOOK_SYNC_TIMESTAMP = "addressBookSyncTimestamp";
    String ADDRESS_BOOK_SYNC_DIALOG = "addressBookSyncDialog";

    // Better Together Preferences
    String BETTER_TOGETHER_ENDPOINT = "better_together_endpoint";
    String BETTER_TOGETHER_PAIRED_ENDPOINT = "better_together_paired_endpoint";
    String BETTER_TOGETHER_PAIRED_ENDPOINT_LAST_ACTIVE_TIME = "better_together_paired_endpoint_last_active_time";
    String BETTER_TOGETHER_SELF_ENDPOINT_LAST_CHECK_TIME = "better_together_self_endpoint_last_check_time";

    // Now
    String NOW_CONSUMPTION_HORIZON = "nowConsumptionHorizon";

    // Channel Follow/Notifications
    String CHANNEL_AUTO_FOLLOW_USER_CHOICE = "ChannelAutoFollowUserChoice";
    String CHANNEL_AUTO_FOLLOW_WORKING_SET = "ChannelAutoFollowWorkingSet";
    String CHANNEL_AUTO_FOLLOW_RETRY_SET = "ChannelAutoFollowRetrySet";
    String CHANNEL_AUTO_FOLLOW_NUMBER_OF_RETRIES = "ChannelAutoFollowNumberOfRetries";

    String VAULT_PREFERENCES = "vault_user_pref";
    String VAULT_USER_PRIVATE_KEY = "vault_user_private_key";
    String VAULT_USER_PUBLIC_KEY = "vault_user_public_key";
    String VAULT_USER_KEYBUNDLEVERSION = "vault_user_keybundle_version";
    String VAULT_USER_CLIENT_KEY = "vault_user_client_key";

    String FEATURES_NEW_COMPOSE_ENABLED = "Features_New_Compose_Enabled";
    String IS_USER_PINNING_DISABLED = "Is_User_Pinning_Disabled";

    String OCPS_BASE_URL = "OneCloudPolicyServiceBaseUrl";

    // User Presence validation
    String PRESENCE_OFF_SHIFT_DID_USER_CONFIRM_DIALOG_DURING_SHIFT = "didUserConfirmDialogDuringShift";
    String CURRENT_OFF_SHIFT_START_TIME = "currentOffShiftStartTime";
    String CURRENT_OFF_SHIFT_END_TIME = "currentOffShiftEndTime";

    // Translation
    String TRANSLATION_PREFERRED_LANGUAGE_ID = "TranslationPreferredLanguageId";
    String TRANSLATION_UNDERSTOOD_LANGUAGE_IDS = "TranslationUnderstoodLanguageIDs";
    String TRANSLATION_SUPPORTED_LANGUAGES = "TranslationSupportedLanguages";
    String TRANSLATION_SUPPORTED_LANGUAGES_LOCALE = "TranslationSupportedLanguagesLocale";
    String TRANSLATION_SUPPORTED_LANGUAGES_NEEDS_SYNCING = "TranslationSupportedLanguagesNeedsSyncing";
    String TRANSLATION_SUPPORTED_LANGUAGES_LAST_SYNC_TIME = "TranslationSupportedLanguagesLastSyncTime";
    String TRANSLATION_DEFAULT_UI_LANGUAGE_ID = "TranslationDefaultUILanguageId";

    //favorites tab
    String FAVORITES_GROUP_ID = "favorites_group_id";

    //IP Phone Hotdesking
    String HOT_DESKING_TIMEOUT = "hotDeskingTimeout";

    // Off-network invite flow error banner
    String OFF_NETWORK_INVITE_FAILED_ERROR_KEY = "offNetworkInviteFailedErrorKey";

    //Location Sharing
    String LOCATION_SHARING_CONSENTED = "locationSharingConsented";
    String LOCATION_SHARING_PRIVACY_CONSENT_ASKED = "locationSharingPrivacyConsentAsked";
    String LOCATION_SHARING_PERMISSION_ASKED = "locationSharingPermissionAsked";
    String LOCATION_SHARING_MY_SESSIONS_SYNC_TIME = "locationSharingMySessionsSyncTime";

    // Notification settings
    String NOTIFICATION_PREFERENCES_MIGRATED = "Notificatoin_Preferences_Migrated";
    String CHATS_NOTIFICATION_SWITCH = "Chats_Notifications_Switch";
    String MENTIONS_NOTIFICATION_SWITCH = "Mentions_Notifications_Switch";
    String REPLIES_NOTIFICATION_SWITCH = "Replies_Notifications_Switch";
    String LIKES_NOTIFICATION_SWITCH = "Likes_Notifications_Switch";
    String APPS_NOTIFICATION_SWITCH = "Third_Party_Notifications_Switch";
    String OTHER_ALERTS_NOTIFICATION_SWITCH = "Other_Alerts_Notifications_Switch";
    String INFERRED_ALERTS_NOTIFICATION_SWITCH = "Inferred_Alerts_Notifications_Switch";
    String SEND_NOTIFICATIONS_ONLY_WHEN_ACTIVE = "Send_Notifications_Only_When_Active";
    String SEND_NOTIFICATIONS_DEBUG = "Send_Notifications_Debug";
    String VIBRATE_NOTIFICATIONS_SWITCH = "Vibrate_Notifications_Switch";
    String WHEN_IN_MEETING_SWITCH = "When_In_Meeting_Switch";
    String CHANNELS_NOTIFICATION_SWITCH = "Channels_Notification_Switch";
    String CALLS_NOTIFICATION_SWITCH = "Calls_Notification_Switch";

    // Quiet hours
    String QUIET_HOURS_START = "QUIET_HOURS_START";
    String QUIET_HOURS_END = "QUIET_HOURS_END";
    String QUIET_HOURS_DAILY_HOURS_ENABLED = "QUIET_HOURS_DAILY_HOURS_ENABLED";
    String QUIET_HOURS_QUIET_DAYS_ENABLED = "QUIET_HOURS_QUIET_DAYS_ENABLED";
    String QUIET_HOURS_QUIET_DAYS = "QUIET_HOURS_QUIET_DAYS";
    String QUIET_HOURS_SUPPRESSED_NOTIFICATION_COUNT = "QUIET_HOURS_SUPPRESSED_NOTIFICATION_COUNT";
    String QUIET_HOURS_HAS_SUPPRESSED_CHAT_MESSAGE = "QUIET_HOURS_HAS_SUPPRESSED_CHAT_MESSAGE";
    String QUIET_TIME_ALLOW_INCOMING_CALLS = "QUIET_TIME_ALLOW_INCOMING_CALLS";
    String QUIET_TIME_ALLOW_IMPORTANT_MESSAGES = "QUIET_TIME_ALLOW_IMPORTANT_MESSAGES";
    String QUIET_TIME_ALLOW_MENTIONS = "QUIET_TIME_ALLOW_MENTIONS";
    String QUIET_TIME_ALLOW_BADGE_COUNTS = "QUIET_TIME_ALLOW_BADGE_COUNTS";

    //Substrate search service debug settings
    String SERVICE_BASE_URL_SUBSTRATE_SEARCH = "service_base_url_3s";
    String CLIENT_FLIGHTS_SUBSTRATE_SEARCH = "client_flights_3s";
    String SERVER_FLIGHTS_SUBSTRATE_SEARCH = "server_flights_3s";
    String QUERY_FLIGHTS_SUBSTRATE_SEARCH = "query_flights_3s";
    String SPELLER_SUBSTRATE_SEARCH = "speller_3s";
    String MESSAGE_QUERY_TOGGLE_SUBSTRATE_SEARCH = "message_query_toggle_3s";
    String MESSAGE_QUERY_SUBSTRATE_SEARCH = "message_query_3s";

    // Kingston
    String WALLPAPER_V2 = "wallpaperV2";
    String DEVICE_LOCK_TIME_OUT_MILLIS = "device_lock_time_out_millis";

    // Teams Panels
    String LED_UNAVAILBLE_COLOR = "LED_UNAVAILABLE_COLOR";
    String WALLPAPER = "WALLPAPER";

    // Background Sync Service
    String BACKGROUND_SYNC_SERVICE_RUN_INTERVAL_MINS = "background_sync_service_run_interval_mins";

    //Key for call default view options
    String IP_PHONES_CALL_DEFAULT_VIEW = "call_default_view";
    String IS_PHONE_NUMBER_ONLY = "is_phone_number_only";
    String IS_PHONE_NUMBER_UPN = "is_phone_number_upn";

    // IP phone auto accept meeting nudges
    String CALL_AUTO_ACCEPT_MEETING_NUDGES = "call_auto_accept_meeting_nudges";
    String CALL_AUTO_ACCEPT_WITH_VIDEO = "call_auto_accept_with_video";

    // OneDrive Provisioning.
    String ONEDRIVE_PROVISIONING = "onedrive_provisioning";

    //IP phone sidecar
    String GROUPS_TO_SHOW_IN_SIDECAR = "groups_to_show_in_sidecar";
    String HAS_SIDECAR_PRESENTATION = "has_valid_presentation";
    String SHOWED_SIDECAR_FRE_BEFORE = "showed_sidecar_fre_before";

    // Dashboard Coach Mark
    String DASHBOARD_COACH_MARK_DISPLAYED = "dashboardCoachMarkDisplayed";

    //Cloud Setting Login view options
    String CLOUD_SETTING_LOGIN_SELECTED = "cloudSettingLoginEnabled";

    // last Computed UserEntitlenment Hash for personnel apps
    String LAST_COMPUTED_USER_ENTITLEMENT_HASH = "lastComputedUserEntitlenmentHash";

    // Token prefetch data
    String TOKEN_FETCH_TIMESTAMP = "token_fetch_timestamp";

    // Key for long press tip of smart reply
    String CLICK_SMART_REPLY_COUNT = "Conversations_ClickSmartReplyCount";
    String SHOW_LONG_PRESS_TIP = "Conversations_ShowLongPressTip";
    // Extended emoji
    String EXTENDED_EMOJI_METADATA_ID = "extendedEmojiMetadataId";
    // Last sw version which sent the notification filter settings
    String LAST_SENT_TELEMETRY_SW_VERSION = "last_sent_telemetry_sw_version";
}
