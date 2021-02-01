package com.microsoft.skype.teams.sdk.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringDef;

import com.microsoft.skype.teams.sdk.SdkApplicationContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Class to define Quick Action configuration
 */
public class QuickActionConfig {

    @StringDef({QuickActionType.RUNNABLE, QuickActionType.EVENT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface QuickActionType {
        String RUNNABLE = "runnable";
        String EVENT = "event";
    }

    /**
     * It's the type of QuickAction
     */
    @QuickActionType
    public String type;
    /**
     * QuickAction name
     */
    public String name;

    /**
     * Flag to enable/disable pre initialization of module
     */
    public boolean isPreInitEnabled;

    @Nullable
    public static QuickActionConfig getQuickActionConfigForModule(SdkApplicationContext sdkApplicationContext) {
        if (sdkApplicationContext != null) {
            SdkAppManifest sdkAppManifest = sdkApplicationContext.getAppManifest();
            if (SdkAppManifest.ModuleType.QUICK_ACTION.equals(sdkAppManifest.moduleType)) {
                return sdkAppManifest.quickActionConfig;
            }
        }
        return null;
    }

    @Override
    @NonNull
    public String toString() {
        return "com.microsoft.skype.teams.sdk.models.QuickActionConfig{"
                + "type='" + type + '\''
                + ", name='" + name + '\''
                + ", isPreInitEnabled=" + isPreInitEnabled
                + '}';
    }
}
