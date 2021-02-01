/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk.react.modules.Utils;

/**
 * This is equivalent to interface {@link java.util.function.Function}. Creating a Teams version of it as this is supported only above API 24.
 *
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 */
public interface Function<T, R>  {
    R apply(T k);
}
