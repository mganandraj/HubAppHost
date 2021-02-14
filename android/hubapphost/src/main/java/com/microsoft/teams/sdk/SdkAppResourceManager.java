/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.sdk;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

// import com.caverock.androidsvg.SVG;
import com.google.gson.JsonObject;
//import com.microsoft.teams.app.SkypeTeamsApplication;
//import com.microsoft.teams.events.EventHandler;
//import com.microsoft.teams.events.IEventBus;
//import com.microsoft.teams.events.IEventHandler;
//import com.microsoft.teams.events.IHandlerCallable;
//import com.microsoft.teams.globalization.IMarketization;
//import com.microsoft.teams.globalization.MarketInfo;
//import com.microsoft.teams.globalization.Marketization;
//import com.microsoft.teams.icons.utils.IconUtils;
import com.microsoft.teams.logger.ILogger;
import com.microsoft.teams.logger.LogPriority;
import com.microsoft.teams.sdk.models.SdkAppResource;
//import com.microsoft.teams.services.images.svg.SvgImageFormat;
import com.microsoft.teams.util.CollectionUtil;
import com.microsoft.teams.utilities.IOUtilities;
import com.microsoft.teams.utilities.java.JsonUtils;
import com.microsoft.teams.utilities.java.StringUtils;
//import com.microsoft.stardust.IconSymbol;
//import com.microsoft.stardust.IconSymbolSize;
//import com.microsoft.stardust.IconSymbolStyle;
import com.microsoft.teams.core.app.ITeamsApplication;
//import com.msft.stardust.helpers.IconHelper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Set;

/**
 * Manages the resources for an SDK application.
 */
public final class SdkAppResourceManager {
    private static final String LOG_TAG = "SdkAppResourceManager";

    private final File mResourceCacheDirectory;
    // private final IEventBus mEventBus;
    // private final IMarketization mMarketization;
    private final ITeamsApplication mTeamsApplication;
    private LinkedList<StringsLookupTable> mStringsTables = new LinkedList<>();
    /*private final IEventHandler<MarketInfo> mMarketChangedEventHandler = EventHandler.background(new IHandlerCallable<MarketInfo>() {
        @Override
        public void handle(@NonNull MarketInfo locale) {
            loadStringsLookupTable(locale.toString());
        }
    });*/

    SdkAppResourceManager(@NonNull ITeamsApplication teamsApplication, @NonNull String rnBundleLocation) {
        mTeamsApplication = teamsApplication;
        mResourceCacheDirectory = new File(rnBundleLocation);

        /*mEventBus = SkypeTeamsApplication.getApplicationComponent().eventBus();
        mMarketization = SkypeTeamsApplication.getApplicationComponent().marketization();

        mEventBus.subscribe(Marketization.MARKET_CHANGED_EVENT, mMarketChangedEventHandler);*/
        loadStringsLookupTable(getLocale());
    }

    public void destroy() {
        // mEventBus.unSubscribe(Marketization.MARKET_CHANGED_EVENT, mMarketChangedEventHandler);
    }

    public String getLocale() {
        // return mMarketization.getCurrentMarket().toString();
        return "en-US";
    }

    @NonNull
    public String getString(@Nullable SdkAppResource resource) {
        final ILogger logger = mTeamsApplication.getLogger(null);
        // TODO: support string parameters substitution
        if (resource == null || resource.type != SdkAppResource.ResourceType.STRING) {
            logger.log(LogPriority.ERROR, LOG_TAG, "Sdk app resource is not a string resource.");
            return "";
        }

        if (CollectionUtil.isCollectionEmpty(mStringsTables)) {
            logger.log(LogPriority.ERROR, LOG_TAG, "No string resource found with id: " + resource.id);
            return "";
        }

        for (StringsLookupTable stringsLookupTable : mStringsTables) {
            String stringValue = stringsLookupTable.getString(resource.id);
            if (!StringUtils.isEmpty(stringValue)) {
                return stringValue;
            }
        }

        logger.log(LogPriority.ERROR, LOG_TAG, "No string resource found with id: " + resource.id);
        return "";
    }

    public Uri getDrawableUri(@NonNull Context context, @NonNull SdkAppResource resource) {
        return null;
        /*final ILogger logger = mTeamsApplication.getLogger(null);
        switch (resource.type) {
            case IMAGE:
                String drawableResourcePath = mResourceCacheDirectory + "/images/" + resource.id;
                File file = new File(drawableResourcePath);
                if (!file.exists()) {
                    logger.log(LogPriority.ERROR, LOG_TAG, "Failed to read image resource file.");
                    logger.log(LogPriority.VERBOSE, LOG_TAG, "Resource id: " + resource.id);
                    return null;
                }
                return Uri.fromFile(file);
            case FLUENT_ICON:
                IconSymbol iconSymbol;
                String style = null;
                if (resource.id.contains("/")) {
                    String[] splits = resource.id.split("/");
                    iconSymbol = IconUtils.getIconSymbol(splits[0]);
                    style = splits[1];
                } else {
                    iconSymbol = IconUtils.getIconSymbol(resource.id);
                }

                if (IconSymbolStyle.FILLED.name().equalsIgnoreCase(style)) {
                    Uri iconUri = IconUtils.toIconUriFilled(context, iconSymbol);
                    if (iconUri == null) {
                        logger.log(LogPriority.ERROR, LOG_TAG, "Failed to read fluent resource file.");
                        logger.log(LogPriority.VERBOSE, LOG_TAG, "Resource id: " + resource.id);
                        return null;
                    }
                    return iconUri;
                } else {
                    Uri iconUri = IconUtils.toIconUriRegular(context, iconSymbol);
                    if (iconUri == null) {
                        logger.log(LogPriority.ERROR, LOG_TAG, "Failed to read fluent resource file.");
                        logger.log(LogPriority.VERBOSE, LOG_TAG, "Resource id: " + resource.id);
                        return null;
                    }
                    return iconUri;
                }

            default:
                logger.log(LogPriority.ERROR, LOG_TAG, "Sdk app resource is not an image resource.");
                return null;
        }*/
    }

    @Nullable
    public Drawable getDrawable(@NonNull Context context, @NonNull SdkAppResource resource) {
        return getDrawable(context, resource, 0, 0);
    }

    @Nullable
    public Drawable getDrawable(@NonNull Context context, @NonNull SdkAppResource resource, int width, int height) {
        return null;
        /*final ILogger logger = mTeamsApplication.getLogger(null);
        switch (resource.type) {
            case IMAGE:
                String drawableResourcePath = mResourceCacheDirectory + "/images/" + resource.id;

                if (!new File(drawableResourcePath).exists()) {
                    logger.log(LogPriority.ERROR, LOG_TAG, "No image resource found with id: " + resource.id);
                    return null;
                }

                if (SvgImageFormat.isSvg(drawableResourcePath)) {
                    FileInputStream drawableResource = null;
                    try {
                        drawableResource = new FileInputStream(drawableResourcePath);
                        SVG svg = SVG.getFromInputStream(drawableResource);
                        if (svg != null) {
                            if (width > 0) {
                                svg.setDocumentWidth(width);
                            }

                            if (height > 0) {
                                svg.setDocumentHeight(height);
                            }

                            int w = (int) svg.getDocumentWidth();
                            int h = (int) svg.getDocumentHeight();
                            Bitmap bitmap = Bitmap.createBitmap(w > 0 ? w : 512, h > 0 ? h : 512, Bitmap.Config.ARGB_8888);
                            Canvas canvas = new Canvas(bitmap);
                            svg.renderToCanvas(canvas);
                            return new BitmapDrawable(context.getResources(), bitmap);
                        }
                    } catch (Exception ex) {
                        logger.log(LogPriority.ERROR, "GetSVGDrawableFromAppCache", ex, "Failed to load SVG.");
                    } finally {
                        if (drawableResource != null) {
                            try {
                                drawableResource.close();
                            } catch (IOException ex) {
                                logger.log(LogPriority.WARNING, "GetSVGDrawableFromAppCache", ex, "Failed to close SVG input stream.");
                            }
                        }
                    }

                    return null;
                }
                return Drawable.createFromPath(drawableResourcePath);
            case FLUENT_ICON:
                return IconHelper.Companion.fetchDrawableWithNoTint(context, IconSymbol.Companion.fromValue(resource.id), IconSymbolSize.NORMAL, IconSymbolStyle.REGULAR);
            case STRING:
            default:
                logger.log(LogPriority.ERROR, LOG_TAG, "Sdk app resource is not an image resource.");
                return null;
        }*/



    }

    private void loadStringsLookupTable(@NonNull String locale) {
        Set<String> tableKeys = new LinkedHashSet<>();
        String stringTableKey = locale;
        do {
            tableKeys.add(stringTableKey);
            int delimiterIndex = stringTableKey.lastIndexOf('-');
            if (delimiterIndex != -1) {
                stringTableKey = locale.substring(0, delimiterIndex);
            } else {
                stringTableKey = "";
            }
        } while (!StringUtils.isEmpty(stringTableKey));

        // Add default locale 'en'
        tableKeys.add("en");

        // Load the string tables
        LinkedList<StringsLookupTable> stringsLookupTables = new LinkedList<>();
        for (String tableKey : tableKeys) {
            String stringResourceFileName = String.format(Locale.ENGLISH, "strings/strings_%s.json", tableKey);
            File stringResourceFile = new File(mResourceCacheDirectory, stringResourceFileName);
            JsonObject stringsLookupTable = readStringsFromResourceFile(stringResourceFile);
            if (stringsLookupTable != null) {
                stringsLookupTables.add(new StringsLookupTable(stringsLookupTable));
            }
        }

        // Assign the strings tables.
        mStringsTables = stringsLookupTables;
    }

    private JsonObject readStringsFromResourceFile(@NonNull File stringResourceFile) {
        try {
            String fileContent = IOUtilities.readFileContent(stringResourceFile);
            return fileContent != null
                   ? JsonUtils.parseObject(fileContent, JsonObject.class, null)
                   : null;
        } catch (IOException e) {
            final ILogger logger = mTeamsApplication.getLogger(null);
            logger.log(LogPriority.ERROR, LOG_TAG, "Failed to read string resource file.");
            return null;
        }
    }

    /**
     * Look up table for strings.
     */
    public static final class StringsLookupTable {
        private final JsonObject mTableJson;

        StringsLookupTable(@NonNull JsonObject tableJson) {
            mTableJson = tableJson;
        }

        public String getString(@NonNull String stringKey) {
            // TODO, supported nested string key
            return JsonUtils.parseString(mTableJson, stringKey);
        }
    }
}
