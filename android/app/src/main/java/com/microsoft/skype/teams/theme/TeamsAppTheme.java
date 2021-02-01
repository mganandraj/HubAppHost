/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.theme;

import androidx.annotation.IntDef;

/** Enum used to indicate the current theme/skin/style of this app. */
@IntDef(value = {com.microsoft.skype.teams.theme.TeamsAppTheme.DEFAULT, com.microsoft.skype.teams.theme.TeamsAppTheme.DARK, com.microsoft.skype.teams.theme.TeamsAppTheme.PLAYGROUND})
public @interface TeamsAppTheme {
    int DEFAULT = 0;
    int DARK = 1;
    int PLAYGROUND = 2;
}