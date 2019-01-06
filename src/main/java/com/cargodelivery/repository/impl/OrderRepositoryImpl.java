package com.cargodelivery.repository.impl;

import com.cargodelivery.model.Order;
import com.cargodelivery.repository.OrderRepository;
import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author : Volodymyr Hrinchenko
 */
public class OrderRepositoryImpl implements OrderRepository {
    /**
     * Logger log4j
     */
    private final static Logger logger = Logger.getLogger(UserRepositoryImpl.class);
    /**
     * Connection of database
     */
    @NotNull
    private final Connection connection;

    /**
     * Init database connection
     *
     * @param connection of database
     */
    public OrderRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Create/save order in database
     *
     * @param order for create
     * @return false if Order was not saved. If creating success true.
     */
    @Override
    public boolean save(Order order) {
        boolean isSave = false;
        try (PreparedStatement statement = connection.prepareStatement(SQLOrder.SAVE.QUERY)) {
            statement.setInt(1, order.getUserId());
            statement.setString(2, order.getCreateDate());
            statement.setString(3, order.getCityFrom());
            statement.setString(4, order.getCityTo());
            statement.setString(5, order.getType().name());
            statement.setInt(6, order.getWeight());
            statement.setString(7, order.getStartDate());
            statement.setString(8, order.getEndDate());
            statement.setString(9, order.getRecipient());
            statement.setString(10, order.getRecipientPhone());
            statement.setString(11, order.getDeliveryAddress());
            statement.setInt(12, order.getPrice());
            isSave = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Invalid connection. ");
            e.printStackTrace();
        }
        logger.info("Order: " + order + " was saved to database");
        return isSave;
    }

    /**
     * Find all orders by User id
     *
     * @param userId
     * @return orders
     */
    @Override
    public List<Order> findAllOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQLOrder.FINDALL.QUERY)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String createDate = resultSet.getString("createDate");
                String cityFrom = resultSet.getString("cityFrom");
                String cityTo = resultSet.getString("cityTo");
                String orderType = resultSet.getString("orderType");
                int weight = resultSet.getInt("weight");
                String startDate = resultSet.getString("startDate");
                String endDate = resultSet.getString("endDate");
                String recipient = resultSet.getString("recipient");
                String recipientPhone = resultSet.getString("recipientPhone");
                String deliveryAddress = resultSet.getString("deliveryAddress");
                int price = resultSet.getInt("price");

                Order order = new Order.OrderBuilder().setId(id)
                        .setUserId(userId)
                        .setCreateDate(createDate)
                        .setCityFrom(cityFrom)
                        .setCityTo(cityTo)
                        .setTypeByName(orderType)
                        .setWeight(weight)
                        .setStartDate(startDate)
                        .setEndDate(endDate)
                        .setRecipient(recipient)
                        .setRecipientPhone(recipientPhone)
                        .setDeliveryAddress(deliveryAddress)
                        .setPrice(price).build();
                orders.add(order);
            }
        } catch (SQLException e) {
            logger.error("Invalid connection. ");
            e.printStackTrace();
        }
        orders = orders.isEmpty() ? null : orders;
        logger.info("Orders were found from database : " + orders);
        return orders;
    }

    /**elete order by order id
     * D
     * @param orderId
     * @return false if Order was not deleted. If deleting success true.
     */
    @Override
    public boolean deleteOrderById(int orderId) {
        boolean isDelete = false;
        try (PreparedStatement statement = connection.prepareStatement(SQLOrder.DELETEBYID.QUERY)) {
            statement.setInt(1, orderId);
            isDelete = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Invalid connection. ");
            e.printStackTrace();
        }
        logger.info("Order by id " + orderId + " was deleted: " + isDelete);
        return isDelete;
    }


    enum SQLOrder {
        SAVE("INSERT INTO orders (id, userId , createDate, cityFrom, cityTo, orderType, weight, startDate, endDate, recipient, \n" +
                "                      recipientPhone, deliveryAddress, price) \n" +
                "VALUES (DEFAULT, (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?));"),
        FINDALL("SELECT * FROM orders WHERE userId = (?)"),
        DELETEBYID("DELETE FROM orders WHERE id = (?)");

        String QUERY;

        SQLOrder(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}