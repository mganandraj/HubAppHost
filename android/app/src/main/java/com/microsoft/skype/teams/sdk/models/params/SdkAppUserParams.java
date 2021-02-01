/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk.models.params;

import android.os.Bundle;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.microsoft.skype.teams.sdk.SdkHelper;
import com.microsoft.skype.teams.storage.tables.User;
import com.microsoft.skype.teams.utilities.java.StringUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Represents the params used to expose the currently signed in user to the SDK app.
 */
public final class SdkAppUserParams implements SdkAppWriteableParams {
    private static final String AAD_ID_KEY = "aadId";
    private static final String DISPLAY_NAME_KEY = "displayName";
    private static final String PRINCIPAL_NAME_KEY = "principalName";
    private static final String TENANT_ID_KEY = "tenantId";
    private static final String SKYPE_MRI_KEY = "skypeMri";
    private static final String JOB_TITLE_KEY = "jobTitle";
    private static final String CONTACT_ID = "contactId";
    private static final String PERSONAL_SHARE_POINT_SITE_URL = "personalSharePointSiteUrl";
    private static final String IMAGE_URI = "imageURI";
    private static final String TENANT_SITE_URL = "tenantSiteUrl";

    public final String aadId;
    public final String displayName;
    public final String principalName;
    public final String tenantId;
    public final String skypeMri;
    public final String jobTitle;
    public final String personalSharePointSiteUrl;
    public final String contactId;
    public final String imageUri;
    public final String tenantSiteUrl;

    public static SdkAppUserParams fromUser(@Nullable User user) {
        if (user == null) {
            return null;
        }

        String userObjectId = user.objectId;
        // the user ObjectId (representing the aadId) will always be null in MSA scenarios. Hence replace it with the CID
        if (StringUtils.isNullOrEmptyOrWhitespace(userObjectId)) {
            userObjectId = SdkHelper.getCidFromImageUri(user.imageUri);
        }
        return new SdkAppUserParams(userObjectId, user.displayName, user.userPrincipalName, user.tenantId, user.mri, user.jobTitle, user.contactId, null, user.imageUri, null);
    }

    public SdkAppUserParams(@NonNull String aadId,
                            @NonNull String displayName,
                            @NonNull String principalName,
                            @NonNull String tenantId,
                            @NonNull String skypeMri,
                            @NonNull String jobTitle,
                            @NonNull String contactId,
                            @Nullable String personalSharePointSiteUrl,
                            @Nullable String imageUri,
                            @Nullable String tenantSiteUrl) {
        this.aadId = aadId;
        this.displayName = displayName;
        this.principalName = principalName;
        this.tenantId = tenantId;
        this.skypeMri = skypeMri;
        this.jobTitle = jobTitle;
        this.contactId = contactId;
        this.personalSharePointSiteUrl = personalSharePointSiteUrl;
        this.imageUri = imageUri;
        this.tenantSiteUrl = tenantSiteUrl;
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(AAD_ID_KEY, aadId);
        bundle.putString(DISPLAY_NAME_KEY, displayName);
        bundle.putString(PRINCIPAL_NAME_KEY, principalName);
        bundle.putString(TENANT_ID_KEY, tenantId);
        bundle.putString(SKYPE_MRI_KEY, skypeMri);
        bundle.putString(JOB_TITLE_KEY, jobTitle);
        bundle.putString(CONTACT_ID, contactId);
        bundle.putString(PERSONAL_SHARE_POINT_SITE_URL, personalSharePointSiteUrl);
        bundle.putString(IMAGE_URI, imageUri);
        bundle.putString(TENANT_SITE_URL, tenantSiteUrl);
        return bundle;
    }

    @NonNull
    @Override
    public WritableMap toMap() {
        WritableMap map = new WritableNativeMap();
        map.putString(AAD_ID_KEY, aadId);
        map.putString(DISPLAY_NAME_KEY, displayName);
        map.putString(PRINCIPAL_NAME_KEY, principalName);
        map.putString(TENANT_ID_KEY, tenantId);
        map.putString(SKYPE_MRI_KEY, skypeMri);
        map.putString(JOB_TITLE_KEY, jobTitle);
        map.putString(CONTACT_ID, contactId);
        map.putString(PERSONAL_SHARE_POINT_SITE_URL, personalSharePointSiteUrl);
        map.putString(IMAGE_URI, imageUri);
        map.putString(TENANT_SITE_URL, tenantSiteUrl);
        return map;
    }
}
