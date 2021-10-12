/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

// Application
export { ApplicationContext } from './lib/application/ApplicationContext';
export { ApplicationParams, TeamsViewProps, AppTheme, SyncState } from './lib/models/params/ApplicationParams';

// Components
export { TeamsView } from './lib/components/TeamsViewComponent';
export { TeamsPickerType, TeamsPickerBehavior, TeamsPickerPosition, TeamsPickerScope, TeamsPickerProps, TeamsPicker } from './lib/components/TeamsPickerComponent';
export { UserIdType, UserAvatarProps, UserAvatarComponentPropTypes, UserAvatar } from './lib/components/UserAvatarComponent';
export { ViewStateType, ViewError, ViewState, StateLayout } from './lib/components/StateLayoutComponent';
export { TeamsWebView, AuthorizationHeaders, AuthorizationType, WebViewSource, TeamsWebViewMessage, TeamsWebViewProps } from './lib/components/TeamsWebViewComponent';
export { FluentIconView, IconStyle, FluentIconViewProps } from './lib/components/FluentIconView';

// Contact Card and Search
export { ContactCardAction, ContactCardItem, ContactCardItemValue, ContactCardItemValueType, ContactCardSingleValueItem, ContactCardMultiValueItem, ContactCardHeroItem, ContactCardSectionHeaderItem, ContactCardEditButtonItem, ContactCardLinkItemValue } from './lib/models/contact/ContactCardItem';

// Diagnostics
export { EventLogger } from './lib/services/diagnostics/EventLogger';
export { TraceLogger } from './lib/services/diagnostics/TraceLogger';

// Configuration
export { ConfigurationManager } from './lib/services/configuration/ConfigurationManager';

// Errors
export { ArgumentException } from './common/errors/ArgumentException';

// HTTP
export { HttpMethod } from './lib/models/http/HttpMethod';
export { HttpRequest } from './lib/models/http/HttpRequest';
export { HttpResponse } from './lib/models/http/HttpResponse';

// Models
export { Channel } from './lib/models/core/Channel';
export { Chat } from './lib/models/core/Chat';
export { Contact } from './lib/models/contact/Contact';
export { DeviceContact } from './lib/models/contact/DeviceContact';
export { Team } from './lib/models/core/Team';
export { Tab } from './lib/models/core/Tab';
export { User } from './lib/models/core/User';
export {
  Person,
  PersonProfilePicture,
  PersonPresenceStatus,
  PersonContactMetadata
} from './lib/models/core/Person';
export { DateTimeParams } from './lib/models/params/DateTimeParams';
export { ImageAndFilePickerResponse, ImageQuality, OfficeLensMode } from './lib/models/ImageAndFilePicker/ImageAndFilePickerResponse';
export { TeamsMessagingRequest, TeamsMessageType } from './lib/models/TeamsMessaging/TeamsMessagingRequest';
export { TeamsMessagingCard, TeamsMessageCardType } from './lib/models/TeamsMessaging/TeamsMessagingCard';
export { TeamsMessagingMention, TeamsMessagingMentionType } from './lib/models/TeamsMessaging/TeamsMessagingMention';
export { TeamsMessagingFile, TeamsMessagingFileInfo } from './lib/models/TeamsMessaging/TeamsMessagingFile';
export { TeamsMessagingResponse } from './lib/models/TeamsMessaging/TeamsMessagingResponse';
export { UserBIEvent, UserBIEventName } from './lib/models/telemetry/UserBIEvent';
export { ShareTarget } from './lib/models/share/ShareTarget';
export { TargetPickerParams } from './lib/models/share/TargetPickerParams';
export { PeopleContact, ContactType, Phones, PhoneType, Address, AddressType } from './lib/models/people/PeopleContact';
export { ContactList, ContactListType } from './lib/models/people/ContactList';
export { ContactsProviderResponseStatus, ContactsProviderResponse, SortOrder, SortType } from './lib/models/people/ContactsProviderResponse';

// Native Modules
export {
  DeviceContactsProvider,
  HttpClient,
  NetworkConnectivityManager,
  SdkNativeEventPublisher,
  TeamsAndChannelsProvider,
  DateTimePickerAndroid,
  ImageAndFilePicker,
  SharepointFilePreview,
  ChannelPicker,
  ShareUtils,
  ContactsProvider,
  EndpointProvider,
  ChatsProvider,
  UserInfoProvider,
  PeopleProvider,
  SecureStorage
} from './lib/services/SdkNativeModules';

// Channel picker
export { ChannelPickerResult } from './lib/models/channel-picker/ChannelPickerResult';
export { ChannelPickerOptions } from './lib/models/channel-picker/ChannelPickerOptions';

// Providers
export { BaseProviderParams, BaseProviderResult } from './lib/models/params/ProviderParams';
export { IdType, UserId } from './lib/services/native-modules/PeopleProviderInterface';

// Services
import * as LocalizedStringsImpl from './lib/services/LocalizedStrings';
export const LocalizedStrings = LocalizedStringsImpl;
export { LocalizedStringsProvider } from './lib/services/LocalizedStrings';

// Shell
export { OptionsMenuItem } from './lib/models/shell/OptionsMenuItem';
export { SnackbarDuration, SnackbarAction, Snackbar } from './lib/models/shell/Snackbar';
export { ActionSheet, ActionSheetOption } from './lib/models/shell/ActionSheet';
export { TeamsShell } from './lib/shell/TeamsShell';
export { TeamsFetchBlob } from './lib/services/native-modules/Fetch-Blob/TeamsFetchBlob';
export { SecondaryFloatingActionButton, FabLayoutParams } from './lib/models/shell/FabLayoutParams';
export { TeamsShellTab } from './lib/models/shell/TeamsShellTab';

// Utilities
export { ArgumentsValidator } from './common/utilities/ArgumentsValidator';
export { Resource } from './lib/utilities/Resource';

export { TeamsModules } from './lib/TeamsModules';
export { DeepLinkUtils } from './lib/utilities/DeepLinkUtils';
export { AuthenticationService } from './lib/services/AuthenticationService';
export { TelemetryClient } from './lib/services/TelemetryClient';
export { IModuleLifecycleHandler, ModuleLifecycle } from './lib/services/ModuleLifecycle';
export { SyncEventListener } from './lib/services/SyncEventListener';
