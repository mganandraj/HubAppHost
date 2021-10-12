/**
 * @hidden from docs
 */
export declare class TeamsFetchBlobReadStream {
    private path;
    private encoding;
    private bufferSize;
    private closed;
    private tick;
    private streamId;
    onDataCallback: ((detail: any) => void);
    onErrorCallback: ((detail: any) => void);
    onEndCallback: ((detail: any) => void);
    constructor(path: string, encoding: string, bufferSize: number);
    open(): void;
    onData(fn: (detail: any) => void): void;
    onError(fn: any): void;
    onEnd(fn: any): void;
}
