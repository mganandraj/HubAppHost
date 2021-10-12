/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Manages experimentation within the project.
 */
public interface IExperimentationManager {
//
//    cannot find symbol method getEcsSettingAsStringArray(String,<null>)
//    cannot find symbol method isStepTelemetryEnabled()
//    cannot find symbol method getRingInfo()
//    cannot find symbol method shouldLogExperimentIds()
//    cannot find symbol method getAppInfoExperimentationIds()
//    cannot find symbol method getAppInfoExperimentationIds()


//    String EVENT_TOU_UPDATED = "ExperimentationManager.Event.TOU.Updated";
//    String EVENT_APP_NEEDS_UPDATE = "ExperimentationManager.Event.App.NeedsUpdate";
//    String TEAM_NAME = "MicrosoftTeamsClientAndroid";
//
//    int ECS_DEFAULT_PENDING_MESSAGE_STALE_TIME_HRS = 24;
//
//    boolean getEcsSettingAsBoolean(@NonNull String teamName, @NonNull String experimentName, boolean defaultValue);
//
//    boolean getEcsSettingAsBoolean(String configName, boolean defaultValue);
//
//    int getEcsSettingAsInt(@NonNull String teamName, @NonNull String experimentName, int defaultValue);
//
//    int getEcsSettingAsInt(String configName, int defaultValue);
//
//    double getEcsSettingAsDouble(@NonNull String teamName, @NonNull String experimentName, double defaultValue);
//
//    String getEcsSettingAsString(String teamName, String configName, String defaultValue);
//
//    String getEcsSettingAsString(String configName, String defaultValue);
//
//    String getNearbyRoomsAppUrl();
//
//    String getNearbyRoomsAppId();
//
    @Nullable
    String[] getEcsSettingAsStringArray(@NonNull String teamName, @NonNull String experimentName, @Nullable String[] defaultValue);

    @Nullable
    String[] getEcsSettingAsStringArray(String configName, String[] defaultValue);
//
//    @Nullable
//    int[] getEcsSettingAsIntArray(@NonNull String experimentName, int[] defaultValue);
//
//    /**
//     * Returns the userObjectId which this instance is associated with.
//     */
//    @Nullable
//    String getUserObjectId();
//
//    boolean isTimeZoneEnabled();
//
//    boolean isTimeZonePersistent();
//
//    boolean isMeetingPresentationTimerEnabled();
//
//    boolean isReactionsMeetingOptionsPolicyEnabled();
//
//    boolean isReactionsEnabled();
//
//    boolean isOverflowReactionsEnabled();
//
//    boolean isSchedulerEnabled();
//
//    boolean isAutoAnswerSettingEnabled();
//
//    boolean isBYOMAutoAcceptEnabledOnNorden();
//
//    boolean isOneToOneCallEscalationEnabled();
//
//    boolean isVideoEnabled();
//
//    boolean isChatCallingEnabled();
//
//    boolean enableAnonymousChats();
//
//    boolean isAppProtectionCapabilityEnabled();
//
//    boolean isCallQueueSettingsEnabled();
//
//    String getCallQueueServiceAppId();
//
//    boolean isAsyncMediaEnabled();
//
//    boolean isReadReceiptsEnabled();
//
//    int getChatReadReceiptMaxParticipants();
//
//    int getMeetingReadReceiptMaxParticipants();
//
//    boolean isLongPollForIncomingCallsEnabled();
//
//    boolean isFilePreviewEnabled();
//
//    boolean isFileDownloadToInternalStorageEnabled();
//
//    boolean isDragAndDropEnabled();
//
//    boolean enableDrawerAnimation();
//
//    boolean enableChatListEmptyState();
//
//    boolean enableChatListEmptyStateCreateGroup();
//
//    boolean enableChatListEmptyStateInviteFriend();
//
//    boolean enableChatListEmptyStatePlanGroup();
//
//    boolean enableChatListEmptyStateSharePhoto();
//
//    boolean enableChatListEmptyStateShareTask();
//
//    boolean enableChatListEmptyStateShareLocation();
//
//    boolean enableChatListEmptyStateSyncContact();
//
//    boolean enableEmptyGroupCreation();
//
//    boolean enableNewGroupChatTextColor();
//
//    JSONObject getFilePreviewFallbackViewer();
//
//    long getBigCacheSizeInBytes();
//
//    long getSmallCacheSizeInBytes();
//
//    boolean shouldLoadMoreSectionAppsInMainActivity();
//
//    boolean shouldActiveJsHostFragmentHandleBackPressed();
//
//    long getSmallCacheFileSizeLimitInBytes();
//
//    long getBigCacheFileSizeLimitInBytes();
//
//    int getFileCacheCleanupTaskDelayTimeInHours();
//
//    boolean isEndOfMeetingNotificationEnabled();
//
//    boolean isLinkSharingEnabled();
//
//    boolean isBreakpadEnabled();
//
//    boolean isNewComposeToggleEnabled(boolean defaultValue);
//
//    boolean getNewComposeDefaultEnableState(boolean defaultValue);
//
//    boolean isAutomaticChatTranslationAlwaysEnabled();
//
//    boolean isAutomaticChatTranslationSuggestionEnabled();
//
//    boolean isAutomaticChannelTranslationAlwaysEnabled();
//
//    boolean isAutomaticChannelTranslationSuggestionEnabled();
//
//    boolean isOnDemandChatTranslationEnabled();
//
//    boolean isOnDemandChannelTranslationEnabled();
//
//    boolean isDumpFileUploadEnabledForAllCrashes();
//
//    boolean isCallVideoUFDEnabled();
//
//    boolean isNativePhonebookCallingEnabled();
//
//    boolean isNewMeetingCapabilitiesEnabled();
//
//    boolean isSetMediaPortRangesEnabled();
//
//    boolean isRestrictedMeetingJoinEnabled();
//
//    boolean isOngoingMeetingRestrictedChatMessagingEnabled();
//
//    boolean isRestrictedChatMessagingEnabled();
//
//    boolean isPrivateMeetingEnabled();
//
//    boolean enableBlockIncomingCallBasedOnPolicy();
//
//    boolean isCallRosterMeetingOptionsEnabled();
//
//    boolean isManageAudioVideoOptionEnabled();
//
//    boolean isCallRosterXlMeetingEnabled();
//
//    boolean isCallRosterV2Enabled();
//
//    boolean isFreemiumMeetingOptionsEnabled();
//
//    boolean isFreemiumMeetingOptionsCookieEnabled();
//
//    boolean isFreemiumAdHocMeetingsEnabled();
//
//    boolean isFreemiumAdHocMeetingsWhatsNewEnabled();
//
//    boolean isFreemiumAdHocMeetingsRenameEnabled();
//
//    boolean isFreemiumAdHocMeetingsDeleteEnabled();
//
//    boolean isFreemiumAdHocMeetingsListEnabled();
//
//    boolean isDeleteMeetingEnabled();
//
//    boolean isSideLoadMobileModulesEnabled();
//
//    boolean reactNativeModulesEnabled();
//
//    boolean nativeModulesEnabled();
//
//    boolean isAutoReconnectEnabled();
//
//    boolean isCallMeBackEnabled();
//
//    boolean isMeetingDialInEnabled();
//
//    boolean isMeetingRecommendationsEnabled();
//
//    boolean isMeetingRecommendationExchangeFilesEnabled();
//
//    int getMaxMeetingRecommendationFilesToShow();
//
//    boolean isDialInOnlyMeetingEnabled();
//
//    boolean isIPAudioVideoModeEnabled();
//
//    boolean isCallsTabEnabled();
//
//    boolean isPSTNCallingEnabled();
//
//    int getMeetingJoinReconnectingDialogPopOutTime();
//
//    boolean isPPTShareEnabled();
//
//    boolean isPPTPrivateViewingEnabled();
//
//    boolean isBroadcastAttendeeExperienceEnabled();
//
//    boolean isLiveEventTelemetryCaptureEnabled();
//
//    boolean isAddARoomJoinExperienceEnabled();
//
//    boolean isNewAttendeeServiceURLEnabled();
//
//    boolean isMeetingsDialOutEnabled();
//
//    boolean isBurnNotificationEnabled();
//
//    boolean enableNotifyAlwaysByDefault();
//
//    boolean isCheckBusinessVoiceForPSTN();
//
//    boolean isVoiceMailEnabledInECS();
//
//    boolean isVoiceMailForAllEnabledInECS();
//
//    boolean isNativeCallsTabEnabled();
//
//    boolean enableCQFForSuccessfulCallsOnly();
//
//    boolean enableHandlePushWithExpiredToken();
//
//    boolean isCallingLegacyMediaPeerTypeEnabled();
//
//    boolean isCallForwardingSettingsEnabled();
//
//    boolean isEmailHrdCheckEnabled();
//
//    int tflUpsellPillarProp();
//
//    boolean enableTutoringTFLBanner();
//
//    boolean enableTFLBannerColor();
//
//    boolean enableTflUpsell();
//
//    boolean enableSuggestedContact();
//
//    boolean isAccountResolutionEnabled();
//
//    String getRecordingBotMri();
//
//    boolean isFederatedChatEnabled();
//
//    boolean isNativeFederatedChatEnabled();
//
//    boolean isNativeFederatedGroupChatEnabled();
//
//    boolean isSFBChatInterOpEnabled();
//
//    boolean supportCoExistenceModes();
//
//    boolean isPreferredClientPromptEnabled();
//
//    boolean isCrossTenantNotificationEnabled();
//
//    boolean isMultiAccountCallingEnabled();
//
//    boolean isMultipleNumberCallingEnabled();
//
//    boolean isAppRatingEnabled();
//
//    boolean isCompanionModeEnabled();
//
//    boolean isConsultTransferEnabled();
//
//    boolean isShareAnnotationEnabled();
//
//    boolean isIntentionalWhiteboardEnabled();
//
//    boolean isIntentionalWhiteboardEnabledForAnon();
//
//    boolean isIntentionalWhiteboardEnabledForFreemium();
//
//    boolean isInvisionWhiteboardEnabled();
//
//    boolean isInvisionWhiteboardEnabledForAnon();
//
//    boolean isInvisionWhiteboardEnabledForFreemium();
//
//    boolean isStartIntentionalWhiteboardEnabled();
//
//    boolean isFeedbackAnnotationEnabled();
//
//    boolean shouldFetchWhiteboardPolicy();
//
//    boolean shouldFetchWhiteboardPolicyForAnonymous();
//
//    boolean shouldFetchWhiteboardPolicyForFreemium();
//
//    boolean isBlueboardQSPEnabled();
//
//    boolean isChatNewMessageSlideInAnimationEnabled();
//
//    boolean isChatShowMoreEnabled();
//
//    int chatListRecentMaxItems();
//
//    long chatListRecentMaxTimeMs();
//
//    int getAppRatingDaysSinceInstalledThreshold();
//
//    int getAppRatingNumOfLaunchesThreshold();
//
//    int getAppRatingDaysSinceSkippedThreshold();
//
//    int getAppRatingNumOfLaunchesSinceSkippedThreshold();
//
//    int getAppRatingNumOfDetailPageVisitedThreshold();
//
//    int getAppRatingNumOfCoreTasksCompletedThreshold();
//
//    int getAppRatingPeriodForAppLaunchThreshold();
//
//    int getEarlyCancelledCallTime();
//
//    int getEarlyCancelledMeetingTime();
//
//    boolean isMuteChatConversationEnabled();
//
//    boolean isHideChatConversationEnabled();
//
//    boolean isTopicNameInNewChatEnabled();
//
    String getRingInfo();
//
//    String getVstsAuthToken();
//
//    String getAttendeeServiceAppId();
//
//    boolean shouldUseMTTokenForAttendeeService();
//
//    boolean isGuestSwitchingEnabled();
//
//    boolean isAdaptiveCardEnabled();
//
//    boolean isO365CardConversionEnabled();
//
//    boolean isTeamManagementEnabled();
//
//    int getChatTimeStampSessionMinutes();
//
//    boolean isChatSwitchOptimizationEnabled();
//
//    boolean enableFederatedGroupChatConsumption();
//
//    boolean isUnifiedPresenceEnabled();
//
//    boolean isContactGroupsEnabled();
//
//    boolean isContactGroupsV2Enabled();
//
//    boolean isMentionsInChatEnabled();
//
//    boolean isVideoMessageSendingEnabled();
//
//    boolean areVideoMessageCompressionSettingsEnabled();
//
//    boolean isRenewTeamsEnabled();
//
//    boolean isForceSyncIfNotificationTruncated();
//
//    boolean isLongPollV2Enabled();
//
//    boolean shouldRespectTenantSettingsForUPS();
//
//    boolean isPresenceEtagEnabled();
//
//    boolean isRemoteMuteEnabled();
//
//    boolean isGroupCallEnabled();
//
//    boolean isMultiCallEnabled();
//
//    boolean isForegroundCallNotificationProcessingEnabled();
//
//    boolean isTeamSensitivityLabelsEnabled();
//
//    boolean isDiscoverPrivateTeamsEnabled();
//
//    int getJoinChannelTimeLimit();
//
//    boolean isPinnedChannelsEnabled();
//
//    boolean isPinnedChannelsInChatListEnabled();
//
//    int getPinnedChannelsMaxLimit();
//
//    boolean isPinnedChatsEnabled();
//
//    int getPinnedChatsMaxLimit();
//
//    boolean persistSelectedTabEnabled();
//
//    boolean isOrgWideTeamsEnabled();
//
//    boolean isTeamDiscoverySettingEnabled();
//
//    boolean isTextToEmoticonEnabled(boolean defaultSetting);
//
//    long getTimeToRefreshUserAggregatedSettings();
//
//    boolean isDoubleTapToLikeEnabled();
//
//    boolean isQuotedChatRepliesEnabled();
//
//    boolean isQuotedChatRepliesAlternateNameEnabled();
//
//    boolean isMessageForwardingEnabled();
//
//    boolean isEditImageEnabled();
//
//    boolean isEditImageEnabledForChannel();
//
//    boolean isForwardImageEnabled();
//
//    boolean shouldUserSeeReadReceiptsPrivacyNotice();
//
//    boolean isLobbyJoinEnabled();
//
//    boolean isMeetingChatsMuteSettingsEnabled();
//
//    boolean isParticipantPinEnable();
//
//    boolean isRaiseHandsEnabled();
//
//    int raiseHandsMessageTimeoutSeconds();
//
//    int hardMuteMessageTimeoutSeconds();
//
//    boolean isCQFAutoDismissEnabled();
//
//    int getCQFAutoDismissTimeout();
//
//    int getCQFAutoDismissNoActionTimeout();
//
//    int capacityOfLargeMeeting();
//
//    boolean isDedupeParticipantListToListenersEnabled();
//
//    boolean isDelayedParticipantListUpdateEnabled();
//
//    long getRefreshParticipantListDelayInMillis();
//
//    boolean isMainStageContentSwitchEnabled();
//
//    boolean isMainStageAnimationEnabled();
//
//    boolean isVideoGridViewV2Enabled();
//
//    boolean isVideoOptimizationOnStageEnabled();
//
//    int getMainStageParticipantCount();
//
//    boolean isRememberMainStageTypeEnabled();
//
//    boolean isModernStageEnabled();
//
//    boolean isBreakoutRoomExperienceEnabled();
//
//    boolean isBreakoutRoomHideLeaveEnabled();
//
//    boolean isBreakoutBannerEnabled();
//
//    boolean isBreakoutMMBannerEnabled();
//
//    int getBreakoutRoomAlertDuration();
//
//    boolean isBreakoutAnnouncementDialogEnabled();
//
//    boolean isDockedCallControlsEnabled();
//
//    String getMobileCallControlButtonConfiguration();
//
//    String getTabletCallControlButtonConfiguration();
//
//    String getKingstonCallControlButtonConfiguration();
//
//    String getIpPhoneCallControlButtonConfiguration();
//
//    String getDuoCallControlButtonConfiguration();
//
//    String getDefaultMeetingChatMuteSetting();
//
//    boolean isShareGroupChatHistoryEnabled();
//
//    boolean isDbTransactionTraceEnabled();
//
//    boolean isContentOnlyModeEnabled();
//
//    boolean isSkyLibEventsDedupingEnabled();
//
//    boolean isSkyLibEventsWhiteListEnabled();
//
//    boolean isCreateCallHandlerInstanceOnLoginEnabled();
//
//    boolean isAutoPruneEnabled();
//
//    int getAutoPruneDays();
//
//    int getMeetingDetailsDaysToSync();
//
//    int getForceAutoPruneDays();
//
//    int getAutoPruneInterval();
//
//    int getAllowedNumberOfConcurrentCalls();
//
//    int getCFQPeerToPeerPercent();
//
//    int getCFQPstnPercent();
//
//    int getCFQGroupCallPercent();
//
//    int getCFQMinCallDuration();
//
//    int getCFQMinLiveEventDuration();
//
//    int getCFQLiveEventPercent();
//
//    int getStreamPlayerTokenReqTimeout();
//
//    String getAMPStyleSheetUrl();
//
//    String getAMPMinifiedJSUrl();
//
//    int getSuggestedUpdateReminderInterval();
//
//    int getEncouragedUpdateReminderInterval();
//
//    boolean isAssignmentsTabEnabled();
//
//    boolean isSkypeChatTokenRefreshSchedulerEnabled();
//
//    boolean isGuestMSAEnabled();
//
//    boolean isNutmixSignupEnabled();
//
//    boolean isNutmixO365GuestInviteDisabled();
//
//    boolean isGuestNonLicenseEnabled();
//
//    boolean isV2SISUEnabled();
//
//    boolean shouldAddConsumerTenant();
//
//    int getNOPAForMSAL();
//
//    int getPresenceFetchInterval();
//
//    boolean isTroubleShootOptionEnabled();
//
//    boolean isPresenceDebuggingOptionEnabled();
//
//    boolean isAudioOffHardwareEnabled();
//
//    int pendingMessageStaleThresholdInHours();
//
//    boolean idMaskCallerIdEnabled();
//
//    boolean enableAnonymousJoin();
//
//    boolean enableMutingUnmutingDelay();
//
//    boolean enableVideoOnOffDelay();
//
//    long getMuteActionDelayTimeoutLimit();
//
//    long getVideoActionDelayTimeoutLimit();
//
//    String getAnonymousHelpUri();
//
//    boolean sharePhotoMediaEnabled();
//
//    boolean shareFileEnabled();
//
//    boolean shareMediaEnabled();
//
//    boolean callTransferEnabled();
//
//    boolean cqfSlimcoreTriggerEnabled();
//
//    boolean webinarMeetingJoinEnabled();
//
//    boolean callDebugEnabled();
//
//    boolean enableTogetherView();
//
//    boolean enableLargeGridView();
//
//    String getTogetherModeBotId();
//
//    String getLargeGridModeBotId();
//
//    int getLargeGalleryModeMinVideoCountForEnabling();
//
//    int getTogetherModeMinParticipantCountForEnabling();
//
//    boolean musicOnHoldEnabled();
//
//    boolean musicOnHoldV2Enabled();
//
//    boolean readMigratedTenantSettings();
//
//    boolean isSdkAppEnabled(@NonNull String sdkAppFlightKey);
//
//    boolean enableBRB();
//
//    boolean shouldDisablePictureQualityOptimization();
//
//    boolean autoIdleEnabled();
//
//    boolean isCallLogContactTypeEnabled();
//
//    void checkForUpdates();
//
//    boolean shouldOverrideEnterButtonForPhysicalKeyboard();
//
//    boolean supportLargeTeams();
//
//    int getMaxTeamSizeForTeamChannelMentions();
//
//    String getThreadFirstPageRosterSize();
//
//    boolean isServiceStateManagerEnabled();
//
//    boolean isSettingEnabled(@NonNull String settingKey, boolean defaultValue);
//
//    boolean runNotificationAsync();
//
//    boolean supportTimeBasedRetention();
//
//    boolean isRetentionPruneForSharedChannelEnabled();
//
//    boolean isPersonalConsumerAnonymousJoinEnabled();
//
//    boolean enableBackgroundActivityDetection();
//
//    int getBackgroundInactiveCounterThreshold();
//
//    boolean enableBackgroundActivityDetectionShowBanner();
//
//    int getBackgroundPruneJobInterval();
//
//    boolean isBottomBarAnimationsDisabled();
//
//    boolean isBottomBarSixPlusLabelsEnabled();
//
//    boolean isChatMessageAnimationsDisabled();
//
//    boolean isCallingAnimationsDisabled();
//
//    boolean forceFullScreenCallNotification();
//
//    boolean enableAndroidQIncomingCallsByNotificationsOnly();
//
//    boolean enableUsingNewStartForegroundServiceApiForCallServices();
//
//    boolean isServiceBindingToStartForegroundServiceEnabled();
//
//    boolean isAutoBRBForLongRunningSkylibInitializationEnabled();
//
//    boolean enableManualDockScanning();
//
//    boolean enableGapDetectionBasedNotificationSync();
//
//    long getGapDetectionBasedNotificationThreshold();
//
//    boolean enableBackgroundNotificationSync();
//
//    boolean enableNotificationAlwaysReceiveForFreUsers();
//
//    int getNotificationMessagesProcessedMapLimitSize();
//
//    boolean enableDockCallingEvents();
//
//    boolean enableDockCortana();
//
//    boolean enableDockContextCheckTimer();
//
//    boolean enableMeetingNotificationEvents();
//
//    boolean isCQFEnabled();
//
//    boolean isLLTEnabled();
//
//    boolean isTenantedSignInEnabled();
//
//    boolean isSmartComposeEnabled();
//
//    boolean isSmartComposeEnabledInChannel();
//
//    boolean disableSmartComposeInPoorNetwork();
//
//    int getSmartComposeDebounceInterval();
//
//    int getSmartComposeHistoryMessageCount();
//
//    String getSmartComposeModelVersion();
//
//    String getAugLoopEndpoint();
//
//    boolean showSmartCompose();
//
//    boolean sendTimeToComposeLog();
//
//    boolean isSmartReplyEnabled();
//
//    String[] getSmartReplyEnabledLocaleList();
//
//    boolean isSmartReplyRenderAnimEnabled();
//
//    boolean isSmartReplyOcvFeedbackEnabled();
//
//    boolean shouldReadSmartReplyTenantControl();
//
//    boolean isSmartReplyLongPressToSendEnabled();
//
//    boolean isSmartReplyLongPressTipEnabled();
//
//    boolean isSmartReplyEmojiEnabled();
//
//    boolean shouldUseSmartReplyFilter();
//
//    /**
//     * Get status of SR in group chat
//     * 1 - smart reply in group chat is only enabled for the last message and has the same UX as in oneononechat.
//     * 2 - smart reply in group chat is enabled for last N messages, N is controlled by ECS smartReplyInGroupChatLastMessageCount,
//     * Default is 3. If SR is not for the last message, show SR with mention.
//     * 0 / other - smart reply is disabled for group chat.
//     */
//    int getSmartReplyInGroupChatStatus();
//
//    int getSmartReplyInGroupChatLastMessageCount();
//
//    boolean isSuggestedMeetingEnabled();
//
//    int suggestedMeetingMessageCheckCount();
//
//    boolean isUserCacheEnabled();
//
//    int getUserCacheSize();
//
//    boolean isThreadCacheEnabled();
//
//    int getThreadCacheSize();
//
//    boolean isThreadUserCacheEnabled();
//
//    int getThreadUserCacheSize();
//
//    boolean isThreadPropertyAttributeCacheEnabled();
//
//    int getThreadPropertyAttributeCacheSize();
//
//    boolean isChatConversationCacheEnabled();
//
//    int getChatConversationCacheSize();
//
//    boolean isConversationCacheEnabled();
//
//    int getConversationCacheSize();
//
//    boolean isTabExtensionOptionsEnabled();
//
//    boolean isCaptivePortalCheckEnabled();
//
//    boolean isStatusNoteV2Enabled();
//
//    boolean isStatusNoteV2BannerEnabled();
//
//    int getStatusNoteV2BannerLargeGroupChatSize();
//
//    boolean isStatusNoteV2MentionMessagesEnabled();
//
//    Integer getUserEntitlementsPollDuration();
//
//    Integer getChatEntitlementsPollDurationInHours();
//
//    boolean allowGetUserJoinedTeamsApi();
//
//    String getCaptivePortalCheckUrl();
//
    String[] getListOfRNAppForPreInit();
//
//    boolean isMeetingStartNotificationsEnabled();
//
//    int getPrejoinCoachmarkShowLimit();
//
//    boolean isPreJoinCoachmarkEnabled();
//
//    boolean isIncomingCallsOffEnabled();
//
//    boolean showUserLOBApps();
//
//    boolean hideDefaultApps();
//
//    boolean showDeviceManagementSettingOption();
//
//    boolean showUserInstalledStoreApps();
//
//    boolean showUserInstalledSideLoadedApps();
//
//    @Nullable
//    String[] getWhitelistedUserInstalledApps();
//
//    @Nullable
//    String[] getOnClickInitializeAppsForBottomBar();
//
//    String[] getWhitelistedAppsToOpenInTeams();
//
//    boolean isExtensibilityTabSSOEnabled();
//
//    boolean isExtensibilityBotSSOEnabled();
//
//    boolean isExtensibilityMeSSOEnabled();
//
//    @Nullable
//    String[] getProactiveMeSsoBotList();
//
//    boolean isBotFileAttachmentEnabled();
//
//    int getUserProfileExpiryDays();
//
//    int getUserProfileExpiryHours();
//
//    boolean isParticipantLongPressMenuToolTipEnabled();
//
//    int getParticipantLongPressMenuToolTipLimit();
//
//    String[] getIPPhoneModelsToEnableVideo();
//
//    String[] getIPPhoneModelsToDisableScreenShareConsumption();
//
//    String[] getIPPhoneModelsToDisableWhiteBoardConsumption();
//
//    String[] getIPPhoneModelsConferenceDevice();
//
//    boolean isPreemptiveCallStatusDetectionEnabled();
//
//    boolean isCommonAreaPhoneModeAllowed();
//
//    boolean hideSignOutForSharedAccounts();
//
//    String getMinAppSchemaVersionToSupportTabsOnMobile();
//
//    int fetchAppEntitlementsNetworkRetryCount();
//
//    boolean isMsalEnabled();
//
//    boolean shouldEnableMSALOptimizations();
//
//    boolean isFre4vEnabled();
//
//    boolean isLowDataUsageModeEnabled();
//
//    boolean isLowDataUsageModeEnabledByDefault();
//
//    int getLowDataUsageModeMaxCountBannerDisplayed();
//
//    boolean isMobilityPolicyEnabled();
//
//    boolean isSyncPresenceOnIncomingCallEnabled();
//
//    boolean isTrouterEnabled();
//
//    int getLowDataUsageModeBandwidthInKps();
//
//    boolean allowDeveloperPreview();
//
//    String[] getBotsNotAllowedInCallAsParticipant();
//
//    boolean isChatTabExtensionsEnabled();
//
//    boolean isPlaceSearchV2Enabled();
//
//    boolean enableEnhancedTelemetry();
//
//    boolean isFadingUserAvatarViewProfileDisabled();
//
//    boolean isRecyclerViewItemAnimationDisabled();
//
//    /**
//     * True if consumption of Fluid based semantic objects is enabled, so the objects can be shown in the chat streams.
//     */
//
//    boolean isFluidConsumptionEnabled();
//
//    boolean isFluidComposeEnabled(@NonNull @FluidComponentType String componentType);
//
//    boolean isFluidSizeLimitEnabled();
//
//    boolean isFluidEnabledForEduUser();
//
//    boolean isFluidForcedWritePermissionEnabled();
//
//    boolean isSupervisedChatEnabled();
//
//    int getFluidSizeLimitValueInBytes();
//
//    int getFluidLoadTimeOutValueInMs();
//
//    double getFluidMessageMaxHeightRatio();
//
//    /**
//     * Value passed to the "audience" property of the host context object passed to the Fluid loader.
//     * The default value is "Production", which points the framework to the production Fluid back end services.
//     */
//    String getFluidAudience();
//
//    boolean isSfcInteropEnabled();
//
//    long getSingleTenantSyncTime();
//
//    long getMultipleTenantSyncTime();
//
//    boolean useTokenIfValidWithoutExecutingAuthRequest();
//
//    @Nullable
//    String getInternalLifeFeedbackLink();
//
//    @Nullable
//    String getHelpArticlesLink();
//
//    boolean isOcvFeedbackEnabled();
//
//    boolean isOcvShakeAndSendEnabled();
//
//    boolean isRateUsEnabled();
//
//    boolean isReportAnIssueEnabled();
//
//    /**
//     * @deprecated use {@link com.microsoft.teams.core.services.configuration.IUserConfiguration#isLiveLocationEnabled() } instead.
//     */
//    @Deprecated
//    boolean isLiveLocationEnabled();
//
//    boolean isSharedPlacesEnabled();
//
//    @Nullable
//    String getLiveLocationServiceURLOverride();
//
//    boolean isLiveLocationBeaconTelemetryEnabled();
//
//    boolean isLiveLocationVerboseTelemetryEnabled();
//
//    boolean isLiveLocationPassiveTrackingEnabled();
//
//    boolean isLiveLocationIndefiniteSharingEnabled();
//
//    boolean isLiveLocationNearbyPlacesEnabled();
//
//    boolean isLiveLocationGeofenceFromPlacesEnabled();
//
//    boolean isLocationAppEnabled();
//
//    @Nullable
//    JSONObject getLiveLocationBatterySettings();
//
//    @Nullable
//    int[] getLiveLocationDurationOptions();
//
//    @Nullable
//    JSONObject getLiveLocationTelemetrySampling();
//
//    int getLiveLocationActiveSessionBannerFrequency();
//
//    int getLiveLocationSessionSyncFrequencyInMinutes();
//
//    int getGeofenceTriggerSyncDelayInSeconds();
//
//    boolean isStaticLocationCardV2Enabled();
//
//    int getSharedPlacesGroupLimit();
//
//    boolean isLiveLocationActivityFeedEnabled();
//
//    boolean isSmbNonVoiceFreEnabled();
//
//    boolean isSmbBusinessVoiceFreEnabled();
//
//    boolean isSmbBusinessVoiceWhatsNewEnabled();
//
//    boolean enableClientFileLogging();
//
//    /**
//     * Can delegates be managed from mobiles, ipphones.
//     * Operations include add delgates, remove delegates, update permissions of delegates.
//     *
//     * @return true if we wnat to enable managing delegates.
//     */
//    boolean isManageDelegatesEnabled();
//
//    /**
//     * Can current logged user created, viewed, edited his contacts.
//     *
//     * @return true if we want to enable contacts/peoples app.
//     */
//    boolean isPeoplesAppIpphoneEnabled();
//
//    /**
//     * Also ring on desktop or dont ring on desktop if kingston is connect to laptop via Better together
//     *
//     * @return true if Also ring desktop options is enabled via ECS
//     */
//    boolean alsoRingDesktopBTSettingEnabled();
//
//    /**
//     * Enable better together settings where user can opt in out of few BT actions
//     *
//     * @return true if BT enabled via ECS
//     */
//    boolean areBetterTogetherSettingsEnabled();
//
//    /**
//     * Should we use V2 apis to fetch contacts related info
//     *
//     * @return true if we want to use people v2 api end points.
//     */
//    boolean isPeopleAppV2ApiEnabled();
//
//    /**
//     * Show we show new home screen or not for ipphones
//     *
//     * @return true if we want to show new home screen
//     */
//    boolean isIpphoneHomeScreenEnabled();
//
//    /**
//     * When user is in meetings tab if we want to automatically scroll
//     * to next coming meeting by checking periodically for next coming meeting .
//     *
//     * @return true if we wnat to scroll automatically to next meeting.
//     */
//    boolean isScrollToUpcomingMeetingEnabled();
//
//    /**
//     * When user is in meetings tab if we want to automatically scroll
//     * to next coming meeting by checking periodically for next coming meeting.
//     * This function gives the frequency with which it should check for next coming meeting.
//     * This is supposed to be in minutes.
//     *
//     * @return period of time to check for next meeting in minutes.
//     */
//    int getScrollToUpcomingMeetingFrequencyInMinutes();
//
//    boolean shouldForceUpdateNonGlobalEndpoint();
//
//    @Deprecated
//    boolean isShareLocationInChatEnabled();
//
//    @Nullable
//    boolean[] getEcsSettingAsBooleanArray(@NonNull String teamName, @NonNull String experimentName, boolean[] defaultValue);
//
//    @Nullable
//    int[] getEcsSettingAsIntArray(@NonNull String teamName, @NonNull String experimentName, int[] defaultValue);
//
//    @Nullable
//    double[] getEcsSettingAsDoubleArray(@NonNull String teamName, @NonNull String experimentName, double[] defaultValue);
//
//    @Nullable
//    JSONObject getEcsSettingAsJSONObject(@NonNull String teamName, @NonNull String experimentName, JSONObject defaultValue);
//
//    int getNPSPanelActionDaysCount();
//
//    boolean isConfigBasedPeoplePicker();
//
//    boolean isChannelNotificationDlgUpdateEnabled();
//
//    boolean isShowChannelNotificationSettingPromptEnabled();
//
//    boolean isShowChannelNotificationIconEnabled();
//
//    boolean isGroupCalendarEnabled();
//
//    boolean enableHighResAvatar();
//
//    int getUserProfilesBatchSize();
//
//    boolean shouldClearIndices();
//
//    boolean isRefreshOnCreateOrUpdateMeetingsDisabled();
//
//    boolean isCreateEventEnabled();
//
//    boolean isEarlyRingingEnabled();
//
//    boolean isCortanaEnabled();
//
//    boolean isCortanaEnabledForEdu();
//
//    boolean isCortanaBackgroundTokenRefreshEnabled();
//
//    boolean isCortanaKWSEnabled();
//
//    boolean isCortanaInViewKWSConsentEnabled();
//
//    boolean isCortanaBeforeViewKWSConsentEnabled();
//
//    boolean isCortanaVoiceFontEnabled();
//
//    boolean isCortanaEarlyAdopters();
//
//    boolean isCortanaBluetoothProfileSwitchEnabled();
//
//    boolean isCortanaMeetingHandsFreeJoinEnabled();
//
//    boolean isCortanaI18NEnabled();
//
//    @Nullable
//    JSONObject getCortanaHelpContent();
//
//    @Nullable
//    JSONObject getCortanaTips();
//
//    @NonNull
//    List<Integer> getCortanaFreBannerDisplayTimeArray();
//
//    @NonNull
//    List<Integer> getOptInPromotionCoachMarkDisplayTimeArray();
//
//    @NonNull
//    String getCortanaWatchdogPolicy();
//
//    int getMaxIdleConnections();
//
//    int getKeepAliveDurationInMinutes();
//
//    boolean isRichTextBlockRecyclingEnabled();
//
//    int getDateWhenInstrumentationRequested();
//
//    boolean isForceUpdateEndpointEnabled();
//
//    boolean shouldSendTimezoneInClientInfo();
//
//    boolean isQuietHoursWrapUpNotificationEnabled();
//
//    boolean isNotificationsDisabledIndicatorEnabled();
//
//    boolean enableATPViaMT();
//
//    boolean isExtendBrbReportEnabled();
//
//    boolean isSearchFromMultipleParticipantsEnabled();
//
//    int getTopNCacheSize();
//
//    int getTopNCacheRefreshDuration();
//
//    int getSearchWarmUpIntervalInMinutes();
//
//    boolean enableFREOptimizations();
//
//    int getTargetingTagMaxMembersInTag();
//
//    boolean isTargetingSettingsFromUserAggregateSettingsEnabled();
//
//    int getTargetingTagMaxTagsInTeam();
//
//    boolean isComposeExtensionsEnabled();
//
//    boolean isMessageActionEnabled();
//
//    boolean isMeetingExtensibilityEnabled();
//
//    boolean isSupportedPlatformFlagForStaticTabsEnabled();
//
//    boolean isSupportedPlatformFlagForConfigurableTabsEnabled();
//
//    boolean isFileListCachingEnabled();
//
//    boolean isSpecialDocumentLibrariesEnabled();
//
//    boolean isSDLForNonEduTenantsEnabled();
//
//    boolean isGoogleAvailabilityDialogEnabled();
//
//    boolean areIpPhonePoliciesEnabled();
//
//    boolean isBranchSurvivabilityEnabled();
//
//    boolean isForceAutoPruneEnabledForOptimization();
//
//    boolean isPriorityMessagesEnabled();
//
//    boolean isPriorityMessagesOverrideEnabled();
//
//    boolean isShareLocationV2Enabled();
//
//    boolean isVoiceMessageEnabled();
//
//    boolean isShareLocationAmsUploadEnabled();
//
//    boolean isSafeLinkCheckEnabled();
//
//    boolean isGooglePlayServiceRegion();
//
//    boolean enableMultipleIncomingCallRinging();
//
//    boolean isBigSwitchEnabled();
//
//    boolean isServerMessageSearchEnabled();
//
//    boolean isServerPeopleSearchEnabled();
//
//    boolean isServerImplicitSearchEnabled();
//
//    boolean isSubstrateMessageSearchEnabled();
//
//    int getSubstrateMessageSearchPageSize();
//
//    boolean getPaginationMessageSubstrateSearchEnabled();
//
//    boolean isRichRecentSearchSuggestionEnabled();
//
//    boolean isAllTabInSearchEnabled();
//
//    boolean isLocalFileSearchEnabled();
//
//    boolean isNowInFeedsEnabled();
//
//    boolean isNowPriorityNotificationAppEnabled();
//
//    boolean isNowAnimationsEnabled();
//
//    boolean isFeedsGraphNotificationsEnabled();
//
//    boolean isPeopleSearchV3();
//
//    boolean isNewPeoplePickerEnabled();
//
//    int getPeoplePickerTopUsersCount();
//
//    int getPeoplePickerNamedGroupChatsCount();
//
//    int getPeoplePickerUnnamedGroupChatsCount();
//
//    int getPeoplePickerBotsCount();
//
//    int getPeoplePickerChannelsCount();
//
//    boolean isTopNCacheEnabled();
//
//    boolean isPruningPendingSyncEnabled();
//
//    boolean isEndJoinScenarioOnCallStatusEnabled();
//
//    boolean isResetAriaTransmitProfileInCallEnabled();
//
//    boolean isPendingMembersEnabled();
//
//    boolean isUseCountOfPendingMembersApiEnabled();
//
//    boolean isFloodgateEnabled();
//
//    String getNPSAudienceGroup();
//
//    int getNPSUserLoginDaysCount();
//
//    boolean isDailInEnable();
//
//    boolean isMeetingsCallMeEnabled();
//
//    boolean isOrgChartEnabled();
//
//    boolean isSMSChatEnabled();
//
//    boolean isSMSEntitlementCheckEnabled();
//
//    int getFileSearchPayloadLength();
//
//    boolean isJoinLinkInviteEnabled();
//
//    boolean isGroupInviteLinkEnabled();
//
//    boolean isGroupChatTwoWaySMSEnabled();
//
//    String[] twoWaySmsEnabledPrefixes();
//
//    String[] twoWaySmsExcludedPrefixes();
//
//    boolean isServiceInviteStringsEnabled();
//
//    boolean isGroupChatCreateSMSUsersEnabled();
//
//    boolean isOffNetworkInviteEnabled();
//
//    boolean isOffNetworkSingleUserInviteEnabled();
//
//    boolean isOffNetworkInviteRequestMessageIdSupported();
//
//    boolean isAddressBookSyncEnabled();
//
//    boolean isContactSyncDialogAndViewEnabled();
//
//    String chatCreationEmptyView();
//
//    boolean isPrivacyEnabled();
//
//    int getAddressBookUploadBatchCount();
//
//    boolean isPeoplePickerSectionHeadersEnabled();
//
//    boolean isPeoplePickerDeviceContactsEnabled();
//
//    boolean isPeoplePickerScdMatchEnabled();
//
//    boolean isPeoplePickerListCollapsible();
//
//    boolean hidePeoplePickerDeviceContactsHeader();
//
//    boolean isDeviceContactsInSearchEnabled();
//
//    boolean enableSearchContactNavigateToChat();
//
//    boolean enablePreSearchContactSyncEmptyState();
//
//    boolean enableProfileButtonOnSearchContacts();
//
//    int getMaxCompanyContactCountForAllTab();
//
//    boolean isPreSearchSuggestedContactsEnabled();
//
//    boolean isBlockContactEnabled();
//
//    boolean isTflPresenceEnabled();
//
//    boolean isAddMSAAliasEnabled();
//
//    boolean isQuietHoursEnabled();
//
//    boolean isPSTNBlockEnabled();
//
    boolean isFallbackLoaderInReactNativeEnabled();

    int getRetryCountForCodepushBundleDownload();

    int getCodePushUpdateCheckBackoffTimeInMinutes();
//
//    boolean isPreFetchResourceTokenInReactNativeEnabled();
//
//    boolean isReactNativeAppEnabled(@NonNull String appId);
//
//    String[] validMobileModules();
//
//    boolean isTeamMemberTagsEnabled();
//
//    boolean isGetTagCardsByTeamIdEnabled();
//
//    boolean isTagsTargetedChatEnabled();
//
//    boolean getScheduledTagsEnabled();
//
//    boolean areAnonymousUsersAllowedInMeeting();
//
//    boolean areAnonymousUsersAllowedToShareLocation();
//
//    boolean areAnonymousUsersAllowedToShareImages();
//
//    boolean isProximityJoinEnabled();
//
//    boolean isOngoingMeetingJoinAsDeepLinkEnabled();
//
//    boolean isCardTaskModuleEnabled();
//
//    boolean isQueryMessagingExtensionsEnabled();
//
//    boolean isPraiseOnlyFlyoutEnabled();
//
//    boolean isNotificationEncryptionEnabled();
//
//    boolean isMediaPathOptimizationEnabled();
//
//    boolean isE911SecurityDeskEnabled();
//
//    boolean isE911CallingPlanEnabled();
//
//    boolean isE911DirectRoutingEnabled();
//
//    boolean isTFLNewFreSyncContactsEnabled();
//
//    boolean isDontShowAgainContactsEnabled();
//
//    boolean isTFLGiphyPickerCoachMarkEnabled();
//
//    boolean isBookmarksEnabled(boolean defaultValue);
//
//    boolean isRedDotOnDashboardTabEnabled();
//
//    boolean isShowBoldInviteFriendsButtonEnabled();
//
//    boolean isBadgeCountServicePushNotificationEnabled();
//
//    boolean isBadgeCountServiceEnabled();
//
//    /**
//     * @return true if globalEndpointId is enabled. Else false.
//     * What is globalEndpointId?
//     * Use a single endpointId to register with EDF, CSA and other services that needs endpointId
//     * Without this we were using user-specific endpointId and that doesn't work with CSA
//     */
//    boolean isGlobalEndpointIdEnabled();
//
//    /*
//        returns 1 for Consumer Account and 0 for Enterprise Account
//     */
//    int getAccountTypeForBadgeCountService();
//
//    long getRegistrationExpirationTimeForBadgeCountService();
//
//    boolean isE911Enabled();
//
//    boolean isE911NCSEnabled();
//
//    boolean isE911LLDPInfoUpdateEnabled();
//
//    boolean isLocationBasedCallRoutingEnabled();
//
//    boolean isChannelsInChatListPilotEnabled();
//
//    String[] getNowWhiteListedAppIds();
//
//    boolean isStructuredMeetingForAnonymousUsersEnabled();
//
//    boolean isStructuredMeetingEnabled();
//
//    boolean isStructuredMeetingActionsEnabled();
//
//    boolean isAddRoomEnabled();
//
//    boolean isAddRoomEnabledForFreemium();
//
//    boolean isWhatsNewExperienceEnabled();
//
//    boolean isWhatsNewUnreadDotEnabled();
//
//    boolean isNewCallingUXEnabled();
//
//    boolean isNewCallingUX2Enabled();
//
//    boolean shouldEnableDatabaseInterceptor();
//
//    boolean shouldTriggerFullDbMigrationInsteadOfReset();
//
//    int thresholdQueryTimeBeforeLogging();
//
//    int thresholdResultsCountBeforeLogging();
//
//    boolean shouldResetInsteadOfMigration();
//
//    int getAutoPruneDaysToOptimizeDatabase();
//
//    boolean isRetentionHorizonSupportedThroughThreadOnly();
//
//    boolean isStartUpProfilingEnabled();
//
//    String getVersionWhereAppShouldBeReset();
//
//    String getVersionWhereSyncStateShouldBeRemoved();
//
//    boolean isAnnouncementIllustrationMessagesEnabled();
//
//    boolean isPrivateChannelEnabled();
//
//    boolean sharedChannelsApiProdEndpointEnabled();
//
    boolean isStepTelemetryEnabled();
//
//    boolean isSendIntentToCallEnabled();
//
//    boolean isDelegateCallingEnabled();
//
//    String getClosedCaptionsBotMri();
//
//    boolean isDataChannelEnabled();
//
//    boolean isOneToOnePstnCallEscalationEnabled();
//
//    boolean isPreJoinEnabled();
//
//    boolean isContinueCallOnPreJoinNavigatedAwayEnabled();
//
//    boolean isContinueCallOnPreCallNavigatedAwayEnabled();
//
//    boolean shouldCheckForValidOIDGuidInPreCall();
//
//    boolean isPrejoinCallEventsSubscribingEnabled();
//
//    int numberOfPrecallsAllowed();
//
//    boolean isMeetingRecordingStorageEnabled();
//
//    boolean isBroadcastPresenterExperienceEnabled();
//
//    boolean isGroupCallPickupEnabled();
//
//    boolean isEnableAudioBluetoothFilterHeadsetOnly();
//
//    boolean shouldIgnoreSCOError();
//
//    boolean shouldLogRowCountForAllTables();
//
//    boolean isHoloLensInteractionEnabled();
//
//    boolean isInAppStreamPlayerEnabled();
//
//    boolean isStartPlayInOneDriveForBusinessEnabled();
//
//    boolean isPlayingInAMSEnabled();
//
//    boolean isOnePlayerForMeetingRecordingEnabled();
//
//    int getAMSRecordingExpirationDuration();
//
//    String getAvatarResolution();
//
//    int getTargetingMaxTeamsInGetTagsByTeamIds();
//
//    int getLoadMoreReplyChainsPageSize();
//
//    int getFileListRefreshTime();
//
//    boolean isTeamsAMSDownloadEnabled();
//
//    long getTeamsOrderRefreshTime();
//
//    boolean isGalleryTabEnabled();
//
//    boolean isGalleryUploadImagesEnabled();
//
//    boolean isStatusNoteV2DismissalPersistenceEnabled();
//
//    boolean isMoreTabInMeetingsEnabled();
//
//    int getStatusNoteV2BannerMaxDismissals();
//
//    double getStatusNoteV2BannerDismissTimeForOneOnOneChat();
//
//    double getStatusNoteV2BannerDismissTimeForGroupChat();
//
//    boolean hideInterOpChatPresence();
//
//    boolean isOfficeLensEnabled();
//
//    boolean isMediaFromNativeKeyboardEnabled();
//
//    boolean meetingChatAddParticipantsEnabled();
//
//    boolean isDTMFOptionEnabled();
//
//    boolean isStartStopRecordingEnabled();
//
//    boolean isOneToOneRecordingEnabled();
//
//    boolean isComplianceBotHandlingEnabled();
//
//    boolean isCallParkEnabled();
//
//    boolean isScreenShareEnabled();
//
//    boolean isScreenShareImageOptimizationEnabled();
//
//    boolean isHandOffRequestEnabled();
//
//    boolean isCallHandOffEnabled();
//
//    int getLiveEventTickIntervalInSec();
//
//    boolean isDialInUFDEnabled();
//
//    boolean shareChannelOrOnedriveFileEnabled();
//
//    int getFromVersionForRemoveSyncStateMigration();
//
//    int getToVersionForRemoveSyncStateMigration();
//
//    boolean shouldClearMessageSyncState();
//
//    String getActiveCallServiceBaseUrl();
//
//    boolean getTargetingTagsOnStartUpEnabled();
//
//    boolean getTargetingTagCardEnabled();
//
//    boolean getAddMemberToTagMultipleCallsEnabled();
//
//    int getTargetingTenantSettingsCacheTime();
//
//    String getPSTNMaskingRegex();
//
//    String getTargetingBaseUrl();
//
//    int getMaxAndConditionsForQuery();
//
//    int getTargetingTagMaxTagNameLength();
//
//    int getTargetingMaxMembersAddedAtOnce();
//
//    boolean isNewCallMeBackUXEnabled();
//
//    boolean callDriveModeEnabled();
//
//    boolean pipEnabled();
//
//    boolean liveEventPipEnabled();
//
//    String[] supportedRecordingRegions();
//
//    String[] supportedRecordingOdbRegions();
//
//    String[] blacklistedHostsFromWebViewAccess();
//
//    boolean isLiveCaptionsEnabled();
//
//    boolean isCallLiveCaptionsEnabled();
//
//    int getCallControlsConfigurableDelayInSeconds();
//
//    boolean isWhatsNewNotificationEnabled();
//
//    boolean isUnifiedChatsViewEnabled();
//
//    boolean isNotificationBlockedDetectionEnabled();
//
//    boolean isCreatePrivateChannelEnabled();
//
//    boolean isExtensibilityAuthEnabled();
//
//    boolean enableDeeplinkToMobileModule();
//
//    boolean callRosterActiveSpeakerEnabled();
//
    String getReactNativeAppDeploymentKey(@NonNull String appId);
//
//    boolean isPhoneAuthEnabled();
//
//    boolean isPowerLiftEnabledPreLogin();
//
//    boolean enableConfRoomPopup();
//
//    boolean isMultiaccountEnabled();
//
//    boolean isPowerLiftEnabled();
//
//    boolean isReactNativeCallingSynchronizationEnabled();
//
//    long getBlockedContactsListFetchFrequency();
//
//    boolean isFLWPresenceEnabled();
//
//    boolean isFLWOffShiftPresenceDialogEnabled();
//
//    boolean isFLWOffShiftPresenceBlockingEnabled();
//
//    boolean isTeamsAMSUploadEnabled();
//
//    int messageDeliveryLatencySamplingPercentage();
//
//    boolean isWebTaskModuleEnabled();
//
//    boolean isImproveImageRenderingEnabled();
//
//    boolean shouldSafeLinkValidatePreviewUrl();
//
//    boolean shouldShowMoreTabCoachMark();
//
//    void requestForceUpdate(@NonNull Activity activity);
//
//    boolean shouldShowSearchIconForGroupCreation();
//
//    int getPeoplePickerFallbackTime();
//
//    boolean isAliasControlsEnabled();
//
//    boolean isDisplayNameOverrideEnabled();
//
//    boolean isDeviceContactTagEnabled();
//
//    boolean isDeviceContactCacheEnabled();
//
//    int getDeviceContactNegativeCacheSize();
//
//    int getMriDisplayNameCacheSize();
//
//    int getMriPhoneEmailCacheSize();
//
//    int getDeviceContactHashCacheSize();
//
//    boolean isPresenceUIEnabled();
//
//    boolean isEscalationUpdateEnabled();
//
//    boolean isEscalateToNewPersonEnabled();
//
//    boolean isGalleryTabInChatEnabled();
//
//    boolean isSharedLinksEnabled();
//
//    boolean isTasksTabInDashboardEnabled();
//
//    boolean isTasksInChatEnabled();
//
//    boolean isSyncMissingConsumerGroupEnabled();
//
//    boolean isInlineImageViewInChatEnabled();
//
//    boolean isNewImagePreviewEnabled();
//
//    boolean isNewImageRenderInChatEnabled();
//
//    boolean isMultiImageSelectInGalleryEnabled();
//
//    int getMaxGalleryImageShareCount();
//
//    boolean isEditProfileEnabled();
//
//    boolean isUpdateMSAAvatarEnabled();
//
//    boolean isDeleteMSAAvatarEnabled();
//
//    boolean isAddContactOnMessageSentEnabled();
//
//    boolean isActivityFeedEnabled();
//
//    boolean isContextualSearchEnabled();
//
//    String getPropertyToParseRetentionHorizon();
//
//    boolean isHotDeskingEnabled();
//
//    int hotDeskingDefaultTimeout();
//
//    boolean isMiniProfilesEnabled();
//
//    boolean enableInviteFree();
//
//    int maxQuarantineCounter();
//
//    boolean shouldMoveHttpCallsToNetworkThreadPool();
//
//    boolean alwaysHonorNetworkAvailableCheck();
//
//    boolean alwaysHonorPowerOptimizationCheck();
//
//    double getNetworkBandwidthSamplingIntervalFactor();
//
//    double getNetworkBandwidthThresholdFactor();
//
//    boolean shouldShowPoorConnectionBanner();
//
//    String[] getApiCallRegexToIgnoreForNwBandwidthSampling();
//
//    int[] getRangeValuesOfNwExceptionMonitor();
//
//    int getResetTimerDelay();
//
//    boolean shouldShowBannerForPoorNwDueToExceptions();
//
//    boolean shouldResetNwSamplerOnNwTypeChange();
//
//    boolean shouldShowConnectingNetworkBanner();
//
//    int getSocketTimeoutValueInSecs();
//
//    int getNumOfBucketsForSocketExcpMonitoring();
//
//    boolean shouldEnableAdaptiveSocketTimeout();
//
//    int getSocketTimeoutExcpThresholdValue();
//
//    boolean isLoadingProgressTimeOutEnabled();
//
//    boolean overrideThreadTenantId();
//
//    String getConsumerAuthSvcEnvironment();
//
//    boolean isDirectDial911Enabled();
//
//    boolean isPeoplePickerInviteFriendEnabled();
//
//    boolean isPerUserNotificationSettingEnabled();
//
//    boolean isBackgroundVoiceMailSyncEnabled();
//
    boolean shouldLogExperimentIds();
//
//    String[] getFeedsAndNotificationsSupportedMobileApps();
//
//    boolean isChatAppEntitlementSyncFromActivityEnabled();
//
//    boolean checkUserPinningAllowedSetting();
//
    String getAppInfoExperimentationIds();
//
//    boolean isDevSettingsEnabled();
//
//    boolean enableNewCallsUXForDevices();
//
//    boolean enableGroupCallHiding();
//
//    boolean isVaultRecoveryEnabled();
//
//    boolean isVaultEnabled();
//
//    boolean isExpertsEnabled();
//
//    boolean isShareLinkBannerEnabled(boolean defaultValue);
//
//    boolean isShareVaultInChatEnabled();
//
//    boolean isVaultResetUserEnabled();
//
//    boolean isDeletePersonalVaultEnabled();
//
//    JSONObject getVaultMediaSupportSettings();
//
//    int vaultConfiguredAuthTimeout();
//
//    int vaultScenarioTelemetrySamplingRate();
//
//    boolean usePersonalStreams();
//
//    boolean isOnBehalfOfUserAttributionEnabled();
//
//    boolean isActionExecuteForAdaptiveCardsEnabled();
//
//    boolean isRefreshForAdaptiveCardsEnabled();
//
//    boolean isAdaptiveCardsContextMenuEnabled();
//
//    boolean isCardActionInvokeComplianceDataEnabled();
//
//    boolean isScrollOptimizationsEnabledForAdaptiveCardRefresh();
//
//    boolean isUserIdsListFilteringEnabledForAdaptiveCardRefresh();
//
//    boolean isUniversalActionForAdaptiveCardsEnabled();
//
//    boolean isBotAllowedToInvokeActionExecute(String botMri);
//
//    boolean isBotAllowedToInvokeAutomaticRefresh(String botMri);
//
//    int getAdaptiveCardCacheDaoSizeLimit();
//
//    int getAdaptiveCardCachePruningFrequency();
//
//    int getAdaptiveCardCacheItemExpiryDuration();
//
//    boolean isSearchSpellerEnabled();
//
//    boolean isSearchPersonalizedSpellerEnabled();
//
//    boolean isSearchSpellerWordWheelingUXDisabled();
//
//    boolean isSearchSpellerTriggerControlEnabled();
//
//    boolean disableSearchBaselineTelemetryLogToAria();
//
//    boolean enableSearchBaselineTelemetryLogToEventAPI();
//
//    boolean isSearchQueryFormulationEnabled();
//
//    int getTextSuggestionsCount();
//
//    int getTopHitSuggestionsCount();
//
//    boolean showTextSuggestionsAtBottom();
//
//    int getQueryFormulationDebounceDelayTimeInMilli();
//
//    boolean isMsaiFileSearchEnabled();
//
//    boolean isBookmarkAnswerSearchEnabled();
//
//    boolean isCalendarAnswerSearchEnabled();
//
//    boolean isAcronymAnswerSearchEnabled();
//
//    boolean isSearchAnswerWordWheelingUXDisabled();
//
//    boolean isBookmarkAnswerV2Enabled();
//
//    boolean isCalendarAnswerV2Enabled();
//
//    int getBookmarkAnswerV2DefaultNumber();
//
//    int getCalendarAnswerExpandNumber();
//
//    int getAnswerTimeoutDuration();
//
//    boolean isBookmarkAnswerTriggerControlEnabled();
//
//    boolean isCalendarAnswerTriggerControlEnabled();
//
//    boolean isAcronymAnswerTriggerControlEnabled();
//
//    boolean isMessageNLSearchEnabled();
//
//    boolean isFileNLSearchEnabled();
//
//    boolean isFileNLSearchAlterationEnabled();
//
//    boolean isUniversalApiSearchEnabled();
//
//    boolean isUniversalApiUserSearchEnabled();
//
//    int getUniversalApiSearchTimeout();
//
//    boolean isSearchFileInChatEnabled();
//
//    boolean isMessageNLRecourseLinkEnabled();
//
//    boolean isMessageNLRecourseLinkTriggerControlEnabled();
//
//    boolean isMessageRelevanceBasedRankingEnabled();
//
//    boolean showMeetingChicletInGroupChat();
//
//    boolean isSearchBaselineTelemetryEnabled();
//
//    boolean isMeetingJoinByCodeEnabled();
//
//    boolean isTeamsTabEnabled();
//
//    boolean isForceUpdateEnabled();
//
//    boolean isEncourageUpdateEnabled();
//
//    boolean isSuggestUpdateEnabled();
//
//    long getContactGroupFetchFrequency();
//
//    boolean isTenantSwitchToastEnabled();
//
//    boolean isBackgroundThreadPoolEnabled();
//
//    boolean isPlaygroundThemeEnabled();
//
//    boolean isCustomLogoEnabled();
//
//    boolean isCustomLogoAMSEnabled();
//
//    boolean isPSTNMaskingEnabled();
//
//    boolean isHideRejoinEnabled();
//
//    boolean isUnmixedBotProtocol();
//
//    boolean usePeopleSearchV2();
//
//    boolean isSkypetokenExpiryOnIdentityPromptEnabled();
//
//    boolean skypeTokenRevocationEnabled();
//
//    boolean isChatReportAbuseEnabled();
//
//    boolean allowCallRingtoneOptions();
//
//    boolean isMeetNowEnable();
//
//    boolean isMeetNowPrejoinEnabled();
//
//    boolean isMeetNowScheduledEnabled();
//
//    boolean isMeetNowGroupScheduledEnabled();
//
//    boolean isMeetNowFromChatEnabled();
//
//    public boolean isMeetNowTFLOverride();
//
//    boolean isMeetNowFromMeetingsEnabled();
//
//    boolean isMeetNowFromMeetingsEnableFreemium();
//
//    boolean isMeetNowFromChannelEnabled();
//
//    boolean isMeetNowFromChannelEnableFreemium();
//
//    boolean isMeetNowFromSharedChannelEnabled();
//
//    boolean isMeetNowExternalUserInChannelEnabled();
//
//    boolean isMeetNowFromTabEnabled();
//
//    boolean isMeetNowPrejoinVideoOnDefaultEnableFreemium();
//
//    boolean isMeetNowPrejoinMicOnDefaultEnableFreemium();
//
//    boolean isMeetNowEasyShareEnabled();
//
//    boolean isMeetNowEasyShareEnableFreemium();
//
//    boolean isMeetNowRosterShareInviteEnabled();
//
//    boolean isMeetNowRosterShareInviteEnableFreemium();
//
//    boolean isMeetNowRosterCopyLinkEnabled();
//
//    boolean isMeetNowRosterCopyLinkEnableFreemium();
//
//    boolean isMeetNowSendInitialMessageEnabled();
//
//    boolean isMeetNowMeetingTypeEnabled();
//
//    boolean isMeetingAccountPickerPageEnabled();
//
//    boolean isMeetNowWhatsNewEnabled();
//
//    boolean isAnonJoinAccountPickerEnabled();
//
//    boolean isWebinarAccountPickerEnabled();
//
//    boolean isMeetingExternalAccountPickerEnabled();
//
//    String getMeetingTenantIdToNameMapping();
//
//    String getMeetingTenantGetHelpUrl();
//
//    boolean isCallsTabShortcutEnabled();
//
//    boolean isRejoinTenantedSignInEnabled();
//
//    boolean isGroupBifurcationEnabled();
//
//    boolean isGroupCreateMultiSelectEnabled();
//
//    boolean isMiddleTierGraphS2SEnabled();
//
//    boolean isMiddleTierCalendarAndConsumerGroupS2SEnabled();
//
//    boolean isMiddleTierProfileS2SEnabled();
//
//    boolean isAndroidAutoMessagingEnabled();
//
//    boolean isAndroidAutoAvatarEnabled();
//
//    boolean isMessagingStyleNotificationsEnabled();
//
//    boolean isNewChatMessageSuggestionEnabled();
//
//    boolean isNewGroupChatPersonalizationCardEnabled();
//
//    boolean isOneToOnePersonalizationCardEnabled();
//
//    boolean isRemoveFileFromRecentEnabled();
//
//    boolean isSubstrateScopeEnabledForCalendar();
//
//    boolean isConsumerGroupOneOnOneCalendarEnabled();
//
//    boolean isCallMergeEnabled();
//
//    boolean isPstnCallMergeEnabled();
//
//    boolean isHardMuteEnabled();
//
//    boolean isIndividualAudioHardMuteEnabled();
//
//    boolean isIndividualVideoHardMuteEnabled();
//
//    boolean isParticipantCountFromSlimcoreEnabled();
//
//    boolean shouldEnableSkylibSetupKeysForEndpoints();
//
//    int getScdPingIntervalInDays();
//
//    boolean isPlatformAppDevicePermissionsEnabled();
//
//    int getSearchDebounceDelayTimeInMilli();
//
//    int getSearchMinQueryLength();
//
//    int getImageCacheDays();
//
//    boolean enableDashboardV2();
//
//    boolean enableConsumerGetTenantsFallback();
//
//    boolean enableMeetingAutoExitOnNorden();
//
//    boolean isGlobalSignInOutEnabled();
//
//    boolean isGlobalSignInOutFlagSecureEnabled();
//
//    boolean enableContentSharingFullScreenMode();
//
//    boolean enableStampingOutsideOnboarding();
//
//    boolean enableBackgroundSyncService();
//
//    int getBGSyncServiceRuntimeIntervalInMins();
//
//    boolean isOptionalTelemetryEnabled();
//
//    boolean isDownloadContentSettingToggleEnabled();
//
//    boolean isGiphyToggleEnabled();
//
//    boolean syncChatEntitlementsWithoutExtensionProperty();
//
//    String[] getAriaTelemetrySuppressEventsList();
//
//    boolean isDeleteActivityItemEnabled();
//
//    boolean enableCQFRatingCaptureOnFeedbackCancel();
//
//    boolean shouldOverrideUserSettingsNameSpace();
//
//    boolean isVoiceMailRNLEnabled();
//
//    boolean isCallDefaultViewEnabled();
//
//    boolean enableDualScreenSupport();
//
//    boolean enableNordenLockScreen();
//
//    boolean enableNordenLockScreenInternalTest();
//
//    boolean enableNordenHDMIIngest();
//
//    int getPlatformDeviceCapabilityFileSizeThresholdInKB();
//
//    int getPlatformLowEndFileTransferThresholdInKB();
//
//    int getPlatformDeviceCapabilityMaxAudioRecordTime();
//
//    boolean isEditGroupAvatarEnabled();
//
//    boolean isFileSizePreviewEnabled();
//
//    boolean isFilesTabEnabled();
//
//    boolean isFileThumbnailPreviewEnabled();
//
//    boolean isFileUploadFromChatFilesTabEnabled();
//
//    boolean isGroupAvatarControlMessageEnabled();
//
//    boolean isAttachAndSendFileEnabled();
//
//    boolean isOneDriveForConsumerEnabled();
//
//    boolean shouldCheckIfUserOnNetworkUsingScd();
//
//    boolean shouldUseDriveItemForFilePreview();
//
//    boolean isAddToCalendarEnabled();
//
//    boolean isCalendarEventExportEnabled();
//
//    boolean isCalendarIncrementalAPIEnabled();
//
//    boolean enableNeverLockTimeOutSettingOptionOnKingston();
//
//    long getSyncIntervalInSecondsForNordenMeeting();
//
//    boolean enableSidecarOnIpPhone();
//
//    boolean deviceCapabilityCheckEnabled();
//
//    boolean isDeviceCapabilityApiEnabled(@Nullable String apiEcsTag);
//
//    boolean isDeviceCapabilitySettingEnabled(@Nullable String settingEcsTag);
//
//    boolean enableAutoAcceptMeetingNudges();
//
//    int autoAcceptCallCountdownInSeconds();
//
//    Set<String> listOfCallingScenariosToMoveToEnd();
//
//    int[] listOfCallEndStatusCodesToIgnore();
//
//    String listOfCallEndScenariosToIgnore();
//
//    boolean shouldCheckForCurrentUserOnSkylibUpdateToken();
//
//    boolean shouldShowMeetingBannerDefaultState();
//
//    boolean shouldSkipInitialAuthCheck();
//
//    boolean allowPlatformOpenNewConversations();
//
//    boolean allowPlatformOpenExistingConversations();
//
//    boolean allowPlatformOpenConversationSpinner();
//
//    String streamUrlQueryParameters();
//
//    boolean isBackgroundBlurEnabled();
//
//    boolean isBackgroundReplacementEnabled();
//
//    boolean isCustomBgEnabled();
//
//    boolean isAddPeopleByAttendeeDisabled();
//
//    boolean shouldShowAnimatedBadgeView();
//
//    int newGroupWelcomeScreenType();
//
//    boolean isUntitledGroupCreationEnabled();
//
//    boolean enableIncrementalGroupSync();
//
//    boolean showSparseCalendar();
//
//    boolean isV2NotificationSettingsEnabled();
//
//    boolean isFetchFederatedForCallTransferEnabled();
//
//    boolean isSpotlightEnabled();
//
//    boolean isMultipleParticipantSpotlightEnabled();
//
//    boolean shouldProvisionOneDriveOnSignIn();
//
//    boolean enableLiveStateCheckForGlobalActiveCall();
//
//    boolean isTranscriptionUfdEnabled();
//
//    boolean isNDIUfdEnabled();
//
//    boolean isChatDashboardEnabled();
//
//    boolean shouldOpenImagesInExternalApp();
//
//    boolean shouldEnableFlashlightForRealWear();
//
//    boolean shouldShowCallOrMeetingParticipantsEnabledForRealWear();
//
//    boolean isOverflowMeetingEnabled();
//
//    boolean isChatDashboardPreheatEnabled();
//
//    boolean enableRoomControl();
//
//    boolean enableJSRoomController();
//
//    @NonNull
//    String roomControllerReactUrl();
//
//    boolean isNonInteractiveXLMeetingEnabled();
//
//    boolean isSkypeChatServicesPreInitEnabled();
//
//    boolean enableL1NavigationBar();
//
//    boolean enableL2NavigationBar();
//
//    boolean enableTenantSwitchOnAvatarSwipe();
//
//    boolean shouldPreventParallelBlocklistSync();
//
//    boolean shouldPreventParallelContactGroupSync();
//
//    boolean shouldPreventParallelAddressBookSync();
//
//    boolean shouldBlockUIOnAcceptingChat();
//
//    List<String> knownGiphyHosts();
//
//    boolean respectGiphyDisplayPolicy();
//
//    boolean shouldUpdateParticipantCheckEnabled();
//
//    boolean reregisterSlimcoreContextsOnSkypeTokenRefresh();
//
//    String getMeetingReminderDefaultType();
//
//    int getMeetingReminderDefaultTimeSetting();
//
//    boolean isAnonymousUserChatEntitlementSyncEnabled();
//
//    int getMaxGroupChatParticipantsForGroupCall();
//
//    boolean isLiveStateServiceEnabled();
//
//    boolean isLiveStateServicePPTSharingEnabled();
//
//    boolean isLiveStateServiceReactionsEnabled();
//
//    boolean isLiveStateServiceTelemetryEnabled();
//
//    public JSONObject getLiveStateServiceSettings();
//
//    boolean isPOPTokenSupportEnabled();
//
//    boolean callProfileViewOverlappingRenderingDisabled();
//
//    int getMaxNumberOfCallRegistryQueuedCalls();
//
//    boolean isTurnOnVideoNotificationEnabled();
//
//    boolean isVroomRecentsListingEnabled();
//
//    int getRecentFilesListPageSize();
//
//    boolean shouldShowDeviceUpdateDialogForRealWear();
//
//    long getReactionsLastingTime();
//
//    int getMaxOverflowReactionsCount();
//
//    boolean isDoormatEnabled();
//
//    boolean isAddAccountCoachMarkEnabled();
//
//    String[] getOverrideSuppressLoggingEvents();
//
//    boolean isMeetingLockEnabled();
//
//    boolean diagnosticDataViewerEnabled();
//
//    boolean controllerConnectedServicesRoamingEnabled();
//
//    boolean telemetryFlushEnabled();
//
//    boolean immersiveReaderEnabled();
//
//    boolean optimizeSyncServiceForBattery();
//
//    boolean optimizeSyncServiceForNetwork();
//
//    int[] getSLAForSyncQueuesHours();
//
//    boolean anonymousSkypeTokenRevocationEnabled();
//
//    boolean isChatFilterEnabled();
//
//    boolean postDummyNotificationForPreCallForegroundService();
//
//    boolean postDummyNotificationForInCallForegroundServiceOnCreate();
//
//    boolean postDummyNotificationForInCallForegroundServiceOnStart();
//
//    String getEmojiMetadataId();
//
//    boolean isExtendedEmojiEnabled();
//
//    boolean allowDisableServicesForCallNotificationsAppInForeground();
//
//    boolean isFollowCallRecordingPolicyEnabled();
//
//    boolean isSlimCoreEventLoggingEnabled();
//
//    boolean isAliasDiscoverabilityEnabled();
//
//    boolean isNotificationFilterViaServerEnabled();
//
//    boolean enableMeetingExtensibility();
//
//    boolean enableMeetingExtensionAppControlBarEntry();
//
//    boolean isChatDashboardCoachmarkEnabled();
//
//    boolean isLiveStateLoggingEnabled();
//
//    boolean isStickersEnabled();
//
//    boolean isCustomMemesEnabled();
//
//    boolean isCustomMemesImagePasteEnabled();
//
//    boolean isAccountPlaceholderIconEnabled();
//
//    boolean isCreateReminderFromMessageEnabled();
//
//    String[] getDualModeEnabledDeviceClasses();
//
//    @NonNull
//    String[] getInMeetingTabsWhitelistedApps();
//
//    boolean shouldSetAnonymousUserDisplayName();
//
//    boolean enableCustomBrbLoggingForNativeApps();
//
//    int getDefaultMessageSizeLimitInKB();
//
//    /**
//     * Complete ECS settings, these are needed to provide full ECS configuration for non active users to other libraries
//     * e.g. slimcore
//     * @return complete ECS string
//     */
//    @Nullable
//    String getECSSettings();
//
//    /**
//     * etag value
//     * e.g. slimcore
//     * @return complete ECS tag last returned from ecs client.
//     */
//    @Nullable
//    String getECSEtag();
//
//    @Nullable
//    String[] getSDKBlocklistedApps();
//
//    /**
//     * URIs of resources for that the app must perioically prefetch tokens using the authentication service.
//     *
//     * @return Array of resource URIs, or null to prevent the prefetch.
//     */
//    @Nullable
//    String[] getTokenPrefetchResources();
//
//    /**
//     * Threshold in hours of non-use of prefetchable tokens. After the user hasn't requested a token for some time,
//     * prefetching stops to not issue unneeded network calls.
//     *
//     * @return Number of hours after that token prefetch stops until the user requests a token again.
//     */
//    int getTokenFetchNonUseThresholdInHours();
//
//    /**
//     * Checks whether Expo casting is enabled.
//     */
//    boolean isExpoCastingEnabled();
//
//    /**
//     * If the database is locked by calendar sync, returning true will allow other threads to write/read to the database.
//     * @return
//     */
//    boolean isCalendarSyncYieldEnable();
//
//    /**
//     * check wheater user entitlement is enabled for reduce sync time feature.
//     */
//    boolean isSyncUserEntitlementsImprovementEnabled();
//
//    /**
//     * Dogfood toast reset count in days
//     * @return
//     */
//    int getDogfoodToastResetDayCount();
//
//    /**
//     * flag to manage df toast
//     * @return
//     */
//    boolean isDogfoodToastEnabled();
//
//    /**
//     * returns dogfood package for the user
//     * @return
//     */
//    public String getDogfoodAppPackage();
//
//    /**
//     * returns dogfood package for the user
//     * @return
//     */
//    public String getDogfoodAppCenterUrl();
//
//    /**
//     * Check app center updates are enabled for RealWear builds
//     */
//    boolean isRealWearAppCenterUpdatesEnabled();

    /**
     * Fluid component type supported
     */
    @StringDef(value = {FluidComponentType.ACTIONITEMS,
            FluidComponentType.AGENDA,
            FluidComponentType.BLULLETLIST,
            FluidComponentType.NUMBEREDLIST,
            FluidComponentType.CHECKEDLIST,
            FluidComponentType.PARAGRAPH,
            FluidComponentType.TABLE})
    @Retention(RetentionPolicy.SOURCE)
    @interface FluidComponentType {
        String ACTIONITEMS = "actionItems";
        String AGENDA = "agenda";
        String BLULLETLIST = "bulletedList";
        String CHECKEDLIST = "checklist";
        String NUMBEREDLIST = "numberedList";
        String PARAGRAPH = "paragraph";
        String TABLE = "table";
    }

}
