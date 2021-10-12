export interface UserBIEvent {
  eventName: UserBIEventName;

  // Panel Properties
  region?: string;
  panelType?: string;
  panelUri?: string;

  // Module Properties
  moduleName?: string;
  moduleType?: string;
  moduleSummary?: string;
  moduleState?: string;

  // Action Properties
  outcome?: string;
  gesture?: string;
  scenario?: string;
  scenarioType?: string;
  destinationUri?: string;
  threadType?: string;

  // Other Properties
  launchMethod?: string;
  databagProp?: { [key: string]: string };
  userBIDatabag?: { [key: string]: string | number | boolean };
  teamId?: string;
  threadId?: string;
  threadMembers?: string;
}

export enum UserBIEventName {
  PANEL_VIEW = 'panelview',
  PANEL_ACTION = 'panelaction'
}
