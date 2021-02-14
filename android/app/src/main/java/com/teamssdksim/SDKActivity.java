package com.teamssdksim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.microsoft.hubapphost.sdk.PlatformAppProvider;
import com.microsoft.teams.mobilemodules.IPlatformApp;
import com.microsoft.teams.sdk.SdkApplicationContext;
// import com.teamssdksim.sdk.PlatformAppProvider;

import java.util.List;

public class SDKActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_d_k);

        ViewGroup host = (ViewGroup)findViewById(R.id.reactHost);
        PlatformAppProvider.initialize(SDKActivity.this.getApplication(), SDKActivity.this);

        Button startButton = new Button(this);
        startButton.setText("Attach");;
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PlatformAppProvider.get().attachPlatformAppsObserver(new PlatformAppProvider.IPlatformAppsObserver() {
                    @Override
                    public void onPlatformAppsChanged(List<IPlatformApp> platformApps) {
                        Log.i("SDKActivity" , platformApps.toString());
                    }
                });
                PlatformAppProvider.get().syncMobileModules();
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

        host.addView(startButton);
        host.addView(seedButton);
        host.addView(syncButton);
    }
}