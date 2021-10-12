"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.TeamsModules = void 0;
const SdkNativeModules_1 = require("./services/SdkNativeModules");
class TeamsModules {
    /**
     * API to open contact card <br>
     *
     * Pass mri as id if exists with type org, otherwise pass contactId with type contact
     *
     * @param {Contact} Contact - Contact info required to open contact card
     *
     */
    static openContactCard(contact) {
        SdkNativeModules_1.NavigationService.openContactCard(contact);
    }
    static startAudioCall(contact) {
        SdkNativeModules_1.NavigationService.startAudioCall(contact);
    }
    static openChat(conversationId) {
        SdkNativeModules_1.NavigationService.openChat(conversationId);
    }
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
    static postMessage(params) {
        const filesArray = params.files;
        if (filesArray) {
            const tempFiles = new Array();
            filesArray.forEach(element => {
                const dict = element;
                dict["id"] = element["itemid"];
                dict["@type"] = "http://schema.skype.com/File";
                tempFiles.push(dict);
            });
            params.files = tempFiles;
        }
        const mentionDict = params.mentionDictionary;
        for (const key in mentionDict) {
            const value = mentionDict[key];
            value["@type"] = "http://schema.skype.com/Mention";
            mentionDict[key] = value;
        }
        return SdkNativeModules_1.TeamsMessaging.postMessageWithParams(params);
    }
    static openChatWithUserMris(userMris) {
        SdkNativeModules_1.NavigationService.openChatWithUserMris(userMris);
    }
    /**
     * API to open message details page
     * @param eventId ID of exchange calendar event.
     * @param isSeriesMaster true if event is of type SeriesMaster, else false
     */
    static openMeetingDetails(eventId, isSeriesMaster) {
        SdkNativeModules_1.NavigationService.openMeetingDetails(eventId, isSeriesMaster);
    }
}
exports.TeamsModules = TeamsModules;
