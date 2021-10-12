import { User } from '../models/core/User';
import { ApplicationParams, AppTheme, TelemetryInfo } from '../models/params/ApplicationParams';
/**
 * Represents the application context. The current app context can be accessed in a
 * <code><a href='xref:teams-mobile-sdk.TeamsView'>TeamsView</a></code> by calling
 * <code><a href='xref:teams-mobile-sdk.TeamsView.getApplicationContext'>getApplicationContext</a></code>.
 *
 * The context will not change until the Teams app restarts so it is safe to store it after
 * fetching it the first time.
 */
export declare class ApplicationContext {
    /**
     * @hidden from docs
     * Parame received from native during component init.
     */
    private params;
    constructor(appParams: ApplicationParams);
    /**
     * The current theme. There are two themes available: default and dark.
     */
    getCurrentAppTheme(): AppTheme;
    /**
     * Returns the current localse.
     */
    getCurrentLocale(): string;
    /**
     * Returns a `User` object representing the current logged in user
     * in the Teams app.
     */
    getCurrentUser(): User;
    /**
     * Returns a `TelemetryInfo` object containing device and teams information.
     */
    getTelemetryInfo(): TelemetryInfo;
    /**
     * Returns the current sessionId.
     */
    getCurrentSessionId(): string;
    /**
     * Returns the accent color from appDefinition in #RRGGBBB format.
     */
    getAccentColor(): string;
    /**
     * Updates app parameters associated to the application context
     */
    protected updateAppParams(appParams: ApplicationParams): void;
}
/**
 * @hidden from docs
 *
 * Manages the singleton `ApplicationContext` instance.
 */
export declare class ApplicationContextManager {
    private static appContext?;
    static initializeOrUpdate(appParams: ApplicationParams): void;
    static getInstance(): ApplicationContext;
}
