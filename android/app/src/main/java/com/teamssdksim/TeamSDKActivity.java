package com.teamssdksim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;

import com.facebook.react.ReactRootView;
import com.google.gson.GsonBuilder;
import com.microsoft.skype.teams.sdk.SdkRunnerAppManager;

public class TeamSDKActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_s_d_k);

        TeamsSdkSimApplication fakeApplication = new TeamsSdkSimApplication(this, this.getApplication());

        SdkRunnerAppManager sdkRunnerAppManager = new SdkRunnerAppManager(this, new GsonBuilder().create(), fakeApplication);
        sdkRunnerAppManager.syncRunnerApp();

//
//        ReactRootView view = new ReactRootView(this);
//        view.startReactApplication(((MainApplication)getApplication()).getReactNativeHost().getReactInstanceManager(), "TeamsSdkSim");
//
//        ViewGroup host = (ViewGroup)findViewById(R.id.reactHost);
//        host.addView(view);
    }
}