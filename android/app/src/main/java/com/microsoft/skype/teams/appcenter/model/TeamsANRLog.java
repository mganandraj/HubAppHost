package com.microsoft.skype.teams.appcenter.model;

import androidx.annotation.NonNull;

import com.github.anrwatchdog.ANRError;
import com.microsoft.appcenter.ingestion.models.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * Model for Teams Application Not Responding (ANR) Log
 */
public class TeamsANRLog {
    private List<AppCenterLog> logs;

    public TeamsANRLog(@NonNull ANRError anrError, @NonNull Device device) {
        logs = new ArrayList<>();
        AppCenterLog appCenterLog = new AppCenterLog(true, new TeamsDevice(device), anrError.getCause());
        logs.add(appCenterLog);
    }
}
