/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk.rnbundle;

import android.content.Context;

import androidx.annotation.NonNull;

import com.microsoft.codepush.common.datacontracts.CodePushPackage;
import com.microsoft.codepush.common.datacontracts.CodePushSyncOptions;
import com.microsoft.codepush.common.enums.CodePushInstallMode;
import com.microsoft.codepush.common.enums.CodePushSyncStatus;
import com.microsoft.codepush.common.interfaces.CodePushDownloadProgressListener;
import com.microsoft.codepush.common.interfaces.CodePushSyncStatusListener;
import com.microsoft.codepush.react.CodePush;
import com.microsoft.teams.logger.ILogger;
import com.microsoft.teams.logger.LogPriority;
import com.microsoft.teams.sdk.SdkConstants;
import com.microsoft.teams.services.diagnostics.telemetryschema.ScenarioContext;
import com.microsoft.teams.services.diagnostics.telemetryschema.ScenarioName;
import com.microsoft.teams.storage.IExperimentationManager;
import com.microsoft.teams.storage.dao.rnapps.RNAppsDao;
import com.microsoft.teams.storage.tables.RNApp;
import com.microsoft.teams.storage.tables.RNBundle;
import com.microsoft.teams.utilities.IOUtilities;
import com.microsoft.teams.utilities.java.StringUtils;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.microsoft.teams.core.services.IScenarioManager;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.microsoft.codepush.common.CodePushConstants.CODE_PUSH_PREFERENCES;
import static com.microsoft.teams.sdk.SdkConstants.SdkRequestDownloadStatus.ERROR_DOWNLOAD_FAILED;
import static com.microsoft.teams.sdk.rnbundle.SdkBundleUtils.getCacheBaseDirectory;
import static com.microsoft.teams.sdk.utils.SdkVersionUtils.SUPPORTED_BINARY_VERSION_LIST;

/**
 * Responsible to download bundle from Codepush
 *
 * Note: Codepush supports only one bundle sync at a time
 */
@Singleton
public class SdkCodepushBundleDownloader implements ISdkBundleDownloader {
    private static final String LOG_TAG = "SdkCodepushBundleDownloader";
    private static final int DEFAULT_WAIT_TIME_IN_SECONDS = 2;

    private final ITeamsApplication mTeamsApplication;
    private final ISdkBundleManager mSdkBundleManager;
    private final DelayQueue<CodepushModel> mQueue;
    private Thread mThread;
    private ISdkBundleDownloadProgressListener mSdkBundleDownloadProgressListener;
    private CodepushModel mDownloadingItem;

    @Inject
    public SdkCodepushBundleDownloader(@NonNull ITeamsApplication teamsApplication,
                                       @NonNull ISdkBundleManager sdkBundleManager) {
        mTeamsApplication = teamsApplication;
        mSdkBundleManager = sdkBundleManager;
        mQueue = new DelayQueue<>();
    }

    @Override
    public void setSdkBundleDownloadProgressListener(@NonNull ISdkBundleDownloadProgressListener sdkBundleDownloadProgressListener) {
        mSdkBundleDownloadProgressListener = sdkBundleDownloadProgressListener;
    }

    @Override
    public void download(@NonNull String appId, @NonNull String deploymentKey, long requestId, @NonNull ILogger logger,
                         @NonNull IScenarioManager scenarioManager, @NonNull ScenarioContext scenarioContext,
                         @NonNull IExperimentationManager experimentationManager, @NonNull RNAppsDao rnAppsDao) {
        CodepushModel codepushModel = new CodepushModel(requestId, appId, deploymentKey, scenarioContext.getStepId());
        RNApp rnApp = rnAppsDao.fromId(appId);
        if (rnApp != null && rnApp.lastUpdateCheckedTimeInMillis > 0) {
            long backoffTime = experimentationManager.getCodePushUpdateCheckBackoffTimeInMinutes() * 60 * 1000;
            if (System.currentTimeMillis() < rnApp.lastUpdateCheckedTimeInMillis + backoffTime) {
                return;
            }
        }

        if (!mQueue.contains(codepushModel) && !codepushModel.equals(mDownloadingItem)) {
            mQueue.add(codepushModel);

            // Update Request
            mSdkBundleDownloadProgressListener.onBundleDownloadStatusUpdated(requestId, SdkConstants.SdkRequestDownloadStatus.QUEUED);

            init(logger, scenarioManager, experimentationManager, rnAppsDao);
        }
    }

    private void init(@NonNull final ILogger logger, @NonNull final IScenarioManager scenarioManager,
                      @NonNull final IExperimentationManager experimentationManager, @NonNull final RNAppsDao rnAppsDao) {
        if (mThread == null) {
            synchronized (SdkCodepushBundleDownloader.class) {
                if (mThread == null) {
                    mThread = new Thread(
                            () -> {
                                while (!mQueue.isEmpty()) {
                                    try {
                                        CodepushModel codepushModel = mQueue.take();
                                        downloadBundle(codepushModel, logger, scenarioManager, experimentationManager, rnAppsDao);
                                    } catch (InterruptedException e) {
                                        logger.log(LogPriority.ERROR, LOG_TAG, "Error in taking codepushModel from queue");
                                    }
                                }
                                mThread = null;
                            });
                    mThread.start();
                }
            }
        }
    }

    @Override
    public void clearTemporaryStorage(@NonNull String appId, @NonNull ILogger logger) {
        Context context = mTeamsApplication.getApplicationContext();
        // Delete app directory from cache for codepush
        File appDirectory = new File(getCacheBaseDirectory(context), appId);
        try {
            IOUtilities.deleteDirectory(appDirectory.getAbsolutePath());
        } catch (IOException e) {
            logger.log(LogPriority.ERROR, LOG_TAG, e);
        }

        // Clear entire preference which is used by codepush
        context.getSharedPreferences(CODE_PUSH_PREFERENCES, 0).edit().clear().apply();
    }

    private void downloadBundle(@NonNull CodepushModel codepushModel,
                                @NonNull ILogger logger,
                                @NonNull IScenarioManager scenarioManager,
                                @NonNull IExperimentationManager experimentationManager,
                                @NonNull RNAppsDao rnAppsDao) {
        mQueue.remove(codepushModel);
        mDownloadingItem = codepushModel;
        long requestId = codepushModel.getRequestId();
        String appId = codepushModel.getAppId();
        String deploymentKey = codepushModel.getDeploymentKey();
        ScenarioContext parentScenarioContext = scenarioManager.getScenario(codepushModel.getStepId());
        ScenarioContext checkUpdateScenarioContext = null;
        ScenarioContext bundleDownloadScenarioContext = null;
        Context context = mTeamsApplication.getApplicationContext();

        // Update Request
        mSdkBundleDownloadProgressListener.onBundleDownloadStatusUpdated(requestId, SdkConstants.SdkRequestDownloadStatus.DOWNLOADING);


        // Check for download package from maximum SDK version to minimum SDK version
        try {
            CodePushPackage codePushPackage;
            CodePush codePush;
            String binaryVersion;
            for (int i = 0; i < SUPPORTED_BINARY_VERSION_LIST.size(); i++) {
                binaryVersion = SUPPORTED_BINARY_VERSION_LIST.get(i);

                codePush = CodePush.builder(deploymentKey, context)
                        .setIsDebugMode(true)
                        .setBaseDir(getCacheBaseDirectory(context).getAbsolutePath())
                        .setAppVersion(binaryVersion)
                        .setAppName(appId)
                        .build();

                // Start scenario marker for checking update on codepush
                checkUpdateScenarioContext = scenarioManager.startScenario(ScenarioName.SYNC_CODEPUSH_CHECK_UPDATE, parentScenarioContext,
                        "appId: " + appId,
                        "retryCount: " + codepushModel.getRetryCount(),
                        "binaryVersion: " + binaryVersion);
                codePushPackage = codePush.checkForUpdate();

                RNApp rnApp = rnAppsDao.fromId(appId);
                if (rnApp != null) {
                    rnApp.lastUpdateCheckedTimeInMillis = System.currentTimeMillis();
                    rnAppsDao.save(rnApp);
                }

                if (codePushPackage != null) {
                    // End scenario marker for checking update on codepush
                    checkUpdateScenarioContext.endScenarioOnSuccessWithStatusCode(SdkConstants.SdkCodepushCheckUpdateStatus.UPDATE_AVAILABLE);
                    checkUpdateScenarioContext = null;

                    // Check if bundle is already downloaded
                    RNBundle localBundle = mSdkBundleManager.getLocalBundle(appId, codePushPackage.getPackageHash());
                    if (localBundle != null) {
                        mSdkBundleDownloadProgressListener.onBundleAlreadyExists(requestId, appId, localBundle);
                        mDownloadingItem = null;
                        return;
                    }

                    // Start scenario marker for bundle download
                    bundleDownloadScenarioContext = scenarioManager.startScenario(ScenarioName.SYNC_CODEPUSH_DOWNLOAD_BUNDLE, parentScenarioContext,
                            "appId: " + appId,
                            "retryCount: " + codepushModel.getRetryCount(),
                            "binaryVersion: " + binaryVersion);

                    // A new remote CodePush package was found
                    DownloadProgressListener downloadProgressListener = new DownloadProgressListener(logger);
                    codePush.addDownloadProgressListener(downloadProgressListener);

                    SyncStatusListener syncStatusListener = new SyncStatusListener(appId, logger);
                    codePush.addSyncStatusListener(syncStatusListener);

                    CodePushSyncOptions codePushSyncOptions = new CodePushSyncOptions();
                    codePushSyncOptions.setMandatoryInstallMode(CodePushInstallMode.ON_NEXT_RESTART);
                    codePush.sync(codePushSyncOptions);

                    codePush.removeSyncStatusListener(syncStatusListener);

                    // End scenario marker for bundle download
                    bundleDownloadScenarioContext.endScenarioOnSuccess();
                    bundleDownloadScenarioContext = null;

                    File updateDirectory = getDownloadedPackage(context, appId, codePushPackage.getPackageHash());
                    File tempDirectory = new File(updateDirectory, appId);
                    mSdkBundleDownloadProgressListener.onBundleDownloadSuccess(requestId, appId, tempDirectory.toString());
                    mDownloadingItem = null;
                    return;
                } else {
                    // End scenario marker for checking update on codepush
                    checkUpdateScenarioContext.endScenarioOnSuccessWithStatusCode(SdkConstants.SdkCodepushCheckUpdateStatus.NO_UPDATE);
                }
            }
        } catch (Exception e) {
            // End scenario marker for checking update on codepush
            if (checkUpdateScenarioContext != null) {
                checkUpdateScenarioContext.endScenarioOnError(SdkConstants.SdkCodepushCheckUpdateStatus.ERROR_OCCURRED,
                        "" + e.getMessage(),
                        StringUtils.EMPTY_STRING);
            }

            // End scenario marker for bundle download
            if (bundleDownloadScenarioContext != null) {
                bundleDownloadScenarioContext.endScenarioOnError(SdkConstants.SdkCodepushBundleDownloadStatus.FAILED,
                        "" + e.getMessage(),
                        StringUtils.EMPTY_STRING);
            }

            logger.log(LogPriority.ERROR, LOG_TAG, "appId: " + appId + ",retryCount: " + codepushModel.getRetryCount() + ", exception: " + e.getMessage());
            codepushModel.increaseRetryCount();

            if (codepushModel.getRetryCount() <= experimentationManager.getRetryCountForCodepushBundleDownload()) {
                mQueue.add(codepushModel);
            } else {
                mSdkBundleDownloadProgressListener.onBundleDownloadFailure(requestId, appId, ERROR_DOWNLOAD_FAILED);
            }
            mDownloadingItem = null;
            return;
        }

        mDownloadingItem = null;
        mSdkBundleDownloadProgressListener.onBundleDownloadNotAvailable(requestId, appId);
    }

    @NonNull
    private File getDownloadedPackage(@NonNull Context context, @NonNull String appId, @NonNull String packageHash) {
        return new File(getCacheBaseDirectory(context), String.format(Locale.ENGLISH, "%s/%s", appId, packageHash));
    }

    /**
     * Listens for and logs CodePush sync status updates.
     */
    private static class SyncStatusListener implements CodePushSyncStatusListener {

        private ILogger mLogger;
        private String mAppId;

        SyncStatusListener(String appId, ILogger logger) {
            mLogger = logger;
            mAppId = appId;
        }

        @Override
        public void syncStatusChanged(CodePushSyncStatus syncStatus) {
            mLogger.log(LogPriority.DEBUG, LOG_TAG, "CodePush sync status: " + syncStatus.name() + ", appId : " + mAppId);
        }
    }

    /**
     * Listens for and logs CodePush download progress.
     */
    private static class DownloadProgressListener implements CodePushDownloadProgressListener {

        private ILogger mLogger;

        DownloadProgressListener(@NonNull ILogger logger) {
            mLogger = logger;
        }

        @Override
        public void downloadProgressChanged(long receivedBytes, long totalBytes) {
            mLogger.log(LogPriority.DEBUG, LOG_TAG, "CodePush sync status: Downloaded %d/%d KB.", receivedBytes / 1024, totalBytes / 1024);
        }
    }

    private static class CodepushModel implements Delayed {
        private final long mRequestId;
        private final String mAppId;
        private final String mDeploymentKey;
        private int mRetryCount;
        private long mExpiryTime;
        private String mStepId;

        CodepushModel(long requestId, @NonNull String appId, @NonNull String deploymentKey, @NonNull String stepId) {
            this.mRequestId = requestId;
            this.mAppId = appId;
            this.mDeploymentKey = deploymentKey;
            this.mRetryCount = 0;
            this.mExpiryTime = System.currentTimeMillis();
            this.mStepId = stepId;
        }

        long getRequestId() {
            return mRequestId;
        }

        String getAppId() {
            return mAppId;
        }

        String getDeploymentKey() {
            return mDeploymentKey;
        }

        public String getStepId() {
            return mStepId;
        }

        @Override
        public long getDelay(@NonNull TimeUnit unit) {
            long diff = mExpiryTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(@NonNull Delayed o) {
            if (this.mExpiryTime < ((CodepushModel) o).mExpiryTime) {
                return -1;
            }
            if (this.mExpiryTime > ((CodepushModel) o).mExpiryTime) {
                return 1;
            }
            return 0;
        }

        void increaseRetryCount() {
            mRetryCount++;
            mExpiryTime = System.currentTimeMillis() + (long) Math.pow(DEFAULT_WAIT_TIME_IN_SECONDS, mRetryCount) * 1000;
        }

        int getRetryCount() {
            return mRetryCount;
        }

        @Override
        public int hashCode() {
            return mDeploymentKey.hashCode() + mAppId.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof CodepushModel)) {
                return false;
            }
            if (obj == this) {
                return true;
            }

            CodepushModel codepushModel = (CodepushModel) obj;
            return this.mDeploymentKey.equals(codepushModel.mDeploymentKey)
                    && this.mAppId.equals(codepushModel.mAppId);
        }
    }
}
