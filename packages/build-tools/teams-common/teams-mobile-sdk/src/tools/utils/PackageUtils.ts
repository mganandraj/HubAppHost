import * as ChildProcess from 'child_process';
import * as FileSystem from 'fs-extra';
import * as Path from 'path';

export class PackageUtils {
  public static appDirectory: string = Path.resolve('.');
  public static buildPath: string = Path.resolve(PackageUtils.appDirectory, 'build');
  public static buildOutputPath: string = Path.resolve(PackageUtils.buildPath, 'outputs');
  public static buildResourcePath: string = Path.resolve(PackageUtils.buildPath, 'resources');
  public static sourcemapOutputPath: string = Path.resolve(PackageUtils.buildOutputPath, 'sourcemap');
  public static bundleOutputPath: string = Path.resolve(PackageUtils.buildOutputPath, 'bundle');
  public static reactNativeCliScriptPath: string = require.resolve('react-native/local-cli/cli');
  public static appCenterCliScriptPath: string = require.resolve('appcenter-cli/bin/appcenter');
  // Following works with macos only. Need separate implementation for other OS.
  public static hermesOsxCliScriptPath: string = require.resolve('hermes-engine/osx-bin/hermesc');

  public static createReactNativeBundle(hermesBundle: boolean, callback): void {
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
    ChildProcess.execFileSync('node', rnIosBundleCommandArgs, {stdio: 'inherit'});
    if (hermesBundle) {
      ChildProcess.execFileSync('node', rnAndroidBundleCommandArgs, {stdio: 'inherit'});
      console.log('Creating Hermes bundle at ' + this.bundleOutputPath);
      const hermesBundleArguments = [
        '-emit-binary',
        '-out', Path.resolve(this.bundleOutputPath, 'hermes.android.bundle'),
        Path.resolve(this.bundleOutputPath + Path.sep + 'index.android.bundle')
      ];
      ChildProcess.execFile(this.hermesOsxCliScriptPath, hermesBundleArguments, callback).stdout!.pipe(process.stdout);
    } else {
      ChildProcess.execFile('node', rnAndroidBundleCommandArgs, callback).stdout!.pipe(process.stdout);
    }
  }

  public static isHermesBundleEnabled(args: any): boolean {
    console.log("Hermes Bundle: " + args.hermes);
    return args.hermes != null && args.hermes === "enabled";
  }

  public static removeAndroidBundleFile() {
    const appAndroidBundlePath = Path.resolve(PackageUtils.bundleOutputPath, 'index.android.bundle');
    console.log("Removing bundle file: " + appAndroidBundlePath);
    FileSystem.removeSync(appAndroidBundlePath);
  }
}
