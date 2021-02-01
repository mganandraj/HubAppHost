/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk.models.params;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.microsoft.skype.teams.sdk.react.ReadableMapUtilities;
import com.microsoft.skype.teams.utilities.StringConstants;
import com.microsoft.skype.teams.utilities.java.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.zip.GZIPOutputStream;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Represents the parameters for an HTTP request.
 */
public final class SdkHttpRequestParams {
    @NonNull
    public final String requestId;

    @NonNull
    public final String serviceName;

    @NonNull
    public final String requestName;

    @NonNull
    public final String method;

    @NonNull
    public final String url;

    @NonNull
    public final Headers headers;

    @Nullable
    public final RequestBody body;

    @NonNull
    public static SdkHttpRequestParams fromReactNativeMap(@Nullable ReadableMap args) throws Exception {
        if (args == null) {
            throw new Exception("Invalid request. Nothing specified.");
        }

        String requestId = readStringArgument(args, "requestId");
        String serviceName = readStringArgument(args, "serviceName");
        String requestName = readStringArgument(args, "requestName");
        String method = readStringArgument(args, "method");
        String url = readStringArgument(args, "url");
        Headers headers = readHeaders(args);
        RequestBody requestBody = null;
        if (!method.equalsIgnoreCase("get") && !method.equalsIgnoreCase("head")) {
            requestBody = readBody(args, headers);
        }

        return new SdkHttpRequestParams(requestId, serviceName, requestName, method, url, headers, requestBody);
    }

    private static RequestBody readBody(@NonNull ReadableMap args, @Nullable Headers headers) throws Exception {
        String body = ReadableMapUtilities.getString(args, "body");
        if (StringUtils.isEmpty(body)) {
            return null;
        }

        String contentType = headers != null ? headers.get(com.microsoft.skype.teams.utilities.Headers.CONTENT_TYPE) : null;
        if (contentType == null) {
            throw new Exception("Request body is set but content-type header is not specified.");
        }

        MediaType contentMediaType = MediaType.parse(contentType);
        String contentEncoding = headers.get(com.microsoft.skype.teams.utilities.Headers.CONTENT_ENCODING);
        if ("gzip".equalsIgnoreCase(contentEncoding)) {
            ByteArrayOutputStream gzipByteArrayOutputStream = new ByteArrayOutputStream();
            OutputStream gzipOutputStream = new GZIPOutputStream(gzipByteArrayOutputStream);
            gzipOutputStream.write(body.getBytes(Charset.forName(StringConstants.CHARSET_UTF_8)));
            gzipOutputStream.close();
            return RequestBody.create(contentMediaType, gzipByteArrayOutputStream.toByteArray());
        }

        return RequestBody.create(contentMediaType, body);
    }

    @NonNull
    public static Headers readHeaders(@Nullable ReadableMap args) throws Exception {
        Headers.Builder headersBuilder = new Headers.Builder();
        if (args == null) {
            return headersBuilder.build();
        }

        ReadableMap headers = ReadableMapUtilities.getMap(args, "headers");
        if (headers == null) {
            return headersBuilder.build();
        }

        ReadableMapKeySetIterator iterator = headers.keySetIterator();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();

            // Attempt to get single value
            String value = ReadableMapUtilities.getString(headers, key);
            if (value != null) {
                headersBuilder.add(key, value);
                continue;
            }

            // If not single value, attempt to get array of values
            ReadableArray values = ReadableMapUtilities.getArray(headers, key);
            if (values == null) {
                throw new Exception("Header value must be specified as a single string value or an array of string values.");
            }

            for (int i = 0; i < values.size(); ++i) {
                if (values.getType(i) != ReadableType.String) {
                    throw new Exception("Header value must be a string");
                }

                headersBuilder.add(key, values.getString(i));
            }
        }

        return headersBuilder.build();
    }

    @NonNull
    private static String readStringArgument(@NonNull ReadableMap args, @NonNull String argName) {
        String argValue = ReadableMapUtilities.getString(args, argName);
        if (argValue == null) {
            throw new IllegalArgumentException(argName + " is not specified for HTTP request.");
        }

        return argValue;
    }

    private SdkHttpRequestParams(@NonNull String requestId,
                                 @NonNull String serviceName,
                                 @NonNull String requestName,
                                 @NonNull String method,
                                 @NonNull String url,
                                 @NonNull Headers headers,
                                 @Nullable RequestBody body) {
        this.requestId = requestId;
        this.serviceName = serviceName;
        this.requestName = requestName;
        this.method = method;
        this.url = url;
        this.headers = headers;
        this.body = body;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "RequestId: %s, RequestName: %s, ServiceName: %s.", requestId, requestName, serviceName);
    }
}
