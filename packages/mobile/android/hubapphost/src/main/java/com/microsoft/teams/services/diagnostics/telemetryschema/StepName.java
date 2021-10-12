/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.services.diagnostics.telemetryschema;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.StringDef;

import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ANONYMOUS_NAME_SCREEN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ANONYMOUS_NAME_SCREEN_NAVIGATED_AWAY;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ANONYMOUS_NAME_SCREEN_NAVIGATED_AWAY_WHILE_SETTING_UP;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ANONYMOUS_NAME_SCREEN_SETUP_FAILED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ANONYMOUS_NAME_SCREEN_USER_ENTERED_NAME;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ANONYMOUS_WELCOME_SCREEN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ANONYMOUS_WELCOME_SCREEN_DID_NOT_RECEIVE_SCENARIO;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ANONYMOUS_WELCOME_SCREEN_JOIN_AS_GUEST;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ANONYMOUS_WELCOME_SCREEN_NAVIGATED_AWAY;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ANONYMOUS_WELCOME_SCREEN_SIGN_IN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ANOTHER_CALL_ACTIVE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.BROADCAST_MEETINGS_DISABLED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.BROADCAST_MEETING_GETTING_ATTENDEE_INFO;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.BROADCAST_MEETING_VOD_ACCESS_DISABLED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.BROADCAST_MEETING_ATTENDEE_INFO_SUCCESS;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.BROADCAST_MEETING_ATTENDEE_INFO_ERROR;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.BROADCAST_MEETING_IS_BYOE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.BROADCAST_MEETING_IS_NOT_BYOE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.BROADCAST_MEETING_INITIALIZE_SIGNALR;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.SIGNALR_CONNECTION_SUCCESS;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.SIGNALR_MALFORMED_URL;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.SIGNALR_NO_CONNECTION_INFO;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.SIGNALR_START_STATE_UPDATE_PROCESSING;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.SIGNALR_STATE_UPDATE_RECEIVED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.SIGNALR_START_STREAM_UPDATE_PROCESSING;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.SIGNALR_STREAM_UPDATE_RECEIVED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.SIGNALR_ALT_STREAM_METATDATA_RECEIVED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.SIGNALR_STREAM_UPDATE_COULD_NOT_PARSE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.BROADCAST_MEETING_BYOE_LOAD_STREAM_PLAYER;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.BROADCAST_MEETING_INITIALIZE_QNA;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.BROADCAST_MEETING_LOADING;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.STREAM_PLAYER_NO_IFRAME_HOST_URL;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.STREAM_PLAYER_SERVER_ERROR;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.STREAM_PLAYER_SIGN_IN_ERROR;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.STREAM_PLAYER_SIGN_IN_SUCCESS;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.STREAM_PLAYER_FALL_BACK_TO_BROWSER;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.STREAM_PLAYER_NO_STREAM_DATA;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.BROADCAST_MEETING_IS_OVERFLOW;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.AMP_PLAYER_LOADING;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.AMP_PLAYER_LOAD_FAILURE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.AMP_PLAYER_UPDATE_STREAM_SOURCE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.AMP_PLAYER_RETRY;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.AMP_PLAYER_SWITCH_TO_ALT_STREAM_ON_ERROR;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.AMP_PLAYER_NO_ALT_STREAM_TO_SWITCH_ON_ERROR;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.AMP_PLAYER_UNKNOWN_ERROR;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.AMP_PLAYER_VIDEO_DOWNLOAD_FAILURE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.AMP_PLAYER_AUDIO_DOWNLOAD_FAILURE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.AMP_PLAYER_VIDEO_DOWNLOAD_COMPLETE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.AMP_PLAYER_AUDIO_DOWNLOAD_COMPLETE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.AMP_PLAYER_CANNOT_PARSE_VIDEO_DOWNLOAD;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.AMP_PLAYER_CANNOT_PARSE_AUDIO_DOWNLOAD;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.AMP_PLAYER_UPDATE_VIDEO_SOURCE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CALENDAR_VIEW_MEETING_JOIN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CALLMEBACK_MEETING_JOIN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CALL_CONNECTED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CALL_CONNECTING;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CALL_HANDLER_NULL;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CALL_OBJECT_CREATED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CALL_SERVICE_ADHOC_MEETING_JOIN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CALL_SERVICE_SCHEDULED_MEETING_JOIN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CALL_SETUP_FAILED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CALL_SETUP_FAILED_DB_ISSUE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CALL_SETUP_SUCCESS;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CHANNEL_ADHOC_MEETING_JOIN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CHANNEL_SCHEDULED_MEETING_JOIN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CHAT_MEETING_JOIN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CHAT_ONE_ON_ONE_CALL_JOIN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.COMPANION_BANNER_ONE_ON_ONE_CALL_JOIN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.COMPANION_HAND_OFF_SCREEN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.COMPANION_HAND_OFF_SCREEN_CLICKED_CLOSE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.COMPANION_HAND_OFF_SCREEN_NAVIGATED_AWAY;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CONNECTING_SIGNALLING_FAILURE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_ENTER_IDLE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_ENTER_LISTENING;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_START_LISTENING;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_START_PLAY_LISTENING_EARCON;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_COMPLETE_PLAY_LISTENING_EARCON;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_ENTER_SPEAKING;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_ENTER_THINKING;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_ERROR_RECEIVED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_FRE_FINISHED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_FRE_SHOW;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_INITIALIZATION_RUNNING_WORK_EXISTS;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_GET_NEW_RESOURCE_TOKEN_START;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_GET_NEW_RESOURCE_TOKEN_END;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_WARMING_UP_STAGE1;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_WARMING_UP_STAGE2;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_WARMING_UP_STAGE3;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_INITIALIZATION_TOKEN_REFRESH_START;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_INITIALIZATION_TOKEN_REFRESHED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_START;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_ON_CREATE_VIEW;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_VIEW_ON_RESUME;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_INITIALIZE_START;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_INITIALIZE_COMPLETED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_INITIALIZING;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_SHOW_SUGGESTION;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_MEETING_JOIN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_PAUSED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_QUERY_RESULT_RECEIVED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_ADAPTIVE_CARD_SUBMIT;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_ADAPTIVE_CARD_OPEN_URL;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_SHUTTING_DOWN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CORTANA_SPEECH_RECOGNITION_STARTED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.DEEP_LINK_NOT_ENOUGH_PARAMETERS;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.DEEP_LINK_PARAMETER_PARSING_FAILED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.DEEP_LINK_RECEIVED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.FAILED_TO_GET_USER_AUTH_DATA;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.JOINED_MEETING_ALREADY_CONNECTED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.JOIN_BUTTON_CLICKED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.JOIN_FROM_DEEPLINK;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CAN_NOT_JOIN_DEEPLINK_FROM_OTHER_TENANT;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.LIVE_STATE_NULL;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.LIVE_STATE_SYNC_DONE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.LIVE_STATE_SYNC_STARTED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.LLDP_INFO_RECEIVED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.LLDP_INFO_UPDATED_IN_E911_INFO;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.LOBBY_AUTO_RECONNECT_DIAL_IN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.LOBBY_CALL_CANCELLED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.LOBBY_DENIED_ENTRY;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.LOBBY_TIMEOUT;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.MEETING_DETAILS_VIEW_MEETING_JOIN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.AD_HOC_MEETING_DETAILS_VIEW_MEETING_JOIN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.MEETING_LOBBY;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.NAVIGATE_TO_PRE_CALL;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.NAVIGATE_TO_PRE_JOIN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.NAVIGATE_TO_PRE_JOIN_APP_BACKGROUND;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.NAVIGATE_TO_PRE_JOIN_APP_FOREGROUND;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.NOT_SUPPORTED_ARCH;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ONGOING_NOTIFICATION_MEETING_JOIN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PERMISSIONS_DENIED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.POLICY_RESTRICTED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.JOIN_ANON_DIFFERENT_CLOUD;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.JOIN_ANON_NO_HOME_TENANT;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_CALL_CALL_CANCELLED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_CALL_NAVIGATED_AWAY;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_CALL_OFFLINE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_CALL_SCREEN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_CALL_SETUP_VALUES_FAILED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_JOIN_BLOCKED_JOIN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_JOIN_CANT_JOIN_NATIVE_CALL;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_JOIN_CLICKED_CLOSE_WHILE_CONNECTING;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_JOIN_CONNECTING_APP_BACKGROUND;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_JOIN_NAVIGATED_AWAY;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_JOIN_NAVIGATED_AWAY_WHILE_CONNECTING;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_JOIN_NAVIGATED_AWAY_WHILE_BLOCKED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_JOIN_SCREEN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_JOIN_SCREEN_APP_BACKGROUND;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_JOIN_SCREEN_CALL_END_REASON;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_JOIN_SCREEN_CALL_ME;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_JOIN_SCREEN_CLICKED_CLOSE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_JOIN_SCREEN_DIAL_IN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_JOIN_SCREEN_DIAL_IN_ERROR;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_JOIN_SCREEN_INVALID_DIAL_IN_DETAILS;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_JOIN_SCREEN_JOINED_AS_ATTENDEE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_JOIN_SCREEN_JOIN_AS_ATTENDEE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_JOIN_SCREN_RETRY_CONNECTION;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_JOIN_SETUP_VALUES_FAILED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PRE_JOIN_TIMEOUT;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PROXIMITY_SENSOR_SERVICE_DEVICE_AWAY;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PROXIMITY_SENSOR_SERVICE_DEVICE_NEARBY;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PROXIMITY_SENSOR_SERVICE_DEVICE_STAY_ACTIVE_BROADCAST_SENT;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.PROXIMITY_SENSOR_SERVICE_INITIALIZED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.REJOIN_ADHOC_MEETING_JOIN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.REJOIN_SCHEDULED_MEETING_JOIN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.REPORTING_CRASH;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.SKYLIB_RESULT_CODE_ERROR;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.SKYLIB_RESULT_CODE_INCOMPLETE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.START;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.START_TRANSFERRING_CALL_QUEUE_CALL;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.STOP;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.TENANT_SWITCH_CLOUD;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.USER_CANCELLED_REQUEST;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.VERIFY_SKYLIB_END;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.VERIFY_SKYLIB_START;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.DATA_FETCHED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CHECK_AAD_TOKEN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CHECK_SIGN_IN_READINESS;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.VIEW_UPDATED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.SCENARIO_PAUSE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.SCENARIO_RESUME;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.CALL_MANAGER_NULL;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.UNIFIED_ANONYMOUS_NAME_SCREEN_USER_ENTERED_NAME;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.UNIFIED_ANONYMOUS_NAME_SCREEN_NAVIGATED_AWAY;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.UNIFIED_ANONYMOUS_NAME_SCREEN_NAVIGATED_AWAY_WHILE_SETTING_UP;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.UNIFIED_ANONYMOUS_NAME_SCREEN_DID_NOT_RECEIVE_SCENARIO;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.UNIFIED_ANONYMOUS_NAME_SCREEN_SIGN_IN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.UNIFIED_ANONYMOUS_NAME_SCREEN_SETUP_FAILED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.UNIFIED_ANONYMOUS_NAME_SCREEN;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.SDK_UNRECOGNIZED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.SDK_INVALID_URL;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ROOM_REMOTE_CLIENT_START_REQUEST_LOCATION_PERMISSION;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ROOM_REMOTE_CLIENT_RUN_WITH_LOCATION_PERMISSION;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ROOM_REMOTE_CLIENT_WAITING_FOR_BLUETOOTH_ON;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ROOM_REMOTE_CLIENT_RUN_WITH_BLUETOOTH_ON;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ROOM_REMOTE_CLIENT_CHECK_PROXIMITY;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ROOM_REMOTE_CLIENT_CANCEL_CHECK_PROXIMITY;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ROOM_REMOTE_CLIENT_CHECK_PROXIMITY_TIMEOUT;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ROOM_REMOTE_CLIENT_START_SETUP_SESSION;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ROOM_REMOTE_CLIENT_ON_ENDPOINT_RECEIVED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ROOM_REMOTE_CLIENT_REUSE_EXISTING_SESSION;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ROOM_REMOTE_CLIENT_END_SESSION;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ROOM_REMOTE_CLIENT_START_PAIR;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ROOM_REMOTE_CLIENT_PAIR_SUCCESS;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ROOM_REMOTE_CLIENT_PAIR_FAILURE;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ROOM_REMOTE_ROOM_ON_ENDPOINT_RECEIVED;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ROOM_REMOTE_ROOM_RECEIVE_PAIRING_COMMAND;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ROOM_REMOTE_ROOM_END_SESSION;
import static com.microsoft.teams.services.diagnostics.telemetryschema.StepName.ROOM_REMOTE_ROOM_ACCEPT_INCOMING_SESSION;

/**
 * Enumeration for Step name
 */
@Retention(RetentionPolicy.SOURCE)
@StringDef({
        START,
        STOP,
        DEEP_LINK_PARAMETER_PARSING_FAILED,
        BROADCAST_MEETINGS_DISABLED,
        ANOTHER_CALL_ACTIVE,
        PERMISSIONS_DENIED,
        DEEP_LINK_NOT_ENOUGH_PARAMETERS,
        FAILED_TO_GET_USER_AUTH_DATA,
        NOT_SUPPORTED_ARCH,
        USER_CANCELLED_REQUEST,
        CALL_HANDLER_NULL,
        CONNECTING_SIGNALLING_FAILURE,
        SKYLIB_RESULT_CODE_ERROR,
        SKYLIB_RESULT_CODE_INCOMPLETE,
        LIVE_STATE_NULL,
        DEEP_LINK_RECEIVED,
        JOIN_FROM_DEEPLINK,
        CAN_NOT_JOIN_DEEPLINK_FROM_OTHER_TENANT,
        JOIN_BUTTON_CLICKED,
        CALL_OBJECT_CREATED,
        CALL_CONNECTING,
        CALL_CONNECTED,
        NAVIGATE_TO_PRE_JOIN,
        NAVIGATE_TO_PRE_JOIN_APP_FOREGROUND,
        NAVIGATE_TO_PRE_JOIN_APP_BACKGROUND,
        NAVIGATE_TO_PRE_CALL,
        CHANNEL_SCHEDULED_MEETING_JOIN,
        CHANNEL_ADHOC_MEETING_JOIN,
        CALENDAR_VIEW_MEETING_JOIN,
        MEETING_DETAILS_VIEW_MEETING_JOIN,
        AD_HOC_MEETING_DETAILS_VIEW_MEETING_JOIN,
        SCENARIO_PAUSE,
        SCENARIO_RESUME,
        CALL_SERVICE_SCHEDULED_MEETING_JOIN,
        CALL_SERVICE_ADHOC_MEETING_JOIN,
        ONGOING_NOTIFICATION_MEETING_JOIN,
        CORTANA_MEETING_JOIN,
        CHAT_MEETING_JOIN,
        CHAT_ONE_ON_ONE_CALL_JOIN,
        COMPANION_BANNER_ONE_ON_ONE_CALL_JOIN,
        REJOIN_SCHEDULED_MEETING_JOIN,
        REJOIN_ADHOC_MEETING_JOIN,
        CALLMEBACK_MEETING_JOIN,
        JOINED_MEETING_ALREADY_CONNECTED,
        PRE_JOIN_SCREEN,
        PRE_JOIN_NAVIGATED_AWAY,
        PRE_JOIN_BLOCKED_JOIN,
        PRE_JOIN_SCREEN_APP_BACKGROUND,
        PRE_JOIN_SCREEN_CLICKED_CLOSE,
        PRE_JOIN_CLICKED_CLOSE_WHILE_CONNECTING,
        PRE_JOIN_NAVIGATED_AWAY_WHILE_CONNECTING,
        PRE_JOIN_NAVIGATED_AWAY_WHILE_BLOCKED,
        PRE_JOIN_CONNECTING_APP_BACKGROUND,
        PRE_CALL_SCREEN,
        PRE_CALL_CALL_CANCELLED,
        PRE_CALL_OFFLINE,
        PRE_CALL_NAVIGATED_AWAY,
        VERIFY_SKYLIB_START,
        VERIFY_SKYLIB_END,
        CALL_SETUP_SUCCESS,
        CALL_SETUP_FAILED,
        CALL_SETUP_FAILED_DB_ISSUE,
        MEETING_LOBBY,
        PRE_JOIN_TIMEOUT,
        LOBBY_CALL_CANCELLED,
        LOBBY_AUTO_RECONNECT_DIAL_IN,
        PRE_JOIN_SCREEN_CALL_ME,
        PRE_JOIN_SCREEN_DIAL_IN,
        PRE_JOIN_SCREEN_JOIN_AS_ATTENDEE,
        PRE_JOIN_SETUP_VALUES_FAILED,
        PRE_CALL_SETUP_VALUES_FAILED,
        PRE_JOIN_CANT_JOIN_NATIVE_CALL,
        LOBBY_TIMEOUT,
        LOBBY_DENIED_ENTRY,
        POLICY_RESTRICTED,
        JOIN_ANON_DIFFERENT_CLOUD,
        JOIN_ANON_NO_HOME_TENANT,
        LIVE_STATE_SYNC_STARTED,
        LIVE_STATE_SYNC_DONE,
        LLDP_INFO_RECEIVED,
        LLDP_INFO_UPDATED_IN_E911_INFO,
        PRE_JOIN_SCREEN_INVALID_DIAL_IN_DETAILS,
        REPORTING_CRASH,
        PRE_JOIN_SCREEN_DIAL_IN_ERROR,
        ANONYMOUS_WELCOME_SCREEN_SIGN_IN,
        ANONYMOUS_WELCOME_SCREEN_JOIN_AS_GUEST,
        ANONYMOUS_WELCOME_SCREEN,
        ANONYMOUS_WELCOME_SCREEN_NAVIGATED_AWAY,
        ANONYMOUS_WELCOME_SCREEN_DID_NOT_RECEIVE_SCENARIO,
        ANONYMOUS_NAME_SCREEN,
        ANONYMOUS_NAME_SCREEN_NAVIGATED_AWAY,
        ANONYMOUS_NAME_SCREEN_NAVIGATED_AWAY_WHILE_SETTING_UP,
        ANONYMOUS_NAME_SCREEN_SETUP_FAILED,
        ANONYMOUS_NAME_SCREEN_USER_ENTERED_NAME,
        CALL_MANAGER_NULL,
        UNIFIED_ANONYMOUS_NAME_SCREEN_USER_ENTERED_NAME,
        UNIFIED_ANONYMOUS_NAME_SCREEN_NAVIGATED_AWAY,
        UNIFIED_ANONYMOUS_NAME_SCREEN_NAVIGATED_AWAY_WHILE_SETTING_UP,
        UNIFIED_ANONYMOUS_NAME_SCREEN_DID_NOT_RECEIVE_SCENARIO,
        UNIFIED_ANONYMOUS_NAME_SCREEN_SIGN_IN,
        UNIFIED_ANONYMOUS_NAME_SCREEN_SETUP_FAILED,
        UNIFIED_ANONYMOUS_NAME_SCREEN,
        PRE_JOIN_SCREEN_JOINED_AS_ATTENDEE,
        PRE_JOIN_SCREEN_CALL_END_REASON,
        BROADCAST_MEETING_GETTING_ATTENDEE_INFO,
        BROADCAST_MEETING_VOD_ACCESS_DISABLED,
        BROADCAST_MEETING_ATTENDEE_INFO_SUCCESS,
        BROADCAST_MEETING_ATTENDEE_INFO_ERROR,
        BROADCAST_MEETING_IS_BYOE,
        BROADCAST_MEETING_IS_NOT_BYOE,
        BROADCAST_MEETING_INITIALIZE_SIGNALR,
        SIGNALR_CONNECTION_SUCCESS,
        SIGNALR_MALFORMED_URL,
        SIGNALR_NO_CONNECTION_INFO,
        SIGNALR_START_STATE_UPDATE_PROCESSING,
        SIGNALR_STATE_UPDATE_RECEIVED,
        SIGNALR_START_STREAM_UPDATE_PROCESSING,
        SIGNALR_STREAM_UPDATE_RECEIVED,
        SIGNALR_ALT_STREAM_METATDATA_RECEIVED,
        SIGNALR_STREAM_UPDATE_COULD_NOT_PARSE,
        BROADCAST_MEETING_BYOE_LOAD_STREAM_PLAYER,
        BROADCAST_MEETING_INITIALIZE_QNA,
        BROADCAST_MEETING_LOADING,
        STREAM_PLAYER_NO_IFRAME_HOST_URL,
        STREAM_PLAYER_SERVER_ERROR,
        STREAM_PLAYER_SIGN_IN_ERROR,
        STREAM_PLAYER_SIGN_IN_SUCCESS,
        STREAM_PLAYER_FALL_BACK_TO_BROWSER,
        STREAM_PLAYER_NO_STREAM_DATA,
        BROADCAST_MEETING_IS_OVERFLOW,
        AMP_PLAYER_LOADING,
        AMP_PLAYER_LOAD_FAILURE,
        AMP_PLAYER_UPDATE_STREAM_SOURCE,
        AMP_PLAYER_RETRY,
        AMP_PLAYER_SWITCH_TO_ALT_STREAM_ON_ERROR,
        AMP_PLAYER_NO_ALT_STREAM_TO_SWITCH_ON_ERROR,
        AMP_PLAYER_UNKNOWN_ERROR,
        AMP_PLAYER_VIDEO_DOWNLOAD_FAILURE,
        AMP_PLAYER_AUDIO_DOWNLOAD_FAILURE,
        AMP_PLAYER_VIDEO_DOWNLOAD_COMPLETE,
        AMP_PLAYER_AUDIO_DOWNLOAD_COMPLETE,
        AMP_PLAYER_CANNOT_PARSE_VIDEO_DOWNLOAD,
        AMP_PLAYER_CANNOT_PARSE_AUDIO_DOWNLOAD,
        AMP_PLAYER_UPDATE_VIDEO_SOURCE,
        COMPANION_HAND_OFF_SCREEN,
        COMPANION_HAND_OFF_SCREEN_CLICKED_CLOSE,
        COMPANION_HAND_OFF_SCREEN_NAVIGATED_AWAY,
        TENANT_SWITCH_CLOUD,
        PRE_JOIN_SCREN_RETRY_CONNECTION,
        START_TRANSFERRING_CALL_QUEUE_CALL,
        CORTANA_START,
        CORTANA_ON_CREATE_VIEW,
        CORTANA_VIEW_ON_RESUME,
        CORTANA_INITIALIZE_START,
        CORTANA_INITIALIZE_COMPLETED,
        CORTANA_INITIALIZING,
        CORTANA_SHOW_SUGGESTION,
        CORTANA_START_LISTENING,
        CORTANA_START_PLAY_LISTENING_EARCON,
        CORTANA_COMPLETE_PLAY_LISTENING_EARCON,
        CORTANA_ENTER_LISTENING,
        CORTANA_ENTER_THINKING,
        CORTANA_ENTER_SPEAKING,
        CORTANA_ENTER_IDLE,
        CORTANA_PAUSED,
        CORTANA_SHUTTING_DOWN,
        CORTANA_SPEECH_RECOGNITION_STARTED,
        CORTANA_QUERY_RESULT_RECEIVED,
        CORTANA_ADAPTIVE_CARD_SUBMIT,
        CORTANA_ADAPTIVE_CARD_OPEN_URL,
        CORTANA_ERROR_RECEIVED,
        CORTANA_FRE_SHOW,
        CORTANA_FRE_FINISHED,
        CALLMEBACK_MEETING_JOIN,
        CORTANA_INITIALIZATION_TOKEN_REFRESH_START,
        CORTANA_INITIALIZATION_TOKEN_REFRESHED,
        CORTANA_INITIALIZATION_RUNNING_WORK_EXISTS,
        CORTANA_GET_NEW_RESOURCE_TOKEN_START,
        CORTANA_GET_NEW_RESOURCE_TOKEN_END,
        CORTANA_WARMING_UP_STAGE1,
        CORTANA_WARMING_UP_STAGE2,
        CORTANA_WARMING_UP_STAGE3,
        PROXIMITY_SENSOR_SERVICE_INITIALIZED,
        PROXIMITY_SENSOR_SERVICE_DEVICE_STAY_ACTIVE_BROADCAST_SENT,
        PROXIMITY_SENSOR_SERVICE_DEVICE_NEARBY,
        PROXIMITY_SENSOR_SERVICE_DEVICE_AWAY,
        CHECK_AAD_TOKEN,
        CHECK_SIGN_IN_READINESS,
        DATA_FETCHED,
        VIEW_UPDATED,
        SDK_UNRECOGNIZED,
        SDK_INVALID_URL,
        ROOM_REMOTE_CLIENT_START_REQUEST_LOCATION_PERMISSION,
        ROOM_REMOTE_CLIENT_RUN_WITH_LOCATION_PERMISSION,
        ROOM_REMOTE_CLIENT_WAITING_FOR_BLUETOOTH_ON,
        ROOM_REMOTE_CLIENT_RUN_WITH_BLUETOOTH_ON,
        ROOM_REMOTE_CLIENT_CHECK_PROXIMITY,
        ROOM_REMOTE_CLIENT_CANCEL_CHECK_PROXIMITY,
        ROOM_REMOTE_CLIENT_CHECK_PROXIMITY_TIMEOUT,
        ROOM_REMOTE_CLIENT_START_SETUP_SESSION,
        ROOM_REMOTE_CLIENT_ON_ENDPOINT_RECEIVED,
        ROOM_REMOTE_CLIENT_REUSE_EXISTING_SESSION,
        ROOM_REMOTE_CLIENT_END_SESSION,
        ROOM_REMOTE_CLIENT_START_PAIR,
        ROOM_REMOTE_CLIENT_PAIR_SUCCESS,
        ROOM_REMOTE_CLIENT_PAIR_FAILURE,
        ROOM_REMOTE_ROOM_ON_ENDPOINT_RECEIVED,
        ROOM_REMOTE_ROOM_RECEIVE_PAIRING_COMMAND,
        ROOM_REMOTE_ROOM_END_SESSION,
        ROOM_REMOTE_ROOM_ACCEPT_INCOMING_SESSION
})
public @interface StepName {
    String START = "START";
    String STOP = "STOP";

    // Meetings-Join Step Names
    String ANOTHER_CALL_ACTIVE = "anotherCallActive";
    String REPORTING_CRASH = "reportingCrash";
    String DEEP_LINK_PARAMETER_PARSING_FAILED = "parameterParsingFailed";
    String BROADCAST_MEETINGS_DISABLED = "broadcastMeetingsDisabled";
    String PERMISSIONS_DENIED = "Permissions_Denied";
    String DEEP_LINK_NOT_ENOUGH_PARAMETERS = "notEnoughParametersInDeepLink";
    String USER_CANCELLED_REQUEST = "userCancelledRequest";
    String CALL_HANDLER_NULL = "callHandlerNull";
    String CONNECTING_SIGNALLING_FAILURE = "Connecting_SignallingFailure";
    String LIVE_STATE_NULL = "liveStateNull";
    String DEEP_LINK_RECEIVED = "deepLinkReceivedOnAndroid";
    String JOIN_FROM_DEEPLINK = "joinFromDeeplink";
    String JOIN_FROM_DEEPLINK_MEETING_CODE = "joinFromDeeplinkMeetingCode";
    String CAN_NOT_JOIN_DEEPLINK_FROM_OTHER_TENANT = "canNotJoinDeepLinkFromOtherTenant";
    String JOIN_BUTTON_CLICKED = "joinButtonClicked";
    String CALL_OBJECT_CREATED = "callObjectCreated";
    String CALL_CONNECTING = "connecting";
    String CALL_CONNECTED = "connected";
    String NAVIGATE_TO_PRE_JOIN = "navigateToPreJoinScreen";
    String NAVIGATE_TO_PRE_JOIN_APP_BACKGROUND = "navigateToPreJoinScreenAppBackground";
    String NAVIGATE_TO_PRE_JOIN_APP_FOREGROUND = "navigateToPreJoinScreenAppForeground";
    String NAVIGATE_TO_PRE_CALL = "navigateToPreCallScreen";
    String CHANNEL_SCHEDULED_MEETING_JOIN = "channelScheduledMeetingJoin";
    String CHANNEL_ADHOC_MEETING_JOIN = "channelAdhocMeetingJoin";
    String CALENDAR_VIEW_MEETING_JOIN = "calendarViewMeetingJoin";
    String MEET_NOW_SETUP = "meetNowSetup";
    String MEET_NOW_SETUP_SUCCEED = "meetNowSetupSucceeed";
    String MEET_NOW_SETUP_FAILED = "meetNowSetupFailed";
    String MAIN_ACTIVITY_MEET_NOW_JOIN = "mainActivityMeetNowJoin";
    String CONTEXT_MENU_MEET_NOW_JOIN = "contextMenuMeetNowJoin";
    String MEETING_DETAILS_VIEW_MEETING_JOIN = "meetingDetailsViewMeetingJoin";
    String SEARCH_ANSWER_MEETING_JOIN = "searchAnswerMeetingJoin";
    String AD_HOC_MEETING_DETAILS_VIEW_MEETING_JOIN = "adHocMeetingDetailsViewMeetingJoin";
    String CALL_SERVICE_SCHEDULED_MEETING_JOIN = "callServiceScheduledMeetingJoin";
    String CALL_SERVICE_ADHOC_MEETING_JOIN = "callServiceAdhocMeetingJoin";
    String ONGOING_NOTIFICATION_MEETING_JOIN = "ongoingNotificationMeetingJoin";
    String CORTANA_MEETING_JOIN = "cortanaMeetingJoin";
    String CHAT_MEETING_JOIN = "chatMeetingJoin";
    String CHAT_ONE_ON_ONE_CALL_JOIN = "chatOneOnOneCallJoin";
    String COMPANION_BANNER_ONE_ON_ONE_CALL_JOIN = "companionBannerOneOnOneCallJoin";
    String COMPANION_BANNER_GROUP_CALL_JOIN = "companionBannerGroupCallJoin";
    String REJOIN_SCHEDULED_MEETING_JOIN = "rejoinScheduledMeetingJoin";
    String REJOIN_ADHOC_MEETING_JOIN = "rejoinAdhocMeetingJoin";
    String CALLMEBACK_MEETING_JOIN = "callMeBackMeetingJoin";
    String JOINED_MEETING_ALREADY_CONNECTED = "JoinedMeeting_alreadyConnected";
    String PRE_JOIN_SCREEN = "PreJoinScreen";
    String PRE_JOIN_NAVIGATED_AWAY = "PreJoinScreen_NavigatedAway";
    String PRE_JOIN_BLOCKED_JOIN = "PreJoinScreen_BlockedJoin";
    String PRE_JOIN_SCREEN_APP_BACKGROUND = "PreJoinScreen_AppBackground";
    String PRE_JOIN_SCREEN_CLICKED_CLOSE = "PreJoinScreen_ClickedClose";
    String PRE_JOIN_CLICKED_CLOSE_WHILE_CONNECTING = "Connecting_ClickedClose";
    String PRE_JOIN_NAVIGATED_AWAY_WHILE_CONNECTING = "Connecting_NavigatedAway";
    String PRE_JOIN_NAVIGATED_AWAY_WHILE_BLOCKED = "Blocked_NavigatedAway";
    String PRE_JOIN_CONNECTING_APP_BACKGROUND = "Connecting_AppBackground";
    String PRE_CALL_SCREEN = "PreCallScreen";
    String PRE_CALL_CALL_CANCELLED = "PreCallScreen_CallCancelled";
    String PRE_CALL_OFFLINE = "PreCallScreen_Offline";
    String PRE_CALL_NAVIGATED_AWAY = "PreCallScreen_NavigatedAway";
    String VERIFY_SKYLIB_START = "CallSetup_verifySkyLibStart";
    String VERIFY_SKYLIB_END = "CallSetup_verifySkyLibEnd";
    String CALL_SETUP_SUCCESS = "CallSetup_Success";
    String CALL_SETUP_FAILED = "CallSetup_Failed";
    String CALL_SETUP_FAILED_DB_ISSUE = "CallSetup_FailedDBIssue";
    String MEETING_LOBBY = "Lobby";
    String PRE_JOIN_TIMEOUT = "PreJoinScreen_TimeOut";
    String LOBBY_CALL_CANCELLED = "Lobby_CallCancelled";
    String LOBBY_AUTO_RECONNECT_DIAL_IN = "Lobby_AutoReconnect_DialIn";
    String PRE_JOIN_SCREEN_CALL_ME = "PreJoinScreen_CallMe";
    String PRE_JOIN_SCREEN_DIAL_IN = "PreJoinScreen_DialIn";
    String PRE_JOIN_SCREEN_JOIN_AS_ATTENDEE = "PreJoinScreen_JoinAsAttendee";
    String PRE_JOIN_SETUP_VALUES_FAILED = "PreJoinScreen_SetupFailed";
    String PRE_CALL_SETUP_VALUES_FAILED = "PreCallScreen_SetupFailed";
    String PRE_JOIN_CANT_JOIN_NATIVE_CALL = "PreCallScreen_CantJoin_NativeCall";
    String LOBBY_TIMEOUT = "Lobby_Timeout";
    String LOBBY_DENIED_ENTRY = "Lobby_DeniedEntry";
    String POLICY_RESTRICTED = "Policy_Restricted";
    String JOIN_ANON_DIFFERENT_CLOUD = "Join_Anon_Different_Cloud";
    String JOIN_ANON_NO_HOME_TENANT = "Join_Anon_No_Home_Tenant";
    String JOIN_ANON_PERSONAL_CONSUMER = "Join_Anon_Personal_Consumer";
    String LIVE_STATE_SYNC_STARTED = "CallSetup_liveStateSyncStarted";
    String LIVE_STATE_SYNC_DONE = "CallSetup_liveStateSyncDone";
    String LLDP_INFO_RECEIVED = "LLDPInfo_received";
    String LLDP_INFO_UPDATED_IN_E911_INFO = "LLDPInfo_updatedInE911Info";
    String PRE_JOIN_SCREEN_INVALID_DIAL_IN_DETAILS = "PreJoinScreen__InvalidDialInDetails";
    String PRE_JOIN_SCREEN_DIAL_IN_ERROR = "PreJoinScreen_dialInError";
    String ANONYMOUS_WELCOME_SCREEN_SIGN_IN = "AnonymousWelcomeScreen_SignIn";
    String ANONYMOUS_WELCOME_SCREEN_JOIN_AS_GUEST = "AnonymousWelcomeScreen_JoinAsGuest";
    String ANONYMOUS_WELCOME_SCREEN = "AnonymousWelcomeScreen";
    String ANONYMOUS_WELCOME_SCREEN_NAVIGATED_AWAY = "AnonymousWelcomeScreen_NavigatedAway";
    String ANONYMOUS_WELCOME_SCREEN_DID_NOT_RECEIVE_SCENARIO = "AnonymousWelcomeScreen_DidNotReceiveScenario";
    String ANONYMOUS_NAME_SCREEN = "AnonymousNameScreen";
    String ANONYMOUS_NAME_SCREEN_NAVIGATED_AWAY = "AnonymousNameScreen_NavigatedAway";
    String ANONYMOUS_NAME_SCREEN_NAVIGATED_AWAY_WHILE_SETTING_UP = "AnonymousNameScreen_NavigatedAway_WhileSettingUp";
    String ANONYMOUS_NAME_SCREEN_SETUP_FAILED = "AnonymousNameScreen_SetupFailed";
    String ANONYMOUS_NAME_SCREEN_USER_ENTERED_NAME = "AnonymousNameScreen_UserEnteredName";
    String ANONYMOUS_NAME_SCREEN_SIGN_IN = "AnonymousNameScreen_SignIn";
    String ANONYMOUS_NAME_SCREEN_DID_NOT_RECEIVE_SCENARIO = "AnonymousNameScreen_DidNotReceiveScenario";
    String UNIFIED_ANONYMOUS_NAME_SCREEN_USER_ENTERED_NAME = "UnifiedAnonymousNameScreen_UserEnteredName";
    String UNIFIED_ANONYMOUS_NAME_SCREEN_NAVIGATED_AWAY = "UnifiedAnonymousNameScreen_NavigatedAway";
    String UNIFIED_ANONYMOUS_NAME_SCREEN_NAVIGATED_AWAY_WHILE_SETTING_UP = "UnifiedAnonymousNameScreen_NavigatedAway_WhileSettingUp";
    String UNIFIED_ANONYMOUS_NAME_SCREEN_DID_NOT_RECEIVE_SCENARIO = "UnifiedAnonymousNameScreen_DidNotReceiveScenario";
    String UNIFIED_ANONYMOUS_NAME_SCREEN_SIGN_IN = "UnifiedAnonymousNameScreen_SignIn";
    String UNIFIED_ANONYMOUS_NAME_SCREEN_SETUP_FAILED = "UnifiedAnonymousNameScreen_SetupFailed";
    String UNIFIED_ANONYMOUS_NAME_SCREEN = "UnifiedAnonymousNameScreen";
    String JOIN_BLOCKED_BY_MEETING_POLICY = "Connecting_JoinMeetingBlockedByMeetingPolicy";
    String JOIN_BLOCKED_BY_MEETING_LOCK = "Connecting_JoinMeetingBlockedByMeetingLock";
    String JOIN_MEETING_USING_CODE = "JoinMeeting_withMeetingCode";
    String CALL_MANAGER_NULL = "callManagerNull";

    String PRE_JOIN_SCREEN_JOINED_AS_ATTENDEE = "PreJoinScreen_JoinedAsAttendee";
    String PRE_JOIN_SCREEN_CALL_END_REASON = "PreJoinScreen_CallEndReason";
    String COMPANION_HAND_OFF_SCREEN = "CompanionHandOffScreen";
    String COMPANION_HAND_OFF_SCREEN_CLICKED_CLOSE = "CompanionHandOffScreen_ClickedClose";
    String COMPANION_HAND_OFF_SCREEN_NAVIGATED_AWAY = "CompanionHandOffScreen_NavigatedAway";
    String TENANT_SWITCH_CLOUD = "TenantSwitch_Cloud";
    String PRE_JOIN_SCREN_RETRY_CONNECTION = "PreJoinScreen_retryConnection";
    String SCENARIO_PAUSE = "scenarioPause";
    String SCENARIO_RESUME = "scenarioResume";

    // Live Event/Broadcast Meeting step names
    String BROADCAST_MEETING_GETTING_ATTENDEE_INFO = "broadcast_meeting_getting_attendee_info";
    String BROADCAST_MEETING_VOD_ACCESS_DISABLED = "broadcast_meeting_vod_access_disabled";
    String BROADCAST_MEETING_ATTENDEE_INFO_SUCCESS = "broadcast_meeting_attendee_info_success";
    String BROADCAST_MEETING_ATTENDEE_INFO_ERROR = "broadcast_meeting_getting_attendee_info_error";
    String BROADCAST_MEETING_IS_BYOE = "broadcast_meeting_is_byoe";
    String BROADCAST_MEETING_IS_NOT_BYOE = "broadcast_meeting_is_not_byoe";
    String BROADCAST_MEETING_INITIALIZE_SIGNALR = "broadcast_meeting_initialize_signalr";
    String SIGNALR_CONNECTION_SUCCESS = "signalr_connection_success";
    String SIGNALR_MALFORMED_URL = "signalr_malformed_url";
    String SIGNALR_NO_CONNECTION_INFO = "signalr_no_connection_info";
    String SIGNALR_START_STATE_UPDATE_PROCESSING = "signalr_start_state_update_processing";
    String SIGNALR_STATE_UPDATE_RECEIVED = "signalr_state_update_received";
    String SIGNALR_START_STREAM_UPDATE_PROCESSING = "signalr_start_stream_update_processing";
    String SIGNALR_STREAM_UPDATE_RECEIVED = "broadcast_meeting_stream_found";
    String SIGNALR_ALT_STREAM_METATDATA_RECEIVED = "signalr_alt_stream_metadata_received";
    String SIGNALR_STREAM_UPDATE_COULD_NOT_PARSE = "signalr_stream_update_could_not_parse";
    String BROADCAST_MEETING_BYOE_LOAD_STREAM_PLAYER = "broadcast_meeting_byoe_load_stream_player";
    String BROADCAST_MEETING_INITIALIZE_QNA = "broadcast_meeting_initialize_qna";
    String BROADCAST_MEETING_LOADING = "broadcast_meeting_loading";
    String STREAM_PLAYER_NO_IFRAME_HOST_URL = "stream_player_no_iframe_host_url";
    String STREAM_PLAYER_SERVER_ERROR = "stream_player_server_error";
    String STREAM_PLAYER_SIGN_IN_ERROR = "stream_player_sign_in_error";
    String STREAM_PLAYER_SIGN_IN_SUCCESS = "stream_player_sign_in_success";
    String STREAM_PLAYER_FALL_BACK_TO_BROWSER = "stream_player_fall_back_to_browser";
    String STREAM_PLAYER_NO_STREAM_DATA = "stream_player_no_stream_data";
    String BROADCAST_MEETING_IS_OVERFLOW = "broadcast_meeting_is_overflow";
    String AMP_PLAYER_LOADING = "amp_player_loading";
    String AMP_PLAYER_LOAD_FAILURE = "amp_player_load_failure";
    String AMP_PLAYER_UPDATE_STREAM_SOURCE = "amp_player_update_stream_source";
    String AMP_PLAYER_RETRY = "amp_player_retry";
    String AMP_PLAYER_SWITCH_TO_ALT_STREAM_ON_ERROR = "amp_player_switch_to_alt_stream";
    String AMP_PLAYER_NO_ALT_STREAM_TO_SWITCH_ON_ERROR = "amp_player_no_alt_stream_to_switch_on_error";
    String AMP_PLAYER_UNKNOWN_ERROR = "amp_player_unknown_error";
    String AMP_PLAYER_VIDEO_DOWNLOAD_FAILURE = "amp_player_video_download_failure";
    String AMP_PLAYER_AUDIO_DOWNLOAD_FAILURE = "amp_player_audio_download_failure";
    String AMP_PLAYER_VIDEO_DOWNLOAD_COMPLETE = "amp_player_video_download_complete";
    String AMP_PLAYER_AUDIO_DOWNLOAD_COMPLETE = "amp_player_audio_download_complete";
    String AMP_PLAYER_CANNOT_PARSE_VIDEO_DOWNLOAD = "amp_player_cannot_parse_video_download";
    String AMP_PLAYER_CANNOT_PARSE_AUDIO_DOWNLOAD = "amp_player_cannot_parse_audio_download";
    String AMP_PLAYER_UPDATE_VIDEO_SOURCE = "amp_player_update_video_source";

    // Call queue step name
    String START_TRANSFERRING_CALL_QUEUE_CALL = "start_transferring_call_queue_call";

    // Skylib Step Names
    String FAILED_TO_GET_USER_AUTH_DATA = "failedToGetUserAuthData";
    String NOT_SUPPORTED_ARCH = "notSupportedArch";
    String SKYLIB_RESULT_CODE_ERROR = "skylibResultCodeError";
    String SKYLIB_RESULT_CODE_INCOMPLETE = "skylibResultCodeIncomplete";

    // Cortana step names
    String CORTANA_START = "cortanaStart";
    String CORTANA_ON_CREATE_VIEW = "cortanaOnCreateView";
    String CORTANA_VIEW_ON_RESUME = "cortanaViewOnResume";
    String CORTANA_INITIALIZE_START = "cortanaInitializeStart";
    String CORTANA_INITIALIZE_COMPLETED = "cortanaInitializeCompleted";
    String CORTANA_INITIALIZING = "cortanaInitializing";
    String CORTANA_SHOW_SUGGESTION = "cortanaShowSuggestion";
    String CORTANA_START_LISTENING = "cortanaStartListening";
    String CORTANA_START_PLAY_LISTENING_EARCON = "cortanaStartPlayListeningEarcon";
    String CORTANA_COMPLETE_PLAY_LISTENING_EARCON = "cortanaCompletePlayListeningEarcon";
    String CORTANA_ENTER_LISTENING = "cortanaEnterListening";
    String CORTANA_ENTER_THINKING = "cortanaEnterThinking";
    String CORTANA_ENTER_SPEAKING = "cortanaEnterSpeaking";
    String CORTANA_ENTER_IDLE = "cortanaEnterIdle";
    String CORTANA_PAUSED = "cortanaPaused";
    String CORTANA_SHUTTING_DOWN = "cortanaShuttingDown";
    String CORTANA_SPEECH_RECOGNITION_STARTED = "cortanaSpeechRecognitionStarted";
    String CORTANA_QUERY_RESULT_RECEIVED = "cortanaQueryResultReceived";
    String CORTANA_ADAPTIVE_CARD_SUBMIT = "cortanaAdaptiveCardSubmit";
    String CORTANA_ADAPTIVE_CARD_OPEN_URL = "cortanaAdaptiveCardOpenUrl";
    String CORTANA_ERROR_RECEIVED = "cortanaErrorReceived";
    String CORTANA_FRE_SHOW = "cortana_fre_show";
    String CORTANA_FRE_FINISHED = "cortana_fre_finished";
    String CORTANA_INITIALIZATION_TOKEN_REFRESH_START = "cortana_initialization_token_refresh_start";
    String CORTANA_INITIALIZATION_TOKEN_REFRESHED = "cortana_initialization_token_refreshed";
    String CORTANA_INITIALIZATION_RUNNING_WORK_EXISTS = "cortana_initialization_running_work_exists";
    String CORTANA_GET_NEW_RESOURCE_TOKEN_START = "cortana_get_new_resource_token_start";
    String CORTANA_GET_NEW_RESOURCE_TOKEN_END = "cortana_get_new_resource_token_end";
    String CORTANA_WARMING_UP_STAGE1 = "cortana_warming_up_stage1";
    String CORTANA_WARMING_UP_STAGE2 = "cortana_warming_up_stage2";
    String CORTANA_WARMING_UP_STAGE3 = "cortana_warming_up_stage3";
    String CORTANA_LISTENING_KWS = "cortana_listening_kws";

    // Proximity Sensor Service Events step names [Currently used by Kingston]
    String PROXIMITY_SENSOR_SERVICE_INITIALIZED = "proximitySensorServiceInitialized";
    String PROXIMITY_SENSOR_SERVICE_DEVICE_STAY_ACTIVE_BROADCAST_SENT = "proximitySensorServiceDeviceStayActiveBroadcastSent";
    String PROXIMITY_SENSOR_SERVICE_DEVICE_NEARBY = "proximitySensorServiceDeviceNearby";
    String PROXIMITY_SENSOR_SERVICE_DEVICE_AWAY = "proximitySensorServiceDeviceAway";

    // Devices Enrollment steps.
    String CHECK_SIGN_IN_READINESS = "checkSignInReadiness";
    String CHECK_AAD_TOKEN = "checkAadToken";


    // Kingston notifications module step names
    String NOTIFIED_RECEIVER = "notifiedReceiver";
    String CLUBBING_CHECK_DONE = "clubbingCheckDone";
    String DATA_FETCHED = "dataFetched";
    String VIEW_UPDATED = "viewUpdated";

    String SDK_UNRECOGNIZED = "sdk_unrecognized";
    String SDK_INVALID_URL = "sdk_invalid_url";

    //Room remote
    String ROOM_REMOTE_CLIENT_START_REQUEST_LOCATION_PERMISSION = "room_remote_client_start_request_location_permission";
    String ROOM_REMOTE_CLIENT_RUN_WITH_LOCATION_PERMISSION = "room_remote_client_run_with_location_permission";
    String ROOM_REMOTE_CLIENT_WAITING_FOR_BLUETOOTH_ON = "room_remote_client_waiting_for_bluetooth_on";
    String ROOM_REMOTE_CLIENT_RUN_WITH_BLUETOOTH_ON = "room_remote_client_run_with_bluetooth_on";
    String ROOM_REMOTE_CLIENT_CHECK_PROXIMITY = "room_remote_client_check_proximity";
    String ROOM_REMOTE_CLIENT_CANCEL_CHECK_PROXIMITY = "room_remote_client_cancel_check_proximity";
    String ROOM_REMOTE_CLIENT_CHECK_PROXIMITY_TIMEOUT = "room_remote_client_check_proximity_timeout";
    String ROOM_REMOTE_CLIENT_START_SETUP_SESSION = "room_remote_client_start_setup_session";
    String ROOM_REMOTE_CLIENT_ON_ENDPOINT_RECEIVED = "room_remote_client_on_endpoint_received";
    String ROOM_REMOTE_CLIENT_REUSE_EXISTING_SESSION = "room_remote_client_reuse_existing_session";
    String ROOM_REMOTE_CLIENT_END_SESSION = "room_remote_client_end_session";
    String ROOM_REMOTE_CLIENT_START_PAIR = "room_remote_client_start_pair";
    String ROOM_REMOTE_CLIENT_PAIR_SUCCESS = "room_remote_client_pair_success";
    String ROOM_REMOTE_CLIENT_PAIR_FAILURE = "room_remote_client_pair_failure";
    String ROOM_REMOTE_ROOM_ACCEPT_INCOMING_SESSION = "room_remote_room_accept_incoming_session";
    String ROOM_REMOTE_ROOM_ON_ENDPOINT_RECEIVED = "room_remote_room_on_endpoint_received";
    String ROOM_REMOTE_ROOM_RECEIVE_PAIRING_COMMAND = "room_remote_room_receive_pairing_command";
    String ROOM_REMOTE_ROOM_END_SESSION = "room_remote_room_end_session";
}

