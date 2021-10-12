"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.DeepLinkUtils = void 0;
const SdkNativeModules_1 = require("../services/SdkNativeModules");
class DeepLinkUtils {
    /**
     * Gets the deep link for a module. Deeplinks can be opened using `Linking`.
     *
     * @param moduleId target module ID
     * @param params params to be included in the deep link
     */
    static getDeepLinkForModule(moduleId, params) {
        return SdkNativeModules_1.NavigationService.getDeepLinkForModule(moduleId, JSON.stringify(params));
    }
    /**
     * Navigates user to a given deep link URL within Teams.
     * If the given deep link is not supported by Teams, user will be navigated to the URL in browser.
     *
     * @param deepLink to any module in Teams.
     */
    static executeDeepLink(deepLink) {
        SdkNativeModules_1.NavigationService.executeDeepLink(deepLink);
    }
}
exports.DeepLinkUtils = DeepLinkUtils;
