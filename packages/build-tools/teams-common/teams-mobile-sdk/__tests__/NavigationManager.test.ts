jest.mock('react-native', () => {
  return {
    NativeAppEventEmitter: {
      addListener: jest.fn()
    }
  };
});
jest.mock('uuid/v4');
jest.mock('../src/lib/services/SdkNativeModules');

import { NativeAppEventEmitter } from 'react-native';
import * as uuid from 'uuid/v4';
import { HostResult } from '../src/lib/navigation/HostResult';
import { NavigationManager } from '../src/lib/navigation/NavigationManager';
import { NavigationService } from '../src/lib/services/SdkNativeModules';

let sut: NavigationManager;

beforeEach(() => {
  (NavigationService.openView as jest.Mock).mockClear();
  (NavigationService.closeView as jest.Mock).mockClear();
  sut = NavigationManager.getInstance();
});

describe('NavigationManager: intialization', () => {
  test('should initialize without errors', () => {
    // Assert
    expect(sut).toBeTruthy();
  });

  test('should be a singleton', () => {
    // Arrange
    ((sut as any).results as Map<string, any>).set('hello', {});

    // Act
    const newInstance = NavigationManager.getInstance();

    // Assert
    expect(sut).toEqual(newInstance);
  });

  test('should subscribe to native events', () => {
    expect(NativeAppEventEmitter.addListener).toHaveBeenCalledTimes(1);
    expect(NativeAppEventEmitter.addListener).toHaveBeenCalledWith('onHostClosed', expect.anything());
  });
});

describe('NavigationManager: opening views', () => {
  const params = {
    name: 'John Doe',
    email: 'jdoe@exmaple.org'
  };
  const paramsKey = '123';
  const viewId = 'someView';
  const resultCallback = jest.fn();
  (uuid as jest.Mock).mockReturnValue(paramsKey);

  beforeEach(() => {
    ((sut as any).viewParamsStore as Map<string, any>).clear();
  });

  test('should store params passed to openView', () => {
    // Act
    sut.openView(viewId, params);

    // Assert
    const viewParams = sut.retrieveViewParams(paramsKey);
    expect(viewParams!.params).toBeDefined();
    expect(viewParams!.onCloseCallback).toBeUndefined();
  });

  test('should not store params if they are undefined', () => {
    // Act
    sut.openView(viewId);

    // Assert
    expect(sut.retrieveViewParams(paramsKey)).toBeUndefined();
  });

  test('should store callback passed to openViewForResult', () => {
    // Act
    sut.openViewForResult(viewId, params, resultCallback);

    // Assert
    const viewParams = sut.retrieveViewParams(paramsKey);
    expect(viewParams).toBeTruthy();
    expect(viewParams!.params).toEqual(params);
    expect(viewParams!.onCloseCallback).toEqual(resultCallback);
  });

  test('should invoke NavigationService native module when openView is called', () => {
    // Act
    sut.openView(viewId, params);

    // Assert
    expect(NavigationService.openView).toHaveBeenCalledTimes(1);
    expect(NavigationService.openView).toHaveBeenCalledWith(viewId, paramsKey);
  });

  test('should invoke NavigationService with empty viewParamsKey when no params are set', () => {
    // Act
    sut.openView(viewId);

    // Assert
    expect(NavigationService.openView).toHaveBeenCalledTimes(1);
    expect(NavigationService.openView).toHaveBeenCalledWith(viewId, '');
  });

  test('should invoke NavigationService native module when openViewForResult is called', () => {
    // Act
    sut.openViewForResult(viewId, params, resultCallback);

    // Assert
    expect(NavigationService.openView).toHaveBeenCalledTimes(1);
    expect(NavigationService.openView).toHaveBeenCalledWith(viewId, paramsKey);
  });
});

describe('NavigationManager: closing views', () => {
  const hostInstanceId = 'host-123';
  const result = {
    name: 'Jane Doe',
    email: 'jane@example.org'
  };
  const callback = jest.fn();

  beforeEach(() => {
    callback.mockClear();
    ((sut as any).results as Map<string, any>).clear();
  });

  test('should invoke NavigationService native module when closeView is called', () => {
    // Act
    sut.closeView(hostInstanceId, false);

    // Assert
    expect(NavigationService.closeView).toHaveBeenCalledTimes(1);
    expect(NavigationService.closeView).toHaveBeenCalledWith(hostInstanceId, false);
  });

  test('should animate by default when no parameter provided to closeView', () => {
    // Act
    sut.closeView(hostInstanceId);

    // Assert
    expect(NavigationService.closeView).toHaveBeenCalledTimes(1);
    expect(NavigationService.closeView).toHaveBeenCalledWith(hostInstanceId, true);
  });

  test('should invoke callback with result when onHostClosed received', () => {
    // Act
    (sut as any).setResultForHost(hostInstanceId, new HostResult(result, callback));
    (sut as any).onHostClosed({closedHostInstanceId: hostInstanceId});

    // Assert
    expect(callback).toHaveBeenCalledTimes(1);
    expect(callback).toHaveBeenLastCalledWith(result);
  });

  test('should delete results once they have been retrieved', () => {
    // Act
    (sut as any).setResultForHost(hostInstanceId, new HostResult(result, callback));
    (sut as any).onHostClosed({ closedHostInstanceId: hostInstanceId });

    // Assert
    expect(((sut as any).results as Map<string, any>).size).toEqual(0);
  });

  test('closeViewForResult should store result against hostInstanceId', () => {
    // Arrange
    const hostResult = new HostResult(result, callback);

    // Act
    sut.closeViewForResult(hostInstanceId, hostResult, false);

    // Assert
    const resultsMap = ((sut as any).results as Map<string, any>);
    expect(resultsMap.size).toEqual(1);
    expect(resultsMap.has(hostInstanceId)).toBeTruthy();
  });

  test('should invoke NavigationService native module when closeViewForResult is called', () => {
    // Arrange
    const hostResult = new HostResult(result, callback);

    // Act
    sut.closeViewForResult(hostInstanceId, hostResult, false);

    // Assert
    expect(NavigationService.closeView).toHaveBeenCalledTimes(1);
    expect(NavigationService.closeView).toHaveBeenCalledWith(hostInstanceId, false);
  });

  test('should animate by default when no parameter provided to closeViewForResult', () => {
    // Arrange
    const hostResult = new HostResult(result, callback);

    // Act
    sut.closeViewForResult(hostInstanceId, hostResult);

    // Assert
    expect(NavigationService.closeView).toHaveBeenCalledWith(hostInstanceId, true);
  });
});
