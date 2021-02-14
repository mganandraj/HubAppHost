/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.androidutils.tasks;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;

import bolts.CancellationTokenSource;

/**
 * Provides ways to cancel a data operation
 */
public class CancellationToken {
    public static final CancellationToken NONE = new CancellationToken();

    private final CancellationTokenSource mTokenSource = new CancellationTokenSource();
    private final CopyOnWriteArrayList<WeakReference<ICancellationCallback>> mCallbackRefs = new CopyOnWriteArrayList<>();

    public boolean isCancellationRequested() {
        return mTokenSource.isCancellationRequested();
    }

    public bolts.CancellationToken getToken() {
        return mTokenSource.getToken();
    }

    private boolean isNone() {
        return equals(NONE);
    }

    public void cancel() {
        if (isNone()) {
            return;
        }

        mTokenSource.cancel();
        notifyCallbacks();
    }

    public void cancelAfter(final long delayMilliseconds) {
        mTokenSource.cancelAfter(delayMilliseconds);
    }

    public void attachCallback(@NonNull ICancellationCallback callback) {
        if (isNone()) {
            return;
        }

        mCallbackRefs.add(new WeakReference<>(callback));
    }

    public void detachCallback(@NonNull ICancellationCallback callback) {
        if (isNone()) {
            return;
        }

        for (WeakReference<ICancellationCallback> callbackRefToRemove : mCallbackRefs) {
            final ICancellationCallback cancellationCallback = callbackRefToRemove.get();
            if (callback.equals(cancellationCallback)) {
                mCallbackRefs.remove(callbackRefToRemove);
                break;
            }
        }
    }

    private void notifyCallbacks() {
        for (WeakReference<ICancellationCallback> callbackRef : mCallbackRefs) {
            ICancellationCallback callback = callbackRef.get();
            if (callback != null) {
                callback.onCancel();
            }
        }
    }

    /**
     * Callback for cancelled event
     */
    public interface ICancellationCallback {
        void onCancel();
    }
}
