# Application context
The application context can be obtained by invoking the 
<code><a href="xref:teams-mobile-sdk.TeamsView">TeamsView</a></code> instance method 
<code><a href="xref:teams-mobile-sdk.TeamsView.getApplicationContext">getApplicationContext()</a></code>.

See <code><a href="xref:teams-mobile-sdk.ApplicationContext">ApplicationContext</a></code> to learn more about the properties available.

## Example
```ts
import { ApplicationContext, TeamsView } from 'teams-mobile-sdk';

class MyAppView extends TeamsView<any, MyAppViewState> {
    public constructor(props: any, state: MyAppViewState) {
        super(props, state);
        const appCtx: ApplicationContext = this.getApplicationContext();
        // do something with appCtx...
    }

    public render () {
        return (...);
    }
}
```
