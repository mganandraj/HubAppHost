/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.androidutils.tasks;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

import bolts.Task;

/**
 * Task related Utilities
 */
public interface ITaskRunner {
    /**
     * Use this method to schedule a {@code runnable} to be ran after {@code delay} milliseconds
     *
     * @param runnable the code to get executed in the future
     * @param delay the amount of milliseconds that we will wait before executing the code
     */
    void runOnMainThread(@NonNull Runnable runnable, @IntRange(from = 1) long delay);

    /**
     * Use this method to remove some {@code runnable} from the scheduling
     *
     * @param runnable the code to get executed in the future
     */
    void removeMainThreadCallBack(@NonNull Runnable runnable);

    /**
     * Use this code to queue a {@code runnable} in the UI_THREAD_EXECUTOR
     *
     * @param runnable the code to get executed
     * @return the task wrapping the {@code runnable}
     */
    @NonNull
    Task<Void> runOnMainThread(@NonNull Runnable runnable);

    /**
     * Use this code to queue a {@code runnable} in the UI_THREAD_EXECUTOR
     *
     * @param codeToExecute the code to get executed
     * @return the task wrapping the {@code runnable}
     */
    @NonNull
    <T> Task<T> runOnMainThread(@NonNull Callable<T> codeToExecute);

    /**
     * Use this code to queue a {@code runnable} in the BACKGROUND_EXECUTOR
     *
     * @param runnable the code to get executed
     * @return the task wrapping the {@code runnable}
     */
    @NonNull
    Task<Void> runOnBackgroundThread(@NonNull Runnable runnable);

    /**
     * Use this code to queue a {@code runnable} in the BACKGROUND_EXECUTOR
     *
     * @param codeToExecute the code to get executed
     * @return the task wrapping the {@code runnable}
     */
    @NonNull
    <T> Task<T> runOnBackgroundThread(@NonNull Callable<T> codeToExecute);

    /**
     * Use this code to queue a {@code runnable} in the BACKGROUND_EXECUTOR
     *
     * @param runnable the code to get executed
     * @param cancellationToken the cancellation token to be used
     * @return the task wrapping the {@code runnable}
     */
    @NonNull
    Task<Void> runOnBackgroundThread(@NonNull Runnable runnable, @Nullable CancellationToken cancellationToken);

    /**
     * Use this code to queue a {@code runnable} in the BACKGROUND_EXECUTOR
     *
     * @param codeToExecute the code to get executed
     * @param cancellationToken the cancellation token to be used
     * @return the task wrapping the {@code runnable}
     */
    <T> Task<T> runOnBackgroundThread(@NonNull Callable<T> codeToExecute, @Nullable CancellationToken cancellationToken);

    /**
     * Use this code to queue a {@code runnable} in the {@code executor}
     *
     * @param runnable the code to get executed
     * @param executor the executor to be used to run the {@code runnable}
     * @return the task wrapping the {@code runnable}
     */
    @NonNull
    Task<Void> runOnBackgroundThread(@NonNull Runnable runnable, @Nullable Executor executor);

    /**
     * Use this code to queue a {@code runnable} in the {@code executor}
     *
     * @param codeToExecute the code to get executed
     * @param executor the executor to be used to run the {@code runnable}
     * @return the task wrapping the {@code runnable}
     */
    @NonNull
    <T> Task<T> runOnBackgroundThread(@NonNull Callable<T> codeToExecute, @Nullable Executor executor);

    /**
     * Use this code to queue a {@code runnable} in the {@code executor}
     *
     * @param runnable the code to get executed
     * @param executor the executor to be used to run the {@code runnable}
     * @param cancellationToken the cancellation token to be used
     * @return the task wrapping the {@code runnable}
     */
    @NonNull
    Task<Void> runOnExecutor(@NonNull final Runnable runnable,
                             @Nullable Executor executor,
                             @Nullable CancellationToken cancellationToken);

    /**
     * Use this code to queue a {@code runnable} in the {@code executor}
     *
     * @param codeToExecute the code to get executed
     * @param executor the executor to be used to run the {@code runnable}
     * @param cancellationToken the cancellation token to be used
     * @return the task wrapping the {@code runnable}
     */
    @NonNull
    <T> Task<T> runOnExecutor(@NonNull final Callable<T> codeToExecute,
                              @Nullable Executor executor,
                              @Nullable CancellationToken cancellationToken);
}