/**
 * Represents configuration options for channel
 * picker.
 */
export interface ChannelPickerOptions {
  /**
   * The string to show as title of the channel picker. It is
   * the developer's responsibility to provide a localized string
   * if localization is desired.
   *
   * If a title isnot provided, a default title will be shown. The
   * default title is a localized version of "Select a channel".
   */
  title?: string;

  /**
   * Use this property to specify if any channel is selected by
   * default in the channel picker.
   *
   * NOTE: The resultant promise of the channel picker will resolve
   * to null when the user cancels the picker even if this property
   * is specified.
   */
  preSelectedChannelId?: string;

  /**
   * The list of channel IDs that the user must pick from. The given
   * channels will be grouped under their respective teams. The order
   * of display of the teams will be the user selected ordering that
   * is stored by Teams. Under each team, the General channel will be
   * shown first and the remaining channels follow in lexicographically
   * increasing order.
   *
   * If this is not specified, all channels the user is a member of will
   * be shown in the picker.
   */
  pickableChannels?: string[];
}
