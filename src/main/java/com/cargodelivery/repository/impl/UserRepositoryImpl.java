package com.cargodelivery.repository.impl;

import com.cargodelivery.model.User;
import com.cargodelivery.repository.UserRepository;
import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author : Volodymyr Hrinchenko
 */
public class UserRepositoryImpl implements UserRepository {
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
    public UserRepositoryImpl(Connection connection) {
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
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getCity());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getMail());
            statement.setString(6, user.getPassword());
            isSave = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Invalid connection. ");
            e.printStackTrace();
        }
        logger.info("User was saved to database : " + isSave);
        return isSave;
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
                user = new User.UserBuilder().setId(id)
                        .setName(resultSet.getString("name"))
                        .setSurname(resultSet.getString("surname"))
                        .setCity(resultSet.getString("city"))
                        .setPhone(resultSet.getString("phone"))
                        .setMail(resultSet.getString("mail"))
                        .setPassword(resultSet.getString("password")).build();
            }
        } catch (SQLException e) {
            logger.error("Invalid connection. ");
            e.printStackTrace();
        }
        logger.info("User was found from database : " + user);
        return user;
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
                user = new User.UserBuilder().setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setSurname(resultSet.getString("surname"))
                        .setCity(resultSet.getString("city"))
                        .setPhone(resultSet.getString("phone"))
                        .setMail(login)
                        .setPassword(resultSet.getString("password")).build();
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
        SAVE("INSERT INTO users (id, name , surname, city, phone, mail, password)\n" +
                "VALUES (DEFAULT, (?), (?), (?), (?), (?), (?));"),
        FINDBYLOGIN("SELECT u.id, u.name, u.surname, u.city, u.phone, u.password\n" +
                "FROM users AS u\n" +
                "WHERE u.mail = (?)"),
        FINDBYID("SELECT u.name, u.surname, u.city, u.phone, u.mail, u.password\n" +
                "FROM users AS u\n" +
                "WHERE u.id = (?);");

        String QUERY;

        SQLUser(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
