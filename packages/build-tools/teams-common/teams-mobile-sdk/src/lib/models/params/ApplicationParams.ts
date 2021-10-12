/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import { UserPDCLevel } from 'src/lib/services/TelemetryClient';
import { User } from '../core/User';

export enum AppTheme {
  DARK = 'dark',
  DEFAULT = 'default'
}

/**
 * @hidden from docs
 */
export interface ApplicationParams {
  appLocale: string;
  currentUser: User;
  appTheme: AppTheme;
  telemetryInfo: TelemetryInfo;
  sessionId: string;
  accentColor: string;
}

/**
 * @hidden from docs
 */
export interface HostParams {
  hostInstanceId: string;
}

/**
 * @hidden from docs
 */
export interface TeamsViewProps {
  appParams: ApplicationParams;
  _hostParams: HostParams;
  moduleParams: any;
  _params: any;
  componentParams: string;
}

/**
 * Contains various properties useful for telemetry purposes.
 */
export interface TelemetryInfo {
  teamsVersion: string;
  sdkVersion: string;
  userRing: string;
  deviceId: string;
  deviceManufacturer: string;
  deviceModel: string;
  deviceName: string;
  deviceType: string;
  deviceVersion: string;
  /**
   * The time at which the current view was started on the native side.
   * Expressed as milliseconds since 1 January 1970.
   */
  viewStartTime: number;
  userPDCLevel: UserPDCLevel;
  /**
   * `true` if the RN is currently pinned on the bottom bar.
   * Currently present only in iOS. Will be `undefined` for Android.
   */
  isAppPinned?: boolean;
}

export enum SyncState {
  INPROGRESS = "inProgress",
  COMPLETED = "completed"
}
