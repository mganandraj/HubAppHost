package com.teamssdksim.impls;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.microsoft.teams.storage.dao.rnapps.RNAppsDao;
import com.microsoft.teams.storage.tables.RNApp;

import java.util.Collection;
import java.util.HashMap;

public class TeamsSdkRNAppsDao implements RNAppsDao {

    HashMap<String, RNApp> mRNAppMap = new HashMap<>();

    @Override
    public RNApp fromId(String moduleId) {
//        RNApp rnApp = new RNApp();
//        rnApp.moduleId = moduleId;
//        rnApp.tenantId = "";
//        rnApp.bundleVersion = "0.1";
//        rnApp.lastUpdateCheckedTimeInMillis = 0;
//
//        return rnApp;
        return mRNAppMap.get(moduleId);
    }

    @Override
    public void delete(String moduleId) {

    }

//    @Override
//    public List<RNApp> getAll(String userId) {
//        return new ArrayList<>(mRNAppMap.values());
//    }
//
//    @Override
//    public void deleteEntryForUser(String appId, String userId) {
//
//    }

//    @Override
//    public void deleteTable(RNApp table) {
//
//    }

    @Override
    public void delete(@NonNull RNApp item) {

    }

    @Override
    public void save(@NonNull RNApp item) {
        mRNAppMap.put(item.moduleId, item);
    }

    @Override
    public void saveAllInTransaction(@Nullable Collection<RNApp> items) {

    }

    @Override
    public void saveAllWithoutTransaction(@Nullable Collection<RNApp> items) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void update(@NonNull RNApp item) {
        mRNAppMap.replace(item.moduleId, item);
    }
}
