import { TraceLogger } from '../../lib/services/diagnostics/TraceLogger';
import { ArgumentException } from '../errors/ArgumentException';

export class ArgumentsValidator {
  private static readonly LOG_TAG: string = 'ArgumentsValidator';

  /**
   * Asserts if passed in value is not undefined or null
   *
   * @static
   * @param {string} argumentName - name of the paramter under assertion
   * @param {*} argumentValue - value of the parameter to assert
   * @memberof ArgumentsValidator
   */
  public static assertNotNullOrUndefined (argumentName: string, argumentValue?: any) {
    if (!argumentValue) {
      throw new ArgumentException(argumentName + ' is null or undefined.');
    }
  }

  /**
   * Asserts if passed in string value is not undefined, null or empty
   *
   * @static
   * @param argumentName {string} - name of the paramter under assertion
   * @param argumentValue {string} - value of the parameter to assert
   * @memberof ArgumentsValidator
   */
  public static assertNonEmptyString (argumentName: string, argumentValue?: string) {
    if (!argumentValue || argumentValue.length === 0 || argumentValue.trim().length === 0) {
      throw new ArgumentException(argumentName + ' is null or undefined.');
    }
  }

  /**
   * Checks if passed in string value is undefined or null and shows warning
   *
   * @static
   * @param argumentName {string} - name of the paramter under assertion
   * @param argumentValue {string} - value of the parameter to assert
   * @memberof ArgumentsValidator
   */
  public static warnIfNullOrUndefined (argumentName: string, argumentValue?: any): boolean {
    if (!argumentValue) {
      const message:string = argumentName + ' is null or undefined.';
      console.warn(message);
      TraceLogger.logWarning(ArgumentsValidator.LOG_TAG, message);
      return true;
    }
    return false;
  }

  /**
   * Checks if passed in string value is undefined, null or empty and shows warning
   *
   * @static
   * @param argumentName {string} - name of the paramter under assertion
   * @param argumentValue {string} - value of the parameter to assert
   * @memberof ArgumentsValidator
   */
  public static warnIfEmptyOrUndefined (argumentName: string, argumentValue?: any): boolean {
    if (!argumentValue || argumentValue.length === 0 || argumentValue.trim().length === 0) {
      const message:string = argumentName + ' is null or undefined.';
      console.warn(message);
      TraceLogger.logWarning(ArgumentsValidator.LOG_TAG, message);
      return true;
    }
    return false;
  }
}
