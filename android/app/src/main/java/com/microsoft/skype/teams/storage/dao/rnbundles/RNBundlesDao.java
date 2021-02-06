package com.microsoft.skype.teams.storage.dao.rnbundles;

import androidx.annotation.NonNull;

import com.microsoft.skype.teams.storage.tables.RNBundle;

import java.util.List;

/**
 * Represents RN Bundle dao interface
 */
public interface RNBundlesDao {
    void save(RNBundle rnBundle);

    RNBundle from(@NonNull String appId, @NonNull String bundleVersion);

    boolean exists(@NonNull String appId, @NonNull String bundleVersion);

    List<RNBundle> getAllBundles(@NonNull String appId);

    void delete(@NonNull RNBundle rnBundle);
}
