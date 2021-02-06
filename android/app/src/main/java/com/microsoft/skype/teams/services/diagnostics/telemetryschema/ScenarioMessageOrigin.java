/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.services.diagnostics.telemetryschema;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Enumeration for Message Origin name
 */
@Retention(RetentionPolicy.SOURCE)
@StringDef({ScenarioMessageOrigin.LONGPOLL, ScenarioMessageOrigin.PUSH, ScenarioMessageOrigin.SYNC})
public @interface ScenarioMessageOrigin {
    String LONGPOLL = "LONGPOLL";
    String PUSH = "PUSH";
    String SYNC = "SYNC";
}

