package com.microsoft.hubapphost.impls;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.teams.logger.ILogger;
import com.microsoft.teams.logger.ITelemetryLogger;
import com.microsoft.teams.services.diagnostics.telemetryschema.ScenarioContext;
import com.microsoft.teams.services.diagnostics.telemetryschema.StepStatus;
import com.microsoft.teams.storage.IExperimentationManager;
import com.microsoft.teams.core.preferences.IPreferences;
import com.microsoft.teams.core.services.IScenarioManager;

import java.util.Map;

import javax.inject.Inject;

public class ScenarioManagerImpl implements IScenarioManager {

    @NonNull IExperimentationManager mExperimentationManager;
    @NonNull ITelemetryLogger mTelemetryLogger;
    @NonNull ILogger mLogger;
    @NonNull IPreferences mPreferences;

    @Inject public ScenarioManagerImpl(@NonNull IExperimentationManager experimentationManager,
                                       @NonNull ITelemetryLogger telemetryLogger,
                                       @NonNull ILogger logger,
                                       @NonNull IPreferences preferences) {
        mExperimentationManager = experimentationManager;
        mTelemetryLogger = telemetryLogger;
        mLogger = logger;
        mPreferences = preferences;
    }
//)

    @Override
    public ScenarioContext startScenario(String scenarioName, @Nullable String instrumentationSource, @Nullable Map<String, Object> databag, String... tags) {
        return new ScenarioContext(scenarioName,
                StepStatus.INCOMPLETE,
                null,
                mExperimentationManager,
                mTelemetryLogger,
                mLogger,
//            @NonNull ITestUtilitiesWrapper testUtilitiesWrapper,
//            @NonNull INotificationUtilitiesWrapper notificationUtilitiesWrapper,
//            @NonNull ApplicationUtilities applicationUtilities,
                "instrumentationSource",
                databag,
                mPreferences,
//            @NonNull IUserConfiguration userConfiguration,
//            @Nullable AuthenticatedUser authenticatedUser,
                null);
    }

    @NonNull
    @Override
    public ScenarioContext startScenario(String scenarioName, String... tags) {
        return new ScenarioContext(scenarioName,
                StepStatus.INCOMPLETE,
                null,
                mExperimentationManager,
                mTelemetryLogger,
                mLogger,
//            @NonNull ITestUtilitiesWrapper testUtilitiesWrapper,
//            @NonNull INotificationUtilitiesWrapper notificationUtilitiesWrapper,
//            @NonNull ApplicationUtilities applicationUtilities,
                "instrumentationSource",
                null,
                mPreferences,
//            @NonNull IUserConfiguration userConfiguration,
//            @Nullable AuthenticatedUser authenticatedUser,
                tags);
    }

    @Override
    public ScenarioContext startScenario(String scenarioName, @NonNull ScenarioContext parentScenarioContext, String... tags) {
        return new ScenarioContext(scenarioName,
                StepStatus.INCOMPLETE,
                parentScenarioContext,
                mExperimentationManager,
                mTelemetryLogger,
                mLogger,
//            @NonNull ITestUtilitiesWrapper testUtilitiesWrapper,
//            @NonNull INotificationUtilitiesWrapper notificationUtilitiesWrapper,
//            @NonNull ApplicationUtilities applicationUtilities,
                "instrumentationSource",
                null,
                mPreferences,
//            @NonNull IUserConfiguration userConfiguration,
//            @Nullable AuthenticatedUser authenticatedUser,
                tags);
    }

    @Override
    public ScenarioContext getScenario(@Nullable String stepId) {
        return null;
    }

    @Override
    public void endScenarioOnError(@Nullable ScenarioContext scenarioContext, @NonNull String scenarioStatusCode, @NonNull String scenarioStatusReason, String... tags) {
        scenarioContext.endScenarioOnError(scenarioStatusCode,  scenarioStatusReason, "", tags);
    }

    @Override
    public void endScenarioOnSuccess(@Nullable ScenarioContext scenarioContext, String... tags) {
        scenarioContext.endScenarioOnSuccess(tags);
    }
}