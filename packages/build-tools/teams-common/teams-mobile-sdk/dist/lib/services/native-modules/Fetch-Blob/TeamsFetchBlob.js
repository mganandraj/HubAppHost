"use strict";
// Copyright 2016 wkh237@github. All rights reserved.
// Use of this source code is governed by a MIT-style license that can be
// found in the LICENSE file.
Object.defineProperty(exports, "__esModule", { value: true });
exports.TeamsFetchBlob = void 0;
const TeamsFetchBlobReadStream_1 = require("./TeamsFetchBlobReadStream");
/**
 * @hidden from docs
 */
class TeamsFetchBlob {
    /**
     * Create file stream from file at `path`.
     * @param  {string} path   The file path.
     * @param  {string} encoding Data encoding, should be one of `base64`, `utf8`, `ascii`
     * @param  {boolean} bufferSize Size of stream buffer.
     * @return {TeamsFetchBlobStream} TeamsFetchBlobStream stream instance.
     */
    readStream(path, encoding, bufferSize) {
        return Promise.resolve(new TeamsFetchBlobReadStream_1.TeamsFetchBlobReadStream(path, encoding, bufferSize));
    }
}
exports.TeamsFetchBlob = TeamsFetchBlob;
