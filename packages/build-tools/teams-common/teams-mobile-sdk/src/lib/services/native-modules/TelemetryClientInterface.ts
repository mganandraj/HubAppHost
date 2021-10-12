import { EventSubscriptionVendor } from 'react-native';
import { UserBIEvent } from '../../models/telemetry/UserBIEvent';
import { UserPDCLevel } from '../../services/TelemetryClient';

export interface TelemetryClientInterface extends EventSubscriptionVendor {

  setAriaTenant (tenantToken: string);

  /**
   * logs a user BI telemetry event to ARIA
   *
   * @param {UserBIEvent} event - user bi event data
   * @memberof TelemetryClientInterface
   */
  logUserBIEvent (event: UserBIEvent);

  /**
   * Start a scenario
   *
   * @param {string} scenarioName - the name of the scenario to start
   * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
   * @returns {Promise<string>} - the Id of the scenario that has started
   * @memberof TelemetryClientInterface
   */
  startScenario (scenarioName: string, databag: { [key: string]: string | number | boolean } | null): Promise<string>;

  /**
   * Start a scenario with reference to a parent scenario
   *
   * @param {string} scenarioName - the name of the scenario to start
   * @param {string} parentScenarioId - the Id of the parent scenario
   * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
   * @returns {Promise<string>} - the Id of the scenario that has started
   * @memberof TelemetryClientInterface
   */
  startScenarioUnderParent (scenarioName: string, parentScenarioId: string, databag: { [key: string]: string | number | boolean } | null): Promise<string>;

  /**
   * Ends a scenario, and all parent scenarios, with an error.
   *
   * @param {string} scenarioId - the scenario to end
   * @param {string} scenarioErrorCode - The reason the scenario has been cancelled
   * @param {string} scenarioErrorReason - A longer description of the reason
   * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
   * @memberof TelemetryClientInterface
   */
  endScenarioChainOnError (scenarioId: string, scenarioErrorCode: string, scenarioErrorReason: string, databag: { [key: string]: string | number | boolean } | null);

  /**
   * Ends a scenario with an error
   *
   * @param {string} scenarioId - the scenario to end
   * @param {string} scenarioErrorCode - The reason the scenario has been cancelled
   * @param {string} scenarioErrorReason - A longer description of the reason
   * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
   * @memberof TelemetryClientInterface
   */
  endScenarioOnError (scenarioId: string, scenarioErrorCode: string, scenarioErrorReason: string, databag: { [key: string]: string | number | boolean } | null);

  /**
   * Ends a scenario, and all parent scenarios, with an incomplete
   *
   * @param {string} scenarioId - the scenario to end
   * @param {string} scenarioErrorCode - The reason the scenario has been cancelled
   * @param {string} scenarioErrorReason - A longer description of the reason
   * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
   * @memberof TelemetryClientInterface
   */
  endScenarioChainOnIncomplete (scenarioId: string, scenarioErrorCode: string, scenarioErrorReason: string, databag: { [key: string]: string | number | boolean } | null);

  /**
   * Ends a scenario with an incomplete
   *
   * @param {string} scenarioId - the scenario to end
   * @param {string} scenarioErrorCode - The reason the scenario has been cancelled
   * @param {string} scenarioErrorReason - A longer description of the reason
   * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
   * @memberof TelemetryClientInterface
   */
  endScenarioOnIncomplete (scenarioId: string, scenarioErrorCode: string, scenarioErrorReason: string, databag: { [key: string]: string | number | boolean } | null);

  /**
   * Ends a scenario, and all parent scenarios with a cancel.
   *
   * @param {string} scenarioId - the scenario to end
   * @param {string} scenarioErrorCode - The reason the scenario has been cancelled
   * @param {string} scenarioErrorReason - A longer description of the reason
   * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
   * @memberof TelemetryClientInterface
   */
  endScenarioChainOnCancel (scenarioId: string, scenarioErrorCode: string, scenarioErrorReason: string, databag: { [key: string]: string | number | boolean } | null);

  /**
   * Ends a scenario with a cancel
   *
   * @param {string} scenarioId - the scenario to end
   * @param {string} scenarioErrorCode - The reason the scenario has been cancelled
   * @param {string} scenarioErrorReason - A longer description of the reason
   * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
   * @memberof TelemetryClientInterface
   */
  endScenarioOnCancel (scenarioId: string, scenarioErrorCode: string, scenarioErrorReason: string, databag: { [key: string]: string | number | boolean } | null);

  /**
   * Ends a scenario with a success.
   *
   * @param {string} scenarioId - the scenario to end
   * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
   * @memberof TelemetryClientInterface
   */
  endScenarioOnSuccess (scenarioId: string, databag: { [key: string]: string | number | boolean } | null);

  /**
   * Ends a scenario, and all parent scenarios, with a success.
   *
   * @param {string} scenarioId - the scenario to end
   * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
   * @memberof TelemetryClientInterface
   */
  endScenarioChainOnSuccess (scenarioId: string, databag: { [key: string]: string | number | boolean } | null);

  /**
   * Registers a handler that is called when user's PDC level changes
   *
   * @param {UserPDCLevel} handler - method to be called when user's PDC level change event is received
   * @memberof TelemetryClientInterface
   */
  registerHandlerForPDCLevelChange(handler: (pdcLevel: UserPDCLevel) => void);

  /**
   * Removes the handler registered for PDC level change event
   */
  removeHandlerForPDCLevelChange();
}
