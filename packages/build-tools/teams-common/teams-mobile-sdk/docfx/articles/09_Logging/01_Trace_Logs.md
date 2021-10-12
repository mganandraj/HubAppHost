# Trace logs

Use `ApplicationContext.getTraceLogger()` to log traces. There are several different priority levels:

* `logVerbose(tag: string, message: string)`
* `logDebug(tag: string, message: string)`
* `logInformation(tag: string, message: string)`
* `logWarning(tag: string, message: string)`
* `logError(tag: string, message: string)`

In the above methods, `tag` can be used to group related traces together.


**Note:** `console.log`, `console.info`, `console.error` outputs are not intercepted by the trace logger and are not recommended.

## How to see trace logs?
* **Android:** Run `adb logcat *:S SdkAppLogs:V`
* **iOS:** Select `Debug → Open System Log...` in the iOS Simulator
