/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
export interface LocalizedStringsProvider {
    getSupportedLocaleStrings(): {
        [key: string]: () => Object;
    };
    getDefaultLocaleStrings(): Object;
}
export declare function setLocale(inputLocale: string): void;
export declare function getString(path: string, paramData?: {
    [name: string]: string | number;
}): string;
export declare function getLocale(): string;
export declare function getParts<T>(path: string, paramData: {
    [name: string]: T | string | number;
}): Array<T | string>;
export declare function initialize(localizedStringsProviderValue: LocalizedStringsProvider): void;
