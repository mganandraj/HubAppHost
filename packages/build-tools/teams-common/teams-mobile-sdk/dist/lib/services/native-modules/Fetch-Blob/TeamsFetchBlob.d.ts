import { TeamsFetchBlobReadStream } from './TeamsFetchBlobReadStream';
/**
 * @hidden from docs
 */
export declare class TeamsFetchBlob {
    /**
     * Create file stream from file at `path`.
     * @param  {string} path   The file path.
     * @param  {string} encoding Data encoding, should be one of `base64`, `utf8`, `ascii`
     * @param  {boolean} bufferSize Size of stream buffer.
     * @return {TeamsFetchBlobStream} TeamsFetchBlobStream stream instance.
     */
    readStream(path: string, encoding: string, bufferSize: number): Promise<TeamsFetchBlobReadStream>;
}
