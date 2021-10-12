/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import * as Archiver from 'archiver';
import * as FileSystem from 'fs-extra';
import * as Minimist from 'minimist';
import * as Path from 'path';
import {PackageUtils} from '../utils/PackageUtils';

class PackagerImpl {

  public package(args: any): void {
    const hermesBundleEnabled = PackageUtils.isHermesBundleEnabled(args);
    const build = args.build === undefined || args.build;
    const _ = this;

    if (build) {
      PackageUtils.createReactNativeBundle(hermesBundleEnabled, function (error) {
        if (error) {
          console.error('Failed to create RN bundle.' + error);
        } else {
          _.compressPackage(hermesBundleEnabled);
        }
      });
    } else {
      _.compressPackage(hermesBundleEnabled);
    }
  }

  private compressPackage(hermesBundleEnabled: boolean) {
    const appPackageJsonFilePath = Path.resolve(PackageUtils.appDirectory, 'package.json');

    const appManifest = require(appPackageJsonFilePath);
    const appName = appManifest.name;
    const appVersion = appManifest.version;

    const compressedPackagePath = Path.resolve(PackageUtils.buildOutputPath, appName + '-' + appVersion + '.zip');
    if (hermesBundleEnabled) {
      PackageUtils.removeAndroidBundleFile();
    }
    FileSystem.createFileSync(compressedPackagePath);
    const output = FileSystem.createWriteStream(compressedPackagePath);
    const archiver = Archiver('zip', {zlib: {level: 9}});
    archiver.pipe(output);
    archiver.directory(PackageUtils.bundleOutputPath, false);
    archiver.directory(Path.resolve(PackageUtils.appDirectory, PackageUtils.buildResourcePath), false);
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
