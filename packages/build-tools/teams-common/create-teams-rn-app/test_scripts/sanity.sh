#!/bin/bash
# This script does a sanity test of the create-teams-rn-app utility.
# It is assumed the code is built and ./build/src/index.js is available.
# Run with -p in the pipeline to skip better-vsts-npm-auth. This is not needed
# in pipeline because we are already running the npm auth task in the pipeline.

set -e # exit script if any command fails

# keep track of the last executed command
trap 'last_command=$current_command; current_command=$BASH_COMMAND' DEBUG
# echo the error code before exiting
trap 'echo "\"${last_command}\" command finished with exit code $?."' EXIT

skip_vsts_npm_auth=false
while getopts "p" opt; do
  case ${opt} in
    p ) skip_vsts_npm_auth=true
      ;;
    \? ) echo "Usage: ./test.sh [-a]. Use -a to skip better-vsts-npm-auth."
      ;;
  esac
done

cwd=$(pwd) # store the current working directory in cwd

node ./dist/index.js --use-defaults hello-world
cd hello-world
if [ "$skip_vsts_npm_auth" = "false" ]
then
    echo 'Performing VSTS npm auth'
    better-vsts-npm-auth -config .npmrc
fi
yarn install
yarn build
yarn package-app

echo 'Test succeeded!'

set +e

cd $cwd
rm -rf hello-world # clean up!
