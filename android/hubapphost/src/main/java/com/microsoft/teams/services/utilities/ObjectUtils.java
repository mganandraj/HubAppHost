/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.services.utilities;

/**
 * General utilities related to objects.
 */
public final class ObjectUtils {

    /**
     * <p>Returns a default value if the object passed is {@code null}.</p>
     *
     * <pre>
     * ObjectUtils.defaultIfNull(null, null)      = null
     * ObjectUtils.defaultIfNull(null, "")        = ""
     * ObjectUtils.defaultIfNull(null, "zz")      = "zz"
     * ObjectUtils.defaultIfNull("abc", *)        = "abc"
     * ObjectUtils.defaultIfNull(Boolean.TRUE, *) = Boolean.TRUE
     * </pre>
     *
     * @param <T>          the type of the object
     * @param object       the {@code Object} to test, may be {@code null}
     * @param defaultValue the default value to return, may be {@code null}
     * @return {@code object} if it is not {@code null}, defaultValue otherwise
     */
    public static <T> T defaultIfNull(final T object, final T defaultValue) {
        return object != null ? object : defaultValue;
    }

    private ObjectUtils() {
        throw new UnsupportedOperationException();
    }
}
