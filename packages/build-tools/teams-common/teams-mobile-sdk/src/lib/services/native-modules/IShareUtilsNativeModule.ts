import { ShareObject } from "src/lib/models/share/ShareObject";
import { ShareTarget } from "src/lib/models/share/ShareTarget";
import { IShareUtilsModule } from './IShareUtilsModule';

/**
 * @hidden from docs
 * Interface for the `ShareUtils` native module.
 */

export interface IShareUtilsNativeModule extends IShareUtilsModule {
    /**
     * @hidden from docs
     * Launches the Teams recipient picker on top of the current view.
     *
     * > [!NOTE]
     * > Calling this method takes the user out of your app.
     *
     * @param shareObject the object that needs to be shared
     * @returns {Promise} A promise that resolves to a `ShareTarget`
     */ 
  pickTargetFromShareTargetPickerForShareObject(shareObject: ShareObject): Promise<ShareTarget>;
}