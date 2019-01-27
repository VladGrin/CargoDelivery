package com.cargodelivery.repository.impl;

import com.cargodelivery.configconnection.MySQLConfiguration;
import com.cargodelivery.model.User;
import com.cargodelivery.repository.UserRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author : Volodymyr Hrinchenko
 */
public class MySQLUserRepository implements UserRepository {
    /**
     * Logger log4j
     */
    private final static Logger logger = Logger.getLogger(MySQLUserRepository.class);
    /**
     * Connection of database
     */
    private Connection connection;
    /**
     * Create empty constructor
     * Init database connection by MySQL connection
     */
    public MySQLUserRepository() {
        this.connection = new MySQLConfiguration().getConnection();
    }
    /**
     * Init database connection
     * @param connection of database
     */
    public MySQLUserRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Create/save user in database
     *
     * @param user for create
     * @return false if User already exist. If creating success true.
     */
    @Override
    public boolean save(User user) {
        boolean isSave = false;
        try (PreparedStatement statement = connection.prepareStatement(SQLUser.SAVE.QUERY)) {
            saveUserToStatement(user, statement);
            isSave = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Invalid connection. ");
            e.printStackTrace();
        }
        logger.info("User: " + user + " was saved to database");
        return isSave;
    }

    private void saveUserToStatement(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getName());
        statement.setString(2, user.getSurname());
        statement.setString(3, user.getCity());
        statement.setString(4, user.getPhone());
        statement.setString(5, user.getMail());
        statement.setString(6, user.getPassword());
        statement.setInt(7, user.getRole().ordinal());
    }

    /**
     * Find User by id
     *
     * @param id for find
     * @return entity User if it exist. If entity does not exist return null.
     */
    @Override
    public User findById(Integer id) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SQLUser.FINDBYID.QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = toUser(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Invalid connection. ");
            e.printStackTrace();
        }
        logger.info("User was found from database : " + user);
        return user;
    }

    private User toUser(ResultSet resultSet) throws SQLException {
        return new User.UserBuilder()
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("name"))
                .setSurname(resultSet.getString("surname"))
                .setCity(resultSet.getString("city"))
                .setPhone(resultSet.getString("phone"))
                .setMail(resultSet.getString("mail"))
                .setPassword(resultSet.getString("password"))
                .setRoleByOrderNumber(resultSet.getInt("role"))
                .build();
    }

    /**
     * Find User by login
     *
     * @param login for find
     * @return entity User if it exist. If entity does not exist return null.
     */
    @Override
    public User findByLogin(String login) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SQLUser.FINDBYLOGIN.QUERY)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = toUser(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Invalid connection. ");
            e.printStackTrace();
        }
        logger.info("User was found from database : " + user);
        return user;
    }

    @Override
    public boolean existsUser(String login, String password) {
        User user = findByLogin(login);
        return user != null && user.getPassword().equals(password);
    }

    enum SQLUser {
        SAVE("INSERT INTO users (id, name , surname, city, phone, mail, password, role)\n" +
                "VALUES (DEFAULT, (?), (?), (?), (?), (?), (?), (?));"),
        FINDBYID("SELECT u.id, u.name, u.surname, u.city, u.phone, u.mail, u.password, u.role\n" +
                "FROM users AS u\n" +
                "WHERE u.id = (?);"),
        FINDBYLOGIN("SELECT u.id, u.name, u.surname, u.city, u.phone, u.mail, u.password, u.role\n" +
                "FROM users AS u\n" +
                "WHERE u.mail = (?)");

        String QUERY;

        SQLUser(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
