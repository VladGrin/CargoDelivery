package com.cargodelivery.util;

public interface DataFormatter {

    String getCurrentDate();
    String getEndDate(String startDate, int deliveryTerm);
}
