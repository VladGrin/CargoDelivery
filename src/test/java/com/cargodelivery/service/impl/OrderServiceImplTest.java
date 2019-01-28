package com.cargodelivery.service.impl;

import com.cargodelivery.exception.IncorrectInputException;
import com.cargodelivery.repository.OrderRepository;
import com.cargodelivery.repository.impl.MySQLOrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;

public class OrderServiceImplTest {

    private OrderServiceImpl orderService;

    private OrderRepository orderRepository;

    @Before
    public void before() {
        orderRepository = Mockito.mock(MySQLOrderRepository.class);
        orderService = new OrderServiceImpl(orderRepository);
    }

    @Test
    public void deleteOrderById() throws IncorrectInputException {

        String orderId = "10";

        Mockito.when(orderRepository.deleteOrderById(10)).thenReturn(true);

        boolean result = orderService.deleteOrderById(orderId);

        assertTrue(result);
    }
}