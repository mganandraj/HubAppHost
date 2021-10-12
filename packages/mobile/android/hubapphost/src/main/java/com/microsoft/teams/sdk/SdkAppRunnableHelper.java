/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.appregistry.AppRegistry;
// import com.microsoft.teams.app.SkypeTeamsApplication;
import com.microsoft.teams.bottombar.listeners.IQuickAction;
//import com.microsoft.teams.events.IEventBus;
//import com.microsoft.teams.events.IEventHandler;
import com.microsoft.teams.logger.ILogger;
import com.microsoft.teams.logger.LogPriority;
import com.microsoft.teams.mobilemodules.IMobileModule;
import com.microsoft.teams.mobilemodules.ReactNativeMobileModule;
import com.microsoft.teams.sdk.models.QuickActionConfig;
import com.microsoft.teams.services.diagnostics.telemetryschema.ScenarioContext;
import com.microsoft.teams.services.diagnostics.telemetryschema.ScenarioName;
import com.microsoft.teams.services.threading.Executors;
import com.microsoft.teams.storage.models.MobileModuleDefinition;
import com.microsoft.teams.androidutils.tasks.CancellationToken;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.microsoft.teams.core.services.IScenarioManager;

import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;

/**
 * Provides helper methods for runnables provided by sdk apps.
 */
public final class SdkAppRunnableHelper {
    private static final AtomicLong RUNNABLE_TASK_COUNTER = new AtomicLong();
    private static final int RUNNABLE_EXECUTION_TIME_OUT = 5000; // 5 seconds
    private static final String LOG_TAG = "SdkAppRunnableHelper";
    public static final String KEY_LAUNCH_FOR_PRE_INIT = "launchForPreInit";

    public static <T, E> Task<T> executeRunnable(
            @NonNull final SdkApplicationContext sdkApplicationContext,
            @NonNull final ILogger logger,
            @NonNull final String runnableName,
            @Nullable final WritableMap runnableParams,
            @NonNull final RunnableResultProcessor<T, E> resultHandler,
            @Nullable final CancellationToken cancellationToken,
            final int runnableExecutionTimeOut) {
        return sdkApplicationContext
                .getReactInstanceManagerAfterContextInitialization()
                .continueWithTask(new Continuation<ReactInstanceManager, Task<T>>() {
                    private T mResult;

                    @Override
                    public Task<T> then(Task<ReactInstanceManager> task) {
                        final TaskCompletionSource<T> taskCompletionSource = new TaskCompletionSource<>();
                        if (task.getResult() == null) {
                            taskCompletionSource.trySetError(new IllegalStateException("React context is not set."));
                            return taskCompletionSource.getTask();
                        }

                        ReactContext reactContext = task.getResult().getCurrentReactContext();
                        if (reactContext == null) {
                            taskCompletionSource.trySetError(new IllegalStateException("React context is not set."));
                            return taskCompletionSource.getTask();
                        }

                        final String runnableTaskId = generateRunnableTaskId(sdkApplicationContext.getAppId(), runnableName);
                        final WritableMap finalRunnableParams = runnableParams == null ? new WritableNativeMap() : runnableParams;
                        finalRunnableParams.putString("requestId", runnableTaskId);
                        finalRunnableParams.putMap("appParams", sdkApplicationContext.getAppInitializationParamsAsMap(runnableTaskId));

                        final CountDownLatch latch = new CountDownLatch(1);
//                        final IEventBus eventBus = SkypeTeamsApplication.getApplicationComponent().eventBus();
//                        final IEventHandler<E> eventHandler = new IEventHandler<E>() {
//                            @NonNull
//                            @Override
//                            public String getName() {
//                                return runnableTaskId;
//                            }
//
//                            @Override
//                            public Task<Void> handleEvent(@Nullable E event) {
//                                logger.log(LogPriority.DEBUG, LOG_TAG, "Executed runnable %s.", runnableTaskId);
//                                mResult = resultHandler.processResult(event);
//                                if (!resultHandler.isIncomplete(event)) {
//                                    latch.countDown();
//                                    eventBus.unSubscribe(runnableTaskId, this);
//                                    taskCompletionSource.trySetResult(mResult);
//                                }
//                                return Task.forResult(null);
//                            }
//                        };
//
//                        if (cancellationToken != null) {
//                            cancellationToken.attachCallback(new CancellationToken.ICancellationCallback() {
//                                @Override
//                                public void onCancel() {
//                                    logger.log(LogPriority.DEBUG, LOG_TAG, "Cancelled runnable %s.", runnableTaskId);
//                                    latch.countDown();
//                                    eventBus.unSubscribe(runnableTaskId, eventHandler);
//                                    taskCompletionSource.trySetCancelled();
//                                    SdkAppNativeEventEmitter.emitProviderCallCancelledEvent(sdkApplicationContext, runnableTaskId);
//                                }
//                            });
//                        }
//
//                        eventBus.subscribe(runnableTaskId, eventHandler);

                        CatalystInstance catalystInstance = reactContext.getCatalystInstance();
                        catalystInstance.getJSModule(AppRegistry.class).runApplication(
                                runnableName,
                                finalRunnableParams);

//                        try {
//                            logger.log(LogPriority.DEBUG, LOG_TAG, "Executing runnable %s.", runnableTaskId);
//                            if (!latch.await(runnableExecutionTimeOut, TimeUnit.MILLISECONDS)) {
//                                logger.log(LogPriority.WARNING, LOG_TAG, "Timed out while executing runnable %s. ", runnableTaskId);
//                                eventBus.unSubscribe(runnableTaskId, eventHandler);
//                                if (mResult != null) {
//                                    taskCompletionSource.trySetResult(mResult);
//                                } else {
//                                    taskCompletionSource.trySetError(new TimeoutException());
//                                }
//                            }
//                        } catch (InterruptedException e) {
//                            logger.log(LogPriority.WARNING, LOG_TAG, e, "Interrupted while executing runnable %s.", runnableTaskId);
//                            eventBus.unSubscribe(runnableTaskId, eventHandler);
//                            taskCompletionSource.trySetError(e);
//                        }

                        return taskCompletionSource.getTask();
                    }
                }, Executors.getHighPriorityViewDataThreadPool());
    }

    public static <T, E> Task<T> executeRunnable(
            @NonNull final SdkApplicationContext sdkApplicationContext,
            @NonNull final ILogger logger,
            @NonNull final String runnableName,
            @Nullable final WritableMap runnableParams,
            @NonNull final RunnableResultProcessor<T, E> resultHandler,
            @Nullable final CancellationToken cancellationToken) {
        return executeRunnable(
                sdkApplicationContext, logger, runnableName, runnableParams, resultHandler, cancellationToken, RUNNABLE_EXECUTION_TIME_OUT);
    }

    /**
     * Generates a unique runnable task id.
     */
    private static String generateRunnableTaskId(@NonNull String appId, @NonNull String runnableName) {
        return String.format(Locale.ENGLISH, "%s.%d", generateBaseRunnableTaskId(appId, runnableName), RUNNABLE_TASK_COUNTER.incrementAndGet());
    }

    private static String generateBaseRunnableTaskId(@NonNull String appId, @NonNull String runnableName) {
        return String.format(Locale.ENGLISH, "SdkApp.%s.%s", appId, runnableName);
    }

    private SdkAppRunnableHelper() {
    }

    /**
     * Helper method to launch quick action runnable in RN module
     *
     * @param sdkApplicationContext - sdk app context
     * @param quickActionConfig - quick action config
     * @param quickActionCompletionListener - listener
     */
    public static void executeQuickAction(@NonNull final SdkApplicationContext sdkApplicationContext,
                                          @NonNull final ILogger logger,
                                          @NonNull QuickActionConfig quickActionConfig,
                                          @Nullable final IQuickAction.IQuickActionCompletionListener quickActionCompletionListener) {
        executeQuickAction(
                sdkApplicationContext, logger, quickActionConfig, quickActionCompletionListener, false);
    }

    /**
     * Helper method to launch quick action runnable in RN module
     *
     * @param sdkApplicationContext - sdk app context
     * @param quickActionConfig - quick action config
     * @param quickActionCompletionListener - listener
     * @param launchForPreInitialization - set this to true if we only want to launch the quick action for pre initialization purpose.
     * when the module receives the params.launchForPreInit with true.
     * module developer should only initialize the code
     * and should not trigger any business logic or UI.
     */
    public static void executeQuickAction(@NonNull final SdkApplicationContext sdkApplicationContext,
                                          @NonNull final ILogger logger,
                                          @NonNull QuickActionConfig quickActionConfig,
                                          @Nullable final IQuickAction.IQuickActionCompletionListener quickActionCompletionListener,
                                          boolean launchForPreInitialization) {
        if (QuickActionConfig.QuickActionType.RUNNABLE.equals(quickActionConfig.type)) {
            final SdkAppRunnableHelper.RunnableResultProcessor<Object, Object> resultProcessor = new SdkAppRunnableHelper.RunnableResultProcessor<Object, Object>() {
                @Override
                public boolean isIncomplete(Object result) {
                    return false;
                }

                @Override
                public Object processResult(Object result) {
                    return null;
                }
            };

            WritableMap runnableParams = null;
            if (launchForPreInitialization) {
                runnableParams = new WritableNativeMap();
                runnableParams.putBoolean(KEY_LAUNCH_FOR_PRE_INIT, true);
            }

            final Task<Object> quickActionTask = SdkAppRunnableHelper.executeRunnable(
                    sdkApplicationContext,
                    logger,
                    quickActionConfig.name,
                    runnableParams,
                    resultProcessor,
                    null);

//            if (quickActionCompletionListener != null) {
//                quickActionTask.continueWith(new Continuation<Object, Task<Object>>() {
//                    @Override
//                    public Task<Object> then(Task<Object> task) {
//                        quickActionCompletionListener.onQuickActionCompleted();
//                        return Task.forResult(null);
//                    }
//                }, launchForPreInitialization ? Task.BACKGROUND_EXECUTOR : Executors.getHighPriorityViewDataThreadPool());
//            }
        }
    }


    /**
     * Helper method to pre initialize the rn app into memory. so the subsequent invocations to launch the RN app will be faster.
     * This method support pre loading RN apps with quick actions only. Other apps will be ignored.
     * The quick action runnable will be launched with a { param.launchForPreInit : true }.
     *
     * @param teamsApplication - Teams application
     * @param mobileModule - module to pre init/ pre load.
     */
    @UiThread
    public static void preInitializeRNApp(@NonNull ITeamsApplication teamsApplication,
                                          @NonNull IMobileModule mobileModule) {
        final ILogger logger = teamsApplication.getLogger(null);
        final IScenarioManager scenarioManager = teamsApplication.getScenarioManager(null);
        if (mobileModule instanceof ReactNativeMobileModule && mobileModule.isEnabled()) {
            MobileModuleDefinition mobileModuleDefinition = mobileModule.getModuleDefinition();
            final SdkApplicationContext sdkApplicationContext = mobileModule.getSdkApplicationContext();
            final QuickActionConfig quickActionConfig = QuickActionConfig.getQuickActionConfigForModule(sdkApplicationContext);
            if (quickActionConfig != null && quickActionConfig.isPreInitEnabled) {
                logger.log(LogPriority.INFO, LOG_TAG, "Pre initializing RN module appId: %s", mobileModuleDefinition.id);
                final ScenarioContext scenarioContext = scenarioManager.startScenario(ScenarioName.PRE_INIT_RN_MODULE, quickActionConfig.name);
                final IQuickAction.IQuickActionCompletionListener listener = new IQuickAction.IQuickActionCompletionListener() {
                    @Override
                    public void onQuickActionCompleted() {
                        scenarioContext.endScenarioOnSuccess();
                    }
                };

                SdkAppRunnableHelper.executeQuickAction(sdkApplicationContext, logger, quickActionConfig, listener, true);
            }
        }
    }

    /**
     * Processes the result of a runnable.
     *
     * @param <T> result type
     * @param <E> input type
     */
    public interface RunnableResultProcessor<T, E> {
        boolean isIncomplete(E result);

        T processResult(E result);
    }
}
