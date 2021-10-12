import { Dictionary } from "lodash";
import { TeamsMessagingResponse } from '../../models/TeamsMessaging/TeamsMessagingResponse';
/**
 * Teams Messaging
 * @hidden from docs
 */
export interface TeamsMessagingInterface {
    /**
     * API to post message to Teams channel / chat.
     *
     * If message sending is not successful, promise
     * will be returned with failure status code.
     *
     * @param {Dictionary<any>} params - param dictionary to post message
     * @returns {Promise<TeamsMessagingResponse>} - A promise that returns TeamsMessagingResponse with status code and unique message ID
     * @memberof TeamsMessagingInterface
     */
    postMessageWithParams(params: Dictionary<any>): Promise<TeamsMessagingResponse>;
}
