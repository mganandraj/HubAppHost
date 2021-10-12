/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import * as React from 'react';
import {
  Alert,
  AlertButton,
  Button,
  Modal,
  Platform,
  ScrollView,
  StyleSheet,
  Text,
  TextInput,
  ToastAndroid,
  View
} from 'react-native';
import {
  Contact,
  DeepLinkUtils,
  Resource,
  TeamsMessageCardType,
  TeamsMessageType,
  TeamsMessagingCard,
  TeamsMessagingFileInfo,
  TeamsMessagingRequest,
  TeamsMessagingResponse,
  TeamsModules,
  TeamsView,
  TraceLogger
} from 'teams-mobile-sdk';
import { TeamsMessagingFile } from 'teams-mobile-sdk/dist/lib/models/TeamsMessaging/TeamsMessagingFile';
import { TeamsMessagingMention, TeamsMessagingMentionType } from 'teams-mobile-sdk/dist/lib/models/TeamsMessaging/TeamsMessagingMention';

const styles = StyleSheet.create({
  container: {
    justifyContent: 'center',
    padding: 20
  },
  margin: {
    margin: 10
  },
  modal: {
    marginTop: 450,
    flex: 1,
    justifyContent: 'flex-end',
    alignItems: 'center',
    backgroundColor: 'yellow'
  },
  text : {
    height: 30,
    width: 290,
    borderColor: 'gray',
    borderWidth: 1
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10
  }
});

class Person {
  public id: Number;
  public name: string;
  public member: boolean;

  constructor (id, name, member) {
    this.id = id;
    this.name = name;
    this.member = member;
  }
}

export class NavigationComponent extends TeamsView<any, any> {
  private static readonly LOG_TAG: string = 'NavigationComponent';

  constructor (props: any, state?: any) {
    super(props, state);
    this.state = {
      modalVisible: false,
      modalAnimation: 'slide' as 'slide',
      viewId: '',
      result: undefined
    };

    if(Platform.OS === 'ios')
    {
      // This will set the background color of the home indicator to fully opaque blue since red and green are 00 but opacity and blue are set to FF (full).
      this.getTeamsShell().setHomeIndicatorBackgroundColorIOS('#FF0000FF');
    }
  }
  
  public render () {
    return (
      <ScrollView contentContainerStyle={styles.container}>
        <Button
          onPress={this.handleOpenContactCardBtnPress.bind(this)}
          title={Resource.getLocalizedString('open_contact_card_btn_title')}
          accessibilityLabel={Resource.getLocalizedString('open_contact_card_btn_accessibility_label')}
        />
        <View style={styles.margin} />
        <Button
          onPress={this.handleStartAudioCallBtnPress.bind(this)}
          title={Resource.getLocalizedString('start_audio_call_btn_title')}
          accessibilityLabel={Resource.getLocalizedString('start_audio_call_btn_accessibility_label')}
        />
        <View style={styles.margin} />
        <Button
          onPress={this.handleOpenChatBtnPress.bind(this)}
          title={Resource.getLocalizedString('open_chat_btn_title')}
          accessibilityLabel={Resource.getLocalizedString('open_chat_btn_accessibility_label')}
        />
        <View style={styles.margin} />
        <Button
        onPress={this.handlePostMessageBtnPress.bind(this)}
        title={Resource.getLocalizedString('post_message_btn_title')}
        accessibilityLabel={Resource.getLocalizedString('post_message_btn_accessibility_label')}
        />
        <View style={styles.margin} />
        <Button
        onPress={this.handlePostAdaptiveCardBtnPress.bind(this)}
        title={Resource.getLocalizedString('post_adaptive_card_btn_title')}
        accessibilityLabel={Resource.getLocalizedString('post_message_btn_accessibility_label')}
        />
        <View style={styles.margin} />
        <Button
          onPress={this.handleOpenChatWithUserMrisBtnPress.bind(this)}
          title={Resource.getLocalizedString('open_chat_with_user_mris_btn_title')}
          accessibilityLabel={Resource.getLocalizedString('open_chat_with_user_mris_btn_accessibility_label')}
        />
        <View style={styles.margin} />
        <Button
          onPress={this.handleOpenMeetingDetailsBtnPress.bind(this)}
          title={Resource.getLocalizedString('open_meeting_details_btn_title')}
          accessibilityLabel={Resource.getLocalizedString('open_meeting_details_btn_accessibility_label')}
        />
        <View style={styles.margin} />
        <Button
          onPress={this.handleOpenViewBtnPress.bind(this)}
          title={Resource.getLocalizedString('open_view_btn_title')}
          accessibilityLabel={Resource.getLocalizedString('open_view_btn_accessibility_label')}
        />
        <View style={styles.margin} />
        <Button
          onPress={() => {
            this.openViewForResult<Person, any>('home', new Person(1, 'John Doe', true), (result: any) => {
              const newState: any = this.state;
              newState.result = result;
              this.setState(newState);
            });
          }}
          title='Open home view (for result)'
          accessibilityLabel={Resource.getLocalizedString('open_view_btn_accessibility_label')}
        />
        <Text style={styles.hello}>
          {JSON.stringify(this.state.result)}
        </Text>
        <View style={styles.margin} />
        <Button
          onPress={this.handleOpenViewDeepLinkBtnPress.bind(this)}
          title={Resource.getLocalizedString('open_view_deep_link_btn_title')}
          accessibilityLabel={Resource.getLocalizedString('open_view_deep_link_btn_accessibility_label')}
        />
        <View style={styles.margin} />
        <Button
          onPress={() => {
            this.setModalVisible();
          }}
          title = {Resource.getLocalizedString('open_modal_btn_title')}
        />
        <View style={styles.margin} />
        <Text>Back navigation handling</Text>
        <Button onPress={() => { this.getTeamsShell().registerBackNavigationHandler(this.onNavigateBack); }}
                title='Register back navigation handler'/>
        <Button onPress={() => { this.getTeamsShell().removeBackNavigationHandler(); }}
                title='Remove back navigation handler'/>
        <Modal
          animationType={this.state.modalAnimation}
          visible={this.state.modalVisible}
          transparent={true}
          >
          <View style={styles.modal}>
            <View style={{ marginBottom: 57 }}>
              <TextInput style={styles.text}
                onChangeText={(text) => this.setState({ viewId: text })}
                value={this.state.viewId}>
              </TextInput>
              <Button
                onPress={this.setModalInVisible.bind(this)}
                title = {Resource.getLocalizedString('hide_modal_btn_title')}
              />
              <Button
                onPress={this.handleOpenViewBtnPressWithResult.bind(this)}
                title={Resource.getLocalizedString('open_view_btn_title_with_result')}
                accessibilityLabel={Resource.getLocalizedString('open_view_btn_accessibility_label')}
              />
            </View>
          </View>
        </Modal>
      </ScrollView>
    );
  }

  public onNavigateBack = () => {
    // show alert!
    if (Platform.OS === 'ios') {
      const buttons: AlertButton[] = [
        {
          text: 'Keep Editing',
          onPress: () => {
            // do nothing!
          },
          style: 'default'
        },
        {
          text: 'Discard changes',
          onPress: () => {
            this.closeView();
          },
          style: 'destructive'
        }
      ];
      Alert.alert('Do you really want to go back?', 'You will lose unsaved changes!', buttons);
    } else if (Platform.OS === 'android') {
      const buttons: AlertButton[] = [
        {
          text: 'Discard changes',
          onPress: () => {
            this.closeView();
          },
        },
        {
          text: 'Keep Editing',
          onPress: () => {
            // do nothing!
          },
        }
      ];
      Alert.alert('Do you really want to go back?', 'You will lose unsaved changes!', buttons);
    }
  }

  public setModalVisible () {
    this.setState({ modalVisible: true });
  }

  public setModalInVisible () {
    this.setState({ modalVisible: false });
  }

  public handleOpenContactCardBtnPress () {
    const currentUser = this.getApplicationContext().getCurrentUser();
    const currentUserContact = {
      id: currentUser.skypeMri,
      name: currentUser.displayName,
      type: 'org'
    } as Contact;

    TeamsModules.openContactCard(currentUserContact);
  }

  public handleStartAudioCallBtnPress () {
    const currentUser = this.getApplicationContext().getCurrentUser();
    const currentUserContact = {
      id: currentUser.skypeMri,
      name: currentUser.displayName,
      type: 'org'
    } as Contact;

    TeamsModules.startAudioCall(currentUserContact);
  }

  public handleOpenChatBtnPress () {
    const conversationId = '19:3129261c-561b-45ea-abea-0358c6178db3_9ccdca84-e5fd-4f01-be65-97d4c1801afb@unq.gbl.spaces'; // Chat with User 2
    TeamsModules.openChat(conversationId);
  }

  public handlePostMessageBtnPress () {
    
    const conversationId = '19:26b1b8c61c584a129ac03e63226c5d2e@thread.skype'; // RN Hello World Testing
    const textMessage = 'The conversation about Test in test has begun.';
   
    const file = {
      itemid: 'tab::5391ce7a-6c37-426f-ab14-1d1f4c17142f',
      fileName: 'Test in test',
      fileType: 'deeplink',
      fileInfo: { serverRelativeUrl : '{\'pageId\':2,\'sectionId\':3,\'origin\':2}' } as TeamsMessagingFileInfo,
      botFileProperties: {}
    } as TeamsMessagingFile;

    const params = {
      threadID: conversationId,
      text:  textMessage,
      messageType: TeamsMessageType.TEXT,
      files: [file]
    } as TeamsMessagingRequest;
    
    TeamsModules.postMessage(params)
    .then((response: TeamsMessagingResponse) => {
      if (response.messageID) {
        this.postReplyMessage(response.messageID);
        Alert.alert(Resource.getLocalizedString('info'), response.messageID, [{ text: Resource.getLocalizedString('ok') }]);
      } else {
        Alert.alert(Resource.getLocalizedString('state_layout_error'), 'Failed with status code ' + response.statusCode, [{ text: Resource.getLocalizedString('ok') }]);
      }
     
    }).catch((error) => {
      Alert.alert(Resource.getLocalizedString('state_layout_error'), error, [{ text: Resource.getLocalizedString('ok') }]);
    });
  }

  public handlePostAdaptiveCardBtnPress () {
    
    const conversationId = '19:99b679b2-6a1d-4caf-a360-e199a5df9ed9_a4fa90b5-cf4b-4a09-9269-6d3a3488d853@unq.gbl.spaces'; 
    const cardJson = {
      "type": "AdaptiveCard",
      "version": "1.2",
      "body": [
        {
          "type": "TextBlock",
          "text": "Press the buttons to toggle the images!",
          "wrap": true
        },
        {
          "type": "TextBlock",
          "text": "Here are some images:",
          "isVisible": false,
          "id": "textToToggle"
        },
        {
          "type": "ColumnSet",
          "columns": [
            {
              "type": "Column",
              "items": [
                {
                  "style": "person",
                  "type": "Image",
                  "url": "https://picsum.photos/100/100?image=112",
                  "isVisible": false,
                  "id": "imageToToggle",
                  "size": "medium"
                }
              ]
            },
            {
              "type": "Column",
              "items": [
                {
                  "type": "Image",
                  "url": "https://picsum.photos/100/100?image=123",
                  "isVisible": false,
                  "id": "imageToToggle2",
                  "size": "medium"
                }
              ]
            }
          ]
        }
      ],
      "actions": [
        {
          "type": "Action.ToggleVisibility",
          "title": "Toggle!",
          "targetElements": [ "textToToggle", "imageToToggle", "imageToToggle2" ]
        },
        {
          "type": "Action.ToggleVisibility",
          "title": "Also Toggle!",
          "targetElements": [
            {
              "elementId": "textToToggle"
            },
            {
              "elementId": "imageToToggle"
            },
            {
              "elementId": "imageToToggle2"
            }
          ]
        },
        {
          "type": "Action.ToggleVisibility",
          "title": "Show!",
          "targetElements": [
            {
              "elementId": "textToToggle",
              "isVisible": true
            },
            {
              "elementId": "imageToToggle",
              "isVisible": true
            },
            {
              "elementId": "imageToToggle2",
              "isVisible": true
            }
          ]
        },
        {
          "type": "Action.ToggleVisibility",
          "title": "Hide!",
          "targetElements": [
            {
              "elementId": "textToToggle",
              "isVisible": false
            },
            {
              "elementId": "imageToToggle",
              "isVisible": false
            },
            {
              "elementId": "imageToToggle2",
              "isVisible": false
            }
          ]
        },
        {
          "type": "Action.ToggleVisibility",
          "title": "Grain!",
          "targetElements": [
            {
              "elementId": "textToToggle",
              "isVisible": true
            },
            {
              "elementId": "imageToToggle",
              "isVisible": true
            },
            {
              "elementId": "imageToToggle2",
              "isVisible": false
            }
          ]
        },
        {
          "type": "Action.ToggleVisibility",
          "title": "Water!",
          "targetElements": [
            {
              "elementId": "textToToggle",
              "isVisible": true
            },
            {
              "elementId": "imageToToggle",
              "isVisible": false
            },
            {
              "elementId": "imageToToggle2",
              "isVisible": true
            }
          ]
        }
      ]
    };

    const card = {
      cardJson: JSON.stringify(cardJson),
      cardType: TeamsMessageCardType.ADAPTIVE_CARD
    } as TeamsMessagingCard;

    const params = {
      threadID: conversationId,
      cards: [card],
      messageType: TeamsMessageType.HTML
    } as TeamsMessagingRequest;
    
    TeamsModules.postMessage(params)
    .then((response: TeamsMessagingResponse) => {
      if (response.messageID) {
        Alert.alert(Resource.getLocalizedString('info'), response.messageID, [{ text: Resource.getLocalizedString('ok') }]);
      } else {
        Alert.alert(Resource.getLocalizedString('state_layout_error'), 'Failed with status code ' + response.statusCode, [{ text: Resource.getLocalizedString('ok') }]);
      }
     
    }).catch((error) => {
      Alert.alert(Resource.getLocalizedString('state_layout_error'), error, [{ text: Resource.getLocalizedString('ok') }]);
    });
  }

  public postReplyMessage(rootMessageID: String) {

    const conversationId = '19:26b1b8c61c584a129ac03e63226c5d2e@thread.skype';
    const textMessage = '<span contenteditable=\"false\" itemscope=\"\" itemtype=\"http://schema.skype.com/Mention\" itemid=\"0\">User 2</span> was mentioned in <a href=\"https://teams.microsoft.com/l/entity/com.microsoft.teamspace.tab.wiki/tab%3A%3A5391ce7a-6c37-426f-ab14-1d1f4c17142f?label=test&context=%7b%0d%0a++%22subEntityId%22%3a+%22%7b%5c%22pageId%5c%22%3a2%2c%5c%22sectionId%5c%22%3a3%2c%5c%22origin%5c%22%3a2%7d%22%2c%0d%0a++%22canvasUrl%22%3a+%22https%3a%2f%2fteams.microsoft.com%2fl%2ftab%2f19%253A26b1b8c61c584a129ac03e63226c5d2e%2540thread.skype%2ftab%253A%253A5391ce7a-6c37-426f-ab14-1d1f4c17142f%3flabel%3dtest%26tenantId%3dc2b7e3f0-bbb1-4bbb-8432-de49e336aa5d%22%2c%0d%0a++%22channelId%22%3a+%2219%3a26b1b8c61c584a129ac03e63226c5d2e%40thread.skype%22%2c%0d%0a++%22contextType%22%3a+%22channel%22%0d%0a%7d&tenantId=c2b7e3f0-bbb1-4bbb-8432-de49e336aa5d\">test';
    
    const mentionDict = {
        0 : { 
          displayName: 'User 2',
          itemid: 0,
          _index: 0,
          mentionType: TeamsMessagingMentionType.PERSON,
          mri: '8:orgid:9ccdca84-e5fd-4f01-be65-97d4c1801afb'} as TeamsMessagingMention
    };
    const params = {
      threadID: conversationId,
      text:  textMessage,
      messageType: TeamsMessageType.HTML,
      rootMessageID: String(rootMessageID),
      mentionDictionary: mentionDict
    } as TeamsMessagingRequest;

    TeamsModules.postMessage(params)
    .then((response: TeamsMessagingResponse) => {
      if (response.messageID) {
        Alert.alert(Resource.getLocalizedString('info'), response.messageID, [{ text: Resource.getLocalizedString('ok') }]);
      } else {
        Alert.alert(Resource.getLocalizedString('state_layout_error'), 'Failed with status code ' + response.statusCode, [{ text: Resource.getLocalizedString('ok') }]);
      }
    }).catch((error) => {
      Alert.alert(Resource.getLocalizedString('state_layout_error'), error, [{ text: Resource.getLocalizedString('ok') }]);
    });
  }

  public handleOpenChatWithUserMrisBtnPress () {
    const mriList: string[] = ["8:orgid:9ccdca84-e5fd-4f01-be65-97d4c1801afb"]; // User 2
    TeamsModules.openChatWithUserMris(mriList);
  }

  public handleOpenMeetingDetailsBtnPress () {
    const eventId: string = "AAMkADVjOTQ1ZmU2LTFhYWEtNGNjMC1iYjc2LTFhZWZjM2VhYTMyZgBGAAAAAAA6h6yyf5K5Q7sPdNecz-_NBwBuJbC1u3duRKZtWG47p6lhAAAAAAENAABuJbC1u3duRKZtWG47p6lhAABfdgYJAAA=";
    TeamsModules.openMeetingDetails(eventId, false);
  }

  public handleOpenViewBtnPress () {
    const person: Person = new Person('123','Test', true);
    this.openView<Person>('home', person);
  }

  public handleOpenViewBtnPressWithResult () {
    const person: Person = new Person('123','Test', true);
    const params = {
      callbackParams: {
        modalVisible: this.state.modalVisible
      },
      message: Resource.getLocalizedString('open_view_msg'),
      person : person
    };
    this.setModalInVisible();
    this.openViewForResult<object, object>(this.state.viewId, params, (hostResult: object) => {
      if (hostResult.hasOwnProperty('callbackParams')) {
        const hostParams = hostResult['callbackParams'];
        if (hostParams.hasOwnProperty('modalVisible')) {
          this.setState({ modalVisible: hostParams.modalVisible });
        }
      }
    });
  }

  public handleOpenViewDeepLinkBtnPress () {
    const viewParams = {
      message: Resource.getLocalizedString('open_view_deep_link_msg')
    };

    DeepLinkUtils.getDeepLinkForModule('9a1f0cd2-ff89-443f-9618-01993b1c5ff0', JSON.stringify(viewParams))
      .then((result: string) => {
        DeepLinkUtils.executeDeepLink(result);
      }).catch((error) => {
        TraceLogger.logError(NavigationComponent.LOG_TAG, error);
      });
  }

  public onViewAppear (): void {
    console.log('inside navigation onViewAppear');
    if (Platform.OS === 'android') {
      ToastAndroid.show('inside navigation onViewAppear', ToastAndroid.SHORT);
    }
  }
}
