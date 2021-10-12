/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
export declare class FileUtilities {
    static ensureEmptyDirectory(dirPath: string): Promise<void>;
    static writeJson(filePath: string, object: any): Promise<void>;
    static writeJsonSync(filePath: string, object: any): void;
    static readJson(filePath: string): Promise<any>;
    static readJsonSync(filePath: string): any;
    static copyDirectory(source: string, destination: string): Promise<void>;
    static compressDirectory(sourceDirectory: string, compressedFilePath: string): Promise<void>;
    static promiseCallback(resolve: any, reject: any): (error: any) => void;
}
