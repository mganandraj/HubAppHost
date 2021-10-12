import * as minimist from 'minimist';
import { SdkConfigManager } from './SdkConfigManager';

try {
  const args = minimist(process.argv.slice(2));
  if (args['k'] && args['v']) {
    SdkConfigManager.set(args['k'], args['v']);
  } else if (args['k']) {
    console.log(SdkConfigManager.get(args['k']));
  } else if (args['r']) {
    SdkConfigManager.remove(args['r']);
  } else if (args['a']) {
    let foundSomething = false;
    const configObject = SdkConfigManager.getAll();
    for (const key in Object.keys(configObject)) {
      const value = configObject[key];
      if (key && value) {
        console.info(key + ':' + value);
        foundSomething = true;
      }
    }

    if (!foundSomething) {
      console.log('Nothing!!');
    }
  } else {
    console.error('Unrecognized command.');
    printUsage();
  }
} catch (error) {
  console.log(error);
  printUsage();
}

function printUsage () {
  const usage = '\nUsage: node <sdk-path>/bin/config <args>'
    + '\n\t-k <key>            : Gets the config value set for <key>.'
    + '\n\t-k <key> -v <value> : Set the config value for <key> to <value>.'
    + '\n\t-r <key>            : Removes the config value set for <key>.'
    + '\n\t-a                  : Gets all config value.';

  console.log(usage);
}
