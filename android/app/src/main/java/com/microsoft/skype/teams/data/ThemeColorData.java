/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.data;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.TypedValue;

import com.microsoft.skype.teams.theme.TeamsAppTheme;

/**
 * The data class which holds all color resource used programmatically in Java code, where needs to
 * dynamically set the color based on different App Themes.
 */
public final class ThemeColorData {
    @TeamsAppTheme
    private static int sAppTheme = TeamsAppTheme.DEFAULT;

    private ThemeColorData() {
    }

    /**
     * The default theme to be used until such time setAppTheme is called is set using this method.
     * This is done as soon as app is inited in onMAMCreate
     * @param theme
     */
    public static void setAppThemeFromGlobalPreferences(@TeamsAppTheme int theme) {
        sAppTheme = theme;
    }

    public static void setAppTheme(@TeamsAppTheme int appTheme) {
        sAppTheme = appTheme;
    }

    public static boolean isDarkTheme() {
        return sAppTheme == TeamsAppTheme.DARK;
    }

    public static boolean isPlaygroundTheme() {
        return sAppTheme == TeamsAppTheme.PLAYGROUND;
    }

    /**
     * Check if context is in dark theme.
     * for ipphone devices, some activities may be in dark theme, event though global theme is light.
     */
    public static boolean isContextInDarkTheme(Context context, @AttrRes final int attr) {
        TypedValue attrValue = new TypedValue();
        context.getTheme().resolveAttribute(attr, attrValue, true);
        return !TextUtils.isEmpty(attrValue.string) && "dark".equals(attrValue.string.toString());
    }

    public static int getValueForAttribute(@NonNull Context context, @AttrRes int attrId) {
        TypedValue attrValue = new TypedValue();
        context.getTheme().resolveAttribute(attrId, attrValue, true);
        return attrValue.data;
    }

    /**
     * Return the resource id corresponding to the drawable from the given attribute
     */
    public static int getResourceIdForAttribute(@NonNull Context context, @AttrRes int attrId) {
        TypedValue attrValue = new TypedValue();
        context.getTheme().resolveAttribute(attrId, attrValue, true);
        return attrValue.resourceId;
    }

    public static Drawable getThemeSpecificDrawable(@NonNull Context context, @AttrRes int attrId) {
        int[] attrs = new int[]{attrId};
        if (context == null) {
            return null;
        }
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs);
        Drawable themeSpecificDrawable = ta.getDrawable(0);
        ta.recycle();
        return themeSpecificDrawable;
    }
}
