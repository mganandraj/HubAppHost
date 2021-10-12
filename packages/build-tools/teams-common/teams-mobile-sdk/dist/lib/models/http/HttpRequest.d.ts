/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
export interface HttpRequest {
    requestId: string;
    serviceName: string;
    requestName: string;
    method: string;
    url: string;
    headers: any;
    body: string;
}
