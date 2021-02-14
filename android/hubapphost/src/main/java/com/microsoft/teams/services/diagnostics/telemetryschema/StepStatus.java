/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.services.diagnostics.telemetryschema;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Enumeration for Step status
 */
@Retention(RetentionPolicy.SOURCE)
@StringDef({StepStatus.OK, StepStatus.ERROR, StepStatus.INCOMPLETE, StepStatus.CANCEL})
public @interface StepStatus {
    String OK = "OK";
    String ERROR = "ERROR";
    String INCOMPLETE = "INCOMPLETE";
    String CANCEL = "ABANDONED";
}
