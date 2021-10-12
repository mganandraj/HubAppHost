/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import * as FollowRedirects from 'follow-redirects';
import * as FileSystem from 'fs-extra';
import { RequestOptions } from 'http';
import * as Url from 'url';
import { SdkConfigManager } from '../config/SdkConfigManager';

const appDownloadUrl = 'https://api.appcenter.ms/v0.1/apps/ownerName/appName/releases/latest';
const APP_NAME = 'appName';
const OWNER_NAME = 'ownerName';

export class TeamsDevAppDownloader {
  public static downloadTeamsDevApp(downloadOutputPath: string, platform: string, callback: (error: Error) => any): void {
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

    const userApiToken = SdkConfigManager.getUserApiToken();
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

  private static download(options: RequestOptions, downloadOutputPath: string, callback: (error?: Error) => any) {
    FollowRedirects.https.get(options, function (response) {
      let rawData = '';

      response.setEncoding('utf8');
      response.on('data', (chunk) => { rawData += chunk; });
      
      response.on('end', () => {
        try {
          const parsedData = JSON.parse(rawData);
          TeamsDevAppDownloader.downloadBinary(parsedData.download_url, downloadOutputPath, callback);
        } catch (error) {
          callback(error);
        }
      });
    }).on('error', function (error) {
      callback(error);
    });
  }

  private static downloadBinary(url: string, downloadOutputPath: string, callback: (error?: Error) => any) {
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
