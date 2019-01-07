package com.cargodelivery.repository;

import com.cargodelivery.model.Order;

import java.util.List;

public interface OrderRepository {

    boolean save(Order order);

    List<Order> findAllOrdersByUserId(int userId);

    boolean deleteOrderById(int orderId);

    Order findOrderById(int orderId);
}
