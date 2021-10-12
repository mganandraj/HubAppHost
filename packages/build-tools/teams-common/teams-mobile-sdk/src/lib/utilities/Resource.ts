/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import { ArgumentsValidator } from '../../common/utilities/ArgumentsValidator';
import * as LocalizedStrings from '../services/LocalizedStrings';

export class Resource {
  /**
   * Returns the URI for a string resource. Useful when sending a
   * string to native.
   *
   * @param stringResourceName name of the string resource
   */
  public static string (stringResourceName: string): string {
    ArgumentsValidator.warnIfNullOrUndefined('stringResourceName', stringResourceName);
    return 'res://strings/' + stringResourceName;
  }

  /**
   * Returns the URI for an image resource. Useful when sending an
   * image to native.
   *
   * @param imageResourceName name of the image resource
   */
  public static image (imageResourcePath: string): string {
    ArgumentsValidator.warnIfNullOrUndefined('imageResourceName', imageResourcePath);
    return 'res://images/' + imageResourcePath;
  }

  /**
   * Gets the localized version of a string.
   *
   * @param path path to the string (usually the string resource name)
   * @param paramData data for placeholder replacement in the string
   */
  public static getLocalizedString (path: string, paramData: { [name: string]: string | number } = {}): string {
    return LocalizedStrings.getString(path, paramData);
  }
}
