package com.microsoft.hubapphost.impls;

import android.util.Log;

import com.microsoft.teams.logger.ILogger;

import java.util.Map;

import javax.inject.Inject;

public class TeamsSdkSimLogger implements ILogger {

    @Inject public TeamsSdkSimLogger(){}

    @Override
    public boolean isLogTransmissionEnabled() {
        return true;
    }

    @Override
    public int getMinimumLogPriority() {
        return 0;
    }

    @Override
    public void setMinimumLogPriority(int logPriority) {

    }

    @Override
    public void enableFileLogging(boolean enabled) {

    }

    @Override
    public void log(int priority, String tag, String format, Object... args) {
        Log.e(tag, String.format(format, args));
    }

    @Override
    public void log(int priority, String tag, Throwable t) {
        Log.e(tag, t.toString());
    }

    @Override
    public void log(int priority, String tag, Throwable t, String format, Object... args) {
        Log.e(tag, String.format(format, args) + " || " + t.toString());
    }

    @Override
    public void log(int priority, String tag, Throwable t, boolean skipCustomerDataScan, String format, Object... args) {
        Log.e(tag, String.format(format, args) + " || " + t.toString());
    }

    @Override
    public void logAdal(Map<String, String> adalAuthEvent) {

    }

    @Override
    public void logCrash(Map<String, String> crashEvent) {
        Log.e("CRASHH", crashEvent.toString());
    }

    @Override
    public void pauseTransmission() {

    }

    @Override
    public void resumeTransmission() {

    }
}
