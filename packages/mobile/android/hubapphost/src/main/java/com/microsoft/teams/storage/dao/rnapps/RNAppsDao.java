package com.microsoft.teams.storage.dao.rnapps;

import androidx.annotation.Nullable;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.microsoft.teams.storage.dao.IBaseDao;
import com.microsoft.teams.storage.tables.RNApp;
import com.microsoft.teams.storage.tables.RNBundle;

/**
 * Represents RN App dao interface
 */
@Dao
public interface RNAppsDao extends IBaseDao<RNApp> {
    @Nullable
    @Query("SELECT * FROM RNApp WHERE moduleId=:moduleId")
    RNApp fromId(String moduleId);

    @Query("DELETE FROM RNApp WHERE moduleId=:moduleId")
    void delete(String moduleId);

    @Insert
    void add(RNApp rnApp);

//    @Query("SELECT * FROM RNApp")
//    List<RNApp> getAll(@SuppressLint("unused") String userId);

//    @Query("delete FROM RNApp WHERE moduleId=:appId")
//    void deleteEntryForUser(String appId , String userId);
}
