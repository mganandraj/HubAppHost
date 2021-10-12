package com.microsoft.codepush.react;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.codepush.common.CodePushConfiguration;
import com.microsoft.codepush.common.datacontracts.CodePushLocalPackage;
import com.microsoft.codepush.common.datacontracts.CodePushRemotePackage;
import com.microsoft.codepush.common.datacontracts.CodePushSyncOptions;
import com.microsoft.codepush.common.enums.CodePushUpdateState;
import com.microsoft.codepush.common.exceptions.CodePushInitializeException;
import com.microsoft.codepush.common.exceptions.CodePushNativeApiCallException;
import com.microsoft.codepush.common.interfaces.CodePushDownloadProgressListener;
import com.microsoft.codepush.common.interfaces.CodePushSyncStatusListener;
import com.microsoft.codepush.common.managers.CodePushAcquisitionManager;
import com.microsoft.codepush.react.interfaces.ReactInstanceHolder;
import com.microsoft.codepush.react.utils.ReactPlatformUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.microsoft.codepush.common.utils.CodePushLogUtils.trackException;

/**
 * A class exposing CodePush API to users.
 */
public class CodePush implements ReactPackage, Serializable {

    /**
     * Instance of {@link CodePushReactNativeCore}.
     */
    private static CodePushReactNativeCore mReactNativeCore;

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        return mReactNativeCore.createNativeModules(reactApplicationContext);
    }

    // Deprecated in RN v0.47.
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return new ArrayList<>();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return new ArrayList<>();
    }

    /**
     * @deprecated use {@link #builder} instead
     */
    @Deprecated
    public CodePush(String deploymentKey, Context context) throws CodePushInitializeException {
        this(deploymentKey, context, false);
    }

    /**
     * @deprecated use {@link #builder} instead
     */
    @Deprecated
    public CodePush(String deploymentKey, Context context, boolean isDebugMode) throws CodePushInitializeException {
        try {
            mReactNativeCore = new CodePushReactNativeCore(
                    deploymentKey,
                    context,
                    isDebugMode,
                    null,
                    null,
                    null,
                    null,
                    new CodePushReactPublicKeyProvider(null, context),
                    new CodePushReactAppEntryPointProvider(null),
                    ReactPlatformUtils.getInstance());
        } catch (CodePushInitializeException e) {
            trackException(e);
            throw e;
        }
    }

    /**
     * @deprecated use {@link #builder} instead
     */
    @Deprecated
    public CodePush(String deploymentKey, Application application, boolean isDebugMode, String appSecret) throws CodePushInitializeException {
        try {
            mReactNativeCore = new CodePushReactNativeCore(
                    deploymentKey,
                    application,
                    appSecret,
                    isDebugMode,
                    null,
                    null,
                    null,
                    null,
                    new CodePushReactPublicKeyProvider(null, application.getApplicationContext()),
                    new CodePushReactAppEntryPointProvider(null),
                    ReactPlatformUtils.getInstance());
        } catch (CodePushInitializeException e) {
            trackException(e);
            throw e;
        }
    }

    /**
     * @deprecated use {@link #builder} instead
     */
    @Deprecated
    public CodePush(String deploymentKey, Context context, boolean isDebugMode, String serverUrl) throws CodePushInitializeException {
        try {
            mReactNativeCore = new CodePushReactNativeCore(
                    deploymentKey,
                    context,
                    isDebugMode,
                    null,
                    serverUrl,
                    null,
                    null,
                    new CodePushReactPublicKeyProvider(null, context),
                    new CodePushReactAppEntryPointProvider(null),
                    ReactPlatformUtils.getInstance());
        } catch (CodePushInitializeException e) {
            trackException(e);
            throw e;
        }
    }

    /**
     * @deprecated use {@link #builder} instead
     */
    @Deprecated
    public CodePush(String deploymentKey, Application application, boolean isDebugMode, String serverUrl, String appSecret) throws CodePushInitializeException {
        try {
            mReactNativeCore = new CodePushReactNativeCore(
                    deploymentKey,
                    application,
                    appSecret,
                    isDebugMode,
                    null,
                    serverUrl,
                    null,
                    null,
                    new CodePushReactPublicKeyProvider(null, application.getApplicationContext()),
                    new CodePushReactAppEntryPointProvider(null),
                    ReactPlatformUtils.getInstance());
        } catch (CodePushInitializeException e) {
            trackException(e);
            throw e;
        }
    }

    /**
     * @deprecated use {@link #builder} instead
     */
    @Deprecated
    public CodePush(String deploymentKey, Context context, boolean isDebugMode, int publicKeyResourceDescriptor) throws CodePushInitializeException {
        try {
            mReactNativeCore = new CodePushReactNativeCore(
                    deploymentKey,
                    context,
                    isDebugMode,
                    null,
                    null,
                    null,
                    null,
                    new CodePushReactPublicKeyProvider(publicKeyResourceDescriptor, context),
                    new CodePushReactAppEntryPointProvider(null),
                    ReactPlatformUtils.getInstance());
        } catch (CodePushInitializeException e) {
            trackException(e);
            throw e;
        }
    }

    /**
     * @deprecated use {@link #builder} instead
     */
    @Deprecated
    public CodePush(String deploymentKey, Context context, boolean isDebugMode, @NonNull String serverUrl, Integer publicKeyResourceDescriptor) throws CodePushInitializeException {
        try {
            mReactNativeCore = new CodePushReactNativeCore(
                    deploymentKey,
                    context,
                    isDebugMode,
                    null,
                    serverUrl,
                    null,
                    null,
                    new CodePushReactPublicKeyProvider(publicKeyResourceDescriptor, context),
                    new CodePushReactAppEntryPointProvider(null),
                    ReactPlatformUtils.getInstance());
        } catch (CodePushInitializeException e) {
            trackException(e);
            throw e;
        }
    }

    /**
     * @deprecated use {@link #builder} instead
     */
    @Deprecated
    public CodePush(String deploymentKey, Application application, boolean isDebugMode, @NonNull String serverUrl, String appSecret, Integer publicKeyResourceDescriptor) throws CodePushInitializeException {
        try {
            mReactNativeCore = new CodePushReactNativeCore(
                    deploymentKey,
                    application,
                    appSecret,
                    isDebugMode,
                    null,
                    serverUrl,
                    null,
                    null,
                    new CodePushReactPublicKeyProvider(publicKeyResourceDescriptor, application.getApplicationContext()),
                    new CodePushReactAppEntryPointProvider(null),
                    ReactPlatformUtils.getInstance());
        } catch (CodePushInitializeException e) {
            trackException(e);
            throw e;
        }
    }

    /**
     * Creates instance of {@link CodePush} for those who want to track exceptions (includes additional parameters).
     *
     * @param deploymentKey               application deployment key.
     * @param application                 application instance.
     * @param appSecret                   the value of app secret from AppCenter portal to configure {@link Crashes} sdk.
     * @param isDebugMode                 whether the application is running in debug mode.
     * @param baseDirectory               base directory for CodePush files.
     * @param serverUrl                   CodePush server url.
     * @param appName                     application name.
     * @param appVersion                  application version.
     * @param publicKeyResourceDescriptor public-key related resource descriptor.
     * @param entryPointName              path to the application entry point.
     * @throws CodePushInitializeException initialization exception.
     */
    public CodePush(
            @NonNull String deploymentKey,
            @NonNull Application application,
            @Nullable String appSecret,
            boolean isDebugMode,
            @Nullable String baseDirectory,
            @Nullable String serverUrl,
            @Nullable String appName,
            @Nullable String appVersion,
            @Nullable Integer publicKeyResourceDescriptor,
            @Nullable String entryPointName
    ) throws CodePushInitializeException {
        try {
            mReactNativeCore = new CodePushReactNativeCore(
                    deploymentKey,
                    application,
                    appSecret,
                    isDebugMode,
                    baseDirectory,
                    serverUrl,
                    appName,
                    appVersion,
                    new CodePushReactPublicKeyProvider(publicKeyResourceDescriptor, application.getApplicationContext()),
                    new CodePushReactAppEntryPointProvider(entryPointName),
                    ReactPlatformUtils.getInstance());
        } catch (CodePushInitializeException e) {
            trackException(e);
            throw e;
        }
    }

    /**
     * Creates instance of {@link CodePush}.
     *
     * @param deploymentKey               application deployment key.
     * @param context                     application context.
     * @param isDebugMode                 whether the application is running in debug mode.
     * @param baseDirectory               base directory for CodePush files.
     * @param serverUrl                   CodePush server url.
     * @param appName                     application name.
     * @param appVersion                  application version.
     * @param publicKeyResourceDescriptor public-key related resource descriptor.
     * @param entryPointName              path to the application entry point.
     * @throws CodePushInitializeException initialization exception.
     */
    public CodePush(
            @NonNull String deploymentKey,
            @NonNull Context context,
            boolean isDebugMode,
            @Nullable String baseDirectory,
            @Nullable String serverUrl,
            @Nullable String appName,
            @Nullable String appVersion,
            @Nullable Integer publicKeyResourceDescriptor,
            @Nullable String entryPointName
    ) throws CodePushInitializeException {
        try {
            mReactNativeCore = new CodePushReactNativeCore(
                    deploymentKey,
                    context,
                    isDebugMode,
                    baseDirectory,
                    serverUrl,
                    appName,
                    appVersion,
                    new CodePushReactPublicKeyProvider(publicKeyResourceDescriptor, context),
                    new CodePushReactAppEntryPointProvider(entryPointName),
                    ReactPlatformUtils.getInstance());
        } catch (CodePushInitializeException e) {
            trackException(e);
            throw e;
        }
    }

    /**
     * Creates builder for this class for those who want to track exceptions.
     *
     * @param deploymentKey application deployment key.
     * @param application   application instance.
     * @param appSecret     the value of app secret from AppCenter portal to configure {@link Crashes} sdk.
     * @return instance of {@link CodePushBuilder}.
     */
    public static CodePushBuilder builder(String deploymentKey, Application application, String appSecret) {
        return new CodePushBuilder(deploymentKey, application, appSecret);
    }

    /**
     * Creates default builder for this class.
     *
     * @param deploymentKey application deployment key.
     * @param context       application context.
     * @return instance of {@link CodePushBuilder}.
     */
    public static CodePushBuilder builder(String deploymentKey, Context context) {
        return new CodePushBuilder(deploymentKey, context);
    }

    /**
     * Gets a link to the default javascript bundle file.
     *
     * @return link starting with "assets://" and leading to javascript bundle file.
     */
    public static String getJSBundleFile() {
        return CodePushReactNativeCore.getJSBundleFile();
    }

    /**
     * Gets a link to the specified javascript bundle file.
     *
     * @param assetsBundleFileName custom bundle file name.
     * @return link starting with "assets://" and leading to javascript bundle file.
     */
    public static String getJSBundleFile(String assetsBundleFileName) {
        return CodePushReactNativeCore.getJSBundleFile(assetsBundleFileName);
    }

    /**
     * Sets instance holder.
     *
     * @param reactInstanceHolder instance of {@link ReactInstanceHolder}.
     * @deprecated Please use {@link CodePush#setReactInstanceManager(ReactInstanceManager)} instead.
     */
    public static void setReactInstanceHolder(ReactInstanceHolder reactInstanceHolder) {
        CodePushReactNativeCore.setReactInstanceHolder(reactInstanceHolder);
    }

    /**
     * Sets instance manager.
     *
     * @param reactInstanceManager instance of {@link ReactInstanceManager}.
     */
    public static void setReactInstanceManager(ReactInstanceManager reactInstanceManager) {
        CodePushReactNativeCore.setReactInstanceManager(reactInstanceManager);
    }

    /**
     * Gets native CodePush configuration.
     *
     * @return native CodePush configuration.
     */
    public CodePushConfiguration getConfiguration() throws CodePushNativeApiCallException {
        try {
            return mReactNativeCore.getNativeConfiguration();
        } catch (CodePushNativeApiCallException e) {
            trackException(e);
            throw e;
        }
    }

    /**
     * Gets instance of {@link CodePushAcquisitionManager}.
     *
     * @return instance of {@link CodePushAcquisitionManager}.
     */
    public CodePushAcquisitionManager getAcquisitionSdk() {
        return mReactNativeCore.getAcquisitionSdk();
    }

    /**
     * Asks the CodePush service whether the configured app deployment has an update available
     * using deploymentKey already set in constructor.
     *
     * @return remote package info if there is an update, <code>null</code> otherwise.
     * @throws CodePushNativeApiCallException if error occurred during the execution of operation.
     */
    public CodePushRemotePackage checkForUpdate() throws CodePushNativeApiCallException {
        try {
            return mReactNativeCore.checkForUpdate();
        } catch (CodePushNativeApiCallException e) {
            trackException(e);
            throw e;
        }
    }

    /**
     * Asks the CodePush service whether the configured app deployment has an update available
     * using specified deployment key.
     *
     * @param deploymentKey deployment key to use.
     * @return remote package info if there is an update, <code>null</code> otherwise.
     * @throws CodePushNativeApiCallException if error occurred during the execution of operation.
     */
    public CodePushRemotePackage checkForUpdate(String deploymentKey) throws CodePushNativeApiCallException {
        try {
            return mReactNativeCore.checkForUpdate(deploymentKey);
        } catch (CodePushNativeApiCallException e) {
            trackException(e);
            throw e;
        }
    }

    /**
     * @deprecated use {@link #getUpdateMetadata()} instead.
     */
    @Deprecated
    public CodePushLocalPackage getCurrentPackage() throws CodePushNativeApiCallException {
        try {
            return mReactNativeCore.getCurrentPackage();
        } catch (CodePushNativeApiCallException e) {
            trackException(e);
            throw e;
        }
    }

    /**
     * Retrieves the metadata for an installed update (e.g. description, mandatory)
     * whose state matches the specified <code>updateState</code> parameter.
     *
     * @param updateState current update state.
     * @return installed update metadata.
     * @throws CodePushNativeApiCallException if error occurred during the operation.
     */
    public CodePushLocalPackage getUpdateMetadata(CodePushUpdateState updateState) throws CodePushNativeApiCallException {
        try {
            return mReactNativeCore.getUpdateMetadata(updateState);
        } catch (CodePushNativeApiCallException e) {
            trackException(e);
            throw e;
        }
    }

    /**
     * Retrieves the metadata for an installed update (e.g. description, mandatory)
     * whose state matches {@link CodePushUpdateState#RUNNING}.
     *
     * @return installed update metadata.
     * @throws CodePushNativeApiCallException if error occurred during the operation.
     */
    public CodePushLocalPackage getUpdateMetadata() throws CodePushNativeApiCallException {
        try {
            return mReactNativeCore.getUpdateMetadata(CodePushUpdateState.RUNNING);
        } catch (CodePushNativeApiCallException e) {
            trackException(e);
            throw e;
        }
    }

    /**
     * Logs custom message on device.
     *
     * @param message message to be logged.
     */
    public static void log(String message) {
        mReactNativeCore.log(message);
    }

    /**
     * Attempts to restart the application unconditionally (whether there is pending update is ignored).
     */
    public void restartApp() throws CodePushNativeApiCallException {
        try {
            mReactNativeCore.restartApp();
        } catch (CodePushNativeApiCallException e) {
            trackException(e);
            throw e;
        }
    }

    /**
     * Attempts to restart the application.
     *
     * @param onlyIfUpdateIsPending if <code>true</code>, restart is performed only if update is pending.
     */
    public void restartApp(boolean onlyIfUpdateIsPending) throws CodePushNativeApiCallException {
        try {
            mReactNativeCore.restartApp(onlyIfUpdateIsPending);
        } catch (CodePushNativeApiCallException e) {
            trackException(e);
            throw e;
        }
    }

    /**
     * Permits restarts.
     */
    public void disallowRestart() {
        mReactNativeCore.disallowRestart();
    }

    /**
     * Allows restarts.
     */
    public void allowRestart() throws CodePushNativeApiCallException {
        try {
            mReactNativeCore.allowRestart();
        } catch (CodePushNativeApiCallException e) {
            trackException(e);
            throw e;
        }
    }

    /**
     * Synchronizes your app assets with the latest release to the configured deployment using default sync options.
     *
     * @throws CodePushNativeApiCallException if error occurred during the execution of operation.
     */
    public void sync() throws CodePushNativeApiCallException {
        try {
            mReactNativeCore.sync();
        } catch (CodePushNativeApiCallException e) {
            trackException(e);
            throw e;
        }
    }

    /**
     * Synchronizes your app assets with the latest release to the configured deployment.
     *
     * @param syncOptions sync options.
     * @throws CodePushNativeApiCallException if error occurred during the execution of operation.
     */
    public void sync(CodePushSyncOptions syncOptions) throws CodePushNativeApiCallException {
        try {
            mReactNativeCore.sync(syncOptions);
        } catch (CodePushNativeApiCallException e) {
            trackException(e);
            throw e;
        }
    }

    /**
     * Adds listener for sync status change event.
     *
     * @param syncStatusListener listener for sync status change event.
     */
    public void addSyncStatusListener(CodePushSyncStatusListener syncStatusListener) {
        mReactNativeCore.addSyncStatusListener(syncStatusListener);
    }

    /**
     * Adds listener for download progress change event.
     *
     * @param downloadProgressListener listener for download progress change event.
     */
    public void addDownloadProgressListener(CodePushDownloadProgressListener downloadProgressListener) {
        mReactNativeCore.addDownloadProgressListener(downloadProgressListener);
    }

    /**
     * Removes listener for sync status change event.
     *
     * @param syncStatusListener listener for sync status change event.
     */
    public void removeSyncStatusListener(CodePushSyncStatusListener syncStatusListener) {
        mReactNativeCore.removeSyncStatusListener(syncStatusListener);
    }

    /**
     * Removes listener for download progress change event.
     *
     * @param downloadProgressListener listener for download progress change event.
     */
    public void removeDownloadProgressListener(CodePushDownloadProgressListener downloadProgressListener) {
        mReactNativeCore.removeDownloadProgressListener(downloadProgressListener);
    }
}