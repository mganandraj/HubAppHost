/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.logger;

import java.util.Map;

/**
 * Interface for the service that logs message to different log targets.
 */
public interface ILogger {

    /**
     * gets transmission state for the logger , true if trans
     *
     * @return true if enabled
     */
    boolean isLogTransmissionEnabled();
    /**
     * Gets the minimum logging priority
     *
     * @return The minimum log priority
     */
    int getMinimumLogPriority();

    /**
     * Sets minimum logging priority
     *
     * @param logPriority - the minimum log priority
     */
    void setMinimumLogPriority(@LogPriority int logPriority);

    /**
     *
     * Enable File logging
     *
     * @param enabled
     *
     */
    void enableFileLogging(boolean enabled);

    /**
     * Logs a message
     *
     * @param priority - Priority of the log whether Verbose, Debug, Info, Assert, Warning, Error
     * @param tag - Tag to use to write the log
     * @param format - Log message format
     * @param args - Log message arguments
     */
    void log(@LogPriority int priority, String tag, String format, Object... args);

    /**
     * Logs an exception
     *
     * @param priority - Priority of the log whether Verbose, Debug, Info, Assert, Warning, Error
     * @param tag - Tag to use to write the log
     * @param t - The exception to log
     */
    void log(@LogPriority int priority, String tag, Throwable t);

    /**
     * Logs an exception with additional message
     *
     * @param priority - Priority of the log whether Verbose, Debug, Info, Assert, Warning, Error
     * @param t - Exception to log
     * @param tag - Tag to use to write the log
     * @param format - Log message format
     * @param args - Log message arguments
     */
    void log(@LogPriority int priority, String tag, Throwable t, String format, Object... args);

    /**
     * Logs an exception with additional message
     *
     * @param priority - Priority of the log whether Verbose, Debug, Info, Assert, Warning, Error
     * @param t - Exception to log
     * @param tag - Tag to use to write the log
     * @param format - Log message format
     * @param skipCustomerDataScan - Whether or not we should skip scanning for customer data
     * @param args - Log message arguments
     */
    void log(@LogPriority int priority, String tag, Throwable t, boolean skipCustomerDataScan, String format, Object... args);

    /**
     * Logs adal Telemetry event.
     *
     * @param adalAuthEvent - adal auth telemetry event.
     */
    void logAdal(Map<String, String> adalAuthEvent);

    /**
     * Logs app crash events (in case of non-global users/tenants)
     * @param crashEvent
     */
    void logCrash(Map<String, String> crashEvent);

    /**
     * Pauses log transmission.
     */
    void pauseTransmission();

    /**
     * Resumes log transmission.
     */
    void resumeTransmission();
}
