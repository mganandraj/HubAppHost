/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
import * as React from 'react';
import { ViewProps } from 'react-native';
export declare const UserIdType: {
    Unknown: string;
    AadUpn: string;
    AadObjectId: string;
    Mri: string;
    DeviceContactId: string;
    PhoneNumber: string;
    ThreadId: string;
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
export declare const UserAvatar: React.ComponentClass<UserAvatarComponentPropTypes>;
