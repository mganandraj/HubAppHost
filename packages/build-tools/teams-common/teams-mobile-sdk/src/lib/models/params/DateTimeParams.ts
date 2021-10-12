/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

export interface DateTimeParams {
  /**
   * Submit button text for Date picker.
   */
  dateViewSubmitButtonText?: string;

  /**
   * Submit button text for Time picker.
   */
  timeViewSubmitButtonText?: string;

  /**
   * Title for Date picker tab.
   */
  dateViewTitle?: string;

  /**
   * Title for Time picker tab.
   */
  timeViewTitle?: string;

  /**
   * If set to true, click on Date picker submit button will take user to Time picker.
   * If set to false, click on Date picker submit button will close dialog.
   */
  isTimeSelectionRequired?: boolean;

  /**
   * Minute in the range (0-59)
   */
  minute?: number;

  /**
   * Hour in the range (0-23)
   */
  hour?: number;

  /**
   * Day of month
   */
  dayOfMonth?: number;

  /**
   * Month of the year (0-11)
   */
  month?: number;

  /**
   * Year
   */
  year?: number;
}
