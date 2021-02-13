package com.microsoft.skype.teams.storage.dao.rnbundles;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.microsoft.skype.teams.storage.tables.RNBundle;

import java.util.List;

/**
 * Represents RN Bundle dao interface
 */
@Dao
public interface RNBundlesDao {
    @Insert
    void save(RNBundle rnBundle);

    @Nullable
    @Query("SELECT * FROM RNBundle WHERE appId=:appId AND bundleVersion=:bundleVersion")
    RNBundle from(@NonNull String appId, @NonNull String bundleVersion);

    @Query("SELECT EXISTS(SELECT * FROM RNBundle WHERE appId=:appId AND bundleVersion=:bundleVersion)")
    boolean exists(@NonNull String appId, @NonNull String bundleVersion);

    @Nullable
    @Query("SELECT * FROM RNBundle WHERE appId=:appId")
    List<RNBundle> getAllBundles(@NonNull String appId);

    @Delete
    void delete(@NonNull RNBundle rnBundle);
}
