/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */
package com.microsoft.teams.sdk.rnbundle;

import com.microsoft.teams.services.utilities.StringUtilities;

/**
 * This aggregates the scenario tags for sdk scenario markers and compiles them into one fashion.
 */
public class SdkScenarioMetadata {
    private String mAppId;
    private String mLaunchType;
    private String mViewType;
    private String mSyncTriggerSource;
    private String mAppVersion;

    public SdkScenarioMetadata() {
        mAppId = StringUtilities.EMPTY;
        mLaunchType = StringUtilities.EMPTY;
        mViewType = StringUtilities.EMPTY;
        mSyncTriggerSource = StringUtilities.EMPTY;
        mAppVersion = StringUtilities.EMPTY;
    }

    public void setAppId(String appId) {
        mAppId = appId;
    }

    public void setLaunchType(String launchType) {
        mLaunchType = launchType;
    }

    public void setViewType(String viewType) {
        mViewType = viewType;
    }

    public void setSyncTriggerSource(String syncTriggerSource) {
        mSyncTriggerSource = syncTriggerSource;
    }

    public void setAppVersion(String appVersion) {
        mAppVersion = appVersion;
    }

    /**
     * Do not change the index of attributes, Add them in the end if required. We are parsing them to create aria dashboard. Current indexes are
     * At 0 > App Id
     * At 1 > Launch Type
     * At 2 > View Type
     * At 3 > Sync Trigger Source
     */
    @Override
    public String toString() {
        return mAppId + ","
                + mLaunchType + ","
                + mViewType + ","
                + mSyncTriggerSource + ","
                + mAppVersion;
    }
}
