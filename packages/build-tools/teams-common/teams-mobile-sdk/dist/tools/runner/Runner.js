"use strict";
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
Object.defineProperty(exports, "__esModule", { value: true });
const RunnerServer_1 = require("./RunnerServer");
class SdkRunner {
    constructor() {
        // Create the runner server
        this.runnerServer = new RunnerServer_1.RunnerServer();
    }
    start() {
        this.runnerServer.start();
    }
}
// Start a runner instance
const runnerInstance = new SdkRunner();
runnerInstance.start();
