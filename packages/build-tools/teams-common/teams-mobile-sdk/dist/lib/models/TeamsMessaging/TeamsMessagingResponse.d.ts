/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
export interface TeamsMessagingResponse {
    /**
     * status code for TeamsMessaging request<br>
     * <br>
     * TeamsMessagingStatusSuccess = 200,                         //Success<br>
     * TeamsMessagingStatusThreadIDNotValid = 401,                //Thread ID not valid<br>
     * TeamsMessagingStatusThreadIDNotFound = 404,                //Thread ID not found<br>
     * TeamsMessagingStatusMessageUploadFailed = 500,             //Message upload failed<br>
     * TeamsMessagingStatusCreateMessageFailed = 501,             //Create Message failed<br>
     */
    statusCode: number;
    /**
     * Unique identifier for message
     */
    messageID: string;
}
