/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.services.diagnostics.telemetryschema;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// import static com.microsoft.skype.teams.data.events.DataEvents.ON_DEMAND_TRANSLATE_CHAT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ACCEPT_CALL_QUEUE_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ACCESS_VAULT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ACQUIRE_ADAL_RESOURCE_TOKEN;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ACQUIRE_ADAL_TOKEN;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ACQUIRE_PASSTHROUGH_TOKEN;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ACQUIRE_PRIMARY_TOKEN;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ACQUIRE_PRIMARY_TOKEN_PHONE_NUMBER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ACQUIRE_PRIMARY_TOKEN_SILENT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ACQUIRE_RESOURCE_TOKEN;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ACQUIRE_RESOURCE_TOKEN_INTERACTIVE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CP_DCF_LOGIN_REQUEST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ACQUIRE_RESOURCE_TOKEN_MSAL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ACQUIRE_RESOURCE_TOKEN_SILENT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ACTIVITY_PILL_COUNT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ADAPTIVE_CARD_CACHE_CLEANUP_WORKER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ADD_BUDDY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ADD_TO_CALENDAR;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ADD_TO_CHAT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ADD_TO_MEETING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ADD_USER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ALLOW_TO_UNMUTE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ANONYMOUS_CALL_JOIN;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ANONYMOUS_JOIN_SETUP;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.APPLY_BG_REPLACEMENT_EFFECT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.APP_AUTHENTICATION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.APP_FRE_SYNC;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.APP_FRE_SYNC_ON_ACTION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.APP_FRE_SYNC_ON_FREEMIUM_ACTION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.APP_FRE_SYNC_ON_FREEMIUM_INVITATION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.APP_FRE_SYNC_RETRY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.APP_INCREMENTAL_SYNC_LAUNCH;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.APP_INCREMENTAL_SYNC_RESUME;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.APP_LAUNCH_SKYLIB_INIT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.APP_LOGIN_IN_PROGRESS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.APP_START;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.AUGLOOP_TOKEN_FETCHED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.AUTHENTICATE_USER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.AUTH_ADAL_FORCE_PROMPT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.AUTH_MSAL_FORCE_PROMPT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.AUTH_MSAL_SKYPE_TOKENS_RENEW;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.AUTH_MSAL_TOKENS_RENEW;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.AUTH_SKYPE_TOKENS_RENEW;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.AUTH_SKYPE_TOKENS_RENEW_FORCE_ADAL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.AUTH_SKYPE_TOKENS_RENEW_FORCE_MSAL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.AUTH_SSO_TOKEN;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.AUTH_TOKENS_RENEW;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.AUTO_PRUNE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.AUTO_PRUNE_DELETE_ACTIVITIES;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.AUTO_PRUNE_DELETE_OTHER_MESSAGES;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.AUTO_PRUNE_FOR_OPTIMIZING_DB;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.AUTO_PRUNE_HARD_DELETE_RC;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.AUTO_PRUNE_SOFT_DELETE_RC;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.App;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.Auth;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.BACKGROUND_NOTIFICATION_SYNC_TASK;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.BELL_COUNT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.BG_REPLACEMENT_DOWNLOAD_IMAGES;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.BOT_INVOKE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DEVICES_ADMIN_AGENT_BROADCAST_EVENT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DEVICES_ADMIN_AGENT_CHECK_PROVISION_STATUS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DEVICES_ADMIN_AGENT_PROVISION_OTP;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DEVICES_ADMIN_AGENT_REMOTE_LOGIN_REQUEST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DEVICES_ADMIN_AGENT_REMOTE_LOGOUT_REQUEST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.BREAKOUT_ROOM_ACCEPT_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.BREAKOUT_ROOM_BANNER_JOIN;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.BRIDGE_THREAD_ACTIVATION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.BROADCAST_EMERGENCY_INFO;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.BYOM_AUTO_ANSWER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.BetterTogether;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALLING_CALL_DISCONNECTED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALLING_SERVICE_INIT_APP_FOREGROUND;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALLS_PILL_COUNT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_ACCEPT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_AUTO_ANSWER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_BLOCK_CONTACT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_BLOCK_NUMBER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_BLOCK_SFCUSER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_HISTORY_LOAD;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_HISTORY_UPDATED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_JOIN_COMPLETE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_JOIN_FROM_DEEP_LINK;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_JOIN_FROM_DEEP_LINK_ANONYMOUS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_JOIN_FROM_DEEP_LINK_ANONYMOUS_TFL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_JOIN_FROM_DEEP_LINK_BROADCAST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_JOIN_FROM_DEEP_LINK_BROADCAST_ANONYMOUS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_ME_BACK;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_ME_BACK_MEETING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_RENDER_VIDEO;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_SERVICE_INIT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_SERVICE_LOGIN;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_START_VIDEO;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_STOP_VIDEO;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_START_VIDEO_USER_ACTION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_STOP_VIDEO_USER_ACTION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_UNBLOCK_CONTACT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_UNBLOCK_NUMBER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_UNBLOCK_SFCUSER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_VOICEMAIL_LOAD;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CANCEL_APPOINTMENT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CANCEL_CHANNEL_MEETING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CANCEL_MEETING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CANCEL_PRIVATE_MEETING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CHANNEL_CONTEXT_MENU;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CHANNEL_DELETE_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CHATS_PILL_COUNT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CHAT_ADD_GIPHY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CHAT_ADD_MEME;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CHAT_CONTEXT_MENU;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CHAT_CREATE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CHAT_DELETE_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CHAT_MEETING_PARTICIPANTS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CHAT_SEND_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CHAT_SEND_MESSAGE_ANDROID_AUTO;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CHAT_SEND_MESSAGE_FEDERATED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CHAT_SEND_MESSAGE_NEWTHREAD;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CHAT_SEND_MESSAGE_SFB;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CHAT_SEND_MESSAGE_SMS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CLEAR_ACCOUNT_PREFS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CLEAR_AUTH_PROVIDER_CACHE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CLEAR_CACHES;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CLEAR_DB;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CLEAR_MULTI_TENANT_DB;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CLEAR_NOTIFICATION_CHANNELS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CLEAR_PREFS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CLEAR_SINGLE_TENANT_DB;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CONSULT_TRANSFER_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CONTENT_APPEAR;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CONVERT_CHAT_VIEW_DATA;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CONVERT_SPANNABLE_TO_HTML;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CORTANA_ADMIN_POLICY_REFRESH;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CORTANA_AUTO_CLOSE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CORTANA_BACKGROUND_TOKEN_REFRESH;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CORTANA_INITIALIZATION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CORTANA_KWS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CORTANA_MORE_MENU;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CORTANA_RESPONSE_ERROR;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CORTANA_SDK_EVENTS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CORTANA_SKILL_ACTION_DELAY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CORTANA_SKILL_ACTION_EXECUTION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CORTANA_WAKE_WORD_ACTIVE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CORTANA_WATCHDOG;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CREATE_CHANNEL_MEETING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CREATE_CONSUMER_GROUP_EVENT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CREATE_DUMMY_MEETING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CREATE_GROUP_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CREATE_MEETING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CREATE_NEW_CHAT_THREAD;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CREATE_NEW_CHAT_THREAD_SUBSCENARIO;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CREATE_NEW_DELEGATE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CREATE_ONE_ON_ONE_CONSUMER_GROUP;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CREATE_ONE_TO_ONE_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CREATE_ONE_TO_ONE_INTEROP_SFB_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CREATE_ONE_TO_ONE_INTEROP_SFC_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALL_ROUTING_UPDATE_CLIENT_PREFERENCE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CREATE_VOICEMAIL_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DB_CHECK_AND_UPDATE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DB_CLEAR;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DB_DESTRUCTIVE_DESTROY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DB_HANDLE_SQL_EXCEPTION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DB_INITIALIZE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DB_MIGRATION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DB_MIGRATION_ON_ERROR;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DB_UPGRADE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DB_UPGRADE_FROM_SPLASH_ACTIVITY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DELEGATE_NOTIFICATION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DELETE_BADGE_COUNT_SERVICE_REGISTRATION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DELETE_DUMMY_MEETING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DELETE_EDF_REGISTRATION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DELETE_END_POINT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DELETE_MEETING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DELETE_MULTI_TENANT_DB;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DELETE_SINGLE_TENANT_DB;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DELETE_UPS_ENDPOINT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DOCK_CONNECTED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DOCK_DISCONNECTED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DOCK_MESSAGE_ACK;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DeviceAddressBookSync;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.EARLY_CALL_CANCEL_SHOW_FEEDBACK;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.EARLY_CALL_RINGING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.EARLY_RINGING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.EDIT_CHANNEL_MEETING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.EDIT_DELEGATE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.EDIT_IMAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.EDIT_MEETING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.EDIT_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.EDIT_SEND_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.EMERGENCY_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.EMERGENCY_CALL_DIRECT_ROUTING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ENABLE_PROFILE_SEARCHABILITY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.END_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ENROLL_MAM;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ENROLL_WITH_INTUNE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.EVENT_RSVP;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.EXTENSIBILITY_APP_AUTHENTICATION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.EXTENSIBILITY_BOT_TO_USER_FILE_ATTACHMENT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.FAVORITE_CHANNEL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.FETCH_CHANNELS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.FETCH_EPHEMERAL_PROFILE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.FETCH_ME_PROFILE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.FETCH_TEAMS_INFO_API;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.FORWARD_EXISTING_AMS_OBJECT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.FORWARD_IMAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.FORWARD_MEETING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.Files;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.Gallery;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GET_ACTIVE_CALL_LIST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GET_AVAILABILITY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GET_BADGE_COUNT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GET_CALL_LOGS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GET_COUNT_OF_PENDING_MEMBERS_REQUEST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GET_FREEMIUM_AD_HOC_MEETING_LIST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GET_PENDING_MEMBERS_REQUEST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GET_RESOURCE_TOKEN_ASYNC;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GET_RESOURCE_TOKEN_INTERACTIVE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GET_RESOURCE_TOKEN_SYNC;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GET_SENDER_SUB_SCENARIO;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GET_SUGGESTED_ROOMS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GET_TENANT_LIST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GET_TENANT_LIST_WITH_NOTIFICATIONS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GET_THREAD_PROPERTIES_SINGLE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GET_TIMEZONE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GET_VOICE_MAIL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GET_VOICE_MAIL_FOLDER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GRANT_URI_READ_PERMISSION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GROUP_CALLS_EXPAND_CLICKED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GROUP_PROFILE_CARD;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.HOLD_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.INCOMING_CALL_QUEUE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.INCOMING_CALL_QUEUE_TRANSFER_REQUEST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.INCOMING_TRANSFER_REQUEST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.INITIALIZE_ON_SKYLIB_NOT_INITIALIZED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.INSTALL_REFERRER_RESPONSE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.INTUNE_REMEDIATE_COMPLIANCE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.INTUNE_REMEDIATE_COMPLIANCE_POLICY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.INVISION_WHITEBOARD_SHARE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.INVITE_OFF_NETWORK_CONTACTS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.IpphoneHomeScreen.CALENDAR_NOTIFICATION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.IpphoneHomeScreen.CALL_NOTIFICATION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.IpphoneHomeScreen.VOICEMAIL_NOTIFICATION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.IpphoneSidecar.SIDECAR_PRESENTATION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.JOIN_ACTIVE_ONE_ON_ONE_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.JOIN_AD_HOC_MEETING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.JOIN_GROUP_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.JOIN_MEETUP_REPLY_CHAIN;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.JOIN_MEETUP_RETRY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.JOIN_MEET_NOW_MEETING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.JOIN_SCHEDULED_MEETUP;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.JOIN_SCHEDULED_MEETUP_BROADCAST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.JOIN_WEBINAR;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.JS_BUNDLE_LOAD;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LENS_LAUNCH;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LIVE_EVENT_ATTENDEE_JOIN;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LIVE_EVENT_AUDIO_DOWNLOAD_FAILURE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LIVE_EVENT_BITRATE_CHANGED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LIVE_EVENT_BUFFERING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LIVE_EVENT_ERROR;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LIVE_EVENT_HEALTH_REPORT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LIVE_EVENT_LOADED_METADATA;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LIVE_EVENT_PAUSE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LIVE_EVENT_PLAYBACK_SESSION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LIVE_EVENT_PLAY_COMPLETE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LIVE_EVENT_PLAY_STARTED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LIVE_EVENT_POTENTIAL_MEDIA_FREEZE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LIVE_EVENT_RAPID_SEEKING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LIVE_EVENT_READY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LIVE_EVENT_RETRY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LIVE_EVENT_TICK;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LIVE_EVENT_VIDEO_DOWNLOAD_FAILURE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LOAD_ACTIVITY_LIST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LOAD_ASSIGNMENTS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LOAD_AUTH_FOR_MONKEY_TEST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LOAD_BOOKMARKS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LOAD_BOOKMARKS_CONVERSATION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LOAD_BOOKMARKS_FEED_ID;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LOAD_CHANNEL_TAB;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LOAD_CHAT_AFTER_FRE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LOAD_MESSAGING_EXTENSTION_RESULTS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LOAD_PERSISTED_BG_REPLACEMENT_EFFECT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LOAD_PPT_SHARE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.INIT_PPT_VIEWER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LOAD_PROFILE_PAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LOAD_STATIC_TAB;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LOAD_TENANT_LIST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LOAD_TENANT_LIST_CONSUMER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LOAD_USER_ACTIVITY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LOG_IN_ON_SKY_LIB_STATUS_CHANGE_EVENT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LOWER_ALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.LOWER_HAND;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.MEDIA_CONNECTED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.MEETING_DETAILS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.MEETING_EXCHANGE_FILE_DOWNLOAD;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.MEETING_INSIGHT_FETCH;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.MEETING_INSIGHT_RENDER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.MEETING_JOIN_BY_CODE_JOINING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.MEETING_OPTIONS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.MEETING_REMINDER_WORKER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALENDAR_SYNC_LOCAL_BY_DATE_RANGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.CALENDAR_SYNC_LOCAL_BY_MEETING_IDS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.MERGE_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.MESSAGING_FORWARD_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.MESSAGING_SEND_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.MESSAGING_SEND_REPLY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.MIGRATE_INDEX_SCENARIO;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.MISSING_NOTIFICATION_START_PROCESS_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.MSAL_APP_AUTHENTICATION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.NATIVE_CONTACT_SYNC;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.NETWORK_RECONNECT_BEGIN;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.NETWORK_RECONNECT_FAIL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.NETWORK_RECONNECT_SUCCESS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.NOTES_PILL_COUNT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.NOTIFICATION_NAV_CHANNEL_CONVERSATION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.NOTIFICATION_NAV_CHANNEL_CONVERSATION_FILE_UPLOAD;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.NOTIFICATION_NAV_CHAT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.NOTIFICATION_NAV_CHAT_FILE_UPLOAD;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.NOTIFICATION_RECEIVED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.Now;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ON_ACTIVE_CALL_PUSH_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ON_CALL_HANDLER_NULL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ON_CALL_LONGPOLL_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ON_CALL_PUSH_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ON_CALL_QUEUE_CALL_PUSH_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ON_MESSAGE_RECEIVED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ON_READ_RECEIPTS_LONGPOLL_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.OPEN_EXISTING_CHAT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.OPEN_FILE_PICKER_ASSIGNMENT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.OPEN_IMAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.OPEN_URI;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.OUTGOING_CALL_QUEUE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PARK_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PARK_CALL_FOR_HOLD;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PARK_CALL_FOR_HOLD_V2;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PARTICIPANT_VBSS_LATENCY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PARTICIPANT_VBSS_SUMMARY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PEOPLE_LOAD_USER_CARD;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PEOPLE_PICKER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PEOPLE_PICKER_LOAD_RESULTS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PEOPLE_V2_ADD_CONTACT_GROUP;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PEOPLE_V2_CREATE_CONTACT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PEOPLE_V2_CREATE_CONTACT_GROUP;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PEOPLE_V2_DELETE_CONTACT_GROUP;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PEOPLE_V2_EDIT_CONTACT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PEOPLE_V2_FETCH_CONTACTS_AND_GROUPS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PEOPLE_V2_FETCH_GROUPS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PEOPLE_V2_REMOVE_CONTACT_GROUP;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PEOPLE_V2_RENAME_CONTACT_GROUP;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PLAY_MEETING_RECORDING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PLAY_MEETING_RECORDING_ONEPLAYER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.POST_MESSAGE_ACTION_CHAIN;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.POST_MESSAGE_VIA_SCHEDULER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.POST_MESSAGE_VIA_WRAPPER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PREJOIN_FETCH_MEETING_METADATA;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PRE_CALL_TO_IN_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PRE_INIT_RN_MODULE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PROCESS_DEEPLINKS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PROCESS_DEEPLINKS_FOR_VALID_USER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PROXIMITY_SENSOR_SERVICE_EVENTS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PSTN_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SHARE_LLDP_INFO;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PULL_REFRESH_ALERTS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.People;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.PeoplePicker;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.RAISE_HAND;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.HARD_MUTE_ATTENDEE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.REACTIONS_FETCH_USERS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.REDEEM_CONSUMER_TENANT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.REDIRECT_TO_FRE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.REFRESH_ADAL_TOKEN;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.REFRESH_DELEGATES_DATA;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.REFRESH_MSAL_TOKEN;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.REGISTER_BADGE_COUNT_SERVICE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.REMOVE_DELEGATE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.RESOLVE_FEDERATED_USER_BY_EMAIL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.RESOLVE_FEDERATED_USER_BY_MRI;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.RESUME_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.RETRY_CALL_PUSH_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.RE_INITIALIZE_ON_INVALID_SKY_LIB_RUNNING_STATUS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.RE_INITIALIZE_ON_SKY_LIB_STATUS_CHANGE_EVENT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.RNLSync;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.RN_ALL_TEAMS_FETCH;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.RN_APP_RENDER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.RN_HTTP_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.RN_PACKAGES_INIT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.RN_RESOURCE_TOKEN_FETCH;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.RN_RESOURCE_TOKEN_EAGER_FETCH;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.RN_SKYPE_TOKEN_FETCH;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.RN_SO_FILE_LOAD;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.RN_TABS_FOR_CHANNEL_WITH_IDS_FETCH;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.RN_TEAMS_WITH_IDS_FETCH;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SAVE_AUSER_TO_LOCAL_DB;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SAVE_IMAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SAVE_LOCAL_MESSAGE_TO_DB;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_APP_DEFINITION_DATA_TRANSFORM;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_AT_MENTION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_CAST_FILE_TO_DEVICE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_CAST_SCREEN_TO_DEVICE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_CHANNEL_LIKE_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_CHANNEL_UNLIKE_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_CHAT_LIKE_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_CHAT_UNLIKE_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_CONVERSATION_DATA_TRANSFORM;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_CONVERSATION_SYNC;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_FRE_TASK;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_GET_BATCH_THREAD_PROPERTIES;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_GET_CONVERSATIONS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_GET_PERSONAL_STREAM;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_GET_TENANT_THREAD_USERS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_GET_THREAD_USERS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_INITIALIZE_REACT_NATIVE_MANAGER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_INSTALL_APP_IN_CHAT_OR_TEAM;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_INSTALL_APP_IN_PERSONAL_SCOPE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_RETRYING_FROM_DEVICE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_SELECT_CAST_DEVICE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_SET_CURRENT_USER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_SIGN_OUT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_SYNC_APPDEF_AND_CHATMSG_SYNC;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_SYNC_BLOCK_LIST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_SYNC_BOOTSTRAP_FREMIUM;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_SYNC_CHAT_ENTITLEMENTS_AND_APP_DEFINITIONS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_SYNC_DATA_FOR_CALLS_TABS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_SYNC_FRE_DATA;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_SYNC_MEETING_DETAILS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_SYNC_MOBILE_MODULES;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_SYNC_TEAM_ENTITLEMENTS_AND_APP_DEFINITIONS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_SYNC_USERS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_SYNC_USERS_FEATURE_SETTINGS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_SYNC_USER_ENTITLEMENTS_AND_APP_DEFINITIONS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_SYNC_VOICEMAIL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_UPDATE_THREAD;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCENARIO_UPDATE_THREAD_PROPS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SCREEN_SHARE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SDK_APP_LOADED_FULLY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SDK_APP_LOADER_APPEAR;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SDK_CALL_ANONYMOUS_SETUP;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SDK_FIRST_VIEW_VISIBLE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SDK_LOAD_ACTIVE_COMPONENT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SDK_LOAD_APP_CONTAINER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SEARCH_ROOMS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SEND_QUICK_LIKE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SEND_QUICK_REPLY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SERVER_EDIT_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SERVER_SEND_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SET_CURRENT_TENANT_ID;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SET_UP_CALL_TRANSFER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SET_UP_JOIN_ACTIVE_ONE_ON_ONE_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SET_UP_JOIN_GROUP_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SET_UP_JOIN_WITHOUT_MODALITY_MEETING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SET_UP_MEETING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SET_UP_VOCIEMAIL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SHARE_IMAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SHARE_VAULT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SHOULD_FILTER_NOTIFICATION_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SHOW_CHANNEL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SHOW_CHAT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SHOW_CHAT_LIST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SHOW_MEETINGS_TAB;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SHOW_MEETING_DETAILS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SHOW_MEETING_PARTICIPANTS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SHOW_REPLYCHAIN;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SHOW_TEAMS_AND_CHANNELS_LIST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SHOW_TEAM_TABS_TAB;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SIGNOUT_USER_FROM_OTHER_SERVICES;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SKYLIB_SETUP;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SLA_ACTIVE_CALLS_VIEWED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SLA_PARKED_CALL_GROUP_EXPANDED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SLA_PUSH_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SLA_RESUME_PARKED_CALL_CLICKED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SLA_RESUME_PARKED_CALL_VIEWED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SMART_COMPOSE_ENABLED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SMART_COMPOSE_INITIALIZATION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SMART_COMPOSE_NOT_ACCEPTED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SMART_COMPOSE_NOT_MATCHED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SMART_COMPOSE_NOT_RENDERED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SMART_COMPOSE_RECEIVED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SMART_COMPOSE_REQUEST_SENT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SMART_COMPOSE_SESSION_CREATED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SMART_COMPOSE_TYPE_THROUGH;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SMART_REPLY_BANNED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SMART_REPLY_ENABLED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SMART_REPLY_RECEIVED;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.START_CHAT_ACTIVITY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.START_CHAT_APP_END_TO_END;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.START_CHAT_CONTAINER_FRAGMENT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.START_CHAT_FRAGMENT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.START_CONTACTS_APP_END_TO_END;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.START_FAVORITE_APP_END_TO_END;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.START_FILES_APP_END_TO_END;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.START_HISTORY_APP_END_TO_END;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.VOICEMAIL_PERIODIC_SYNC;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.START_ORG_APP_END_TO_END;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.START_TEAMS_AND_CHANNELS_ACTIVITY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.START_TEAMS_AND_CHANNELS_APP_END_TO_END;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.START_VOICEMAIL_APP_END_TO_END;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.STAR_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.STATUS_NOTE_SET_STATUS_ACTIVITY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.STATUS_NOTE_SET_STATUS_REQUEST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.STATUS_NOTE_START_SET_STATUS_ACTIVITY;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.STOP_SERVICES;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.STREAM_PLAYER_LIVE_EVENT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.STREAM_PLAYER_MEETING_RECORDING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SWITCH_TENANT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SWITCH_TENANT_DB;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SWITCH_USER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SYNC_APP_POLICIES;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SYNC_CODEPUSH_CHECK_UPDATE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SYNC_CODEPUSH_DOWNLOAD_BUNDLE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SYNC_CODEPUSH_RN_APP;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SYNC_CONTACTS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SYNC_LOCAL_RN_APP;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SYNC_MESSAGES_FOR_CHAT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SYNC_MESSAGES_FOR_CONVERSATION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SYNC_MESSAGES_FOR_FAV_CONVERSATIONS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SYNC_MOBILE_MODULES;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SYNC_NEW_CHAT_THREAD;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SYNC_PERSONAL_APPS_ON_ENTITLEMENTS_CHANGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SYNC_TEAM_LIST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.Search;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.SfcInterop;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TARGETING_ADD_TAG_MEMBER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TARGETING_ADD_TAG_MEMBERS_REQUEST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TARGETING_CREATE_TAG;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TARGETING_CREATE_TAG_REQUEST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TARGETING_GET_TAG_CARDS_AND_TENANT_SETTINGS_REQUEST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TARGETING_GET_TAG_MEMBERS_REQUEST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TARGETING_GET_TEAM_TAG_CARDS_REQUEST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TARGETING_GET_TENANT_SETTINGS_REQUEST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TARGETING_REMOVE_TAG_MEMBER;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TARGETING_REMOVE_TAG_MEMBERS_REQUEST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TARGETING_VIEW_MANAGE_TAGS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TARGETING_VIEW_TAG_MEMBERS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TASK_MODULE_COMPLETE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TENTATIVE_SIGNIN;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TIME_BASED_RETENTION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TIME_BASED_RETENTION_SHARED_CHANNELS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TOGGLE_BACKGROUND_BLUR;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TRANSFER_ACCEPTED_CALL_QUEUE_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TRANSFER_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TRANSLATE_CHANNEL_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TRANSLATE_CHAT_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TRANSLATION_SUPPORTED_LANGUAGES;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.TROUTER_REGISTRATION;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.UI_MANAGER_LOAD;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.UNENROLL_MAM;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.UNLOCK_TEAM_REQUEST;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.UNPARK_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.UNPARK_CALL_FOR_END_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.UNPARK_CALL_FOR_HOLD;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.UNPARK_CALL_FOR_HOLD_V2;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.UNPARK_SLA_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.UPDATE_DUMMY_MEETING;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.UPDATE_GROUP_AVATAR;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.UPDATE_SKYLIB_SKYPE_TOKEN_ON_APP_TOKEN_RFRESH;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.UPDATE_SKYLIB_SKYPE_TOKEN_ON_REFRESH_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.UPDATE_SKYLIB_SKYPE_TOKEN_ON_SKYLIB_EVENT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.UPLOAD_BITMAP_TO_AMS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.UPLOAD_IMAGES;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.UPLOAD_STATIC_MAP_IMAGES;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.UPLOAD_STATIC_MAP_TO_AMS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.UPLOAD_VOICE_MESSAGES;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.UPLOAD_VOICE_MESSAGE_FILE_TO_AMS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.USER_VOICE_SETTINGS;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.VERIFY_SKYLIB_STATE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.VOICEMAIL_PILL_COUNT;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.VOIP_CALL;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.WARM_PUSH_CALL_MESSAGE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DO_NOT_ALLOW_TO_UNMUTE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.WorkManager;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ANNOTATION_SHARE;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.GET_CROSS_TENANT_RESOURCE_TOKEN;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.DO_NOT_ALLOW_TO_SHARE_VIDEO;
import static com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName.ALLOW_TO_SHARE_VIDEO;

/**
 * Defines different scenario names within the app.
 */
@Retention(RetentionPolicy.SOURCE)
@StringDef(
        value = {
                ADAPTIVE_CARD_CACHE_CLEANUP_WORKER,
                ADD_BUDDY,
                AUGLOOP_TOKEN_FETCHED,
                CHAT_CREATE,
                CHAT_SEND_MESSAGE,
                CHAT_SEND_MESSAGE_SFB,
                CHAT_SEND_MESSAGE_SMS,
                CHAT_SEND_MESSAGE_ANDROID_AUTO,
                CHAT_SEND_MESSAGE_NEWTHREAD,
                CONVERT_SPANNABLE_TO_HTML,
                SAVE_LOCAL_MESSAGE_TO_DB,
                UPLOAD_IMAGES,
                UPLOAD_STATIC_MAP_IMAGES,
                UPLOAD_VOICE_MESSAGES,
                SERVER_SEND_MESSAGE,
                SERVER_EDIT_MESSAGE,
                UPLOAD_BITMAP_TO_AMS,
                UPLOAD_VOICE_MESSAGE_FILE_TO_AMS,
                FORWARD_EXISTING_AMS_OBJECT,
                UPLOAD_STATIC_MAP_TO_AMS,
                SHARE_VAULT,
                ACCESS_VAULT,
                MESSAGING_SEND_MESSAGE,
                MESSAGING_FORWARD_MESSAGE,
                EDIT_SEND_MESSAGE,
                EDIT_MESSAGE,
                MESSAGING_SEND_REPLY,
                NETWORK_RECONNECT_BEGIN,
                NETWORK_RECONNECT_FAIL,
                NATIVE_CONTACT_SYNC,
                CREATE_NEW_CHAT_THREAD,
                NETWORK_RECONNECT_SUCCESS,
                APP_AUTHENTICATION,
                MSAL_APP_AUTHENTICATION,
                AUTH_SSO_TOKEN,
                AUTH_ADAL_FORCE_PROMPT,
                AUTH_MSAL_FORCE_PROMPT,
                SAVE_AUSER_TO_LOCAL_DB,
                ACQUIRE_ADAL_TOKEN,
                AUTH_TOKENS_RENEW,
                AUTH_MSAL_TOKENS_RENEW,
                ENROLL_WITH_INTUNE,
                ENROLL_MAM,
                UNENROLL_MAM,
                ACQUIRE_PASSTHROUGH_TOKEN,
                LOAD_AUTH_FOR_MONKEY_TEST,
                POST_MESSAGE_ACTION_CHAIN,
                AUTH_SKYPE_TOKENS_RENEW,
                AUTH_MSAL_SKYPE_TOKENS_RENEW,
                CREATE_ONE_TO_ONE_CALL,
                CREATE_ONE_TO_ONE_INTEROP_SFB_CALL,
                CREATE_ONE_TO_ONE_INTEROP_SFC_CALL,
                CALL_ROUTING_UPDATE_CLIENT_PREFERENCE,
                GET_CALL_LOGS,
                PSTN_CALL,
                EMERGENCY_CALL,
                EMERGENCY_CALL_DIRECT_ROUTING,
                SHARE_LLDP_INFO,
                JOIN_SCHEDULED_MEETUP,
                JOIN_MEET_NOW_MEETING,
                JOIN_MEETUP_RETRY,
                JOIN_SCHEDULED_MEETUP_BROADCAST,
                CALL_ME_BACK_MEETING,
                CALL_JOIN_FROM_DEEP_LINK,
                CALL_JOIN_FROM_DEEP_LINK_BROADCAST,
                CALL_JOIN_FROM_DEEP_LINK_BROADCAST_ANONYMOUS,
                CALL_JOIN_FROM_DEEP_LINK_ANONYMOUS,
                CALL_JOIN_FROM_DEEP_LINK_ANONYMOUS_TFL,
                JOIN_AD_HOC_MEETING,
                LOAD_TENANT_LIST,
                LOAD_TENANT_LIST_CONSUMER,
                SWITCH_TENANT,
                GET_TENANT_LIST,
                CHAT_SEND_MESSAGE_FEDERATED,
                VOIP_CALL,
                CALL_SERVICE_INIT,
                CALL_SERVICE_LOGIN,
                VERIFY_SKYLIB_STATE,
                UPDATE_SKYLIB_SKYPE_TOKEN_ON_APP_TOKEN_RFRESH,
                UPDATE_SKYLIB_SKYPE_TOKEN_ON_REFRESH_MESSAGE,
                UPDATE_SKYLIB_SKYPE_TOKEN_ON_SKYLIB_EVENT,
                STOP_SERVICES,
                AUTHENTICATE_USER,
                SWITCH_TENANT_DB,
                PROCESS_DEEPLINKS,
                REGISTER_BADGE_COUNT_SERVICE,
                GET_BADGE_COUNT,
                GET_TENANT_LIST_WITH_NOTIFICATIONS,
                RESOLVE_FEDERATED_USER_BY_EMAIL,
                RESOLVE_FEDERATED_USER_BY_MRI,
                LOG_IN_ON_SKY_LIB_STATUS_CHANGE_EVENT,
                RE_INITIALIZE_ON_SKY_LIB_STATUS_CHANGE_EVENT,
                RE_INITIALIZE_ON_INVALID_SKY_LIB_RUNNING_STATUS,
                ON_CALL_PUSH_MESSAGE,
                ON_CALL_QUEUE_CALL_PUSH_MESSAGE,
                ON_ACTIVE_CALL_PUSH_MESSAGE,
                WARM_PUSH_CALL_MESSAGE,
                JOIN_MEETUP_REPLY_CHAIN,
                CREATE_GROUP_CALL,
                JOIN_GROUP_CALL,
                JOIN_ACTIVE_ONE_ON_ONE_CALL,
                DELETE_MEETING,
                RETRY_CALL_PUSH_MESSAGE,
                ON_CALL_HANDLER_NULL,
                SET_UP_MEETING,
                SCENARIO_CHAT_LIKE_MESSAGE,
                SCENARIO_CHANNEL_LIKE_MESSAGE,
                SCENARIO_CHAT_UNLIKE_MESSAGE,
                SCENARIO_CHANNEL_UNLIKE_MESSAGE,
                SCENARIO_AT_MENTION,
                GROUP_PROFILE_CARD,
                PEOPLE_LOAD_USER_CARD,
                LOAD_ASSIGNMENTS,
                OPEN_FILE_PICKER_ASSIGNMENT,
                NOTIFICATION_RECEIVED,
                AUTO_PRUNE,
                AUTO_PRUNE_FOR_OPTIMIZING_DB,
                AUTO_PRUNE_HARD_DELETE_RC,
                AUTO_PRUNE_SOFT_DELETE_RC,
                AUTO_PRUNE_DELETE_OTHER_MESSAGES,
                AUTO_PRUNE_DELETE_ACTIVITIES,
                ACQUIRE_RESOURCE_TOKEN,
                ACQUIRE_RESOURCE_TOKEN_INTERACTIVE,
                GET_RESOURCE_TOKEN_INTERACTIVE,
                GET_CROSS_TENANT_RESOURCE_TOKEN,
                ACQUIRE_RESOURCE_TOKEN_MSAL,
                ACQUIRE_RESOURCE_TOKEN_SILENT,
                ACQUIRE_PRIMARY_TOKEN_SILENT,
                ACQUIRE_PRIMARY_TOKEN,
                GET_RESOURCE_TOKEN_SYNC,
                ACQUIRE_PRIMARY_TOKEN_PHONE_NUMBER,
                GET_RESOURCE_TOKEN_ASYNC,
                REFRESH_ADAL_TOKEN,
                REFRESH_MSAL_TOKEN,
                APP_LOGIN_IN_PROGRESS,
                ACQUIRE_ADAL_RESOURCE_TOKEN,
                INITIALIZE_ON_SKYLIB_NOT_INITIALIZED,
                ScenarioName.Team.CREATE,
                ScenarioName.Team.EDIT,
                ScenarioName.Team.DELETE,
                ScenarioName.Team.VALIDATE_NAME,
                ScenarioName.Team.VALIDATE_NAME_SERVER,
                ScenarioName.Team.UPDATE_PROFILE_PICTURE_UPDATE,
                ScenarioName.Team.GET_DEFAULT_TEAM_INITIALS,
                SET_CURRENT_TENANT_ID,
                SCENARIO_CONVERSATION_SYNC,
                SCENARIO_SIGN_OUT,
                SCENARIO_GET_BATCH_THREAD_PROPERTIES,
                ANONYMOUS_JOIN_SETUP,
                ANONYMOUS_CALL_JOIN,
                SYNC_NEW_CHAT_THREAD,
                SYNC_PERSONAL_APPS_ON_ENTITLEMENTS_CHANGE,
                SET_UP_VOCIEMAIL,
                RAISE_HAND,
                HARD_MUTE_ATTENDEE,
                DO_NOT_ALLOW_TO_SHARE_VIDEO,
                DO_NOT_ALLOW_TO_UNMUTE,
                LOWER_HAND,
                LOWER_ALL,
                ALLOW_TO_UNMUTE,
                ALLOW_TO_SHARE_VIDEO,
                CREATE_VOICEMAIL_CALL,
                POST_MESSAGE_VIA_SCHEDULER,
                INCOMING_TRANSFER_REQUEST,
                INCOMING_CALL_QUEUE_TRANSFER_REQUEST,
                TRANSFER_CALL,
                CONSULT_TRANSFER_CALL,
                END_CALL,
                PARK_CALL,
                UNPARK_CALL,
                UNPARK_SLA_CALL,
                UNPARK_CALL_FOR_HOLD,
                UNPARK_CALL_FOR_HOLD_V2,
                UNPARK_CALL_FOR_END_CALL,
                PARK_CALL_FOR_HOLD,
                PARK_CALL_FOR_HOLD_V2,
                SET_UP_CALL_TRANSFER,
                MEDIA_CONNECTED,
                CALL_ME_BACK,
                LOAD_PPT_SHARE,
                INIT_PPT_VIEWER,
                ON_CALL_LONGPOLL_MESSAGE,
                ON_MESSAGE_RECEIVED,
                CALLING_CALL_DISCONNECTED,
                CHAT_DELETE_MESSAGE,
                CHAT_CONTEXT_MENU,
                CHANNEL_CONTEXT_MENU,
                FAVORITE_CHANNEL,
                PEOPLE_PICKER,
                PEOPLE_PICKER_LOAD_RESULTS,
                CHANNEL_DELETE_MESSAGE,
                PRE_CALL_TO_IN_CALL,
                SYNC_LOCAL_RN_APP,
                SYNC_CODEPUSH_RN_APP,
                SYNC_CODEPUSH_CHECK_UPDATE,
                SYNC_CODEPUSH_DOWNLOAD_BUNDLE,
                SDK_LOAD_APP_CONTAINER,
                SDK_APP_LOADED_FULLY,
                SDK_APP_LOADER_APPEAR,
                SDK_LOAD_ACTIVE_COMPONENT,
                SDK_FIRST_VIEW_VISIBLE,
                PRE_INIT_RN_MODULE,
                RN_APP_RENDER,
                BRIDGE_THREAD_ACTIVATION,
                RN_SO_FILE_LOAD,
                RN_PACKAGES_INIT,
                UI_MANAGER_LOAD,
                CONTENT_APPEAR,
                JS_BUNDLE_LOAD,
                RN_RESOURCE_TOKEN_FETCH,
                RN_RESOURCE_TOKEN_EAGER_FETCH,
                RN_SKYPE_TOKEN_FETCH,
                RN_ALL_TEAMS_FETCH,
                RN_TEAMS_WITH_IDS_FETCH,
                RN_HTTP_CALL,
                RN_TABS_FOR_CHANNEL_WITH_IDS_FETCH,
                SYNC_MOBILE_MODULES,
                DB_MIGRATION,
                DB_MIGRATION_ON_ERROR,
                DB_CHECK_AND_UPDATE,
                CALL_HISTORY_LOAD,
                CALLING_SERVICE_INIT_APP_FOREGROUND,
                LOAD_STATIC_TAB,
                ON_READ_RECEIPTS_LONGPOLL_MESSAGE,
                STAR_MESSAGE,
                LOAD_BOOKMARKS,
                LOAD_BOOKMARKS_CONVERSATION,
                LOAD_BOOKMARKS_FEED_ID,
                LOAD_ACTIVITY_LIST,
                SHOW_MEETINGS_TAB,
                SHOW_MEETING_DETAILS,
                SHOW_MEETING_PARTICIPANTS,
                CHAT_MEETING_PARTICIPANTS,
                MEETING_OPTIONS,
                MEETING_DETAILS,
                MEETING_EXCHANGE_FILE_DOWNLOAD,
                MEETING_INSIGHT_FETCH,
                MEETING_INSIGHT_RENDER,
                CREATE_MEETING,
                EDIT_MEETING,
                CREATE_CHANNEL_MEETING,
                EDIT_CHANNEL_MEETING,
                CREATE_ONE_ON_ONE_CONSUMER_GROUP,
                CREATE_CONSUMER_GROUP_EVENT,
                ADD_TO_CALENDAR,
                ADD_TO_MEETING,
                ADD_TO_CHAT,
                CANCEL_APPOINTMENT,
                CANCEL_PRIVATE_MEETING,
                CANCEL_CHANNEL_MEETING,
                CANCEL_MEETING,
                EVENT_RSVP,
                FORWARD_MEETING,
                GET_TIMEZONE,
                GET_AVAILABILITY,
                CREATE_DUMMY_MEETING,
                DELETE_DUMMY_MEETING,
                UPDATE_DUMMY_MEETING,
                GET_FREEMIUM_AD_HOC_MEETING_LIST,
                MEETING_REMINDER_WORKER,
                CALENDAR_SYNC_LOCAL_BY_DATE_RANGE,
                CALENDAR_SYNC_LOCAL_BY_MEETING_IDS,
                DEVICES_ADMIN_AGENT_PROVISION_OTP,
                DEVICES_ADMIN_AGENT_CHECK_PROVISION_STATUS,
                DEVICES_ADMIN_AGENT_BROADCAST_EVENT,
                DEVICES_ADMIN_AGENT_REMOTE_LOGIN_REQUEST,
                DEVICES_ADMIN_AGENT_REMOTE_LOGOUT_REQUEST,
                CP_DCF_LOGIN_REQUEST,
                OPEN_EXISTING_CHAT,
                LOAD_USER_ACTIVITY,
                PULL_REFRESH_ALERTS,
                SCENARIO_FRE_TASK,
                SCENARIO_SYNC_FRE_DATA,
                SCENARIO_SYNC_BOOTSTRAP_FREMIUM,
                SCENARIO_SYNC_MOBILE_MODULES,
                SCENARIO_UPDATE_THREAD,
                SYNC_TEAM_LIST,
                SCENARIO_INITIALIZE_REACT_NATIVE_MANAGER,
                SCENARIO_SYNC_DATA_FOR_CALLS_TABS,
                SCENARIO_SYNC_APPDEF_AND_CHATMSG_SYNC,
                SYNC_MESSAGES_FOR_CONVERSATION,
                SYNC_MESSAGES_FOR_FAV_CONVERSATIONS,
                SCENARIO_SYNC_BLOCK_LIST,
                SCENARIO_SYNC_USERS,
                SCENARIO_SYNC_USERS_FEATURE_SETTINGS,
                SCENARIO_SYNC_VOICEMAIL,
                SCENARIO_GET_THREAD_USERS,
                SCENARIO_GET_TENANT_THREAD_USERS,
                SCENARIO_UPDATE_THREAD_PROPS,
                SCENARIO_GET_PERSONAL_STREAM,
                SCENARIO_GET_CONVERSATIONS,
                SCENARIO_CONVERSATION_DATA_TRANSFORM,
                APP_FRE_SYNC,
                APP_FRE_SYNC_ON_ACTION,
                APP_FRE_SYNC_RETRY,
                APP_FRE_SYNC_ON_FREEMIUM_ACTION,
                APP_FRE_SYNC_ON_FREEMIUM_INVITATION,
                SCENARIO_SYNC_USER_ENTITLEMENTS_AND_APP_DEFINITIONS,
                SCENARIO_SYNC_CHAT_ENTITLEMENTS_AND_APP_DEFINITIONS,
                SCENARIO_SYNC_TEAM_ENTITLEMENTS_AND_APP_DEFINITIONS,
                SCREEN_SHARE,
                PARTICIPANT_VBSS_LATENCY,
                PARTICIPANT_VBSS_SUMMARY,
                LIVE_EVENT_HEALTH_REPORT,
                LIVE_EVENT_PLAYBACK_SESSION,
                LIVE_EVENT_AUDIO_DOWNLOAD_FAILURE,
                LIVE_EVENT_BITRATE_CHANGED,
                LIVE_EVENT_BUFFERING,
                LIVE_EVENT_ERROR,
                LIVE_EVENT_LOADED_METADATA,
                LIVE_EVENT_PAUSE,
                LIVE_EVENT_PLAY_COMPLETE,
                LIVE_EVENT_PLAY_STARTED,
                LIVE_EVENT_POTENTIAL_MEDIA_FREEZE,
                LIVE_EVENT_RAPID_SEEKING,
                LIVE_EVENT_READY,
                LIVE_EVENT_RETRY,
                LIVE_EVENT_TICK,
                LIVE_EVENT_VIDEO_DOWNLOAD_FAILURE,
                STREAM_PLAYER_LIVE_EVENT,
                STREAM_PLAYER_MEETING_RECORDING,
                SCENARIO_APP_DEFINITION_DATA_TRANSFORM,
                SHOW_CHANNEL,
                SHOW_CHAT,
                SHOW_CHAT_LIST,
                SHOW_REPLYCHAIN,
                SYNC_MESSAGES_FOR_CHAT,
                FETCH_CHANNELS,
                START_CHAT_CONTAINER_FRAGMENT,
                START_CHAT_FRAGMENT,
                SCENARIO_SYNC_MEETING_DETAILS,
                ACTIVITY_PILL_COUNT,
                BELL_COUNT,
                CHATS_PILL_COUNT,
                CALLS_PILL_COUNT,
                VOICEMAIL_PILL_COUNT,
                NOTES_PILL_COUNT,
                APP_INCREMENTAL_SYNC_LAUNCH,
                APP_INCREMENTAL_SYNC_RESUME,
                LIVE_EVENT_ATTENDEE_JOIN,
                CONVERT_CHAT_VIEW_DATA,
                SHOULD_FILTER_NOTIFICATION_MESSAGE,
                NOTIFICATION_NAV_CHAT,
                NOTIFICATION_NAV_CHAT_FILE_UPLOAD,
                NOTIFICATION_NAV_CHANNEL_CONVERSATION,
                NOTIFICATION_NAV_CHANNEL_CONVERSATION_FILE_UPLOAD,
                BACKGROUND_NOTIFICATION_SYNC_TASK,
                MISSING_NOTIFICATION_START_PROCESS_MESSAGE,
                APP_LAUNCH_SKYLIB_INIT,
                GET_SENDER_SUB_SCENARIO,
                PROCESS_DEEPLINKS_FOR_VALID_USER,
                OPEN_URI,
                SEND_QUICK_REPLY,
                SEND_QUICK_LIKE,
                CHAT_ADD_GIPHY,
                CHAT_ADD_MEME,
                SHOW_TEAM_TABS_TAB,
                LENS_LAUNCH,
                APP_START,
                INVISION_WHITEBOARD_SHARE,
                ANNOTATION_SHARE,
                GET_VOICE_MAIL_FOLDER,
                GET_VOICE_MAIL,
                GET_ACTIVE_CALL_LIST,
                GET_SUGGESTED_ROOMS,
                SEARCH_ROOMS,
                HOLD_CALL,
                RESUME_CALL,
                BREAKOUT_ROOM_BANNER_JOIN,
                BREAKOUT_ROOM_ACCEPT_CALL,
                CALL_HISTORY_UPDATED,
                CALL_VOICEMAIL_LOAD,
                EARLY_CALL_RINGING,
                EARLY_CALL_CANCEL_SHOW_FEEDBACK,
                TASK_MODULE_COMPLETE,
                BOT_INVOKE,
                FETCH_TEAMS_INFO_API,
                LOAD_MESSAGING_EXTENSTION_RESULTS,
                DB_UPGRADE,
                DB_UPGRADE_FROM_SPLASH_ACTIVITY,
                DB_DESTRUCTIVE_DESTROY,
                DB_INITIALIZE,
                DB_HANDLE_SQL_EXCEPTION,
                EARLY_RINGING,
                PLAY_MEETING_RECORDING,
                PLAY_MEETING_RECORDING_ONEPLAYER,
                SKYLIB_SETUP,
                SHARE_IMAGE,
                REFRESH_DELEGATES_DATA,
                CREATE_NEW_DELEGATE,
                REMOVE_DELEGATE,
                DELEGATE_NOTIFICATION,
                EDIT_DELEGATE,
                SLA_PUSH_MESSAGE,
                SLA_ACTIVE_CALLS_VIEWED,
                SLA_RESUME_PARKED_CALL_VIEWED,
                SLA_PARKED_CALL_GROUP_EXPANDED,
                SLA_RESUME_PARKED_CALL_CLICKED,
                MEETING_JOIN_BY_CODE_JOINING,
                GROUP_CALLS_EXPAND_CLICKED,
                JOIN_WEBINAR,
                USER_VOICE_SETTINGS,
                PEOPLE_V2_FETCH_CONTACTS_AND_GROUPS,
                PEOPLE_V2_FETCH_GROUPS,
                PEOPLE_V2_ADD_CONTACT_GROUP,
                PEOPLE_V2_REMOVE_CONTACT_GROUP,
                PEOPLE_V2_CREATE_CONTACT,
                PEOPLE_V2_EDIT_CONTACT,
                PEOPLE_V2_RENAME_CONTACT_GROUP,
                PEOPLE_V2_DELETE_CONTACT_GROUP,
                PEOPLE_V2_CREATE_CONTACT_GROUP,
                SAVE_IMAGE,
                OPEN_IMAGE,
                EDIT_IMAGE,
                FORWARD_IMAGE,
                TIME_BASED_RETENTION,
                TIME_BASED_RETENTION_SHARED_CHANNELS,
                CREATE_NEW_CHAT_THREAD_SUBSCENARIO,
                AUTH_SKYPE_TOKENS_RENEW_FORCE_ADAL,
                AUTH_SKYPE_TOKENS_RENEW_FORCE_MSAL,
                INTUNE_REMEDIATE_COMPLIANCE_POLICY,
                INTUNE_REMEDIATE_COMPLIANCE,
                SWITCH_USER,
                ADD_USER,
                SCENARIO_SET_CURRENT_USER,
                TENTATIVE_SIGNIN,
                REDEEM_CONSUMER_TENANT,
                CLEAR_CACHES,
                CLEAR_DB,
                CLEAR_NOTIFICATION_CHANNELS,
                CLEAR_ACCOUNT_PREFS,
                CLEAR_PREFS,
                CLEAR_AUTH_PROVIDER_CACHE,
                REDIRECT_TO_FRE,
                SIGNOUT_USER_FROM_OTHER_SERVICES,
                DELETE_EDF_REGISTRATION,
                DELETE_BADGE_COUNT_SERVICE_REGISTRATION,
                DELETE_UPS_ENDPOINT,
                DELETE_END_POINT,
                DELETE_SINGLE_TENANT_DB,
                DELETE_MULTI_TENANT_DB,
                CLEAR_SINGLE_TENANT_DB,
                CLEAR_MULTI_TENANT_DB,
                DB_CLEAR,
                Search.SEARCH,
                Search.TOP_N_CACHE_SYNC,
                Search.CHANNEL_CONTEXTUAL_SEARCH,
                Search.CHAT_CONTEXTUAL_SEARCH,
                Search.SPELLER_WILL_SHOW,
                Search.SPELLER_SHOWN,
                Search.RECOURSE_LINK_SHOWN,
                Search.SEARCH_FILE_LOCAL,
                Search.SEARCH_FILE_SERVER,
                Search.SEARCH_CHAT_LOCAL,
                Search.SEARCH_CHAT_SERVER,
                Search.SEARCH_CHANNEL_LOCAL,
                Search.SEARCH_CHANNEL_SERVER,
                Search.SEARCH_MESSAGE_LOCAL,
                Search.SEARCH_MESSAGE,
                Search.SEARCH_DEVICE_CONTACTS,
                Search.SEARCH_PEOPLE_LOCAL,
                Search.SEARCH_PEOPLE_LOCAL_TOP_N_CACHE,
                Search.SEARCH_SDK_APP_CONTACTS,
                Search.SEARCH_PEOPLE,
                Search.SEARCH_TEAM_MEMBERS,
                Search.SEARCH_INSTANT_SCD,
                Search.SEARCH_FILE_MSAI,
                Search.SEARCH_FILES_SEARCH_SDK,
                Search.SEARCH_FILES_LOCAL_SEARCH_SDK,
                Search.SEARCH_WARM_UP,
                Search.MSAI_SDK_INIT,
                Search.MSAI_FETCH_TOKEN,
                Search.MSAI_ERROR,
                Search.MSAI_ANSWER_SEARCH,
                Search.MSAI_UNIVERSAL_SEARCH,
                Search.MSAI_UNIVERSAL_SEARCH_FALLBACK_MESSAGE,
                Search.MSAI_UNIVERSAL_SEARCH_FALLBACK_FILE,
                Search.SEARCH_ANSWER_MSAI,
                Search.MSAI_ANSWER_SEARCH,
                Search.MSAI_UNIVERSAL_SEARCH,
                Search.MSAI_UNIVERSAL_SEARCH_FALLBACK_MESSAGE,
                Search.MSAI_UNIVERSAL_SEARCH_FALLBACK_FILE,
                Search.MSAI_SUGGESTION_SEARCH,
                LOAD_CHANNEL_TAB,
                EXTENSIBILITY_APP_AUTHENTICATION,
                EXTENSIBILITY_BOT_TO_USER_FILE_ATTACHMENT,
                CORTANA_BACKGROUND_TOKEN_REFRESH,
                CORTANA_ADMIN_POLICY_REFRESH,
                CORTANA_WAKE_WORD_ACTIVE,
                CORTANA_SKILL_ACTION_EXECUTION,
                CORTANA_KWS,
                CORTANA_AUTO_CLOSE,
                CORTANA_SKILL_ACTION_DELAY,
                CORTANA_SDK_EVENTS,
                CORTANA_MORE_MENU,
                CORTANA_INITIALIZATION,
                CORTANA_WATCHDOG,
                CORTANA_RESPONSE_ERROR,
                DOCK_MESSAGE_ACK,
                DOCK_CONNECTED,
                DOCK_DISCONNECTED,
                PREJOIN_FETCH_MEETING_METADATA,
                TARGETING_GET_TEAM_TAG_CARDS_REQUEST,
                TARGETING_GET_TAG_MEMBERS_REQUEST,
                TARGETING_REMOVE_TAG_MEMBERS_REQUEST,
                TARGETING_ADD_TAG_MEMBERS_REQUEST,
                TARGETING_CREATE_TAG_REQUEST,
                TARGETING_VIEW_MANAGE_TAGS,
                TARGETING_VIEW_TAG_MEMBERS,
                TARGETING_CREATE_TAG,
                TARGETING_ADD_TAG_MEMBER,
                TARGETING_REMOVE_TAG_MEMBER,
                TARGETING_GET_TENANT_SETTINGS_REQUEST,
                TARGETING_GET_TAG_CARDS_AND_TENANT_SETTINGS_REQUEST,
                RNLSync.BUDDY_SYNC,
                RNLSync.OUTLOOK_CONTACT_SYNC,
                RNLSync.RNL_CONTACTS_SYNC,
                RNLSync.REVERSE_NUMBER_LOOKUP,
                People.PEOPLE_APP_SYNC,
                People.PEOPLE_CONTACT_LISTS_SYNC,
                People.PEOPLE_CONTACT_SYNC,
                People.DELETE_CONTACT,
                People.ADD_TO_CONTACT,
                SCENARIO_INSTALL_APP_IN_PERSONAL_SCOPE,
                SCENARIO_INSTALL_APP_IN_CHAT_OR_TEAM,
                SYNC_CONTACTS,
                ENABLE_PROFILE_SEARCHABILITY,
                FETCH_EPHEMERAL_PROFILE,
                FETCH_ME_PROFILE,
                LOAD_PROFILE_PAGE,
                LOAD_CHAT_AFTER_FRE,
                INSTALL_REFERRER_RESPONSE,
                STATUS_NOTE_SET_STATUS_ACTIVITY,
                STATUS_NOTE_START_SET_STATUS_ACTIVITY,
                STATUS_NOTE_SET_STATUS_REQUEST,
                TROUTER_REGISTRATION,
                App.APP_START_COLD,
                App.APP_START_WARM,
                OUTGOING_CALL_QUEUE,
                INCOMING_CALL_QUEUE,
                CALL_BLOCK_NUMBER,
                CALL_BLOCK_CONTACT,
                CALL_BLOCK_SFCUSER,
                CALL_UNBLOCK_NUMBER,
                CALL_UNBLOCK_CONTACT,
                CALL_UNBLOCK_SFCUSER,
                CALL_ACCEPT,
                ACCEPT_CALL_QUEUE_CALL,
                CALL_AUTO_ANSWER,
                TRANSFER_ACCEPTED_CALL_QUEUE_CALL,
                BROADCAST_EMERGENCY_INFO,
                CALL_JOIN_COMPLETE,
                CALL_RENDER_VIDEO,
                CALL_START_VIDEO,
                CALL_STOP_VIDEO,
                CALL_START_VIDEO_USER_ACTION,
                CALL_STOP_VIDEO_USER_ACTION,
                MERGE_CALL,
                TOGGLE_BACKGROUND_BLUR,
                LOAD_PERSISTED_BG_REPLACEMENT_EFFECT,
                APPLY_BG_REPLACEMENT_EFFECT,
                BG_REPLACEMENT_DOWNLOAD_IMAGES,
                Now.DELETE_ITEM,
                Now.CLEAR_ALL,
                Now.DATA_PUSH,
                Now.REFRESH_ITEMS,
                Now.EXTENSION_SYNC,
                Now.TOTAL_SYNC,
                Now.INIT_POLLING_MAP,
                SET_UP_JOIN_WITHOUT_MODALITY_MEETING,
                ScenarioName.Calendar.WHOLE_CALENDAR_LOAD,
                ScenarioName.Calendar.AGENDA_VIEW_LOAD,
                ScenarioName.Calendar.EMPTY_PANEL_LOAD,
                ScenarioName.Calendar.MINI_MONTH_LOAD,
                ScenarioName.Calendar.SERVER_FETCH_DATE_PICKER_VIEW,
                ScenarioName.Calendar.SERVER_FETCH_DATE_PICKER_VIEW_INCREMENTAL,
                ScenarioName.Calendar.SERVER_FETCH_DATE_PICKER_VIEW_CHAIN,
                ScenarioName.Calendar.SERVER_FETCH_DATE_PICKER_VIEW_INCREMENTAL_CHAIN,
                ScenarioName.Calendar.SERVER_FETCH_AGENDA_VIEW,
                ScenarioName.Calendar.SERVER_FETCH_AGENDA_VIEW_GROUP,
                ScenarioName.Calendar.DB_FETCH_AGENDA_VIEW,
                ScenarioName.Calendar.DB_FETCH_DATE_PICKER_VIEW,
                ScenarioName.Calendar.PAGINATED_LOCAL_SYNC_FOCUS_DAYS,
                ScenarioName.Calendar.PAGINATED_LOCAL_SYNC_NON_FOCUS_DAYS,
                ScenarioName.Calendar.PAGINATED_REMOTE_SYNC_FOCUS_DAYS,
                ScenarioName.Calendar.PAGINATED_REMOTE_SYNC_NON_FOCUS_DAYS,
                SET_UP_JOIN_GROUP_CALL,
                SET_UP_JOIN_ACTIVE_ONE_ON_ONE_CALL,
                WorkManager.EXECUTION,
                GRANT_URI_READ_PERMISSION,
                GET_PENDING_MEMBERS_REQUEST,
                GET_COUNT_OF_PENDING_MEMBERS_REQUEST,
                UNLOCK_TEAM_REQUEST,
                MIGRATE_INDEX_SCENARIO,
                POST_MESSAGE_VIA_WRAPPER,
                PeoplePicker.PEOPLE_PICKER_SERVER_RESULT_SOURCE,
                Auth.EMPTY_USER_OBJECT_ID,
                DeviceAddressBookSync.SCENARIO_DEVICE_ADDRESS_BOOK_SYNC,
                DeviceAddressBookSync.SCENARIO_DEVICE_ADDRESS_BOOK_UN_SYNC,
                SYNC_APP_POLICIES,
                SMART_REPLY_ENABLED,
                SMART_COMPOSE_ENABLED,
                SMART_COMPOSE_INITIALIZATION,
                SMART_COMPOSE_RECEIVED,
                SMART_COMPOSE_TYPE_THROUGH,
                SMART_COMPOSE_NOT_MATCHED,
                SMART_COMPOSE_NOT_ACCEPTED,
                SMART_COMPOSE_NOT_RENDERED,
                SMART_COMPOSE_REQUEST_SENT,
                SMART_COMPOSE_SESSION_CREATED,
                SMART_REPLY_RECEIVED,
                SMART_REPLY_BANNED,
                TRANSLATE_CHAT_MESSAGE,
                TRANSLATE_CHANNEL_MESSAGE,
//                ON_DEMAND_TRANSLATE_CHAT,
                TRANSLATION_SUPPORTED_LANGUAGES,
                PROXIMITY_SENSOR_SERVICE_EVENTS,
                SCENARIO_CAST_SCREEN_TO_DEVICE,
                SCENARIO_CAST_FILE_TO_DEVICE,
                SCENARIO_SELECT_CAST_DEVICE,
                SCENARIO_RETRYING_FROM_DEVICE,
                SfcInterop.SFC_BLOCK,
                SfcInterop.SFC_UNBLOCK,
                BetterTogether.INITIALIZE_TRANSPORT,
                BetterTogether.INITIALIZE_ENDPOINT,
                BetterTogether.TRANSPORT_DISCOVER_ENDPOINTS,
                BetterTogether.TRANSPORT_UPDATE_SKYPE_TOKEN,
                BetterTogether.TRANSPORT_UPDATE_SKYPE_TOKEN_CALLBACK,
                BetterTogether.TRANSPORT_SEND_COMMAND,
                BetterTogether.TRANSPORT_RE_REGISTER,
                BetterTogether.TRANSPORT_COMMAND_RECEIVED,
                BetterTogether.DISCOVER_DEVICES,
                BetterTogether.PAIR_WITH_DEVICE,
                BetterTogether.PAIR_WITH_USER,
                BetterTogether.UNPAIR_WITH_DEVICE,
                BetterTogether.HANDLE_RESPONSE_COMMAND,
                BetterTogether.HANDLE_KEEP_ALIVE_COMMAND,
                BetterTogether.HANDLE_LOCK_OR_UNLOCK_COMMAND,
                BetterTogether.HANDLE_CALL_START_COMMAND,
                BetterTogether.HANDLE_MEETING_START_COMMAND,
                BetterTogether.HANDLE_CALL_END_COMMAND,
                BetterTogether.HANDLE_MEETING_END_COMMAND,
                BetterTogether.OUTGOING_CALL_END_COMMAND,
                BetterTogether.OUTGOING_CALL_START_COMMAND,
                BetterTogether.OPEN_FILE_ON_CONNECTED_DEVICE,
                BetterTogether.OPEN_SHARE_ON_CONNECTED_DEVICE,
                BetterTogether.COLLECT_LOGS_FROM_CONNECTED_DEVICE,
                BetterTogether.REPLY_ON_CONNECTED_DEVICE,
                BetterTogether.CONNECTED_DEVICES_SETTINGS,
                BetterTogether.HANDLE_INC_CALL_HOLD,
                BetterTogether.HANDLE_INC_CALL_MUTE,
                BetterTogether.HANDLE_INC_CALL_RESUME,
                BetterTogether.HANDLE_INC_CALL_UNMUTE,
                BetterTogether.HANDLE_INC_CALL_SPEAKER_MUTE,
                BetterTogether.HANDLE_INC_CALL_SPEAKER_UNMUTE,
                BetterTogether.HANDLE_INC_CALL_START_VIDEO,
                BetterTogether.HANDLE_INC_CALL_STOP_VIDEO,
                BetterTogether.HANDLE_INC_CALL_UNKNOWN_COMMAND,
                BetterTogether.HANDLE_INC_CAPTIONS_ON,
                BetterTogether.HANDLE_INC_CAPTIONS_OFF,
                BetterTogether.HANDLE_INC_VOLUME_DOWN,
                BetterTogether.HANDLE_INC_VOLUME_UP,
                BetterTogether.HANDLE_INC_STAGE_LAYOUT_SHOW_GALLERY,
                BetterTogether.HANDLE_INC_STAGE_LAYOUT_SHOW_CONTENT,
                BetterTogether.HANDLE_INC_STAGE_LAYOUT_SHOW_GALLERY_CONTENT,
                BetterTogether.HANDLE_INC_STAGE_LAYOUT_SHOW_LARGE_GALLERY,
                BetterTogether.HANDLE_INC_STAGE_LAYOUT_SHOW_TOGETHER,
                BetterTogether.HANDLE_INC_ROOM_STATE_UPDATE,
                BetterTogether.JOIN_BETTER_TOGETHER_MEETING,
                BetterTogether.SEND_KEEP_ALIVE_TO_PAIRED_ENDPOINT,
                BetterTogether.OUTGOING_CALL_HOLD,
                BetterTogether.OUTGOING_CALL_MUTE,
                BetterTogether.OUTGOING_CALL_RESUME,
                BetterTogether.OUTGOING_CALL_START_VIDEO,
                BetterTogether.OUTGOING_CALL_STOP_VIDEO,
                BetterTogether.OUTGOING_CALL_TRANSFER,
                BetterTogether.OUTGOING_CALL_UNMUTE,
                BetterTogether.OUTGOING_UNKNOWN_COMMAND,
                BetterTogether.OUTGOING_ROOM_STATE_UPDATE,
                BetterTogether.OUTGOING_ROOM_CAPABILITIES_UPDATE,
                BetterTogether.ROOM_CONTROL_MUTE,
                BetterTogether.ROOM_CONTROL_UNMUTE,
                BetterTogether.ROOM_CONTROL_CAMERA_ON,
                BetterTogether.ROOM_CONTROL_CAMERA_OFF,
                BetterTogether.ROOM_CONTROL_VOLUME_UP,
                BetterTogether.ROOM_CONTROL_VOLUME_DOWN,
                BetterTogether.ROOM_CONTROL_CAPTIONS_ON,
                BetterTogether.ROOM_CONTROL_CAPTIONS_OFF,
                BetterTogether.ROOM_CONTROL_LEAVE_MEETING,
                BetterTogether.ROOM_CONTROL_STAGE_LAYOUT_SHOW_GALLERY,
                BetterTogether.ROOM_CONTROL_STAGE_LAYOUT_SHOW_CONTENT,
                BetterTogether.ROOM_CONTROL_STAGE_LAYOUT_SHOW_GALLERY_CONTENT,
                BetterTogether.ROOM_CONTROL_STAGE_LAYOUT_SHOW_LARGE_GALLERY,
                BetterTogether.ROOM_CONTROL_STAGE_LAYOUT_SHOW_TOGETHER,
                BetterTogether.BEGIN_PAIR_WITH_CODE,
                BetterTogether.PAIR_WITH_CODE,
                BetterTogether.JOIN_CALL_SIGNALLING,
                BetterTogether.SYNC_ACTIVE_CALL,
                BetterTogether.SESSION_SETUP,
                INVITE_OFF_NETWORK_CONTACTS,
                UPDATE_GROUP_AVATAR,
                START_CHAT_ACTIVITY,
                START_CHAT_APP_END_TO_END,
                ScenarioName.KingstonNotificationsModule.NEW_CHAT_NOTIFICATION,
                ScenarioName.KingstonNotificationsModule.NEW_ACTIVITY_NOTIFICATION,
                ScenarioName.KingstonNotificationsModule.REFRESH_NOTIFICATIONS_MODULE,
                ScenarioName.KingstonNotificationsModule.REFRESH_NOTIFICATIONS,
                ScenarioName.KingstonNotificationsModule.REFRESH_MEETINGS,
                ScenarioName.KingstonNotificationsModule.REFRESH_NOTES,
                ScenarioName.KingstonNotificationsModule.SYNC_ACTIVITY_FEED,
                ScenarioName.KingstonNotificationsModule.SYNC_MEETINGS,
                ScenarioName.KingstonNotificationsModule.LOAD_MORE_NOTIFICATIONS,
                START_TEAMS_AND_CHANNELS_ACTIVITY,
                SHOW_TEAMS_AND_CHANNELS_LIST,
                START_CONTACTS_APP_END_TO_END,
                START_TEAMS_AND_CHANNELS_APP_END_TO_END,
                START_FAVORITE_APP_END_TO_END,
                START_HISTORY_APP_END_TO_END,
                START_VOICEMAIL_APP_END_TO_END,
                START_ORG_APP_END_TO_END,
                START_FILES_APP_END_TO_END,
                VOICEMAIL_PERIODIC_SYNC,
                ScenarioName.SYNC_CONVERSATION_THREAD,
                GET_THREAD_PROPERTIES_SINGLE,
                ScenarioName.IPPhoneCompanyPortalIntents.LAUNCH_COMPANY_PORTAL,
                ScenarioName.IPPhoneCompanyPortalIntents.UNENROLL_USER,
                ScenarioName.IPPhoneCompanyPortalIntents.ENROLLMENT_STATUS,
                ScenarioName.IPPhoneCompanyPortalIntents.UNENROLLMENT_STATUS,
                ScenarioName.IPPhoneCompanyPortalIntents.SIGN_IN_READINESS_STATUS,
                ScenarioName.IPPhoneCompanyPortalIntents.CHECK_AAD_TOKEN,
                BYOM_AUTO_ANSWER,
                REACTIONS_FETCH_USERS,
                BYOM_AUTO_ANSWER,
                ScenarioName.PinnedChats.PIN_CHAT_ITEM,
                ScenarioName.PinnedChats.UNPIN_CHAT_ITEM,
                ScenarioName.PinnedChats.UPDATE_PINNED_CHATS,
                ScenarioName.PinnedChats.SYNC_PINNED_CHATS,
                ScenarioName.Vault.CREATE_USER,
                ScenarioName.Vault.CREATE_VAULT,
                ScenarioName.Vault.CREATE_VAULT_ITEM,
                ScenarioName.Vault.DELETE_VAULT_ITEM,
                ScenarioName.Vault.DELETE_VAULT_ITEMS,
                ScenarioName.Vault.EXPORT_VAULT,
                ScenarioName.Vault.LOAD_VAULT_ITEM,
                ScenarioName.Vault.SEARCH_VAULT,
                ScenarioName.Vault.SYNC_ITEMS,
                ScenarioName.Vault.SYNC_KEYS,
                ScenarioName.Vault.SYNC_VAULT,
                ScenarioName.Vault.UPDATE_VAULT_ITEM,
                ScenarioName.Vault.GRANT_ACCESS,
                ScenarioName.Vault.MANAGE_VAULT_KEY,
                ScenarioName.Vault.DELETE_PERSONAL_VAULT,
                ScenarioName.LiveLocation.LOCATION_ACTIVE_TRACKING,
                ScenarioName.LiveLocation.LOCATION_SHARING_SESSION_START,
                ScenarioName.LiveLocation.LOCATION_SHARING_SESSION_STOP,
                ScenarioName.LiveLocation.LOCATION_SHARING_SESSION_STOP_ALL,
                ScenarioName.LiveLocation.LOCATION_GROUP_MAP_SYNC,
                ScenarioName.LiveLocation.LOCATION_MESSAGE_SEND,
                ScenarioName.LiveLocation.MAP_LOAD,
                ScenarioName.LiveLocation.MAP_MARKERS_LOAD,
                ScenarioName.LiveLocation.STOP_LOCATION_SHARING_LOGOUT,
                ScenarioName.LiveLocation.FAMILY_SYNC,
                ScenarioName.LiveLocation.LOCATION_PLACE_ADD,
                ScenarioName.LiveLocation.LOCATION_PLACE_EDIT,
                ScenarioName.LiveLocation.LOCATION_PLACE_DELETE,
                ScenarioName.LiveLocation.LOCATION_PLACES_FETCH,
                ScenarioName.LiveLocation.LOCATION_TRIGGER_ADD,
                ScenarioName.LiveLocation.LOCATION_TRIGGER_ADD_ALL,
                ScenarioName.LiveLocation.LOCATION_TRIGGER_DELETE,
                ScenarioName.LiveLocation.LOCATION_TRIGGER_DELETE_ALL,
                ScenarioName.LiveLocation.LOCATION_TRIGGER_EDIT,
                ScenarioName.LiveLocation.LOCATION_TRIGGER_DETAIL_FETCH,
                ScenarioName.LiveLocation.LOCATION_TRIGGERS_FETCH,
                ScenarioName.LiveLocation.NOTIFICATION_DELAY,
                ScenarioName.Groups.GROUP_CREATE_NEW_GROUP,
                ScenarioName.Groups.GROUP_UPDATE_GROUP_AVATAR,
                ScenarioName.Groups.GROUP_EDIT_NAME,
                ScenarioName.Groups.GROUP_ADD_USER,
                ScenarioName.Groups.GROUP_REMOVE_USER,
                ScenarioName.Groups.GROUP_CREATE_SHARE_LINK,
                ScenarioName.Groups.GROUP_JOIN_SWITCH,
                ScenarioName.Groups.GROUP_LEAVE_CHAT,
                ScenarioName.Groups.GROUP_MUTE_SWITCH,
                ScenarioName.Groups.GROUP_INVITE_OFF_NETWORK_CONTACTS,
                ScenarioName.Invites.REDEEM_INVITE_LINK,
                ScenarioName.Invites.RECLAIM_TWO_WAY_SMS,
                ScenarioName.CoreIA.OPEN_DASHBOARD_LOAD,
                ScenarioName.CoreIA.DASHBOARD_CALENDAR_TILE_LOAD,
                ScenarioName.CoreIA.DASHBOARD_GALLERY_TILE_LOAD,
                ScenarioName.CoreIA.DASHBOARD_LOCATION_TILE_LOAD,
                ScenarioName.CoreIA.DASHBOARD_VAULT_TILE_LOAD,
                ScenarioName.CoreIA.DASHBOARD_FILES_TILE_LOAD,
                ScenarioName.CoreIA.DASHBOARD_TASKS_TILE_LOAD,
                Files.FILE_UPLOAD_RESUME_CHAT,
                Files.FILE_UPLOAD_RESUME_CHANNEL,
                Files.FILE_UPLOAD_CHANNEL_FOREGROUND_SERVICE,
                Files.FILE_UPLOAD_CHAT_FOREGROUND_SERVICE,
                Files.FILE_UPLOAD_CLEANUP_TASK,
                Files.FILE_SIZE_FETCH,
                Files.FILE_LARGE_THUMBNAIL_PREVIEW,
                Files.FILE_SMALL_THUMBNAIL_PREVIEW,
                Files.FILE_CACHE_CLEANUP_TASK,
                Files.LINK_CHICLET_FORMATION_CHAT,
                Files.LINK_CHICLET_FORMATION_CHANNEL,
                Files.OPEN_LINK_SETTINGS,
                Files.GENERATE_SHARE_LINK,
                Files.ATTACH_AND_SEND_FILE,
                Files.LINK_CHICLET_IN_MESSAGE,
                Files.DOWNLOAD_FILE,
                Files.USER_DOWNLOAD_FILE,
                Files.VROOM_FILE_UPLOAD_CHANNEL_FOREGROUND_SERVICE,
                Files.VROOM_FILE_UPLOAD_CHAT_FOREGROUND_SERVICE,
                Files.VROOM_FILE_UPLOAD_RESUME_CHANNEL,
                Files.VROOM_FILE_UPLOAD_RESUME_CHAT,
                Files.CHAT_FILES_TEAMFILES,
                Files.CHAT_FILES_TEAMFILES_LOCAL,
                Files.SHOW_FILES_IN_DIRECTORY,
                Files.FILE_UPLOAD_CHAT,
                Files.FILE_UPLOAD_CHANNEL,
                Files.FILE_PREVIEW,
                Files.FILE_PREVIEW_WITHIN_TEAMS,
                Files.FILE_PREVIEW_REQUEST,
                Files.FILE_PREVIEW_LOAD,
                Files.FILE_PREVIEW_VIEW,
                Files.ODSP_VIEWER_ASSETS_CACHE,
                Files.FILES_RECENT,
                Files.FILES_RECENT_PAGINATION,
                Files.FILES_PERSONAL,
                Files.FILES_PERSONAL_PAGINATION,
                Files.FILES_TEAMFILES,
                Files.FILES_TEAMFILES_PAGINATION,
                Files.FILES_INCALL,
                Files.FILES_INCALL_PAGINATION,
                Files.FILES_RECENT_LOCAL,
                Files.FILES_PERSONAL_LOCAL,
                Files.FILES_TEAMFILES_LOCAL,
                Files.PDF_CONVERTED_CACHE_PRUNE,
                Files.FILES_TEAMFILES_SPECIALDOCUMENTLIBRARIES,
                Files.FILES_TEAMFILES_SPECIALDOCUMENTLIBRARIESFILELIST,
                Files.FILES_DATA_PRUNE,
                Files.CREATE_FOLDER_CHANNEL,
                Files.CREATE_FOLDER_ONEDRIVE,
                Files.FILE_UPLOAD_INTERIM_FAILURE,
                Files.FILE_UPLOAD_RESUME_SUCCESS,
                Files.FILE_UPLOAD_FIRST_RESUME_ATTEMPT,
                Files.SEND_FILE_UPLOAD_FROM_CHAT_FILES_TAB,
                Files.SHARED_FILES_CLEANUP_TASK,
                Files.CONSUMER_VROOM_FILE_UPLOAD_CHAT_FOREGROUND_SERVICE,
                Files.CONSUMER_VROOM_FILE_UPLOAD_RESUME_CHAT,
                Files.CONSUMER_ONEDRIVE_PROVISIONING_ON_SIGN_IN,
                Files.CONSUMER_ONEDRIVE_PROVISIONING_ON_UPLOAD,
                Files.CONSUMER_ONEDRIVE_PROVISIONING_ON_PERSONAL_FILE_LISTING,
                Gallery.GALLERY_SHOW_SINGLE_IMAGE,
                ScenarioName.STAMPING_OUTSIDE_FRE_TRIGGERED,
                ScenarioName.TOGGLE_SEARCHABILITY,
                ScenarioName.TAB_CONTEXT_APP_SESSION,
                ScenarioName.Feedback.SUBMIT_BRB_FORM,
                ScenarioName.Feedback.SUBMIT_OCV_FORM,
                ScenarioName.BackgroundSync.BACKGROUND_SYNC_SERVICE,
                ScenarioName.BackgroundSync.BACKGROUND_SYNC_SERVICE_WORKER,
                ScenarioName.BackgroundSync.BACKGROUND_SYNC_CONVERSATIONS_SYNC,
                ScenarioName.BackgroundSync.BACKGROUND_SYNC_THREAD_PROPERTIES_AND_USERS,
                ScenarioName.BackgroundSync.BACKGROUND_SYNC_GET_MESSAGES,
                ScenarioName.SHARED_DEVICE_ACTIVITY_SCREEN_TIME,
                ScenarioName.PlannerTasks.UPDATE_PLANNER_TASK_AND_NAV_TO_VIEW,
                ScenarioName.PlannerTasks.CREATE_DEFAULT_PLAN_AND_NAV_TO_VIEW,
                ScenarioName.PlannerTasks.CREATE_PERSONAL_PLAN_AND_NAV_TO_VIEW,
                ScenarioName.PlannerTasks.CREATE_PERSONAL_TASK,
                ScenarioName.PlannerTasks.CREATE_PLANNER_PLAN_AND_NAV_TO_VIEW,
                ScenarioName.PlannerTasks.CREATE_PLANNER_TASK,
                ScenarioName.PlannerTasks.DELETE_PERSONAL_PLAN,
                ScenarioName.PlannerTasks.DELETE_PERSONAL_TASK,
                ScenarioName.PlannerTasks.DELETE_PLANNER_PLAN,
                ScenarioName.PlannerTasks.DELETE_PLANNER_TASK,
                ScenarioName.PlannerTasks.RENAME_PERSONAL_PLAN,
                ScenarioName.PlannerTasks.RENAME_PLANNER_PLAN,
                ScenarioName.PlannerTasks.UPDATE_PERSONAL_TASK_AND_NAV_TO_VIEW,
                CALL_NOTIFICATION,
                CALENDAR_NOTIFICATION,
                VOICEMAIL_NOTIFICATION,
                ScenarioName.Fluid.FLUID_LOAD_MESSAGE,
                ScenarioName.Fluid.FLUID_COMPOSE_MESSAGE,
                ScenarioName.Fluid.FLUID_TOKEN_FETCH,
                SIDECAR_PRESENTATION,
                ScenarioName.APP_INCREMENTAL_SYNC_CONVERSATION_RESUME,
                ScenarioName.APP_INCREMENTAL_SYNC_CONVERSATION_LAUNCH,
                ScenarioName.PLATFORM_OPEN_CONVERSATION_SEND_MESSAGE,
                ScenarioName.PLATFORM_OPEN_CONVERSATION_API,
                ScenarioName.APP_INCR_SYNC_LAUNCH_AUTH_USER,
                ScenarioName.APP_INCR_SYNC_RESUME_AUTH_USER,
                ScenarioName.MAKE_OCPS_CALL,
                ScenarioName.MeetNow.MEET_NOW_FLYOUT_OPEN,
                ScenarioName.MeetNow.MEET_NOW_URL_JOIN,
                ScenarioName.MeetNow.MEET_NOW_TAB_OPEN,
                ScenarioName.BackgroundSync.BACKGROUND_SYNC_PROCESS_NOTIFICATIONS,
                SDK_CALL_ANONYMOUS_SETUP,
                ScenarioName.LiveStateService.OPEN,
                ScenarioName.LiveStateService.CLOSE,
                ScenarioName.LiveStateService.SEND_REACTION,
                ScenarioName.HDMISource.CONNECTED_OUTSIDE_CALL,
                ScenarioName.HDMISource.PREVIEW_OUTSIDE_CALL,
                ScenarioName.HDMISource.DISCONNECTED_OUTSIDE_CALL,
                ScenarioName.HDMISource.CONNECTED_INSIDE_CALL,
                ScenarioName.HDMISource.ABLE_TO_SHARE_CONTENT_INSIDE_CALL,
                ScenarioName.HDMISource.STOPPED_DURING_A_CALL,
                ScenarioName.HDMISource.START_PREVIEW,
                ScenarioName.SyncService.ACCOUNT_TENANTS_NOTIFICATION_FRE,
                ScenarioName.SyncService.ALERTS_SYNC_FRE,
                ScenarioName.SyncService.ALERTS_SYNC_EXT_FRE,
                ScenarioName.SyncService.APP_DEFNS_SYNC_FRE,
                ScenarioName.SyncService.APP_DEFNS_SYNC_EXT_FRE,
                ScenarioName.SyncService.BLOCK_LIST_SYNC_FRE,
                ScenarioName.SyncService.BOOKMARKS_SYNC_FRE,
                ScenarioName.SyncService.BOOKMARKS_STREAM_ID_SYNC_FRE,
                ScenarioName.SyncService.CALENDAR_SYNC_FRE,
                ScenarioName.SyncService.CALLS_DATA_SYNC_FRE,
                ScenarioName.SyncService.CALLS_DATA_SYNC_EXT_FRE,
                ScenarioName.SyncService.CALL_LOGS_SYNC_FRE,
                ScenarioName.SyncService.CALL_LOGS_SYNC_EXT_FRE,
                ScenarioName.SyncService.CHECK_SEARCH_STAMPING_FRE,
                ScenarioName.SyncService.CONTACT_GROUPS_SYNC_FRE,
                ScenarioName.SyncService.CONVERSATION_SYNC_FRE,
                ScenarioName.SyncService.CONVERSATION_SYNC_EXT_FRE,
                ScenarioName.SyncService.CORE_MESSAGING_FRE,
                ScenarioName.SyncService.FAVORITE_MESSAGES_SYNC_FRE,
                ScenarioName.SyncService.FAVORITE_MESSAGES_SYNC_EXT_FRE,
                ScenarioName.SyncService.MESSAGES_SYNC_FRE,
                ScenarioName.SyncService.MESSAGES_SYNC_EXT_FRE,
                ScenarioName.SyncService.OTHER_CHATS_TEAM_THREAD_PROPS_SYNC_FRE,
                ScenarioName.SyncService.OTHER_CHATS_TEAM_THREAD_PROPS_SYNC_EXT_FRE,
                ScenarioName.SyncService.PINNED_CHANNELS_SYNC_FRE,
                ScenarioName.SyncService.RECENT_CHATS_THREAD_PROPS_SYNC_FRE,
                ScenarioName.SyncService.RECENT_CHATS_THREAD_PROPS_SYNC_EXT_FRE,
                ScenarioName.SyncService.VAULT_SECRETS_SYNC_FRE,
                ScenarioName.SyncService.VOICEMAIL_SYNC_FRE,
                ScenarioName.SyncService.FRE_SYNC_MESSAGING,
                ScenarioName.SyncService.FRE_SYNC_ENTIRE,
                ScenarioName.SyncService.ACCOUNT_TENANTS_NOTIFICATION_DELTA,
                ScenarioName.SyncService.ALERTS_SYNC_DELTA,
                ScenarioName.SyncService.ALERTS_SYNC_EXT_DELTA,
                ScenarioName.SyncService.APP_DEFNS_SYNC_DELTA,
                ScenarioName.SyncService.APP_DEFNS_SYNC_EXT_DELTA,
                ScenarioName.SyncService.BLOCK_LIST_SYNC_DELTA,
                ScenarioName.SyncService.BOOKMARKS_SYNC_DELTA,
                ScenarioName.SyncService.BOOKMARKS_STREAM_ID_SYNC_DELTA,
                ScenarioName.SyncService.CALENDAR_SYNC_DELTA,
                ScenarioName.SyncService.CALLS_DATA_SYNC_DELTA,
                ScenarioName.SyncService.CALL_LOGS_SYNC_DELTA,
                ScenarioName.SyncService.CALL_LOGS_SYNC_EXT_DELTA,
                ScenarioName.SyncService.CHECK_SEARCH_STAMPING_DELTA,
                ScenarioName.SyncService.CONTACT_GROUPS_SYNC_DELTA,
                ScenarioName.SyncService.CONVERSATION_SYNC_DELTA,
                ScenarioName.SyncService.CORE_MESSAGING_DELTA,
                ScenarioName.SyncService.FAVORITE_MESSAGES_SYNC_DELTA,
                ScenarioName.SyncService.FAVORITE_MESSAGES_SYNC_EXT_DELTA,
                ScenarioName.SyncService.MESSAGES_SYNC_DELTA,
                ScenarioName.SyncService.OTHER_CHATS_TEAM_THREAD_PROPS_SYNC_DELTA,
                ScenarioName.SyncService.PINNED_CHANNELS_SYNC_DELTA,
                ScenarioName.SyncService.RECENT_CHATS_THREAD_PROPS_SYNC_DELTA,
                ScenarioName.SyncService.VAULT_SECRETS_SYNC_DELTA,
                ScenarioName.SyncService.VOICEMAIL_SYNC_DELTA,
                ScenarioName.SyncService.DELTA_SYNC_MESSAGING,
                ScenarioName.SyncService.DELTA_SYNC_ENTIRE,
                ScenarioName.SyncService.DELTA_SYNC_ESSENTIAL,
                ScenarioName.SyncService.SYNC_CONVERSATIONS_ONLY,
                ScenarioName.SyncService.SYNC_AUTH_USER,
                ScenarioName.SyncService.SYNC_PILL_COUNT_UPDATE,
                ScenarioName.SyncService.CORE_MESSAGING_SYNC_TO_SERVER,
                ScenarioName.SyncService.CALL_LOGS_PENDING_CHANGES,
                ScenarioName.SyncService.BOOKMARKS_SYNC_TO_SERVER,
                ScenarioName.SyncService.ADDRESS_BOOK_SYNC_FRE,
                ScenarioName.SyncService.ADDRESS_BOOK_SYNC_DELTA,
                ScenarioName.SyncService.APP_POLICY_SYNC_FRE,
                ScenarioName.SyncService.APP_POLICY_SYNC_DELTA,
                ScenarioName.SyncService.ORS_OCPS_POLICY_SYNC_FRE,
                ScenarioName.SyncService.ORS_OCPS_POLICY_SYNC_DELTA,
                ScenarioName.SyncService.RNL_CONTACTS_SYNC_FRE,
                ScenarioName.SyncService.RNL_CONTACTS_SYNC_DELTA,
                ScenarioName.SyncService.TEAM_MEMBER_TAGS_SYNC_FRE,
                ScenarioName.SyncService.TEAM_MEMBER_TAGS_SYNC_DELTA,
                ScenarioName.SyncService.TOP_N_CACHE_SYNC_FRE,
                ScenarioName.SyncService.TOP_N_CACHE_SYNC_DELTA,
                ScenarioName.SURVIVABILITY_CHECK_APPLIANCE_LIVENESS,
                ScenarioName.SURVIVABILITY_SWITCH_APPLIANCE_MODE,
                ScenarioName.DOWNLOAD_EXTENDED_EMOJI,
                ScenarioName.RoomRemote.ROOM_REMOTE_CLIENT_BANNER_ENTRYPOINT,
                ScenarioName.RoomRemote.ROOM_REMOTE_CLIENT_PARTICIPANT_OPTION_ENTRYPOINT,
                ScenarioName.RoomRemote.ROOM_REMOTE_ROOM_RECEIVE_INCOMING_SESSION,
                ScenarioName.TOGETHER_MODE,
                ScenarioName.ADD_TOGETHER_MODE_BOT,
                ScenarioName.LARGE_GALLERY_MODE,
                ScenarioName.ADD_LARGE_GALLERY_MODE_BOT,
                ScenarioName.MEETING_RECORDING,
                ScenarioName.ADD_RECORDING_BOT,
                ScenarioName.MEETING_LIVE_CAPTION,
                ScenarioName.ADD_LIVE_CAPTION_BOT,
                ScenarioName.Extensibility.MeetingExtensibility.IN_MEETING_NOTIFICATION_SUPPRESSED,
                ScenarioName.Extensibility.MeetingExtensibility.LAUNCH_IN_MEETING_NOTIFICATION_COMPLETION_TIME,
                ScenarioName.Extensibility.MeetingExtensibility.LAUNCH_IN_MEETING_TAB_COMPLETION_TIME,
                ScenarioName.Extensibility.PLATFORM_WEB_APP_LOAD_TIME,
                ScenarioName.MeetingExtension.MEETING_EXTENSION_GET_APPS,
                ScenarioName.MeetingExtension.MEETING_EXTENSION_LAUNCH_APP,
                ScenarioName.MeetingExtension.MEETING_EXTENSION_LAUNCH_NOTIFICATION
        })
public @interface ScenarioName {
    String ADAPTIVE_CARD_CACHE_CLEANUP_WORKER = "adaptive_card_cache_cleanup_worker";
    String ADD_BUDDY = "add_buddy";
    String CHAT_CREATE = "chat_create";
    String CHAT_SEND_MESSAGE = "chat_send_message";
    String CHAT_SEND_MESSAGE_SFB = "chat_send_message_sfb";
    String CHAT_SEND_MESSAGE_SFC = "chat_send_message_sfc";
    String CHAT_SEND_MESSAGE_FEDERATED = "chat_send_message_federated";
    String CHAT_SEND_MESSAGE_SMS = "chat_send_message_sms";
    String CHAT_SEND_MESSAGE_ANDROID_AUTO = "chat_send_message_android_auto";
    String CHAT_SEND_MESSAGE_NEWTHREAD = "chat_send_message_newthread"; // Create a new chat with user and send message
    String MESSAGING_SEND_MESSAGE = "messaging_send_message";
    String MESSAGING_SEND_REPLY = "messaging_send_reply";
    String MESSAGING_FORWARD_MESSAGE = "messaging_forward_message";
    String MESSAGING_ESCALATE_MESSAGE = "messaging_escalate_message";
    String EDIT_SEND_MESSAGE = "edit_send_message";
    String EDIT_MESSAGE = "edit_message";
    String POST_MESSAGE_VIA_SCHEDULER = "post_message_via_job_scheduler";
    String POST_MESSAGE_VIA_WRAPPER = "post_message_via_wrapper";
    String CONVERT_SPANNABLE_TO_HTML = "convert_spannable_to_html";
    String SAVE_LOCAL_MESSAGE_TO_DB = "save_local_message_to_db";
    String POST_MESSAGE_ACTION_CHAIN = "post-message_action_chain";
    String CHAT_DELETE_MESSAGE = "chat_delete_message";
    String CHANNEL_DELETE_MESSAGE = "chanel_delete_message";
    String CHAT_CONTEXT_MENU = "chat_context_menu";
    String CHANNEL_CONTEXT_MENU = "channel_context_menu";
    String FAVORITE_CHANNEL = "admin_favorite_channel"; // Make a channel Favorite
    String PEOPLE_PICKER = "people_picker";
    String PEOPLE_PICKER_LOAD_RESULTS = "people_picker_load_results";
    String UPLOAD_IMAGES = "upload_images";
    String UPLOAD_STATIC_MAP_IMAGES = "upload_static_map_images";
    String SHARE_VAULT = "shareVault";
    String ACCESS_VAULT = "accessVault";
    String UPLOAD_VOICE_MESSAGES = "upload_voice_messages";
    String SERVER_SEND_MESSAGE = "server_send_message";
    String SERVER_EDIT_MESSAGE = "server_edit_message";
    String UPLOAD_BITMAP_TO_AMS = "fileinlineUpload";
    String UPLOAD_VOICE_MESSAGE_FILE_TO_AMS = "voiceMessageUpload";
    String FORWARD_EXISTING_AMS_OBJECT = "forwardExistingAmsObject";
    String UPLOAD_STATIC_MAP_TO_AMS = "staticMapUpload";
    String CREATE_NEW_CHAT_THREAD = "chat_send_message_newthread";
    String CREATE_NEW_CHAT_THREAD_SFC = "chat_send_message_newthread_sfc";
    String CREATE_NEW_CHAT_THREAD_SUBSCENARIO = "chat_send_message_newthread_subscenario";
    String SYNC_NEW_CHAT_THREAD = "chat_send_message_syncthread";
    String APP_AUTHENTICATION = "app_authenticated";
    String MSAL_APP_AUTHENTICATION = "msal_app_authenticated";
    String AUTH_SSO_TOKEN = "auth_sso_token";
    String AUTH_ADAL_FORCE_PROMPT = "auth_adal_force_prompt";
    String AUTH_MSAL_FORCE_PROMPT = "auth_msal_force_prompt";
    String SAVE_AUSER_TO_LOCAL_DB = "save_user-to_local_db";
    String ACQUIRE_ADAL_TOKEN = "acquire_adal_token";
    String ACQUIRE_RESOURCE_TOKEN = "acquire_resource_token";
    String ACQUIRE_PASSTHROUGH_TOKEN = "acquire_passthrough_token";
    String GET_RESOURCE_TOKEN_SYNC = "get_resource_token_sync";
    String GET_RESOURCE_TOKEN_ASYNC = "get_resource_token_async";
    String ACQUIRE_RESOURCE_TOKEN_INTERACTIVE = "acquire_resource_token_interactive";
    String GET_RESOURCE_TOKEN_INTERACTIVE = "get_resource_token_interactive";
    String GET_CROSS_TENANT_RESOURCE_TOKEN = "get_cross_tenant_resource_token";
    String ACQUIRE_RESOURCE_TOKEN_MSAL = "acquire_resource_token_msal";
    String ACQUIRE_ADAL_RESOURCE_TOKEN = "acquire_adal_resource_token";
    String ACQUIRE_MSAL_RESOURCE_TOKEN = "acquire_msal_resource_token";
    String ACQUIRE_RESOURCE_TOKEN_SILENT = "acquire_resource_token_silent";
    String ACQUIRE_PRIMARY_TOKEN_SILENT = "acquire_primary_token_silent";
    String ACQUIRE_PRIMARY_TOKEN = "acquire_primary_token";
    String AUTH_TOKENS_RENEW = "auth_tokens_renew";
    String ACQUIRE_PRIMARY_TOKEN_PHONE_NUMBER = "acquire_primary_token_phone_number";
    String AUTH_MSAL_TOKENS_RENEW = "auth_msal_tokens";
    String AUTHENTICATE_USER = "authenticate_user";
    String LOAD_AUTH_FOR_MONKEY_TEST = "load_auth_for_monkey_test";
    String AUTH_SKYPE_TOKENS_RENEW = "auth_skype_tokens";
    String AUTH_MSAL_SKYPE_TOKENS_RENEW = "auth_msal_skype_tokens";
    String AUTH_SKYPE_TOKENS_RENEW_FORCE_ADAL = "auth_skype_tokens_force_adal";
    String AUTH_SKYPE_TOKENS_RENEW_FORCE_MSAL = "auth_skype_tokens_force_msal";
    String GROUP_PROFILE_CARD = "group_profile_card";
    String PEOPLE_LOAD_USER_CARD = "people_load_usercard";
    String REFRESH_ADAL_TOKEN = "refresh_adal_token";
    String REFRESH_MSAL_TOKEN = "refresh_msal_token";
    String APP_LOGIN_IN_PROGRESS = "app_login_in_progress";
    String APP_START = "app_start";
    String SEND_QUICK_REPLY = "send_quick_reply";
    String SEND_QUICK_LIKE = "send_quick_like";
    String CHAT_ADD_GIPHY = "chat_add_giphy";
    String CHAT_ADD_MEME = "chat_add_meme";
    String START_CHAT_ACTIVITY = "start_chat_activity";
    String START_TEAMS_AND_CHANNELS_ACTIVITY = "start_teams_and_channels_activity";
    String SHOW_TEAMS_AND_CHANNELS_LIST = "show_teams_and_channels_list";
    String START_CONTACTS_APP_END_TO_END = "start_contacts_app_end_to_end";
    String START_CHAT_APP_END_TO_END = "start_chat_app_end_to_end";
    String START_TEAMS_AND_CHANNELS_APP_END_TO_END = "start_teams_and_channels_app_end_to_end";
    String START_FAVORITE_APP_END_TO_END = "start_favorite_app_end_to_end";
    String START_HISTORY_APP_END_TO_END = "start_history_app_end_to_end";
    String START_VOICEMAIL_APP_END_TO_END = "start_voicemail_app_end_to_end";
    String START_ORG_APP_END_TO_END = "start_org_app_end_to_end";
    String START_FILES_APP_END_TO_END = "start_files_app_end_to_end";
    String SYNC_CONVERSATION_THREAD = "sync_conversation_thread";
    String VOICEMAIL_PERIODIC_SYNC = "voicemail_periodic_sync";
    String GET_THREAD_PROPERTIES_SINGLE = "get_thread_properties_single";

    //Intune scenarios
    String ENROLL_WITH_INTUNE = "enroll_with_intune";
    String ENROLL_MAM = "enroll_mam";
    String UNENROLL_MAM = "unenroll_mam";
    String INTUNE_REMEDIATE_COMPLIANCE_POLICY = "intune_remediate_compliance_policy";
    String INTUNE_REMEDIATE_COMPLIANCE = "intune_remediate_compliance";

    //Manage delegates
    String REFRESH_DELEGATES_DATA = "refresh_delegates_data";
    String CREATE_NEW_DELEGATE = "create_new_delegates";
    String REMOVE_DELEGATE = "remove_delegate";
    String DELEGATE_NOTIFICATION = "delegate_added_notification";
    String EDIT_DELEGATE = "edit_delegate";
    String SLA_PUSH_MESSAGE = "sla_push_message";
    String SLA_ACTIVE_CALLS_VIEWED = "sla_active_calls_viewed";
    String SLA_RESUME_PARKED_CALL_VIEWED = "sla_parked_calls_viewed";
    String SLA_PARKED_CALL_GROUP_EXPANDED = "sla_parked_calls_group_expanded";
    String SLA_ACTIVE_CALLS_CLICKED = "sla_active_calls_clicked";
    String SLA_RESUME_PARKED_CALL_CLICKED = "sla_resume_parked_call_clicked";

    //Meeting join by code
    String MEETING_JOIN_BY_CODE_JOINING = "meeting_join_by_code_joining";

    String USER_VOICE_SETTINGS = "user_voice_settings";

    //people contacts V2
    String PEOPLE_V2_FETCH_CONTACTS_AND_GROUPS = "fetch_contact_and_groups_v2";
    String PEOPLE_V2_FETCH_GROUPS = "fetch_groups_v2";
    String PEOPLE_V2_CREATE_CONTACT = "create_contact_v2";
    String PEOPLE_V2_CREATE_CONTACT_GROUP = "create_contact_group_v2";
    String PEOPLE_V2_EDIT_CONTACT = "edit_contact_v2";
    String PEOPLE_V2_RENAME_CONTACT_GROUP = "rename_contact_group_v2";
    String PEOPLE_V2_DELETE_CONTACT_GROUP = "delete_contact_group_v2";
    String PEOPLE_V2_ADD_CONTACT_GROUP = "add_contact_group";
    String PEOPLE_V2_REMOVE_CONTACT_GROUP = "remove_contact_group";

    //Group calls
    String GROUP_CALLS_EXPAND_CLICKED = "group_calls_banner_expand_clicked";

    //Webinar scenarios
    String JOIN_WEBINAR = "join_webinar";

    //Image scenarios
    String SHARE_IMAGE = "share_image";
    String SAVE_IMAGE = "save_image";
    String OPEN_IMAGE = "open_image";
    String EDIT_IMAGE = "edit_image";
    String FORWARD_IMAGE = "forward_image";

    // calling scenarios
    String CALLING_SERVICE_INIT_APP_FOREGROUND = "calling_service_init_app_foreground";
    String GET_CALL_LOGS = "get_call_logs";
    String CREATE_ONE_TO_ONE_CALL = "create_one_to_one_call";
    String CREATE_ONE_TO_ONE_INTEROP_SFB_CALL = "cm_create_one_to_one_interop_call"; // SFB only.
    String CREATE_ONE_TO_ONE_INTEROP_SFC_CALL = "cm_create_one_to_one_interop_sfc_call";
    String CALL_ROUTING_UPDATE_CLIENT_PREFERENCE = "cm_call_routing_update_client_preference";
    String CREATE_VOICEMAIL_CALL = "create_voicemail_call";
    String PSTN_CALL = "pstn_call";
    String EMERGENCY_CALL = "emergency_call";
    String EMERGENCY_CALL_DIRECT_ROUTING = "emergency_call_direct_routing";
    String SHARE_LLDP_INFO = "share_lldp_info";
    String VOIP_CALL = "voip_call";
    String JOIN_SCHEDULED_MEETUP = "join_scheduled_meetup";
    String JOIN_MEET_NOW_MEETING = "create_and_join_instant_meeting";
    String JOIN_MEETUP_RETRY = "join_meetup_retry";
    String JOIN_SCHEDULED_MEETUP_BROADCAST = "join_scheduled_meetup_broadcast";
    String CALL_ME_BACK_MEETING = "call_me_back_meeting";
    String JOIN_MEETUP_REPLY_CHAIN = "join_meetup_reply_chain";
    String CALL_JOIN_FROM_DEEP_LINK = "join_or_create_meetup_from_link";
    String CALL_JOIN_FROM_DEEP_LINK_BROADCAST = "join_or_create_meetup_from_link_broadcast";
    String CALL_JOIN_FROM_DEEP_LINK_BROADCAST_ANONYMOUS = "join_or_create_meetup_from_link_broadcast_anonymous";
    String CALL_JOIN_FROM_DEEP_LINK_ANONYMOUS = "join_or_create_meetup_from_link_anonymous";
    String CALL_JOIN_FROM_DEEP_LINK_ANONYMOUS_TFL = "join_or_create_meetup_from_link_anonymous_tfl";
    String CALL_SERVICE_INIT = "calling_service_init";
    String SKYLIB_SETUP = "skylib_setup";
    String CALL_SERVICE_LOGIN = "calling_service_login";
    String EARLY_CALL_RINGING = "early_call_ringing";
    String EARLY_CALL_CANCEL_SHOW_FEEDBACK = "show_call_early_cancel_feedback";
    String VERIFY_SKYLIB_STATE = "verify_skylib_state";
    String UPDATE_SKYLIB_SKYPE_TOKEN_ON_APP_TOKEN_RFRESH = "update_skylib_token_on_app_token_refresh";
    String UPDATE_SKYLIB_SKYPE_TOKEN_ON_REFRESH_MESSAGE = "update_skylib_token_on_token_refresh_message";
    String UPDATE_SKYLIB_SKYPE_TOKEN_ON_SKYLIB_EVENT = "update_skylib_token_on_skylib_token_refresh";
    String LOG_IN_ON_SKY_LIB_STATUS_CHANGE_EVENT = "log_in_on_sky_lib_status_change_event";
    String RE_INITIALIZE_ON_SKY_LIB_STATUS_CHANGE_EVENT = "re_initialize_on_sky_lib_status_change_event";
    String RE_INITIALIZE_ON_INVALID_SKY_LIB_RUNNING_STATUS = "re_initialize_on_invalid_sky_lib_running_status";
    String INITIALIZE_ON_SKYLIB_NOT_INITIALIZED = "initialize_on_skylib_not_initialized";
    String ON_CALL_PUSH_MESSAGE = "on_call_push_message";
    String ON_CALL_QUEUE_CALL_PUSH_MESSAGE = "on_call_queue_call_push_message";
    String ON_ACTIVE_CALL_PUSH_MESSAGE = "on_active_call_push_message";
    String WARM_PUSH_CALL_MESSAGE = "on_warm_push_call_message";
    String EARLY_RINGING = "early_ringing";
    String ON_CALL_LONGPOLL_MESSAGE = "on_call_longpoll_message";
    String ON_MESSAGE_RECEIVED = "on_message_received";
    String ON_READ_RECEIPTS_LONGPOLL_MESSAGE = "on_read_receipts_longpoll_message";
    String RETRY_CALL_PUSH_MESSAGE = "retry_call_push_message";
    String NETWORK_RECONNECT_BEGIN = "network_reconnect_begin";
    String NETWORK_RECONNECT_SUCCESS = "network_reconnect_success";
    String NETWORK_RECONNECT_FAIL = "network_reconnect_fail";
    String NATIVE_CONTACT_SYNC = "native_contact_sync";
    String JOIN_AD_HOC_MEETING = "join_ad_hoc_meeting";
    String ON_CALL_HANDLER_NULL = "on_call_handler_null";
    String CREATE_GROUP_CALL = "create_group_call";
    String JOIN_GROUP_CALL = "join_group_call";
    String JOIN_ACTIVE_ONE_ON_ONE_CALL = "join_one_on_one_call";
    String SET_UP_MEETING = "set_up_meeting";
    String SET_UP_JOIN_WITHOUT_MODALITY_MEETING = "set_up_join_without_modality_meeting";
    String SET_UP_JOIN_GROUP_CALL = "set_up_join_group_call";
    String SET_UP_JOIN_ACTIVE_ONE_ON_ONE_CALL = "set_up_join_one_on_on_call";
    String SET_UP_VOCIEMAIL = "set_up_vociemail";
    String RAISE_HAND = "cm_raise_hand";
    String DO_NOT_ALLOW_TO_UNMUTE = "cm_do_not_allow_to_unmute";
    String DO_NOT_ALLOW_TO_SHARE_VIDEO = "cm_do_not_allow_to_share_video";
    String HARD_MUTE_ATTENDEE = "cm_hard_mute_attendee";
    String LOWER_HAND = "cm_lower_hand";
    String LOWER_ALL = "cm_lower_all";
    String ALLOW_TO_UNMUTE = "cm_allow_to_unmute";
    String ALLOW_TO_SHARE_VIDEO = "cm_allow_to_share_video";
    String SET_UP_CALL_TRANSFER = "set_up_call_transfer";
    String INCOMING_TRANSFER_REQUEST = "incoming_transfer_request";
    String MEDIA_CONNECTED = "media_connected";
    String INCOMING_CALL_QUEUE_TRANSFER_REQUEST = "incoming_call_queue_transfer_request";
    String TRANSFER_CALL = "transfer_call";
    String CONSULT_TRANSFER_CALL = "consult_transfer_call";
    String PARK_CALL = "park_call";
    String END_CALL = "end_call";
    String PARK_CALL_FOR_HOLD = "park_call_for_hold";
    String PARK_CALL_FOR_HOLD_V2 = "park_call_for_hold_v2";
    String UNPARK_CALL = "unpark_call";
    String UNPARK_SLA_CALL = "unpark_sla_call";
    String UNPARK_CALL_FOR_HOLD = "unpark_call_for_hold";
    String UNPARK_CALL_FOR_HOLD_V2 = "unpark_call_for_hold_v2";
    String UNPARK_CALL_FOR_END_CALL = "unpark_call_for_end_call";
    String CALLING_CALL_DISCONNECTED = "calling_call_disconnected";
    String CALL_ME_BACK = "call_me_back";
    String LOAD_PPT_SHARE = "load_ppt_share";
    String INIT_PPT_VIEWER = "cm_init_ppt_viewer";
    String INVISION_WHITEBOARD_SHARE = "invision_whiteboard_share";
    String ANNOTATION_SHARE = "annotation_share";
    String LIVE_EVENT_ATTENDEE_JOIN = "live_event_attendee_join";
    String PRE_CALL_TO_IN_CALL = "pre_call_to_in_call";
    String CALL_HISTORY_LOAD = "call_history_load";
    String CALL_HISTORY_UPDATED = "call_history_updated";
    String CALL_VOICEMAIL_LOAD = "call_voicemail_load";
    String BREAKOUT_ROOM_BANNER_JOIN = "breakout_room_banner_join";
    String BREAKOUT_ROOM_ACCEPT_CALL = "breakout_room_accept_call";
    String HOLD_CALL = "hold_call";
    String RESUME_CALL = "resume_call";
    String PLAY_MEETING_RECORDING = "play_meeting_recording";
    String PLAY_MEETING_RECORDING_ONEPLAYER = "play_meeting_recording_oneplayer";
    String SCREEN_SHARE = "screen_share";
    String PARTICIPANT_VBSS_LATENCY = "participant_vbss_latency";
    String PARTICIPANT_VBSS_SUMMARY = "cm_participant_vbss_summary";
    String TROUTER_REGISTRATION = "trouter_registeration";
    String OUTGOING_CALL_QUEUE = "outgoing_call_queue";
    String INCOMING_CALL_QUEUE = "incoming_call_queue";
    String CALL_BLOCK_NUMBER = "block_number";
    String CALL_BLOCK_CONTACT = "block_contact";
    String CALL_BLOCK_SFCUSER = "block_sfc_user";
    String CALL_UNBLOCK_NUMBER = "unblock_number";
    String CALL_UNBLOCK_CONTACT = "unblock_contact";
    String CALL_UNBLOCK_SFCUSER = "unblock_sfc_user";
    String CALL_ACCEPT = "call_accept";
    String ACCEPT_CALL_QUEUE_CALL = "accept_call_queue_call";
    String CALL_AUTO_ANSWER = "call_auto_answer";
    String TRANSFER_ACCEPTED_CALL_QUEUE_CALL = "transfer_accepted_call_queue_call";
    String BROADCAST_EMERGENCY_INFO = "broadcast_emergency_info";
    String CALL_JOIN_COMPLETE = "call_join_complete";
    String CALL_RENDER_VIDEO = "video_stream_rendering";
    String CALL_START_VIDEO = "start_video";
    String CALL_STOP_VIDEO = "stop_video";
    String CALL_START_VIDEO_USER_ACTION = "start_video_ui";
    String CALL_STOP_VIDEO_USER_ACTION = "stop_video_ui";
    String MERGE_CALL = "merge_call";
    String TOGGLE_BACKGROUND_BLUR = "toggle_background_blur";
    String APPLY_BG_REPLACEMENT_EFFECT = "apply_bg_replacement_effect";
    String LOAD_PERSISTED_BG_REPLACEMENT_EFFECT = "load_persisted_bg_replacement_effect";
    String BG_REPLACEMENT_DOWNLOAD_IMAGES = "bg_effect_download_images";

    // Expo
    String SCENARIO_CAST_SCREEN_TO_DEVICE = "cast_screen_to_device";
    String SCENARIO_CAST_FILE_TO_DEVICE = "cast_file_to_device";
    String SCENARIO_SELECT_CAST_DEVICE = "select_cast_to_device";
    String SCENARIO_RETRYING_FROM_DEVICE = "retrying_from_device_options";

    //live event scenarios
    String LIVE_EVENT_HEALTH_REPORT = "live_event_health_report";
    String LIVE_EVENT_PLAYBACK_SESSION = "live_event_playback_session";
    String LIVE_EVENT_AUDIO_DOWNLOAD_FAILURE = "live_event_audio_download_failure";
    String LIVE_EVENT_BITRATE_CHANGED = "live_event_bitrate_changed";
    String LIVE_EVENT_BUFFERING = "live_event_buffering";
    String LIVE_EVENT_ERROR = "live_event_error";
    String LIVE_EVENT_LOADED_METADATA = "live_event_loaded_metadata";
    String LIVE_EVENT_PAUSE = "live_event_pause";
    String LIVE_EVENT_PLAY_COMPLETE = "live_event_play_complete";
    String LIVE_EVENT_PLAY_STARTED = "live_event_play_started";
    String LIVE_EVENT_POTENTIAL_MEDIA_FREEZE = "live_event_potential_media_freeze";
    String LIVE_EVENT_RAPID_SEEKING = "live_event_rapid_seeking";
    String LIVE_EVENT_READY = "live_event_ready";
    String LIVE_EVENT_RETRY = "live_event_retry";
    String LIVE_EVENT_TICK = "live_event_tick";
    String LIVE_EVENT_VIDEO_DOWNLOAD_FAILURE = "live_event_video_download_failure";
    String BROADCAST_RESOLVE_ROLE = "broadcast_resolve_role";
    String BROADCAST_ATTENDEE_PERFORMANCE = "broadcast_attendee_performance";

    //Stream player
    String STREAM_PLAYER_LIVE_EVENT = "stream_player_live_event";
    String STREAM_PLAYER_MEETING_RECORDING = "stream_player_meeting_recording";

    // Prejoin scenarios
    String PREJOIN_FETCH_MEETING_METADATA = "prejoin_fetch_meeting_metadata";

    // calendar scenarios
    String DELETE_MEETING = "admin_delete_schedulingevent";
    String SHOW_MEETINGS_TAB = "show_meetings_tab";
    String SHOW_MEETING_DETAILS = "show_meeting_details";
    String SHOW_MEETING_PARTICIPANTS = "show_meeting_participants";
    String CHAT_MEETING_PARTICIPANTS = "chat_with_meeting_participants";
    String MEETING_OPTIONS = "meeting_options";
    String MEETING_DETAILS = "meeting_details";
    String MEETING_EXCHANGE_FILE_DOWNLOAD = "meeting_exchange_file_download";
    String MEETING_INSIGHT_FETCH = "meeting_insight_fetch";
    String MEETING_INSIGHT_RENDER = "meeting_insight_render";
    String CREATE_MEETING = "create_meeting";
    String CREATE_DUMMY_MEETING = "create_dummy_meeting";
    String DELETE_DUMMY_MEETING = "delete_dummy_meeting";
    String UPDATE_DUMMY_MEETING = "update_dummy_meeting";
    String EDIT_MEETING = "edit_meeting";
    String CREATE_CHANNEL_MEETING = "create_channel_meeting";
    String EDIT_CHANNEL_MEETING = "edit_channel_meeting";
    String CREATE_ONE_ON_ONE_CONSUMER_GROUP = "create_one_on_one_consumer_group";
    String CREATE_CONSUMER_GROUP_EVENT = "create_consumer_group_event";
    String ADD_TO_CALENDAR = "add_to_calendar";
    String ADD_TO_MEETING = "add_to_meeting";
    String ADD_TO_CHAT = "add_to_chat";
    String CANCEL_MEETING = "cancel_meeting";
    String CANCEL_PRIVATE_MEETING = "cancel_private_meeting";
    String CANCEL_CHANNEL_MEETING = "cancel_channel_meeting";
    String CANCEL_APPOINTMENT = "cancel_appointment";
    String EVENT_RSVP = "event_rsvp";
    String FORWARD_MEETING = "forward_meeting";
    String GET_TIMEZONE = "get_timezone";
    String GET_AVAILABILITY = "get_availability";
    String GET_FREEMIUM_AD_HOC_MEETING_LIST = "get_freemium_ad_hoc_meeting_list";
    String MEETING_REMINDER_WORKER = "meeting_reminder_worker";
    String CALENDAR_SYNC_LOCAL_BY_DATE_RANGE = "calendar_sync_local_by_date_range";
    String CALENDAR_SYNC_LOCAL_BY_MEETING_IDS = "calendar_sync_local_by_meeting_ids";

    //Devices authentication
    String DEVICES_ADMIN_AGENT_PROVISION_OTP = "devices_admin_agent_provision_otp";
    String DEVICES_ADMIN_AGENT_CHECK_PROVISION_STATUS = "devices_admin_agent_check_provision_status";
    String DEVICES_ADMIN_AGENT_BROADCAST_EVENT = "devices_admin_agent_broadcast_event";
    String DEVICES_ADMIN_AGENT_REMOTE_LOGIN_REQUEST = "devices_admin_agent_remote_login_request";
    String DEVICES_ADMIN_AGENT_REMOTE_LOGOUT_REQUEST = "devices_admin_agent_remote_logout_request";
    String CP_DCF_LOGIN_REQUEST = "cp_dcf_login_request";

    // Dock
    String DOCK_MESSAGE_ACK = "dock_message_ack";
    String DOCK_CONNECTED = "dock_connected";
    String DOCK_DISCONNECTED = "dock_disconnected";

    // Show Alerts List
    String LOAD_ACTIVITY_LIST = "sync_activity_list";
    String LOAD_USER_ACTIVITY = "load_user_activity";
    String PULL_REFRESH_ALERTS = "alerts_pull_refresh";

    // Sync Team list
    String SYNC_TEAM_LIST = "sync_team_list";
    String SHOW_TEAM_TABS_TAB = "show_team_tabs_tab";

    String OPEN_EXISTING_CHAT = "open_existing_chat";

    // notification scenarios
    String NOTIFICATION_RECEIVED = "notification_received";
    String SHOULD_FILTER_NOTIFICATION_MESSAGE = "should_filter_notification_message";
    String NOTIFICATION_NAV_CHAT = "notification_nav_chat";
    String NOTIFICATION_NAV_CHAT_FILE_UPLOAD = "notification_nav_chat_file_upload";
    String NOTIFICATION_NAV_CHANNEL_CONVERSATION = "notification_nav_channel_conversation";
    String NOTIFICATION_NAV_CHANNEL_CONVERSATION_FILE_UPLOAD = "notification_nav_channel_conversation_file_upload";
    String BACKGROUND_NOTIFICATION_SYNC_TASK = "background_notification_sync_task";
    String MISSING_NOTIFICATION_START_PROCESS_MESSAGE = "missing_notification_start_process_message";

    String APP_LAUNCH_SKYLIB_INIT = "app_launch_skylib_init";

    // auto prune scenarios
    String AUTO_PRUNE = "auto_prune";
    String AUTO_PRUNE_FOR_OPTIMIZING_DB = "auto_prune_for_optimizing_database";
    String AUTO_PRUNE_HARD_DELETE_RC = "auto_prune_hard_delete_reply_chain";
    String AUTO_PRUNE_SOFT_DELETE_RC = "auto_prune_soft_delete_reply_chain";
    String AUTO_PRUNE_DELETE_OTHER_MESSAGES = "auto_prune_delete_other_messages";
    String AUTO_PRUNE_DELETE_ACTIVITIES = "auto_prune_delete_activities";
    // Time based retention policy related
    String TIME_BASED_RETENTION = "time_based_retention";
    String TIME_BASED_RETENTION_SHARED_CHANNELS = "time_based_retention_shared_channels";
    // resolve federated user by email
    String RESOLVE_FEDERATED_USER_BY_EMAIL = "resolve_federated_user_by_email";
    // resolve federated user by mri
    String RESOLVE_FEDERATED_USER_BY_MRI = "resolve_federated_user_by_mri";
    // tenant switch scenarios
    String LOAD_TENANT_LIST = "load_tenant_list";
    String LOAD_TENANT_LIST_CONSUMER = "load_tenant_list_consumer";
    String SWITCH_TENANT = "switch_tenant";
    String ADD_USER = "add_user";
    String GET_TENANT_LIST = "get_tenant_list";
    String REGISTER_BADGE_COUNT_SERVICE = "register_badge_count_service";
    String GET_TENANT_LIST_WITH_NOTIFICATIONS = "get_tenant_list_with_notifications";
    String GET_BADGE_COUNT = "get_badge_count";
    String STOP_SERVICES = "stop_services";
    String SWITCH_USER = "switch_user";
    String TENTATIVE_SIGNIN = "tentative_signin";
    String REDEEM_CONSUMER_TENANT = "redeem_consumer_tenant";
    String CLEAR_CACHES = "clear_caches";
    String REDIRECT_TO_FRE = "redirect_to_fre";
    String DELETE_EDF_REGISTRATION = "delete_edf_registration";
    String DELETE_BADGE_COUNT_SERVICE_REGISTRATION = "delete_bcs_registration";
    String DELETE_END_POINT = "delete_end_point";
    String DELETE_UPS_ENDPOINT = "delete_ups_end_point";
    String SIGNOUT_USER_FROM_OTHER_SERVICES = "signout_user_from_other_services";
    String CLEAR_ACCOUNT_PREFS = "clear_account_prefs";
    String CLEAR_PREFS = "clear_prefs";
    String CLEAR_DB = "clear_db";
    String CLEAR_NOTIFICATION_CHANNELS = "clear_notification_channels";
    String SET_CURRENT_TENANT_ID = "set_current_tenant_id";
    String SWITCH_TENANT_DB = "switch_tenant_db";
    String PROCESS_DEEPLINKS = "process_deeplinks";
    String SCENARIO_SIGN_OUT = "sign_out";
    String DELETE_SINGLE_TENANT_DB = "delete_single_tenant_db";
    String DELETE_MULTI_TENANT_DB = "delete_multi_tenant_db";
    String CLEAR_SINGLE_TENANT_DB = "clear_single_tenant_db";
    String CLEAR_MULTI_TENANT_DB = "clear_multi_tenant_db";
    String SCENARIO_SET_CURRENT_USER = "set_current_user";
    String PROCESS_DEEPLINKS_FOR_VALID_USER = "process_deeplinks_for_validuser";
    String OPEN_URI = "open_uri";
    String CLEAR_AUTH_PROVIDER_CACHE = "clear_auth_provider_cache";

    // like scenarios
    String SCENARIO_CHAT_LIKE_MESSAGE = "chat_messaging_like_message"; // Click on likes in chat
    String SCENARIO_CHAT_UNLIKE_MESSAGE = "chat_messaging_unlike_message"; // Click on unlikes in chat
    String SCENARIO_CHANNEL_LIKE_MESSAGE = "channel_messaging_like_message"; // Click on likes in channel
    String SCENARIO_CHANNEL_UNLIKE_MESSAGE = "channel_messaging_unlike_message"; // Click on unlikes in channel
    String SCENARIO_AT_MENTION = "at_mention";
    // Assignments scenarios
    String LOAD_ASSIGNMENTS = "load_assignments";
    String OPEN_FILE_PICKER_ASSIGNMENT = "open_file_picker_assignments";
    // Anonymous join scenarios
    String ANONYMOUS_JOIN_SETUP = "anonymousSetup";
    String ANONYMOUS_CALL_JOIN = "anonymous_call_join";
    // ReactNative Scenarios
    String SYNC_LOCAL_RN_APP = "sync_local_rn_app";
    String SYNC_CODEPUSH_RN_APP = "sync_codepush_rn_app";
    String SYNC_CODEPUSH_CHECK_UPDATE = "sync_codepush_check_update";
    String SYNC_CODEPUSH_DOWNLOAD_BUNDLE = "sync_codepush_download_bundle";
    String SDK_LOAD_APP_CONTAINER = "sdk_load_app_container";
    String SDK_APP_LOADED_FULLY = "sdk_app_loaded_fully";
    String SDK_APP_LOADER_APPEAR = "sdk_app_loader_appear";
    String SDK_FIRST_VIEW_VISIBLE = "sdk_first_view_visible";
    String SDK_LOAD_ACTIVE_COMPONENT = "sdk_load_active_component";
    String PRE_INIT_RN_MODULE = "pre_init_rn_app";
    String SYNC_MOBILE_MODULES = "sync_mobile_modules";
    String RN_APP_RENDER = "rn_app_render";
    // React Native Internal Markers
    String BRIDGE_THREAD_ACTIVATION = "bridge_thread_activation";
    String RN_SO_FILE_LOAD = "rn_so_file_load";
    String RN_PACKAGES_INIT = "rn_packages_init";
    String UI_MANAGER_LOAD = "ui_manager_load";
    String CONTENT_APPEAR = "content_appear";
    String JS_BUNDLE_LOAD = "js_bundle_load";
    // React Native APIs
    String RN_RESOURCE_TOKEN_FETCH = "rn_resource_token_fetch";
    String RN_RESOURCE_TOKEN_EAGER_FETCH = "rn_resource_token_eager_fetch";
    String RN_SKYPE_TOKEN_FETCH = "rn_skype_token_fetch";
    String RN_ALL_TEAMS_FETCH = "rn_all_teams_fetch";
    String RN_TEAMS_WITH_IDS_FETCH = "rn_teams_with_ids_fetch";
    String RN_TABS_FOR_CHANNEL_WITH_IDS_FETCH = "rn_tabs_for_channel_with_ids_fetch";
    String RN_HTTP_CALL = "rn_http_call";

    //Fre scenarios
    String APP_FRE_SYNC = "app_fre_sync";
    String APP_FRE_SYNC_RETRY = "app_fre_sync_retry";
    String APP_FRE_SYNC_ON_ACTION = "app_fre_sync_on_action";
    String APP_FRE_SYNC_ON_FREEMIUM_INVITATION = "app_fre_sync_on_freemium_invite";
    String APP_FRE_SYNC_ON_FREEMIUM_ACTION = "app_fre_sync_freemium";
    String SCENARIO_GET_PERSONAL_STREAM = "get_personal_stream";
    String SCENARIO_GET_CONVERSATIONS = "get_conversations";
    String SCENARIO_SYNC_BLOCK_LIST = "sync_block_list";
    String SCENARIO_SYNC_FRE_DATA = "sync_fre_data";
    String SCENARIO_SYNC_VOICEMAIL = "sync_voicemail";
    String SCENARIO_SYNC_MEETING_DETAILS = "sync_meeting_details";
    String SCENARIO_SYNC_USERS = "sync_users";
    String SCENARIO_SYNC_USERS_FEATURE_SETTINGS = "sync_users_feature_settings";
    String SCENARIO_SYNC_MOBILE_MODULES = "sync_mobile_modules";
    String SCENARIO_SYNC_BOOTSTRAP_FREMIUM = "sync_bootstrap_fremium";
    String SCENARIO_FRE_TASK = "fre_task";
    String SCENARIO_CONVERSATION_SYNC = "conversation_sync";
    String SCENARIO_UPDATE_THREAD_PROPS = "update_thread_props";
    String SCENARIO_INITIALIZE_REACT_NATIVE_MANAGER = "initialize_react_native_manager";
    String SCENARIO_SYNC_APPDEF_AND_CHATMSG_SYNC = "syncAppDefinitionsAndChatMessagesInitialSync";
    String SCENARIO_SYNC_DATA_FOR_CALLS_TABS = "sync_data_for_calls_tabs";
    String SCENARIO_UPDATE_THREAD = "update_thread";
    String SCENARIO_GET_BATCH_THREAD_PROPERTIES = "get_batch_thread_properties";
    String SCENARIO_SYNC_APP_DEFINITIONS = "sync_app_definitions";
    String SCENARIO_GET_THREAD_USERS = "get_thread_users";
    String SCENARIO_GET_TENANT_THREAD_USERS = "get_tenant_thread_users";
    String SYNC_MESSAGES_FOR_CHAT = "sync_messages_for_chat";
    String CONVERT_CHAT_VIEW_DATA = "convert_chat_view_data";
    String SYNC_MESSAGES_FOR_CONVERSATION = "sync_messages_for_conversations";
    String SYNC_MESSAGES_FOR_FAV_CONVERSATIONS = "sync_messages_for_fav_conversations";
    String SCENARIO_SYNC_USER_ENTITLEMENTS_AND_APP_DEFINITIONS = "sync_user_entitlements_and_app_definitions";
    String SCENARIO_SYNC_CHAT_ENTITLEMENTS_AND_APP_DEFINITIONS = "sync_chat_entitlements_and_app_definitions";
    String SCENARIO_SYNC_TEAM_ENTITLEMENTS_AND_APP_DEFINITIONS = "sync_team_entitlements_and_app_definitions";
    String SCENARIO_CONVERSATION_DATA_TRANSFORM = "conversation_data_transform";
    String SCENARIO_APP_DEFINITION_DATA_TRANSFORM = "app_definition_data_transform";
    String SCENARIO_INSTALL_APP_IN_PERSONAL_SCOPE = "install_app_in_personal_scope";
    String SCENARIO_INSTALL_APP_IN_CHAT_OR_TEAM = "install_app_in_chat_or_team";
    String SYNC_CONTACTS = "sync_contacts";
    String ENABLE_PROFILE_SEARCHABILITY = "enable_profile_searchability";
    String FETCH_EPHEMERAL_PROFILE = "fetch_ephemeral_profile";
    String FETCH_ME_PROFILE = "fetch_me_profile";
    String LOAD_PROFILE_PAGE = "load_profile_page";
    String LOAD_CHAT_AFTER_FRE = "load_chat_after_fre";
    String INSTALL_REFERRER_RESPONSE = "install_referrer_response";

    //Active Sync
    String SHOW_CHANNEL = "messaging_switch_channel";
    String SHOW_CHAT = "chat_switch";
    String SHOW_CHAT_LIST = "sync_chat_list"; // Show Chat List
    String SHOW_REPLYCHAIN = "messaging_switch_replychain";
    String FETCH_CHANNELS = "fetch_channels";
    String START_CHAT_CONTAINER_FRAGMENT = "start_chat_container_fragment";
    String START_CHAT_FRAGMENT = "start_chat_fragment";
    String GET_SENDER_SUB_SCENARIO = "get_sender_sub_scenario";
    String BELL_COUNT = "bell_count_bcs_push_notification";
    String ACTIVITY_PILL_COUNT = "activity_pill_count";
    String CHATS_PILL_COUNT = "chats_pill_count";
    String CALLS_PILL_COUNT = "calls_pill_count";
    String VOICEMAIL_PILL_COUNT = "voicemail_pill_count";
    String NOTES_PILL_COUNT = "notes_pill_count";
    String APP_INCREMENTAL_SYNC_LAUNCH = "app_incremental_sync_launch";
    String APP_INCREMENTAL_SYNC_RESUME = "app_incremental_sync_resume";
    String APP_INCR_SYNC_LAUNCH_AUTH_USER = "app_incr_sync_launch_auth_user";
    String APP_INCR_SYNC_RESUME_AUTH_USER = "app_incr_sync_resume_auth_user";

    // Bot scenarios
    String TOGETHER_MODE = "cm_together_mode";
    String ADD_TOGETHER_MODE_BOT = "cm_add_together_mode_bot";
    String LARGE_GALLERY_MODE = "cm_large_gallery_mode";
    String ADD_LARGE_GALLERY_MODE_BOT = "cm_add_large_gallery_mode_bot";
    String MEETING_RECORDING = "cm_meeting_recording";
    String ADD_RECORDING_BOT = "cm_add_recording_bot";
    String MEETING_LIVE_CAPTION = "cm_meeting_live_caption";
    String ADD_LIVE_CAPTION_BOT = "cm_add_live_caption_bot";

    // Static tab scenarios
    String LOAD_STATIC_TAB = "load_static_tab";
    // bookmark or save message
    String STAR_MESSAGE = "message_star";
    String LOAD_BOOKMARKS = "load_bookmarks"; // Load bookmarks
    String LOAD_BOOKMARKS_CONVERSATION = "load_bookmarks_conversation"; // Load bookmarks conversation for stream
    String LOAD_BOOKMARKS_FEED_ID = "load_bookmarks_feed_id";

    String DB_MIGRATION = "db_migration";
    String DB_MIGRATION_ON_ERROR = "db_migration_on_error";
    String DB_CHECK_AND_UPDATE = "db_check_and_update";
    String DB_UPGRADE = "db_upgrade";
    String DB_UPGRADE_FROM_SPLASH_ACTIVITY = "db_upgrade_from_splash_activity";
    String DB_DESTRUCTIVE_DESTROY = "db_destructive_destroy";
    String DB_INITIALIZE = "db_initialize";
    String DB_HANDLE_SQL_EXCEPTION = "db_handle_sql_exception";
    String DB_CLEAR = "db_clear";

    //Office Lens scenarios
    String LENS_LAUNCH = "office_lens_enabled";

    // Service API sync scenarios
    String GET_VOICE_MAIL_FOLDER = "get_voice_mail_folder";
    String GET_VOICE_MAIL = "get_voice_mail";
    String GET_ACTIVE_CALL_LIST = "get_active_call_list";
    String GET_SUGGESTED_ROOMS = "get_suggested_rooms";
    String SEARCH_ROOMS = "search_rooms";

    // Extensibility scenarios
    String TASK_MODULE_COMPLETE = "task_module_complete";
    String LOAD_MESSAGING_EXTENSTION_RESULTS = "load_messaging_extension_results";
    String LOAD_CHANNEL_TAB = "load_channel_tab";
    String EXTENSIBILITY_APP_AUTHENTICATION = "extensibility_app_authentication";
    String EXTENSIBILITY_BOT_TO_USER_FILE_ATTACHMENT = "extensibility_bot_to_user_file_attachment";
    String BOT_INVOKE = "bot_invoke";
    String SYNC_PERSONAL_APPS_ON_ENTITLEMENTS_CHANGE = "sync_personal_apps_on_entitlements_change";

    //JS SDK APIs
    String FETCH_TEAMS_INFO_API = "fetch_teams_info";

    //Cortana
    String CORTANA_BACKGROUND_TOKEN_REFRESH = "cortana_background_token_refresh";
    String CORTANA_ADMIN_POLICY_REFRESH = "cortana_admin_policy_refresh";
    String CORTANA_WAKE_WORD_ACTIVE = "cortana_wake_word_active";
    String CORTANA_SKILL_ACTION_EXECUTION = "cortana_skill_action_execution";
    String CORTANA_KWS = "cortana_kws";
    String CORTANA_AUTO_CLOSE = "cortana_auto_close";
    String CORTANA_SKILL_ACTION_DELAY = "cortana_skill_action_delay";
    String CORTANA_SDK_EVENTS = "cortana_sdk_events";
    String CORTANA_MORE_MENU = "cortana_more_menu";
    String CORTANA_INITIALIZATION = "cortana_initialization";
    String CORTANA_WATCHDOG = "cortana_watchdog";
    String CORTANA_RESPONSE_ERROR = "cortana_response_error";

    //Status Note
    String STATUS_NOTE_SET_STATUS_ACTIVITY = "status_note_set_status_activity";
    String STATUS_NOTE_START_SET_STATUS_ACTIVITY = "status_note_start_set_status_activity";
    String STATUS_NOTE_SET_STATUS_REQUEST = "status_note_set_status_request";

    //Targeting Service Requests
    String TARGETING_GET_TEAM_TAG_CARDS_REQUEST = "targeting_service_get_tag_cards";
    String TARGETING_GET_TAG_MEMBERS_REQUEST = "targeting_service_get_tag_members";
    String TARGETING_REMOVE_TAG_MEMBERS_REQUEST = "targeting_service_remove_tag_members";
    String TARGETING_ADD_TAG_MEMBERS_REQUEST = "targeting_service_add_tag_members";
    String TARGETING_CREATE_TAG_REQUEST = "targeting_service_create_tag";
    String TARGETING_GET_TENANT_SETTINGS_REQUEST = "targeting_get_tenant_settings";
    String TARGETING_GET_TAG_CARDS_AND_TENANT_SETTINGS_REQUEST = "targeting_get_tag_cards_and_tenant_settings";

    //Targeting Interactions
    String TARGETING_VIEW_MANAGE_TAGS = "targeting_view_manage_tags";
    String TARGETING_VIEW_TAG_MEMBERS = "targeting_view_tag_members";
    String TARGETING_CREATE_TAG = "targeting_create_tag";
    String TARGETING_ADD_TAG_MEMBER = "targeting_add_tag_member";
    String TARGETING_REMOVE_TAG_MEMBER = "targeting_remove_tag_member";

    String GRANT_URI_READ_PERMISSION = "grant_uri_read_permission";

    // Pending Members
    String GET_PENDING_MEMBERS_REQUEST = "get_pending_members_request";
    String GET_COUNT_OF_PENDING_MEMBERS_REQUEST = "get_count_of_pending_members_request";
    String UNLOCK_TEAM_REQUEST = "unlock_team_request";

    //Create indices
    String MIGRATE_INDEX_SCENARIO = "migrate_index_scenario";

    String SYNC_APP_POLICIES = "sync_app_policies";

    //Smart reply
    String SMART_REPLY_ENABLED = "smart_reply_enabled";
    String SMART_REPLY_RECEIVED = "smart_reply_received";
    String SMART_REPLY_BANNED = "smart_reply_banned";

    // Smart compose
    String SMART_COMPOSE_ENABLED = "smartComposeEnabled";
    String SMART_COMPOSE_INITIALIZATION = "smartComposeInitialization";
    String SMART_COMPOSE_RECEIVED = "smartComposeReceived";
    String SMART_COMPOSE_TYPE_THROUGH = "smartComposeTypeThrough";
    String SMART_COMPOSE_NOT_MATCHED = "smartComposeNotMatched";
    String SMART_COMPOSE_NOT_ACCEPTED = "smartComposeNotAccepted";
    String SMART_COMPOSE_NOT_RENDERED = "smartComposeNotRendered";
    String SMART_COMPOSE_REQUEST_SENT = "smartComposeRequestSent";
    String SMART_COMPOSE_SESSION_CREATED = "smartComposeSessionCreated";
    String AUGLOOP_TOKEN_FETCHED = "augloopTokenFetched";

    // Translation
    String TRANSLATE_CHAT_MESSAGE = "translate_chat_message";
    String TRANSLATION_SUPPORTED_LANGUAGES = "translation_supported_languages";
    String TRANSLATE_CHANNEL_MESSAGE = "translate_channel_message";

    // Proximity Sensor Service Events [Currently used by Kingston]
    String PROXIMITY_SENSOR_SERVICE_EVENTS = "proximity_sensor_service_events";

    // Invite off-network contacts.
    String INVITE_OFF_NETWORK_CONTACTS = "invite_off_network_contacts";

    // New group chat avatar.
    String UPDATE_GROUP_AVATAR = "update_group_avatar";

    // Norden Auto cast
    String BYOM_AUTO_ANSWER = "norde_auto_cast";

    // Reactions
    String REACTIONS_FETCH_USERS = "reactions_fetch_users";

    // One time searchability stamping check
    String STAMPING_OUTSIDE_FRE_TRIGGERED = "stampingOutsideFreTriggered";

    // Searchability toggle
    String TOGGLE_SEARCHABILITY = "toggleSearchability";

    String TAB_CONTEXT_APP_SESSION = "tab_context_app_session";

    String SHARED_DEVICE_ACTIVITY_SCREEN_TIME = "SharedDeviceActivityTime";

    // Platform related scenarios
    String PLATFORM_OPEN_CONVERSATION_SEND_MESSAGE = "platform_open_conversation_send_message";
    String PLATFORM_OPEN_CONVERSATION_API = "platform_open_conversation_api";

    // OCPS
    String MAKE_OCPS_CALL = "make_ocps_call";

    // Emoji
    String DOWNLOAD_EXTENDED_EMOJI = "download_emoji";

    //Background Sync Scenarios
    @interface BackgroundSync {
        String BACKGROUND_SYNC_SERVICE = "background_sync_service";
        String BACKGROUND_SYNC_SERVICE_WORKER = "background_sync_service_worker";
        String BACKGROUND_SYNC_CONVERSATIONS_SYNC = "bg_sync_conversations_sync";
        String BACKGROUND_SYNC_THREAD_PROPERTIES_AND_USERS = "bg_sync_thread_props_and_users";
        String BACKGROUND_SYNC_GET_MESSAGES = "bg_sync_get_messages";
        String BACKGROUND_SYNC_PROCESS_NOTIFICATIONS = "bg_sync_process_notifications";
    }

    //Teams SDK
    String SDK_CALL_ANONYMOUS_SETUP = "sdk_call_anonymous_setup";

    // Survivability service
    String SURVIVABILITY_CHECK_APPLIANCE_LIVENESS = "survivability_check_appliance_liveness";
    String SURVIVABILITY_SWITCH_APPLIANCE_MODE = "survivability_switch_appliance_mode";

    //Now Scenarios
    @interface Now {
        String DELETE_ITEM = "delete_item";
        String CLEAR_ALL = "clear_all";
        String DATA_PUSH = "now_data_push";
        String REFRESH_ITEMS = "refresh_items";
        String EXTENSION_SYNC = "extensions_sync";
        String TOTAL_SYNC = "total_sync";
        String INIT_POLLING_MAP = "init_polling_map";
    }

    // Team Scenarios - Create, Validate
    @interface Team {
        String CREATE = "create_team";
        String EDIT = "edit_team";
        String DELETE = "delete_team";
        String VALIDATE_NAME = "validate_team_name";
        String VALIDATE_NAME_SERVER = "validate_team_name_server";
        String UPDATE_PROFILE_PICTURE_UPDATE = "update_team_profile_picture";
        String GET_DEFAULT_TEAM_INITIALS = "get_default_team_initials";
    }

    @interface Search {
        String SEARCH = "search";
        String TOP_N_CACHE_SYNC = "top_n_cache_sync";
        String CHAT_CONTEXTUAL_SEARCH = "chat_contextual_search";
        String CHANNEL_CONTEXTUAL_SEARCH = "channel_contextual_search";
        String SPELLER_WILL_SHOW = "speller_will_show";
        String SPELLER_SHOWN = "speller_shown";
        String RECOURSE_LINK_SHOWN = "recourse_link_shown";
        String SEARCH_FILE_LOCAL = "search_file_local";
        String SEARCH_FILE_SERVER = "search_file_server";
        String SEARCH_CHAT_LOCAL = "search_chat_local";
        String SEARCH_CHAT_SERVER = "search_chat_server";
        String SEARCH_CHANNEL_LOCAL = "search_channel_local";
        String SEARCH_CHANNEL_SERVER = "search_channel_server";
        String SEARCH_MESSAGE_LOCAL = "search_message_local";
        String SEARCH_MESSAGE = "search_message";
        String SEARCH_DEVICE_CONTACTS = "search_device_contacts";
        String SEARCH_PEOPLE_LOCAL = "search_people_local";
        String SEARCH_PEOPLE_LOCAL_TOP_N_CACHE = "search_people_local_top_n_cache";
        String SEARCH_SDK_APP_CONTACTS = "search_sdk_app_contacts";
        String SEARCH_PEOPLE = "search_people";
        String SEARCH_TEAM_MEMBERS = "search_team_members";
        String SEARCH_FILE_MSAI = "search_file_msai";
        String SEARCH_FILES_SEARCH_SDK = "search_files_searchSDK";
        String SEARCH_FILES_LOCAL_SEARCH_SDK = "search_files_local_searchSDK";
        String SEARCH_WARM_UP = "search_warm_up";
        String MSAI_SDK_INIT = "msai_sdk_init";
        String MSAI_FETCH_TOKEN = "msai_fetch_token";
        String MSAI_ERROR = "msai_error";
        String MSAI_ANSWER_SEARCH = "msai_answer_search";
        String MSAI_UNIVERSAL_SEARCH = "msai_universal_search";
        String MSAI_UNIVERSAL_SEARCH_FALLBACK_MESSAGE = "msai_universal_search_fallback_message";
        String MSAI_UNIVERSAL_SEARCH_FALLBACK_FILE = "msai_universal_search_fallback_file";
        String MSAI_SUGGESTION_SEARCH = "msai_suggestion_search";
        String SEARCH_INSTANT_SCD = "search_instant_scd";
        String SEARCH_ANSWER_MSAI = "search_answer_msai";
    }

    @interface People {
        String PEOPLE_APP_SYNC = "people_app_sync";
        String PEOPLE_CONTACT_SYNC = "people_contacts_sync";
        String PEOPLE_CONTACT_LISTS_SYNC = "people_contacts_lists_sync";
        String DELETE_CONTACT = "delete_contact";
        String ADD_TO_CONTACT = "add_to_contact";
    }

    @interface PeoplePicker {
        String PEOPLE_PICKER_SERVER_RESULT_SOURCE = "people_picker_server_result_source";
    }

    @interface Files {
        // File listing
        String FILES_RECENT = "documents_view_filelist_recent";
        String FILES_RECENT_LOCAL = "documents_view_filelist_recent_local";
        String FILES_RECENT_PAGINATION = "documents_view_filelist_recent_pagination";
        String FILES_PERSONAL = "documents_view_filelist_personal";
        String FILES_PERSONAL_LOCAL = "documents_view_filelist_personal_local";
        String FILES_PERSONAL_PAGINATION = "documents_view_filelist_personal_pagination";
        String FILES_TEAMFILES = "documents_view_filelist_teams";
        String FILES_TEAMFILES_LOCAL = "documents_view_filelist_teams_local";
        String FILES_TEAMFILES_SPECIALDOCUMENTLIBRARIES = "special_document_libraries_listing";
        String FILES_TEAMFILES_SPECIALDOCUMENTLIBRARIESFILELIST = "file_listing_in_special_document_libraries";
        String FILES_TEAMFILES_PAGINATION = "documents_view_filelist_teams_pagination";
        String CHAT_FILES_TEAMFILES = "documents_view_chat_filelist_teams";
        String CHAT_FILES_TEAMFILES_LOCAL = "documents_view_chat_filelist_teams_local";
        String SHOW_FILES_IN_DIRECTORY = "files_in_directory_load";
        String CREATE_FOLDER_ONEDRIVE = "create_folder_onedrive";
        String CREATE_FOLDER_CHANNEL = "create_folder_channel";

        String ODSP_VIEWER_ASSETS_CACHE = "odsp_viewer_assets_cache";
        String FILES_INCALL = "documents_view_filelist_incall";
        String FILES_INCALL_PAGINATION = "documents_view_filelist_incall_pagination";
        String FILE_UPLOAD_CHANNEL = "file_upload_channel";
        String FILE_UPLOAD_CHAT = "file_upload_chat";
        String LINK_CHICLET_FORMATION_CHANNEL = "link_chiclet_formation_channel";
        String LINK_CHICLET_FORMATION_CHAT = "link_chiclet_formation_chat";
        String OPEN_LINK_SETTINGS = "open_link_settings";
        String GENERATE_SHARE_LINK = "generate_share_link";
        String ATTACH_AND_SEND_FILE = "attach_and_send_file";
        String LINK_CHICLET_IN_MESSAGE = "link_chiclet_in_message";
        String FILE_UPLOAD_RESUME_CHAT = "file_upload_resume_chat";
        String FILE_UPLOAD_RESUME_CHANNEL = "file_upload_resume_channel";
        String FILE_UPLOAD_CHANNEL_FOREGROUND_SERVICE = "file_upload_channel_foreground_service";
        String FILE_UPLOAD_CHAT_FOREGROUND_SERVICE = "file_upload_chat_foreground_service";
        String VROOM_FILE_UPLOAD_CHANNEL_FOREGROUND_SERVICE = "vroom_file_upload_channel_foreground_service";
        String VROOM_FILE_UPLOAD_CHAT_FOREGROUND_SERVICE = "vroom_file_upload_chat_foreground_service";
        String VROOM_FILE_UPLOAD_RESUME_CHANNEL = "vroom_file_upload_resume_channel";
        String VROOM_FILE_UPLOAD_RESUME_CHAT = "vroom_file_upload_resume_chat";
        String CONSUMER_VROOM_FILE_UPLOAD_RESUME_CHAT = "consumer_vroom_file_upload_resume_chat";
        String CONSUMER_VROOM_FILE_UPLOAD_CHAT_FOREGROUND_SERVICE = "consumer_vroom_file_upload_chat_foreground_service";

        // File preview
        String FILE_PREVIEW = "file_preview";
        String FILE_PREVIEW_WITHIN_TEAMS = "file_preview_within_teams";
        String FILE_PREVIEW_REQUEST = "file_preview_request";
        String FILE_PREVIEW_LOAD = "file_preview_load";
        String FILE_PREVIEW_VIEW = "file_preview_view";

        // File chiclet
        String FILE_SIZE_FETCH = "file_size_fetch";
        String FILE_LARGE_THUMBNAIL_PREVIEW = "file_large_thumbnail_preview";
        String FILE_SMALL_THUMBNAIL_PREVIEW = "file_small_thumbnail_preview";

        String FILES_DATA_PRUNE = "files_list_prune";
        String PDF_CONVERTED_CACHE_PRUNE = "pdf_converted_cache_prune";

        // File upload
        String FILE_UPLOAD_INTERIM_FAILURE = "fileUploadInterimFailure";
        String FILE_UPLOAD_RESUME_SUCCESS = "fileUploadResumeSuccess";
        String FILE_UPLOAD_FIRST_RESUME_ATTEMPT = "fileUploadFirstResumeAttempt";
        String SEND_FILE_UPLOAD_FROM_CHAT_FILES_TAB = "sendFileUploadFromChatFilesTab";

        // File sharing
        String SHARED_FILES_CLEANUP_TASK = "sharedFilesCleanUpTask";

        // File cleanup tasks
        String FILE_CACHE_CLEANUP_TASK = "fileCacheCleanupTask";
        String FILE_UPLOAD_CLEANUP_TASK = "file_upload_cleanup_task";

        // File download retated Scenarios
        String DOWNLOAD_FILE = "download_file";
        String USER_DOWNLOAD_FILE = "user_download_file";

        // Provisioning for consumer OneDrive.
        String CONSUMER_ONEDRIVE_PROVISIONING_ON_SIGN_IN = "consumer_onedrive_provisioning_on_sign_in";
        String CONSUMER_ONEDRIVE_PROVISIONING_ON_UPLOAD = "consumer_onedrive_provisioning_on_upload";
        String CONSUMER_ONEDRIVE_PROVISIONING_ON_PERSONAL_FILE_LISTING = "consumer_onedrive_provisioning_on_personal_file_listing";
    }

    @interface RNLSync {
        String OUTLOOK_CONTACT_SYNC = "outlook_contact_sync";
        String BUDDY_SYNC = "chat_retrieve_contact_groups";
        String RNL_CONTACTS_SYNC = "rnl_contacts_sync";
        String REVERSE_NUMBER_LOOKUP = "reverse_number_lookup";
    }

    @interface App {
        String APP_START_COLD = "app_start_cold";
        String APP_START_WARM = "app_start_warm";
    }

    @interface Calendar {
        //Parent
        String WHOLE_CALENDAR_LOAD = "whole_calendar_load";
        //Children
        String EMPTY_PANEL_LOAD = "empty_panel_load";
        String MINI_MONTH_LOAD = "mini_month_load";
        String AGENDA_VIEW_LOAD = "agenda_view_load";

        String DB_FETCH_AGENDA_VIEW = "db_fetch_agenda_view";
        String DB_FETCH_DATE_PICKER_VIEW = "db_fetch_date_picker_view";

        String SERVER_FETCH_AGENDA_VIEW = "server_fetch_agenda_view"; // <- load_event_list
        String SERVER_FETCH_AGENDA_VIEW_GROUP = "server_fetch_agenda_view_group"; // <- load_group_event_list
        String SERVER_FETCH_DATE_PICKER_VIEW = "server_fetch_date_picker_view"; // <- diff_api_init_call
        String SERVER_FETCH_DATE_PICKER_VIEW_INCREMENTAL = "server_fetch_date_picker_view_incremental"; //  <- diff_api_subsequent_call
        String SERVER_FETCH_DATE_PICKER_VIEW_CHAIN = "server_fetch_date_picker_view_chain"; // inclusive of all chain calls
        String SERVER_FETCH_DATE_PICKER_VIEW_INCREMENTAL_CHAIN = "server_fetch_date_picker_view_incremental_chain"; // inclusive of all chain calls

        // calendar date picker related(paged_meeting_fetch) [[
        String PAGINATED_LOCAL_SYNC_FOCUS_DAYS = "paginated_local_sync_focus_days";
        String PAGINATED_LOCAL_SYNC_NON_FOCUS_DAYS = "paginated_local_sync_non_focus_days";
        String PAGINATED_REMOTE_SYNC_FOCUS_DAYS = "paginated_remote_sync_focus_days";
        String PAGINATED_REMOTE_SYNC_NON_FOCUS_DAYS = "paginated_remote_sync_non_focus_days";
        // calendar date picker related(paged_meeting_fetch) ]]

        @interface Key {
            String EVENT_IS_CHAIN_CONTINOUS = "isChainContinous"; // is setup phase completed in one shot or not
            String EVENT_SYNC_INTERVAL = "sync_interval"; // time delay between two chain calls
            String EVENT_FETCH_COUNT = "event_fetch_count"; // total events received from server in an entire chain call
            String EVENT_REQUEST_COUNT = "request_count"; // request to server
            String EVENT_STEP_COUNT = "step_count"; // no. of chain calls involved
            String EVENT_TIME_TAKEN = "time_taken"; // total time for the entire chain calls to complete
            String EVENT_TRANSFORMATION_TIME_TAKEN = "transform_time_taken"; // total time taken for the response transformation
            String EVENT_DB_UPDATE_TIME_TAKEN = "dbUpdate_time_taken"; // total time taken for the database operation
            String EVENT_UX_UPDATE_TIME_TAKEN = "uxUpdate_time_taken"; // time taken for the item list selective update
            String EVENT_IS_UX_UPDATE_PAUSED = "uxUpdate_is_paused"; // is ux update paused or not
        }
    }

    @interface WorkManager {
        String EXECUTION = "work_manager_execution";
    }

    @interface Auth {
        String EMPTY_USER_OBJECT_ID = "empty_user_object_id";
    }

    @interface DeviceAddressBookSync {
        // Address Book Sync Scenario
        String SCENARIO_DEVICE_ADDRESS_BOOK_SYNC = "device_address_book_sync";
        String SCENARIO_DEVICE_ADDRESS_BOOK_UN_SYNC = "device_address_book_un_sync";
    }

    @interface SfcInterop {
        String SFC_BLOCK = "sfc_block";
        String SFC_UNBLOCK = "sfc_unblock";
    }

    @interface BetterTogether {
        String INITIALIZE_TRANSPORT = "bt_transport_initialize";
        String INITIALIZE_ENDPOINT = "bt_endpoint_initialize";
        String TRANSPORT_DISCOVER_ENDPOINTS = "bt_transport_discover_endpoints";
        String TRANSPORT_UPDATE_SKYPE_TOKEN = "bt_transport_auth";
        String TRANSPORT_COMMAND_RECEIVED = "bt_transport_incoming_command";
        String TRANSPORT_SEND_COMMAND = "bt_transport_outgoing_command";
        String TRANSPORT_UPDATE_SKYPE_TOKEN_CALLBACK = "bt_transport_update_skype_token";
        String TRANSPORT_RE_REGISTER = "bt_transport_re_register";
        String DISCOVER_DEVICES = "bt_discover_devices";
        String PAIR_WITH_DEVICE = "bt_pair_with_device";
        String PAIR_WITH_USER = "bt_pair_with_user";
        String UNPAIR_WITH_DEVICE = "bt_unpair_with_device";
        String HANDLE_RESPONSE_COMMAND = "bt_handle_response_command";
        String HANDLE_KEEP_ALIVE_COMMAND = "bt_handle_keep_alive_command";
        String HANDLE_LOCK_OR_UNLOCK_COMMAND = "bt_handle_lock_or_unlock_command";
        String HANDLE_CALL_START_COMMAND = "bt_handle_inc_call_start_command";
        String HANDLE_MEETING_START_COMMAND = "bt_handle_inc_meeting_start_command";
        String HANDLE_CALL_END_COMMAND = "bt_handle_inc_call_end_command";
        String HANDLE_MEETING_END_COMMAND = "bt_handle_inc_meeting_end_command";
        String OUTGOING_CALL_END_COMMAND = "bt_outgoing_call_end_command";
        String OUTGOING_CALL_START_COMMAND = "bt_outgoing_call_start_command";
        String REPLY_ON_CONNECTED_DEVICE = "bt_reply_on_connected_device";
        String CONNECTED_DEVICES_SETTINGS = "bt_connected_device_settings";
        String OPEN_FILE_ON_CONNECTED_DEVICE = "bt_open_file_on_connected_device";
        String OPEN_SHARE_ON_CONNECTED_DEVICE = "bt_open_share_on_connected_device";
        String COLLECT_LOGS_FROM_CONNECTED_DEVICE = "bt_collect_logs_from_connected_device";

        String HANDLE_INC_CALL_MUTE = "bt_handle_inc_mute";
        String HANDLE_INC_CALL_UNMUTE = "bt_handle_inc_unmute";
        String HANDLE_INC_CALL_SPEAKER_MUTE = "bt_handle_inc_speaker_mute";
        String HANDLE_INC_CALL_SPEAKER_UNMUTE = "bt_handle_inc_speaker_unmute";
        String HANDLE_INC_CALL_START_VIDEO = "bt_handle_inc_start_video";
        String HANDLE_INC_CALL_STOP_VIDEO = "bt_handle_inc_stop_video";
        String HANDLE_INC_CALL_HOLD = "bt_handle_inc_call_hold";
        String HANDLE_INC_CALL_RESUME = "bt_handle_inc_call_resume";
        String HANDLE_INC_CALL_TRANSFER = "bt_handle_inc_call_transfer";
        String HANDLE_INC_CALL_UNKNOWN_COMMAND = "bt_handle_inc_unknown_call_command";

        String HANDLE_INC_CAPTIONS_ON = "bt_handle_inc_captions_on";
        String HANDLE_INC_CAPTIONS_OFF = "bt_handle_inc_captions_off";
        String HANDLE_INC_VOLUME_UP = "bt_handle_inc_volume_up";
        String HANDLE_INC_VOLUME_DOWN = "bt_handle_inc_volume_down";
        String HANDLE_INC_STAGE_LAYOUT_SHOW_GALLERY = "bt_handle_inc_stage_layout_show_gallery";
        String HANDLE_INC_STAGE_LAYOUT_SHOW_CONTENT = "bt_handle_inc_stage_layout_show_content";
        String HANDLE_INC_STAGE_LAYOUT_SHOW_GALLERY_CONTENT = "bt_handle_inc_stage_layout_show_gallery_content";
        String HANDLE_INC_STAGE_LAYOUT_SHOW_LARGE_GALLERY = "bt_handle_inc_stage_layout_show_large_gallery";
        String HANDLE_INC_STAGE_LAYOUT_SHOW_TOGETHER = "bt_handle_inc_stage_layout_show_together";
        String HANDLE_INC_ROOM_STATE_UPDATE = "bt_handle_inc_room_state_update";

        String OUTGOING_CALL_MUTE = "bt_outgoing_mute";
        String OUTGOING_CALL_UNMUTE = "bt_outgoing_unmute";
        String OUTGOING_CALL_START_VIDEO = "bt_outgoing_start_video";
        String OUTGOING_CALL_STOP_VIDEO = "bt_outgoing_stop_video";
        String OUTGOING_CALL_HOLD = "bt_outgoing_call_hold";
        String OUTGOING_CALL_RESUME = "bt_outgoing_call_resume";
        String OUTGOING_CALL_TRANSFER = "bt_outgoing_call_transfer";
        String OUTGOING_UNKNOWN_COMMAND = "bt_outgoing_unknown_command";
        String OUTGOING_ROOM_STATE_UPDATE = "bt_outgoing_room_state_update";
        String OUTGOING_ROOM_CAPABILITIES_UPDATE = "bt_outgoing_room_capabilities_update";

        String JOIN_BETTER_TOGETHER_MEETING = "bt_join_meeting";
        String SEND_KEEP_ALIVE_TO_PAIRED_ENDPOINT = "bt_keep_alive_outgoing";

        String ROOM_CONTROL_MUTE = "bt_room_control_mute";
        String ROOM_CONTROL_UNMUTE = "bt_room_control_unmute";
        String ROOM_CONTROL_CAMERA_ON = "bt_room_control_camera_on";
        String ROOM_CONTROL_CAMERA_OFF = "bt_room_control_camera_off";
        String ROOM_CONTROL_VOLUME_UP = "bt_room_control_volume_up";
        String ROOM_CONTROL_VOLUME_DOWN = "bt_room_control_volume_down";
        String ROOM_CONTROL_CAPTIONS_ON = "bt_room_control_captions_on";
        String ROOM_CONTROL_CAPTIONS_OFF = "bt_room_control_captions_off";
        String ROOM_CONTROL_LEAVE_MEETING = "bt_room_control_leave_meeting";
        String ROOM_CONTROL_STAGE_LAYOUT_SHOW_GALLERY = "bt_room_control_stage_layout_show_gallery";
        String ROOM_CONTROL_STAGE_LAYOUT_SHOW_CONTENT = "bt_room_control_stage_layout_show_content";
        String ROOM_CONTROL_STAGE_LAYOUT_SHOW_GALLERY_CONTENT = "bt_room_control_stage_layout_show_gallery_content";
        String ROOM_CONTROL_STAGE_LAYOUT_SHOW_LARGE_GALLERY = "bt_room_control_stage_layout_show_large_gallery";
        String ROOM_CONTROL_STAGE_LAYOUT_SHOW_TOGETHER = "bt_room_control_stage_layout_show_together";
        String BEGIN_PAIR_WITH_CODE = "bt_being_pair_with_code";
        String PAIR_WITH_CODE = "bt_pair_with_code";
        String JOIN_CALL_SIGNALLING = "bt_join_call_signalling";
        String SYNC_ACTIVE_CALL = "bt_sync_active_call";

        String SESSION_SETUP = "bt_session_setup";
    }

    @interface KingstonNotificationsModule {
        String NEW_CHAT_NOTIFICATION = "ambient_new_chat_notification";
        String NEW_ACTIVITY_NOTIFICATION = "ambient_new_alert_notification";
        String REFRESH_NOTIFICATIONS_MODULE = "ambient_refresh_notifications_module";
        String REFRESH_NOTIFICATIONS = "ambient_refresh_notifications";
        String REFRESH_MEETINGS = "ambient_refresh_meetings";
        String REFRESH_NOTES = "ambient_refresh_notes";
        String SYNC_ACTIVITY_FEED = "ambient_sync_activity_feed";
        String SYNC_MEETINGS = "ambient_sync_meetings";
        String LOAD_MORE_NOTIFICATIONS = "ambient_load_more_notifications";
    }

    @interface IPPhoneCompanyPortalIntents {
        String LAUNCH_COMPANY_PORTAL = "ipphone_launch_company_portal";
        String UNENROLL_USER = "ipphone_unenroll_user_from_company_portal";
        String ENROLLMENT_STATUS = "ipphone_enrollment_status_from_company_portal";
        String UNENROLLMENT_STATUS = "ipphone_unenrollment_status_from_company_portal";
        String SIGN_IN_READINESS_STATUS = "sign_in_readiness_status_from_company_portal";
        String CHECK_AAD_TOKEN = "check_aad_token";
    }

    // Pinned Chats scenarios
    @interface PinnedChats {
        String PIN_CHAT_ITEM = "pinChat";
        String UNPIN_CHAT_ITEM = "unpinChat";
        String SYNC_PINNED_CHATS = "syncPinnedChats";
        String UPDATE_PINNED_CHATS = "updatePinnedChats";
    }

    //Vault(Safe) TFL scenarios
    @interface Vault {
        String CREATE_VAULT_ITEM = "createVaultItem";
        String UPDATE_VAULT_ITEM = "updateVaultItem";
        String DELETE_VAULT_ITEM = "deleteVaultItem";
        String DELETE_VAULT_ITEMS = "deleteVaultItems";
        String SEARCH_VAULT = "searchVault";
        String SYNC_VAULT = "syncVault";
        String LOAD_VAULT_ITEM = "loadVaultItem";
        String CREATE_USER = "createVaultUser";
        String EXPORT_VAULT = "exportVaultData";
        String SYNC_KEYS = "syncVaultKeys";
        String SYNC_ITEMS = "syncVaultItems";
        String CREATE_VAULT = "createVault";
        String GRANT_ACCESS = "grantVaultAccess";
        String MANAGE_VAULT_KEY = "manageVaultKey";
        String DELETE_PERSONAL_VAULT = "deletePersonalVaultItems";
        String SYNC_ALL_MEDIA = "vaultSyncAllMedia";
        String SYNC_MEDIA_FOR_SECRET = "vaultSyncSecretMedia";
    }

    //Gallery TFL scenarios
    @interface Gallery {
        String GALLERY_LOAD_SLIDESHOW_IMAGE = "loadSlideshowImage";
        String GALLERY_LOAD_TILE_IMAGE = "loadTileImage";
        String GALLERY_QUERY_OSEARCH = "galleryQueryOSearch";
        String GALLERY_UPLOAD_IMAGES = "galleryMediaUpload";
        String GALLERY_SHOW_SINGLE_IMAGE = "galleryShowSingleImage";
    }

    //Invite TFL scenarios
    @interface Invites {
        String REDEEM_INVITE_LINK = "redeemInviteLink";
        String RECLAIM_TWO_WAY_SMS = "reclaimTwoWaySms";
    }

    @interface LiveLocation {
        String LOCATION_SHARING_SESSION_START = "location_session_start";
        String LOCATION_SHARING_SESSION_STOP = "location_session_stop";
        String LOCATION_SHARING_SESSION_STOP_ALL = "location_session_stop_all";
        String LOCATION_GROUP_MAP_SYNC = "location_group_map_sync";
        String LOCATION_MESSAGE_SEND = "location_message_send";
        String LOCATION_ACTIVE_TRACKING = "location_active_tracking";
        String MAP_LOAD = "location_map_load";
        String MAP_MARKERS_LOAD = "location_map_markers_load";
        String STOP_LOCATION_SHARING_LOGOUT = "stop_location_sharing_logout";
        String FAMILY_SYNC = "location_family_sync";
        String LOCATION_PLACE_ADD = "location_place_add";
        String LOCATION_PLACE_EDIT = "location_place_edit";
        String LOCATION_PLACE_DELETE = "location_place_delete";
        String LOCATION_PLACES_FETCH = "location_places_fetch";
        String LOCATION_TRIGGER_ADD = "location_trigger_add";
        String LOCATION_TRIGGER_ADD_ALL = "location_trigger_add_all";
        String LOCATION_TRIGGER_DELETE = "location_trigger_delete";
        String LOCATION_TRIGGER_DELETE_ALL = "location_trigger_delete_all";
        String LOCATION_TRIGGER_EDIT = "location_trigger_edit";
        String LOCATION_TRIGGER_DETAIL_FETCH = "location_trigger_detail_fetch";
        String LOCATION_TRIGGERS_FETCH = "location_triggers_fetch";
        String NOTIFICATION_DELAY = "location_notification_delay";
    }

    @interface Groups {
        String GROUP_CREATE_NEW_GROUP = "chat_create_new";
        String GROUP_ADD_USER = "chat_add_member";
        String GROUP_REMOVE_USER = "chat_remove_user";
        String GROUP_LEAVE_CHAT = "chat_leave";
        String GROUP_EDIT_NAME = "chat_rename";
        String GROUP_UPDATE_GROUP_AVATAR = "chat_update_picture";
        String GROUP_JOIN_SWITCH = "chat_join_switch";
        String GROUP_CREATE_SHARE_LINK = "chat_generate_invite_link";
        String GROUP_MUTE_SWITCH = "chat_mute_switch";
        String GROUP_INVITE_OFF_NETWORK_CONTACTS = "chat_invite_off_network_contacts";
    }

    @interface CoreIA {
        String OPEN_DASHBOARD_LOAD = "open_dashboard_load";
        String DASHBOARD_CALENDAR_TILE_LOAD = "dashboard_calendar_tile_load";
        String DASHBOARD_GALLERY_TILE_LOAD = "dashboard_gallery_tile_load";
        String DASHBOARD_LOCATION_TILE_LOAD = "dashboard_location_tile_load";
        String DASHBOARD_VAULT_TILE_LOAD = "dashboard_vault_tile_load";
        String DASHBOARD_FILES_TILE_LOAD = "dashboard_files_tile_load";
        String DASHBOARD_TASKS_TILE_LOAD = "dashboard_tasks_tile_load";
    }

    // BRB and OCV Scenarios
    @interface Feedback {
        String SUBMIT_BRB_FORM = "brbFormSubmitted";
        String SUBMIT_OCV_FORM = "ocvFormSubmitted";
    }

    @interface PlannerTasks {
        String UPDATE_PLANNER_TASK_AND_NAV_TO_VIEW = "update_planner_task_and_nav_to_view";
        String CREATE_DEFAULT_PLAN_AND_NAV_TO_VIEW = "create_default_plan_and_nav_to_view";
        String CREATE_PERSONAL_PLAN_AND_NAV_TO_VIEW = "create_personal_plan_and_nav_to_view";
        String CREATE_PERSONAL_TASK = "create_personal_task";
        String CREATE_PLANNER_PLAN_AND_NAV_TO_VIEW = "create_planner_plan_and_nav_to_view";
        String CREATE_PLANNER_TASK = "create_planner_task";
        String DELETE_PERSONAL_PLAN = "delete_personal_plan";
        String DELETE_PERSONAL_TASK = "delete_personal_task";
        String DELETE_PLANNER_PLAN = "delete_planner_plan";
        String DELETE_PLANNER_TASK = "delete_planner_task";
        String RENAME_PERSONAL_PLAN = "rename_personal_plan";
        String RENAME_PLANNER_PLAN = "rename_planner_plan";
        String UPDATE_PERSONAL_TASK_AND_NAV_TO_VIEW = "update_personal_task_and_nav_to_view";
    }

    /**
     * Scenario maker for ipphone Home screen feature.
     * <p>
     * NOTE: In alphabetical order only.
     */
    @interface IpphoneHomeScreen {
        String CALL_NOTIFICATION = "callNotification";
        String CALENDAR_NOTIFICATION = "calendarNotification";
        String VOICEMAIL_NOTIFICATION = "voicemailNotification";
    }


    @Retention(RetentionPolicy.SOURCE)
    @interface Fluid {
        /**
         * Track loading of a Fluid component in the chat stream as a message.
         */
        String FLUID_LOAD_MESSAGE = "fluid_load_message";
        /**
         * Track loading of Fluid component to compose a new chat message.
         */
        String FLUID_COMPOSE_MESSAGE = "fluid_compose_message";
        /**
         * Track fetching of an access token.
         */
        String FLUID_TOKEN_FETCH = "fluid_token_fetch";
    }

    /**
     * Scenario maker for ipphone Sidecar feature.
     */
    @interface IpphoneSidecar {
        String SIDECAR_PRESENTATION = "sidecarPresentation";
    }

    String APP_INCREMENTAL_SYNC_CONVERSATION_RESUME = "app_incremental_sync_conversation_resume";
    String APP_INCREMENTAL_SYNC_CONVERSATION_LAUNCH = "app_incremental_sync_conversation_launch";

    @interface SyncService {
        //FRE scenarios
        String ACCOUNT_TENANTS_NOTIFICATION_FRE = "account_tenants_notification_fre";
        String ADDRESS_BOOK_SYNC_FRE = "address_book_sync_fre";
        String ALERTS_SYNC_FRE = "alerts_sync_fre";
        String ALERTS_SYNC_EXT_FRE = "alerts_sync_ext_fre";
        String APP_DEFNS_SYNC_FRE = "app_defns_sync_fre";
        String APP_DEFNS_SYNC_EXT_FRE = "app_defns_sync_ext_fre";
        String APP_POLICY_SYNC_FRE = "app_policy_sync_fre";
        String BLOCK_LIST_SYNC_FRE = "block_list_sync_fre";
        String BOOKMARKS_SYNC_FRE = "bookmarks_sync_fre";
        String BOOKMARKS_STREAM_ID_SYNC_FRE = "bookmarks_stream_id_sync_fre";
        String CALENDAR_SYNC_FRE = "calendar_sync_fre";
        String CALLS_DATA_SYNC_FRE = "calls_data_sync_fre";
        String CALLS_DATA_SYNC_EXT_FRE = "calls_data_sync_ext_fre";
        String CALL_LOGS_SYNC_FRE = "call_logs_sync_fre";
        String CALL_LOGS_SYNC_EXT_FRE = "call_logs_sync_ext_fre";
        String CHECK_SEARCH_STAMPING_FRE = "check_search_stamping_fre";
        String CONTACT_GROUPS_SYNC_FRE = "contact_groups_sync_fre";
        String CONVERSATION_SYNC_FRE = "conversation_sync_fre";
        String CONVERSATION_SYNC_EXT_FRE = "conversation_sync_ext_fre";
        String CORE_MESSAGING_FRE = "core_messaging_fre";
        String FAVORITE_MESSAGES_SYNC_FRE = "favorite_messages_sync_fre";
        String FAVORITE_MESSAGES_SYNC_EXT_FRE = "favorite_messages_sync_ext_fre";
        String MESSAGES_SYNC_FRE = "messages_sync_fre";
        String MESSAGES_SYNC_EXT_FRE = "messages_sync_ext_fre";
        String ORS_OCPS_POLICY_SYNC_FRE = "ors_ocps_policy_sync_fre";
        String OTHER_CHATS_TEAM_THREAD_PROPS_SYNC_FRE = "other_chats_team_thread_props_sync_fre";
        String OTHER_CHATS_TEAM_THREAD_PROPS_SYNC_EXT_FRE = "other_chats_team_thread_props_sync_ext_fre";
        String PINNED_CHANNELS_SYNC_FRE = "pinned_channels_sync_fre";
        String RECENT_CHATS_THREAD_PROPS_SYNC_FRE = "recent_chats_thread_props_sync_fre";
        String RECENT_CHATS_THREAD_PROPS_SYNC_EXT_FRE = "recent_chats_thread_props_sync_ext_fre";
        String RNL_CONTACTS_SYNC_FRE = "rnl_contacts_sync_fre";
        String TEAM_MEMBER_TAGS_SYNC_FRE = "team_member_tags_sync_fre";
        String TOP_N_CACHE_SYNC_FRE = "top_n_cache_sync_fre";
        String VAULT_SECRETS_SYNC_FRE = "vault_secrets_sync_fre";
        String VOICEMAIL_SYNC_FRE = "voicemail_sync_fre";
        String FRE_SYNC_MESSAGING = "fre_sync_messaging";
        String FRE_SYNC_ENTIRE = "fre_sync_complete";

        //Delta Sync Scenarios
        String ACCOUNT_TENANTS_NOTIFICATION_DELTA = "account_tenants_notification_delta";
        String ADDRESS_BOOK_SYNC_DELTA = "address_book_sync_delta";
        String ALERTS_SYNC_DELTA = "alerts_sync_delta";
        String ALERTS_SYNC_EXT_DELTA = "alerts_sync_ext_delta";
        String APP_DEFNS_SYNC_DELTA = "app_defns_sync_delta";
        String APP_DEFNS_SYNC_EXT_DELTA = "app_defns_sync_ext_delta";
        String APP_POLICY_SYNC_DELTA = "app_policy_sync_delta";
        String BLOCK_LIST_SYNC_DELTA = "block_list_sync_delta";
        String BOOKMARKS_SYNC_DELTA = "bookmarks_sync_delta";
        String BOOKMARKS_STREAM_ID_SYNC_DELTA = "bookmarks_stream_id_sync_delta";
        String CALENDAR_SYNC_DELTA = "calendar_sync_delta";
        String CALLS_DATA_SYNC_DELTA = "calls_data_sync_delta";
        String CALL_LOGS_SYNC_DELTA = "call_logs_sync_delta";
        String CALL_LOGS_SYNC_EXT_DELTA = "call_logs_sync_ext_delta";
        String CHECK_SEARCH_STAMPING_DELTA = "check_search_stamping_delta";
        String CONTACT_GROUPS_SYNC_DELTA = "contact_groups_sync_delta";
        String CONVERSATION_SYNC_DELTA = "conversation_sync_delta";
        String CORE_MESSAGING_DELTA = "core_messaging_delta";
        String FAVORITE_MESSAGES_SYNC_DELTA = "favorite_messages_sync_delta";
        String FAVORITE_MESSAGES_SYNC_EXT_DELTA = "favorite_messages_sync_ext_delta";
        String MESSAGES_SYNC_DELTA = "messages_sync_delta";
        String ORS_OCPS_POLICY_SYNC_DELTA = "ors_ocps_policy_sync_delta";
        String OTHER_CHATS_TEAM_THREAD_PROPS_SYNC_DELTA = "other_chats_team_thread_props_sync_delta";
        String PINNED_CHANNELS_SYNC_DELTA = "pinned_channels_sync_delta";
        String RECENT_CHATS_THREAD_PROPS_SYNC_DELTA = "recent_chats_thread_props_sync_delta";
        String RNL_CONTACTS_SYNC_DELTA = "rnl_contacts_sync_delta";
        String TEAM_MEMBER_TAGS_SYNC_DELTA = "team_member_tags_sync_delta";
        String TOP_N_CACHE_SYNC_DELTA = "top_n_cache_sync_delta";
        String VAULT_SECRETS_SYNC_DELTA = "vault_secrets_sync_delta";
        String VOICEMAIL_SYNC_DELTA = "voicemail_sync_delta";
        String DELTA_SYNC_MESSAGING = "delta_sync_messaging";
        String DELTA_SYNC_ENTIRE = "delta_sync_complete";

        String DELTA_SYNC_ESSENTIAL = "start_sync";
        String SYNC_CONVERSATIONS_ONLY = "sync_conversations_only";
        String SYNC_AUTH_USER = "sync_auth_user";
        String SYNC_PILL_COUNT_UPDATE = "sync_pill_count_update";

        String CORE_MESSAGING_SYNC_TO_SERVER = "core_messaging_sync_to_server";
        String CALL_LOGS_PENDING_CHANGES = "call_logs_pending_changes";
        String BOOKMARKS_SYNC_TO_SERVER = "bookmarks_sync_to_server";
    }

    @interface MeetNow {
        String MEET_NOW_FLYOUT_OPEN = "meet_now_flyout_open";
        String MEET_NOW_URL_JOIN = "meet_now_url_join";
        String MEET_NOW_TAB_OPEN = "meet_now_tab_open";
    }

    @interface LiveStateService {
        String OPEN = "live_state_service_open";
        String CLOSE = "live_state_service_close";
        String SEND_REACTION = "meeting_reactions_send_reaction";
    }

    @interface HDMISource {
        String CONNECTED_OUTSIDE_CALL = "connected_outside_call";
        String PREVIEW_OUTSIDE_CALL = "preview_outside_call";
        String DISCONNECTED_OUTSIDE_CALL = "disconnected_outside_call";
        String CONNECTED_INSIDE_CALL = "connected_inside_call";
        String ABLE_TO_SHARE_CONTENT_INSIDE_CALL = "able_to_share_content_inside_call";
        String STOPPED_DURING_A_CALL = "stopped_during_a_call";
        String START_PREVIEW = "start_preview";
    }

    @interface RoomRemote {
        String ROOM_REMOTE_CLIENT_BANNER_ENTRYPOINT = "room_remote_client_banner_entrypoint";
        String ROOM_REMOTE_CLIENT_PARTICIPANT_OPTION_ENTRYPOINT = "room_remote_client_participant_option_entrypoint";
        String ROOM_REMOTE_ROOM_RECEIVE_INCOMING_SESSION = "room_remote_room_receive_incoming_session";
    }

    @interface Extensibility {
        // scenario for measuring the load time from initializing the webview to
        // displaying the first page of a platform app.
        String PLATFORM_WEB_APP_LOAD_TIME = "platform_web_app_load_time";
        @interface  MeetingExtensibility {
            // scenario for measuring execution time for MeetingExtensibilityService.launchInMeetingNotification()
            String LAUNCH_IN_MEETING_NOTIFICATION_COMPLETION_TIME = "launch_in_meeting_notification_completion_time";
            // scenario for measuring execution time for launching activity/ returning fragment to display in-meeting tabs
            String LAUNCH_IN_MEETING_TAB_COMPLETION_TIME = "launch_in_meeting_tab_completion_time";
            // scenario for marking when an in-meeting notification is suppressed
            String IN_MEETING_NOTIFICATION_SUPPRESSED = "in_meeting_notification_suppressed";
        }
    }

    @interface MeetingExtension {
        String MEETING_EXTENSION_GET_APPS = "meeting_extension_get_apps";
        String MEETING_EXTENSION_LAUNCH_APP = "meeting_extension_launch_app";
        String MEETING_EXTENSION_LAUNCH_NOTIFICATION = "meeting_extension_launch_notification";
    }
}
