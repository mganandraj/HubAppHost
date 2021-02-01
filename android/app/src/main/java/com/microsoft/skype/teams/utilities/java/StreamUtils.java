/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.utilities.java;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utility methods for working with streams
 */
public final class StreamUtils {
    static final int STREAM_BUFFER_SIZE = 8192;

    private StreamUtils() {
    }

    /**
     * Wraps a stream in a BufferedInputStream
     *
     * @param inputStream Input stream to buffer
     * @return inputStream wrapped in a BufferedInputStream, unless the input is already a ByteArrayInputStream
     * or BufferdInputStream
     */
    @NonNull
    public static InputStream bufferStreamIfRequired(@NonNull InputStream inputStream) {
        if (inputStream instanceof ByteArrayInputStream || inputStream instanceof BufferedInputStream) {
            return inputStream;
        }
        return new BufferedInputStream(inputStream);
    }

    /**
     * Copies a stream to a new stream
     *
     * @param outputStream Stream to which to copy the stream
     * @param inputStream  Stream to copy
     * @throws IOException Thrown if there is an issue copying the stream
     */
    public static void copy(@NonNull OutputStream outputStream, @NonNull InputStream inputStream) throws IOException {
        copy(outputStream, inputStream, STREAM_BUFFER_SIZE);
    }

    /**
     * Copies a stream to a new stream
     *
     * @param outputStream Stream to which to copy the stream
     * @param inputStream  Stream to copy
     * @param bufferSize   Buffer size for the copy operation
     * @throws IOException Thrown if there is an issue copying the stream
     */
    public static void copy(@NonNull OutputStream outputStream, @NonNull InputStream inputStream, int bufferSize) throws IOException {
        int bufferSizeToUse = bufferSize;
        if (bufferSizeToUse <= 0) {
            bufferSizeToUse = STREAM_BUFFER_SIZE;
        }

        byte[] buf = new byte[bufferSizeToUse];
        int len;

        while ((len = inputStream.read(buf, 0, buf.length)) != -1) {
            outputStream.write(buf, 0, len);
        }
    }

    /**
     * Copies a stream to a new stream
     *
     * @param outputStream Stream to which to copy the stream
     * @param inputStream  Stream to copy
     * @param progress     Callback to notify of copying progress
     * @throws IOException Thrown if there is an issue copying the stream
     */
    public static void copy(@NonNull OutputStream outputStream, @NonNull InputStream inputStream, @Nullable CopyProgress progress) throws IOException {
        copy(outputStream, inputStream, STREAM_BUFFER_SIZE, progress);
    }

    /**
     * Copies a stream to a new stream
     *
     * @param outputStream Stream to which to copy the stream
     * @param inputStream  Stream to copy
     * @param bufferSize   Buffer size for the copy operation
     * @param progress     Callback to notify of copying progress
     * @throws IOException Thrown if there is an issue copying the stream
     */
    public static void copy(@NonNull OutputStream outputStream, @NonNull InputStream inputStream, int bufferSize, @Nullable CopyProgress progress) throws IOException {
        if (progress == null) {
            copy(outputStream, inputStream, bufferSize);

            return;
        }

        int bufferSizeToUse = bufferSize;
        if (bufferSizeToUse <= 0) {
            bufferSizeToUse = STREAM_BUFFER_SIZE;
        }

        int totalSize = inputStream.available(); // Not a very good indication of the buffers size.
        int bytesRead = 0;

        byte[] buf = new byte[bufferSizeToUse];
        int len;

        while ((len = inputStream.read(buf, 0, buf.length)) != -1) {
            outputStream.write(buf, 0, len);

            bytesRead += len;
            progress.update(bytesRead, totalSize);
        }
    }

    /**
     * Closes a stream while suppressing exceptions due to the stream already being closed
     *
     * @param items Streams to close
     */
    public static void closeSilent(@Nullable Closeable... items) {
        if (items == null || items.length <= 0) {
            return;
        }

        for (Closeable current : items) {
            closeSilent(current);
        }
    }

    /**
     * Closes a stream while suppressing exceptions due to the stream already being closed
     *
     * @param item Stream to close
     */
    public static void closeSilent(@Nullable Closeable item) {
        if (item == null) {
            return;
        }

        try {
            item.close();
        } catch (Exception x) {
            // Do nothing; stream may have already been closed
        }
    }

    /**
     * Callback to notify of progress when copying a strema
     */
    public interface CopyProgress {
        /**
         * Update the callback of the copy operation progress
         *
         * @param bytesRead Number of bytes copied so far
         * @param totalSize Total number of bytes to copy
         */
        void update(int bytesRead, int totalSize);
    }
}
