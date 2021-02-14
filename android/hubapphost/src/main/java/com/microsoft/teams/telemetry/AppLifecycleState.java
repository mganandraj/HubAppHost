/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.telemetry;

/**
 * Defines the AppLifecycleState values for the event
 */
public enum AppLifecycleState {
    UNKNOWN,
    LAUNCH,
    EXIT,
    SUSPEND,
    RESUME,
    FOREGROUND,
    BACKGROUND
}
