/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.utilities;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.annotation.RequiresApi;

import com.microsoft.skype.teams.services.utilities.StringUtilities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Contains helper methods for I/O related operations
 */
public final class IOUtilities {
    private static final int BUFFER_SIZE = 1024;

    /**
     * Gets list of all file names in the path inside assets folder
     */
    @NonNull
    public static String[] getFileNamesFromAssetsFolder(@NonNull Context context, @NonNull String path) throws IOException {
        String[] fileNames = context.getAssets().list(path);
        return fileNames != null ? fileNames : new String[0];
    }

    /**
     * Gets content from a file inside app assets folder.
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    public static String getContentFromAssetsFile(@NonNull Context context, @NonNull String fileName) throws IOException {
        return readContent(new InputStreamReader(context.getAssets().open(fileName), StandardCharsets.UTF_8));
    }

    /**
     * Gets content from an app packaged raw resource file.
     */
    public static String getContentFromRawFile(@NonNull Context context, @RawRes int resourceId) throws IOException {
        InputStreamReader reader = new InputStreamReader(
                context.getResources().openRawResource(resourceId),
                StringConstants.CHARSET_UTF_8);

        return readContent(reader);
    }

    /**
     * Unzips the specified zipped input stream to the specified directory.
     */
    public static void unzipFile(@NonNull InputStream inputStream, @NonNull File directory) throws IOException {
        ZipInputStream zipInputStream = null;
        ZipEntry zipEntry;
        try {
            ensureEmptyDirectory(directory);
            zipInputStream = new ZipInputStream(inputStream);
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                String name = getValidatedFilenameForZipEntry(zipEntry.getName(), ".");
                if (zipEntry.isDirectory()) {
                    ensureEmptyDirectory(new File(directory, name));
                } else {
                    File outputFile = new File(directory, name);
                    outputFile.getParentFile().mkdirs();
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputFile), BUFFER_SIZE);

                    int readBytes;
                    byte readBuffer[] = new byte[BUFFER_SIZE];
                    while ((readBytes = zipInputStream.read(readBuffer, 0, BUFFER_SIZE)) != -1) {
                        bufferedOutputStream.write(readBuffer, 0, readBytes);
                    }

                    zipInputStream.closeEntry();
                    bufferedOutputStream.close();
                }
            }
        } finally {
            if (zipInputStream != null) {
                zipInputStream.close();
            }
        }
    }

    private static String getValidatedFilenameForZipEntry(@NonNull String filename, @NonNull String intendedDir) throws IOException {
        File f = new File(filename);
        String canonicalPath = f.getCanonicalPath();

        File rootFile = new File(intendedDir);
        String rootCanonicalPath = rootFile.getCanonicalPath();

        if (canonicalPath.startsWith(rootCanonicalPath)) {
            return canonicalPath;
        } else {
            throw new IllegalStateException("File is outside extraction target directory.");
        }
    }

    public static String readFileContent(@Nullable File file) throws IOException {
        if (file == null || !file.exists()) {
            return null;
        }

        InputStream inputStream = new FileInputStream(file);
        return readContent(new InputStreamReader(inputStream, "UTF-8"));
    }

    @Nullable
    public static File checkAndCreateFile(@NonNull Context context, @NonNull String dirPath, @NonNull String fileName) {
        // We use internal temp dir to store outlook attachment.
        File dir = new File(context.getFilesDir(), dirPath);
        if (dir.exists() || dir.mkdirs()) {
            return new File(dir, fileName);
        }
        return null;
    }

    public static void writeFileContent(@NonNull File file, @NonNull String content) throws IOException {
        if (!file.exists() && ((!file.getParentFile().exists() && !file.getParentFile().mkdirs()) || !file.createNewFile())) {
            throw new IOException("Unable to create " + file.getName());
        }

        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes(Charset.forName(StringConstants.CHARSET_UTF_8)));
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    public static void writeFileContentByDecode(@NonNull File file, @NonNull String content) throws IOException {
        if (!file.exists() && ((!file.getParentFile().exists() && !file.getParentFile().mkdirs()) || !file.createNewFile())) {
            throw new IOException("Unable to create " + file.getName());
        }

        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            byte[] decodedBytes = android.util.Base64.decode(content, android.util.Base64.DEFAULT);
            outputStream.write(decodedBytes);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    /**
     * Reads content from the given reader
     */
    private static String readContent(@NonNull Reader reader) throws IOException {
        StringBuilder buffer = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            final String lineSeparator = StringUtilities.getSystemNewLine();
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line).append(lineSeparator);
            }
        }

        return buffer.toString();
    }

    private static void ensureEmptyDirectory(@NonNull File directory) {
        if (directory.exists()) {
            deleteFileOrFolderSilently(directory);
        }

        directory.mkdirs();
    }

    /**
     * Copies the contents of one directory to another recursively.
     */
    public static void copyDirectory(File sourceDir, File destDir) throws IOException {
        if (!destDir.exists() && !destDir.mkdirs()) {
            throw new IOException("Unable to copy file from " + sourceDir.getAbsolutePath() + " to " + destDir.getAbsolutePath() + ". Error creating directory.");
        }

        File[] sourceFiles = sourceDir.listFiles();
        if (sourceFiles == null) {
            throw new IOException("Pathname " + sourceDir.getPath() + " doesn't denote a directory.");
        }

        for (File sourceFile : sourceFiles) {
            if (sourceFile.isDirectory()) {
                copyDirectory(new File(sourceDir.getPath(), sourceFile.getName()), new File(destDir.getPath(), sourceFile.getName()));
            } else {
                InputStream sourceFileInputStream = null;
                FileOutputStream destinationFileOutputStream = null;
                try {
                    File destFile = new File(destDir, sourceFile.getName());
                    sourceFileInputStream = new BufferedInputStream(new FileInputStream(sourceFile));
                    destinationFileOutputStream = new FileOutputStream(destFile);
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int bytesRead;
                    while ((bytesRead = sourceFileInputStream.read(buffer)) > 0) {
                        destinationFileOutputStream.write(buffer, 0, bytesRead);
                    }
                } finally {
                    if (sourceFileInputStream != null) {
                        sourceFileInputStream.close();
                    }

                    if (destinationFileOutputStream != null) {
                        destinationFileOutputStream.close();
                    }
                }
            }
        }
    }

    /**
     * Deletes directory located by the following path.
     */
    public static void deleteDirectory(String directoryPath) throws IOException {
        if (directoryPath == null) {
            throw new IOException("Directory cannot be null.");
        }
        File file = new File(directoryPath);
        if (file.exists()) {
            deleteFileOrFolderSilently(file);
        }
    }

    /**
     * Deletes file or folder throwing no errors if something goes wrong.
     */
    @SuppressWarnings("WeakerAccess")
    public static void deleteFileOrFolderSilently(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                return;
            }

            for (File fileEntry : files) {
                if (fileEntry.isDirectory()) {
                    deleteFileOrFolderSilently(fileEntry);
                } else {
                    if (!fileEntry.delete()) {
                        return;
                    }
                }
            }
        }

        file.delete();
    }

    public static void copyFileorDirFromAssets(final Context context , String destinationDirectory, String currentDirectory) throws IOException {
        String assets[] = null;
        try {
            assets = context.getAssets().list(currentDirectory);
            if (assets.length == 0) {
                copyFile(context , destinationDirectory, currentDirectory);
            } else {
                File dir = new File(destinationDirectory);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                for (int i = 0; i < assets.length; i++) {
                    copyFileorDirFromAssets(context, destinationDirectory + "/" + assets[i] , currentDirectory + "/" + assets[i]);
                }
            }
        } catch (IOException e) {
            throw new IOException("Unable to copy assets");
        }
    }

    private static void copyFile(final Context context, String destination, String sourceFile) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = context.getAssets().open(sourceFile);
            File f = new File(destination);
            f.getParentFile().mkdirs();
            f.createNewFile();
            out = new FileOutputStream(f, false);

            byte[] buffer = new byte[BUFFER_SIZE];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            throw new IOException("Unable to copy assets");
        }
    }

    private IOUtilities() {
    }
}
