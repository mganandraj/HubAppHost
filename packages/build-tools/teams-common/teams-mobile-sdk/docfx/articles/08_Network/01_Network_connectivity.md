# Network connectivity
Teams Mobile SDK provides `NetworkConnectivityManager` to check the current network status.

## Methods
### isNetworkAvailable (): Promise<boolean>
Returns if network is currently available.

#### Example
```typescript
NetworkConnectivityManager.isNetworkAvailable()
    .then((result: boolean) => {
        ToastAndroid.show(
        result ? ApplicationContext.getString('get_network_availability_available_message') : ApplicationContext.getString('get_network_availability_unavailable_message'),
        ToastAndroid.LONG);
    }).catch((error) => {
        ApplicationContext.getTraceLogger().logError(LOG_TAG, error)
        ToastAndroid.show(
        ApplicationContext.getString('get_network_availability_failure_message'),
        ToastAndroid.LONG);
    });
```
