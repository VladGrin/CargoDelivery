package com.cargodelivery.util.impl;

import com.cargodelivery.util.DataFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

public class MySQLDateFormatter implements DataFormatter {

    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String getCurrentDate() {

        Calendar calendar = getCalendar(LocalDateTime.now());

        return FORMAT.format(calendar.getTime());
    }

    @Override
    public String getEndDate(String startDate, int deliveryTerm) {

        Calendar calendar = getCalendar(startDate);
        calendar.add(Calendar.DATE, deliveryTerm);

        return FORMAT.format(calendar.getTime());
    }

    private Calendar getCalendar(Object object) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(FORMAT.parse(String.valueOf(object)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }
}