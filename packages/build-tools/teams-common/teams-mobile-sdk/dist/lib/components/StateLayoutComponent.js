"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.StateLayout = exports.ViewStateType = void 0;
const react_native_1 = require("react-native");
var ViewStateType;
(function (ViewStateType) {
    ViewStateType[ViewStateType["LOADING"] = 0] = "LOADING";
    ViewStateType[ViewStateType["EMPTY"] = 1] = "EMPTY";
    ViewStateType[ViewStateType["AVAILABLE"] = 2] = "AVAILABLE";
    ViewStateType[ViewStateType["ERROR"] = 3] = "ERROR";
})(ViewStateType = exports.ViewStateType || (exports.ViewStateType = {}));
// The name of the native component should not be changed. It is defined in the
// view managers in the native code and the values need to match.
exports.StateLayout = (0, react_native_1.requireNativeComponent)('StateLayout');
