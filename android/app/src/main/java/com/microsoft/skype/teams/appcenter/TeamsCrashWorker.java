package com.microsoft.skype.teams.appcenter;

import android.content.Context;
import androidx.annotation.NonNull;

//import com.microsoft.skype.teams.R;
//import com.microsoft.skype.teams.app.SkypeTeamsApplication;
//import com.microsoft.skype.teams.appcenter.model.TeamsANRLog;
//import com.microsoft.skype.teams.data.backendservices.AppCenterServiceInterface;
//import com.microsoft.skype.teams.data.proxy.AppCenterServiceProvider;
//import com.microsoft.skype.teams.data.servicetype.ApiName;
import com.microsoft.skype.teams.logger.ILogger;
import com.microsoft.skype.teams.logger.LogPriority;
//import com.microsoft.skype.teams.services.diagnostics.telemetryschema.ServiceType;
//import com.microsoft.teams.networkutils.IHttpResponseCallback;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.microsoft.teams.core.app.TeamsApplicationUtilities;
//import com.microsoft.teams.core.preferences.IPreferences;

import java.util.UUID;

import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
//import retrofit2.Response;

/**
 * A worker to upload Teams Crash / ANR logs
 */
public class TeamsCrashWorker extends Worker {
    static final String TEAMS_ANR_LOG = "TeamsANRLog";
    private Context mContext;
    private final ITeamsApplication mTeamsApplication;
    // private final IPreferences mPreferences;

    public TeamsCrashWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.mContext = context;
        mTeamsApplication = TeamsApplicationUtilities.getTeamsApplication(context);
        // mPreferences = mTeamsApplication.getAppDataFactory().create(IPreferences.class);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            final ILogger logger = mTeamsApplication.getLogger(null);

            // get input data
            Data data = getInputData();
            String logId = data.getString(TEAMS_ANR_LOG);
            if (logId == null) {
                logger.log(LogPriority.ERROR, TEAMS_ANR_LOG, "Failed uploading ANR log : logId is null in Worker");
                return Result.failure();
            }

//            TeamsANRLog log = TeamsCrashHelper.getANRLog(logId, mPreferences);
//            TeamsCrashHelper.removeANRLog(logId, mPreferences);
//            if (log != null) {
//                // due to system constructor no easy way to inject hence using direct reference
//                SkypeTeamsApplication
//                        .getApplicationComponent().httpCallExecutor()
//                        .execute(ServiceType.APPCENTER, ApiName.APP_CENTER,
//                                 () -> {
//                                     AppCenterServiceInterface appCenterServiceInterface = AppCenterServiceProvider.getAppCenterService();
//                                     return appCenterServiceInterface.uploadCrash("application/json",
//                                                                                  mContext.getString(R.string.appcenter_secret),
//                                                                                  UUID.randomUUID().toString(), log, AppCenterServiceProvider.VERSION);
//                                 }, new IHttpResponseCallback<Void>() {
//                                    @Override
//                                    public void onResponse(Response<Void> response, String errorMessage) {
//                                        if (response != null && !response.isSuccessful()) {
//                                            mTeamsApplication.getLogger(null).log(LogPriority.ERROR, TEAMS_ANR_LOG, "Error uploading ANR log : %s", errorMessage);
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onFailure(Throwable failure) {
//                                        mTeamsApplication.getLogger(null).log(LogPriority.ERROR, TEAMS_ANR_LOG, "Failed uploading ANR log : %s", failure);
//                                    }
//                                }, null);
//            } else {
//                logger.log(LogPriority.ERROR, TEAMS_ANR_LOG, "Failed uploading ANR log : log is null");
//            }
            return Result.success();
        } catch (Exception ex) {
            return Result.failure();
        }
    }
}
