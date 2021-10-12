const json = {name: 'test'};
const filename = 'test.json';
const directory = './';

jest.doMock('fs-extra', () => ({
  emptyDir: jest.fn((dirPath, callback) => callback()),
  writeJson: jest.fn((filePath, object, callback) => callback()),
  outputJsonSync: jest.fn(),
  readJson: jest.fn((filePath, callback) => callback(undefined, json)),
  readJSONSync: jest.fn().mockReturnValue(json),
  exists: jest.fn((filePath, callback) => callback(true)),
  existsSync: jest.fn().mockReturnValue(true),
  copy: jest.fn((source, destination, callback) => callback()),
  createFile: jest.fn((filePath, callback) => callback()),
  createWriteStream: jest.fn().mockReturnValue('')
}));

jest.mock('archiver', () => {
  return jest.fn().mockImplementation(() => {
    return {
      pipe: jest.fn(),
      directory: jest.fn(),
      finalize: jest.fn()
    };
  });
});

import { FileUtilities } from '../src/common/utilities/FileUtilities';

it('should ensure empty directory', () => {
  expect.assertions(1);
  return expect(FileUtilities.ensureEmptyDirectory(directory)).resolves.toEqual(undefined);
});

it('should write to a .json file asynchronously', () => {
  expect.assertions(1);
  return expect(FileUtilities.writeJson(filename, json)).resolves.toEqual(undefined);
});

it('should write to a .json file synchronously', () => {
  expect(FileUtilities.writeJsonSync(filename, json)).toEqual(undefined);
});

it('should read a .json file asynchronously', () => {
  expect.assertions(1);
  return expect(FileUtilities.readJson(filename)).resolves.toEqual(json);
});

it('should read a .json file synchronously', () => {
  expect(FileUtilities.readJsonSync(filename)).toMatchObject(json);
});

it('should copy directory', () => {
  expect.assertions(1);
  return expect(FileUtilities.copyDirectory(directory, directory)).resolves.toEqual(undefined);
});

it('should compress directory', () => {
  expect.assertions(1);
  return expect(FileUtilities.compressDirectory(directory, directory)).resolves.toEqual(undefined);
});
