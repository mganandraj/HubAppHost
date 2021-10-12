import { User } from '../models/core/User';
import { ApplicationParams, AppTheme, TelemetryInfo } from '../models/params/ApplicationParams';
import * as LocalizedStrings from '../services/LocalizedStrings';

/**
 * Represents the application context. The current app context can be accessed in a
 * <code><a href='xref:teams-mobile-sdk.TeamsView'>TeamsView</a></code> by calling
 * <code><a href='xref:teams-mobile-sdk.TeamsView.getApplicationContext'>getApplicationContext</a></code>.
 *
 * The context will not change until the Teams app restarts so it is safe to store it after
 * fetching it the first time.
 */
export class ApplicationContext {
  /**
   * @hidden from docs
   * Parame received from native during component init.
   */
  private params: ApplicationParams;

  constructor (appParams: ApplicationParams) {
    this.params = appParams;

    LocalizedStrings.setLocale(this.params.appLocale);
  }

  /**
   * The current theme. There are two themes available: default and dark.
   */
  public getCurrentAppTheme (): AppTheme {
    return this.params.appTheme as AppTheme;
  }

  /**
   * Returns the current localse.
   */
  public getCurrentLocale (): string {
    return this.params.appLocale;
  }

  /**
   * Returns a `User` object representing the current logged in user
   * in the Teams app.
   */
  public getCurrentUser (): User {
    return this.params.currentUser;
  }

  /**
   * Returns a `TelemetryInfo` object containing device and teams information.
   */
  public getTelemetryInfo (): TelemetryInfo {
    return this.params.telemetryInfo;
  }

  /**
   * Returns the current sessionId.
   */
  public getCurrentSessionId (): string {
    return this.params.sessionId;
  }

  /**
   * Returns the accent color from appDefinition in #RRGGBBB format.
   */
  public getAccentColor (): string {
    return this.params.accentColor;
  }

  /**
   * Updates app parameters associated to the application context
   */
  protected updateAppParams (appParams: ApplicationParams) {
    this.params = appParams;
  }
}

/**
 * @hidden from docs
 * 
 * exposes update method such that hidden from developers.
 */
class ApplicationContextPrivate extends ApplicationContext {

  constructor (appParams: ApplicationParams) {
    super(appParams);
  }

  public updateAppParams(appParams: ApplicationParams) {
    super.updateAppParams(appParams);
  }

}


/**
 * @hidden from docs
 *
 * Manages the singleton `ApplicationContext` instance.
 */
export class ApplicationContextManager {
  private static appContext?: ApplicationContextPrivate;

  public static initializeOrUpdate (appParams: ApplicationParams) {
    if (this.appContext === undefined) {
      this.appContext = new ApplicationContextPrivate(appParams);
    } else {
      this.appContext.updateAppParams(appParams);
    }
  }

  public static getInstance (): ApplicationContext {
    return this.appContext!;
  }
}
