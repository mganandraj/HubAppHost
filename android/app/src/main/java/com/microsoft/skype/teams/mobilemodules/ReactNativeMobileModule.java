/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.mobilemodules;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.skype.teams.logger.ILogger;
import com.microsoft.skype.teams.logger.LogPriority;
// import com.microsoft.skype.teams.mobilemodules.injection.ActivityFeedExtensionFactory;
import com.microsoft.skype.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.skype.teams.sdk.SdkApplicationContext;
import com.microsoft.skype.teams.sdk.SdkHelper;
// import com.microsoft.skype.teams.sdk.models.SdkTabContext;
import com.microsoft.skype.teams.sdk.rnbundle.ISdkBundleDownloadManager;
import com.microsoft.skype.teams.sdk.utils.SdkRunnerUtils;
import com.microsoft.skype.teams.services.configuration.AppConfiguration;
//import com.microsoft.skype.teams.storage.IExperimentationManager;
//import com.microsoft.skype.teams.storage.dao.rnapps.RNAppsDao;
//import com.microsoft.skype.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.skype.teams.storage.models.MobileModuleDefinition;
import com.microsoft.skype.teams.storage.tables.RNApp;
import com.microsoft.skype.teams.storage.tables.RNBundle;
// import com.microsoft.skype.teams.tasks.TasksActivityFeedExtension;
import com.microsoft.skype.teams.utilities.BundleTypeAdapterFactory;
//import com.microsoft.skype.teams.views.activities.CustomTabsShellActivity;
//import com.microsoft.skype.teams.views.fragments.SdkAppHostFragment;
//import com.microsoft.skype.teams.views.utilities.SettingsUtilities;
import com.microsoft.teams.androidutils.NavigationParcel;
import com.microsoft.teams.core.app.ITeamsApplication;
//import com.microsoft.teams.core.data.extensions.IActivityFeedExtension;
//import com.microsoft.teams.injection.PlatformAppScope;
import com.microsoft.teams.core.preferences.IPreferences;
//import com.microsoft.teams.core.services.IScenarioManager;
//import com.microsoft.teams.core.services.configuration.IUserConfiguration;
//import com.microsoft.teams.core.services.navigation.ITeamsNavigationService;
//import com.microsoft.teams.core.views.fragments.BaseFragment;
//import com.microsoft.teamspace.tab.planner.PlannerActivityFeedExtension;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import bolts.Task;
import bolts.TaskCompletionSource;


//import static com.microsoft.skype.teams.mobilemodules.MobileModuleConstants.ORG_CHART_ID;
//import static com.microsoft.skype.teams.mobilemodules.MobileModuleConstants.PARAM_KEY_DEEPLINK;
//import static com.microsoft.skype.teams.mobilemodules.MobileModuleConstants.PARAM_KEY_TAB_CONTEXT;
import static com.microsoft.teams.androidutils.NavigationParcel.NAVIGATION_PARAMS;

/**
 * Implements the React Native mobile module.
 */
// @PlatformAppScope
public class ReactNativeMobileModule extends com.microsoft.skype.teams.mobilemodules.BaseMobileModule {
    private static final String LOG_TAG = "MobileModuleImplReactNative";
    private final ISdkBundleDownloadManager mSdkBundleDownloadManager;
    private final AppConfiguration mAppConfiguration;
//    private final RNAppsDao mRnAppsDao;
//    private final RNBundlesDao mRNBundlesDao;
//    private final SdkApplicationContext.Factory mSdkApplicationContextFactory;
    private final ISdkRunnerAppManager mSdkRunnerAppManager;
//    private final IExperimentationManager mExperimentationManager;
//    private final IScenarioManager mScenarioManager;
//    private final ITeamsNavigationService mTeamsNavigationService;
//    private final ActivityFeedExtensionFactory mActivityFeedExtensionFactory;
    private SdkApplicationContext mSdkApplicationContext;

    @Inject
    ReactNativeMobileModule(@Nullable MobileModuleDefinition mobileModuleDefinition,
                            @NonNull Context context,
//                            @NonNull RNAppsDao rnAppsDao,
//                            @NonNull RNBundlesDao rnBundlesDao,
                            @NonNull ISdkRunnerAppManager sdkRunnerAppManager,
                            @NonNull ITeamsApplication teamsApplication,
                            @NonNull AppConfiguration appConfiguration,
//                            @NonNull SdkApplicationContext.Factory sdkApplicationContextFactory,
                            @NonNull IPreferences preferences,
                            @NonNull ILogger logger,
                            @NonNull ISdkBundleDownloadManager sdkBundleDownloadManager
//                            @NonNull IExperimentationManager experimentationManager,
//                            @NonNull IScenarioManager scenarioManager,
//                            @NonNull ITeamsNavigationService teamsNavigationService,
//                            @NonNull ActivityFeedExtensionFactory activityFeedExtensionFactory
                            ) {
        super(mobileModuleDefinition, context, teamsApplication, preferences, logger);
        mAppConfiguration = appConfiguration;
//        mRnAppsDao = rnAppsDao;
//        mRNBundlesDao = rnBundlesDao;
        mSdkBundleDownloadManager = sdkBundleDownloadManager;
//        mSdkApplicationContextFactory = sdkApplicationContextFactory;
        mSdkRunnerAppManager = sdkRunnerAppManager;
//        mExperimentationManager = experimentationManager;
//        mScenarioManager = scenarioManager;
//        mTeamsNavigationService = teamsNavigationService;
//        mActivityFeedExtensionFactory = activityFeedExtensionFactory;
    }

/*
    @NonNull
    @Override
    public BaseFragment getFragment(@Nullable Object params) {
        String paramsString = null;
        if (params == null) {
            paramsString = null;
        } else if (params instanceof String) {
            paramsString = (String) params;
        } else if (params instanceof Bundle) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(new BundleTypeAdapterFactory())
                    .create();
            paramsString = gson.toJson(params, Bundle.class);
        }

        MobileModuleDefinition moduleDef = mModuleDefinition;
        return SdkAppHostFragment.newInstance(moduleDef.rnPackageUrl, moduleDef.id, paramsString);
    }
*/

    @Override
    public boolean isEnabled() {
        return true;
//        final IUserConfiguration userConfiguration = mTeamsApplication.getUserConfiguration(null);
//
//        // Check if mobile modules are enabled
//        if (!mAppConfiguration.mobileModulesEnabled()) {
//            mLogger.log(LogPriority.DEBUG,
//                    LOG_TAG,
//                    "[isEnabled] mobileModulesEnabled: "
//                            + mAppConfiguration.mobileModulesEnabled());
//            return false;
//        }
//
//        // Check if react native mobile modules are enabled
//        if (!SettingsUtilities.reactNativeMobileModulesEnabled(mLogger,
//                mExperimentationManager,
//                mPreferences)) {
//            mLogger.log(LogPriority.DEBUG,
//                    LOG_TAG,
//                    "[isEnabled] reactNativeMobileModulesEnabled: false");
//            return false;
//        }
//
//        final String appId = mModuleDefinition.appId;
//        boolean isEnabled;
//        String format;
//
//        if (ORG_CHART_ID.equals(mModuleDefinition.id)) {
//            isEnabled = userConfiguration.isOrgChartEnabled();
//            format = String.format("[isEnabled] isOrgChartEnabled: [%s] for app[id: %s]",
//                    isEnabled, appId);
//        } else if (SdkRunnerUtils.isRunnerApp(mModuleDefinition.id)) {
//            isEnabled = SdkRunnerUtils.isRunnerMode();
//            format = String.format("[isEnabled] isRunnerMode: [%s] for Runner app[id: %s]",
//                    isEnabled, appId);
//        } else {
//            isEnabled = mExperimentationManager.isReactNativeAppEnabled(mModuleDefinition.id);
//            format = String.format("[isEnabled] isReactNativeAppEnabled: %s for app[id: %s]",
//                    isEnabled, appId);
//        }
//
//        mLogger.log(LogPriority.DEBUG, LOG_TAG, format);
//        return isEnabled;
    }

    @NonNull
    @Override
    public Task<Void> syncModule(@NonNull String scenarioTag) {
        if (!isEnabled()) {
            return Task.forResult(null);
        }

        try {
            List<MobileModuleDefinition> mobileModuleDefinitions = new ArrayList<>();
            mobileModuleDefinitions.add(mModuleDefinition);
            mSdkBundleDownloadManager.syncRNApps(mobileModuleDefinitions, scenarioTag,/* mRnAppsDao, mScenarioManager, */mLogger/*, mExperimentationManager*/);
            return Task.forResult(null);
        } catch (Exception ex) {
            mLogger.log(LogPriority.ERROR, LOG_TAG, ex, "Failed to sync package from %s.", mModuleDefinition.id);
            return Task.forError(ex);
        }
    }

    @NonNull
    @Override
    public Task<Void> destroyModule(boolean clearStorage) {
        if (mSdkApplicationContext != null) {
            final TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
            if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                mSdkApplicationContext.destroy();
                taskCompletionSource.trySetResult(null);
            } else {
                new Handler(Looper.getMainLooper()).post(() -> {
                    mSdkApplicationContext.destroy();
                    taskCompletionSource.trySetResult(null);
                });
            }
            return taskCompletionSource.getTask();
        }
        return Task.forResult(null);
    }

//    @Nullable
//    @Override
//    public IActivityFeedExtension getActivityFeedExtension() {
//        if (!isEnabled()) {
//            return null;
//        }
//
//        if (MobileModuleConstants.PLANNER_APP_ID.equals(getPackageId())) {
//            return mActivityFeedExtensionFactory.create(PlannerActivityFeedExtension.class);
//        } else if (MobileModuleConstants.TASKS_APP_ID.equals(getPackageId())) {
//            // Legacy ActivityFeedExtension for old Teams Tasks.
//            // TODO: Remove when Tasks is deprecated
//            return mActivityFeedExtensionFactory.create(TasksActivityFeedExtension.class);
//        } else { //Add additional cases here
//            return null;
//        }
//    }

    @WorkerThread
    @Nullable
    @Override
    public SdkApplicationContext getSdkApplicationContext() {
        if (mSdkApplicationContext == null) {
            RNBundle rnBundle = null;
            String appId = getPackageId();
            // if (SdkRunnerUtils.isRunnerApp(appId)) {
                rnBundle = mSdkRunnerAppManager.getRNBundle();
//            } else {
//                RNApp rnApp = mRnAppsDao.fromId(appId); // <- DB call on UI THREAD
//                if (rnApp != null) {
//                    rnBundle = mRNBundlesDao.from(appId, rnApp.bundleVersion); // <- DB call on UI THREAD
//                }
//            }

//            if (rnBundle != null) {
//                mSdkApplicationContext =  mSdkApplicationContextFactory.create(rnBundle);
//            }

            mSdkApplicationContext = new SdkApplicationContext(mTeamsApplication.getApplication(),
                    rnBundle,
                    mTeamsApplication);

        }
        return mSdkApplicationContext;
    }

    @NonNull
    @Override
    public String getPackageId() {
        return mModuleDefinition.id;
    }
//    @Nullable
//    @Override
//    public Intent resolveDeepLink(@NonNull Uri deepLink) {
//        Map<String, Object> navigationParams = new ArrayMap<>();
//        navigationParams.put(CustomTabsShellActivity.PARAM_TAB_ID, getModuleDefinition().id);
//        Bundle extras = new Bundle();
//        extras.putString(PARAM_KEY_DEEPLINK, deepLink.toString());
//
//        // try to fetch tab context if deeplink to a channel tab
//        SdkTabContext tabContext = SdkHelper.fetchTabContextForChannelTab(deepLink, mTeamsNavigationService);
//        if (tabContext != null) {
//            extras.putString(PARAM_KEY_TAB_CONTEXT, tabContext.toJson());
//        }
//
//        navigationParams.put(SdkHelper.MODULE_PARAMS_PARAM_NAME, extras);
//        Intent intent = new Intent(mContext, CustomTabsShellActivity.class);
//        intent.putExtra(NAVIGATION_PARAMS, new NavigationParcel(navigationParams));
//        // If context is not from activity, add FLAG_ACTIVITY_NEW_TASK flag to the intent
//        if (!(mContext instanceof Activity)) {
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        }
//
//        return intent;
//    }
}
