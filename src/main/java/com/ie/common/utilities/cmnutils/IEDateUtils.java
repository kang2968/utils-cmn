package com.ie.common.utilities.cmnutils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * common Date Utilities
 *
 * @author bradly
 * @version 1.0
 */
public class IEDateUtils {
    
    private IEDateUtils() {
    }
    
    /**
     * Fetch start time of the day by date
     *
     * @param timezoneID
     * @param value
     * @return
     */
    public static Date dayStart(final String timezoneID, final Date value) {
        IEValidateUtils.notBlank(timezoneID, "The timezoneID can't be blank");
        IEValidateUtils.notNull(value, "The date can't be null");
        Calendar cal = Calendar.getInstance();
        cal.setTime(value);
        cal.setTimeZone(TimeZone.getTimeZone(timezoneID));
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        return cal.getTime();
    }
    
    /**
     * Fetch start time of the day by millisecond
     *
     * @param timezoneID
     * @param millis
     * @return
     */
    public static Date dayStart(final String timezoneID, final long millis) {
        IEValidateUtils.notBlank(timezoneID, "The timezoneID can't be blank");
        IEValidateUtils.isGreaterThan(millis, 0, "The millis value must be greater than 0");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        cal.setTimeZone(TimeZone.getTimeZone(timezoneID));
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        return cal.getTime();
    }
    
    /**
     * Fetch start time of today
     *
     * @param timezoneID
     * @return
     */
    public static Date todayStart(final String timezoneID) {
        IEValidateUtils.notBlank(timezoneID, "The timezoneID can't be blank");
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone(timezoneID));
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        return cal.getTime();
    }
    
    /**
     * Fetch end time of the day by date
     *
     * @param timezoneID
     * @param value
     * @return
     */
    public static Date dayEnd(final String timezoneID, final Date value) {
        IEValidateUtils.notBlank(timezoneID, "The timezoneID can't be blank");
        IEValidateUtils.notNull(value, "The date value can't be null");
        Calendar cal = Calendar.getInstance();
        cal.setTime(value);
        cal.setTimeZone(TimeZone.getTimeZone(timezoneID));
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MILLISECOND, -1);
        return cal.getTime();
    }
    
    /**
     * Fetch end time of the day by millisecond
     *
     * @param timezoneID
     * @param millis
     * @return
     */
    public static Date dayEnd(final String timezoneID, final long millis) {
        IEValidateUtils.notBlank(timezoneID, "The timezoneID can't be blank");
        IEValidateUtils.isGreaterThan(millis, 0, "The millis value must be greater than 0 ");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        cal.setTimeZone(TimeZone.getTimeZone(timezoneID));
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MILLISECOND, -1);
        return cal.getTime();
    }
    
    /**
     * Fetch end time of today
     *
     * @param timezoneID
     * @return
     */
    public static Date todayEnd(final String timezoneID) {
        IEValidateUtils.notBlank(timezoneID, "The timezoneID can't be blank");
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone(timezoneID));
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MILLISECOND, -1);
        return cal.getTime();
    }
    
    /**
     * Fetch start time of today when using default timezone
     *
     * @return
     */
    public static Date todayStart() {
        return todayStart(TimeZone.getDefault().getID());
    }
    
    /**
     * Fetch end time of today when using default timezone
     *
     * @return
     */
    public static Date todayEnd() {
        return todayEnd(TimeZone.getDefault().getID());
    }
    
    /**
     * Fetch start time of the day when using default timezone
     *
     * @param value
     * @return
     */
    public static Date dayStart(Date value) {
        return dayStart(TimeZone.getDefault().getID(), value);
    }
    
    /**
     * Fetch end time of the day when using default timezone
     *
     * @param millis
     * @return
     */
    public static Date dayStart(long millis) {
        return dayStart(TimeZone.getDefault().getID(), millis);
    }
    
}
