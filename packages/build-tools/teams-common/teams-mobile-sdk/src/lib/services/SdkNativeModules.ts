/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import { NativeModules } from 'react-native';
import { ChannelPickerOptions } from '../models/channel-picker/ChannelPickerOptions';
import { ChannelPickerResult } from '../models/channel-picker/ChannelPickerResult';
import { Contact } from '../models/contact/Contact';
import { DeviceContact } from '../models/contact/DeviceContact';
import { HttpRequest } from '../models/http/HttpRequest';
import { HttpResponse } from '../models/http/HttpResponse';
import { DateTimeParams } from '../models/params/DateTimeParams';
import { ActionSheet } from '../models/shell/ActionSheet';
import { FabLayoutParams } from '../models/shell/FabLayoutParams';
import { OptionsMenuItem } from '../models/shell/OptionsMenuItem';
import { Snackbar } from '../models/shell/Snackbar';
import { TeamsShellTab } from '../models/shell/TeamsShellTab';
import { TitleDropdownItem } from '../models/shell/TitleDropdownItem';
import { ChatsProviderModule } from './native-modules/ChatsProviderModule';
import { ContactsProviderInterface } from './native-modules/ContactsProviderInterface';
import { ImageAndFilePickerInterface } from './native-modules/ImageAndFilePickerInterface';
import { PeopleProviderInterface } from './native-modules/PeopleProviderInterface';
import { TeamsAndChannelsProviderInterface } from './native-modules/TeamsAndChannelsProviderInterface';
import { TeamsMessagingInterface } from './native-modules/TeamsMessagingInterface';
import { TelemetryClientInterface } from './native-modules/TelemetryClientInterface';
import { ShareUtilsModule } from './ShareUtilsModule';
/**
 * Interface for native modules exposed from SDK.
 */
interface SdkNativeModulesInterface {
  sdkNativeEventPublisher: SdkNativeEventPublisherInterface;
  authenticationService: AuthenticationServiceInterface;
  logger: LoggerInterface;
  navigationService: NavigationServiceInterface;
  deviceContactsProvider: DeviceContactsProviderInterface;
  networkConnectivityManager: NetworkConnectivityManagerInterface;
  teamsShellInteractor: TeamsShellInteractorInterface;
  httpClient: HttpClientInterface;
  teamsAndChannelsProvider: TeamsAndChannelsProviderInterface;
  channelPicker: ChannelPickerInterface;
  dateTimePickerAndroid: DateTimePickerInterface;
  imageAndFilePicker: ImageAndFilePickerInterface;
  teamsMessenger: TeamsMessagingInterface;
  sharepointFilePreview: SharepointFilePreviewInterface;
  telemetryClient: TelemetryClientInterface;
  contactsProvider: ContactsProviderInterface;
  endpointProvider: EndpointProviderInterface;
  usersProvider: UserInfoProviderInterface;
  peopleProvider: PeopleProviderInterface;
  secureStorage: SecureStorageInterface;
}

/**
 * Publishes event from React Native to Native, i.e. JS to Java/Obj-C
 */
interface SdkNativeEventPublisherInterface {
  publishEvent (eventName: string, eventDetails: any);
}

/**
 * The authentication services enables developers to authenticate the user to their application resources.
 * Before fetching a resource token for a domain, the domain must first be declared within the app's manifest.
 *
 * **For example**
 * ```json
 * {
 *   "views": [
 *     // ...
 *   ],
 *   "authDomains": [
 *     "https://graph.microsoft.com"
 *   ]
 * }
 * ```
 *
 * Token requests for domains which are not declared within the manifest will fail.
 */
interface AuthenticationServiceInterface {
  /**
   * This method gets the resource token for the specified resource. If the resource is supported,
   * the method will return a valid access token for the resource. If the resource is not supported,
   * the method will fail with an error.
   *
   * @param resource resource for which the token is being requested
   * @returns {Promise<string>} A promise that returns the access token (type string) when resolved
   */
  getResourceToken (resource: string): Promise<string>;

  /**
   * This method gets the access token for the specified resource. If the resource is supported,
   * the method will return a valid access token for the resource. If the resource is not supported,
   * the method will fail with an error. 
   *
   * @param resource resource for which the token is being requested
   * @param silent optional flag indicating whether to attempt the token acquisition silently or 
   * allow a prompt to be shown. Default value of the flag is false
   * @param claim optional claim string to pass to AAD when requesting for the access token
   * @returns {Promise<string>} A promise that returns the access token (type string) when resolved
   */
  getResourceTokenWithClaim (resource: string, silent?: boolean, claim?: string): Promise<string>;

  /**
   * This method gets users skype token
   *
   * @param isConsumer flag indicating whether a consumer or not
   * @returns {Promise} A promise that returns the skype token (type string) when resolved
   */
  getSkypeToken (isConsumer: boolean): Promise<string>;
}

/**
 * User information provider
 *
 * Method in this interface has equivalents in native code.
 *
 * See SdkUsersProviderModule.java for Android.
 *
 * See SDKUsersProviderModule.m for iOS.
 */
export interface UserInfoProviderInterface {

  /**
   * This method fetches the user license. It checks if the user
   * is having EDU license i.e. either
   * 1. Teacher (TeamsForFacultyEducation) or
   * 2. Student (TeamsForStudentEducation),
   * if the user license is not EDU then it checks
   * if the user is having TeamsForLife (TFL) or TeamsForWork (TFW) license.
   *
   * @returns {Promise} A promise that returns the user license(type string) when resolved
   */
  getUserLicense (): Promise<string>;
}

export interface ConfigurationInterface {

  setAriaTenant(tenantToken:string);
}

export interface EndpointProviderInterface {
  /**
   * This method gets the endpoint for middle tier.
   *
   * @returns {Promise} A promise that returns the endpoint for middle tier when resolved.
   */
  getMTEndpoint (): Promise<string>;

  /**
   * This method gets the endpoint for Skype Chat Service.
   *
   * @returns {Promise} A promise that returns the endpoint for Skype Chat Service when resolved.
   */
  getSCSEndpoint (): Promise<string>;
}

/**
 * Logger interface
 */
interface LoggerInterface {
  logVerbose (tag: string, message: string);
  logInformation (tag: string, message: string);
  logDebug (tag: string, message: string);
  logWarning (tag: string, message: string);
  logError (tag: string, message: string);
  setAriaTenant(tenantToken: string);
}

/**
 * Navigation service interface.
 *
 * All these methods have equivalents in native code.
 *
 * See SdkNavigtionServiceModule.java for Android.
 *
 * See SDKNavigationServiceModule.m for iOS.
 *
 * These are not exposed directly to the outside developer.
 * The basic navigation operations like opening and closing views and modules
 * are exposed via `TeamsView`. The other methods that open Teams core modules
 * like contact card, chat etc. are exposed via `TeamsModules`.
 */
interface NavigationServiceInterface {
  openContactCard (contact: Contact);
  startAudioCall (contact: Contact);
  openChat (conversationId: string);
  openChatWithUserMris (userMris: string[]);
  openModule<T> (moduleId: string, params: T);
  openView (viewId: string, params: string);
  closeView (hostInstanceId: string, animated: boolean);
  getDeepLinkForModule (moduleId: string, params: string): Promise<string>;
  openMeetingDetails (eventId: string, isSeriesMaster: boolean);
  executeDeepLink(deepLink: string);
}

/**
 * Navigation interface
 */
export interface DeviceContactsProviderInterface {
  getDeviceContacts (): Promise<DeviceContact[]>;
}

/**
 * Receives signals from Native to React Native, i.e. Java/Obj-C to JS
 */
export interface NetworkConnectivityManagerInterface {
  isNetworkAvailable (): Promise<boolean>;
}

/**
 * Provides methods to interact with the Teams Shell UI which hosts this application.
 */
interface TeamsShellInteractorInterface {
  setOptionsMenu (appInstanceId: string, menuItems: OptionsMenuItem[]);
  invalidateOptionsMenu (appInstanceId: string);
  showSnackbar (appInstanceId: string, snackbar: Snackbar, callback?: (snackbarId: number) => void);
  dismissSnackbar (snackbarId: number, callback?: (dismissed: boolean) => void);
  setTitle (appInstanceId: string, title: string);
  setTitleWithCallBack (appInstanceId: string, title: string);
  setSubtitle (appInstanceId: string, subtitle: string);
  closeModule (appInstanceId: string);
  closeModuleWithResult (appInstanceId: string, success: boolean);
  setBackgroundColor (appInstanceId: string, color: string);
  setHomeIndicatorBackgroundColorIOS(hostInstanceId: string, colorInHexCode: string);
  showActionSheet (appInstanceId: string, actionSheet: ActionSheet, onOptionSelected: (selectedOptionId: string) => void, onCanceled?: () => void);
  enableFabLayoutAndroid (appInstanceId: string, fabLayoutParams: FabLayoutParams);
  disableFabLayoutAndroid (appInstanceId: string);
  setTitleDropdown (hostInstanceId: string, items: TitleDropdownItem[]);
  registerBackNavigationHandler (hostInstanceId: string): void;
  removeBackNavigationHandler (hostInstanceId: string): void;
  setUpTabs(hostInstanceId: string, tabList: TeamsShellTab[]);
  setUpTabsWithDefaultSelectedTab(hostInstanceId: string, tabList: TeamsShellTab[], defaultSelectedTabId: string);
}

/**
 * Native interface for channel picker.
 */
export interface ChannelPickerInterface {
  /**
   * Shows channel picker. Various configuration options
   * are available through the options parameter. See
   * ChannelPickerOptions class for documentation on options.
   *
   * @param options options to configure the channel picker
   * @returns {Promise} A promise that resolves to an object of type ChannelPickerResult.
   *          In case the picker is canceled, the promise resolves to undefined.
   */
  launch (options: ChannelPickerOptions): Promise<ChannelPickerResult>;
}

/**
 * Executes network calls.
 */
export interface HttpClientInterface {
  execute (request: HttpRequest): Promise<HttpResponse>;
  cancel (requestId: string);
}

/**
 * Shows Date and Time picker dialog (Android only)
 */
export interface DateTimePickerInterface {
  /**
   * Shows DateTime picker dialog.
   *
   * @returns Promise returns null, if dialog is cancelled.
   *          Promise returns timeInMillis, if positive button is pressed in dialog.
   */
  showDateTimePickerDialog (params: DateTimeParams): Promise<number>;
}

/**
 * Shows Sharepoint File Preview
 */
export interface SharepointFilePreviewInterface {
  /**
   * @deprecated
   * @param fileTitle : Name of the File
   * @param fileUrl : Object URL of the File
   * @param fileType : Type of the File
   * @param fileDownloadUrl : Download URL of the File
   */
  showPreview (fileTitle: string, fileUrl: string, fileType: string, fileDownloadUrl: string);

  /**
   * @param fileTitle : Name of the File
   * @param fileUrl : Object URL of the File
   * @param fileType : Type of the File
   * @param fileDownloadUrl : Download URL of the File
   * @param platformScenarioName : platform identifier - e.g. <RNAppid_file>
   */
  showPreviewWithScenario(fileTitle: string, fileUrl: string, fileType: string, fileDownloadUrl: string, platformScenarioName: string);
}

/**
 * Provides methods to add, get and remove entries from secure storage
 * This has storage limit of 50 MB per app for Android whereas no specific limit on iOS. The Promise will be rejected in case the storage limit exceeds.
 */
export interface SecureStorageInterface {
  /**
   * Stores the key and the encrypted value in Secure Storage. Also updates the old value with new value if key is already present.
   * 
   * @param key : a non empty string representing key
   * @param value : a non empty string representing value
   * 
   * @returns Promise is resolved if successful.
   *          Promise is rejected with proper error/exception otherwise.
   */
  addEntry (key: string, value: string): Promise<void>;

  /**
   * Gets the decrypted value from Secure Storage.
   * 
   * @param key : a non empty string representing key
   * 
   * @returns Promise is resolved to String value which could be null if entry does not exist.
   *          Promise is rejected with proper error/exception otherwise.
   */
  getEntry (key: string): Promise<string>;

  /**
   * Removes the entry for key from Secure Storage.
   * 
   * @param key : a non empty string representing key
   * 
   * @returns Promise is resolved if successful.
   *          Promise is rejected with proper error/exception otherwise .
   */
  removeEntry (key: string): Promise<void>;
}

// Cast RN native modules object as an instance of SDK native modules interf
const nativeModules = NativeModules as SdkNativeModulesInterface;

// Export each SDK native module.
export const SdkNativeEventPublisher = nativeModules.sdkNativeEventPublisher;

/**
 * The authentication services enables developers to authenticate the user to their application resources.
 * Before fetching a resource token for a domain, the domain must first be declared within the app's manifest.
 *
 * **For example**
 * ```json
 * {
 *   "views": [
 *     // ...
 *   ],
 *   "authDomains": [
 *     "https://graph.microsoft.com"
 *   ]
 * }
 * ```
 *
 * Token requests for domains which are not declared within the manifest will fail.
 */
export const UserInfoProvider = nativeModules.usersProvider;

export const PeopleProvider = nativeModules.peopleProvider;

/**
 * @hidden from docs
 */
export const Logger = nativeModules.logger;

/**
 * @hidden from docs
 */
export const NavigationService = nativeModules.navigationService;

export const DeviceContactsProvider = nativeModules.deviceContactsProvider;
export const NetworkConnectivityManager = nativeModules.networkConnectivityManager;

/**
 * @hidden from docs
 */
export const TeamsShellInteractor = nativeModules.teamsShellInteractor;

export const HttpClient = nativeModules.httpClient;
export const TeamsAndChannelsProvider = nativeModules.teamsAndChannelsProvider;
export const ChannelPicker = nativeModules.channelPicker;
export const DateTimePickerAndroid = nativeModules.dateTimePickerAndroid;
export const ImageAndFilePicker = nativeModules.imageAndFilePicker;
export const SharepointFilePreview = nativeModules.sharepointFilePreview;
export const ContactsProvider = nativeModules.contactsProvider;
export const EndpointProvider = nativeModules.endpointProvider;
export const SecureStorage = nativeModules.secureStorage;

/**
 * @hidden from docs
 */
export const TeamsMessaging = nativeModules.teamsMessenger;

/**
 * @hidden from docs
 */
export const NativeTelemetryClient = nativeModules.telemetryClient;

/**
 * @hidden from docs
 */
 export const NativeAuthenticationService = nativeModules.authenticationService;

/**
 * Utility methods for sharing images to users, group chats or channels.
 *
 * See [IShareUtils](xref:teams-mobile-sdk.IShareUtils) for the methods available in
 * this module.
 */
export const ShareUtils = ShareUtilsModule;

export const ChatsProvider = new ChatsProviderModule();

