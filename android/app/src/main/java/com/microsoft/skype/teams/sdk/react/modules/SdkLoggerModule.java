/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk.react.modules;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.microsoft.skype.teams.logger.ILogger;
import com.microsoft.skype.teams.logger.LogPriority;
// import com.microsoft.skype.teams.logger.SDKTeamsTelemetryLogger;
import com.microsoft.skype.teams.logger.StackTraceUtilities;
// import com.microsoft.skype.teams.models.AuthenticatedUser;
import com.microsoft.skype.teams.sdk.SdkApplicationContext;
import com.microsoft.skype.teams.sdk.models.SdkAppManifest;
//import com.microsoft.skype.teams.sdk.react.injection.SdkModuleScope;
//import com.microsoft.skype.teams.services.diagnostics.ITeamsTelemetryLoggerProvider;
//import com.microsoft.skype.teams.services.diagnostics.IUserBITelemetryManager;
//import com.microsoft.skype.teams.services.diagnostics.UserBIType;
//import com.microsoft.skype.teams.storage.IExperimentationManager;
import com.microsoft.teams.core.app.ITeamsApplication;
//import com.microsoft.teams.core.preferences.IPreferences;
//import com.microsoft.teams.injection.PlatformAppId;
//import com.microsoft.teams.telemetry.EventPriority;
//import com.microsoft.teams.telemetry.EventProperties;
import com.microsoft.teams.telemetry.TraceLevel;

import java.util.Locale;

import javax.inject.Inject;

//import static com.microsoft.skype.teams.logger.TeamsTelemetryWriter.TRACE_MESSAGE_COLUMN;
//import static com.microsoft.skype.teams.logger.TeamsTelemetryWriter.TRACE_SOURCE_COLUMN;

/**
 * React native module  for logger.
 */
// @SdkModuleScope
@ReactModule(name = SdkLoggerModule.MODULE_NAME)
public class SdkLoggerModule extends TeamsReactContextBaseJavaModule {
    public static final String MODULE_NAME = "logger";
    private static final String LOG_TAG = "SdkAppLogs";
    //TODO: remove these once telemetry logging is supported for RN modules
    private static final String ICON_PRESS = "ICON_PRESS";
    private static final String CARD_PRESS = "CARD_PRESS";
    private final ITeamsApplication mTeamsApplication;
    // private final IUserBITelemetryManager mUserBITelemetryManager;
    private final ILogger mLogger;
    private final SdkApplicationContext mSdkApplicationContext;
    // private final ITeamsTelemetryLoggerProvider mTeamsTelemetryLoggerProvider;
//    private final IPreferences mPreferences;
//    private final AuthenticatedUser mAuthenticatedUser;
//    private final IExperimentationManager mExperimentationManager;
//    private SDKTeamsTelemetryLogger mSDKTeamsTelemetryLogger = null;

    @Inject
    public SdkLoggerModule(@NonNull ReactApplicationContext reactContext,
                           @NonNull /*@PlatformAppId*/ String moduleId,
                           @NonNull ITeamsApplication teamsApplication,
//                           @NonNull IUserBITelemetryManager userBITelemetryManager,
                           @NonNull ILogger logger,
                           @NonNull SdkApplicationContext sdkApplicationContext
//                           @NonNull ITeamsTelemetryLoggerProvider teamsTelemetryLoggerProvider,
//                           @NonNull IPreferences preferences,
//                           @Nullable AuthenticatedUser authenticateduser,
//                           @NonNull IExperimentationManager experimentationManager
    ) {
        super(reactContext, moduleId);
        mTeamsApplication = teamsApplication;
       // mUserBITelemetryManager = userBITelemetryManager;
        mLogger = logger;
        mSdkApplicationContext = sdkApplicationContext;
//        mTeamsTelemetryLoggerProvider = teamsTelemetryLoggerProvider;
//        mPreferences = preferences;
//        mAuthenticatedUser = authenticateduser;
//        mExperimentationManager = experimentationManager;
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void setAriaTenant(String tenantToken) {
//        mSDKTeamsTelemetryLogger = new SDKTeamsTelemetryLogger(tenantToken,
//                                                               mTeamsApplication,
//                                                               mTeamsTelemetryLoggerProvider,
//                                                               mPreferences,
//                                                               mAuthenticatedUser,
//                                                               mExperimentationManager,
//                                                               mUserBITelemetryManager);
        logMessage(LogPriority.WARNING, LOG_TAG, "Call to unimplemented mathod: SdkLoggerModule::setAriaTenant");
    }

    @ReactMethod
    public void logVerbose(String tag, String message) {
        logMessage(LogPriority.VERBOSE, tag, message);
    }

    @ReactMethod
    public void logDebug(String tag, String message) {
        logMessage(LogPriority.DEBUG, tag, message);
    }

    @ReactMethod
    public void logInformation(String tag, String message) {
//        // TODO: remove these conditionals once telemetry logging is supported for RN modules, keep "logMessage(...)"
//        if (tag.equals(ICON_PRESS)) {
//            mUserBITelemetryManager.logOrgChartTelemetry(
//                    UserBIType.ActionScenario.openContactCard,
//                    UserBIType.ActionScenarioType.nav,
//                    UserBIType.MODULE_NAME_CONTACT_CARD_BUTTON,
//                    null
//            );
//        } else if (tag.equals(CARD_PRESS)) {
//            mUserBITelemetryManager.logOrgChartTelemetry(
//                    UserBIType.ActionScenario.viewOrgChart,
//                    UserBIType.ActionScenarioType.nav,
//                    UserBIType.MODULE_NAME_CONTACT_ROW,
//                    null
//            );
//        } else {
            logMessage(LogPriority.INFO, tag, message);
//        }
    }

    @ReactMethod
    public void logWarning(String tag, String message) {
        logMessage(LogPriority.WARNING, tag, message);
    }

    @ReactMethod
    public void logError(String tag, String message) {
        logMessage(LogPriority.ERROR, tag, message);
    }

    public void logError(String tag, @NonNull Throwable error, String message) {
        logMessage(LogPriority.ERROR, tag, String.format(Locale.ENGLISH, "%s\n%s", StackTraceUtilities.getStackTraceString(error), message));
    }

    private void logMessage(@LogPriority int logPriority, String tag, String message) {
        // If custom aria tenant has been set RN app, send logs in their aria trace table too
//        if (mSDKTeamsTelemetryLogger != null) {
//            // log events (DEBUG, INFO, WARN, ERROR) if the logging is enabled else log INFO, WARNING and ERROR events.
//            EventPriority priority = EventPriority.NORMAL;
//            switch (logPriority) {
//                case LogPriority.WARNING:
//                case LogPriority.ERROR:
//                case LogPriority.ASSERT:
//                    priority = EventPriority.HIGH;
//                    // Note: this case statement does not have break on purpose as we need to log trace with updated priority
//                case LogPriority.DEBUG:
//                case LogPriority.INFO:
//                    final EventProperties properties = new EventProperties("trace");
//                    properties.setProperty(TRACE_MESSAGE_COLUMN, message);
//                    properties.setProperty(TRACE_SOURCE_COLUMN, tag);
//                    properties.setPriority(priority);
//                    TraceLevel traceLevel = getTraceLevel(logPriority);
//                    mSDKTeamsTelemetryLogger.logTraceEvent(traceLevel, message, properties);
//                    break;
//                case LogPriority.ADAL_INFO:
//                case LogPriority.VERBOSE:
//                default:
//                    // Aria should never log Verbose & ADAL_INFO as it might have PII Data.
//                    break;
//            }
//        }
//
        // Log in Teams default aria trace and BRB logs
        SdkAppManifest sdkAppManifest = mSdkApplicationContext.getAppManifest();

        // Logging the message directly, as currently all user of this API is within Microsoft. Any PII related issue should be assigned to
        // respective team on the basis of App name being mentioned in the log.
        mLogger.log(logPriority, LOG_TAG, "App: %s, Version: %s, SdkVersion: %s, MinSdkVersion: %s, NativeSdkVersion: %d, Tag: %s, %s",
                    getModuleId(), sdkAppManifest.version, sdkAppManifest.targetReactNativeSdkVersion, sdkAppManifest.minReactNativeSdkVersion,
                    sdkAppManifest.targetNativeSdkVersion, tag, message);
    }

    @NonNull
    private TraceLevel getTraceLevel(@LogPriority int logPriority) {
        switch (logPriority) {
            case LogPriority.VERBOSE:
                return TraceLevel.VERBOSE;
            case LogPriority.ADAL_INFO:
                return TraceLevel.VERBOSE;
            case LogPriority.DEBUG:
            case LogPriority.INFO:
                return TraceLevel.INFORMATION;
            case LogPriority.WARNING:
                return TraceLevel.WARNING;
            case LogPriority.ERROR:
            case LogPriority.ASSERT:
                return TraceLevel.ERROR;
            default:
                return TraceLevel.VERBOSE;
        }
    }
}
