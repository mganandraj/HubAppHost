package com.microsoft.teams.logger.constants;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({})
public @interface SampledMetricPropKeys {
    String TAG = "SampledMetric_Tag";
    String PRIORITY = "SampledMetric_Priority";
}
