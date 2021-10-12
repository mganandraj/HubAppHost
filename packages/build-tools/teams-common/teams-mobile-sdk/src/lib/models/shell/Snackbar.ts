/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

export enum SnackbarDuration {
    SHORT = 0,
    LONG = 1,
    INDEFINITE = 2
}

export interface SnackbarAction {
  id: string;
  title: string;
  onSelected: () => void;
}

export interface Snackbar {
  title: string;
  duration?: SnackbarDuration;
  action?: SnackbarAction;
}
