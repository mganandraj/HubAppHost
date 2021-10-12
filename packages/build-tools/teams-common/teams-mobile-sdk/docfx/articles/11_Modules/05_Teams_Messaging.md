# Teams_Messaging
Teams Mobile SDK provides the ability to post message to Teams channel or Teams chat window.


## Posting a message

### postMessage (params: Dictionary<any>)
This method post the message with the given params.

**Note:** Only @mention of place or channel, hyperlink & files are supported now in message.

#### Example
This example post message to channel.

```typescript
import { 
  TeamsModules, 
  TeamsMessagingResponse, 
  TeamsMessagingRequest, 
  TeamsMessagingFileInfo 
} from 'teams-mobile-sdk';

const conversationId = '19:4123d4f3bcf5487fa9697fbbc344c66b@thread.skype';
const textMessage = '<span contenteditable=\"false\" itemscope=\"\" itemtype=\"http://schema.skype.com/Mention\" itemid=\"0\">Vivek Ojha</span> was mentioned in <a href=\"https://teams.microsoft.com/l/entity/com.microsoft.teamspace.tab.wiki/tab%3a%3a6fc945c4-31cd-4738-8b7c-b3629d963252?label=First+page&context=%7b%0d%0a++%22subEntityId%22%3a+%22%7b%5c%22pageId%5c%22%3a9%2c%5c%22sectionId%5c%22%3a14%2c%5c%22origin%5c%22%3a2%7d%22%2c%0d%0a++%22canvasUrl%22%3a+%22https%3a%2f%2fteams.microsoft.com%2fl%2ftab%2f19%253a4123d4f3bcf5487fa9697fbbc344c66b%2540thread.skype%2ftab%253a%253a6fc945c4-31cd-4738-8b7c-b3629d963252%3flabel%3dFirst%2bpage%26tenantId%3d72f988bf-86f1-41af-91ab-2d7cd011db47%22%2c%0d%0a++%22channelId%22%3a+%2219%3a4123d4f3bcf5487fa9697fbbc344c66b%40thread.skype%22%2c%0d%0a++%22contextType%22%3a+%22channel%22%0d%0a%7d&tenantId=72f988bf-86f1-41af-91ab-2d7cd011db47\">First page';

const file = {
      itemid: 'tab::6fc945c4-31cd-4738-8b7c-b3629d963252',
      fileName: 'mentions - conv test in Page name',
      fileType: 'deeplink',
      fileInfo: { serverRelativeUrl : '{\'pageId\':9,\'sectionId\':13,\'origin\':2}' } as TeamsMessagingFileInfo,
      botFileProperties: {}
    } as TeamsMessagingFile;

const mentionDict = {
        0 : { 
          displayName: 'Vivek Ojha',
          itemid: 0,
          _index: 0,
          mentionType: TeamsMessagingMentionType.PERSON,
          mri: '8:orgid:a1bca144-15ad-4338-b99b-a33d540ebfc5'} as TeamsMessagingMention
    }
const params = {
      threadID: conversationId,
      text:  textMessage,
      messageType: TeamsMessageType.HTML,
      rootMessageID: '1549517187171', // message id from response
      mentionDictionary: mentionDict,
      files: [file]
    } as TeamsMessagingRequest;

TeamsModules.postMessage(params);
  .then((response: TeamsMessagingResponse) => {
    if (response.messageID) {
      Alert.alert(Resource.getLocalizedString('info'), response.messageID, [{ text: Resource.getLocalizedString('ok') }]);
    } else {
      Alert.alert(Resource.getLocalizedString('state_layout_error'), 'Failed with status code ' + response.statusCode, [{ text: Resource.getLocalizedString('ok') }]);
    }
  }).catch((error) => {
    Alert.alert(Resource.getLocalizedString('state_layout_error'), error, [{ text: Resource.getLocalizedString('ok') }]);
  });
```

See [API docs](xref:teams-mobile-sdk.TeamsModules.postMessage) to learn more.
