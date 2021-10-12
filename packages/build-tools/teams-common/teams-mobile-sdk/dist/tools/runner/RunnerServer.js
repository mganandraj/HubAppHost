"use strict";
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.RunnerServer = void 0;
const Archiver = require("archiver");
const Express = require("express");
const FileSystem = require("fs-extra");
const Path = require("path");
const FileUtilities_1 = require("../../common/utilities/FileUtilities");
const PackageUtils_1 = require("../utils/PackageUtils");
class RunnerServer {
    constructor() {
        this.appBuildDirectory = Path.resolve('.', 'build');
        this.prepareAppPackage();
        this.expressServer = Express();
        this.expressServer.get('/', this.handleGetRunnerReadyRequest.bind(this));
        this.expressServer.get('/manifest', this.handleGetAppManifestRequest.bind(this));
        this.expressServer.get('/definition', this.handleGetAppDefinitionRequest.bind(this));
        this.expressServer.get('/resources', this.handleGetResources.bind(this));
        this.expressServer.get('/package', this.handleGetPackage.bind(this));
    }
    start() {
        this.expressServer.listen(3333, () => console.log('Runner running on port 3333!'));
    }
    prepareAppPackage() {
        const archiver = Archiver('zip', { zlib: { level: 9 } });
        const appPackagePath = Path.resolve(this.appBuildDirectory, 'outputs', 'package.zip');
        FileSystem.createFileSync(appPackagePath);
        const oStream = FileSystem.createWriteStream(appPackagePath);
        archiver.pipe(oStream);
        archiver.directory(PackageUtils_1.PackageUtils.buildResourcePath, false);
        archiver.finalize();
    }
    handleGetRunnerReadyRequest(_, res) {
        res.send('Ready, Set, Go!');
    }
    handleGetAppManifestRequest(_, res) {
        FileUtilities_1.FileUtilities.readJson(Path.resolve(this.appBuildDirectory, 'resources/manifest/manifest.json'))
            .then((object) => {
            res.json(object);
        }, (error) => {
            console.log(error);
            res.status(500).send('Failed to read manifest.');
        });
    }
    handleGetAppDefinitionRequest(_, res) {
        const appDefinitionPath = Path.resolve('.', 'appDefinition.json');
        FileSystem.exists(appDefinitionPath, (exists) => {
            if (exists) {
                FileUtilities_1.FileUtilities.readJson(appDefinitionPath)
                    .then((object) => {
                    res.json(object);
                }, (error) => {
                    console.log(error);
                    res.status(500).send('Failed to read app definition.');
                });
            }
            else {
                res.status(500).send('`appDefinition.json` not found. Please add it in root directory.');
            }
        });
    }
    handleGetResources(_, res) {
        res.download(Path.resolve(this.appBuildDirectory, 'outputs/resources.zip'));
    }
    handleGetPackage(_, res) {
        res.download(Path.resolve(this.appBuildDirectory, 'outputs/package.zip'));
    }
}
exports.RunnerServer = RunnerServer;
