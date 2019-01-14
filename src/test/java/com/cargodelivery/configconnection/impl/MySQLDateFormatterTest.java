package com.cargodelivery.configconnection.impl;

import com.cargodelivery.util.impl.MySQLDateFormatter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MySQLDateFormatterTest {

    @Test
    public void getEndDate() {
        String startDate = "2018-10-28";
        int deliveryTerm = 5;
        MySQLDateFormatter mySQLDateFormatter = new MySQLDateFormatter();
        String endDate = mySQLDateFormatter.getEndDate(startDate, deliveryTerm);
        String expectedDate = "2018-11-02";
        assertEquals(expectedDate, endDate);
    }
}