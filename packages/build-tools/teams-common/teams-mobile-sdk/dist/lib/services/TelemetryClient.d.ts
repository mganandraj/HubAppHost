import { UserBIEvent } from '../models/telemetry/UserBIEvent';
/**
 * Represents user's current Privacy Data Control(PDC) level. RN apps should log events corresponding to user's PDC level.
 * Refer https://domoreexp.visualstudio.com/DefaultCollection/Teamspace/_wiki/wikis/Teamspace.wiki/2791/Privacy-Data-Controls-in-Telemetry
 */
export declare enum UserPDCLevel {
    /**
     * Permissible Event PDC Level: Basic, Full, Required Service Data
     */
    Unset = 0,
    /**
     * Permissible Event PDC Level: Basic, Required Service Data
     */
    Basic = 1,
    /**
     * Also known as Optional. Permissible Event PDC Level: Basic, Full, Required Service Data
     */
    Full = 2,
    /**
     * Permissible Event PDC Level: Required Service Data
     */
    Required = 3
}
/** Provides helper methods to log Telemetry Events */
export declare class TelemetryClient {
    private static pdcLevelChangedHandler?;
    private static emitter;
    private static subscriber?;
    /**
     * Logs a user BI telemetry event to ARIA
     *
     * @param {UserBIEvent} event - user bi event data
     * @memberof TelemetryClientInterface
     */
    static logUserBIEvent(event: UserBIEvent): void;
    /**
     * Start a scenario
     *
     * @param {string} scenarioName - the name of the scenario to start
     * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
     * @returns {Promise<string>} - the Id of the scenario that has started
     * @memberof TelemetryClientInterface
     */
    static startScenario(scenarioName: string, databag?: {
        [key: string]: string | number | boolean;
    }): Promise<string>;
    /**
     * Start a scenario with reference to a parent scenario
     *
     * @param {string} scenarioName - the name of the scenario to start
     * @param {string} parentScenarioId - the Id of the parent scenario
     * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
     * @returns {Promise<string>} - the Id of the scenario that has started
     * @memberof TelemetryClientInterface
     */
    static startScenarioUnderParent(scenarioName: string, parentScenarioId: string, databag?: {
        [key: string]: string | number | boolean;
    }): Promise<string>;
    /**
     * Ends a scenario, and all parent scenarios, with an error.
     *
     * @param {string} scenarioId - the scenario to end
     * @param {string} scenarioErrorCode - The reason the scenario has been cancelled
     * @param {string} scenarioErrorReason - A longer description of the reason
     * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
     * @memberof TelemetryClientInterface
     */
    static endScenarioChainOnError(scenarioId: string, scenarioErrorCode: string, scenarioErrorReason: string, databag?: {
        [key: string]: string | number | boolean;
    }): void;
    /**
     * Ends a scenario with an error
     *
     * @param {string} scenarioId - the scenario to end
     * @param {string} scenarioErrorCode - The reason the scenario has been cancelled
     * @param {string} scenarioErrorReason - A longer description of the reason
     * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
     * @memberof TelemetryClientInterface
     */
    static endScenarioOnError(scenarioId: string, scenarioErrorCode: string, scenarioErrorReason: string, databag?: {
        [key: string]: string | number | boolean;
    }): void;
    /**
     * Ends a scenario, and all parent scenarios, with an incomplete
     *
     * @param {string} scenarioId - the scenario to end
     * @param {string} scenarioErrorCode - The reason the scenario has been cancelled
     * @param {string} scenarioErrorReason - A longer description of the reason
     * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
     * @memberof TelemetryClientInterface
     */
    static endScenarioChainOnIncomplete(scenarioId: string, scenarioErrorCode: string, scenarioErrorReason: string, databag?: {
        [key: string]: string | number | boolean;
    } | null): void;
    /**
     * Ends a scenario with an incomplete
     *
     * @param {string} scenarioId - the scenario to end
     * @param {string} scenarioErrorCode - The reason the scenario has been cancelled
     * @param {string} scenarioErrorReason - A longer description of the reason
     * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
     * @memberof TelemetryClientInterface
     */
    static endScenarioOnIncomplete(scenarioId: string, scenarioErrorCode: string, scenarioErrorReason: string, databag?: {
        [key: string]: string | number | boolean;
    }): void;
    /**
     * Ends a scenario, and all parent scenarios with a cancel.
     *
     * @param {string} scenarioId - the scenario to end
     * @param {string} scenarioErrorCode - The reason the scenario has been cancelled
     * @param {string} scenarioErrorReason - A longer description of the reason
     * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
     * @memberof TelemetryClientInterface
     */
    static endScenarioChainOnCancel(scenarioId: string, scenarioErrorCode: string, scenarioErrorReason: string, databag?: {
        [key: string]: string | number | boolean;
    }): void;
    /**
     * Ends a scenario with a cancel
     *
     * @param {string} scenarioId - the scenario to end
     * @param {string} scenarioErrorCode - The reason the scenario has been cancelled
     * @param {string} scenarioErrorReason - A longer description of the reason
     * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
     * @memberof TelemetryClientInterface
     */
    static endScenarioOnCancel(scenarioId: string, scenarioErrorCode: string, scenarioErrorReason: string, databag?: {
        [key: string]: string | number | boolean;
    }): void;
    /**
     * Ends a scenario with a success.
     *
     * @param {string} scenarioId - the scenario to end
     * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
     * @memberof TelemetryClientInterface
     */
    static endScenarioOnSuccess(scenarioId: string, databag?: {
        [key: string]: string | number | boolean;
    }): void;
    /**
     * Ends a scenario, and all parent scenarios, with a success.
     *
     * @param {string} scenarioId - the scenario to end
     * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
     * @memberof TelemetryClientInterface
     */
    static endScenarioChainOnSuccess(scenarioId: string, databag?: {
        [key: string]: string | number | boolean;
    }): void;
    /**
     * Registers a callback/listener for user's PDC level changes.
     * Note: Only a single listener can be registered at a time. Please ensure to remove existing
     * listener by calling removeHandlerForPDCLevelChange before registering another listener.
     *
     * @param {UserPDCLevel} handler - method to be called when user's PDC level change event is received
     * @memberof TelemetryClientInterface
     */
    static registerHandlerForPDCLevelChange(handler: (pdcLevel: UserPDCLevel) => void): void;
    /**
     * Removes callback/listener registered for user's PDC level changes
     */
    static removeHandlerForPDCLevelChange(): void;
    /**
     * Calls PDC level change handler method if registered
     *
     * @param {any} event - dictionary that contains user's current PDC level
     */
    private static onPDCLevelChanged;
}
