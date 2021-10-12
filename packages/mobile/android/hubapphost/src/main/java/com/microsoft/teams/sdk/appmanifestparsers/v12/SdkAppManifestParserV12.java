package com.microsoft.teams.sdk.appmanifestparsers.v12;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArraySet;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.microsoft.teams.sdk.appmanifestparsers.ISdkAppManifestParser;
import com.microsoft.teams.sdk.models.QuickActionConfig;
import com.microsoft.teams.sdk.models.SdkAppManifest;
import com.microsoft.teams.sdk.models.SdkAppProviderConfiguration;
import com.microsoft.teams.sdk.models.SdkAppResource;
import com.microsoft.teams.sdk.models.SdkAppViewConfiguration;
import com.microsoft.teams.sdk.models.SdkContactsExtensionConfiguration;
import com.microsoft.teams.sdk.models.SdkShareExtensionConfiguration;
import com.microsoft.teams.sdk.models.EagerFetch;
import com.microsoft.teams.util.CollectionUtil;
import com.microsoft.teams.utilities.java.JsonUtils;
import com.microsoft.teams.utilities.java.ListUtils;
import com.microsoft.teams.utilities.java.StringUtils;
import com.microsoft.teams.core.models.ContactType;
import com.microsoft.teams.core.models.share.ShareTarget;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * v12 of manifest parser.
 *
 * Changes:
 * 1. Removed `modules` property. The child properties of `modules` that are required have
 * been moved to the top level. These are: `id`, `name`, `title`, `icons`.
 * 2. Removed `componentParams` property from `views`.
 */
public class SdkAppManifestParserV12 implements ISdkAppManifestParser {
    private static final String LOG_TAG = "SdkAppManifestParserV12";

    @NonNull
    @Override
    public SdkAppManifest parseManifest(@NonNull JsonObject manifestObject) throws Exception {
        SdkAppManifest sdkAppManifest = new SdkAppManifest();
        sdkAppManifest.version = JsonUtils.parseString(manifestObject, "version");
        sdkAppManifest.targetReactNativeSdkVersion = JsonUtils.parseString(manifestObject, "targetReactNativeSdkVersion");
        sdkAppManifest.minReactNativeSdkVersion = JsonUtils.parseString(manifestObject, "minReactNativeSdkVersion");

        // validateSdkAppManifestFieldValue("version", sdkAppManifest.version);

        switch (JsonUtils.parseString(manifestObject, "moduleType")) {
            case "quickAction":
                sdkAppManifest.moduleType = SdkAppManifest.ModuleType.QUICK_ACTION;
                break;
            default:
                sdkAppManifest.moduleType = SdkAppManifest.ModuleType.UI_MODULE;
        }
        sdkAppManifest.quickActionConfig = JsonUtils.parseObject(JsonUtils.getJsonStringFromObject(JsonUtils.parseObject(manifestObject, "quickActionConfig")),
                QuickActionConfig.class,
                null);

        JsonArray views = JsonUtils.parseArray(manifestObject, "views");
        // views might be empty for quick action modules like camera
        parseViews(sdkAppManifest, views, sdkAppManifest.quickActionConfig != null);

        JsonArray authDomains = JsonUtils.parseArray(manifestObject, "authDomains");
        parseAuthDomains(sdkAppManifest, authDomains);

        JsonObject extensions = JsonUtils.parseObject(manifestObject, "extensions");
        parseExtensions(sdkAppManifest, extensions);

        JsonObject eagerFetch = JsonUtils.parseObject(manifestObject, "eagerFetch");
        parseEagerFetch(sdkAppManifest, eagerFetch);

        return sdkAppManifest;
    }

    private void parseViews(@NonNull SdkAppManifest sdkAppManifest, JsonArray views, boolean ignoreEmptyViews) throws Exception {
        sdkAppManifest.views = new LinkedHashMap<>();
        if (views != null) {
            for (JsonElement view : views) {
                String viewName = JsonUtils.parseString(view, "name");
                if (StringUtils.isEmpty(viewName)) {
                    throwError("A view must specify a valid unique name.");
                }

                SdkAppResource title = SdkAppResource.fromUri(JsonUtils.parseString(view, "title"));
                if (title == null) {
                    throwError("View %s must specify a title as a string resource.", viewName);
                }

                String componentName = JsonUtils.parseString(view, "componentName");
                if (StringUtils.isEmpty(componentName)) {
                    throwError("View %s must specify a valid componentName.", viewName);
                }

                assert title != null;
                sdkAppManifest.views.put(viewName, new SdkAppViewConfiguration(viewName, title, componentName, null));
            }
        }

        if (sdkAppManifest.views.isEmpty() && !ignoreEmptyViews) {
            throwError("Cannot resolve any views. Check manifest configuration.");
        }
    }

    public void parseEagerFetch(@NonNull SdkAppManifest sdkAppManifest, @Nullable JsonElement eagerFetch) {
        if (eagerFetch == null) {
            return;
        }
        List<String> resourceTokenDomains = new ArrayList<>();
        JsonArray resourceTokenDomainsJsonArray = JsonUtils.parseArray(eagerFetch, "resourceToken");
        if (resourceTokenDomainsJsonArray != null) {
            for (JsonElement jsonElement : resourceTokenDomainsJsonArray) {
                resourceTokenDomains.add(jsonElement.getAsString());
            }
        }
        sdkAppManifest.eagerFetch = new EagerFetch(resourceTokenDomains);
    }

    private void parseAuthDomains(@NonNull SdkAppManifest sdkAppManifest, JsonArray authDomains) {
        sdkAppManifest.authDomains = new ArrayList<>();
        if (authDomains != null) {
            for (JsonElement authDomain : authDomains) {
                sdkAppManifest.authDomains.add(authDomain.getAsString());
            }
        }
    }

    private void parseExtensions(@NonNull SdkAppManifest sdkAppManifest, @NonNull JsonObject extensions) {
        JsonObject contactsExtension = JsonUtils.parseObject(extensions, "contacts");
        parseContactExtensions(sdkAppManifest, contactsExtension);

        JsonObject shareExtension = JsonUtils.parseObject(extensions, "share");
        parseShareExtensions(sdkAppManifest, shareExtension);
    }

    private void parseShareExtensions(@NonNull SdkAppManifest sdkAppManifest, JsonObject shareExtension) {
        if (shareExtension != null) {
            Set<ShareTarget> shareTargetSet = new ArraySet<>();
            JsonArray configuredShareTargets = JsonUtils.parseArray(shareExtension, "shareTargets");
            if (configuredShareTargets != null) {
                for (int i = 0; i < configuredShareTargets.size(); ++i) {
                    JsonObject shareTarget = configuredShareTargets.get(i).getAsJsonObject();
                    String targetId = shareTarget.get("targetId").getAsString();
                    String moduleId = shareTarget.get("moduleId").getAsString();
                    String name = shareTarget.get("name").getAsString();
                    List<String> contentTypes = new ArrayList<>();

                    JsonArray configuredContentTypes = JsonUtils.parseArray(shareTarget, "contentTypes");
                    if (configuredContentTypes != null) {
                        for (int x = 0; x < configuredContentTypes.size(); ++x) {
                            contentTypes.add(configuredContentTypes.get(x).getAsString());
                        }
                    }

                    if (ListUtils.isListNullOrEmpty(contentTypes)) {
                        continue;
                    }

                    shareTargetSet.add(new ShareTarget(
                            targetId,
                            moduleId,
                            name,
                            contentTypes));
                }

                if (!CollectionUtil.isCollectionEmpty(shareTargetSet)) {
                    sdkAppManifest.shareExtensionConfiguration = new SdkShareExtensionConfiguration(shareTargetSet);
                }
            }
        }
    }

    private void parseContactExtensions(@NonNull SdkAppManifest sdkAppManifest, JsonObject contactsExtension) {
        if (contactsExtension != null) {
            Set<ContactType> appContactTypes = new ArraySet<>();
            JsonArray configuredContactTypes = JsonUtils.parseArray(contactsExtension, "contactTypes");
            if (configuredContactTypes != null) {
                for (int i = 0; i < configuredContactTypes.size(); ++i) {
                    JsonObject contactType = configuredContactTypes.get(i).getAsJsonObject();
                    String contactTypeId = contactType.get("id").getAsString();
                    String contactTypeName = contactType.get("name").getAsString();
                    if (!StringUtils.isEmptyOrWhiteSpace(contactTypeId)) {
                        appContactTypes.add(new ContactType(contactTypeId, StringUtils.isEmptyOrWhiteSpace(contactTypeName) ? contactTypeId : contactTypeName));
                    }
                }
            }

            SdkAppProviderConfiguration contactCardExtensionsProviderConfiguration = parseProviderConfiguration(
                    contactsExtension, "contactCardExtensionsProvider");
            SdkAppProviderConfiguration contactMetadataProviderConfiguration = parseProviderConfiguration(
                    contactsExtension, "contactMetadataProvider");
            SdkAppProviderConfiguration contactSearchResultsProviderConfiguration = parseProviderConfiguration(
                    contactsExtension, "contactSearchResultsProvider");
            boolean enableContactSearchResultsOrdering = JsonUtils.parseBoolean(contactsExtension, "enableContactSearchResultsOrdering", false);

            if (!CollectionUtil.isCollectionEmpty(appContactTypes)
                    || contactCardExtensionsProviderConfiguration != null
                    || contactSearchResultsProviderConfiguration != null) {
                sdkAppManifest.contactExtensionConfiguration = new SdkContactsExtensionConfiguration(
                        appContactTypes,
                        contactCardExtensionsProviderConfiguration,
                        contactMetadataProviderConfiguration,
                        contactSearchResultsProviderConfiguration,
                        enableContactSearchResultsOrdering);
            }
        }
    }

    /**
     * Validates the specified required field value is not null or blank.
     *
     * @param fieldName The name of the required field.     *
     * @param fieldValue The parsed value of the required field.
     */
    private void validateSdkAppManifestFieldValue(String fieldName, String fieldValue) throws Exception {
        if (StringUtils.isEmptyOrWhiteSpace(fieldValue)) {
            throwError("%s is not specified or is blank in the manifest. Please specify a valid value.", fieldName);
        }
    }

    /**
     * Throws an error with the specified message and message parameters.
     */
    private void throwError(String message, Object... params) throws Exception {
        String errorMessage = String.format(Locale.ENGLISH, message, params);
        throw new Exception(errorMessage);
    }

    /**
     * Parses provider configuration for the specified provider name.
     */
    @Nullable
    private SdkAppProviderConfiguration parseProviderConfiguration(@NonNull JsonObject root, @NonNull String providerName) {
        JsonObject providerConfiguration = JsonUtils.parseObject(root, providerName);
        if (providerConfiguration != null) {
            String runnableName = JsonUtils.parseString(providerConfiguration, "runnableName");
            if (!StringUtils.isEmpty(runnableName)) {
                return new SdkAppProviderConfiguration(runnableName);
            }
        }

        return null;
    }
}
