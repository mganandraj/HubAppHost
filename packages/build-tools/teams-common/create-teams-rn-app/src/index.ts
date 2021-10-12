#!/usr/bin/env node
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
import chalk  from 'chalk';
import { Command } from 'commander';
import prompts from 'prompts';

import { copy, mkdirp } from 'fs-extra';
import path from 'path';
import { v4 as uuidv4 } from 'uuid';
import packageJSON from '../package.json';
import { DEFAULT_APP_VERSION, DEFAULT_AUTHOR, DEFAULT_LICENSE, DEFAULT_TEAMS_MOBILE_SDK_VERSION } from './constants';
import { fillCopyrightHeaders, fillManifestJsonValues, fillPackageJsonValues, fillStringResources } from './template';

let projectDirName: string;

const program = new Command(packageJSON.name)
    .version(packageJSON.version)
    .option('--use-defaults', 'use default values; no input prompts', false)
    .arguments('<directory>')
    .action(directory => (projectDirName = directory))
    .parse(process.argv);

async function runAsync(): Promise<void> {
    console.log(chalk.bold('Creating a new Teams RN app in directory: ') + chalk.blue.bold(projectDirName));

    const appId = await getAppId();
    const userFriendlyAppName = await getUserFriendlyAppName();
    const description = await getDescription();
    const author = await getAuthor();
    const license = await getLicense();
    const appVersion = await getAppVersion();
    const teamsMobileSdkVersion = await getTeamsMobileSdkVersion();
    const minTeamsMobileSdkVersion = await getMinTeamsMobileSdkVersion();

    try {
        await mkdirp(projectDirName);
    } catch (err) {
        console.error(chalk.red('Couldn\'t create directory: ' + projectDirName));
        console.error(err);
        process.exit(1);
    }

    const projectRoot = path.join(process.cwd(), projectDirName);
    try {
        await copy(path.join(__dirname, '../template'), projectRoot);
    } catch (err) {
        console.error(chalk.red('Failed to copy template!'));
        console.error(err);
        process.exit(1);
    }

    const packageJSONPath = path.join(projectRoot, 'package.json');
    fillPackageJsonValues(packageJSONPath,
                          projectDirName,
                          appVersion,
                          description,
                          author,
                          license,
                          teamsMobileSdkVersion);
    
    const manifestJsonPath = path.join(projectRoot, 'manifest.json');
    fillManifestJsonValues(manifestJsonPath,
                           appId,
                           userFriendlyAppName,
                           minTeamsMobileSdkVersion);

    const stringEnJsonPath = path.join(projectRoot, './resources/strings/strings_en.json');
    fillStringResources(stringEnJsonPath, userFriendlyAppName);

    fillCopyrightHeaders(projectRoot, author);

    console.log(chalk.bold(`✅ Your project is ready!`));
}

async function getAppId(): Promise<string> {
    const defaultAppId = uuidv4();
    if (program.useDefaults === true) {
        return defaultAppId;
    } else {
        const answer = await prompts({
            type: 'text',
            name: 'value',
            message: 'choose an app id',
            initial: defaultAppId
        });
        return answer.value;
    }
}

async function getUserFriendlyAppName() {
    const defaultFriendlyAppName = projectDirName.replace('-', ' ')
        .replace('_', ' ')
        .replace(/\w\S*/g, word => word.charAt(0).toLocaleUpperCase()
            + word.substr(1).toLocaleLowerCase());

    if (program.useDefaults === true) {
        // convert projectDirName to title case
        return defaultFriendlyAppName;
    } else {
        const answer = await prompts({
            type: 'text',
            name: 'value',
            message: 'enter a user-friendly app name: ',
            initial: defaultFriendlyAppName
        });
        return answer.value;
    }
}

async function getDescription(): Promise<string> {
    if (program.useDefaults === true) {
        return '';
    } else {
        const answer = await prompts({
            type: 'text',
            name: 'value',
            message: 'description'
        });
        return answer.value;
    }
}

async function getAuthor(): Promise<string> {
    if (program.useDefaults === true) {
        return DEFAULT_AUTHOR;
    } else {
        const answer = await prompts({
            type: 'text',
            name: 'value',
            message: 'author',
            initial: DEFAULT_AUTHOR
        });
        return answer.value;
    }
}

async function getLicense(): Promise<string> {
    if (program.useDefaults === true) {
        return DEFAULT_LICENSE;
    } else {
        const answer = await prompts({
            type: 'text',
            name: 'value',
            initial: DEFAULT_LICENSE,
            message: 'license'
        });
        return answer.value;
    }
}

async function getAppVersion(): Promise<string> {
    if (program.useDefaults === true) {
        return DEFAULT_APP_VERSION;
    } else {
        const answer = await prompts({
            type: 'text',
            name: 'value',
            initial: DEFAULT_APP_VERSION,
            message: 'version'
        });
        return answer.value;
    }
}

async function getTeamsMobileSdkVersion(): Promise<string> {
    if (program.useDefaults === true) {
        return DEFAULT_TEAMS_MOBILE_SDK_VERSION;
    } else {
        const answer = await prompts({
            type: 'text',
            name: 'value',
            initial: DEFAULT_TEAMS_MOBILE_SDK_VERSION,
            message: 'teams-mobile-sdk version'
        });
        return answer.value;
    }
}

async function getMinTeamsMobileSdkVersion(): Promise<string> {
    if (program.useDefaults === true) {
        return DEFAULT_TEAMS_MOBILE_SDK_VERSION;
    } else {
        const answer = await prompts({
            type: 'text',
            name: 'value',
            initial: DEFAULT_TEAMS_MOBILE_SDK_VERSION,
            message: 'minimum teams-mobile-sdk version'
        });
        return answer.value;
    }
}

runAsync();
