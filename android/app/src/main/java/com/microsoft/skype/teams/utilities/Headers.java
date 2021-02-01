/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.utilities;

import com.microsoft.skype.teams.services.utilities.StringUtilities;
import com.microsoft.skype.teams.utilities.java.StringUtils;

import okhttp3.Request;
// import retrofit2.Response;

/**
 * Headers Utility class
 */
public final class Headers {
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CLAIMS_CAPABILITY = "claimsChallengeCapable";
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_LENGTH_LOCAL = "x-computed-content-length";
    public static final String ACCEPT = "Accept";
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String REGISTRATION_TOKEN = "RegistrationToken";
    public static final String SET_REGISTRATION_TOKEN = "Set-RegistrationToken";
    public static final String X_SKYPE_TOKEN = "X-Skypetoken";
    public static final String AUTHORIZATION = "Authorization";
    public static final String CLIENT_AUTHORIZATION = "ClientAuthorization";
    public static final String PREFER = "Prefer";
    public static final String USER_AGENT = "User-Agent";
    public static final String AUTH_SKYPE_REGISTRATION_TOKEN = "Auth-SkypeRegistrationToken";
    public static final String COOKIE = "Cookie";
    public static final String AUTHENTICATION = "Authentication";
    public static final String CLIENT_ENVIRONMENT = "x-ms-client-env";
    public static final String CLIENT_TYPE = "x-ms-client-type";
    public static final String CLIENT_VERSION = "x-ms-client-version";
    public static final String CLIENT_FLIGHTS = "x-client-flights";
    public static final String SERVER_FLIGHTS = "x-server-flights";
    public static final String CLIENT_INFO = "ClientInfo";
    public static final String LOCATION = "Location";
    public static final String CORRELATION_ID = "x-ms-correlation-id"; // Client generated co-relation id
    public static final String REQUEST_ID = "x-ms-request-id";
    public static final String SUBSTRATE_REQUEST_ID = "client-request-id";
    public static final String SCENARIO_ID = "x-ms-scenario-id";
    public static final String SESSION_ID = "x-ms-session-id";
    public static final String USER_LOCALE = "X-Client-UI-Language";
    public static final String JOIN_ID = "x-ms-join-id";
    public static final String SOURCE_DETAIL = "X-SourceDetail";
    public static final String SOURCE_ID = "X-SourceId";
    public static final String SOURCE_USER = "X-SourceUser";
    public static final String WORKLOAD_ID = "X-WorkloadId";
    public static final String SAFE_LINK_CORRELATION_ID = "X-CorrelationId";
    public static final String X_RPS = "X-Rps";
    public static final String MS_CV = "ms-cv"; // Correlation vector
    public static final String USER_NAME = "userName";
    public static final String TENANT_ID = "tenantId";
    public static final String CROSS_TENANT_ID = "crossTenantId";

    // Below headers are for unified presence service for Teams for Life
    public static final String X_MS_CLIENT_CONSUMER_TYPE = "x-ms-client-consumer-type";
    public static final String X_MS_CLIENT_USER_AGENT = "x-ms-client-user-agent";
    public static final String X_MS_ENDPOINT_ID = "x-ms-endpoint-id";

    // Below headers are for requesting suggested rooms
    public static final String FIND_MEETING_LOCATIONS_APPNAME = "x-findmeetinglocations-appname";
    public static final String FIND_MEETING_LOCATIONS_APPSCENARIO = "x-findmeetinglocations-appscenario";
    public static final String FIND_MEETING_LOCATIONS_APPNAME_CONSTANT = "Teams/Android";
    public static final String FIND_MEETING_LOCATIONS_APPSCENARIO_CONSTANT = "Android Locations";

    // Below headers are for IP/CAP Compliance
    public static final String SPO_ERROR_CODE = "X-SPO-ErrorCode";
    public static final String SPO_ERROR_MESSAGE = "X-SPO-ErrorMessage";

    // Below headers contains "server request id" which will used to populate Http_ServerRequestId in HTTP Kusto table.
    public static final String SERVER_REQUEST_ID = "X-ServerRequestId"; // Middle Tier calls sends this header as server request id
    public static final String CONTEXT_ID = "ContextId"; // Chat service sends this header as server request id
    public static final String UPS_SERVER_ID = "x-ms-correlation-id"; // Unified presence send this header for server request id.
    public static final String SP_REQUEST_ID = "sprequestguid"; // Sharepoint APIs send this header for server request id
    public static final String SUBSTRATE_SERVER_ID = "request-id"; // Substrate APIs send this header for server request id
    public static final String X_CID = "x-cid"; // For OCPS APIs

    public static String getHeaderValue(Request request, String headerName) {
        return getHeaderValue(request, headerName, null);
    }

    public static String getHeaderValue(Request request, String headerName, String defaultValue) {
        if (request != null) {
            return getHeaderValue(request.headers(), headerName, defaultValue);
        } else {
            return StringUtilities.EMPTY;
        }
    }

//    public static String getHeaderValue(Response response, String headerName) {
//        return getHeaderValue(response, headerName, null);
//    }

    public static String getHeaderValue(okhttp3.Response response, String headerName) {
        return getHeaderValue(response.headers(), headerName, null);
    }

//    public static String getHeaderValue(Response response, String headerName, String defaultValue) {
//        return response == null ? defaultValue : getHeaderValue(response.headers(), headerName, defaultValue);
//    }

    private static String getHeaderValue(okhttp3.Headers headers, String headerName, String defaultValue) {
        if (headers == null || StringUtils.isEmptyOrWhiteSpace(headerName)) {
            return defaultValue;
        }

        return headers.get(headerName);
    }

    private Headers() {
    }
}
