package com.microsoft.skype.teams.storage.dao.rnapps;

import com.microsoft.skype.teams.storage.dao.IBaseDao;
import com.microsoft.skype.teams.storage.tables.RNApp;

import java.util.List;

/**
 * Represents RN App dao interface
 */
public interface RNAppsDao extends IBaseDao<RNApp> {
    RNApp fromId(String moduleId);

    void delete(String moduleId);

    List<RNApp> getAll(String userId);

    void deleteEntryForUser(String appId , String userId);
}
