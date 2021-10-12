/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk.react.modules;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
//import com.microsoft.teams.views.activities.BaseActivity;
//import com.microsoft.teams.views.fragments.SdkAppHostFragment;

import java.util.Locale;

/**
 * Implements the base Native Java module for Teams SDK.
 */
public abstract class TeamsReactContextBaseJavaModule extends ReactContextBaseJavaModule implements LifecycleEventListener //,
//        BaseActivity.OnActivityResultListener,
//        BaseActivity.OnRequestPermissionsResultListener
{
    protected AbstractActivityResultHandler mActivityResultHandler;
    protected final String mModuleId;
    public static final String APP_ID = "APP_ID";

    TeamsReactContextBaseJavaModule(@NonNull ReactApplicationContext reactContext, String moduleId) {
        super(reactContext);
        reactContext.addLifecycleEventListener(this);
        mModuleId = moduleId;
    }

    String formatMessage(@NonNull String format, Object... args) {
        return String.format(Locale.ENGLISH, format, args);
    }

//    public static String getTabId(BaseActivity currentActivity) {
//        List<Fragment> fragments = currentActivity.getSupportFragmentManager().getFragments();
//        for (Fragment fragment : fragments) {
//            if (fragment instanceof SdkAppHostFragment) {
//                return ((SdkAppHostFragment) fragment).getAppId();
//            }
//        }
//        return null;
//    }

    // get current module id
    public String getModuleId() {
        return mModuleId;
    }

    /**
     * Module's lifecycle events are already registered in
     * {@link #TeamsReactContextBaseJavaModule(ReactApplicationContext, String)}
     */
    @Override
    public void onHostPause() {

    }

    /**
     * Module's lifecycle events are already registered in
     * {@link #TeamsReactContextBaseJavaModule(ReactApplicationContext, String)}
     */
    @Override
    public void onHostResume() {

    }

    /**
     * Module's lifecycle events are already registered in
     * {@link #TeamsReactContextBaseJavaModule(ReactApplicationContext, String)}
     */
    @Override
    public void onHostDestroy() {

    }

//    /**
//     * To recieve onActivityResult, register it using
//     * {@link BaseActivity#addOnActivityResultListener(BaseActivity.OnActivityResultListener)}
//     */
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (mActivityResultHandler != null && mActivityResultHandler.getRequestCode() == requestCode) {
//            mActivityResultHandler.onResult(resultCode, data);
//            //Clear the state once the result is handled
//            clearState();
//        }
//    }

//    /**
//     * Method to clear the state of the module
//     */
//    @CallSuper
//    protected void clearState() {
//        if (getCurrentActivity() instanceof BaseActivity) {
//            ((BaseActivity) getCurrentActivity()).removeOnActivityResultListener(this);
//        }
//        mActivityResultHandler = null;
//    }

    @NonNull
    protected String getScenarioTags(Object... addData) {
        StringBuilder builder = new StringBuilder(getModuleId());
        for (Object data : addData) {
            builder.append(" : ");
            builder.append(data);
        }
        return builder.toString();
    }

//    /**
//     * To recieve onRequestPermissionsResult, register it using
//     * {@link BaseActivity#addOnRequestPermissionsResultListener(BaseActivity.OnRequestPermissionsResultListener)}
//     */
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//    }

    /**
     * Abstract class to handle result from an activity
     */
    public abstract class AbstractActivityResultHandler {
        void onResult(int resultCode, Intent resultData) {
            if (resultCode == Activity.RESULT_OK) {
                onSuccess(resultData);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                onCancel();
            } else {
                onError();
            }
        }

        abstract void onSuccess(Intent resultData);

        abstract void onCancel();

        abstract void onError();

        abstract int getRequestCode();
    }
}
