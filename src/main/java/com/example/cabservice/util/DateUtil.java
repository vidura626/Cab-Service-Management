package com.example.cabservice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    // Define the date format used in the request (yyyy/MM/dd)
    private static final String DATE_FORMAT = "yyyy/MM/dd";

    // Converts String date to java.sql.Date for DB operations
    public static java.sql.Date convertStringToSqlDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            Date parsedDate = sdf.parse(dateStr);
            return new java.sql.Date(parsedDate.getTime());  // Converts to java.sql.Date
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use 'yyyy/MM/dd'.");
        }
    }

    // Converts String date to java.util.Date for the response DTO
    public static Date convertStringToUtilDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use 'yyyy/MM/dd'.");
        }
    }

    // Converts java.util.Date to String for sending in the response
    public static String convertUtilDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    // Converts java.sql.Date to java.util.Date (for Response DTO)
    public static Date convertSqlDateToUtilDate(java.sql.Date sqlDate) {
        return new Date(sqlDate.getTime());
    }
}
