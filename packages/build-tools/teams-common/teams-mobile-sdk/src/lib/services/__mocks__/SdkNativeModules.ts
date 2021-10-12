export const Logger = {
  logVerbose: jest.fn(),
  logInformation: jest.fn(),
  logDebug: jest.fn(),
  logWarning: jest.fn(),
  logError: jest.fn()
};

export const TeamsShellInteractor = {
  setOptionsMenu: jest.fn(),
  invalidateOptionsMenu: jest.fn(),
  showSnackbar: jest.fn(),
  dismissSnackbar: jest.fn(),
  setTitle: jest.fn(),
  closeModule: jest.fn(),
  closeModuleWithResult: jest.fn(),
  setBackgroundColor: jest.fn(),
  showActionSheet: jest.fn(),
  enableFabLayoutAndroid: jest.fn(),
  disableFabLayoutAndroid: jest.fn(),
  setTitleDropdown: jest.fn(),
  registerBackNavigationHandler: jest.fn(),
  removeBackNavigationHandler: jest.fn()
};

export const NavigationService = {
  openView: jest.fn(),
  closeView: jest.fn(),
  openModule: jest.fn(),
  getDeepLinkForModule: jest.fn()
};

export const TeamsMessaging = {
  postMessageWithParams: jest.fn()
}
