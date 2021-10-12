"use strict";
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.TeamsPicker = exports.TeamsPickerScope = exports.TeamsPickerPosition = exports.TeamsPickerBehavior = exports.TeamsPickerType = void 0;
const PropTypes = require("prop-types");
const React = require("react");
const react_native_1 = require("react-native");
/**
 * Defines types of content to be displayed in picker.
 *
 * Default: PEOPLE
 */
var TeamsPickerType;
(function (TeamsPickerType) {
    /**
     * All users not covered by other types below.
     */
    TeamsPickerType[TeamsPickerType["PEOPLE"] = 0] = "PEOPLE";
    /**
     * Current user
     */
    TeamsPickerType[TeamsPickerType["SELF"] = 1] = "SELF";
    /**
     * Bots
     */
    TeamsPickerType[TeamsPickerType["BOTS"] = 2] = "BOTS";
    /**
     * Federated users
     */
    TeamsPickerType[TeamsPickerType["FEDERATED_USERS"] = 3] = "FEDERATED_USERS";
    /**
     * Group chats
     */
    TeamsPickerType[TeamsPickerType["GROUP_CHATS"] = 4] = "GROUP_CHATS";
    /**
     * Channels
     */
    TeamsPickerType[TeamsPickerType["CHANNELS"] = 5] = "CHANNELS";
    /**
     * Teams
     */
    TeamsPickerType[TeamsPickerType["TEAMS"] = 6] = "TEAMS";
    /**
     * Contacts
     */
    TeamsPickerType[TeamsPickerType["SAVED_CONTACTS"] = 7] = "SAVED_CONTACTS";
    /**
     * One to one chats
     */
    TeamsPickerType[TeamsPickerType["ONETOONE_CHATS"] = 8] = "ONETOONE_CHATS";
    /**
     * Filter out SMS users
     */
    TeamsPickerType[TeamsPickerType["NO_SMS_USERS"] = 9] = "NO_SMS_USERS";
    /**
     *  Federated Chats
     */
    TeamsPickerType[TeamsPickerType["FEDERATED_CHATS"] = 10] = "FEDERATED_CHATS";
    /**
     * Filter out SMS chats
     */
    TeamsPickerType[TeamsPickerType["NO_SMS_CHATS"] = 11] = "NO_SMS_CHATS";
})(TeamsPickerType = exports.TeamsPickerType || (exports.TeamsPickerType = {}));
/**
 * Defines behavior when interacting with picker.
 */
var TeamsPickerBehavior;
(function (TeamsPickerBehavior) {
    /**
     * Allows the selection of multiple items up to `pickerProps.limit`. Each selected
     * item appears as a "chip" in the search box which can be deleted later.
     */
    TeamsPickerBehavior[TeamsPickerBehavior["MULTIPLE"] = 0] = "MULTIPLE";
    /**
     * Allows selection of one item at a time. Upon selection, the search box is cleared.
     */
    TeamsPickerBehavior[TeamsPickerBehavior["SINGLE_CLEAR"] = 1] = "SINGLE_CLEAR";
})(TeamsPickerBehavior = exports.TeamsPickerBehavior || (exports.TeamsPickerBehavior = {}));
/**
 * Defines where picker suggestions should appear.
 */
var TeamsPickerPosition;
(function (TeamsPickerPosition) {
    /**
     * Picker suggestions will appear below the search box.
     */
    TeamsPickerPosition[TeamsPickerPosition["BOTTOM"] = 0] = "BOTTOM";
    /**
     * Picker suggestions will appear above the search box.
     */
    TeamsPickerPosition[TeamsPickerPosition["TOP"] = 1] = "TOP";
})(TeamsPickerPosition = exports.TeamsPickerPosition || (exports.TeamsPickerPosition = {}));
/**
 * Defines scope of search results.
 */
var TeamsPickerScope;
(function (TeamsPickerScope) {
    /**
     * Search org-wide.
     */
    TeamsPickerScope[TeamsPickerScope["ORG"] = 0] = "ORG";
    /**
     * Search limited to team/thread specified in `pickerProps.scopeTeamId` and `pickerProps.scopeThreadId`.
     * Defaults to TeamPickerScope.ORG if proper IDs are not provided.
     */
    TeamsPickerScope[TeamsPickerScope["TEAM"] = 1] = "TEAM";
})(TeamsPickerScope = exports.TeamsPickerScope || (exports.TeamsPickerScope = {}));
class TeamsPicker extends React.Component {
    constructor(props) {
        super(props);
        this._onPick = (event) => {
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
        };
        this._onUnpick = (event) => {
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
        };
        this._onPick = this._onPick.bind(this);
    }
    render() {
        /* tslint:disable:no-use-before-declare */
        return (React.createElement(react_native_1.KeyboardAvoidingView, Object.assign({}, this.props, { behavior: 'padding' }),
            React.createElement(TeamsPickerComponent, Object.assign({}, this.props, { onPick: this._onPick, onUnpick: this._onUnpick }))));
        /* tslint:enable:no-use-before-declare */
    }
}
exports.TeamsPicker = TeamsPicker;
TeamsPicker.SpecialContactListIDs = {
    ALLCONTACTS: "allContacts"
};
TeamsPicker.propTypes = Object.assign({ pickerProps: PropTypes.object, onPick: PropTypes.func, onUnpick: PropTypes.func, onSelectUser: PropTypes.func, onSelectGroupChat: PropTypes.func, onSelectChannel: PropTypes.func, onSelectTeam: PropTypes.func, onRemoveUser: PropTypes.func, onRemoveGroupChat: PropTypes.func, onRemoveChannel: PropTypes.func, onRemoveTeam: PropTypes.func }, react_native_1.ViewPropTypes);
const TeamsPickerComponent = (0, react_native_1.requireNativeComponent)('TeamsPicker');
