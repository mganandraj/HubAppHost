/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.core.utilities;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.microsoft.teams.utilities.java.UtilityInstantiationException;
import com.microsoft.teams.core.models.BuildConfiguration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Helper class for app's build configuration.
 * Provides helper methods to query the app build type, version, flavor, etc.
 */
public final class AppBuildConfigurationHelper {
    private static BuildConfiguration sAppBuildConfiguration;

    //Save some variables at initialization which used often
    private static boolean sIsIpPhone = false;
    private static boolean sIsDev = false;
    private static boolean sIsProduction = false;
    private static boolean sIsConvergedApp = false;
    private static boolean sIsTFLLife = false;
    private static boolean sIsBaidu = false;
    private static boolean sIsKingston = false;
    private static boolean sIsRealWear = false;
    private static boolean sIsNorden = false;
    private static boolean sIsPanel = false;
    private static boolean sIsPlaybeta = false;

    public static void setAppBuildConfiguration(@NonNull BuildConfiguration appBuildConfiguration) {
        sAppBuildConfiguration = appBuildConfiguration;
        sIsPanel = sAppBuildConfiguration.flavor.contains("panel");
        sIsNorden = sAppBuildConfiguration.flavor.toLowerCase().contains("norden");
        sIsIpPhone = sIsNorden || sIsPanel  || sAppBuildConfiguration.flavor.toLowerCase().contains("ipphone");
        sIsKingston = sAppBuildConfiguration.flavor.contains("kingston");
        sIsDev = sAppBuildConfiguration.flavor.contains("dev")
                || sAppBuildConfiguration.flavor.contains("life");
        sIsConvergedApp = sAppBuildConfiguration.flavor.contains("life")
                || (sAppBuildConfiguration.isConvergedApp && !sIsKingston && !sIsNorden && !sIsIpPhone)
                        /* We can remove this comment, when the ipphone team wanted to enable new MSAL stack
                          || sAppBuildConfiguration.flavor.contains("ipPhone")
                        */
                || (sAppBuildConfiguration.flavor.contains("dev") && isDebug() && !isAutomation());
        sIsTFLLife = sAppBuildConfiguration.flavor.contains("life");
        sIsProduction = sAppBuildConfiguration.flavor.contains("production");
        sIsBaidu = sAppBuildConfiguration.flavor.contains("baidu");
        sIsRealWear = sAppBuildConfiguration.flavor.contains("realwear");
        sIsPanel = sAppBuildConfiguration.flavor.contains("panel");
        sIsPlaybeta = sAppBuildConfiguration.flavor.contains("playBeta");
    }

    public static boolean isDebug() {
        return sAppBuildConfiguration.debug;
    }

    public static boolean isRelease() {
        return !isDebug();
    }

    public static boolean isDev() {
        return sIsDev;
    }

    public static boolean isConvergedApp() {
        return sIsConvergedApp;
    }

    public static void setTFLOn() {
        sIsTFLLife = true;
    }

    public static void setBaidu(boolean isBaidu) {
        sIsBaidu = isBaidu;
    }

    public static boolean sIsTFL() {
        return sIsTFLLife;
    }

    public static boolean isDebugOrDevBuild() {
        return isDebug() || isDev();
    }

    public static boolean isProduction() {
        return sIsProduction || sIsBaidu || sIsRealWear;
    }

    public static boolean isTap() {
        return sAppBuildConfiguration.flavor.contains("beta");
    }

    public static boolean isBeta() {
        // "prealpha" contains "alpha" substring, so check to make sure that the flavor is NOT prealpha.
        return sAppBuildConfiguration.flavor.contains("alpha") && !sAppBuildConfiguration.flavor.contains("prealpha");
    }

    public static boolean isBaidu() {
        return sIsBaidu;
    }

    public static boolean isAlpha() {
        return sAppBuildConfiguration.flavor.contains("prealpha");
    }

    public static boolean isMonkeyTest() {
        return sAppBuildConfiguration.flavor.contains("monkeytest");
    }

    public static boolean isDeviceFlavor() {
        return isIpPhone() || isKingston() || isNorden() || isPanel();
    }

    public static boolean isIpPhone() {
        return sIsIpPhone;
    }

    public static boolean isKingston() {
        return sIsKingston;
    }

    public static boolean isPanel() {
        return sIsPanel;
    }

    public static boolean isRealWear() {
        return sIsRealWear;
    }

    public static boolean isNorden() {
        return sIsNorden;
    }

    public static boolean isPlayBeta() {
        return sIsPlaybeta;
    }

    public static boolean isPhoneDevice() {
        return !isKingston() && !isNorden() && !isIpPhone();
    }

    public static boolean isIntegration() {
        return sAppBuildConfiguration.flavor.contains("integration");
    }

    public static boolean isDevDebug() {
        return isDebug() && isDev();
    }

    @NonNull
    public static String getApplicationId() {
        return sAppBuildConfiguration.applicationId;
    }

    @NonNull
    public static String getVersionName() {
        return sAppBuildConfiguration.versionName;
    }

    @NonNull
    public static String getBranchName() {
        return sAppBuildConfiguration.branchName;
    }

    @NonNull
    public static String getFlavor() {
        return sAppBuildConfiguration.flavor;
    }

    public static int getVersionCode() {
        return sAppBuildConfiguration.versionCode;
    }

    @NonNull
    public static String getBuildType() {
        return sAppBuildConfiguration.buildType;
    }

    /**
     * Return the build type value that the app is
     */
    @BuildType
    @NonNull
    public static String getBuildTypeValue() {
        if (isDev()) {
            return BuildType.DEV_BUILD;
        } else if (isTap()) {
            return BuildType.TAP_BUILD;
        } else if (isProduction()) {
            return BuildType.PROD_BUILD;
        } else if (isBeta()) {
            return BuildType.BETA_BUILD;
        } else if (isAlpha()) {
            return BuildType.ALPHA_BUILD;
        } else {
            return BuildType.UNKNOWN;
        }
    }

    public static void setAutomationOn() {
        sAppBuildConfiguration.automation = true;
    }

    public static boolean isAutomation() { return sAppBuildConfiguration.automation; }

    private AppBuildConfigurationHelper() {
        throw new UtilityInstantiationException();
    }

    /**
     * Build Type identifiers
     */
    @StringDef({
            BuildType.DEV_BUILD,
            BuildType.ALPHA_BUILD,
            BuildType.BETA_BUILD,
            BuildType.TAP_BUILD,
            BuildType.PROD_BUILD,
            BuildType.UNKNOWN
    })
    @Retention(RetentionPolicy.SOURCE)
    @interface BuildType {
        String DEV_BUILD = "Dev";
        String ALPHA_BUILD = "Alpha";
        String BETA_BUILD = "Beta";
        String TAP_BUILD = "Tap";
        String PROD_BUILD = "Prod";
        String UNKNOWN = "Unknown";
    }
}
