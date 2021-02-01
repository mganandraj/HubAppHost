/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk.react.packages;

import androidx.annotation.NonNull;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.microsoft.skype.teams.sdk.SdkApplicationContext;
import com.microsoft.skype.teams.sdk.react.modules.SdkLoggerModule;
import com.microsoft.teams.core.app.ITeamsApplication;
/*import com.microsoft.skype.teams.sdk.react.injection.SdkModuleFactory;
import com.microsoft.skype.teams.sdk.react.injection.SdkPackageScope;
import com.microsoft.skype.teams.sdk.react.injection.SdkViewManagerFactory;
import com.microsoft.skype.teams.sdk.react.injection.components.SdkModuleComponent;
import com.microsoft.skype.teams.sdk.react.modules.SdkAuthenticationServiceModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkChannelPickerModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkChatsProviderModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkContactsProviderModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkDateTimePickerModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkDeviceContactsProviderModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkEndpointProviderModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkFetchBlobModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkHttpClientModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkImageAndFilePickerModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkImagesProviderModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkLoggerModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkMessageModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkNativeEventPublisherModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkNavigationServiceModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkNetworkConnectivityManagerModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkShareUtilsModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkSharepointFilePreviewModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkTeamsAndChannelsProviderModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkTeamsShellInteractorModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkTelemetryClientModule;
import com.microsoft.skype.teams.sdk.react.modules.SdkUsersProviderModule;
import com.microsoft.skype.teams.sdk.react.modules.storage.SdkAsyncStorageModule;
import com.microsoft.skype.teams.sdk.react.modules.storage.SdkSecureStorageModule;
import com.microsoft.skype.teams.sdk.react.viewmanagers.SdkFluentIconViewManager;
import com.microsoft.skype.teams.sdk.react.viewmanagers.SdkStateLayoutViewManager;
import com.microsoft.skype.teams.sdk.react.viewmanagers.SdkTeamsPickerViewManager;
import com.microsoft.skype.teams.sdk.react.viewmanagers.SdkUserAvatarViewManager;
import com.microsoft.skype.teams.sdk.react.viewmanagers.SdkWebViewManager;*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * The package responsible for exporting all the react native modules needed for the SDK.
 */
// @SdkPackageScope
public class SdkReactPackage implements ReactPackage {
    ITeamsApplication mTteamsApplication;
    SdkApplicationContext mSdkApplicationContext;
    // private final SdkModuleComponent.Factory mSdkModuleComponentFactory;

    /*@Inject
    public SdkReactPackage(@NonNull SdkModuleComponent.Factory sdkModuleComponentFactory) {
        mSdkModuleComponentFactory = sdkModuleComponentFactory;
    }*/
    public SdkReactPackage(ITeamsApplication teamsApplication, SdkApplicationContext sdkApplicationContext) {
        mTteamsApplication = teamsApplication;
        mSdkApplicationContext = sdkApplicationContext;
        // mSdkModuleComponentFactory = sdkModuleComponentFactory;
    }

    @Override
    public List<NativeModule> createNativeModules(@NonNull ReactApplicationContext reactContext) {
        /*final SdkModuleComponent sdkModuleComponent = mSdkModuleComponentFactory.create(reactContext);
        final SdkModuleFactory sdkModuleFactory = sdkModuleComponent.sdkModuleFactory();*/

        // TODO: should we restrict the APIs based on the application scopes?
        List<NativeModule> modules = new ArrayList<>();
        modules.add(new SdkLoggerModule(reactContext, "SdkLoggerModule",  mTteamsApplication, mTteamsApplication.getLogger(""), mSdkApplicationContext));
        /*modules.add(sdkModuleFactory.create(SdkNativeEventPublisherModule.class));
        modules.add(sdkModuleFactory.create(SdkAuthenticationServiceModule.class));
        modules.add(sdkModuleFactory.create(SdkLoggerModule.class));
        modules.add(sdkModuleFactory.create(SdkDeviceContactsProviderModule.class));
        modules.add(sdkModuleFactory.create(SdkNavigationServiceModule.class));
        modules.add(sdkModuleFactory.create(SdkNetworkConnectivityManagerModule.class));
        modules.add(sdkModuleFactory.create(SdkTeamsShellInteractorModule.class));
        modules.add(sdkModuleFactory.create(SdkHttpClientModule.class));
        modules.add(sdkModuleFactory.create(SdkTeamsAndChannelsProviderModule.class));
        modules.add(sdkModuleFactory.create(SdkDateTimePickerModule.class));
        modules.add(sdkModuleFactory.create(SdkSharepointFilePreviewModule.class));
        modules.add(sdkModuleFactory.create(SdkChannelPickerModule.class));
        modules.add(sdkModuleFactory.create(SdkImageAndFilePickerModule.class));
        modules.add(sdkModuleFactory.create(SdkFetchBlobModule.class));
        modules.add(sdkModuleFactory.create(SdkTelemetryClientModule.class));
        modules.add(sdkModuleFactory.create(SdkMessageModule.class));
        modules.add(sdkModuleFactory.create(SdkShareUtilsModule.class));
        modules.add(sdkModuleFactory.create(SdkImagesProviderModule.class));
        modules.add(sdkModuleFactory.create(SdkUsersProviderModule.class));
        modules.add(sdkModuleFactory.create(SdkContactsProviderModule.class));
        modules.add(sdkModuleFactory.create(SdkEndpointProviderModule.class));
        modules.add(sdkModuleFactory.create(SdkChatsProviderModule.class));
        modules.add(sdkModuleFactory.create(SdkAsyncStorageModule.class));
        modules.add(sdkModuleFactory.create(SdkSecureStorageModule.class));*/
        return modules;
    }

    /**
     * @return a list of view managers that should be registered with UIManagerModule.
     */
    @Override
    @NonNull
    public List<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactContext) {
        /*final SdkModuleComponent sdkModuleComponent = mSdkModuleComponentFactory.create(reactContext);
        final SdkViewManagerFactory sdkViewManagerFactory = sdkModuleComponent.sdkViewManagerFactory();*/

        List<ViewManager> viewManagers = new ArrayList<>();
        /*return Arrays.<ViewManager>asList(
                sdkViewManagerFactory.create(SdkUserAvatarViewManager.class),
                sdkViewManagerFactory.create(SdkStateLayoutViewManager.class),
                sdkViewManagerFactory.create(SdkTeamsPickerViewManager.class),
                sdkViewManagerFactory.create(SdkFluentIconViewManager.class),
                sdkViewManagerFactory.create(SdkWebViewManager.class));*/
        return viewManagers;
    }
}
