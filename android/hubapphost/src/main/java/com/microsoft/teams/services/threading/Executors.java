/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.services.threading;

import com.microsoft.teams.logger.ILogger;
import com.microsoft.teams.logger.LogPriority;
import com.microsoft.teams.utilities.java.StringUtils;
import com.microsoft.teams.core.utilities.AppBuildConfigurationHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Comparator;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringDef;

/**
 * Helper class to create executor thread pools.
 */
public final class Executors {

    private static final String TAG = Executors.class.getSimpleName();
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAX_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final long KEEP_ALIVE_TIME = 1L;
    private static final int THREAD_PRIORITY_MEDIUM_HIGH = 7;

    private static ThreadPoolExecutor mNetworkThreadPoolExecutor;
    private static ThreadPoolExecutor mLongPollThreadPoolExecutor;
    private static ThreadPoolExecutor mSyncServiceThreadPoolExecutor;
    private static ThreadPoolExecutor mOKHTTPThreadPoolExecutor;
    private static ThreadPoolExecutor mActiveSyncThreadPoolExecutor;
    private static ThreadPoolExecutor mAuthExecutor;
    private static ThreadPoolExecutor mExtensibleAuthExecutor;
    private static ThreadPoolExecutor mBackgroundOperationsThreadPoolExecutor;

    /**
     * Should be used only from HttpCallExecutor to execute network calls. Should not be used in any other file.
     * 1. Since Network call is a IO bound task, we need to make sure that there are more threads available.
     * 2. By setting {@link java.util.concurrent.ThreadPoolExecutor#allowCoreThreadTimeOut(boolean)} as true,
     * we can be sure than once the threads are idle, they will be terminated.
     * 3. It is highly unlikely that all possible threads of all thread pools will end up doing network call.
     * Hence core pool size of set to max of 2 times the CPU_COUNT value and 8.
     *      a. For devices with CPU_CORES <= 4, core pool size is explicitly set as 8 to make sure at least 8 calls
     *      can happen in parallel.
     *      b. For devices with CPU_CORES > 4, core pool size is 2 * CPU_CORES size.
     * 4. To reduce the overhead of creating new threads, we have increased
     * {@link java.util.concurrent.ThreadPoolExecutor#setKeepAliveTime(long, TimeUnit)} (boolean)} to 5 seconds.
     * @return - a network thread pool to be used only for network calls.
     */
    public static ThreadPoolExecutor getNetworkThreadPool() {
        if (mNetworkThreadPoolExecutor == null || mNetworkThreadPoolExecutor.isShutdown()) {
            int corePoolSize = Math.max(2 * CORE_POOL_SIZE, 8);
            mNetworkThreadPoolExecutor = newThreadPool(ThreadPoolName.NETWORK_POOL,
                    corePoolSize, 2 * corePoolSize, 5L);
        }
        return mNetworkThreadPoolExecutor;
    }

    public static ThreadPoolExecutor getLongPollThreadPool() {
        if (mLongPollThreadPoolExecutor == null || mLongPollThreadPoolExecutor.isShutdown()) {
            mLongPollThreadPoolExecutor = newThreadPool(ThreadPoolName.LONG_POLL);
        }
        return mLongPollThreadPoolExecutor;
    }

    public static Executor getBreakpadThreadPool() {
        return BreakpadThreadPoolExecutor.BREAKPAD_THREAD_POOL_EXECUTOR;
    }

    public static ThreadPoolExecutor getSyncServiceThreadPool() {
        if (mSyncServiceThreadPoolExecutor == null || mSyncServiceThreadPoolExecutor.isShutdown()) {
            mSyncServiceThreadPoolExecutor = newThreadPool(ThreadPoolName.SYNC_SERVICE);
        }
        return mSyncServiceThreadPoolExecutor;
    }

    public static ThreadPoolExecutor getOkHttpCallbackExecutor() {
        if (mOKHTTPThreadPoolExecutor == null || mOKHTTPThreadPoolExecutor.isShutdown()) {
            mOKHTTPThreadPoolExecutor = newThreadPool(ThreadPoolName.OK_HTTP_CALLBACK);
        }
        return mOKHTTPThreadPoolExecutor;
    }

    /**
     * Note - DO NOT use this thread pool for non-essential UI update tasks (also, this should only be used for tasks that are based off local data, and not network data!!!!)
     *
     * This thread pool has the following:
     * 1. Increased thread priority for all threads in the pool (See {@link Thread#MIN_PRIORITY} to {@link Thread#MAX_PRIORITY}, the defined
     * value is set to {@link #THREAD_PRIORITY_MEDIUM_HIGH} = 7 which is exactly in the middle of {@link Thread#NORM_PRIORITY} and {@link Thread#MAX_PRIORITY}
     * 2. PriorityBlocking queue to prioritize tasks (callers must set this task priority relative to other tasks when the Runnable is created,
     * see {@link PriorityThreadPoolExecutor}
     *
     * @return - a high priority thread pool to be used only for user driven, local data sourced UI update tasks
     */
    public static Executor getHighPriorityViewDataThreadPool() {
        return HighPriorityViewDataThreadPoolExecutor.HIGH_PRIORITY_VIEW_DATA_THREAD_POOL_EXECUTOR;
    }

    public static ThreadPoolExecutor getActiveSyncThreadPool() {
        if (mActiveSyncThreadPoolExecutor == null || mActiveSyncThreadPoolExecutor.isShutdown()) {
            mActiveSyncThreadPoolExecutor = newThreadPool(ThreadPoolName.ACTIVE_SYNC);
        }
        return mActiveSyncThreadPoolExecutor;
    }

    public static Executor getSendMessageThreadPool() {
        return SendMessageThreadPoolExecutor.MESSAGE_SEND_THREAD_POOL_EXECUTOR;
    }

    public static Executor getCallingThreadPool() {
        return CallingThreadPoolExecutor.CALL_INITIALIZATION_POOL_EXECUTOR;
    }

    public static ThreadPoolExecutor getAuthExecutor() {
        if (mAuthExecutor == null || mAuthExecutor.isShutdown()) {
            mAuthExecutor = newThreadPool(ThreadPoolName.AUTH, 1, 1);
        }
        return mAuthExecutor;
    }

    public static ThreadPoolExecutor getExtensibilityAuthExecutor() {
        if (mExtensibleAuthExecutor == null || mExtensibleAuthExecutor.isShutdown()) {
            mExtensibleAuthExecutor = newThreadPool(ThreadPoolName.EXTENSIBILITY_AUTH_EXECUTOR, 1, 1);
        }
        return mExtensibleAuthExecutor;
    }

    public static ThreadPoolExecutor getFileServiceThreadPool() {
        return FileServiceThreadPoolExecutor.FILE_UPLOAD_THREAD_POOL_EXECUTOR;
    }

    public static Executor getNotificationThreadPoolExecutor() {
        return NotificationThreadPoolExecutor.NOTIFICATION_THREAD_POOL_EXECUTOR;
    }

    public static Executor getBetterTogetherThreadPool() {
        return BetterTogetherThreadPoolExecutor.BETTER_TOGETHER_THREAD_POOL_EXECUTOR;
    }

    public static ThreadPoolExecutor getBackgroundOperationsThreadPool() {
        if (mBackgroundOperationsThreadPoolExecutor == null || mBackgroundOperationsThreadPoolExecutor.isShutdown()) {
            mBackgroundOperationsThreadPoolExecutor = newDefaultFactoryThreadPool(CORE_POOL_SIZE, MAX_POOL_SIZE, 5L);
        }
        return mBackgroundOperationsThreadPoolExecutor;
    }

    public static void assertNotExecutingOnPool(@ThreadPoolName String poolName, boolean throwIfExecuting, @NonNull ILogger logger) {
        if (!AppBuildConfigurationHelper.isDebug() || !AppBuildConfigurationHelper.isDev()) {
            return;
        }

        String currentThreadName = Thread.currentThread().getName();
        if (currentThreadName != null && currentThreadName.startsWith(getThreadNamePrefixForPool(poolName))) {
            String error = String.format(Locale.ENGLISH, "The operation running on thread %s is running on an undesired thread pool %s.", currentThreadName, poolName);
            IllegalThreadStateException assertError = new IllegalThreadStateException(error);
            logger.log(
                    throwIfExecuting ? LogPriority.ERROR : LogPriority.WARNING,
                    "AssertNotExecutingOnPool",
                    assertError);

            if (throwIfExecuting) {
                throw assertError;
            }
        }
    }

    /***
     * Creates a new thread pool with default values for thread priority and max/min thread creation
     *
     * @param name - the name of the thread pool
     * @return - the {@link ThreadPoolExecutor} to queue tasks against
     */
    private static ThreadPoolExecutor newThreadPool(@NonNull String name) {
        return newThreadPool(name, CORE_POOL_SIZE, MAX_POOL_SIZE, Thread.NORM_PRIORITY);
    }

    /***
     * Creates a new thread pool with default values for max/min thread creation, but with a specified thread priority
     * for all threads to be created in the thread pool
     *
     * @param name - the name of the thread pool
     * @param threadPriority - the {@link Thread#MIN_PRIORITY} to {@link Thread#MAX_PRIORITY} value of which to give all newly spawned threads
     * @return - the {@link ThreadPoolExecutor} to queue tasks against
     */
    private static ThreadPoolExecutor newThreadPool(@NonNull String name, int threadPriority) {
        return newThreadPool(name, CORE_POOL_SIZE, MAX_POOL_SIZE, threadPriority);
    }

    /***
     * Creates a new thread pool with default values for thread priority, but specific values for min/max threads to be spawned in the
     * thread pool.
     *
     * @param name - the name of the thread pool
     * @param corePoolSize - the minimum number {@link #CORE_POOL_SIZE} of threads to keep alive in the thread pool even when all threads are idle
     * @param maxPoolSize - the maximum number {@link #MAX_POOL_SIZE} of threads to spawn
     * @return - the {@link ThreadPoolExecutor} to queue tasks against
     */
    private static ThreadPoolExecutor newThreadPool(@NonNull String name, int corePoolSize, int maxPoolSize) {
        return newThreadPool(name, corePoolSize, maxPoolSize, Thread.NORM_PRIORITY);
    }

    /***
     * Creates a new thread pool with default values for thread priority, but specific values for min/max threads to be spawned in the
     * thread pool and specified keepAliveTimeInSeconds.
     *
     * @param name - the name of the thread pool.
     * @param corePoolSize - the minimum number {@link #CORE_POOL_SIZE} of threads to keep alive in the thread pool even when all threads are idle.
     * @param maxPoolSize - the maximum number {@link #MAX_POOL_SIZE} of threads to spawn.
     * @param keepAliveTimeInSeconds - Threads will time out after these many seconds.
     * @return - the {@link ThreadPoolExecutor} to queue tasks against
     */
    private static ThreadPoolExecutor newThreadPool(String name, int corePoolSize, int maxPoolSize, long keepAliveTimeInSeconds) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTimeInSeconds,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new PriorityThreadFactory(name, Thread.NORM_PRIORITY));

        executor.allowCoreThreadTimeOut(true);
        return executor;
    }

    /***
     * Creates a new thread pool with default values for thread priority (see {@link Thread.NORM_PRIORITY}), but specific values for min/max threads to be spawned in the
     * thread pool.
     *
     * @param name - the name of the thread pool
     * @param corePoolSize - the minimum number {@link #CORE_POOL_SIZE} of threads to keep alive in the thread pool even when all threads are idle
     * @param maxPoolSize - the maximum number {@link #MAX_POOL_SIZE} of threads to spawn
     * @param threadPriority - the {@link Thread#MIN_PRIORITY} to {@link Thread#MAX_PRIORITY} value of which to give all newly spawned threads
     * @return - the {@link ThreadPoolExecutor} to queue tasks against
     */
    private static ThreadPoolExecutor newThreadPool(@NonNull String name, int corePoolSize, int maxPoolSize, int threadPriority) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new PriorityThreadFactory(name, threadPriority));

        executor.allowCoreThreadTimeOut(true);
        return executor;
    }

    /**
     * Internally it will use {@link java.util.concurrent.Executors} defaultThreadFactory just like Bolts does in {@link bolts.AndroidExecutors}
     * @param corePoolSize the minimum number {@link #CORE_POOL_SIZE} of threads to keep alive in the thread pool even when all threads are idle
     * @param maxPoolSize the maximum number {@link #MAX_POOL_SIZE} of threads to spawn
     * @param keepAliveTimeInSeconds Threads will time out after these many seconds.
     * @return newly created ThreadPool with java.util.concurrent.Executors.defaultThreadFactory()
     */
    private static ThreadPoolExecutor newDefaultFactoryThreadPool(int corePoolSize, int maxPoolSize, long keepAliveTimeInSeconds) {
        ThreadPoolExecutor executor =  new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTimeInSeconds, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
        executor.allowCoreThreadTimeOut(true);
        return executor;
    }

    /**
     * The priority thread pool creates a thread pool with single thread. The thread pool queue
     * will only accept one task at a time and if an attempt to queue more tasks is done then
     * a {@link RejectedExecutionException} is thrown because of the {@link ThreadPoolExecutor.AbortPolicy}
     *
     * @param name           thread name
     * @param threadPriority the priority of thread between 1 to 10, 1 is least priority
     * @return ExecutorService
     */
    private static ExecutorService newSingleThreadPriorityThreadPool(String name,
                                                                     @IntRange(from = 1, to = 10) int threadPriority) {
        return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1), new PriorityThreadFactory(name, threadPriority),
                new ThreadPoolExecutor.AbortPolicy());
    }

    private static ThreadPoolExecutor newPriorityThreadPool(@NonNull String name, @IntRange(from = 1, to = 10) int threadPriority) {
        return PriorityThreadPoolExecutor.newInstance(name, threadPriority);
    }

    private static String getThreadNamePrefixForPool(@ThreadPoolName String poolName) {
        return "Pool-" + poolName + "-Thread-";
    }

    private Executors() {
    }

    /**
     * The default thread factory.
     */
    private static class DefaultThreadFactory implements ThreadFactory {
        private ThreadIdentifier mThreadIdentifier;

        DefaultThreadFactory(@ThreadPoolName String name) {
            mThreadIdentifier = new ThreadIdentifier(name);
        }

        public Thread newThread(@NonNull Runnable r) {
            Thread t = new Thread(r, mThreadIdentifier.getThreadName());
            if (t.isDaemon()) {
                t.setDaemon(false);
            }

            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }

            return t;
        }
    }

    /**
     * Priority thread factory
     */
    private static class PriorityThreadFactory implements ThreadFactory {
        private ThreadIdentifier mThreadIdentifier;

        PriorityThreadFactory(@ThreadPoolName String name, int threadPriority) {
            mThreadIdentifier = new ThreadIdentifier(name, threadPriority);
        }

        @Override
        public Thread newThread(final @NonNull Runnable r) {
            Thread t = new Thread(r, mThreadIdentifier.getThreadName());
            t.setDaemon(false);
            t.setPriority(mThreadIdentifier.mThreadPriority);
            return t;
        }
    }


    /**
     * Class to maintain variables and methods related to thread information.
     */
    public static class ThreadIdentifier {

        private final AtomicInteger mThreadNumber = new AtomicInteger(1);

        private final int mThreadPriority;

        private final String mNamePrefix;

        @ThreadPoolName
        private final String mPoolName;

        ThreadIdentifier(String poolName) {
            this(poolName, Thread.NORM_PRIORITY);
        }

        ThreadIdentifier(@ThreadPoolName String poolName, int threadPriority) {
            mNamePrefix = getThreadNamePrefixForPool(poolName);
            mThreadPriority = threadPriority;
            mPoolName = poolName;
        }

        public ThreadIdentifier(Thread thread) {
            mPoolName = getPoolNameFromThreadName(thread.getName());
            mThreadPriority = thread.getPriority();
            mNamePrefix = getThreadNamePrefixForPool(mPoolName);
        }

        private String getThreadNamePrefixForPool(@ThreadPoolName String poolName) {
            return "Pool-" + poolName + "-Thread-";
        }

        private String getThreadName() {
            return mNamePrefix + mThreadNumber.getAndIncrement();
        }

        public String getPoolName() {
            return mPoolName;
        }

        /**
         * In the ThreadFactory, we have defined the thread name to have the below syntax.
         * "Pool-" + poolName + "-Thread-". Hence we split by "-" to obtain the pool name.
         *
         * @return Returns the pool name that the thread belongs to.
         */
        @ThreadPoolName
        private static String getPoolNameFromThreadName(@NonNull String threadName) {
            if (!StringUtils.isEmptyOrWhiteSpace(threadName) && threadName.startsWith("Pool")) {
                String[] stringArray = threadName.split("-");
                if (stringArray.length > 2) {

                    // return the pool name.
                    return stringArray[1];
                }

                // return the thread name as UNKNOWN if it does not match the "Pool-" + poolName + "-Thread-" syntax.
                return ThreadPoolName.UNKNOWN;
            }

            return ThreadPoolName.UNKNOWN;
        }
    }

    /**
     * Thread Pool executor for the work manager. This is a special thread pool that catches
     * and runtime exceptions that you can listen using the {@link ExecutorCallback}.
     * The Executor is initialized with a fixed thread pool (1) and keep alive time defined in
     * {@link #KEEP_ALIVE_TIME}
     */
    public static class WorkManagerThreadPoolExecutor extends ThreadPoolExecutor {

        static ExecutorCallback mCallback;

        /**
         * Get a new instance of the executor
         *
         * @param callback the callback to listen to any run time exceptions
         * @return a new instance of the executor
         */
        public static WorkManagerThreadPoolExecutor newInstance(@Nullable ExecutorCallback callback) {
            mCallback = callback;
            return new WorkManagerThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
                    TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
                    new DefaultThreadFactory(ThreadPoolName.WORK_MANAGER));
        }

        WorkManagerThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                      TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);

            Throwable throwable = t;

            if (throwable == null && r instanceof Future<?>) {
                try {
                    Future<?> future = (Future<?>) r;
                    if (future.isDone()) {
                        future.get();
                    }
                } catch (CancellationException ce) {
                    throwable = ce;
                } catch (ExecutionException ee) {
                    throwable = ee;
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }

            if (throwable != null && mCallback != null) {
                mCallback.executionException(throwable);
            }
        }

        /**
         * Callback to listen to execution exception
         */
        public interface ExecutorCallback {
            void executionException(Throwable throwable);
        }
    }

    /**
     * PriorityThreadPoolExecutor utilizes {@link PriorityBlockingQueue} to queue up tasks and order them using
     * the provided {@link PriorityComparator} object. Runnables must implement {@link TaskPriority}
     * to assign a task priority so that their task will get queued up based on the
     * TaskPriority they have relative to other tasks' TaskPriority.
     */
    public static class PriorityThreadPoolExecutor extends ThreadPoolExecutor {

        public static PriorityThreadPoolExecutor newInstance(@NonNull String name, @IntRange(from = 1, to = 10) int threadPriority) {
            return new PriorityThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
                    TimeUnit.SECONDS, new PriorityBlockingQueue<>(MAX_POOL_SIZE, new PriorityComparator()),
                    new PriorityThreadFactory(name, threadPriority));
        }

        PriorityThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                   TimeUnit unit, PriorityBlockingQueue<Runnable> priorityBlockingQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, priorityBlockingQueue, threadFactory);
        }

        private static class PriorityComparator implements Comparator<Runnable> {

            @Override
            public int compare(final Runnable lhs, final Runnable rhs) {
                int lhsPriority = lhs instanceof TaskPriority ? ((TaskPriority) lhs).getPriority() : 0;
                int rhsPriority = rhs instanceof TaskPriority ? ((TaskPriority) rhs).getPriority() : 0;
                long diff = lhsPriority - rhsPriority;
                return Long.signum(diff);
            }
        }
    }

    /***
     * Runnable should implement this Interface to take advantage of the PriorityBlockingQueue utilized by the {@link PriorityThreadPoolExecutor}
     * This is used to compare tasks relative to each other, based on their priority, in order to schedule them in the queue properly.
     */
    public interface TaskPriority {
        int getPriority();
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ThreadPoolName.VIEW_DATA, ThreadPoolName.NETWORK_POOL, ThreadPoolName.LONG_POLL,
            ThreadPoolName.SYNC_SERVICE, ThreadPoolName.SYNC_SERVICE_DB, ThreadPoolName.OK_HTTP_CALLBACK,
            ThreadPoolName.MESSAGE_SEND, ThreadPoolName.CALL_INITIALIZATION,
            ThreadPoolName.AUTH, ThreadPoolName.EXTENSIBILITY_AUTH_EXECUTOR, ThreadPoolName.FILE_UPLOAD_OPERATION,
            ThreadPoolName.WORK_MANAGER, ThreadPoolName.NOTIFICATION, ThreadPoolName.BETTER_TOGETHER, ThreadPoolName.UNKNOWN})
    public @interface ThreadPoolName {
        String VIEW_DATA = "ViewData";
        String NETWORK_POOL = "NetworkPool";
        String LONG_POLL = "LongPoll";
        String SYNC_SERVICE = "SyncService";
        String SYNC_SERVICE_DB = "SyncServiceDb";
        String ACTIVE_SYNC = "ActiveSync";
        String OK_HTTP_CALLBACK = "OkHttpCallback";
        String MESSAGE_SEND = "SendMessage";
        String CALL_INITIALIZATION = "CallInitialization";
        String AUTH = "Auth";
        String EXTENSIBILITY_AUTH_EXECUTOR = "ExtensibilityAuth";
        String BREAKPAD = "Breakpad";
        String FILE_UPLOAD_OPERATION = "FileUploadOperation";
        String WORK_MANAGER = "WorkManager";
        String NOTIFICATION = "Notification";
        String BETTER_TOGETHER = "BetterTogether";
        String UNKNOWN = "Unknown";
    }

    /** Static classes for ThreadPoolExecutors to avoid all the thread pools instantiating at app launch and only instantiate as per usage **/

    private static class BreakpadThreadPoolExecutor {
        private static final ThreadPoolExecutor BREAKPAD_THREAD_POOL_EXECUTOR = newThreadPool(ThreadPoolName.BREAKPAD);
    }

    private static class HighPriorityViewDataThreadPoolExecutor {
        private static final ThreadPoolExecutor HIGH_PRIORITY_VIEW_DATA_THREAD_POOL_EXECUTOR = newPriorityThreadPool(ThreadPoolName.VIEW_DATA, THREAD_PRIORITY_MEDIUM_HIGH);
    }

    private static class SendMessageThreadPoolExecutor {
        private static final ThreadPoolExecutor MESSAGE_SEND_THREAD_POOL_EXECUTOR = newThreadPool(ThreadPoolName.MESSAGE_SEND);
    }

    private static class CallingThreadPoolExecutor {
        private static final ThreadPoolExecutor CALL_INITIALIZATION_POOL_EXECUTOR = newThreadPool(ThreadPoolName.CALL_INITIALIZATION);
    }

    private static class FileServiceThreadPoolExecutor {
        private static final ThreadPoolExecutor FILE_UPLOAD_THREAD_POOL_EXECUTOR = newThreadPool(ThreadPoolName.FILE_UPLOAD_OPERATION);
    }

    private static class NotificationThreadPoolExecutor {
        private static final ThreadPoolExecutor NOTIFICATION_THREAD_POOL_EXECUTOR = newThreadPool(ThreadPoolName.NOTIFICATION);
    }

    private static class BetterTogetherThreadPoolExecutor {
        private static final ExecutorService BETTER_TOGETHER_THREAD_POOL_EXECUTOR = newThreadPool(ThreadPoolName.BETTER_TOGETHER);
    }
}
