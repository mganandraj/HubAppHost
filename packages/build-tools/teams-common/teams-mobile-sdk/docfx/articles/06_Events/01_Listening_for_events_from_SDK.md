# Listening for events from the SDK

An app can register to listen for events from the SDK using `NativeAppEventEmitter`.

## Events

### onUserSignedOut
This is emitted when the signed user signs out of the Teams app.

#### Example
```typescript
// Add sign out event listener
NativeAppEventEmitter.addListener('onUserSignedOut', (event) => ApplicationContext.getTraceLogger().logDebug(LOG_TAG, 'Sign out event received'));
```

### onProviderRequestCancelled
This is emitted when a provider request is cancelled by native code. When a request is cancelled, Teams will no longer process responses from the request. 

If applicable, remove the event listener once it's no longer needed.

The event provides the following data:

  * **appInstanceId:** Instance ID of cancelled request

#### Example
```typescript
// Add provider request cancellation event listener
NativeAppEventEmitter.addListener('onProviderRequestCancelled', (event) => ApplicationContext.getTraceLogger().logDebug(LOG_TAG, 'Cancelled request ' + event.appInstanceId));
```
