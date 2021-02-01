/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.core.models;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Defines supported content types shared to Teams.
 */
@StringDef({ShareContentType.TEXT, ShareContentType.IMAGE, ShareContentType.IMAGES, ShareContentType.TEAMS_MESSAGE, ShareContentType.TEAMS_ESCALATABLE_MESSAGE})
@Retention(RetentionPolicy.SOURCE)
public @interface ShareContentType {
    String TEXT = "text";
    String IMAGE = "image";
    String IMAGES = "images";
    String TEAMS_MESSAGE = "teamsMessage";
    String TEAMS_ESCALATABLE_MESSAGE = "teamsEscalatableMessage";
}
