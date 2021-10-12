"use strict";
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.SdkNativeEventPublisher = exports.NetworkConnectivityManager = exports.HttpClient = exports.DeviceContactsProvider = exports.SortType = exports.SortOrder = exports.ContactsProviderResponseStatus = exports.ContactListType = exports.AddressType = exports.PhoneType = exports.ContactType = exports.TargetPickerParams = exports.UserBIEventName = exports.TeamsMessagingMentionType = exports.TeamsMessageCardType = exports.TeamsMessageType = exports.OfficeLensMode = exports.ImageQuality = exports.Chat = exports.HttpMethod = exports.ArgumentException = exports.ConfigurationManager = exports.TraceLogger = exports.EventLogger = exports.ContactCardLinkItemValue = exports.ContactCardEditButtonItem = exports.ContactCardSectionHeaderItem = exports.ContactCardHeroItem = exports.ContactCardMultiValueItem = exports.ContactCardSingleValueItem = exports.ContactCardItemValueType = exports.ContactCardItem = exports.ContactCardAction = exports.IconStyle = exports.FluentIconView = exports.AuthorizationType = exports.TeamsWebView = exports.StateLayout = exports.ViewStateType = exports.UserAvatar = exports.UserIdType = exports.TeamsPicker = exports.TeamsPickerScope = exports.TeamsPickerPosition = exports.TeamsPickerBehavior = exports.TeamsPickerType = exports.TeamsView = exports.SyncState = exports.AppTheme = exports.ApplicationContext = void 0;
exports.SyncEventListener = exports.ModuleLifecycle = exports.TelemetryClient = exports.AuthenticationService = exports.DeepLinkUtils = exports.TeamsModules = exports.Resource = exports.ArgumentsValidator = exports.TeamsFetchBlob = exports.TeamsShell = exports.SnackbarDuration = exports.LocalizedStrings = exports.SecureStorage = exports.PeopleProvider = exports.UserInfoProvider = exports.ChatsProvider = exports.EndpointProvider = exports.ContactsProvider = exports.ShareUtils = exports.ChannelPicker = exports.SharepointFilePreview = exports.ImageAndFilePicker = exports.DateTimePickerAndroid = exports.TeamsAndChannelsProvider = void 0;
// Application
var ApplicationContext_1 = require("./lib/application/ApplicationContext");
Object.defineProperty(exports, "ApplicationContext", { enumerable: true, get: function () { return ApplicationContext_1.ApplicationContext; } });
var ApplicationParams_1 = require("./lib/models/params/ApplicationParams");
Object.defineProperty(exports, "AppTheme", { enumerable: true, get: function () { return ApplicationParams_1.AppTheme; } });
Object.defineProperty(exports, "SyncState", { enumerable: true, get: function () { return ApplicationParams_1.SyncState; } });
// Components
var TeamsViewComponent_1 = require("./lib/components/TeamsViewComponent");
Object.defineProperty(exports, "TeamsView", { enumerable: true, get: function () { return TeamsViewComponent_1.TeamsView; } });
var TeamsPickerComponent_1 = require("./lib/components/TeamsPickerComponent");
Object.defineProperty(exports, "TeamsPickerType", { enumerable: true, get: function () { return TeamsPickerComponent_1.TeamsPickerType; } });
Object.defineProperty(exports, "TeamsPickerBehavior", { enumerable: true, get: function () { return TeamsPickerComponent_1.TeamsPickerBehavior; } });
Object.defineProperty(exports, "TeamsPickerPosition", { enumerable: true, get: function () { return TeamsPickerComponent_1.TeamsPickerPosition; } });
Object.defineProperty(exports, "TeamsPickerScope", { enumerable: true, get: function () { return TeamsPickerComponent_1.TeamsPickerScope; } });
Object.defineProperty(exports, "TeamsPicker", { enumerable: true, get: function () { return TeamsPickerComponent_1.TeamsPicker; } });
var UserAvatarComponent_1 = require("./lib/components/UserAvatarComponent");
Object.defineProperty(exports, "UserIdType", { enumerable: true, get: function () { return UserAvatarComponent_1.UserIdType; } });
Object.defineProperty(exports, "UserAvatar", { enumerable: true, get: function () { return UserAvatarComponent_1.UserAvatar; } });
var StateLayoutComponent_1 = require("./lib/components/StateLayoutComponent");
Object.defineProperty(exports, "ViewStateType", { enumerable: true, get: function () { return StateLayoutComponent_1.ViewStateType; } });
Object.defineProperty(exports, "StateLayout", { enumerable: true, get: function () { return StateLayoutComponent_1.StateLayout; } });
var TeamsWebViewComponent_1 = require("./lib/components/TeamsWebViewComponent");
Object.defineProperty(exports, "TeamsWebView", { enumerable: true, get: function () { return TeamsWebViewComponent_1.TeamsWebView; } });
Object.defineProperty(exports, "AuthorizationType", { enumerable: true, get: function () { return TeamsWebViewComponent_1.AuthorizationType; } });
var FluentIconView_1 = require("./lib/components/FluentIconView");
Object.defineProperty(exports, "FluentIconView", { enumerable: true, get: function () { return FluentIconView_1.FluentIconView; } });
Object.defineProperty(exports, "IconStyle", { enumerable: true, get: function () { return FluentIconView_1.IconStyle; } });
// Contact Card and Search
var ContactCardItem_1 = require("./lib/models/contact/ContactCardItem");
Object.defineProperty(exports, "ContactCardAction", { enumerable: true, get: function () { return ContactCardItem_1.ContactCardAction; } });
Object.defineProperty(exports, "ContactCardItem", { enumerable: true, get: function () { return ContactCardItem_1.ContactCardItem; } });
Object.defineProperty(exports, "ContactCardItemValueType", { enumerable: true, get: function () { return ContactCardItem_1.ContactCardItemValueType; } });
Object.defineProperty(exports, "ContactCardSingleValueItem", { enumerable: true, get: function () { return ContactCardItem_1.ContactCardSingleValueItem; } });
Object.defineProperty(exports, "ContactCardMultiValueItem", { enumerable: true, get: function () { return ContactCardItem_1.ContactCardMultiValueItem; } });
Object.defineProperty(exports, "ContactCardHeroItem", { enumerable: true, get: function () { return ContactCardItem_1.ContactCardHeroItem; } });
Object.defineProperty(exports, "ContactCardSectionHeaderItem", { enumerable: true, get: function () { return ContactCardItem_1.ContactCardSectionHeaderItem; } });
Object.defineProperty(exports, "ContactCardEditButtonItem", { enumerable: true, get: function () { return ContactCardItem_1.ContactCardEditButtonItem; } });
Object.defineProperty(exports, "ContactCardLinkItemValue", { enumerable: true, get: function () { return ContactCardItem_1.ContactCardLinkItemValue; } });
// Diagnostics
var EventLogger_1 = require("./lib/services/diagnostics/EventLogger");
Object.defineProperty(exports, "EventLogger", { enumerable: true, get: function () { return EventLogger_1.EventLogger; } });
var TraceLogger_1 = require("./lib/services/diagnostics/TraceLogger");
Object.defineProperty(exports, "TraceLogger", { enumerable: true, get: function () { return TraceLogger_1.TraceLogger; } });
// Configuration
var ConfigurationManager_1 = require("./lib/services/configuration/ConfigurationManager");
Object.defineProperty(exports, "ConfigurationManager", { enumerable: true, get: function () { return ConfigurationManager_1.ConfigurationManager; } });
// Errors
var ArgumentException_1 = require("./common/errors/ArgumentException");
Object.defineProperty(exports, "ArgumentException", { enumerable: true, get: function () { return ArgumentException_1.ArgumentException; } });
// HTTP
var HttpMethod_1 = require("./lib/models/http/HttpMethod");
Object.defineProperty(exports, "HttpMethod", { enumerable: true, get: function () { return HttpMethod_1.HttpMethod; } });
var Chat_1 = require("./lib/models/core/Chat");
Object.defineProperty(exports, "Chat", { enumerable: true, get: function () { return Chat_1.Chat; } });
var ImageAndFilePickerResponse_1 = require("./lib/models/ImageAndFilePicker/ImageAndFilePickerResponse");
Object.defineProperty(exports, "ImageQuality", { enumerable: true, get: function () { return ImageAndFilePickerResponse_1.ImageQuality; } });
Object.defineProperty(exports, "OfficeLensMode", { enumerable: true, get: function () { return ImageAndFilePickerResponse_1.OfficeLensMode; } });
var TeamsMessagingRequest_1 = require("./lib/models/TeamsMessaging/TeamsMessagingRequest");
Object.defineProperty(exports, "TeamsMessageType", { enumerable: true, get: function () { return TeamsMessagingRequest_1.TeamsMessageType; } });
var TeamsMessagingCard_1 = require("./lib/models/TeamsMessaging/TeamsMessagingCard");
Object.defineProperty(exports, "TeamsMessageCardType", { enumerable: true, get: function () { return TeamsMessagingCard_1.TeamsMessageCardType; } });
var TeamsMessagingMention_1 = require("./lib/models/TeamsMessaging/TeamsMessagingMention");
Object.defineProperty(exports, "TeamsMessagingMentionType", { enumerable: true, get: function () { return TeamsMessagingMention_1.TeamsMessagingMentionType; } });
var UserBIEvent_1 = require("./lib/models/telemetry/UserBIEvent");
Object.defineProperty(exports, "UserBIEventName", { enumerable: true, get: function () { return UserBIEvent_1.UserBIEventName; } });
var TargetPickerParams_1 = require("./lib/models/share/TargetPickerParams");
Object.defineProperty(exports, "TargetPickerParams", { enumerable: true, get: function () { return TargetPickerParams_1.TargetPickerParams; } });
var PeopleContact_1 = require("./lib/models/people/PeopleContact");
Object.defineProperty(exports, "ContactType", { enumerable: true, get: function () { return PeopleContact_1.ContactType; } });
Object.defineProperty(exports, "PhoneType", { enumerable: true, get: function () { return PeopleContact_1.PhoneType; } });
Object.defineProperty(exports, "AddressType", { enumerable: true, get: function () { return PeopleContact_1.AddressType; } });
var ContactList_1 = require("./lib/models/people/ContactList");
Object.defineProperty(exports, "ContactListType", { enumerable: true, get: function () { return ContactList_1.ContactListType; } });
var ContactsProviderResponse_1 = require("./lib/models/people/ContactsProviderResponse");
Object.defineProperty(exports, "ContactsProviderResponseStatus", { enumerable: true, get: function () { return ContactsProviderResponse_1.ContactsProviderResponseStatus; } });
Object.defineProperty(exports, "SortOrder", { enumerable: true, get: function () { return ContactsProviderResponse_1.SortOrder; } });
Object.defineProperty(exports, "SortType", { enumerable: true, get: function () { return ContactsProviderResponse_1.SortType; } });
// Native Modules
var SdkNativeModules_1 = require("./lib/services/SdkNativeModules");
Object.defineProperty(exports, "DeviceContactsProvider", { enumerable: true, get: function () { return SdkNativeModules_1.DeviceContactsProvider; } });
Object.defineProperty(exports, "HttpClient", { enumerable: true, get: function () { return SdkNativeModules_1.HttpClient; } });
Object.defineProperty(exports, "NetworkConnectivityManager", { enumerable: true, get: function () { return SdkNativeModules_1.NetworkConnectivityManager; } });
Object.defineProperty(exports, "SdkNativeEventPublisher", { enumerable: true, get: function () { return SdkNativeModules_1.SdkNativeEventPublisher; } });
Object.defineProperty(exports, "TeamsAndChannelsProvider", { enumerable: true, get: function () { return SdkNativeModules_1.TeamsAndChannelsProvider; } });
Object.defineProperty(exports, "DateTimePickerAndroid", { enumerable: true, get: function () { return SdkNativeModules_1.DateTimePickerAndroid; } });
Object.defineProperty(exports, "ImageAndFilePicker", { enumerable: true, get: function () { return SdkNativeModules_1.ImageAndFilePicker; } });
Object.defineProperty(exports, "SharepointFilePreview", { enumerable: true, get: function () { return SdkNativeModules_1.SharepointFilePreview; } });
Object.defineProperty(exports, "ChannelPicker", { enumerable: true, get: function () { return SdkNativeModules_1.ChannelPicker; } });
Object.defineProperty(exports, "ShareUtils", { enumerable: true, get: function () { return SdkNativeModules_1.ShareUtils; } });
Object.defineProperty(exports, "ContactsProvider", { enumerable: true, get: function () { return SdkNativeModules_1.ContactsProvider; } });
Object.defineProperty(exports, "EndpointProvider", { enumerable: true, get: function () { return SdkNativeModules_1.EndpointProvider; } });
Object.defineProperty(exports, "ChatsProvider", { enumerable: true, get: function () { return SdkNativeModules_1.ChatsProvider; } });
Object.defineProperty(exports, "UserInfoProvider", { enumerable: true, get: function () { return SdkNativeModules_1.UserInfoProvider; } });
Object.defineProperty(exports, "PeopleProvider", { enumerable: true, get: function () { return SdkNativeModules_1.PeopleProvider; } });
Object.defineProperty(exports, "SecureStorage", { enumerable: true, get: function () { return SdkNativeModules_1.SecureStorage; } });
// Services
const LocalizedStringsImpl = require("./lib/services/LocalizedStrings");
exports.LocalizedStrings = LocalizedStringsImpl;
var Snackbar_1 = require("./lib/models/shell/Snackbar");
Object.defineProperty(exports, "SnackbarDuration", { enumerable: true, get: function () { return Snackbar_1.SnackbarDuration; } });
var TeamsShell_1 = require("./lib/shell/TeamsShell");
Object.defineProperty(exports, "TeamsShell", { enumerable: true, get: function () { return TeamsShell_1.TeamsShell; } });
var TeamsFetchBlob_1 = require("./lib/services/native-modules/Fetch-Blob/TeamsFetchBlob");
Object.defineProperty(exports, "TeamsFetchBlob", { enumerable: true, get: function () { return TeamsFetchBlob_1.TeamsFetchBlob; } });
// Utilities
var ArgumentsValidator_1 = require("./common/utilities/ArgumentsValidator");
Object.defineProperty(exports, "ArgumentsValidator", { enumerable: true, get: function () { return ArgumentsValidator_1.ArgumentsValidator; } });
var Resource_1 = require("./lib/utilities/Resource");
Object.defineProperty(exports, "Resource", { enumerable: true, get: function () { return Resource_1.Resource; } });
var TeamsModules_1 = require("./lib/TeamsModules");
Object.defineProperty(exports, "TeamsModules", { enumerable: true, get: function () { return TeamsModules_1.TeamsModules; } });
var DeepLinkUtils_1 = require("./lib/utilities/DeepLinkUtils");
Object.defineProperty(exports, "DeepLinkUtils", { enumerable: true, get: function () { return DeepLinkUtils_1.DeepLinkUtils; } });
var AuthenticationService_1 = require("./lib/services/AuthenticationService");
Object.defineProperty(exports, "AuthenticationService", { enumerable: true, get: function () { return AuthenticationService_1.AuthenticationService; } });
var TelemetryClient_1 = require("./lib/services/TelemetryClient");
Object.defineProperty(exports, "TelemetryClient", { enumerable: true, get: function () { return TelemetryClient_1.TelemetryClient; } });
var ModuleLifecycle_1 = require("./lib/services/ModuleLifecycle");
Object.defineProperty(exports, "ModuleLifecycle", { enumerable: true, get: function () { return ModuleLifecycle_1.ModuleLifecycle; } });
var SyncEventListener_1 = require("./lib/services/SyncEventListener");
Object.defineProperty(exports, "SyncEventListener", { enumerable: true, get: function () { return SyncEventListener_1.SyncEventListener; } });
