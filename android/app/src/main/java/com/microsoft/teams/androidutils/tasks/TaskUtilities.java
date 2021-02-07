/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.androidutils.tasks;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.teams.androidutils.AndroidUtils;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

import bolts.Continuation;
import bolts.Task;

/**
 * Task related Utilities
 */
public final class TaskUtilities {

    public static final Handler MAIN_THREAD_HANDLER = new Handler(Looper.getMainLooper());
    private static HashSet<WeakReference<ITaskErrorListener>> sErrorListeners = new HashSet<>();
    /**
     * Custom executor to offload all background operations to instead of using Bolts' BACKGROUND_EXECUTOR
     * This is an experiment to keep thread timeout > 1s and observe impact on number of threads created and performance of app
     * sBackgroundThreadPoolExecutor will be set only if ECS for experiment is set to true
     */
    private static Executor sBackgroundThreadPoolExecutor;

    /**
     * Registers a new error listener to be notified when a task ends with an error
     *
     * @param errorListener Listener to notify when a task ends in an error
     */
    public static void registerErrorListener(ITaskErrorListener errorListener) {
        sErrorListeners.add(new WeakReference<ITaskErrorListener>(errorListener));
    }

    public static void setBackgroundThreadPoolExecutor(Executor sBackgroundThreadPoolExecutor) {
        TaskUtilities.sBackgroundThreadPoolExecutor = sBackgroundThreadPoolExecutor;
    }

    /**
     * Custom executor to offload all background operations to instead of using Bolts' BACKGROUND_EXECUTOR
     * This is an experiment to keep thread timeout > 1s and observe impact on number of threads created and performance of app
     * sBackgroundThreadPoolExecutor will be set only if ECS for experiment is set to true
     * See Task 743561: Reduce number of threads at app start for more details
     */
    public static Executor getBackgroundExecutor() {
        return sBackgroundThreadPoolExecutor == null ? Task.BACKGROUND_EXECUTOR : sBackgroundThreadPoolExecutor;
    }

    /**
     * Use this method to schedule a {@code runnable} to be ran after {@code delay} milliseconds
     *
     * @param runnable the code to get executed in the future
     * @param delay the amount of milliseconds that we will wait before executing the code
     */
    public static void runOnMainThread(final Runnable runnable, @IntRange(from = 1) long delay) {
        if (runnable == null) {
            return;
        }

        MAIN_THREAD_HANDLER.postDelayed(runnable, delay);
    }

    /**
     * Use this method to remove some {@code runnable} from the scheduling
     *
     * @param runnable the code to get executed in the future
     */
    public static void removeMainThreadCallBack(final Runnable runnable) {
        if (runnable == null) {
            return;
        }

        MAIN_THREAD_HANDLER.removeCallbacks(runnable);
    }

    /**
     * Use this code to queue a {@code runnable} in the UI_THREAD_EXECUTOR
     *
     * @param runnable the code to get executed
     * @return the task wrapping the {@code runnable}
     */
    public static Task<Void> runOnMainThread(@NonNull Runnable runnable) {
        return runOnExecutor(runnable, Task.UI_THREAD_EXECUTOR, null);
    }

    public static <T> T runOnMainThreadSync(@NonNull final Callable<T> callable) throws Exception {
        if (AndroidUtils.isMainThread()) {
            return callable.call();
        }

        Task<T> task = Task.call(callable, Task.UI_THREAD_EXECUTOR);
        task.waitForCompletion();
        return task.getResult();
    }

    /**
     * Use this code to queue a {@code runnable} in the UI_THREAD_EXECUTOR
     *
     * @param codeToExecute the code to get executed
     * @return the task wrapping the {@code runnable}
     */
    public static <T> Task<T> runOnMainThread(@NonNull Callable<T> codeToExecute) {
        return runOnExecutor(codeToExecute, Task.UI_THREAD_EXECUTOR, null);
    }

    /**
     * Use this code to queue a {@code runnable} in the BACKGROUND_EXECUTOR
     *
     * @param runnable the code to get executed
     * @return the task wrapping the {@code runnable}
     */
    public static Task<Void> runOnBackgroundThread(@NonNull Runnable runnable) {
        return runOnExecutor(runnable, getBackgroundExecutor(), null);
    }

    /**
     * Use this code to queue a {@code runnable} in the BACKGROUND_EXECUTOR
     *
     * @param codeToExecute the code to get executed
     * @return the task wrapping the {@code runnable}
     */
    public static <T> Task<T> runOnBackgroundThread(@NonNull Callable<T> codeToExecute) {
        return runOnExecutor(codeToExecute, getBackgroundExecutor(), null);
    }

    /**
     * Use this code to queue a {@code runnable} in the BACKGROUND_EXECUTOR
     *
     * @param runnable the code to get executed
     * @param cancellationToken the cancellation token to be used
     * @return the task wrapping the {@code runnable}
     */
    public static Task<Void> runOnBackgroundThread(@NonNull Runnable runnable, @Nullable CancellationToken cancellationToken) {
        return runOnExecutor(runnable, getBackgroundExecutor(), cancellationToken);
    }

    /**
     * Use this code to queue a {@code runnable} in the BACKGROUND_EXECUTOR
     *
     * @param codeToExecute the code to get executed
     * @param cancellationToken the cancellation token to be used
     * @return the task wrapping the {@code runnable}
     */
    public static <T> Task<T> runOnBackgroundThread(@NonNull Callable<T> codeToExecute, @Nullable CancellationToken cancellationToken) {
        return runOnExecutor(codeToExecute, getBackgroundExecutor(), cancellationToken);
    }

    /**
     * Use this code to queue a {@code runnable} in the {@code executor}
     *
     * @param runnable the code to get executed
     * @param executor the executor to be used to run the {@code runnable}
     * @return the task wrapping the {@code runnable}
     */
    public static Task<Void> runOnBackgroundThread(@NonNull Runnable runnable, @Nullable Executor executor) {
        return runOnExecutor(runnable, executor, null);
    }

    /**
     * Use this code to queue a {@code runnable} in the {@code executor}
     *
     * @param codeToExecute the code to get executed
     * @param executor the executor to be used to run the {@code runnable}
     * @return the task wrapping the {@code runnable}
     */
    public static <T> Task<T> runOnBackgroundThread(@NonNull Callable<T> codeToExecute, @Nullable Executor executor) {
        return runOnExecutor(codeToExecute, executor, null);
    }

    /**
     * Use this code to queue a {@code runnable} in the {@code executor}
     *
     * @param runnable the code to get executed
     * @param executor the executor to be used to run the {@code runnable}
     * @param cancellationToken the cancellation token to be used
     * @return the task wrapping the {@code runnable}
     */
    public static Task<Void> runOnExecutor(@NonNull final Runnable runnable,
                                           @Nullable Executor executor,
                                           @Nullable CancellationToken cancellationToken) {
        Callable<Void> callable = new Callable<Void>() {
            @Override
            public Void call() {
                runnable.run();
                return null;
            }
        };

        return runOnExecutor(callable, executor, cancellationToken);
    }

    /**
     * Use this code to queue a {@code runnable} in the {@code executor} after some delay
     *
     * @param codeToExecute the code to get executed
     * @param cancellationToken the cancellation token to be used
     * @param delay delay in milliseconds
     */
    public static Task<Void> runOnBackgroundThreadWithDelay(@NonNull final Runnable codeToExecute,
                                                            @Nullable final CancellationToken cancellationToken,
                                                            @IntRange(from = 1) long delay) {
        Callable<Void> callable = new Callable<Void>() {
            @Override
            public Void call() {
                codeToExecute.run();
                return null;
            }
        };

        return runOnBackgroundThreadWithDelay(callable, cancellationToken, delay);
    }

    /**
     * Use this code to queue a {@code runnable} in the {@code executor} after some delay
     *
     * @param codeToExecute the code to get executed
     * @param cancellationToken the cancellation token to be used
     * @param delay delay in milliseconds
     */
    public static <T> Task<T> runOnBackgroundThreadWithDelay(@NonNull final Callable<T> codeToExecute,
                                                             @Nullable final CancellationToken cancellationToken,
                                                             @IntRange(from = 1) long delay) {

        bolts.CancellationToken taskCancellationToken = cancellationToken != null ? cancellationToken.getToken() : null;

        Task<Void> delayTask = Task.delay(delay, taskCancellationToken);
        return delayTask.continueWithTask(new Continuation<Void, Task<T>>() {
            @Override
            public Task<T> then(Task<Void> task) throws Exception {
                return runOnExecutor(codeToExecute, getBackgroundExecutor(), cancellationToken);
            }
        });
    }

    /**
     * It saves thread switching if already on BG thread otherwise move the execution to background thread.
     */
    public static <T> Task<T> runInBackgroundIfOnMainThread(@NonNull Callable<T> codeToExecute, @Nullable CancellationToken cancellationToken) {
        Executor executor = null;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            executor = getBackgroundExecutor();
        }
        return runOnExecutor(codeToExecute, executor, cancellationToken);
    }

    /**
     * Use this code to queue a {@code runnable} in the {@code executor}
     *
     * @param codeToExecute the code to get executed
     * @param executor the executor to be used to run the {@code runnable}
     * @param cancellationToken the cancellation token to be used
     * @return the task wrapping the {@code runnable}
     */
    public static <T> Task<T> runOnExecutor(@NonNull final Callable<T> codeToExecute,
                                            @Nullable Executor executor,
                                            @Nullable CancellationToken cancellationToken) {
        Task<T> resultTask;

        // If executor is main thread executor and already on main thread, execute it right away
        if (executor == Task.UI_THREAD_EXECUTOR && Looper.getMainLooper() == Looper.myLooper()) {
            try {
                resultTask = Task.forResult(codeToExecute.call());
            } catch (Exception e) {
                resultTask = Task.forError(e);
            }
        } else {
            bolts.CancellationToken taskCancellationToken = cancellationToken != null ? cancellationToken.getToken() : null;
            resultTask = (executor != null) ? Task.call(codeToExecute, executor, taskCancellationToken)
                    : Task.call(codeToExecute, taskCancellationToken);
        }

        return resultTask.continueWithTask(new Continuation<T, Task<T>>() {
            @Override
            public Task<T> then(Task<T> task) {
                if (task.isFaulted()) {
                    // Notify any listeners that this task had an error
                    for (WeakReference<ITaskErrorListener> listenerReference : sErrorListeners) {
                        ITaskErrorListener listener = listenerReference.get();
                        if (listener != null) {
                            listener.handleTaskError(task);
                            sErrorListeners.remove(listenerReference);
                        } else {
                            sErrorListeners.remove(listenerReference);
                        }
                    }
                }

                return task;
            }
        });
    }

    /**
     * Wraps a task as a safe task which even on error or cancellation returns a task with success status.
     */
    public static <T> Task<T> safeTask(@NonNull Task<T> task) {
        return task.continueWithTask(new Continuation<T, Task<T>>() {
            @Override
            public Task<T> then(Task<T> task) {
                return Task.forResult(null);
            }
        });
    }

    /**
     * Creates a continuation task which only runs when a result is received from the previous task.
     *
     * @param continuation The continuation task.
     * @param <R> The task result type.
     * @param <T> The continuation task result type.
     * @return The continuation instance.
     */
    public static <R, T> Continuation<R, Task<T>> continueWithResult(@NonNull final ITaskResultContinuation<R, T> continuation) {
        return new Continuation<R, Task<T>>() {
            @Override
            public Task<T> then(Task<R> task) {
                if (task.isCancelled()) {
                    return Task.cancelled();
                } else if (task.isFaulted()) {
                    return Task.forError(task.getError());
                }

                return continuation.onResult(task.getResult());
            }
        };
    }

    private TaskUtilities() {
    }

    /**
     * Listener to be notified of task errors
     */
    public interface ITaskErrorListener {
        void handleTaskError(Task task);
    }

    /**
     * Continuation for a task which runs only when a result is the set in the previous task.
     *
     * @param <R>The task result type.
     * @param <T> The continuation task result type.
     */
    public interface ITaskResultContinuation<R, T> {
        Task<T> onResult(R result);
    }
}
