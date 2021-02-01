package com.microsoft.skype.teams.sdk.models.params;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Constants for themes.
 */
@Retention(RetentionPolicy.SOURCE)
@StringDef({SdkAppTheme.DEFAULT, SdkAppTheme.DARK})
public @interface SdkAppTheme {
    String DEFAULT = "default";
    String DARK = "dark";
}
