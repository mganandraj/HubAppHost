/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import * as React from 'react';
import {
  Button,
  Image,
  Platform,
  ScrollView,
  StyleSheet,
  Text,
  ToastAndroid,
  View
} from 'react-native';

import {
  AppTheme,
  ChannelPicker,
  ChannelPickerOptions,
  ChannelPickerResult,
  Contact,
  ImageAndFilePicker,
  OptionsMenuItem,
  Resource,
  ShareTarget,
  Snackbar,
  SnackbarAction,
  SnackbarDuration,
  TeamsView,
  TelemetryClient,
  TraceLogger,
  User
} from 'teams-mobile-sdk';

import { ShareObject } from 'teams-mobile-sdk/dist/lib/models/share/ShareObject';
import { TitleDropdownItem } from 'teams-mobile-sdk/dist/lib/models/shell/TitleDropdownItem';
import { ShareUtils } from 'teams-mobile-sdk/dist/lib/services/SdkNativeModules';
import { UserPDCLevel } from 'teams-mobile-sdk/dist/lib/services/TelemetryClient';
import { ActionSheetsComponent } from './ActionSheetsComponent';
import { AuthenticationComponent } from './AuthenticationComponent';
import { DateTimePickerAndroidComponent } from './DateTimePickerAndroidComponent';
import { DeviceContactsComponent } from './DeviceContactsComponent';
import { FabLayoutComponent } from './FabLayoutComponent';
import { HttpClientComponent } from './HttpClientComponent';
import { PeopleComponent } from './PeopleComponent';
import { UserInfoComponent } from './UserInfoComponent';
import { Utilities } from './utilities/Utilities';

const appVersion = require('../../resources/manifest/manifest.json').version;
const minReactNativeSdkVersion = require('../../resources/manifest/manifest.json').minReactNativeSdkVersion;
const targetReactNativeSdkVersion = require('../../resources/manifest/manifest.json').targetReactNativeSdkVersion;
const styles = StyleSheet.create({
  container: {
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20
  },
  margin: {
    margin: 10
  },
  margin_btw_buttons: {
    margin: 5
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10
  }
});

export interface GreetingComponentProps {
  userGreeting: string;
  sharedText: string;
  sharedImages: string[];
  contactToEdit: Contact;
  callbackParams: any;
}

export interface GreetingComponentState {
  channelPickerResult?: ChannelPickerResult;
  channelPickerCanceled?: boolean;
  titleDropdownSelection: string;
  toast?: string;
}

const TITLE_DROPDOWN_IDS = {
  DEFAULT_VIEW: 'default_view',
  FAB_DEMO: 'fab_demo',
  ACTION_SHEETS_DEMO: 'action_sheets_demo',
  DATE_PICKER_DEMO: 'date_picker_demo',
  AUTH_DEMO: 'auth_demo',
  USER_INFO_DEMO: 'user_info_demo',
  PEOPLE_DEMO: 'people_demo',
  DEVICE_CONTACTS_DEMO: 'device_contants_demo',
  HTTP_CLIENT_DEMO: 'http_client_demo'
};

const titleDropdownItems = Platform.select<TitleDropdownItem[]>({
  ios: [
    {
      id: TITLE_DROPDOWN_IDS.DEFAULT_VIEW,
      title: 'Home'
    },
    {
      id: TITLE_DROPDOWN_IDS.ACTION_SHEETS_DEMO,
      title: 'Action sheets demo'
    },
    {
      id: TITLE_DROPDOWN_IDS.AUTH_DEMO,
      title: 'Auth demo'
    },
    {
      id: TITLE_DROPDOWN_IDS.USER_INFO_DEMO,
      title: 'User info demo'
    },
    {
      id: TITLE_DROPDOWN_IDS.PEOPLE_DEMO,
      title: 'People demo'
    },
    {
      id: TITLE_DROPDOWN_IDS.DEVICE_CONTACTS_DEMO,
      title: 'Device contacts demo'
    },
    {
      id: TITLE_DROPDOWN_IDS.HTTP_CLIENT_DEMO,
      title: 'HTTP client demo'
    }
  ],
  android: [
    {
      id: TITLE_DROPDOWN_IDS.DEFAULT_VIEW,
      title: 'Home'
    },
    {
      id: TITLE_DROPDOWN_IDS.ACTION_SHEETS_DEMO,
      title: 'Action sheets demo'
    },
    {
      id: TITLE_DROPDOWN_IDS.AUTH_DEMO,
      title: 'Auth demo'
    },
    {
      id: TITLE_DROPDOWN_IDS.USER_INFO_DEMO,
      title: 'User info demo'
    },
    {
      id: TITLE_DROPDOWN_IDS.PEOPLE_DEMO,
      title: 'People demo'
    },
    {
      id: TITLE_DROPDOWN_IDS.DEVICE_CONTACTS_DEMO,
      title: 'Device contacts demo'
    },
    {
      id: TITLE_DROPDOWN_IDS.HTTP_CLIENT_DEMO,
      title: 'HTTP client demo'
    },
    {
      id: TITLE_DROPDOWN_IDS.FAB_DEMO,
      title: 'Fab demo'
    },
    {
      id: TITLE_DROPDOWN_IDS.DATE_PICKER_DEMO,
      title: 'Date picker demo'
    }
  ]
}) as TitleDropdownItem[];

export class GreetingComponent extends TeamsView<GreetingComponentProps, GreetingComponentState> {
  private static readonly LOG_TAG: string = 'GreetingComponent';

  private userGreeting: string;
  private sharedText: string;
  private sharedImage: string;
  private contactToEdit: Contact;
  private callbackParams: any;

  constructor(props: GreetingComponentProps, state?: any) {
    super(props, state);

    this.userGreeting = 'Hello!\nPlease select an option from the action bar.';
    this.sharedText = props.sharedText;
    this.contactToEdit = props.contactToEdit;
    if (props.callbackParams) {
      this.callbackParams = props.callbackParams;
    }

    if (props.sharedImages) {
      this.sharedImage = props.sharedImages[0];
    }

    this.state = {
      channelPickerResult: undefined,
      channelPickerCanceled: false,
      titleDropdownSelection: TITLE_DROPDOWN_IDS.DEFAULT_VIEW,
      toast: undefined
    } as GreetingComponentState;

    this.getTeamsShell().setTitleDropdown(titleDropdownItems, (selection: string) => {
      console.log(`User selected title dropdown item with id ${selection}`);
      const newState = { ...this.state };
      newState.titleDropdownSelection = selection;
      this.setState(newState);
    });

    this.getTeamsShell().setSubtitle(Resource.getLocalizedString("greeting_subTitle"));
  }

  public onChannelPicked = (result: ChannelPickerResult) => {
    this.setState({
      channelPickerResult: result
    });
  }

  public componentDidMount() {
    console.log("inside component did mount of greetings component");

    TelemetryClient.registerHandlerForPDCLevelChange((pdcLevel: UserPDCLevel) => {
      this.onPDCLevelChanged(UserPDCLevel[pdcLevel]);
    });
  }

  public componentWillUnmount() {
    console.log("inside component will unmount of greetings component");

    TelemetryClient.removeHandlerForPDCLevelChange();
  }

  public render() {
    if (this.state.titleDropdownSelection === TITLE_DROPDOWN_IDS.DEFAULT_VIEW) {
      return this.renderDefaultView();
    } else if (this.state.titleDropdownSelection === TITLE_DROPDOWN_IDS.FAB_DEMO) {
      return this.renderFabDemo();
    } else if (this.state.titleDropdownSelection === TITLE_DROPDOWN_IDS.ACTION_SHEETS_DEMO) {
      return this.renderActionSheetsDemo();
    } else if (this.state.titleDropdownSelection === TITLE_DROPDOWN_IDS.AUTH_DEMO) {
      return this.renderAuthComponent();
    } else if (this.state.titleDropdownSelection === TITLE_DROPDOWN_IDS.USER_INFO_DEMO) {
      return this.renderUserInfoComponent();
    } else if (this.state.titleDropdownSelection === TITLE_DROPDOWN_IDS.PEOPLE_DEMO) {
      return this.renderPeopleComponent();
    } else if (this.state.titleDropdownSelection === TITLE_DROPDOWN_IDS.DATE_PICKER_DEMO) {
      return this.renderDateTimePickerComponent();
    } else if (this.state.titleDropdownSelection === TITLE_DROPDOWN_IDS.DEVICE_CONTACTS_DEMO) {
      return this.renderDeviceContactsComponent();
    } else if (this.state.titleDropdownSelection === TITLE_DROPDOWN_IDS.HTTP_CLIENT_DEMO) {
      return this.renderHttpClientComponent();
    } else {
      return this.renderDefaultView();
    }
  }

  public async handleLaunchChannelPickerPressed() {
    try {
      const options: ChannelPickerOptions = {
        title: 'Pick a channel',
        preSelectedChannelId: (this.state.channelPickerResult) ? this.state.channelPickerResult.selectedChannel.id : undefined
      };
      const result: ChannelPickerResult = await ChannelPicker.launch(options);

      if (result !== undefined) {
        this.setState({
          channelPickerResult: result,
          channelPickerCanceled: false,
          titleDropdownSelection: this.state.titleDropdownSelection
        });
      } else {
        this.setState({
          channelPickerResult: undefined,
          channelPickerCanceled: true,
          titleDropdownSelection: this.state.titleDropdownSelection
        });
      }
    } catch (e) {
      this.setState({
        channelPickerResult: undefined,
        channelPickerCanceled: false,
        titleDropdownSelection: this.state.titleDropdownSelection
      });
    }
  }

  public openShareTargetPicker = async () => {
    try {
      const photo = await ImageAndFilePicker.pickPhotoFromGallery(false);
      console.log(`Picked photo: ${photo.fileIdentifiers[0]}, ${photo.statusCode}`);
      const shareTarget: ShareTarget = await ShareUtils.pickTargetFromShareTargetPicker();
      console.log(`User selected share target: ${shareTarget.id} (${shareTarget.type})`);
      ShareUtils.shareImagesToShareTarget(shareTarget, photo.fileIdentifiers);
    } catch (e) {
      console.log('Could not obtain share target');
    }
  }

  public shareText = async () => {
    try {
      // Sample text with link
      const shareObject: ShareObject = new ShareObject();
      const textToShare: string = "Learning Course\nhttps://medium.com/androiddevelopers/now-in-android-32-3e3eb4270a1f";
      shareObject.textToShare = textToShare;
      ShareUtils.pickShareTargetAndShareObject(shareObject);
    } catch (e) {
      console.log('Could not share info');
    }
  }

  public shareImagesAndText = async () => {
    try {
      const photo = await ImageAndFilePicker.pickPhotoFromGallery(false);
      console.log(`Picked photo: ${photo.fileIdentifiers[0]}, ${photo.statusCode}`);
      const shareTarget: ShareTarget = await ShareUtils.pickTargetFromShareTargetPicker();
      console.log(`User selected share target: ${shareTarget.id} (${shareTarget.type})`);
      const shareObject: ShareObject = new ShareObject();
      shareObject.imagesToShare = photo.fileIdentifiers;
      const textToShare: string = "Learning Course\nhttps://medium.com/androiddevelopers/now-in-android-32-3e3eb4270a1f";
      shareObject.textToShare = textToShare;
      ShareUtils.shareToShareTarget(shareTarget, shareObject);
    } catch (e) {
      console.log('Could not share info');
    }
  }

  public handleCloseBtnPress() {
    this.closeView(true);
  }

  public handleCloseBtnPressWithResult() {
    const per = {
      text: 'Coming from New Host',
      callbackParams: this.callbackParams ? this.callbackParams : {}
    };
    this.closeViewWithResult(per, true);
  }

  public handleUpdateContactBtnPress() {
    this.contactToEdit.name += Resource.getLocalizedString('greeting_update_contact_edited');
    this.contactToEdit.description = Resource.getLocalizedString('greeting_update_contact_edited_desc');
    this.closeModule(true);
  }

  public onOptionsMenuItemSelected(selectedMenuItemId: string): void {
    if (selectedMenuItemId === 'menuItem1' || selectedMenuItemId === 'menuItem2') {
      this.getTeamsShell().setTitle(Resource.getLocalizedString('hello_world_view_title_updated'));
      let finalSnackbarId = 0;
      const snackbar = {
        title: Resource.getLocalizedString('menu_item_selected') + selectedMenuItemId,
        duration: SnackbarDuration.INDEFINITE,
        action: {
          id: 'snackbar-dismiss-action',
          title: Resource.getLocalizedString('snackbar_dismiss_btn'),
          onSelected: () => {
            this.getTeamsShell().dismissSnackbar(finalSnackbarId, (dismissed: boolean) => {
              TraceLogger.logDebug(GreetingComponent.LOG_TAG, 'Snackbar (' + finalSnackbarId + ') dismissed: ' + dismissed);
            });
          }
        } as SnackbarAction
      } as Snackbar;

      this.getTeamsShell().showSnackbar(snackbar, (snackbarId: number) => {
        TraceLogger.logDebug(GreetingComponent.LOG_TAG, 'Snackbar shown with id: ' + snackbarId);
        finalSnackbarId = snackbarId;
      });
    }
  }

  public getOptionsMenuItems(): OptionsMenuItem[] {
    let appTheme = this.getApplicationContext().getCurrentAppTheme();
    if (!appTheme) {
      appTheme = AppTheme.DEFAULT;
    }
    return [
      { id: 'menuItem1', title: Resource.string('menu_item_1_title'), icon: Resource.image(appTheme + '/icn_menu_item_1.svg'), contentDescription: Resource.string('menu_item_1_title') },
      { id: 'menuItem2', title: Resource.string('menu_item_2_title'), icon: Resource.image(appTheme + '/icn_menu_item_2.svg'), contentDescription: Resource.string('menu_item_2_title') }
    ];
  }

  public onPrimaryFabClick(): void {
    console.log('Primary fab clicked');
    Utilities.showAlert('Primary Button clicked', ToastAndroid.SHORT);
  }

  public onSecondaryFabClick(_: string): void {
    console.log('Secondary fab clicked');
    Utilities.showAlert('Secondary Fab clicked', ToastAndroid.SHORT);
  }

  public onViewAppear(): void {
    console.log('inside greetings onViewAppear');
    if (Platform.OS === 'android') {
      ToastAndroid.show('inside greetings onViewAppear', ToastAndroid.SHORT);
    } else {
      const newState: GreetingComponentState = { ...this.state };
      newState.toast = 'onViewAppear called!';
      this.setState(newState);
    }
  }

  private getSelectedChannel(): string {
    if (this.state.channelPickerResult) {
      return `${this.state.channelPickerResult.parentTeam.name} > ${this.state.channelPickerResult.selectedChannel.name}`;
    } else if (this.state.channelPickerCanceled) {
      return 'Canceled';
    }

    return 'None';
  }

  private onPDCLevelChanged(event: string): void {
    let finalSnackbarId = 0;

    const snackbar = {
      title: Resource.getLocalizedString('get_user_pdc_level_message') + event,
      duration: SnackbarDuration.INDEFINITE,
      action: {
        id: 'snackbar-dismiss-action',
        title: Resource.getLocalizedString('snackbar_dismiss_btn'),
        onSelected: () => {
          this.getTeamsShell().dismissSnackbar(finalSnackbarId, (dismissed: boolean) => {
            TraceLogger.logDebug(GreetingComponent.LOG_TAG, 'Snackbar (' + finalSnackbarId + ') dismissed: ' + dismissed);
          });
        }
      } as SnackbarAction
    } as Snackbar;

    this.getTeamsShell().showSnackbar(snackbar, (snackbarId: number) => {
      finalSnackbarId = snackbarId;
    });
  }

  private renderDefaultView(): JSX.Element {
    const greeting = this.userGreeting;

    const sharedTextView = (this.sharedText ? <Text style={{ textAlign: 'center' }}>{this.sharedText}</Text> : null);
    const sharedImageView = (this.sharedImage ? <Image style={{ width: 100, height: 100 }} source={{ uri: this.sharedImage }} /> : null);

    const viewParams = this.getViewParams<any>();
    const appTheme = this.getApplicationContext().getCurrentAppTheme();
    const textColor = appTheme === AppTheme.DARK ? 'white' : 'black';
    let closeViewButtons: any = null;
    if (viewParams) {
      closeViewButtons = (
        <View>
          <View style={styles.margin} />
          <Button
            onPress={() => {
              this.closeViewWithResult({ greeting: `Hello, ${viewParams.name}!` }, true);
            }}
            title='Close view with result'
          />
          <View style={styles.margin} />
          <Button
            onPress={() => {
              this.closeView(true);
            }}
            title='Close view without result'
          />
        </View>
      );
    }

    setTimeout(() => {
      const newState: GreetingComponentState = this.state;
      newState.toast = undefined;
      this.setState(newState);
    }, 2000);

    return (
      <View
        nativeID='first-view-9a1f0cd2-ff89-443f-9618-01993b1c5ff0'>
        <ScrollView contentContainerStyle={styles.container}>
          {this.state.toast && <Text>{this.state.toast}</Text>}
          <Text style={{color: textColor}}>
            App Version : {appVersion}
          </Text>
          <Text style={{color: textColor}}>
            minReactNativeSdkVersion : {minReactNativeSdkVersion}
          </Text>
          <Text style={{color: textColor}}>
            targetReactNativeSdkVersion : {targetReactNativeSdkVersion}
          </Text>
          <Text style={{color: textColor}}>
            Teams Version
            : {this.getApplicationContext().getTelemetryInfo() == null ? "-" : this.getApplicationContext().getTelemetryInfo().teamsVersion}
          </Text>
          <Text style={{color: textColor}}>
            SDK Version
            : {this.getApplicationContext().getTelemetryInfo() == null ? "-" : this.getApplicationContext().getTelemetryInfo().sdkVersion}
          </Text>
          <Text style={{color: textColor}}>
            User PDC Level
            : {this.getApplicationContext().getTelemetryInfo() == null ? "-" : UserPDCLevel[this.getApplicationContext().getTelemetryInfo().userPDCLevel]}
          </Text>
          <Text
            style={[styles.hello, {color: textColor}]}>
            {greeting}
          </Text>
          <View style={styles.margin}/>
          <Text
            style={[styles.hello, {color: textColor}]}>
            Received params: {this.getViewParams<any>() ? JSON.stringify(this.getViewParams<any>()) : 'None'}
          </Text>
          <View style={styles.margin}/>
          <Button
            onPress={this.handleCloseBtnPress.bind(this)}
            title={Resource.getLocalizedString('greeting_close_btn')}
          />
          <View style={styles.margin}/>
          {closeViewButtons}
          <View style={styles.margin}/>
          <Button
            onPress={this.handleCloseBtnPressWithResult.bind(this)}
            title={Resource.getLocalizedString('greeting_close_btn_with_result')}
          />
          <View style={styles.margin}/>
          {sharedTextView}
          {sharedImageView}
          <View style={styles.margin}/>
          <Button onPress={this.handleLaunchChannelPickerPressed.bind(this)}
                  title='Launch channel picker'/>
          <Text>
            Selected channel: {this.getSelectedChannel()}
          </Text>
          <View style={styles.margin_btw_buttons}/>
          <Button onPress={this.openShareTargetPicker} title='Share image'/>
          <View style={styles.margin_btw_buttons}/>
          <Button onPress={this.shareText} title='Text share'/>
          <View style={styles.margin_btw_buttons}/>
          <Button onPress={this.openAsyncStorageDemo}
                  title='Async Storage demo'/>
          <View style={styles.margin_btw_buttons}/>
          <Button onPress={this.openImageAndFilePickerDemo}
                  title='Image and file picker demo'/>
          <View style={styles.margin_btw_buttons}/>
          <Button onPress={this.openNavigationComponent}
                  title='Navigation demo'/>
          <View style={styles.margin_btw_buttons}/>
          <Button onPress={this.openNetworkConnectivityComponent}
                  title='Network connectivity demo'/>
          <View style={styles.margin_btw_buttons}/>
          <Button onPress={this.openSharepointFilePreviewComponent}
                  title='Sharepoint file preview demo'/>
          <View style={styles.margin_btw_buttons}/>
          <Button onPress={this.openStateLayoutComponent}
                  title='StateLayout demo'/>
          <View style={styles.margin_btw_buttons}/>
          <Button onPress={this.openTeamsAndChannelsApiComponent}
                  title='Teams and channels API demo'/>
          <View style={styles.margin_btw_buttons}/>
          <Button onPress={this.openTabsInChatsApiComponent}
                  title='Chats API demo'/>
          <View style={styles.margin_btw_buttons}/>
          <Button onPress={this.openTeamsWebViewComponent}
                  title='Teams WebView demo'/>
          <View style={styles.margin_btw_buttons}/>
          <Button onPress={this.openTelemetryClientComponent}
                  title='Telemetry demo'/>
          <View style={styles.margin_btw_buttons}/>
          <Button onPress={this.openUserAvatarComponent}
                  title='User avatar component demo'/>
          <View style={styles.margin_btw_buttons}/>
          <Button onPress={this.openTabbedComponent}
                  title='Tabbed component demo'/>
          <View style={styles.margin_btw_buttons}/>
          <Button onPress={this.openFluentIconsDemo} title='Fluent icons demo'/>
          <View style={styles.margin_btw_buttons}/>
          <Button onPress={this.openSecureStorageDemo}
                  title='Secure Storage demo'/>
          <View style={styles.margin_btw_buttons}/>
        </ScrollView>
      </View>
    );
  }

  private openAsyncStorageDemo = () => {
    this.openView('asyncStorage', undefined);
  }

  private openImageAndFilePickerDemo = () => {
    this.openView('imageAndFilePicker', undefined);
  }

  private openNavigationComponent = () => {
    this.openView('navigation', undefined);
  }

  private openNetworkConnectivityComponent = () => {
    this.openView('networkConnectivity', undefined);
  }

  private openSharepointFilePreviewComponent = () => {
    this.openView('sharepointFilePreview', undefined);
  }

  private openStateLayoutComponent = () => {
    this.openView('stateLayout', undefined);
  }

  private openTeamsAndChannelsApiComponent = () => {
    this.openView('teamsAndChannelsApi', undefined);
  }

  private openTabsInChatsApiComponent = () => {
    this.openView('tabsInChatsApi', undefined);
  }

  private openTeamsWebViewComponent = () => {
    this.openView('teamsWebView', undefined);
  }

  private openTelemetryClientComponent = () => {
    this.openView('telemetryClient', undefined);
  }

  private openUserAvatarComponent = () => {
    this.openView('userAvatar', undefined);
  }

  private openTabbedComponent = () => {
    this.openView('tabbedComponent', undefined);
  }

  private openFluentIconsDemo = () => {
    this.openView('fluentIconsDemo', undefined);
  }

  private openSecureStorageDemo = () => {
    this.openView('secureStorage', undefined);
  }

  private renderFabDemo(): JSX.Element {
    return (
      <FabLayoutComponent teamsShell={this.getTeamsShell()}
        theme={this.getApplicationContext().getCurrentAppTheme()} />
    );
  }

  private renderActionSheetsDemo(): JSX.Element {
    return (
      <ActionSheetsComponent
        teamsShell={this.getTeamsShell()}
        theme={this.getApplicationContext().getCurrentAppTheme()} />
    );
  }

  private renderAuthComponent(): JSX.Element {
    return <AuthenticationComponent />;
  }

  private renderUserInfoComponent(): JSX.Element {
    return <UserInfoComponent />;
  }

  private renderPeopleComponent(): JSX.Element {
    const currentUser: User = this.getApplicationContext().getCurrentUser();

    return <PeopleComponent currentUser={currentUser} />;
  }

  private renderDateTimePickerComponent(): JSX.Element {
    return <DateTimePickerAndroidComponent />;
  }

  private renderDeviceContactsComponent(): JSX.Element {
    return <DeviceContactsComponent />;
  }

  private renderHttpClientComponent(): JSX.Element {
    return <HttpClientComponent />;
  }
}
