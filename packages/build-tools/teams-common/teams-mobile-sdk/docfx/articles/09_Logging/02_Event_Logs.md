# Event logs logs

Use `ApplicationContext.getEventLogger()` to log SDK defined events from an app.

## Methods

### logViewReady ()
This method should be used to log when a view has been mounted. This should be done only from the root view components for an app module. This creates a scenario event for the view in Teams' scenario table in ARIA. It is important that apps log this event correctly. Else, there will be incomplete scenarios logged for the app views in Teams' telemetry.

#### Example
```typescript
export class HelloWorldAppComponent extends React.Component<ApplicationComponentProps, any> {
  constructor (props: ApplicationComponentProps) {
    super(props);

    // process the props
    // ....

    // Initialize ApplicationContext
    ApplicationContext.initialize(props.appParams, LocalizedStringsProviderImpl.getInstance());
  }

  componentDidMount () {
    ApplicationContext.getEventLogger().logViewReady();
  }

  render () {
    // render view
    // ...
  }
}
```
