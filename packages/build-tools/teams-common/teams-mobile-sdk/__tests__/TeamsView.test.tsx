jest.mock('../src/lib/services/SdkNativeModules');
jest.mock('../src/lib/navigation/NavigationManager');

import * as React from 'react';
import { Text, View } from 'react-native';
import * as renderer from 'react-test-renderer';
import { ApplicationContext } from '../src/lib/application/ApplicationContext';
import { HostContext } from '../src/lib/application/HostContext';
import { TeamsView } from '../src/lib/components/TeamsViewComponent';
import { ApplicationParams, AppTheme, HostParams } from '../src/lib/models/params/ApplicationParams';
import { HostResult } from '../src/lib/navigation/HostResult';
import { NavigationManager } from '../src/lib/navigation/NavigationManager';
import { ViewParamsBundle } from '../src/lib/navigation/ViewParamsBundle';
import * as LocalizedStrings from '../src/lib/services/LocalizedStrings';
import { NavigationService } from '../src/lib/services/SdkNativeModules';

describe('initialization', () => {
  class TestView extends TeamsView<any, any> {
    public render () {
      return <View><Text>Hello</Text></View>;
    }
  }

  let currentInstance: TestView;

  const mockAppParams: ApplicationParams = {
    appLocale: 'en-US',
    currentUser: {
      displayName: 'John Doe',
      principalName: 'jdoe@example.org',
      aadId: 'abc',
      skypeMri: '8:orgidxyz',
      tenantId: 'abc-def',
      jobTitle: 'Do Nothinger',
      personalSharePointSiteUrl: 'https://blah.sharepoint.com/jdoe'
    },
    appTheme: AppTheme.DARK
  } as ApplicationParams;

  const mockHostParams: HostParams = {
    hostInstanceId: 'xyz'
  };

  beforeAll(() => {
    LocalizedStrings.initialize({
      getSupportedLocaleStrings (): any {
        const temp = [];
        temp['en-US'] = {};
        temp['en-CA'] = {};
        temp['es-MX'] = {};
        return temp;
      },
      getDefaultLocaleStrings (): any {
        return ['en-US'];
      }
    });
    currentInstance = renderer.create(<TestView _hostParams={mockHostParams} appParams={mockAppParams} />).root.instance as TestView;
  });

  test('view should render without errors', () => {
    expect(currentInstance).toBeTruthy();
  });

  test('should initialize ApplicationContext', () => {
    const appContext: ApplicationContext = currentInstance.getApplicationContext();
    expect(appContext).toBeTruthy();
    expect(appContext.getCurrentLocale()).toEqual(mockAppParams.appLocale);
    expect(appContext.getCurrentAppTheme()).toEqual(mockAppParams.appTheme);
    expect(appContext.getCurrentUser()).toEqual(mockAppParams.currentUser);
  });

  test('should initialize host context', () => {
    const hostContext: HostContext = (currentInstance as any).hostContext;
    expect(hostContext).toBeTruthy();
    expect(hostContext.hostInstanceId).toEqual(mockHostParams.hostInstanceId);
  });

  test('should initialize TeamsShell instance', () => {
    expect(currentInstance.getTeamsShell()).toBeTruthy();
  });

  test('should initialize EventLogger', () => {
    expect(currentInstance.getEventLogger()).toBeTruthy();
  });

  /**
   * Helper function used by view params initialize tests.
   *
   * Renders `TestView` by passing the given `params`. `navigationManager` is made available as the
   * `NavigationManager` instance in the context of the test view.
   *
   * @param navigationManager navigation manager to use
   * @param params params to pass to the test view
   */
  const renderTestViewAndRetreiveViewParams = (navigationManager: NavigationManager, params: any): any => {
    (NavigationManager.getInstance as jest.Mock).mockReturnValue(navigationManager);
    currentInstance = renderer.create(
      <TestView
        _hostParams={mockHostParams}
        appParams={mockAppParams}
        _params={params} />).root.instance as TestView;
    return currentInstance.getViewParams();
  };

  test('should initialize view params when it can be retrieved from NavigationManager', () => {
    const params: any = {
      name: 'Jane Doe',
      email: 'jane2@example.org'
    };

    const dummyParamsKey: string = 'dummyKey';

    const mockNavigationManager = {
      retrieveViewParams: (viewParamsKey: string) => {
        expect(viewParamsKey).toEqual(dummyParamsKey);
        return new ViewParamsBundle(params);
      }
    } as NavigationManager;

    expect(renderTestViewAndRetreiveViewParams(mockNavigationManager, dummyParamsKey)).toEqual(params);
  });

  test('should initialize view params when it cannot be retrieved from NavigationManager', () => {
    const aNumber: number = 42;
    const anObject: any = {
      name: 'Jane Doe',
      email: 'jane@example.org'
    };
    const nonJsonString: string = 'some_dummy_data';
    const jsonString: string = JSON.stringify(anObject);

    const mockNavigationManager = {
      retrieveViewParams: (viewParamsKey: string) => {
        return undefined; // to simulate key not found in store
      }
    } as NavigationManager;

    expect(renderTestViewAndRetreiveViewParams(mockNavigationManager, aNumber)).toEqual(aNumber);
    expect(renderTestViewAndRetreiveViewParams(mockNavigationManager, anObject)).toEqual(anObject);
    expect(renderTestViewAndRetreiveViewParams(mockNavigationManager, nonJsonString)).toEqual(nonJsonString);
    expect(renderTestViewAndRetreiveViewParams(mockNavigationManager, jsonString)).toEqual(anObject);
  });
});

describe('navigation methods', () => {
  class TestView extends TeamsView<any, any> {
    public render () {
      return <View><Text>hello</Text></View>;
    }
  }

  let currentInstance: TestView;

  const mockAppParams: ApplicationParams = {
    appLocale: 'en-US',
    currentUser: {
      displayName: 'jdoe',
      principalName: 'jdoe@example.org',
      aadId: 'abc',
      skypeMri: '8:orgidxyz',
      tenantId: 'abc-def',
      jobTitle: 'Do Nothinger',
      personalSharePointSiteUrl: 'https://blah.sharepoint.com/jdoe'
    },
    appTheme: AppTheme.DARK
  } as ApplicationParams;

  const mockHostParams: HostParams = {
    hostInstanceId: 'xyz'
  };

  const mockViewParams = {
    params: {
      name: 'Jane'
    },
    onCloseCallback: () => {
      // nothing
    }
  };

  const NavigationManagerMock = (NavigationManager as any as jest.Mock<NavigationManager>);

  beforeAll(() => {
    LocalizedStrings.initialize({
      getSupportedLocaleStrings (): any {
        const temp = [];
        temp['en-US'] = {};
        temp['en-CA'] = {};
        temp['es-MX'] = {};
        return temp;
      },
      getDefaultLocaleStrings (): any {
        return ['en-US'];
      }
    });
  });

  beforeEach(() => {
    NavigationManagerMock.mockClear();

    const mockNavigationManager = new NavigationManager();
    mockNavigationManager.retrieveViewParams = jest.fn().mockReturnValue(new ViewParamsBundle(mockViewParams.params, mockViewParams.onCloseCallback));
    (NavigationManager.getInstance as jest.Mock).mockReturnValue(mockNavigationManager);

    currentInstance = renderer.create(<TestView _hostParams={mockHostParams} appParams={mockAppParams} _params='dummy'/>).root.instance as TestView;
  });

  test('openView should invoke corresponding NavigationManager method', () => {
    currentInstance.openView('view1', {name: 'John'});

    const mockInstance = NavigationManagerMock.mock.instances[0];
    expect(mockInstance.openView).toHaveBeenCalledTimes(1);
    expect(mockInstance.openView).toHaveBeenCalledWith('view1', {name: 'John'});
  });

  test('openViewForResult should invoke corresponding NavigationManager method', () => {
    const callback = (result: any) => {
      // do nothing
    };

    currentInstance.openViewForResult('view2', {name: 'Jane'}, callback);
    const mockInstance = NavigationManagerMock.mock.instances[0];
    expect(mockInstance.openViewForResult).toHaveBeenCalledTimes(1);
    expect(mockInstance.openViewForResult).toHaveBeenCalledWith('view2', {name: 'Jane'}, callback);
  });

  test('closeView should invoke corresponding NavigationManager method', () => {
    currentInstance.closeView(false);
    const mockInstance = NavigationManagerMock.mock.instances[0];
    expect(mockInstance.closeView).toHaveBeenCalledTimes(1);
    expect(mockInstance.closeView).toHaveBeenCalledWith(mockHostParams.hostInstanceId, false);
  });

  test('closeView should clean up view params from NavigationManager', () => {
    currentInstance.closeView(false);
    const mockInstance = NavigationManagerMock.mock.instances[0];
    expect(mockInstance.deleteViewParams).toHaveBeenCalledTimes(1);
    expect(mockInstance.deleteViewParams).toHaveBeenCalledWith('dummy');
  });

  test('closeViewWithResult should invoke corresponding NavigationManager method', () => {
    const result = {
      name: 'John'
    };

    const expectedHostResult: HostResult = new HostResult(result, mockViewParams.onCloseCallback);
    currentInstance.closeViewWithResult(result, false);

    const mockInstance = NavigationManagerMock.mock.instances[0];
    expect(mockInstance.closeViewForResult).toHaveBeenCalledTimes(1);
    expect(mockInstance.closeViewForResult).toHaveBeenCalledWith(mockHostParams.hostInstanceId, expectedHostResult, false);
  });

  test('closeViewWithResult should clean up view params from NavigationManager', () => {
    const result = {
      name: 'John'
    };

    const expectedHostResult: HostResult = new HostResult(result, mockViewParams.onCloseCallback);
    currentInstance.closeViewWithResult(result, false);

    const mockInstance = NavigationManagerMock.mock.instances[0];
    expect(mockInstance.deleteViewParams).toHaveBeenCalledTimes(1);
    expect(mockInstance.deleteViewParams).toHaveBeenCalledWith('dummy');
  });

  test('closeViewWithResult should not call NavigationService if callback is not available', () => {
    NavigationManagerMock.mockClear();

    const mockNavigationManager = new NavigationManager();
    mockNavigationManager.retrieveViewParams = jest.fn().mockReturnValue(new ViewParamsBundle(mockViewParams.params));
    (NavigationManager.getInstance as jest.Mock).mockReturnValue(mockNavigationManager);

    currentInstance = renderer.create(<TestView _hostParams={mockHostParams} appParams={mockAppParams} _params='dummy'/>).root.instance as TestView;

    const result = {
      name: 'John'
    };

    currentInstance.closeViewWithResult(result, false);

    const mockInstance = NavigationManagerMock.mock.instances[0];
    expect(mockInstance.closeViewForResult).not.toBeCalled();
  });

  test('openModule should call NavigationService method with stringified params', () => {
    // Arrange
    const params = {
      name: 'John Doe'
    };
    const moduleId = 'abc';

    // Act
    currentInstance.openModule(moduleId, params);

    // Assert
    expect(NavigationService.openModule).toHaveBeenCalledTimes(1);
    expect(NavigationService.openModule).toHaveBeenCalledWith(moduleId, JSON.stringify(params));
  });
});
