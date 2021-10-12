"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
const ChildProcess = require("child_process");
const FileSystem = require("fs-extra");
const Minimist = require("minimist");
const Path = require("path");
const FileUtilities_1 = require("../../common/utilities/FileUtilities");
const PackageUtils_1 = require("../utils/PackageUtils");
const readline = require('readline');
const teamSdkVersion = require('../../../package.json').version;
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});
class PublisherImpl {
    constructor(args) {
        this.parseConfig(args);
        this.hermesBundleEnabled = PackageUtils_1.PackageUtils.isHermesBundleEnabled(args);
        this.appCenterCliScriptPath = PackageUtils_1.PackageUtils.appCenterCliScriptPath;
        this.build = args.build === undefined || args.build;
        if (!this.appCenterCliScriptPath) {
            throw new Error('Unable to resolve Appcenter cli script.');
        }
    }
    publish() {
        const _ = this;
        if (this.build) {
            PackageUtils_1.PackageUtils.createReactNativeBundle(this.hermesBundleEnabled, function (error) {
                if (error) {
                    _.fatalError('Failed to create RN bundle: ' + error);
                }
                else {
                    _.upload();
                }
            });
        }
        else {
            _.upload();
        }
    }
    upload() {
        this.stageUpdate();
        this.login();
        this.publishUpdate();
    }
    stageUpdate() {
        const appManifest = require(Path.resolve(PackageUtils_1.PackageUtils.appDirectory, 'package.json'));
        const appName = appManifest.name;
        const appVersion = appManifest.version;
        const updatePath = Path.resolve(PackageUtils_1.PackageUtils.appDirectory, 'build/outputs/' + appName + '-' + appVersion);
        if (this.hermesBundleEnabled) {
            PackageUtils_1.PackageUtils.removeAndroidBundleFile();
        }
        FileUtilities_1.FileUtilities.ensureEmptyDirectory(updatePath)
            .then(() => {
            FileSystem.mkdirsSync(updatePath);
            return FileUtilities_1.FileUtilities.copyDirectory(PackageUtils_1.PackageUtils.bundleOutputPath, updatePath);
        }).then(() => {
            return FileUtilities_1.FileUtilities.copyDirectory(Path.resolve(PackageUtils_1.PackageUtils.appDirectory, 'build/resources'), updatePath);
        }).catch((error) => {
            this.fatalError(error);
        });
    }
    login() {
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
        ChildProcess.execFileSync('node', appCenterCodePushCommandArgs, { stdio: 'inherit' });
    }
    logout() {
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
        ChildProcess.execFileSync('node', appCenterCodePushCommandArgs, { stdio: 'inherit' });
    }
    publishUpdate() {
        const appManifest = require(Path.resolve(PackageUtils_1.PackageUtils.appDirectory, 'build/resources/manifest/manifest.json'));
        const appName = appManifest.name;
        const appVersion = appManifest.version;
        const minReactNativeSdkVersion = appManifest.minReactNativeSdkVersion;
        const targetReactNativeSdkVersion = appManifest.targetReactNativeSdkVersion;
        const subversions = targetReactNativeSdkVersion.split('.', 3);
        const maxReactNativeSdkVersion = subversions[0] + '.' + subversions[1] + '.x';
        const updatePath = Path.resolve(PackageUtils_1.PackageUtils.appDirectory, 'build/outputs/' + appName + '-' + appVersion);
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
        ChildProcess.execFile('node', appCenterCodePushCommandArgs, (error) => { this.finish(error); }).stdout.pipe(process.stdout);
    }
    finish(error) {
        this.logout();
        if (error) {
            this.fatalError('Error encountered when publishing update.');
        }
        console.log('Successfully published update.');
    }
    parseConfig(args) {
        if (args.config) {
            this.config = FileUtilities_1.FileUtilities.readJsonSync(args.config);
            if (!this.config) {
                this.fatalError('Malformed configuration.');
            }
            this.assertConfigExists('appName', this.config.appName);
            this.assertConfigExists('deployment', this.config.deployment);
        }
        else {
            this.fatalError('No configuration provided.');
        }
    }
    assertConfigExists(name, object) {
        if (!object) {
            this.fatalError('Missing required configuration: ' + name);
        }
    }
    fatalError(message) {
        console.error('Unable to publish: ' + message);
        process.exit(1);
    }
}
const args = Minimist(process.argv.slice(2));
const Publisher = new PublisherImpl(args);
if (teamSdkVersion.search('-') !== -1) {
    rl.question("Bundle contains alpha version of teams-mobile-sdk which might not work in production. Want to continue? [Y/N]", function (answer) {
        if (answer === 'Y' || answer === 'y') {
            Publisher.publish();
            rl.close();
        }
        else {
            rl.close();
        }
    });
}
else {
    Publisher.publish();
    rl.close();
}
