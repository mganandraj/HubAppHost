package com.microsoft.skype.teams.logger.constants;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
        FailurePropKeys.TAG,
        FailurePropKeys.PRIORITY,
})
public @interface FailurePropKeys {
    String TAG = "Failure_Tag";
    String PRIORITY = "Failure_Priority";
}
