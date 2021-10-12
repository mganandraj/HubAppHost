export enum ContactType {
  /**
   * INTENANT - Teams and SfbEmail
   */
  INTENANT = "InTenant",
  /**
   * EXTERNAL - SfbPhone and ExternalEmail and contacts with just names
   */
  EXTERNAL = "External",
  /**
   * FEDERATEDONPREM - SfbFederatedOnPrem
   */
  FEDERATED_ONPREM = "FederatedOnPrem",
  /**
   * FEDERATEDONLINE - SfbFederatedOnline
   */
  FEDERATED_ONLINE = "FederatedOnline"
}

export enum PhoneType {
  NONE = "None",
  HOME = "Home",
  BUSINESS = "Business",
  MOBILE = "Mobile"
}

export enum AddressType {
  HOME = "Home",
  BUSINESS = "Business",
  OTHER = "Other"
}

export interface Address {
  city: string;
  countryOrRegion: string;
  postalCode: string;
  street: string;
  type: AddressType;
  postOfficeBox: string;
}

export interface Phones
{
  type: PhoneType;
  number: string;
}

/**
 * Represents the people contact
 */
export interface PeopleContact {
  id: string;
  eTag: string;
  firstName: string;
  lastName: string;
  title: string;
  displayName: string;
  nickName: string;
  middleName: string;
  mri: string;
  sipAddress: string;
  contactType: ContactType;
  upn: string;
  isTeamsFavorite: boolean;
  contactListIds: string[];
  location: string;
  jobTitle: string;
  assistant: string;
  companyName: string;
  department: string;
  manager: string;
  homeAddress: Address;
  businessAddress: Address;
  otherAddress: Address;
  phones: Phones[];
  emails: string[];
  creationDate: string;
  modifiedDate: string;
  notes: string;
}
