"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ContactCardEditButtonItem = exports.ContactCardSectionHeaderItem = exports.ContactCardHeroItem = exports.ContactCardMultiValueItem = exports.ContactCardSingleValueItem = exports.ContactCardItem = exports.ContactCardLinkItemValue = exports.ContactCardAction = exports.ContactCardItemValueType = exports.ContactCardItemTemplate = void 0;
var ContactCardItemTemplate;
(function (ContactCardItemTemplate) {
    ContactCardItemTemplate[ContactCardItemTemplate["Hero"] = 0] = "Hero";
    ContactCardItemTemplate[ContactCardItemTemplate["SingleValue"] = 1] = "SingleValue";
    ContactCardItemTemplate[ContactCardItemTemplate["MultiValue"] = 2] = "MultiValue";
    ContactCardItemTemplate[ContactCardItemTemplate["SectionHeader"] = 3] = "SectionHeader";
    ContactCardItemTemplate[ContactCardItemTemplate["EditButton"] = 4] = "EditButton";
})(ContactCardItemTemplate = exports.ContactCardItemTemplate || (exports.ContactCardItemTemplate = {}));
var ContactCardItemValueType;
(function (ContactCardItemValueType) {
    ContactCardItemValueType[ContactCardItemValueType["Text"] = 0] = "Text";
    ContactCardItemValueType[ContactCardItemValueType["PhoneNumber"] = 1] = "PhoneNumber";
    ContactCardItemValueType[ContactCardItemValueType["Link"] = 2] = "Link";
})(ContactCardItemValueType = exports.ContactCardItemValueType || (exports.ContactCardItemValueType = {}));
var ContactCardAction;
(function (ContactCardAction) {
    ContactCardAction[ContactCardAction["OpenChat"] = 0] = "OpenChat";
    ContactCardAction[ContactCardAction["StartAudioCall"] = 1] = "StartAudioCall";
    ContactCardAction[ContactCardAction["StartVideoCall"] = 2] = "StartVideoCall";
})(ContactCardAction = exports.ContactCardAction || (exports.ContactCardAction = {}));
class ContactCardLinkItemValue {
    constructor(displayText, linkUrl) {
        this.displayText = displayText;
        this.linkUrl = linkUrl;
        this.valueType = ContactCardItemValueType.Link;
    }
}
exports.ContactCardLinkItemValue = ContactCardLinkItemValue;
class ContactCardItem {
}
exports.ContactCardItem = ContactCardItem;
class ContactCardSingleValueItem extends ContactCardItem {
    getTemplate() {
        return ContactCardItemTemplate.SingleValue;
    }
    getProperties() {
        return this;
    }
}
exports.ContactCardSingleValueItem = ContactCardSingleValueItem;
class ContactCardMultiValueItem extends ContactCardItem {
    getTemplate() {
        return ContactCardItemTemplate.MultiValue;
    }
    getProperties() {
        return this;
    }
}
exports.ContactCardMultiValueItem = ContactCardMultiValueItem;
class ContactCardHeroItem extends ContactCardItem {
    getTemplate() {
        return ContactCardItemTemplate.Hero;
    }
    getProperties() {
        return this;
    }
}
exports.ContactCardHeroItem = ContactCardHeroItem;
class ContactCardSectionHeaderItem extends ContactCardItem {
    getTemplate() {
        return ContactCardItemTemplate.SectionHeader;
    }
    getProperties() {
        return this;
    }
}
exports.ContactCardSectionHeaderItem = ContactCardSectionHeaderItem;
class ContactCardEditButtonItem extends ContactCardItem {
    getTemplate() {
        return ContactCardItemTemplate.EditButton;
    }
    getProperties() {
        return this;
    }
}
exports.ContactCardEditButtonItem = ContactCardEditButtonItem;
