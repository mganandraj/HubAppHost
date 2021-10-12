import * as React from 'react';
import {
    Alert,
    Button,
    Image,
    ScrollView,
    StyleSheet,
    Text,
    View
} from 'react-native';

import {
    ImageAndFilePicker,
    ImageAndFilePickerResponse,
    ImageQuality,
    OfficeLensMode,
    Resource,
    TeamsFetchBlob,
    TeamsView
} from 'teams-mobile-sdk';

const styles = StyleSheet.create({
  container: {
    flex: 0.7,
    paddingLeft: 5,
    paddingRight: 5
  },
  margin: {
    margin: 10
  },
  imagePreviewContainerStyle: {
    flexDirection: "row",
    flex: 0.3,
    padding: 5,
    backgroundColor: "black"
  },
  imagePreviewStyle: { flex: 1, resizeMode: "contain", backgroundColor: "gray" },
  imageSizeTextStyle: { flex: 1, color:"white", paddingLeft: 5 }
});

export class ImageAndFilePickerComponent extends TeamsView<any, any> {
  constructor (props: any, state?: any) {
    super(props, state);
    this.state = {
      imagePath: '',
      data : ''
    };
  }

  public render () {
    return (
      <View style={{flexDirection:"column", flex:1}}>
        <ScrollView style ={styles.container}>
          <Button
            onPress={this.takePhoto.bind(this, false)}
            title={Resource.getLocalizedString('imageAndFilePicker_take_photo_public_store')}
          />
          <Button
            onPress={this.takePhoto.bind(this, true)}
            title={Resource.getLocalizedString('imageAndFilePicker_take_photo_private_store')}
          />
          <Button
            onPress={this.takePhotoWithOfficeLens.bind(this, false, false)}
            title={Resource.getLocalizedString('imageAndFilePicker_take_lens_photo_public_store_single_capture')}
          />
          <Button
            onPress={this.takePhotoWithOfficeLens.bind(this, false, true)}
            title={Resource.getLocalizedString('imageAndFilePicker_take_lens_photo_public_store_multiple_capture')}
          />
          <Button
            onPress={this.takePhotoWithOfficeLens.bind(this, true, false)}
            title={Resource.getLocalizedString('imageAndFilePicker_take_lens_photo_private_store_single_capture')}
          />
          <Button
            onPress={this.takePhotoWithOfficeLens.bind(this, true, true)}
            title={Resource.getLocalizedString('imageAndFilePicker_take_lens_photo_private_store_multiple_capture')}
          />
          <Button
            onPress={this.takePhotoWithCustomizedOfficeLens.bind(this, false, false, OfficeLensMode.WHITEBOARD)}
            title={Resource.getLocalizedString('imageAndFilePicker_take_lens_photo_public_store_single_capture_in_whiteboard')}
          />
          <Button
            onPress={this.pickImage.bind(this, false)}
            title={Resource.getLocalizedString('imageAndFilePicker_pick_photo_single_selection')}
          />
          <Button
            onPress={this.pickImage.bind(this, true)}
            title={Resource.getLocalizedString('imageAndFilePicker_pick_photo_multiple_selection')}
          />
          <Button
            onPress={this.pickDocument.bind(this, false)}
            title={Resource.getLocalizedString('imageAndFilePicker_pick_document_single_selection')}
          />
          <Button
            onPress={this.pickDocument.bind(this, true)}
            title={Resource.getLocalizedString('imageAndFilePicker_pick_document_multiple_selection')}
          />
          <Button
            onPress={this.captureImageAndOptimizeForUpload.bind(this, false)}
            title={Resource.getLocalizedString('imageAndFilePicker_capture_image_and_optimise_for_network_upload')}
          />
          <Button
            onPress={this.captureImageAndCompress.bind(this, false, ImageQuality.EXCELLENT)}
            title={Resource.getLocalizedString('imageAndFilePicker_capture_image_and_compress_quality_excellent')}
          />
          <Button
            onPress={this.captureImageAndCompress.bind(this, false, ImageQuality.GOOD)}
            title={Resource.getLocalizedString('imageAndFilePicker_capture_image_and_compress_quality_good')}
          />
          <Button
            onPress={this.captureImageAndCompress.bind(this, false, ImageQuality.MODERATE)}
            title={Resource.getLocalizedString('imageAndFilePicker_capture_image_and_compress_quality_moderate')}
          />
          <Button
            onPress={this.captureImageAndCompress.bind(this, false, ImageQuality.LOW)}
            title={Resource.getLocalizedString('imageAndFilePicker_capture_image_and_compress_quality_low')}
          />
        </ScrollView>
        <View style={styles.imagePreviewContainerStyle}>
          <Image 
            style={styles.imagePreviewStyle}
            source={{ uri: 'data:image/png;base64,' + this.state.data }} />
            <Text style={styles.imageSizeTextStyle}>
            {`Data Size : ${(this.state.data ? this.state.data.length : 0) / 1024} KB`}
            </Text>
          </View>
      </View>
    );
  }

public fetchImage() {
  const fetchBlob = new TeamsFetchBlob();
  fetchBlob.readStream(
    // file path
    this.state.imagePath,
    // encoding, should be one of `base64`, `utf8`, `ascii`
    'base64',
    // (optional) buffer size, default to 4096 (4095 for BASE64 encoded data)
    // when reading file in BASE64 encoding, buffer size must be multiples of 3.
    4095)
  .then((ifstream) => {
    ifstream.open();
    let data = '';
    ifstream.onData((chunk) => {
      // when encoding is `ascii`, chunk will be an array contains numbers
      // otherwise it will be a string
      data += chunk;
      console.log('js data' + chunk);
    });

    ifstream.onError((err) => {
      console.log('oops', err);
    });
    ifstream.onEnd(() => {  
      console.log('success' + data);
      this.setState({
        data: data
      });
    });
});
}

  public handleResponse (response: ImageAndFilePickerResponse) {
    if (response.fileMetadatas) {

      let fileNames = '';
      response.fileMetadatas.map((item) => (
        fileNames = fileNames + ' ' + item.fileName)
      );
this.setState({
imagePath: response.fileMetadatas[0].filePath
});

this.fetchImage();
      Alert.alert(Resource.getLocalizedString('info'), fileNames);
    } else if (response.statusCode) {
      Alert.alert(Resource.getLocalizedString('state_layout_error'),Resource.getLocalizedString('status') + response.statusCode);
    }
  }

  public takePhoto (storeInPrivate: Boolean) {
    ImageAndFilePicker.takePhotoWithDefaultCamera(storeInPrivate)
      .then((response: ImageAndFilePickerResponse) => {
        this.handleResponse(response);
      }).catch((error => {
        Alert.alert(Resource.getLocalizedString('state_layout_error'), error, [{ text: Resource.getLocalizedString('ok') }]);
      }));
  }

  public takePhotoWithOfficeLens (storeInPrivate: Boolean, allowMultipleCapture: Boolean) {
    ImageAndFilePicker.takePhotoWithOfficeLensCamera(storeInPrivate, allowMultipleCapture)
      .then((response: ImageAndFilePickerResponse) => {
        this.handleResponse(response);
      }).catch((error => {
        Alert.alert(Resource.getLocalizedString('state_layout_error'), error, [{ text: Resource.getLocalizedString('ok') }]);
      }));
  }

  public takePhotoWithCustomizedOfficeLens (_storeInPrivate: Boolean, _allowMultipleCapture: Boolean, launchMode?: String) {
    ImageAndFilePicker.takePhotoWithCustomizedOfficeLensCamera(false, false, launchMode)
      .then((response: ImageAndFilePickerResponse) => {
        this.handleResponse(response);
      }).catch((error => {
        Alert.alert(Resource.getLocalizedString('state_layout_error'), error, [{text: Resource.getLocalizedString('ok')}]);
      }));
  }

  public pickImage (allowMultipleSelection: Boolean) {
    ImageAndFilePicker.pickPhotoFromGallery(allowMultipleSelection)
      .then((response: ImageAndFilePickerResponse) => {
        this.handleResponse(response);
      }).catch((error => {
        Alert.alert(Resource.getLocalizedString('state_layout_error'), error, [{ text: Resource.getLocalizedString('ok') }]);
      }));
  }

  public pickDocument (allowMultipleSelection: Boolean) {
    ImageAndFilePicker.pickDocumentFromBrowser(allowMultipleSelection)
      .then((response: ImageAndFilePickerResponse) => {
        this.handleResponse(response);
      }).catch((error => {
        Alert.alert(Resource.getLocalizedString('state_layout_error'), error, [{ text: Resource.getLocalizedString('ok') }]);
      }));
  }

  public captureImageAndOptimizeForUpload (allowMultipleSelection: Boolean) {
    ImageAndFilePicker.takePhotoWithOfficeLensCamera(true, allowMultipleSelection)
      .then((response: ImageAndFilePickerResponse) => {
        if (response.fileMetadatas && response.fileMetadatas.length > 0) {
          ImageAndFilePicker.optimizeImageForNetworkUpload(response.fileMetadatas[0]).then((response: ImageAndFilePickerResponse)=>{
            this.handleResponse(response);
          });
        }
      }).catch((error => {
        Alert.alert(Resource.getLocalizedString('state_layout_error'), error, [{ text: Resource.getLocalizedString('ok') }]);
      }));
  }

  public captureImageAndCompress (allowMultipleSelection: Boolean, targetImageQuality:ImageQuality) {
    ImageAndFilePicker.takePhotoWithOfficeLensCamera(true, allowMultipleSelection)
      .then((response: ImageAndFilePickerResponse) => {
        if (response.fileMetadatas && response.fileMetadatas.length > 0) {
          ImageAndFilePicker.compressImage(response.fileMetadatas[0], targetImageQuality).then((response: ImageAndFilePickerResponse)=>{
            this.handleResponse(response);
          });
        }
      }).catch((error => {
        Alert.alert(Resource.getLocalizedString('state_layout_error'), error, [{ text: Resource.getLocalizedString('ok') }]);
      }));
  }
}
