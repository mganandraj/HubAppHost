
/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.core.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.skype.teams.logger.ILogger;
/*
import com.microsoft.skype.teams.services.diagnostics.IUserBITelemetryManager;
import com.microsoft.skype.teams.storage.IExperimentationManager;
import com.microsoft.skype.teams.utilities.BaseDebugUtilities;
import com.microsoft.teams.core.diagnostics.IAppStartScenario;
import com.microsoft.teams.core.injection.AppDataFactory;
import com.microsoft.teams.core.injection.UserDataFactory;
import com.microsoft.teams.core.services.IScenarioManager;
import com.microsoft.teams.core.services.configuration.IUserConfiguration;
*/

/**
 * Interface for Teams application
 */
public interface ITeamsApplication {
    @Nullable
    Activity getActivity();

    /**
     * Gets the instance of {@link AppDataFactory}.
     *
     * @return the {@link AppDataFactory} instance.
     */
    /*@NonNull
    AppDataFactory getAppDataFactory();
*/
    /**
     * Gets the stepId of the ScenarioContext started at App launch
     *
     * @return stepId
     */
    @Nullable
    String getAppLaunchStepId();

    /**
     * Gets instance of {@link Application}
     * @return {@link Application} instance
     */
    @NonNull
    Application getApplication();

    @NonNull
    Context getApplicationContext();

    /*@Nullable
    IAppStartScenario getAppStartScenario();*/

    @Nullable
    String getCurrentCallDescription();

    /**
     * Get the current {@link BaseDebugUtilities} for this specific build type.
     * @return The current {@link BaseDebugUtilities} for this specific build type.
     */
/*
    @NonNull
    BaseDebugUtilities getCurrentDebugUtilities();
*/

    /**
     * Gets the default instance of {@link IExperimentationManager} - the {@link IExperimentationManager} instance
     * that is not bound to a specific user.
     *
     * @return the default {@link IExperimentationManager} instance.
     */
/*
    @NonNull
    @Deprecated
    IExperimentationManager getDefaultExperimentationManager();
*/

    /**
     * Gets a default instance of {@link IScenarioManager}.
     *
     * @return the default {@link IScenarioManager} instance.
     */
/*
    @NonNull
    @Deprecated
    IScenarioManager getDefaultScenarioManager();
*/

    /**
     * Gets a default instance of {@link IUserBITelemetryManager}.
     *
     * @return the default {@link IUserBITelemetryManager} instance.
     */
/*
    @NonNull
    @Deprecated
    IUserBITelemetryManager getDefaultUserBITelemetryManager();
*/

    /**
     * Discontinue the use of the IMEI in favor of a randomly generated ID, this API returns
     * a unique random string in the life cycle of teams app until it is uninstalled or data is cleaned.
     * https://developer.android.com/training/articles/user-data-ids
     * This API is designed to replace IMEI.
     * if you just want an unique id in the whole life of Teams app, please use API getFakeAndroidId()
     *
     * @return fake IMEI
     */
    @NonNull
    String getFakeIMEI();

    /**
     * Discontinue the use of the ANDROID_ID in favor of a randomly generated ID, this API returns
     * a unique random string in the life cycle of teams app until it is uninstalled or data is cleaned.
     * https://developer.android.com/training/articles/user-data-ids
     * @return fake android id
     */
    @NonNull
    String getFakeAndroidId();

    /**
     * Gets the an instance of {@link IExperimentationManager}. If the specified userObjectId is not null,
     * the return value is the {@link IExperimentationManager} that is bound to that userObjectId. If the
     * specified userObjectId is null, the return value is either the {@link IExperimentationManager} bound
     * to the default user or, when there is no signed in user, the {@link IExperimentationManager} returned
     * by {@link #getDefaultExperimentationManager()}.
     *
     * @param userObjectId specifies the specific user to obtain an {@link IExperimentationManager} for.
     * @return an {@link IExperimentationManager} instance.
     */
/*
    @NonNull
    IExperimentationManager getExperimentationManager(@Nullable String userObjectId);
*/

    /**
     * Gets the an instance of {@link ILogger}. If the specified userObjectId is not null,
     * the return value is the {@link ILogger} that is bound to that userObjectId. If the
     * specified userObjectId is null, the return value is either the {@link ILogger} bound
     * to the default user or, when there is no signed in user, the default {@link ILogger}
     * is returned.
     *
     * @param userObjectId specifies the specific user to obtain an {@link ILogger} for.
     * @return an {@link ILogger} instance.
     */
    @NonNull
    ILogger getLogger(@Nullable String userObjectId);

    @Nullable
    int getMsalConsumerConfigId();

    @Nullable
    int getMsalEnterpriseConfigId();

    /**
     * Gets the an instance of {@link IScenarioManager}. If the specified userObjectId is not null,
     * the return value is the {@link IScenarioManager} that is bound to that userObjectId. If the
     * specified userObjectId is null, the return value is either the {@link IScenarioManager} bound
     * to the default user or, when there is no signed in user, the {@link IScenarioManager} returned
     * by {@link #getDefaultScenarioManager()}.
     *
     * @param userObjectId specifies the specific user to obtain an {@link IScenarioManager} for.
     * @return an {@link IScenarioManager} instance.
     */
/*
    @NonNull
    IScenarioManager getScenarioManager(@Nullable String userObjectId);
*/

    /**
     * Gets the an instance of {@link IUserBITelemetryManager}. If the specified userObjectId is not null,
     * the return value is the {@link IUserBITelemetryManager} that is bound to that userObjectId. If the
     * specified userObjectId is null, the return value is either the {@link IUserBITelemetryManager} bound
     * to the default user or, when there is no signed in user, the {@link IUserBITelemetryManager} returned
     * by {@link #getDefaultUserBITelemetryManager()}.
     *
     * @param userObjectId specifies the specific user to obtain an {@link IUserBITelemetryManager} for.
     * @return an {@link IUserBITelemetryManager} instance.
     */
/*
    @NonNull
    IUserBITelemetryManager getUserBITelemetryManager(@Nullable String userObjectId);
*/

    /**
     * Gets the instance of {@link IUserConfiguration}. If the specified userObjectId is not null,
     * the return value is the {@link IUserConfiguration} that is bound to that userObjectId. If the
     * specified userObjectId is null, the return value is either the {@link IUserConfiguration} bound
     * to the default user or, when there is no signed in user, the default {@link IUserConfiguration}
     * is returned.
     *
     * @param userObjectId specifies the specific user to obtain an {@link IUserConfiguration} for.
     * @return an {@link IUserConfiguration} instance.
     */
/*
    @NonNull
    IUserConfiguration getUserConfiguration(@Nullable String userObjectId);
*/

    /**
     * Gets the instance of {@link UserDataFactory} for the current user.
     *
     * @return the {@link UserDataFactory} instance for the current user or
     * null if there is none.
     */
/*
    @Nullable
    UserDataFactory getUserDataFactory();
*/

    /**
     * Gets the instance of {@link UserDataFactory} for the specified user.
     *
     * @return the {@link UserDataFactory} instance for the specified user.
     */
/*
    @NonNull
    UserDataFactory getUserDataFactory(@NonNull String userObjectId);
*/

    @Nullable
    String getUserId();

    boolean isApplicationLaunch();

    /**
     * Returns true if car mode is enabled for the given userObjectID
     *
     * @param context current context
     * @param userObjectID specifies the specific user to obtain an {@link IExperimentationManager} for.
     * @return true if the device car mode is enabled, false otherwise.
     */
    // boolean isCarMode(@NonNull Context context, @Nullable String userObjectID);

    void onANR();

    /**
     * Resets the stepId of the AppLaunch ScenarioContext to null
     */
    void resetAppLaunchScenarioId();

    /**
     * Relaunching the application with a scheduled alarm manager
     *
     * @param restartAfter time in mills seconds after to schedule restart
     */
    void reLaunchAppScheduled(long restartAfter);

    /**
     * Relaunch the application after the specified delay with the specified intent.
     */
    void reLaunchAppScheduled(long restartAfter, @NonNull Intent launchIntent);

    /**
     * Returns {@link LaunchInfo} for this instance of Application. It provides the information about
     * the type of launch. HOT/COLD/WARM
     * @return LaunchInfo
     */
/*
    @Nullable
    LaunchInfo launchInfo();
*/
}
