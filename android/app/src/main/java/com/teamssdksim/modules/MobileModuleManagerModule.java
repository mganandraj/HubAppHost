package com.teamssdksim.modules;

import com.microsoft.skype.teams.mobilemodules.IMobileModuleManager;
import com.microsoft.skype.teams.mobilemodules.IPlatformAppManager;
import com.microsoft.skype.teams.mobilemodules.MobileModuleManager;
import com.microsoft.skype.teams.storage.IExperimentationManager;

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