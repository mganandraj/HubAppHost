/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk.react;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.microsoft.teams.utilities.java.JsonUtils;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Utility class to create response/error objects to be sent over RN bridge
 */
public final class WritableMapUtils {

    private WritableMapUtils() {
        // static helper class. So, keeping the constructor private.
    }

    public static WritableMap getDefaultResponseMap(int statusCode, Object result) throws IllegalArgumentException {
        Map<String, Object> map = new HashMap<>(2);
        map.put("statusCode", statusCode);
        map.put("result", result);

        return Arguments.makeNativeMap(map);
    }

    public static WritableMap getErrorMap(@Nullable String key, @NonNull String errorMessage) {
        WritableMap errorMap = Arguments.createMap();
        errorMap.putString("message", errorMessage);
        if (key != null) {
            errorMap.putString("key", key);
        }
        return errorMap;
    }

    /**
     * Utility method to convert an object of class {@link T} to a {@link WritableMap}
     *
     * @param srcObj object to be converted
     * @param srcObjClass class type of object to be converted
     * @return map representation of converted object
     * @throws ClassCastException
     */
    public static <T> WritableMap parseToMap(T srcObj, Class<T> srcObjClass) throws ClassCastException {
        WritableMap writableMap = null;
        try {
            writableMap = fromJsonObjectToNativeMap(JsonUtils.GSON.toJsonTree(srcObj, srcObjClass).getAsJsonObject());
            T object = ReadableMapUtilities.parseToObject(writableMap, srcObjClass);
            return writableMap;
        } catch (JSONException e) {
            throw new ClassCastException("Unable to cast source class object to native map");
        }
    }

    private static WritableMap fromJsonObjectToNativeMap(@NonNull JsonObject jsonObject) throws JSONException {
        WritableMap writableMap = new WritableNativeMap();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            JsonElement jsonElement = entry.getValue();
            String key = entry.getKey();
            if (jsonElement.isJsonObject()) {
                writableMap.putMap(key, fromJsonObjectToNativeMap(jsonElement.getAsJsonObject()));
            } else if (jsonElement.isJsonPrimitive()) {
                JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();
                if (jsonPrimitive.isBoolean()) {
                    writableMap.putBoolean(key, jsonPrimitive.getAsBoolean());
                } else if (jsonPrimitive.isNumber()) {
                    writableMap.putDouble(key, jsonPrimitive.getAsDouble());
                } else if (jsonPrimitive.isString()) {
                    writableMap.putString(key, jsonPrimitive.getAsString());
                }
            } else if (jsonElement.isJsonArray()) {
                writableMap.putArray(key, fromJsonArrayToNativeArray(jsonElement.getAsJsonArray()));
            } else if (jsonElement.isJsonNull()) {
                writableMap.putNull(key);
            }
        }
        return writableMap;
    }


    private static WritableArray fromJsonArrayToNativeArray(@NonNull JsonArray jsonArray) throws JSONException {
        WritableArray array = new WritableNativeArray();

        for (JsonElement jsonElement : jsonArray) {
            if (jsonElement.isJsonObject()) {
                array.pushMap(fromJsonObjectToNativeMap(jsonElement.getAsJsonObject()));
            } else if (jsonElement.isJsonPrimitive()) {
                JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();
                if (jsonPrimitive.isBoolean()) {
                    array.pushBoolean(jsonPrimitive.getAsBoolean());
                } else if (jsonPrimitive.isNumber()) {
                    array.pushDouble(jsonPrimitive.getAsDouble());
                } else if (jsonPrimitive.isString()) {
                    array.pushString(jsonPrimitive.getAsString());
                }
            } else if (jsonElement.isJsonArray()) {
                array.pushArray(fromJsonArrayToNativeArray(jsonArray.getAsJsonArray()));
            } else if (jsonElement.isJsonNull()) {
                array.pushNull();
            }
        }
        return array;
    }
}
