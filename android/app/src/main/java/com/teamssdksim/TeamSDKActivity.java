package com.teamssdksim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.google.gson.GsonBuilder;
import com.microsoft.skype.teams.mobilemodules.IMobileModule;
import com.microsoft.skype.teams.mobilemodules.IPlatformApp;
import com.microsoft.skype.teams.mobilemodules.PlatformApp;
import com.microsoft.skype.teams.mobilemodules.PlatformAppManager;
import com.microsoft.skype.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.skype.teams.sdk.SdkApplicationContext;
import com.microsoft.skype.teams.sdk.SdkRunnerAppManager;
import com.microsoft.skype.teams.storage.dao.appdefinition.AppDefinitionDao;
import com.microsoft.skype.teams.storage.models.MobileModuleDefinition;
import com.microsoft.skype.teams.storage.tables.AppDefinition;

public class TeamSDKActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_s_d_k);

        ViewGroup host = (ViewGroup)findViewById(R.id.reactHost);

        TeamsSdkSimApplication fakeApplication = new TeamsSdkSimApplication(this, this.getApplication());

        SdkRunnerAppManager sdkRunnerAppManager = new SdkRunnerAppManager(this, new GsonBuilder().create(), fakeApplication);
        sdkRunnerAppManager.syncRunnerApp();

        TeamsSdkSimAppDefinitionDao appDefinitionDao = new TeamsSdkSimAppDefinitionDao();

        TeamsSdkRNAppsDao teamsSdkRNAppsDao = new TeamsSdkRNAppsDao();
        TeamsSdkSimRNBundlesDao teamsSdkSimRNBundlesDao = new TeamsSdkSimRNBundlesDao();

        TeamsSdkSimMobileModuleFactory teamsSdkSimMobileModuleFactory = new TeamsSdkSimMobileModuleFactory();
        TeamsSdkSimPlatformAppFactory teamsSdkSimPlatformAppFactory = new TeamsSdkSimPlatformAppFactory(appDefinitionDao,
                teamsSdkSimMobileModuleFactory,
                sdkRunnerAppManager);

        PlatformAppManager platformAppManager = new PlatformAppManager(teamsSdkSimPlatformAppFactory,
                sdkRunnerAppManager,
                appDefinitionDao);

        Button startButton = new Button(this);
        startButton.setText("Start");;
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDefinition appDefinition= sdkRunnerAppManager.getAppDefinition();
                MobileModuleDefinition mobileModuleDefinition = appDefinition.getMobileModuleDefinition();

                // PlatformApp platformApp = new PlatformApp("TeamsSdkSimAppId", mobileModuleDefinition, appDefinitionDao, sdkRunnerAppManager);
                IPlatformApp platformApp = platformAppManager.get("TeamsSdkSimAppId");
                IMobileModule mobileModule = platformApp.getMobileModule(TeamSDKActivity.this, sdkRunnerAppManager, fakeApplication, teamsSdkRNAppsDao, teamsSdkSimRNBundlesDao);
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