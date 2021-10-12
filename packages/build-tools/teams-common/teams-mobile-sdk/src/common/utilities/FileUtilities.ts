/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import * as Archiver from 'archiver';
import * as FileSystem from 'fs-extra';

export class FileUtilities {
  public static ensureEmptyDirectory (dirPath: string): Promise<void> {
    return new Promise<void>((resolve, reject) => {
      FileSystem.emptyDir(dirPath, FileUtilities.promiseCallback(resolve, reject));
    });
  }

  public static writeJson (filePath: string, object: any): Promise<void> {
    return new Promise<void>((resolve, reject) => {
      FileSystem.writeJson(filePath, object, FileUtilities.promiseCallback(resolve, reject));
    });
  }

  public static writeJsonSync (filePath: string, object: any) {
    FileSystem.outputJsonSync(filePath, object);
  }

  public static readJson (filePath: string): Promise<any> {
    return new Promise<void>((resolve, reject) => {
      FileSystem.exists(filePath, (exists) => {
        if (exists) {
          FileSystem.readJson(filePath, (error, object) => {
            if (!error) {
              resolve(object);
            } else {
              reject(error);
            }
          });
        } else {
          resolve(undefined);
        }
      });
    });
  }

  public static readJsonSync (filePath: string): any {
    if (!FileSystem.existsSync(filePath)) {
      return undefined;
    }

    return FileSystem.readJSONSync(filePath);
  }

  public static copyDirectory (source: string, destination: string): Promise<void> {
    return new Promise<void>((resolve, reject) => {
      return FileSystem.copy(source, destination, FileUtilities.promiseCallback(resolve, reject));
    });
  }

  public static compressDirectory (sourceDirectory: string, compressedFilePath: string): Promise<void> {
    return new Promise<void>((resolve, reject) => {
      FileSystem.createFile(compressedFilePath, FileUtilities.promiseCallback(resolve, reject));
    }).then(() => {
      const output = FileSystem.createWriteStream(compressedFilePath);
      const archiver = Archiver('zip', { zlib: { level: 9 } });
      archiver.pipe(output);
      archiver.directory(sourceDirectory, false);
      return archiver.finalize();
    });
  }

  public static promiseCallback (resolve, reject) {
    return (error) => {
      if (!error) {
        resolve();
      } else {
        reject(error);
      }
    };
  }
}
