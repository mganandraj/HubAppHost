/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

/**
 * Represents the accepted values of picker type
 */
 export enum PickerType {
  CHAT,
  CHANNEL,
  ALL
}

/**
 * Represents configuration options for share target picker.
 */
export class TargetPickerParams {
  /**
   * The type of picker to open. For e.g. if `pickerType` is `CHAT`, only chats are
   * are shown in the picker.
   */
  public pickerType: PickerType;
}
  