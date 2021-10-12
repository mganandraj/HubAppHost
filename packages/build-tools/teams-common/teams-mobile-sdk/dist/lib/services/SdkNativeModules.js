"use strict";
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.ChatsProvider = exports.ShareUtils = exports.NativeAuthenticationService = exports.NativeTelemetryClient = exports.TeamsMessaging = exports.SecureStorage = exports.EndpointProvider = exports.ContactsProvider = exports.SharepointFilePreview = exports.ImageAndFilePicker = exports.DateTimePickerAndroid = exports.ChannelPicker = exports.TeamsAndChannelsProvider = exports.HttpClient = exports.TeamsShellInteractor = exports.NetworkConnectivityManager = exports.DeviceContactsProvider = exports.NavigationService = exports.Logger = exports.PeopleProvider = exports.UserInfoProvider = exports.SdkNativeEventPublisher = void 0;
const react_native_1 = require("react-native");
const ChatsProviderModule_1 = require("./native-modules/ChatsProviderModule");
const ShareUtilsModule_1 = require("./ShareUtilsModule");
// Cast RN native modules object as an instance of SDK native modules interf
const nativeModules = react_native_1.NativeModules;
// Export each SDK native module.
exports.SdkNativeEventPublisher = nativeModules.sdkNativeEventPublisher;
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
exports.UserInfoProvider = nativeModules.usersProvider;
exports.PeopleProvider = nativeModules.peopleProvider;
/**
 * @hidden from docs
 */
exports.Logger = nativeModules.logger;
/**
 * @hidden from docs
 */
exports.NavigationService = nativeModules.navigationService;
exports.DeviceContactsProvider = nativeModules.deviceContactsProvider;
exports.NetworkConnectivityManager = nativeModules.networkConnectivityManager;
/**
 * @hidden from docs
 */
exports.TeamsShellInteractor = nativeModules.teamsShellInteractor;
exports.HttpClient = nativeModules.httpClient;
exports.TeamsAndChannelsProvider = nativeModules.teamsAndChannelsProvider;
exports.ChannelPicker = nativeModules.channelPicker;
exports.DateTimePickerAndroid = nativeModules.dateTimePickerAndroid;
exports.ImageAndFilePicker = nativeModules.imageAndFilePicker;
exports.SharepointFilePreview = nativeModules.sharepointFilePreview;
exports.ContactsProvider = nativeModules.contactsProvider;
exports.EndpointProvider = nativeModules.endpointProvider;
exports.SecureStorage = nativeModules.secureStorage;
/**
 * @hidden from docs
 */
exports.TeamsMessaging = nativeModules.teamsMessenger;
/**
 * @hidden from docs
 */
exports.NativeTelemetryClient = nativeModules.telemetryClient;
/**
 * @hidden from docs
 */
exports.NativeAuthenticationService = nativeModules.authenticationService;
/**
 * Utility methods for sharing images to users, group chats or channels.
 *
 * See [IShareUtils](xref:teams-mobile-sdk.IShareUtils) for the methods available in
 * this module.
 */
exports.ShareUtils = ShareUtilsModule_1.ShareUtilsModule;
exports.ChatsProvider = new ChatsProviderModule_1.ChatsProviderModule();
