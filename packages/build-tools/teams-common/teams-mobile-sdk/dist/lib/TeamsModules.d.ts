import { Contact } from './models/contact/Contact';
import { TeamsMessagingRequest } from './models/TeamsMessaging/TeamsMessagingRequest';
import { TeamsMessagingResponse } from './models/TeamsMessaging/TeamsMessagingResponse';
export declare class TeamsModules {
    /**
     * API to open contact card <br>
     *
     * Pass mri as id if exists with type org, otherwise pass contactId with type contact
     *
     * @param {Contact} Contact - Contact info required to open contact card
     *
     */
    static openContactCard(contact: Contact): void;
    static startAudioCall(contact: Contact): void;
    static openChat(conversationId: string): void;
    /**
     * API to post message to Teams chat / channel <br>
     *
     * If message sending is not successful, promise
     * will be returned with failure status code.
     *
     * See [Teams Messaging](../articles/11_Modules/05_Teams_Messaging.md) for more.
     *
     * @param {TeamsMessagingRequest} params - input params required to post message
     * @returns {Promise} - A promise that returns TeamsMessagingResponse
     * with status code and message ID
     *
     */
    static postMessage(params: TeamsMessagingRequest): Promise<TeamsMessagingResponse>;
    static openChatWithUserMris(userMris: string[]): void;
    /**
     * API to open message details page
     * @param eventId ID of exchange calendar event.
     * @param isSeriesMaster true if event is of type SeriesMaster, else false
     */
    static openMeetingDetails(eventId: string, isSeriesMaster: boolean): void;
}
