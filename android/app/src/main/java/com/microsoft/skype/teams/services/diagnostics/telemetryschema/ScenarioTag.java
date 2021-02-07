package com.microsoft.skype.teams.services.diagnostics.telemetryschema;


import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({ScenarioTag.SYNC_ALL_RN_APPS, ScenarioTag.RN_HOST_FRAGMENT})
@Retention(RetentionPolicy.SOURCE)
public @interface ScenarioTag {
    String SYNC_ALL_RN_APPS = "sync_all_rn_apps";
    String RN_HOST_FRAGMENT = "rn_host_fragment";
}

