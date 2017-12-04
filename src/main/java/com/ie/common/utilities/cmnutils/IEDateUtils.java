package com.ie.common.utilities.cmnutils;

import com.ie.common.utilities.cmnutils.exception.IEValidateCodeException;
import com.ie.common.utilities.cmnutils.exception.IEValidateMsgException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * common Date Utilities
 * @author bradly
 * @version 1.0
 */
public class IEDateUtils {

    private IEDateUtils(){}

    private static final IE2DHashMap<String, String, SimpleDateFormat> sdfCache = new IE2DHashMap<>();

    private static final ReadWriteLock sdfLock = new ReentrantReadWriteLock();

    /**
     * Fetch a SimpleDateFormat Instance
     * @param timezoneID
     * @param pattern
     * @return
     */
    public static SimpleDateFormat getFormatInstance(final String timezoneID, final String pattern){
        IEValidateUtils.notBlank(timezoneID, "The timezoneID can't be blank");
        IEValidateUtils.notBlank(pattern, "The pattern can't be blank");
        SimpleDateFormat sdf = null;
        try{
            sdfLock.readLock().lock();
            sdf = sdfCache.get(timezoneID, pattern);
            if(sdf != null){
                return sdf;
            }
        }finally{
            sdfLock.readLock().unlock();
        }
        try{
            sdfLock.writeLock().lock();
            sdf = new SimpleDateFormat(pattern);
            sdf.setTimeZone(TimeZone.getTimeZone(timezoneID));
            sdfCache.set(timezoneID, pattern, sdf);
            return sdf;
        }finally {
            sdfLock.writeLock().unlock();
        }
    }

    /**
     * Format date to String
     * @param timezoneID
     * @param pattern
     * @param date
     * @return
     */
    public static String format(final String timezoneID, final String pattern, final Date date){
        IEValidateUtils.notNull(date, "The date can't be null");
        SimpleDateFormat sdf = getFormatInstance(timezoneID, pattern);
        return sdf.format(date);
    }

    /**
     * Parse String to Date
     * @param timezoneID
     * @param pattern
     * @param value
     * @return
     * @throws ParseException
     */
    public static Date parse(final String timezoneID, final String pattern, final String value) throws ParseException {
        IEValidateUtils.notBlank(value, "The string value of date can't be blank");
        SimpleDateFormat sdf = getFormatInstance(timezoneID, pattern);
        return sdf.parse(value);
    }

    /**
     * Parse String to Date
     * @param timezoneID
     * @param pattern
     * @param value
     * @return It will be null when parse failed
     */
    public static Date parseNoThrow(final String timezoneID, final String pattern, final String value){
        try {
            return parse(timezoneID, pattern, value);
        } catch(IEValidateCodeException | IEValidateMsgException e){
            throw e;
        } catch (Exception e){ }
        return null;
    }

    /**
     * Fetch start time of the day by date
     * @param timezoneID
     * @param value
     * @return
     */
    public static Date dayStart(final String timezoneID, final Date value){
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
     * @param timezoneID
     * @param millis
     * @return
     */
    public static Date dayStart(final String timezoneID, final long millis){
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
     * @param timezoneID
     * @return
     */
    public static Date todayStart(final String timezoneID){
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
     * @param timezoneID
     * @param value
     * @return
     */
    public static Date dayEnd(final String timezoneID, final Date value){
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
     * @param timezoneID
     * @param millis
     * @return
     */
    public static Date dayEnd(final String timezoneID, final long millis){
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
     * @param timezoneID
     * @return
     */
    public static Date todayEnd(final String timezoneID){
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
     * @return
     */
    public static Date todayStart(){
        return todayStart(TimeZone.getDefault().getID());
    }

    /**
     * Fetch end time of today when using default timezone
     * @return
     */
    public static Date todayEnd(){
        return todayEnd(TimeZone.getDefault().getID());
    }

    /**
     * Fetch start time of the day when using default timezone
     * @param value
     * @return
     */
    public static Date dayStart(Date value){
        return dayStart(TimeZone.getDefault().getID(), value);
    }

    /**
     * Fetch end time of the day when using default timezone
     * @param millis
     * @return
     */
    public static Date dayStart(long millis){
        return dayStart(TimeZone.getDefault().getID(), millis);
    }
    
}
