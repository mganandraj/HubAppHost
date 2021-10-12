---
uid: create_a_new_app_auto 
---

# Creating a new app
This article describes how to get a basic Teams RN app up and running using the
`create-teams-rn-app` command-line utility.

## Pre-requisites
1. [Node.js](https://nodejs.org/en)
2. [Yarn 1.19.1](https://yarnpkg.com/en/)
3. For Windows: [`vsts-npm-auth`](https://www.npmjs.com/package/vsts-npm-auth)
4. For macOS: [`better-vsts-npm-auth](https://www.npmjs.com/package/better-vsts-npm-auth)
5. Obtain `Read` permissions for the `teams-mobile-platform` NPM registry by
   reaching out to us on the **Teams Mobile Platform > Issues** channel.

## Installing `create-teams-rn-app`
`create-teams-rn-app` is available _only_ in the `teams-mobile-platform` private
NPM registry at:

```
https://domoreexp.pkgs.visualstudio.com/_packaging/teams-mobile-platform/npm/registry/
```

By default, the `npm` command downloads packages from the 
[public feed](https://registry.npmjs.org/). So, to install `create-teams-rn-app`,
`npm` should be configured to use the private registry. There are many ways to
do this.

The simplest way is to temporarily change the `registry` config to point to the
private registry, install `create-teams-rn-app` and then change the registry 
back to the public one:

```
$ npm config set registry https://domoreexp.pkgs.visualstudio.com/_packaging/teams-mobile-platform/npm/registry/
$ ... do the installation ...
$ npm config set registry https://registry.npmjs.org/
```

Another way of doing it is using [npmrc profiles](https://docs.npmjs.com/configuring-your-registry-settings-as-an-npm-enterprise-user).

Once you have setup the registry correctly, you will need to authenticate
yourself. You can do this using `better-vsts-npm-auth` (use `vsts-npm-auth` if
you are on Windows):

```
$ cd ~ # go to the home directory; this is where the .npmrc file is
$ better-vsts-npm-auth -config .npmrc # use vsts-npm-auth in Windows
```

Now, you can install the latest version of `create-teams-rn-app` by running:

```
$ npm i -g create-teams-rn-app
```

The same command can be run again later to update `create-teams-rn-app` to the 
latest available version.

> [!NOTE]
> At this point you may change the default registry back to the public one (or
> change your npmrc profile back to default, if took that route)

## Bootstrap a minimal app
Run:

```
$ create-teams-rn-app hello-world
```

It will prompt for a few details like the author name, app ID, etc. You
can simply press the return key to accept the default values. You can also use 
the flag `--use-defaults` to silently create an app using the default values.

For now, just accept all the default values by pressing the return key at each
prompt.

Once finished, you will have a new directory called `hello-world` which contains
a working RN app that can run inside Teams.

## Run your app
You should now be able to install dependences and build the app:
```
$ cd hello-world
$ better-vsts-npm-auth -config .npmrc
$ yarn install
$ yarn build
```

To see your app in action you need to get a dev build of Teams running on your
device/simulator. Refer to [Build and deploy](xref:build_and_deploy).

Once all that is set up run:
```
$ yarn android
```

Or, if you are using iOS
```
$ yarn ios-sim
```
