package com.microsoft.skype.teams.appcenter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;

import com.github.anrwatchdog.ANRError;
import com.google.gson.reflect.TypeToken;
import com.microsoft.appcenter.ingestion.models.Device;
import com.microsoft.appcenter.utils.DeviceInfoHelper;
import com.microsoft.skype.teams.appcenter.model.TeamsANRLog;
import com.microsoft.skype.teams.logger.ILogger;
import com.microsoft.skype.teams.logger.LogPriority;
// import com.microsoft.skype.teams.services.workmanager.TeamsWorkManager;
import com.microsoft.skype.teams.utilities.java.JsonUtils;
import com.microsoft.skype.teams.utilities.java.StringUtils;
import com.microsoft.teams.core.models.GlobalPreferences;
import com.microsoft.teams.core.preferences.IPreferences;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.microsoft.skype.teams.appcenter.TeamsCrashWorker.TEAMS_ANR_LOG;

/**
 * Helper class for Teams Crashes / ANRs
 */
public final class TeamsCrashHelper {

    private static final String TAG = TeamsCrashHelper.class.getSimpleName();

    protected TeamsCrashHelper() {
    }

    public static void scheduleWorker(@NonNull Context context,
                                      @NonNull String userObjectId,
                                      @NonNull final ANRError anrError,
                                      @Nullable ILogger logger,
                                      @NonNull IPreferences preferences) {
        try {
            // build the input data
            Device device = DeviceInfoHelper.getDeviceInfo(context);
            TeamsANRLog teamsANRLog = new TeamsANRLog(anrError, device);
            Data.Builder dataBuilder = new Data.Builder();
            String logId = UUID.randomUUID().toString();
            dataBuilder.putString(TEAMS_ANR_LOG, logId);
            Data data = dataBuilder.build();

            // save ANR in Pref
            addANRLog(logId, teamsANRLog, preferences);

            // schedule the work request
            Constraints myConstraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();

            OneTimeWorkRequest.Builder uploadWorkRequestBuilder = new OneTimeWorkRequest
                    .Builder(TeamsCrashWorker.class)
                    .setInputData(data).setConstraints(myConstraints);

//            TeamsWorkManager.enqueue(context, TeamsWorkManager.WorkerTag.CRASH_MANGER, uploadWorkRequestBuilder, userObjectId);
        } catch (DeviceInfoHelper.DeviceInfoException e) {
            if (logger != null) {
                logger.log(LogPriority.INFO, TAG, "Error getting device info from DeviceInfoHelper");
            }
        }
    }

    @NonNull
    private static Map<String, TeamsANRLog> getANRLogs(@NonNull IPreferences preferences) {
        HashMap<String, TeamsANRLog> result = new HashMap<>();
        String anrList = preferences.getStringGlobalPref(GlobalPreferences.TEAMS_ANR_LIST, null);
        if (StringUtils.isNullOrEmptyOrWhitespace(anrList)) {
            return result;
        }

        Type anrListType = new TypeToken<HashMap<String, TeamsANRLog>>() { }.getType();
        result = JsonUtils.GSON.fromJson(anrList, anrListType);
        return result;
    }

    static void addANRLog(@NonNull String id,
                          @NonNull TeamsANRLog anrLog,
                          @NonNull IPreferences preferences) {
        Map<String, TeamsANRLog> anrLogListMap = getANRLogs(preferences);
        anrLogListMap.put(id, anrLog);
        String anrLogListMapString = JsonUtils.GSON.toJson(anrLogListMap);
        preferences.putStringGlobalPref(GlobalPreferences.TEAMS_ANR_LIST, anrLogListMapString);
    }

    @Nullable
    static TeamsANRLog getANRLog(@NonNull String id,
                                 @NonNull IPreferences preferences) {
        Map<String, TeamsANRLog> anrLogListMap = getANRLogs(preferences);
        return anrLogListMap.get(id);
    }

    static void removeANRLog(@NonNull String logId,
                             @NonNull IPreferences preferences) {
        Map<String, TeamsANRLog> anrLogListMap = getANRLogs(preferences);
        anrLogListMap.remove(logId);
        String anrLogListMapString = JsonUtils.GSON.toJson(anrLogListMap);
        preferences.putStringGlobalPref(GlobalPreferences.TEAMS_ANR_LIST, anrLogListMapString);
    }
}
