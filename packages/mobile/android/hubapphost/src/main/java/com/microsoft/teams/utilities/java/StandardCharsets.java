package com.microsoft.teams.utilities.java;

import java.nio.charset.Charset;

/**
 * List of the standard encodings.
 * There is a class with the same constants in Android SDK, but it missing on some of the platforms
 */
public final class StandardCharsets {
    private StandardCharsets() {
    }

    public static final Charset UTF_8 = Charset.forName("UTF-8");
}
