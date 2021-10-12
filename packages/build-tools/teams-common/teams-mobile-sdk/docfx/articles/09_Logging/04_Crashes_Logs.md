# Crash Logs

In the Event of an unhandled crash 
1. In development mode (using runner app) the stack trace is shown with on a red screen overlay
2. In production/ bundle mode (loaded from codepush or app asset) the teams app will crash with fatal exception.

Below section will show some sample stack traces for 

## 1. Crash In native module codepush

When the Exception occurs in one of the methods on native module invoked by the JS thread

```
2018-12-27 12:04:03.543 9828-9933/com.microsoft.skype.teams.dev E/AndroidRuntime: FATAL EXCEPTION: mqt_native_modules
    Process: com.microsoft.skype.teams.dev, PID: 9828
    java.lang.RuntimeException: Crash in React Native Mobile Module Id : 9a1f0cd2-ff89-443f-9618-01993b1c5ff0 ,Name: teams-mobile-sdk-example, Version: 0.1.16
        at com.microsoft.skype.teams.sdk.SdkApplicationContext$1.handleException(SdkApplicationContext.java:205)
        at com.facebook.react.bridge.CatalystInstanceImpl.onNativeException(CatalystInstanceImpl.java:525)
        at com.facebook.react.bridge.CatalystInstanceImpl.access$1000(CatalystInstanceImpl.java:39)
        at com.facebook.react.bridge.CatalystInstanceImpl$NativeExceptionHandler.handleException(CatalystInstanceImpl.java:541)
        at com.facebook.react.bridge.queue.MessageQueueThreadHandler.dispatchMessage(MessageQueueThreadHandler.java:31)
        at android.os.Looper.loop(Looper.java:193)
        at com.facebook.react.bridge.queue.MessageQueueThreadImpl$3.run(MessageQueueThreadImpl.java:192)
        at java.lang.Thread.run(Thread.java:764)
     Caused by: java.lang.RuntimeException: Could not invoke httpClient.execute
        at com.facebook.react.bridge.JavaMethodWrapper.invoke(JavaMethodWrapper.java:383)
        at com.facebook.react.bridge.JavaModuleWrapper.invoke(JavaModuleWrapper.java:160)
        at com.facebook.react.bridge.queue.NativeRunnable.run(Native Method)
        at android.os.Handler.handleCallback(Handler.java:873)
        at android.os.Handler.dispatchMessage(Handler.java:99)
        at com.facebook.react.bridge.queue.MessageQueueThreadHandler.dispatchMessage(MessageQueueThreadHandler.java:29)
        at android.os.Looper.loop(Looper.java:193) 
        at com.facebook.react.bridge.queue.MessageQueueThreadImpl$3.run(MessageQueueThreadImpl.java:192) 
        at java.lang.Thread.run(Thread.java:764) 
     Caused by: java.lang.reflect.InvocationTargetException
        at java.lang.reflect.Method.invoke(Native Method)
        at com.facebook.react.bridge.JavaMethodWrapper.invoke(JavaMethodWrapper.java:372)
        at com.facebook.react.bridge.JavaModuleWrapper.invoke(JavaModuleWrapper.java:160) 
        at com.facebook.react.bridge.queue.NativeRunnable.run(Native Method) 
        at android.os.Handler.handleCallback(Handler.java:873) 
        at android.os.Handler.dispatchMessage(Handler.java:99) 
        at com.facebook.react.bridge.queue.MessageQueueThreadHandler.dispatchMessage(MessageQueueThreadHandler.java:29) 
        at android.os.Looper.loop(Looper.java:193) 
        at com.facebook.react.bridge.queue.MessageQueueThreadImpl$3.run(MessageQueueThreadImpl.java:192) 
        at java.lang.Thread.run(Thread.java:764) 
     Caused by: java.lang.Error: crash in native code
        at com.microsoft.skype.teams.sdk.react.modules.SdkHttpClientModule.execute(SdkHttpClientModule.java:66)
        at java.lang.reflect.Method.invoke(Native Method) 
        at com.facebook.react.bridge.JavaMethodWrapper.invoke(JavaMethodWrapper.java:372) 
        at com.facebook.react.bridge.JavaModuleWrapper.invoke(JavaModuleWrapper.java:160) 
        at com.facebook.react.bridge.queue.NativeRunnable.run(Native Method) 
        at android.os.Handler.handleCallback(Handler.java:873) 
        at android.os.Handler.dispatchMessage(Handler.java:99) 
        at com.facebook.react.bridge.queue.MessageQueueThreadHandler.dispatchMessage(MessageQueueThreadHandler.java:29) 
        at android.os.Looper.loop(Looper.java:193) 
        at com.facebook.react.bridge.queue.MessageQueueThreadImpl$3.run(MessageQueueThreadImpl.java:192) 
        at java.lang.Thread.run(Thread.java:764) 

```
The Mobile RN SDK is adding metadata about the module details like below to the crash stack trace
``` 
java.lang.RuntimeException: Crash in React Native Mobile Module Id : 9a1f0cd2-ff89-443f-9618-01993b1c5ff0 ,Name: teams-mobile-sdk-example, Version: 0.1.16
```


## 2. Crash in JS code of the RN module

When an Unhandled Exception occurs in the JS code of the RN App

```
2018-12-27 12:05:04.435 10541-10648/com.microsoft.skype.teams.dev E/AndroidRuntime: FATAL EXCEPTION: mqt_native_modules
    Process: com.microsoft.skype.teams.dev, PID: 10541
    java.lang.RuntimeException: Crash in React Native Mobile Module Id : 9a1f0cd2-ff89-443f-9618-01993b1c5ff0 ,Name: teams-mobile-sdk-example, Version: 0.1.16
        at com.microsoft.skype.teams.sdk.SdkApplicationContext$1.handleException(SdkApplicationContext.java:205)
        at com.facebook.react.bridge.CatalystInstanceImpl.onNativeException(CatalystInstanceImpl.java:525)
        at com.facebook.react.bridge.CatalystInstanceImpl.access$1000(CatalystInstanceImpl.java:39)
        at com.facebook.react.bridge.CatalystInstanceImpl$NativeExceptionHandler.handleException(CatalystInstanceImpl.java:541)
        at com.facebook.react.bridge.queue.MessageQueueThreadHandler.dispatchMessage(MessageQueueThreadHandler.java:31)
        at android.os.Looper.loop(Looper.java:193)
        at com.facebook.react.bridge.queue.MessageQueueThreadImpl$3.run(MessageQueueThreadImpl.java:192)
        at java.lang.Thread.run(Thread.java:764)
     Caused by: com.facebook.react.common.JavascriptException: error in JS code by krishna, stack:
    value@568:1058
    touchableHandlePress@193:1584
    _performSideEffectsForTransition@186:8607
    _receiveSignal@186:7371
    touchableHandleResponderRelease@186:4687
    y@90:576
    k@90:719
    P@90:773
    D@90:1940
    F@90:2699
    W@90:2499
    <unknown>@90:14013
    Ie@90:74956
    Oe@90:13683
    We@90:13856
    receiveTouches@90:14676
    value@26:3449
    <unknown>@26:960
    value@26:2703
    value@26:932
    
        at com.facebook.react.modules.core.ExceptionsManagerModule.showOrThrowError(ExceptionsManagerModule.java:54)
        at com.facebook.react.modules.core.ExceptionsManagerModule.reportFatalException(ExceptionsManagerModule.java:38)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.facebook.react.bridge.JavaMethodWrapper.invoke(JavaMethodWrapper.java:372)
        at com.facebook.react.bridge.JavaModuleWrapper.invoke(JavaModuleWrapper.java:160)
        at com.facebook.react.bridge.queue.NativeRunnable.run(Native Method)
        at android.os.Handler.handleCallback(Handler.java:873)
        at android.os.Handler.dispatchMessage(Handler.java:99)
        at com.facebook.react.bridge.queue.MessageQueueThreadHandler.dispatchMessage(MessageQueueThreadHandler.java:29)
        	... 3 more

```

In the stack trace above there are 2 parts
1. Metadata about the module from which the crash occurred

```
java.lang.RuntimeException: Crash in React Native Mobile Module Id : 9a1f0cd2-ff89-443f-9618-01993b1c5ff0 ,Name: teams-mobile-sdk-example, Version: 0.1.16
```

2. The JS stack trace

```
 Caused by: com.facebook.react.common.JavascriptException: error message in JS code, stack:
    value@568:1058
    touchableHandlePress@193:1584
    _performSideEffectsForTransition@186:8607
    _receiveSignal@186:7371
    touchableHandleResponderRelease@186:4687
    y@90:576
    k@90:719
    P@90:773
    D@90:1940
    F@90:2699
    W@90:2499
    <unknown>@90:14013
    Ie@90:74956
    Oe@90:13683
    We@90:13856
    receiveTouches@90:14676
    value@26:3449
    <unknown>@26:960
    value@26:2703
    value@26:932
```
The Stack present here isn't very readable. This is because the Javascript files are bundled into a single file by the RN bundler.
So we need the source maps of the bundled code to reverse map this stack trace to the original file names and line numbers.

Let us briefly discuss about the process of de-obfuscate the stack trace.

In the yarn package-app step we need to generate the source maps for the bundles. This source map, maps the code between the bundle (index.android/ios.bundle) to the generated javascript files.
If we are using typescript in our project we need to save the source map between javascript to Typescript files also.

(Internal details of the SDK)
When packaging the app we need to add below command line args to the cli

Add to sdk/src/tools/utils/PackageUtil.ts

rnAndroidBundleCommandArgs …

'--sourcemap-output', Path.resolve(this.bundleOutputPath, 'sourcemap.android.js')

'--sourcemap-output', Path.resolve(this.bundleOutputPath, 'sourcemap.ios.js')


### Reading the stack trace

We can use a tool like stack-beautifier (https://www.npmjs.com/package/stack-beautifier). 
This is one of the tool I found online. There might better tools to do this job. But, For the purpose of the demo this tool also works fine.

Create an input file with name errorlog and save it along with the source maps file

```
File errorlog

Input

Fatal Exception: com.facebook.react.common.JavascriptException: error in JS code by krishna, stack:
value@568:1058
touchableHandlePress@193:1584
_performSideEffectsForTransition@186:8607
_receiveSignal@186:7371
touchableHandleResponderRelease@186:4687
y@90:576
k@90:719
P@90:773
D@90:1940
F@90:2699
W@90:2499
<unknown>@90:14013
Ie@90:74956
Oe@90:13683
We@90:13856
receiveTouches@90:14676
value@26:3449
<unknown>@26:960
value@26:2703
value@26:932

```

```
cmd line execution

Output
krishnas-MacBook-Pro:26-dec-2_58_pm krchaval$ stack-beautifier -t errorlog sourcemap.android.js 
Fatal Exception: com.facebook.react.common.JavascriptException: error in JS code by krishna, stack:
  at body (./build/src/components/HttpClientComponent.js:58:12)
  at e (./node_modules/react-native/Libraries/Components/Touchable/TouchableNativeFeedback.android.js:187:45)
  at e (./node_modules/react-native/Libraries/Components/Touchable/Touchable.js:803:34)
  at curState (./node_modules/react-native/Libraries/Components/Touchable/Touchable.js:717:44)
  at Signals (./node_modules/react-native/Libraries/Components/Touchable/Touchable.js:435:24)
  at context (./node_modules/react-native/Libraries/Renderer/oss/ReactNativeRenderer-prod.js:48:15)
  at reporter (./node_modules/react-native/Libraries/Renderer/oss/ReactNativeRenderer-prod.js:66:34)
  at this (./node_modules/react-native/Libraries/Renderer/oss/ReactNativeRenderer-prod.js:79:30)
  at type (./node_modules/react-native/Libraries/Renderer/oss/ReactNativeRenderer-prod.js:173:42)
  at e (./node_modules/react-native/Libraries/Renderer/oss/ReactNativeRenderer-prod.js:222:24)
  at cb (./node_modules/react-native/Libraries/Renderer/oss/ReactNativeRenderer-prod.js:206:35)
  at events (./node_modules/react-native/Libraries/Renderer/oss/ReactNativeRenderer-prod.js:1066:26)
  at a (./node_modules/react-native/Libraries/Renderer/oss/ReactNativeRenderer-prod.js:6403:14)
  at fn (./node_modules/react-native/Libraries/Renderer/oss/ReactNativeRenderer-prod.js:1027:31)
  at ./node_modules/react-native/Libraries/Renderer/oss/ReactNativeRenderer-prod.js:1047:17
  at index (./node_modules/react-native/Libraries/Renderer/oss/ReactNativeRenderer-prod.js:1115:30)
  at _lastFlush (./node_modules/react-native/Libraries/BatchedBridge/MessageQueue.js:356:36)
  at ./node_modules/react-native/Libraries/BatchedBridge/MessageQueue.js:118:17
  at DebuggerInternal (./node_modules/react-native/Libraries/BatchedBridge/MessageQueue.js:313:6)
  at this (./node_modules/react-native/Libraries/BatchedBridge/MessageQueue.js:116:4)

```

Now the stack trace is pointing to the right files. 
we can use typescript mappings to further convert these stack traces to the TS files.



