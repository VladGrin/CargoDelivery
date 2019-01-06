package com.cargodelivery.configconnection;

public interface DataFormatter {

    String getCurrentDate();
    String getEndDate(String startDate, int deliveryTerm);
}
