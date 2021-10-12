/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
/**
 * Manages the configuration needed by SDK to perform different tasks like build, install, etc.
 * Developers use this to configure their development environment.
 */
export declare class SdkConfigManager {
    static getUserApiToken(): string | undefined;
    static get(configKey: string): string | undefined;
    static getAll(): object;
    static set(configKey: string, configValue: string | undefined): void;
    static remove(configKey: string): void;
    private static readConfigObject;
    private static writeConfigObject;
}
