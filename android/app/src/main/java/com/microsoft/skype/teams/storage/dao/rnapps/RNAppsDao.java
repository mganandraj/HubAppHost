package com.microsoft.skype.teams.storage.dao.rnapps;

import android.annotation.SuppressLint;

import androidx.annotation.Nullable;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.microsoft.skype.teams.storage.dao.IBaseDao;
import com.microsoft.skype.teams.storage.tables.RNApp;

import java.util.List;

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

//    @Query("SELECT * FROM RNApp")
//    List<RNApp> getAll(@SuppressLint("unused") String userId);

//    @Query("delete FROM RNApp WHERE moduleId=:appId")
//    void deleteEntryForUser(String appId , String userId);
}
