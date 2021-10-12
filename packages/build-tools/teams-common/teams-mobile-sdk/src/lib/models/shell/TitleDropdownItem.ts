/**
 * Represents a title dropdown item.
 */
export interface TitleDropdownItem {
  /**
   * A unique identifier for the item. This identifier will be sent
   * to the handler when the user selects an item fom the title
   * dropdown.
   */
  id: string;

  /**
   * Title of the item.
   */
  title: string;
}
