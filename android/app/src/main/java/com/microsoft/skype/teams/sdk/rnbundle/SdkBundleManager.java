/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk.rnbundle;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.skype.teams.logger.ILogger;
import com.microsoft.skype.teams.logger.LogPriority;
import com.microsoft.skype.teams.sdk.models.SdkAppManifest;
import com.microsoft.skype.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.skype.teams.storage.tables.RNBundle;
import com.microsoft.skype.teams.utilities.IOUtilities;
import com.microsoft.skype.teams.utilities.java.JsonUtils;
import com.microsoft.skype.teams.utilities.java.StringUtils;
import com.microsoft.teams.core.app.ITeamsApplication;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.microsoft.skype.teams.sdk.rnbundle.SdkBundleUtils.BundleSource.CODEPUSH;
import static com.microsoft.skype.teams.sdk.rnbundle.SdkBundleUtils.BundleSource.LOCAL;

/**
 * Responsible to manage downloaded bundles
 * Restriction: Bundle with version number should have same content across all codepush deployment environment.
 */
@Singleton
public final class SdkBundleManager implements ISdkBundleManager {

    private static final String LOG_TAG = "SdkBundleManager";
    private static final String BUNDLE_DIRECTORY = "rnbundle";

    private final ITeamsApplication mTeamsApplication;
    private final RNBundlesDao mRNBundlesDao;

    @Inject
    public SdkBundleManager(@NonNull ITeamsApplication teamsApplication,
                            @NonNull RNBundlesDao rnBundlesDao) {
        mTeamsApplication = teamsApplication;
        mRNBundlesDao = rnBundlesDao;
    }

    @Override
    public boolean exists(@NonNull String appId, @NonNull String version) {
        return mRNBundlesDao.exists(appId, version);
    }

    @Override
    public void addBundle(@NonNull String appId, @NonNull SdkAppManifest manifest, @NonNull String tempBundleLocation, @NonNull SdkBundleDownloadRequest downloadRequest) {
        String source = downloadRequest.getSource();
        ILogger logger = downloadRequest.getLogger();
        Context context = mTeamsApplication.getApplicationContext();
        String version = manifest.version;
        RNBundle rnBundle = mRNBundlesDao.from(appId, version);
        String newPackageHash = new File(tempBundleLocation).getParentFile().getName();

        if (rnBundle == null) {
            /* Add entry in RNBundle **/

            // Copy file
            File rnBundleVersionDestinationDirectory = getRNBundleVersionDirectory(context, appId, version);
            try {
                IOUtilities.copyDirectory(new File(tempBundleLocation), rnBundleVersionDestinationDirectory);

                // Add in DB
                rnBundle = new RNBundle();
                rnBundle.appId = appId;
                rnBundle.bundleVersion = version;
                rnBundle.bundleSource = source;
                rnBundle.bundleLocation = rnBundleVersionDestinationDirectory.getAbsolutePath();
                rnBundle.manifest = JsonUtils.GSON.toJson(manifest, SdkAppManifest.class);
                if (CODEPUSH.equals(source)) {
                    rnBundle.packageHashList = newPackageHash;
                }
                mRNBundlesDao.save(rnBundle);
            } catch (IOException e) {
                logger.log(LogPriority.ERROR, LOG_TAG, e.getMessage());
            }
        } else {
            if (CODEPUSH.equals(source)) {
                /* Update existing RNBundle entry by appending packageHash.
                  earlier bundle source was LOCAL, replace it with CODEPUSH */
                String packageList = rnBundle.packageHashList;
                if (StringUtils.isNotEmpty(packageList)) {
                    packageList += ", ";
                }
                packageList += newPackageHash;
                rnBundle.packageHashList = packageList;

                if (rnBundle.bundleSource.equals(LOCAL)) {
                    rnBundle.bundleSource = CODEPUSH;
                }
                mRNBundlesDao.save(rnBundle);
            }
        }
    }

    @Override
    public void deleteBundle(@NonNull String appId, @NonNull String version) {
        RNBundle rnBundle = mRNBundlesDao.from(appId, version);
        if (rnBundle != null) {
            mRNBundlesDao.delete(rnBundle);
            try {
                IOUtilities.deleteDirectory(rnBundle.bundleLocation);
            } catch (IOException e) {
                mTeamsApplication.getLogger(null).log(LogPriority.ERROR, LOG_TAG, e.getMessage());
            }
        }
    }

    @Override
    public RNBundle getLocalBundle(@NonNull String appId, @NonNull String packageHash) {
        List<RNBundle> rnBundles = mRNBundlesDao.getAllBundles(appId);
        if (rnBundles == null) {
            return null;
        }

        for (RNBundle rnBundle : rnBundles) {
            List<String> hashes = Arrays.asList(rnBundle.packageHashList.split("\\s*,\\s*"));
            for (String ph : hashes) {
                if (packageHash.equals(ph)) {
                    return rnBundle;
                }
            }
        }
        return null;
    }

    @Override
    @Nullable
    public String getBundleLocation(@NonNull String appId, @NonNull String version) {
        RNBundle rnBundle = mRNBundlesDao.from(appId, version);
        if (rnBundle != null) {
            return rnBundle.bundleLocation;
        }
        return null;
    }

    @NonNull
    private File getRNBundleBaseDirectory(@NonNull Context context) {
        return new File(context.getFilesDir(), BUNDLE_DIRECTORY);
    }

    @NonNull
    private File getRNBundleAppDirectory(@NonNull Context context, @NonNull String appId) {
        return new File(getRNBundleBaseDirectory(context), appId);
    }

    @NonNull
    private File getRNBundleVersionDirectory(@NonNull Context context, @NonNull String appId, @NonNull String version) {
        return new File(getRNBundleAppDirectory(context, appId), version);
    }
}
