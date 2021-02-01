/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk.react;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
// import com.microsoft.skype.teams.people.contactcard.views.ContactCardFragment;
import com.microsoft.skype.teams.sdk.SdkApplicationContext;
import com.microsoft.skype.teams.sdk.models.params.SdkImageSourceMetadata;
//import com.microsoft.skype.teams.services.threading.Executors;
//import com.microsoft.skype.teams.views.activities.CustomTabsShellActivity;

import bolts.Task;
import bolts.TaskCompletionSource;

import java.util.List;

import bolts.Continuation;

/**
 * Emits native event to sdk apps.
 */
public final class SdkAppNativeEventEmitter {
    private SdkAppNativeEventEmitter() {
    }

    public static void emitSignOutEvent(@NonNull SdkApplicationContext sdkApplicationContext) {
        emitEvent(sdkApplicationContext, "onUserSignedOut", null);
    }

    public static void emitOptionsMenuInvalidatedEvent(@NonNull SdkApplicationContext sdkApplicationContext,
                                                       @NonNull String hostInstanceId) {
        WritableNativeMap eventData = new WritableNativeMap();
        eventData.putString("appInstanceId", hostInstanceId); // for backwards compatibility
        eventData.putString("hostInstanceId", hostInstanceId);
        emitEvent(sdkApplicationContext, "onShellOptionsMenuInvalidated", eventData);
    }

    public static void emitOptionsMenuItemSelectedEvent(@NonNull SdkApplicationContext sdkApplicationContext,
                                                        @NonNull String hostInstanceId,
                                                        @NonNull String selectedOptionsMenuItemId) {
        WritableNativeMap eventData = new WritableNativeMap();
        eventData.putString("appInstanceId", hostInstanceId); // for backwards compatibility
        eventData.putString("hostInstanceId", hostInstanceId);
        eventData.putString("optionsMenuItemId", selectedOptionsMenuItemId);
        emitEvent(sdkApplicationContext, "onShellOptionsMenuItemSelected", eventData);
    }

    public static void emitTabSelectedEvent(@NonNull SdkApplicationContext sdkApplicationContext,
                                            @NonNull String hostInstanceId,
                                            @NonNull String tabId) {
        WritableNativeMap eventData = new WritableNativeMap();
        eventData.putString("appInstanceId", hostInstanceId); // for backwards compatibility
        eventData.putString("hostInstanceId", hostInstanceId);
        eventData.putString("tabId", tabId);
        emitEvent(sdkApplicationContext, "onTabSelected", eventData);
    }

    public static void emitSnackbarActionSelectedEvent(@NonNull SdkApplicationContext sdkApplicationContext,
                                                       @NonNull String hostInstanceId,
                                                       int snackbarId,
                                                       @NonNull String snackbarActionId) {
        WritableNativeMap eventData = new WritableNativeMap();
        eventData.putString("appInstanceId", hostInstanceId); // for backwards compatibility
        eventData.putString("hostInstanceId", hostInstanceId);
        eventData.putInt("snackbarId", snackbarId);
        eventData.putString("snackbarActionId", snackbarActionId);
        emitEvent(sdkApplicationContext, "onSnackbarActionSelected", eventData);
    }

    public static void emitProviderCallCancelledEvent(@NonNull SdkApplicationContext sdkApplicationContext,
                                                      @NonNull String requestId) {
        WritableNativeMap eventData = new WritableNativeMap();
        eventData.putString("requestId", requestId);
        emitEvent(sdkApplicationContext, "onProviderRequestCancelled", eventData);
    }

    public static void emitPrimaryFabClickEvent(@NonNull SdkApplicationContext sdkApplicationContext,
                                                @NonNull String hostInstanceId) {
        WritableNativeMap eventData = new WritableNativeMap();
        eventData.putString("appInstanceId", hostInstanceId); // for backwards compatibility
        eventData.putString("hostInstanceId", hostInstanceId);
        emitEvent(sdkApplicationContext, "onPrimaryFabClick", eventData);
    }

    public static void emitSecondaryFabClickEvent(@NonNull SdkApplicationContext sdkApplicationContext,
                                                  @NonNull String hostInstanceId,
                                                  @NonNull String buttonId) {
        WritableNativeMap eventData = new WritableNativeMap();
        eventData.putString("appInstanceId", hostInstanceId); // for backwards compatibility
        eventData.putString("hostInstanceId", hostInstanceId);
        eventData.putString("buttonId", buttonId);
        emitEvent(sdkApplicationContext, "onSecondaryFabClick", eventData);
    }

//    public static void emitOnHostClosedEvent(@NonNull SdkApplicationContext sdkApplicationContext,
//                                             @NonNull String closedHostInstanceId) {
//        WritableNativeMap eventData = new WritableNativeMap();
//        eventData.putString(CustomTabsShellActivity.CLOSED_HOST_INSTANCE_ID, closedHostInstanceId);
//        emitEvent(sdkApplicationContext, "onHostClosed", eventData);
//    }

    public static void emitTitleDropdownItemSelectedEvent(@NonNull SdkApplicationContext sdkApplicationContext,
                                                          @NonNull String hostInstanceId,
                                                          @NonNull String selectedItemId) {
        WritableNativeMap eventData = new WritableNativeMap();
        eventData.putString("hostInstanceId", hostInstanceId);
        eventData.putString("selectedItemId", selectedItemId);
        emitEvent(sdkApplicationContext, "onTitleDropdownItemSelected", eventData);
    }


    public static void emitonTitleClickedEvent(@NonNull SdkApplicationContext sdkApplicationContext,
                                               @NonNull String hostInstanceId) {
        WritableNativeMap eventData = new WritableNativeMap();
        eventData.putString("hostInstanceId", hostInstanceId);
        emitEvent(sdkApplicationContext, "onTitleClicked", eventData);
    }

    public static void emitBackNavigationInitiatedEvent(@NonNull SdkApplicationContext sdkApplicationContext,
                                                        @NonNull String hostInstanceId) {
        WritableNativeMap eventData = new WritableNativeMap();
        eventData.putString("hostInstanceId", hostInstanceId);
        emitEvent(sdkApplicationContext, "onBackNavigationInitiated", eventData);
    }

    public static void emitOnImagesFetchedEvent(@NonNull SdkApplicationContext sdkApplicationContext,
                                                @NonNull List<SdkImageSourceMetadata> sdkImageSourceMetadata) {
        WritableNativeMap eventData = new WritableNativeMap();
        eventData.putArray("sdkImageSourceMetadata", ReactNativeUtilities.convertObjectArraytoWritableArray(sdkImageSourceMetadata));
        emitEvent(sdkApplicationContext, "fetchedImages", eventData);
    }

    public static void emitOnFocusEvent(@NonNull SdkApplicationContext sdkApplicationContext,
                                        @NonNull String hostInstanceId) {
        WritableNativeMap eventData = new WritableNativeMap();
        eventData.putString("hostInstanceId", hostInstanceId);
        emitEvent(sdkApplicationContext, "onViewAppear", eventData);
    }

//    public static void emitContactSyncEvent(@NonNull SdkApplicationContext sdkApplicationContext,
//                                            boolean isSuccessful) {
//        WritableNativeMap eventData = new WritableNativeMap();
//        eventData.putBoolean("isSuccessful", isSuccessful);
//        emitEvent(sdkApplicationContext, "onContactsSyncCompleted", eventData);
//    }
//
//    public static void emitContactDeletedEvent(@NonNull SdkApplicationContext sdkApplicationContext,
//                                               String contactId) {
//        WritableNativeMap eventData = new WritableNativeMap();
//        eventData.putString("contactId", contactId);
//        emitEvent(sdkApplicationContext, ContactCardFragment.CONTACT_DELETED_EVENT, eventData);
//    }

    private static void emitEvent(@NonNull final SdkApplicationContext sdkApplicationContext, @NonNull final String eventName, @Nullable final Object data) {
        throw new RuntimeException("Not implemented !");
/*        sdkApplicationContext
                .getReactInstanceManagerAfterContextInitialization()
                .continueWith((Continuation<ReactInstanceManager, Void>) task -> {
                    if (task.getResult() == null) {
                        return null;
                    }

                    ReactContext reactContext = task.getResult().getCurrentReactContext();
                    if (reactContext == null) {
                        return null;
                    }

                    RCTNativeAppEventEmitter nativeAppEventEmitter = reactContext.getJSModule(RCTNativeAppEventEmitter.class);
                    if (nativeAppEventEmitter != null) {
                        nativeAppEventEmitter.emit(eventName, data);
                    }

                    return null;
                }, Executors.getHighPriorityViewDataThreadPool());*/
    }
}
