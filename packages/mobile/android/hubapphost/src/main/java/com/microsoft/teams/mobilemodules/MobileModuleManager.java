/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.mobilemodules;

import android.content.Intent;
import android.net.Uri;

//import com.microsoft.teams.data.events.DataEvents;
//import com.microsoft.teams.data.events.TenantOrAccountSwitchedData;
//import com.microsoft.teams.events.EventHandler;
//import com.microsoft.teams.events.IEventBus;
//import com.microsoft.teams.events.IEventHandler;
//import com.microsoft.teams.events.IHandlerCallable;
//import com.microsoft.teams.nativemodules.INativePackagesProvider;
import com.microsoft.teams.sdk.SdkHelper;
import com.microsoft.teams.sdk.react.SdkAppNativeEventEmitter;
//import com.microsoft.teams.services.authorization.IAccountManager;
import com.microsoft.teams.storage.IExperimentationManager;
//import com.microsoft.teams.core.data.extensions.IActivityFeedExtension;
//import com.microsoft.teams.core.data.extensions.IContactCardExtension;
//import com.microsoft.teams.core.data.extensions.IFreDataExtension;
//import com.microsoft.teams.injection.UserScope;
//import com.microsoft.teams.core.nativemodules.NativePackage;
//import com.microsoft.teams.core.services.navigation.ITeamsNavigationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import bolts.Task;

/**
 * Manages the mobile modules
 */
//@UserScope
public class MobileModuleManager implements IMobileModuleManager {
    private static final String LOG_TAG = "MobileModuleManager";
//    private final INativePackagesProvider mNativePackagesProvider;
//    private final IAccountManager mAccountManager;
    private final IPlatformAppManager mPlatformAppManager;
    private final IExperimentationManager mExperimentationManager;
//    private final ITeamsNavigationService mTeamsNavigationService;
    private final Set<IMobileModule> mMobileModuleSet;

//    private final IEventHandler<TenantOrAccountSwitchedData> mTenantOrAccountSwitchedEventHandler = EventHandler.background(
//            new IHandlerCallable<TenantOrAccountSwitchedData>() {
//                @Override
//                public void handle(@Nullable TenantOrAccountSwitchedData data) {
//                    if (data != null && data.getPreviousAccount().userObjectId.equals(mAccountManager.getUserObjectId())) {
//                        destroyAll(false);
//                    }
//                }
//            });

    @Inject
    public MobileModuleManager(/*@NonNull INativePackagesProvider nativePackagesProvider,*/
                               @NonNull IPlatformAppManager platformAppManager,
//                               @NonNull IEventBus eventBus,
//                               @NonNull IAccountManager accountManager,
                               @NonNull IExperimentationManager experimentationManager
                               /*@NonNull ITeamsNavigationService teamsNavigationService*/) {
//        mNativePackagesProvider = nativePackagesProvider;
//        mAccountManager = accountManager;
        mMobileModuleSet = new CopyOnWriteArraySet<>();
        mPlatformAppManager = platformAppManager;
        mExperimentationManager = experimentationManager;
//        mTeamsNavigationService = teamsNavigationService;

//        eventBus.subscribe(DataEvents.TENANT_OR_ACCOUNT_SWITCHED, mTenantOrAccountSwitchedEventHandler);
    }

    @Nullable
    @Override
    public IMobileModule getMobileModule(@NonNull String appId) {
        IPlatformApp platformApp = mPlatformAppManager.get(appId);
        if (platformApp == null) {
            return null;
        }
        IMobileModule mobileModule = platformApp.getMobileModule();
        if (mobileModule != null) {
            mMobileModuleSet.add(mobileModule);
        }
        return mobileModule;
    }
//
//    @NonNull
//    @Override
//    public List<IContactCardExtension> getContactCardExtensions() {
//        List<IContactCardExtension> list = new ArrayList<>();
//        for (NativePackage nativePackage: mNativePackagesProvider.getNativePackages()) {
//            IContactCardExtension extension = nativePackage.getContactCardExtensionConfiguration();
//            if (extension != null) {
//                list.add(extension);
//            }
//        }
//
//        return list;
//    }
//
//    @NonNull
//    @Override
//    public List<IFreDataExtension> getFreDataExtensions() {
//        List<IFreDataExtension> list = new ArrayList<>();
//        for (NativePackage nativePackage: mNativePackagesProvider.getNativePackages()) {
//            IFreDataExtension extension = nativePackage.getFreDataExtension();
//            if (extension != null) {
//                list.add(extension);
//            }
//        }
//
//        return list;
//    }
//
//    /**
//     * ActivityFeedExtension is a temporary measure to get fetch data routing through RN.
//     * In future, Activity Feeds would get Tasks related feeds through API call.
//     * There is no plan to support this approach routing approach for any other app.
//     */
//    @NonNull
//    @Override
//    @Deprecated
//    public List<IActivityFeedExtension> getActivityFeedExtensions() {
//        List<IActivityFeedExtension> list = new ArrayList<>();
//        IMobileModule plannerModule = getMobileModule(MobileModuleConstants.PLANNER_APP_ID);
//        if (plannerModule != null) {
//            IActivityFeedExtension plannerExtension = plannerModule.getActivityFeedExtension();
//            if (plannerExtension != null) {
//                list.add(plannerExtension);
//            }
//        }
//
//        IMobileModule tasksModule = getMobileModule(MobileModuleConstants.TASKS_APP_ID);
//        if (tasksModule != null) {
//            IActivityFeedExtension tasksExtension = tasksModule.getActivityFeedExtension();
//            if (tasksExtension != null) {
//                list.add(tasksExtension);
//            }
//        }
//
//        // For native packages
//        for (NativePackage nativePackage: mNativePackagesProvider.getNativePackages()) {
//            IActivityFeedExtension extension = nativePackage.getActivityFeedExtension();
//            if (extension != null) {
//                list.add(extension);
//            }
//        }
//
//        return list;
//    }

    @NonNull
    @Override
    public Task<Void> destroyAll(boolean clearStorage) {
        List<Task<Void>> destroyMobileModulePackageTasks = new ArrayList<>();

        for (IMobileModule mobileModule : mMobileModuleSet) {
            if (clearStorage && mobileModule.isEnabled() && mobileModule instanceof ReactNativeMobileModule && mobileModule.getSdkApplicationContext() != null) {
                SdkAppNativeEventEmitter.emitSignOutEvent(mobileModule.getSdkApplicationContext());
            }
            destroyMobileModulePackageTasks.add(mobileModule.destroyModule(clearStorage));
        }
        mMobileModuleSet.clear();
        return Task.whenAll(destroyMobileModulePackageTasks);
    }

    /**
     * @param deepLink - deep link url used by teams activity feed to navigate to modules.
     *                 e.g "https://teams.microsoft.com/l/entity/1ded03cb-ece5-4e7c-9f73-61c375528078/teamstasksint.personalApp.mytasks?label=random+task+list+name&context=%7b%22subEntityId%22%3a%22nt%2fTaskListRecalledFromYourTeam%2fteam%2fed1f7009-5219-40f7-9dfe-c0ae77b633f4%2fchannel%2f19%3ae8a4742c27d949b5a430a0c706ffaa45%40thread.skype%2ftaskLists%2f97ac4eaf341640bb94024b1803cfa67b%22%7d"
     * @return - intent to launch React native mobile module resolved for this deep link.
     * Else return null if no matching mobile module is present. This will only return a RN module if present for this app.
     */
    @Override
    @Nullable
    public Intent resolveMobileModuleForDeepLink(@NonNull Uri deepLink) {
//        if (!mExperimentationManager.enableDeeplinkToMobileModule()) {
//            // if the ecs flag is turned off. let us not deeplink to mobile module.
//            return null;
//        }
//
//        if (!SdkHelper.isValidDeeplinkToTab(deepLink, mTeamsNavigationService)) {
//            return null;
//        }
//
//        List<String> paths = deepLink.getPathSegments();
//        String appId = paths.get(2);
//
//        IMobileModule mobileModule = getMobileModule(appId);
//        return mobileModule != null ? mobileModule.resolveDeepLink(deepLink) : null;
        return null;
    }
}
