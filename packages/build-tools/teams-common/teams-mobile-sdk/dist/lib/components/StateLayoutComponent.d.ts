/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
import * as React from 'react';
import { ViewProps } from 'react-native';
export declare enum ViewStateType {
    LOADING = 0,
    EMPTY = 1,
    AVAILABLE = 2,
    ERROR = 3
}
export interface ViewError {
    title: string;
    description: string;
    errorImageResource: string;
}
export interface ViewState {
    type: ViewStateType;
    error: ViewError;
    errorMessage: string;
    lastUpdatedTime: number;
}
export interface StateLayoutProperties extends ViewProps {
    viewState?: ViewState;
    onChange?: any;
    syncing?: boolean;
}
export declare const StateLayout: React.ComponentClass<StateLayoutProperties>;
