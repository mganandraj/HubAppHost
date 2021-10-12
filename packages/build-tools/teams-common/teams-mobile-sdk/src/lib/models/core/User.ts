/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

export interface User {
  displayName: string;
  principalName: string;
  aadId: string;
  skypeMri: string;
  tenantId: string;
  jobTitle: string;
  /**
   * Represent personal sharepoint url of user.
   * Note : This value will be present only for current logged-in user.
   */
  personalSharePointSiteUrl?: string;

  /**
   * Represent sharepoint url of user.
   * Note : This value will be present only for current logged-in user.
   */
  tenantSiteUrl?: string;

  /**
   * Represents the contact id of the user if its a contact
   */
  contactId?: string;
  imageURI?: string;
}
