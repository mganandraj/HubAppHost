package com.microsoft.teams.sdk.models.params;

import android.os.Bundle;
import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;

import java.util.ArrayList;

/**
 * Response class of SdkImageAndFilePicker.java
 */
public class SdkImageAndFilePickerResponseParams implements SdkAppWriteableParams {

    public static final int SUCCESS = 200;
    public static final int ACCESS_DENIED = 403;
    public static final int CAMERA_NOT_SUPPORTED = 415;
    public static final int OFFICE_LENS_NOT_INITIALISED = 416;
    public static final int DOCUMENT_MENU_NOT_INITIALISED = 417;
    public static final int FAILED_FETCH = 419;
    public static final int CANCELLED = 420;
    public static final int OFFICE_LENS_CANCELLED = 421;
    public static final int DOCUMENT_PROVIDER_CANCELLED = 422;
    public static final int SHARE_TARGET_PICKER_CANCELLED = 423;
    public static final int ERROR_EMPTY_FILE_IDENTIFIER = 424;
    public static final int STATUS_UNKNOWN = 500;
    public static final String STATUS_CODE_TAG = "statusCode";
    public static final String FILE_METADATA_TAG = "fileMetadatas";
    public static final String FILE_IDENTIFIER_TAG = "fileIdentifiers";

    private int mStatusCode;
    private ArrayList<SdkImageAndFileMetadata> mFileMetadatas;

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public void setStatusCode(int statusCode) {
        this.mStatusCode = statusCode;
    }

    public void addFile(String fileIdentifier, String fileName, String filePath) {
        if (this.mFileMetadatas == null) {
            this.mFileMetadatas = new ArrayList<>();
        }
        SdkImageAndFileMetadata fileMetadata = new SdkImageAndFileMetadata(fileIdentifier, fileName, filePath);
        this.mFileMetadatas.add(fileMetadata);
    }

    public void addFile(SdkImageAndFileMetadata sdkImageAndFileMetadata) {
        if (this.mFileMetadatas == null) {
            this.mFileMetadatas = new ArrayList<>();
        }
        this.mFileMetadatas.add(sdkImageAndFileMetadata);
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        return Arguments.toBundle(toMap());
    }

    @NonNull
    @Override
    public WritableMap toMap() {
        WritableMap map = Arguments.createMap();
        map.putInt(this.STATUS_CODE_TAG, mStatusCode);
        if (mFileMetadatas != null) {

            WritableArray fileMetadataArray = new WritableNativeArray();
            WritableArray fileIdentifierArray = new WritableNativeArray();
            for (SdkImageAndFileMetadata metaData : mFileMetadatas) {
                fileMetadataArray.pushMap(metaData.toMap());
                fileIdentifierArray.pushString(metaData.getFileIdentifier());
            }
            map.putArray(this.FILE_METADATA_TAG, fileMetadataArray);
            map.putArray(this.FILE_IDENTIFIER_TAG, fileIdentifierArray); //To be removed once all 1P apps are updated to latest response
        }
        return map;
    }
}
