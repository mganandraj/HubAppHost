/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
export declare class RunnerServer {
    private appBuildDirectory;
    private expressServer;
    constructor();
    start(): void;
    private prepareAppPackage;
    private handleGetRunnerReadyRequest;
    private handleGetAppManifestRequest;
    private handleGetAppDefinitionRequest;
    private handleGetResources;
    private handleGetPackage;
}
