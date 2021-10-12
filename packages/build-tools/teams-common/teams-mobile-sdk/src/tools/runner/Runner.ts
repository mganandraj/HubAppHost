/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import { RunnerServer } from './RunnerServer';

class SdkRunner {
  private runnerServer: RunnerServer;

  constructor () {
    // Create the runner server
    this.runnerServer = new RunnerServer();
  }

  public start (): void {
    this.runnerServer.start();
  }
}

// Start a runner instance
const runnerInstance = new SdkRunner();
runnerInstance.start();
