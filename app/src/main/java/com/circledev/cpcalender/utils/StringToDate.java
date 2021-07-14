package com.circledev.cpcalender.utils;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
        int durationInt = (int) Double.parseDouble(duration);
            int h = durationInt / 3600;
            int m = durationInt % 3600 / 60;
            if(m==0) {
                return h+" hours";
            }
            return h+" hours"+ " and " + m +" minutes";
    }

    public static String dateFormat(Date date) {
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(date);
    }

    public static String timeFormat(Date date) {
        return DateFormat.getTimeInstance(DateFormat.SHORT).format(date);
    }
}
