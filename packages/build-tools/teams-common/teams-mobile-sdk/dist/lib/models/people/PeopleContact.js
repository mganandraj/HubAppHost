"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.AddressType = exports.PhoneType = exports.ContactType = void 0;
var ContactType;
(function (ContactType) {
    /**
     * INTENANT - Teams and SfbEmail
     */
    ContactType["INTENANT"] = "InTenant";
    /**
     * EXTERNAL - SfbPhone and ExternalEmail and contacts with just names
     */
    ContactType["EXTERNAL"] = "External";
    /**
     * FEDERATEDONPREM - SfbFederatedOnPrem
     */
    ContactType["FEDERATED_ONPREM"] = "FederatedOnPrem";
    /**
     * FEDERATEDONLINE - SfbFederatedOnline
     */
    ContactType["FEDERATED_ONLINE"] = "FederatedOnline";
})(ContactType = exports.ContactType || (exports.ContactType = {}));
var PhoneType;
(function (PhoneType) {
    PhoneType["NONE"] = "None";
    PhoneType["HOME"] = "Home";
    PhoneType["BUSINESS"] = "Business";
    PhoneType["MOBILE"] = "Mobile";
})(PhoneType = exports.PhoneType || (exports.PhoneType = {}));
var AddressType;
(function (AddressType) {
    AddressType["HOME"] = "Home";
    AddressType["BUSINESS"] = "Business";
    AddressType["OTHER"] = "Other";
})(AddressType = exports.AddressType || (exports.AddressType = {}));
