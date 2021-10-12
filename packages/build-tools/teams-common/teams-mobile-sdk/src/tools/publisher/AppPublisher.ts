/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
import * as ChildProcess from 'child_process';
import * as FileSystem from 'fs-extra';
import * as Minimist from 'minimist';
import * as Path from 'path';
import { FileUtilities } from '../../common/utilities/FileUtilities';
import { PackageUtils } from '../utils/PackageUtils';
const readline = require('readline');
const teamSdkVersion = require('../../../package.json').version;
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

interface Config {
  accessToken: string;
  appName: string;
  deployment: string;
  description: string;
  additionalArgs: string[];
}

class PublisherImpl {
  private appCenterCliScriptPath: string;
  private config: Config;
  private hermesBundleEnabled: boolean;
  private build: boolean;

  constructor(args: any) {
    this.parseConfig(args);
    this.hermesBundleEnabled = PackageUtils.isHermesBundleEnabled(args);
    this.appCenterCliScriptPath = PackageUtils.appCenterCliScriptPath;
    this.build = args.build === undefined || args.build;

    if (!this.appCenterCliScriptPath) {
      throw new Error('Unable to resolve Appcenter cli script.');
    }
  }

  public publish (): void {
    const _ = this;

    if (this.build) {
      PackageUtils.createReactNativeBundle(this.hermesBundleEnabled, function (error: any) {
        if (error) {
          _.fatalError('Failed to create RN bundle: ' + error);
        } else {
          _.upload();
        }
      });
    } else {
      _.upload();
    }
  }

  private upload (): void {
    this.stageUpdate();
    this.login();
    this.publishUpdate();
  }

  private stageUpdate (): void {
    const appManifest = require(Path.resolve(PackageUtils.appDirectory, 'package.json'));
    const appName = appManifest.name;
    const appVersion = appManifest.version;

    const updatePath = Path.resolve(PackageUtils.appDirectory, 'build/outputs/' + appName + '-' + appVersion);
    if (this.hermesBundleEnabled) {
      PackageUtils.removeAndroidBundleFile();
    }
    FileUtilities.ensureEmptyDirectory(updatePath)
      .then(() => {
        FileSystem.mkdirsSync(updatePath);
        return FileUtilities.copyDirectory(PackageUtils.bundleOutputPath, updatePath);
      }).then(() => {
        return FileUtilities.copyDirectory(Path.resolve(PackageUtils.appDirectory, 'build/resources'), updatePath);
      }).catch((error) => {
        this.fatalError(error);
      });
  }

  private login (): void {
    if (!this.config.accessToken) {
      console.warn('WARNING: No access token provided, assuming already logged in.');
      return;
    }

    const appCenterCodePushCommandArgs = [
      this.appCenterCliScriptPath,
      'login',
      '--disable-telemetry',
      '--token', this.config.accessToken
    ];

    console.log('Logging in to AppCenter... ');
    ChildProcess.execFileSync('node', appCenterCodePushCommandArgs, {stdio: 'inherit'});
  }

  private logout (): void {
    if (!this.config.accessToken) {
      // Don't logout if an access token wasn't provided.
      return;
    }

    const appCenterCodePushCommandArgs = [
      this.appCenterCliScriptPath,
      'logout',
      '--disable-telemetry'
    ];

    console.log('Logging out from AppCenter... ');
    ChildProcess.execFileSync('node', appCenterCodePushCommandArgs, {stdio: 'inherit'});
  }

  private publishUpdate (): void {
    const appManifest = require(Path.resolve(PackageUtils.appDirectory, 'build/resources/manifest/manifest.json'));
    const appName = appManifest.name;
    const appVersion = appManifest.version;
    const minReactNativeSdkVersion = appManifest.minReactNativeSdkVersion;
    const targetReactNativeSdkVersion: string = appManifest.targetReactNativeSdkVersion;
    const subversions = targetReactNativeSdkVersion.split('.', 3);
    const maxReactNativeSdkVersion = subversions[0] + '.' + subversions[1] + '.x' ;

    const updatePath = Path.resolve(PackageUtils.appDirectory, 'build/outputs/' + appName + '-' + appVersion);

    const targetBinaryVersion = minReactNativeSdkVersion + ' - ' + maxReactNativeSdkVersion;
    const description = "AppVersion : " + appVersion + ". " + this.config.description;

    let appCenterCodePushCommandArgs = [
      this.appCenterCliScriptPath,
      'codepush', 'release',
      '--disable-telemetry',
      '--app', this.config.appName,
      '--update-contents-path', updatePath,
      '--target-binary-version', targetBinaryVersion,
      '--deployment-name', this.config.deployment,
      "--description", description
    ];

    appCenterCodePushCommandArgs = appCenterCodePushCommandArgs.concat(this.config.additionalArgs);

    console.log('Publishing ' + appName + ' ' + appVersion + ' to ' + this.config.appName + ' ' + this.config.deployment);
    ChildProcess.execFile('node', appCenterCodePushCommandArgs, (error) => { this.finish(error); }).stdout!.pipe(process.stdout);
  }

  private finish (error: any): void {
    this.logout();

    if (error) {
      this.fatalError('Error encountered when publishing update.');
    }
    console.log('Successfully published update.');
  }

  private parseConfig (args: any) {
    if (args.config) {
      this.config = FileUtilities.readJsonSync(args.config);
      if (!this.config) {
        this.fatalError('Malformed configuration.');
      }

      this.assertConfigExists('appName', this.config.appName);
      this.assertConfigExists('deployment', this.config.deployment);
    } else {
      this.fatalError('No configuration provided.');
    }
  }

  private assertConfigExists (name: string, object: any) {
    if (!object) {
      this.fatalError('Missing required configuration: ' + name);
    }
  }

  private fatalError (message: string) {
    console.error('Unable to publish: ' + message);
    process.exit(1);
  }
}

const args = Minimist(process.argv.slice(2));
const Publisher = new PublisherImpl(args);
if(teamSdkVersion.search('-') !== -1) {
  rl.question("Bundle contains alpha version of teams-mobile-sdk which might not work in production. Want to continue? [Y/N]", function(answer) {
    if(answer === 'Y' || answer === 'y') {
      Publisher.publish();
      rl.close();
    } else {
      rl.close();
    }
  });
} else {
  Publisher.publish();
  rl.close();
}
