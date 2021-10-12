/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.bridge.DefaultNativeModuleCallExceptionHandler;
import com.facebook.react.bridge.FallbackJSBundleLoader;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.shell.MainPackageConfig;
import com.google.gson.Gson;
// import com.horcrux.svg.SvgPackage;
// import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.teams.app.AppStateProvider;
import com.microsoft.teams.data.ThemeColorData;
// import com.microsoft.teams.files.common.IFileTraits;
// import com.microsoft.teams.files.common.SharepointSettings;
// import com.microsoft.teams.helper.UserHelper;
import com.microsoft.teams.logger.ILogger;
import com.microsoft.teams.logger.LogPriority;
// import com.microsoft.teams.mobilemodules.IReactNativeFabricEventLogger;
// import com.microsoft.teams.models.AuthenticatedUser;
import com.microsoft.teams.sdk.models.SdkAppManifest;
import com.microsoft.teams.sdk.models.params.SdkAppHostParams;
import com.microsoft.teams.sdk.models.params.SdkAppInitializationParams;
import com.microsoft.teams.sdk.models.params.SdkAppTheme;
import com.microsoft.teams.sdk.models.params.SdkAppUserParams;
import com.microsoft.teams.sdk.models.params.SdkTelemetryInfo;
//import com.microsoft.teams.sdk.react.injection.SdkPackageFactory;
//import com.microsoft.teams.sdk.react.injection.components.SdkPackageComponent;
import com.microsoft.teams.sdk.react.packages.SdkMainReactPackage;
import com.microsoft.teams.sdk.react.packages.SdkReactPackage;
import com.microsoft.teams.sdk.utils.SdkRunnerUtils;
//import com.microsoft.teams.services.authorization.IAccountManager;
//import com.microsoft.teams.services.images.FrescoImagePipelineConfig;
//import com.microsoft.teams.storage.dao.user.UserDao;
//import com.microsoft.teams.storage.dao.userpreferences.UserPreferencesDao;
import com.microsoft.teams.storage.tables.RNBundle;
//import com.microsoft.teams.storage.tables.User;
//import com.microsoft.teams.views.activities.BaseActivity;
//import com.microsoft.teams.views.activities.MainActivity;
import com.microsoft.teams.core.app.ITeamsApplication;
//import com.microsoft.teams.injection.PlatformAppScope;
//import com.reactnativecommunity.netinfo.NetInfoPackage;
//import com.reactnativecommunity.webview.RNCWebViewPackage;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import bolts.Task;
import bolts.TaskCompletionSource;

/**
 * Application context implementation for an SDK app.
 */
public class SdkApplicationContext {
    private static final String LOG_TAG = "SdkApplicationContext";

    @Nullable
    private ReactInstanceManager mReactInstanceManager;

    private final Application mApplication;
    private final RNBundle mRNBundle;
    private final ITeamsApplication mTeamsApplication;
    // private final IFileTraits mFileTraits;
    // private final IAccountManager mAccountManager;

    private final SdkAppManifest mSdkAppManifest;
    private final SdkAppResourceManager mResourceManager;
    private final String mRnBundleLocation;
    private final String mAppId;
    // private final UserDao mUserDao;
    // private final UserPreferencesDao mUserPreferencesDao;
    //private final SdkPackageComponent.Factory mSdkPackageComponentFactory;
    //private final IReactNativeFabricEventLogger mReactNativeFabricEventLogger;
    private long mBundleSize;

    /**
     * Initializes a new instance of the SDK application context.
     */
    public SdkApplicationContext(@NonNull Application application,
                                 @NonNull RNBundle rnBundle,
                                 @NonNull ITeamsApplication teamsApplication) //,
                                 // @NonNull IFileTraits fileTraits,
                                 // @NonNull IAccountManager accountManager,
                                 // @NonNull UserDao userDao,
                                 // @NonNull UserPreferencesDao userPreferencesDao,
                                 // @NonNull SdkPackageComponent.Factory sdkPackageComponentFactory,
                                 //@NonNull IReactNativeFabricEventLogger reactNativeFabricEventLogger)
                                 {
        mApplication = application;
        mRNBundle = rnBundle;
        mTeamsApplication = teamsApplication;
       /* mFileTraits = fileTraits;
        mAccountManager = accountManager;
        mUserDao = userDao;
        mUserPreferencesDao = userPreferencesDao;
        mSdkPackageComponentFactory = sdkPackageComponentFactory;
        mReactNativeFabricEventLogger = reactNativeFabricEventLogger;*/

        mSdkAppManifest = new Gson().fromJson(rnBundle.manifest, SdkAppManifest.class);
        mRnBundleLocation = rnBundle.bundleLocation;
        mResourceManager = new SdkAppResourceManager(mTeamsApplication, mRnBundleLocation);
        mAppId = rnBundle.appId;
    }

    @UiThread
    public void destroy() {
        if (mResourceManager != null) {
            mResourceManager.destroy();
        }

        if (mReactInstanceManager != null) {
            mReactInstanceManager.destroy();
            mReactInstanceManager = null;
        }
    }

    public long getBundleSize() {
        if (mBundleSize == 0) {
            File appJsBundleFile = new File(mRnBundleLocation, "index.android.bundle");
            if (appJsBundleFile.exists()) {
                mBundleSize = appJsBundleFile.length();
            }
        }
        return mBundleSize;
    }

    /**
     * Gets the manifest of the SDK app associated with this context.
     */
    @NonNull
    public SdkAppManifest getAppManifest() {
        return mSdkAppManifest;
    }

    @NonNull
    public String getAppId() {
        return mAppId;
    }

    @NonNull
    public RNBundle getRNBundle() {
        return mRNBundle;
    }

    /**
     * Gets the resource manager associated with this SDK app.
     * Use the resource manager to get strings, images & other resources from the SDK app.
     */
    @NonNull
    public SdkAppResourceManager getResources() {
        return mResourceManager;
    }

    /**
     * Gets the react instance manager associated with this SDK application.
     * <p>
     * Returns null if bundle doesn't exist.
     * <p>
     * This should be called on UI thread as ReactInstanceManager.createReactContextInBackground()
     * inside createReactInstanceManager() is expected to run on UI thread.
     */
    @Nullable
    @UiThread
    public synchronized ReactInstanceManager getReactInstanceManager(@Nullable Activity activity) {
        if (mReactInstanceManager == null) {
            mReactInstanceManager = createReactInstanceManager(activity);
        }
        return mReactInstanceManager;
    }

    /**
     * Check if react instance manager has already created or not.
     */
    public boolean isReactInstanceManagerInitialised() {
        return mReactInstanceManager != null;
    }

    /**
     * creates a react instance manager if needed.
     * This call will wait for the react context to be created before returning the react instance manager.
     *
     * @return react instance manager
     */
    @NonNull
    public Task<ReactInstanceManager> getReactInstanceManagerAfterContextInitialization() {
        final TaskCompletionSource<ReactInstanceManager> tcs = new TaskCompletionSource<>();

        // run the below code on UI thread
        Task.call(new Callable<Void>() {
            @Override
            public Void call() {
                final ReactInstanceManager reactInstanceManager = getReactInstanceManager(null);
                if (reactInstanceManager == null) {
                    tcs.trySetResult(null);
                    return null;
                }

                if (reactInstanceManager.getCurrentReactContext() != null) {
                    tcs.trySetResult(reactInstanceManager);
                }
                // If react context is not yet initialized
                reactInstanceManager.addReactInstanceEventListener(new ReactInstanceManager.ReactInstanceEventListener() {
                    @Override
                    public void onReactContextInitialized(ReactContext context) {
                        reactInstanceManager.removeReactInstanceEventListener(this);
                        tcs.trySetResult(reactInstanceManager);
                    }
                });

                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);

        return tcs.getTask();
    }

    public boolean isActive() {
        return mReactInstanceManager != null && mReactInstanceManager.hasStartedCreatingInitialContext();
    }

    /**
     * Gets the SDK app initialization params as an Android bundle.
     */
    @NonNull
    public Bundle getAppInitializationParamsAsBundle(@NonNull String appInstanceId) {
        return getAppInitializationParams(appInstanceId).toBundle();
    }

    @NonNull
    public Bundle getHostParamsAsBundle(@NonNull String hostInstanceId) {
        return new SdkAppHostParams(hostInstanceId).toBundle();
    }

    /**
     * Gets the SDK app initialization params as a React Native map.
     */
    @NonNull
    public WritableMap getAppInitializationParamsAsMap(@NonNull String appInstanceId) {
        return getAppInitializationParams(appInstanceId).toMap();
    }

    /**
     * Gets the current Teams app theme as a string that the RN SDK understands.
     *
     * @return a string representing the current theme (e.g. "dark", "default")
     */
    @NonNull
    public String getCurrentAppTheme() {
        return (ThemeColorData.isDarkTheme()) ? SdkAppTheme.DARK : SdkAppTheme.DEFAULT;
    }

    /**
     * Gets the app initialization params.
     */
    @NonNull
    private SdkAppInitializationParams getAppInitializationParams(@NonNull String appInstanceId) {
        /*final AuthenticatedUser currentUser = mAccountManager.getUser();
        if (currentUser == null) {
            throw new IllegalStateException("User is not signed in.");
        }

        final User currentUserProfile = mUserDao.fetchUser(currentUser.mri);
        if (currentUserProfile == null) {
            final ILogger logger = mTeamsApplication.getLogger(null);
            logger.log(LogPriority.ERROR, LOG_TAG, "Signed in user profile is missing.");
        }*/

/*
        SdkAppUserParams userParams = new SdkAppUserParams(
                currentUser.userObjectId,
                currentUserProfile != null ? UserHelper.getDisplayName(currentUserProfile, mApplication) : currentUser.displayName,
                currentUser.userPrincipalName,
                currentUser.tenantId,
                currentUser.mri,
                currentUserProfile == null ? "" : currentUserProfile.jobTitle,
                null,
                SharepointSettings.getPersonalSiteUrl(mUserPreferencesDao, mFileTraits),
                null,
                currentUser.settings == null ? null : currentUser.settings.tenantSiteUrl);
*/

        SdkAppUserParams userParams = new SdkAppUserParams(
                "TeamsSdkSim",
                "TeamsSdkSim",
                "TeamsSdkSim",
                "TeamsSdkSim",
                "TeamsSdkSim",
                "TeamsSdkSim",
                null,
                "",
                null,
                null);


        /*SdkTelemetryInfo sdkTelemetryInfo = new SdkTelemetryInfo(mSdkAppManifest.targetReactNativeSdkVersion,
                mTeamsApplication.getExperimentationManager(null).getRingInfo(),
                mTeamsApplication.getFakeAndroidId());
*/

        SdkTelemetryInfo sdkTelemetryInfo = new SdkTelemetryInfo(mSdkAppManifest.targetReactNativeSdkVersion,
                "TeamsSdkSim",
                mTeamsApplication.getFakeAndroidId());

        return new SdkAppInitializationParams(mResourceManager.getLocale(), userParams, appInstanceId, getCurrentAppTheme(), sdkTelemetryInfo);
    }

    /**
     * Creates a new react instance manager.
     * This should be called on UI thread as ReactInstanceManager.createReactContextInBackground(),
     * which is called inside this method, expects to be called from UI thread.
     */
    @Nullable
    @UiThread
    private ReactInstanceManager createReactInstanceManager(@Nullable Activity activity) {
        // mReactNativeFabricEventLogger.initialize();

        ILogger logger = mTeamsApplication.getLogger(null);
        if (mRNBundle != null) {
            logger.log(LogPriority.INFO, LOG_TAG, "Fetching ReactContext for AppId : " + mRNBundle.appId + ", Path:" + mRNBundle.bundleLocation);
        }

        File appJsBundleFile = new File(mRnBundleLocation, "index.android.bundle");
        if (!SdkRunnerUtils.isRunnerMode() && !appJsBundleFile.exists()) {
            logger.log(LogPriority.WARNING, LOG_TAG, "Unable to create ReactContext because of missing bundle. File Path :  "
                    + appJsBundleFile.getAbsolutePath());
            return null;
        } else {
            logger.log(LogPriority.INFO, LOG_TAG, "Trigger VM_INIT with Bundle size:" + getBundleSize());
        }

        MainPackageConfig mainReactConfig = new MainPackageConfig.Builder()
                //.setFrescoConfig(FrescoImagePipelineConfig.create(mApplication))
                .build();
        Activity currentActivity = activity;
        if (currentActivity == null) {
            logger.log(LogPriority.WARNING, LOG_TAG, "Current Activity null for: " + mAppId + ". App Status : " + AppStateProvider.isAppVisible());
            // We should remove this. Adding above log to verify if it's getting used in any case.
            currentActivity = AppStateProvider.getCurrentActivity();
        }
        if (currentActivity == null) {
            logger.log(LogPriority.ERROR, LOG_TAG, "Current activity seems to be null. App Status : " + AppStateProvider.isAppVisible());
            return null;
        }

        //NOTE: using the current user - should be replaced with a specific user and updated when the user changes
        //final SdkPackageComponent sdkPackageComponent = mSdkPackageComponentFactory.create(mainReactConfig, this);
        //final SdkPackageFactory sdkPackageFactory = sdkPackageComponent.sdkPackageFactory();

        ReactInstanceManagerBuilder builder = ReactInstanceManager
                                                      .builder()
                                                      .setApplication(mApplication)
                                                      .setCurrentActivity(currentActivity)
                                                      .addPackage(new SdkMainReactPackage(null))
                                                      .addPackage(new SdkReactPackage(mTeamsApplication, this))
                                                      /*.addPackage(sdkPackageFactory.create(SdkMainReactPackage.class))
                                                      .addPackage(new SvgPackage())
                                                      .addPackage(sdkPackageFactory.create(SdkReactPackage.class))
                                                      .addPackage(new NetInfoPackage())
                                                      .addPackage(new RNCWebViewPackage())*/
                                                      .setJSMainModulePath("index.android")
                                                      .setInitialLifecycleState(LifecycleState.RESUMED);

        if (SdkRunnerUtils.isRunnerMode() && SdkRunnerUtils.isRunnerApp(mAppId)) {
            builder.setUseDeveloperSupport(true);
        } else {
            if (mTeamsApplication.getExperimentationManager(null).isFallbackLoaderInReactNativeEnabled()) {
                ArrayList<JSBundleLoader> loaders = new ArrayList<>();
                loaders.add(JSBundleLoader.createFileLoader(appJsBundleFile.getAbsolutePath()));
                // Duplicate file loader is being intentionally added as fallback loader.
                loaders.add(JSBundleLoader.createFileLoader(appJsBundleFile.getAbsolutePath()));
                JSBundleLoader jsBundleLoader = new FallbackJSBundleLoader(loaders);

                builder.setJSBundleLoader(jsBundleLoader);
            } else {
                builder.setJSBundleFile(appJsBundleFile.getAbsolutePath());
            }
            builder.setUseDeveloperSupport(false);
            builder.setNativeModuleCallExceptionHandler(new DefaultNativeModuleCallExceptionHandler() {
                @Override
                public void handleException(Exception e) {
                    // we will get unhandled crashes in native code accessed by the react native module
                    // and unhandled crashes from the javascript code of the react native module here
                    String version = mSdkAppManifest.version;
                    String message = String.format("Crash in React Native App Id : %s, Version: %s", mAppId, version);
                    // Logging of manifest should be removed if RN sdk start supporting 3p apps.
                    logger.log(LogPriority.ERROR, LOG_TAG, "%s , %s", message, getRawAndParsedManifest());
                    RuntimeException wrappedException = new RuntimeException(message, e);

                    Crashes.trackError(wrappedException);

                    Activity currentActivity = AppStateProvider.getCurrentActivity();
                    /*if (currentActivity != null && !(currentActivity instanceof MainActivity)) {
                        currentActivity.finish();
                    } else*/ {
                        // By default in production the app crashes
                        // TODO: we can show user a prompt about the RN module crash and then crash the teams app otherwise.
                        super.handleException(wrappedException);
                    }

                }
            });
        }

        // Build react instance manager
        final ReactInstanceManager reactInstanceManager = builder.build();

        // App is running in runner mode, let's reload the settings and application code to ensure we're running the latest bits
        if (SdkRunnerUtils.isRunnerMode() && SdkRunnerUtils.isRunnerApp(mAppId)) {
            DevSupportManager devSupportManager = reactInstanceManager.getDevSupportManager();
            devSupportManager.reloadSettings();
            devSupportManager.handleReloadJS();
        }

        // Final guard against loading missing bundle
        if (!SdkRunnerUtils.isRunnerMode() && !appJsBundleFile.exists()) {
            logger.log(LogPriority.WARNING, LOG_TAG, "Unable to create ReactContext because of missing bundle. File Path :  "
                    + appJsBundleFile.getAbsolutePath());
            return null;
        }

        // Create the react context if not already started creating
        if (!reactInstanceManager.hasStartedCreatingInitialContext()) {
            try {
                reactInstanceManager.createReactContextInBackground();
            } catch (Exception e) {
                logger.log(LogPriority.WARNING, LOG_TAG, "Unable to create ReactContext: " + e.getMessage());
                return null;
            }
        }

        /*if (currentActivity instanceof BaseActivity) {
            ((BaseActivity) currentActivity).attachReactInstanceManagerHost(reactInstanceManager);
        }*/

        return reactInstanceManager;
    }

    @NonNull
    private String getRawAndParsedManifest() {
        String rawManifest = "NULL";
        if (mRNBundle != null) {
            rawManifest = mRNBundle.manifest;
        }
        return String.format("Raw Manifest : %s, Parsed Manifest: %s", rawManifest, mSdkAppManifest);
    }

    /**
     * Factory for creating SdkApplicationContext objects
     */
    /*@PlatformAppScope
    public static class Factory {
        private final Application mApplication;
        private final ITeamsApplication mTeamsApplication;
        private final IFileTraits mFileTraits;
        private final IAccountManager mAccountManager;
        private final UserDao mUserDao;
        private final UserPreferencesDao mUserPreferencesDao;
        private final SdkPackageComponent.Factory mSdkPackageComponentFactory;
        private final IReactNativeFabricEventLogger mReactNativeFabricEventLogger;

        @Inject
        public Factory(@NonNull Application application,
                       @NonNull ITeamsApplication teamsApplication,
                       @NonNull IFileTraits fileTraits,
                       @NonNull IAccountManager accountManager,
                       @NonNull UserDao userDao,
                       @NonNull UserPreferencesDao userPreferencesDao,
                       @NonNull SdkPackageComponent.Factory sdkPackageComponentFactory,
                       @NonNull IReactNativeFabricEventLogger reactNativeFabricEventLogger) {
            mApplication = application;
            mTeamsApplication = teamsApplication;
            mFileTraits = fileTraits;
            mAccountManager = accountManager;
            mUserDao = userDao;
            mUserPreferencesDao = userPreferencesDao;
            mSdkPackageComponentFactory = sdkPackageComponentFactory;
            mReactNativeFabricEventLogger = reactNativeFabricEventLogger;
        }

        public SdkApplicationContext create(@NonNull RNBundle rnBundle) {
            return new SdkApplicationContext(mApplication, rnBundle, mTeamsApplication, mFileTraits, mAccountManager,
                    mUserDao, mUserPreferencesDao, mSdkPackageComponentFactory, mReactNativeFabricEventLogger);
        }*/
    //}
}
