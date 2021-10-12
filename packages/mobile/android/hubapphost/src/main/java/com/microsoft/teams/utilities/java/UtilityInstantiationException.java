/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.utilities.java;

/**
 * Exception thrown when create an instance of a utility class.
 */
@SuppressWarnings("unused")
public class UtilityInstantiationException extends RuntimeException {
    public UtilityInstantiationException() {
        super("Utility classes shouldn't be instantiated");
    }
}