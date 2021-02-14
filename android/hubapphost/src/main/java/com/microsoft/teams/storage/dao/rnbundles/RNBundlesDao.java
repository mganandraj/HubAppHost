package com.microsoft.teams.storage.dao.rnbundles;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.microsoft.teams.storage.tables.RNBundle;

import java.util.List;

/**
 * Represents RN Bundle dao interface
 */
@Dao
public interface RNBundlesDao {
    // Note : In teams code, there is only one "save" method which is expected to "Update or Add".
    // It is hard to do it with Room db, hence simplifying the flow by adding an "add method" and renaming "save" to update (to ensure we udpate all callsites)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(RNBundle rnBundle);

    @Insert
    void add(RNBundle rnBundle);

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
