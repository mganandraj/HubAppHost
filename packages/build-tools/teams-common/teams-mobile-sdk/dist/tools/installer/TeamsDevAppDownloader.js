"use strict";
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.TeamsDevAppDownloader = void 0;
const FollowRedirects = require("follow-redirects");
const FileSystem = require("fs-extra");
const Url = require("url");
const SdkConfigManager_1 = require("../config/SdkConfigManager");
const appDownloadUrl = 'https://api.appcenter.ms/v0.1/apps/ownerName/appName/releases/latest';
const APP_NAME = 'appName';
const OWNER_NAME = 'ownerName';
class TeamsDevAppDownloader {
    static downloadTeamsDevApp(downloadOutputPath, platform, callback) {
        const sdkConfig = require('../../../sdkconfig.json');
        const devAppDownloadConfig = sdkConfig ? sdkConfig['devAppDownloadConfig'] : undefined;
        const appName = devAppDownloadConfig ? devAppDownloadConfig[platform][APP_NAME] : undefined;
        if (!appName) {
            throw new Error('App name is not specified in sdk config.');
        }
        const ownerName = devAppDownloadConfig ? devAppDownloadConfig[OWNER_NAME] : undefined;
        if (!ownerName) {
            throw new Error('Owner name is not specified in sdk config.');
        }
        const userApiToken = SdkConfigManager_1.SdkConfigManager.getUserApiToken();
        if (!userApiToken) {
            const error = 'User Api token is not configured. Please configure. Example:'
                + '\nyarn sdk-config -k userApiToken -v <your-user-api-token>';
            throw new Error(error);
        }
        const url = appDownloadUrl.replace(APP_NAME, appName).replace(OWNER_NAME, ownerName);
        console.log('Downloading Teams dev build from: ' + url);
        const parsedUrl = new Url.URL(url);
        const options = {
            protocol: parsedUrl.protocol,
            host: parsedUrl.host,
            method: 'GET',
            path: parsedUrl.pathname,
            headers: { 'X-API-Token': userApiToken }
        };
        TeamsDevAppDownloader.download(options, downloadOutputPath, callback);
    }
    static download(options, downloadOutputPath, callback) {
        FollowRedirects.https.get(options, function (response) {
            let rawData = '';
            response.setEncoding('utf8');
            response.on('data', (chunk) => { rawData += chunk; });
            response.on('end', () => {
                try {
                    const parsedData = JSON.parse(rawData);
                    TeamsDevAppDownloader.downloadBinary(parsedData.download_url, downloadOutputPath, callback);
                }
                catch (error) {
                    callback(error);
                }
            });
        }).on('error', function (error) {
            callback(error);
        });
    }
    static downloadBinary(url, downloadOutputPath, callback) {
        const file = FileSystem.createWriteStream(downloadOutputPath);
        const parsedUrl = new Url.URL(url);
        const options = {
            protocol: parsedUrl.protocol,
            host: parsedUrl.host,
            method: 'GET',
            path: parsedUrl.pathname
        };
        FollowRedirects.https.get(options, function (response) {
            response.pipe(file);
            file.on('finish', function () {
                file.close();
                callback();
            });
        }).on('error', function (error) {
            callback(error);
        });
    }
}
exports.TeamsDevAppDownloader = TeamsDevAppDownloader;
