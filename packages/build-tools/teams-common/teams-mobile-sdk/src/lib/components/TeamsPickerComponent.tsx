/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import * as PropTypes from 'prop-types';
import * as React from 'react';
import { KeyboardAvoidingView, requireNativeComponent, ViewProps, ViewPropTypes } from 'react-native';
import { Channel } from '../models/core/Channel';
import { Chat } from '../models/core/Chat';
import { Team } from '../models/core/Team';
import { User } from '../models/core/User';

/**
 * Defines types of content to be displayed in picker.
 *
 * Default: PEOPLE
 */
export enum TeamsPickerType {
  /**
   * All users not covered by other types below.
   */
  PEOPLE,

  /**
   * Current user
   */
  SELF,

  /**
   * Bots
   */
  BOTS,

  /**
   * Federated users
   */
  FEDERATED_USERS,

  /**
   * Group chats
   */
  GROUP_CHATS,

  /**
   * Channels
   */
  CHANNELS,

  /**
   * Teams
   */
  TEAMS,

  /**
   * Contacts
   */
  SAVED_CONTACTS,

  /**
   * One to one chats
   */
  ONETOONE_CHATS,

  /**
   * Filter out SMS users
   */
  NO_SMS_USERS,

  /**
   *  Federated Chats
   */
  FEDERATED_CHATS,

  /**
   * Filter out SMS chats
   */
  NO_SMS_CHATS

}

/**
 * Defines behavior when interacting with picker.
 */
export enum TeamsPickerBehavior {
  /**
   * Allows the selection of multiple items up to `pickerProps.limit`. Each selected
   * item appears as a "chip" in the search box which can be deleted later.
   */
  MULTIPLE,

  /**
   * Allows selection of one item at a time. Upon selection, the search box is cleared.
   */
  SINGLE_CLEAR
}

/**
 * Defines where picker suggestions should appear.
 */
export enum TeamsPickerPosition {
  /**
   * Picker suggestions will appear below the search box.
   */
  BOTTOM,

  /**
   * Picker suggestions will appear above the search box.
   */
  TOP
}

/**
 * Defines scope of search results.
 */
export enum TeamsPickerScope {
  /**
   * Search org-wide.
   */
  ORG,

  /**
   * Search limited to team/thread specified in `pickerProps.scopeTeamId` and `pickerProps.scopeThreadId`.
   * Defaults to TeamPickerScope.ORG if proper IDs are not provided.
   */
  TEAM
}

/**
 * Properties of TeamsPicker.
 */
export interface TeamsPickerProps {
  /**
   * List of types of items to display.
   *
   * Default: [TeamsPickerType.PEOPLE]
   */
  types: TeamsPickerType[];

  /**
   * Behavior of picker.
   *
   * Default: TeamsPickerBehavior.MULTIPLE
   */
  behavior: TeamsPickerBehavior;

  /**
   * Position of picker suggestions.
   *
   * Default: TeamsPickerPosition.BOTTOM
   */
  position: TeamsPickerPosition;

  /**
   * Scope of picker search.
   *
   * Default: TeamsPickerScope.ORG
   */
  scope: TeamsPickerScope;

  /**
   * Team ID related to `scope`.
   *
   * This field is required if `scope` is TeamsPickerScope.TEAM.
   *
   * Default: null
   */
  scopeTeamId?: string;

  /**
   * Thread ID related to `scope`.
   *
   * This field is optional if `scope` is TeamsPickerScope.TEAM.
   *
   * Defaults: - `scopeTeamId` if defined and `scope` is TeamsPickerScope.TEAM
   *           - null in all other cases
   */
  scopeThreadId?: string;

  /**
   * Text to display in search box when empty and not focused.
   *
   * Default: "Search"
   */
  hint?: string;

  /**
   * Text to announce when voiceover is focused on search box.
   *
   * Default: "To search field is editing"
   */
  accessibilityLabel?: string;

  /**
   * Maximum number of items which may be selected when `behavior` is TeamsPickerBehavior.MULTIPLE.
   * Setting this prop with items already picked will result in the selected items being reduced to
   * the new limit.
   *
   * Special cases:
   *     - 0 : no items (clears list)
   *     - negative number : unlimited
   *
   * Default: -1 (unlimited)
   */
  limit?: number;

  /**
   * Indicates if suggestions should occupy entire screen.
   *
   * Default: false
   */
  fullscreen?: boolean;

  /**
   * Indicates if suggestions should be shown when search box is empty.
   * If enabled, focus is requested for the search box.
   *
   * Default: false
   */
  showSuggestionsWhenEmpty?: boolean;

  /**
   * Indicates if top anchor (line) is visible.
   *
   * Default: false
   */
  showTopAnchor?: boolean;

  /**
   * Indicates if top anchor (line) is visible.
   *
   * Default: false
   */
  showBottomAnchor?: boolean;

  /**
   * Indicates whether FirstResponder is SearchBar or not.
   *
   * Default: false
   */
  autoFocus?: boolean;

  /**
   * Indicates list of contact list ids. The contacts in these list ids will be excluded in the people picker
   */
  excludeContactsInLists?: string[];
}

interface IProps extends ViewProps {
  pickerProps: TeamsPickerProps;
  onPick?: (event: any) => void;
  onUnpick?: (event: any) => void;
  onSelectUser?: (person: User) => void;
  onSelectGroupChat?: (chat: Chat) => void;
  onSelectChannel?: (channel: Channel) => void;
  onSelectTeam?: (team: Team) => void;
  onRemoveUser?: (person: User) => void;
  onRemoveGroupChat?: (chat: Chat) => void;
  onRemoveChannel?: (channel: Channel) => void;
  onRemoveTeam?: (team: Team) => void;
}

class TeamsPicker extends React.Component<IProps> {
  public static SpecialContactListIDs = {
    ALLCONTACTS: "allContacts"
  };
  public static propTypes = {
    pickerProps: PropTypes.object,
    onPick: PropTypes.func,
    onUnpick: PropTypes.func,
    onSelectUser: PropTypes.func,
    onSelectGroupChat: PropTypes.func,
    onSelectChannel: PropTypes.func,
    onSelectTeam: PropTypes.func,
    onRemoveUser: PropTypes.func,
    onRemoveGroupChat: PropTypes.func,
    onRemoveChannel: PropTypes.func,
    onRemoveTeam: PropTypes.func,
    ...ViewPropTypes
  };

  constructor (props) {
    super(props);
    this._onPick = this._onPick.bind(this);
  }

  public _onPick = (event: any) => {
    // Users (and bots)
    if (event.nativeEvent.user && this.props.onSelectUser) {
      this.props.onSelectUser(event.nativeEvent.user);
    }

    // Group chats
    if (event.nativeEvent.chat && this.props.onSelectGroupChat) {
      this.props.onSelectGroupChat(event.nativeEvent.chat);
    }

    // Channels
    if (event.nativeEvent.channel && this.props.onSelectChannel) {
      this.props.onSelectChannel(event.nativeEvent.channel);
    }

    // Teams
    if (event.nativeEvent.team && this.props.onSelectTeam) {
      this.props.onSelectTeam(event.nativeEvent.team);
    }
  }

  public _onUnpick = (event: any) => {
    // Users (and bots)
    if (event.nativeEvent.user && this.props.onRemoveUser) {
      this.props.onRemoveUser(event.nativeEvent.user);
    }

    // Group chats
    if (event.nativeEvent.chat && this.props.onRemoveGroupChat) {
      this.props.onRemoveGroupChat(event.nativeEvent.chat);
    }

    // Channels
    if (event.nativeEvent.channel && this.props.onRemoveChannel) {
      this.props.onRemoveChannel(event.nativeEvent.channel);
    }

    // Teams
    if (event.nativeEvent.team && this.props.onRemoveTeam) {
      this.props.onRemoveTeam(event.nativeEvent.team);
    }
  }

  public render () {
    /* tslint:disable:no-use-before-declare */
    return (<KeyboardAvoidingView {...this.props} behavior='padding'>
            <TeamsPickerComponent {...this.props} onPick={this._onPick} onUnpick={this._onUnpick}></TeamsPickerComponent>
          </KeyboardAvoidingView>);
    /* tslint:enable:no-use-before-declare */
  }
}

const TeamsPickerComponent = requireNativeComponent<IProps>('TeamsPicker');

export { TeamsPicker };
