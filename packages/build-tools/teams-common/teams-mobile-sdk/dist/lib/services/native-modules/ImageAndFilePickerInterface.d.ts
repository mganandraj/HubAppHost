import { FileMetaData, ImageAndFilePickerResponse, ImageQuality } from '../../models/ImageAndFilePicker/ImageAndFilePickerResponse';
/**
 * Image and file picker.
 */
export interface ImageAndFilePickerInterface {
    /**
     * API to take photo with default device camera
     *
     * If camera capability is not available in device, promise
     * will be returned with non supported status code.
     * set storeInPrivate to save the clicked image in a private folder
     *
     * @returns {Promise} A promise that returns ImageAndFilePickerResponse
     * with status code and array of unique file identifiers
     */
    takePhotoWithDefaultCamera(storeInPrivate: Boolean): Promise<ImageAndFilePickerResponse>;
    /**
     * API to take photo with office lens camera
     *
     * Multiple photo capture is available. Maximum limit is based
     * on Teams app configuration
     * set the storeInPrivate to save the clicked image(s) in a private folder.
     * set  allowMultipleCapture to be able to take multiple photos.
     *
     * @returns {Promise} A promise that returns ImageAndFilePickerResponse
     * with status code and array of unique file identifiers
     */
    takePhotoWithOfficeLensCamera(storeInPrivate: Boolean, allowMultipleCapture: Boolean): Promise<ImageAndFilePickerResponse>;
    /**
     * API to take photo with office lens camera
     *
     * Multiple photo capture is available. Maximum limit is based
     * on Teams app configuration
     * @param storeInPrivate save the clicked image(s) in a private folder.
     * @param allowMultipleCapture allow clicking multiple photos.
     * @param launchMode Mode in which office lens will open. Valid values: WhiteBoard, BusinessCard, Document, Photo. Default value is 'Photo'.
     *
     *
     * @returns {Promise} A promise that returns ImageAndFilePickerResponse
     * with status code and array of unique file identifiers
     */
    takePhotoWithCustomizedOfficeLensCamera(storeInPrivate: Boolean, allowMultipleCapture: Boolean, launchMode?: String): Promise<ImageAndFilePickerResponse>;
    /**
     * API to pick photo from device's photo app
     * allowMultipleSelection not supported in ios
     *
     * @returns {Promise} A promise that returns ImageAndFilePickerResponse
     * with status code and array of unique file identifiers
     */
    pickPhotoFromGallery(allowMultipleSelection: Boolean): Promise<ImageAndFilePickerResponse>;
    /**
     * API to pick document from browser (iCloud, drive, etc.)
     * `allowMultipleSelection` is not supported in iOS.
     *
     * @returns {Promise} A promise that returns ImageAndFilePickerResponse
     * with status code and array of unique file identifiers
     */
    pickDocumentFromBrowser(allowMultipleSelection: Boolean): Promise<ImageAndFilePickerResponse>;
    /**
     * API to optimise the given image for network upolad
     * This will optimise the image based on multiple factors
     * - User preference for image quality
     * - Network type
     * - Network bandwidth
     *
     * The resulting image falls into one of these buckets
     * 1. Excellent      (Threshold size  20 MB)
     * 2. Good           (Threshold size   4 MB)
     * 3. Moderate       (Threshold size   2 MB)
     * 4. Low            (Threshold size 100 KB)
     * 5. Uncompressed
     *
     * @param imageMetadata meta data of image file, which need to be optimized
     * @returns {Promise} A promise that returns ImageAndFilePickerResponse
     * with status code and array of optimised file identifiers
     */
    optimizeImageForNetworkUpload(imageMetadata: FileMetaData): Promise<ImageAndFilePickerResponse>;
    /**
     * API to compress the given image to the target image quality
     *
     * The resulting image falls into one of these buckets based on target image quality passed in params
     * 1. Excellent      (Threshold size  20 MB)
     * 2. Good           (Threshold size   4 MB)
     * 3. Moderate       (Threshold size   2 MB)
     * 4. Low            (Threshold size 100 KB)
     * 5. Uncompressed  (if any error occurs during the compression)
     *
     * @param imageMetadata meta data of image file, which need to be optimized
     * @param targetImageQuality desired quality of compressed image
     * @returns {Promise} A promise that returns ImageAndFilePickerResponse
     * with status code and array of optimised file identifiers
     */
    compressImage(imageMetadata: FileMetaData, targetImageQuality: ImageQuality): Promise<ImageAndFilePickerResponse>;
}
