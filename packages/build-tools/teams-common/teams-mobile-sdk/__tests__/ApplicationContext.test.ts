
import 'jest';
import { ApplicationContext } from '../src/lib/application/ApplicationContext';
import { User } from '../src/lib/models/core/User';
import { AppTheme } from '../src/lib/models/params/ApplicationParams';
import * as LocalizedStrings from '../src/lib/services/LocalizedStrings';

describe('ApplicationContext', () => {
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

  test('should throw if appParams is invalid', () => {
    expect(() => { const a = new ApplicationContext(undefined as any); }).toThrow();
    expect(() => { const a = new ApplicationContext(null as any); }).toThrow();
  });

  test('should throw if any of the properties is null or undefined', () => {
    expect(() => { const a = new ApplicationContext({ appLocale: undefined as any, currentUser: {} as User, appTheme: AppTheme.DARK }); }).toThrow();
    expect(() => { const a = new ApplicationContext({ appLocale: 'en-US', currentUser: undefined as any, appTheme: AppTheme.DARK }); }).toThrow();
    expect(() => { const a = new ApplicationContext({ appLocale: 'en-in', currentUser: {} as User, appTheme: undefined as any }); }).toThrow();
  });

  test('should initialize without errors with valid parameters', () => {
    expect(new ApplicationContext({ appLocale: 'en-in', currentUser: {} as User, appTheme: AppTheme.DEFAULT })).toBeTruthy();
  });

  test('getCurrentLocale should return current locale', () => {
    const user = {
      displayName: 'John Doe'
    } as User;
    const appContext = new ApplicationContext({ appLocale: 'en-in', currentUser: user, appTheme: AppTheme.DEFAULT });

    expect(appContext.getCurrentLocale()).toEqual('en-in');
  });

  test('getCurrentAppTheme should return current theme', () => {
    const user = {
      displayName: 'John Doe'
    } as User;
    const appContext = new ApplicationContext({ appLocale: 'en-in', currentUser: user, appTheme: AppTheme.DEFAULT });

    expect(appContext.getCurrentAppTheme()).toEqual(AppTheme.DEFAULT);
  });

  test('getCurrentUser should return current user', () => {
    const user = {
      displayName: 'John Doe'
    } as User;
    const appContext = new ApplicationContext({ appLocale: 'en-in', currentUser: user, appTheme: AppTheme.DEFAULT });

    expect(appContext.getCurrentUser()).toBeTruthy();
    expect(appContext.getCurrentUser().displayName).toEqual(user.displayName);
  });
});
