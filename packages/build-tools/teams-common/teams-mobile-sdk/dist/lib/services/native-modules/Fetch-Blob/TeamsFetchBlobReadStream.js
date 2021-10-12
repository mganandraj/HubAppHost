"use strict";
// Copyright 2016 wkh237@github. All rights reserved.
// Use of this source code is governed by a MIT-style license that can be
// found in the LICENSE file.
Object.defineProperty(exports, "__esModule", { value: true });
exports.TeamsFetchBlobReadStream = void 0;
const react_native_1 = require("react-native");
const FetchBlob = react_native_1.NativeModules.fetchBlob;
const emitter = react_native_1.DeviceEventEmitter;
const uuid = require('uuid/v4');
/**
 * @hidden from docs
 */
class TeamsFetchBlobReadStream {
    constructor(path, encoding, bufferSize) {
        this.tick = 10;
        if (!path) {
            throw Error('FetchBlob could not open file stream with empty `path`');
        }
        this.encoding = encoding || 'utf8';
        this.bufferSize = bufferSize;
        this.path = path;
        this.closed = false;
        this.streamId = 'RNFBRS' + uuid();
        // register for file stream event
        const subscription = emitter.addListener(this.streamId, (e) => {
            const { event, detail } = e;
            if (this.onDataCallback && event === 'data') {
                this.onDataCallback(detail);
                return;
            }
            else if (this.onEndCallback && event === 'end') {
                this.onEndCallback(detail);
            }
            else {
                if (this.onErrorCallback) {
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
    open() {
        if (!this.closed) {
            FetchBlob.readStream(this.path, this.encoding, this.bufferSize || 10240, this.tick || -1, this.streamId);
        }
        else {
            throw new Error('Stream closed');
        }
    }
    onData(fn) {
        this.onDataCallback = fn;
    }
    onError(fn) {
        this.onErrorCallback = fn;
    }
    onEnd(fn) {
        this.onEndCallback = fn;
    }
}
exports.TeamsFetchBlobReadStream = TeamsFetchBlobReadStream;
