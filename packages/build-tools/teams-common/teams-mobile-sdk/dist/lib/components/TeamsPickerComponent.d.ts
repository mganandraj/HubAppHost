/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
import * as PropTypes from 'prop-types';
import * as React from 'react';
import { ViewProps } from 'react-native';
import { Channel } from '../models/core/Channel';
import { Chat } from '../models/core/Chat';
import { Team } from '../models/core/Team';
import { User } from '../models/core/User';
/**
 * Defines types of content to be displayed in picker.
 *
 * Default: PEOPLE
 */
export declare enum TeamsPickerType {
    /**
     * All users not covered by other types below.
     */
    PEOPLE = 0,
    /**
     * Current user
     */
    SELF = 1,
    /**
     * Bots
     */
    BOTS = 2,
    /**
     * Federated users
     */
    FEDERATED_USERS = 3,
    /**
     * Group chats
     */
    GROUP_CHATS = 4,
    /**
     * Channels
     */
    CHANNELS = 5,
    /**
     * Teams
     */
    TEAMS = 6,
    /**
     * Contacts
     */
    SAVED_CONTACTS = 7,
    /**
     * One to one chats
     */
    ONETOONE_CHATS = 8,
    /**
     * Filter out SMS users
     */
    NO_SMS_USERS = 9,
    /**
     *  Federated Chats
     */
    FEDERATED_CHATS = 10,
    /**
     * Filter out SMS chats
     */
    NO_SMS_CHATS = 11
}
/**
 * Defines behavior when interacting with picker.
 */
export declare enum TeamsPickerBehavior {
    /**
     * Allows the selection of multiple items up to `pickerProps.limit`. Each selected
     * item appears as a "chip" in the search box which can be deleted later.
     */
    MULTIPLE = 0,
    /**
     * Allows selection of one item at a time. Upon selection, the search box is cleared.
     */
    SINGLE_CLEAR = 1
}
/**
 * Defines where picker suggestions should appear.
 */
export declare enum TeamsPickerPosition {
    /**
     * Picker suggestions will appear below the search box.
     */
    BOTTOM = 0,
    /**
     * Picker suggestions will appear above the search box.
     */
    TOP = 1
}
/**
 * Defines scope of search results.
 */
export declare enum TeamsPickerScope {
    /**
     * Search org-wide.
     */
    ORG = 0,
    /**
     * Search limited to team/thread specified in `pickerProps.scopeTeamId` and `pickerProps.scopeThreadId`.
     * Defaults to TeamPickerScope.ORG if proper IDs are not provided.
     */
    TEAM = 1
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
declare class TeamsPicker extends React.Component<IProps> {
    static SpecialContactListIDs: {
        ALLCONTACTS: string;
    };
    static propTypes: {
        hitSlop?: PropTypes.Validator<import("react-native").Insets | undefined> | undefined;
        onLayout?: PropTypes.Validator<((event: import("react-native").LayoutChangeEvent) => void) | undefined> | undefined;
        pointerEvents?: PropTypes.Validator<"box-none" | "none" | "box-only" | "auto" | undefined> | undefined;
        removeClippedSubviews?: PropTypes.Validator<boolean | undefined> | undefined;
        style?: PropTypes.Validator<import("react-native").StyleProp<import("react-native").ViewStyle>> | undefined;
        testID?: PropTypes.Validator<string | undefined> | undefined;
        nativeID?: PropTypes.Validator<string | undefined> | undefined;
        collapsable?: PropTypes.Validator<boolean | undefined> | undefined;
        needsOffscreenAlphaCompositing?: PropTypes.Validator<boolean | undefined> | undefined;
        renderToHardwareTextureAndroid?: PropTypes.Validator<boolean | undefined> | undefined;
        focusable?: PropTypes.Validator<boolean | undefined> | undefined;
        shouldRasterizeIOS?: PropTypes.Validator<boolean | undefined> | undefined;
        isTVSelectable?: PropTypes.Validator<boolean | undefined> | undefined;
        hasTVPreferredFocus?: PropTypes.Validator<boolean | undefined> | undefined;
        tvParallaxProperties?: PropTypes.Validator<import("react-native").TVParallaxProperties | undefined> | undefined;
        tvParallaxShiftDistanceX?: PropTypes.Validator<number | undefined> | undefined;
        tvParallaxShiftDistanceY?: PropTypes.Validator<number | undefined> | undefined;
        tvParallaxTiltAngle?: PropTypes.Validator<number | undefined> | undefined;
        tvParallaxMagnification?: PropTypes.Validator<number | undefined> | undefined;
        onStartShouldSetResponder?: PropTypes.Validator<((event: import("react-native").GestureResponderEvent) => boolean) | undefined> | undefined;
        onMoveShouldSetResponder?: PropTypes.Validator<((event: import("react-native").GestureResponderEvent) => boolean) | undefined> | undefined;
        onResponderEnd?: PropTypes.Validator<((event: import("react-native").GestureResponderEvent) => void) | undefined> | undefined;
        onResponderGrant?: PropTypes.Validator<((event: import("react-native").GestureResponderEvent) => void) | undefined> | undefined;
        onResponderReject?: PropTypes.Validator<((event: import("react-native").GestureResponderEvent) => void) | undefined> | undefined;
        onResponderMove?: PropTypes.Validator<((event: import("react-native").GestureResponderEvent) => void) | undefined> | undefined;
        onResponderRelease?: PropTypes.Validator<((event: import("react-native").GestureResponderEvent) => void) | undefined> | undefined;
        onResponderStart?: PropTypes.Validator<((event: import("react-native").GestureResponderEvent) => void) | undefined> | undefined;
        onResponderTerminationRequest?: PropTypes.Validator<((event: import("react-native").GestureResponderEvent) => boolean) | undefined> | undefined;
        onResponderTerminate?: PropTypes.Validator<((event: import("react-native").GestureResponderEvent) => void) | undefined> | undefined;
        onStartShouldSetResponderCapture?: PropTypes.Validator<((event: import("react-native").GestureResponderEvent) => boolean) | undefined> | undefined;
        onMoveShouldSetResponderCapture?: PropTypes.Validator<((event: import("react-native").GestureResponderEvent) => boolean) | undefined> | undefined;
        onTouchStart?: PropTypes.Validator<((event: import("react-native").GestureResponderEvent) => void) | undefined> | undefined;
        onTouchMove?: PropTypes.Validator<((event: import("react-native").GestureResponderEvent) => void) | undefined> | undefined;
        onTouchEnd?: PropTypes.Validator<((event: import("react-native").GestureResponderEvent) => void) | undefined> | undefined;
        onTouchCancel?: PropTypes.Validator<((event: import("react-native").GestureResponderEvent) => void) | undefined> | undefined;
        onTouchEndCapture?: PropTypes.Validator<((event: import("react-native").GestureResponderEvent) => void) | undefined> | undefined;
        accessible?: PropTypes.Validator<boolean | undefined> | undefined;
        accessibilityActions?: PropTypes.Validator<readonly Readonly<{
            name: string;
            label?: string | undefined;
        }>[] | undefined> | undefined;
        accessibilityLabel?: PropTypes.Validator<string | undefined> | undefined;
        accessibilityRole?: PropTypes.Validator<import("react-native").AccessibilityRole | undefined> | undefined;
        accessibilityState?: PropTypes.Validator<import("react-native").AccessibilityState | undefined> | undefined;
        accessibilityHint?: PropTypes.Validator<string | undefined> | undefined;
        accessibilityValue?: PropTypes.Validator<import("react-native").AccessibilityValue | undefined> | undefined;
        onAccessibilityAction?: PropTypes.Validator<((event: import("react-native").AccessibilityActionEvent) => void) | undefined> | undefined;
        accessibilityComponentType?: PropTypes.Validator<"none" | "button" | "radiobutton_checked" | "radiobutton_unchecked" | undefined> | undefined;
        accessibilityLiveRegion?: PropTypes.Validator<"none" | "polite" | "assertive" | undefined> | undefined;
        importantForAccessibility?: PropTypes.Validator<"auto" | "yes" | "no" | "no-hide-descendants" | undefined> | undefined;
        accessibilityElementsHidden?: PropTypes.Validator<boolean | undefined> | undefined;
        accessibilityTraits?: PropTypes.Validator<import("react-native").AccessibilityTrait | import("react-native").AccessibilityTrait[] | undefined> | undefined;
        accessibilityViewIsModal?: PropTypes.Validator<boolean | undefined> | undefined;
        onAccessibilityEscape?: PropTypes.Validator<(() => void) | undefined> | undefined;
        onAccessibilityTap?: PropTypes.Validator<(() => void) | undefined> | undefined;
        onMagicTap?: PropTypes.Validator<(() => void) | undefined> | undefined;
        accessibilityIgnoresInvertColors?: PropTypes.Validator<boolean | undefined> | undefined;
        pickerProps: PropTypes.Requireable<object>;
        onPick: PropTypes.Requireable<(...args: any[]) => any>;
        onUnpick: PropTypes.Requireable<(...args: any[]) => any>;
        onSelectUser: PropTypes.Requireable<(...args: any[]) => any>;
        onSelectGroupChat: PropTypes.Requireable<(...args: any[]) => any>;
        onSelectChannel: PropTypes.Requireable<(...args: any[]) => any>;
        onSelectTeam: PropTypes.Requireable<(...args: any[]) => any>;
        onRemoveUser: PropTypes.Requireable<(...args: any[]) => any>;
        onRemoveGroupChat: PropTypes.Requireable<(...args: any[]) => any>;
        onRemoveChannel: PropTypes.Requireable<(...args: any[]) => any>;
        onRemoveTeam: PropTypes.Requireable<(...args: any[]) => any>;
    };
    constructor(props: any);
    _onPick: (event: any) => void;
    _onUnpick: (event: any) => void;
    render(): JSX.Element;
}
export { TeamsPicker };
