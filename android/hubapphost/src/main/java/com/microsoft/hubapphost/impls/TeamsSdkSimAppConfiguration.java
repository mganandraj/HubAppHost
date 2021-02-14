package com.microsoft.hubapphost.impls;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.microsoft.teams.services.configuration.AppConfiguration;

import javax.inject.Inject;

public class TeamsSdkSimAppConfiguration implements AppConfiguration {

    @Inject public TeamsSdkSimAppConfiguration() {}

    @Override
    public boolean isShareMediaEnabled() {
        return false;
    }

    @Override
    public boolean forceHideAllNotifications() {
        return false;
    }

    @Override
    public boolean isClearAppDataEnabled() {
        return false;
    }

    @Override
    public boolean isTeamsAndChatsSyncDisabled() {
        return false;
    }

    @Override
    public boolean isCallBannerDisabled() {
        return false;
    }

    @Override
    public int searchInitiateThreshold() {
        return 0;
    }

    @Override
    public boolean isSearchSettingsEnabled() {
        return false;
    }

    @Override
    public boolean shouldSyncContacts() {
        return false;
    }

    @Override
    public boolean syncVoicemailsOnAppLaunch() {
        return false;
    }

    @Override
    public boolean shouldReportIssueUseBrbFeedback() {
        return false;
    }

    @Override
    public boolean shouldTrackHardwareStateUpdates() {
        return false;
    }

    @Override
    public boolean shouldShowMoreOptionsHamburger() {
        return false;
    }

    @Override
    public boolean shouldShowPartnerSettings() {
        return false;
    }

    @Override
    public boolean logDetailedPerfScenarios() {
        return false;
    }

    @Override
    public boolean forceAllowCalling() {
        return false;
    }

    @Override
    public boolean shouldAllowPhonebookSearch() {
        return false;
    }

    @Override
    public boolean showGroupCallFullScreenNotificationOnLockScreen() {
        return false;
    }

    @Override
    public boolean areAppShortcutsEnabled() {
        return false;
    }

    @Override
    public boolean isCallMoreOptionsEnabledOnLockScreen() {
        return false;
    }

    @Override
    public boolean isValidUrlToOpenInWebViewer(@NonNull Context context, @NonNull Uri uri, boolean isRequestFromWebView) {
        return false;
    }

    @Override
    public boolean forceDisableEmojis() {
        return false;
    }

    @Override
    public boolean updateMeetingsTabUsingTimer() {
        return false;
    }

    @Override
    public boolean disableAndroidAutoLinkUrls() {
        return false;
    }

    @Override
    public boolean isGlobalSearchFromNumericKeypadSupported() {
        return false;
    }

    @Override
    public boolean isCompanionModeEnabled() {
        return false;
    }

    @Override
    public boolean disableExternalApps() {
        return false;
    }

    @Override
    public boolean isMeetingJoinByCodeAnonymousEnabled() {
        return false;
    }

    @Override
    public boolean disableSuggestAFeature() {
        return false;
    }

    @Override
    public boolean shouldResetModeToNormal() {
        return false;
    }

    @Override
    public boolean isFileDownloadSupported() {
        return false;
    }

    @Override
    public boolean registerDreamingIntent() {
        return false;
    }

    @Override
    public boolean mobileModulesEnabled() {
        return false;
    }

    @Override
    public boolean relaunchAppOnLanguageChange() {
        return false;
    }

    @Override
    public boolean isIpPhoneWithPhysicalKeypad() {
        return false;
    }

    @Override
    public boolean ringingHasPrecedence() {
        return false;
    }

    @Override
    public boolean enableAutoDial() {
        return false;
    }

    @Override
    public boolean isShareOnCallRoasterEnabled() {
        return false;
    }

    @Override
    public boolean disableActivityAnimations() {
        return false;
    }

    @Override
    public boolean enableAutoScrollTopOnTabSelection() {
        return false;
    }

    @Override
    public boolean isVCDevice() {
        return false;
    }

    @Override
    public boolean collectCallFeedback() {
        return false;
    }

    @Override
    public boolean collectAccountSignInInfo() {
        return false;
    }

    @Override
    public boolean isNordenConsole() {
        return false;
    }

    @Override
    public boolean chatNotificationEventsEnabled() {
        return false;
    }

    @Override
    public boolean isMeetingDataSplitByDate() {
        return false;
    }

    @Override
    public boolean isDesktopReplyBetterTogetherOptionEnabled() {
        return false;
    }

    @Override
    public boolean speedDialBuddyListEnabled() {
        return false;
    }

    @Override
    public boolean allowOverrideTheme() {
        return false;
    }

    @Override
    public boolean allowShareImage() {
        return false;
    }

    @Override
    public int getDefaultAppTheme() {
        return 0;
    }

    @Override
    public boolean showStateLayoutNetworkIndicatorsOnBaseShellActivity() {
        return false;
    }

    @Override
    public boolean allowVideoShareInCalls() {
        return false;
    }

    @Override
    public int getMessagesPageSize() {
        return 0;
    }

    @Override
    public int getInitialMessagesPageSize() {
        return 0;
    }

    @Override
    public boolean useTabletLayoutForOrgChart() {
        return false;
    }

    @Override
    public boolean hideSearchBarFromOrgChart() {
        return false;
    }

    @Override
    public boolean showDataAndStorageSettings() {
        return false;
    }

    @Override
    public boolean shouldShowSignUpButton() {
        return false;
    }

    @Override
    public boolean shouldEnableEnhancedEnrollment() {
        return false;
    }

    @Override
    public boolean shouldShowLearnMoreLink() {
        return false;
    }

    @Override
    public boolean showContextMenuForFileBlock() {
        return false;
    }

    @Override
    public boolean showOptionsMenuInFileViewer() {
        return false;
    }

    @Override
    public boolean shouldSmartReplyShowInChat() {
        return false;
    }

    @Override
    public boolean showProfileSettings() {
        return false;
    }

    @Override
    public boolean showHelpAndFeedBackSettings() {
        return false;
    }

    @Override
    public boolean enableViewProfilePicture() {
        return false;
    }

    @Override
    public boolean isExperimentalRnSdkAllowed() {
        return false;
    }

    @Override
    public boolean enableConsumerTenant() {
        return false;
    }

    @Override
    public boolean shouldShowLoadingFeedbackToast() {
        return false;
    }

    @Override
    public boolean isCreateChannelMeetingEnabled() {
        return false;
    }

    @Override
    public boolean shouldAutoDismissKeyBoardOnTouchOutside() {
        return false;
    }

    @Override
    public long maxAlertSyncTime() {
        return 0;
    }

    @Override
    public boolean shouldShowHelp() {
        return false;
    }

    @Override
    public boolean shouldShowWhatsNew() {
        return false;
    }

    @Override
    public boolean shouldDisplayContactCardHeroActionItems() {
        return false;
    }

    @Override
    public boolean shouldDisplayRealWearActionItems() {
        return false;
    }

    @Override
    public boolean shouldAllowQuickActionsInNotification() {
        return false;
    }

    @Override
    public boolean shouldHideFabs() {
        return false;
    }

    @Override
    public boolean showVideoAsLocalContentShare() {
        return false;
    }

    @Override
    public boolean shouldAlwaysDefaultToVideoCall() {
        return false;
    }

    @Override
    public boolean shouldAlwaysEnableMicWhileJoiningCall() {
        return false;
    }

    @Override
    public boolean shouldDisableMeetingItemClick() {
        return false;
    }

    @Override
    public boolean shouldAutomaticallyLaunchCallChatOnFirstClick() {
        return false;
    }

    @Override
    public boolean shouldCallBarIncludeDuration() {
        return false;
    }

    @Override
    public boolean shouldLaunchKeyboardAutomaticallyWhenSearchingUsers() {
        return false;
    }

    @Override
    public boolean shouldUseVoiceCommandFriendlyStrings() {
        return false;
    }

    @Override
    public boolean shouldAllowOnlySingleMemberAdditionToGroupChat() {
        return false;
    }

    @Override
    public boolean shouldShowHeaderOptionsInCallRoster() {
        return false;
    }

    @Override
    public boolean shouldHideActivityOptionsMenu() {
        return false;
    }

    @Override
    public boolean showSfbUnavailablePopUp() {
        return false;
    }

    @Override
    public boolean shouldShowParticipantOverflowCountInCalls() {
        return false;
    }

    @Override
    public boolean showAudioCallUFD() {
        return false;
    }

    @Override
    public boolean useQueueForAriaEvents() {
        return false;
    }

    @Override
    public boolean enableRedialWhenNoOneAnswered() {
        return false;
    }

    @Override
    public boolean disableContactCardOptions() {
        return false;
    }

    @Override
    public boolean shouldShowProfileButton() {
        return false;
    }

    @Override
    public boolean shouldAddScreenOnForBroadcastMeeting() {
        return false;
    }

    @Override
    public boolean allowIncomingCalls() {
        return false;
    }

    @Override
    public boolean isDCFLoginFlowEnabled() {
        return false;
    }

    @Override
    public boolean forceSyncServicesOnDevices() {
        return false;
    }

    @Override
    public boolean needDisableLocalNotification() {
        return false;
    }

    @Override
    public boolean needFilterOutOldChatNotifications() {
        return false;
    }

    @Override
    public boolean needShowSelfSentMessageInChatNotifications() {
        return false;
    }

    @Override
    public boolean allowShowEmptyChatInList() {
        return false;
    }

    @Override
    public boolean allowNearbyRoomDiscoveryForCompanionMode() {
        return false;
    }

    @Override
    public boolean isCortanaSupportedDevice() {
        return false;
    }

    @Override
    public boolean allowShowCortanaSafetyFirstNotice() {
        return false;
    }

    @Override
    public boolean isCortanaSearchBarEntryEnabled() {
        return false;
    }

    @Override
    public boolean isBackgroundMeetingSyncEnabled() {
        return false;
    }

    @Override
    public boolean isBackgroundVoiceMailSyncEnabled() {
        return false;
    }

    @Override
    public boolean isGroupCallEnabled() {
        return false;
    }

    @Override
    public boolean isCallQueueSettingsEnabled() {
        return false;
    }

    @Override
    public boolean isProximityJoinEnabled() {
        return false;
    }

    @Override
    public boolean isCallParkEnabled() {
        return false;
    }

    @Override
    public boolean isHotDeskingEnabled() {
        return false;
    }

    @Override
    public boolean shouldDisplayAppStatusBar() {
        return false;
    }

    @Override
    public boolean shouldShowCurrentTimeInActivities() {
        return false;
    }

    @Override
    public boolean isSignInUsingOkHardKeyAllowed() {
        return false;
    }

    @Override
    public boolean isProximityJoinToggleEnabled() {
        return false;
    }

    @Override
    public boolean shouldShowFeedbackToast() {
        return false;
    }

    @Override
    public boolean shouldShowCloseButtonForRsvpDialog() {
        return false;
    }

    @Override
    public boolean isMultipaneViewEnabledForVoicemail() {
        return false;
    }

    @Override
    public boolean shouldShowRateView() {
        return false;
    }

    @Override
    public boolean shouldUseOneDSSDKForTelemetryLogging() {
        return false;
    }

    @Override
    public boolean isNotificationsSettingsItemSupported(int notificationsSettingsItemType) {
        return false;
    }

    @Override
    public boolean shouldUseDefaultPreJoinSettings() {
        return false;
    }

    @Override
    public boolean allowMeetingsHold() {
        return false;
    }

    @Override
    public boolean shouldAllowAppBarAndToolBarRequestFocus() {
        return false;
    }

    @Override
    public boolean turnOffCallAudioVideo() {
        return false;
    }

    @Override
    public boolean shouldShowMeetingReminderNotificationActions() {
        return false;
    }

    @Override
    public int getAppCenterUpdateTrack() {
        return 0;
    }

    @Override
    public boolean fetchDeviceAccountLicenseDetails() {
        return false;
    }
}
