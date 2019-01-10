package com.cargodelivery.configconnection.impl;

import com.cargodelivery.configconnection.DataFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

public class MySQLDateFormatter implements DataFormatter {
    @Override
    public String getCurrentDate() {
        LocalDateTime data = LocalDateTime.now();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(format.parse(String.valueOf(data)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format.format(calendar.getTime());
    }

    @Override
    public String getEndDate(String startDate, int deliveryTerm) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(format.parse(String.valueOf(startDate)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DATE, deliveryTerm);
        return format.format(calendar.getTime());
    }
}
