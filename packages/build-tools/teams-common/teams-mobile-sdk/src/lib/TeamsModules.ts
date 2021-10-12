import { Dictionary } from 'lodash';
import { Contact } from './models/contact/Contact';
import { TeamsMessagingRequest } from './models/TeamsMessaging/TeamsMessagingRequest';
import { TeamsMessagingResponse } from './models/TeamsMessaging/TeamsMessagingResponse';
import { NavigationService, TeamsMessaging } from './services/SdkNativeModules';

export class TeamsModules {
  /**
   * API to open contact card <br>
   *
   * Pass mri as id if exists with type org, otherwise pass contactId with type contact
   * 
   * @param {Contact} Contact - Contact info required to open contact card
   * 
   */
  public static openContactCard (contact: Contact): void {
    NavigationService.openContactCard(contact);
  }

  public static startAudioCall (contact: Contact): void {
    NavigationService.startAudioCall(contact);
  }

  public static openChat (conversationId: string): void {
    NavigationService.openChat(conversationId);
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
  public static postMessage (params: TeamsMessagingRequest): Promise<TeamsMessagingResponse> {

    const filesArray = params.files as Dictionary<any>;
    if (filesArray) {
      const tempFiles = new Array<Dictionary<any>>();
      filesArray.forEach(element => {
        const dict = element;
        dict["id"] = element["itemid"];
        dict["@type"] = "http://schema.skype.com/File";
        tempFiles.push(dict);
      });
      params.files = tempFiles as any;
    }

    const mentionDict = params.mentionDictionary as Dictionary<any>;
    for (const key in mentionDict) {
      const value = mentionDict[key];
      value["@type"] = "http://schema.skype.com/Mention";
      mentionDict[key] = value;
    }

    return TeamsMessaging.postMessageWithParams(params);
  }

  public static openChatWithUserMris (userMris: string[]): void {
    NavigationService.openChatWithUserMris(userMris);
  }

  /**
   * API to open message details page
   * @param eventId ID of exchange calendar event.
   * @param isSeriesMaster true if event is of type SeriesMaster, else false
   */
  public static openMeetingDetails (eventId: string, isSeriesMaster: boolean): void {
    NavigationService.openMeetingDetails(eventId, isSeriesMaster);
  }
}
