/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */
export interface FileMetaData {
  /**
   * Unique identifier for file/image selected from document/image picker
   */
  fileIdentifier: String;

  /**
   * File name without extension for file/image selected from document/image picker
   */
  fileName: String;

  /**
   * File extension for file/image selected from document/image picker
   */
  fileExtension: String;

  /**
   * File extension for file/image selected from document/image picker
   */
  filePath: String;
}

/**
 * Enums to describe image quality
 */
export enum ImageQuality{
  EXCELLENT = 0,
  GOOD = 1,
  MODERATE = 2,
  LOW = 3
}

export interface ImageAndFilePickerResponse {
  /**
   * status code for ImageAndFilePicker request <br>
   * <br>
   * SDKImageAndFilePickerStatusSuccess = 200,                        //Success<br>
   * SDKImageAndFilePickerStatusAccessDenied = 403,                   //Camera access denied<br>
   * SDKImageAndFilePickerStatusCameraNotSupported = 415,             //Camera not supported in device/simulator<br>
   * SDKImageAndFilePickerStatusOfficeLensNotInitialised = 416,       //Office lens failed to initialise<br>
   * SDKImageAndFilePickerStatusDocumentMenuNotInitialised = 417,     //Document menu failed to initialise<br>
   * SDKImageAndFilePickerStatusFailedToWrite = 418,                  //Image failed to write<br>
   * SDKImageAndFilePickerStatusImagePickerFailedFetch = 419,         //Image picker failed to fetch image<br>
   * SDKImageAndFilePickerStatusImagePickerCancelled = 420,           //Image picker operation cancelled<br>
   * SDKImageAndFilePickerStatusOfficeLensCancelled = 421,            //Office lens operation cancelled<br>
   * SDKImageAndFilePickerStatusDocumentProviderCancelled = 422,      //Document Provider cancelled<br>
   * SDKImageAndFilePickerStatusEmptyFilePathOrFileIdentifier = 423,  //Empty image path or identifier<br>
   * SDKImageAndFilePickerStatusUnknown = 500                         //Some unknown error occured<br>
   */
  statusCode: number;

  /**
   * @deprecated
   * Array of unique file Identifiers for file/image selected from document/image picker
   */
  fileIdentifiers: string[];

  /**
   * Array of file metadata for file/image selected from document/image picker
   */
  fileMetadatas: FileMetaData[];
}

export const OfficeLensMode = {
  WHITEBOARD: 'WhiteBoard',
  BUSINESS_CARD: 'BusinessCard',
  DOCUMENT: 'Document',
  PHOTO: 'Photo'
};
