jest.mock('../src/lib/services/SdkNativeModules');

import { HostContext } from '../src/lib/application/HostContext';
import { SnackbarActionSelectedEvent } from '../src/lib/models/events/SnackbarActionSelectedEvent';
import { OptionsMenuItem } from '../src/lib/models/shell/OptionsMenuItem';
import { SnackbarAction } from '../src/lib/models/shell/Snackbar';
import { SnackbarCallback } from '../src/lib/models/shell/SnackbarCallback';
import { TitleDropdownItem } from '../src/lib/models/shell/TitleDropdownItem';
import { TeamsShellInteractor } from '../src/lib/services/SdkNativeModules';
import { TeamsShellInteractionListener } from '../src/lib/shell/TeamsShellInteractionListener';
import { TeamsShellNativeEventsDispatcher } from '../src/lib/shell/TeamsShellNativeEventsDispatcher';
import { TeamsShellPrivate } from '../src/lib/shell/TeamsShellPrivate';

let sut: TeamsShellPrivate; // system under test

const mockTeamsShellNativeEventsDispatcher: TeamsShellNativeEventsDispatcher = {
  registerShell: jest.fn()
} as any as TeamsShellNativeEventsDispatcher;

const hostContext = new HostContext('1123');
let mockTeamsShellInteractionListener: TeamsShellInteractionListener;

beforeAll(() => {
  TeamsShellNativeEventsDispatcher.getInstance = () => {
    return mockTeamsShellNativeEventsDispatcher;
  };
});

beforeEach(() => {
  (mockTeamsShellNativeEventsDispatcher.registerShell as jest.Mock).mockClear();

  mockTeamsShellInteractionListener = {
    getOptionsMenuItems: jest.fn(),
    onOptionsMenuItemSelected: jest.fn(),
    onPrimaryFabClick: jest.fn(),
    onSecondaryFabClick: jest.fn(),
    onTabSelected: jest.fn()
  };

  sut = new TeamsShellPrivate(hostContext, mockTeamsShellInteractionListener);
});

describe('TeamsShell: intialization', () => {
  test('should initialize without errors', () => {
    expect(sut).toBeTruthy();
  });

  test('should register itself with TeamsShellNativeEventsDispatcher', () => {
    expect(mockTeamsShellNativeEventsDispatcher.registerShell).toHaveBeenCalledTimes(1);
    expect(mockTeamsShellNativeEventsDispatcher.registerShell).toHaveBeenCalledWith(hostContext.hostInstanceId, sut);
  });
});

describe('TeamsShell: native events handling', () => {
  test('should implement TeamsShellEventsResponder', () => {
    expect(sut.onOptionsMenuInvalidated).toBeDefined();
    expect(sut.onOptionsMenuItemSelected).toBeDefined();
    expect(sut.onSnackbarActionSelected).toBeDefined();
    expect(sut.onPrimaryFabClick).toBeDefined();
    expect(sut.onSecondaryFabClick).toBeDefined();
  });

  test('should call setOptionsMenu of TeamsShellInteractor when onOptionsMenuInvalidated is received', () => {
    // Arrange
    const menuItems: OptionsMenuItem[] = [
      { id: '1', title: 'Lorem', icon: 'someImage', contentDescription: 'Blah' },
      { id: '2', title: 'Ipsum', icon: 'someImage2', contentDescription: 'Bleh' },
      { id: '3', title: 'Dolor', icon: 'someImage3', contentDescription: 'Bluh' }
    ];
    (mockTeamsShellInteractionListener.getOptionsMenuItems as jest.Mock).mockReturnValue(menuItems);

    // Act
    sut.onOptionsMenuInvalidated({ hostInstanceId: hostContext.hostInstanceId });

    // Assert
    expect(TeamsShellInteractor.setOptionsMenu).toHaveBeenCalledTimes(1);
    expect(TeamsShellInteractor.setOptionsMenu).toHaveBeenCalledWith(hostContext.hostInstanceId, menuItems);
  });

  test('should call listener method when onOptionsMenuItemSelected is received', () => {
    // Arrange
    const selectedOptionsMenuItemId = 'some_menu_item_1';

    // Act
    sut.onOptionsMenuItemSelected({ optionsMenuItemId: selectedOptionsMenuItemId, hostInstanceId: hostContext.hostInstanceId});

    // Assert
    expect(mockTeamsShellInteractionListener.onOptionsMenuItemSelected).toHaveBeenCalledTimes(1);
    expect(mockTeamsShellInteractionListener.onOptionsMenuItemSelected).toHaveBeenCalledWith(selectedOptionsMenuItemId);
  });

  test('should call the registered snackbar callback when onSnackbarActionSelected is received', () => {
    // Arrange
    const snackbarAction: SnackbarAction = {
      id: 'some_action',
      title: 'Dismiss',
      onSelected: jest.fn()
    };
    const snackbarCallbacks = [
      new SnackbarCallback(42, { title: 'Hello World!', action: snackbarAction }),
      new SnackbarCallback(43, { title: 'Goodbye World!' })
    ];
    (sut as any).snackbarCallbacks = snackbarCallbacks;
    const event: SnackbarActionSelectedEvent = {
      snackbarId: 42,
      snackbarActionId: 'some_action',
      hostInstanceId: hostContext.hostInstanceId
    };

    // Act
    sut.onSnackbarActionSelected(event);

    // Assert
    expect(snackbarAction.onSelected).toHaveBeenCalledTimes(1);
  });

  test('should call listener method when onPrimaryFabClick is received', () => {
    // Act
    sut.onPrimaryFabClick({ hostInstanceId: hostContext.hostInstanceId });

    // Assert
    expect(mockTeamsShellInteractionListener.onPrimaryFabClick).toHaveBeenCalledTimes(1);
  });

  test('should call listener method when onPrimaryFabClick is received', () => {
    // Arrange
    const buttonId = 'button_1';

    // Act
    sut.onSecondaryFabClick({ buttonId: buttonId, hostInstanceId: hostContext.hostInstanceId });

    // Assert
    expect(mockTeamsShellInteractionListener.onSecondaryFabClick).toHaveBeenCalledTimes(1);
    expect(mockTeamsShellInteractionListener.onSecondaryFabClick).toHaveBeenCalledWith(buttonId);
  });
});

describe('TeamsShell: setTitleDropdown tests', () => {
  const titleDropdownItems: TitleDropdownItem[] = [
    {
      id: 'opt1',
      title: 'hello'
    },
    {
      id: 'opt2',
      title: 'goodbye'
    }
  ];

  test('setTitleDropdown should invoke TeamsShellInterator', () => {
    // Act
    sut.setTitleDropdown(titleDropdownItems, () => {
      // do nothing
    });

    // Assert
    expect(TeamsShellInteractor.setTitleDropdown).toHaveBeenCalledTimes(1);
    expect(TeamsShellInteractor.setTitleDropdown).toHaveBeenCalledWith(hostContext.hostInstanceId, titleDropdownItems);
  });

  test('should call the registered handler when a title dropdown is selected', () => {
    // Arrange
    const handler = jest.fn();
    const selectedItemId = 'some_id';

    // Act
    sut.setTitleDropdown(titleDropdownItems, handler);
    sut.onTitleDropdownItemSelected({ hostInstanceId: hostContext.hostInstanceId, selectedItemId: selectedItemId});

    // Assert
    expect(handler).toHaveBeenCalledTimes(1);
    expect(handler).toHaveBeenCalledWith(selectedItemId);
  });
});

describe('TeamsShell: back navigation handler tests', () => {
  test('registerBackNavigationHandler should invoke TeamsShellInteractor', () => {
    // Arrange
    const mockHandler = jest.fn();

    // Act
    sut.registerBackNavigationHandler(mockHandler);

    // Assert
    expect(TeamsShellInteractor.registerBackNavigationHandler).toHaveBeenCalledTimes(1);
    expect(TeamsShellInteractor.registerBackNavigationHandler).toHaveBeenCalledWith(hostContext.hostInstanceId);
  });

  test('should invoke the registered handler when user initiates back navigation', () => {
    // Arrange
    const mockHandler = jest.fn();

    // Act
    sut.registerBackNavigationHandler(mockHandler);
    sut.onBackNavigationInitiated({ hostInstanceId: hostContext.hostInstanceId });

    // Assert
    expect(mockHandler).toHaveBeenCalledTimes(1);
  });

  test('removeBackNavigationHandler should invoke TeamsShellInteractor', () => {
    // Act
    sut.removeBackNavigationHandler();

    // Assert
    expect(TeamsShellInteractor.removeBackNavigationHandler).toHaveBeenCalledTimes(1);
    expect(TeamsShellInteractor.removeBackNavigationHandler).toHaveBeenCalledWith(hostContext.hostInstanceId);
  });

  test('removeBackNavigationHandler should remove the handler', () => {
    // Arrange
    const mockHandler = jest.fn();

    // Act
    sut.registerBackNavigationHandler(mockHandler);
    sut.removeBackNavigationHandler();
    sut.onBackNavigationInitiated({ hostInstanceId: hostContext.hostInstanceId });

    // Assert
    expect(mockHandler).not.toHaveBeenCalled();
  });
});
