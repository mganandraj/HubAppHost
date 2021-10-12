export interface UserBIEvent {
    eventName: UserBIEventName;
    region?: string;
    panelType?: string;
    panelUri?: string;
    moduleName?: string;
    moduleType?: string;
    moduleSummary?: string;
    moduleState?: string;
    outcome?: string;
    gesture?: string;
    scenario?: string;
    scenarioType?: string;
    destinationUri?: string;
    threadType?: string;
    launchMethod?: string;
    databagProp?: {
        [key: string]: string;
    };
    userBIDatabag?: {
        [key: string]: string | number | boolean;
    };
    teamId?: string;
    threadId?: string;
    threadMembers?: string;
}
export declare enum UserBIEventName {
    PANEL_VIEW = "panelview",
    PANEL_ACTION = "panelaction"
}
