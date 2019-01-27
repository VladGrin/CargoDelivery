package com.cargodelivery.repository.impl;

//import com.cargodelivery.configconnection.MySQLConfiguration;
import com.cargodelivery.configconnection.ConnectionPool;
import com.cargodelivery.model.Order;
import com.cargodelivery.repository.OrderRepository;
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
public class MySQLOrderRepository implements OrderRepository {
    /**
     * Logger log4j
     */
    private final static Logger logger = Logger.getLogger(MySQLOrderRepository.class);
    /**
     * Connection of database
     */
    private final Connection connection;
    /**
     * Create empty constructor
     * Init database connection by MySQL connection
     */
    public MySQLOrderRepository() {
        this.connection = ConnectionPool.getInstance().getConnection(); //new MySQLConfiguration().getConnection();
    }
    /**
     * Init database connection
     * @param connection of database
     */
    public MySQLOrderRepository(Connection connection) {
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
            setOrderToStatement(order, statement);
            isSave = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Invalid connection. ");
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().closeConnection(connection);
        }
        logger.info("Order: " + order + " was saved to database");
        return isSave;
    }

    private void setOrderToStatement(Order order, PreparedStatement statement) throws SQLException {
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
        statement.setBoolean(13, order.isPayment());
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
        try (PreparedStatement statement = connection.prepareStatement(SQLOrder.FINDALLBYUSERID.QUERY)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = getOrder(userId, resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            logger.error("Invalid connection. ");
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().closeConnection(connection);
        }
        orders = orders.isEmpty() ? null : orders;
        logger.info("Orders were found from database : " + orders);
        return orders;
    }

    private Order getOrder(int userId, ResultSet resultSet) throws SQLException {

        return new Order.OrderBuilder()
                .setId(resultSet.getInt("id"))
                .setUserId(userId)
                .setCreateDate(resultSet.getString("createDate"))
                .setCityFrom(resultSet.getString("cityFrom"))
                .setCityTo(resultSet.getString("cityTo"))
                .setTypeByName(resultSet.getString("orderType"))
                .setWeight(resultSet.getInt("weight"))
                .setStartDate(resultSet.getString("startDate"))
                .setEndDate(resultSet.getString("endDate"))
                .setRecipient(resultSet.getString("recipient"))
                .setRecipientPhone(resultSet.getString("recipientPhone"))
                .setDeliveryAddress(resultSet.getString("deliveryAddress"))
                .setPrice(resultSet.getInt("price"))
                .setPayment(resultSet.getBoolean("payment")).build();
    }

    /**
     * Delete order by order id
     *
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
        } finally {
            ConnectionPool.getInstance().closeConnection(connection);
        }
        logger.info("Order by id " + orderId + " was deleted: " + isDelete);
        return isDelete;
    }

    /**
     * Find order by order id
     *
     * @param orderId
     * @return false if Order was not finded. If find success true.
     */
    @Override
    public Order findOrderById(int orderId) {
        Order order = null;
        try (PreparedStatement statement = connection.prepareStatement(SQLOrder.FINDBYID.QUERY)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("userId");
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
                boolean payment = resultSet.getBoolean("payment");

                order = new Order.OrderBuilder().setId(orderId)
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
                        .setPrice(price)
                        .setPayment(payment).build();
            }
        } catch (SQLException e) {
            logger.error("Invalid connection. ");
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().closeConnection(connection);
        }
        logger.info("Order by id " + orderId + " was finded: " + order);
        return order;
    }

    /**
     * Update payment in Order by order id
     *
     * @param orderId
     * @return false if Payment was not updated. If updating success true.
     */
    @Override
    public boolean updatePaymentByOrderId(Integer orderId, boolean isPayment) {
        boolean isUpdate = false;
        try (PreparedStatement statement = connection.prepareStatement(SQLOrder.UPDATEPAYMENTBYORDERID.QUERY)) {
            statement.setBoolean(1, isPayment);
            statement.setInt(2, orderId);
            isUpdate = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Invalid connection. ");
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().closeConnection(connection);
        }
        logger.info("Order: " + orderId + " was updated");
        return isUpdate;
    }

    enum SQLOrder {

        SAVE("INSERT INTO orders (id, userId , createDate, cityFrom, cityTo, orderType, weight, startDate, endDate, recipient, \n" +
                "                      recipientPhone, deliveryAddress, price, payment) \n" +
                "VALUES (DEFAULT, (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?));"),
        FINDALLBYUSERID("SELECT * FROM orders WHERE userId = (?)"),
        DELETEBYID("DELETE FROM orders WHERE id = (?)"),
        FINDBYID("SELECT * FROM orders WHERE id = (?)"),
        UPDATEPAYMENTBYORDERID("UPDATE orders SET payment = (?) WHERE id=(?);");

        String QUERY;

        SQLOrder(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}