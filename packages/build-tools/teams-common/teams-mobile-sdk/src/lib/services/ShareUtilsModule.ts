import { NativeModules } from 'react-native';
import { Platform } from 'react-native';
import { ShareObject } from "src/lib/models/share/ShareObject";
import { ShareTarget } from "src/lib/models/share/ShareTarget";
import { TargetPickerParams } from '../models/share/TargetPickerParams';
import { IShareUtilsModule } from './native-modules/IShareUtilsModule';
import { IShareUtilsNativeModule } from './native-modules/IShareUtilsNativeModule';


const NativeShareUtils: IShareUtilsNativeModule = NativeModules.shareUtils as IShareUtilsNativeModule;

/**
 * @hidden from docs
 * Wrapper object to expose share utils native modules functionality
 */

export const ShareUtilsModule : IShareUtilsModule = {
    /**
     * @hidden from docs
     * Launches the Teams recipient picker on top of the current view.
     *
     * > [!NOTE]
     * > Calling this method takes the user out of your app.
     * For Android this method requests storage permission from the user due to a known bug. 
     * Please use the pickShareTargetAndShareObject API to share content to target.
     *
     * @returns {Promise} A promise that resolves to a `ShareTarget`
     */
    pickTargetFromShareTargetPicker: (): Promise<ShareTarget> => {
        return NativeShareUtils.pickTargetFromShareTargetPicker();
    },

    /**
     * @hidden from docs
     * Launches the Teams recipient picker on top of the current view with a choice to open 
     * either a chat or channel picker.
     * 
     * > [!WARNING]
     * > iOS doesn't support `params` currently and will always show a combined picker.
     *
     * > [!NOTE]
     * > Calling this method takes the user out of your app.
     * @param params an object containing key "pickerType"
     * @returns {Promise} A promise that resolves to a `ShareTarget`
     */
    pickTargetFromTargetPicker: (params: TargetPickerParams): Promise<ShareTarget> => {
        if (Platform.OS === 'android') {
            return NativeShareUtils.pickTargetFromTargetPicker(params);
        } else {
            // iOS doesn't support params as of now and will behave in the old way only.
            return NativeShareUtils.pickTargetFromShareTargetPicker();
        }
    },

   /**
    * @hidden from docs
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
    shareImagesToShareTarget: (shareTarget: ShareTarget, imageHandles: string[]): Promise<void> => {
        return NativeShareUtils.shareImagesToShareTarget(shareTarget, imageHandles);
    },

    /**
     * @hidden from docs
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
    shareToShareTarget: (shareTarget: ShareTarget, shareObject: ShareObject): Promise<void> => {
        return NativeShareUtils.shareToShareTarget(shareTarget, shareObject);
    },

    /**
     * @hidden from docs
     * Share information, such as, images, text to a target. Calling this method
     * opens the respective chat or channel view on top of the current view.
     * The information passed is added to compose box.
     * For Android this method invokes pickTargetFromShareTargetPickerForShareObject from Native ShareUtils 
     * For iOS it invokes pickTargetFromShareTargetPicker from Native ShareUtils
     * > [!NOTE]
     * > Calling this method takes the user out of your app.
     *
     * @param shareTarget the target recipient to share to
     * @param shareObject images and/or text which may contain link to be shared.
     * @returns {Promise} A `void` promise. Use resolution/rejection to handle success/failure.
     */
    pickShareTargetAndShareObject: (shareObject: ShareObject): Promise<void> => {
        return (Platform.OS === 'android' ? NativeShareUtils.pickTargetFromShareTargetPickerForShareObject(shareObject) : NativeShareUtils.pickTargetFromShareTargetPicker())
            .then(shareTarget => {
                return NativeShareUtils.shareToShareTarget(shareTarget, shareObject);
            });
    }
};