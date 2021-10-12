# Authentication Service
Teams Mobile SDK provides <code><a href="xref:teams-mobile-sdk.AuthenticationServiceInterface">AuthenticationService</a></code> to authenticate the user to your application resources.

## Declaring domains
Before fetching a resource token for a domain, the domain must first be declared within the app's manifest.

### Example
```json
{
  "views": [
    // ...
  ],
  "authDomains": [
    "https://graph.microsoft.com"
  ]
}
```

Token requests for domains which are not declared within the manifest will fail.

## Usage
This example acquires the access token for Microsoft Graph.

```typescript
AuthenticationService.getResourceToken('https://graph.microsoft.com')
    .then((accessToken: string) => {
        ApplicationContext.getTraceLogger().logDebug(LOG_TAG, 'Acquired access token.');
    }).catch((error) => {
        ApplicationContext.getTraceLogger().logError(LOG_TAG, 'Failed to acquire token.');
    });
```
