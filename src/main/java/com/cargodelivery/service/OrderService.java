package com.cargodelivery.service;

import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.Order;

import java.util.List;

public interface OrderService {

    boolean saveOrder(Order order);

    List<Order> getAllOrdersByUserId(int userId) throws NoSuchDataException;
}
