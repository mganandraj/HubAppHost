"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.TelemetryClient = exports.UserPDCLevel = void 0;
const react_native_1 = require("react-native");
const ArgumentsValidator_1 = require("../../common/utilities/ArgumentsValidator");
const UserBIEvent_1 = require("../models/telemetry/UserBIEvent");
const SdkNativeModules_1 = require("./SdkNativeModules");
const Events = {
    ON_PDC_LEVEL_CHANGED: 'onPDCLevelChanged'
};
/**
 * Represents user's current Privacy Data Control(PDC) level. RN apps should log events corresponding to user's PDC level.
 * Refer https://domoreexp.visualstudio.com/DefaultCollection/Teamspace/_wiki/wikis/Teamspace.wiki/2791/Privacy-Data-Controls-in-Telemetry
 */
var UserPDCLevel;
(function (UserPDCLevel) {
    /**
     * Permissible Event PDC Level: Basic, Full, Required Service Data
     */
    UserPDCLevel[UserPDCLevel["Unset"] = 0] = "Unset";
    /**
     * Permissible Event PDC Level: Basic, Required Service Data
     */
    UserPDCLevel[UserPDCLevel["Basic"] = 1] = "Basic";
    /**
     * Also known as Optional. Permissible Event PDC Level: Basic, Full, Required Service Data
     */
    UserPDCLevel[UserPDCLevel["Full"] = 2] = "Full";
    /**
     * Permissible Event PDC Level: Required Service Data
     */
    UserPDCLevel[UserPDCLevel["Required"] = 3] = "Required";
})(UserPDCLevel = exports.UserPDCLevel || (exports.UserPDCLevel = {}));
/** Provides helper methods to log Telemetry Events */
class TelemetryClient {
    /**
     * Logs a user BI telemetry event to ARIA
     *
     * @param {UserBIEvent} event - user bi event data
     * @memberof TelemetryClientInterface
     */
    static logUserBIEvent(event) {
        ArgumentsValidator_1.ArgumentsValidator.warnIfNullOrUndefined('event', event);
        ArgumentsValidator_1.ArgumentsValidator.warnIfNullOrUndefined('event.eventName', event.eventName);
        if (event.eventName === UserBIEvent_1.UserBIEventName.PANEL_ACTION) {
            ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('event.moduleName', event.moduleName);
        }
        else if (event.eventName === UserBIEvent_1.UserBIEventName.PANEL_VIEW) {
            ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('event.panelType', event.panelType);
        }
        SdkNativeModules_1.NativeTelemetryClient.logUserBIEvent(event);
    }
    /**
     * Start a scenario
     *
     * @param {string} scenarioName - the name of the scenario to start
     * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
     * @returns {Promise<string>} - the Id of the scenario that has started
     * @memberof TelemetryClientInterface
     */
    static startScenario(scenarioName, databag) {
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioName', scenarioName);
        return SdkNativeModules_1.NativeTelemetryClient.startScenario(scenarioName, databag === undefined ? null : databag);
    }
    /**
     * Start a scenario with reference to a parent scenario
     *
     * @param {string} scenarioName - the name of the scenario to start
     * @param {string} parentScenarioId - the Id of the parent scenario
     * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
     * @returns {Promise<string>} - the Id of the scenario that has started
     * @memberof TelemetryClientInterface
     */
    static startScenarioUnderParent(scenarioName, parentScenarioId, databag) {
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioName', scenarioName);
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('parentScenarioId', parentScenarioId);
        return SdkNativeModules_1.NativeTelemetryClient.startScenarioUnderParent(scenarioName, parentScenarioId, databag === undefined ? null : databag);
    }
    /**
     * Ends a scenario, and all parent scenarios, with an error.
     *
     * @param {string} scenarioId - the scenario to end
     * @param {string} scenarioErrorCode - The reason the scenario has been cancelled
     * @param {string} scenarioErrorReason - A longer description of the reason
     * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
     * @memberof TelemetryClientInterface
     */
    static endScenarioChainOnError(scenarioId, scenarioErrorCode, scenarioErrorReason, databag) {
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioId', scenarioId);
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorCode', scenarioErrorCode);
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorReason', scenarioErrorReason);
        SdkNativeModules_1.NativeTelemetryClient.endScenarioChainOnError(scenarioId, scenarioErrorCode, scenarioErrorReason, databag === undefined ? null : databag);
    }
    /**
     * Ends a scenario with an error
     *
     * @param {string} scenarioId - the scenario to end
     * @param {string} scenarioErrorCode - The reason the scenario has been cancelled
     * @param {string} scenarioErrorReason - A longer description of the reason
     * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
     * @memberof TelemetryClientInterface
     */
    static endScenarioOnError(scenarioId, scenarioErrorCode, scenarioErrorReason, databag) {
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioId', scenarioId);
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorCode', scenarioErrorCode);
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorReason', scenarioErrorReason);
        SdkNativeModules_1.NativeTelemetryClient.endScenarioOnError(scenarioId, scenarioErrorCode, scenarioErrorReason, databag === undefined ? null : databag);
    }
    /**
     * Ends a scenario, and all parent scenarios, with an incomplete
     *
     * @param {string} scenarioId - the scenario to end
     * @param {string} scenarioErrorCode - The reason the scenario has been cancelled
     * @param {string} scenarioErrorReason - A longer description of the reason
     * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
     * @memberof TelemetryClientInterface
     */
    static endScenarioChainOnIncomplete(scenarioId, scenarioErrorCode, scenarioErrorReason, databag) {
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioId', scenarioId);
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorCode', scenarioErrorCode);
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorReason', scenarioErrorReason);
        SdkNativeModules_1.NativeTelemetryClient.endScenarioChainOnIncomplete(scenarioId, scenarioErrorCode, scenarioErrorReason, databag === undefined ? null : databag);
    }
    /**
     * Ends a scenario with an incomplete
     *
     * @param {string} scenarioId - the scenario to end
     * @param {string} scenarioErrorCode - The reason the scenario has been cancelled
     * @param {string} scenarioErrorReason - A longer description of the reason
     * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
     * @memberof TelemetryClientInterface
     */
    static endScenarioOnIncomplete(scenarioId, scenarioErrorCode, scenarioErrorReason, databag) {
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioId', scenarioId);
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorCode', scenarioErrorCode);
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorReason', scenarioErrorReason);
        SdkNativeModules_1.NativeTelemetryClient.endScenarioOnIncomplete(scenarioId, scenarioErrorCode, scenarioErrorReason, databag === undefined ? null : databag);
    }
    /**
     * Ends a scenario, and all parent scenarios with a cancel.
     *
     * @param {string} scenarioId - the scenario to end
     * @param {string} scenarioErrorCode - The reason the scenario has been cancelled
     * @param {string} scenarioErrorReason - A longer description of the reason
     * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
     * @memberof TelemetryClientInterface
     */
    static endScenarioChainOnCancel(scenarioId, scenarioErrorCode, scenarioErrorReason, databag) {
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioId', scenarioId);
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorCode', scenarioErrorCode);
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorReason', scenarioErrorReason);
        SdkNativeModules_1.NativeTelemetryClient.endScenarioChainOnCancel(scenarioId, scenarioErrorCode, scenarioErrorReason, databag === undefined ? null : databag);
    }
    /**
     * Ends a scenario with a cancel
     *
     * @param {string} scenarioId - the scenario to end
     * @param {string} scenarioErrorCode - The reason the scenario has been cancelled
     * @param {string} scenarioErrorReason - A longer description of the reason
     * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
     * @memberof TelemetryClientInterface
     */
    static endScenarioOnCancel(scenarioId, scenarioErrorCode, scenarioErrorReason, databag) {
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioId', scenarioId);
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorCode', scenarioErrorCode);
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorReason', scenarioErrorReason);
        SdkNativeModules_1.NativeTelemetryClient.endScenarioOnCancel(scenarioId, scenarioErrorCode, scenarioErrorReason, databag === undefined ? null : databag);
    }
    /**
     * Ends a scenario with a success.
     *
     * @param {string} scenarioId - the scenario to end
     * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
     * @memberof TelemetryClientInterface
     */
    static endScenarioOnSuccess(scenarioId, databag) {
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioId', scenarioId);
        SdkNativeModules_1.NativeTelemetryClient.endScenarioOnSuccess(scenarioId, databag === undefined ? null : databag);
    }
    /**
     * Ends a scenario, and all parent scenarios, with a success.
     *
     * @param {string} scenarioId - the scenario to end
     * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
     * @memberof TelemetryClientInterface
     */
    static endScenarioChainOnSuccess(scenarioId, databag) {
        ArgumentsValidator_1.ArgumentsValidator.warnIfEmptyOrUndefined('scenarioId', scenarioId);
        SdkNativeModules_1.NativeTelemetryClient.endScenarioChainOnSuccess(scenarioId, databag === undefined ? null : databag);
    }
    /**
     * Registers a callback/listener for user's PDC level changes.
     * Note: Only a single listener can be registered at a time. Please ensure to remove existing
     * listener by calling removeHandlerForPDCLevelChange before registering another listener.
     *
     * @param {UserPDCLevel} handler - method to be called when user's PDC level change event is received
     * @memberof TelemetryClientInterface
     */
    static registerHandlerForPDCLevelChange(handler) {
        if (!this.pdcLevelChangedHandler) {
            TelemetryClient.emitter = new react_native_1.NativeEventEmitter(SdkNativeModules_1.NativeTelemetryClient);
            TelemetryClient.subscriber = TelemetryClient.emitter.addListener(Events.ON_PDC_LEVEL_CHANGED, TelemetryClient.onPDCLevelChanged);
        }
        this.pdcLevelChangedHandler = handler;
    }
    /**
     * Removes callback/listener registered for user's PDC level changes
     */
    static removeHandlerForPDCLevelChange() {
        if (TelemetryClient.subscriber) {
            TelemetryClient.subscriber.remove();
            TelemetryClient.subscriber = undefined;
            this.pdcLevelChangedHandler = undefined;
        }
    }
}
exports.TelemetryClient = TelemetryClient;
/**
 * Calls PDC level change handler method if registered
 *
 * @param {any} event - dictionary that contains user's current PDC level
 */
TelemetryClient.onPDCLevelChanged = (event) => {
    if (TelemetryClient.pdcLevelChangedHandler && event.userPDCLevel) {
        TelemetryClient.pdcLevelChangedHandler(event.userPDCLevel);
    }
};
