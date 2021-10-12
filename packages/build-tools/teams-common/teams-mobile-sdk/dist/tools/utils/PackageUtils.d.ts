export declare class PackageUtils {
    static appDirectory: string;
    static buildPath: string;
    static buildOutputPath: string;
    static buildResourcePath: string;
    static sourcemapOutputPath: string;
    static bundleOutputPath: string;
    static reactNativeCliScriptPath: string;
    static appCenterCliScriptPath: string;
    static hermesOsxCliScriptPath: string;
    static createReactNativeBundle(hermesBundle: boolean, callback: any): void;
    static isHermesBundleEnabled(args: any): boolean;
    static removeAndroidBundleFile(): void;
}
