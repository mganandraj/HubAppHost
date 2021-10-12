# Telemetry Client
Teams mobile SDK provides `TelemetryClient` to log userBI and Timed Scenario events to ARIA. TelemetryClient will enable the RN apps/ modules to send feature usage and engineering scenario logs to ARIA Same as teams app.

Below is a sample code snippet

```typescript
1. send a sample user BI event

  let event = {
      eventName: UserBIEventName.PANEL_ACTION,
      moduleName: ' test button',
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

# ARIA Dashboard where events are logged

1. Android
   
  Dev -  https://aria.microsoft.com/eventinspector?tenant=03754ec0677f4b208ff4a89e041fa144
  Dev Kusto explorer - https://portal.aria.ms/kusto-explorer?projectId=03754ec0677f4b208ff4a89e041fa144
  
  Production - https://aria.microsoft.com/eventinspector?tenant=ff1dcc789c59440fbddc3c901af3c6cd
  Production Kusto explorer - https://portal.aria.ms/kusto-explorer?projectId=ff1dcc789c59440fbddc3c901af3c6cd 

2. IOS
   
  Dev - https://aria.microsoft.com/eventinspector?tenant=808845a6f30d4346ad59338a3035c7e5
  Dev kusto explorer - https://portal.aria.ms/kusto-explorer?projectId=808845a6f30d4346ad59338a3035c7e5

  Production - https://aria.microsoft.com/eventinspector?tenant=8f77bb4ba71b494d9bdb459034b2626f
  Production Kusto Explorer - https://portal.aria.ms/kusto-explorer?projectId=8f77bb4ba71b494d9bdb459034b2626f 


# Kusto Queries to access userBI events and scenarios

Note: Some fields are named differently in case of android and ios telemetry events. So we have a different query to summarize data for IOS and Android.

1. Query to fetch scenario events from a mobile module

```
// Android telemetry data

scenario
| where EventInfo_Time > ago(7d)
| where Scenario_Name == "<scenario_name>"
| where InstrumentationSource == "<module_id>"
| extend db = parsejson(Scenario_Databag1)
| extend an = db.appName
| project Scenario_Delta , Scenario_Name, an
```

```
// IOS telemetry data

scenario
| where EventInfo_Time > ago(30d)
| where InstrumentationSource == "<module_id>"
| where Scenario_Name == "<scenario_name>" 
| project Scenario_ScenarioTimeTaken , Scenario_Step , Scenario_Name, InstrumentationSource  , mobileModuleName , mobileModuleType , mobileModuleVersion 
```

2. Query to fetch user BI events from a mobile module

```
// Android and IOS

userbi
| where EventInfo_Time > ago(7d)
| where InstrumentationSource == "<module_id>" 
```

3. Query to fetch usage of app by module id

```
// Note: The replace operations are converting the dataBagProperties field to json string 
// Android telemetry data

userbi
| where EventInfo_Time > ago(15d) 
| where DataBagProperties contains "mobilemodule" 
| extend r1 = replace(':', '":"', DataBagProperties ) 
| extend r2 = replace('{', '{"', r1)
| extend r3 = replace(',', '","', r2)
| extend r4 = replace('}','"}', r3) 
| extend dbJson = parse_json(r4) 
| summarize count() by tostring(dbJson.mobileModuleId)
```



```
// IOS telemetry data

userbi
| where EventInfo_Time > ago(30d)
| where DataBagProperties contains "mobilemodule"
| extend j = parse_json(DataBagProperties)
| summarize count() by tostring(j.tabID)
```

For more info on Kusto query syntax please refer https://docs.microsoft.com/en-us/azure/kusto/query/ 
