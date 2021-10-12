const token = 'token';
const invalidConfigKey = 'invalidConfigKey';
const setKey = 'setKey';
const initialValue = 'initialValue';
const newValue = 'newValue';
const removeKey = 'removeKey';
const configObject = {userApiToken: token, setKey: initialValue, removeKey: initialValue};

jest.doMock('fs-extra', () => ({
  existsSync: jest.fn().mockReturnValue(true),
  readJSONSync: jest.fn().mockReturnValue(configObject),
  outputJsonSync: jest.fn()
}));

import { SdkConfigManager } from '../src/tools/config/SdkConfigManager';

it('should get user API token', () => {
  expect(SdkConfigManager.getUserApiToken()).toMatch(token);
});

it('should throw an error if config key is empty', () => {
  expect(function () { SdkConfigManager.get(''); }).toThrow(Error);
});

it('should return undefined if config key is invalid', () => {
  expect(SdkConfigManager.get(invalidConfigKey)).toBeUndefined();
});

it('should return entire config object', () => {
  expect(SdkConfigManager.getAll()).toMatchObject(configObject);
});

it('should set value corresponding to key to a new value', () => {
  expect(SdkConfigManager.get(setKey)).toMatch(initialValue);
  SdkConfigManager.set(setKey, newValue);
  expect(SdkConfigManager.get(setKey)).toMatch(newValue);
});

it('should remove value corresponding to key', () => {
  expect(SdkConfigManager.get(removeKey)).toMatch(initialValue);
  SdkConfigManager.remove(removeKey);
  expect(SdkConfigManager.get(removeKey)).toBeUndefined();
});
