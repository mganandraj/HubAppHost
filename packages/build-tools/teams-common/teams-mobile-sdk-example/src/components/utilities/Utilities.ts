import {
  Alert,
  Platform,
  ToastAndroid
} from 'react-native';

export class Utilities {
  /**
   * Shows alert for iOS & toast for android
   *
   * @param message A string with the text to toast/alert
   * @param duration The duration of the toast.
   * 
   */
  public static showAlert (message: string, duration?: number) {
      if (Platform.OS === 'android') {
        ToastAndroid.show(message, duration ? duration : ToastAndroid.LONG);
      } else {
        Alert.alert(message);
      }
  }
}
 