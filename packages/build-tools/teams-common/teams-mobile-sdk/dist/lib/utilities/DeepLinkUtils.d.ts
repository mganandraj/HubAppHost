export declare class DeepLinkUtils {
    /**
     * Gets the deep link for a module. Deeplinks can be opened using `Linking`.
     *
     * @param moduleId target module ID
     * @param params params to be included in the deep link
     */
    static getDeepLinkForModule(moduleId: string, params: any): Promise<string>;
    /**
     * Navigates user to a given deep link URL within Teams.
     * If the given deep link is not supported by Teams, user will be navigated to the URL in browser.
     *
     * @param deepLink to any module in Teams.
     */
    static executeDeepLink(deepLink: string): void;
}
