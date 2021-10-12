package com.microsoft.teams.logger.constants;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Listing of all of the Aria Scenario event property keys
 */
@Retention(RetentionPolicy.SOURCE)
@StringDef({ScenarioPropKeys.INSTANCE_ID,
        ScenarioPropKeys.NAME,
        ScenarioPropKeys.STEP,
        ScenarioPropKeys.STATUS,
        ScenarioPropKeys.DELTA,
        ScenarioPropKeys.STATUS_REASON,
        ScenarioPropKeys.STATUS_CODE,
        ScenarioPropKeys.STEP_TIME_TAKEN,
        ScenarioPropKeys.SCENARIO_TIME_TAKEN,
        ScenarioPropKeys.FRAMERATE,
        ScenarioPropKeys.APPSTATUS,
        ScenarioPropKeys.NETWORK_STATUS,
        ScenarioPropKeys.STEP_ID,
        ScenarioPropKeys.CORRELATION_ID,
        ScenarioPropKeys.IS_SAMPLED,
        ScenarioPropKeys.MESSAGE_ORIGIN,
        ScenarioPropKeys.DEPENDENCY_STRING,
        ScenarioPropKeys.METADATA,
        ScenarioPropKeys.TIME_TAKEN,
        ScenarioPropKeys.DATA_BAG1,
        ScenarioPropKeys.CALL_DATA_BAG,
        ScenarioPropKeys.SOURCE,
        ScenarioPropKeys.NETWORK_QUALITY,
        ScenarioPropKeys.NETWORK_TYPE,
})
public @interface ScenarioPropKeys {
    String INSTANCE_ID = "Scenario.InstanceId";
    String NAME = "Scenario.Name";
    String STEP = "Scenario.Step";
    String STATUS = "Scenario.Status";
    String DELTA = "Scenario.Delta";
    String STATUS_REASON = "Scenario.StatusReason4";
    String STATUS_CODE = "Scenario.StatusCode";
    String STEP_TIME_TAKEN = "Scenario_StepTimeTaken";
    String SCENARIO_TIME_TAKEN = "Scenario.ScenarioTimeTaken";
    String FRAMERATE = "frameRate";
    String APPSTATUS = "appStatus";
    String NETWORK_STATUS = "Scenario.NetworkStatus";
    String STEP_ID = "Scenario.StepId";
    String CORRELATION_ID = "Scenario.CorrelationId";
    String IS_SAMPLED = "Scenario.isSampled";
    String MESSAGE_ORIGIN = "Scenario.ScenarioMessageOrigin";
    String DEPENDENCY_STRING = "Scenario.DependencyString";
    String METADATA = "Scenario.MetaData6";
    String TIME_TAKEN = "Scenario_ScenarioTimeTaken";
    String DATA_BAG1 = "Scenario_Databag1";
    String CALL_DATA_BAG = "Scenario_CallDataBag";
    String USER_RING = "User.Ring";
    String USER_TYPE = "User.Type";
    String EXPERIMENTATION_IDS = "AppInfo.ExpIds";
    String SOURCE = "source";
    String NETWORK_QUALITY = "Scenario.NetworkQuality";
    String NETWORK_TYPE = "Scenario.NetworkType";
    String USER_INFO_LICENSE_TYPE = "UserInfo.LicenseType";
}
