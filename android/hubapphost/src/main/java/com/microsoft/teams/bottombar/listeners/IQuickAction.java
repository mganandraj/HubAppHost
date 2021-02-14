/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.bottombar.listeners;

/**
 * Interface to expose the bottom bar behaviour of ReactNative modules
 */
public interface IQuickAction {
    boolean isQuickAction();

    void executeQuickAction(IQuickActionCompletionListener quickActionCompletionListener);

    /**
     * Callback interface to listen Quick Action completion event
     */
    interface IQuickActionCompletionListener {
        void onQuickActionCompleted();
    }
}
