/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.theme;

import androidx.annotation.IntDef;

/** Enum used to indicate the current theme/skin/style of this app. */
@IntDef(value = {com.microsoft.teams.theme.TeamsAppTheme.DEFAULT, com.microsoft.teams.theme.TeamsAppTheme.DARK, com.microsoft.teams.theme.TeamsAppTheme.PLAYGROUND})
public @interface TeamsAppTheme {
    int DEFAULT = 0;
    int DARK = 1;
    int PLAYGROUND = 2;
}