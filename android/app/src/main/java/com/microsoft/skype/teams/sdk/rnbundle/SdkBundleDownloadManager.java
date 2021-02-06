/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk.rnbundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
// import com.microsoft.skype.teams.events.IEventBus;
import com.microsoft.skype.teams.logger.ILogger;
import com.microsoft.skype.teams.logger.LogPriority;
import com.microsoft.skype.teams.sdk.SdkAppManifestParserProvider;
import com.microsoft.skype.teams.sdk.SdkConstants;
import com.microsoft.skype.teams.sdk.SdkHelper;
import com.microsoft.skype.teams.sdk.models.SdkAppManifest;
import com.microsoft.skype.teams.sdk.utils.SdkVersionUtils;
import com.microsoft.skype.teams.services.configuration.AppConfiguration;
import com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioContext;
// import com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName;
import com.microsoft.skype.teams.services.diagnostics.telemetryschema.ScenarioName;
import com.microsoft.skype.teams.storage.IExperimentationManager;
import com.microsoft.skype.teams.storage.dao.rnapps.RNAppsDao;
import com.microsoft.skype.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.skype.teams.storage.models.MobileModuleDefinition;
import com.microsoft.skype.teams.storage.tables.RNApp;
import com.microsoft.skype.teams.storage.tables.RNBundle;
import com.microsoft.skype.teams.utilities.IOUtilities;
import com.microsoft.skype.teams.utilities.java.JsonUtils;
import com.microsoft.skype.teams.utilities.java.ListUtils;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.microsoft.teams.core.services.IScenarioManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

// import static com.microsoft.skype.teams.data.events.DataEvents.SDK_BUNDLE_SYNC_REQUEST_UPDATED;
import static com.microsoft.skype.teams.sdk.SdkConstants.RN_BUNDLE_DOWNLOAD_ERROR;
import static com.microsoft.skype.teams.sdk.SdkConstants.RN_BUNDLE_PARSING_ERROR;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.BUNDLE_ADDED;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.BUNDLE_ALREADY_EXISTS;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.DOWNLOADED;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.DOWNLOADING;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.ERROR_BUNDLE_UNAVAILABLE;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.ERROR_BUNDLE_UNZIP_FAILED;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.ERROR_DOWNLOAD_FAILED;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.ERROR_INVALID_MANIFEST;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.ERROR_JS_FILE_UNAVAILABLE;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.ERROR_MANIFEST_PARSING_FAILED;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.ERROR_MANIFEST_UNAVAILABLE;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.JS_FILE_EXISTS;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.MANIFEST_FILE_EXISTS;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.QUEUED;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.UPDATE_NOT_AVAILABLE;
import static com.microsoft.skype.teams.sdk.rnbundle.SdkBundleUtils.BundleSource.CODEPUSH;
import static com.microsoft.skype.teams.sdk.rnbundle.SdkBundleUtils.BundleSource.LOCAL;
import static com.microsoft.skype.teams.sdk.rnbundle.SdkBundleUtils.CompatibilityStatus.COMPATIBLE;
import static com.microsoft.skype.teams.sdk.rnbundle.SdkBundleUtils.CompatibilityStatus.INCOMPATIBLE_UPDATE_RNAPP;
import static com.microsoft.skype.teams.sdk.rnbundle.SdkBundleUtils.generateRequestId;

/**
 * Responsible to manage syncing process of RN bundle.
 */
@Singleton
public final class SdkBundleDownloadManager implements ISdkBundleDownloadManager, ISdkBundleDownloadProgressListener {
    private static final String LOG_TAG = "SdkRNBundleSyncManager";

    private final ISdkBundleDownloader mSdkCodepushBundleDownloader;
    private final ISdkBundleDownloader mSdkLocalBundleDownloader;
    private final ISdkBundleManager mSdkBundleManager;
    private final Gson mGson;
    private final AppConfiguration mAppConfiguration;
//    private final IEventBus mEventBus;
    private final RNBundlesDao mRNBundlesDao;
    private final Map<Long, SdkBundleDownloadRequest> mRequestMap;
    private final ITeamsApplication mTeamsApplication;

    @Inject
    public SdkBundleDownloadManager(@NonNull @Named("CodepushBundle") ISdkBundleDownloader sdkCodepushBundleDownloader,
                                    @NonNull @Named("LocalBundle") ISdkBundleDownloader sdkLocalBundleDownloader,
                                    @NonNull ISdkBundleManager sdkBundleManager,
                                    @NonNull Gson gson,
                                    @NonNull AppConfiguration appConfiguration,
//                                    @NonNull IEventBus eventBus,
                                    @NonNull RNBundlesDao rnBundlesDao,
                                    @NonNull ITeamsApplication teamsApplication) {
        mSdkCodepushBundleDownloader = sdkCodepushBundleDownloader;
        mSdkLocalBundleDownloader = sdkLocalBundleDownloader;
        mSdkBundleManager = sdkBundleManager;
        mGson = gson;
        mAppConfiguration = appConfiguration;
//        mEventBus = eventBus;
        mRNBundlesDao = rnBundlesDao;
        mTeamsApplication = teamsApplication;
        mRequestMap = new ConcurrentHashMap<>();

        // Set callback mechanism
        mSdkCodepushBundleDownloader.setSdkBundleDownloadProgressListener(this);
        mSdkLocalBundleDownloader.setSdkBundleDownloadProgressListener(this);
    }

    @Override
    public List<Long> syncRNApps(@Nullable List<MobileModuleDefinition> mobileModuleDefinitions, @NonNull String syncTriggerSource, @NonNull RNAppsDao rnAppsDao,
                                 @NonNull IScenarioManager scenarioManager, @NonNull ILogger logger, @NonNull IExperimentationManager experimentationManager) {
        if (ListUtils.isListNullOrEmpty(mobileModuleDefinitions)) {
            return new ArrayList<>();
        }
        List<Long> requestIds = new ArrayList<>();
        for (MobileModuleDefinition mobileModuleDefinition : mobileModuleDefinitions) {
            @SdkBundleUtils.BundleSource final String source = SdkBundleUtils.getBundleSourceType(mobileModuleDefinition.rnPackageUrl);

            Map<String, Object> databag = new HashMap<>();
            databag.put("addId", mobileModuleDefinition.id);

            SdkScenarioMetadata metadata = new SdkScenarioMetadata();
            metadata.setAppId(mobileModuleDefinition.appId);
            metadata.setSyncTriggerSource(syncTriggerSource);


            long requestId = generateRequestId();
            SdkBundleDownloadRequest request = new SdkBundleDownloadRequest(requestId, mobileModuleDefinition.id, source, rnAppsDao, scenarioManager, logger);
            mRequestMap.put(requestId, request);
            requestIds.add(requestId);

            switch (source) {
                case LOCAL: {
                    ScenarioContext scenarioContext = scenarioManager.startScenario(ScenarioName.SYNC_LOCAL_RN_APP, null, databag, metadata.toString());
                    request.setScenarioContext(scenarioContext);
                    final String bundleName = SdkBundleUtils.getBundleName(mobileModuleDefinition.rnPackageUrl);
                    mSdkLocalBundleDownloader.download(mobileModuleDefinition.id, bundleName, requestId, logger, scenarioManager,
                            scenarioContext, experimentationManager, rnAppsDao);
                    break;
                }
                case CODEPUSH: {
                    ScenarioContext scenarioContext = scenarioManager.startScenario(ScenarioName.SYNC_CODEPUSH_RN_APP, null, databag, metadata.toString());
                    request.setScenarioContext(scenarioContext);
                    String deploymentKey = SdkHelper.getDeploymentKey(mobileModuleDefinition.id, experimentationManager, mobileModuleDefinition);
                    mSdkCodepushBundleDownloader.download(mobileModuleDefinition.id, deploymentKey, requestId, logger, scenarioManager,
                            scenarioContext, experimentationManager, rnAppsDao);
                    break;
                }
                default: {
                    logger.log(LogPriority.WARNING, LOG_TAG, "Unknown Format : " + source);
                }
            }
        }
        return requestIds;
    }

    @Override
    public void onBundleDownloadSuccess(final long requestId, @NonNull final String appId, @NonNull final String tempBundleLocation) {
        SdkBundleDownloadRequest request = mRequestMap.get(requestId);
        if (request == null) {
            return;
        }
        ILogger logger = request.getLogger();

        // Update SdkBundleDownloadRequest: DOWNLOADED
        onBundleDownloadStatusUpdated(requestId, DOWNLOADED);

        // Check bundle validity
        @SdkConstants.SdkRequestDownloadStatus
        int bundleValidity = SdkRNValidator.validateBundle(tempBundleLocation);
        onBundleDownloadStatusUpdated(requestId, bundleValidity);


        if (bundleValidity == JS_FILE_EXISTS) {
            // Check manifest validity
            @SdkConstants.SdkRequestDownloadStatus
            int manifestValidity = SdkRNValidator.validateManifest(tempBundleLocation);
            onBundleDownloadStatusUpdated(requestId, manifestValidity);

            if (manifestValidity == MANIFEST_FILE_EXISTS) {
                // Read manifest version
                try {
                    SdkAppManifest sdkAppManifest = parseManifest(tempBundleLocation, mAppConfiguration.isExperimentalRnSdkAllowed());
                    request.setVersion(sdkAppManifest.version);

                    logger.log(LogPriority.INFO, LOG_TAG, "App ID: %s and Bundle version: %s", appId, sdkAppManifest.version);
                    logger.log(LogPriority.INFO, LOG_TAG, "App ID: %s and temp bundle location: %s", appId, tempBundleLocation);

                    // Check if bundle exists
                    boolean bundleAlreadyExists = mSdkBundleManager.exists(appId, sdkAppManifest.version);
                    if (!bundleAlreadyExists) {
                        mSdkBundleManager.addBundle(appId, sdkAppManifest, tempBundleLocation, request);
                        onBundleDownloadStatusUpdated(requestId, BUNDLE_ADDED);
                    } else {
                        onBundleDownloadStatusUpdated(requestId, BUNDLE_ALREADY_EXISTS);
                    }
                    // insertOrUpdateInRNAppTable(appId, sdkAppManifest, request.getRnAppsDao());
                } catch (Exception e) {
                    // Update SdkBundleDownloadRequest: ERROR_MANIFEST_PARSING_FAILED
                    onBundleDownloadStatusUpdated(requestId, ERROR_MANIFEST_PARSING_FAILED);
                    logger.log(LogPriority.ERROR, LOG_TAG, e);
                }
            }
        }
        clearAppState(requestId, appId);
    }

    @Override
    public void onBundleDownloadFailure(long requestId, @NonNull String appId, int failureReason) {
        onBundleDownloadStatusUpdated(requestId, failureReason);
        clearAppState(requestId, appId);
    }

    @Override
    public void onBundleDownloadNotAvailable(long requestId, @NonNull String appId) {
        onBundleDownloadStatusUpdated(requestId, UPDATE_NOT_AVAILABLE);
        clearAppState(requestId, appId);
    }

    @Override
    public void onBundleAlreadyExists(long requestId, @NonNull String appId, @NonNull RNBundle rnBundle) {
        SdkBundleDownloadRequest request = mRequestMap.get(requestId);
        onBundleDownloadStatusUpdated(requestId, BUNDLE_ALREADY_EXISTS);
        SdkAppManifest sdkAppManifest = mGson.fromJson(rnBundle.manifest, SdkAppManifest.class);
        /*if (request != null) {
            insertOrUpdateInRNAppTable(appId, sdkAppManifest, request.getRnAppsDao());
        }*/
        clearAppState(requestId, appId);
    }

    @Override
    public void onBundleDownloadStatusUpdated(long requestId, @SdkConstants.SdkRequestDownloadStatus int status) {
        SdkBundleDownloadRequest request = mRequestMap.get(requestId);
        if (request != null) {
            IScenarioManager scenarioManager = request.getScenarioManager();
            ILogger logger = request.getLogger();
            ScenarioContext scenarioContext = request.getScenarioContext();
            request.setStatus(status);
            switch (status) {
                case ERROR_DOWNLOAD_FAILED:
                    scenarioManager.endScenarioOnError(scenarioContext, RN_BUNDLE_DOWNLOAD_ERROR, "ERROR_DOWNLOAD_FAILED");
                    break;
                case ERROR_BUNDLE_UNAVAILABLE:
                    scenarioManager.endScenarioOnError(scenarioContext, RN_BUNDLE_PARSING_ERROR, "ERROR_BUNDLE_UNAVAILABLE");
                    break;
                case ERROR_JS_FILE_UNAVAILABLE:
                    scenarioManager.endScenarioOnError(scenarioContext, RN_BUNDLE_PARSING_ERROR, "ERROR_JS_FILE_UNAVAILABLE");
                    break;
                case ERROR_MANIFEST_UNAVAILABLE:
                    scenarioManager.endScenarioOnError(scenarioContext, RN_BUNDLE_PARSING_ERROR, "ERROR_MANIFEST_UNAVAILABLE");
                    break;
                case ERROR_MANIFEST_PARSING_FAILED:
                    scenarioManager.endScenarioOnError(scenarioContext, RN_BUNDLE_PARSING_ERROR, "ERROR_MANIFEST_PARSING_FAILED");
                    break;
                case ERROR_BUNDLE_UNZIP_FAILED:
                    scenarioManager.endScenarioOnError(scenarioContext, RN_BUNDLE_PARSING_ERROR, "ERROR_BUNDLE_UNZIP_FAILED");
                    break;
                case ERROR_INVALID_MANIFEST:
                    scenarioManager.endScenarioOnError(scenarioContext, RN_BUNDLE_PARSING_ERROR, "ERROR_INVALID_MANIFEST");
                    break;
                case UPDATE_NOT_AVAILABLE:
                    scenarioManager.endScenarioOnSuccess(scenarioContext, "UPDATE_NOT_AVAILABLE");
                    break;
                case BUNDLE_ALREADY_EXISTS:
                    scenarioManager.endScenarioOnSuccess(scenarioContext, "BUNDLE_ALREADY_EXISTS");
                    break;
                case BUNDLE_ADDED:
                    scenarioManager.endScenarioOnSuccess(scenarioContext);
                    break;
                case QUEUED:
                case DOWNLOADING:
                case DOWNLOADED:
                case JS_FILE_EXISTS:
                case MANIFEST_FILE_EXISTS:
                default:
                    break;
            }
            logger.log(LogPriority.INFO, LOG_TAG, "appId: " + request.getAppId() + ", status: " + status);
        }
        // mEventBus.post(SDK_BUNDLE_SYNC_REQUEST_UPDATED, request);
    }

    private static SdkAppManifest parseManifest(@NonNull String bundleLocation, boolean isExperimentalRnSdkAllowed) throws Exception {
        String sdkAppManifestContent = IOUtilities.readFileContent(new File(bundleLocation, "manifest/manifest.json"));
        JsonObject jsonObject = JsonUtils.parseObject(sdkAppManifestContent, JsonObject.class, null);
        if (jsonObject == null) {
            throw new Exception("Manifest not parsable");
        }

        String targetReactNativeSdkVersion = JsonUtils.parseString(jsonObject, "targetReactNativeSdkVersion");
        return SdkAppManifestParserProvider.getAppManifestParser(targetReactNativeSdkVersion, isExperimentalRnSdkAllowed).parseManifest(jsonObject);
    }

    private void clearAppState(long requestId, @NonNull String appId) {
        SdkBundleDownloadRequest request = mRequestMap.get(requestId);
        if (request != null) {
            if (CODEPUSH.equals(request.getSource())) {
                mSdkCodepushBundleDownloader.clearTemporaryStorage(appId, request.getLogger());
            } else {
                mSdkLocalBundleDownloader.clearTemporaryStorage(appId, request.getLogger());
            }
        } else {
            ILogger logger = mTeamsApplication.getLogger(null);
            logger.log(LogPriority.WARNING, LOG_TAG, "SdkBundleDownloadRequest not found with requestId:" + requestId + ", AppId:" + appId);
        }
    }

    private void insertOrUpdateInRNAppTable(@NonNull String appId, @NonNull SdkAppManifest sdkAppManifest, @NonNull RNAppsDao rnAppsDao) {
        String bundleLocation = mSdkBundleManager.getBundleLocation(appId, sdkAppManifest.version);
        if (bundleLocation != null) {
            RNApp rnapp = rnAppsDao.fromId(appId);
            if (rnapp == null) {
                rnapp = new RNApp();
                rnapp.moduleId = appId;
                rnapp.bundleVersion = sdkAppManifest.version;
            } else {
                // If newer bundle version number is less or equal than existing version number, ignore it
                if (rnapp.bundleVersion == null || SdkVersionUtils.compareSDKVersions(sdkAppManifest.version, rnapp.bundleVersion) != 1) {
                    return;
                }

                // If newer version is incompatible and older version is compatible, ignore it
                @SdkBundleUtils.CompatibilityStatus
                String newerBundleCompatibility = SdkVersionUtils.checkCompatibilityStatus(sdkAppManifest.targetReactNativeSdkVersion,
                        mAppConfiguration.isExperimentalRnSdkAllowed());

                if (INCOMPATIBLE_UPDATE_RNAPP.equals(newerBundleCompatibility)) {
                    RNBundle oldBundle = mRNBundlesDao.from(appId, rnapp.bundleVersion);
                    SdkAppManifest oldSdkAppManifest = mGson.fromJson(oldBundle.manifest, SdkAppManifest.class);

                    @SdkBundleUtils.CompatibilityStatus
                    String olderBundleCompatibility = SdkVersionUtils.checkCompatibilityStatus(oldSdkAppManifest.targetReactNativeSdkVersion,
                            mAppConfiguration.isExperimentalRnSdkAllowed());
                    if (COMPATIBLE.equals(olderBundleCompatibility)) {
                        return;
                    }
                }
                rnapp.bundleVersion = sdkAppManifest.version;
            }
            rnapp.lastUpdateCheckedTimeInMillis = System.currentTimeMillis();
            rnAppsDao.save(rnapp);
        }
    }
}
