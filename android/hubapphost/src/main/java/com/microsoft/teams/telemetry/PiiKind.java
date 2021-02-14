/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.telemetry;

/**
 * Defines the PiiKind values for the event
 */
public enum PiiKind {
    NONE,
    DISTINGUISHED_NAME,
    GENERIC_DATA,
    IPV4_ADDRESS,
    IPV6_ADDRESS,
    MAIL_SUBJECT,
    PHONE_NUMBER,
    QUERY_STRING,
    SIP_ADDRESS,
    SMTP_ADDRESS,
    IDENTITY,
    URI,
    FQDN,
    IPV4_ADDRESS_LEGACY
}
