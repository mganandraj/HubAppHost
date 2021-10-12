package com.teamssdksim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.microsoft.hubapphost.sdk.PlatformAppProvider;
import com.microsoft.teams.androidutils.tasks.TaskUtilities;
import com.microsoft.teams.mobilemodules.IMobileModule;
import com.microsoft.teams.mobilemodules.IPlatformApp;
import com.microsoft.teams.sdk.SdkApplicationContext;

import java.util.List;

public class SDKActivity extends AppCompatActivity {

    ViewGroup host;

    public void showApp(IPlatformApp platformApp) {
        // May get called on background thread.

        IMobileModule mobileModule = platformApp.getMobileModule();
        SdkApplicationContext sdkApplicationContext = mobileModule.getSdkApplicationContext();

        TaskUtilities.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                if (sdkApplicationContext != null) {
                    ReactInstanceManager instanceManager = sdkApplicationContext.getReactInstanceManager(SDKActivity.this);

                    ReactRootView reactRootView = new ReactRootView(SDKActivity.this);

                    Bundle reactAppParams = new Bundle();
                    reactAppParams.putBundle("appParams", sdkApplicationContext.getAppInitializationParamsAsBundle(""));
                    reactAppParams.putBundle("_hostParams", sdkApplicationContext.getHostParamsAsBundle("TestSdkSimInstanceId"));
                    reactAppParams.putString("_params", "");

                    reactRootView.startReactApplication(instanceManager, "HomeComponent", reactAppParams);

                    host.addView(reactRootView);

                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_d_k);

        host = (ViewGroup)findViewById(R.id.reactHost);
        PlatformAppProvider.initialize(SDKActivity.this.getApplication(), SDKActivity.this);

        Button startButton = new Button(this);
        startButton.setText("Attach");;
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PlatformAppProvider.get().attachPlatformAppsObserver(new PlatformAppProvider.IPlatformAppsObserver() {
                    @Override
                    public void onPlatformAppsChanged(List<IPlatformApp> platformApps) {
                        for(IPlatformApp platformApp: platformApps)
                            showApp(platformApp);
                    }
                });
            }
        });

        Button seedButton = new Button(this);
        seedButton.setText("Seed");
        seedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlatformAppProvider.get().seedMobileModules();
            }
        });

        Button syncButton = new Button(this);
        syncButton.setText("Sync");;
        syncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlatformAppProvider.get().syncMobileModules();
            }
        });

        Button showRunnerButton = new Button(this);
        showRunnerButton.setText("ShowRunner");;
        showRunnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IPlatformApp runnerApp = PlatformAppProvider.get().getRunnerApp();
                showApp(runnerApp);
                // PlatformAppProvider.get().syncMobileModules();
            }
        });

        host.addView(startButton);
        host.addView(seedButton);
        host.addView(syncButton);
        host.addView(showRunnerButton);
    }
}