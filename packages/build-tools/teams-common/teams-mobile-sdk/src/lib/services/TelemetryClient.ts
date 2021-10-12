import { EmitterSubscription, NativeEventEmitter } from 'react-native';
import { ArgumentsValidator } from '../../common/utilities/ArgumentsValidator';
import { UserBIEvent, UserBIEventName } from '../models/telemetry/UserBIEvent';
import { NativeTelemetryClient } from './SdkNativeModules';

const Events = {
  ON_PDC_LEVEL_CHANGED: 'onPDCLevelChanged'
};

/**
 * Represents user's current Privacy Data Control(PDC) level. RN apps should log events corresponding to user's PDC level. 
 * Refer https://domoreexp.visualstudio.com/DefaultCollection/Teamspace/_wiki/wikis/Teamspace.wiki/2791/Privacy-Data-Controls-in-Telemetry
 */
export enum UserPDCLevel {
  /**
   * Permissible Event PDC Level: Basic, Full, Required Service Data
   */
  Unset, 
  /**
   * Permissible Event PDC Level: Basic, Required Service Data
   */
  Basic, 
  /**
   * Also known as Optional. Permissible Event PDC Level: Basic, Full, Required Service Data
   */
  Full,
  /**
   * Permissible Event PDC Level: Required Service Data
   */
  Required
}

/** Provides helper methods to log Telemetry Events */
export class TelemetryClient {
  private static pdcLevelChangedHandler?: (pdcLevel: UserPDCLevel) => void;
  private static emitter: NativeEventEmitter;
  private static subscriber?: EmitterSubscription;

  /**
   * Logs a user BI telemetry event to ARIA
   *
   * @param {UserBIEvent} event - user bi event data
   * @memberof TelemetryClientInterface
   */
  public static logUserBIEvent (event: UserBIEvent) {
    ArgumentsValidator.warnIfNullOrUndefined('event',event);
    ArgumentsValidator.warnIfNullOrUndefined('event.eventName',event.eventName);
    if (event.eventName === UserBIEventName.PANEL_ACTION) {
      ArgumentsValidator.warnIfEmptyOrUndefined('event.moduleName', event.moduleName);
    }else if (event.eventName === UserBIEventName.PANEL_VIEW) {
      ArgumentsValidator.warnIfEmptyOrUndefined('event.panelType', event.panelType);
    }

    NativeTelemetryClient.logUserBIEvent(event);
  }

  /**
   * Start a scenario
   *
   * @param {string} scenarioName - the name of the scenario to start
   * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
   * @returns {Promise<string>} - the Id of the scenario that has started
   * @memberof TelemetryClientInterface
   */
  public static startScenario (scenarioName: string, databag?: { [key: string]: string | number | boolean; }): Promise<string> {
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioName', scenarioName);
    return NativeTelemetryClient.startScenario(scenarioName, databag === undefined ? null : databag);
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
  public static startScenarioUnderParent (scenarioName: string, parentScenarioId: string, databag?: { [key: string]: string | number | boolean; }): Promise<string> {
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioName', scenarioName);
    ArgumentsValidator.warnIfEmptyOrUndefined('parentScenarioId', parentScenarioId);
    return NativeTelemetryClient.startScenarioUnderParent(scenarioName, parentScenarioId, databag === undefined ? null : databag);
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
  public static endScenarioChainOnError (scenarioId: string, scenarioErrorCode: string, scenarioErrorReason: string, databag?: { [key: string]: string | number | boolean; }) {
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioId', scenarioId);
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorCode', scenarioErrorCode);
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorReason', scenarioErrorReason);
    NativeTelemetryClient.endScenarioChainOnError(scenarioId, scenarioErrorCode, scenarioErrorReason, databag === undefined ? null : databag);
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
  public static endScenarioOnError (scenarioId: string, scenarioErrorCode: string, scenarioErrorReason: string, databag?: { [key: string]: string | number | boolean; }) {
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioId', scenarioId);
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorCode', scenarioErrorCode);
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorReason', scenarioErrorReason);
    NativeTelemetryClient.endScenarioOnError(scenarioId, scenarioErrorCode, scenarioErrorReason, databag === undefined ? null : databag);
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
  public static endScenarioChainOnIncomplete (scenarioId: string, scenarioErrorCode: string, scenarioErrorReason: string, databag?: { [key: string]: string | number | boolean; } | null) {
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioId', scenarioId);
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorCode', scenarioErrorCode);
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorReason', scenarioErrorReason);
    NativeTelemetryClient.endScenarioChainOnIncomplete(scenarioId, scenarioErrorCode, scenarioErrorReason, databag === undefined ? null : databag);
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
  public static endScenarioOnIncomplete (scenarioId: string, scenarioErrorCode: string, scenarioErrorReason: string, databag?: { [key: string]: string | number | boolean; }) {
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioId', scenarioId);
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorCode', scenarioErrorCode);
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorReason', scenarioErrorReason);
    NativeTelemetryClient.endScenarioOnIncomplete(scenarioId, scenarioErrorCode, scenarioErrorReason, databag === undefined ? null : databag);
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
  public static endScenarioChainOnCancel (scenarioId: string, scenarioErrorCode: string, scenarioErrorReason: string, databag?: { [key: string]: string | number | boolean; }) {
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioId', scenarioId);
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorCode', scenarioErrorCode);
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorReason', scenarioErrorReason);
    NativeTelemetryClient.endScenarioChainOnCancel(scenarioId, scenarioErrorCode, scenarioErrorReason, databag === undefined ? null : databag);
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
  public static endScenarioOnCancel (scenarioId: string, scenarioErrorCode: string, scenarioErrorReason: string, databag?: { [key: string]: string | number | boolean; }) {
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioId', scenarioId);
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorCode', scenarioErrorCode);
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioErrorReason', scenarioErrorReason);
    NativeTelemetryClient.endScenarioOnCancel(scenarioId, scenarioErrorCode, scenarioErrorReason, databag === undefined ? null : databag);
  }

  /**
   * Ends a scenario with a success.
   *
   * @param {string} scenarioId - the scenario to end
   * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
   * @memberof TelemetryClientInterface
   */
  public static endScenarioOnSuccess (scenarioId: string, databag?: { [key: string]: string | number | boolean; }) {
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioId', scenarioId);
    NativeTelemetryClient.endScenarioOnSuccess(scenarioId, databag === undefined ? null : databag);
  }

  /**
   * Ends a scenario, and all parent scenarios, with a success.
   *
   * @param {string} scenarioId - the scenario to end
   * @param {{ [key: string]: string}} databag - additional context surrounding the scenario
   * @memberof TelemetryClientInterface
   */
  public static endScenarioChainOnSuccess (scenarioId: string, databag?: { [key: string]: string | number | boolean; }) {
    ArgumentsValidator.warnIfEmptyOrUndefined('scenarioId', scenarioId);
    NativeTelemetryClient.endScenarioChainOnSuccess(scenarioId, databag === undefined ? null : databag);
  }

  /**
   * Registers a callback/listener for user's PDC level changes.
   * Note: Only a single listener can be registered at a time. Please ensure to remove existing
   * listener by calling removeHandlerForPDCLevelChange before registering another listener.
   *
   * @param {UserPDCLevel} handler - method to be called when user's PDC level change event is received
   * @memberof TelemetryClientInterface
   */
  public static registerHandlerForPDCLevelChange(handler: (pdcLevel: UserPDCLevel) => void) {
    if (!this.pdcLevelChangedHandler) {
      TelemetryClient.emitter = new NativeEventEmitter(NativeTelemetryClient);
      TelemetryClient.subscriber = TelemetryClient.emitter.addListener(Events.ON_PDC_LEVEL_CHANGED, TelemetryClient.onPDCLevelChanged);
    }
    this.pdcLevelChangedHandler = handler;
  }

  /**
   * Removes callback/listener registered for user's PDC level changes
   */
  public static removeHandlerForPDCLevelChange() {
    if (TelemetryClient.subscriber) {
      TelemetryClient.subscriber.remove();
      TelemetryClient.subscriber = undefined;
      this.pdcLevelChangedHandler = undefined;
    }
  }

  /**
   * Calls PDC level change handler method if registered
   *
   * @param {any} event - dictionary that contains user's current PDC level
   */
  private static onPDCLevelChanged = (event: any) => {
    if (TelemetryClient.pdcLevelChangedHandler && event.userPDCLevel) {
      TelemetryClient.pdcLevelChangedHandler(event.userPDCLevel);
    }
  }
}
