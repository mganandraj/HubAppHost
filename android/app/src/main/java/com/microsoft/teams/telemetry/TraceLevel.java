/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.telemetry;

/**
 * Defines the TraceLevel values for the event
 */
public enum TraceLevel {
    NONE(0),
    ERROR(1),
    WARNING(2),
    INFORMATION(3),
    VERBOSE(4);

    private final int mValue;

    TraceLevel(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }
}
