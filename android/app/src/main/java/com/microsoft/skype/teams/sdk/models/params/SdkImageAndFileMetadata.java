package com.microsoft.skype.teams.sdk.models.params;

import android.os.Bundle;
import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
//import com.microsoft.skype.teams.files.common.FileUtilities;
//import com.microsoft.teams.core.files.FileUtilitiesCore;

/**
 * Represents parameter of File metadata from SDK
 */
public class SdkImageAndFileMetadata implements SdkAppWriteableParams {

    public static final String FILE_IDENTIFIER_TAG = "fileIdentifier";
    public static final String FILE_NAME_TAG = "fileName";
    public static final String FILE_EXTENSION_TAG = "fileExtension";
    public static final String FILE_PATH_TAG = "filePath";

    private String mFileName;
    private String mFileExtension;
    private String mFileIdentifier;
    private String mFilePath;

    public String getFileIdentifier() {
        return this.mFileIdentifier;
    }

    public SdkImageAndFileMetadata(String fileIdentifier, String fileName, String filePath) {
        this.mFileIdentifier = fileIdentifier;
        // this.mFileName = FileUtilities.getResourceNameFromFileName(fileName);
        // this.mFileExtension = FileUtilitiesCore.getFileExtension(fileName);
        this.mFilePath = filePath;
        throw new RuntimeException("Not Implemented");
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
        map.putString(this.FILE_IDENTIFIER_TAG, mFileIdentifier);
        map.putString(this.FILE_NAME_TAG, mFileName);
        map.putString(this.FILE_EXTENSION_TAG, mFileExtension);
        map.putString(this.FILE_PATH_TAG, mFilePath);

        return map;
    }

    public static SdkImageAndFileMetadata fromMap(ReadableMap map) {
        String fileName = map.getString(FILE_NAME_TAG);
        String fileIdentifier = map.getString(FILE_IDENTIFIER_TAG);
        String filePath = map.getString(FILE_PATH_TAG);
        return new SdkImageAndFileMetadata(fileIdentifier, fileName, filePath);
    }
}
