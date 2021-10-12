import * as React from 'react';
import { ApplicationContext, ApplicationContextManager } from '../application/ApplicationContext';
import { HostContext } from '../application/HostContext';
import { ApplicationParams, HostParams, TeamsViewProps } from '../models/params/ApplicationParams';
import { OptionsMenuItem } from '../models/shell/OptionsMenuItem';
import { HostResult } from '../navigation/HostResult';
import { NavigationManager } from '../navigation/NavigationManager';
import { ViewParamsBundle } from '../navigation/ViewParamsBundle';
import { EventLogger } from '../services/diagnostics/EventLogger';
import { Logger, NavigationService } from '../services/SdkNativeModules';
import { TeamsShell } from '../shell/TeamsShell';
import { TeamsShellInteractionListener } from '../shell/TeamsShellInteractionListener';
import { TeamsShellPrivate } from '../shell/TeamsShellPrivate';
import { ViewLifecycleListener } from '../shell/ViewLifecycleListener';
import { ViewLifecycleNativeEventsDispatcher } from '../shell/ViewLifecycleNativeEventsDispatcher';

/**
 * Base class for a component that represents a view in the Teams app.
 *
 * <h4>What is a view?</h4>
 *
 * Each screen of your module is a view! All components
 * that represent a view in the Teams app must extend this class.
 *
 * This class derives from [React.Component](https://reactjs.org/docs/react-component.html)
 * and thus can be used exactly the same way. The generic type parameters needed
 * here are the ones that you would normally supply to `React.Component`.
 *
 * This class performs initialization this is required for some of the other Teams SDK
 * functionality to work. So, make sure to always invoke the super class methods in the
 * constructor and any of the React component lifecycle methods.
 *
 * > [!CAUTION]
 * > Only the top level components that represent a view should extend `TeamsView`. Extending it in
 * > nested components will cause undefined behaviour. Even if it works today, it might not
 * > work in future releases of the SDK and should not be relied upon.
 *
 * <h4>Navigation</h4>
 *
 * `TeamsView` provides access to all the navigation operations like opening a
 * new view, closing current view etc. See the following methods for more info:
 * - [openView](xref:teams-mobile-sdk.TeamsView.closeModule)
 * - [openViewForResult](xref:teams-mobile-sdk.TeamsView.openViewForResult)
 * - [closeView](xref:teams-mobile-sdk.TeamsView.closeView)
 * - [closeViewWithResult](xref:teams-mobile-sdk.TeamsView.closeViewWithResult)
 * - [openModule](xref:teams-mobile-sdk.TeamsView.openModule)
 * - [closeModule](xref:teams-mobile-sdk.TeamsView.closeModule)
 *
 * <h4>Interacting with the Teams shell</h4>
 *
 * `TeamsView` also provides the ability to modify the "Teams shell" in which the
 * current view exists. This includes ability to set buttons in the navigation bar,
 * ability to show/hide a floating action button layout, ability to show action
 * sheets and more. You can get an instance of the Teams shell using [[getTeamsShell]].
 *
 * See [TeamsShell](xref:teams-mobile-sdk.TeamsShell) to know more.
 *
 * <h4>Example usage</h4>
 *
 * ```ts
 * class MyCustomView extends TeamsView<MyCustomViewProps, MyCustomViewState> {
 *   constructor (props: MyCustomViewProps, state?: MyCustomViewState) {
 *     super (props, state); // always call super!
 *     // your initialization goes here
 *   }
 *   ...
 *   public goToNextView () {
 *     this.openView('nextViewIdentifier', {
 *         id: '1',
 *         name: 'John Doe'
 *     });
 *     // and similarly you can use closeView and openModule/closeModule
 *   }
 *   ...
 *   private showFab () {
 *     // First, get an instance of the teams shell.
 *     const teamsShell: TeamsShell = this.getTeamsShell();
 *
 *     // Now, let's show the fab layout
 *     teamsShell.showFabLayoutAndroid({ ... });
 *   }
 * }
 * ```
 *
 * @typeparam P  Indicates props type.
 * @typeparam S  Indicates state type.
 * @typeparam SS  ?.
 */
export class TeamsView<P = {}, S = {}, SS = any> extends React.Component<P, S, SS>
                                                 implements TeamsShellInteractionListener, ViewLifecycleListener {
  private readonly LOG_TAG: string;
  private hostContext: HostContext;
  private teamsShell: TeamsShellPrivate;
  private navigator: NavigationManager;
  private viewParams?: ViewParamsBundle;
  private eventLogger: EventLogger;

  constructor (props: P, state?: S) {
    super(props, state);

    this.LOG_TAG = this.constructor.name;

    const teamsProps = (props as any) as TeamsViewProps;
    const appParams: ApplicationParams = teamsProps.appParams;

    this.initializeApplicationContext(appParams);
    this.initializeHostContext(teamsProps._hostParams);

    this.navigator = NavigationManager.getInstance();
    this.initializeViewParams(teamsProps._params);

    this.teamsShell = new TeamsShellPrivate(this.hostContext, this);
    this.eventLogger = new EventLogger(this.hostContext.hostInstanceId);
    
    ViewLifecycleNativeEventsDispatcher.getInstance().registerView(this.hostContext.hostInstanceId, this);
  }

  public componentWillUnmount (): void {
    this.teamsShell.destroy();
    ViewLifecycleNativeEventsDispatcher.getInstance().deregisterView(this.hostContext.hostInstanceId, this);
  }

  /**
   * Gets the current application context. The application context is available
   * at the time of view creation so it is safe to call this method any time after
   * the super class constructor has been invoked.
   *
   * @returns the current application context.
   *
   * @see ApplicationContext for more information.
   */
  public getApplicationContext (): ApplicationContext {
    return ApplicationContextManager.getInstance();
  }

  /**
   * Gets the `TeamsShell` instance that can be used in the current view's context
   * It is safe to call this method anytime after the super class constructor has
   * been invoked.
   *
   * @returns `TeamsShell` instance for the current view
   */
  public getTeamsShell (): TeamsShell {
    return this.teamsShell as TeamsShell;
  }

  /**
   * Returns an `EventLogger` that can be used to send events to iOS/Android native.
   * It is safe to call this method anytime after the super class constructor has
   * finished executing. It is safe to store the object returned and use it later.
   *
   * @returns a usable `EventLogger` instance.
   */
  public getEventLogger (): EventLogger {
    return this.eventLogger;
  }

  /*------------------------------ NAVIGATION ------------------------------*/

  /**
   * Gets the parameters that were passed to this view (if any) casted to the
   * specified type. The returned value will be `undefined` if no parameters
   * were passed to this view. Check the value before using.
   *
   * These parameters can come from various sources:
   * 1. openView calls: When a view opens another view within the same module by
   *    calling `openView` or `openViewForResult`, these parameters represent the
   *    params passed to those functions.
   * 2. openModule calls: When a module opens another module using openModule
   *    params will be passed to the default view of the target module.
   * 3. Deep links: When a module opens another module via a deep link the params
   *    are passed to the default view of the target module.
   *
   * The parameters are available at the time of view creation so it is safe
   * to call this method any time after the super class constructor is invoked.
   *
   * @returns the parameters passed to this view as type `T`.
   *          `undefined` if no parameters were passed to this view.
   */
  public getViewParams<T> (): T | undefined {
    if (this.viewParams && this.viewParams.params) {
      return this.viewParams.params as T;
    }

    return undefined;
  }

  /**
   * Pushes a new view with the view name as viewId on the navigation stack.
   * If view is not found, first view in the view list defined in manifest is pushed.
   *
   * @param viewId Name of the view.
   * @param params Params to pass to new view.
   */
  public openView<T> (viewId: string, params: T): void {
    this.navigator.openView<T>(viewId, params);
  }

  /**
   * Pushes a new view with the given viewId on the navigation stack (i.e. on top of the
   * current view). If view is not found, first view in the view list defined in manifest
   * is pushed. Callback called with the returned result when the view is closed.
   *
   * The contents of `params` are sent across the bridge to the native. So, be mindful of the
   * data type limitations of the bridge while choosing the type of data you want
   * to send as parameters to the new view.
   *
   * @param viewId Name of the view (as defined in the manifest).
   * @param params Parameters to pass to new view.
   * @param callback function to be called when the view that is being opened is closed.
   */
  public openViewForResult<T, R> (viewId: string, params: T, callback: (closeHostResult: R) => void): void {
    this.navigator.openViewForResult<T, R>(viewId, params, callback);
  }

  /**
   * Closes the current view with or without animation.
   *
   * @param animated To close the view with animation or not. `true` by default.
   */
  public closeView (animated?: boolean): void {
    this.navigator.closeView(this.hostContext.hostInstanceId, animated);
    this.navigator.deleteViewParams((this.props as any as TeamsViewProps)._params);
  }

  /**
   * Closes the current view and returns a result to the previous view.
   * The result is passed on to the callback that was specified when calling
   * `openViewForResult(...)`.
   *
   * These result object is sent across the bridge to the native. So, be mindful of the
   * data type limitations of the bridge while choosing the type of data you want
   * to send as result.
   *
   * @param result Result to return to the previous view.
   * @param animated To close the view with animation or not. `true` by default.
   */
  public closeViewWithResult<R> (result: R, animated?: boolean): void {
    if (this.viewParams && this.viewParams.onCloseCallback) {
      const hostResult = new HostResult(result, this.viewParams.onCloseCallback);
      this.navigator.closeViewForResult(this.hostContext.hostInstanceId, hostResult, animated);
      this.navigator.deleteViewParams((this.props as any as TeamsViewProps)._params);
    } else {
      Logger.logWarning(this.LOG_TAG, `closeViewWithResult was called but no callback was available.
                                      The current view will not be closed. Did you mean to use closeView()?`);
    }
  }

  /**
   * Opens a module with the given ID. The `params` provided are passed to the newly
   * opened module. `params` will be converted to JSON before sending over the bridge
   * using `JSON.stringify()`.
   *
   * @param {string} moduleId ID of the module to be opened
   * @param {any} params parameters to be passed to the newly opened module.
   */
  public openModule<T> (moduleId: string, params: T): void {
    NavigationService.openModule(moduleId, JSON.stringify(params));
  }

  /**
   * Closes the current module.
   *
   * @param success whether the current module successfully completed its operation
   */
  public closeModule (success?: boolean) {
    this.teamsShell.closeModule(success);
  }

  /* --------------- TeamsShellInteractionListener --------------- */

  /**
   * This method will be called by Teams shell to get the options menu items.
   * Override this if your view needs to display these items. If you want
   * the Teams shell to redraw these items invoke `invalidateOptionsMenu()`.
   *
   * The base class implementation of this method returns an empty array.
   *
   * @returns An array of options menu items to be displayed.
   */
  public getOptionsMenuItems (): OptionsMenuItem[] {
    return [];
  }

  /**
   * This method will be called whenever the user selects an options menu item.
   * Override this if you need to handle it.
   *
   * The base class implementation is empty.
   *
   * @param selectedMenuItemId ID of the options menu item that was selected
   */
  public onOptionsMenuItemSelected (_: string): void {
    // override
  }

  /**
   * This method will be called whenever user selects a tab in the app bar/navigation bar.
   * Override this to handle tab selection.
   *
   * The base class implementation throws error for not implementing it, this will ensure tab selection is handled properly.
   *
   * @param tabId ID of the tab that was selected
   */
  public onTabSelected (_: string): void {
    throw new Error("Method not implemented.");
  }

  /**
   * [ANDROID ONLY]
   * This method will be called when the user clicks on the primary Fab. Override
   * this if you have a FAB in your view.
   *
   * The base class implementation is empty.
   */
  public onPrimaryFabClick (): void {
    // override
  }

  /**
   * [ANDROID ONLY]
   * This method will be called when the user clicks on the primary FAB. Override
   * this if you have a secondary FAB in your view.
   *
   * The base class implementation is empty.
   *
   * @param buttonId ID of the secondary fab that was clicked
   */
  public onSecondaryFabClick (_: string): void {
    // override
  }

  /* --------------- ViewLifecycleListener --------------- */

  /**
   * This method will be called when the view comes into focus. Override
   * this if you want to perform some action when view appears.
   *
   * The base class implementation is empty.
   */
  public onViewAppear (): void {
    // override
  }

  private initializeApplicationContext (appParams: ApplicationParams): void {
    if (appParams) {
      ApplicationContextManager.initializeOrUpdate(appParams);
    } else {
      Logger.logWarning(this.LOG_TAG, `TeamsView didn't receive app context! If you are trying to render a
                                      TeamsView inside another, please make sure you are passing all the
                                      props correctly.`);
    }
  }

  private initializeHostContext (hostParams: HostParams): void {
    if (hostParams) {
      this.hostContext = new HostContext(hostParams.hostInstanceId);
    } else {
      Logger.logWarning(this.LOG_TAG, `TeamsView didn't receive host info! If you are trying to render a
                                      TeamsView inside another, please make sure you are passing all the
                                      props correctly.`);
    }
  }

  private initializeViewParams (rawViewParams: any): void {
    if (rawViewParams && typeof rawViewParams === 'string') {
      Logger.logInformation(this.LOG_TAG, 'Received params of type string. Attempting to retrieve from NavigationManager.');
      this.viewParams = this.navigator.retrieveViewParams(rawViewParams);
      if (!this.viewParams) {
        Logger.logInformation(this.LOG_TAG, 'Unable to retreive params from NavigationManager. Trying to parse as JSON.');
        try {
          const parsedParams = JSON.parse(rawViewParams);
          this.viewParams = new ViewParamsBundle(parsedParams);
        } catch (e) {
          Logger.logInformation(this.LOG_TAG, 'Failed to parse _params. Invalid JSON. Using the raw value itself.');
          this.viewParams = new ViewParamsBundle(rawViewParams);
        }
      } else {
        Logger.logInformation(this.LOG_TAG, 'Successfully retrieved view params from NavigationManager.');
      }
    } else if (rawViewParams) {
      Logger.logInformation(this.LOG_TAG, `Non string _params. Using as-is.`);
      this.viewParams = new ViewParamsBundle(rawViewParams);
    }
  }
}
