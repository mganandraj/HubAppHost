"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ArgumentsValidator = void 0;
const TraceLogger_1 = require("../../lib/services/diagnostics/TraceLogger");
const ArgumentException_1 = require("../errors/ArgumentException");
class ArgumentsValidator {
    /**
     * Asserts if passed in value is not undefined or null
     *
     * @static
     * @param {string} argumentName - name of the paramter under assertion
     * @param {*} argumentValue - value of the parameter to assert
     * @memberof ArgumentsValidator
     */
    static assertNotNullOrUndefined(argumentName, argumentValue) {
        if (!argumentValue) {
            throw new ArgumentException_1.ArgumentException(argumentName + ' is null or undefined.');
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
    static assertNonEmptyString(argumentName, argumentValue) {
        if (!argumentValue || argumentValue.length === 0 || argumentValue.trim().length === 0) {
            throw new ArgumentException_1.ArgumentException(argumentName + ' is null or undefined.');
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
    static warnIfNullOrUndefined(argumentName, argumentValue) {
        if (!argumentValue) {
            const message = argumentName + ' is null or undefined.';
            console.warn(message);
            TraceLogger_1.TraceLogger.logWarning(ArgumentsValidator.LOG_TAG, message);
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
    static warnIfEmptyOrUndefined(argumentName, argumentValue) {
        if (!argumentValue || argumentValue.length === 0 || argumentValue.trim().length === 0) {
            const message = argumentName + ' is null or undefined.';
            console.warn(message);
            TraceLogger_1.TraceLogger.logWarning(ArgumentsValidator.LOG_TAG, message);
            return true;
        }
        return false;
    }
}
exports.ArgumentsValidator = ArgumentsValidator;
ArgumentsValidator.LOG_TAG = 'ArgumentsValidator';
