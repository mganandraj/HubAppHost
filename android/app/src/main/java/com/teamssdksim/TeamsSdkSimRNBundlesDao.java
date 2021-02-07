package com.teamssdksim;

import androidx.annotation.NonNull;

import com.microsoft.skype.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.skype.teams.storage.tables.AppDefinition;
import com.microsoft.skype.teams.storage.tables.RNBundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamsSdkSimRNBundlesDao implements RNBundlesDao {

    HashMap<String, RNBundle> mRNBundleMap = new HashMap<>();

    @Override
    public void save(RNBundle rnBundle) {
        mRNBundleMap.put(rnBundle.appId, rnBundle);
    }

    @Override
    public RNBundle from(@NonNull String appId, @NonNull String bundleVersion) {
        return mRNBundleMap.get(appId);
    }

    @Override
    public boolean exists(@NonNull String appId, @NonNull String bundleVersion) {
        return mRNBundleMap.containsKey(appId);
    }

    @Override
    public List<RNBundle> getAllBundles(@NonNull String appId) {
        return new ArrayList<>(mRNBundleMap.values());
    }

    @Override
    public void delete(@NonNull RNBundle rnBundle) {
        mRNBundleMap.remove(rnBundle.appId);
    }
}
