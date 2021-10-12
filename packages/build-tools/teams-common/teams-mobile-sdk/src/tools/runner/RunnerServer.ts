/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import * as Archiver from 'archiver';
import * as Express from 'express';
import * as FileSystem from 'fs-extra';
import * as Path from 'path';
import { FileUtilities } from '../../common/utilities/FileUtilities';
import { PackageUtils } from '../utils/PackageUtils';

export class RunnerServer {
  private appBuildDirectory: string;
  private expressServer: any;

  constructor () {
    this.appBuildDirectory = Path.resolve('.', 'build');
    this.prepareAppPackage();
    this.expressServer = Express();
    this.expressServer.get('/', this.handleGetRunnerReadyRequest.bind(this));
    this.expressServer.get('/manifest', this.handleGetAppManifestRequest.bind(this));
    this.expressServer.get('/definition', this.handleGetAppDefinitionRequest.bind(this));
    this.expressServer.get('/resources', this.handleGetResources.bind(this));
    this.expressServer.get('/package', this.handleGetPackage.bind(this));
  }

  public start (): void {
    this.expressServer.listen(3333, () => console.log('Runner running on port 3333!'));
  }

  private prepareAppPackage() {
    const archiver = Archiver('zip', { zlib: { level: 9 }});
    const appPackagePath = Path.resolve(this.appBuildDirectory, 'outputs', 'package.zip');

    FileSystem.createFileSync(appPackagePath);
    const oStream = FileSystem.createWriteStream(appPackagePath);
    archiver.pipe(oStream);

    archiver.directory(PackageUtils.buildResourcePath, false);
    archiver.finalize();
  }

  private handleGetRunnerReadyRequest (_: Express.Request, res: Express.Response) {
    res.send('Ready, Set, Go!');
  }

  private handleGetAppManifestRequest (_: Express.Request, res: Express.Response) {
    FileUtilities.readJson(Path.resolve(this.appBuildDirectory, 'resources/manifest/manifest.json'))
      .then((object) => {
        res.json(object);
      }, (error) => {
        console.log(error);
        res.status(500).send('Failed to read manifest.');
      });
  }

    private handleGetAppDefinitionRequest (_: Express.Request, res: Express.Response) {
      const appDefinitionPath = Path.resolve('.', 'appDefinition.json');
      FileSystem.exists(appDefinitionPath, (exists) => {
        if (exists) {
          FileUtilities.readJson(appDefinitionPath)
          .then((object) => {
              res.json(object);
            }, (error) => {
              console.log(error);
              res.status(500).send('Failed to read app definition.');
          });
        } else {
          res.status(500).send('`appDefinition.json` not found. Please add it in root directory.');
        }
      });


  }

  private handleGetResources (_: Express.Request, res: Express.Response) {
    res.download(Path.resolve(this.appBuildDirectory, 'outputs/resources.zip'));
  }

  private handleGetPackage (_: Express.Request, res: Express.Response) {
    res.download(Path.resolve(this.appBuildDirectory, 'outputs/package.zip'));
  }
}
