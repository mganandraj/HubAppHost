export enum ContactCardItemTemplate {
  Hero,
  SingleValue,
  MultiValue,
  SectionHeader,
  EditButton
}

export enum ContactCardItemValueType {
  Text,
  PhoneNumber,
  Link
}

export enum ContactCardAction {
  OpenChat,
  StartAudioCall,
  StartVideoCall
}

export interface ContactCardItemValue {
  displayText: string;
  valueType: ContactCardItemValueType;
  contentDescription?: string;
}

export abstract class ContactCardLinkItemValue implements ContactCardItemValue {
  public displayText: string;
  public valueType: ContactCardItemValueType;
  public linkUrl: string;

  constructor (displayText: string, linkUrl: string) {
    this.displayText = displayText;
    this.linkUrl = linkUrl;
    this.valueType = ContactCardItemValueType.Link;
  }
}

export abstract class ContactCardItem {
  public abstract getTemplate (): ContactCardItemTemplate;
  public abstract getProperties (): ContactCardItem;
}

export class ContactCardSingleValueItem extends ContactCardItem {
  public header: string;
  public value: ContactCardItemValue;

  public getTemplate (): ContactCardItemTemplate {
    return ContactCardItemTemplate.SingleValue;
  }

  public getProperties (): any {
    return this;
  }
}

export class ContactCardMultiValueItem extends ContactCardItem {
  public header: string;
  public values: ContactCardItemValue[];

  public getTemplate (): ContactCardItemTemplate {
    return ContactCardItemTemplate.MultiValue;
  }

  public getProperties (): any {
    return this;
  }
}

export class ContactCardHeroItem extends ContactCardItem {
  public title: string;
  public subTitle: string;
  public avatarUrl?: string;
  public actions: ContactCardAction[];

  public getTemplate (): ContactCardItemTemplate {
    return ContactCardItemTemplate.Hero;
  }

  public getProperties (): any {
    return this;
  }
}

export class ContactCardSectionHeaderItem extends ContactCardItem {
  public header: string;

  public getTemplate (): ContactCardItemTemplate {
    return ContactCardItemTemplate.SectionHeader;
  }

  public getProperties (): any {
    return this;
  }
}

export class ContactCardEditButtonItem extends ContactCardItem {
  public moduleId: string;

  public getTemplate (): ContactCardItemTemplate {
    return ContactCardItemTemplate.EditButton;
  }

  public getProperties (): any {
    return this;
  }
}
