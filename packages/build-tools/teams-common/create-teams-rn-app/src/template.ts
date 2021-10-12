/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import { readFileSync, readJSONSync, writeFileSync, writeJSONSync } from "fs-extra";
import path from "path";

export function fillPackageJsonValues(path: string,
                                      appName: string, 
                                      appVersion: string, 
                                      description: string, 
                                      author: string, 
                                      license: string, 
                                      teamsMobileSdkVersion: string): void {
    const packageJson = readJSONSync(path);
    packageJson.name = appName;
    packageJson.version = appVersion;
    packageJson.description = description;
    packageJson.author = author;
    packageJson.license = license;
    packageJson.dependencies["teams-mobile-sdk"] = teamsMobileSdkVersion; 

    writeJSONSync(path, packageJson, { spaces: 2 });
}

export function fillManifestJsonValues(path: string,
                                       appId: string,
                                       appName: string,
                                       minRnSdkVersion: string) {
    const manifestJson = readJSONSync(path);
    manifestJson.id = appId;
    manifestJson.name = appName;
    manifestJson.minReactNativeSdkVersion = minRnSdkVersion;

    writeJSONSync(path, manifestJson, {spaces: 2});
}

export function fillStringResources(path: string, appName: string): void {
    const strings = readJSONSync(path);
    strings.app_name = appName;
    writeJSONSync(path, strings, { spaces: 2 });
}

export function fillCopyrightHeaders(rootPath: string, author: string) {
    fillAuthorName(path.join(rootPath, './src/components/HomeComponent.tsx'), author);
    fillAuthorName(path.join(rootPath, './src/services/LocalizedStringsProviderImpl.ts'), author);
}

function fillAuthorName(filePath: string, author: string) {
    const fileContents = readFileSync(filePath).toString('utf-8');
    fileContents.replace('<author>', author);
    writeFileSync(filePath, fileContents);
}
