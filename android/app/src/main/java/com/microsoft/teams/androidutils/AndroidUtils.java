/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.androidutils;

import android.net.Uri;
import android.os.Build;
import androidx.annotation.Nullable;

import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
// import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Set of general Android utility methods
 */
public final class AndroidUtils {
    private AndroidUtils() {
    }

    /**
     * Checks if the device is running Lollipop (API 21) or higher
     *
     * @return True if device OS is version 21 or higher
     */
    public static boolean isLollipopOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * Checks if the device is running Lollipop MR1 (API 22) or higher
     *
     * @return True if device OS is version 22 or higher
     */
    public static boolean isLollipopMR1OrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    /**
     * Checks if the device is running Marshmallow (API 23) or higher
     *
     * @return True if the device OS is version 23 or higher
     */
    public static boolean isMarshmallowOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * Checks if the device is running Nougat (API 24) or higher
     *
     * @return True if the device OS is version 24 or higher
     */
    public static boolean isNougatOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    /**
     * Checks if the device is running Nougat MR1 (API 25) or higher
     *
     * @return True if the device OS is version 25 or higher
     */
    public static boolean isNougatMR1OrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1;
    }

    /**
     * Checks if the device is running Oreo (API 26) or higher
     *
     * @return True if the device OS is version 26 or higher
     */
    public static boolean isOreoOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

    /**
     * Checks if the device is running Oreo MR1 (API 27) or higher
     *
     * @return True if the device OS is version 27 or higher
     */
    public static boolean isOreoMR1OrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1;
    }

    /**
     * Checks if the device is running Pie (API 28) or higher
     *
     * @return True if the device OS is version 28 or higher
     */
    public static boolean isPOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P;
    }

    /**
     * Checks if the device is running Android Q (API 29) or higher
     *
     * @return True if the device OS is version 29 or higher
     */
//    public static boolean is10OrHigher() {
//        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;
//    }

    /**
     * Parses a string into a URI without throwing an exception
     *
     * @param uri String URI to parse
     * @return URI form of string input, null if string is empty or not parseable
     */
    @Nullable
    public static Uri safeParse(@Nullable String uri) {
        if (TextUtils.isEmpty(uri)) {
            return null;
        }

        try {
            return Uri.parse(uri);
        } catch (Exception ex) {
            // Do nothing.
        }
        return null;
    }

    /**
     * Checks if visibility has the low profile flag
     *
     * @param visibility Visibility to check
     * @return True if the visibility contains the SYSTEM_UI_FLAG_LOW_PROFILE flag
     */
    public static boolean isSystemUiFlagLowProfile(int visibility) {
        return (visibility & View.SYSTEM_UI_FLAG_LOW_PROFILE) == View.SYSTEM_UI_FLAG_LOW_PROFILE;
    }

    /**
     * Deletes a filesystem item that can be either a file or a directory
     *
     * @param item Item to delete
     * @return True if item was succesfully deleted
     */
//    public static boolean deleteFile(@Nullable File item) {
//        if (item == null || !item.exists()) {
//            return false;
//        }
//
//        if (item.isDirectory()) {
//            try {
//                FileUtils.deleteDirectory(item);
//                return true;
//            } catch (IOException ex) {
//                return false;
//            }
//        } else {
//            return item.delete();
//        }
//    }

    /***
     * Returns true if the caller is running on the main thread
     * @return
     */
    public static boolean isMainThread() {
        return (Looper.myLooper() == Looper.getMainLooper());
    }
}
