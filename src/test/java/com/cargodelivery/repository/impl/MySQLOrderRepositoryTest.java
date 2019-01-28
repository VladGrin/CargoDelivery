//package com.cargodelivery.repository.impl;
//
//import com.cargodelivery.model.Order;
//import com.cargodelivery.repository.OrderRepository;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//
//import static org.junit.Assert.*;
//
//public class MySQLOrderRepositoryTest {
//
//    private OrderRepository orderRepository;
//
//    private Connection connection;
//
//    @Before
//    public void before() {
//        ResourceBundle resource = ResourceBundle.getBundle("database");
//        try {
//            String userName = resource.getString("db.user");
//            String password = resource.getString("db.password");
//            String connectionUrl = resource.getString("db.url");
//            connection = DriverManager.getConnection(connectionUrl, userName, password);
//            orderRepository = new MySQLOrderRepository();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @After
//    public void after() {
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * @see MySQLOrderRepository#save(Order)
//     */
//    @Ignore
//    @Test
//    public void addOrderToDataBaseTest() {
//        Order order = new Order.OrderBuilder().setUserId(1).setCreateDate("2018-10-10").setCityFrom("Винница")
//                .setCityTo("Киев").setType(Order.Type.DOCUMENT).setWeight(1).setStartDate("2018-12-20")
//                .setEndDate("2018-12-22").setRecipient("Величко Юрий").setRecipientPhone("+380937896541")
//                .setDeliveryAddress("ул. Рейтарскаяб 25/15").setPrice(40).setPayment(false).build();
//        assertTrue(orderRepository.save(order));
//    }
//
//    /**
//     * @see MySQLOrderRepository#findAllOrdersByUserId(int)
//     */
//    @Test
//    public void findAllOrdersByUserIdTest() {
//        Order order = orderRepository.findAllOrdersByUserId(1)
//                .stream().findFirst().get();
//        Order expectedOrder = new Order.OrderBuilder().setId(1).setUserId(1).setCreateDate("2018-10-10").setCityFrom("Винница")
//                .setCityTo("Киев").setType(Order.Type.DOCUMENT).setWeight(1).setStartDate("2018-12-20")
//                .setEndDate("2018-12-22").setRecipient("Величко Юрий").setRecipientPhone("+380937896541")
//                .setDeliveryAddress("ул. Рейтарскаяб 25/15").setPrice(40).setPayment(true).build();
//        assertEquals(expectedOrder, order);
//    }
//
//    /**
//     * @see MySQLOrderRepository#findOrderById(int)
//     */
//    @Test
//    public void findOrderById() {
//        Order orderById = orderRepository.findOrderById(1);
//        Order expectedOrder = new Order.OrderBuilder().setId(1).setUserId(1).setCreateDate("2018-10-10").setCityFrom("Винница")
//                .setCityTo("Киев").setType(Order.Type.DOCUMENT).setWeight(1).setStartDate("2018-12-20")
//                .setEndDate("2018-12-22").setRecipient("Величко Юрий").setRecipientPhone("+380937896541")
//                .setDeliveryAddress("ул. Рейтарскаяб 25/15").setPrice(40).setPayment(true).build();
//
//        assertEquals(expectedOrder, orderById);
//    }
//
//    /**
//     * @see MySQLOrderRepository#updatePaymentByOrderId(Integer, boolean)
//     */
//    @Test
//    public void updatePaymentByOrderId() {
//
//        assertTrue(orderRepository.updatePaymentByOrderId(1, true));
//    }
//}