package com.microsoft.teams.appcenter.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.microsoft.appcenter.crashes.ingestion.models.Exception;
import com.microsoft.appcenter.crashes.utils.ErrorLogHelper;
import com.microsoft.teams.core.utilities.DateUtilities;

import java.util.Date;
import java.util.UUID;

import static com.microsoft.teams.core.utilities.DateUtilities.UTC_DATE_FORMAT;

/**
 * Model used to upoad custom App Center Log
 */
public class AppCenterLog {

    private final String type = "managedError";
    private String timestamp;
    private String id;
    private boolean fatal;
    @SerializedName("device")
    private TeamsDevice teamsDevice;
    private Exception exception;
    // required fields so adding place holders
    private final int processId = 999;
    private final String processName = "com.microsoft.teams.flavor";
    private String appLaunchTimestamp;

    AppCenterLog(boolean fatal, @NonNull TeamsDevice teamsDevice, @NonNull Throwable throwable) {
        this.fatal = fatal;
        this.teamsDevice = teamsDevice;
        this.id = UUID.randomUUID().toString();
        this.exception = ErrorLogHelper.getModelExceptionFromThrowable(throwable);
        try {
            this.timestamp = DateUtilities.getFormattedDate(new Date(System.currentTimeMillis()), UTC_DATE_FORMAT);
            // required field so adding same time stamp
            this.appLaunchTimestamp = timestamp;
        } catch (java.lang.Exception e) {
            // do nothing
        }
    }
}
