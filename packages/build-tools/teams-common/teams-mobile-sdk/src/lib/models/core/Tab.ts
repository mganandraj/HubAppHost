export interface Tab {
  /**
   * A unique identifier for the tab.
   */
  id: string;

  /**
   * Name of the Tab
   */
  displayName: string;

  /**
   * Channel id
   */
  parentThreadId: string;

  /**
   * type of Tab
   */
  type: string;

  /**
   * tab Url
   */
  tabURL: string;

  /**
   * Tab settings
   */
  metadata: string;
  /**
   * Tab Properties
   */
  tabDefinitionJson: string;
}
