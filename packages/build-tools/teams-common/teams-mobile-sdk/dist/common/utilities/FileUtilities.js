"use strict";
/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.FileUtilities = void 0;
const Archiver = require("archiver");
const FileSystem = require("fs-extra");
class FileUtilities {
    static ensureEmptyDirectory(dirPath) {
        return new Promise((resolve, reject) => {
            FileSystem.emptyDir(dirPath, FileUtilities.promiseCallback(resolve, reject));
        });
    }
    static writeJson(filePath, object) {
        return new Promise((resolve, reject) => {
            FileSystem.writeJson(filePath, object, FileUtilities.promiseCallback(resolve, reject));
        });
    }
    static writeJsonSync(filePath, object) {
        FileSystem.outputJsonSync(filePath, object);
    }
    static readJson(filePath) {
        return new Promise((resolve, reject) => {
            FileSystem.exists(filePath, (exists) => {
                if (exists) {
                    FileSystem.readJson(filePath, (error, object) => {
                        if (!error) {
                            resolve(object);
                        }
                        else {
                            reject(error);
                        }
                    });
                }
                else {
                    resolve(undefined);
                }
            });
        });
    }
    static readJsonSync(filePath) {
        if (!FileSystem.existsSync(filePath)) {
            return undefined;
        }
        return FileSystem.readJSONSync(filePath);
    }
    static copyDirectory(source, destination) {
        return new Promise((resolve, reject) => {
            return FileSystem.copy(source, destination, FileUtilities.promiseCallback(resolve, reject));
        });
    }
    static compressDirectory(sourceDirectory, compressedFilePath) {
        return new Promise((resolve, reject) => {
            FileSystem.createFile(compressedFilePath, FileUtilities.promiseCallback(resolve, reject));
        }).then(() => {
            const output = FileSystem.createWriteStream(compressedFilePath);
            const archiver = Archiver('zip', { zlib: { level: 9 } });
            archiver.pipe(output);
            archiver.directory(sourceDirectory, false);
            return archiver.finalize();
        });
    }
    static promiseCallback(resolve, reject) {
        return (error) => {
            if (!error) {
                resolve();
            }
            else {
                reject(error);
            }
        };
    }
}
exports.FileUtilities = FileUtilities;
