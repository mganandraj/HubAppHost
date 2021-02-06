/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.mobilemodules;

//import com.microsoft.skype.teams.data.events.DataEvents;
//import com.microsoft.skype.teams.events.EventHandler;
//import com.microsoft.skype.teams.events.IEventBus;
//import com.microsoft.skype.teams.mobilemodules.injection.component.PlatformAppComponent;
import com.microsoft.skype.teams.sdk.SdkRunnerAppManager;
import com.microsoft.skype.teams.sdk.utils.SdkRunnerUtils;
import com.microsoft.skype.teams.storage.dao.appdefinition.AppDefinitionDao;
import com.microsoft.skype.teams.storage.models.MobileModuleDefinition;
import com.microsoft.skype.teams.storage.tables.AppDefinition;
//import com.microsoft.teams.injection.PlatformAppId;
//import com.microsoft.teams.injection.UserScope;
import com.teamssdksim.TeamsSdkSimPlatformAppFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;

/**
 * Manages Platform apps
 */
//@UserScope
public class PlatformAppManager implements IPlatformAppManager {
    // private final PlatformAppComponent.Factory mPlatformAppComponentFactory;
    private TeamsSdkSimPlatformAppFactory mPlatformAppFactory;
    private final SdkRunnerAppManager mSdkRunnerAppManager;
    private final AppDefinitionDao mAppDefinitionDao;
    private final Map<String, IPlatformApp> mPlatformAppMap = new HashMap<>();
    // private final IEventBus mEventBus;
    /**
     * ToDo: Get rid of negative cache after these changes:
     * 1. MobileModuleDefinition table has been deleted.
     * 2. Cache (including negative cache) has been implemented on AppDefinitionDao
     **/
    private final List<String> mNegativeCache = new ArrayList<>();

    @Inject
    public PlatformAppManager(@NonNull /*PlatformAppComponent.Factory platformAppComponentFactory*/ TeamsSdkSimPlatformAppFactory platformAppFactory,
                              @NonNull SdkRunnerAppManager sdkRunnerAppManager,
                              @NonNull AppDefinitionDao appDefinitionDao/*,
                              @NonNull IEventBus eventBus*/) {
//        mPlatformAppComponentFactory = platformAppComponentFactory;
        mPlatformAppFactory = platformAppFactory;
        mSdkRunnerAppManager = sdkRunnerAppManager;
        mAppDefinitionDao = appDefinitionDao;
//        mEventBus = eventBus;

//        subscribeToEventBus();
    }

//    private void subscribeToEventBus() {
//        mEventBus.subscribe(DataEvents.USER_ENTITLEMENTS_UPDATED, EventHandler.background(
//                data -> {
//                    // Clear negative cache
//                    mNegativeCache.clear();
//
//                    // Update app definition object in PlatformApp
//                    Map<String, AppDefinition> map = mAppDefinitionDao.fromIds(new ArrayList<>(mPlatformAppMap.keySet()));
//                    for (Map.Entry<String, AppDefinition> entry: map.entrySet()) {
//                        IPlatformApp platformApp = mPlatformAppMap.get(entry.getKey());
//                        if (platformApp != null) {
//                            platformApp.setAppDefinition(entry.getValue());
//                        }
//                    }
//
//                }));
//    }

    @WorkerThread
    @Nullable
    @Override
    public IPlatformApp get(@NonNull /*@PlatformAppId*/ String appId) {
        IPlatformApp platformApp = mPlatformAppMap.get(appId);

        if (platformApp == null) {
            if (mNegativeCache.contains(appId)) {
                return null;
            }

            AppDefinition appDefinition;
            if (SdkRunnerUtils.isRunnerApp(appId)) {
                appDefinition = mSdkRunnerAppManager.getAppDefinition();
            } else {
                appDefinition = mAppDefinitionDao.fromId(appId);
            }

            if (appDefinition != null) {
                MobileModuleDefinition mobileModuleDefinition = appDefinition.getMobileModuleDefinition();
                if (mobileModuleDefinition != null) {
                    // platformApp = mPlatformAppComponentFactory.create(appId, mobileModuleDefinition).platformApp();
                    // platformApp = mPlatformAppFactory.

                    platformApp = mPlatformAppFactory.create(appId, mobileModuleDefinition, mAppDefinitionDao, mPlatformAppFactory.getMobileModuleFactory(), mSdkRunnerAppManager);
                    mPlatformAppMap.put(appId, platformApp);
                }
            } else {
                mNegativeCache.add(appId);
            }
        }

        return platformApp;
    }
}
