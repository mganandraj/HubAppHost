package com.microsoft.teams.sdk;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.teams.data.transforms.CoreParserHelper;
import com.microsoft.teams.logger.ILogger;
import com.microsoft.teams.logger.LogPriority;
import com.microsoft.teams.sdk.ISdkRunnerAppManager;
import com.microsoft.teams.sdk.models.SdkAppManifest;
import com.microsoft.teams.sdk.utils.SdkRunnerUtils;
import com.microsoft.teams.sdk.utils.SdkVersionUtils;
import com.microsoft.teams.services.configuration.AppConfiguration;
import com.microsoft.teams.storage.tables.AppDefinition;
import com.microsoft.teams.storage.tables.RNBundle;
//import com.microsoft.teams.tabs.AppTab;
//import com.microsoft.teams.tabs.ITabProvider;
import com.microsoft.teams.utilities.IOUtilities;
import com.microsoft.teams.utilities.java.JsonUtils;
import com.microsoft.teams.androidutils.tasks.ITaskRunner;
import com.microsoft.teams.core.app.ITeamsApplication;
// import com.microsoft.teams.injection.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/*
import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
*/
import bolts.TaskCompletionSource;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.microsoft.teams.sdk.SdkHelper.throwError;
import static com.microsoft.teams.sdk.rnbundle.SdkBundleUtils.getCacheAppDirectory;

/**
 * Manages Runner App mode
 */
@Singleton
public class SdkRunnerAppManager implements ISdkRunnerAppManager {
    private static final String LOG_TAG = "SdkRunnerAppManager";
    private final ITaskRunner mTaskRunner;
    private final Context mContext;
    private final Gson mGson;
    private final AppConfiguration mAppConfiguration;
    private final ITeamsApplication mTeamsApplication;
    private SdkAppManifest mSdkAppManifest;
    private AppDefinition mAppDefinition;

    /**
     * OkHttp client to talk to the runner server
     */
    private final OkHttpClient mRunnerHttpClient = new OkHttpClient();

    /**
     * Provides a new instance for injection.
     */
    @Inject
    public SdkRunnerAppManager(@NonNull Context context,
                               @NonNull Gson gson,
                               @NonNull ITaskRunner taskRunner,
                               @NonNull AppConfiguration appConfiguration,
                               @NonNull ITeamsApplication teamsApplication) {
        mContext = context;
        mGson = gson;
        mTaskRunner = taskRunner;
        mAppConfiguration = appConfiguration;
        mTeamsApplication = teamsApplication;
    }

    @NonNull
    @Override
    public AppDefinition getAppDefinition() {
        return mAppDefinition;
    }

    @Override
    public RNBundle getRNBundle() {
        RNBundle rnBundle = new RNBundle();
        rnBundle.appId = mAppDefinition.appId;
        rnBundle.manifest = mGson.toJson(mSdkAppManifest, SdkAppManifest.class);
        rnBundle.bundleLocation = getCacheAppDirectory(mContext, mAppDefinition.appId).getAbsolutePath();
        return rnBundle;
    }

    @NonNull
    @Override
    public /*Task<Void>*/ void syncRunnerApp(/*@NonNull ITabProvider tabProvider*/) {
        final ILogger logger = mTeamsApplication.getLogger(null);
        if (!SdkRunnerUtils.isRunnerMode()) {
            logger.log(LogPriority.DEBUG, LOG_TAG, "Not in runner mode.");
            // return Task.forResult(null);
        }

        logger.log(LogPriority.DEBUG, LOG_TAG, "In runner mode.");

        final TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
        mTaskRunner.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {

                // Download the app manifest and resources from runner server.
                try {
                    mSdkAppManifest = downloadAppManifestFromRunnerServer();
                    mAppDefinition = downloadAppDefinitionFromRunnerServer();
                    downloadAppResourcesFromRunnerServer(mAppDefinition);
                } catch (Exception e) {
                    logger.log(LogPriority.ERROR, LOG_TAG, e, "Failed to download app from runner server.");
                    taskCompletionSource.trySetError(e);
                    return;
                }

//                saveRunnerAppTab(mAppDefinition, tabProvider).continueWith(new Continuation<Void, Void>() {
//                    @Override
//                    public Void then(Task<Void> task) {
//                        if (task.isFaulted()) {
//                            taskCompletionSource.trySetError(task.getError());
//                        } else if (task.isCancelled()) {
//                            taskCompletionSource.trySetCancelled();
//                        } else {
//                            taskCompletionSource.trySetResult(null);
//                        }
//
//                        return null;
//                    }
//                });
            }
        });

        // return taskCompletionSource.getTask();
    }
//
//    @NonNull
//    @Override
//    public void syncRunnerApp() {
//        final ILogger logger = mTeamsApplication.getLogger(null);
//
//        Executors.newSingleThreadExecutor().execute(new Runnable() {
//            @Override
//            public void run() {
//                // Download the app manifest and resources from runner server.*/
//                try {
//                    mSdkAppManifest = downloadAppManifestFromRunnerServer();
//                    mAppDefinition = downloadAppDefinitionFromRunnerServer();
//                    downloadAppResourcesFromRunnerServer(mAppDefinition);
//                } catch (Exception e) {
//                    logger.log(LogPriority.ERROR, LOG_TAG, e, "Failed to download app from runner server.");
//                    return;
//                }
//            }
//        });
//    }

    @Nullable
    private SdkAppManifest downloadAppManifestFromRunnerServer() throws Exception {
        SdkAppManifest sdkAppManifest = null;
        Request request = new Request.Builder()
                .url(SdkRunnerServerHelper.getAppManifestUrl())
                .build();

        final ILogger logger = mTeamsApplication.getLogger(null);
        logger.log(LogPriority.VERBOSE, LOG_TAG, "Downloading app manifest from runner server: %s", request.url().toString());
        Response response = mRunnerHttpClient.newCall(request).execute();
        if (response.code() != 200) {
            throwError("Didn't receive ok from the runner server. Response code: %d", response.code());
        } else if (response.body() == null) {
            throwError("Nothing received from the runner server.");
        } else {
            ResponseBody responseBody = response.body();
            String responseString = responseBody != null ? responseBody.string() : null;
            JsonObject jsonObject = JsonUtils.parseObject(responseString, JsonObject.class, null);
            sdkAppManifest = SdkAppManifestParserProvider.getAppManifestParser(SdkVersionUtils.getMaxSupportedBinaryVersion(),
                                                                               /*mAppConfiguration.isExperimentalRnSdkAllowed()*/false).parseManifest(jsonObject);
            if (sdkAppManifest == null) {
                throwError("Failed to parse app manifest. Received manifest: %s.", responseString);
            }
        }

        return sdkAppManifest;
    }

    @Nullable
    private AppDefinition downloadAppDefinitionFromRunnerServer() throws Exception {
        AppDefinition appDefinition = null;
        Request request = new Request.Builder()
            .url(SdkRunnerServerHelper.getAppDefinitionUrl())
            .build();

        final ILogger logger = mTeamsApplication.getLogger(null);
        logger.log(LogPriority.VERBOSE, LOG_TAG, "Downloading app definition from runner server: %s", request.url().toString());
        Response response = mRunnerHttpClient.newCall(request).execute();
        if (response.code() != 200) {
            throwError("Didn't receive ok from the runner server. Response code: %d", response.code());
        } else if (response.body() == null) {
            throwError("Nothing received from the runner server.");
        } else {
            ResponseBody responseBody = response.body();
            String responseString = responseBody != null ? responseBody.string() : null;
            JsonObject jsonObject = JsonUtils.parseObject(responseString, JsonObject.class, null);
            appDefinition = new AppDefinition();
            CoreParserHelper.parseAppDefinition(jsonObject, appDefinition, null, logger);
            if (appDefinition == null) {
                throwError("Failed to parse app definition. Received definition: %s.", responseString);
            }
        }

        appDefinition.appId = SdkRunnerUtils.transformToRunnerAppId(appDefinition.appId);
        appDefinition.name = SdkRunnerUtils.transformToRunnerAppName(appDefinition.name);

        return appDefinition;
    }

    private void downloadAppResourcesFromRunnerServer(@Nullable AppDefinition appDefinition) throws Exception {
        if (appDefinition == null) {
            return;
        }

        // Download the resources and unzip it
        Request request = new Request.Builder()
                .url(SdkRunnerServerHelper.getAppResourcesDownloadUrl())
                .build();

        final ILogger logger = mTeamsApplication.getLogger(null);
        logger.log(LogPriority.VERBOSE, LOG_TAG, "Downloading app resources from runner server: %s", request.url().toString());
        Response response = mRunnerHttpClient.newCall(request).execute();
        if (response.code() != 200) {
            throwError("Didn't receive ok from the runner server. Response code: %d", response.code());
        } else if (response.body() == null) {
            throwError("Nothing received from the runner server.");
        } else {
            IOUtilities.unzipFile(response.body().byteStream(), getCacheAppDirectory(mContext, appDefinition.appId));
        }
    }

    /*@WorkerThread
    private Task<Void> saveRunnerAppTab(@NonNull final AppDefinition appDefinition, @NonNull final ITabProvider tabProvider) {

        AppTab appTab = new AppTab();
        appTab.id = appDefinition.appId;
        appTab.name = appDefinition.name;
        appTab.position = AppTab.INACTIVE_STARTING_POSITION;
        appTab.enable = true;
        appTab.appDefinition = appDefinition;

        return Task.call(() -> {
            tabProvider.updateSdkRunnerTabs(appTab);
            return null;
        });
    }*/
}
