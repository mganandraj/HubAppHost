/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
export declare class EventLogger {
    private hostInstanceId;
    constructor(hostInstanceId: string);
    logViewReady(): void;
    logContentReady(): void;
}
