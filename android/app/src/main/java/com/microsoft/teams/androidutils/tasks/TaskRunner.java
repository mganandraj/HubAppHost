/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.androidutils.tasks;

import android.os.Handler;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

import bolts.Continuation;
import bolts.Task;

/**
 * This class is meant to execute pieces of code in different threads
 */
public class TaskRunner implements ITaskRunner {
    private Handler mMainThreadHandler;
    private static TaskUtilities.ITaskErrorListener sErrorListener;

    public static void setErrorListener(TaskUtilities.ITaskErrorListener listener) {
        sErrorListener = listener;
    }


    public TaskRunner(Handler mMainThreadHandler) {
        this.mMainThreadHandler = mMainThreadHandler;
    }

    @Override
    public void runOnMainThread(@NonNull Runnable runnable, @IntRange(from = 1) long delay) {
        mMainThreadHandler.postDelayed(runnable, delay);
    }

    @Override
    public void removeMainThreadCallBack(@NonNull Runnable runnable) {
        mMainThreadHandler.removeCallbacks(runnable);
    }

    @NonNull
    @Override
    public Task<Void> runOnMainThread(@NonNull Runnable runnable) {
        return runOnExecutor(runnable, Task.UI_THREAD_EXECUTOR, null);
    }

    @NonNull
    @Override
    public <T> Task<T> runOnMainThread(@NonNull Callable<T> codeToExecute) {
        return runOnExecutor(codeToExecute, Task.UI_THREAD_EXECUTOR, null);
    }

    @NonNull
    @Override
    public Task<Void> runOnBackgroundThread(@NonNull Runnable runnable) {
        return runOnExecutor(runnable, getBackgroundExecutor(), null);
    }

    @NonNull
    @Override
    public <T> Task<T> runOnBackgroundThread(@NonNull Callable<T> codeToExecute) {
        return runOnExecutor(codeToExecute, getBackgroundExecutor(), null);
    }

    @NonNull
    @Override
    public Task<Void> runOnBackgroundThread(@NonNull Runnable runnable, @Nullable CancellationToken cancellationToken) {
        return runOnExecutor(runnable, getBackgroundExecutor(), cancellationToken);
    }

    @Override
    public <T> Task<T> runOnBackgroundThread(@NonNull Callable<T> codeToExecute, @Nullable CancellationToken cancellationToken) {
        return runOnExecutor(codeToExecute, getBackgroundExecutor(), cancellationToken);
    }

    @NonNull
    @Override
    public Task<Void> runOnBackgroundThread(@NonNull Runnable runnable, @Nullable Executor executor) {
        return runOnExecutor(runnable, executor, null);
    }

    @NonNull
    @Override
    public <T> Task<T> runOnBackgroundThread(@NonNull Callable<T> codeToExecute, @Nullable Executor executor) {
        return runOnExecutor(codeToExecute, executor, null);
    }

    @NonNull
    @Override
    public Task<Void> runOnExecutor(@NonNull final Runnable runnable,
                                    @Nullable Executor executor,
                                    @Nullable CancellationToken cancellationToken) {
        Callable<Void> callable = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                runnable.run();
                return null;
            }
        };

        return runOnExecutor(callable, executor, cancellationToken);
    }

    @NonNull
    @Override
    public <T> Task<T> runOnExecutor(@NonNull final Callable<T> codeToExecute,
                                     @Nullable Executor executor,
                                     @Nullable CancellationToken cancellationToken) {
        bolts.CancellationToken taskCancellationToken = cancellationToken != null ? cancellationToken.getToken() : null;
        Task<T> resultTask = (executor != null) ? Task.call(codeToExecute, executor, taskCancellationToken)
                : Task.call(codeToExecute, taskCancellationToken);

        resultTask.continueWith(new Continuation<T, Task<T>>() {
            @Override
            public Task<T> then(Task<T> task) throws Exception {
                if (task.isFaulted() && sErrorListener != null) {
                    sErrorListener.handleTaskError(task);
                }
                return task;
            }
        });

        return resultTask;
    }

    private Executor getBackgroundExecutor() {
        return TaskUtilities.getBackgroundExecutor();
    }
}
