import * as React from 'react';
import { Button, StyleSheet, View } from 'react-native';
import {
  Resource,
  SharepointFilePreview,
  TeamsView
} from 'teams-mobile-sdk';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20
  },
  margin: {
    margin: 10
  }
});

export class SharepointFilePreviewComponent extends TeamsView<any, any> {
  constructor (props: any, state?: any) {
    super(props, state);
  }

  public render () {
    return (
      <View style={styles.container}>
        <Button
          title={Resource.getLocalizedString('show_file_preview_btn_title')}
          onPress={this.onButtonPressFile.bind(this)}/>
        <View style={styles.margin} />
        <Button
          title={Resource.getLocalizedString('show_image_preview_btn_title')}
          onPress={this.onButtonPressImage.bind(this)}/>
      </View>
    );
  }

  public onButtonPressFile () {
    const fileTitle = 'Asynchronous File Upload_Dev_Design.docx';
    const fileUrl = 'https://microsoft.sharepoint.com/teams/MicrosoftTeamsMobileClients/Shared Documents/Android/DevDesigns_Update29/Asynchronous File Upload_Dev_Design.docx';
    const fileType = 'docx';
    const fileDownloadUrl = "https://microsoft.sharepoint.com/teams/MicrosoftTeamsMobileClients/_api/web/GetFileById('EECBC7FD-7131-4EC9-B1C4-9FC39C5D39A9')/$value";

    this.showPreview(fileTitle, fileUrl, fileType, fileDownloadUrl);
  }

  public onButtonPressImage () {
    const fileTitle = 'Screen Shot 2018-09-13 at 12.58.21 PM.png';
    const fileUrl = 'https://microsoft.sharepoint.com/teams/MicrosoftTeamsMobileClients/Shared%20Documents/General/Screen%20Shot%202018-09-13%20at%2012.58.21%20PM.png';
    const fileType = 'png';
    const fileDownloadUrl = "https://microsoft.sharepoint.com/teams/MicrosoftTeamsMobileClients/_api/web/GetFileById('98dad65d-9ae2-42de-9156-382e25bea32f')/$value";

    this.showPreview(fileTitle, fileUrl, fileType, fileDownloadUrl);
  }

  public showPreview (fileTitle: string, fileUrl: string, fileType: string, fileDownloadUrl: string) {
    SharepointFilePreview.showPreview(fileTitle, fileUrl, fileType, fileDownloadUrl);
  }
}
