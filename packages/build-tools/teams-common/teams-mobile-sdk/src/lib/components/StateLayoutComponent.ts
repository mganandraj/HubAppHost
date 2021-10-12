/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
import * as React from 'react';
import { requireNativeComponent, ViewProps } from 'react-native';

export enum ViewStateType {
  LOADING,
  EMPTY,
  AVAILABLE,
  ERROR
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

// The name of the native component should not be changed. It is defined in the
// view managers in the native code and the values need to match.
export const StateLayout: React.ComponentClass<StateLayoutProperties> = requireNativeComponent('StateLayout');
