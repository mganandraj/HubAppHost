# Executing network calls
Teams Mobile SDK provides `HttpClient` to execute network calls.

### Creating HTTP requests
HTTP requests must implement the `HttpRequest` interface. Below are the attributes for an HTTP request:

* **requestId:** A unique identifier for this network request. This is used to tag the request and can be used to cancel the request if needed. This is required.
* **serviceName:** The name of the service. Must be specified in CamelCase. Example: GitHub. This is used for tracking the HTTP requests and grouping them by the specified service name. This is required.
* **requestName:** The name of the request. Must be specified in CamelCase. Example: GetUserRepos. This is used for tracking the HTTP requests and grouping them by the specified request name. This is required.
* **method:** The HTTP method to use for this request. Must be one of `HttpMethod.GET`, `HttpMethod.HEAD`, `HttpMethod.POST`, `HttpMethod.PUT`, `HttpMethod.PATCH`, `HttpMethod.DELETE`. This is required.
* **url:** The URL to execute the request to. This is required.
* **headers:** The headers to pass for this request. Must be specified as a dictionary with string keys and values as either a string or an array of string values. This is optional.
* **body:** The payload to send with the request. Applicable to `POST`, `PUT`, `PATCH`, `DELETE` requests. Must be specified as a string. Please JSON stringify objects if passing them as body. This is optional.

### Handling HTTP responses
HTTP response expose following attributes:

* **status:** The HTTP status code. Example: 200, 201, 500, etc.
* **data:** The response body as string.
* **headers:** The response headers as a dictionary with string keys and values as an array of string values.

### Cancelling an HTTP request
```typescript
let requestId = 'some-random-request-id';

// Code to execute call

// Taking too long or don't need to execute this request. Cancel!!
HttpClient.cancel(requestId);
```

### Example (GET)
```typescript
import {
  HttpClient,
  HttpRequest,
  HttpResponse,
  HttpMethod
} from 'teams-mobile-sdk';

let request = {
  requestId: '1',
  serviceName: 'JSONPlaceholder',
  requestName: 'GetPosts',
  method: HttpMethod.GET,
  url: 'https://jsonplaceholder.typicode.com/posts',
  headers: {
    'Accept': 'application/json'
  }
} as HttpRequest;

HttpClient.execute(request)
  .then((response: HttpResponse) => {
    ToastAndroid.show('Received response: status - ' + response.status + ', data - ' + response.data, ToastAndroid.LONG);
  }).catch((error) => {
    ToastAndroid.show('Failed to execute request. Error: ' + error, ToastAndroid.LONG);
  });
```

### Example (POST)
```typescript
let request = {
  requestId: '2',
  serviceName: 'JSONPlaceholder',
  requestName: 'CreatePost',
  method: HttpMethod.POST,
  url: 'https://jsonplaceholder.typicode.com/posts',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify({
    userId: 1,
    id: Date.now(),
    title: 'Sample post',
    body: 'This is a sample post'
  })
} as HttpRequest;

HttpClient.execute(request)
  .then((response: HttpResponse) => {
    ToastAndroid.show('Received response: status - ' + response.status + ', data - ' + response.data, ToastAndroid.LONG);
  }).catch((error) => {
    ToastAndroid.show('Failed to execute request. Error: ' + error, ToastAndroid.LONG);
  });
```
