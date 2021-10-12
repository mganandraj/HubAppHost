# ImageAndFilePicker
Teams Mobile SDK provides `ImageAndFilePicker` to take photo / pick photo / pick document from device.

## Methods

### takePhotoWithDefaultCamera (storeInPrivate: Boolean): Promise<ImageAndFilePickerResponse>
This method takes photo with default device camera. If camera capability is not available in device, the method will return non supported status code. The default camera shall allow and return file metadata for only one photo.

#### Example

```typescript
  ImageAndFilePicker.takePhotoWithDefaultCamera(false)
      .then((response: ImageAndFilePickerResponse) => {
        this.handleResponse(response);
      }).catch((error => {
        AlertIOS.alert(ApplicationContext.getString('state_layout_error'), error, [{text: ApplicationContext.getString('ok')}]);
      }));
```

### takePhotoWithOfficeLensCamera (storeInPrivate: Boolean, allowMultipleCapture: Boolean): Promise<ImageAndFilePickerResponse>
This method takes photo with office lens camera. 

#### Example

```typescript
  ImageAndFilePicker.takePhotoWithOfficeLensCamera(false, true)
      .then((response: ImageAndFilePickerResponse) => {
        this.handleResponse(response);
      }).catch((error => {
        AlertIOS.alert(ApplicationContext.getString('state_layout_error'), error, [{text: ApplicationContext.getString('ok')}]);
      }));
```

### takePhotoWithCustomizedOfficeLensCamera (storeInPrivate: Boolean, allowMultipleCapture: Boolean, launchMode?: String): Promise<ImageAndFilePickerResponse>
This method takes photo with office lens camera. 

#### Example

```typescript
  ImageAndFilePicker.takePhotoWithCustomizedOfficeLensCamera(false, true, OfficeLensMode.WHITEBOARD)
      .then((response: ImageAndFilePickerResponse) => {
        this.handleResponse(response);
      }).catch((error => {
        AlertIOS.alert(ApplicationContext.getString('state_layout_error'), error, [{text: ApplicationContext.getString('ok')}]);
      }));
```

### pickPhotoFromGallery (allowMultipleSelection: Boolean): Promise<ImageAndFilePickerResponse>
This method picks photo device's photo app. Multiple selection not supported in iOS.

#### Example

```typescript
  ImageAndFilePicker.pickPhotoFromGallery(true)
      .then((response: ImageAndFilePickerResponse) => {
        this.handleResponse(response);
      }).catch((error => {
        AlertIOS.alert(ApplicationContext.getString('state_layout_error'), error, [{text: ApplicationContext.getString('ok')}]);
      }));
```

### pickDocumentFromBrowser (allowMultipleSelection: Boolean): Promise<ImageAndFilePickerResponse>
This method picks document from browser (iCloud, drive, etc.)
  
#### Example

```typescript
  ImageAndFilePicker.pickDocumentFromBrowser(true)
      .then((response: ImageAndFilePickerResponse) => {
        this.handleResponse(response);
      }).catch((error => {
        AlertIOS.alert(ApplicationContext.getString('state_layout_error'), error, [{text: ApplicationContext.getString('ok')}]);
      }));
```
