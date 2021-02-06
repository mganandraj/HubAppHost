package com.microsoft.codepush.common.apirequests;

import android.util.Log;

import com.microsoft.codepush.common.exceptions.CodePushApiHttpRequestException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Represents request to CodePush server.
 *
 * @param <T> result of execution of request.
 */
public class ApiHttpRequest<T> {

    /**
     * Log tag
     */
    private static final String LOG_TAG = "CodePushHttpRequest";

    /**
     * Thread pool
     */
    private static ExecutorService httpRequestExecutor;

    /**
     * Task for making request.
     */
    private BaseHttpTask<T> mRequestTask;

    /**
     * Creates an instance of {@link ApiHttpRequest}.
     *
     * @param mRequestTask Task for making request.
     */
    public ApiHttpRequest(BaseHttpTask<T> mRequestTask) {
        this.mRequestTask = mRequestTask;
    }

    /**
     * Makes request to CodePush server.
     *
     * @return result of execution of request.
     * @throws CodePushApiHttpRequestException if there was error during the execution of request.
     */
    public T makeRequest() throws CodePushApiHttpRequestException {
        Future<T> future = getExecutor().submit(mRequestTask);
        Log.d(LOG_TAG, "SCHEDULED request. future:" + future.toString());
        T taskResult;
        try {
            taskResult = future.get();
            Log.d(LOG_TAG, "FINISHED request. future:" + future.toString());
        } catch (InterruptedException | ExecutionException e) {
            Log.w(LOG_TAG, "EXCEPTION on request. future:" + future.toString() + " exception: " + e.toString());
            throw new CodePushApiHttpRequestException(e);
        }
        CodePushApiHttpRequestException innerException = mRequestTask.getInnerException();
        if (innerException != null) {
            throw innerException;
        }
        return taskResult;
    }

    /**
     * Executor getter with lazy create
     *
     */
    private static ExecutorService getExecutor() {
        synchronized (ApiHttpRequest.class) {
            if (httpRequestExecutor == null) {
                httpRequestExecutor = Executors.newCachedThreadPool();
            }
        }

        return httpRequestExecutor;
    }
}