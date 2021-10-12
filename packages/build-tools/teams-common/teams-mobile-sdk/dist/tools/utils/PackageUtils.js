"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.PackageUtils = void 0;
const ChildProcess = require("child_process");
const FileSystem = require("fs-extra");
const Path = require("path");
class PackageUtils {
    static createReactNativeBundle(hermesBundle, callback) {
        if (!this.reactNativeCliScriptPath) {
            callback('Unable to resolve RN cli script.');
            return;
        }
        const rnAndroidEntryFile = Path.resolve(this.appDirectory, 'index.android.js');
        if (!FileSystem.existsSync(rnAndroidEntryFile)) {
            callback('Entry file index.android.js not found!');
            return;
        }
        const rnIosEntryFile = Path.resolve(this.appDirectory, 'index.ios.js');
        if (!FileSystem.existsSync(rnIosEntryFile)) {
            callback('Entry file index.ios.js not found!');
            return;
        }
        FileSystem.ensureDirSync(this.bundleOutputPath);
        FileSystem.ensureDirSync(this.sourcemapOutputPath);
        const rnAndroidBundleCommandArgs = [
            this.reactNativeCliScriptPath,
            'bundle',
            '--platform', 'android',
            '--dev', 'false',
            '--entry-file', rnAndroidEntryFile,
            '--bundle-output', Path.resolve(this.bundleOutputPath, 'index.android.bundle'),
            '--assets-dest', Path.resolve(this.bundleOutputPath),
            '--sourcemap-output', Path.resolve(this.sourcemapOutputPath, 'sourcemap.android.js')
        ];
        const rnIosBundleCommandArgs = [
            this.reactNativeCliScriptPath,
            'bundle',
            '--platform', 'ios',
            '--dev', 'false',
            '--entry-file', rnIosEntryFile,
            '--bundle-output', Path.resolve(this.bundleOutputPath, 'index.ios.bundle'),
            '--assets-dest', Path.resolve(this.bundleOutputPath),
            '--sourcemap-output', Path.resolve(this.sourcemapOutputPath, 'sourcemap.ios.js')
        ];
        console.log('Creating RN bundle at ' + this.bundleOutputPath);
        ChildProcess.execFileSync('node', rnIosBundleCommandArgs, { stdio: 'inherit' });
        if (hermesBundle) {
            ChildProcess.execFileSync('node', rnAndroidBundleCommandArgs, { stdio: 'inherit' });
            console.log('Creating Hermes bundle at ' + this.bundleOutputPath);
            const hermesBundleArguments = [
                '-emit-binary',
                '-out', Path.resolve(this.bundleOutputPath, 'hermes.android.bundle'),
                Path.resolve(this.bundleOutputPath + Path.sep + 'index.android.bundle')
            ];
            ChildProcess.execFile(this.hermesOsxCliScriptPath, hermesBundleArguments, callback).stdout.pipe(process.stdout);
        }
        else {
            ChildProcess.execFile('node', rnAndroidBundleCommandArgs, callback).stdout.pipe(process.stdout);
        }
    }
    static isHermesBundleEnabled(args) {
        console.log("Hermes Bundle: " + args.hermes);
        return args.hermes != null && args.hermes === "enabled";
    }
    static removeAndroidBundleFile() {
        const appAndroidBundlePath = Path.resolve(PackageUtils.bundleOutputPath, 'index.android.bundle');
        console.log("Removing bundle file: " + appAndroidBundlePath);
        FileSystem.removeSync(appAndroidBundlePath);
    }
}
exports.PackageUtils = PackageUtils;
PackageUtils.appDirectory = Path.resolve('.');
PackageUtils.buildPath = Path.resolve(PackageUtils.appDirectory, 'build');
PackageUtils.buildOutputPath = Path.resolve(PackageUtils.buildPath, 'outputs');
PackageUtils.buildResourcePath = Path.resolve(PackageUtils.buildPath, 'resources');
PackageUtils.sourcemapOutputPath = Path.resolve(PackageUtils.buildOutputPath, 'sourcemap');
PackageUtils.bundleOutputPath = Path.resolve(PackageUtils.buildOutputPath, 'bundle');
PackageUtils.reactNativeCliScriptPath = require.resolve('react-native/local-cli/cli');
PackageUtils.appCenterCliScriptPath = require.resolve('appcenter-cli/bin/appcenter');
// Following works with macos only. Need separate implementation for other OS.
PackageUtils.hermesOsxCliScriptPath = require.resolve('hermes-engine/osx-bin/hermesc');
