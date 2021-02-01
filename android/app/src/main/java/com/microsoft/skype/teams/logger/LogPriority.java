/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.logger;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Log priorities, the values match the ones used by android Log class methods
 */
@IntDef(value = {LogPriority.VERBOSE, LogPriority.DEBUG, LogPriority.ADAL_INFO, LogPriority.INFO, LogPriority.WARNING, LogPriority.ERROR, LogPriority.ASSERT})
@Retention(RetentionPolicy.SOURCE)
public @interface LogPriority {
    int ALL = -1;
    int VERBOSE = 2;
    int DEBUG = 3;
    int ADAL_INFO = 4;
    int INFO = 5;
    int WARNING = 6;
    int ERROR = 7;
    int ASSERT = 8;
}