/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.storage.converters;

import com.microsoft.teams.storage.ThreadType;
// import com.raizlabs.android.dbflow.converter.TypeConverter;

/**
 * Represents ThreadType Converter for Model class.
 */
public class ThreadTypeConverter/* extends TypeConverter<Integer, ThreadType>*/ {
//     @Override
    public Integer getDBValue(ThreadType model) {
        return model.getNumVal();
    }

//    @Override
    public ThreadType getModelValue(Integer data) {
        return ThreadType.values()[data];
    }
}