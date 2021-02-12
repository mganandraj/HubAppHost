package com.teamssdksim.modules;

import com.microsoft.skype.teams.mobilemodules.MobileModuleSyncManager;
import com.microsoft.skype.teams.sdk.rnbundle.SdkBundleManager;
import com.teamssdksim.TeamSDKActivity;
import com.teamssdksim.TeamsSdkSimMobileModuleFactory;

import javax.inject.Singleton;
import dagger.Component;

// https://developer.android.com/training/dependency-injection/dagger-android

@Component(
        modules = {
                AppConfigurationModule.class,
                AppDefinitionDaoModule.class,
                ContextModule.class,
                ExperimentationManagerModule.class,
                LoggerModule.class,
                MobileModuleManagerModule.class,
                PlatformAppFactoryModule.class,
                PlatformAppManagerModule.class,
                PreferencesModule.class,
                RNAppsDaoModule.class,
                RNBundlesDaoModule.class,
                ScenarioManagerModule.class,
                SdkBundleManagerModule.class,
                SdkDownloadManagerModule.class,
                SdkRunnerAppManagerModule.class,
                MobileModuleFactoryModule.class,
                MobileModuleSyncManagerModule.class,
                TelemetryLoggerModule.class,
                TaskRunnerModule.class,
                TeamsApplicationModule.class
        })

@Singleton
public interface AppComponent {
    void inject(TeamSDKActivity loginActivity);
}
