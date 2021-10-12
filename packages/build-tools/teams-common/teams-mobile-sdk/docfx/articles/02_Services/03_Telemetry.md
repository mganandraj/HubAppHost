# Telemetry Client
Teams mobile SDK provides <code>[TelemetryClient](xref:teams-mobile-sdk.TelemetryClient)</code> to log userBI and Timed Scenario events to ARIA. 
TelemetryClient will enable the RN apps/ modules to send feature usage and engineering scenario logs to ARIA same as Teams app.

# ARIA Dashboard where events are logged

1. Android
  Dev -  https://aria.microsoft.com/eventinspector?tenant=03754ec0677f4b208ff4a89e041fa144
  Production - https://aria.microsoft.com/eventinspector?tenant=ff1dcc789c59440fbddc3c901af3c6cd

2. IOS
  Dev - https://aria.microsoft.com/eventinspector?tenant=808845a6f30d4346ad59338a3035c7e5
  Production - https://aria.microsoft.com/eventinspector?tenant=8f77bb4ba71b494d9bdb459034b2626f

Below is a sample code snippet

```typescript
1. Send a sample user BI event

  let event = {
      eventName: UserBIEventName.PANEL_ACTION,
      moduleName: 'test button',
      databagProp: { 'a': 'a', 'b': '10', mobileModuleType: 'x' },
      userBIDatabag: { 'a': 'a', 'b': 10, mobileModuleType: 'x', isA: true }
    } as UserBIEvent;
    TelemetryClient.logUserBIEvent(event);

2. Below example starts a scenario, after successfully launching it will launch a child scenario under it. On completion will stop the whole scenario group.

 TelemetryClient.startScenario('clickedfab', { 'a': 'a', 'b': 10, mobileModuleType: 'x', isA: true})
    .then((id: string) => {
      ToastAndroid.show('scenario id' + id, ToastAndroid.SHORT);
      TelemetryClient.startScenarioUnderParent('child scenario', id, { 'x': 'x', 'y': 10, isZ: true}).then((childid: string) => {
        TelemetryClient.endScenarioChainOnIncomplete(childid, 'app_error_code_1', 'errormsg', { 'k': 'k', 'm': 10, isK: true});
        // TelemetryClient.endScenarioChainOnSuccess(childid);
      });
    });
```
