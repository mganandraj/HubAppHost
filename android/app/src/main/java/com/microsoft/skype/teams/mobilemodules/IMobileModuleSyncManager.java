/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.mobilemodules;

import bolts.Task;

/**
 * Manages syncing of Mobile Modules
 */
public interface IMobileModuleSyncManager {
    Task<Void> syncMobileModules();

    void initializeMobileModulesAfterFre();
}
