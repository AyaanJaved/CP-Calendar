package com.ayaanjaved.cpcalendar.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class StringToDate {
    public static Date stringToDate(String date){
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.UK);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date returnDate;
        try {
            returnDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            returnDate = new Date();
        }
        return returnDate;
    }

    public static String stringToHours(String duration) {
        if( duration.equals("-") )
            return "Undefined";
        int seconds = (int) Double.parseDouble(duration);
        int years = (int) TimeUnit.SECONDS.toDays(seconds)/365;
        int day = (int) TimeUnit.SECONDS.toDays(seconds) - (years*365);
        long hours = TimeUnit.SECONDS.toHours(seconds) -
                TimeUnit.DAYS.toHours(day) - 8760*years;
        long minute = TimeUnit.SECONDS.toMinutes(seconds) -
                TimeUnit.DAYS.toMinutes(day) -
                TimeUnit.HOURS.toMinutes(hours) - 525600*years;

        StringBuilder stringBuilder = new StringBuilder();
        if(years != 0)
            stringBuilder.append(years).append(" year(s) ");
        if(day != 0)
            stringBuilder.append(day).append(" day(s) ");
        if(hours != 0 && years ==0)
            stringBuilder.append(hours).append(" hours ");
        if(minute != 0 && years == 0)
            stringBuilder.append(minute).append(" minutes ");

        return stringBuilder.toString();
    }

    public static String dateFormat(Date date) {
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(date);
    }

    public static String timeFormat(Date date) {
        return DateFormat.getTimeInstance(DateFormat.SHORT).format(date);
    }
}
