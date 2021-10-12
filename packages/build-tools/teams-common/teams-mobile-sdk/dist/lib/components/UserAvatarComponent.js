"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.UserAvatar = exports.UserIdType = void 0;
const react_native_1 = require("react-native");
exports.UserIdType = {
    Unknown: 'unknown',
    AadUpn: 'aadUpn',
    AadObjectId: 'aadObjectId',
    Mri: 'mri',
    DeviceContactId: 'deviceContactId',
    PhoneNumber: 'phoneNumber',
    ThreadId: 'threadId'
};
// The name of the native component should not be changed. It is defined in the
// view managers in the native code and the values need to match.
exports.UserAvatar = (0, react_native_1.requireNativeComponent)('UserAvatar');
