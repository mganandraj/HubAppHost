export declare class ArgumentsValidator {
    private static readonly LOG_TAG;
    /**
     * Asserts if passed in value is not undefined or null
     *
     * @static
     * @param {string} argumentName - name of the paramter under assertion
     * @param {*} argumentValue - value of the parameter to assert
     * @memberof ArgumentsValidator
     */
    static assertNotNullOrUndefined(argumentName: string, argumentValue?: any): void;
    /**
     * Asserts if passed in string value is not undefined, null or empty
     *
     * @static
     * @param argumentName {string} - name of the paramter under assertion
     * @param argumentValue {string} - value of the parameter to assert
     * @memberof ArgumentsValidator
     */
    static assertNonEmptyString(argumentName: string, argumentValue?: string): void;
    /**
     * Checks if passed in string value is undefined or null and shows warning
     *
     * @static
     * @param argumentName {string} - name of the paramter under assertion
     * @param argumentValue {string} - value of the parameter to assert
     * @memberof ArgumentsValidator
     */
    static warnIfNullOrUndefined(argumentName: string, argumentValue?: any): boolean;
    /**
     * Checks if passed in string value is undefined, null or empty and shows warning
     *
     * @static
     * @param argumentName {string} - name of the paramter under assertion
     * @param argumentValue {string} - value of the parameter to assert
     * @memberof ArgumentsValidator
     */
    static warnIfEmptyOrUndefined(argumentName: string, argumentValue?: any): boolean;
}
