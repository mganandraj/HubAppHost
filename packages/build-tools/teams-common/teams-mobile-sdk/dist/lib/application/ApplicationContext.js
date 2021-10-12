"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ApplicationContextManager = exports.ApplicationContext = void 0;
const LocalizedStrings = require("../services/LocalizedStrings");
/**
 * Represents the application context. The current app context can be accessed in a
 * <code><a href='xref:teams-mobile-sdk.TeamsView'>TeamsView</a></code> by calling
 * <code><a href='xref:teams-mobile-sdk.TeamsView.getApplicationContext'>getApplicationContext</a></code>.
 *
 * The context will not change until the Teams app restarts so it is safe to store it after
 * fetching it the first time.
 */
class ApplicationContext {
    constructor(appParams) {
        this.params = appParams;
        LocalizedStrings.setLocale(this.params.appLocale);
    }
    /**
     * The current theme. There are two themes available: default and dark.
     */
    getCurrentAppTheme() {
        return this.params.appTheme;
    }
    /**
     * Returns the current localse.
     */
    getCurrentLocale() {
        return this.params.appLocale;
    }
    /**
     * Returns a `User` object representing the current logged in user
     * in the Teams app.
     */
    getCurrentUser() {
        return this.params.currentUser;
    }
    /**
     * Returns a `TelemetryInfo` object containing device and teams information.
     */
    getTelemetryInfo() {
        return this.params.telemetryInfo;
    }
    /**
     * Returns the current sessionId.
     */
    getCurrentSessionId() {
        return this.params.sessionId;
    }
    /**
     * Returns the accent color from appDefinition in #RRGGBBB format.
     */
    getAccentColor() {
        return this.params.accentColor;
    }
    /**
     * Updates app parameters associated to the application context
     */
    updateAppParams(appParams) {
        this.params = appParams;
    }
}
exports.ApplicationContext = ApplicationContext;
/**
 * @hidden from docs
 *
 * exposes update method such that hidden from developers.
 */
class ApplicationContextPrivate extends ApplicationContext {
    constructor(appParams) {
        super(appParams);
    }
    updateAppParams(appParams) {
        super.updateAppParams(appParams);
    }
}
/**
 * @hidden from docs
 *
 * Manages the singleton `ApplicationContext` instance.
 */
class ApplicationContextManager {
    static initializeOrUpdate(appParams) {
        if (this.appContext === undefined) {
            this.appContext = new ApplicationContextPrivate(appParams);
        }
        else {
            this.appContext.updateAppParams(appParams);
        }
    }
    static getInstance() {
        return this.appContext;
    }
}
exports.ApplicationContextManager = ApplicationContextManager;
