# SharepointFilePreview
Teams Mobile SDK provides `SharepointFilePreview` to preview sharepoint files.

## Methods

### showPreview (fileTitle: string, fileUrl: string, fileType: string, fileDownloadUrl: string);
This method shows preview of sharepoint files.

#### Example

```typescript
  const fileTitle = 'Screen Shot 2018-09-13 at 12.58.21 PM.png';
  const fileUrl = 'https://microsoft.sharepoint.com/teams/MicrosoftTeamsMobileClients/Shared%20Documents/General/Screen%20Shot%202018-09-13%20at%2012.58.21%20PM.png';
  const fileType = 'png';
  const fileDownloadUrl = "https://microsoft.sharepoint.com/teams/MicrosoftTeamsMobileClients//_api/web/GetFileById('98dad65d-9ae2-42de-9156-382e25bea32f')/$value";

 SharepointFilePreview.showPreview(fileTitle, fileUrl, fileType, fileDownloadUrl);
```
