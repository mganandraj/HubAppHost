package com.microsoft.teams.sdk.models.params;

import android.os.Bundle;
import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

/**
 * Represents result for {@link com.microsoft.teams.sdk.react.modules.SdkChannelPickerModule} for SDK app
 */
public class SdkChannelPickerResultParams implements SdkAppWriteableParams {

    public final SdkAppChannelParams mChannel;
    public final SdkAppTeamParams mTeam;

    public SdkChannelPickerResultParams(@NonNull SdkAppChannelParams channel, @NonNull SdkAppTeamParams team) {
        this.mChannel = channel;
        this.mTeam = team;
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        Bundle bundle = Arguments.toBundle(toMap());
        return bundle != null ? bundle : new Bundle();
    }

    @NonNull
    @Override
    public WritableMap toMap() {
        WritableMap map = Arguments.createMap();
        map.putMap("selectedChannel", mChannel.toMap());
        map.putMap("parentTeam", mTeam.toMap());
        return map;
    }
}
