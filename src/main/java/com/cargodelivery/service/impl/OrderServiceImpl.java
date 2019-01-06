package com.cargodelivery.service.impl;

import com.cargodelivery.configconnection.DataFormatter;
import com.cargodelivery.configconnection.impl.MySQLDateFormatter;
import com.cargodelivery.exception.IncorrectInputException;
import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.Order;
import com.cargodelivery.repository.OrderRepository;
import com.cargodelivery.repository.impl.OrderRepositoryImpl;
import com.cargodelivery.service.OrderService;
import com.cargodelivery.validator.Validator;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final static Logger logger = Logger.getLogger(OrderServiceImpl.class);
    private OrderRepository orderRepository;
    private DataFormatter dataFormatter;

    public OrderServiceImpl(Connection connection) {
        this.orderRepository = new OrderRepositoryImpl(connection);
        dataFormatter = new MySQLDateFormatter();
    }

    @Override
    public boolean saveOrder(int userId, String createDate, String cityFrom, String cityTo, String cargoType,
                             String weight, String startDate, String endDate, String recipient,
                             String recipientPhone, String deliveryAdrress, int price) throws IncorrectInputException {
        Validator.validateText(cityFrom);
        Validator.validateText(cityTo);
        Validator.validateText(cargoType);
        Validator.validateWeight(weight, cargoType);
        Validator.validateMobileNumber(recipientPhone);

        Order order = new Order.OrderBuilder()
                .setUserId(userId)
                .setCreateDate(createDate)
                .setCityFrom(cityFrom)
                .setCityTo(cityTo)
                .setTypeByName(cargoType)
                .setWeight(Integer.parseInt(weight))
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setRecipient(recipient)
                .setRecipientPhone(recipientPhone)
                .setDeliveryAddress(deliveryAdrress)
                .setPrice(price)
                .build();

        logger.info("Order saved: " + order);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrdersByUserId(int userId) throws NoSuchDataException {
        List<Order> allOrdersByUserId = orderRepository.findAllOrdersByUserId(userId);
        if (allOrdersByUserId == null) {
            throw new NoSuchDataException("Orders list is empty.");
        }
        return allOrdersByUserId;
    }
}
