/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.logger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Utilities related to StackTraces
 */
public final class StackTraceUtilities {
    /**
     * Gets the stack trace for an exception
     */
    @NonNull
    public static String getStackTraceString(@Nullable Throwable t) {
        if (null == t) {
            return "";
        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        String exceptionStackTrace = sw.toString();
        if (exceptionStackTrace != null && exceptionStackTrace.contains("SQLiteException")) {
            return "SQLiteException - StackTrace got masked due to potential EUII" + t.getStackTrace();
        }

        if (exceptionStackTrace != null && exceptionStackTrace.contains("SSLHandshakeException: Unacceptable certificate")) {
            return "SSLHandshakeException: Unacceptable certificate - StackTrace got masked due to potential EUII" + t.getStackTrace();
        }

        if (exceptionStackTrace != null && exceptionStackTrace.contains("EMAILADDRESS")) {
            return "Exception StackTrace got masked due to potential email EUII"  + t.getStackTrace();
        }


        return exceptionStackTrace;
    }

    @NonNull
    public static String getCurrentThreadName() {
        String threadName = Thread.currentThread().getName();

        // OkHttp threads contain request URLs, so remove them
        if (threadName.startsWith("OkHttp")) {
            threadName = "OkHttpRequest";
        }

        return threadName;
    }

    @NonNull
    public static String getErrorTitle(@NonNull Throwable throwable) {
        StringBuilder titleBuilder = new StringBuilder()
                .append(throwable.getClass().getName())
                .append(": ")
                .append(throwable.getMessage());

        StackTraceElement[] stackTraceElements = throwable.getStackTrace();
        if (stackTraceElements != null && stackTraceElements.length > 0) {
            StackTraceElement firstTraceElement = stackTraceElements[0];
            titleBuilder.append(" at ").append(firstTraceElement.toString());
        }

        return titleBuilder.toString();
    }

    private StackTraceUtilities() {
    }
}
