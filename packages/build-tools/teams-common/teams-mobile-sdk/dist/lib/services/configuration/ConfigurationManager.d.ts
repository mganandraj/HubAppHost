/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
export declare class ConfigurationManager {
    private static instance;
    private constructor();
    static getInstance(): ConfigurationManager;
    setAriaTenant(tenantToken: string): void;
}
