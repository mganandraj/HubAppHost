package com.microsoft.hubapphost.modules;

import com.microsoft.teams.mobilemodules.IMobileModuleManager;
import com.microsoft.teams.mobilemodules.IPlatformAppManager;
import com.microsoft.teams.mobilemodules.MobileModuleManager;
import com.microsoft.teams.storage.IExperimentationManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MobileModuleManagerModule {

    @Singleton
    @Provides
    static IMobileModuleManager provideMobileModuleManager(IPlatformAppManager platformAppManager,
                                                           IExperimentationManager experimentationManager) {
        return new MobileModuleManager(platformAppManager, experimentationManager);
    }
}