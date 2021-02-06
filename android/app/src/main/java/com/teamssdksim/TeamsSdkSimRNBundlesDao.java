package com.teamssdksim;

import androidx.annotation.NonNull;

import com.microsoft.skype.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.skype.teams.storage.tables.RNBundle;

import java.util.List;

public class TeamsSdkSimRNBundlesDao implements RNBundlesDao {
    @Override
    public void save(RNBundle rnBundle) {

    }

    @Override
    public RNBundle from(@NonNull String appId, @NonNull String bundleVersion) {
        return null;
    }

    @Override
    public boolean exists(@NonNull String appId, @NonNull String bundleVersion) {
        return false;
    }

    @Override
    public List<RNBundle> getAllBundles(@NonNull String appId) {
        return null;
    }

    @Override
    public void delete(@NonNull RNBundle rnBundle) {

    }
}
