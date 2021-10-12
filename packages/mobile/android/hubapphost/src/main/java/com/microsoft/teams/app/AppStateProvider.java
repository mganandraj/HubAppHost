/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.app;

import android.app.Activity;
import androidx.core.util.Pair;


import java.lang.ref.WeakReference;

import javax.annotation.Nullable;

/** Static provider for App State */
public final class AppStateProvider {

    private static boolean sAppVisible;

    //Used to consume app create duration for incoming call telemetry
    private static boolean sIsAppCreateScenarioComplete = false;
    private static WeakReference<Activity> sCurrentActivity;
    private static long sAppCreateStartTime;
    private static long sAppCreateEndTime;

    private AppStateProvider() { }

    public static void setIsAppVisible(boolean isAppVisible) {
        sAppVisible = isAppVisible;
    }

    public static boolean isAppVisible() {
        return sAppVisible;
    }

    /**
     * Needed for incoming calls to see if the app was launched for incoming call
     */
    public static boolean isAppCreateScenarioComplete() {
        return sIsAppCreateScenarioComplete;
    }

    /**
     * Invoked by app creation cause
     */
    public static void setAppCreateScenarioComplete() {
        sIsAppCreateScenarioComplete = true;
    }

    @Nullable
    public static Activity getCurrentActivity() {
        return sCurrentActivity != null ? sCurrentActivity.get() : null;
    }

    /**
     * Sets the current running activity for the application
     *
     * @param activity the activity
     */
    public static void setCurrentActivity(Activity activity) {
        sCurrentActivity = new WeakReference<>(activity);
    }

    public static void setAppCreateStartTime(long appCreateStartTime) {
        sAppCreateStartTime = appCreateStartTime;
    }

    public static void setAppCreateEndTime(long appCreateEndTime) {
        sAppCreateEndTime = appCreateEndTime;
    }

    /**
     * Needed for incoming call telemetry
     */
    public static Pair<Long, Long> getAppLaunchTimes() {
        return new Pair(sAppCreateStartTime, sAppCreateEndTime);
    }
}
