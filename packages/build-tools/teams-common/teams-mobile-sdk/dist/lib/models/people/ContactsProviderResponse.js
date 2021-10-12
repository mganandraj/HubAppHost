"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ContactSyncState = exports.SortOrder = exports.SortType = exports.ContactsProviderResponseStatus = void 0;
var ContactsProviderResponseStatus;
(function (ContactsProviderResponseStatus) {
    ContactsProviderResponseStatus[ContactsProviderResponseStatus["Success"] = 200] = "Success";
    ContactsProviderResponseStatus[ContactsProviderResponseStatus["UnableToAccessData"] = 301] = "UnableToAccessData";
})(ContactsProviderResponseStatus = exports.ContactsProviderResponseStatus || (exports.ContactsProviderResponseStatus = {}));
var SortType;
(function (SortType) {
    SortType[SortType["Ascending"] = 0] = "Ascending";
    SortType[SortType["Descending"] = 1] = "Descending";
})(SortType = exports.SortType || (exports.SortType = {}));
var SortOrder;
(function (SortOrder) {
    SortOrder[SortOrder["DisplayName"] = 0] = "DisplayName";
    SortOrder[SortOrder["CreationDate"] = 1] = "CreationDate";
})(SortOrder = exports.SortOrder || (exports.SortOrder = {}));
var ContactSyncState;
(function (ContactSyncState) {
    ContactSyncState["INITIAL"] = "initial";
    ContactSyncState["IN_PROGRESS"] = "inProgress";
    ContactSyncState["COMPLETED"] = "completed";
})(ContactSyncState = exports.ContactSyncState || (exports.ContactSyncState = {}));
