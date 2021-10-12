export declare enum ContactCardItemTemplate {
    Hero = 0,
    SingleValue = 1,
    MultiValue = 2,
    SectionHeader = 3,
    EditButton = 4
}
export declare enum ContactCardItemValueType {
    Text = 0,
    PhoneNumber = 1,
    Link = 2
}
export declare enum ContactCardAction {
    OpenChat = 0,
    StartAudioCall = 1,
    StartVideoCall = 2
}
export interface ContactCardItemValue {
    displayText: string;
    valueType: ContactCardItemValueType;
    contentDescription?: string;
}
export declare abstract class ContactCardLinkItemValue implements ContactCardItemValue {
    displayText: string;
    valueType: ContactCardItemValueType;
    linkUrl: string;
    constructor(displayText: string, linkUrl: string);
}
export declare abstract class ContactCardItem {
    abstract getTemplate(): ContactCardItemTemplate;
    abstract getProperties(): ContactCardItem;
}
export declare class ContactCardSingleValueItem extends ContactCardItem {
    header: string;
    value: ContactCardItemValue;
    getTemplate(): ContactCardItemTemplate;
    getProperties(): any;
}
export declare class ContactCardMultiValueItem extends ContactCardItem {
    header: string;
    values: ContactCardItemValue[];
    getTemplate(): ContactCardItemTemplate;
    getProperties(): any;
}
export declare class ContactCardHeroItem extends ContactCardItem {
    title: string;
    subTitle: string;
    avatarUrl?: string;
    actions: ContactCardAction[];
    getTemplate(): ContactCardItemTemplate;
    getProperties(): any;
}
export declare class ContactCardSectionHeaderItem extends ContactCardItem {
    header: string;
    getTemplate(): ContactCardItemTemplate;
    getProperties(): any;
}
export declare class ContactCardEditButtonItem extends ContactCardItem {
    moduleId: string;
    getTemplate(): ContactCardItemTemplate;
    getProperties(): any;
}
