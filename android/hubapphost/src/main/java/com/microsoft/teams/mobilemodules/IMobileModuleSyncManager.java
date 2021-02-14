/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.mobilemodules;

import bolts.Task;

/**
 * Manages syncing of Mobile Modules
 */
public interface IMobileModuleSyncManager {
    Task<Void> syncMobileModules();

    void initializeMobileModulesAfterFre();
}
