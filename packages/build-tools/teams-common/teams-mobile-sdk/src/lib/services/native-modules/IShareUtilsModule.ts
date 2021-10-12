import { ShareObject } from "src/lib/models/share/ShareObject";
import { ShareTarget } from "src/lib/models/share/ShareTarget";
import { TargetPickerParams } from "src/lib/models/share/TargetPickerParams";

/**
 * Interface for the `ShareUtilsModule` native module.
 */
export interface IShareUtilsModule {
  /**
   * Launches the Teams recipient picker on top of the current view.
   *
   * > [!NOTE]
   * > Calling this method takes the user out of your app.
   *  For Android this method requests storage permission from the user due to a known bug. 
   *  Please use the pickShareTargetAndShareObject API to share content to target.
   * @returns {Promise} A promise that resolves to a `ShareTarget`
   */
  pickTargetFromShareTargetPicker(): Promise<ShareTarget>;

  /**
   * Launches the chat/channel recipient picker on top of the current view.
   *
   * > [!WARNING]
   * > iOS doesn't support `params` currently and will always show a combined picker.
   * 
   * > [!NOTE]
   * > Calling this method takes the user out of your app.
   * @param params Configuration of the picker to be displayed.
   * @returns {Promise} A promise that resolves to a `ShareTarget`
   */
   pickTargetFromTargetPicker(params: TargetPickerParams): Promise<ShareTarget>;

  /**
   * > [!NOTE]
   * > This method is deprecated and a new API 'shareToShareTarget' which supports
   * > sharing images, text/link or both images and text/links is exposed. It is
   * > highly advisible to use 'shareToShareTarget' API instead of 'shareImagesToShareTarget'.
   *
   * Share images to a target. Calling this method opens the respective chat or
   * channel view on top of the current view. The images specified in
   * `imageHandles` are added to the compose box. If multiple images are
   * specified, each of them appears on a new line i.e. the images are
   * vertically stacked.
   *
   * > [!NOTE]
   * > Calling this method takes the user out of your app.
   *
   * > [!NOTE]
   * > Image handles can be obtained via `ImageAndFilePicker` APIs.
   *
   * @deprecated
   * @param shareTarget the target recipient to share to
   * @param imageHandles handles of the images that are to be shared
   * @returns {Promise} A `void` promise. Use resolution/rejection to handle success/failure.
   */
  shareImagesToShareTarget(shareTarget: ShareTarget, imageHandles: string[]): Promise<void>;

  /**
   * Share information, such as, images, text to a target. Calling this method
   * opens the respective chat or channel view on top of the current view.
   * The information passed is added to compose box.
   *
   * > [!NOTE]
   * > Calling this method takes the user out of your app.
   *
   * @param shareTarget the target recipient to share to
   * @param shareObject images and/or text which may contain link to be shared.
   * @returns {Promise} A `void` promise. Use resolution/rejection to handle success/failure.
   */
  shareToShareTarget(shareTarget: ShareTarget, shareObject: ShareObject): Promise<void>;


  /**
   * Share information, such as, images, text to a target. Calling this method
   * opens the respective chat or channel view on top of the current view.
   * The information passed is added to compose box.
   *
   * > [!NOTE]
   * > Calling this method takes the user out of your app.
   *
   * @param shareObject images and/or text which may contain link to be shared.
   * @returns {Promise} A `void` promise. Use resolution/rejection to handle success/failure.
   */
  pickShareTargetAndShareObject(shareObject: ShareObject): Promise<void>;
}
