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
import com.google.gson.JsonObject;
import com.microsoft.skype.teams.data.transforms.CoreParserHelper;
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
import com.microsoft.skype.teams.storage.database.SdkDatabase;
import com.microsoft.skype.teams.storage.tables.AppDefinition;
import com.microsoft.skype.teams.utilities.java.JsonUtils;
import com.microsoft.teams.androidutils.tasks.ITaskRunner;
import com.microsoft.teams.androidutils.tasks.TaskUtilities;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.microsoft.teams.core.services.IScenarioManager;
import com.teamssdksim.modules.AppComponent;

import com.teamssdksim.modules.ContextModule;
import com.teamssdksim.modules.DaggerAppComponent;
import com.teamssdksim.modules.TaskRunnerModule;
import com.teamssdksim.modules.TeamsApplicationModule;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import bolts.Continuation;
import bolts.Task;

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

//    @Inject
//    AppDefinitionDao mAppDefinitionDao;

//    @Inject
//    RNAppsDao mRNAppsDao;
//
//    @Inject
//    RNBundlesDao mRNBundlesDao;

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

        SdkDatabase.initialize(this.getApplicationContext());

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

                TaskUtilities.runOnBackgroundThread(new Callable<SdkApplicationContext>() {
                    @Override
                    public SdkApplicationContext call() throws Exception {
                        IPlatformApp platformApp = mPlatformAppManager.get("0b04a6cf-e696-4eb0-bac2-aaf883c9add5");
                        IMobileModule mobileModule = platformApp.getMobileModule();
                        SdkApplicationContext sdkApplicationContext = mobileModule.getSdkApplicationContext();
                        return sdkApplicationContext;
                    }
                }).onSuccess(new Continuation<SdkApplicationContext, Void>() {
                    @Override
                    public Void then(Task<SdkApplicationContext> task) throws Exception {
                        SdkApplicationContext sdkApplicationContext = task.getResult();
                        if(sdkApplicationContext != null) {

                            ReactInstanceManager instanceManager = sdkApplicationContext.getReactInstanceManager(TeamSDKActivity.this);

                            ReactRootView reactRootView = new ReactRootView(TeamSDKActivity.this);

                            Bundle reactAppParams = new Bundle();
                            reactAppParams.putBundle("appParams", sdkApplicationContext.getAppInitializationParamsAsBundle(""));
                            reactAppParams.putBundle("_hostParams", sdkApplicationContext.getHostParamsAsBundle("TestSdkSimInstanceId"));
                            reactAppParams.putString("_params", "");

                            reactRootView.startReactApplication(instanceManager, "HomeComponent", reactAppParams);

                            host.addView(reactRootView);
                        }

                        return null;
                    }
                }, Task.UI_THREAD_EXECUTOR);
            }
        });

        Button seedButton = new Button(this);
        seedButton.setText("Seed");;
        seedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppDefinitionDao appDefinitionDao = SdkDatabase.get().appDefinitionDao();

                TaskUtilities.runOnBackgroundThread(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {

                        ArrayList<AppDefinition> appDefinitions = new ArrayList<>();
                        String appDefinitionString = "{\n" +
                                "    \"manifestVersion\": \"1.0\",\n" +
                                "    \"version\": \"0.1.16\",\n" +
                                "    \"categories\": [\n" +
                                "        \"Microsoft\",\n" +
                                "        \"Productivity\"\n" +
                                "    ],\n" +
                                "    \"developerName\": \"Microsoft Corp.\",\n" +
                                "    \"developerUrl\": \"http://www.microsoft.com/\",\n" +
                                "    \"privacyUrl\": \"https://privacy.microsoft.com/en-us/privacystatement\",\n" +
                                "    \"termsOfUseUrl\": \"https://www.microsoft.com/en-us/servicesagreement\",\n" +
                                "    \"validDomains\": [],\n" +
                                "    \"permissions\": [],\n" +
                                "    \"mobileModules\": [\n" +
                                "        {\n" +
                                "            \"type\": \"reactNative\",\n" +
                                "            \"rnPackageUrl\": \"codepush://anandrag-6x40/TeamsHelloWorld/?deploymentKey=51sn_RABfAd_st3RxXoeXi9csqW-m9s0pQ4Hm\"\n" +
                                "        }\n" +
                                "    ],\n" +
                                "    \"id\": \"0b04a6cf-e696-4eb0-bac2-aaf883c9add5\",\n" +
                                "    \"name\": \"React Native Hello World\",\n" +
                                "    \"shortDescription\": \"Showcases the Teams Mobile React Native SDK.\",\n" +
                                "    \"longDescription\": \"Showcases the Teams Mobile React Native SDK.\",\n" +
                                "    \"smallImageUrl\": \"https://statics.teams.cdn.office.net/evergreen-assets/apps/1ded03cb-ece5-4e7c-9f73-61c375528078_smallImage.png?v=0.0.15\",\n" +
                                "    \"largeImageUrl\": \"https://statics.teams.cdn.office.net/evergreen-assets/apps/1ded03cb-ece5-4e7c-9f73-61c375528078_largeImage.png?v=0.0.15\",\n" +
                                "    \"accentColor\": \"#7719aa\"\n" +
                                "}";

                        JsonObject jsonObject = JsonUtils.parseObject(appDefinitionString, JsonObject.class, null);
                        AppDefinition appDefinition = new AppDefinition();
                        CoreParserHelper.parseAppDefinition(jsonObject, appDefinition, null, mTeamsApplication.getLogger(""));
                        if (appDefinition != null) {
                            try {
                                appDefinitionDao.save(appDefinition);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        return null;
                    }}).onSuccess(new Continuation<Void, Void>() {
                    @Override
                    public Void then(Task<Void> task) throws Exception {
                        // Trigger a synchonization
                        mMobileModuleSyncManager.syncMobileModules();
                        return null;
                    }
                });

            }
        });

        Button syncButton = new Button(this);
        syncButton.setText("Sync");;
        syncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMobileModuleSyncManager.syncMobileModules();
            }
        });

        host.addView(startButton);
        host.addView(seedButton);
        host.addView(syncButton);

    }
}