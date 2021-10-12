jest.mock('fs-extra', () => ({
  existsSync: jest.fn(),
  copySync: jest.fn()
}));

console = {
  error: jest.fn(),
  info: jest.fn(),
  warn: jest.fn()
} as any as Console;

const createLocalizedStrings = require('../src/tools/build/localization/LocalizationBuilder');
const numLangs = 44;
const directory = './';

import { copySync, existsSync } from 'fs-extra';
import * as gulp from 'gulp';
import { Stream } from 'stream';

class DummyStream extends Stream.Duplex {
  constructor (options: any) {
    super(options);
  }

  _read (size: number) {
    // do nothing
    this.push(null);
  }

  _write (chunk: any, encoding: any, callback: any) {
    callback();
  }

  _final (callback) {
    callback();
  }

  _destroy (callback) {
    callback();
  }
}

it('should call console.error with the message \'No strings defined. You need to declare your string resources inside resources/strings folder.\'', () => {
  (existsSync as jest.Mock).mockReturnValueOnce(false);
  createLocalizedStrings(directory);
  expect(console.error).toHaveBeenCalledWith('No strings defined. You need to declare your string resources inside resources/strings folder.');
});

it('should call console.error with the message \'Cannot find strings_en.json file. Must define string resources in default locale, i.e. en.\'', () => {
  (existsSync as jest.Mock).mockReturnValueOnce(true);
  createLocalizedStrings(directory);
  expect(console.error).toHaveBeenCalledWith('Cannot find strings_en.json file. Must define string resources in default locale, i.e. en.');
});

it('should create localized strings', () => {
  const srcSpy = jest.spyOn(gulp, 'src');
  const destSpy = jest.spyOn(gulp, 'dest');
  const mockStream = new DummyStream({ emitClose: false });
  srcSpy.mockReturnValue(mockStream);
  destSpy.mockReturnValue(mockStream);
  (existsSync as jest.Mock).mockReturnValueOnce(true).mockReturnValueOnce(true);

  createLocalizedStrings(directory);

  expect(copySync as jest.Mock).toHaveBeenCalledTimes(numLangs);
  expect(console.warn).toHaveBeenCalledTimes(numLangs);
});
