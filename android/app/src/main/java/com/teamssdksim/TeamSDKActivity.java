package com.teamssdksim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.skype.teams.mobilemodules.IMobileModule;
import com.microsoft.skype.teams.mobilemodules.IPlatformApp;
import com.microsoft.skype.teams.mobilemodules.MobileModuleManager;
import com.microsoft.skype.teams.mobilemodules.MobileModuleSyncManager;
import com.microsoft.skype.teams.mobilemodules.PlatformApp;
import com.microsoft.skype.teams.mobilemodules.PlatformAppManager;
import com.microsoft.skype.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.skype.teams.sdk.SdkApplicationContext;
import com.microsoft.skype.teams.sdk.SdkRunnerAppManager;
import com.microsoft.skype.teams.sdk.rnbundle.ISdkBundleDownloadManager;
import com.microsoft.skype.teams.sdk.rnbundle.ISdkBundleDownloader;
import com.microsoft.skype.teams.sdk.rnbundle.ISdkBundleManager;
import com.microsoft.skype.teams.sdk.rnbundle.SdkBundleDownloadManager;
import com.microsoft.skype.teams.sdk.rnbundle.SdkBundleManager;
import com.microsoft.skype.teams.sdk.rnbundle.SdkCodepushBundleDownloader;
import com.microsoft.skype.teams.sdk.rnbundle.SdkLocalBundleDownloader;
import com.microsoft.skype.teams.services.configuration.AppConfiguration;
import com.microsoft.skype.teams.storage.IExperimentationManager;
import com.microsoft.skype.teams.storage.dao.appdefinition.AppDefinitionDao;
import com.microsoft.skype.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.skype.teams.storage.models.MobileModuleDefinition;
import com.microsoft.skype.teams.storage.tables.AppDefinition;
import com.microsoft.teams.androidutils.tasks.TaskRunner;
import com.microsoft.teams.core.app.ITeamsApplication;

import javax.inject.Named;

public class TeamSDKActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_s_d_k);

        ViewGroup host = (ViewGroup)findViewById(R.id.reactHost);

        TeamsSdkSimExperimentationManager teamsSdkSimExperimentationManager = new TeamsSdkSimExperimentationManager();
        TeamsSdkSimTelemetryLogger teamsSdkSimTelemetryLogger = new TeamsSdkSimTelemetryLogger();
        TeamsSdkSimLogger teamsSdkSimLogger = new TeamsSdkSimLogger();
        AppConfiguration appConfiguration = new TeamsSdkSimAppConfiguration();
        Preferences preferences = new Preferences();
        TeamsSdkSimScenarioManager teamsSdkSimScenarioManager = new TeamsSdkSimScenarioManager(teamsSdkSimExperimentationManager,
                teamsSdkSimTelemetryLogger,
                teamsSdkSimLogger,
                preferences);

        TeamsSdkSimApplication fakeApplication = new TeamsSdkSimApplication(this, this.getApplication(), teamsSdkSimExperimentationManager,  teamsSdkSimScenarioManager, teamsSdkSimLogger,teamsSdkSimTelemetryLogger,  preferences);

        SdkRunnerAppManager sdkRunnerAppManager = new SdkRunnerAppManager(this, new GsonBuilder().create(), fakeApplication);
        sdkRunnerAppManager.syncRunnerApp();

        TeamsSdkSimAppDefinitionDao appDefinitionDao = new TeamsSdkSimAppDefinitionDao(fakeApplication);

        TeamsSdkRNAppsDao teamsSdkRNAppsDao = new TeamsSdkRNAppsDao();
        TeamsSdkSimRNBundlesDao teamsSdkSimRNBundlesDao = new TeamsSdkSimRNBundlesDao();


        SdkBundleManager sdkBundleManager = new SdkBundleManager(fakeApplication, teamsSdkSimRNBundlesDao);

        ISdkBundleDownloadManager sdkBundleDownloadManager = new SdkBundleDownloadManager(
                new SdkCodepushBundleDownloader(fakeApplication, sdkBundleManager),
                new SdkLocalBundleDownloader(fakeApplication),
                sdkBundleManager,
                new GsonBuilder().create(),
                appConfiguration,
                teamsSdkSimRNBundlesDao,
                fakeApplication
        );

        TeamsSdkSimMobileModuleFactory teamsSdkSimMobileModuleFactory = new TeamsSdkSimMobileModuleFactory(TeamSDKActivity.this, sdkRunnerAppManager, fakeApplication, sdkBundleDownloadManager, teamsSdkRNAppsDao, teamsSdkSimRNBundlesDao, appConfiguration, preferences, teamsSdkSimScenarioManager, teamsSdkSimExperimentationManager);
        TeamsSdkSimPlatformAppFactory teamsSdkSimPlatformAppFactory = new TeamsSdkSimPlatformAppFactory(appDefinitionDao,
                teamsSdkSimMobileModuleFactory,
                sdkRunnerAppManager);

        PlatformAppManager platformAppManager = new PlatformAppManager(teamsSdkSimPlatformAppFactory,
                sdkRunnerAppManager,
                appDefinitionDao);

        Handler uiThreadHandler = new Handler(Looper.getMainLooper());
        TaskRunner taskRunner = new TaskRunner(uiThreadHandler);

        MobileModuleManager mobileModuleManager = new MobileModuleManager(platformAppManager, teamsSdkSimExperimentationManager);

        MobileModuleSyncManager mobileModuleSyncManager = new MobileModuleSyncManager(this,
                fakeApplication,
                fakeApplication.getLogger(""),
                fakeApplication.getScenarioManager(""),
//                                   @NonNull IEventBus eventBus,
                taskRunner,
                teamsSdkSimExperimentationManager,
//                                   @NonNull INativePackagesProvider nativePackagesProvider,
                preferences,
                mobileModuleManager,
                appDefinitionDao);
        mobileModuleSyncManager.syncMobileModules();

        Button startButton = new Button(this);
        startButton.setText("Start");;
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // IPlatformApp platformApp = platformAppManager.get("runner-TeamsSdkSimAppId");
                IPlatformApp platformApp = platformAppManager.get("0b04a6cf-e696-4eb0-bac2-aaf883c9add5");

                IMobileModule mobileModule = platformApp.getMobileModule();
                SdkApplicationContext sdkApplicationContext = mobileModule.getSdkApplicationContext();
                ReactInstanceManager instanceManager = sdkApplicationContext.getReactInstanceManager(TeamSDKActivity.this);

                ReactRootView reactRootView = new ReactRootView(TeamSDKActivity.this);

                Bundle reactAppParams = new Bundle();
                reactAppParams.putBundle("appParams", sdkApplicationContext.getAppInitializationParamsAsBundle(""));
                reactAppParams.putBundle("_hostParams", sdkApplicationContext.getHostParamsAsBundle("TestSdkSimInstanceId"));
                reactAppParams.putString("_params", "");

                reactRootView.startReactApplication(instanceManager, "HomeComponent", reactAppParams);

                host.addView(reactRootView);
            }
        });

        host.addView(startButton);


    }
}