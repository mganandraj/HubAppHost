/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

export interface OptionsMenuItem {
  id: string;
  title: string;
  icon?: string;
  /**
   * Description of the content which will be used to support Accessibility
   */
  contentDescription?: string;
}
