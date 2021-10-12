# Device Contacts Provider
Teams Mobile SDK provides `DeviceContactsProvider` to enable access to the device's contacts.

## Methods

### getDeviceContacts (): Promise<Array<DeviceContact>>
Returns all contacts from the user's device.

#### Permissions
The necessary permissions to read device contacts will automatically be requested if not already granted. If the permission request is denied, `error['code']` will be `PermissionDenied`.

#### Example
```typescript
DeviceContactsProvider.getDeviceContacts()
    .then((result: Array<DeviceContact>) => {
        let contactsCount = result ? result.length : 0;
        ApplicationContext.getTraceLogger().logDebug(LOG_TAG, 'Found ' + contactsCount + ' device contact(s);');
    }).catch((error) => {
        if (error['code'] === 'PermissionDenied') {
          ApplicationContext.getTraceLogger().logError(LOG_TAG, 'Failed to get contacts permission.');
          return;
        }
        ApplicationContext.getTraceLogger().logError(LOG_TAG, 'Failed to get device contacts.');
        ApplicationContext.getTraceLogger().logError(LOG_TAG, error);
    });
```

See <code>[API docs](xref:teams-mobile-sdk.DeviceContactsProviderInterface)</code> to learn more.
