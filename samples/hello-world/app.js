/**
 * Copyright © <author>. All rights reserved.
 */

'use strict';

import { AppRegistry } from 'react-native';
import { HomeComponent } from './build/src/components/HomeComponent';
import { LocalizedStrings, ModuleLifecycle } from 'teams-mobile-sdk';
import { LocalizedStringsProviderImpl } from './build/src/services/LocalizedStringsProviderImpl';

export function registerApplication() {
  // String localization
  LocalizedStrings.initialize(LocalizedStringsProviderImpl.getInstance());

  // Register all view components
  AppRegistry.registerComponent("HomeComponent", () => HomeComponent);

  // Add sign out event listener
  ModuleLifecycle.registerHandler({
    onUserSignedOut: (event) => {
      console.log('Sign out event received')
    }
  });

  console.log('Application registered.');
}
