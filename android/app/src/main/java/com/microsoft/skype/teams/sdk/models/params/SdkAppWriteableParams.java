/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk.models.params;

import android.os.Bundle;
import androidx.annotation.NonNull;

import com.facebook.react.bridge.WritableMap;

/**
 * Interface for sdk app params
 */
public interface SdkAppWriteableParams {
    @NonNull
    Bundle toBundle();

    @NonNull
    WritableMap toMap();
}
