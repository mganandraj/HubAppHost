# Endpoints Provider
Teams Mobile SDK provides `EndpointProvider` to enable access to required endpoint urls.

## Methods

### getMTEndpoint (): Promise&lt;string>
Returns endpoint for middle tier.


#### Example
```typescript
EndpointProvider.getMTEndpoint().then((MTEndpoint) => {
  // do something with MTEndpoint
})
```
See <code>[API docs](xref:teams-mobile-sdk.EndpointProviderInterface)</code> to learn more.
