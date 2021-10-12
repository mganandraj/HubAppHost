/**
 * @hidden from docs
 *
 * Used to store the result returned by a host along
 * with the callback that needs to called when the host
 * closes.
 */
export declare class HostResult {
    result: any;
    callback: (result: any) => void;
    constructor(result: any, callback: (result: any) => void);
}
