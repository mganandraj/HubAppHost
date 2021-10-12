jest.mock('../src/lib/services/SdkNativeModules');

import { TeamsModules } from '../src/lib/TeamsModules';
import { TeamsMessaging } from '../src/lib/services/SdkNativeModules';
import { TeamsMessagingRequest, TeamsMessageType }  from '../src/lib/models/TeamsMessaging/TeamsMessagingRequest';
import { TeamsMessagingFile, TeamsMessagingFileInfo }  from '../src/lib/models/TeamsMessaging/TeamsMessagingFile';
import { TeamsMessagingMention, TeamsMessagingMentionType } from '../src/lib/models/TeamsMessaging/TeamsMessagingMention';

describe('TeamsModules: postMessage tests', () => {

  test('should call post message', () => {

    const conversationId = '19:4123d4f3bcf5487fa9697fbbc344c66b@thread.skype';
    const textMessage = 'The conversation about mentions - conv test in Page name has begun.';
   
    const file = {
      version: 2,
      type: 'deeplink',
      title: 'mentions - conv test in Page name',
      parentContext: 'channel',
      itemid: 'tab::6fc945c4-31cd-4738-8b7c-b3629d963252',
      fileName: 'mentions - conv test in Page name',
      fileType: 'deeplink',
      fileInfo: { serverRelativeUrl : '{\'pageId\':9,\'sectionId\':13,\'origin\':2}' } as TeamsMessagingFileInfo,
      botFileProperties: {}
    } as TeamsMessagingFile;

    const params = {
      threadID : conversationId,
      text        :  textMessage,
      messageType : "Text",
      files : [file]
    } as TeamsMessagingRequest;

    // Act
    TeamsModules.postMessage(params);

    // Assert
    expect(TeamsMessaging.postMessageWithParams).toHaveBeenCalledTimes(1);
  });

  test('should call post message with file params', () => {

    const conversationId = '19:4123d4f3bcf5487fa9697fbbc344c66b@thread.skype';
    const textMessage = 'The conversation about mentions - conv test in Page name has begun.';
   
    const file = {
      itemid: 'tab::6fc945c4-31cd-4738-8b7c-b3629d963252',
      fileName: 'mentions - conv test in Page name',
      fileType: 'deeplink',
      fileInfo: { serverRelativeUrl : '{\'pageId\':9,\'sectionId\':13,\'origin\':2}' } as TeamsMessagingFileInfo,
      botFileProperties: {}
    } as TeamsMessagingFile;

    const params = {
      threadID : conversationId,
      text        :  textMessage,
      messageType : TeamsMessageType.TEXT,
      files : [file]
    } as TeamsMessagingRequest;

    const expectedParams = params;
    expectedParams.files = [ {
      '@types': 'http://schema.skype.com/File',
      'id': 'tab::6fc945c4-31cd-4738-8b7c-b3629d963252',
      'itemid': 'tab::6fc945c4-31cd-4738-8b7c-b3629d963252',
      'fileName': 'mentions - conv test in Page name',
      'fileType': 'deeplink',
      'fileInfo': { 'serverRelativeUrl' : '{\'pageId\':9,\'sectionId\':13,\'origin\':2}' } ,
      'botFileProperties': {}
    }] as any;
    // Act
    TeamsModules.postMessage(params);

    // Assert
    expect(TeamsMessaging.postMessageWithParams).toHaveBeenCalledWith(expectedParams);
  });

  test('should call post message with mention params', () => {

    const conversationId = '19:4123d4f3bcf5487fa9697fbbc344c66b@thread.skype';
    const textMessage = '<span contenteditable=\"false\" itemscope=\"\" itemtype=\"http://schema.skype.com/Mention\" itemid=\"0\">Vivek Ojha</span> was mentioned in <a href=\"https://teams.microsoft.com/l/entity/com.microsoft.teamspace.tab.wiki/tab%3a%3a6fc945c4-31cd-4738-8b7c-b3629d963252?label=First+page&context=%7b%0d%0a++%22subEntityId%22%3a+%22%7b%5c%22pageId%5c%22%3a9%2c%5c%22sectionId%5c%22%3a14%2c%5c%22origin%5c%22%3a2%7d%22%2c%0d%0a++%22canvasUrl%22%3a+%22https%3a%2f%2fteams.microsoft.com%2fl%2ftab%2f19%253a4123d4f3bcf5487fa9697fbbc344c66b%2540thread.skype%2ftab%253a%253a6fc945c4-31cd-4738-8b7c-b3629d963252%3flabel%3dFirst%2bpage%26tenantId%3d72f988bf-86f1-41af-91ab-2d7cd011db47%22%2c%0d%0a++%22channelId%22%3a+%2219%3a4123d4f3bcf5487fa9697fbbc344c66b%40thread.skype%22%2c%0d%0a++%22contextType%22%3a+%22channel%22%0d%0a%7d&tenantId=72f988bf-86f1-41af-91ab-2d7cd011db47\">First page';
    
    const mentionDict = {
        0 : { 
          displayName: 'Vivek Ojha',
          itemid: 0,
          _index: 0,
          mentionType: TeamsMessagingMentionType.PERSON,
          mri: '8:orgid:a1bca144-15ad-4338-b99b-a33d540ebfc5'} as TeamsMessagingMention
    };
    const params = {
      threadID: conversationId,
      text:  textMessage,
      messageType: TeamsMessageType.HTML,
      rootMessageID: '1549441761648',
      mentionDictionary: mentionDict
    } as TeamsMessagingRequest;

    const expectedParams = params;
    expectedParams.mentionDictionary =  {
      0 : { 
        '@type': 'http://schema.skype.com/Mention',
        'displayName': 'Vivek Ojha',
        'itemid': 0,
        '_index': 0,
        'mentionType': 'person',
        'mri': '8:orgid:a1bca144-15ad-4338-b99b-a33d540ebfc5'}
    } as any;
    // Act
    TeamsModules.postMessage(params);

    // Assert
    expect(TeamsMessaging.postMessageWithParams).toHaveBeenCalledWith(expectedParams);
  });
});
