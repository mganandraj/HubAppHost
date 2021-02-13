package com.microsoft.skype.teams.storage.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.microsoft.skype.teams.storage.dao.appdefinition.AppDefinitionDao;
import com.microsoft.skype.teams.storage.dao.rnapps.RNAppsDao;
import com.microsoft.skype.teams.storage.dao.rnbundles.RNBundlesDao;
import com.microsoft.skype.teams.storage.tables.AppDefinition;
import com.microsoft.skype.teams.storage.tables.RNApp;
import com.microsoft.skype.teams.storage.tables.RNBundle;

@Database(entities = {AppDefinition.class, RNApp.class, RNBundle.class}, version = 1)
public abstract class SdkDatabase extends RoomDatabase {
    public abstract AppDefinitionDao appDefinitionDao();
    public abstract RNAppsDao rNAppsDao();
    public abstract RNBundlesDao rnBundlesDao();

    private static SdkDatabase sDb = null;
    public static SdkDatabase get() {
        if (sDb == null)
            throw new RuntimeException("SdkDatabase is not initialized !");

        return sDb;
    }

    public static void initialize(Context context) {
        if (sDb != null)
            throw new RuntimeException("SdkDatabase must be initialized one and only once !");

        try {
            sDb = Room.databaseBuilder(context,
                    SdkDatabase.class, "TeamsSdkSimDb").build();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
