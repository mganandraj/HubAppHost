# DateTimePickerAndroid
Teams Mobile SDK provides `DateTimePickerAndroid` to select Date and Time (Android Only).

## Methods

### showDateTimePickerDialog(params: DateTimeParams): Promise<number>
This method shows dialog to select Date and Time. Dialog can be configured by passing params. If date and time are selected, method returns time in millis. If dialog is cancelled, method returns null.

#### Example

```typescript
      DateTimePickerAndroid.showDateTimePickerDialog(this.state.pickerProps)
        .then((timeInMillis: number) => {
          ToastAndroid.show("Selected Date Time : " + timeInMillis, ToastAndroid.SHORT);
        })
```
