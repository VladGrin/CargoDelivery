package com.cargodelivery.service.impl;

import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.Order;
import com.cargodelivery.repository.OrderRepository;
import com.cargodelivery.repository.impl.OrderRepositoryImpl;
import com.cargodelivery.service.OrderService;

import java.sql.Connection;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(Connection connection) {
        this.orderRepository = new OrderRepositoryImpl(connection);
    }

    @Override
    public boolean saveOrder(Order order) {
        orderRepository.save(order);
        return true;
    }

    @Override
    public List<Order> getAllOrdersByUserId(int userId) throws NoSuchDataException {
        List<Order> allOrdersByUserId = orderRepository.findAllOrdersByUserId(userId);
        if (allOrdersByUserId == null) {
            throw new NoSuchDataException("Cities list is empty.");
        }
        return allOrdersByUserId;
    }
}
