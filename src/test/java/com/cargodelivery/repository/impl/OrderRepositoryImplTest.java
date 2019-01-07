package com.cargodelivery.repository.impl;

import com.cargodelivery.model.Order;
import com.cargodelivery.repository.OrderRepository;
import com.sun.istack.internal.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderRepositoryImplTest {

    @NotNull
    private OrderRepository orderRepository;

    @NotNull
    private Connection connection;

    @Before
    public void before() {
        try {
            String userName = "root";
            String password = "1111";
            String connectionUrl = "jdbc:mysql://localhost:3306/cargodelivery?useSSL=true&characterEncoding=utf8";
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            orderRepository = new OrderRepositoryImpl(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see com.cargodelivery.repository.impl.OrderRepositoryImpl#save(Order)
     */
    @Ignore
    @Test
    public void addOrderToDataBaseTest() {
        Order order = new Order.OrderBuilder().setUserId(1).setCreateDate("2018-10-10").setCityFrom("Винница")
                .setCityTo("Киев").setType(Order.Type.DOCUMENT).setWeight(1).setStartDate("2018-12-20")
                .setEndDate("2018-12-22").setRecipient("Величко Юрий").setRecipientPhone("+380937896541")
                .setDeliveryAddress("ул. Рейтарскаяб 25/15").setPrice(40).setPayment(false).build();
        assertTrue(orderRepository.save(order));
    }

    /**
     * @see com.cargodelivery.repository.impl.OrderRepositoryImpl#findAllOrdersByUserId(int)
     */
    @Test
    public void findAllOrdersByUserIdTest() {
        Order order = orderRepository.findAllOrdersByUserId(1)
                .stream().findFirst().get();
        Order expectedOrder = new Order.OrderBuilder().setId(1).setUserId(1).setCreateDate("2018-10-10").setCityFrom("Винница")
                .setCityTo("Киев").setType(Order.Type.DOCUMENT).setWeight(1).setStartDate("2018-12-20")
                .setEndDate("2018-12-22").setRecipient("Величко Юрий").setRecipientPhone("+380937896541")
                .setDeliveryAddress("ул. Рейтарскаяб 25/15").setPrice(40).setPayment(false).build();
        assertEquals(expectedOrder, order);
    }

    /**
     * @see com.cargodelivery.repository.impl.OrderRepositoryImpl#findOrderById(int)
     */
    @Test
    public void findOrderById() {
        Order orderById = orderRepository.findOrderById(1);
        Order expectedOrder = new Order.OrderBuilder().setId(1).setUserId(1).setCreateDate("2018-10-10").setCityFrom("Винница")
                .setCityTo("Киев").setType(Order.Type.DOCUMENT).setWeight(1).setStartDate("2018-12-20")
                .setEndDate("2018-12-22").setRecipient("Величко Юрий").setRecipientPhone("+380937896541")
                .setDeliveryAddress("ул. Рейтарскаяб 25/15").setPrice(40).setPayment(false).build();

        assertEquals(expectedOrder, orderById);
    }

    /**
     * @see com.cargodelivery.repository.impl.OrderRepositoryImpl#updatePaymentByOrderId(Integer, boolean)
     */
    @Test
    public void updatePaymentByOrderId() {

        assertTrue(orderRepository.updatePaymentByOrderId(1, true));
    }
}