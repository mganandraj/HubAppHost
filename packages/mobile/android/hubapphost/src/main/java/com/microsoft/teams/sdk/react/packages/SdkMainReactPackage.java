/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk.react.packages;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.shell.MainPackageConfig;
import com.facebook.react.shell.MainReactPackage;
/*
import com.microsoft.teams.sdk.react.injection.SdkPackageScope;
import com.microsoft.teams.sdk.react.injection.components.SdkModuleComponent;
import com.microsoft.teams.sdk.react.modules.SdkFrescoModule;
*/

/**
 * React Native package which provides alternatives to certain built-in React Native modules.
 */
//@SdkPackageScope
public class SdkMainReactPackage extends MainReactPackage {

    private final MainPackageConfig mConfig;
    // private final SdkModuleComponent.Factory mSdkModuleComponentFactory;

    /*@Inject
    public SdkMainReactPackage(@NonNull MainPackageConfig config,
                               @NonNull SdkModuleComponent.Factory sdkModuleComponentFactory) {
        super(config);
        mConfig = config;
        mSdkModuleComponentFactory = sdkModuleComponentFactory;
    }*/
    public SdkMainReactPackage(@NonNull MainPackageConfig config) {
        super(config);
        mConfig = config;
        // mSdkModuleComponentFactory = sdkModuleComponentFactory;
    }

    @Nullable
    @Override
    public NativeModule getModule(String name, ReactApplicationContext context) {
        /*if (name.equals(FrescoModule.NAME)) {
            return new SdkFrescoModule(context, true, mConfig != null ? mConfig.getFrescoConfig() : null);
        }*/

        return super.getModule(name, context);
    }
}
