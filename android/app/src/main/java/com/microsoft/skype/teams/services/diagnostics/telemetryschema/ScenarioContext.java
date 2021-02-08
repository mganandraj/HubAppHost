/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.services.diagnostics.telemetryschema;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.skype.teams.app.AppStateProvider;
import com.microsoft.skype.teams.logger.ILogger;
import com.microsoft.skype.teams.logger.ITelemetryLogger;
import com.microsoft.skype.teams.logger.LogPriority;
import com.microsoft.skype.teams.logger.constants.ScenarioPropKeys;
//import com.microsoft.skype.teams.models.AuthenticatedUser;
import com.microsoft.skype.teams.services.configuration.ExperimentationConstants;
import com.microsoft.skype.teams.services.diagnostics.StatusCode;
//import com.microsoft.skype.teams.services.diagnostics.SuppressEmail;
//import com.microsoft.skype.teams.services.diagnostics.UserBIType;
//import com.microsoft.skype.teams.services.utilities.ApplicationUtilities;
import com.microsoft.skype.teams.services.utilities.StringUtilities;
import com.microsoft.skype.teams.storage.IExperimentationManager;
//import com.microsoft.skype.teams.utilities.INotificationUtilitiesWrapper;
//import com.microsoft.skype.teams.utilities.ITestUtilitiesWrapper;
import com.microsoft.skype.teams.utilities.java.StringUtils;
import com.microsoft.teams.core.preferences.IPreferences;
//import com.microsoft.teams.core.services.configuration.IUserConfiguration;
//import com.microsoft.teams.core.utilities.AppBuildConfigurationHelper;
import com.microsoft.teams.telemetry.EventPriority;
import com.microsoft.teams.telemetry.EventProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import bolts.Continuation;
import bolts.Task;

/**
 * Schema for scenario context
 */
public class ScenarioContext extends TelemetryEvent {
    public static final String LOG_TAG = "ScenarioContextEvent";
    public static final String EVENT_NAME = "scenario";

    private static final int AVG_SIZE_OF_TAG = 10;

    // Marked transient to indicate that GSON should not serialize
    private final transient IExperimentationManager mExperimentationManager;
    private final transient ITelemetryLogger mTelemetryLogger;
    private final transient ILogger mLogger;
//    private final transient ITestUtilitiesWrapper mTestUtilitiesWrapper;
//    private final transient INotificationUtilitiesWrapper mNotificationUtilitiesWrapper;
//    private final transient ApplicationUtilities mApplicationUtilities;
    private final transient IPreferences mPreferences;
//    private final transient IUserConfiguration mUserConfiguration;
//    private final transient AuthenticatedUser mAuthenticatedUser;

    //Tied to user action. e.g. chat_Send_message. This remains same for all the subscenarios and is passed along.
    private String mScenarioId;

    //This is unique to the scenario. This supports parallelism. e.g. UPLOAD_BITMAP is called for each image in send_message. These can be scheduled parallely.
    private String mStepId;

    //This is human readable value for the scenario.
    private String mScenarioName;

    //START, STOP
    private String mStepName;

    //OK, CANCEL, INCOMPLETE, ERROR
    private String mStepStatus;

    //This is the logical statusCode for the errors that happen. This is useful to drive uniformity across platforms as well as grouping the scenarios by statuscode
    private String mStatusCode;

    //This is the dependency String e.g. ADAL, Sharepoint
    private String mDependencyString;

    //This is the verbose reason
    private String mStatusReason;

    // Source of this event
    private String mSource;

    //Time taken by the scenario
    private long mScenarioTimeTaken;

    //Comma separated key value pairs.
    private String mMetaData = StringUtilities.EMPTY;

    private String mUserLicense;

    //App state when scenario started - Foreground or Background
    private String mAppStatus = StringUtilities.EMPTY;

    private final ScenarioContext mParentScenarioContext;

    // call related data-bag
    private Map<String, Object> mCallDataBag;

    //Databag in json string format
    private String mScenarioDatabag;

    //Databag in collection format.
    //Always update mScenarioDatabag when updating this field.
    private Map<String, Object> mDataBag;

    //For MessageScenarioContext: Unique combination of the senderid, conversation id / thread id, and client message id
    // For Calling: unique UUID or deeplinkId
    protected String mCorrelationId;

    private final String mInstrumentationSource;

    private List<String> mOverrideSuppressLoggingEvents = new ArrayList<>();

    public ScenarioContext(
            @ScenarioName String scenarioName,
            String stepStatus,
            @Nullable ScenarioContext parentScenarioContext,
            @NonNull IExperimentationManager experimentationManager,
            @NonNull ITelemetryLogger telemetryLogger,
            @NonNull ILogger logger,
//            @NonNull ITestUtilitiesWrapper testUtilitiesWrapper,
//            @NonNull INotificationUtilitiesWrapper notificationUtilitiesWrapper,
//            @NonNull ApplicationUtilities applicationUtilities,
            @Nullable String instrumentationSource,
            @Nullable Map<String, Object> databag,
            @NonNull IPreferences preferences,
//            @NonNull IUserConfiguration userConfiguration,
//            @Nullable AuthenticatedUser authenticatedUser,
            @Nullable String... metaData) {
        this(
                scenarioName,
                stepStatus,
                parentScenarioContext,
                experimentationManager,
                telemetryLogger,
                logger,
//                testUtilitiesWrapper,
//                notificationUtilitiesWrapper,
//                applicationUtilities,
                instrumentationSource,
                databag,
                preferences,
                false,
//                userConfiguration,
//                authenticatedUser,
                Collections.emptyList(), // passing empty list as we do not want to override scenario suppression for FileScenarioContext, MessageScenarioContext
                metaData);
    }

    // pass the Scenario names in overrideSuppressLoggingEvents if you do not want to suppress any scenario's
    public ScenarioContext(@ScenarioName String scenarioName,
                           String stepStatus,
                           @Nullable ScenarioContext parentScenarioContext,
                           @NonNull IExperimentationManager experimentationManager,
                           @NonNull ITelemetryLogger telemetryLogger,
                           @NonNull ILogger logger,
//                           @NonNull ITestUtilitiesWrapper testUtilitiesWrapper,
//                           @NonNull INotificationUtilitiesWrapper notificationUtilitiesWrapper,
//                           @NonNull ApplicationUtilities applicationUtilities,
                           @Nullable String instrumentationSource,
                           @Nullable Map<String, Object> databag,
                           @NonNull IPreferences preferences,
                           boolean polluteCustomerLogs,
//                           @NonNull IUserConfiguration userConfiguration,
//                           @Nullable AuthenticatedUser authenticatedUser,
                           @NonNull List<String> overrideSuppressLoggingEvents,
                           @Nullable String... metaData) {
        super();
        mStepId = UUID.randomUUID().toString();
        mScenarioName = scenarioName;
        mStepName = StepName.START;
        mStepStatus = stepStatus;
        mScenarioId = parentScenarioContext == null ? getStepId() : parentScenarioContext.getScenarioId();
        mParentScenarioContext = parentScenarioContext;
        mAppStatus = AppStateProvider.isAppVisible() ? APP_FOREGROUND : APP_BACKGROUND;
        mExperimentationManager = experimentationManager;
        mTelemetryLogger = telemetryLogger;
        mLogger = logger;
//        mTestUtilitiesWrapper = testUtilitiesWrapper;
//        mNotificationUtilitiesWrapper = notificationUtilitiesWrapper;
//        mApplicationUtilities = applicationUtilities;
        mInstrumentationSource = instrumentationSource;
        mPreferences = preferences;
        mPolluteCustomerLogs = polluteCustomerLogs;
//        mUserConfiguration = userConfiguration;
//        mAuthenticatedUser = authenticatedUser;
        mOverrideSuppressLoggingEvents = overrideSuppressLoggingEvents;
        setMetaData(metaData);

//        if (mAuthenticatedUser != null && mAuthenticatedUser.settings != null) {
//            mUserLicense = StringUtils.isEmpty(mAuthenticatedUser.settings.licenseType) ? UserBIType.UNKNOWN_LICENSE : mAuthenticatedUser.settings.licenseType;
//        }

        this.appendDataBag(databag);

//        mTestUtilitiesWrapper.scenarioExecutionStarted(scenarioName);

        if (isScenarioStartEventWhitelisted()) {
            mTelemetryLogger.log(this);
        } else {
            mTelemetryLogger.debugPrintEvents(LOG_TAG, this, polluteCustomerLogs);
        }

//        if (isScenarioInstrumentationNotificationEnabled()) {
//            mNotificationUtilitiesWrapper.showInstrumentationNotification(
//                    mApplicationUtilities.getApplicationContext(),
//                    this.mStepName,
//                    this.mScenarioName,
//                    mUserConfiguration,
//                    getUserObjectId());
//        }
    }

    @Override
    @NonNull
    public String getLogTag() { return LOG_TAG; }

    @NonNull
    public String getCallDataBag() {
        return StringUtilities.convertMapToJson(mCallDataBag);
    }

    /**
     * Attaches the specified scenario to the task.
     */
    public static <T> Task<T> attachScenarioContextToTask(@NonNull Task<T> task, @NonNull final ScenarioContext scenarioContext, final String... tags) {
        return task.continueWithTask(new Continuation<T, Task<T>>() {
            @Override
            public Task<T> then(Task<T> task) {
                if (task.isFaulted()) {
                    String taskError = task.getError() != null ? task.getError().getMessage() : "Something went wrong!";
                    scenarioContext.endScenarioOnError(StatusCode.UNKNOWN, taskError, null, tags);
                } else if (task.isCancelled()) {
                    scenarioContext.endScenarioOnIncomplete(StatusCode.CANCELLED, null, null, tags);
                } else {
                    scenarioContext.endScenarioOnSuccess(tags);
                }

                return task;
            }
        });
    }

    public String getCorrelationId() {
        return mCorrelationId;
    }

    public void setCorrelationId(@Nullable String correlationId) {
        mCorrelationId = correlationId;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(@Nullable String source) {
        mSource = source;
    }

    public String getScenarioId() {
        return mScenarioId;
    }

    public String getStatusReason() {
        return mStatusReason;
    }

    public String getStepId() {
        return mStepId;
    }

    public String getScenarioName() {
        return mScenarioName;
    }

    public String getStepName() {
        return mStepName;
    }

    public String getStepStatus() {
        return mStepStatus;
    }

    public String getStatusCode() {
        return mStatusCode;
    }

    public String getUserLicense() {
        return mUserLicense;
    }

    public long getScenarioTimeTaken() {
        return mScenarioTimeTaken;
    }

    public String getMetaData() {
        return mMetaData;
    }

    public String getAppStatus() {
        return mAppStatus;
    }

    /**
     * Appends the passed in callDataBag to the existing mCallDataBag values
     *
     * @param callDataBag - call databag values to add to the scenario event
     */
    public void appendToCallDataBag(@Nullable Map<String, Object> callDataBag) {
        if (callDataBag != null && callDataBag.size() > 0) {
            if (mCallDataBag == null) {
                mCallDataBag = new ConcurrentHashMap<>();
            }

            mCallDataBag.putAll(callDataBag);
        }
    }

    /**
     * Appends the key, value pair to the existing mCallDataBag
     *
     * @param key - key
     * @param value - value for that key
     */
    public void appendToCallDataBag(@Nullable String key, @Nullable Object value) {
        if (StringUtils.isEmptyOrWhiteSpace(key) || value == null) {
            return;
        }
        if (mCallDataBag == null) {
            mCallDataBag = new ConcurrentHashMap<>();
        }
        mCallDataBag.put(key, value);
    }

    public String getDependencyString() {
        return mDependencyString;
    }

    public void setDependencyString(String dependencyString) {
        mDependencyString = dependencyString;
    }

    @Nullable
    public String getInstrumentationSource() {
        return mInstrumentationSource;
    }

    @Nullable
    public String getScenarioDatabag() {
        return mScenarioDatabag;
    }

    private void setMetaData(String... metaData) {
        if (metaData != null) {
            StringBuilder strBuilder = new StringBuilder(AVG_SIZE_OF_TAG * metaData.length);
            boolean isFirstKey = TextUtils.isEmpty(this.mMetaData);

            for (String key : metaData) {
                if (!StringUtils.isEmpty(key)) {
                    if (!isFirstKey) {
                        strBuilder.append(',');
                    } else {
                        isFirstKey = false;
                    }

                    strBuilder.append(key);
                }
            }
            this.mMetaData = this.mMetaData.concat(strBuilder.toString());
        }
    }

    boolean isScenarioStartEventWhitelisted() {
        String[] whitelistedScenarios = mExperimentationManager.getEcsSettingAsStringArray(ExperimentationConstants.LOG_SCENARIO_START_WHITELIST, null);
        if (whitelistedScenarios == null || StringUtils.isNullOrEmptyOrWhitespace(mScenarioName)) {
            return false;
        }

        for (String whitelistedScenario : whitelistedScenarios) {
            if (mScenarioName.equalsIgnoreCase(whitelistedScenario)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Appends the passed in databag to the existing data bag values
     *
     * @param databag - databag values to add to the scenario event
     */
    public void appendDataBag(@Nullable Map<String, Object> databag) {
        if (databag != null && databag.size() > 0) {
            if (mDataBag == null) {
                mDataBag = new ConcurrentHashMap<>();
            }

            mDataBag.putAll(databag);
            mScenarioDatabag = StringUtilities.convertMapToJson(mDataBag);
        }
    }

    /**
     * Appends the passed in key value pair to the data bag.
     *
     * @param key key for the new databag entry
     * @param value value for the new databag entry
     */
    public void appendDataBag(@NonNull String key, @NonNull Object value) {
        if (mDataBag == null) {
            mDataBag = new ConcurrentHashMap<>();
        }

        mDataBag.put(key, value);
        mScenarioDatabag = StringUtilities.convertMapToJson(mDataBag);
    }

    public void setMetadataAsString(String metadata) {
        if (StringUtils.isEmptyOrWhiteSpace(metadata)) {
            return;
        }
        mMetaData = metadata;
    }

    public ScenarioContext getParentScenarioContext() {
        return mParentScenarioContext;
    }

    public void endScenarioOnError(@StatusCode.StatusCodeValue String scenarioStatusCode,
                                   @NonNull String scenarioStatusReason,
                                   @NonNull String dependencyString,
                                   String... tags) {
        endScenario(StepStatus.ERROR, scenarioStatusCode, scenarioStatusReason, dependencyString, tags);
    }

    public void pauseScenarioOnIncomplete(@StatusCode.StatusCodeValue String scenarioStatusCode,
                                          @NonNull String scenarioStatusReason,
                                          @NonNull String dependencyString,
                                          String... tags) {
        endScenario(StepStatus.INCOMPLETE, scenarioStatusCode, scenarioStatusReason, dependencyString, tags);
    }

    public void endScenarioOnIncomplete(@StatusCode.StatusCodeValue String scenarioStatusCode,
                                        @NonNull String scenarioStatusReason,
                                        @NonNull String dependencyString,
                                        String... tags) {
        endScenario(StepStatus.INCOMPLETE, scenarioStatusCode, scenarioStatusReason, dependencyString, tags);
    }

    public void endScenarioOnCancel(@StatusCode.StatusCodeValue String scenarioStatusCode,
                                    @NonNull String scenarioStatusReason,
                                    @NonNull String dependencyString,
                                    String... tags) {
        endScenario(StepStatus.CANCEL, scenarioStatusCode, scenarioStatusReason, dependencyString, tags);
    }

    public void endScenarioOnSuccessWithTimeTaken(long scenarioTimeTaken, String... tags) {
        endScenarioWithTimeTaken(StepStatus.OK, null, null, null, scenarioTimeTaken, tags);
    }

    public void endScenarioOnSuccess(String... tags) {
        endScenario(StepStatus.OK, null, null, null, tags);
    }

    public void endScenarioOnSuccessWithStatusCode(@StatusCode.StatusCodeValue String scenarioStatusCode, String... tags) {
        endScenario(StepStatus.OK, scenarioStatusCode, null, null, tags);
    }

    public void addKeyValueTags(@NonNull String key, @NonNull String value) {
        setMetaData(String.format("%s = %s", key, value));
    }

    public void logStep(@NonNull String stepName) {
        if (mExperimentationManager.isStepTelemetryEnabled()
                || mOverrideSuppressLoggingEvents.contains(mScenarioName)) {
            this.mStepName = stepName;
            this.mScenarioTimeTaken = calculateLatencyFromNow();
            mTelemetryLogger.log(this);
            if (isScenarioInstrumentationNotificationEnabled()) {
                /*mNotificationUtilitiesWrapper.showInstrumentationNotification(
                        mApplicationUtilities.getApplicationContext(),
                        this.mStepName,
                        this.mScenarioName,
                        mUserConfiguration,
                        getUserObjectId());*/
            }
        }
    }

    private void endScenarioWithTimeTaken(String stepStatus,
                                          @StatusCode.StatusCodeValue String scenarioStatusCode,
                                          @Nullable String scenarioStatusReason,
                                          @NonNull String dependencyString,
                                          long scenarioTimeTaken,
                                          String... metaData) {
        // TODO: The comment and test below are outdated. However, I wanted verification before removing.
        // TODO: The codebase has mixed usages of ApplicationUtilities.getScenarioManagerInstance.endScenarioOnSuccess() and direct calls to scenarioContext.endScenarioOnSuccess
        // This is resulting in multiple* flows triggering ending a scenario more than once
        // This check below will help to only end a scenario if it has not been ended previously. Ideally, the mixed usages for calling on scenarios will be cleaned up.
        if (!TextUtils.equals(this.mStepName, StepName.STOP)) {
            this.mStepName = StepName.STOP;
            this.mStepStatus = stepStatus;
            this.mStatusCode = scenarioStatusCode;
            this.mStatusReason = scenarioStatusReason;
            this.mScenarioTimeTaken = scenarioTimeTaken;
            this.mAppStatus = AppStateProvider.isAppVisible() ? APP_FOREGROUND : APP_BACKGROUND;
            this.mDependencyString = dependencyString;
            setMetaData(metaData);
            /*if (mTestUtilitiesWrapper != null) {
                mTestUtilitiesWrapper.scenarioExecutionEnded(mScenarioName, this.mStepId, this.mScenarioTimeTaken, this.mStepStatus);
                mTestUtilitiesWrapper.logScenarioPerfMetric(this);
            }*/
            if (mTelemetryLogger != null) {
                mTelemetryLogger.log(this);
            }

//            if (isScenarioInstrumentationNotificationEnabled() && mNotificationUtilitiesWrapper != null) {
//                mNotificationUtilitiesWrapper.showInstrumentationNotification(
//                        mApplicationUtilities.getApplicationContext(),
//                        this.mStepName,
//                        this.mScenarioName,
//                        mUserConfiguration,
//                        getUserObjectId());
//            }
        } else {
            // TODO: Convert to an AppAssert (or equivalent), which will include helpful information such as stack trace
            mLogger.log(LogPriority.ERROR, this.mScenarioName, "Scenario has already ended for stepId %s for scenario %s ", this.mStepId, this.mScenarioName);
        }
    }

    private void endScenario(String stepStatus,
                             @StatusCode.StatusCodeValue String scenarioStatusCode,
                             @Nullable String scenarioStatusReason,
                             @NonNull String dependencyString,
                             String... metaData) {
        endScenarioWithTimeTaken(stepStatus, scenarioStatusCode, suppressPii(scenarioStatusReason), dependencyString, calculateLatencyFromNow(), metaData);
    }

    private String suppressPii(@Nullable String source) {
        // return source == null ? StringUtils.EMPTY_STRING : new SuppressEmail().hashEUII(source);
        return null;
    }

    private String getUserObjectId() {
//        if (mAuthenticatedUser == null) {
//            return null;
//        }
//
//        return mAuthenticatedUser.userObjectId;
        return null;
    }

    public String toString() {
        String msg = String.format("%s %s %-25s %-6s %-10s %-10s %s %s [%s] [%s]",
                getScenarioId(),
                getStepId(),
                getScenarioName(),
                getStepName(),
                getStepStatus(),
                getStatusCode(),
                getStatusReason(),
                String.valueOf(getScenarioTimeTaken()),
                getMetaData(),
                getScenarioDatabag());
        return msg;
    }

    @Override
    @Nullable
    public EventProperties toEventProperties(@NonNull ITelemetryLogger telemetryLogger) {
        IExperimentationManager experimentationManager = telemetryLogger.getExperimentationManager();
        Map<String, String> dictionary = new androidx.collection.ArrayMap<>(21);

        dictionary.put(ScenarioPropKeys.INSTANCE_ID, getScenarioId());
        dictionary.put(ScenarioPropKeys.STEP_ID, getStepId());
        dictionary.put(ScenarioPropKeys.NAME, getScenarioName());
        dictionary.put(ScenarioPropKeys.STEP, getStepName());
        dictionary.put(ScenarioPropKeys.STATUS, getStepStatus());
        dictionary.put(ScenarioPropKeys.CORRELATION_ID, getCorrelationId());
        dictionary.put(ScenarioPropKeys.DEPENDENCY_STRING, getDependencyString());
        dictionary.put(ScenarioPropKeys.STATUS_CODE, getStatusCode());
        dictionary.put(ScenarioPropKeys.STATUS_REASON, getStatusReason());
        dictionary.put(ScenarioPropKeys.METADATA, telemetryLogger.getConversationIdToLog(getMetaData()));
        dictionary.put(ScenarioPropKeys.TIME_TAKEN, String.valueOf(getScenarioTimeTaken()));
        dictionary.put(ScenarioPropKeys.DELTA, String.valueOf(getScenarioTimeTaken()));
        dictionary.put(ScenarioPropKeys.STEP_TIME_TAKEN, String.valueOf(getScenarioTimeTaken()));
        dictionary.put(ScenarioPropKeys.APPSTATUS, getAppStatus());
        dictionary.put(ScenarioPropKeys.NETWORK_STATUS, telemetryLogger.getNetworkStatus());
        dictionary.put(ScenarioPropKeys.CALL_DATA_BAG, getCallDataBag());
        dictionary.put(ScenarioPropKeys.USER_RING, experimentationManager.getRingInfo());
        dictionary.put(ScenarioPropKeys.USER_TYPE, telemetryLogger.getUserTypeForTelemetry());
        dictionary.put(ScenarioPropKeys.SOURCE, getSource());
        dictionary.put(ScenarioPropKeys.NETWORK_QUALITY, telemetryLogger.getNetworkQuality());
        dictionary.put(ScenarioPropKeys.NETWORK_TYPE, telemetryLogger.getNetworkType());
        dictionary.put(ScenarioPropKeys.USER_INFO_LICENSE_TYPE, getUserLicense());

        if (experimentationManager.shouldLogExperimentIds()
                && !StringUtils.isEmptyOrWhiteSpace(experimentationManager.getAppInfoExperimentationIds())) {
            dictionary.put(ScenarioPropKeys.EXPERIMENTATION_IDS, experimentationManager.getAppInfoExperimentationIds());
        }

        if (!TextUtils.isEmpty(getScenarioDatabag())) {
            dictionary.put(ScenarioPropKeys.DATA_BAG1, getScenarioDatabag());
        }
        if (!TextUtils.isEmpty(getInstrumentationSource())) {
            dictionary.put(INSTRUMENTATION_SOURCE_KEY, getInstrumentationSource());
        }

        final EventProperties properties = new EventProperties(ScenarioContext.EVENT_NAME, dictionary);
        properties.setPriority(EventPriority.HIGH);
        return properties;
    }

    public boolean isScenarioInProgress() {
        return !StepName.STOP.equals(getStepName());
    }

    private boolean isScenarioInstrumentationNotificationEnabled() {
//        return AppBuildConfigurationHelper.isDevDebug()
//                && mPreferences.getBooleanGlobalPref(
//                GlobalPreferences.ENABLE_INSTRUMENTATION_NOTIFICATIONS, false)
//                && mPreferences.getBooleanGlobalPref(
//                GlobalPreferences.ENABLE_SCENARIO_INSTRUMENTATION_NOTIFICATIONS, false);
        return false;
    }
}
