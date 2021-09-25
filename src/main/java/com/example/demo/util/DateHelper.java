package com.example.demo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
public class DateHelper {
    public static final String DATE_FORMAT_YYYYMMDD = "yyyy/MM/dd";
    public static final String DATE_TIME_FORMAT_YYYYMMDD_HHMM = "yyyy/MM/dd HH:mm";

    public static Date createDateFromString(String date, String format) throws ParseException {
        if (date == null) {
            throw new IllegalArgumentException("Date is null!");
        }
        DateFormat df = new SimpleDateFormat(format);
        return df.parse(date);
    }

    public static String formatDate(Date date, String format) {
        DateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * Formats Date with Time using yyyy/MM/dd HH:mm
     *
     * @return formatted Date as a String
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, DATE_TIME_FORMAT_YYYYMMDD_HHMM);
    }

    /**
     * Formats Date using yyyy/MM/dd
     *
     * @return formatted Date as a String
     */

    public static String formatDate(Date date) {
        return formatDate(date, DATE_FORMAT_YYYYMMDD);
    }

    public static Instant strToIntance(String date) {
        String timeColonPattern = "HH:mm";
        LocalTime localTime = LocalTime.parse(date, DateTimeFormatter.ofPattern(timeColonPattern));
        LocalDateTime ldt = localTime.atDate(LocalDate.parse("2020-07-29"));
        return ldt.atZone(ZoneId.systemDefault()).toInstant();
    }
    

}
