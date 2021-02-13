package com.teamssdksim.modules;

import android.content.Context;

import com.microsoft.skype.teams.logger.ILogger;
import com.microsoft.skype.teams.mobilemodules.IMobileModuleManager;
import com.microsoft.skype.teams.mobilemodules.IMobileModuleSyncManager;
import com.microsoft.skype.teams.mobilemodules.MobileModuleSyncManager;
import com.microsoft.skype.teams.storage.IExperimentationManager;
import com.microsoft.skype.teams.storage.dao.appdefinition.AppDefinitionDao;
import com.microsoft.teams.androidutils.tasks.ITaskRunner;
import com.microsoft.teams.core.app.ITeamsApplication;
import com.microsoft.teams.core.preferences.IPreferences;
import com.microsoft.teams.core.services.IScenarioManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MobileModuleSyncManagerModule {

    @Singleton
    @Provides
    static IMobileModuleSyncManager provideMobileModuleSyncManager(Context context,
                                                                   ITeamsApplication teamsApplication,
                                                                   ILogger logger,
                                                                   IScenarioManager scenarioManager,
                                                                   ITaskRunner taskRunner,
                                                                   IExperimentationManager experimentationManager,
                                                                   IPreferences preferences,
                                                                   IMobileModuleManager mobileModuleManager,
                                                                   AppDefinitionDao appDefinitionDao) {
        return new MobileModuleSyncManager(context,
                teamsApplication,
                logger,
                scenarioManager,
                taskRunner,
                experimentationManager,
                preferences,
                mobileModuleManager,
                appDefinitionDao);
    }
}