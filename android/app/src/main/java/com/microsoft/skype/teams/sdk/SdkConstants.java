package com.microsoft.skype.teams.sdk;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.BUNDLE_ADDED;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.BUNDLE_ALREADY_EXISTS;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.DOWNLOADED;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.DOWNLOADING;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.ERROR_BUNDLE_UNAVAILABLE;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.ERROR_BUNDLE_UNZIP_FAILED;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.ERROR_DOWNLOAD_FAILED;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.ERROR_INVALID_MANIFEST;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.ERROR_JS_FILE_UNAVAILABLE;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.ERROR_MANIFEST_PARSING_FAILED;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.ERROR_MANIFEST_UNAVAILABLE;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.JS_FILE_EXISTS;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.MANIFEST_FILE_EXISTS;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.QUEUED;
import static com.microsoft.skype.teams.sdk.SdkConstants.SdkRequestDownloadStatus.UPDATE_NOT_AVAILABLE;

/**
 * SDK Constants
 */
public final class SdkConstants {

    // private constructor
    private SdkConstants() {
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({QUEUED, DOWNLOADING, DOWNLOADED, JS_FILE_EXISTS, ERROR_BUNDLE_UNAVAILABLE, ERROR_JS_FILE_UNAVAILABLE,
            MANIFEST_FILE_EXISTS, ERROR_DOWNLOAD_FAILED, UPDATE_NOT_AVAILABLE, BUNDLE_ADDED, BUNDLE_ALREADY_EXISTS,
            ERROR_MANIFEST_UNAVAILABLE, ERROR_MANIFEST_PARSING_FAILED, ERROR_BUNDLE_UNZIP_FAILED, ERROR_INVALID_MANIFEST})
    public @interface SdkRequestDownloadStatus {
        int QUEUED = 1;
        int DOWNLOADING = 2;
        int DOWNLOADED = 3;
        int JS_FILE_EXISTS = 4;
        int MANIFEST_FILE_EXISTS = 5;
        int ERROR_DOWNLOAD_FAILED = 11;
        int ERROR_BUNDLE_UNAVAILABLE = 12;
        int ERROR_JS_FILE_UNAVAILABLE = 13;
        int ERROR_MANIFEST_UNAVAILABLE = 14;
        int ERROR_MANIFEST_PARSING_FAILED = 15;
        int ERROR_BUNDLE_UNZIP_FAILED = 16;
        int ERROR_INVALID_MANIFEST = 17;
        int UPDATE_NOT_AVAILABLE = 21;
        int BUNDLE_ADDED = 22;
        int BUNDLE_ALREADY_EXISTS = 23;
        int BOTTOM_BAR_PAGE_RECREATE = 24;
    }

    public static final String RN_BUNDLE_DOWNLOAD_ERROR = "rn_bundle_download_error";
    public static final String RN_BUNDLE_PARSING_ERROR = "rn_bundle_parsing_error";

    /**
     * Constant class for Codepush check update scenario
     */
    public static final class SdkCodepushCheckUpdateStatus {
        public static final String NO_UPDATE = "no_update";
        public static final String UPDATE_AVAILABLE = "update_available";
        public static final String ERROR_OCCURRED = "error_occurred";
    }

    /**
     * Constant class for Codepush bundle download scenario
     */
    public static final class SdkCodepushBundleDownloadStatus {
        public static final String DOWNLOADED = "downloaded";
        public static final String FAILED = "failed";
    }
}
