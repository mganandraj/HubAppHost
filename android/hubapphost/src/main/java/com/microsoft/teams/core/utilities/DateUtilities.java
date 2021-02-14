package com.microsoft.teams.core.utilities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.microsoft.teams.logger.ILogger;
import com.microsoft.teams.logger.LogPriority;
import com.microsoft.teams.utilities.java.StringUtils;
// import com.microsoft.teams.core.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static com.microsoft.teams.core.utilities.DateUtilities.CustomDateTimeFormat.BEFORE_THIS_YEAR;
import static com.microsoft.teams.core.utilities.DateUtilities.CustomDateTimeFormat.HOURS_AND_MINUTES;
import static com.microsoft.teams.core.utilities.DateUtilities.CustomDateTimeFormat.LAST_WEEK;
import static com.microsoft.teams.core.utilities.DateUtilities.CustomDateTimeFormat.MONTH_DAY_HOURS_AND_MINUTES;
import static com.microsoft.teams.core.utilities.DateUtilities.CustomDateTimeFormat.MONTH_DAY_MONTH_ABBREV;
import static com.microsoft.teams.core.utilities.DateUtilities.CustomDateTimeFormat.NOW;
import static com.microsoft.teams.core.utilities.DateUtilities.CustomDateTimeFormat.THIS_YEAR;
import static com.microsoft.teams.core.utilities.DateUtilities.CustomDateTimeFormat.TODAY;
import static com.microsoft.teams.core.utilities.DateUtilities.CustomDateTimeFormat.YESTERDAY;

/***
 *  Util class that convert date to strings.
 */
public final class DateUtilities {
    public static final long ONE_WEEK_IN_MILLIS = TimeUnit.DAYS.toMillis(7);
    public static final long TWO_WEEKS_IN_MILLIS = 2 * ONE_WEEK_IN_MILLIS;
    public static final long FOUR_WEEKS_IN_MILLIS = 4 * ONE_WEEK_IN_MILLIS;

    public static final long ONE_MINUTE_IN_MILLIS = TimeUnit.MINUTES.toMillis(1);
    public static final long THIRTY_MINUTES_IN_MILLIS = TimeUnit.MINUTES.toMillis(30);
    public static final long ONE_HOUR_IN_MILLIS = TimeUnit.HOURS.toMillis(1);
    public static final long ONE_DAY_IN_MILLIS = TimeUnit.DAYS.toMillis(1);
    public static final long THIRTY_DAYS_IN_MILLIS = TimeUnit.DAYS.toMillis(30);

    public static final String TIMEZONE_UTC = "UTC";

    // Time flags (24 hours vs 12 hours)
    @SuppressWarnings("deprecation")
    private static final int HOUR_MINUTES_12_HOUR = DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_12HOUR;
    @SuppressWarnings("deprecation")
    private static final int HOUR_MINUTES_24_HOUR = DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_24HOUR;

    // Date flags
    private static final int MONTH_DAY_YEAR = DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NUMERIC_DATE; //"M/d/yyyy", "d/M/yyyy"
    private static final int MONTH_DAY = MONTH_DAY_YEAR | DateUtils.FORMAT_NO_YEAR; //"M/d", "d/M"
    private static final int MONTH_DAY_YEAR_ABBREV = DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_ABBREV_MONTH; // Feb 18, 2017
    private static final int MONTH_DAY_MONTH_ABBREV_FLAGS = DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_MONTH; // Feb 18

    private static final String UTC_OFFSET_FORMAT = "%s%02d:%02d";
    private static final int NUMBER_OF_MINUTES_PER_HOUR = 60;
    public static final String UTC_ZONE = "UTC";
    public static final String GMT_TIME_ZONE = "GMT";

    private static final SimpleDateFormat MONTH_FORMAT = new SimpleDateFormat("MMM", Locale.getDefault());
    public static final String UTC_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String UTC_DATE_FORMAT_WITH_MILLI_SEC = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'";
    private static final SimpleDateFormat UTC_SIMPLE_DATE_FORMAT = new SimpleDateFormat(UTC_DATE_FORMAT, Locale.US);

    /**
     * This method should be used when you need to display the day breaker in chat or need date/time of a message.
     *
     * Today 12:00 AM         - If the date is within 6 days, then we show "Today", "Yesterday", "Monday" etc.
     * March 12 12:00 AM      - If the date is beyond 6 days old, then we show "Date at Time" format.
     *
     * @param context the {@link Context} which will be used to find the localized date formats
     * @param targetTimeInMillis {@link Date} in milliseconds that will be represented in a relative format
     * @return a String representation of the {@code targetDate}, formatted according to the rules explained above
     */
    public static String formatMessageDateTime(@NonNull Context context, long targetTimeInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        String formattedTime = DateUtils.formatDateTime(context, targetTimeInMillis, DateUtils.FORMAT_SHOW_TIME);
        String spaceString = " ";

        // return Today string if it is from same day.
        if (targetTimeInMillis >= calendar.getTimeInMillis()) {
            return /*context.getString(R.string.date_format_today)*/ "Today" + spaceString + formattedTime;
        }

        // return Yesterday string
        calendar.add(Calendar.DATE, -1);
        if (targetTimeInMillis >= calendar.getTimeInMillis()) {
            return /*context.getString(R.string.date_format_yesterday)*/ "Yesterday" + spaceString + formattedTime;
        }

        // return day strings if its less than 6 days old
        calendar.add(Calendar.DATE, -5);
        if (targetTimeInMillis >= calendar.getTimeInMillis()) {
            return DateUtils.formatDateTime(context, targetTimeInMillis, DateUtils.FORMAT_SHOW_WEEKDAY) + spaceString + formattedTime;
        }

        // return the date string if its beyond 6 days old.
        return DateUtils.formatDateTime(context, targetTimeInMillis, DateUtils.FORMAT_SHOW_DATE) + spaceString + formattedTime;
    }

    public static String formatMonth(@NonNull Calendar date) {
        synchronized (MONTH_FORMAT) {
            return MONTH_FORMAT.format(date.getTime());
        }
    }

    @NonNull
    public static String getRelativeDateString(@NonNull Context context, long targetTimeInMillis, int dateFormatFlag) {
        Calendar compareTime = Calendar.getInstance();
        compareTime.add(Calendar.DAY_OF_MONTH, -1);
        compareTime.set(Calendar.HOUR_OF_DAY, 0);
        compareTime.set(Calendar.MINUTE, 0);
        compareTime.set(Calendar.SECOND, 0);
        compareTime.set(Calendar.MILLISECOND, 0);

        // Older than yesterday
        if (compareTime.getTimeInMillis() > targetTimeInMillis) {
            return DateUtils.formatDateTime(context, targetTimeInMillis, dateFormatFlag);
        }

        compareTime.add(Calendar.DAY_OF_MONTH, 1);

        // Older than today
        if (compareTime.getTimeInMillis() > targetTimeInMillis) {
            return "Yesterday";// context.getString(R.string.date_format_yesterday);
        }

        compareTime.add(Calendar.DAY_OF_MONTH, 1);

        // Older than tomorrow
        if (compareTime.getTimeInMillis() > targetTimeInMillis) {
            return "Today";// /*context.getString(R.string.date_format_today)*/"Today";
        }

        compareTime.add(Calendar.DAY_OF_MONTH, 1);

        // Older than 2 days from today
        if (compareTime.getTimeInMillis() > targetTimeInMillis) {
            return "Tomorrow";// /*context.getString(R.string.date_format_tomorrow)*/"Tomorrow";
        }

        return DateUtils.formatDateTime(context, targetTimeInMillis, dateFormatFlag);
    }

    /**
     * returns relative date
     * ex. Today, Yesterday, Tomorrow or Feb 5 (MMM dd)
     */
    public static String getRelativeDateString(@NonNull Context context, long targetTimeInMillis) {
        return getRelativeDateString(context, targetTimeInMillis, MONTH_DAY_YEAR_ABBREV);
    }

    /**
     * returns relative date
     * Yesterday, Today, Tomorrow
     * Day name     - If the date is incoming and within a week (ex. Sunday/Monday/...)
     * MM/dd        - If the date is beyond a week, but within this year
     * MM/dd/yy     - If the date is beyond this year
     */
    @NonNull
    public static String getRelativeDateStringWithWeekDay(@NonNull Context context, long targetTimeInMillis, int dateFormatFlag) {
        Calendar compareTime = Calendar.getInstance();
        compareTime.add(Calendar.DAY_OF_MONTH, -1);
        compareTime.set(Calendar.HOUR_OF_DAY, 0);
        compareTime.set(Calendar.MINUTE, 0);
        compareTime.set(Calendar.SECOND, 0);
        compareTime.set(Calendar.MILLISECOND, 0);

        // Older than yesterday
        if (compareTime.getTimeInMillis() > targetTimeInMillis) {
            return DateUtils.formatDateTime(context, targetTimeInMillis, dateFormatFlag);
        }

        // yesterday
        compareTime.add(Calendar.DAY_OF_MONTH, 1);
        if (compareTime.getTimeInMillis() > targetTimeInMillis) {
            return "Yesterday";//context.getString(R.string.date_format_yesterday);
        }

        // today
        compareTime.add(Calendar.DAY_OF_MONTH, 1);
        if (compareTime.getTimeInMillis() > targetTimeInMillis) {
            return /*context.getString(R.string.date_format_today)*/"Today";
        }

        // tomorrow
        compareTime.add(Calendar.DAY_OF_MONTH, 1);
        if (compareTime.getTimeInMillis() > targetTimeInMillis) {
            return /*context.getString(R.string.date_format_tomorrow)*/"Tomorrow";
        }

        // within one week
        compareTime.add(Calendar.DAY_OF_MONTH, 5);
        if (compareTime.getTimeInMillis() > targetTimeInMillis) {
            return DateUtils.formatDateTime(context, targetTimeInMillis, DateUtils.FORMAT_SHOW_WEEKDAY);
        }

        // beyond one week
        return DateUtils.formatDateTime(context, targetTimeInMillis, dateFormatFlag);
    }


    /**
     * Platform api for relative time span (w.r.t current) which doesn't require {@link Context}
     */
    @NonNull
    public static String getRelativeTimeSpanString(long time) {
        return DateUtils.getRelativeTimeSpanString(time, System.currentTimeMillis(), 0).toString();
    }

    public static boolean isSameDay(long lhsDate, long rhsDate) {
        Calendar startDayWithNoTime = getCalendarInstanceWithNoTime(lhsDate);

        // Older than startDate
        if (startDayWithNoTime.getTimeInMillis() > rhsDate) {
            return false;
        }

        startDayWithNoTime.add(Calendar.DAY_OF_MONTH, 1);

        // Older than startDate +1
        return startDayWithNoTime.getTimeInMillis() > rhsDate;
    }

    public static boolean isTodayOrLater(long timeInMs) {
        Calendar calendar = Calendar.getInstance();
        // Setting the calendar to the start of Today at 00:00:00 000
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return timeInMs >= calendar.getTimeInMillis();
    }

    public static boolean isTodayOrBefore(long timeInMs) {
        return getCalendarInstanceWithNoTime(timeInMs).getTimeInMillis() <= getTodayWithNoTime().getTimeInMillis();
    }

    public static boolean isTodayOrBefore(@NonNull Date date) {
        return getDateWithNoTime(date).getTime() <= getTodayWithNoTime().getTimeInMillis();
    }

    public static boolean isToday(long timeInMs) {
        return getCalendarInstanceWithNoTime(timeInMs).getTimeInMillis() == getTodayWithNoTime().getTimeInMillis();
    }

    public static boolean isToday(@NonNull Date date) {
        return getDateWithNoTime(date).getTime() == getTodayWithNoTime().getTimeInMillis();
    }

    public static boolean isBeforeOfToday(@NonNull Date date) {
        return getDateWithNoTime(date).getTime() < getTodayWithNoTime().getTimeInMillis();
    }

    public static boolean isYesterday(long timeInMs) {
        return getCustomDateTimeFormat(timeInMs, true) == YESTERDAY;
    }

    public static boolean isCurrentYear(@NonNull Date date) {
        Calendar calendarDay = Calendar.getInstance();
        Calendar currentDay = Calendar.getInstance();
        calendarDay.setTime(date);
        currentDay.setTimeInMillis(System.currentTimeMillis());
        return calendarDay.get(Calendar.YEAR) == currentDay.get(Calendar.YEAR);
    }

    /**
     * This method should be used when you need to display just the hours and minutes of a date irrespectively of the day or how far back that date is.
     *
     * HH:mm        - If the system format is set to 24hrs
     * HH:mm am/pm     - If the system format is set to 12hrs
     *
     * @param context the {@link Context} which will be used to find the localized date formats
     * @param targetTimeInMillis {@link Date} in milliseconds that will be represented in a relative format
     * @return a String representation of the {@code targetDate}, formatted according to the rules explained above
     */
    public static String formatHoursAndMinutes(@NonNull Context context, long targetTimeInMillis) {
        return formatDateTime(context, targetTimeInMillis, HOURS_AND_MINUTES, false);
    }

    /**
     * This method should be used when you need to display Abbrev Month Day Year.
     *
     * @param targetTimeInMillis {@link Date} in milliseconds that will be formatted
     */
    public static String formatMonthDateAndYear(long targetTimeInMillis) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormats.MONTH_DATE_YEAR, Locale.getDefault());
        return simpleDateFormat.format(new Date(targetTimeInMillis));
    }

    /**
     * This method should be used when you need to display Abbrev Month Day.
     *
     * @param targetTimeInMillis {@link Date} in milliseconds that will be formatted
     */
    public static String formatMonthDate(long targetTimeInMillis) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormats.MONTH_DATE, Locale.getDefault());
        return simpleDateFormat.format(new Date(targetTimeInMillis));
    }

    /**
     * This method should be used when you need to display day date month year time.
     *
     * Monday, 11 January 2021 16:15
     *
     * @param targetTimeInMillis {@link Date} in milliseconds that will be represented in a relative format
     * @return a String representation of the {@code targetDate}, formatted according to the rules explained above
     */
    @NonNull
    public static String formatDayDateMonthYearTime(long targetTimeInMillis) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormats.DAY_DATE_MONTH_YEAR_TIME, Locale.getDefault());
        return simpleDateFormat.format(new Date(targetTimeInMillis));
    }

    /**
     * Workaround for a bug in Android, date.toString() can cause crashes on Android 8.0 and 8.1,
     * more details in https://issuetracker.google.com/issues/110848122
     */
    public static String dateToString(Date date) {
        try {
            return date.toString();
        } catch (AssertionError e) {
            return StringUtils.EMPTY_STRING;
        }
    }

    /**
     * This method should be used when you need to display just the hours and minutes of a date irrespectively of the day or how far back that date is.
     *
     * Jan 12, HH:mm        - If the system format is set to 24hrs
     * Jan 12, HH:mm am/pm     - If the system format is set to 12hrs
     *
     * @param context the {@link Context} which will be used to find the localized date formats
     * @param targetTimeInMillis {@link Date} in milliseconds that will be represented in a relative format
     * @return a String representation of the {@code targetDate}, formatted according to the rules explained above
     */
    public static String formatMonthDayHoursAndMinutes(@NonNull Context context, long targetTimeInMillis) {
        return formatDateTime(context, targetTimeInMillis, MONTH_DAY_HOURS_AND_MINUTES, false);
    }

    /**
     * This method should be used when you need to display just the hours and minutes of a date irrespectively of the day or how far back that date is.
     *
     * Jan 12
     *
     * @param context the {@link Context} which will be used to find the localized date formats
     * @param targetTimeInMillis {@link Date} in milliseconds that will be represented in a relative format
     * @return a String representation of the {@code targetDate}, formatted according to the rules explained above
     */
    public static String formatMonthDayWithMonthAbbrev(@NonNull Context context, long targetTimeInMillis) {
        return formatDateTime(context, targetTimeInMillis, MONTH_DAY_MONTH_ABBREV, false);
    }

    /**
     * This method should be used when you need to display just the hours and minutes of a date irrespectively of the day or how far back that date is.
     * It is similar with {@link #formatMonthDayWithMonthAbbrev(Context, long)}, but in format UTC time zone.
     *
     * Jan 12
     *
     * @param context the {@link Context} which will be used to find the localized date formats
     * @param targetTimeInMillis {@link Date} in milliseconds that will be represented in a relative format
     * @return a String representation of the {@code targetDate} in UTC time zone, formatted according to the rules explained above
     */
    public static String formatMonthDayWithMonthAbbrevInUTC(@NonNull Context context, long targetTimeInMillis) {
        return DateUtils.formatDateRange(context, new Formatter(Locale.getDefault()), targetTimeInMillis, targetTimeInMillis,
                MONTH_DAY_MONTH_ABBREV_FLAGS, TimeZone.getTimeZone(TIMEZONE_UTC).getID()).toString();
    }

    /**
     * This method should be used when you need to display month day year including the present year.
     *
     * dd/mm/yyyy or mm/dd/yyyy
     *
     * @param context the {@link Context} which will be used to find the localized date formats
     * @param targetTimeInMillis {@link Date} in milliseconds that will be represented in a relative format
     * @return a String representation of the {@code targetDate}, formatted according to the rules explained above
     */
    public static String formatMonthDayYear(@NonNull Context context, long targetTimeInMillis) {
        return DateUtils.formatDateTime(context, targetTimeInMillis, DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_YEAR);
    }

    /**
     * This method parses date returned from formatMonthDayYear
     * should be used when you need to display month/day/year(short) including the present year.
     *
     * dd/mm/yy or mm/dd/yy
     *
     * @param context the {@link Context} which will be used to find the localized date formats
     * @param date {@link Date} in milliseconds that will be represented in a relative format
     * @return a String representation of the {@code targetDate}, formatted according to the rules explained above
     */
    public static String formatMonthDayYearShort(@NonNull Context context, Date date) {
        String monthDayYear = formatMonthDayYear(context, date.getTime());
        // yyyy/mm/dd, index = 4
        // mm/dd/yyyy, index <= 2
        char separator = '/';
        int index = monthDayYear.indexOf(separator);
        if (index == 4) {
            return monthDayYear.substring(2);
        } else if (index <= 2) {
            return monthDayYear.substring(0, monthDayYear.indexOf(separator, monthDayYear.indexOf(separator) + 1) + 1).concat(monthDayYear.substring(monthDayYear.length() - 2));
        }
        return monthDayYear;
    }

    /**
     * This method should be used when you need to display weekday month date and year.
     *
     * Monday, March 12, 2018
     *
     * @param context the {@link Context} which will be used to find the localized date formats
     * @param date {@link Date} that will be represented in a relative format
     * @return a String representation of the {@code targetDate}, formatted according to the rules explained above
     */
    public static String formatFullWeekdayMonthDate(@NonNull Context context, @NonNull Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormats.FULL_DAY_MONTH_DATE_YEAR, Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    /**
     * This method should be used when you need to display weekday month date and year.
     *
     * Mon, March 12, 2018
     *
     * @param context the {@link Context} which will be used to find the localized date formats
     * @param date {@link Date} that will be represented in a relative format
     * @return a String representation of the {@code targetDate}, formatted according to the rules explained above
     */
    public static String formatWeekdayMonthDate(@NonNull Context context, @NonNull Date date) {
        return DateUtils.formatDateTime(context, date.getTime(), DateUtils.FORMAT_ABBREV_WEEKDAY
                | DateUtils.FORMAT_SHOW_WEEKDAY
                | DateUtils.FORMAT_SHOW_DATE
                | DateUtils.FORMAT_ABBREV_MONTH
                | DateUtils.FORMAT_SHOW_YEAR);
    }

    /**
     * This method should be used when you need to display a TimeStamp relative to the device current TimeZone.
     * This method has different behavior depending on how far away the date is, you can expect this behavior for
     * en_US locales, but, keep in mind that they might look a little different for other locales.
     *
     * HH:mm        - If the date is within Today's range (ex. 04:20)
     * Yesterday    - If the date is within yesterday range (ex. )
     * Day name     - If the date is before yesterday, but within a week (ex. )
     * MM/dd        - If the date is more than a week old, but within this year (ex. )
     * MM/dd/yy     - If the date is before this year (ex. )
     *
     * @param context the {@link Context} which will be used to find the localized date formats
     * @param targetTimeInMillis {@link Date} in milliseconds that will be represented in a relative format
     * @return a String representation of the {@code targetDate}, formatted according to the rules explained above
     */
    public static String formatDateRelative(@NonNull Context context, long targetTimeInMillis) {
        final Date targetTime = new Date(targetTimeInMillis);
        return formatDateRelative(context, targetTime);
    }

    /**
     * This method should be used when you need to display a TimeStamp relative to the device current TimeZone.
     * This method has different behavior depending on how far away the date is, you can expect this behavior for
     * en_US locales, but, keep in mind that they might look a little different for other locales.
     *
     * Now          - If the data is sent just now
     * HH:mm        - If the date is within Today's range (ex. 04:20)
     * Yesterday    - If the date is within yesterday range (ex. )
     * Day name     - If the date is before yesterday, but within a week (ex. Sunday/Monday/...)
     * MM/dd        - If the date is more than a week old, but within this year (ex. )
     * MM/dd/yy     - If the date is before this year (ex. )
     *
     * @param context the {@link Context} which will be used to find the localized date formats
     * @param targetDate {@link Date} that will be represented in a relative format
     * @return a String representation of the {@code targetDate}, formatted according to the rules explained above
     */
    public static String formatDateRelative(@NonNull Context context, @NonNull Date targetDate) {
        return formatDate(context, targetDate, true, true);
    }

    public static String formatDate(Context context, Date date) {
        return formatDate(context, date, false, false);
    }

    public static String formatDateUsingShortFormat(Context context, Date date) {
        return formatDate(context, date, true, false);
    }

    /**
     * This method should be used when you need to format a date to system format that is set by the user in Settings.
     *
     * @param context the {@link Context}
     * @param date    {@link Date} that will be represented in a system user selected format
     * @return a String representation of the {@code date}, formatted according to the rules explained above
     */
    public static String formatDateWithSystemDateFormatSelected(@NonNull Context context, long date) {
        Date dateToFormat = new Date(date);
        String format = Settings.System.getString(context.getContentResolver(), Settings.System.DATE_FORMAT);
        if (TextUtils.isEmpty(format)) {
            format = DateFormats.MONTH_DATE_YEAR;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(dateToFormat);
    }

    /**
     * This method should be used when you need to format a date to system format that is set by the user in Settings.
     *
     * @param context the {@link Context}
     * @param date    {@link Date} that will be represented in a system user selected format
     * @return a String representation of the {@code date}, formatted according to the rules explained above
     */
    public static String formatDateToDayDateMonth(@NonNull Context context, long date) {
        Date dateToFormat = new Date(date);
        SimpleDateFormat sdf = new SimpleDateFormat(DateFormats.DAY_DATE_MONTH, Locale.getDefault());
        return sdf.format(dateToFormat);
    }

    /**
     * In debug logs, sometimes we need full date and time (till milliseconds and timezone) to compare out traces by eyeballing them
     * <br>
     * Pattern "yyyy-MM-dd 'T' HH:mm:ss.SSS Z" --> 2018-05-04 T 19:19:03.745 -0700 (sample)
     *
     * @param milliseconds (optional) if not provided, then returns current time and date device default locale
     * @return returns string as seen above
     */
    public static String fullDateTime(@NonNull Locale locale, long... milliseconds) {
        final Date currentOrParamDate;
        if (milliseconds.length == 0) {
            currentOrParamDate = new Date();
        } else {
            currentOrParamDate = new Date(milliseconds[0]);
        }
        return new SimpleDateFormat("yyyy-MM-dd 'T' HH:mm:ss.SSS Z", locale).format(currentOrParamDate);
    }

    /**
     * returns current day with no time
     */
    public static Calendar getTodayWithNoTime() {
        return getCalendarInstanceWithNoTime(System.currentTimeMillis());
    }

    /**
     * Compares calendar objects with the list of calendar objects
     *
     * @param calendarList List of calendar objects
     * @param calendar calendar object
     * @return true if calendar date is found in list, false otherwise
     */
    public static boolean isDayPresentIn(@NonNull List<Calendar> calendarList, @NonNull Calendar calendar) {
        boolean isSameDay = false;
        for (Calendar cal : calendarList) {
            isSameDay = isSameDay(cal.getTimeInMillis(), calendar.getTimeInMillis());
            if (isSameDay) {
                return isSameDay;
            }
        }
        return isSameDay;
    }

    /*
     * return date with no time example : 12/1/2017 00:00:00
     * @param date date in which time need to be removed
     */
    public static Date getDateWithNoTime(@NonNull Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.AM_PM, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /*
     * sets date with no time example : 12/1/2017 00:00:00
     * @param cal date in which time need to be removed
     */
    public static void setDateWithNoTime(@NonNull Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.AM_PM, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }
    /**
     * @return offset of local time from utc time in the format [+-][hours]:[min]
     */
    public static String getUTCOffset(@NonNull Locale locale) {
        TimeZone tz = TimeZone.getDefault();
        int offsetInMilliSeconds = tz.getOffset((new Date()).getTime());
        String sign = (offsetInMilliSeconds >= 0 ? "+" : "-");

        return String.format(locale,
                UTC_OFFSET_FORMAT,
                sign,
                Math.abs(offsetInMilliSeconds / ONE_HOUR_IN_MILLIS),
                Math.abs((offsetInMilliSeconds / ONE_MINUTE_IN_MILLIS) % NUMBER_OF_MINUTES_PER_HOUR));
    }

    public static Calendar getCalendarInstanceWithNoTime(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.AM_PM, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    /**
     * Get the length of time between now and the next minute
     *
     * @return length of time in millis
     */
    public static long getTimeToNextMinute() {
        long now = System.currentTimeMillis();
        long difference = now - (TimeUnit.MILLISECONDS.toMinutes(now) * ONE_MINUTE_IN_MILLIS);
        return Math.max((ONE_MINUTE_IN_MILLIS - difference), 0);
    }

    /**
     * Calculate the time difference in days, hours, minutes
     */
    public static long getTimeDiffInDays(long fromTimeStamp, long toTimeStamp) {
        long diff = Math.abs(toTimeStamp - fromTimeStamp);
        return diff / ONE_DAY_IN_MILLIS;
    }

    // negtive return indicats toTime is older than fromTime
    public static long getTimeDiffInFutureDays(long fromTimeStamp, long toTimeStamp) {
        return (toTimeStamp - fromTimeStamp) / ONE_DAY_IN_MILLIS;
    }

    public static long getTimeDiffInHours(long fromTimeStamp, long toTimeStamp) {
        long diff = Math.abs(toTimeStamp - fromTimeStamp);
        return diff / ONE_HOUR_IN_MILLIS;
    }

    public static long getTimeDiffInMinutes(long fromTimeStamp, long toTimeStamp) {
        long diff = Math.abs(toTimeStamp - fromTimeStamp);
        return diff / ONE_MINUTE_IN_MILLIS;
    }

    /**
     * Calculates absolute number of days between fromTime and toTime
     *
     * @return absolute number of days between fromTime and toTime
     */
    public static int daysBetween(@NonNull final Calendar day1, @NonNull final Calendar day2) {
        Calendar dayOne = (Calendar) day1.clone();
        Calendar dayTwo = (Calendar) day2.clone();

        if (dayTwo.get(Calendar.YEAR) == dayOne.get(Calendar.YEAR)) {
            return Math.abs(dayTwo.get(Calendar.DAY_OF_YEAR) - dayOne.get(Calendar.DAY_OF_YEAR));
        } else {
            if (dayOne.get(Calendar.YEAR) > dayTwo.get(Calendar.YEAR)) {
                //swap them
                Calendar temp = dayTwo;
                dayTwo = dayOne;
                dayOne = temp;
            }
            int extraDays = 0;

            int dayOneOriginalYearDays = dayTwo.get(Calendar.DAY_OF_YEAR);

            while (dayTwo.get(Calendar.YEAR) > dayOne.get(Calendar.YEAR)) {
                dayTwo.add(Calendar.YEAR, -1);
                // getActualMaximum() important for leap years
                extraDays += dayTwo.getActualMaximum(Calendar.DAY_OF_YEAR);
            }

            return extraDays - dayOne.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays;
        }
    }

    /**
     * Formats a date with the weekday. It will auto-append the year if the date is from a different
     * year than now.
     *
     * Example:
     *  - Sunday, January 3
     *  - Sunday, January 3, 1982
     */
    public static String formatDateWithWeekDay(Context context, long date) {
        return DateUtils.formatDateTime(context, date, DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_WEEKDAY);
    }

    public static Date parseDateByFormat(String dateString, String format, TimeZone timezone) throws ParseException {
        java.text.DateFormat utcDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        utcDateFormat.setTimeZone(timezone);
        return utcDateFormat.parse(dateString);
    }

    public static String getFormattedDate(Date date, String format, TimeZone timezone) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        dateFormat.setTimeZone(timezone);
        return dateFormat.format(date);
    }

    public static String getFormattedDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        return dateFormat.format(date);
    }

    private static String formatDate(Context context, Date date, boolean useShortFormat, boolean isRelative) {
        if (date == null) {
            return null;
        }

        final long targetDateInMillis = date.getTime();
        return formatDateTime(context, targetDateInMillis, getCustomDateTimeFormat(targetDateInMillis, isRelative), useShortFormat);
    }

    public static String formatInApiFormat(@Nullable Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtilities.DateFormats.API_DATE_FORMAT, Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }

    public static String formatInISOFormat(@Nullable Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormats.ISO_8061_DATE_FORMAT, Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }

    @Nullable
    public static Date parseInApiFormat(@Nullable String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtilities.DateFormats.API_DATE_FORMAT, Locale.ENGLISH);
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    @Nullable
    public static Date parseInYearMonthDateFormat(@Nullable String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }
        try {
            return parseDateByFormat(date, DateFormats.YEAR_MONTH_DATE, TimeZone.getDefault());
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Helper to convert date formats.
     *
     * @param dateString - in ISO 8061 format. https://en.wikipedia.org/wiki/ISO_8601
     * @return date string in API format {@value DateFormats#API_DATE_FORMAT}
     */
    @Nullable
    public static String convertDateInISO8061ToApiFormat(@Nullable String dateString) {
        if (!TextUtils.isEmpty(dateString)) {
            SimpleDateFormat dateformatISO8061 = new SimpleDateFormat(DateFormats.ISO_8061_DATE_FORMAT, Locale.ENGLISH);
            try {
                Date date = dateformatISO8061.parse(dateString);
                if (date != null) {
                    SimpleDateFormat dateFormatAPI = new SimpleDateFormat(DateUtilities.DateFormats.API_DATE_FORMAT, Locale.ENGLISH);
                    return dateFormatAPI.format(date);
                }
            } catch (ParseException e) {
                return null;
            }
        }
        return null;
    }

    public static String convertToUTC(@NonNull Date date) {
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat(DateUtilities.DateFormats.API_DATE_FORMAT);
            sdf.setTimeZone(TimeZone.getTimeZone(UTC_ZONE));
            return sdf.format(date);
        } catch (Exception ex) {
            return StringUtils.EMPTY_STRING;
        }
    }

    public static String convertToUTCWithoutTimeZone(@NonNull Date date) {
        try {
            String dateString = convertToUTC(date);
            if (StringUtils.isEmpty(dateString)) {
                return dateString;
            } else {
                // remove timezone info from dateString
                String newDateString;
                int indexOfPlus = dateString.indexOf('+');
                if (indexOfPlus > 0) {
                    newDateString = dateString.substring(0, indexOfPlus);
                } else {
                    newDateString = dateString;
                }
                return newDateString;
            }
        } catch (Exception ex) {
            return StringUtils.EMPTY_STRING;
        }
    }

    public static String convertToUTC(@Nullable String date) {
        if (TextUtils.isEmpty(date)) {
            return date;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtilities.DateFormats.API_DATE_FORMAT, Locale.ENGLISH);
        Date dateObj;
        try {
            dateObj = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(UTC_ZONE));
        return simpleDateFormat.format(dateObj);
    }

    public static String convertToSimpleFormat(@NonNull Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtilities.DateFormats.SIMPLE_DATE_FORMAT, Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(GMT_TIME_ZONE));
        return simpleDateFormat.format(date);
    }

    public static String convertToCancelRequestFormat(@NonNull Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtilities.DateFormats.CANCEL_REQUEST_TIME_FORMAT, Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }

    /***
     * Gets the next working day time in millis for a specified hour/minute/A.M/P.M. designation.
     *
     * @param hourOfDay - the hour must be in range of [0, 12]
     * @param minuteOfDay - the hour must be in range of [0, 59]
     * @param meridianDesignation - must be one of Calendar.AM or Calendar.PM
     * @return - the next working day at the specific hour/minute/meridian designation in millis
     */
    public static long getNextWorkingDayTimeAtSpecificHourMinuteInMillis(int hourOfDay, int minuteOfDay, int meridianDesignation) {
        Calendar today = Calendar.getInstance();
        int dayOffset = 1;
        int weekday = today.get(Calendar.DAY_OF_WEEK);
        // need the next working week monday, offset is 3 numerical days from Friday, 2 numerical days from Saturday
        if (weekday == Calendar.FRIDAY) {
            dayOffset = 3;
        } else if (weekday == Calendar.SATURDAY) {
            dayOffset = 2;
        }

        today.add(Calendar.DAY_OF_YEAR, dayOffset);
        today.set(Calendar.HOUR, hourOfDay);
        today.set(Calendar.MINUTE, minuteOfDay);
        today.set(Calendar.AM_PM, meridianDesignation);

        return today.getTimeInMillis();
    }

    /**
     * Gets weekday's full name like "Sunday" from day of week indicator.
     *
     * @param dayOfWeek field indicating the day of week
     * @return Alphabetical value of that weekday.
     */
    public static String getWeekdayFullName(int dayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
    }

    public static int getTodayAsInt() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1 /*https://stackoverflow.com/questions/19348065/calendar-month-gives-wrong-value*/;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        return year * 10000 + month * 100 + dayOfMonth;
    }

    /**
     * Converts a date/time value in Seconds Since Epoch to a formatted string
     *
     * @param format Desired format string, e.g. H:mm:ss
     * @param time Time in seconds since Epoch
     * @return Formatted date/time string
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @NonNull
    public static String getFormattedDateTimeFromEpochSeconds(String format, long time) {
        Date date = new Date(time * 1000);
        return getDateFormat(format, date);
    }

    /**
     * get first day of month without time
     */
    public static Calendar getCurrentMonthsFirstDay() {
        Calendar calendar = getTodayWithNoTime();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar;
    }

    /**
     * get first day of prev month without time
     */
    public static Calendar getPrevMonthsFirstDay() {
        Calendar calendar = getCurrentMonthsFirstDay();
        calendar.add(Calendar.MONTH, -1);
        return calendar;
    }

    /**
     * get last day of month without time
     */
    public static Calendar getCurrentMonthsLastDay() {
        Calendar calendar = getTodayWithNoTime();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar;
    }

    /**
     * To get the first date if next month if provided date is not first.
     *
     * @param calendar date to process
     */
    public static Calendar getClosestFirstDateInFuture(@NonNull Calendar calendar) {
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return calendar;
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        if (calendar.before(getCurrentMonthsFirstDay())) {
            calendar.add(Calendar.MONTH, 1);
        }
        return calendar;
    }

    /**
     * To get the last date of previous month if provided date is not last.
     *
     * @param calendar date to process
     */
    public static Calendar getClosestLastDateInPast(@NonNull Calendar calendar) {
        if (calendar.get(Calendar.DAY_OF_MONTH) == calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            return calendar;
        }
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar;
    }

    /**
     * Returns a {@link Date} that represents the start (00:00) of the next day
     * from the given date.
     *
     * For example, if given date is "9th Sep 2019 1130Z" the result will be
     * "10th Sept 2019 0000Z".
     *
     * @param date the given date
     * @return a {@link Date} corresponding to the start of the next day from `date`.
     */
    @NonNull
    public static Date getStartOfNextDay(@NonNull Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return DateUtilities.getDateWithNoTime(calendar.getTime());
    }

    /**
     * Returns a {@link Date} that is equal to `startDate` plus `millis` milliseconds.
     *
     * @param startDate the start date
     * @param millis amount of milliseconds to add
     */
    @NonNull
    public static Date addMillisToDate(@NonNull Date startDate, long millis) {
        Calendar calendar = Calendar.getInstance();

        // Implementation Note:  Calendar.add() takes an integer value which limits the amount
        // of time to approximately 24 days before needing to use a different time unit.

        calendar.setTime(startDate);
        calendar.add(Calendar.MILLISECOND, (int) millis);
        return calendar.getTime();
    }

    @NonNull
    public static Date getTodayStartTime() {
        Calendar today = Calendar.getInstance();
        return today.getTime();
    }

    @NonNull
    public static Date getStartOfNDaysLater(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * Get current time milliseconds for a time zone
     *
     * @param timeZone the timezone to get the current time
     * @return the curren time milli seconds for that time zone
     */
    public static long getCurrentTimeMilliSeconds(String timeZone) {
        Calendar currentTime = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
        currentTime.set(Calendar.ZONE_OFFSET, TimeZone.getTimeZone(timeZone).getRawOffset());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, currentTime.get(Calendar.HOUR_OF_DAY));
        return calendar.getTimeInMillis();
    }

    /**
     * Gets a date object and formats it to ISO in UTC format.
     *
     * @param date A generic date object
     * @return String formatted string with the pattern:
     * "yyyy-MM-dd'T'HH:mm:ss'Z'"
     */
    @NonNull
    public static String formatISOInUTC(@NonNull Date date) {
        TimeZone tz = TimeZone.getTimeZone(UTC_ZONE);
        synchronized (UTC_SIMPLE_DATE_FORMAT) {
            UTC_SIMPLE_DATE_FORMAT.setTimeZone(tz);
            return UTC_SIMPLE_DATE_FORMAT.format(date);
        }
    }

    /**
     * Gets a date object from ISO8061 format string without considering time zone.
     *
     * @param dateString A ISO8061 format string, eg "yyyy-MM-dd'T'HH:mm:ssZ"
     * @param logger An instance of ILogger.
     * @return date object
     */
    @NonNull
    public static Date getDateFromISO8061FormatWithoutTimeZone(@NonNull String dateString, @NonNull ILogger logger) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat(DateFormats.UTC_DATE_FORMAT_NO_TIMEZONE, Locale.US);

        // remove timezone info from dateString
        String newDateString;
        int indexOfPlus = dateString.indexOf('+');
        if (indexOfPlus > 0) {
            newDateString = dateString.substring(0, indexOfPlus);
        } else {
            newDateString = dateString;
        }

        try {
            date = formatter.parse(newDateString);
        } catch (ParseException ex) {
            logger.log(LogPriority.ERROR, "DateUtilities:getDateFromUTCFormatWithoutTimeZone", "Failed to parse UTC sting %s", dateString);
        }

        return date;
    }

    @CustomDateTimeFormat
    private static int getCustomDateTimeFormat(long timeInMs, boolean isRelative) {
        Calendar calendar = Calendar.getInstance();

        if (isRelative) {
            final long aFewSecondsAgo = calendar.getTimeInMillis() - TimeUnit.MINUTES.toMillis(1);
            if (timeInMs >= aFewSecondsAgo) {
                return NOW;
            }
        }

        // Setting the calendar to the start of Today at 00:00:00 000
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if (timeInMs >= calendar.getTimeInMillis()) {
            return TODAY;
        }

        if (isRelative) {
            calendar.add(Calendar.DATE, -1);
            if (timeInMs >= calendar.getTimeInMillis()) {
                return YESTERDAY;
            }

            // Past week's logic (we only subtract 5 because yesterday's logic already subtracted 1)
            calendar.add(Calendar.DATE, -5);
            if (timeInMs >= calendar.getTimeInMillis()) {
                return LAST_WEEK;
            }
        }

        calendar.set(Calendar.DAY_OF_YEAR, 1);
        if (timeInMs >= calendar.getTimeInMillis()) {
            return THIS_YEAR;
        }

        return BEFORE_THIS_YEAR;
    }

    private static String formatDateTime(@NonNull Context context, long targetDateInMillis, @CustomDateTimeFormat int customFormat, boolean useShortFormat) {
        boolean use24HourFormat = DateFormat.is24HourFormat(context);
        int formatFlags = use24HourFormat ? HOUR_MINUTES_24_HOUR : HOUR_MINUTES_12_HOUR;

        switch (customFormat) {
            case NOW:
            case TODAY:
            case HOURS_AND_MINUTES:
                // Just time flags
                break;
            case YESTERDAY:
                return /*context.getString(R.string.date_format_yesterday)"Yesterday*/"Yesterday";
            case LAST_WEEK:
                formatFlags = DateUtils.FORMAT_SHOW_WEEKDAY;
                break;
            case THIS_YEAR:
                formatFlags = useShortFormat ? MONTH_DAY : MONTH_DAY | formatFlags;
                break;
            case MONTH_DAY_HOURS_AND_MINUTES:
                formatFlags = MONTH_DAY_MONTH_ABBREV_FLAGS | formatFlags;
                break;
            case MONTH_DAY_MONTH_ABBREV:
                formatFlags = MONTH_DAY_MONTH_ABBREV_FLAGS;
                break;
            case BEFORE_THIS_YEAR:
            default:
                formatFlags = useShortFormat ? MONTH_DAY_YEAR : MONTH_DAY_YEAR | formatFlags;
                break;
        }

        return DateUtils.formatDateTime(context, targetDateInMillis, formatFlags);
    }

    /**
     * Converts a date into a formatted string
     *
     * @param preferredFormat Desired format string, e.g. H:mm:ss
     * @param date Time to format
     * @return Formatted date/time string
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @NonNull
    private static String getDateFormat(@NonNull String preferredFormat, @NonNull Date date) {
        String localeFormat = DateFormat.getBestDateTimePattern(Locale.getDefault(), preferredFormat);
        return new SimpleDateFormat(localeFormat).format(date);
    }

    /**
     * Convert a 12 hour format to a format with "AM" and "PM" marker in the upper right of the time
     *
     * @param format The 12 hour format string, for example h:mma, 8:21AM
     * @param markerProportion The size proportion of the "AM" and "PM" marker
     * @return The new time format string, with "AM" and "PM" marker in the upper right
     */
    @NonNull
    public static CharSequence get12HourTimeFormatWithMarker(@NonNull String format, float markerProportion) {
        final int spanStart = format.length() - 1;
        SpannableStringBuilder builder = new SpannableStringBuilder(format);
        builder.setSpan(new SuperscriptSpan(), spanStart, spanStart + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new RelativeSizeSpan(markerProportion), spanStart, spanStart + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    private DateUtilities() {

    }

    /**
     * Constant Class for Date Formats
     */
    public static final class DateFormats {
        private DateFormats() {

        }

        public static final String FULL_DAY_MONTH_DATE_YEAR = "EEEE MMM dd, yyyy"; //Monday March 12, 2018
        public static final String DAY_MONTH_DATE_YEAR = "EEE MMM dd, yyyy"; //Mon March 12, 2018
        public static final String API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ"; //2018-03-27T16:00:00.000+05:30
        public static final String ISO_8061_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZZZZ"; //2018-03-27T16:00:00+05:30
        public static final String MONTH_DATE_YEAR = "MMM dd, yyyy"; //Mar 12, 2018
        public static final String MONTH_DATE_YEAR_TIME = "MMM dd yyyy hh:mm aaa"; //Mar 12, 2018, 03:15 PM
        public static final String MONTH_DATE = "MMM dd"; //Mar 12
        public static final String YEAR_MONTH_DATE = "yyyy-MM-dd"; //2019-07-14
        public static final String SIMPLE_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss'z'";
        public static final String CANCEL_REQUEST_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
        public static final String EXPIRY_DATE_FORMAT = "MM/yyyy";
        public static final String FORM_MONTH_DATE_YEAR_FORMAT = "MM/dd/yyyy";
        public static final String UTC_DATE_FORMAT_NO_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ss";
        public static final String DAY_DATE_MONTH = "EEE, dd MMM"; //Mon, 09 Aug
        public static final String WEEK_MONTH_DAY_DATE = "EEEEMMMMd"; //Tuesday, July 28
        public static final String TIME_24_HOUR_FORMAT = "HH:mm"; //15:08
        public static final String TIME_12_HOUR_FORMAT = "h:mma"; //12:08AM, 8:03PM
        // The leading spaces are to make sure the time can be center aligned on layout when AM and PM marker is made small and upper right
        public static final String TIME_12_HOUR_FORMAT_WITH_LEADING_SPACES = "  h:mma"; //"  12:08AM", "  8:05PM"
        public static final String DAY_DATE_MONTH_YEAR_TIME = "EEEE, dd MMMM yyyy HH:mm"; //Monday, 11 January 2021 16:15
    }

    @SuppressLint("ShiftFlags")
    @Retention(RetentionPolicy.SOURCE)
    @IntDef(value = {NOW, TODAY, YESTERDAY, LAST_WEEK, THIS_YEAR, BEFORE_THIS_YEAR, HOURS_AND_MINUTES, MONTH_DAY_HOURS_AND_MINUTES, MONTH_DAY_MONTH_ABBREV},
            flag = true)
    @interface CustomDateTimeFormat {
        int NOW = 0;
        int TODAY = 1;
        int YESTERDAY = 2;
        int LAST_WEEK = 3;
        int THIS_YEAR = 4;
        int BEFORE_THIS_YEAR = 5;
        int HOURS_AND_MINUTES = 6;
        int MONTH_DAY_HOURS_AND_MINUTES = 7;
        int MONTH_DAY_MONTH_ABBREV = 8;
    }
}
