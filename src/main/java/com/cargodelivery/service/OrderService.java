package com.cargodelivery.service;

import com.cargodelivery.exception.IncorrectInputException;
import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.Order;

import java.util.List;

public interface OrderService {

    boolean saveOrder(int userId, String createDate, String cityFrom, String cityTo, String cargoType,
                      String weight, String startDate, String endDate, String recipient,
                      String recipientPhone, String deliveryAdrress, int price) throws IncorrectInputException;

    List<Order> getAllOrdersByUserId(int userId) throws NoSuchDataException;

    boolean deleteOrderById(String orderId);
}
