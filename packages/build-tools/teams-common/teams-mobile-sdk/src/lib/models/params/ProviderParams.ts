/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import { ApplicationParams } from './ApplicationParams';

export interface BaseProviderParams {
  appParams: ApplicationParams;
  requestId: string;
}

export interface BaseProviderResult {
  requestId: string;
}
