/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk.react;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.microsoft.skype.teams.sdk.models.params.SdkAppWriteableParams;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Helper methods to read and write native objects
 */
public final class ReactNativeUtilities {
    private static final String LOG_TAG = "ReactNativeUtilities";

    public static WritableArray convertObjectArraytoWritableArray(List<? extends SdkAppWriteableParams> objectList) {
        WritableArray array = Arguments.createArray();
        for (SdkAppWriteableParams object : objectList) {
            array.pushMap(object.toMap());
        }
        return array;
    }

    public static WritableMap convertObjectMapToWritableMap(Map<String, List<? extends SdkAppWriteableParams>> objectMap) {
        WritableMap map = Arguments.createMap();
        Iterator it = objectMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            List<? extends SdkAppWriteableParams> list = objectMap.get(pair.getKey());
            map.putArray(pair.getKey().toString(), ReactNativeUtilities.convertObjectArraytoWritableArray(list));
        }
        return map;
    }

    private ReactNativeUtilities() {
    }
}
