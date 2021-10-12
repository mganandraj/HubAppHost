/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
export declare class TraceLogger {
    static logVerbose(tag: string, message: string): void;
    static logInformation(tag: string, message: string): void;
    static logDebug(tag: string, message: string): void;
    static logWarning(tag: string, message: string): void;
    static logError(tag: string, message: string): void;
    private static createMessage;
}
