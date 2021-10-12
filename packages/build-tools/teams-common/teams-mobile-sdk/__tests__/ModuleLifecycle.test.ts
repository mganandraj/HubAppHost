import { NativeAppEventEmitter } from 'react-native';
import { IModuleLifecycleHandler, ModuleLifecycle } from '../src/lib/services/ModuleLifecycle';

const Events = {
  ON_USER_SIGNED_OUT: 'onUserSignedOut'
};

describe('Module lifecycle events', () => {
  const dummyHandler: IModuleLifecycleHandler = {
    onUserSignedOut: jest.fn()
  };

  beforeEach(() => {
    ModuleLifecycle.registerHandler(dummyHandler);
  });

  test('should have only one subscriber for native events', () => {
    // Act
    ModuleLifecycle.registerHandler({ onUserSignedOut: jest.fn() });
    ModuleLifecycle.registerHandler({ onUserSignedOut: jest.fn() });
    ModuleLifecycle.registerHandler({ onUserSignedOut: jest.fn() });

    // Assert
    expect(NativeAppEventEmitter.listeners(Events.ON_USER_SIGNED_OUT).length).toEqual(1);
  });

  test ('should handle onUserSignedOut', () => {
    // Act
    NativeAppEventEmitter.emit(Events.ON_USER_SIGNED_OUT);

    // Assert
    expect(dummyHandler.onUserSignedOut).toHaveBeenCalledTimes(1);
    expect(dummyHandler.onUserSignedOut).toHaveBeenCalledWith(undefined);
  });
});
