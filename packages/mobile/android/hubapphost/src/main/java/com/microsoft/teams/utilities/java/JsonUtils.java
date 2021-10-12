/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.utilities.java;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Utility methods to work with Json data
 */
public final class JsonUtils {
    public static final Gson GSON = new Gson();
    public static final Gson GSON_PRETTY_PRINT = new GsonBuilder().setPrettyPrinting().create();
    private static final Gson GSON_ENABLE_COMPLEX_MAP_KEY_SERIALIZATION = new GsonBuilder().enableComplexMapKeySerialization().create();
    private static final Gson GSON_EXCLUDE_FILEDS_WITHOUT_EXPOSE_ANNOTATION = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private static final Gson GSON_DISABLE_HTML_ESCAPING = new GsonBuilder().disableHtmlEscaping().create();
    public static final Gson GSON_SERIALIZE_NULLS = new GsonBuilder().serializeNulls().create();
    private static final String[] DATE_FORMATS = new String[]{
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ",
            "yyyy-MM-dd'T'HH:mm:ssZ",
            "yyyy-MM-dd'T'HH:mm:ss",
            "MMM d, yyyy hh:mm:ss aaa",
            "MMM d, yyyy hh:mm:ss",
    };

    private JsonUtils() {
        throw new UtilityInstantiationException();
    }

    public static <T> T parseObject(@Nullable String objectJson, @Nullable Class<T> objectClass, @Nullable T defaultValue) {
        try {
            return GSON.fromJson(objectJson, objectClass);
        } catch (Exception ex) {
            // STUB
        }

        return defaultValue;
    }

    public static <T> T parseObject(@Nullable String objectJson, @Nullable Type type, @Nullable T defaultValue) {
        try {
            return GSON.fromJson(objectJson, type);
        } catch (Exception ex) {
            // STUB
        }

        return defaultValue;
    }

    public static <T> T parseObject(JsonElement jsonElement, Class<T> objectClass, T defaultValue) {
        try {
            return GSON.fromJson(jsonElement, objectClass);
        } catch (Exception ex) {
            // STUB
        }

        return defaultValue;
    }

    public static boolean isNullOrEmpty(JsonObject jsonObject) {
        return (jsonObject == null || (jsonObject.entrySet().isEmpty()));
    }

    public static boolean isNullOrEmpty(JsonArray jsonArray) {
        return (jsonArray == null || (jsonArray.size() == 0));
    }

    @Nullable
    public static JsonArray parseArray(@Nullable JsonElement jsonElement, @NonNull String jPath) {
        JsonArray parsedArray = null;
        if (jsonElement != null && jsonElement.isJsonObject()) {
            JsonObject jObject = jsonElement.getAsJsonObject();
            if (jObject.has(jPath) && jObject.get(jPath).isJsonArray()) {
                parsedArray = jObject.getAsJsonArray(jPath);
            }
        }

        return parsedArray;
    }

    @Nullable
    public static JsonArray parseDeepArray(@Nullable JsonElement jsonElement, @Nullable String fullPath) {
        JsonObject object = jsonElement != null && jsonElement.isJsonObject() ? jsonElement.getAsJsonObject() : null;
        if (!StringUtils.isEmptyOrWhiteSpace(fullPath)) {
            String[] parts = fullPath.split("\\.");
            for (int i = 0; i < parts.length - 1; i++) {
                object = parseObject(object, parts[i]);
            }
            return parseArray(object, parts[parts.length - 1]);
        }
        return null;
    }

    public static boolean parseBoolean(JsonElement jsonElement, String jPath) {
        return parseBoolean(jsonElement, jPath, false);
    }

    public static boolean parseBoolean(JsonElement jsonElement, String jPath, boolean defaultValue) {
        boolean result = defaultValue;

        if (jsonElement != null && jsonElement.isJsonObject()) {
            JsonObject jObject = jsonElement.getAsJsonObject();
            if (jObject.has(jPath) && jObject.get(jPath).isJsonPrimitive()) {
                result = jObject.getAsJsonPrimitive(jPath).getAsBoolean();
            }
        }

        return result;
    }

    public static String parseString(JsonElement jsonElement, String jPath) {
        return parseString(jsonElement, jPath, "");
    }

    public static String parseDeepString(JsonElement jsonElement, String fullPath) {
        JsonObject object = jsonElement != null && jsonElement.isJsonObject() ? jsonElement.getAsJsonObject() : null;
        String[] parts = fullPath.split("\\.");
        for (int i = 0; i < parts.length - 1; i++) {
            object = parseObject(object, parts[i]);
        }
        return parseString(object, parts[parts.length - 1]);
    }

    public static boolean parseDeepBoolean(@Nullable JsonElement jsonElement, @NonNull String fullPath) {
        JsonObject object = jsonElement != null && jsonElement.isJsonObject() ? jsonElement.getAsJsonObject() : null;
        String[] parts = fullPath.split("\\.");
        for (int i = 0; i < parts.length - 1; i++) {
            object = parseObject(object, parts[i]);
        }
        return parseBoolean(object, parts[parts.length - 1]);
    }

    @NonNull
    public static String parseString(@Nullable JsonElement jsonElement, @NonNull String jPath, @NonNull String defaultValue) {
        String parsedString = defaultValue;
        if (jsonElement != null && jsonElement.isJsonObject()) {
            JsonObject jObject = jsonElement.getAsJsonObject();
            if (jObject.has(jPath) && jObject.get(jPath).isJsonPrimitive()) {
                parsedString = jObject.getAsJsonPrimitive(jPath).getAsString();
            }
        }

        return parsedString;
    }

    public static JsonObject parseObject(JsonElement jsonElement, String jPath) {
        JsonObject jsonObject = null;
        if (jsonElement != null && jsonElement.isJsonObject()) {
            JsonObject jObject = jsonElement.getAsJsonObject();
            if (jObject.has(jPath) && jObject.get(jPath).isJsonObject()) {
                jsonObject = jObject.getAsJsonObject(jPath);
            }
        }

        return jsonObject;
    }

    public static int parseInt(JsonElement jsonElement, String jPath) {
        return parseInt(jsonElement, jPath, 0);
    }

    public static int parseInt(JsonElement jsonElement, String jPath, int defaultValue) {
        int parsedInt = defaultValue;
        if (jsonElement != null && jsonElement.isJsonObject()) {
            JsonObject jObject = jsonElement.getAsJsonObject();
            if (jObject.has(jPath) && jObject.get(jPath).isJsonPrimitive()) {
                try {
                    parsedInt = jObject.getAsJsonPrimitive(jPath).getAsInt();
                } catch (NumberFormatException e) {
                    // Eat the integer parsing exception.
                }
            }
        }

        return parsedInt;
    }

    public static short parseShort(@Nullable JsonElement jsonElement, @NonNull String jPath) {
        return parseShort(jsonElement, jPath, (short) 0);
    }

    public static short parseShort(@Nullable JsonElement jsonElement, @NonNull String jPath, short defaultValue) {
        short parserShort = defaultValue;
        if (jsonElement != null && jsonElement.isJsonObject()) {
            JsonObject jObject = jsonElement.getAsJsonObject();
            if (jObject.has(jPath) && jObject.get(jPath).isJsonPrimitive()) {
                try {
                    parserShort = jObject.getAsJsonPrimitive(jPath).getAsShort();
                } catch (NumberFormatException e) {
                    // Eat the integer parsing exception.
                }
            }
        }

        return parserShort;
    }

    public static long parseLong(JsonElement jsonElement, String jPath) {
        long parsedLong = 0;
        if (jsonElement != null && jsonElement.isJsonObject()) {
            JsonObject jObject = jsonElement.getAsJsonObject();
            if (jObject.has(jPath) && jObject.get(jPath).isJsonPrimitive()) {
                try {
                    parsedLong = jObject.getAsJsonPrimitive(jPath).getAsLong();
                } catch (NumberFormatException e) {
                    // Eat the long parsing exception.
                }
            }
        }

        return parsedLong;
    }

    public static char parseCharacter(JsonElement jsonElement, String jPath) {
        char parsedCharacter = 0;
        if (jsonElement != null && jsonElement.isJsonObject()) {
            JsonObject jObject = jsonElement.getAsJsonObject();
            if (jObject.has(jPath) && jObject.get(jPath).isJsonPrimitive()) {
                try {
                    parsedCharacter = jObject.getAsJsonPrimitive(jPath).getAsCharacter();
                } catch (NumberFormatException e) {
                    // Eat the character parsing exception.
                }
            }
        }

        return parsedCharacter;
    }

    public static Date parseDate(JsonElement jsonElement, String jPath) {
        return parseDate(jsonElement, jPath, TimeZone.getTimeZone("UTC"));
    }

    public static Date parseDate(JsonElement jsonElement, String jPath, TimeZone timeZone) {
        String parsedString = parseString(jsonElement, jPath);
        return getDateFromJsonString(parsedString, timeZone);
    }

    public static long parseTimestamp(JsonElement jsonElement, String jPath) {
        return parseTimestamp(jsonElement, jPath, TimeZone.getTimeZone("UTC"));
    }

    public static long parseTimestamp(JsonElement jsonElement, String jPath, TimeZone timeZone) {
        Date parsedDate = parseDate(jsonElement, jPath, timeZone);
        return parsedDate != null ? parsedDate.getTime() : 0;
    }

    public static Date getDateFromJsonString(String dateString) {
        return getDateFromJsonString(dateString, TimeZone.getDefault());
    }

    public static Object getObjectFromString(JsonElement jsonObject, Type type) {
        Object result = null;
        try {
            result = GSON.fromJson(jsonObject, type);
        } catch (Exception ex) {

        }

        return result;
    }

    public static Date getDateFromJsonString(String dateString, TimeZone timeZone) {
        Date date = null;
        if (!StringUtils.isEmpty(dateString)) {
            for (String format : DATE_FORMATS) {
                SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.US);
                formatter.setTimeZone(timeZone);
                try {
                    date = formatter.parse(dateString);
                    break;
                } catch (ParseException ex) {
                    // Eat the parse exception.
                }
            }
        }

        return date;
    }

    public static String getJsonStringFromObject(Object obj) {
        return getJsonStringFromObject(obj, false);
    }

    public static String getJsonStringFromObject(Object obj, boolean excludeNonExposed) {
        if (obj != null) {
            Gson gson = excludeNonExposed ? GSON_EXCLUDE_FILEDS_WITHOUT_EXPOSE_ANNOTATION : GSON;
            return gson.toJson(obj);
        }

        return "";
    }

    public static @Nullable
    JsonElement getJsonFromObject(@Nullable Object obj, boolean excludeNonExposed) {
        if (obj != null) {
            Gson gson = excludeNonExposed ? GSON_EXCLUDE_FILEDS_WITHOUT_EXPOSE_ANNOTATION : GSON;
            return gson.toJsonTree(obj);
        }
        return null;
    }

    public static JsonArray getJsonArrayFromObject(JsonElement jsonElement, String jPath) {
        try {
            JsonArray objArray = null;
            if (jsonElement != null && jsonElement.isJsonObject()
                    && jsonElement.getAsJsonObject().has(jPath)
                    && jsonElement.getAsJsonObject().get(jPath).isJsonArray()) {
                objArray = jsonElement.getAsJsonObject().get(jPath).getAsJsonArray();
            }

            return objArray;
        } catch (Exception ex) {
            // STUB
        }

        return null;
    }

    public static JsonElement getJsonElementFromString(String json) {
        if (!StringUtils.isEmptyOrWhiteSpace(json)) {
            try {
                return GSON.fromJson(json, JsonElement.class);
            } catch (JsonSyntaxException jse) {
                // STUB
            }
        }

        return null;
    }

    @Nullable
    public static JsonObject getJsonObjectFromString(String json) {
        if (!StringUtils.isEmptyOrWhiteSpace(json)) {
            try {
                JsonElement jsonElement = GSON.fromJson(json, JsonElement.class);
                return jsonElement != null && jsonElement.isJsonObject() ? jsonElement.getAsJsonObject() : null;
            } catch (JsonSyntaxException jse) {
                // STUB
            }
        }

        return null;
    }

    public static JsonArray getJsonArrayFromString(String json) {
        if (!StringUtils.isEmptyOrWhiteSpace(json)) {
            try {
                JsonElement jsonElement = GSON.fromJson(json, JsonElement.class);
                return jsonElement != null && jsonElement.isJsonArray() ? jsonElement.getAsJsonArray() : null;
            } catch (Exception exception) {
                // STUB
            }
        }

        return null;
    }

    @Nullable
    public static Object[] getArrayFromString(@Nullable String json) {
        if (!StringUtils.isEmptyOrWhiteSpace(json)) {
            try {
                return GSON_ENABLE_COMPLEX_MAP_KEY_SERIALIZATION.fromJson(json, Object[].class);
            } catch (Exception exception) {
                // STUB
            }
        }

        return null;
    }

    public static String getJsonArrayStringFromArray(@NonNull Object [] array) {
        return GSON.toJson(array);
    }

    @Nullable
    public static HashMap getMapFromString(@Nullable String json) {
        if (!StringUtils.isEmptyOrWhiteSpace(json)) {
            try {
                return GSON_ENABLE_COMPLEX_MAP_KEY_SERIALIZATION.fromJson(json, HashMap.class);
            } catch (Exception exception) {
                // STUB
            }
        }

        return null;
    }

    @NonNull
    public static String unescapeJsonString(@NonNull String json) {
        if (StringUtils.isEmptyOrWhiteSpace(json)) {
            return json;
        }

        return json.replaceAll("^\"|\"$", "").replace("\\\"", "\"");
    }

    public static String getUnescapedHtmlJsonString(Object object) {
        return GSON_DISABLE_HTML_ESCAPING.toJson(object).toString();
    }

    @NonNull
    public static String constructBooleanJson(String key, boolean value) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(key, value);
        return jsonObject.toString();
    }

    @NonNull
    public static String constructLongJson(String key, long value) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(key, value);
        return jsonObject.toString();
    }

    @Nullable
    public static String getStringFromJsonElement(@Nullable JsonElement element) {
        if (element == null || !element.isJsonPrimitive()) {
            return null;
        }
        return element.getAsString();
    }

    /***
     * By providing your own type adapter, you can exactly specify which properties to null-serialize into a JSON payload.
     * See MessageRequest.MessagePropertiesTypeGsonSerializeAdapter, policyViolation field for a concrete example
     * See https://futurestud.io/tutorials/gson-advanced-custom-serialization-part-1 for more details
     * E.g.
     *
     * {
     *     "id": 12345
     *     "name": null <--- your type adapter must specify to serialize this despite nullity
     * }
     *
     * @param object - the JSON object to serialize
     * @param type - the Class type to serialize
     * @param typeAdapter - the custom type adapter to specially handle how to serialize the fields located in the class type
     * @return - a JSON formatted string representing the object parameter
     */
    @Nullable
    public static String getCustomNullSerializedStringFromObject(@Nullable Object object, @NonNull Type type, @NonNull Object typeAdapter) {
        Gson gson = new GsonBuilder().registerTypeAdapter(type, typeAdapter)
                                     .serializeNulls()
                                     .create();
        return gson.toJson(object);
    }

    /**
     * Merge function for merging two Json Objects
     *
     * @param first  JsonObject
     * @param second JsonObject
     * @return merged JsonObject where {@code second} takes preference over {@code first} in case of duplicate key
     */
    public static JsonObject mergeJsonObjects(@Nullable JsonObject first, @Nullable JsonObject second) {
        if (first == null && second == null) {
            return new JsonObject();
        }

        if (first == null) {
            return second;
        }

        if (second == null) {
            return first;
        }

        for (String key : second.keySet()) {
            first.add(key, second.get(key));
        }
        return first;
    }

    /**
     * If the input is a jsonObject the function returns an array of all the values,
     * for the keys present in the jsonObject
     * @param jsonElement : jsonElement to parse
     * @return : array of all values;
     */
    @NonNull
    public static JsonArray getAllValuesFromJson(@Nullable JsonElement jsonElement) {
        JsonArray jsonArray = new JsonArray();
        try {
            if (jsonElement != null && jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                for (String key : jsonObject.keySet()) {
                    jsonArray.add(jsonObject.get(key));
                }
            }
        } catch (Exception ex) {
            // STUB
        }
        return jsonArray;
    }

    /**
     * Deserializes a byte array containing JSON data
     *
     * @param gson     GSON instance to use for deserialization
     * @param bytes    Byte array containing the JSON data to deserialize
     * @param classOfT Class to which to deserialize the data
     * @param <T>      Deserialized return type
     * @return Deserialized JSON data
     * @throws JsonSyntaxException Thrown if there is an issue with the JSON syntax in the serialized data
     * @throws JsonIOException     Thrown if there is an issue deserializing the JSON data
     */
    @Nullable
    public static <T> T fromJson(@NonNull Gson gson, @NonNull byte[] bytes, @NonNull Class<T> classOfT) throws
            JsonSyntaxException, JsonIOException {
        return fromJson(gson, new ByteArrayInputStreamAdapter(bytes), classOfT);
    }

    /**
     * Deserializes a byte array containing JSON data
     *
     * @param gson        GSON instance to use for deserialization
     * @param inputStream Stream containing the JSON data to serialized
     * @param classOfT    Class to which to deserialize the data
     * @param <T>         Deserialized return type
     * @return Deserialized JSON data
     * @throws JsonSyntaxException Thrown if there is an issue with the JSON syntax in the serialized data
     * @throws JsonIOException     Thrown if there is an issue deserializing the JSON data
     */
    @Nullable
    public static <T> T fromJson(@NonNull Gson gson, @NonNull InputStream inputStream, @NonNull Class<T> classOfT) throws
            JsonSyntaxException, JsonIOException {
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        try {
            return gson.fromJson(reader, classOfT);
        } finally {
            StreamUtils.closeSilent(reader);
        }
    }

    /**
     * Provides a method of inputting a byte array where a stream is required without copying the
     * contents of the array. Note that you CANNOT use the byte array for anything else when it is
     * used within a ByteArrayInputStreamAdapter.
     *
     * Like the ByteArrayInputStream it is modelled after, the mark read limit and constraints about
     * resetting when the stream is closed are ignored.
     */
    public static class ByteArrayInputStreamAdapter extends InputStream {
        private final byte[] mData;
        private int mPos = 0;
        private int mMark = 0;

        /**
         * Creates a new ByteArrayInputStreamAdapter from a byte array
         *
         * @param data Data to convert to a stream
         */
        public ByteArrayInputStreamAdapter(@NonNull byte[] data) {
            mData = data;
        }

        /**
         * Returns the number of bytes still available to be read
         * @return Number of bytes still to be read, or length - position
         * @throws IOException Not thrown
         */
        @Override
        public int available() throws IOException {
            return mData.length - mPos;
        }

        /**
         * Marks the current read position for use with reset
         * @param readlimit - Like the ByteArrayInputStream it is modelled after, this is ignored.
         */
        @Override
        public void mark(int readlimit) {
            mMark = mPos;
        }

        /**
         * Returns true because the mark and reset methods are supported by this InputStream
         * @return Returns true
         */
        @Override
        public boolean markSupported() {
            return true;
        }

        /**
         * Resets the current position to the marked position
         * @throws IOException Not thrown
         */
        @Override
        public synchronized void reset() throws IOException {
            mPos = mMark;
        }

        /**
         * Retrieves the next value from the input and advances the position
         * @return Current value in the input, -1 when completed
         * @throws IOException Not thrown
         */
        @Override
        public int read() throws IOException {
            if (mPos >= mData.length) {
                return -1;
            }

            return mData[mPos++];
        }
    }

}