/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.services.utilities;

import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

import com.microsoft.skype.teams.utilities.java.StringUtils;

/*
import com.google.common.collect.Ordering;
import com.google.common.hash.Hashing;
*/
/*
import com.microsoft.skype.teams.logger.ILogger;
import com.microsoft.skype.teams.logger.LogPriority;
import com.microsoft.skype.teams.util.PhoneUtils;
import com.microsoft.skype.teams.utilities.StringConstants;
import com.microsoft.skype.teams.utilities.java.ListUtils;
import com.microsoft.teams.core.R;
import com.microsoft.teams.core.models.IUser;

import org.jsoup.helper.StringUtil;
*/

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * Common location for useful string helper methods.
 */
public final class StringUtilities {
    /**
     * Empty string
     */
    public static final String EMPTY = "";
    public static final String COMMA = ",";
    public static final String WHITE_SPACE = " ";
    public static final String URL_SEPARATOR = "/";
    public static final String[] EMPTY_ARRAY = new String[0];
    /**
     * Comparator that tolerates nulls in arguments.
     * Good for TreeMap<String, ?>, TreeSet<String> etc.
     *
     * To be replaced with
     * @see java.util.Comparator#nullsFirst
     * when possible.
     */
/*
    public static final Comparator<? super String> NULL_TOLERANT_CASE_INSENSITIVE_ORDER
            = Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst();

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+");
*/

    /**
     * Returns the input string with all whitespace condensed to a single space.
     * New lines, multiple spaces, tabs etc. will all be replaced with a space.
     *
     * @param toCompress The string to compress whitespace for
     * @return The string with whitespace replaced
     */
/*
    public static String compressWhitespace(String toCompress) {
        return StringUtils.isNotEmpty(toCompress) ? WHITESPACE_PATTERN.matcher(toCompress).replaceAll(" ") : EMPTY;
    }
*/


    /**
     * Returns a boolean indicating if the text represented by the given HTML is all whitespace or not.
     *
     * @return True if whitespace, false if the HTML will render non-whitespace TEXT.
     */
    public static boolean isHtmlContentWhitespace(String html) {
        return StringUtils.isEmptyOrWhiteSpace(getTextFromHtml(html, true));
    }

    /**
     * Gets text from html
     *
     * @param html Html to be converted to text
     * @param trim Flag indicating whether leading and trailing whitespaces should be removed
     * @return Html converted to text
     */
    public static String getTextFromHtml(String html, boolean trim) {
        if (StringUtils.isEmpty(html)) {
            return html;
        }

        return trim ? Html.fromHtml(html).toString().trim() : Html.fromHtml(html).toString();
    }



    /**
     * Returns an aggregated and friendly display-name of a list of users
     *
     * @param context The context
     * @param users The list of users
     * @return A string that lists a short a friendly display-name of all users
     */
   /* @NonNull
    public static String getAggregatedIUserDisplayString(@NonNull Context context, @Nullable List<? extends IUser> users) {
        List<String> userDisplayNames = null;
        if (!ListUtils.isListNullOrEmpty(users)) {
            userDisplayNames = new ArrayList<>(users.size());
            for (IUser user : users) {
                // userDisplayNames.add(user.displayName);
                userDisplayNames.add(user.getDisplayName(context));
            }
        }

        return getAggregatedUsersList(context, userDisplayNames);
    }*/

    /**
     * Returns an aggregated and friendly display-name of a list of users
     * seind
     *
     * @param context The context
     * @param userDisplayNames The list of users
     * @return A string that lists a short a friendly display-name of all users
     */
/*
    @NonNull
    public static String getAggregatedUsersList(@NonNull Context context, @Nullable List<String> userDisplayNames) {
        return getAggregatedUsersList(context, userDisplayNames, true);
    }
*/
/*
    @NonNull
    public static String getAggregatedUsersList(@NonNull Context context, @Nullable List<String> userDisplayNames) {
        return getAggregatedUsersList(context, userDisplayNames, true);
    }
*/

    /**
     * Returns an aggregated and friendly display-name of a list of users
     *
     * @param context The context
     * @param userDisplayNames The list of users
     * @return A string that lists a short a friendly display-name of all users
     */
/*
    @NonNull
    public static String getAggregatedUsersList(@NonNull Context context, @Nullable List<String> userDisplayNames, boolean shortenName) {
        return getAggregatedUsersList(context, userDisplayNames, shortenName, true);
    }
*/
/*
    */
/**
     * Returns an aggregated and friendly display-name of a list of users
     *
     * @param context The context
     * @param userDisplayNames The list of users
     * @param isSorted Should the list ordered in alphabetic
     *                 Sometimes we think sorting a long list then just use 3 of the names is meaningless, so the option to do not sort is added.
     * @return A string that lists a short a friendly display-name of all users
     *//*

/*
    @NonNull
    public static String getAggregatedUsersList(@NonNull Context context, @Nullable List<String> userDisplayNames, boolean shortenName, boolean isSorted) {
        if (userDisplayNames == null || userDisplayNames.isEmpty()) {
            return "";
        }

        if (isSorted) {
            Collections.sort(userDisplayNames);
        }
        final int numberOfUsers = userDisplayNames.size();

        switch (numberOfUsers) {
            case 1:
                return userDisplayNames.get(0);
            case 2:
                return context.getString(R.string.aggregated_users_2,
                        shortenName ? getShortUserDisplayName(context, userDisplayNames.get(0)) : userDisplayNames.get(0),
                        shortenName ? getShortUserDisplayName(context, userDisplayNames.get(1)) : userDisplayNames.get(1));
            case 3:
                return context.getString(R.string.aggregated_users_3,
                        shortenName ? getShortUserDisplayName(context, userDisplayNames.get(0)) : userDisplayNames.get(0),
                        shortenName ? getShortUserDisplayName(context, userDisplayNames.get(1)) : userDisplayNames.get(1),
                        shortenName ? getShortUserDisplayName(context, userDisplayNames.get(2)) : userDisplayNames.get(2));
            default:
                final int remaining = numberOfUsers - 2;
                return context.getString(R.string.aggregated_users_4_and_more_abbreviated,
                        shortenName ? getShortUserDisplayName(context, userDisplayNames.get(0)) : userDisplayNames.get(0),
                        shortenName ? getShortUserDisplayName(context, userDisplayNames.get(1)) : userDisplayNames.get(1),
                        remaining);
        }
    }

    public static String toLowerCaseInvariant(String string) {
        return string == null ? null : string.toLowerCase(Locale.ENGLISH);
    }

    /**
     * Null-safe, case-insensitive and two-arguments version of {@link String#startsWith(String)} method.
     * Tests if this string starts with the specified prefix.
     *
     * @param   prefix   the prefix.
     * @return  {@code true} if the character sequence represented by the
     *          argument is a prefix of the character sequence represented by
     *          this string; {@code false} otherwise.
     *          Note also that {@code true} will be returned if the
     *          argument is an empty string or is equal to this
     *          {@code String} object as determined by the
     *          {@link #equals(Object)} method.
     */
   /* public static boolean startsWithIgnoreCase(@Nullable String string, @Nullable String prefix) {
        return string != null && prefix != null && toLowerCaseInvariant(string).startsWith(toLowerCaseInvariant(prefix));
    }

    public static boolean containsIgnoreCase(@Nullable String string, @Nullable String subString) {
        return string != null && subString != null && string.toLowerCase().contains(subString.toLowerCase());
    }

    @NonNull
    public static String replaceIgnoreCase(@Nullable String stringThatContainsTemplate,
                                           @Nullable String template,
                                           @Nullable String replacement) {
        String localReplacement = replacement;
        if (!containsIgnoreCase(stringThatContainsTemplate, template) || stringThatContainsTemplate.isEmpty() || template.isEmpty()) {
            throw new RuntimeException(String.format("Nothing to replace in %s", stringThatContainsTemplate));
        }
        if (localReplacement == null) {
            localReplacement = "";
        }
        return stringThatContainsTemplate.replaceAll(String.format("(?i)%s", template), localReplacement);
    }

    @Nullable
    public static String replaceIgnoreCase(@Nullable String stringThatContainsTemplate,
                                           @Nullable Map<String, String> mapper) {
        if (mapper == null) {
            return stringThatContainsTemplate;
        }
        String result = stringThatContainsTemplate;
        for (String template : mapper.keySet()) {
            if (containsIgnoreCase(result, template)) {
                result = replaceIgnoreCase(result, template, mapper.get(template));
            }
        }
        return result;
    }

    public static boolean hasTemplate(@Nullable String stringThatContainsTemplate, @NonNull String templateTag) {
        return stringThatContainsTemplate != null && stringThatContainsTemplate.matches(String.format(".*%s.*%s.*", templateTag, templateTag));
    }

    @NonNull
    public static String wrapAsHtml(@Nullable String text) {
        return wrapAsHtml(text, "div");
    }

    @NonNull
    public static String wrapAsHtml(@Nullable String html, String tag) {
        String wrappedHtml;
        if (html == null) {
            wrappedHtml = "";
        } else {
            wrappedHtml = html;
        }

        if (StringUtils.isEmptyOrWhiteSpace(tag)) {
            throw new IllegalArgumentException("tag should be non-null and non-blank");
        }

        String trimmedTag = tag.trim();

        // already wrapped
        if (wrappedHtml.startsWith("<" + trimmedTag + ">") && wrappedHtml.endsWith("</" + trimmedTag + ">")) {
            return wrappedHtml;
        }

        return String.format(Locale.ENGLISH, "<%s>%s</%s>", trimmedTag, wrappedHtml, trimmedTag);
    }*/
/*

    @NonNull
    public static String getShortStrFromSeconds(@NonNull Resources resources, long totalSeconds) {

        if (totalSeconds <= 0) {
            return resources.getString(R.string.time_in_seconds, 0);
        }

        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        String formattedDuration = "";

        if (hours > 0) {
            formattedDuration = resources.getString(R.string.time_in_hours, hours);
        }

        if (minutes > 0) {
            if (StringUtils.isEmpty(formattedDuration)) {
                formattedDuration = resources.getString(R.string.time_in_minutes, minutes);
            } else {
                formattedDuration += " " + resources.getString(R.string.time_in_minutes, minutes);
            }
        }

        if (seconds > 0) {
            if (StringUtils.isEmpty(formattedDuration)) {
                formattedDuration = resources.getString(R.string.time_in_seconds, seconds);
            } else {
                formattedDuration += " " + resources.getString(R.string.time_in_seconds, seconds);
            }
        }

        return formattedDuration;
        return "";
    }
*/
/*
    @NonNull
    public static String getReadableStrFromSeconds(@NonNull Resources resources, @Nullable Integer totalSeconds) {
        if (totalSeconds == null) {
            return "";
        }

        final int hr = (int) TimeUnit.SECONDS.toHours(totalSeconds);
        final int min = (int) TimeUnit.SECONDS.toMinutes(totalSeconds - TimeUnit.HOURS.toSeconds(hr));
        final int sec = (int) TimeUnit.SECONDS.toSeconds(totalSeconds - TimeUnit.HOURS.toSeconds(hr) - TimeUnit.MINUTES.toSeconds(min));

        String hourString = null;
        String minString = null;
        String secString = null;

        int numOfSegments = 0;

        if (hr > 0) {
            hourString = resources.getQuantityString(R.plurals.accessibility_event_hours, hr, hr);
            numOfSegments++;
        }

        if (min > 0) {
            minString = resources.getQuantityString(R.plurals.accessibility_event_minutes, min, min);
            numOfSegments++;
        }

        if (sec > 0) {
            secString = resources.getQuantityString(R.plurals.accessibility_event_seconds, sec, sec);
            numOfSegments++;
        }

        if (numOfSegments == 3) {
            return resources.getString(R.string.accessibility_event_time_3_segments, hourString, minString, secString);
        } else if (numOfSegments == 2) {
            String firstSegment = hourString == null ? minString : hourString;
            String secondSegment = secString == null ? minString : secString;
            return resources.getString(R.string.accessibility_event_time_2_segments, firstSegment, secondSegment);
        } else if (numOfSegments == 1) {
            // Only 1 string is not null.
            return hourString != null ? hourString : minString != null ? minString : secString;
        } else {
            return resources.getQuantityString(R.plurals.accessibility_event_seconds, 0, 0);
        }
    }

    @NonNull
    public static String getDisplayStrFromSeconds(@Nullable Integer totalSeconds) {
        if (totalSeconds == null || totalSeconds <= 0) {
            return "00:00";
        }

        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds / 60) % 60;
        int seconds = totalSeconds % 60;

        String minutesAndSeconds = (minutes < 10 ? "0" + minutes : minutes) + ":"
                + (seconds < 10 ? "0" + seconds : seconds);

        if (hours > 0) {
            return (hours < 10 ? "0" + hours : hours) + ":" + minutesAndSeconds;
        }

        return minutesAndSeconds;
    }

    public static int getSecondsFromDisplayStr(@Nullable String displayStr, @NonNull ILogger logger) {
        // display str format is expected in hh:mm:ss with trailing milliseconds
        if (displayStr == null) {
            return -1;
        }
        String[] splitArray = displayStr.split(":");
        if (splitArray.length != 3 && splitArray.length != 2) {
            // expecting hh:mm:ss or mm:ss
            return -1;
        }

        int h = 0;
        int m = 0;
        int s = 0;

        switch (splitArray.length) {
            case 2:
                try {
                    m = Integer.parseInt(splitArray[0]);
                    s = Integer.parseInt(splitArray[1].substring(0, 2));
                } catch (Exception e) {
                    logger.log(LogPriority.INFO, "Calling: Call ", "BreakoutRooms timer incorrect format: " + displayStr);
                }
                break;
            case 3:
                try {
                    h = Integer.parseInt(splitArray[0]);
                    m = Integer.parseInt(splitArray[1]);
                    s = Integer.parseInt(splitArray[2].substring(0, 2));
                } catch (Exception e) {
                    logger.log(LogPriority.INFO, "Calling: Call ", "BreakoutRooms timer incorrect format: " + displayStr);
                }
                break;
            default:
        }
        return ((h * 60 * 60) + (m * 60) + s);
    }

    *//**
     * Returns first name complete and the last name abbreviated.
     *
     * @param displayName The user display-name
     * @return First name complete and last name abbreviated
     *//*
    public static String getLastNameAbbreviation(@NonNull Context context, @NonNull String displayName) {
        String[] splitName = displayName.trim().split("\\s+");

        if (splitName.length > 1) {
            String firstName = splitName[0];
            String lastName = splitName[1];

            if (splitName[splitName.length - 1].equalsIgnoreCase(context.getString(R.string.guest_user_identifier).trim())) { // Guest User, suffix is guest identifier
                if (splitName.length == 2) { // Special case: no last name, just return the whole name. eg. Bob (Guest)
                    return displayName;
                } else { // eg. Test User (Guest) -> Test G. (Guest). Length is more than 2
                    return firstName + " " + lastName.charAt(0) + ". " + splitName[splitName.length - 1];
                }
            } else { // Not a guest user
                return splitName[0] + " " + lastName.charAt(0) + ".";
            }
        } else {
            return displayName;
        }
    }

    @Nullable
    public static String getTwoCharNameAbbreviation(@Nullable String givenName, @Nullable String surname) {
        StringBuilder stringBuilder = new StringBuilder(2);

        boolean hasGivenName = !TextUtils.isEmpty(givenName);
        boolean hasSurName = !TextUtils.isEmpty(surname);

        if (!hasGivenName && !hasSurName) {
            return null;
        }

        if (hasGivenName && hasSurName) {
            stringBuilder.append(givenName.trim().charAt(0)).append(surname.trim().charAt(0));
        } else if (hasGivenName) {
            stringBuilder.append(givenName.trim().charAt(0));
            if (givenName.trim().length() > 1) {
                stringBuilder.append(givenName.trim().charAt(1));
            }
        } else {
            stringBuilder.append(surname.trim().charAt(0));
            if (surname.trim().length() > 1) {
                stringBuilder.append(surname.trim().charAt(1));
            }
        }

        return stringBuilder.toString();
    }

    *//**
     * Returns a short display name for a user (which is the first name)
     *
     * @param context application context
     * @param displayName The user display-name
     * @return The first-name of the user or 'Unknown' if it can't be resolved
     *//*
    @NonNull
    public static String getShortUserDisplayName(@NonNull Context context, @Nullable String displayName) {
        // TODO: we need to check whether the user is the current logged-in user and display "You" instead of his name
        //If this is a PSTN number,return the complete number.No other way to know if its a PSTN number
        if (PhoneUtils.isValidGlobalPhoneNumber(displayName)) {
            return displayName;
        } else {
            return StringUtils.isEmpty(displayName) ? context.getString(R.string.unknown_user_first_name) : displayName.split(" ")[0];
        }
    }

    *//**
     * Returns a short display name for a user (which is the first name)
     *
     //* @param context application context
     //* @param userDisplayName The user display-name
     * @return The short-name of the user or 'Unknown' if it can't be resolved
     *//*
    @NonNull
    public static String getShortUserDisplayNameByLocale(@NonNull Context context, @Nullable String userDisplayName,
                                                         @Nullable String userGivenName, @Nullable String userSurName) {
        String fullName = getNameAfterCleaningDisambiguationBrackets(userDisplayName);
        if (StringUtils.isEmpty(fullName)) {
            return context.getString(R.string.unknown_user_first_name);
        }
        if (PhoneUtils.isValidGlobalPhoneNumber(fullName)) {
            return fullName;
        }
        String givenName = getNameAfterCleaningDisambiguationBrackets(userGivenName);
        String surName = getNameAfterCleaningDisambiguationBrackets(userSurName);

        String firstName = getGivenNameForUser(fullName, givenName);
        String lastName = getSurNameForUser(fullName, surName);
        // Avoid adding lastName again when part of givenName
        if (StringUtils.isNotEmpty(lastName) && StringUtils.isNotEmpty(firstName) && firstName.toLowerCase().contains(lastName.toLowerCase())) {
            return firstName;
        }
        String displayName = context.getString(R.string.display_name_by_locale, firstName, lastName);
        return displayName.split(" #@@# ")[0];
    }
*/

    public static String getSystemNewLine() {
        return System.getProperty("line.separator", "\n");
    }
/*
    *//**
     * Converts a map object into JSON without using a Serializer
     *//*
    @SuppressWarnings("unchecked")
    public static String convertMapToJson(@Nullable Map<String, Object> map) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append('{');

        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                // Account for the starting brace
                if (strBuilder.length() > 1) {
                    strBuilder.append(',');
                }

                strBuilder.append('"')
                        .append(entry.getKey())
                        .append("\":");

                Object value = entry.getValue();
                if (value == null) {
                    strBuilder.append("null");
                } else if (isObjectMapWithKeyAsString(value)) {
                    strBuilder.append(convertMapToJson((Map<String, Object>) value));
                } else if (value instanceof Integer || value instanceof Float || value instanceof Double
                        || value instanceof Boolean || value instanceof Short || value instanceof Long) {
                    strBuilder.append(value);
                } else {
                    // Either this is an unsupported type or a String, so treat as a string
                    strBuilder.append('"')
                            .append(value)
                            .append('"');
                }
            }
        }

        strBuilder.append('}');
        return strBuilder.toString();
    }

    *//**
     * Convert the standard Base64 to Modified Base64 for Program identifiers
     * https://en.wikipedia.org/wiki/Base64#Program_identifiers
     * The '+' and '/' characters of standard Base64 are respectively replaced by '_' and '-'
     *
     * @param standardBase64
     * @return modifiedBase64
     *//*
    @Nullable
    public static String convertToModifiedBase64(@Nullable final String standardBase64) {
        if (StringUtils.isNullOrEmptyOrWhitespace(standardBase64)) {
            return standardBase64;
        }
        String modifiedBase64 = standardBase64.replace("+", "_");
        modifiedBase64 = modifiedBase64.replace("/", "-");
        return modifiedBase64;
    }

    *//**
     * Check whether or not the given object type is actually a map instance of Map<String, ?> type.
     *
     * This method was originally created to be used in conjunction with the convertMapToJson method. This method is used
     * to help determine whether or not the given object can be serialized into JSON. The current restriction is that the key
     * must be a string. If otherwise, we will simply call toString on the object for serialization purposes.
     *
     * Note: This method will fail if the object is of type map, but has varying types for its key value. A heavier operation can
     * be done to iterate through all keys in the map for a deep validation, but has been considered to be too costly and largely unnecessary.
     *
     * @param object object to be tested
     * @return true if the given object type is actually a map instance of Map<String, ?> type
     *//*
    private static boolean isObjectMapWithKeyAsString(Object object) {
        return object instanceof Map                                        // basic check that the object is a map
                && !((Map) object).isEmpty()                                 // check that the map is not empty
                && ((Map) object).keySet().toArray()[0] instanceof String;   // verify that the the first entry in the map has a key of type String
    }

    *//**
     * Encodes the URI component in UTF-8 encoding
     *
     * @param component URI component
     * @return encoded URI component or original component if encoding fails
     *//*
    public static String getEncodedUriComponent(@Nullable String component) {
        if (StringUtils.isEmpty(component)) {
            return StringUtils.EMPTY_STRING;
        }

        try {
            return URLEncoder.encode(component, StringConstants.CHARSET_UTF_8);
        } catch (UnsupportedEncodingException e) {
            return component;
        }
    }

    *//**
     * returns count of character from index where difference in character between two strings is found
     * returns
     *//*
    public static int differenceCount(@Nullable String str1, @Nullable String str2) {
        if (str1 == null && str2 == null) {
            return 0;
        }
        if (str1 == null) {
            return str2.length();
        }
        if (str2 == null) {
            return str1.length();
        }
        int at = indexOfDifference(str1, str2);
        if (at == -1) {
            return 0;
        }
        return str2.length() - at;
    }

    *//**
     * returns index at which difference in character between two strings is found
     * returns -1 when both params are identical
     *//*
    private static int indexOfDifference(@Nullable CharSequence cs1, @Nullable CharSequence cs2) {
        if (cs1 == cs2) {
            return -1;
        }
        if (cs1 == null || cs2 == null) {
            return 0;
        }
        int i;
        for (i = 0; i < cs1.length() && i < cs2.length(); ++i) {
            if (cs1.charAt(i) != cs2.charAt(i)) {
                break;
            }
        }
        if (i < cs2.length() || i < cs1.length()) {
            return i;
        }
        return -1;
    }

    *//**
     * @param length : length of the random string to be generated
     * @return numeric random String of provide length
     *//*
    public static String randomNumeric(int length) {
        return generateRandomString("01234456789", length);
    }

    *//**
     *
     * @param length : length of the random string to be generated
     * @return alphaNumeric random String of provide length
     *//*
    public static String randomAlphaNumeric(int length) {
        return generateRandomString(ALPHA_NUMERIC_STRING, length);
    }

    *//**
     * Reimplementation of jsoup's {@link StringUtil#normaliseWhitespace(String)}.
     * Modified to allow zero-width-join unicode characters (8205) as they are needed to
     * properly display compound emojis.
     * @param string String to format
     * @return Formatted String
     *//*
    @NonNull
    public static String normalizeWhitespace(@NonNull String string) {
        StringBuilder sb = StringUtil.stringBuilder();
        boolean lastWasWhite = false;
        int len = string.length();
        int c;
        for (int i = 0; i < len; i += Character.charCount(c)) {
            c = string.codePointAt(i);
            // invisible characters: zero width sp, zw non join, soft hyphen
            // zw join (8205) is okay
            boolean isInvisibleChar = Character.getType(c) == 16 && (c == 8203 || c == 8204 || c == 173);
            if (StringUtil.isActuallyWhitespace(c)) {
                if (lastWasWhite) {
                    continue;
                }
                sb.append(' ');
                lastWasWhite = true;
            } else if (!isInvisibleChar) {
                sb.appendCodePoint(c);
                lastWasWhite = false;
            }
        }
        return sb.toString();
    }

    public static String calculateMd5Hash(@NonNull String source) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(source.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    *//**
     * Generate a guid
     *//*
    public static String generateGUID() {
        return UUID.randomUUID().toString();
    }

    private static String generateRandomString(@NonNull String charactersToUse, int length) {
        if (length <= 0) {
            return EMPTY;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int character = (int) (Math.random() * charactersToUse.length());
            builder.append(charactersToUse.charAt(character));
        }

        return builder.toString();
    }

    *//**
     * Prevents a default instance of StringUtilities class from being created.
     *//*
    private StringUtilities() {
    }

    *//**
     * Generate String by List
     * example:
     * ["a","b","c"] returns
     * a\nb\nc\n
     * @param list To generate string
     * @return string
     *//*
    public static String getStringFromArray(List list) {
        if (list == null) {
            return StringUtils.EMPTY_STRING;
        }
        StringBuilder failureDetailBuilder = new StringBuilder();
        for (Object item : list) {
            failureDetailBuilder.append(item.toString());
            failureDetailBuilder.append('\n');
        }
        return failureDetailBuilder.toString();
    }

    *//**
     * Remove all Emoji chars from a string.
     *
     * @param source
     * @return a string without Emoji
     *//*
    @Nullable
    public static String filterEmoji(@Nullable String source) {
        if (StringUtils.isNullOrEmptyOrWhitespace(source)) {
            return source;
        }
        StringBuilder stringBuilder = new StringBuilder(source.length());
        int len = source.length();
        boolean skipFlag = false;
        for (int i = 0; i < len; i++) {
            if (skipFlag) {
                skipFlag = false;
                continue;
            }
            char hs = source.charAt(i);
            if (0xd800 <= hs && hs <= 0xdbff) {
                if (source.length() > i + 1) {
                    char ls = source.charAt(i + 1);
                    int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
                    if (0x1d000 <= uc && uc <= 0x1f9c0) {
                        skipFlag = true;
                        continue;
                    }
                }
            } else if (source.length() > i + 1) {
                char ls = source.charAt(i + 1);
                if (ls == 0x20e3 || ls == 0xfe0f || ls == 0xd83c) {
                    skipFlag = true;
                    continue;
                }
            } else {
                // non surrogate
                if ((0x2100 <= hs && hs <= 0x27ff) || (0x2B05 <= hs && hs <= 0x2b07)
                        || (0x2934 <= hs && hs <= 0x2935) || (0x3297 <= hs && hs <= 0x3299)) {
                    continue;
                } else if (hs == 0xa9 || hs == 0xae || hs == 0x303d || hs == 0x3030
                        || hs == 0x2b55 || hs == 0x2b1c || hs == 0x2b1b || hs == 0x2b50) {
                    continue;
                }
            }
            stringBuilder.append(hs);
        }
        return stringBuilder.toString();
    }

    *//**
     * The default {@link String#hashCode()} implements results in more than expected
     * collisions, particularly when strings have a common prefix. The might becomes
     * noticeable in case of URLs which do have common prefixes.
     * This is a fast implementation of hash function from guava.
     *//*
    @NonNull
    public static String goodFastHash(@Nullable String string) {
        // noinspection UnstableApiUsage
        return Hashing
                .goodFastHash(128)
                .hashString(ObjectUtils.defaultIfNull(string, EMPTY), StandardCharsets.UTF_8)
                .toString();
    }

    @VisibleForTesting
    public static String getNameAfterCleaningDisambiguationBrackets(@Nullable String name) {
        // Remove extra name/info with in brackets, e.g DMX User 1 (test)
        if (StringUtils.isEmpty(name)) {
            return name;
        }
        String clearName = name.split("\\(")[0];
        return clearName.trim();
    }

    @VisibleForTesting
    public static String getGivenNameForUser(@NonNull String fullName, @Nullable String givenName) {
        if (!StringUtils.isEmpty(givenName) && fullName.contains(givenName)) {
            return givenName;
        }
        return fullName.split(WHITE_SPACE)[0];
    }

    @VisibleForTesting
    public static String getSurNameForUser(@NonNull String fullName, @Nullable String surName) {
        if (!StringUtils.isEmpty(surName) && fullName.contains(surName)) {
            return surName;
        }
        String[] names = fullName.split(WHITE_SPACE);
        return names[names.length - 1];
    }*/
}
