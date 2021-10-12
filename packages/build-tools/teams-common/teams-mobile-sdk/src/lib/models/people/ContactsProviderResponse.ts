export enum ContactsProviderResponseStatus {
  Success = 200,
  UnableToAccessData = 301
}

export enum SortType {
  Ascending = 0,
  Descending = 1
}

export enum SortOrder {
  DisplayName = 0,
  CreationDate = 1
}

export enum ContactSyncState {
   INITIAL = "initial",
   IN_PROGRESS = "inProgress",
   COMPLETED = "completed"
}

/**
 * Represents the response returned by the contact provider APIs
 */
export interface ContactsProviderResponse<T> {
  /**
   * status code for ContactsProvider request 
   */
  statusCode: ContactsProviderResponseStatus;
  result: T;
}
