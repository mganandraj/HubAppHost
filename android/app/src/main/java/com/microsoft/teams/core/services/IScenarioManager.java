/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.core.services;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Supplier;

import com.microsoft.skype.teams.data.BaseException;
import com.microsoft.skype.teams.services.diagnostics.StatusCode;
import com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioContext;
import com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioMessageOrigin;
import com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName;

import java.util.Map;

/**
 * Scenario manager interface.
 */
public interface IScenarioManager {
    /**
     * Start a scenario
     *
     * @param scenarioName the name of the scenario to start
     * @param instrumentationSource the source of where this instrumentation is coming from
     * @param tags additional context surrounding the scenario
     * @return the context of the scenario that has started
     */
    ScenarioContext startScenario(@ScenarioName String scenarioName, @Nullable String instrumentationSource, @Nullable Map<String, Object> databag, String... tags);
//
//    ScenarioContext startMessageScenario(/*@ScenarioName*/ String scenarioName, @Nullable String correlationId, String... tags);
//
//    ScenarioContext startMessageScenario(/*@ScenarioName*/ String scenarioName, @Nullable String correlationId, boolean isContact, String... tags);
//
//    @NonNull
//    ScenarioContext startScenario(/*@ScenarioName*/ String scenarioName, String... tags);
//
//    @NonNull
//    ScenarioContext startScenario(/*@ScenarioName*/ String scenarioName, boolean polluteCustomerLogs, String... tags);
//
//    ScenarioContext startScenario(/*@ScenarioName*/ String scenarioName,
//                                  @NonNull ScenarioContext parentScenarioContext,
//                                  @Nullable String instrumentationSource,
//                                  @Nullable Map<String, Object> databag,
//                                  String... tags);
//
//    ScenarioContext startScenario(@Nullable Map<String, Object> databag,
//                                  Supplier<ScenarioContext> scenarioContextSupplier);
//
//    /**
//     * Start a scenario with reference to a parent scenario
//     *
//     * @param scenarioName the name of the scenario to start
//     * @param parentScenarioContext the context of the parent scenario
//     * @param tags additional context surrounding the scenario
//     * @return the Id of the scenario that has started
//     */
//    ScenarioContext startScenario(/*@ScenarioName*/ String scenarioName, @NonNull ScenarioContext parentScenarioContext, String... tags);
//
//    ScenarioContext startScenario(/*@ScenarioName*/ String scenarioName, @NonNull ScenarioContext parentScenarioContext, boolean polluteCustomerLogs, String... tags);
//
//    ScenarioContext logSingleScenarioOnSuccess(/*@ScenarioName*/ String scenarioName);
//
//    /**
//     * Start a single-event scenario. Single Scenarios are a single-step event. There is no timing information associated with this scenario. Instead it is a single instant and
//     * doesn't result in a start/stop
//     *
//     * @param scenarioName the name of the scenario to start
//     * @param parentScenarioContext the context of the parent scenario
//     * @param instrumentationSource the source of where this instrumentation is coming from
//     * @param databag JSON databag for extra scenario properties
//     * @param tags additional context surrounding the scenario
//     * @return the context of the scenario that has started
//     */
//    ScenarioContext logSingleScenarioOnSuccess(/*@ScenarioName*/ String scenarioName,
//                                               @Nullable ScenarioContext parentScenarioContext,
//                                               @Nullable String instrumentationSource,
//                                               @Nullable Map<String, Object> databag,
//                                               String... tags);
//
//    void logSingleScenarioOnFailure(/*@ScenarioName*/ String scenarioName,
//                                    @NonNull @StatusCode.StatusCodeValue String scenarioStatusCode,
//                                    @NonNull String scenarioStatusReason);
//
//    void logMessageScenarioOnSuccess(/*@ScenarioName*/ String scenarioName,
//                                     String correlationId,
//                                     @ScenarioMessageOrigin String messageOrigin,
//                                     boolean isSampled,
//                                     String... tags);
//
//    /**
//     * Start a single-event scenario. Single Scenarios are a single-step event. There is no timing information associated with this scenario. Instead it is a single instant and
//     * doesn't result in a start/stop
//     *
//     * @param scenarioName the name of the scenario to start
//     * @param parentScenarioContext the context of the parent scenario
//     * @param instrumentationSource the source of where this instrumentation is coming from
//     * @param databag JSON databag for extra scenario properties
//     * @param scenarioStatusCode The error that occurred
//     * @param scenarioStatusReason A longer description of the error
//     * @param tags additional context surrounding the scenario
//     * @return the context of the scenario that has started
//     */
//    ScenarioContext logSingleScenarioOnError(/*@ScenarioName*/ String scenarioName,
//                                             @Nullable ScenarioContext parentScenarioContext,
//                                             @Nullable String instrumentationSource,
//                                             @Nullable Map<String, Object> databag,
//                                             @NonNull String scenarioStatusCode,
//                                             @NonNull String scenarioStatusReason,
//                                             String... tags);
//
//    ScenarioContext getScenario(@Nullable String stepId);
//
//    /**
//     * Ends a scenario, and all parent scenarios, with an error.
//     *
//     * @param scenarioContext the scenario to end
//     * @param scenarioStatusCode The error that occurred
//     * @param scenarioStatusReason A longer description of the error
//     * @param tags additional context surrounding the scenario
//     */
//    void endScenarioChainOnError(@NonNull ScenarioContext scenarioContext,
//                                 @NonNull @StatusCode.StatusCodeValue String scenarioStatusCode,
//                                 @NonNull String scenarioStatusReason,
//                                 String... tags);
//
//    void endScenarioChainOnError(@NonNull ScenarioContext scenarioContext,
//                                 @NonNull BaseException exception,
//                                 String... tags);
//
//    /**
//     * Ends a scenario with an error
//     *
//     * @param scenarioContext the scenario to end
//     * @param scenarioStatusCode The error that occurred
//     * @param scenarioStatusReason A longer description of the error
//     * @param tags additional context surrounding the scenario
//     */
    void endScenarioOnError(@Nullable ScenarioContext scenarioContext,
                            @NonNull @StatusCode.StatusCodeValue String scenarioStatusCode,
                            @NonNull String scenarioStatusReason,
                            String... tags);
//
//    void endScenarioOnError(@Nullable String stepId,
//                            @NonNull @StatusCode.StatusCodeValue String scenarioStatusCode,
//                            @NonNull String scenarioStatusReason,
//                            String... tags);
//
//    void endScenarioOnError(@Nullable ScenarioContext scenarioContext,
//                            @NonNull BaseException exception,
//                            String... tags);
//
//    void endScenarioChainOnIncomplete(@NonNull ScenarioContext scenarioContext,
//                                      @NonNull BaseException exception,
//                                      String... tags);
//
//    /**
//     * Ends a scenario, and all parent scenarios, with an incomplete
//     *
//     * @param scenarioContext the scenario to end
//     * @param scenarioStatusCode The reason the scenario has ended.
//     * @param scenarioStatusReason A longer description of the reason
//     * @param tags additional context surrounding the scenario
//     */
//    void endScenarioChainOnIncomplete(@NonNull ScenarioContext scenarioContext,
//                                      @NonNull @StatusCode.StatusCodeValue String scenarioStatusCode,
//                                      @NonNull String scenarioStatusReason,
//                                      String... tags);
//
//    void endScenarioOnIncomplete(@NonNull ScenarioContext scenarioContext,
//                                 @NonNull BaseException exception,
//                                 String... tags);
//
//    /***
//     * Ends a scenario with an incomplete or error or cancel depending on the errorType
//     *
//     * @param scenarioContext the scenario to end
//     * @param baseException exception
//     * @param tags additional context surrounding the scenario
//     */
//    void endScenario(@NonNull ScenarioContext scenarioContext, @NonNull BaseException baseException, String... tags);
//
//    /**
//     * Ends a scenario with an incomplete
//     *
//     * @param scenarioContext the scenario to end
//     * @param scenarioStatusCode The reason the scenario has ended
//     * @param scenarioStatusReason A longer description of the reason
//     * @param tags additional context surrounding the scenario
//     */
//    void endScenarioOnIncomplete(@NonNull ScenarioContext scenarioContext,
//                                 @NonNull @StatusCode.StatusCodeValue String scenarioStatusCode,
//                                 @NonNull String scenarioStatusReason,
//                                 String... tags);
//
//    void endScenarioOnIncomplete(@Nullable String stepId,
//                                 @NonNull @StatusCode.StatusCodeValue String scenarioStatusCode,
//                                 @NonNull String scenarioStatusReason,
//                                 String... tags);
//
//    void endScenarioChainOnCancel(@NonNull ScenarioContext scenarioContext,
//                                  @NonNull BaseException exception,
//                                  String... tags);
//
//    /**
//     * Ends a scenario, and all parent scenarios with a cancel.
//     *
//     * @param scenarioContext the scenario to end
//     * @param scenarioStatusCode The reason the scenario has been cancelled
//     * @param scenarioStatusReason A longer description of the reason
//     * @param tags additional context surrounding the scenario
//     */
//    void endScenarioChainOnCancel(@NonNull ScenarioContext scenarioContext,
//                                  @NonNull @StatusCode.StatusCodeValue String scenarioStatusCode,
//                                  @NonNull String scenarioStatusReason,
//                                  String... tags);
//
//    void endScenarioOnCancel(@Nullable ScenarioContext scenarioContext,
//                             @NonNull BaseException exception,
//                             String... tags);
//
//    /**
//     * Ends a scenario with a cancel
//     *
//     * @param scenarioContext the scenario to end
//     * @param scenarioStatusCode The reason the scenario has been cancelled
//     * @param scenarioStatusReason A longer description of the reason
//     * @param tags additional context surrounding the scenario
//     */
//    void endScenarioOnCancel(@Nullable ScenarioContext scenarioContext,
//                             @NonNull @StatusCode.StatusCodeValue String scenarioStatusCode,
//                             @NonNull String scenarioStatusReason,
//                             String... tags);
//
//    void endScenarioOnCancel(@Nullable String stepId,
//                             @NonNull @StatusCode.StatusCodeValue String scenarioStatusCode,
//                             @NonNull String scenarioStatusReason,
//                             String... tags);
//
//    /**
//     * Ends a scenario with a success.
//     *
//     * @param scenarioContext the scenario to end
//     * @param tags additional context surrounding the scenario
//     */
    void endScenarioOnSuccess(@Nullable ScenarioContext scenarioContext, String... tags);
//
//    /**
//     * Ends a scenario with a success.
//     *
//     * @param scenarioStatusCode scenario statusCode
//     * @param scenarioContext the scenario to end
//     * @param tags additional context surrounding the scenario
//     */
//    void endScenarioOnSuccessWithStatusCode(@Nullable ScenarioContext scenarioContext, @NonNull @StatusCode.StatusCodeValue String scenarioStatusCode, String... tags);
//
//    /**
//     * IMPORTANT - Please don't use this method unless absolutely required. In almost all cases, endScenarioOnSuccess should suffice
//     * This was added as a special hack for measuring app launch time without adding to app launch latency
//     * Refer to Bug 779562: Send app launch time in Scenario Delta field for more details
//     */
//    void endScenarioOnSuccessWithTimeTaken(@Nullable ScenarioContext scenarioContext, long scenarioTimeTaken, String... tags);
//
//    /**
//     * Ends a scenario, and all parent scenarios, with a success.
//     *
//     * @param scenarioContext the scenario to end
//     * @param tags additional context surrounding the scenario
//     */
//    void endScenarioChainOnSuccess(@NonNull ScenarioContext scenarioContext, String... tags);
//
//    void endScenarioOnSuccess(@Nullable String stepId, String... tags);
//
//    /**
//     * Add additional key-value tags to the scenario
//     *
//     * @param scenarioContext the scenario to append to
//     * @param key the key of the tag
//     * @param value the value of the tag
//     */
//    void addKeyValueTags(@NonNull ScenarioContext scenarioContext, @NonNull String key, @NonNull String value);
//
//    @Nullable
//    Map<String, Object> getDataBag();



}
