/**
 * @hidden from docs
 *
 * Represents the params and callback passed to openView.
 */
export class ViewParamsBundle {
  public params?: any;
  public onCloseCallback?: (result: any) => void;

  constructor (params?: any, onCloseCallback?: (result: any) => void) {
    this.params = params;
    this.onCloseCallback = onCloseCallback;
  }
}
