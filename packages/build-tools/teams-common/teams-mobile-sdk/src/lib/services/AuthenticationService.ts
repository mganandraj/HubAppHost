import { NativeAuthenticationService } from './SdkNativeModules';

/**
 * The authentication services enables developers to authenticate the user to their application resources.
 * Before fetching a resource token for a domain, the domain must first be declared within the app's manifest.
 *
 * **For example**
 * ```json
 * {
 *   "views": [
 *     // ...
 *   ],
 *   "authDomains": [
 *     "https://graph.microsoft.com"
 *   ]
 * }
 * ```
 *
 * Token requests for domains which are not declared within the manifest will fail.
 */
export class AuthenticationService {

  /**
   * This method gets the resource token for the specified resource. If the resource is supported,
   * the method will return a valid access token for the resource. If the resource is not supported,
   * the method will fail with an error.
   *
   * @param resource resource for which the token is being requested
   * @returns {Promise} A promise that returns the access token (type string) when resolved
   */
   public static getResourceToken (resource: string): Promise<string> {
     return NativeAuthenticationService.getResourceToken(resource);
   }

   /**
    * This method gets the access token for the specified resource. If the resource is supported,
    * the method will return a valid access token for the resource. If the resource is not supported,
    * the method will fail with an error. 
    *
    * @param resource resource for which the token is being requested
    * @param silent optional flag indicating whether to attempt the token acquisition silently or 
    * allow a prompt to be shown. Default value of the flag is false
    * @param claim optional claim string to pass to AAD when requesting for the access token
    * @returns {Promise} A promise that returns the access token (type string) when resolved
    */
    public static getResourceTokenWithClaim (resource: string, silent?: boolean, claim?: string): Promise<string> {
      if (silent) {
        return NativeAuthenticationService.getResourceTokenWithClaim(resource, silent, claim);
      } else {
        return NativeAuthenticationService.getResourceTokenWithClaim(resource, false, claim);
      }
    }
 
    /**
     * This method gets users skype token
     *
     * @param isConsumer flag indicating whether a consumer or not
     * @returns {Promise} A promise that returns the skype token (type string) when resolved
     */
    public static getSkypeToken (isConsumer: boolean): Promise<string> {
      return NativeAuthenticationService.getSkypeToken(isConsumer);
    }
}
