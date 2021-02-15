package com.microsoft.hubapphost.sdk;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.JsonObject;
import com.microsoft.hubapphost.impls.PreferencesImpl;
import com.microsoft.hubapphost.modules.AppComponent;
import com.microsoft.hubapphost.modules.ContextModule;
import com.microsoft.hubapphost.modules.DaggerAppComponent;
import com.microsoft.hubapphost.modules.TaskRunnerModule;
import com.microsoft.hubapphost.modules.TeamsApplicationModule;
import com.microsoft.teams.data.transforms.CoreParserHelper;
import com.microsoft.teams.logger.ILogger;
import com.microsoft.teams.logger.ITelemetryLogger;
import com.microsoft.teams.mobilemodules.IPlatformApp;
import com.microsoft.teams.mobilemodules.MobileModuleSyncManager;
import com.microsoft.teams.mobilemodules.PlatformAppManager;
import com.microsoft.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.teams.sdk.rnbundle.ISdkBundleDownloadManager;
import com.microsoft.teams.sdk.rnbundle.ISdkBundleManager;
import com.microsoft.teams.services.configuration.AppConfiguration;
import com.microsoft.teams.storage.IExperimentationManager;
import com.microsoft.teams.storage.dao.appdefinition.AppDefinitionDao;
import com.microsoft.teams.storage.database.SdkDatabase;
import com.microsoft.teams.storage.tables.AppDefinition;
import com.microsoft.teams.utilities.java.JsonUtils;
import com.microsoft.teams.androidutils.tasks.ITaskRunner;
import com.microsoft.teams.androidutils.tasks.TaskUtilities;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.microsoft.teams.core.services.IScenarioManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import bolts.Continuation;
import bolts.Task;

public class PlatformAppProvider {

    @Inject
    IExperimentationManager mExperimentationManager;

    @Inject
    ITelemetryLogger mTelemetryLogger;

    @Inject
    ILogger mLogger;

    @Inject
    AppConfiguration mAppConfiguration;

    @Inject
    PreferencesImpl mPreferencesImpl;

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

    private static PlatformAppProvider sPlatformAppProvider = null;

    public interface IPlatformAppsObserver {
        // TODO call it mobilemodulessyscompleted ?
        void onPlatformAppsChanged(List<IPlatformApp> platformApps);
    }

    ArrayList<IPlatformAppsObserver> mPlatformAppsObservers = new ArrayList<>();

    public void attachPlatformAppsObserver(IPlatformAppsObserver platformAppsObserver) {
        mPlatformAppsObservers.add(platformAppsObserver);
    }

    public void seedMobileModules() {

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
                        mAppDefinitionDao.add(appDefinition);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                return null;
            }
        }).onSuccess(new Continuation<Void, Void>() {
            @Override
            public Void then(Task<Void> task) throws Exception {
                // Trigger a synchonization
                mMobileModuleSyncManager.syncMobileModules();
                return null;
            }
        });

    }

    public void syncMobileModules() {
        mSdkRunnerAppManager.syncRunnerApp();

        TaskUtilities.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                mMobileModuleSyncManager.syncMobileModules()
                        .onSuccess(new Continuation<Void, Object>() {

                            @Override
                            public Object then(Task<Void> task) throws Exception {

                                ArrayList<IPlatformApp> platformApps = new ArrayList<>();
                                for (AppDefinition appDefinition : mAppDefinitionDao.getAppDefinitionsWithMobileModules()) {
                                    IPlatformApp platformApp = mPlatformAppManager.get(appDefinition.appId);
                                    platformApps.add(platformApp);
                                }

                                for (IPlatformAppsObserver platformAppsObserver : mPlatformAppsObservers) {
                                    platformAppsObserver.onPlatformAppsChanged(platformApps);
                                }
                                return null;
                            }

                        });
            }
        });
    }

    // TODO .. THis shouldn't be bound to an activity.
    private PlatformAppProvider(Application application, Activity activity) {
        SdkDatabase.initialize(activity);

        mAppComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(activity))
                .taskRunnerModule(new TaskRunnerModule(new Handler(Looper.getMainLooper())))
                .teamsApplicationModule(new TeamsApplicationModule(application, activity))
                .build();

        mAppComponent.inject(this);

        syncMobileModules();

    }

    public static PlatformAppProvider initialize(Application application, Activity activity) {
        if (sPlatformAppProvider == null) {
            synchronized (PlatformAppProvider.class) {
                if (sPlatformAppProvider == null) {
                    sPlatformAppProvider = new PlatformAppProvider(application, activity);
                }
            }
        }

        return sPlatformAppProvider;
    }

    public static PlatformAppProvider get() {
        if (sPlatformAppProvider == null) {
            throw new RuntimeException("PlatformAppProvider::initialize must be called prior to calling get.");
        }

        return sPlatformAppProvider;
    }


}
