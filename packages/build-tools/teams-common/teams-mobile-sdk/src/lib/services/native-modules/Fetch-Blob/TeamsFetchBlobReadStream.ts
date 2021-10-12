// Copyright 2016 wkh237@github. All rights reserved.
// Use of this source code is governed by a MIT-style license that can be
// found in the LICENSE file.

import {
  DeviceEventEmitter,
  NativeModules
} from 'react-native';

const FetchBlob = NativeModules.fetchBlob;
const emitter = DeviceEventEmitter;
const uuid = require('uuid/v4');

/**
 * @hidden from docs
 */
export class TeamsFetchBlobReadStream {

  private path : string;
  private encoding : string;
  private bufferSize : number;
  private closed : Boolean;
  private tick : number = 10;
  private streamId: string;
  public onDataCallback: ((detail: any) => void);
  public onErrorCallback: ((detail: any) => void);
  public onEndCallback: ((detail: any) => void);

  constructor (path:string, encoding:string, bufferSize: number) {
    if(!path) {
      throw Error('FetchBlob could not open file stream with empty `path`');
    }
    this.encoding = encoding || 'utf8';
    this.bufferSize = bufferSize;
    this.path = path;
    this.closed = false;
    this.streamId = 'RNFBRS'+ uuid();
     // register for file stream event
    const subscription = emitter.addListener(this.streamId, (e) => {
      const {event, detail} = e;
      if(this.onDataCallback && event === 'data') {
        this.onDataCallback(detail);
        return;
      }
      else if (this.onEndCallback && event === 'end') {
        this.onEndCallback(detail);
      }
      else {
        if(this.onErrorCallback) {
          this.onErrorCallback(detail);
        }
        else {
          throw new Error(detail);
        }
      }
      // when stream closed or error, remove event handler
      if (event === 'error' || event === 'end') {
        subscription.remove();
        this.closed = true;
      }
    });

  }

  public open() {
    if(!this.closed) {
      FetchBlob.readStream(this.path, this.encoding, this.bufferSize || 10240 , this.tick || -1, this.streamId);
    }
    else {
      throw new Error('Stream closed');
    }
  }

  public onData(fn:(detail: any) => void) {
    this.onDataCallback = fn;
  }

  public onError(fn) {
    this.onErrorCallback = fn;
  }

  public onEnd (fn) {
    this.onEndCallback = fn;
  }

}
