/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

/**
 * A model to define a tab in AppBar(Android)/NavigationBar(iOS)
 */
export interface TeamsShellTab {
  /**
   * Unique id of the tab
   */
  id: string;
  /**
   * title of the tab, which will be displayed for this tab in AppBar(Android)/NavigationBar(iOS)
   */
  title: string;
  /**
   * Discription of the tab, which will be used to support Accessibility
   */
  contentDescription?: string;
}
