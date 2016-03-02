package com.simon7in.strategy.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: jiayu.shenjy
 * To change this template use File | Settings | File Templates.
 */
public final class DateUtil {

    private static final Logger log = LoggerFactory.getLogger(DateUtil.class);
    private static final String TIME_PATTERN = "HH:mm";
    public static final String YMD_HMS_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private DateUtil() {
    }

    /**
     * Return default datePattern (MM/dd/yyyy)
     *
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {
        Locale locale = LocaleContextHolder.getLocale();
        String defaultDatePattern;
        try {
            defaultDatePattern = ResourceBundle.getBundle("ApplicationResources",
                    locale).getString("date.format");
        } catch (MissingResourceException mse) {
            defaultDatePattern = "MM/dd/yyyy HH:mm:ss";
        }
        if (defaultDatePattern == null || defaultDatePattern.isEmpty()) {
            defaultDatePattern = "MM/dd/yyyy HH:mm:ss";
        }

        return defaultDatePattern;
    }

    public static String getDateTimePattern() {
        return DateUtil.getDatePattern() + " HH:mm:ss.S";
    }

    /**
     * This method attempts to convert an Oracle-formatted date in the form
     * dd-MMM-yyyy to mm/dd/yyyy.
     *
     * @param aDate
     *            date from database as a string
     * @return formatted string for the ui
     */
    public static String getDate(Date aDate) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date/time in the
     * format you specify on input
     *
     * @param aMask
     *            the date pattern the string is in
     * @param strDate
     *            a string representation of a date
     * @return a converted Date object
     * @throws ParseException
     *             when String doesn't match the expected format
     * @see SimpleDateFormat
     */
    public static Date convertStringToDate(String aMask, String strDate)
            throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(aMask);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '"
                    + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            // log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    /**
     * This method returns the current date time in the format: MM/dd/yyyy HH:MM
     * a
     *
     * @param theTime
     *            the current time
     * @return the current date/time
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(TIME_PATTERN, theTime);
    }

    /**
     * This method returns the current date in the format: MM/dd/yyyy
     *
     * @return the current date
     * @throws ParseException
     *             when String doesn't match the expected format
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    /**
     * This method generates a string representation of a date's date/time in
     * the format you specify on input
     *
     * @param aMask
     *            the date pattern the string is in
     * @param aDate
     *            a date object
     * @return a formatted string representation of the date
     * @see SimpleDateFormat
     */
    public static String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            log.warn("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date based on the
     * System Property 'dateFormat' in the format you specify on input
     *
     * @param aDate
     *            A date to convert
     * @return a string representation of the date
     */
    public static String convertDateToString(String aMask, Date aDate) {
        return getDateTime(aMask, aDate);
    }

    /**
     * This method generates a string representation of a date based on the
     * System Property 'dateFormat' in the format you specify on input
     *
     * @param aDate
     *            A date to convert
     * @return a string representation of the date
     */
    public static String convertDateToString(Date aDate) {
        return getDateTime(getDatePattern(), aDate);
    }

    /**
     * This method converts a String to a date using the datePattern
     *
     * @param strDate
     *            the date to convert (in format MM/dd/yyyy)
     * @return a date object
     * @throws ParseException
     *             when String doesn't match the expected format
     */
    public static Date convertStringToDate(final String strDate)
            throws ParseException {
        return convertStringToDate(getDatePattern(), strDate);
    }

    public static String formatDateWithChinaDf(Date date) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.format(date);
        }
    }

    public static String getDispalyDateTime(Date date) {
        String retString = "";
        if (date == null) {
            return retString;
        }

        Long m = ((new Date()).getTime() - date.getTime()) / 1000;
        Long oneHour = 60 * 60L;
        Long oneDay = 24 * oneHour;
        Long oneWeek = 7 * oneDay;
        Long fourWeek = oneWeek * 4;

        if (m <= 60) {
            retString = m + "秒前";
        } else if (m < oneHour) {
            retString = m / 60 + "分钟前";
        } else if (m < oneDay) {
            retString = m / oneHour + "小时前";
        } else if (m < oneWeek) {
            retString = m / oneDay + "天前";
        } else if (m < fourWeek) {
            retString = m / oneWeek + "周前";
        } else {
            retString = DateUtil.getDateTime("yy-MM-dd", date);
        }
        return retString;
    }

    public static Date setBeginOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        date = cal.getTime();
        return date;
    }

    public static Date setEndOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        date = cal.getTime();
        return date;
    }

    public static long nDaysBetweenTwoDate(Date beginDate, Date endDate)
            throws ParseException {
        long day = 0;
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    public static Date nDaysAfterOneDate(Date basicDate, long n) {
        long nDay = (basicDate.getTime() / (24 * 60 * 60 * 1000) + 1 + n)
                * (24 * 60 * 60 * 1000);
        basicDate.setTime(nDay);

        return basicDate;
    }
    /**
     * 计算两个日子之间的工作日时间
     * @param startDate
     * @param endDate
     * @return
     */

    public static int getWorkDays(Date startDate,Date endDate) {
    	int result = 0;
    	Calendar cal_start = Calendar.getInstance();
    	Calendar cal_end = Calendar.getInstance();
    	cal_start.setTime(startDate);
    	cal_end.setTime(endDate);
    	while (cal_start.compareTo(cal_end)<=0) {
	    	if (cal_start.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY && cal_start.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY)
	    	result++;
	    	cal_start.add(Calendar.DATE, 1);
    		}

    	return result;
    	}

    /**
     * 上个月的第一天
     * @return
     */
    public static Date getLastMonthFirstDay(){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(getLastMonthLastDay()); ;
        //上个月的第一天
        cal.set(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();
    }

    /**
     * 上个月的最后一天
     * @return
     */
    public static Date getLastMonthLastDay(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        //日期减一，获得上个月的最后一天
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }
}