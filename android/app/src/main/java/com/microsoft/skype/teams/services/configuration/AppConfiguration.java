/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.services.configuration;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

//import com.microsoft.skype.teams.views.activities.BaseActivity;
//import com.microsoft.teams.core.services.navigation.ITeamsNavigationService;

/** Class to decide functionality on basis of appMode, tenant settings, Logged in User settings. */
public interface AppConfiguration {

    boolean isShareMediaEnabled();

    boolean forceHideAllNotifications();

    boolean isClearAppDataEnabled();

    boolean isTeamsAndChatsSyncDisabled();

    boolean isCallBannerDisabled();

    int searchInitiateThreshold();

    boolean isSearchSettingsEnabled();

    boolean shouldSyncContacts();

    boolean syncVoicemailsOnAppLaunch();

    boolean shouldReportIssueUseBrbFeedback();

    boolean shouldTrackHardwareStateUpdates();

    boolean shouldShowMoreOptionsHamburger();

    boolean shouldShowPartnerSettings();

    boolean logDetailedPerfScenarios();

    boolean forceAllowCalling();

    boolean shouldAllowPhonebookSearch();

    boolean showGroupCallFullScreenNotificationOnLockScreen();

    boolean areAppShortcutsEnabled();

    boolean isCallMoreOptionsEnabledOnLockScreen();

    boolean isValidUrlToOpenInWebViewer(@NonNull Context context, @NonNull Uri uri, boolean isRequestFromWebView);

    boolean forceDisableEmojis();

    boolean updateMeetingsTabUsingTimer();

    boolean disableAndroidAutoLinkUrls();

    boolean isGlobalSearchFromNumericKeypadSupported();

    boolean isCompanionModeEnabled();

    boolean disableExternalApps();

    boolean isMeetingJoinByCodeAnonymousEnabled();

    // boolean isValidUrlToOpenInWebViewer(@NonNull Context context, @NonNull Uri uri, @ITeamsNavigationService.LinkNavigationSource String source);

    boolean disableSuggestAFeature();

    boolean shouldResetModeToNormal();

    boolean isFileDownloadSupported();

    boolean registerDreamingIntent();

    boolean mobileModulesEnabled();

    boolean relaunchAppOnLanguageChange();

    boolean isIpPhoneWithPhysicalKeypad();

    boolean ringingHasPrecedence();

    boolean enableAutoDial();

    boolean isShareOnCallRoasterEnabled();

    boolean disableActivityAnimations();

    boolean enableAutoScrollTopOnTabSelection();

    boolean isVCDevice();

    boolean collectCallFeedback();

    boolean collectAccountSignInInfo();

    boolean isNordenConsole();

    boolean chatNotificationEventsEnabled();

    boolean isMeetingDataSplitByDate();

    /**
     * Cheks if Better together settings reply on desktop is enabled or not
     *
     * @return true if option is enabled.
     */
    boolean isDesktopReplyBetterTogetherOptionEnabled();

    /**
     * Whether the speed dial buddy list (groupType = "Favorites") should be saved in the DB.
     *
     * <p>Today, we are simply discarding it. Making it configurable since we need it for Kingston.
     *
     * @return true if the speed dial buddy list should be saved.
     */
    boolean speedDialBuddyListEnabled();

    boolean allowOverrideTheme();

    boolean allowShareImage();

    int getDefaultAppTheme();

    /**
     * Determines whether to show network availability indicators.
     *
     * @return true/false
     */
    boolean showStateLayoutNetworkIndicatorsOnBaseShellActivity();

    /**
     * Gets whether video share is enabled in calls.
     *
     * @return True/false indicating whether video share is enabled in calls.
     */
    boolean allowVideoShareInCalls();

    /**
     * Gets the messages pageSize which is used while fetching messages of a conversation through
     * network call.
     *
     * @return int pageSize
     */
    int getMessagesPageSize();

    /**
     * Gets the initial messages pageSize which is used while fetching messages of a conversation
     * through network call during FRE.
     *
     * @return int pageSize
     */
    int getInitialMessagesPageSize();

    /**
     * Returns true if orgchart should be displayed in landscape view
     *
     * @return boolean whether to show orgchart in tablet view or not
     */
    boolean useTabletLayoutForOrgChart();

    /**
     * Returns true to hide search bar from RN View
     *
     * @return boolean whether to show search bar in orgchart or not
     */
    boolean hideSearchBarFromOrgChart();

    /**
     * Determines whether to show data and storage settings.
     *
     * @return true/false
     */
    boolean showDataAndStorageSettings();

    /**
     * Determines whether to show "Sign up for free" button in sign in page.
     *
     * @return true/false
     */
    boolean shouldShowSignUpButton();

    /**
     * Determines whether to enable CP enrollment enhancements or not.
     *
     * @return  Flag indicating whether to enable CP enrollment enhancements
     */
    boolean shouldEnableEnhancedEnrollment();

    /**
     * Determines whether to show "Learn more" link in sign in page.
     *
     * @return true/false
     */
    boolean shouldShowLearnMoreLink();

    /**
     * Determines whether to show context menu for file blocks.
     *
     * @return true/false
     */
    boolean showContextMenuForFileBlock();

    /**
     * Determines whether to show option menu items in file viewer.
     *
     * @return true/false
     */
    boolean showOptionsMenuInFileViewer();

    /**
     * Get whether smart reply should show in chat fragment.
     *
     * @return True means should show in chat fragment which is the normal way in Android mobile;
     *     False means should show in chat container fragment which is the way in Kingston and
     *     ipPhone.
     */
    boolean shouldSmartReplyShowInChat();

    boolean showProfileSettings();

    boolean showHelpAndFeedBackSettings();

    boolean enableViewProfilePicture();

    boolean isExperimentalRnSdkAllowed();

    boolean enableConsumerTenant();

    boolean shouldShowLoadingFeedbackToast();

    boolean isCreateChannelMeetingEnabled();

    boolean shouldAutoDismissKeyBoardOnTouchOutside();

    long maxAlertSyncTime();

    boolean shouldShowHelp();

    boolean shouldShowWhatsNew();

    /**
     * Whether or not to display the contact card hero action items
     */
    boolean shouldDisplayContactCardHeroActionItems();

    /**
     * Whether or not to display the RealWear Action Items
     */
    boolean shouldDisplayRealWearActionItems();

    /**
     * Whether or not to display quick actions in notification
     */
    boolean shouldAllowQuickActionsInNotification();

    /**
     * Whether or not to hide the fabs
     */
    boolean shouldHideFabs();

    /**
     * Whether or not to use the local content share for video
     */
    boolean showVideoAsLocalContentShare();

    /**
     * Whether or not to default to video calls
     */
    boolean shouldAlwaysDefaultToVideoCall();

    /**
     * Whether or not to default enable mic while joining meetings/calls
     */
    boolean shouldAlwaysEnableMicWhileJoiningCall();

    /**
     * Whether or not to disable meeting item click to prevent meeting details screen to be shown
     */
    boolean shouldDisableMeetingItemClick();

    /**
     * Whether or not to automatically start a call or chat on list item click
     */
    boolean shouldAutomaticallyLaunchCallChatOnFirstClick();

    /**
     * Whether or not the incall call bar should include the duration of the call/meeting
     */
    boolean shouldCallBarIncludeDuration();

    /**
     * Whether or not to launch the keyboard automatically when searching users
     */
    boolean shouldLaunchKeyboardAutomaticallyWhenSearchingUsers();

    boolean shouldUseVoiceCommandFriendlyStrings();

    boolean shouldAllowOnlySingleMemberAdditionToGroupChat();

    boolean shouldShowHeaderOptionsInCallRoster();

    boolean shouldHideActivityOptionsMenu();

    boolean showSfbUnavailablePopUp();

    boolean shouldShowParticipantOverflowCountInCalls();

    boolean showAudioCallUFD();

    boolean useQueueForAriaEvents();

    boolean enableRedialWhenNoOneAnswered();

    /**
     * Whether or not show the options menu in contactCard page
     */
    boolean disableContactCardOptions();

    /**
     * Whether or not to show profile button in the call search result page
     */
    boolean shouldShowProfileButton();

    /**
     * Whether or not to add FLAG_KEEP_SCREEN_ON for BroadcastMeetingActivity
     */
    boolean shouldAddScreenOnForBroadcastMeeting();

    boolean allowIncomingCalls();

    boolean isDCFLoginFlowEnabled();

    /**
     * Should force sync services on main activity
     *
     * @return True for ipphones and other devices except mobile as Teams i shown 24*7,  so sync services dont get called as often as mobile.
     */
    boolean forceSyncServicesOnDevices();

    // int getPreferredScreenOrientationMode(@NonNull BaseActivity activity);

    /**
     * Whether need to disable local notification.
     */
    boolean needDisableLocalNotification();

    /**
     * Whether need to filter out old chat notifications
     */
    boolean needFilterOutOldChatNotifications();

    /**
     * Whether need to filter out self-sent message notifications.
     */
    boolean needShowSelfSentMessageInChatNotifications();

    /**
     * On Kingston if user explicitly select chat during meeting/call, we are going to show the chat even if it's empty.
     */
    boolean allowShowEmptyChatInList();

    /**
     * Whether we want to do BLE scanning to discover nearby rooms for companion mode.
     */
    boolean allowNearbyRoomDiscoveryForCompanionMode();

    /**
     * If Cortana supports the current device
     */
    boolean isCortanaSupportedDevice();

    /**
     * If safety first notice could be shown for the current device.
     */
    boolean allowShowCortanaSafetyFirstNotice();

    /**
     * If Cortana search bar entry enabled
     */
    boolean isCortanaSearchBarEntryEnabled();

    /**
     * Check if it's allowed to continuously sync Meetings in background or not.
     * @return true if continuous syncing of Meetings is allowed.
     */
    boolean isBackgroundMeetingSyncEnabled();

    /**
     * Check if it's allowed to continuously sync Voicemail in background or not.
     * @return true if continuous syncing of Voicemail is allowed.
     */
    boolean isBackgroundVoiceMailSyncEnabled();

    /**
     * Check is Group call is allowed or not.
     * @return true if Group call is allowed.
     */
    boolean isGroupCallEnabled();

    /**
     * Check if it's allowed to show Call Queue settings or not.
     * @return true if it's allowed to show Call Queue settings.
     */
    boolean isCallQueueSettingsEnabled();

    /**
     * Check if Proximity Join is enabled or not.
     * @return true if Proximity Join is enabled.
     */
    boolean isProximityJoinEnabled();

    /**
     * Check if Call Parking is enabled or not.
     * @return true if Call Parking is enabled.
     */
    boolean isCallParkEnabled();

    /**
     * Check if Hotdesking is enabled or not.
     * @return true if Hotdesking is enabled.
     */
    boolean isHotDeskingEnabled();

    /**
     * Check if displaying Status bar is allowed or not.
     * @return true if Status bar should be displayed.
     */
    boolean shouldDisplayAppStatusBar();

    /**
     * Check if showing current time on some specific pages.
     * @return true if current time should be displayed.
     */
    boolean shouldShowCurrentTimeInActivities();

    /**
     * For non-touch device, user can press central key on the
     * navigation button (that has 4 navigation keys and 1 central key)
     * that will serve as 'OK' key on the device to sign-in.
     *
     * This function will Check if sign-in using above specified "OK" hard-key allowed or not.
     * @return true if it is allowed to sign-in using "OK" hard-key.
     */
    boolean isSignInUsingOkHardKeyAllowed();

    boolean isProximityJoinToggleEnabled();

    /**
     * Check if showing a toast telling user to wait before feedback page is opened.
     * @return true if a toast should show.
     */
    boolean shouldShowFeedbackToast();

    /**
     * Check if showing a close button for the RSVP dialog.
     * @return true if a close button should show.
     */
    boolean shouldShowCloseButtonForRsvpDialog();

    /**
     * Check if showing Multipane view is allowed or not for Voicemail tab.
     * @return true if it's allowed.
     */
    boolean isMultipaneViewEnabledForVoicemail();

    /**
     * Should show RateView after call end.
     * @return true should show RateView.
     */
    boolean shouldShowRateView();

    /**
     * Whether or not 1DS SDK should be used for logging telemetry data to Aria
     * @return true if 1DS SDK should be used
     */
    boolean shouldUseOneDSSDKForTelemetryLogging();

    /**
     * Check if the item of the notifications settings is supported. The input parameter is type @NotificationsSettingsItemTypes
     * @return true if it's supported.
     */
    boolean isNotificationsSettingsItemSupported(int notificationsSettingsItemType);

    /**
     * Whether or not default settings should be used for pre-join.
     * @return true if use default pre-join settings, otherwise false.
     */
    boolean shouldUseDefaultPreJoinSettings();

    /**
     * Checks whether meeting calls can be put on hold.
     */
    boolean allowMeetingsHold();

    /**
     * Checks whether allow appbar and toolbar request focus.
     */
    boolean shouldAllowAppBarAndToolBarRequestFocus();

    /**
     * Checks whether call audio and video is turned off.
     */
    boolean turnOffCallAudioVideo();

    /**
     * Checks whether or not to show meeting reminder notification actions.
     */
    boolean shouldShowMeetingReminderNotificationActions();

    /**
     * The AppCenterUpdateTrack
     */
    int getAppCenterUpdateTrack();

    /**
     * Checks whether the app needs to fetch additional license details for the user.
     */
    boolean fetchDeviceAccountLicenseDetails();
}
