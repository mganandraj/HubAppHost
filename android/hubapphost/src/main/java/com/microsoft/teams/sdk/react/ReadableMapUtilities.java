/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk.react;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.microsoft.teams.utilities.java.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides helper methods related to ReadableMap
 */
public final class ReadableMapUtilities {
    private ReadableMapUtilities() {
        // static helper class. So, keeping the constructor private.
    }

    @Nullable
    public static String getString(@Nullable ReadableMap map, @Nullable String stringKey) {
        return getString(map, stringKey, null);
    }

    @Nullable
    public static String getString(@Nullable ReadableMap map, @Nullable String stringKey, @Nullable String defaultValue) {
        if (map == null || stringKey == null || !map.hasKey(stringKey) || map.getType(stringKey) != ReadableType.String) {
            return defaultValue;
        }

        return map.getString(stringKey);
    }

    public static int getInt(@Nullable ReadableMap map, @Nullable String intKey) {
        return getInt(map, intKey, 0);
    }

    public static int getInt(@Nullable ReadableMap map, @Nullable String intKey, int defaultValue) {
        if (map == null || intKey == null || !map.hasKey(intKey) || map.getType(intKey) != ReadableType.Number) {
            return defaultValue;
        }

        return map.getInt(intKey);
    }

    public static double getDouble(@Nullable ReadableMap map, @Nullable String doubleKey) {
        return getDouble(map, doubleKey, 0);
    }

    public static double getDouble(@Nullable ReadableMap map, @Nullable String doubleKey, int defaultValue) {
        if (map == null || doubleKey == null || !map.hasKey(doubleKey) || map.getType(doubleKey) != ReadableType.Number) {
            return defaultValue;
        }

        return map.getDouble(doubleKey);
    }

    public static boolean getBoolean(@Nullable ReadableMap map, @Nullable String intKey) {
        return getBoolean(map, intKey, false);
    }

    public static boolean getBoolean(@Nullable ReadableMap map, @Nullable String intKey, boolean defaultValue) {
        if (map == null || intKey == null || !map.hasKey(intKey) || map.getType(intKey) != ReadableType.Boolean) {
            return defaultValue;
        }

        return map.getBoolean(intKey);
    }

    @Nullable
    public static ReadableMap getMap(@Nullable ReadableMap map, @Nullable String mapKey) {
        if (map == null || mapKey == null || !map.hasKey(mapKey) || map.getType(mapKey) != ReadableType.Map) {
            return null;
        }

        return map.getMap(mapKey);
    }

    /**
     * Helper function to extract a map of key value pairs in string format for a give key in the input map
     * Ignore any non string values
     *
     * @param map - readable map from React native
     * @param mapKey - key name to read the map from
     * @return - a map of key value pairs in string format
     */
    @Nullable
    public static Map<String, String> getStringMap(@Nullable ReadableMap map, @Nullable String mapKey) {
        ReadableMap valueMap = getMap(map, mapKey);
        if (valueMap == null) {
            return null;
        }
        Map<String, String> result = new ArrayMap<>();
        ReadableMapKeySetIterator iterator = valueMap.keySetIterator();

        // iterate on the value map corresponding to the mapKey input
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            String value = getString(valueMap, key);
            // Ignore the value if is is not a string
            if (value != null) {
                result.put(key, value);
            }
        }

        return result;
    }

    /**
     * Helper method to covert a readable map associated with a key in the input map as a hashMap of string and object
     *
     * @param map - readable map from React native
     * @param mapKey - key name to read the map from
     * @return - a map of key value pairs for a given key
     */
    @Nullable
    public static HashMap<String, Object> getHashMap(@Nullable ReadableMap map, @Nullable String mapKey) {
        if (map == null || mapKey == null || !map.hasKey(mapKey) || map.getType(mapKey) != ReadableType.Map) {
            return null;
        }

        return map.getMap(mapKey).toHashMap();
    }

    /**
     * Helper method to extract a readable array at a given key in the ReadableMap
     *
     * @param map - readable map from React native
     * @param arrayKey - key name to read the array from
     * @return - a ReadableArray associated with the key if present with type check
     */
    @Nullable
    public static ReadableArray getArray(@Nullable ReadableMap map, @Nullable String arrayKey) {
        if (map == null || arrayKey == null || !map.hasKey(arrayKey) || map.getType(arrayKey) != ReadableType.Array) {
            return null;
        }

        return map.getArray(arrayKey);
    }

    /**
     * Helper function to make null check and return a hasmap with all the entries from the readable map input
     *
     * @param map - readable map from the RN method call
     * @return - a Hash map with all the entries from ReadableMap
     */
    @Nullable
    public static Map<String, Object> toHashMap(@Nullable ReadableMap map) {
        if (map == null) {
            return null;
        }

        return map.toHashMap();
    }

    @Nullable
    public static List<String> getArrayList(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }
        List<String> list = new ArrayList<>(readableArray.size());
        for (int i = 0; i < readableArray.size(); i++) {
            ReadableType indexType = readableArray.getType(i);
            switch (indexType) {
                case String:
                    list.add(readableArray.getString(i));
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object with index: " + i);
            }
        }
        return list;
    }

    /**
     * Utility method to convert map to an object of given class {@link T}
     *
     * @param readableMap map to be converted
     * @param targetObjectClass class type of target object
     * @return object of target class {@link T}
     * @throws ClassCastException
     */
    public static <T> T parseToObject(@NonNull ReadableMap readableMap, Class<T> targetObjectClass) throws ClassCastException {
        try {
            return JsonUtils.GSON.fromJson(convertMapToJson(readableMap).toString(), targetObjectClass);
        } catch (JSONException e) {
            throw new ClassCastException("Unable to convert the map to target class");
        }
    }

    private static JSONObject convertMapToJson(ReadableMap readableMap) throws JSONException {
        JSONObject object = new JSONObject();
        ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            switch (readableMap.getType(key)) {
                case Null:
                    object.put(key, JSONObject.NULL);
                    break;
                case Boolean:
                    object.put(key, readableMap.getBoolean(key));
                    break;
                case Number:
                    object.put(key, readableMap.getDouble(key));
                    break;
                case String:
                    object.put(key, readableMap.getString(key));
                    break;
                case Map:
                    object.put(key, convertMapToJson(readableMap.getMap(key)));
                    break;
                case Array:
                    object.put(key, convertArrayToJson(readableMap.getArray(key)));
                    break;
                default:
                    throw new JSONException("Incompatible type, unable to convert to Json");
            }
        }
        return object;
    }

    private static JSONArray convertArrayToJson(ReadableArray readableArray) throws JSONException {
        JSONArray array = new JSONArray();
        for (int i = 0; i < readableArray.size(); i++) {
            switch (readableArray.getType(i)) {
                case Null:
                    break;
                case Boolean:
                    array.put(readableArray.getBoolean(i));
                    break;
                case Number:
                    array.put(readableArray.getDouble(i));
                    break;
                case String:
                    array.put(readableArray.getString(i));
                    break;
                case Map:
                    array.put(convertMapToJson(readableArray.getMap(i)));
                    break;
                case Array:
                    array.put(convertArrayToJson(readableArray.getArray(i)));
                    break;
                default:
                    throw new JSONException("Incompatible type, unable to convert to JSONArray");
            }
        }
        return array;
    }
}
