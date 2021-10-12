/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.utilities.java;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** String utilities */
public final class StringUtils {
    public static final String EMPTY_STRING = "";
    public static final String HTML_COMMENT_REGEX = "<!--(.|\\s)*?-->";
    public static final String UNDERSCORE = "_";
    public static final char FORWARD_SLASH = '/';
    public static final String EMAIL_REGEX = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";
    public static final String EMAIL_REGEX_EXCLUDE_THREAD_ID_PATTERN =
            "[A-Za-z0-9+_.]+@(?!thread|unq|sfb|fed|sfc)[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";
    public static final String ELLIPSIS = "...";
    public static final String FULL_STOP = ".";
    public static final String ALPHANUMERIC_REGEX = "\\p{Alnum}";
    public static final String PUNCTUATION_REGEX = "\\p{Punct}";
    public static final String BASIC_MATH_OPERATOR_REGEX = "[=-÷×+]";
    public static final String SEMI_COLON = ";";
    public static final String HYPHEN = "-";
    public static final String GUID_REGEX = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}";
    private static final char NO_BREAK_SPACE = 160;
    public static final char SPACE = ' ';
    public static final char COMMA = ',';
    public static final String CURLY_BRACE_OPEN = "{";
    public static final String CURLY_BRACE_CLOSE = "}";

    /**
     * Returns true if string is null or 0 length, false otherwise.
     *
     * @param input - String to check.
     * @return - True if null or empty, otherwise false.
     */
    public static boolean isEmpty(@Nullable String input) {
        return input == null || input.length() == 0;
    }

    /**
     * Checks whether the input is empty or not, been empty is defined by the fact it doesn't
     * contain any character
     *
     * @param input {@link String} that will be tested
     * @return true if input contains at least one character, false otherwise
     */
    public static boolean isNotEmpty(@Nullable String input) {
        return input != null && !input.isEmpty();
    }

    /**
     * Returns true if string is null or 0 length or contains only whitespaces, false otherwise.
     *
     * @param input - String to check.
     * @return - True if null or empty (this includes whitespaces), otherwise false.
     */
    public static boolean isEmptyOrWhiteSpace(@Nullable String input) {
        String strippedString = stripLeading(input);
        return strippedString == null || strippedString.length() == 0;
    }

    /**
     * Checks whether the input is NULL, and if so, it will return an empty string.
     *
     * @param input {@link String} that will be tested
     * @return the original input if not NULL, empty {@link String} otherwise
     */
    @NonNull
    public static String emptyIfNull(@Nullable String input) {
        return input != null ? input : "";
    }

    /**
     * checks whether both {@link String} have the same content. NOTE: Casing is respected ex. Null
     * = Null "" = "" "aBc" = "aBc" "AbC" != "aBc"
     *
     * @param left {@link String} to be used as part of the comparison
     * @param right {@link String} to be used as part of the comparison
     * @return true if the content is the same in both {@link String}, false otherwise
     */
    public static boolean equals(@Nullable String left, @Nullable String right) {
        //noinspection StringEquality
        return (left == right) || ((left != null) && left.equals(right));
    }

    /**
     * checks whether both {@link String} have the same content. Note: Casing is not respected ex.
     * Null = Null "" = "" "aBc" = "aBc" "AbC" = "aBc"
     *
     * @param left {@link String} to be used as part of the comparison
     * @param right {@link String} to be used as part of the comparison
     * @return true if the content is the same in both {@link String}, false otherwise
     */
    public static boolean equalsIgnoreCase(@Nullable String left, @Nullable String right) {
        //noinspection StringEquality
        return (left == null && right == null) || ((left != null) && left.equalsIgnoreCase(right));
    }

    /**
     * Compares two strings lexicographically, ignoring case differences.
     *
     * @param left {@link String} to be used as part of the comparison
     * @param right {@link String} to be used as part of the comparison
     * @return 0 if the content is the same in both {@link String}, 1 if left is greater than right,
     *     -1 otherwise
     */
    public static int compareToIgnoreCase(@Nullable String left, @Nullable String right) {
        if (left == null && right == null) {
            return 0;
        } else if (left == null) {
            return -1;
        } else if (right == null) {
            return 1;
        }
        return left.compareToIgnoreCase(right);
    }

    /**
     * this will strip (remove) all the non-printing characters from the start of the {@link String}
     *
     * @param input the {@link String} to be stripped of empty spaces
     * @return the subset of the input with the leading non-printing characters removed
     */
    @Nullable
    public static String stripLeading(@Nullable String input) {
        if (input == null) {
            return null;
        }

        int firstImportantCharacter = 0;
        int inputLength = input.length();
        for (int i = 0; i < inputLength; i++) {
            char currentChar = input.charAt(i);
            if (!Character.isWhitespace(currentChar)) {
                break;
            }
            firstImportantCharacter++;
        }

        return input.substring(firstImportantCharacter);
    }

    /**
     * this will strip (remove) all the non-printing characters from the end of the {@link String}
     *
     * @param input the {@link String} to be stripped of empty spaces
     * @return the subset of the input with the trailing non-printing characters removed
     */
    @Nullable
    public static String stripTrailing(@Nullable String input) {
        if (input == null || input.length() == 0) {
            return input;
        }

        int inputLength = input.length();
        int lastImportantCharacter = inputLength;
        for (int i = inputLength - 1; i >= 0; i--) {
            char currentChar = input.charAt(i);
            if (!Character.isWhitespace(currentChar)) {
                break;
            }
            lastImportantCharacter--;
        }

        return input.substring(0, lastImportantCharacter);
    }

    /**
     * this will strip (remove) all the non-printing characters from both the start and end of the
     * {@link String}
     *
     * @param input the {@link String} to be stripped of empty spaces
     * @return the subset of the input with the leading and trailing non-printing characters removed
     */
    @Nullable
    public static String strip(@Nullable String input) {
        String inputStrippedLeading = stripLeading(input);
        return stripTrailing(inputStrippedLeading);
    }

    /**
     * Replaces all occurrences of a given of a substring in a string with another substring This
     * method implements a faster way of replacing all occurrences of a substring in a string with
     * another substring as compared to the trivial way of using replacingAll with regular
     * expressions to do so.
     */
    @Nullable
    public static String replace(
            @Nullable String text, @NonNull String replace, @NonNull String with) {
        if (isEmpty(text) || isEmpty(replace)) {
            return text;
        }

        int start = 0;
        int end;
        StringBuilder sb = new StringBuilder(text.length());
        int replaceLength = replace.length();
        while ((end = text.indexOf(replace, start)) != -1) {
            sb.append(text, start, end).append(with);
            start = end + replaceLength;
        }

        return sb.append(text.substring(start)).toString();
    }

    /**
     * Serializes the strings in a list to a string separated by a provided delimiter.
     *
     * <p>Does *not* include null or whitespace items.
     *
     * @param strings List of strings to join.
     * @param delimiter Delimiter to separate the strings.
     * @return A serialized string from joining the items in the list and separating by the
     *     delimiter.
     */
    @Nullable
    public static String join(@Nullable Collection<String> strings, @NonNull String delimiter) {
        return join(strings, delimiter, false);
    }

    /**
     * Serializes the strings in a list to a string separated by a provided delimiter.
     *
     * @param strings List of strings to join.
     * @param delimiter Delimiter to separate the strings.
     * @return A serialized string from joining the items in the list and separating by the
     *     delimiter.
     */
    @Nullable
    public static String joinIncludeEmptyItems(
            @Nullable Collection<String> strings, @NonNull String delimiter) {
        return join(strings, delimiter, true);
    }

    /**
     * Returns true if string is null or completely composed of whitespace or html non-breaking
     * spaces (char NO_BREAK_SPACE), false otherwise.
     *
     * @param input String to check if null or composed entirely of whitespace.
     * @return True if null or composed entirely of whitespace, false otherwise.
     */
    public static boolean isNullOrHtmlNonBreakingWhitespace(String input) {
        return (input == null) || isEmptyOrWhiteSpace(input.replace(NO_BREAK_SPACE, SPACE));
    }

    /**
     * Safe toString method that won't throw an exception or return null
     *
     * @param object Object to convert to a string
     * @return Object's toString method. If object is null, or the toString call returns null,
     *     returns a default empty string
     */
    @NonNull
    public static String safeToString(@Nullable Object object) {
        if (object == null) {
            return EMPTY_STRING;
        }

        try {
            String result = object.toString();

            return result != null ? result : EMPTY_STRING;
        } catch (Exception ex) {
            // Do nothing.
        }
        return EMPTY_STRING;
    }

    public static boolean containsPattern(CharSequence s, String pattern) {
        if (s == null || s.length() == 0) {
            return false;
        }

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(s);

        return m.find();
    }

    /**
     * Checks a string for null, returns an empty string if it is
     *
     * @param input Input string to check
     * @return Original string, unless it was null, in which case returns empty.
     */
    @NonNull
    public static String ensureNonNull(@Nullable String input) {
        return input != null ? input : "";
    }

    @NonNull
    public static String escapeDoubleQuotes(@Nullable String input) {
        if (input == null) {
            return "";
        }

        return input.replace("\"", "\\\"");
    }

    /**
     * Checks if a string is a GUID (aka UUID). E.g. 123e4567-e89b-12d3-a456-426655440000
     *
     * @param input input string to check
     * @return true if the string is a GUID and false otherwise
     */
    public static boolean isGuid(@Nullable String input) {
        if (input == null) {
            return false;
        }

        return Pattern.matches(GUID_REGEX, input);
    }

    @SuppressWarnings("PMD")
    public static boolean isNullOrEmptyOrWhitespace(String string) {
        return string == null || string.isEmpty() || string.trim().isEmpty();
    }

    /**
     * Serializes the strings in a list to a string separated by a provided delimiter.
     *
     * @param strings List of strings to join.
     * @param delimiter Delimiter to separate the strings.
     * @param includeEmptyItems If true, does include null or whitespace items.
     * @return A serialized string from joining the items in the list and separating by the
     *     delimiter.
     */
    @Nullable
    private static String join(
            @Nullable Collection<String> strings,
            @NonNull String delimiter,
            boolean includeEmptyItems) {
        if (strings == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (String str : strings) {
            String strippedStr = strip(str);
            if (isNotEmpty(strippedStr)) {
                sb.append(strippedStr).append(delimiter);
            } else if (includeEmptyItems) {
                sb.append(delimiter);
            }
        }

        // Remove last delimiter
        if (sb.length() > 0) {
            sb.setLength(sb.length() - delimiter.length());
        }

        return sb.toString();
    }

    /**
     * Returns length of string. return 0 for null or empty string
     *
     * @param string List of strings to join.
     * @return length of string. return 0 for null or empty string.
     */
    public static int length(@Nullable String string) {
        if (com.microsoft.teams.utilities.java.StringUtils.isEmpty(string)) {
            return 0;
        }
        return string.length();
    }

    @NonNull
    public static String normaliseQuery(@Nullable String s) {
        if (s == null) {
            return EMPTY_STRING;
        }
        return s.replaceAll("([\"\\\\])", "\\\\$1");
    }

    private StringUtils() {
        throw new UtilityInstantiationException();
    }

    /**
     * Returns the concat of two strings and dedpulicate the end of s1 and start of s2
     *
     * @param string1 input of one string.
     * @param string2 input of another string.
     * @return the concated string.
     */
    public static String concatAndDeduplicate(String string1, String string2) {
        String result = string1;
        int length = string2.length();
        while (length > 0) {
            String common = string2.substring(0, length);
            if (string1.endsWith(common)) {
                result = string1 + string2.substring(length);
                break;
            }
            length--;
        }
        return result;
    }
    /*
     * Check if one string contains another string in a case insensitive manner
     * @param source String in which pattern may/may not be contained
     * @param pattern String that may/may not be contained in the source string
     * @return true if pattern found in source, false if not found
     */
    public static boolean containsIgnoreCase(@Nullable String source, @Nullable String pattern) {
        if (isEmpty(source) && isEmpty(pattern)) {
            return true;
        } else if (isEmpty(source)) {
            return false;
        } else if (isEmpty(pattern)) {
            return true;
        } else {
            return source.toLowerCase(Locale.getDefault()).contains(pattern.toLowerCase(Locale.getDefault()));
        }
    }

    /**
     * This will strip (remove) all the given char from the start of the {@link String}
     *
     * @param input the {@link String} to be stripped of empty spaces
     * @return the subset of the input with the leading non-printing characters removed
     */
    @Nullable
    private static String stripLeading(@Nullable String input, char stripChar) {
        if (input == null) {
            return null;
        }

        int firstImportantCharacter = 0;
        int inputLength = input.length();
        for (int i = 0; i < inputLength; i++) {
            char currentChar = input.charAt(i);
            if (currentChar != stripChar) {
                break;
            }
            firstImportantCharacter++;
        }

        return input.substring(firstImportantCharacter);
    }

    /**
     * This will strip (remove) all the given char from the end of the {@link String}
     *
     * @param input the {@link String} to be stripped of empty spaces
     * @return the subset of the input with the trailing non-printing characters removed
     */
    @Nullable
    private static String stripTrailing(@Nullable String input, char stripChar) {
        if (input == null || input.length() == 0) {
            return input;
        }

        int inputLength = input.length();
        int lastImportantCharacter = inputLength;
        for (int i = inputLength - 1; i >= 0; i--) {
            char currentChar = input.charAt(i);
            if (currentChar != stripChar) {
                break;
            }
            lastImportantCharacter--;
        }

        return input.substring(0, lastImportantCharacter);
    }

    /**
     * This will strip (remove) all the given char from both the start and end of the
     * {@link String}
     *
     * @param input the {@link String} to be stripped of empty spaces
     * @return the subset of the input with the leading and trailing non-printing characters removed
     */
    @Nullable
    public static String strip(@Nullable String input, char stripChar) {
        String inputStrippedLeading = stripLeading(input, stripChar);
        return stripTrailing(inputStrippedLeading, stripChar);
    }

    /**
     * If the text exceeds maxLength, then this method truncates the text to maxLength - 3 and appends ellipsis at the end.
     * @param text Text that needs to be truncated.
     * @param maxLength Maximum length of characters to be supported
     * @return text if text has length lesser than maxLength, otherwise strip the text to maxLength - 3 characters and appends ellipsis at the end.
     */
    @Nullable
    public static String getTruncatedText(@Nullable String text, int maxLength) {
        if (com.microsoft.teams.utilities.java.StringUtils.isEmptyOrWhiteSpace(text) || text.length() <= maxLength) {
            return text;
        }

        String trimmedText = text.substring(0, maxLength - 3);
        return trimmedText.concat(ELLIPSIS);
    }
}
