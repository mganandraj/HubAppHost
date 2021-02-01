/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.sdk.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.teams.core.models.ContactType;

import java.util.Collections;
import java.util.Set;

/**
 * Represents the sdk app configuration for contacts extension.
 */
public class SdkContactsExtensionConfiguration {
    /**
     * The custom contact types exposed by the SDK app.
     */
    public final Set<ContactType> contactTypes;

    /**
     * Provider for contact card extensions.
     */
    public final SdkAppProviderConfiguration contactCardExtensionsProvider;

    /**
     * Provider for contact metadata.
     */
    public final SdkAppProviderConfiguration contactMetadataProvider;

    /**
     * Provider for contact search results.
     */
    public final SdkAppProviderConfiguration contactSearchResultsProvider;

    /**
     * Flag for contact search results ordering.
     */
    public final boolean enableContactSearchResultsOrdering;

    /**
     * Initializes a new instance of SdkContactsExtensionConfiguration class with the specified values.
     */
    public SdkContactsExtensionConfiguration(@NonNull Set<ContactType> contactTypes,
                                             @Nullable SdkAppProviderConfiguration contactCardExtensionsProvider,
                                             @Nullable SdkAppProviderConfiguration contactMetadataProvider,
                                             @Nullable SdkAppProviderConfiguration contactSearchResultsProvider,
                                             boolean enableContactSearchResultsOrdering) {
        this.contactTypes = Collections.unmodifiableSet(contactTypes);
        this.contactCardExtensionsProvider = contactCardExtensionsProvider;
        this.contactMetadataProvider = contactMetadataProvider;
        this.contactSearchResultsProvider = contactSearchResultsProvider;
        this.enableContactSearchResultsOrdering = enableContactSearchResultsOrdering;
    }
}
