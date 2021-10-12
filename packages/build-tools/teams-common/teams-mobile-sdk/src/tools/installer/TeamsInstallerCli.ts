import * as minimist from 'minimist';
import { TeamsDevAppInstaller } from './TeamsDevAppInstaller';

try {
  const args = minimist(process.argv.slice(2));
  if (args['p']) {
    const platform = args['p'];

    if (platform !== 'android' && platform !== 'ios'){
      throw new Error('Unrecognized platfrom. Specify correct platform');
    } else {
      TeamsDevAppInstaller.installTeamsDevApp(platform);
    }
  } else {
    throw new Error('Unrecognized command.');
  }
} catch (error) {
  console.log(error.message);
  printUsage();
}

function printUsage () {
  const usage = '\nUsage: yarn install-teams -p <platform>'
    + '\n\t<platform>        : Specify the platform value i.e. "ios" or "android".';

  console.log(usage);
}
