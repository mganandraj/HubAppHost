package com.teamssdksim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.microsoft.skype.teams.logger.ILogger;
import com.microsoft.skype.teams.logger.ITelemetryLogger;
import com.microsoft.skype.teams.mobilemodules.IMobileModule;
import com.microsoft.skype.teams.mobilemodules.IPlatformApp;
import com.microsoft.skype.teams.mobilemodules.MobileModuleSyncManager;
import com.microsoft.skype.teams.mobilemodules.PlatformAppManager;
import com.microsoft.skype.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.skype.teams.sdk.SdkApplicationContext;
import com.microsoft.skype.teams.sdk.rnbundle.ISdkBundleDownloadManager;
import com.microsoft.skype.teams.sdk.rnbundle.ISdkBundleManager;
import com.microsoft.skype.teams.services.configuration.AppConfiguration;
import com.microsoft.skype.teams.storage.IExperimentationManager;
import com.microsoft.skype.teams.storage.dao.appdefinition.AppDefinitionDao;
import com.microsoft.skype.teams.storage.dao.rnapps.RNAppsDao;
import com.microsoft.skype.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.teams.androidutils.tasks.ITaskRunner;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.microsoft.teams.core.services.IScenarioManager;
import com.teamssdksim.modules.AppComponent;

import com.teamssdksim.modules.ContextModule;
import com.teamssdksim.modules.DaggerAppComponent;
import com.teamssdksim.modules.TaskRunnerModule;
import com.teamssdksim.modules.TeamsApplicationModule;

import javax.inject.Inject;

public class TeamSDKActivity extends AppCompatActivity {

    @Inject
    IExperimentationManager mExperimentationManager;

    @Inject
    ITelemetryLogger mTelemetryLogger;

    @Inject
    ILogger mLogger;

    @Inject
    AppConfiguration mAppConfiguration;

    @Inject
    Preferences mPreferences;

    @Inject
    IScenarioManager mScenarioManager;

    @Inject
    ITeamsApplication mTeamsApplication;

    @Inject
    ITaskRunner mTaskRunner;

    @Inject
    ISdkRunnerAppManager mSdkRunnerAppManager;

    @Inject
    AppDefinitionDao mAppDefinitionDao;

    @Inject
    RNAppsDao mRNAppsDao;

    @Inject
    RNBundlesDao mRNBundlesDao;

    @Inject
    ISdkBundleManager mSdkBundleManager;

    @Inject
    ISdkBundleDownloadManager mSdkBundleDownloadManager;

    @Inject
    PlatformAppManager mPlatformAppManager;

    @Inject
    MobileModuleSyncManager mMobileModuleSyncManager;

    private AppComponent mAppComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_s_d_k);
        ViewGroup host = (ViewGroup)findViewById(R.id.reactHost);

        mAppComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .taskRunnerModule(new TaskRunnerModule(new Handler(Looper.getMainLooper())))
                .teamsApplicationModule(new TeamsApplicationModule(this.getApplication(), this))
                .build();

        mAppComponent.inject(this);

        mSdkRunnerAppManager.syncRunnerApp();
        mMobileModuleSyncManager.syncMobileModules();

        Button startButton = new Button(this);
        startButton.setText("Start");;
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IPlatformApp platformApp = mPlatformAppManager.get("0b04a6cf-e696-4eb0-bac2-aaf883c9add5");

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