import { NavigationService } from '../services/SdkNativeModules';

export class DeepLinkUtils {
  /**
   * Gets the deep link for a module. Deeplinks can be opened using `Linking`.
   *
   * @param moduleId target module ID
   * @param params params to be included in the deep link
   */
  public static getDeepLinkForModule (moduleId: string, params: any): Promise<string> {
    return NavigationService.getDeepLinkForModule(moduleId, JSON.stringify(params));
  }

  /**
   * Navigates user to a given deep link URL within Teams.
   * If the given deep link is not supported by Teams, user will be navigated to the URL in browser.
   * 
   * @param deepLink to any module in Teams.
   */
  public static executeDeepLink (deepLink: string) : void {
    NavigationService.executeDeepLink(deepLink);
  }
}
