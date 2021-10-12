/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
/**
 * Represents the accepted values of picker type
 */
export declare enum PickerType {
    CHAT = 0,
    CHANNEL = 1,
    ALL = 2
}
/**
 * Represents configuration options for share target picker.
 */
export declare class TargetPickerParams {
    /**
     * The type of picker to open. For e.g. if `pickerType` is `CHAT`, only chats are
     * are shown in the picker.
     */
    pickerType: PickerType;
}
