/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.data;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.teams.services.authorization.ErrorType;
import com.microsoft.teams.services.diagnostics.StatusCode;
// import com.microsoft.teams.core.R;

/** Represents base exception */
public abstract class BaseException extends Exception {

    @StatusCode.StatusCodeValue
    protected String mErrorCode;
    protected String mDependencySrc = null;
    protected Throwable mInnerException;
    protected String mErrorType = ErrorType.CODE_ERROR;

    public BaseException(@NonNull @StatusCode.StatusCodeValue String errorCode, @Nullable Throwable throwable) {
        super(throwable);
        mErrorCode = errorCode;
    }

    public BaseException(@NonNull @StatusCode.StatusCodeValue String errorCode,
                         @Nullable Throwable throwable,
                         @Nullable String dependencySource) {
        this(errorCode, throwable);
        mDependencySrc = dependencySource;
    }

    public BaseException(@NonNull @StatusCode.StatusCodeValue String errorCode, @NonNull String errorMessage) {
        super(errorMessage);
        mErrorCode = errorCode;
    }

    public BaseException(@NonNull @StatusCode.StatusCodeValue String errorCode,
                         @NonNull String errorMessage,
                         @Nullable String dependencySource) {
        this(errorCode, errorMessage);
        mDependencySrc = dependencySource;
    }

    @NonNull
    @StatusCode.StatusCodeValue
    public String getErrorCode() {
        return mErrorCode;
    }

    public String getErrorType() {
        return mErrorType;
    }

    public Throwable getInnerException() {
        return mInnerException;
    }

    @Nullable
    public String getDependencySrc() {
        return mDependencySrc;
    }

    public String getUiErrorMessage(@NonNull Context context) {
        final String message = getMessage();
        if (message == null) {
            // return context.getString(R.string.unknown_error_title);
            return "context.getString(R.string.unknown_error_title)";
        } else {
            return message;
        }
    }

    public boolean isUIRequiredError() {
        if (matchesKnownError(mErrorCode, StatusCode.NO_ACCOUNT_FOUND)
                || matchesKnownError(mErrorCode, StatusCode.PASSWORD_RESET)
                || matchesKnownError(mErrorCode, StatusCode.PROMPT_REQUIRED)
                || matchesKnownError(mErrorCode, StatusCode.ACCOUNT_IS_NULL)
                || matchesKnownError(mErrorCode, StatusCode.AUTH_UI_REQUIRED)) {
            return true;
        }

        return  false;
    }

    protected boolean matchesKnownError(@Nullable String error, @NonNull String knownError) {
        return error != null && (knownError.equalsIgnoreCase(error));
    }

    protected boolean matchesKnownError(@NonNull Throwable throwable, @NonNull String knownError) {
        return throwable.getMessage().contains(knownError) || (mErrorCode != null && mErrorCode.contains(knownError));
    }

    public boolean isTransientError() {
        return StatusCode.IO_EXCEPTION.equalsIgnoreCase(mErrorCode)
                || StatusCode.NETWORK_UNAVAILABLE.equalsIgnoreCase(mErrorCode)
                || StatusCode.SOCKET_TIMEOUT.equalsIgnoreCase(mErrorCode);
    }
}
