/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.mobilemodules;

import android.content.Context;

import androidx.annotation.NonNull;

//import com.microsoft.teams.data.events.DataEvents;
//import com.microsoft.teams.data.events.TenantOrAccountSwitchedData;
//import com.microsoft.teams.events.EventHandler;
//import com.microsoft.teams.events.IEventBus;
//import com.microsoft.teams.events.IEventHandler;
//import com.microsoft.teams.events.IHandlerCallable;
//import com.microsoft.teams.injection.ApplicationContext;
import com.microsoft.teams.logger.ILogger;
import com.microsoft.teams.logger.LogPriority;
//import com.microsoft.teams.nativemodules.INativePackagesProvider;
import com.microsoft.teams.sdk.SdkAppRunnableHelper;
import com.microsoft.teams.services.diagnostics.StatusCode;
import com.microsoft.teams.services.diagnostics.telemetryschema.ScenarioContext;
import com.microsoft.teams.services.diagnostics.telemetryschema.ScenarioName;
import com.microsoft.teams.storage.IExperimentationManager;
import com.microsoft.teams.storage.dao.appdefinition.AppDefinitionDao;
import com.microsoft.teams.storage.tables.AppDefinition;
import com.microsoft.teams.utilities.java.StringUtils;
import com.microsoft.teams.androidutils.tasks.CancellationToken;
import com.microsoft.teams.androidutils.tasks.ITaskRunner;
import com.microsoft.teams.androidutils.tasks.TaskUtilities;
import com.microsoft.teams.core.app.ITeamsApplication;
//import com.microsoft.teams.core.data.extensions.IFreDataExtension;
//import com.microsoft.teams.injection.UserScope;
import com.microsoft.teams.core.models.GlobalPreferences;
//import com.microsoft.teams.core.nativemodules.NativePackage;
import com.microsoft.teams.core.preferences.IPreferences;
import com.microsoft.teams.core.services.IScenarioManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.inject.Inject;

import bolts.Continuation;
import bolts.Task;

import static com.microsoft.teams.services.diagnostics.telemetryschema.ScenarioTag.SYNC_ALL_RN_APPS;
//import static com.microsoft.teams.core.models.now.card.suffix.SuffixFactory.get;

/**
 * Responsible to sync mobile modules and actions post syncing
 */
//@UserScope
public class MobileModuleSyncManager implements IMobileModuleSyncManager {
    private static final String LOG_TAG = "MobileModuleSyncManager";
    private final ITaskRunner mTaskRunner;
    private final ITeamsApplication mTeamsApplication;
    private final ILogger mLogger;
    private final IScenarioManager mScenarioManager;
    private final IExperimentationManager mExperimentationManager;
//    private final IEventBus mEventBus;
    private final IMobileModuleManager mMobileModuleManager;
    private final AppDefinitionDao mAppDefinitionDao;
    private final Context mContext;
//    private final INativePackagesProvider mNativePackagesProvider;
    private AtomicBoolean mSyncing;
    private AtomicBoolean mShouldSyncAgain;
    private IPreferences mPreferences;
    private AtomicBoolean mInitializeMobileModules;

    // We have received a tenant or account Switched notification
//    private final IEventHandler<TenantOrAccountSwitchedData> mTenantOrAccountSwitchedEventHandler = EventHandler.background(
//            new IHandlerCallable<TenantOrAccountSwitchedData>() {
//                @Override
//                public void handle(@Nullable final TenantOrAccountSwitchedData tenantOrAccountSwitchedData) {
//                    mLogger.log(LogPriority.DEBUG, LOG_TAG, "Received event to switch tenant or account");
//                    syncMobileModules();
//                }
//            }
//    );
//
//    // State needed for initialization work after user entitlement during FRE.
//    private final IEventHandler<Object> mUserEntitlementsUpdatedEventHandler = EventHandler.background(
//            new IHandlerCallable<Object>() {
//                @Override
//                public void handle(@Nullable Object data) {
//                    if (!mInitializeMobileModules.getAndSet(false)) {
//                        mLogger.log(LogPriority.DEBUG, LOG_TAG, "User entitlements update event received after FRE. Ignoring.");
//                        return;
//                    }
//
//                    mLogger.log(LogPriority.INFO, LOG_TAG,
//                            "User entitlements update event received. Proceeding with forced sync on mobile modules.");
//                    ScenarioContext scenarioContext = mScenarioManager.startScenario(ScenarioName.SCENARIO_SYNC_MOBILE_MODULES);
//                    MobileModuleSyncManager.this.syncMobileModules()
//                            .continueWithTask(
//                                    syncedTask -> initializeMobileModules())
//                            .continueWith(initTask -> {
//                                mScenarioManager.endScenarioOnSuccess(scenarioContext);
//                                mLogger.log(LogPriority.DEBUG, LOG_TAG, "Forced sync on mobile modules completed.");
//                                return null;
//                            });
//                }
//            });
//

    @Inject
    public MobileModuleSyncManager(@NonNull /*@ApplicationContext*/ Context context,
                                   @NonNull ITeamsApplication teamsApplication,
                                   @NonNull ILogger logger,
                                   @NonNull IScenarioManager scenarioManager,
//                                   @NonNull IEventBus eventBus,
                                   @NonNull ITaskRunner taskRunner,
                                   @NonNull IExperimentationManager experimentationManager,
//                                   @NonNull INativePackagesProvider nativePackagesProvider,
                                   @NonNull IPreferences preferences,
                                   @NonNull IMobileModuleManager mobileModuleManager,
                                   @NonNull AppDefinitionDao appDefinitionDao) {
        mContext = context;
        mLogger = logger;
        mTeamsApplication = teamsApplication;
//        mEventBus = eventBus;
        mScenarioManager = scenarioManager;
        mTaskRunner = taskRunner;
        mExperimentationManager = experimentationManager;
//        mNativePackagesProvider = nativePackagesProvider;
        mPreferences = preferences;
        mMobileModuleManager = mobileModuleManager;
        mAppDefinitionDao = appDefinitionDao;

        mSyncing = new AtomicBoolean();
        mShouldSyncAgain = new AtomicBoolean();
        mInitializeMobileModules = new AtomicBoolean();

//        subscribeToEventBus();
    }

    /**
     * Indicator if we are in an FRE flow. If so, mobile module initialization has to be done
     * We do this after user entitlements are downloaded using an event handler.
     */
    @Override
    public void initializeMobileModulesAfterFre() {
        mInitializeMobileModules.set(true);
    }

//    private void subscribeToEventBus() {
//        mEventBus.subscribe(DataEvents.TENANT_OR_ACCOUNT_SWITCHED, mTenantOrAccountSwitchedEventHandler);
//        mEventBus.subscribe(DataEvents.USER_ENTITLEMENTS_UPDATED, mUserEntitlementsUpdatedEventHandler);
//    }

    @Override
    public Task<Void> syncMobileModules() {

        // If syncing is in progress, re-trigger sync after current sync finish
        if (mSyncing.getAndSet(true)) {
            mShouldSyncAgain.set(true);
            mLogger.log(LogPriority.DEBUG, LOG_TAG, "Already syncing mobile modules!");
        }

        mLogger.log(LogPriority.DEBUG, LOG_TAG, "Syncing mobile modules.");
        Task<Void> syncTask = mTaskRunner.runOnBackgroundThread(() -> {
            ScenarioContext scenarioContext = mScenarioManager.startScenario(ScenarioName.SYNC_MOBILE_MODULES);
            TaskUtilities.safeTask(syncMobileModulePackages(mAppDefinitionDao.getAppDefinitionsWithMobileModules()))
                    .continueWith((Continuation<Void, Void>) task -> {
                        endMobileModuleSyncScenarioFromTaskResult(scenarioContext, task);
                        mSyncing.set(false);
                        if (mShouldSyncAgain.getAndSet(false)) {
                            syncMobileModules();
                        } else {
                            // Fire event for module refresh if needed (if modules have updated or otherwise changed)
                            if (mPreferences.getBooleanGlobalPref(GlobalPreferences.MODULE_REFRESH_NEEDED, false)) {
                                mLogger.log(LogPriority.DEBUG, LOG_TAG, "Refreshing mobile modules tabs.");
//                                mEventBus.post(MobileModuleEvents.MOBILE_MODULES_SYNCED, null);
                                mPreferences.putBooleanGlobalPref(GlobalPreferences.MODULE_REFRESH_NEEDED, false);
                            }

                            // TODO
//                          preLoadRNApp();
                        }

                        return null;
                    });
        }, CancellationToken.NONE);

        return syncTask;
    }

    private void endMobileModuleSyncScenarioFromTaskResult(@NonNull ScenarioContext scenarioContext, @NonNull Task<Void> task) {
        if (task.isFaulted()) {
            scenarioContext.endScenarioOnError(StatusCode.UNKNOWN, "Mobile module sync failed", StringUtils.EMPTY_STRING);
        } else if (task.isCancelled()) {
            scenarioContext.endScenarioOnCancel(StatusCode.CANCELLED, "Mobile module sync canceled", StringUtils.EMPTY_STRING);
        } else {
            scenarioContext.endScenarioOnSuccess();
        }
    }

    /**
     * Helper method to start preloading the required Rn apps.
     * This will kick off the React native bridge creation onto background threads.
     * This should be invoked from UI thread.
     */
    private void preLoadRNApp() {
        String[] listOfRNAppsForPreInitialization = mExperimentationManager.getListOfRNAppForPreInit();
        if (listOfRNAppsForPreInitialization == null || listOfRNAppsForPreInitialization.length == 0) {
            return;
        }
        mTaskRunner.runOnMainThread(() -> {
            for (String appId : listOfRNAppsForPreInitialization) {
                // TODO
                // SdkAppRunnableHelper.preInitializeRNApp(mTeamsApplication, get(appId));
            }
        });
    }

    private Task<Void> syncMobileModulePackages(List<AppDefinition> appDefinitions) {
        mLogger.log(LogPriority.DEBUG, LOG_TAG, "Found %d modules to sync package for.", appDefinitions.size());
        List<Task<Void>> syncMobileModulePackageTasks = new ArrayList<>();
        for (AppDefinition definition : appDefinitions) {
            IMobileModule mobileModule = mMobileModuleManager.getMobileModule(definition.appId);
            if (mobileModule != null) {
                syncMobileModulePackageTasks.add(mobileModule.syncModule(SYNC_ALL_RN_APPS));
            }
        }

        return Task.whenAll(syncMobileModulePackageTasks);
    }

//    private Task<Void> initializeMobileModules() {
//        mLogger.log(LogPriority.DEBUG, LOG_TAG, "Invoking application created on native packages.");
//        for (NativePackage nativePackage : mNativePackagesProvider.getNativePackages()) {
//            nativePackage.onApplicationCreatedIfNeeded(mTeamsApplication.getApplication());
//        }
//
//        mLogger.log(LogPriority.DEBUG, LOG_TAG, "Running FRE for data extensions.");
//        List<Task<Void>> syncFreData = new ArrayList<>();
//        for (IFreDataExtension freDataExtension : mMobileModuleManager.getFreDataExtensions()) {
//            if (freDataExtension == null) {
//                continue;
//            }
//            syncFreData.add(freDataExtension.syncFreData(mContext));
//        }
//        return Task.whenAll(syncFreData);
//    }
}
