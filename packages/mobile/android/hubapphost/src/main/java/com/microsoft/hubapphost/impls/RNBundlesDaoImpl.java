package com.microsoft.hubapphost.impls;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.microsoft.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.teams.storage.tables.RNBundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RNBundlesDaoImpl implements RNBundlesDao {

    HashMap<String, RNBundle> mRNBundleMap = new HashMap<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void update(RNBundle rnBundle) {
        if(mRNBundleMap.containsKey(rnBundle.appId))
            mRNBundleMap.replace(rnBundle.appId, rnBundle);
        else
            mRNBundleMap.put(rnBundle.appId, rnBundle);
    }

    @Override
    public void add(RNBundle rnBundle) {
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
