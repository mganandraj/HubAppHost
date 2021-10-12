"use strict";
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
Object.defineProperty(exports, "__esModule", { value: true });
const Archiver = require("archiver");
const FileSystem = require("fs-extra");
const Minimist = require("minimist");
const Path = require("path");
const PackageUtils_1 = require("../utils/PackageUtils");
class PackagerImpl {
    package(args) {
        const hermesBundleEnabled = PackageUtils_1.PackageUtils.isHermesBundleEnabled(args);
        const build = args.build === undefined || args.build;
        const _ = this;
        if (build) {
            PackageUtils_1.PackageUtils.createReactNativeBundle(hermesBundleEnabled, function (error) {
                if (error) {
                    console.error('Failed to create RN bundle.' + error);
                }
                else {
                    _.compressPackage(hermesBundleEnabled);
                }
            });
        }
        else {
            _.compressPackage(hermesBundleEnabled);
        }
    }
    compressPackage(hermesBundleEnabled) {
        const appPackageJsonFilePath = Path.resolve(PackageUtils_1.PackageUtils.appDirectory, 'package.json');
        const appManifest = require(appPackageJsonFilePath);
        const appName = appManifest.name;
        const appVersion = appManifest.version;
        const compressedPackagePath = Path.resolve(PackageUtils_1.PackageUtils.buildOutputPath, appName + '-' + appVersion + '.zip');
        if (hermesBundleEnabled) {
            PackageUtils_1.PackageUtils.removeAndroidBundleFile();
        }
        FileSystem.createFileSync(compressedPackagePath);
        const output = FileSystem.createWriteStream(compressedPackagePath);
        const archiver = Archiver('zip', { zlib: { level: 9 } });
        archiver.pipe(output);
        archiver.directory(PackageUtils_1.PackageUtils.bundleOutputPath, false);
        archiver.directory(Path.resolve(PackageUtils_1.PackageUtils.appDirectory, PackageUtils_1.PackageUtils.buildResourcePath), false);
        output.on('finish', () => {
            console.log('Success! Created app package at ' + compressedPackagePath);
        });
        output.on('error', (error) => {
            console.error('Failed to package app.');
            console.error(error);
        });
        archiver.finalize();
    }
}
const args = Minimist(process.argv.slice(2));
const Packager = new PackagerImpl();
Packager.package(args);
