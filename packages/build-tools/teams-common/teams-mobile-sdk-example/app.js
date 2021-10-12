/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

'use strict';

import { AppRegistry } from 'react-native';
import { GreetingComponent } from './build/src/components/GreetingComponent';
import { LocalizedStrings, ModuleLifecycle } from 'teams-mobile-sdk';
import { LocalizedStringsProviderImpl } from './build/src/services/LocalizedStringsProviderImpl';
import { UserAvatarComponent } from './build/src/components/UserAvatarComponent';
import { NavigationComponent } from './build/src/components/NavigationComponent';
import { NetworkConnectivityComponent } from './build/src/components/NetworkConnectivityComponent';
import { StateLayoutComponent } from './build/src/components/StateLayoutComponent';
import { TeamsAndChannelsApiComponent } from './build/src/components/TeamsAndChannelsApiComponent';
import { TabsInChatsApiComponent } from './build/src/components/TabsInChatsApiComponent';
import { TeamsWebViewComponent } from './build/src/components/TeamsWebViewComponent';
import { TelemetryClientComponent } from './build/src/components/TelemetryClientComponent';
import { ImageAndFilePickerComponent } from './build/src/components/ImageAndFilePickerComponent';
import { SharepointFilePreviewComponent } from './build/src/components/SharepointFilePreviewComponent';
import { AsyncStorageComponent } from './build/src/components/AsyncStorageComponent';
import { TabbedComponent } from './build/src/components/TabbedComponent';
import { FluentIconsDemoComponent } from './build/src/components/FluentIconsDemoComponent';
import { SecureStorageComponent } from './build/src/components/SecureStorageComponent';

export function registerApplication() {
  // String localization
  LocalizedStrings.initialize(LocalizedStringsProviderImpl.getInstance());

  // Register all view components
  AppRegistry.registerComponent("GreetingComponent", () => GreetingComponent);
  AppRegistry.registerComponent("ImageAndFilePickerComponent", () => ImageAndFilePickerComponent);
  AppRegistry.registerComponent("NavigationComponent", () => NavigationComponent);
  AppRegistry.registerComponent("UserAvatarComponent", () => UserAvatarComponent);
  AppRegistry.registerComponent("NetworkConnectivityComponent", () => NetworkConnectivityComponent);
  AppRegistry.registerComponent("StateLayoutComponent", () => StateLayoutComponent);
  AppRegistry.registerComponent("TeamsAndChannelsApiComponent", () => TeamsAndChannelsApiComponent);
  AppRegistry.registerComponent("TabsInChatsApiComponent", () => TabsInChatsApiComponent);
  AppRegistry.registerComponent("TeamsWebViewComponent", () => TeamsWebViewComponent);
  AppRegistry.registerComponent("SharepointFilePreviewComponent", () => SharepointFilePreviewComponent);
  AppRegistry.registerComponent("TelemetryClientComponent", () => TelemetryClientComponent);
  AppRegistry.registerComponent("AsyncStorageComponent", () => AsyncStorageComponent);
  AppRegistry.registerComponent("TabbedComponent", ()=> TabbedComponent);
  AppRegistry.registerComponent("FluentIconsDemoComponent", () => FluentIconsDemoComponent);
  AppRegistry.registerComponent("SecureStorageComponent", () => SecureStorageComponent);

  // Add sign out event listener
  ModuleLifecycle.registerHandler({
    onUserSignedOut: (event) => {
      console.log('Sign out event received')
    }
  });

  console.log('Application registered.');
}
