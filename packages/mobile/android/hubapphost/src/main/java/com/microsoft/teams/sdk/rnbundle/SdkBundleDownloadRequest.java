package com.microsoft.teams.sdk.rnbundle;

import androidx.annotation.NonNull;

import com.microsoft.teams.logger.ILogger;
import com.microsoft.teams.services.diagnostics.telemetryschema.ScenarioContext;
//import com.microsoft.teams.storage.dao.rnapps.RNAppsDao;
import com.microsoft.teams.storage.dao.rnapps.RNAppsDao;
import com.microsoft.teams.core.services.IScenarioManager;

/**
 * Model class for React Native bundle sync request
 */
public class SdkBundleDownloadRequest {
    @SdkBundleUtils.BundleSource
    private final String mSource;
    private final String mAppId;
    private final long mRequestId;
    private final RNAppsDao mRnAppsDao;
    private final IScenarioManager mScenarioManager;
    private final ILogger mLogger;

    private ScenarioContext mScenarioContext;
    private int mStatus;
    private String mVersion;

    public SdkBundleDownloadRequest(long requestId,
                                    @NonNull String appId,
                                    @NonNull String source,
                                    @NonNull RNAppsDao rnAppsDao,
                                    @NonNull IScenarioManager scenarioManager,
                                    @NonNull ILogger logger) {
        mAppId = appId;
        mSource = source;
        mRequestId = requestId;
        mRnAppsDao = rnAppsDao;
        mLogger = logger;
        mScenarioManager = scenarioManager;
    }

    public void setScenarioContext(@NonNull ScenarioContext scenarioContext) {
        mScenarioContext = scenarioContext;
    }

    public IScenarioManager getScenarioManager() {
        return mScenarioManager;
    }

    public ILogger getLogger() {
        return mLogger;
    }

    public void setVersion(@NonNull String version) {
        this.mVersion = version;
    }

    public void setStatus(int status) {
        this.mStatus = status;
    }

    public String getSource() {
        return mSource;
    }

    public String getAppId() {
        return mAppId;
    }

    public int getStatus() {
        return mStatus;
    }

    public String getVersion() {
        return mVersion;
    }

    public long getRequestId() {
        return mRequestId;
    }

    public ScenarioContext getScenarioContext() {
        return mScenarioContext;
    }

    public RNAppsDao getRnAppsDao() {
        return mRnAppsDao;
    }
}