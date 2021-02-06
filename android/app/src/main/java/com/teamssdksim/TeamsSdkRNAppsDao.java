package com.teamssdksim;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.skype.teams.storage.dao.rnapps.RNAppsDao;
import com.microsoft.skype.teams.storage.tables.RNApp;

import java.util.Collection;
import java.util.List;

public class TeamsSdkRNAppsDao implements RNAppsDao {
    @Override
    public RNApp fromId(String moduleId) {
        return null;
    }

    @Override
    public void delete(String moduleId) {

    }

    @Override
    public List<RNApp> getAll(String userId) {
        return null;
    }

    @Override
    public void deleteEntryForUser(String appId, String userId) {

    }

    @Override
    public void deleteTable(RNApp table) {

    }

    @Override
    public void delete(@NonNull RNApp item) {

    }

    @Override
    public void save(@NonNull RNApp item) {

    }

    @Override
    public void saveAllInTransaction(@Nullable Collection<RNApp> items) {

    }

    @Override
    public void saveAllWithoutTransaction(@Nullable Collection<RNApp> items) {

    }

    @Override
    public void update(@NonNull RNApp item) {

    }
}
