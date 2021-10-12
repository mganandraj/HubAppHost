/**
 * @hidden from docs
 *
 * Used to store the result returned by a host along
 * with the callback that needs to called when the host
 * closes.
 */
export class HostResult {
  public result: any;
  public callback: (result: any) => void;

  constructor (result: any, callback: (result: any) => void) {
    this.result = result;
    this.callback = callback;
  }
}
