/**
 * Represents an action sheet.
 * https://developer.apple.com/design/human-interface-guidelines/ios/views/action-sheets/
 * https://material.io/develop/android/components/bottom-sheet-behavior/
 */
export interface ActionSheet {
    /**
     * Title for the action sheet.
     * This is displayed on top of the sheet.
     */
    title?: string;
    /**
     * A brief message for the action sheet. Think of this as
     * a subtitle. Message is displayed just below the title
     * in a smaller font.
     */
    message?: string;
    /**
     * List of options to be shown in the action sheet (max 10).
     * No action sheet will be displayed if this array is empty.
     * Any invalid objects in the array (e.g. if icon is not SVG) will be ignored.
     */
    options: ActionSheetOption[];
}
/**
 * Represents a single option in the action sheet.
 */
export interface ActionSheetOption {
    /**
     * The image resource to use as icon for an action sheet options.
     * This is mandatory. If this is not provided or is invalid, the
     * corresponding option will not be displayed in the action sheet.
     *
     * The value of this field is actually a URI for an image packaged
     * with the app. Use Resource.image('...') to get the value for this field.
     */
    icon: string;
    /**
     * Label for the action sheet option. This is the main text content
     * of an option. If this is null or undefined, the corresponding option
     * will not be displayed in the action sheet.
     */
    label: string;
    /**
     * A unique identifier for the option. This value is passed to the handler
     * when the user selects an option in the action sheet. If this is
     * null or undefined, the corresponding option will not be displayed in
     * the action sheet.
     */
    id: string;
}
