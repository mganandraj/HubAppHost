// Copyright 2016 wkh237@github. All rights reserved.
// Use of this source code is governed by a MIT-style license that can be
// found in the LICENSE file.

import { TeamsFetchBlobReadStream } from './TeamsFetchBlobReadStream';

/**
 * @hidden from docs
 */
export class TeamsFetchBlob {

/**
 * Create file stream from file at `path`.
 * @param  {string} path   The file path.
 * @param  {string} encoding Data encoding, should be one of `base64`, `utf8`, `ascii`
 * @param  {boolean} bufferSize Size of stream buffer.
 * @return {TeamsFetchBlobStream} TeamsFetchBlobStream stream instance.
 */
  public readStream(path : string, encoding : string, bufferSize : number): Promise<TeamsFetchBlobReadStream> {
    return Promise.resolve(new TeamsFetchBlobReadStream(path, encoding, bufferSize));
  }
}

