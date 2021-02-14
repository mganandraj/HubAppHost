package com.microsoft.teams.core.models;

import androidx.annotation.StringDef;

// import com.microsoft.teams.theme.utils.ThemeSettingUtil;

import static com.microsoft.teams.core.models.GlobalPreferences.AGGREGATE_MESSAGE_RECEIVED_ON_LONG_POLL;
import static com.microsoft.teams.core.models.GlobalPreferences.ANONYMOUS_MEETING_EXIT;
import static com.microsoft.teams.core.models.GlobalPreferences.ANONYMOUS_USER_NAME_HINT;
import static com.microsoft.teams.core.models.GlobalPreferences.APPS_NOTIFICATION_SWITCH;
import static com.microsoft.teams.core.models.GlobalPreferences.APP_INSTALLED_DATE;
import static com.microsoft.teams.core.models.GlobalPreferences.APP_SETTINGS_USERNAME_MRI_LIST_MAP;
import static com.microsoft.teams.core.models.GlobalPreferences.ARIA_COLLECTOR_URL_NON_GLOBAL;
import static com.microsoft.teams.core.models.GlobalPreferences.AUTHENTICATED_USER;
import static com.microsoft.teams.core.models.GlobalPreferences.AUTHENTICATED_USER_LIST;
import static com.microsoft.teams.core.models.GlobalPreferences.AUTH_USER_KEY;
import static com.microsoft.teams.core.models.GlobalPreferences.AUTO_PRUNE_COMPLETED_TIME;
import static com.microsoft.teams.core.models.GlobalPreferences.BACKGROUND_INACTIVE_COUNTER;
import static com.microsoft.teams.core.models.GlobalPreferences.BADGE_COUNT_SERVICE_ENDPOINT_ID;
import static com.microsoft.teams.core.models.GlobalPreferences.BADGE_COUNT_SERVICE_NUMBER_OF_ACCOUNTS_REGISTERED;
import static com.microsoft.teams.core.models.GlobalPreferences.BADGE_COUNT_SERVICE_PAYLOAD;
import static com.microsoft.teams.core.models.GlobalPreferences.BADGE_COUNT_SERVICE_PUSH_NOTIFICATION_TIMESTAMP;
import static com.microsoft.teams.core.models.GlobalPreferences.BADGE_COUNT_SERVICE_PUSH_NOTIFICATION_UPDATE;
import static com.microsoft.teams.core.models.GlobalPreferences.BADGE_COUNT_SERVICE_REGISTRATION_REQUEST_TICKS;
import static com.microsoft.teams.core.models.GlobalPreferences.BADGE_COUNT_SERVICE_REGISTRATION_SUCCESSFUL;
import static com.microsoft.teams.core.models.GlobalPreferences.BADGE_COUNT_SERVICE_RESPONSE_PAYLOAD;
import static com.microsoft.teams.core.models.GlobalPreferences.BADGE_COUNT_SERVICE_TOTAL_PILL_COUNT;
import static com.microsoft.teams.core.models.GlobalPreferences.BOUNCE_APP_DRAWER;
import static com.microsoft.teams.core.models.GlobalPreferences.BRB_LAST_RETRY_TIME;
import static com.microsoft.teams.core.models.GlobalPreferences.BROADCAST_TIMEZONE_ENABLED;
import static com.microsoft.teams.core.models.GlobalPreferences.CALLS_MISSED_NOTIFICATION_SWITCH;
import static com.microsoft.teams.core.models.GlobalPreferences.CALLS_NOTIFICATIONS_SWITCH;
import static com.microsoft.teams.core.models.GlobalPreferences.CHANNELS_NOTIFICATIONS_SWITCH;
import static com.microsoft.teams.core.models.GlobalPreferences.CHATS_NOTIFICATION_SWITCH;
import static com.microsoft.teams.core.models.GlobalPreferences.CORE_TASK_FINISHED_KEY;
import static com.microsoft.teams.core.models.GlobalPreferences.CUSTOM_LOGOS;
import static com.microsoft.teams.core.models.GlobalPreferences.DB_MIGRATION_REQUIRED;
import static com.microsoft.teams.core.models.GlobalPreferences.DB_QUERY_LOGGING_ENABLED;
import static com.microsoft.teams.core.models.GlobalPreferences.DB_UPDATE_METADATA_SETTING_KEY;
import static com.microsoft.teams.core.models.GlobalPreferences.DEFAULT_SHARE_TARGET;
import static com.microsoft.teams.core.models.GlobalPreferences.DEVICE_LOGIN_CLOUD_SELECTION;
import static com.microsoft.teams.core.models.GlobalPreferences.DEVICE_LOGIN_PROVISIONED_TENANT_ID;
import static com.microsoft.teams.core.models.GlobalPreferences.DEVICE_LOGIN_PROVISIONED_TENANT_NAME;
import static com.microsoft.teams.core.models.GlobalPreferences.DF_POP_UP_LAST_SHOWN;
import static com.microsoft.teams.core.models.GlobalPreferences.DISABLE_JOB_SCHEDULER_SEND_MESSAGE;
import static com.microsoft.teams.core.models.GlobalPreferences.DISABLE_MSAL_OPTIMIZATIONS;
import static com.microsoft.teams.core.models.GlobalPreferences.DISPLAY_TFL_TEAMS_BANNER;
import static com.microsoft.teams.core.models.GlobalPreferences.DLP_ALERTS_NOTIFICATION_SWITCH;
import static com.microsoft.teams.core.models.GlobalPreferences.DONT_SHOW_AGAIN_CHOSEN_BY_USER_CAMERA;
import static com.microsoft.teams.core.models.GlobalPreferences.DONT_SHOW_AGAIN_CHOSEN_BY_USER_CONTACTS;
import static com.microsoft.teams.core.models.GlobalPreferences.DONT_SHOW_AGAIN_CHOSEN_BY_USER_MIC;
import static com.microsoft.teams.core.models.GlobalPreferences.ECS_APP_VERSION;
import static com.microsoft.teams.core.models.GlobalPreferences.ENABLE_CUSTOMER_DATA_SCANNER;
import static com.microsoft.teams.core.models.GlobalPreferences.ENABLE_INSTRUMENTATION_NOTIFICATIONS;
import static com.microsoft.teams.core.models.GlobalPreferences.ENABLE_PANEL_ACTION_INSTRUMENTATION_NOTIFICATIONS;
import static com.microsoft.teams.core.models.GlobalPreferences.ENABLE_PANEL_VIEW_INSTRUMENTATION_NOTIFICATIONS;
import static com.microsoft.teams.core.models.GlobalPreferences.ENABLE_SCENARIO_INSTRUMENTATION_NOTIFICATIONS;
import static com.microsoft.teams.core.models.GlobalPreferences.ENABLE_TROUBLESHOOT_SCREEN;
import static com.microsoft.teams.core.models.GlobalPreferences.FEATURES_APP_RATING_ENABLED;
import static com.microsoft.teams.core.models.GlobalPreferences.FEATURES_ARIA_DISBLED;
//import static com.microsoft.teams.core.models.GlobalPreferences.FEATURES_DARK_THEME_ENABLED;
import static com.microsoft.teams.core.models.GlobalPreferences.FEATURES_HOST_DARK_THEME_ENABLED;
//import static com.microsoft.teams.core.models.GlobalPreferences.FEATURES_PLAYGROUND_THEME_ENABLED;
import static com.microsoft.teams.core.models.GlobalPreferences.FEATURES_SYNC_DISBLED;
import static com.microsoft.teams.core.models.GlobalPreferences.FIRST_SIGNIN_SUCCEEDED;
import static com.microsoft.teams.core.models.GlobalPreferences.FIRST_TIME_APP_LAUNCH;
import static com.microsoft.teams.core.models.GlobalPreferences.FIRST_TIME_ENTER_MAIN_SCREEN;
import static com.microsoft.teams.core.models.GlobalPreferences.FIRST_TIME_INSTALL;
import static com.microsoft.teams.core.models.GlobalPreferences.FIRST_TIME_INSTALL_DEEP_LINK;
import static com.microsoft.teams.core.models.GlobalPreferences.FIRST_TIME_USER_ENTER_APP;
import static com.microsoft.teams.core.models.GlobalPreferences.FOLLOWED_NOTIFICATION_SWITCH;
import static com.microsoft.teams.core.models.GlobalPreferences.FORCE_AUTO_PRUNE_REQUIRED;
import static com.microsoft.teams.core.models.GlobalPreferences.FORCE_UPDATE_NON_GLOBAL_ENDPOINTS;
import static com.microsoft.teams.core.models.GlobalPreferences.FPS_COUNTER_ENABLED;
import static com.microsoft.teams.core.models.GlobalPreferences.FRE_CONTACTS_PERMISSION_POP_UP_SHOWN;
import static com.microsoft.teams.core.models.GlobalPreferences.FRE_EXPERIENCED_USERS_KEY;
import static com.microsoft.teams.core.models.GlobalPreferences.GLOBAL_NOTIFICATIONS_ENDPOINT_ID;
import static com.microsoft.teams.core.models.GlobalPreferences.HOT_DESK_HOST_USER_ID;
import static com.microsoft.teams.core.models.GlobalPreferences.INCOMING_CALLS_SWITCH;
import static com.microsoft.teams.core.models.GlobalPreferences.INFERRED_ALERTS_NOTIFICATION_SWITCH;
import static com.microsoft.teams.core.models.GlobalPreferences.IS_FORCE_AUTO_PRUNE_FOR_OPTIMIZATION;
import static com.microsoft.teams.core.models.GlobalPreferences.IS_LOCATION_PERMISSION_REQUESTED;
import static com.microsoft.teams.core.models.GlobalPreferences.JOB_DEBUG_MESSAGE_STALE_MILLIS;
import static com.microsoft.teams.core.models.GlobalPreferences.JOB_DEBUG_NOTIFICATION;
import static com.microsoft.teams.core.models.GlobalPreferences.KEY_DEVICE_ID_DEBUG;
import static com.microsoft.teams.core.models.GlobalPreferences.LAST_ANONYMOUS_MEETING_URL;
import static com.microsoft.teams.core.models.GlobalPreferences.LAST_APP_RESTART_TIME;
import static com.microsoft.teams.core.models.GlobalPreferences.LAST_DB_RESET_TIME;
import static com.microsoft.teams.core.models.GlobalPreferences.LAST_SIGNIN_TIMESTAMP_MILLIS;
import static com.microsoft.teams.core.models.GlobalPreferences.LAST_TENANT_NOTIFICATION_SYNC_TIME;
import static com.microsoft.teams.core.models.GlobalPreferences.LAST_TIME_APP_IN_BACKGROUND;
import static com.microsoft.teams.core.models.GlobalPreferences.LAST_TIME_APP_IN_FOREGROUND;
import static com.microsoft.teams.core.models.GlobalPreferences.LAST_TIME_COUNTER_RESET;
import static com.microsoft.teams.core.models.GlobalPreferences.LEAK_CANARY_ENABLED;
import static com.microsoft.teams.core.models.GlobalPreferences.LIKES_NOTIFICATION_SWITCH;
import static com.microsoft.teams.core.models.GlobalPreferences.LOGGED_IN_ANONYMOUS_THREAD_ID;
import static com.microsoft.teams.core.models.GlobalPreferences.LOGIN_HINT_KEY;
import static com.microsoft.teams.core.models.GlobalPreferences.LOG_CLOUDTYPE_ACCOUNTTYPE_LOGCAT_DISABLED;
import static com.microsoft.teams.core.models.GlobalPreferences.MAM_AUTHORITY_URL_KEY;
import static com.microsoft.teams.core.models.GlobalPreferences.MAM_IDENTITY_KEY;
import static com.microsoft.teams.core.models.GlobalPreferences.MAM_REENROLLMENT_KEY;
import static com.microsoft.teams.core.models.GlobalPreferences.MEDIA_QUALITY;
import static com.microsoft.teams.core.models.GlobalPreferences.MENTIONS_NOTIFICATION_SWITCH;
import static com.microsoft.teams.core.models.GlobalPreferences.MESSAGES_PROCESSED_FOR_NOTIFICATION;
import static com.microsoft.teams.core.models.GlobalPreferences.MODULE_REFRESH_NEEDED;
import static com.microsoft.teams.core.models.GlobalPreferences.MSAL_ENABLED;
import static com.microsoft.teams.core.models.GlobalPreferences.NON_GLOBAL_ENDPOINTS;
import static com.microsoft.teams.core.models.GlobalPreferences.NOTIFICATION_ENCRYPTION_ASE_KEY;
import static com.microsoft.teams.core.models.GlobalPreferences.NOTIFICATION_ENCRYPTION_AUTH_KEY;
import static com.microsoft.teams.core.models.GlobalPreferences.NOTIFICATION_ENCRYPTION_CRYPTO_METHOD;
import static com.microsoft.teams.core.models.GlobalPreferences.NOTIFICATION_ENCRYPTION_ASE_KEY_GENERATION_TIME;
import static com.microsoft.teams.core.models.GlobalPreferences.NOTIFICATION_BG_TELEMETRY_LAST_LOGGED;
import static com.microsoft.teams.core.models.GlobalPreferences.NOTIFICATION_BLOCKER_POP_UP_COUNT;
import static com.microsoft.teams.core.models.GlobalPreferences.NOTIFICATION_FG_TELEMETRY_LAST_LOGGED;
import static com.microsoft.teams.core.models.GlobalPreferences.NOTIFICATION_SOUND_URI;
import static com.microsoft.teams.core.models.GlobalPreferences.NUM_OF_APP_LAUNCHES_KEY;
import static com.microsoft.teams.core.models.GlobalPreferences.OLD_SHARED_PREFS_CLEANUP_NEEDED;
import static com.microsoft.teams.core.models.GlobalPreferences.OTHER_ALERTS_NOTIFICATION_SWITCH;
import static com.microsoft.teams.core.models.GlobalPreferences.PEOPLE_BANNER_CLICKED;
import static com.microsoft.teams.core.models.GlobalPreferences.PERFORMANCE_MONITORING_ENABLED;
import static com.microsoft.teams.core.models.GlobalPreferences.PERSISTED_MARKET_COUNTRY;
import static com.microsoft.teams.core.models.GlobalPreferences.PERSISTED_MARKET_LANGUAGE;
import static com.microsoft.teams.core.models.GlobalPreferences.PREFERRED_CLIENT_PROMPT_OPTION;
import static com.microsoft.teams.core.models.GlobalPreferences.PREFERRED_TRANSLATION_SETTINGS_OPTION;
import static com.microsoft.teams.core.models.GlobalPreferences.PRIORITY_ALERTS_NOTIFICATION_SWITCH;
import static com.microsoft.teams.core.models.GlobalPreferences.QUIET_HOURS_DAILY_HOURS_ENABLED;
import static com.microsoft.teams.core.models.GlobalPreferences.QUIET_HOURS_END;
import static com.microsoft.teams.core.models.GlobalPreferences.QUIET_HOURS_HAS_SUPPRESSED_CHAT_MESSAGE;
import static com.microsoft.teams.core.models.GlobalPreferences.QUIET_HOURS_QUIET_DAYS;
import static com.microsoft.teams.core.models.GlobalPreferences.QUIET_HOURS_QUIET_DAYS_ENABLED;
import static com.microsoft.teams.core.models.GlobalPreferences.QUIET_HOURS_START;
import static com.microsoft.teams.core.models.GlobalPreferences.QUIET_HOURS_SUPPRESSED_NOTIFICATION_COUNT;
import static com.microsoft.teams.core.models.GlobalPreferences.QUIET_TIME_ALLOW_BADGE_COUNTS;
import static com.microsoft.teams.core.models.GlobalPreferences.QUIET_TIME_ALLOW_IMPORTANT_MESSAGES;
import static com.microsoft.teams.core.models.GlobalPreferences.QUIET_TIME_ALLOW_INCOMING_CALLS;
import static com.microsoft.teams.core.models.GlobalPreferences.QUIET_TIME_ALLOW_MENTIONS;
import static com.microsoft.teams.core.models.GlobalPreferences.REDUCE_DATA_USAGE_FOR_VIDEO_CALL;
import static com.microsoft.teams.core.models.GlobalPreferences.REPLIES_NOTIFICATION_SWITCH;
import static com.microsoft.teams.core.models.GlobalPreferences.SAVED_RECENT_MEETING_CODES;
import static com.microsoft.teams.core.models.GlobalPreferences.SHOULD_SAVE_RECENT_MEETING_CODES;
import static com.microsoft.teams.core.models.GlobalPreferences.SEEN_MORE_TAB_COACHMARK;
import static com.microsoft.teams.core.models.GlobalPreferences.SEND_MESSAGE_UNIQUE_CONVERSATION_IDS;
import static com.microsoft.teams.core.models.GlobalPreferences.SEND_NOTIFICATIONS_DEBUG;
import static com.microsoft.teams.core.models.GlobalPreferences.SEND_NOTIFICATIONS_ONLY_WHEN_ACTIVE;
import static com.microsoft.teams.core.models.GlobalPreferences.SHAKE_AND_SEND_DISMISS_COUNT;
import static com.microsoft.teams.core.models.GlobalPreferences.SHAKE_AND_SEND_ENABLED;
import static com.microsoft.teams.core.models.GlobalPreferences.SHOULD_FORCE_NETWORK_BROADCAST;
import static com.microsoft.teams.core.models.GlobalPreferences.SHOW_DEBUG_ELEMENTS;
import static com.microsoft.teams.core.models.GlobalPreferences.SHOW_PRESENCE_DEBUG_INFO;
import static com.microsoft.teams.core.models.GlobalPreferences.SIGNED_OUT_USERS;
import static com.microsoft.teams.core.models.GlobalPreferences.SIGNED_SUCCESSFUL_ACCOUNTS;
import static com.microsoft.teams.core.models.GlobalPreferences.SKIP_APP_RATING_THRESHOLD_DATE;
import static com.microsoft.teams.core.models.GlobalPreferences.SKYLIB_INIT_DISBLED;
import static com.microsoft.teams.core.models.GlobalPreferences.SKYPE_TOKEN_REFRESH_JOB_RAN;
import static com.microsoft.teams.core.models.GlobalPreferences.SYSTEM_INITIATED_DB_RESET;
import static com.microsoft.teams.core.models.GlobalPreferences.TEAMS_FAKE_ANDROID_ID;
import static com.microsoft.teams.core.models.GlobalPreferences.TEAMS_ANR_LIST;
import static com.microsoft.teams.core.models.GlobalPreferences.TEAMS_FAKE_IMEI;
import static com.microsoft.teams.core.models.GlobalPreferences.TOU_LAST_VERSION_SEEN;
import static com.microsoft.teams.core.models.GlobalPreferences.TOU_URL;
import static com.microsoft.teams.core.models.GlobalPreferences.TRENDING_ALERTS_NOTIFICATION_SWITCH;
import static com.microsoft.teams.core.models.GlobalPreferences.UPDATED_GROUP_AVATARS;
import static com.microsoft.teams.core.models.GlobalPreferences.USER_ACCEPTED_PRIVACY_NOTICE;
import static com.microsoft.teams.core.models.GlobalPreferences.USER_CLICKED_FREEMIUM_MEETINGS_TAB;
import static com.microsoft.teams.core.models.GlobalPreferences.USER_CLICKED_MEETNOW_ICON;
import static com.microsoft.teams.core.models.GlobalPreferences.USER_CONSENTED_CORTANA;
import static com.microsoft.teams.core.models.GlobalPreferences.USER_DISLIKED_APP_KEY;
import static com.microsoft.teams.core.models.GlobalPreferences.USER_EXPERIENCED_SMB_BUSINESS_VOICE_FRE;
import static com.microsoft.teams.core.models.GlobalPreferences.USER_INITIATED_DB_RESET;
import static com.microsoft.teams.core.models.GlobalPreferences.USER_PERFORMED_ACTION;
import static com.microsoft.teams.core.models.GlobalPreferences.USER_RATED_APP_KEY;
import static com.microsoft.teams.core.models.GlobalPreferences.USER_SEEN_ALWAYS_NOTIFY_DIALOG;
import static com.microsoft.teams.core.models.GlobalPreferences.USER_SEEN_CORTANA_FRE;
import static com.microsoft.teams.core.models.GlobalPreferences.USER_SEEN_FREEMIUM_MEETINGS_WHATS_NEW;
import static com.microsoft.teams.core.models.GlobalPreferences.USER_SEEN_MEETNOW_MEETINGS_WHATS_NEW;
import static com.microsoft.teams.core.models.GlobalPreferences.USER_SEEN_READ_RECEIPTS_PRIVACY_NOTICE;
import static com.microsoft.teams.core.models.GlobalPreferences.USER_SEEN_READ_RECEIPTS_PRIVACY_NOTICE_UPGRADE_TO_CURRENT_VERSION;
import static com.microsoft.teams.core.models.GlobalPreferences.USER_SEEN_SMB_BUSINESS_VOICE_WHATS_NEW;
import static com.microsoft.teams.core.models.GlobalPreferences.USER_SKIPPED_APP_RATING_KEY;
import static com.microsoft.teams.core.models.GlobalPreferences.USER_SPEECH_LOGGING_CONSENT;
import static com.microsoft.teams.core.models.GlobalPreferences.VERSION_FOR_WHICH_CUSTOM_RESET_ALREADY_HAPPENED;
import static com.microsoft.teams.core.models.GlobalPreferences.VERSION_FOR_WHICH_RNAPP_DATA_SYNCED;
import static com.microsoft.teams.core.models.GlobalPreferences.VERSION_FOR_WHICH_SYNC_STATE_ALREADY_REMOVED;
import static com.microsoft.teams.core.models.GlobalPreferences.VIBRATE_NOTIFICATIONS_SWITCH;
import static com.microsoft.teams.core.models.GlobalPreferences.VOICEMAILS_NOTIFICATION_SWITCH;
import static com.microsoft.teams.core.models.GlobalPreferences.WHATS_NEW_BLOG_VISITED;
import static com.microsoft.teams.core.models.GlobalPreferences.WHATS_NEW_EXPERIENCE_NOTIFICATION_SCHEDULED;
import static com.microsoft.teams.core.models.GlobalPreferences.WHATS_NEW_EXPERIENCE_NOTIFICATION_SHOWN;
import static com.microsoft.teams.core.models.GlobalPreferences.WHATS_NEW_HAMBURGER_MENU_VISITED;
import static com.microsoft.teams.core.models.GlobalPreferences.WHATS_NEW_NOTIFICATION_SWITCH;
import static com.microsoft.teams.core.models.GlobalPreferences.WHEN_IN_MEETING_SWITCH;
import static com.microsoft.teams.core.models.GlobalPreferences.SEEN_MEET_NOW_COACHMARK;
import static com.microsoft.teams.core.models.GlobalPreferences.SEEN_CHANNEL_MEET_NOW_COACHMARK;
import static com.microsoft.teams.core.models.GlobalPreferences.DIAGNOSTIC_DATA_VIEWER_CONNECTION_STRING;
import static com.microsoft.teams.core.models.GlobalPreferences.CORTANA_SDK_VERSION;
import static com.microsoft.teams.core.models.GlobalPreferences.LAST_CRASH_TITLE;
import static com.microsoft.teams.core.models.GlobalPreferences.LAST_SENT_TELEMETRY_SW_VERSION;
import static com.microsoft.teams.core.models.GlobalPreferences.TENANT_HASH_TO_PROFILE_AVATAR_URL;

@StringDef(value = {APP_INSTALLED_DATE,
        APP_SETTINGS_USERNAME_MRI_LIST_MAP,
        ANONYMOUS_USER_NAME_HINT,
        AUTHENTICATED_USER,
        AUTHENTICATED_USER_LIST,
        SIGNED_OUT_USERS,
        AUTH_USER_KEY,
        FRE_CONTACTS_PERMISSION_POP_UP_SHOWN,
        BADGE_COUNT_SERVICE_PAYLOAD,
        BADGE_COUNT_SERVICE_RESPONSE_PAYLOAD,
        BADGE_COUNT_SERVICE_PUSH_NOTIFICATION_TIMESTAMP,
        BADGE_COUNT_SERVICE_PUSH_NOTIFICATION_UPDATE,
        BADGE_COUNT_SERVICE_ENDPOINT_ID,
        BADGE_COUNT_SERVICE_REGISTRATION_SUCCESSFUL,
        BADGE_COUNT_SERVICE_TOTAL_PILL_COUNT,
        BADGE_COUNT_SERVICE_REGISTRATION_REQUEST_TICKS,
        BADGE_COUNT_SERVICE_NUMBER_OF_ACCOUNTS_REGISTERED,
        BOUNCE_APP_DRAWER,
        BRB_LAST_RETRY_TIME,
        CALLS_MISSED_NOTIFICATION_SWITCH,
        GLOBAL_NOTIFICATIONS_ENDPOINT_ID,
        CHATS_NOTIFICATION_SWITCH,
        CORE_TASK_FINISHED_KEY,
        DB_MIGRATION_REQUIRED,
        DB_UPDATE_METADATA_SETTING_KEY,
        DEFAULT_SHARE_TARGET,
        DISABLE_JOB_SCHEDULER_SEND_MESSAGE,
        DLP_ALERTS_NOTIFICATION_SWITCH,
        DONT_SHOW_AGAIN_CHOSEN_BY_USER_CAMERA,
        DONT_SHOW_AGAIN_CHOSEN_BY_USER_MIC,
        DONT_SHOW_AGAIN_CHOSEN_BY_USER_CONTACTS,
        ECS_APP_VERSION,
        ENABLE_CUSTOMER_DATA_SCANNER,
        ENABLE_TROUBLESHOOT_SCREEN,
        FEATURES_APP_RATING_ENABLED,
        SHOULD_SAVE_RECENT_MEETING_CODES,
        SAVED_RECENT_MEETING_CODES,
        FEATURES_ARIA_DISBLED,
//        FEATURES_DARK_THEME_ENABLED,
//        FEATURES_PLAYGROUND_THEME_ENABLED,
        FEATURES_HOST_DARK_THEME_ENABLED,
        FEATURES_SYNC_DISBLED,
        FIRST_SIGNIN_SUCCEEDED,
        SIGNED_SUCCESSFUL_ACCOUNTS,
        FIRST_TIME_INSTALL,
        FIRST_TIME_APP_LAUNCH,
        MSAL_ENABLED,
        FIRST_TIME_INSTALL_DEEP_LINK,
        FIRST_TIME_USER_ENTER_APP,
        FIRST_TIME_ENTER_MAIN_SCREEN,
        FOLLOWED_NOTIFICATION_SWITCH,
        FPS_COUNTER_ENABLED,
        FRE_EXPERIENCED_USERS_KEY,
        MESSAGES_PROCESSED_FOR_NOTIFICATION,
        INCOMING_CALLS_SWITCH,
        PREFERRED_TRANSLATION_SETTINGS_OPTION,
        INFERRED_ALERTS_NOTIFICATION_SWITCH,
        IS_LOCATION_PERMISSION_REQUESTED,
        JOB_DEBUG_MESSAGE_STALE_MILLIS,
        JOB_DEBUG_NOTIFICATION,
        KEY_DEVICE_ID_DEBUG,
        LAST_ANONYMOUS_MEETING_URL,
        LAST_DB_RESET_TIME,
        LAST_SIGNIN_TIMESTAMP_MILLIS,
        IS_FORCE_AUTO_PRUNE_FOR_OPTIMIZATION,
        AUTO_PRUNE_COMPLETED_TIME,
        FORCE_AUTO_PRUNE_REQUIRED,
        LEAK_CANARY_ENABLED,
        DB_QUERY_LOGGING_ENABLED,
        BACKGROUND_INACTIVE_COUNTER,
        LAST_TENANT_NOTIFICATION_SYNC_TIME,
        DISABLE_MSAL_OPTIMIZATIONS,
        LAST_TIME_APP_IN_BACKGROUND,
        LAST_TIME_APP_IN_FOREGROUND,
        LAST_TIME_COUNTER_RESET,
        SKYPE_TOKEN_REFRESH_JOB_RAN,
        LIKES_NOTIFICATION_SWITCH,
        LOG_CLOUDTYPE_ACCOUNTTYPE_LOGCAT_DISABLED,
        LOGGED_IN_ANONYMOUS_THREAD_ID,
        LOGIN_HINT_KEY,
        MAM_AUTHORITY_URL_KEY,
        MAM_IDENTITY_KEY,
        MAM_REENROLLMENT_KEY,
        MEDIA_QUALITY,
        REDUCE_DATA_USAGE_FOR_VIDEO_CALL,
        MENTIONS_NOTIFICATION_SWITCH,
        MODULE_REFRESH_NEEDED,
        NOTIFICATION_BLOCKER_POP_UP_COUNT,
        NOTIFICATION_SOUND_URI,
        NOTIFICATION_BG_TELEMETRY_LAST_LOGGED,
        NOTIFICATION_FG_TELEMETRY_LAST_LOGGED,
        NUM_OF_APP_LAUNCHES_KEY,
        CUSTOM_LOGOS,
        OLD_SHARED_PREFS_CLEANUP_NEEDED,
        OTHER_ALERTS_NOTIFICATION_SWITCH,
        PERFORMANCE_MONITORING_ENABLED,
        PERSISTED_MARKET_COUNTRY,
        PERSISTED_MARKET_LANGUAGE,
        PREFERRED_CLIENT_PROMPT_OPTION,
        PRIORITY_ALERTS_NOTIFICATION_SWITCH,
        QUIET_HOURS_START,
        QUIET_HOURS_END,
        QUIET_HOURS_DAILY_HOURS_ENABLED,
        QUIET_HOURS_QUIET_DAYS_ENABLED,
        QUIET_TIME_ALLOW_INCOMING_CALLS,
        QUIET_TIME_ALLOW_IMPORTANT_MESSAGES,
        QUIET_TIME_ALLOW_MENTIONS,
        QUIET_TIME_ALLOW_BADGE_COUNTS,
        QUIET_HOURS_QUIET_DAYS,
        QUIET_HOURS_SUPPRESSED_NOTIFICATION_COUNT,
        QUIET_HOURS_HAS_SUPPRESSED_CHAT_MESSAGE,
        REPLIES_NOTIFICATION_SWITCH,
        SEND_MESSAGE_UNIQUE_CONVERSATION_IDS,
        SEND_NOTIFICATIONS_DEBUG,
        SEND_NOTIFICATIONS_ONLY_WHEN_ACTIVE,
        SHAKE_AND_SEND_ENABLED,
        SHAKE_AND_SEND_DISMISS_COUNT,
        SHOW_DEBUG_ELEMENTS,
        SHOW_PRESENCE_DEBUG_INFO,
        SKYLIB_INIT_DISBLED,
        SKIP_APP_RATING_THRESHOLD_DATE,
        APPS_NOTIFICATION_SWITCH,
        TENANT_HASH_TO_PROFILE_AVATAR_URL,
        TRENDING_ALERTS_NOTIFICATION_SWITCH,
        TOU_LAST_VERSION_SEEN,
        TOU_URL,
        NON_GLOBAL_ENDPOINTS,
        FORCE_UPDATE_NON_GLOBAL_ENDPOINTS,
        USER_CONSENTED_CORTANA,
        USER_DISLIKED_APP_KEY,
        USER_INITIATED_DB_RESET,
        USER_PERFORMED_ACTION,
        USER_RATED_APP_KEY,
        USER_SKIPPED_APP_RATING_KEY,
        USER_SEEN_CORTANA_FRE,
        USER_ACCEPTED_PRIVACY_NOTICE,
        USER_SEEN_READ_RECEIPTS_PRIVACY_NOTICE,
        USER_SEEN_READ_RECEIPTS_PRIVACY_NOTICE_UPGRADE_TO_CURRENT_VERSION,
        USER_SEEN_ALWAYS_NOTIFY_DIALOG,
        USER_SPEECH_LOGGING_CONSENT,
        VIBRATE_NOTIFICATIONS_SWITCH,
        VOICEMAILS_NOTIFICATION_SWITCH,
        WHATS_NEW_NOTIFICATION_SWITCH,
        DEVICE_LOGIN_CLOUD_SELECTION,
        DEVICE_LOGIN_PROVISIONED_TENANT_ID,
        DEVICE_LOGIN_PROVISIONED_TENANT_NAME,
        WHATS_NEW_HAMBURGER_MENU_VISITED,
        WHATS_NEW_BLOG_VISITED,
        WHATS_NEW_EXPERIENCE_NOTIFICATION_SCHEDULED,
        WHATS_NEW_EXPERIENCE_NOTIFICATION_SHOWN,
        ARIA_COLLECTOR_URL_NON_GLOBAL,
        SHOULD_FORCE_NETWORK_BROADCAST,
        VERSION_FOR_WHICH_SYNC_STATE_ALREADY_REMOVED,
        VERSION_FOR_WHICH_CUSTOM_RESET_ALREADY_HAPPENED,
        ENABLE_INSTRUMENTATION_NOTIFICATIONS,
        ENABLE_SCENARIO_INSTRUMENTATION_NOTIFICATIONS,
        ENABLE_PANEL_VIEW_INSTRUMENTATION_NOTIFICATIONS,
        ENABLE_PANEL_ACTION_INSTRUMENTATION_NOTIFICATIONS,
        USER_CLICKED_FREEMIUM_MEETINGS_TAB,
        USER_CLICKED_MEETNOW_ICON,
        USER_EXPERIENCED_SMB_BUSINESS_VOICE_FRE,
        USER_SEEN_SMB_BUSINESS_VOICE_WHATS_NEW,
        USER_SEEN_FREEMIUM_MEETINGS_WHATS_NEW,
        USER_SEEN_MEETNOW_MEETINGS_WHATS_NEW,
        VERSION_FOR_WHICH_RNAPP_DATA_SYNCED,
        PEOPLE_BANNER_CLICKED,
        VERSION_FOR_WHICH_RNAPP_DATA_SYNCED,
        TEAMS_ANR_LIST,
        SEEN_MORE_TAB_COACHMARK,
        HOT_DESK_HOST_USER_ID,
        UPDATED_GROUP_AVATARS,
        NOTIFICATION_ENCRYPTION_ASE_KEY,
        NOTIFICATION_ENCRYPTION_AUTH_KEY,
        NOTIFICATION_ENCRYPTION_CRYPTO_METHOD,
        NOTIFICATION_ENCRYPTION_ASE_KEY_GENERATION_TIME,
        DISPLAY_TFL_TEAMS_BANNER,
        SYSTEM_INITIATED_DB_RESET,
        LAST_APP_RESTART_TIME,
        AGGREGATE_MESSAGE_RECEIVED_ON_LONG_POLL,
        LAST_APP_RESTART_TIME,
        BROADCAST_TIMEZONE_ENABLED,
        WHEN_IN_MEETING_SWITCH,
        AGGREGATE_MESSAGE_RECEIVED_ON_LONG_POLL,
        SEEN_MEET_NOW_COACHMARK,
        SEEN_CHANNEL_MEET_NOW_COACHMARK,
        CALLS_NOTIFICATIONS_SWITCH,
        CHANNELS_NOTIFICATIONS_SWITCH,
        ANONYMOUS_MEETING_EXIT,
        DIAGNOSTIC_DATA_VIEWER_CONNECTION_STRING,
        TEAMS_FAKE_IMEI,
        TEAMS_FAKE_ANDROID_ID,
        CORTANA_SDK_VERSION,
        LAST_CRASH_TITLE,
        LAST_SENT_TELEMETRY_SW_VERSION,
        DF_POP_UP_LAST_SHOWN
})
public @interface GlobalPreferences {
    String ANONYMOUS_USER_NAME_HINT = "ANONYMOUS_USER_NAME_HINT";
    String AUTHENTICATED_USER = "Authenticated_User";
    String AUTHENTICATED_USER_LIST = "Authenticated_User_List";
    String SIGNED_OUT_USERS = "Signed_Out_Users";
    String AUTH_USER_KEY = "Authentication_User_Key";
    String BOUNCE_APP_DRAWER = "app_drawer_bounce";
    String BRB_LAST_RETRY_TIME = "brb_last_retry_time";
    String DB_MIGRATION_REQUIRED = "DB_Migration_Required";
    String DB_UPDATE_METADATA_SETTING_KEY = "Db_Update_Metadata";
    String DEFAULT_SHARE_TARGET = "defaultShareTarget";
    String ECS_APP_VERSION = "ECS_App_Version";
    String ENABLE_CUSTOMER_DATA_SCANNER = "enable_customer_data_scanner";
    String FEATURES_APP_RATING_ENABLED = "Features_AppRating_Enabled";
    String SHOULD_SAVE_RECENT_MEETING_CODES = "SHOULD_SAVE_RECENT_MEETING_CODES";
    String SAVED_RECENT_MEETING_CODES = "SAVED_RECENT_MEETING_CODES";
//    String FEATURES_DARK_THEME_ENABLED = ThemeSettingUtil.DARKTHEME_FEATURE_KEY;
//    String FEATURES_PLAYGROUND_THEME_ENABLED = ThemeSettingUtil.PLAYGROUND_FEATURE_KEY;
    String BROADCAST_TIMEZONE_ENABLED = "broadcast_timezone_data";
    String FEATURES_HOST_DARK_THEME_ENABLED = "Features_Host_Dark_Theme_Enabled";
    String FIRST_SIGNIN_SUCCEEDED = "First_Signin_Succeeded";
    String SIGNED_SUCCESSFUL_ACCOUNTS = "Signed_Successful_Accounts";
    String FIRST_TIME_INSTALL = "First_Install";
    String FIRST_TIME_APP_LAUNCH = "First_App_Launch";
    String MSAL_ENABLED = "Msal_Enabled";
    String FIRST_TIME_INSTALL_DEEP_LINK = "First_Time_Install_Deeplink";
    String FIRST_TIME_USER_ENTER_APP = "First_Time_User_Enter_App";
    String FIRST_TIME_ENTER_MAIN_SCREEN = "First_Time_Enter_Main_Screen";
    String FRE_EXPERIENCED_USERS_KEY = "Fre_Experienced_Users_Key";
    String MESSAGES_PROCESSED_FOR_NOTIFICATION = "Messages_Processed_For_Notification";
    String IS_LOCATION_PERMISSION_REQUESTED = "Is_Location_Permission_Requested";
    String KEY_DEVICE_ID_DEBUG = "device_id_debug";
    String LAST_ANONYMOUS_MEETING_URL = "LAST_ANONYMOUS_MEETING_URL";
    String LOGGED_IN_ANONYMOUS_THREAD_ID = "LOGGED_IN_ANONYMOUS_THREAD_ID";
    String LOGIN_HINT_KEY = "Login_Hint_Key";
    String MEDIA_QUALITY = "Media_Quality";
    String REDUCE_DATA_USAGE_FOR_VIDEO_CALL = "Reduce_Data_Usage_For_Video_Call";
    String MODULE_REFRESH_NEEDED = "Module_Refresh_Needed";
    String NOTIFICATION_BLOCKER_POP_UP_COUNT = "notification_blocker_pop_ip_count";
    String NOTIFICATION_SOUND_URI = "Notification_Sound_Uri";
    String NOTIFICATION_BG_TELEMETRY_LAST_LOGGED = "Notification_BG_Telemetry_Last_Logged";
    String NOTIFICATION_FG_TELEMETRY_LAST_LOGGED = "Notification_FG_Telemetry_Last_Logged";
    String PERSISTED_MARKET_COUNTRY = "Persisted_Market_Country";
    String PERSISTED_MARKET_LANGUAGE = "Persisted_Market_Language";
    String TOU_LAST_VERSION_SEEN = "Tou_Last_Version_Seen";
    String TOU_URL = "Tou_Url";
    String NON_GLOBAL_ENDPOINTS = "Non_Global_Endpoints";
    String FORCE_UPDATE_NON_GLOBAL_ENDPOINTS = "Force_Update_Non_Global_Endpoint";
    String LAST_TENANT_NOTIFICATION_SYNC_TIME = "Last_Tenant_Notification_Sync_Time";
    String DISABLE_MSAL_OPTIMIZATIONS = "Disable_Msal_Optimizations";

    // Debug settings
    String FPS_COUNTER_ENABLED = "Fps_Counter__Enabled";
    String SHAKE_AND_SEND_ENABLED = "Shake_and_Send_Enabled";
    String SHAKE_AND_SEND_DISMISS_COUNT = "Shake_and_Send_Dismiss_Count";
    String ENABLE_TROUBLESHOOT_SCREEN = "Enable_Troubleshoot_Screen";
    String SHOW_DEBUG_ELEMENTS = "Show_Debug_Elements";
    String SHOW_PRESENCE_DEBUG_INFO = "Show_Presence_Debug_Info";
    String DISABLE_JOB_SCHEDULER_SEND_MESSAGE = "disable_job_scheduler_send_msg";
    String JOB_DEBUG_NOTIFICATION = "job_debug_notification_and_channel_key";
    String JOB_DEBUG_MESSAGE_STALE_MILLIS = "job_debug_stale_message_time";
    String FEATURES_SYNC_DISBLED = "Features_Sync_Disabled";
    String FEATURES_ARIA_DISBLED = "Features_Aria_Disabled";
    String LOG_CLOUDTYPE_ACCOUNTTYPE_LOGCAT_DISABLED = "Log_CloudType_AccountType_Logcat_Disabled";
    String SKYLIB_INIT_DISBLED = "Skylib_Init_Disabled";


    // Notifications
    String GLOBAL_NOTIFICATIONS_ENDPOINT_ID = "Global_Notifications_Endpoint_Id";
    String CHATS_NOTIFICATION_SWITCH = "Chats_Notifications_Switch";
    String MENTIONS_NOTIFICATION_SWITCH = "Mentions_Notifications_Switch";
    String REPLIES_NOTIFICATION_SWITCH = "Replies_Notifications_Switch";
    String LIKES_NOTIFICATION_SWITCH = "Likes_Notifications_Switch";
    String FOLLOWED_NOTIFICATION_SWITCH = "Followed_Notifications_Switch";
    String APPS_NOTIFICATION_SWITCH = "Third_Party_Notifications_Switch";
    String OTHER_ALERTS_NOTIFICATION_SWITCH = "Other_Alerts_Notifications_Switch";
    String CALLS_MISSED_NOTIFICATION_SWITCH = "Calls_Missed_Notifications_Switch";
    String INFERRED_ALERTS_NOTIFICATION_SWITCH = "Inferred_Alerts_Notifications_Switch";
    String TRENDING_ALERTS_NOTIFICATION_SWITCH = "Trending_Alerts_Notifications_Switch";
    String PRIORITY_ALERTS_NOTIFICATION_SWITCH = "Priority_Alerts_Notifications_Switch";
    String DLP_ALERTS_NOTIFICATION_SWITCH = "Dlp_Alerts_Notifications_Switch";
    String VOICEMAILS_NOTIFICATION_SWITCH = "Voicemails_Notifications_Switch";
    String SEND_NOTIFICATIONS_ONLY_WHEN_ACTIVE = "Send_Notifications_Only_When_Active";
    String SEND_NOTIFICATIONS_DEBUG = "Send_Notifications_Debug";
    String VIBRATE_NOTIFICATIONS_SWITCH = "Vibrate_Notifications_Switch";
    String INCOMING_CALLS_SWITCH = "Incoming_Calls_Switch";
    String WHEN_IN_MEETING_SWITCH = "When_In_Meeting_Switch";
    String CHANNELS_NOTIFICATIONS_SWITCH = "Channels_Switch";
    String CALLS_NOTIFICATIONS_SWITCH = "Calls_Switch";

    // Notification encryption
    String NOTIFICATION_ENCRYPTION_ASE_KEY = "Notification_Encryption_Ase_Key";
    String NOTIFICATION_ENCRYPTION_AUTH_KEY = "Notification_Encryption_Auth_Key";
    String NOTIFICATION_ENCRYPTION_CRYPTO_METHOD = "Notification_Encryption_Crypto_Method";
    String NOTIFICATION_ENCRYPTION_ASE_KEY_GENERATION_TIME = "Notification_Encryption_Ase_Key_Generation_Time";

    // Translation Settings Preferences
    String PREFERRED_TRANSLATION_SETTINGS_OPTION = "PREFERRED_TRANSLATION_SETTINGS_OPTION";

    // Performance monitoring
    String PERFORMANCE_MONITORING_ENABLED = "Performance_Monitoring_Enabled";
    String LEAK_CANARY_ENABLED = "Leak_Canary_Enabled";
    String DB_QUERY_LOGGING_ENABLED = "Db_Query_Logging_Enabled";

    // Baidu Push Notification
    String FORCE_BAIDU_PUSH = "Force_Baidu_Push";

    // Background Activity Detection
    String BACKGROUND_INACTIVE_COUNTER = "backgroundInactiveCounter";
    String LAST_TIME_APP_IN_BACKGROUND = "lastTimeAppInBackground";
    String LAST_TIME_APP_IN_FOREGROUND = "lastTimeAppInForeground";
    String LAST_TIME_COUNTER_RESET = "lastTimeCounterReset";
    String SKYPE_TOKEN_REFRESH_JOB_RAN = "skypeTokenRefreshJobRan";
    String FRE_CONTACTS_PERMISSION_POP_UP_SHOWN = "Fre_Contacts_Permission_Pop_Up_Shown";

    // Badge Count Service
    String BADGE_COUNT_SERVICE_PAYLOAD = "Badge_Count_Service_Payload";
    String BADGE_COUNT_SERVICE_RESPONSE_PAYLOAD = "Badge_Count_Service_Response_Payload";
    String BADGE_COUNT_SERVICE_PUSH_NOTIFICATION_UPDATE = "Badge_Count_Service_Push_Notification_Update";
    String BADGE_COUNT_SERVICE_PUSH_NOTIFICATION_TIMESTAMP = "Badge_Count_Service_Push_Notification_Timestamp";
    String BADGE_COUNT_SERVICE_TOTAL_PILL_COUNT = "Badge_Count_Service_Total_Pill_Count";
    String BADGE_COUNT_SERVICE_ENDPOINT_ID = "Badge_Count_Service_EndpointId";
    String BADGE_COUNT_SERVICE_REGISTRATION_SUCCESSFUL = "Badge_Count_Registration_Successful";
    String BADGE_COUNT_SERVICE_NUMBER_OF_ACCOUNTS_REGISTERED = "Number_Of_Accounts_Registered_With_Badge_Count_Service";
    String BADGE_COUNT_SERVICE_REGISTRATION_REQUEST_TICKS = "Registration_Request_Ticks_Badge_Count_Service";

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

    /* The keys for MAM Intune Settings */
    String MAM_IDENTITY_KEY = "MAM_IDENTITY_KEY";
    String MAM_AUTHORITY_URL_KEY = "MAM_AUTHORITY_URL_KEY";
    String MAM_REENROLLMENT_KEY = "MAM_REENROLLMENT_KEY";

    /* Preferred client prompt */
    String PREFERRED_CLIENT_PROMPT_OPTION = "PREFERRED_CLIENT_PROMPT_OPTION";

    /* Keys for app rating and feedback feature */
    String NUM_OF_APP_LAUNCHES_KEY = "Num_Of_App_Launches_Key";
    String APP_INSTALLED_DATE = "App_Installed_Date";
    String SKIP_APP_RATING_THRESHOLD_DATE = "Skip_App_Rating_Threshold_Date";
    String CORE_TASK_FINISHED_KEY = "Core_Task_Finished_Key";
    String USER_SKIPPED_APP_RATING_KEY = "User_Skipped_App_Rating_Key"; //show app rating screen again in 15 days
    String USER_DISLIKED_APP_KEY = "User_Liked_App_Key"; //don't show the app rating screen
    String USER_RATED_APP_KEY = "User_Rated_App_Key"; //don't show the app rating screen

    // DB Reset Flags
    String LAST_DB_RESET_TIME = "Last_DB_Reset_Time";
    String IS_FORCE_AUTO_PRUNE_FOR_OPTIMIZATION = "Is_Force_Auto_Prune_For_Optimization";
    String AUTO_PRUNE_COMPLETED_TIME = "Auto_Prune_Completed_Time";
    String FORCE_AUTO_PRUNE_REQUIRED = "Force_Auto_Prune_Required";

    //permission setting for Mic and camera
    String DONT_SHOW_AGAIN_CHOSEN_BY_USER_MIC = "Dont_show_again_chosen_by_user_mic";
    String DONT_SHOW_AGAIN_CHOSEN_BY_USER_CAMERA = "Dont_show_again_chosen_by_user_camera";
    String DONT_SHOW_AGAIN_CHOSEN_BY_USER_CONTACTS = "Dont_show_again_chosen_by_user_contacts";

    /* Floodgate - NPS settings **/
    String SEND_MESSAGE_UNIQUE_CONVERSATION_IDS = "Send_Message_Unique_Conversation_Ids";

    /* What's New Experience */
    String WHATS_NEW_NOTIFICATION_SWITCH = "Whats_New_Notifications_Switch";
    String WHATS_NEW_HAMBURGER_MENU_VISITED = "WHATS_NEW_HAMBURGER_MENU_VISITED";
    String WHATS_NEW_BLOG_VISITED = "WHATS_NEW_BLOG_VISITED";
    String WHATS_NEW_EXPERIENCE_NOTIFICATION_SCHEDULED = "WHATS_NEW_EXPERIENCE_NOTIFICATION_SCHEDULED";
    String WHATS_NEW_EXPERIENCE_NOTIFICATION_SHOWN = "WHATS_NEW_EXPERIENCE_NOTIFICATION_SHOWN";

    /*Device Login*/
    String DEVICE_LOGIN_CLOUD_SELECTION = "DEVICE_LOGIN_CLOUD_SELECTION";
    String DEVICE_LOGIN_PROVISIONED_TENANT_ID = "DEVICE_LOGIN_PROVISIONED_TENANT_ID";
    String DEVICE_LOGIN_PROVISIONED_TENANT_NAME = "DEVICE_LOGIN_PROVISIONED_TENANT_NAME";

    /*Custom logos*/
    String CUSTOM_LOGOS = "Custom_logos";

    /* Baidu users accepted privacy policy */
    String USER_ACCEPTED_PRIVACY_NOTICE = "user_accepted_privacy_policy";

    /**
     * Whether it is upgraded to the current newest version to determine user can see the Read
     * Receipts Privacy Notice.
     */
    String USER_SEEN_READ_RECEIPTS_PRIVACY_NOTICE_UPGRADE_TO_CURRENT_VERSION = "user_seen_read_receipts_privacy_notice_upgrade_to_current_version";

    /**
     * Whether user has ever seen the read receipts privacy notice, this is set only 1 time per app
     * life cycle, sicne promisingly,
     */
    String USER_SEEN_READ_RECEIPTS_PRIVACY_NOTICE = "user_seen_read_receipts_privacy_notice";
    String USER_SEEN_ALWAYS_NOTIFY_DIALOG = "user_seen_always_notify_dialog";

    String OLD_SHARED_PREFS_CLEANUP_NEEDED = "Old_Shared_Prefs_Cleanup_Needed";


    String APP_SETTINGS_USERNAME_MRI_LIST_MAP = "username_mri_list_map";

    String USER_INITIATED_DB_RESET = "user_initiated_db_reset";

    String SYSTEM_INITIATED_DB_RESET = "system_initiated_db_reset";

    // Aria Collector Url
    String ARIA_COLLECTOR_URL_NON_GLOBAL = "Aria_Collector_Url_Non_Global";

    // Network
    String SHOULD_FORCE_NETWORK_BROADCAST = "Should_Force_Network_Broadcast";

    // Custom Data Prune/Reset.
    String VERSION_FOR_WHICH_SYNC_STATE_ALREADY_REMOVED = "version_where_sync_state_already_removed";
    String VERSION_FOR_WHICH_CUSTOM_RESET_ALREADY_HAPPENED = "version_where_custom_reset_already_happened";

    // Instrumentation testing related, only applicable for debug builds
    String ENABLE_INSTRUMENTATION_NOTIFICATIONS = "enable_instrumentation_notifications";
    String ENABLE_SCENARIO_INSTRUMENTATION_NOTIFICATIONS = "enable_scenario_instrumentation_notifications";
    String ENABLE_PANEL_ACTION_INSTRUMENTATION_NOTIFICATIONS = "enable_panel_action_instrumentation_notifications";
    String ENABLE_PANEL_VIEW_INSTRUMENTATION_NOTIFICATIONS = "enable_panel_view_instrumentation_notifications";

    // Cortana
    String CORTANA_SDK_VERSION = "cortana_sdk_version";

    /**
     * Cortana prefs.
     * These prefs have been moved to UserPreferences. Don't use prefs here any more.
     */
    @Deprecated
    String LAST_SIGNIN_TIMESTAMP_MILLIS = "last_signin_timestamp_millis_";
    @Deprecated
    String USER_PERFORMED_ACTION = "user_performed_action_";
    @Deprecated
    String USER_CONSENTED_CORTANA = "user_consented_cortana_";
    @Deprecated
    String USER_SPEECH_LOGGING_CONSENT = "user_speech_logging_consent";
    @Deprecated
    String USER_SEEN_CORTANA_FRE = "user_seen_cortana_fre_";

    // SMB (Small Medium Business) Business Voice FRE
    String USER_EXPERIENCED_SMB_BUSINESS_VOICE_FRE = "user_experienced_smb_business_voice_fre";

    // Whether user has seen what's new for SMB Business Voices, this is set only 1 time per app life cycle.
    String USER_SEEN_SMB_BUSINESS_VOICE_WHATS_NEW = "user_seen_smb_business_voice_whats_new";

    // Whether user has seen what's new for freemium meetings.
    String USER_SEEN_FREEMIUM_MEETINGS_WHATS_NEW = "user_seen_freemium_meetings_whats_new";

    String USER_CLICKED_FREEMIUM_MEETINGS_TAB = "user_clicked_freemium_meetings_tab";

    // Whether user has seen what's new for meet now meetings.
    String USER_SEEN_MEETNOW_MEETINGS_WHATS_NEW = "user_seen_meetnow_meetings_whats_new";

    // Whether user has clicked meet now meetings icon.
    String USER_CLICKED_MEETNOW_ICON = "user_clicked_meetnow_meetings_icon";

    // RN Apps
    String VERSION_FOR_WHICH_RNAPP_DATA_SYNCED = "version_for_which_rn_app_synced";

    //People
    String PEOPLE_BANNER_CLICKED = "peopleBannerClicked";

    // app center
    String TEAMS_ANR_LIST = "teams_anr_list";

    // More tab coachmark
    String SEEN_MORE_TAB_COACHMARK = "seen_more_tab_coachmark";

    //IP Phone Hotdesking
    String HOT_DESK_HOST_USER_ID = "hot_desk_host_user_id";

    // Group Avatar
    String UPDATED_GROUP_AVATARS = "updated_group_avatars";

    // Tfl teams banner
    String DISPLAY_TFL_TEAMS_BANNER = "displayTflTeamsBanner";

    // IP phone app restart time
    String LAST_APP_RESTART_TIME = "lastAppRestartTime";

    // MDL Aggregate metric
    String AGGREGATE_MESSAGE_RECEIVED_ON_LONG_POLL = "AGGREGATE_MESSAGE_RECEIVED_ON_LONG_POLL";

    // MeetNow coachmark
    String SEEN_MEET_NOW_COACHMARK = "seen_meet_now_coachmark";
    String SEEN_CHANNEL_MEET_NOW_COACHMARK = "seen_channel_meet_now_coachmark";
    // Android Id & Device ID
    String TEAMS_FAKE_IMEI = "Teams_Fake_IMEI";
    String TEAMS_FAKE_ANDROID_ID = "Teams_FAKE_Android_Id";

    // Anonymous meeting exit flag
    String ANONYMOUS_MEETING_EXIT = "anonymous_meeting_exit";

    // Anonymous meeting exit flag
    String USER_EDUCATION = "user_education";

    // Diagnostic Data Viewer
    String DIAGNOSTIC_DATA_VIEWER_CONNECTION_STRING = "diagnostic_data_viewer_connection_string";
    String LAST_CRASH_TITLE = "last_crash_title";
    String LAST_SENT_TELEMETRY_SW_VERSION = "last_sent_telemetry_sw_version";

    // User id to profile avatar
    String TENANT_HASH_TO_PROFILE_AVATAR_URL = "tenant_hash_to_profile_avatar_url";

    // timestamp for Last shown Dogfood pop-up
    String DF_POP_UP_LAST_SHOWN = "df_pop_up_last_shown";
}

