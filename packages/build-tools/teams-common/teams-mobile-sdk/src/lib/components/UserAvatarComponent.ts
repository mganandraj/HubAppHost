/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
import * as React from 'react';
import { requireNativeComponent, ViewProps } from 'react-native';

export const UserIdType = {
  Unknown: 'unknown',
  AadUpn: 'aadUpn',
  AadObjectId: 'aadObjectId',
  Mri: 'mri',
  DeviceContactId: 'deviceContactId',
  PhoneNumber: 'phoneNumber',
  ThreadId: 'threadId'
};

export interface UserAvatarProps {
  displayName?: string;
  userId?: string;
  userIdType: string;
  userAvatarUrl?: string;
  showPresenceIndicator?: boolean;
}

export interface UserAvatarComponentPropTypes extends ViewProps {
  avatarProps?: UserAvatarProps;
}

// The name of the native component should not be changed. It is defined in the
// view managers in the native code and the values need to match.
export const UserAvatar: React.ComponentClass<UserAvatarComponentPropTypes> = requireNativeComponent('UserAvatar');
