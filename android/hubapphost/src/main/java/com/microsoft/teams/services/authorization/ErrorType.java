/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.services.authorization;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

import androidx.annotation.StringDef;

/**
 * ErrorType
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@StringDef({
        ErrorType.USER_ERROR,
        ErrorType.SYSTEM_ERROR,
        ErrorType.CODE_ERROR
})
public @interface ErrorType {
    String USER_ERROR = "USER_ERROR";
    String SYSTEM_ERROR = "SYSTEM_ERROR";
    String CODE_ERROR = "CODE_ERROR";
}
